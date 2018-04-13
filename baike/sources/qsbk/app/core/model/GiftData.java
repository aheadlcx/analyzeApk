package qsbk.app.core.model;

import android.animation.AnimatorSet;
import com.google.gson.annotations.SerializedName;

public class GiftData {
    public String an;
    public long c;
    public boolean cb;
    @SerializedName(alternate = {"de"}, value = "desc")
    public String desc;
    public FrameAnimationData[] ga;
    public FrameAnimationData[] gb;
    public long gd;
    public String gn;
    public String ig;
    public transient AnimatorSet mAnimator;
    public long pr;
    public int s;
    public boolean selected;
}
