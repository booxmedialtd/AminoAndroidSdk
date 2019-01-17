package com.aminocom.sdk.provider;

import com.aminocom.sdk.model.client.Group;

import java.util.List;

import io.reactivex.Flowable;

public interface RecordingGroupProvider {
    Flowable<List<Group>> getGroups();

    Flowable<List<Group>> getGroupRecordings(String groupId);
}