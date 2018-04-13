package qsbk.app.activity.publish;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleArticle.CircleVideo;
import qsbk.app.model.CircleArticle.VoteInfo;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.PicUrl;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.share.Share2QiuyouCircleDialogHelper;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.CircleUpgradeDialog;
import qsbk.app.utils.ToastAndDialog;

class s implements SimpleCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ CircleTopic b;
    final /* synthetic */ JSONArray c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ double f;
    final /* synthetic */ double g;
    final /* synthetic */ CirclePublishActivity h;

    s(CirclePublishActivity circlePublishActivity, String str, CircleTopic circleTopic, JSONArray jSONArray, String str2, String str3, double d, double d2) {
        this.h = circlePublishActivity;
        this.a = str;
        this.b = circleTopic;
        this.c = jSONArray;
        this.d = str2;
        this.e = str3;
        this.f = d;
        this.g = d2;
    }

    public void onSuccess(JSONObject jSONObject) {
        int i;
        JSONException jSONException;
        if (this.h.I && this.h.E.hasArticle() && this.h.E.article.allow_comment && this.h.p.isChecked()) {
            Share2QiuyouCircleDialogHelper.submitQiushiComment(QsbkApp.getInstance(), this.h.E.article, this.a, this.h.P);
        } else if (this.h.I && this.h.E.circleArticle != null && this.h.p.isChecked()) {
            if (this.h.E.circleArticle.isForward()) {
                Share2QiuyouCircleDialogHelper.submitCircleArticleComment(this.h.E.circleArticle.originalCircleArticle, this.a, this.h.P);
            } else {
                Share2QiuyouCircleDialogHelper.submitCircleArticleComment(this.h.E.circleArticle, this.a, this.h.P);
            }
        }
        this.h.G = null;
        this.h.t();
        this.h.b.setText("");
        this.h.y.clear();
        try {
            int optInt = jSONObject.optInt("score");
            String string = jSONObject.getString("article_id");
            if (this.b != null && this.b.id.equals("0")) {
                this.b.id = jSONObject.optString("topic_id", "0");
            }
            if (!(this.b == null || this.b.id.equals("0"))) {
                CircleTopicManager.getInstance().insertTopicToLRU(this.b);
            }
            CircleArticle circleArticle = new CircleArticle();
            circleArticle.auditStatus = 1;
            circleArticle.type = 3;
            if (this.h.I && this.h.E != null) {
                circleArticle.type = ((Integer) QYQShareInfo.TYPE_MAP.get(this.h.E.type)).intValue();
                circleArticle.live_origin = this.h.E.live_origin;
                if (!(this.h.E.circleArticle == null || this.h.E.circleArticle.isShare())) {
                    CircleArticle circleArticle2 = this.h.E.circleArticle;
                    if (circleArticle2.isForward()) {
                        circleArticle2 = circleArticle2.originalCircleArticle;
                    }
                    circleArticle.originalCircleArticle = circleArticle2;
                }
            }
            circleArticle.id = string;
            circleArticle.content = this.a;
            Object optString = jSONObject.optString("content");
            if (!TextUtils.isEmpty(optString)) {
                circleArticle.content = optString;
                if (!TextUtils.isEmpty(optString)) {
                    circleArticle.content = optString;
                    if (circleArticle.isShare() && !TextUtils.isEmpty(circleArticle.content)) {
                        try {
                            JSONObject jSONObject2 = new JSONObject(circleArticle.content);
                            if (jSONObject2.has("live_origin")) {
                                circleArticle.live_origin = jSONObject2.optInt("live_origin");
                            }
                            circleArticle.content = jSONObject2.optString("circle_content");
                            circleArticle.shareLink = jSONObject2.optString("qiushi_link");
                            circleArticle.shareContent = jSONObject2.optString("qiushi_content");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            try {
                if (this.c != null && this.c.length() > 0) {
                    circleArticle.atInfoTexts = AtInfo.constructJson(this.c);
                }
                if (!TextUtils.isEmpty(this.d)) {
                    VoteInfo voteInfo = new VoteInfo();
                    voteInfo.vote = -1;
                    voteInfo.a = this.d;
                    voteInfo.b = this.e;
                    voteInfo.aCount = 0;
                    voteInfo.bCount = 0;
                    circleArticle.voteInfo = voteInfo;
                }
                if (this.b == null || !this.b.isAnonymous) {
                    circleArticle.distance = "0m";
                } else {
                    circleArticle.distance = "";
                }
                ArrayList arrayList = new ArrayList();
                circleArticle.createAt = (int) (System.currentTimeMillis() / 1000);
                circleArticle.picUrls = arrayList;
                if (this.h.B != null) {
                    String[] split = this.h.B.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    for (String str : split) {
                        PicUrl picUrl = new PicUrl(circleArticle.createAt);
                        String str2;
                        if (this.h.I) {
                            picUrl.url = str2;
                        } else {
                            if (str2.contains(Config.TRACE_TODAY_VISIT_SPLIT)) {
                                int parseInt;
                                int indexOf = str2.indexOf(Config.TRACE_TODAY_VISIT_SPLIT);
                                try {
                                    parseInt = Integer.parseInt(str2.substring(str2.length() - 1, str2.length()));
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                    parseInt = 0;
                                }
                                str2 = str2.substring(0, indexOf);
                                if (parseInt == MediaFormat.IMAGE_GIF.upload || parseInt == MediaFormat.IMAGE_GIF_VIDEO.upload) {
                                    picUrl.mediaFormat = MediaFormat.IMAGE_GIF_VIDEO;
                                    picUrl.lowUrl = "http://circle-pic.qiushibaike.com/" + str2 + ".mp4";
                                    picUrl.highUrl = "http://circle-pic.qiushibaike.com/" + str2 + ".mp4";
                                }
                            }
                            picUrl.url = "http://circle-pic.qiushibaike.com/" + str2 + "?imageView2/2/w/500/q/80";
                        }
                        picUrl.status = 2;
                        arrayList.add(picUrl);
                    }
                }
                if (this.b != null) {
                    circleArticle.topic = this.b;
                }
                if (this.b == null || !this.b.isAnonymous) {
                    circleArticle.user = QsbkApp.currentUser;
                } else {
                    circleArticle.user = BaseUserInfo.createAnonymous();
                    circleArticle.user.isMe = true;
                }
                circleArticle.likeCount = 0;
                circleArticle.commentCount = 0;
                circleArticle.latitude = String.valueOf(this.f);
                circleArticle.longitude = String.valueOf(this.g);
                if (this.h.z != null) {
                    circleArticle.video = new CircleVideo();
                    circleArticle.video.lowUrl = this.h.z.getFilePath(this.h);
                    circleArticle.video.width = this.h.z.width;
                    circleArticle.video.height = this.h.z.height;
                    circleArticle.video.picUrl = this.h.z.url;
                    circleArticle.type = 10;
                    circleArticle.auditStatus = 2;
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("punches");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    circleArticle.clockedInfo = new long[optJSONArray.length()];
                    for (i = 0; i < optJSONArray.length(); i++) {
                        circleArticle.clockedInfo[i] = optJSONArray.getLong(i);
                    }
                }
                CircleArticleBus.newArticle(circleArticle);
                i = optInt;
            } catch (JSONException e3) {
                JSONException jSONException2 = e3;
                i = optInt;
                jSONException = jSONException2;
            }
        } catch (JSONException e32) {
            jSONException = e32;
            i = 0;
            jSONException.printStackTrace();
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "发布成功" + (i <= 0 ? "" : "，积分+" + i) + (this.h.z != null ? "\n视频动态需要通过审核哦～" : ""), Integer.valueOf(0)).show();
            i = jSONObject.optInt("rank", 0);
            if (i > 0) {
                CircleUpgradeDialog.show(this.h, i);
            }
            this.h.b.setText("");
            this.h.y.clear();
            this.h.I = false;
            this.h.E = null;
            this.h.P.clear();
            this.h.z = null;
            this.h.C();
            this.h.finish();
        }
        if (this.h.z != null) {
        }
        if (i <= 0) {
        }
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "发布成功" + (i <= 0 ? "" : "，积分+" + i) + (this.h.z != null ? "\n视频动态需要通过审核哦～" : ""), Integer.valueOf(0)).show();
        i = jSONObject.optInt("rank", 0);
        if (i > 0) {
            CircleUpgradeDialog.show(this.h, i);
        }
        this.h.b.setText("");
        this.h.y.clear();
        this.h.I = false;
        this.h.E = null;
        this.h.P.clear();
        this.h.z = null;
        this.h.C();
        this.h.finish();
    }

    public void onFailure(int i, String str) {
        this.h.G = null;
        this.h.t();
        AlertDialog create;
        if (i == 30014) {
            create = new Builder(this.h).setTitle("请先绑定手机，再发布").setMessage(str).setPositiveButton("绑定手机", new t(this)).create();
            create.setCanceledOnTouchOutside(true);
            create.show();
        } else if (i == 30000) {
            create = new Builder(this.h).setTitle("补充完个人资料，才能完成此操作哦。").setPositiveButton("补充个人资料", new u(this)).setNegativeButton("取消", null).create();
            create.setCanceledOnTouchOutside(true);
            create.show();
        } else if (str != null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        } else {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "发送失败,内容已保存为草稿", Integer.valueOf(0)).show();
        }
    }
}
