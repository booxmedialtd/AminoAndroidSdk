package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.Category;
import com.aminocom.sdk.model.client.Program;
import com.aminocom.sdk.model.network.category.CategoryListItem;

import java.util.List;

public class CategoryMapper {
    private CategoryMapper() {
    }

    public static Category from(CategoryListItem item, List<Program> programs) {
        Category category = new Category();

        category.setId(item.id);
        category.setTitle(item.title);
        category.setThumbnail(item.thumbnailUrl);
        category.setType(item.categoryType);
        category.setPrograms(programs);

        return category;
    }
}