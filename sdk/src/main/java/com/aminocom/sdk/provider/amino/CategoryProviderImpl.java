package com.aminocom.sdk.provider.amino;

import android.util.Log;

import com.aminocom.sdk.CacheTTLConfig;
import com.aminocom.sdk.LocalRepository;
import com.aminocom.sdk.ServerApi;
import com.aminocom.sdk.mapper.CategoryMapper;
import com.aminocom.sdk.mapper.CategoryProgramMapper;
import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.network.category.CategoryListItem;
import com.aminocom.sdk.provider.CategoryProvider;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class CategoryProviderImpl implements CategoryProvider {
    private static final String TAG = CategoryProviderImpl.class.getSimpleName();

    private ServerApi api;
    private LocalRepository localRepository;
    private String service;

    private long categoriesCacheTime = 0;

    public static CategoryProvider newInstance(ServerApi api, LocalRepository localRepository, String service) {
        return new CategoryProviderImpl(api, localRepository, service);
    }

    private CategoryProviderImpl(ServerApi api, LocalRepository localRepository, String service) {
        this.api = api;
        this.localRepository = localRepository;
        this.service = service;
    }

    @Override
    public Flowable<List<Category>> getCategories() {
        if (System.currentTimeMillis() - categoriesCacheTime > CacheTTLConfig.CATEGORY_TTL) {
            return api.getCategoryList(service)
                    .toObservable()
                    .flatMapIterable(response -> response.categoryList.categories)
                    .flatMapSingle(this::getCategory)
                    .toList()
                    .doOnSuccess(categories -> {
                        localRepository.cacheCategories(categories);
                        categoriesCacheTime = System.currentTimeMillis();
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
