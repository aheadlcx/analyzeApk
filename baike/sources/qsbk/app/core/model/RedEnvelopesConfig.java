package qsbk.app.core.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RedEnvelopesConfig {
    @SerializedName(alternate = {"e"}, value = "enable")
    public boolean enable;
    @SerializedName(alternate = {"c"}, value = "globalMinCoin")
    public long globalMinCoin;
    @SerializedName(alternate = {"it"}, value = "items")
    public List<RedEnvelopes> items;
    @SerializedName(alternate = {"p"}, value = "minPrecent")
    public float minPrecent;
    @SerializedName(alternate = {"pos"}, value = "position")
    public String position;
}
