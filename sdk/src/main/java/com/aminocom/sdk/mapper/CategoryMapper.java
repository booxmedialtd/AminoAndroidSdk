package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.client.CategoryProgram;
import com.aminocom.sdk.model.network.category.CategoryListItem;

import java.util.List;

public class CategoryMapper {
    private CategoryMapper() {
    }

    public static Category from(CategoryListItem categoryItem, List<CategoryProgram> programs) {
        Category result = new Category();

        result.setId(categoryItem.id);
        result.setTitle(categoryItem.title);
        result.setThumbnail(categoryItem.thumbnailUrl);
        result.setType(categoryItem.categoryType);
        result.setPrograms(programs);

        return result;
    }
}