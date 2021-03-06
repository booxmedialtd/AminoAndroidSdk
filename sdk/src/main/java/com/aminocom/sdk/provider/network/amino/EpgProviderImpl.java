package com.aminocom.sdk.provider.network.amino;

import com.aminocom.sdk.provider.local.LocalProvider;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.mapper.EpgMapper;
import com.aminocom.sdk.mapper.ProgramMapper;
import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.provider.network.EpgProvider;
import com.aminocom.sdk.settings.Settings;
import com.aminocom.sdk.util.DateUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class EpgProviderImpl implements EpgProvider {

    private static final int INITIAL_EPG_PAGE = 0;
    private static final int MAX_EPG_PAGE = 20;
    private static final int RECORDING_PAGE_NUMBER = 1;

    private ServerApi api;
    private LocalProvider localRepository;
    private String service;
    private Settings settings;

    private long epgCacheTime = 0;

    public static EpgProvider newInstance(ServerApi api, LocalProvider localRepository, String service, Settings settings) {
        return new EpgProviderImpl(api, localRepository, service, settings);
    }

    private EpgProviderImpl(ServerApi api, LocalProvider localRepository, String service, Settings settings) {
        this.api = api;
        this.localRepository = localRepository;
        this.service = service;
        this.settings = settings;
    }

    @Override
    public Flowable<List<Epg>> getTodayEpg() {
        long todayDate = System.currentTimeMillis();

        return getEpg(todayDate, todayDate);
    }

    @Override
    public Flowable<List<Epg>> getEpg(long startDate, long endDate) {
        return localRepository.getChannels()
                .flatMapSingle(channels -> Flowable.fromIterable(channels)
                        .flatMap(channel -> localRepository.getEpgPrograms(channel.getId(), startDate, endDate)
                                .map(programs -> EpgMapper.from(channel, programs))
                        )
                        .toList()
                );
    }

    // TODO: Add saving of a loaded date and checking of current loaded date to decrease server load
    // TODO: decrease number of connections to the DB
    @Override
    public Single<Boolean> loadEpg(long startDate, long endDate) {
        final long start = DateUtil.getTvDayStartTime(startDate);
        final long end = DateUtil.getTvDayEndTime(endDate);

        if (System.currentTimeMillis() - settings.getEpgLoadedTime() > settings.getCacheTtlManager().getEpgTtl()) {
            return Flowable.range(INITIAL_EPG_PAGE, MAX_EPG_PAGE)
                    .flatMapSingle(page -> api.getEpg(service, DateUtil.getTimeInSeconds(start), DateUtil.getTimeInSeconds(end), page))
                    .takeUntil(response -> response.resultSet.currentPage == response.resultSet.totalPages - 1)
                    .flatMapIterable(response -> response.channels)
                    .map(response -> ProgramMapper.from(response.id, response.programs))
                    .doOnNext(epgList -> {
                        localRepository.cachePrograms(epgList);
                        settings.setEpgLoadedTime(System.currentTimeMillis());
                    })
                    .buffer(1000)
                    .flatMapSingle(response -> api.getRecording(settings.getUserName(), service, DateUtil.getTimeInSeconds(start), DateUtil.getTimeInSeconds(end), RECORDING_PAGE_NUMBER))
                    .flatMap(response -> Flowable.fromIterable(response.recordedContent.programList.programs))
                    .map(ProgramMapper::from)
                    .toList()
                    .doOnSuccess(programs ->
                            localRepository.updateOrInsertPrograms(programs)
                    )
                    .map(programs -> true);
        } else {
            return Single.just(true);
        }
    }
}