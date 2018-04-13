package qsbk.app.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class c implements Creator<ArticleListConfig> {
    c() {
    }

    public ArticleListConfig createFromParcel(Parcel parcel) {
        ArticleListConfig articleListConfig = new ArticleListConfig();
        articleListConfig.mIsShuffle = parcel.readByte() == (byte) 0;
        articleListConfig.mTitle = parcel.readString();
        articleListConfig.mUniqueName = parcel.readString();
        articleListConfig.mUrl = parcel.readString();
        return articleListConfig;
    }

    public ArticleListConfig[] newArray(int i) {
        return new ArticleListConfig[i];
    }
}
