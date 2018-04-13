package com.squareup.wire;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

public abstract class Message<M extends Message<M, B>, B extends Builder<M, B>> implements Serializable {
    private final transient ProtoAdapter<M> a;
    private final transient ByteString b;
    transient int e = 0;
    protected transient int f = 0;

    public static abstract class Builder<M extends Message<M, B>, B extends Builder<M, B>> {
        transient ByteString e = ByteString.EMPTY;
        transient Buffer f;
        transient ProtoWriter h;

        public abstract M build();

        protected Builder() {
        }

        public final Builder<M, B> addUnknownFields(ByteString byteString) {
            if (byteString.size() > 0) {
                a();
                try {
                    this.h.writeBytes(byteString);
                } catch (IOException e) {
                    throw new AssertionError();
                }
            }
            return this;
        }

        public final Builder<M, B> addUnknownField(int i, FieldEncoding fieldEncoding, Object obj) {
            a();
            try {
                fieldEncoding.rawProtoAdapter().encodeWithTag(this.h, i, obj);
                return this;
            } catch (IOException e) {
                throw new AssertionError();
            }
        }

        public final Builder<M, B> clearUnknownFields() {
            this.e = ByteString.EMPTY;
            if (this.f != null) {
                this.f.clear();
                this.f = null;
            }
            this.h = null;
            return this;
        }

        public final ByteString buildUnknownFields() {
            if (this.f != null) {
                this.e = this.f.readByteString();
                this.f = null;
                this.h = null;
            }
            return this.e;
        }

        private void a() {
            if (this.f == null) {
                this.f = new Buffer();
                this.h = new ProtoWriter(this.f);
                try {
                    this.h.writeBytes(this.e);
                    this.e = ByteString.EMPTY;
                } catch (IOException e) {
                    throw new AssertionError();
                }
            }
        }
    }

    public abstract Builder<M, B> newBuilder();

    protected Message(ProtoAdapter<M> protoAdapter, ByteString byteString) {
        if (protoAdapter == null) {
            throw new NullPointerException("adapter == null");
        } else if (byteString == null) {
            throw new NullPointerException("unknownFields == null");
        } else {
            this.a = protoAdapter;
            this.b = byteString;
        }
    }

    public final ByteString unknownFields() {
        ByteString byteString = this.b;
        return byteString != null ? byteString : ByteString.EMPTY;
    }

    public final M withoutUnknownFields() {
        return newBuilder().clearUnknownFields().build();
    }

    public String toString() {
        return this.a.toString(this);
    }

    public final ProtoAdapter<M> adapter() {
        return this.a;
    }

    public final void encode(BufferedSink bufferedSink) throws IOException {
        this.a.encode(bufferedSink, (Object) this);
    }

    public final byte[] encode() {
        return this.a.encode(this);
    }

    public final void encode(OutputStream outputStream) throws IOException {
        this.a.encode(outputStream, (Object) this);
    }
}
