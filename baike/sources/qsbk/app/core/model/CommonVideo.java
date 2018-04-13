package qsbk.app.core.model;

import android.text.TextUtils;
import java.io.Serializable;
import qsbk.app.core.R;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.Constants;
import qsbk.app.core.utils.PreferenceUtils;

public class CommonVideo implements Serializable {
    public static final int CLOSE = 0;
    public static final int RUNNING = 1;
    public static final int SUSPEND = 2;
    public User author;
    public String content;
    public DollData doll_detail;
    public long game_id = 0;
    public String hdl_audio_url;
    public String hdl_live_url;
    public String hdl_side_url;
    public long id;
    public int live_type;
    public String location;
    public int mic_status;
    public String pic_url;
    public long room_id;
    public String rtmp_live_url;
    public Share share;
    public int status = 1;
    public String stream_id;
    public String thumbnail_url;
    public String video_url;
    public long visitors_count;
    public int vote_count;

    public static CommonVideo newInstance(long j) {
        CommonVideo commonVideo = new CommonVideo();
        commonVideo.id = j;
        return commonVideo;
    }

    public String getContent() {
        return this.content;
    }

    public String getPicUrl() {
        return this.pic_url;
    }

    public String getThumbUrl() {
        return this.thumbnail_url;
    }

    public String getLiveUrl() {
        String string = PreferenceUtils.instance().getString("live_play_protocol", Constants.DEFAULT_LIVE_PLAY_PROTOCOL);
        if (TextUtils.isEmpty(this.hdl_live_url) || !Constants.DEFAULT_LIVE_PLAY_PROTOCOL.equalsIgnoreCase(string)) {
            return this.rtmp_live_url;
        }
        return this.hdl_live_url;
    }

    public String getSideUrl() {
        return this.hdl_side_url;
    }

    public String getAudioUrl() {
        return this.hdl_audio_url;
    }

    public String getVoteCount() {
        if (this.vote_count < 1) {
            this.vote_count = 1;
        }
        return Integer.toString(this.vote_count);
    }

    public String getVisitorsCount() {
        if (this.visitors_count < 1) {
            this.visitors_count = 1;
        }
        return Long.toString(this.visitors_count);
    }

    public long getAuthorId() {
        return this.author != null ? this.author.getOriginId() : 0;
    }

    public String getAuthorAvatar() {
        return this.author != null ? this.author.headurl : null;
    }

    public String getAuthorName() {
        return this.author != null ? this.author.name : AppUtils.getInstance().getAppContext().getString(R.string.text_unknown);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.id != ((CommonVideo) obj).id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.id ^ (this.id >>> 32));
    }

    public String getVideoUrl() {
        return this.video_url;
    }

    public boolean isLiveVideo() {
        return !(TextUtils.isEmpty(this.stream_id) || (TextUtils.isEmpty(this.rtmp_live_url) && TextUtils.isEmpty(this.hdl_live_url))) || this.status == 0;
    }

    public String toJson() {
        return AppUtils.toJson(this);
    }

    public boolean isPCLive() {
        return this.live_type == 2;
    }

    public boolean isValidLive() {
        return !TextUtils.isEmpty(this.stream_id) && (!(TextUtils.isEmpty(this.rtmp_live_url) && TextUtils.isEmpty(this.hdl_live_url)) && this.room_id > 0);
    }

    public long getGameId() {
        return this.game_id;
    }

    public boolean isDollRoom() {
        return this.game_id == 666;
    }
}
