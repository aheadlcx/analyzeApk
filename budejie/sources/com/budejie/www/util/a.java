package com.budejie.www.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.budejie.www.activity.CommendDetailOld;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.PublishCommentActivity;
import com.budejie.www.activity.detail.PostDetailActivity;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.label.DeputyModeratorSetActivity;
import com.budejie.www.activity.posts.PostsActivity;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.type.SearchItem;
import com.budejie.www.wallpaper.WallPaperSetActivity;
import com.tencent.connect.common.Constants;
import java.io.Serializable;
import java.util.List;

public class a {
    public static void a(Activity activity, ListItemObject listItemObject, String str, boolean z, boolean z2, String str2) {
        Intent intent;
        Intent intent2;
        if (listItemObject != null) {
            if ("41".equals(listItemObject.getType()) || Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(listItemObject.getType()) || "29".equals(listItemObject.getType())) {
                intent2 = new Intent(activity, PostDetailActivity.class);
            } else {
                intent2 = new Intent(activity, CommendDetailOld.class);
                intent2.putExtra("is_rich_ommend", z);
            }
            intent2.putExtra("listitem_object", listItemObject);
            if (z2) {
                intent2.putExtra("to_comment", z2);
                intent = intent2;
            } else {
                intent = intent2;
            }
        } else if (TextUtils.isEmpty(str)) {
            intent = null;
        } else {
            intent2 = new Intent(activity, PostDetailActivity.class);
            intent2.putExtra("msg_wid", str);
            intent = intent2;
        }
        if (intent != null) {
            if (!TextUtils.isEmpty(str2)) {
                intent.putExtra("request_detail_url", str2);
            }
            if (activity instanceof CommonLabelActivity) {
                intent.putExtra("is_from_commonlabel", true);
            } else if (activity instanceof PostsActivity) {
                intent.putExtra("origin_type", ((PostsActivity) activity).d);
            }
            activity.startActivity(intent);
        }
    }

    public static void a(Activity activity, ListItemObject listItemObject, String str, boolean z) {
        a(activity, listItemObject, str, z, false, "");
    }

    public static void a(Activity activity, ListItemObject listItemObject, String str, boolean z, boolean z2) {
        a(activity, listItemObject, str, z, z2, "");
    }

    public static void a(Context context, ListItemObject listItemObject) {
        a(context, listItemObject, null);
    }

    public static void a(Context context, ListItemObject listItemObject, CommentItem commentItem) {
        Intent intent = new Intent(context, PublishCommentActivity.class);
        intent.putExtra("PublishTag", listItemObject);
        intent.putExtra("ReplyCommentTag", commentItem);
        context.startActivity(intent);
    }

    public static void b(Context context, ListItemObject listItemObject) {
        Intent intent = new Intent(context, WallPaperSetActivity.class);
        intent.putExtra("wallPaperTag", listItemObject);
        context.startActivity(intent);
    }

    public static void a(Context context, String str, String str2) {
        Intent intent = new Intent(context, WallPaperSetActivity.class);
        intent.putExtra("native_video_path_tag", str);
        intent.putExtra("native_video_image_tag", str2);
        context.startActivity(intent);
    }

    public static void a(Context context, Class cls) {
        context.startActivity(new Intent(context, cls));
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent(context, PersonalProfileActivity.class);
        intent.putExtra(PersonalProfileActivity.c, str);
        context.startActivity(intent);
    }

    public static void a(Context context, String str, boolean z, List<SearchItem> list) {
        Intent intent = new Intent(context, DeputyModeratorSetActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("is_set_moderator_tag", z);
        bundle.putString("theme_id_tag", str);
        bundle.putSerializable("user_data_tag", (Serializable) list);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void a(Context context, String str, int i) {
        a(context, str, null, 1, i);
    }

    public static void a(Context context, String str, String str2, int i, int i2) {
        Intent intent = new Intent(context, CommonLabelActivity.class);
        intent.putExtra("theme_id", str);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("theme_name", str2);
        }
        intent.putExtra("colum_set", i);
        intent.putExtra("tab_position_tag", i2);
        context.startActivity(intent);
    }
}
