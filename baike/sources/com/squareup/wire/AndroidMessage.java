package com.squareup.wire;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.squareup.wire.Message.Builder;
import java.lang.reflect.Array;
import okio.ByteString;

public abstract class AndroidMessage<M extends Message<M, B>, B extends Builder<M, B>> extends Message<M, B> implements Parcelable {

    private static final class a<M> implements Creator<M> {
        private final ProtoAdapter<M> a;

        a(ProtoAdapter<M> protoAdapter) {
            this.a = protoAdapter;
        }

        public M createFromParcel(Parcel parcel) {
            try {
                return this.a.decode(parcel.createByteArray());
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public M[] newArray(int i) {
            return (Object[]) Array.newInstance(this.a.a, i);
        }
    }

    public static <E> Creator<E> newCreator(ProtoAdapter<E> protoAdapter) {
        return new a(protoAdapter);
    }

    protected AndroidMessage(ProtoAdapter<M> protoAdapter, ByteString byteString) {
        super(protoAdapter, byteString);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(encode());
    }

    public final int describeContents() {
        return 0;
    }
}
