package qsbk.app.core.model;

import com.google.gson.annotations.SerializedName;

public class GiftCount {
    @SerializedName(alternate = {"c"}, value = "count")
    public int count;
    @SerializedName(alternate = {"g"}, value = "giftId")
    public long giftId;
}
