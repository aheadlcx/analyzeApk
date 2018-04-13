package com.budejie.www.activity.mycomment;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.mycomment.e.a;
import com.budejie.www.activity.view.AudioLayout;
import com.budejie.www.activity.view.MediaPlayView;
import com.budejie.www.activity.view.MediaPlayView$a;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.b;
import com.budejie.www.g.c;
import com.budejie.www.util.ac;
import com.budejie.www.widget.CmtPopupWindow;

public class k extends b {
    private BudejieApplication i = ((BudejieApplication) this.d.getApplication());

    public k(Activity activity, a aVar, d dVar, int i, CmtPopupWindow cmtPopupWindow) {
        super(activity, aVar, dVar, i, cmtPopupWindow);
    }

    public View b() {
        l lVar = new l();
        ViewGroup viewGroup = (ViewGroup) this.e.inflate(R.layout.new_mycomment_list_item_voice, null);
        lVar.j = (LinearLayout) viewGroup.findViewById(R.id.item_layout);
        lVar.k = (AsyncImageView) viewGroup.findViewById(R.id.writerProfile);
        lVar.l = (TextView) viewGroup.findViewById(R.id.writerName);
        lVar.m = (TextView) viewGroup.findViewById(R.id.commentHint);
        lVar.o = (LinearLayout) viewGroup.findViewById(R.id.name_time_layout);
        lVar.q = (AudioLayout) viewGroup.findViewById(R.id.mycomment_content_voice);
        lVar.p = (TextView) viewGroup.findViewById(R.id.mycomment_content_textview);
        lVar.r = (LinearLayout) viewGroup.findViewById(R.id.mycomment_content_layout);
        lVar.s = (LinearLayout) viewGroup.findViewById(R.id.mycomment_reply_content_layout);
        lVar.u = (TextView) viewGroup.findViewById(R.id.mycomment_reply_content_textview);
        lVar.v = (AudioLayout) viewGroup.findViewById(R.id.mycomment_reply_content_voice);
        lVar.w = (TextView) viewGroup.findViewById(R.id.mycomment_username_textview);
        lVar.x = (TextView) viewGroup.findViewById(R.id.mycomment_reply_username_textview);
        lVar.t = (TextView) viewGroup.findViewById(R.id.content);
        lVar.a = (AsyncImageView) viewGroup.findViewById(R.id.main_img);
        lVar.b = (ViewGroup) viewGroup.findViewById(R.id.progress_layout);
        lVar.c = (ProgressBar) viewGroup.findViewById(R.id.gif_progress);
        lVar.n = viewGroup.findViewById(R.id.writerProfile_ready);
        lVar.d = viewGroup.findViewById(R.id.imageready);
        lVar.e = (FrameLayout) viewGroup.findViewById(R.id.image_layout);
        lVar.f = (LinearLayout) viewGroup.findViewById(R.id.error_img_layout);
        lVar.y = (TextView) viewGroup.findViewById(R.id.playTimeLength);
        lVar.z = (MediaPlayView) viewGroup.findViewById(R.id.mMPview);
        lVar.A = (TextView) viewGroup.findViewById(R.id.itemPlayCount);
        lVar.B = viewGroup.findViewById(R.id.voiceViewFill);
        viewGroup.setTag(lVar);
        return viewGroup;
    }

    public int c() {
        return RowType.COMMENT_SOUND_ROW.ordinal();
    }

    public void a(b bVar) {
        super.a(bVar);
        final l lVar = (l) bVar;
        lVar.z.setLayoutParams(lVar.z.getParams());
        lVar.z.setOnMediaPlayerStateListener(new c(lVar.z));
        lVar.z.setPlayPath(this.c.getVoiceUri());
        lVar.z.setServerTime(Integer.parseInt(this.c.getVoicetime()) * 1000);
        lVar.z.setDataId(this.c.getWid());
        lVar.z.setPlayingListener(new MediaPlayView$a(this) {
            final /* synthetic */ k b;

            public void a() {
                lVar.y.setVisibility(8);
                this.b.i.a(this.b.c);
            }

            public void b() {
                lVar.y.setVisibility(0);
                this.b.i.a(Status.end);
            }
        });
        ac a = ac.a(this.d);
        Object m = a.m();
        if (a.c() || a.a()) {
            if (TextUtils.isEmpty(m) || !m.equals(this.c.getVoiceUri())) {
                lVar.z.e();
            } else {
                lVar.z.a();
            }
        } else if (TextUtils.isEmpty(m) || !m.equals(this.c.getVoiceUri()) || a.b()) {
            lVar.z.e();
        } else {
            Log.e("AAA", "id:" + this.c.getName());
            lVar.z.b();
        }
        lVar.B.setVisibility(0);
        int a2 = this.a.a(lVar.B);
        lVar.y.setVisibility(0);
        this.a.a(lVar.y, a2);
        lVar.y.setText(ac.a((long) (Integer.parseInt(this.c.getVoicetime()) * 1000)));
        if (TextUtils.isEmpty(this.c.getPlaycount())) {
            lVar.A.setText("");
        } else {
            this.a.b(lVar.A, a2);
            lVar.A.setText(this.c.getPlaycount() + "次播放");
        }
        lVar.d.setLayoutParams(new LayoutParams(-1, -1));
    }

    protected void a(c cVar) {
    }
}
