package qsbk.app.share;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.Article;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.ShareMsgItem;
import qsbk.app.nearby.api.LocationCache;
import qsbk.app.nearby.api.LocationCache.Location;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class Share2QiuyouCircleDialogHelper {
    public static final String TYPE_GIF = "gif";
    public static final String TYPE_IMAGE = "image";
    public static final String TYPE_LINK = "link";
    public static final String TYPE_LIVE = "live";
    public static final String TYPE_TEXT = "text";
    public static final String TYPE_VIDEO = "video";
    public static final String TYPE_WEB = "web";
    private static final int[] a = new int[]{-16738680, -8336444};
    private static final int[] b = new int[]{-1, -16777216};
    private static final int[] c = new int[]{Color.parseColor("#ff424242"), Color.parseColor("#ffeeeeee")};

    public static AlertDialog showDialog(Context context, Article article) {
        String str;
        if (article.isVideoArticle()) {
            str = article.absPicPath;
        } else if (TextUtils.isEmpty(article.image)) {
            str = null;
        } else {
            str = QsbkApp.absoluteUrlOfMediumContentImage(article.id, article.image);
        }
        String str2 = article.content;
        String str3 = article.id;
        String str4 = article.isGIFArticle() ? "gif" : article.isVideoArticle() ? "video" : article.isImageArticle() ? "image" : "text";
        return showDialog(context, str, str3, str2, null, str4, article);
    }

    public static AlertDialog showDialog(Context context, ShareMsgItem shareMsgItem) {
        return showDialog(context, shareMsgItem, "link");
    }

    public static AlertDialog showDialog(Context context, ShareMsgItem shareMsgItem, String str) {
        return showDialog(context, shareMsgItem.imageUrl, shareMsgItem.link, shareMsgItem.content, shareMsgItem.title, str, null);
    }

    public static AlertDialog showDialog(Context context, String str, String str2, String str3, String str4, String str5, Article article) {
        if (context == null) {
            return null;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.share_to_qiuyoucircle, null);
        inflate.setBackgroundColor(UIHelper.isNightTheme() ? c[0] : c[1]);
        AlertDialog create = new Builder(context).setView(inflate).create();
        inflate.setMinimumHeight(UIHelper.dip2px(context, 152.0f));
        inflate.setMinimumWidth(UIHelper.dip2px(context, 320.0f));
        EditText editText = (EditText) inflate.findViewById(R.id.input);
        TextView textView = (TextView) inflate.findViewById(R.id.title);
        textView.setTextColor(UIHelper.isNightTheme() ? b[0] : b[1]);
        editText.setTextColor(UIHelper.isNightTheme() ? b[0] : b[1]);
        textView.setText("分享到糗友圈");
        textView = (TextView) inflate.findViewById(R.id.content);
        textView.setTextColor(UIHelper.isNightTheme() ? b[0] : b[1]);
        textView.setText(str3);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.image);
        if (str == null) {
            imageView.setVisibility(8);
        } else {
            imageView.setVisibility(0);
            FrescoImageloader.displayImage(imageView, str, TileBackground.getBackgroud(context, BgImageType.SHARE));
        }
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.qiuyou_checkbox);
        checkBox.setButtonDrawable(UIHelper.isNightTheme() ? R.drawable.publish_check_box_night : R.drawable.publish_check_box);
        if (article == null) {
            checkBox.setVisibility(8);
            checkBox.setChecked(false);
            inflate.findViewById(R.id.comment_qiushi).setVisibility(8);
        }
        Button button = (Button) inflate.findViewById(R.id.cancel);
        button.setTextColor(UIHelper.isNightTheme() ? a[0] : a[1]);
        button.setOnClickListener(new n(create));
        Button button2 = (Button) inflate.findViewById(R.id.sure);
        button2.setTextColor(UIHelper.isNightTheme() ? a[0] : a[1]);
        button2.setOnClickListener(new o(context, str, str2, str3, str4, str5, editText, checkBox, article, create));
        create.show();
        return create;
    }

    private static String a(String str, String str2) {
        if ("link".equals(str)) {
            return "分享了糗事";
        }
        if ("live".equals(str)) {
            return "分享了直播";
        }
        return TextUtils.isEmpty(str2) ? "分享了糗事" : str2;
    }

    private static void b(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        HashMap hashMap = new HashMap();
        hashMap.put("is_shared", Integer.valueOf(1));
        String a = TextUtils.isEmpty(str6.trim()) ? a(str5, str4) : str6;
        if (a.length() > 300) {
            a.substring(0, 300);
        }
        hashMap.put("content", a);
        hashMap.put("qiushi_link", str2);
        hashMap.put("qiushi_content", str3);
        hashMap.put("qiushi_type", str5);
        hashMap.put("pic_urls", str);
        Location location = LocationCache.getInstance().getLocation(Long.MAX_VALUE);
        if (location == null) {
            hashMap.put(ParamKey.LONGITUDE, Integer.valueOf(0));
            hashMap.put(ParamKey.LATITUDE, Integer.valueOf(0));
        } else {
            hashMap.put(ParamKey.LONGITUDE, Double.valueOf(location.longitude));
            hashMap.put(ParamKey.LATITUDE, Double.valueOf(location.latitude));
        }
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.CIRCLE_PUBLISH, new p(str5, a, str, str2, str3, location, context));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    private static void b(Context context, Article article, String str) {
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能评论哦！", Integer.valueOf(0)).show();
            context.startActivity(new Intent(context, ActionBarLoginActivity.class));
        } else if (article.allow_comment && HttpUtils.netIsAvailable()) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            submitQiushiComment(context, article, str, null);
        }
    }

    public static void submitQiushiComment(Context context, Article article, String str, ArrayList<AtInfo> arrayList) {
        String format = String.format(Constants.COMMENT_CREATE, new Object[]{article.id});
        Map hashMap = new HashMap();
        hashMap.put("content", str);
        hashMap.put("anonymous", Boolean.valueOf(false));
        hashMap.put("share", Boolean.valueOf(true));
        if (arrayList != null && arrayList.size() > 0) {
            JSONObject jSONObject = new JSONObject();
            for (int i = 0; i < arrayList.size(); i++) {
                AtInfo atInfo = (AtInfo) arrayList.get(i);
                try {
                    jSONObject.put(atInfo.name, Integer.parseInt(atInfo.uid));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (jSONObject != null) {
                hashMap.put("at_infos", jSONObject);
            }
        }
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new q());
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.setIsSubmit(true);
        simpleHttpTask.executeOnExecutor(SimpleHttpTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static void submitCircleArticleComment(CircleArticle circleArticle, String str, ArrayList<AtInfo> arrayList) {
        String format = String.format(Constants.CIRCLE_ADD_COMMENT, new Object[]{circleArticle.id});
        Map hashMap = new HashMap();
        hashMap.put("content", str);
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < arrayList.size(); i++) {
            JSONObject jSONObject = new JSONObject();
            jSONObject = AtInfo.toJson((AtInfo) arrayList.get(i));
            if (jSONObject != null) {
                jSONArray.put(jSONObject);
            }
        }
        if (jSONArray != null && jSONArray.length() > 0) {
            hashMap.put("at_users", jSONArray);
        }
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new r());
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }
}
