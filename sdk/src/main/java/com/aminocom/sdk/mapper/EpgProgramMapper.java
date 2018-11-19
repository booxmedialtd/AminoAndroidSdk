package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.EpgProgram;
import com.aminocom.sdk.model.network.epg.EpgProgramItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EpgProgramMapper {
    private EpgProgramMapper() {
    }

    public static List<EpgProgram> from(List<EpgProgramItem> programs) {
        List<EpgProgram> result = new ArrayList<>();

        for (EpgProgramItem item : programs) {
            result.add(from(item));
        }

        return result;
    }

    public static EpgProgram from(EpgProgramItem item) {
        EpgProgram result = new EpgProgram();

        result.setTitle(item.title);
        result.setDescription(item.description);
        result.setStartTime(TimeUnit.SECONDS.toMillis(item.startTime));
        result.setDuration(item.duration);
        result.setShowId(item.showId);
        result.setProgramUId(item.programUId);
        result.setDvbInformation(item.dvbInformation);

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