package com.budejie.www.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.TougaoActivity;
import com.budejie.www.bean.UserItem;
import com.budejie.www.util.BudejieDownloadHelper.DownloadStatus;
import com.tencent.connect.common.Constants;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class av {
    private static av e = new av();
    private Context a;
    private UserItem b;
    private int c;
    private int d;
    private SharedPreferences f;

    private av() {
    }

    public static av a() {
        return e;
    }

    public void a(Activity activity, UserItem userItem) {
        if (an.a((Context) activity)) {
            if (this.d == 0) {
                this.a = activity;
                this.d = activity.getResources().getDisplayMetrics().widthPixels;
            }
            this.b = userItem;
            this.f = this.a.getSharedPreferences("weiboprefer", 0);
            a(activity);
            return;
        }
        an.a(activity, activity.getString(R.string.nonet), -1).show();
    }

    public static void a(Activity activity, Map<String, Object> map) {
        if (BudejieDownloadHelper.b(activity, "com.bdj.video.build")) {
            String str;
            Intent intent = new Intent("com.bdj.video.build.CameraActivity");
            Intent intent2 = new Intent(activity, TougaoActivity.class);
            if (!(map == null || map.isEmpty())) {
                str = (String) map.get("theme_name");
                intent2.putExtra("label_id", ((Integer) map.get("theme_id")).intValue());
                intent2.putExtra("label_name", str);
            }
            intent2.putExtra("TOUGAO_TYPE", 41);
            intent.putExtra("external_target_intent", PendingIntent.getActivity(activity, 100, intent2, 1073741824));
            aa.b("ss", "" + OnlineConfigAgent.getInstance().getConfigParams(activity, "ad_switch"));
            str = OnlineConfigAgent.getInstance().getConfigParams(activity, "upload_video_file_max_size");
            if (TextUtils.isEmpty(str)) {
                str = Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
            }
            String configParams = OnlineConfigAgent.getInstance().getConfigParams(activity, "upload_video_file_max_duration");
            if (TextUtils.isEmpty(configParams)) {
                configParams = "1200";
            }
            String configParams2 = OnlineConfigAgent.getInstance().getConfigParams(activity, "upload_video_file_min_duration");
            if (TextUtils.isEmpty(configParams2)) {
                configParams2 = "5";
            }
            String configParams3 = OnlineConfigAgent.getInstance().getConfigParams(activity, "upload_video_file_media_type");
            if (TextUtils.isEmpty(configParams3)) {
                configParams3 = "mp4,mov";
            }
            intent.putExtra("external_file_size", str);
            intent.putExtra("external_max_duration", configParams);
            intent.putExtra("external_min_duration", configParams2);
            intent.putExtra("external_media_type", configParams3);
            activity.startActivity(intent);
            return;
        }
        BudejieDownloadHelper.a((Context) activity, new av$1(activity));
    }

    private void a(Activity activity) {
        Dialog dialog = new Dialog(activity, R.style.custom_dlg_new);
        View inflate = ((LayoutInflater) activity.getSystemService("layout_inflater")).inflate(R.layout.alert_dialog_layout, null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.send_video_img);
        TextView textView = (TextView) inflate.findViewById(R.id.send_video_text);
        if (BudejieDownloadHelper.a == DownloadStatus.loading) {
            imageView.setImageResource(R.drawable.send_video_loading);
        } else if (!BudejieDownloadHelper.b(this.a, "com.bdj.video.build")) {
            imageView.setImageResource(R.drawable.send_video_uninstall);
        }
        List arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(R.id.send_video));
        arrayList.add(Integer.valueOf(R.id.send_picture));
        arrayList.add(Integer.valueOf(R.id.send_text));
        arrayList.add(Integer.valueOf(R.id.send_voice));
        arrayList.add(Integer.valueOf(R.id.send_link));
        arrayList.add(Integer.valueOf(R.id.music_album));
        arrayList.add(Integer.valueOf(R.id.iv_icon));
        Map hashMap = new HashMap();
        for (int i = 0; i < arrayList.size(); i++) {
            hashMap.put(Integer.valueOf(i), inflate.findViewById(((Integer) arrayList.get(i)).intValue()));
        }
        c.a(imageView);
        c.a((ImageView) inflate.findViewById(R.id.send_picture_icon));
        c.a((ImageView) inflate.findViewById(R.id.send_text_icon));
        c.a((ImageView) inflate.findViewById(R.id.send_voice_icon));
        c.a((ImageView) inflate.findViewById(R.id.enter_posts_icon));
        c.a((ImageView) inflate.findViewById(R.id.send_link_icon));
        c.a((ImageView) inflate.findViewById(R.id.music_album_icon));
        c.a((ImageView) inflate.findViewById(R.id.send_videoOnline_icon));
        View findViewById = inflate.findViewById(R.id.cacel);
        Handler av_2 = new av$2(this, hashMap, findViewById, dialog);
        OnClickListener av_3 = new av$3(this, findViewById, av_2);
        OnClickListener av_4 = new av$4(this, activity, dialog, av_3);
        findViewById.setOnClickListener(av_3);
        dialog.setContentView(inflate);
        LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.gravity = 17;
        attributes.height = -1;
        attributes.width = -1;
        attributes.windowAnimations = R.style.custom_dlg_new;
        dialog.getWindow().setAttributes(attributes);
        dialog.setOnShowListener(new av$5(this, hashMap, av_4, arrayList, av_2));
        dialog.show();
    }
}
