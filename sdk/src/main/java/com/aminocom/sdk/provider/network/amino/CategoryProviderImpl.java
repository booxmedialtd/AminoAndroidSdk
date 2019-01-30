package com.aminocom.sdk.provider.network.amino;

import android.util.Log;

import com.aminocom.sdk.provider.local.LocalProvider;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.mapper.CategoryMapper;
import com.aminocom.sdk.mapper.CategoryProgramMapper;
import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.network.category.CategoryListItem;
import com.aminocom.sdk.provider.network.CategoryProvider;
import com.aminocom.sdk.settings.Settings;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class CategoryProviderImpl implements CategoryProvider {
    private static final String TAG = CategoryProviderImpl.class.getSimpleName();

    private ServerApi api;
    private LocalProvider localRepository;
    private String service;
    private Settings settings;

    public static CategoryProvider newInstance(ServerApi api, LocalProvider localRepository, String service, Settings settings) {
        return new CategoryProviderImpl(api, localRepository, service, settings);
    }

    private CategoryProviderImpl(ServerApi api, LocalProvider localRepository, String service, Settings settings) {
        this.api = api;
        this.localRepository = localRepository;
        this.service = service;
        this.settings = settings;
    }

    @Override
    public Flowable<List<Category>> getCategories() {
        if (System.currentTimeMillis() - settings.getCategoryLoadedTime() > settings.getCacheTtlManager().getCategoryTtl()) {
            return api.getCategoryList(service)
                    .toObservable()
                    .flatMapIterable(response -> response.categoryList.categories)
                    .flatMapSingle(this::getCategory)
                    .toList()
                    .doOnSuccess(categories -> {
                        localRepository.cacheCategories(categories);
                        settings.setCategoryLoadedTime(System.currentTimeMillis());
                    })
                    .flatMapPublisher(list -> localRepository.getCategories());
        } else {
            return localRepository.getCategories();
        }
    }

    private Single<Category> getCategory(CategoryListItem item) {
        return api.getCategory(item.epgUrl)
                .doOnError(t -> Log.e(TAG, "Failed to load category: " + item.title))
                .toObservable()
                .onExceptionResumeNext(Observable.empty())
                .filter(response -> response.epg.resultSet.totalItems > 0)
                .flatMapIterable(categoryResponse -> categoryResponse.epg.programList.programs)
                .map(CategoryProgramMapper::from)
                .toList()
                .map(programs -> CategoryMapper.from(item, programs));
    }
}
