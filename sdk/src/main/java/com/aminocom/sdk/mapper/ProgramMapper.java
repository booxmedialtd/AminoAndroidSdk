package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.model.network.epg.EpgProgramItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProgramMapper {
    private ProgramMapper() {
    }

    public static List<Program> from(String channelId, List<EpgProgramItem> programs) {
        List<Program> result = new ArrayList<>();

        for (EpgProgramItem item : programs) {
            result.add(from(channelId, item));
        }

        return result;
    }

    public static Program from(String channelId, EpgProgramItem item) {
        Program result = new Program();

        result.setTitle(item.title);
        result.setDescription(item.description);
        result.setStartTime(TimeUnit.SECONDS.toMillis(item.startTime));
        result.setEndTime(result.getStartTime() + TimeUnit.SECONDS.toMillis(item.duration));
        result.setDuration(item.duration);
        result.setShowId(item.showId);
        result.setProgramUId(item.programUId);
        result.setDvbInformation(item.dvbInformation);
        result.setChannelId(channelId);

        if (item.services.recording != null) {
            result.setRecording(ServiceStateMapper.from(item.services.recording));
        }

        if (item.services.catchup != null) {
            result.setCatchUp(ServiceStateMapper.from(item.services.catchup));
        }

        if (item.externalInformation != null) {
            result.setExternalInformation(ExternalInformationMapper.from(item.externalInformation));
        }

        return result;
    }
}