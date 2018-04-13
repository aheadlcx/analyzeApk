package qsbk.app.utils;

import android.util.Log;
import qsbk.app.QsbkApp;
import qsbk.app.core.model.Share;
import qsbk.app.core.model.User;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.Live;
import qsbk.app.model.LiveRoom;
import qsbk.app.model.QsbkCommonVideo;
import qsbk.app.model.UserInfo;

public class LiveUtil {
    private static final String a = LiveUtil.class.getSimpleName();

    public static QsbkCommonVideo convert2CommonVideo(LiveRoom liveRoom) {
        if (liveRoom == null) {
            Log.d(a, "convert2CommonVideo() called with: liveRoom = [" + liveRoom + "]");
            return null;
        }
        QsbkCommonVideo qsbkCommonVideo = new QsbkCommonVideo();
        qsbkCommonVideo.live_id = liveRoom.live_id;
        qsbkCommonVideo.stream_id = liveRoom.stream_id;
        qsbkCommonVideo.content = liveRoom.content;
        String str = liveRoom.thumbnail_url;
        qsbkCommonVideo.thumbnail_url = str;
        qsbkCommonVideo.pic_url = str;
        qsbkCommonVideo.visitors_count = Long.parseLong(liveRoom.visitors_count);
        qsbkCommonVideo.rtmp_live_url = liveRoom.rtmp_live_url;
        qsbkCommonVideo.hdl_live_url = liveRoom.hdl_live_url;
        qsbkCommonVideo.room_id = Long.parseLong(liveRoom.room_id);
        qsbkCommonVideo.live_type = liveRoom.live_type;
        qsbkCommonVideo.game_id = (long) liveRoom.game_id;
        qsbkCommonVideo.location = liveRoom.location;
        qsbkCommonVideo.share = new Share();
        qsbkCommonVideo.share.caption = liveRoom.share.caption;
        qsbkCommonVideo.share.url = liveRoom.share.url;
        qsbkCommonVideo.share.wb_info = liveRoom.share.wb_info;
        qsbkCommonVideo.author = new User();
        User user = qsbkCommonVideo.author;
        User user2 = qsbkCommonVideo.author;
        String str2 = liveRoom.author.headurl;
        user2.headurl = str2;
        user.avatar = str2;
        qsbkCommonVideo.author.gender = liveRoom.author.gender;
        qsbkCommonVideo.author.id = Long.parseLong(liveRoom.author.id);
        qsbkCommonVideo.author.intro = liveRoom.author.intro;
        qsbkCommonVideo.author.name = liveRoom.author.name;
        qsbkCommonVideo.author.origin = liveRoom.author.origin;
        qsbkCommonVideo.author.is_follow = liveRoom.is_follow;
        qsbkCommonVideo.author.nick_id = liveRoom.author.nick_id;
        qsbkCommonVideo.author.origin_id = liveRoom.author.origin_id;
        qsbkCommonVideo.author.platform_id = liveRoom.author.platform_id;
        return qsbkCommonVideo;
    }

    public static QsbkCommonVideo convert2CommonVideo(Live live) {
        if (live == null) {
            return null;
        }
        QsbkCommonVideo qsbkCommonVideo = new QsbkCommonVideo();
        qsbkCommonVideo.live_id = String.valueOf(live.liveId);
        qsbkCommonVideo.game_id = (long) live.gameId;
        qsbkCommonVideo.author = new User();
        qsbkCommonVideo.author.avatar = live.author.headurl;
        qsbkCommonVideo.author.id = Long.parseLong(live.author.id);
        qsbkCommonVideo.author.name = live.author.name;
        qsbkCommonVideo.author.origin = live.author.origin;
        qsbkCommonVideo.author.is_follow = live.isFollow;
        qsbkCommonVideo.author.nick_id = live.author.nick_id;
        return qsbkCommonVideo;
    }

    public static QsbkCommonVideo convert2CommonVideo(CircleArticle circleArticle) {
        if (circleArticle == null) {
            return null;
        }
        QsbkCommonVideo qsbkCommonVideo = new QsbkCommonVideo();
        qsbkCommonVideo.live_id = circleArticle.shareLink;
        qsbkCommonVideo.author = new User();
        qsbkCommonVideo.author.avatar = QsbkApp.absoluteUrlOfMediumUserIcon(circleArticle.user.userIcon, circleArticle.user.userId);
        qsbkCommonVideo.author.gender = circleArticle.user.gender;
        qsbkCommonVideo.author.id = Long.parseLong(circleArticle.user.userId);
        qsbkCommonVideo.author.name = circleArticle.user.userName;
        qsbkCommonVideo.author.origin = (long) circleArticle.live_origin;
        return qsbkCommonVideo;
    }

    public static User convert2User(UserInfo userInfo) {
        if (userInfo == null) {
            Log.d(a, "convert2User() called with: userInfo = [" + userInfo + "]");
            return null;
        }
        User user = new User();
        user.gender = userInfo.gender;
        user.name = userInfo.userName;
        user.headurl = QsbkApp.absoluteUrlOfLargeUserIcon(userInfo.userIcon, userInfo.userId);
        user.id = Long.parseLong(userInfo.userId);
        user.age = userInfo.age;
        user.astrology = userInfo.astrology;
        user.hometown = userInfo.hometown;
        user.job = userInfo.job;
        user.origin = 1;
        user.level = userInfo.remixLevel;
        return user;
    }

    public static void updateUserInfo(User user) {
        if (user != null && QsbkApp.currentUser != null) {
            QsbkApp.currentUser.remixLevel = user.level;
        }
    }
}
