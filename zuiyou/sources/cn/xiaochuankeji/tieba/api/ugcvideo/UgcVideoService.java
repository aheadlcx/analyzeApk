package cn.xiaochuankeji.tieba.api.ugcvideo;

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
import cn.xiaochuankeji.tieba.json.UgcVideoMusicSearchJson;
import cn.xiaochuankeji.tieba.json.UgcVideoPostReviewInfo;
import cn.xiaochuankeji.tieba.json.UgcVideoPublishedDanmakuJson;
import cn.xiaochuankeji.tieba.json.UgcVideoRecommendListJson;
import cn.xiaochuankeji.tieba.json.UgcVideoShareJson;
import cn.xiaochuankeji.tieba.json.UgcVideoSubDanmakus;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface UgcVideoService {
    @o(a = "/ugcvideo/danmaku/cancel_like")
    d<EmptyJson> canceDanmakulLike(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/post/cancel_like")
    d<EmptyJson> cancelLikeOrDislikeMain(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/review/cancel_like")
    d<EmptyJson> cancelLikeOrDislikeReview(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/danmaku/dislike")
    d<UgcVideoDanmakuLikeJson> danmakuDislike(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/danmaku/like")
    d<UgcVideoDanmakuLikeJson> danmakuLike(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/post/delete")
    d<EmptyJson> deleteMainVideo(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/review/delete")
    d<EmptyJson> deleteReviewVideo(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/user/atts")
    d<UgcAttriContentJson> getAttriContent(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/danmaku/danmakus")
    d<UgcVideoDanmakuDanmakus> getDanmakuDanmakus(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/msg/danmaku_detail")
    d<UgcVideoDanmakuMsgJson> getDanmakuMsgDetail(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/msg/danmaku_list")
    d<UgcVideoDanmakuMsgPageJson> getDanmakuMsgDetailPage(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/emoji/list4cate")
    d<UgcEmojiListJson> getEmojiList(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/post/get")
    d<UgcPostJson> getMainVideo(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/msg/review_detail")
    d<UgcVideoPostReviewInfo> getMsgReviewDetail(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/msg/review_list")
    d<UgcVideoPostReviewInfo> getMsgReviewList(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/music/list4cate")
    d<UgcVideoMusicHomeJson> getMusicCategoryList(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/music/list4hot")
    d<UgcVideoMusicHomeJson> getMusicHomeList();

    @o(a = "/ugcvideo/music/search")
    d<UgcVideoMusicSearchJson> getMusicSearchList(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/user/creates4zy")
    d<MomentListJson> getNewUserCreateList(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/user/likes4zy")
    d<MomentListJson> getNewUserLikeList(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/post/detail")
    d<UgcVideoPostReviewInfo> getPostDetail(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/post/share")
    d<UgcVideoShareJson> getPostShareTxt(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/index")
    d<UgcVideoRecommendListJson> getRecommendList(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/review/detail")
    d<UgcVideoPostReviewInfo> getReviewDetail(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/review/list")
    d<UgcVideoFollowListJson> getReviewList(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/review/share")
    d<UgcVideoShareJson> getReviewShareTxt(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/danmaku/sub_danmakus")
    d<UgcVideoSubDanmakus> getSubDanmakus(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/font/getfonturlbyfid")
    d<UgcTypefaceJson> getTypeface(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/activity/list")
    d<UgcEventListJson> getUgcEventList(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/activity/summary")
    d<UgcEventSummaryJson> getUgcEventSummary(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/user/dots")
    d<UgcVideoDotJson> getUgcVideoDots(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/post/like")
    d<EmptyJson> likeOrDislikeMain(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/review/like")
    d<EmptyJson> likeOrDislikeReview(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/danmaku/create")
    d<UgcVideoPublishedDanmakuJson> publishDanmaku(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/review/create")
    d<UgcPostJson> publishReviewVideo(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/post/create")
    d<UgcPostJson> publishVideo(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/post/create4zy")
    d<UgcPostJsonForPost> publishVideoForPost(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/activity/setflag")
    d<EmptyJson> setEventFlag(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/music/favor")
    d<EmptyJson> ugcFavorMusic(@a JSONObject jSONObject);
}
