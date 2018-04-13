package com.opensource.svgaplayer.proto;

import android.os.Parcelable.Creator;
import com.squareup.wire.AndroidMessage;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import okio.ByteString;

public final class MovieParams extends AndroidMessage<MovieParams, MovieParams$Builder> {
    public static final ProtoAdapter<MovieParams> ADAPTER = new MovieParams$a();
    public static final Creator<MovieParams> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_FPS = Integer.valueOf(0);
    public static final Integer DEFAULT_FRAMES = Integer.valueOf(0);
    public static final Float DEFAULT_VIEWBOXHEIGHT = Float.valueOf(0.0f);
    public static final Float DEFAULT_VIEWBOXWIDTH = Float.valueOf(0.0f);
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 3)
    public final Integer fps;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 4)
    public final Integer frames;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)
    public final Float viewBoxHeight;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)
    public final Float viewBoxWidth;

    public MovieParams(Float f, Float f2, Integer num, Integer num2) {
        this(f, f2, num, num2, ByteString.EMPTY);
    }

    public MovieParams(Float f, Float f2, Integer num, Integer num2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.viewBoxWidth = f;
        this.viewBoxHeight = f2;
        this.fps = num;
        this.frames = num2;
    }

    public MovieParams$Builder newBuilder() {
        MovieParams$Builder movieParams$Builder = new MovieParams$Builder();
        movieParams$Builder.viewBoxWidth = this.viewBoxWidth;
        movieParams$Builder.viewBoxHeight = this.viewBoxHeight;
        movieParams$Builder.fps = this.fps;
        movieParams$Builder.frames = this.frames;
        movieParams$Builder.addUnknownFields(unknownFields());
        return movieParams$Builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MovieParams)) {
            return false;
        }
        MovieParams movieParams = (MovieParams) obj;
        if (unknownFields().equals(movieParams.unknownFields()) && Internal.equals(this.viewBoxWidth, movieParams.viewBoxWidth) && Internal.equals(this.viewBoxHeight, movieParams.viewBoxHeight) && Internal.equals(this.fps, movieParams.fps) && Internal.equals(this.frames, movieParams.frames)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int i2 = this.f;
        if (i2 != 0) {
            return i2;
        }
        int hashCode = ((this.viewBoxWidth != null ? this.viewBoxWidth.hashCode() : 0) + (unknownFields().hashCode() * 37)) * 37;
        if (this.viewBoxHeight != null) {
            i2 = this.viewBoxHeight.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.fps != null) {
            i2 = this.fps.hashCode();
        } else {
            i2 = 0;
        }
        i2 = (i2 + hashCode) * 37;
        if (this.frames != null) {
            i = this.frames.hashCode();
        }
        i2 += i;
        this.f = i2;
        return i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.viewBoxWidth != null) {
            stringBuilder.append(", viewBoxWidth=").append(this.viewBoxWidth);
        }
        if (this.viewBoxHeight != null) {
            stringBuilder.append(", viewBoxHeight=").append(this.viewBoxHeight);
        }
        if (this.fps != null) {
            stringBuilder.append(", fps=").append(this.fps);
        }
        if (this.frames != null) {
            stringBuilder.append(", frames=").append(this.frames);
        }
        return stringBuilder.replace(0, 2, "MovieParams{").append('}').toString();
    }
}
