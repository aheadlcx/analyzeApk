package com.opensource.svgaplayer.proto;

import android.os.Parcelable.Creator;
import com.squareup.wire.AndroidMessage;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireField;
import com.squareup.wire.WireField.Label;
import com.squareup.wire.internal.Internal;
import java.util.List;
import java.util.Map;
import okio.ByteString;

public final class MovieEntity extends AndroidMessage<MovieEntity, MovieEntity$Builder> {
    public static final ProtoAdapter<MovieEntity> ADAPTER = new MovieEntity$a();
    public static final Creator<MovieEntity> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final String DEFAULT_VERSION = "";
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BYTES", keyAdapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final Map<String, ByteString> images;
    @WireField(adapter = "com.opensource.svgaplayer.proto.MovieParams#ADAPTER", tag = 2)
    public final MovieParams params;
    @WireField(adapter = "com.opensource.svgaplayer.proto.SpriteEntity#ADAPTER", label = Label.REPEATED, tag = 4)
    public final List<SpriteEntity> sprites;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String version;

    public MovieEntity(String str, MovieParams movieParams, Map<String, ByteString> map, List<SpriteEntity> list) {
        this(str, movieParams, map, list, ByteString.EMPTY);
    }

    public MovieEntity(String str, MovieParams movieParams, Map<String, ByteString> map, List<SpriteEntity> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.version = str;
        this.params = movieParams;
        this.images = Internal.immutableCopyOf("images", map);
        this.sprites = Internal.immutableCopyOf("sprites", list);
    }

    public MovieEntity$Builder newBuilder() {
        MovieEntity$Builder movieEntity$Builder = new MovieEntity$Builder();
        movieEntity$Builder.version = this.version;
        movieEntity$Builder.params = this.params;
        movieEntity$Builder.images = Internal.copyOf("images", this.images);
        movieEntity$Builder.sprites = Internal.copyOf("sprites", this.sprites);
        movieEntity$Builder.addUnknownFields(unknownFields());
        return movieEntity$Builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MovieEntity)) {
            return false;
        }
        MovieEntity movieEntity = (MovieEntity) obj;
        if (unknownFields().equals(movieEntity.unknownFields()) && Internal.equals(this.version, movieEntity.version) && Internal.equals(this.params, movieEntity.params) && this.images.equals(movieEntity.images) && this.sprites.equals(movieEntity.sprites)) {
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
        i2 = ((this.version != null ? this.version.hashCode() : 0) + (unknownFields().hashCode() * 37)) * 37;
        if (this.params != null) {
            i = this.params.hashCode();
        }
        i2 = ((((i2 + i) * 37) + this.images.hashCode()) * 37) + this.sprites.hashCode();
        this.f = i2;
        return i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.version != null) {
            stringBuilder.append(", version=").append(this.version);
        }
        if (this.params != null) {
            stringBuilder.append(", params=").append(this.params);
        }
        if (!this.images.isEmpty()) {
            stringBuilder.append(", images=").append(this.images);
        }
        if (!this.sprites.isEmpty()) {
            stringBuilder.append(", sprites=").append(this.sprites);
        }
        return stringBuilder.replace(0, 2, "MovieEntity{").append('}').toString();
    }
}
