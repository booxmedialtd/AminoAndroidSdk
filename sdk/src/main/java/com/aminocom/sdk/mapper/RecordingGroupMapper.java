package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.RecordingGroup;
import com.aminocom.sdk.model.network.recording.group.RecordingGroupItem;

import java.util.ArrayList;
import java.util.List;

public class RecordingGroupMapper {
    private RecordingGroupMapper() {
    }

    public static RecordingGroup from(RecordingGroupItem item) {
        RecordingGroup result = new RecordingGroup();

        result.setId(item.id);
        result.setType(item.type);
        result.setProgramsCount(item.programsCount);

        if (item.program != null) {
            result.setThumbnail(item.program.thumbnailUrl);
        }

        return result;
    }

    public static List<RecordingGroup> from(List<RecordingGroupItem> recordingGroups) {
        List<RecordingGroup> result = new ArrayList<>();

        for (RecordingGroupItem item : recordingGroups) {
            result.add(from(item));
        }

        return result;
    }
}