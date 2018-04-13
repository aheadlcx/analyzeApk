package qsbk.app.live.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class LiveRedEnvelopesRecordResponse {
    @SerializedName(alternate = {"srec"}, value = "info")
    public LiveRedEnvelopesRecordInfo info;
    @SerializedName(alternate = {"rrec"}, value = "items")
    public List<LiveRedEnvelopesRecord> items;
    @SerializedName(alternate = {"t"}, value = "template")
    public Map<String, String> template;
}
