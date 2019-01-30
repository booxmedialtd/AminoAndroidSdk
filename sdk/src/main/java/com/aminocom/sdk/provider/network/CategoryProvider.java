package com.aminocom.sdk.provider.network;

import com.aminocom.sdk.model.client.Category;

import java.util.List;

import io.reactivex.Flowable;

public interface CategoryProvider {
    Flowable<List<Category>> getCategories();
}