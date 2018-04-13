package qsbk.app.live.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class LiveMarketDataRecordResponse {
    @SerializedName(alternate = {"jse"}, value = "items")
    public List<LiveMarketDataRecord> items;
    @SerializedName(alternate = {"t"}, value = "template")
    public Map<String, String> template;
}
