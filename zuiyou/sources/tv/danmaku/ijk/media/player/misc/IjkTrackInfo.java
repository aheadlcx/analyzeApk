package tv.danmaku.ijk.media.player.misc;

import android.text.TextUtils;
import tv.danmaku.ijk.media.player.IjkMediaMeta.IjkStreamMeta;

public class IjkTrackInfo implements ITrackInfo {
    private IjkStreamMeta mStreamMeta;
    private int mTrackType = 0;

    public IjkTrackInfo(IjkStreamMeta ijkStreamMeta) {
        this.mStreamMeta = ijkStreamMeta;
    }

    public void setMediaMeta(IjkStreamMeta ijkStreamMeta) {
        this.mStreamMeta = ijkStreamMeta;
    }

    public IMediaFormat getFormat() {
        return new IjkMediaFormat(this.mStreamMeta);
    }

    public String getLanguage() {
        if (this.mStreamMeta == null || TextUtils.isEmpty(this.mStreamMeta.mLanguage)) {
            return "und";
        }
        return this.mStreamMeta.mLanguage;
    }

    public int getTrackType() {
        return this.mTrackType;
    }

    public void setTrackType(int i) {
        this.mTrackType = i;
    }

    public String toString() {
        return getClass().getSimpleName() + '{' + getInfoInline() + "}";
    }

    public String getInfoInline() {
        StringBuilder stringBuilder = new StringBuilder(128);
        switch (this.mTrackType) {
            case 1:
                stringBuilder.append("VIDEO");
                stringBuilder.append(", ");
                stringBuilder.append(this.mStreamMeta.getCodecShortNameInline());
                stringBuilder.append(", ");
                stringBuilder.append(this.mStreamMeta.getBitrateInline());
                stringBuilder.append(", ");
                stringBuilder.append(this.mStreamMeta.getResolutionInline());
                break;
            case 2:
                stringBuilder.append("AUDIO");
                stringBuilder.append(", ");
                stringBuilder.append(this.mStreamMeta.getCodecShortNameInline());
                stringBuilder.append(", ");
                stringBuilder.append(this.mStreamMeta.getBitrateInline());
                stringBuilder.append(", ");
                stringBuilder.append(this.mStreamMeta.getSampleRateInline());
                break;
            case 3:
                stringBuilder.append("TIMEDTEXT");
                stringBuilder.append(", ");
                stringBuilder.append(this.mStreamMeta.mLanguage);
                break;
            case 4:
                stringBuilder.append("SUBTITLE");
                break;
            default:
                stringBuilder.append("UNKNOWN");
                break;
        }
        return stringBuilder.toString();
    }
}
