package cn.xiaochuankeji.tieba.ui.topic.holder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.data.UgcDataBean;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView.ViewType;
import com.iflytek.aiui.AIUIConstant;
import org.json.JSONObject;

public class HolderCreator {

    public enum PostFromType {
        FROM_VOICE,
        FROM_FOLLOW,
        FROM_RECOMMEND,
        FROM_TOPIC,
        FROM_MY_POST,
        FROM_USER_POST,
        FROM_MY_FAVOR,
        FROM_MY_LIKED,
        FROM_HISTORY,
        FROM_VOICEPUBLISH
    }

    public static BaseViewHolder a(Activity activity, ViewGroup viewGroup, int i, PostFromType postFromType) {
        switch (i) {
            case 1:
                return a(activity, viewGroup, postFromType);
            case 2:
                return c(activity, viewGroup, postFromType);
            case 3:
                return b(activity, viewGroup, postFromType);
            default:
                return a(activity, viewGroup, postFromType);
        }
    }

    private static PostViewHolder a(Activity activity, ViewGroup viewGroup, PostFromType postFromType) {
        return new PostViewHolder(LayoutInflater.from(activity).inflate(R.layout.layout_topic_post_item, viewGroup, false), activity, postFromType, viewGroup);
    }

    private static UgcViewHolder b(Activity activity, ViewGroup viewGroup, PostFromType postFromType) {
        return new UgcViewHolder(LayoutInflater.from(activity).inflate(R.layout.layout_topic_ugc_item, viewGroup, false), activity, postFromType);
    }

    private static VoiceViewHolder c(Activity activity, ViewGroup viewGroup, PostFromType postFromType) {
        return new VoiceViewHolder(LayoutInflater.from(activity).inflate(R.layout.layout_voice_post_item, viewGroup, false), activity, postFromType);
    }

    public static boolean a(PostFromType postFromType) {
        return postFromType.equals(PostFromType.FROM_RECOMMEND) || postFromType.equals(PostFromType.FROM_VOICE);
    }

    public static ViewType b(PostFromType postFromType) {
        switch (postFromType) {
            case FROM_RECOMMEND:
            case FROM_VOICE:
                return ViewType.DELETE;
            case FROM_TOPIC:
                return ViewType.MORE;
            case FROM_MY_FAVOR:
                return ViewType.CANCEL_FAVOR;
            default:
                return null;
        }
    }

    public static ViewType[] a(PostFromType postFromType, boolean z, boolean z2) {
        if (z && z2 && (postFromType.equals(PostFromType.FROM_RECOMMEND) || postFromType.equals(PostFromType.FROM_VOICE) || postFromType.equals(PostFromType.FROM_TOPIC))) {
            return new ViewType[]{b(postFromType), ViewType.CANCEL_FOLLOW};
        } else if (z || !(postFromType.equals(PostFromType.FROM_RECOMMEND) || postFromType.equals(PostFromType.FROM_VOICE) || postFromType.equals(PostFromType.FROM_TOPIC))) {
            return new ViewType[]{b(postFromType)};
        } else {
            return new ViewType[]{b(postFromType), ViewType.FOLLOW};
        }
    }

    public static String c(PostFromType postFromType) {
        switch (postFromType) {
            case FROM_RECOMMEND:
                return "index";
            case FROM_VOICE:
                return "index-voice";
            case FROM_TOPIC:
                return "topicdetail";
            case FROM_USER_POST:
                return AIUIConstant.USER;
            case FROM_FOLLOW:
                return "index-follow";
            default:
                return "other";
        }
    }

    public static String a(c cVar) {
        switch (cVar.localPostType()) {
            case 2:
                return "#最右#分享一条有趣的内容给你，不好看算我输。请戳链接>>" + a.d(cVar.getId()) + "?zy_to=applink&to=applink";
            case 3:
                UgcDataBean ugcDataBean = (UgcDataBean) cVar;
                return "#最右#分享一条有趣的内容给你，不好看算我输。请戳链接>>" + a.b(ugcDataBean.getId(), ((UgcVideoInfoBean) ugcDataBean.ugcVideos.get(0)).id);
            default:
                return "#最右#分享一条有趣的内容给你，不好看算我输。请戳链接>>" + a.a(PostDataBean.getPostFromPostDataBean((PostDataBean) cVar)) + "?zy_to=applink&to=applink";
        }
    }

    public static String d(PostFromType postFromType) {
        switch (postFromType) {
            case FROM_RECOMMEND:
            case FROM_VOICE:
                return "home_tab";
            case FROM_TOPIC:
                return "topic_detail";
            default:
                return null;
        }
    }

    public static boolean a(long j) {
        return j == cn.xiaochuankeji.tieba.background.a.g().c();
    }

    public static c a(JSONObject jSONObject) {
        switch (jSONObject.optInt("c_type")) {
            case 3:
                return new UgcDataBean(jSONObject);
            default:
                return PostDataBean.getPostDataBeanFromJson(jSONObject);
        }
    }
}
