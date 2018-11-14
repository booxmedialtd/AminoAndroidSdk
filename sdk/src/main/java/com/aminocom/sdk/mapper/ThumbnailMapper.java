package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.Thumbnail;
import com.aminocom.sdk.model.network.ThumbnailElement;

import java.util.ArrayList;
import java.util.List;

class ThumbnailMapper {
    private ThumbnailMapper() {
    }

    static List<Thumbnail> from(List<ThumbnailElement> elements) {
        List<Thumbnail> result = new ArrayList<>();

        for (ThumbnailElement element : elements) {
            result.add(from(element));
        }

        return result;
    }

    private static Thumbnail from(ThumbnailElement element) {
        Thumbnail result = new Thumbnail();

        result.setUrl(element.url);
        result.setWidth(element.width);
        result.setHeight(element.height);

        return result;
    }
}