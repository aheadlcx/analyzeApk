package com.opensource.svgaplayer.proto;

import com.squareup.wire.Message.Builder;

public final class MovieParams$Builder extends Builder<MovieParams, MovieParams$Builder> {
    public Integer fps;
    public Integer frames;
    public Float viewBoxHeight;
    public Float viewBoxWidth;

    public MovieParams$Builder viewBoxWidth(Float f) {
        this.viewBoxWidth = f;
        return this;
    }

    public MovieParams$Builder viewBoxHeight(Float f) {
        this.viewBoxHeight = f;
        return this;
    }

    public MovieParams$Builder fps(Integer num) {
        this.fps = num;
        return this;
    }

    public MovieParams$Builder frames(Integer num) {
        this.frames = num;
        return this;
    }

    public MovieParams build() {
        return new MovieParams(this.viewBoxWidth, this.viewBoxHeight, this.fps, this.frames, super.buildUnknownFields());
    }
}
