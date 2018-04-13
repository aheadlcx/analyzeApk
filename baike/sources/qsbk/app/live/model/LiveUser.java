package qsbk.app.live.model;

import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LiveUser {
    @JsonProperty("a")
    public String av;
    public String b;
    public int fl;
    public long i;
    public int l;
    public String lb;
    public int ld;
    public int lr;
    public int m;
    public String n;
    public int or;
    public List<String> pu;
    public int s;
    public String t;

    @JsonIgnore
    public boolean isValid() {
        return !TextUtils.isEmpty(this.n);
    }

    @JsonIgnore
    public boolean showFamilyEnterEffect() {
        return this.lr == 1;
    }
}
