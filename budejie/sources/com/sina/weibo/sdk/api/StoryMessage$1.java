package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class StoryMessage$1 implements Creator<StoryMessage> {
    StoryMessage$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public StoryMessage a(Parcel parcel) {
        return new StoryMessage(parcel);
    }

    public StoryMessage[] a(int i) {
        return new StoryMessage[i];
    }
}
