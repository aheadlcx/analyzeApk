package com.budejie.www.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.TougaoActivity;
import com.budejie.www.activity.auditpost.AuditPostsActivity;
import com.budejie.www.activity.htmlpage.HtmlFeatureActivity;
import com.budejie.www.activity.image.CaptureActivity;
import com.budejie.www.activity.image.SelectImageActivity;
import com.budejie.www.activity.label.h;
import com.budejie.www.activity.video.a;
import com.budejie.www.http.i;
import com.umeng.analytics.MobclickAgent;
import java.io.File;

class av$4 implements OnClickListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ Dialog b;
    final /* synthetic */ OnClickListener c;
    final /* synthetic */ av d;

    av$4(av avVar, Activity activity, Dialog dialog, OnClickListener onClickListener) {
        this.d = avVar;
        this.a = activity;
        this.b = dialog;
        this.c = onClickListener;
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.send_video:
                i.a(av.a(this.d).getString(R.string.track_action_send_video), av.a(this.d).getString(R.string.track_event_send_post));
                if (a.a()) {
                    av.a(this.a, null);
                    MobclickAgent.onEvent(this.a, "发帖菜单", "发视频");
                    MobclickAgent.onEvent(this.a, "E02-A01", "发视频");
                    aa.a("TougaoDialogTools", "发视频");
                    break;
                }
                Toast.makeText(this.a, "sd卡未挂载!", 0).show();
                return;
            case R.id.send_picture:
                this.a.startActivity(new Intent(this.a, SelectImageActivity.class));
                MobclickAgent.onEvent(this.a, "发帖菜单", "发图片");
                MobclickAgent.onEvent(this.a, "E02-A01", "发图片");
                aa.a("TougaoDialogTools", "发图片");
                this.b.dismiss();
                i.a(av.a(this.d).getString(R.string.track_action_send_picture), av.a(this.d).getString(R.string.track_event_send_post));
                break;
            case R.id.send_text:
                intent = new Intent(this.a, TougaoActivity.class);
                intent.putExtra("TOUGAO_TYPE", 29);
                this.a.startActivity(intent);
                MobclickAgent.onEvent(this.a, "发帖菜单", "发段子");
                MobclickAgent.onEvent(this.a, "E02-A01", "发段子");
                aa.a("TougaoDialogTools", "发段子");
                i.a(av.a(this.d).getString(R.string.track_action_send_text), av.a(this.d).getString(R.string.track_event_send_post));
                break;
            case R.id.send_voice:
                h.a().a(0);
                h.a().a("");
                if (a.a()) {
                    intent = new Intent(this.a, CaptureActivity.class);
                    intent.putExtra("output", new File(Environment.getExternalStorageDirectory(), i.a().d() + ".jpg").getAbsolutePath());
                    intent.putExtra("type", "voice");
                    this.a.startActivity(intent);
                    g.a(this.a).d();
                    MobclickAgent.onEvent(this.a, "发帖菜单", "发声音");
                    MobclickAgent.onEvent(this.a, "E02-A01", "发声音");
                    aa.a("TougaoDialogTools", "发声音");
                    i.a(av.a(this.d).getString(R.string.track_action_send_voice), av.a(this.d).getString(R.string.track_event_send_post));
                    break;
                }
                an.a(this.a, this.a.getString(R.string.no_sdcard), -1).show();
                return;
            case R.id.enter_posts:
                if (an.a(this.a.getSharedPreferences("weiboprefer", 0))) {
                    this.a.startActivity(new Intent(this.a, AuditPostsActivity.class));
                } else {
                    an.a(this.a, 1, "new", "shenhe", 125);
                }
                MobclickAgent.onEvent(this.a, "发帖菜单", "审核");
                MobclickAgent.onEvent(this.a, "E02-A01", "审核");
                aa.a("TougaoDialogTools", "审核");
                break;
            case R.id.send_link:
                intent = new Intent(this.a, TougaoActivity.class);
                intent.putExtra("TOUGAO_TYPE", 0);
                this.a.startActivity(intent);
                MobclickAgent.onEvent(this.a, "发帖菜单", "发链接");
                MobclickAgent.onEvent(this.a, "E02-A01", "发链接");
                aa.a("TougaoDialogTools", "发链接");
                i.a(av.a(this.d).getString(R.string.track_action_send_link), av.a(this.d).getString(R.string.track_event_send_post));
                break;
            case R.id.music_album:
                MobclickAgent.onEvent(this.a, "E02-A01", "音乐相册");
                aa.a("TougaoDialogTools", "音乐相册");
                intent = new Intent();
                intent.setClass(this.a, HtmlFeatureActivity.class);
                intent.setData(Uri.parse("http://xt.budejie.com/xuantu/album/new/"));
                this.a.startActivity(intent);
                break;
            case R.id.send_videoOnline:
                if (!an.a(av.b(this.d))) {
                    an.a((Activity) av.a(this.d), 0, "", "", 0);
                    break;
                }
                MobclickAgent.onEvent(this.a, "发帖菜单", "发直播");
                MobclickAgent.onEvent(this.a, "E02-A01", "发直播");
                aa.a("TougaoDialogTools", "发直播");
                an.a(this.a, true);
                break;
        }
        this.c.onClick(view);
    }
}
