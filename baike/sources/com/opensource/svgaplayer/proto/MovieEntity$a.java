package com.opensource.svgaplayer.proto;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.internal.Internal;
import java.io.IOException;
import java.util.Map;
import okio.ByteString;

final class MovieEntity$a extends ProtoAdapter<MovieEntity> {
    private final ProtoAdapter<Map<String, ByteString>> d = ProtoAdapter.newMapAdapter(ProtoAdapter.STRING, ProtoAdapter.BYTES);

    public MovieEntity$a() {
        super(FieldEncoding.LENGTH_DELIMITED, MovieEntity.class);
    }

    public int encodedSize(MovieEntity movieEntity) {
        return (((ProtoAdapter.STRING.encodedSizeWithTag(1, movieEntity.version) + MovieParams.ADAPTER.encodedSizeWithTag(2, movieEntity.params)) + this.d.encodedSizeWithTag(3, movieEntity.images)) + SpriteEntity.ADAPTER.asRepeated().encodedSizeWithTag(4, movieEntity.sprites)) + movieEntity.unknownFields().size();
    }

    public void encode(ProtoWriter protoWriter, MovieEntity movieEntity) throws IOException {
        ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, movieEntity.version);
        MovieParams.ADAPTER.encodeWithTag(protoWriter, 2, movieEntity.params);
        this.d.encodeWithTag(protoWriter, 3, movieEntity.images);
        SpriteEntity.ADAPTER.asRepeated().encodeWithTag(protoWriter, 4, movieEntity.sprites);
        protoWriter.writeBytes(movieEntity.unknownFields());
    }

    public MovieEntity decode(ProtoReader protoReader) throws IOException {
        MovieEntity$Builder movieEntity$Builder = new MovieEntity$Builder();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        movieEntity$Builder.version((String) ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        movieEntity$Builder.params((MovieParams) MovieParams.ADAPTER.decode(protoReader));
                        break;
                    case 3:
                        movieEntity$Builder.images.putAll((Map) this.d.decode(protoReader));
                        break;
                    case 4:
                        movieEntity$Builder.sprites.add(SpriteEntity.ADAPTER.decode(protoReader));
                        break;
                    default:
                        FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                        movieEntity$Builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
            protoReader.endMessage(beginMessage);
            return movieEntity$Builder.build();
        }
    }

    public MovieEntity redact(MovieEntity movieEntity) {
        MovieEntity$Builder newBuilder = movieEntity.newBuilder();
        if (newBuilder.params != null) {
            newBuilder.params = (MovieParams) MovieParams.ADAPTER.redact(newBuilder.params);
        }
        Internal.redactElements(newBuilder.sprites, SpriteEntity.ADAPTER);
        newBuilder.clearUnknownFields();
        return newBuilder.build();
    }
}
