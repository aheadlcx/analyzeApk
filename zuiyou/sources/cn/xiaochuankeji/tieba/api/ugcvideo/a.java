package cn.xiaochuankeji.tieba.api.ugcvideo;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.post.PostUgcVideo.UGCVideoInfo;
import cn.xiaochuankeji.tieba.background.post.PostUgcVideo.UgcImageInfo;
import cn.xiaochuankeji.tieba.background.post.PostUgcVideo.UgcTextInfo;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.MomentListJson;
import cn.xiaochuankeji.tieba.json.UgcAttriContentJson;
import cn.xiaochuankeji.tieba.json.UgcEmojiListJson;
import cn.xiaochuankeji.tieba.json.UgcEventListJson;
import cn.xiaochuankeji.tieba.json.UgcEventSummaryJson;
import cn.xiaochuankeji.tieba.json.UgcPostJson;
import cn.xiaochuankeji.tieba.json.UgcPostJsonForPost;
import cn.xiaochuankeji.tieba.json.UgcTypefaceJson;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuDanmakus;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuLikeJson;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuMsgJson;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuMsgPageJson;
import cn.xiaochuankeji.tieba.json.UgcVideoDotJson;
import cn.xiaochuankeji.tieba.json.UgcVideoFollowListJson;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicHomeJson;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicJson;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicSearchJson;
import cn.xiaochuankeji.tieba.json.UgcVideoPostReviewInfo;
import cn.xiaochuankeji.tieba.json.UgcVideoPublishedDanmakuJson;
import cn.xiaochuankeji.tieba.json.UgcVideoRecommendListJson;
import cn.xiaochuankeji.tieba.json.UgcVideoShareJson;
import cn.xiaochuankeji.tieba.json.UgcVideoSubDanmakus;
import cn.xiaochuankeji.tieba.network.c;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.StickerTrace;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.cloud.SpeechConstant;
import java.util.List;
import rx.d;

public class a {
    private UgcVideoService a = ((UgcVideoService) c.a().a(UgcVideoService.class));

    public d<UgcVideoRecommendListJson> a(String str, int i) {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("direction", str);
        }
        if (i != 0) {
            jSONObject.put("auto", Integer.valueOf(i));
        }
        return this.a.getRecommendList(jSONObject);
    }

    public d<UgcVideoFollowListJson> a(long j, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("offset", str);
        }
        return this.a.getReviewList(jSONObject);
    }

    public d<EmptyJson> a(long j, boolean z, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("type", Integer.valueOf(z ? 1 : -1));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("from", str);
        }
        return this.a.likeOrDislikeMain(jSONObject);
    }

    public d<EmptyJson> b(long j, boolean z, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("rid", Long.valueOf(j));
        jSONObject.put("type", Integer.valueOf(z ? 1 : -1));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("from", str);
        }
        return this.a.likeOrDislikeReview(jSONObject);
    }

    public d<EmptyJson> c(long j, boolean z, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("rid", Long.valueOf(j));
        jSONObject.put("type", Integer.valueOf(z ? 1 : -1));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("from", str);
        }
        return this.a.cancelLikeOrDislikeReview(jSONObject);
    }

    public d<EmptyJson> d(long j, boolean z, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("type", Integer.valueOf(z ? 1 : -1));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("from", str);
        }
        return this.a.cancelLikeOrDislikeMain(jSONObject);
    }

    public d<MomentListJson> a(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("uid", Long.valueOf(j));
        jSONObject.put("offset", Long.valueOf(j2));
        return this.a.getNewUserLikeList(jSONObject);
    }

    public d<MomentListJson> b(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("uid", Long.valueOf(j));
        jSONObject.put("offset", Long.valueOf(j2));
        return this.a.getNewUserCreateList(jSONObject);
    }

    public d<UgcPostJson> a(long j, UGCVideoInfo uGCVideoInfo, UgcImageInfo ugcImageInfo, UgcVideoMusicJson ugcVideoMusicJson, UgcTextInfo[] ugcTextInfoArr, List<StickerTrace> list) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("localid", Long.valueOf(j));
        jSONObject.put("video", uGCVideoInfo);
        jSONObject.put("img", ugcImageInfo);
        if (ugcVideoMusicJson != null) {
            jSONObject.put("music", ugcVideoMusicJson);
        }
        jSONObject.put("texts", ugcTextInfoArr);
        if (list != null) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.addAll(list);
            jSONObject.put("emojis", jSONArray);
        }
        return this.a.publishVideo(jSONObject);
    }

    public d<UgcPostJsonForPost> a(long j, String str, long j2, UGCVideoInfo uGCVideoInfo, UgcImageInfo ugcImageInfo, UgcVideoMusicJson ugcVideoMusicJson, UgcTextInfo[] ugcTextInfoArr, List<StickerTrace> list) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("localid", Long.valueOf(j));
        jSONObject.put("text4zy", str);
        jSONObject.put("tid", Long.valueOf(j2));
        jSONObject.put("video", uGCVideoInfo);
        jSONObject.put("img", ugcImageInfo);
        if (ugcVideoMusicJson != null) {
            jSONObject.put("music", ugcVideoMusicJson);
        }
        jSONObject.put("texts", ugcTextInfoArr);
        if (list != null) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.addAll(list);
            jSONObject.put("emojis", jSONArray);
        }
        return this.a.publishVideoForPost(jSONObject);
    }

    public d<UgcPostJson> a(long j, long j2, UGCVideoInfo uGCVideoInfo, UgcImageInfo ugcImageInfo, UgcVideoMusicJson ugcVideoMusicJson, UgcTextInfo[] ugcTextInfoArr, List<StickerTrace> list, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("localid", Long.valueOf(j));
        jSONObject.put("video", uGCVideoInfo);
        jSONObject.put("img", ugcImageInfo);
        if (ugcVideoMusicJson != null) {
            jSONObject.put("music", ugcVideoMusicJson);
        }
        jSONObject.put("texts", ugcTextInfoArr);
        if (list != null) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.addAll(list);
            jSONObject.put("emojis", jSONArray);
        }
        jSONObject.put("pid", Long.valueOf(j2));
        if (TextUtils.isEmpty(str)) {
            jSONObject.put("from", "other");
        } else {
            jSONObject.put("from", str);
        }
        return this.a.publishReviewVideo(jSONObject);
    }

    public d<UgcVideoMusicHomeJson> a() {
        return this.a.getMusicHomeList();
    }

    public d<UgcVideoMusicHomeJson> c(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("offset", Long.valueOf(j2));
        jSONObject.put("cateid", Long.valueOf(j));
        return this.a.getMusicCategoryList(jSONObject);
    }

    public d<UgcVideoMusicSearchJson> a(String str, long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("title", str);
        if (0 != j) {
            jSONObject.put("offset", Long.valueOf(j));
        }
        return this.a.getMusicSearchList(jSONObject);
    }

    public d<EmptyJson> a(long j, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", Long.valueOf(j));
        jSONObject.put("favor", Integer.valueOf(i));
        return this.a.ugcFavorMusic(jSONObject);
    }

    public d<UgcVideoPostReviewInfo> a(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("rid", Long.valueOf(j));
        return this.a.getMsgReviewDetail(jSONObject);
    }

    public d<UgcVideoPostReviewInfo> a(long j, List<Long> list) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        if (list != null && list.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            for (Long add : list) {
                jSONArray.add(add);
            }
            jSONObject.put("ids", jSONArray);
        }
        return this.a.getPostDetail(jSONObject);
    }

    public d<UgcVideoPostReviewInfo> b(long j) {
        return a(j, null);
    }

    public d<UgcVideoPostReviewInfo> b(long j, List<Long> list) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("rid", Long.valueOf(j));
        if (list != null && list.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            for (Long add : list) {
                jSONArray.add(add);
            }
            jSONObject.put("ids", jSONArray);
        }
        return this.a.getReviewDetail(jSONObject);
    }

    public d<UgcVideoPostReviewInfo> c(long j) {
        return b(j, null);
    }

    public d<UgcVideoShareJson> b(long j, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("from", str);
        }
        return this.a.getPostShareTxt(jSONObject);
    }

    public d<UgcVideoShareJson> c(long j, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("rid", Long.valueOf(j));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("from", str);
        }
        return this.a.getReviewShareTxt(jSONObject);
    }

    public d<EmptyJson> d(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        return this.a.deleteMainVideo(jSONObject);
    }

    public d<EmptyJson> e(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("rid", Long.valueOf(j));
        return this.a.deleteReviewVideo(jSONObject);
    }

    public d<UgcVideoDanmakuDanmakus> a(long j, long j2, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        if (0 != j2) {
            jSONObject.put("rid", Long.valueOf(j2));
        }
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("offset", str);
        }
        return this.a.getDanmakuDanmakus(jSONObject);
    }

    public d<UgcVideoSubDanmakus> d(long j, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("dmid", Long.valueOf(j));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("offset", str);
        }
        return this.a.getSubDanmakus(jSONObject);
    }

    public d<UgcVideoDanmakuLikeJson> f(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", Long.valueOf(j));
        jSONObject.put("type", Integer.valueOf(1));
        return this.a.danmakuLike(jSONObject);
    }

    public d<UgcVideoDanmakuLikeJson> g(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", Long.valueOf(j));
        jSONObject.put("type", Integer.valueOf(1));
        return this.a.danmakuDislike(jSONObject);
    }

    public d<EmptyJson> h(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", Long.valueOf(j));
        return this.a.canceDanmakulLike(jSONObject);
    }

    public d<UgcVideoPublishedDanmakuJson> a(long j, long j2, long j3, long j4, long j5, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("localid", Long.valueOf(System.currentTimeMillis()));
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("rid", Long.valueOf(j2));
        jSONObject.put(SpeechConstant.ISV_VID, Long.valueOf(j3));
        if (j4 != 0) {
            jSONObject.put(SpeechConstant.IST_SESSION_ID, Long.valueOf(j4));
        }
        jSONObject.put("snaptime", Long.valueOf(j5));
        jSONObject.put("text", str);
        jSONObject.put("type", Integer.valueOf(1));
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put("from", str2);
        }
        return this.a.publishDanmaku(jSONObject);
    }

    public d<UgcEmojiListJson> a(int i, int i2, long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cateid", Integer.valueOf(i));
        jSONObject.put("offset", Integer.valueOf(i2));
        if (0 != j) {
            jSONObject.put("ver", Long.valueOf(j));
        }
        return this.a.getEmojiList(jSONObject);
    }

    public d<UgcTypefaceJson> a(int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("fid", Integer.valueOf(i));
        return this.a.getTypeface(jSONObject);
    }

    public d<UgcVideoDanmakuMsgJson> b(long j, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("lastid", Long.valueOf(j));
        jSONObject.put("msg_type", Integer.valueOf(i));
        return this.a.getDanmakuMsgDetail(jSONObject);
    }

    public d<UgcVideoDanmakuMsgPageJson> a(long j, long j2, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", Long.valueOf(j));
        if (0 != j2) {
            jSONObject.put("offset", Long.valueOf(j2));
        }
        jSONObject.put("msg_type", Integer.valueOf(i));
        return this.a.getDanmakuMsgDetailPage(jSONObject);
    }

    public d<UgcAttriContentJson> a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("upoffset", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put("downoffset", str2);
        }
        return this.a.getAttriContent(jSONObject);
    }

    public d<UgcVideoDotJson> i(long j) {
        JSONObject jSONObject = new JSONObject();
        if (0 != j) {
            jSONObject.put("att_lastid", Long.valueOf(j));
        }
        return this.a.getUgcVideoDots(jSONObject);
    }

    public d<UgcEventListJson> b() {
        return this.a.getUgcEventList(new JSONObject());
    }

    public d<UgcEventSummaryJson> c() {
        return this.a.getUgcEventSummary(new JSONObject());
    }

    public d<EmptyJson> j(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", Long.valueOf(j));
        return this.a.setEventFlag(jSONObject);
    }
}
