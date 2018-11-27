package com.aminocom.sdk.provider.amino;

import android.util.Log;

import com.aminocom.sdk.CacheTTLConfig;
import com.aminocom.sdk.LocalRepository;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.mapper.EpgMapper;
import com.aminocom.sdk.mapper.ProgramMapper;
import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.provider.EpgProvider;
import com.aminocom.sdk.util.DateUtil;

import java.util.Calendar;
import java.util.List;

import io.reactivex.Flowable;

public class EpgProviderImpl implements EpgProvider {

    private static final int INITIAL_EPG_PAGE = 0;
    private static final int MAX_EPG_PAGE = 20;

    private ServerApi api;
    private LocalRepository localRepository;
    private String service;

    private long epgCacheTime = 0;

    public static EpgProvider newInstance(ServerApi api, LocalRepository localRepository, String service) {
        return new EpgProviderImpl(api, localRepository, service);
    }

    private EpgProviderImpl(ServerApi api, LocalRepository localRepository, String service) {
        this.api = api;
        this.localRepository = localRepository;
        this.service = service;
    }

    @Override
    public Flowable<List<Epg>> getTodayEpg() {
        return getEpg(Calendar.getInstance().getTimeInMillis());
    }

    // TODO: Add saving of a loaded date and checking of current loaded date to decrease server load
    // TODO: decrease number of connections to the DB
    @Override
    public Flowable<List<Epg>> getEpg(long dateInMillis) {
        final long startDate = DateUtil.getTvDayStartTime(dateInMillis);
        final long endDate = DateUtil.getTvDayEndTime(dateInMillis);

        if (System.currentTimeMillis() - epgCacheTime > CacheTTLConfig.CHANNEL_TTL) {
            return loadEpg(startDate, endDate)
                    .flatMap(list -> localRepository.getEpg());
        } else {
            return localRepository.getEpg();
        }
    }

    private Flowable<List<Program>> loadEpg(long startDate, long endDate) {
        return Flowable.range(INITIAL_EPG_PAGE, MAX_EPG_PAGE)
                .flatMapSingle(page -> api.getEpg(service, DateUtil.getTimeInSeconds(startDate), DateUtil.getTimeInSeconds(endDate), page))
                .takeUntil(response -> response.resultSet.currentPage == response.resultSet.totalPages - 1)
                .flatMapIterable(response -> response.channels)
                .map(EpgMapper::from)
                .doOnNext(epgList -> {
                    Log.e("LOG_TAG", "===================== getEpg: epg size: " + epgList.size());
                    localRepository.cachePrograms(epgList);
                    epgCacheTime = System.currentTimeMillis();
                })
                .flatMapSingle(response -> api.getRecording(service, DateUtil.getTimeInSeconds(startDate), DateUtil.getTimeInSeconds(endDate)))
                .flatMap(response -> Flowable.fromIterable(response.recordedContent.programList.programs))
                .map(ProgramMapper::from)
                .toList()
                .doOnSuccess(programs ->
                        localRepository.cachePrograms(programs)
                )
                .toFlowable();
    }
}