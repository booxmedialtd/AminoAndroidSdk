package com.aminocom.sdk;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class ObservableList<T> {
    protected final List<T> list;
    private final BehaviorSubject<List<T>> onUpdateItems;

    ObservableList() {
        this.list = new ArrayList<>();
        this.onUpdateItems = BehaviorSubject.create();
    }

    public void setItems(List<T> items) {
        list.clear();
        list.addAll(items);

        onUpdateItems.onNext(items);
    }

    Observable<List<T>> getObservable() {
        return onUpdateItems;
    }
}