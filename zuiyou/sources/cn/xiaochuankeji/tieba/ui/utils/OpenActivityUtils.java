package cn.xiaochuankeji.tieba.ui.utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuan.jsbridge.b;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.h.d;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.ui.base.MainActivity;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.SubjectActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.InnerCommentDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.InnerCommentDetailActivity.SubcommentFilter;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity.CommentFilter;
import cn.xiaochuankeji.tieba.ui.tale.TaleDetailActivity;
import cn.xiaochuankeji.tieba.ui.tale.TaleListActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.cloud.SpeechConstant;
import com.tencent.wcdb.database.SQLiteDatabase;

public class OpenActivityUtils {

    public enum ActivityType {
        kNone(-1),
        kActivityPostDetail(1),
        kActivityTopicDetail(3),
        kActivityUgcVideoDetail(4),
        kInnerCommentDetail(5);
        
        private int value;

        private ActivityType(int i) {
            this.value = i;
        }

        public int toInt() {
            return this.value;
        }

        public static ActivityType valueOf(int i) {
            for (ActivityType activityType : values()) {
                if (activityType.toInt() == i) {
                    return activityType;
                }
            }
            return kNone;
        }
    }

    private static Intent b(ActivityType activityType) {
        Intent intent = new Intent(BaseApplication.getAppContext(), MainActivity.class);
        intent.putExtra("ActivityType", activityType.toInt());
        intent.setFlags(872415232);
        return intent;
    }

    private static PendingIntent a(ActivityType activityType, Intent intent) {
        return PendingIntent.getActivity(BaseApplication.getAppContext(), activityType.toInt(), intent, 134217728);
    }

    public static PendingIntent a(ActivityType activityType) {
        return a(activityType, b(activityType));
    }

    public static PendingIntent a(long j, boolean z) {
        ActivityType activityType = ActivityType.kActivityPostDetail;
        Intent b = b(activityType);
        b.putExtra("ActivityType", activityType.toInt());
        b.putExtra("PostID", j);
        b.putExtra("ToPostComment", z);
        return a(activityType, b);
    }

    public static PendingIntent a(long j) {
        ActivityType activityType = ActivityType.kActivityPostDetail;
        Intent b = b(activityType);
        b.putExtra("ActivityType", activityType.toInt());
        b.putExtra("PostID", j);
        return a(activityType, b);
    }

    public static PendingIntent a(long j, long j2, boolean z) {
        ActivityType activityType = ActivityType.kActivityPostDetail;
        Intent b = b(activityType);
        b.putExtra("ActivityType", activityType.toInt());
        b.putExtra("PostID", j);
        b.putExtra("post_comment_id", j2);
        b.putExtra("ToPostComment", z);
        return a(activityType, b);
    }

    public static PendingIntent a(long j, long j2, long j3, long j4) {
        ActivityType activityType = ActivityType.kInnerCommentDetail;
        Intent b = b(activityType);
        b.putExtra("ActivityType", activityType.toInt());
        b.putExtra("PostID", j);
        b.putExtra("second_parent_id", j2);
        b.putExtra("second_src_id", j3);
        b.putExtra("second_child_id", j4);
        return a(activityType, b);
    }

    public static PendingIntent a(long j, long j2, long j3) {
        return a(j, j2, 0, j3);
    }

    public static PendingIntent b(long j) {
        ActivityType activityType = ActivityType.kActivityUgcVideoDetail;
        Intent b = b(activityType);
        b.putExtra("ActivityType", activityType.toInt());
        b.putExtra("ugcvideo_id", j);
        return a(activityType, b);
    }

    public static PendingIntent c(long j) {
        ActivityType activityType = ActivityType.kActivityTopicDetail;
        Intent b = b(activityType);
        b.putExtra("ActivityType", activityType.toInt());
        b.putExtra("TopicID", j);
        return a(activityType, b);
    }

    public static boolean a(Intent intent) {
        long longExtra;
        long longExtra2;
        switch (ActivityType.valueOf(intent.getIntExtra("ActivityType", 0))) {
            case kActivityPostDetail:
                longExtra = intent.getLongExtra("PostID", 0);
                longExtra2 = intent.getLongExtra("post_comment_id", 0);
                boolean booleanExtra = intent.getBooleanExtra("ToPostComment", false);
                Post post = new Post(longExtra);
                if (longExtra2 == 0) {
                    PostDetailActivity.a(BaseApplication.getAppContext(), post, booleanExtra ? 1 : 0, null, "", EntranceType.Push);
                } else {
                    PostDetailActivity.a(BaseApplication.getAppContext(), post, 1, new CommentFilter(longExtra2, 1), "", EntranceType.Push);
                }
                d.a().a(booleanExtra ? 3 : 2, post);
                return true;
            case kActivityUgcVideoDetail:
                UgcVideoActivity.a(BaseApplication.getAppContext(), intent.getLongExtra("ugcvideo_id", 0), "other");
                return true;
            case kActivityTopicDetail:
                long longExtra3 = intent.getLongExtra("TopicID", 0);
                Topic topic = new Topic();
                topic._topicID = longExtra3;
                TopicDetailActivity.a(BaseApplication.getAppContext(), topic, "");
                d.a().a(2, topic);
                return true;
            case kInnerCommentDetail:
                long longExtra4 = intent.getLongExtra("PostID", 0);
                long longExtra5 = intent.getLongExtra("second_parent_id", 0);
                longExtra = intent.getLongExtra("second_child_id", 0);
                longExtra2 = intent.getLongExtra("second_src_id", 0);
                if (!(0 == longExtra4 || 0 == longExtra5 || 0 == longExtra)) {
                    SubcommentFilter subcommentFilter;
                    if (longExtra2 > 0) {
                        subcommentFilter = new SubcommentFilter(longExtra, longExtra2, 2);
                    } else {
                        subcommentFilter = new SubcommentFilter(longExtra, longExtra, 1);
                    }
                    InnerCommentDetailActivity.a(BaseApplication.getAppContext(), longExtra4, longExtra5, 0, subcommentFilter, EntranceType.Push);
                    break;
                }
        }
        return false;
    }

    public static void a(Context context, Uri uri) {
        if (uri != null) {
            try {
                Object scheme = uri.getScheme();
                if (!TextUtils.isEmpty(scheme) && scheme.equals("zuiyou")) {
                    String host = uri.getHost();
                    if (!TextUtils.isEmpty(host)) {
                        if (host.equals("postdetail")) {
                            try {
                                PostDetailActivity.a(context, new Post(Long.parseLong(uri.getQueryParameter("id"))));
                            } catch (NumberFormatException e) {
                            }
                        } else if (host.equals("topicdetail")) {
                            try {
                                r2 = Long.parseLong(uri.getQueryParameter("id"));
                                Topic topic = new Topic();
                                topic._topicID = r2;
                                TopicDetailActivity.a(BaseApplication.getAppContext(), topic, "");
                            } catch (NumberFormatException e2) {
                            }
                        } else if (host.equals("reviewdetail")) {
                            long longValue = Long.valueOf(uri.getQueryParameter("pid")).longValue();
                            long longValue2 = Long.valueOf(uri.getQueryParameter("rid")).longValue();
                            if (longValue > 0 && longValue2 > 0) {
                                InnerCommentDetailActivity.a((Activity) context, longValue, longValue2, 0);
                            }
                        } else if (host.equals("ugcpost")) {
                            r2 = Long.valueOf(uri.getQueryParameter("id")).longValue();
                            if (r2 > 0) {
                                UgcVideoActivity.b(context, r2, "other");
                            }
                        } else if (host.equals("ugcreview")) {
                            r2 = Long.valueOf(uri.getQueryParameter("id")).longValue();
                            if (r2 > 0) {
                                UgcVideoActivity.a(context, r2, "other");
                            }
                        } else if (host.equals(SpeechConstant.SUBJECT)) {
                            try {
                                r2 = Long.valueOf(uri.getQueryParameter("id")).longValue();
                                Parcelable bVar = new b();
                                bVar.a = r2;
                                Intent intent = new Intent(context, SubjectActivity.class);
                                intent.putExtra("web_data", bVar);
                                intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                                context.startActivity(intent);
                            } catch (Exception e3) {
                            }
                        } else if (host.equals(AIUIConstant.USER)) {
                            r2 = Long.valueOf(uri.getQueryParameter("mid")).longValue();
                            if (r2 > 0) {
                                MemberDetailActivity.a(context, r2);
                            }
                        } else if (host.equals("theme")) {
                            r2 = Long.valueOf(uri.getQueryParameter("id")).longValue();
                            if (r2 > 0) {
                                TaleListActivity.a(context, "share", r2, null);
                            }
                        } else if (host.equals("article")) {
                            r2 = Long.valueOf(uri.getQueryParameter("id")).longValue();
                            if (r2 > 0) {
                                TaleDetailActivity.a(context, "share", r2);
                            }
                        }
                    }
                }
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
    }
}
