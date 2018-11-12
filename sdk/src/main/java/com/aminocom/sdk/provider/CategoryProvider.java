package com.aminocom.sdk.provider;

import com.aminocom.sdk.model.client.Category;

import java.util.List;

import io.reactivex.Observable;

public interface CategoryProvider {
    Observable<List<Category>> getCategories();
}