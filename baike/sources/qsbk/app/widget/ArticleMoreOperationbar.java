package qsbk.app.widget;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.umeng.commonsdk.proguard.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.activity.publish.CirclePublishActivity;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.CircleArticle;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.StringUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.video.VideoPlayerView;
import qsbk.app.video.VideoPlayersManager;

public class ArticleMoreOperationbar {
    private CircleArticle a;
    private boolean b;

    public enum Operation {
        DELETE(11, "删除"),
        REPORT(7, "举报"),
        NOTINTEREST(16, "不感兴趣"),
        FORBIDDEN(14, "封禁");
        
        int a;
        String b;

        private Operation(int i, String str) {
            this.a = i;
            this.b = str;
        }
    }

    public ArticleMoreOperationbar() {
        this.b = false;
    }

    public ArticleMoreOperationbar(boolean z) {
        this.b = z;
    }

    public static void reportDialog(Context context, CircleArticle circleArticle) {
        CharSequence substring;
        StringBuffer append = new StringBuffer("举报 : ").append(circleArticle.content);
        if (append.length() > 30) {
            substring = append.substring(0, 29);
        } else {
            substring = append.toString();
        }
        String[] strArr = new String[]{g.an, "dirty", "abuse", "politics", "others"};
        new Builder(context).setTitle(substring).setItems(new String[]{"广告/欺诈", "淫秽色情", "骚扰谩骂", "反动政治", "其他"}, new l(strArr, circleArticle)).setNegativeButton("取消", new a()).show();
    }

    private static void b(CircleArticle circleArticle, String str) {
        String format = String.format(Constants.CIRCLE_ARTICLE_REPORT, new Object[]{circleArticle.id});
        Map hashMap = new HashMap();
        hashMap.put("reason", str);
        hashMap.put("toid", circleArticle.user.userId);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new x(circleArticle));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public static void unlikeArticle(CircleArticle circleArticle) {
        if (HttpUtils.netIsAvailable()) {
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.CIRCLE_ARTICLE_UNLIKE, new Object[]{circleArticle.id}), new y(circleArticle));
            simpleHttpTask.setMapParams(new HashMap());
            simpleHttpTask.execute();
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "未发现可用网络，请稍候再试").show();
    }

    public static void deleteArticle(CircleArticle circleArticle) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.CIRCLE_ARTICLE_DELETE, new Object[]{circleArticle.id}), new z(circleArticle));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.execute();
    }

    public static void deleteDialog(Context context, CircleArticle circleArticle) {
        Object obj = ((QsbkApp.currentUser == null || !TextUtils.equals(circleArticle.user.userId, QsbkApp.currentUser.userId)) && !(circleArticle.user.isAnonymous() && circleArticle.user.isMe)) ? null : 1;
        new Builder(context).setMessage(obj != null ? "确认要删除吗？\n删除动态 积分将-5" : "确认要删除吗？").setPositiveButton("确定", new ab(context, circleArticle)).setNegativeButton("取消", new aa()).show();
    }

    public static void topDialog(Context context, CircleArticle circleArticle) {
        Context baseContext;
        boolean hasTop;
        if (context instanceof ContextWrapper) {
            baseContext = ((ContextWrapper) context).getBaseContext();
        } else {
            baseContext = context;
        }
        if (baseContext instanceof CircleTopicActivity) {
            hasTop = ((CircleTopicActivity) baseContext).hasTop();
        } else {
            hasTop = false;
        }
        new Builder(context).setMessage(hasTop ? "置顶该动态，之前置顶的动态将被取消置顶，确认继续该操作？" : "置顶后，该动态将出现在该话题的顶部").setPositiveButton("确定", new ad(context, circleArticle)).setNegativeButton("取消", new ac()).show();
    }

    public static void top(Context context, CircleArticle circleArticle) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.CIRCLE_TOP_ARTICLE, new b(context, "处理中", context));
        Map hashMap = new HashMap();
        hashMap.put("topic_id", circleArticle.topic.id);
        hashMap.put("article_id", circleArticle.id);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public static void untopDialog(Context context, CircleArticle circleArticle) {
        new Builder(context).setMessage("取消置顶，该动态将不会出现在话题顶部").setPositiveButton("确定", new d(context, circleArticle)).setNegativeButton("取消", new c()).show();
    }

    public static void untop(Context context, CircleArticle circleArticle) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.CIRCLE_UNTOP_ARTICLE, new e(context, "处理中", context));
        Map hashMap = new HashMap();
        hashMap.put("topic_id", circleArticle.topic.id);
        hashMap.put("article_id", circleArticle.id);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public static void removeDialog(Context context, CircleArticle circleArticle) {
        new Builder(context).setMessage("移除动态，该动态将不会出现在该话题").setPositiveButton("确定", new g(context, circleArticle)).setNegativeButton("取消", new f()).show();
    }

    public static void remove(Context context, CircleArticle circleArticle) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.CIRCLE_TOPIC_ARTICLE_DELETE, new h(context, "处理中", circleArticle));
        Map hashMap = new HashMap();
        hashMap.put("topic_id", circleArticle.topic.id);
        hashMap.put("article_id", circleArticle.id);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public static void blockDialog(Context context, CircleArticle circleArticle) {
        new Builder(context).setMessage("屏蔽TA，TA发表的所有话题动态将不会出现在该话题，且不能评论该话题动态，您可以在话题「屏蔽管理」中解除屏蔽TA").setPositiveButton("确定", new i(context, circleArticle)).setNegativeButton("取消", null).show();
    }

    public static void forbidDialog(Context context, CircleArticle circleArticle) {
        new Builder(context).setTitle("删除并封禁该动态？").setItems(new String[]{"删除并封禁", "取消"}, new j(context, circleArticle)).show();
    }

    public static void forbid(Context context, CircleArticle circleArticle) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.ADMIN_FORBID_CIRCLE_DYNAMIC, new Object[]{Integer.valueOf(Integer.parseInt(circleArticle.id))}), new k(context, "处理中"));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.execute();
    }

    public static void adminDeleteDialog(Context context, CircleArticle circleArticle) {
        new Builder(context).setTitle("删除该条动态？").setItems(new String[]{"删除", "取消"}, new m(context, circleArticle)).show();
    }

    public static void adminDelete(Context context, CircleArticle circleArticle) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.CIRCLE_BLOCK_USER, new n(context, "处理中"));
        Map hashMap = new HashMap();
        hashMap.put("topic_id", circleArticle.topic.id);
        hashMap.put("block_user_id", circleArticle.user.userId);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    private static void b(Context context, CircleArticle circleArticle) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.CIRCLE_BLOCK_USER, new o(context, "处理中"));
        Map hashMap = new HashMap();
        hashMap.put("topic_id", circleArticle.topic.id);
        hashMap.put("article_id", circleArticle.id);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public static void handleShare(int i, Fragment fragment, CircleArticle circleArticle) {
        handleShare(i, fragment, fragment.getActivity(), circleArticle);
    }

    public static void handleShare(int i, Activity activity, CircleArticle circleArticle) {
        handleShare(i, null, activity, circleArticle);
    }

    public static void handleShare(int i, Fragment fragment, Activity activity, CircleArticle circleArticle) {
        switch (i) {
            case 1:
            case 3:
            case 4:
            case 8:
            case 9:
            case 10:
                ShareUtils.doShare(i, activity, fragment, circleArticle);
                return;
            case 5:
                if (!TextUtils.isEmpty(circleArticle.content)) {
                    StringUtils.copyToClipboard(circleArticle.content, activity);
                    ToastAndDialog.makePositiveToast(activity, "文字内容已复制到粘贴板", Integer.valueOf(0)).show();
                    return;
                }
                return;
            case 7:
                reportDialog(activity, circleArticle);
                return;
            case 11:
                deleteDialog(activity, circleArticle);
                return;
            case 14:
                forbidDialog(activity, circleArticle);
                return;
            case 15:
                CirclePublishActivity.launch((Context) activity, new QYQShareInfo(circleArticle));
                return;
            case 16:
                unlikeArticle(circleArticle);
                return;
            case 17:
                if (circleArticle.isTop) {
                    untopDialog(activity, circleArticle);
                    return;
                } else {
                    topDialog(activity, circleArticle);
                    return;
                }
            case 18:
                removeDialog(activity, circleArticle);
                return;
            case 19:
                blockDialog(activity, circleArticle);
                return;
            case 20:
                showReportTopicDialog(activity, circleArticle);
                return;
            default:
                return;
        }
    }

    public static void showReportTopicDialog(Activity activity, CircleArticle circleArticle) {
        CharSequence substring;
        StringBuffer append = new StringBuffer("举报 : ").append(circleArticle.topic.content);
        if (append.length() > 30) {
            substring = append.substring(0, 29);
        } else {
            substring = append.toString();
        }
        String[] strArr = new String[]{g.an, "dirty", "abuse", "politics", "others"};
        new Builder(activity).setTitle(substring).setItems(new String[]{"广告/欺诈", "淫秽色情", "骚扰谩骂", "反动政治", "其他"}, new q(activity, strArr, circleArticle)).setNegativeButton("取消", new p()).show();
    }

    public static void reportTopic(Activity activity, String str, CircleArticle circleArticle) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.CIRCLE_REPORT_TOPIC, new Object[]{circleArticle.topic.id}), new s(activity, "处理中", circleArticle, activity));
        Map hashMap = new HashMap();
        hashMap.put("reason", str);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public void show(Context context, CircleArticle circleArticle) {
        int i = 4;
        if (circleArticle != null) {
            boolean isTopicMaster;
            String[] strArr;
            Object obj;
            int i2;
            Object obj2;
            Dialog create;
            VideoPlayerView last;
            if (this.b) {
                Context baseContext;
                if (context instanceof ContextWrapper) {
                    baseContext = ((ContextWrapper) context).getBaseContext();
                } else {
                    baseContext = context;
                }
                if (baseContext instanceof CircleTopicActivity) {
                    isTopicMaster = ((CircleTopicActivity) baseContext).isTopicMaster();
                    this.a = circleArticle;
                    strArr = new String[10];
                    obj = new String[10];
                    strArr[0] = "share";
                    obj[0] = "分享";
                    strArr[1] = "copy";
                    obj[1] = "复制文字";
                    if ((!this.b && QsbkApp.currentUser != null && TextUtils.equals("admin", QsbkApp.currentUser.adminRole)) || (QsbkApp.currentUser != null && "1".equalsIgnoreCase(QsbkApp.currentUser.userId))) {
                        strArr[2] = "unlike";
                        obj[2] = "不感兴趣";
                        strArr[3] = "delete";
                        obj[3] = "删除";
                        strArr[4] = "forbid";
                        obj[4] = "封禁";
                        if (circleArticle.isTop) {
                            strArr[5] = "untop";
                            obj[5] = "取消置顶";
                        } else {
                            strArr[5] = "top";
                            obj[5] = "置顶";
                        }
                        strArr[6] = "remove";
                        obj[6] = "移除";
                        strArr[7] = "block";
                        obj[7] = "屏蔽TA";
                        i = 8;
                    } else if ((this.b && QsbkApp.currentUser != null && TextUtils.equals("admin", QsbkApp.currentUser.adminRole)) || (QsbkApp.currentUser != null && "1".equalsIgnoreCase(QsbkApp.currentUser.userId))) {
                        strArr[2] = "unlike";
                        obj[2] = "不感兴趣";
                        strArr[3] = "adminDelete";
                        obj[3] = "删除";
                        strArr[4] = "forbid";
                        obj[4] = "封禁";
                        i = 5;
                    } else if ((QsbkApp.currentUser == null && TextUtils.equals(circleArticle.user.userId, QsbkApp.currentUser.userId)) || (circleArticle.user.isAnonymous() && circleArticle.user.isMe)) {
                        strArr[2] = "delete";
                        obj[2] = "删除";
                        if (isTopicMaster) {
                            if (circleArticle.isClocked()) {
                                i2 = 3;
                            } else if (circleArticle.isTop) {
                                strArr[3] = "untop";
                                obj[3] = "取消置顶";
                                i2 = 4;
                            } else {
                                strArr[3] = "top";
                                obj[3] = "置顶";
                                i2 = 4;
                            }
                            strArr[i2] = "remove";
                            obj[i2] = "移除";
                            i2++;
                            strArr[i2] = "block";
                            obj[i2] = "屏蔽TA";
                            i = i2 + 1;
                        } else {
                            i = 3;
                        }
                    } else {
                        strArr[2] = "unlike";
                        obj[2] = "不感兴趣";
                        strArr[3] = "report";
                        obj[3] = "举报";
                        if (isTopicMaster) {
                            if (!circleArticle.isClocked()) {
                                if (circleArticle.isTop) {
                                    strArr[4] = "top";
                                    obj[4] = "置顶";
                                    i = 5;
                                } else {
                                    strArr[4] = "untop";
                                    obj[4] = "取消置顶";
                                    i = 5;
                                }
                            }
                            strArr[i] = "remove";
                            obj[i] = "移除";
                            i2 = i + 1;
                            strArr[i2] = "block";
                            obj[i2] = "屏蔽TA";
                            i = i2 + 1;
                        }
                    }
                    obj2 = new String[i];
                    System.arraycopy(obj, 0, obj2, 0, i);
                    create = new Builder(context).setItems(obj2, new u(this, strArr, circleArticle, context)).setNegativeButton("取消", new t(this)).create();
                    last = VideoPlayersManager.getLast();
                    if (last != null && last.isPlaying()) {
                        last.pause();
                        create.setOnDismissListener(new v(this, last));
                    }
                    create.show();
                }
            }
            isTopicMaster = false;
            this.a = circleArticle;
            strArr = new String[10];
            obj = new String[10];
            strArr[0] = "share";
            obj[0] = "分享";
            strArr[1] = "copy";
            obj[1] = "复制文字";
            if (!this.b) {
            }
            if (this.b) {
            }
            if (QsbkApp.currentUser == null) {
            }
            strArr[2] = "unlike";
            obj[2] = "不感兴趣";
            strArr[3] = "report";
            obj[3] = "举报";
            if (isTopicMaster) {
                if (circleArticle.isClocked()) {
                    if (circleArticle.isTop) {
                        strArr[4] = "top";
                        obj[4] = "置顶";
                        i = 5;
                    } else {
                        strArr[4] = "untop";
                        obj[4] = "取消置顶";
                        i = 5;
                    }
                }
                strArr[i] = "remove";
                obj[i] = "移除";
                i2 = i + 1;
                strArr[i2] = "block";
                obj[i2] = "屏蔽TA";
                i = i2 + 1;
            }
            obj2 = new String[i];
            System.arraycopy(obj, 0, obj2, 0, i);
            create = new Builder(context).setItems(obj2, new u(this, strArr, circleArticle, context)).setNegativeButton("取消", new t(this)).create();
            last = VideoPlayersManager.getLast();
            last.pause();
            create.setOnDismissListener(new v(this, last));
            create.show();
        }
    }

    public static void showOperation(Context context, CircleArticle circleArticle) {
        List arrayList = new ArrayList();
        if ((QsbkApp.currentUser == null || !TextUtils.equals(circleArticle.user.userId, QsbkApp.currentUser.userId)) && !(circleArticle.user.isAnonymous() && circleArticle.user.isMe)) {
            arrayList.add(Operation.NOTINTEREST);
        } else {
            arrayList.add(Operation.DELETE);
        }
        arrayList.add(Operation.REPORT);
        if (!(QsbkApp.currentUser == null || !QsbkApp.currentUser.isBackEndAdmin() || arrayList.contains(Operation.DELETE))) {
            arrayList.add(Operation.DELETE);
        }
        CharSequence[] charSequenceArr = new String[arrayList.size()];
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                charSequenceArr[i] = ((Operation) arrayList.get(i)).b;
            }
        }
        new Builder(context).setItems(charSequenceArr, new w(arrayList, context, circleArticle)).create().show();
    }
}
