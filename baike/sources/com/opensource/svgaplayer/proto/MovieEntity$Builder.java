package com.opensource.svgaplayer.proto;

import com.squareup.wire.Message.Builder;
import com.squareup.wire.internal.Internal;
import java.util.List;
import java.util.Map;
import okio.ByteString;

public final class MovieEntity$Builder extends Builder<MovieEntity, MovieEntity$Builder> {
    public Map<String, ByteString> images = Internal.newMutableMap();
    public MovieParams params;
    public List<SpriteEntity> sprites = Internal.newMutableList();
    public String version;

    public MovieEntity$Builder version(String str) {
        this.version = str;
        return this;
    }

    public MovieEntity$Builder params(MovieParams movieParams) {
        this.params = movieParams;
        return this;
    }

    public MovieEntity$Builder images(Map<String, ByteString> map) {
        Internal.checkElementsNotNull((Map) map);
        this.images = map;
        return this;
    }

    public MovieEntity$Builder sprites(List<SpriteEntity> list) {
        Internal.checkElementsNotNull((List) list);
        this.sprites = list;
        return this;
    }

    public MovieEntity build() {
        return new MovieEntity(this.version, this.params, this.images, this.sprites, super.buildUnknownFields());
    }
}
