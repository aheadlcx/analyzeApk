package com.opensource.svgaplayer.proto;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import java.io.IOException;

final class MovieParams$a extends ProtoAdapter<MovieParams> {
    public MovieParams$a() {
        super(FieldEncoding.LENGTH_DELIMITED, MovieParams.class);
    }

    public int encodedSize(MovieParams movieParams) {
        return (((ProtoAdapter.FLOAT.encodedSizeWithTag(1, movieParams.viewBoxWidth) + ProtoAdapter.FLOAT.encodedSizeWithTag(2, movieParams.viewBoxHeight)) + ProtoAdapter.INT32.encodedSizeWithTag(3, movieParams.fps)) + ProtoAdapter.INT32.encodedSizeWithTag(4, movieParams.frames)) + movieParams.unknownFields().size();
    }

    public void encode(ProtoWriter protoWriter, MovieParams movieParams) throws IOException {
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, movieParams.viewBoxWidth);
        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, movieParams.viewBoxHeight);
        ProtoAdapter.INT32.encodeWithTag(protoWriter, 3, movieParams.fps);
        ProtoAdapter.INT32.encodeWithTag(protoWriter, 4, movieParams.frames);
        protoWriter.writeBytes(movieParams.unknownFields());
    }

    public MovieParams decode(ProtoReader protoReader) throws IOException {
        MovieParams$Builder movieParams$Builder = new MovieParams$Builder();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        movieParams$Builder.viewBoxWidth((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 2:
                        movieParams$Builder.viewBoxHeight((Float) ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 3:
                        movieParams$Builder.fps((Integer) ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 4:
                        movieParams$Builder.frames((Integer) ProtoAdapter.INT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                        movieParams$Builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
            protoReader.endMessage(beginMessage);
            return movieParams$Builder.build();
        }
    }

    public MovieParams redact(MovieParams movieParams) {
        MovieParams$Builder newBuilder = movieParams.newBuilder();
        newBuilder.clearUnknownFields();
        return newBuilder.build();
    }
}
