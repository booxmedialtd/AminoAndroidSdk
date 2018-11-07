package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.CategoryProgram;
import com.aminocom.sdk.model.network.category.ProgramItem;

public class CategoryProgramMapper {
    private CategoryProgram CategoryProgramMapper(ProgramItem programItem) {
        CategoryProgram result = new CategoryProgram();

        result.setTitle(programItem.title);
        result.setProgramUid(programItem.programUid);
        result.setStartTime(programItem.startTime);
        result.setDescription(programItem.description);
        result.setDuration(programItem.duration);
        result.setThumbnailUrl(programItem.thumbnailUrl);
        result.setRecordable(programItem.recordable);
        result.setChannelId(programItem.channelId);
        result.setCategoryName(programItem.category);
        result.setCategoryIds(programItem.categoryIds);
        result.setSubCategory(programItem.subCategory);
        result.setCategories(programItem.categories);

        return result;
    }
}