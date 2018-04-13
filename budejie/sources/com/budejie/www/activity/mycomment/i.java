package com.budejie.www.activity.mycomment;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.view.AudioLayout;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.b;
import com.budejie.www.adapter.e.a;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.aa;
import com.budejie.www.util.ac;
import com.budejie.www.widget.CmtPopupWindow;

public class i extends b {
    private RelativeLayout i;
    private a j;

    public i(Activity activity, e.a aVar, d dVar, int i, CmtPopupWindow cmtPopupWindow) {
        super(activity, aVar, dVar, i, cmtPopupWindow);
    }

    public View b() {
        j jVar = new j();
        ViewGroup viewGroup = (ViewGroup) this.e.inflate(R.layout.new_mycomment_list_item_video, null);
        jVar.j = (LinearLayout) viewGroup.findViewById(R.id.item_layout);
        jVar.k = (AsyncImageView) viewGroup.findViewById(R.id.writerProfile);
        jVar.l = (TextView) viewGroup.findViewById(R.id.writerName);
        jVar.m = (TextView) viewGroup.findViewById(R.id.commentHint);
        jVar.o = (LinearLayout) viewGroup.findViewById(R.id.name_time_layout);
        jVar.q = (AudioLayout) viewGroup.findViewById(R.id.mycomment_content_voice);
        jVar.p = (TextView) viewGroup.findViewById(R.id.mycomment_content_textview);
        jVar.r = (LinearLayout) viewGroup.findViewById(R.id.mycomment_content_layout);
        jVar.s = (LinearLayout) viewGroup.findViewById(R.id.mycomment_reply_content_layout);
        jVar.u = (TextView) viewGroup.findViewById(R.id.mycomment_reply_content_textview);
        jVar.v = (AudioLayout) viewGroup.findViewById(R.id.mycomment_reply_content_voice);
        jVar.w = (TextView) viewGroup.findViewById(R.id.mycomment_username_textview);
        jVar.x = (TextView) viewGroup.findViewById(R.id.mycomment_reply_username_textview);
        jVar.t = (TextView) viewGroup.findViewById(R.id.content);
        jVar.a = (AsyncImageView) viewGroup.findViewById(R.id.main_img);
        jVar.b = (ViewGroup) viewGroup.findViewById(R.id.progress_layout);
        jVar.c = (ProgressBar) viewGroup.findViewById(R.id.gif_progress);
        jVar.n = viewGroup.findViewById(R.id.writerProfile_ready);
        jVar.d = viewGroup.findViewById(R.id.imageready);
        jVar.e = (FrameLayout) viewGroup.findViewById(R.id.image_layout);
        jVar.f = (LinearLayout) viewGroup.findViewById(R.id.error_img_layout);
        jVar.y = (TextView) viewGroup.findViewById(R.id.playTimeLength);
        jVar.z = (TextView) viewGroup.findViewById(R.id.itemPlayCount);
        jVar.A = viewGroup.findViewById(R.id.voiceViewFill);
        jVar.B = (RelativeLayout) viewGroup.findViewById(R.id.video_container_layout);
        jVar.C = (ImageView) viewGroup.findViewById(R.id.video_play_btn);
        jVar.D = (RelativeLayout) viewGroup.findViewById(R.id.video_play_complete);
        jVar.E = (TextView) viewGroup.findViewById(R.id.video_replay);
        OnClickListener anonymousClass1 = new OnClickListener(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Object[] objArr = (Object[]) view.getTag();
                switch (view.getId()) {
                    case R.id.video_replay:
                        aa.b("PostVideoView", "video_replay");
                        k.a(this.a.d).r();
                        this.a.i.setVisibility(8);
                        return;
                    case R.id.video_share:
                        aa.b("PostVideoView", "video_share");
                        if (((ListItemObject) objArr[0]) == null || ((ListItemObject) objArr[0]).getOriginal_topic() == null) {
                            this.a.j.a(view, (ListItemObject) objArr[0], this.a.g);
                            return;
                        } else {
                            this.a.j.a(view, ((ListItemObject) objArr[0]).getOriginal_topic(), this.a.g);
                            return;
                        }
                    case R.id.video_play_btn:
                        if (objArr != null) {
                            k.a(this.a.d).a((ListItemObject) objArr[0], (k.a) objArr[1], null);
                            return;
                        }
                        return;
                    case R.id.video_play_complete:
                        aa.b("PostVideoView", "video_play_complete");
                        return;
                    default:
                        return;
                }
            }
        };
        jVar.a.setOnClickListener(anonymousClass1);
        jVar.C.setOnClickListener(anonymousClass1);
        jVar.E.setOnClickListener(anonymousClass1);
        jVar.D.setOnClickListener(anonymousClass1);
        viewGroup.setTag(jVar);
        return viewGroup;
    }

    public int c() {
        return RowType.COMMENT_VIDEO_ROW.ordinal();
    }

    public void a(b bVar) {
        j jVar = (j) bVar;
        this.i = jVar.D;
        jVar.C.setVisibility(8);
        super.a(bVar);
        com.budejie.www.adapter.b.a.a(jVar.B, this.c.getWidth(), this.c.getHeight());
        k a = k.a(this.d);
        a.getClass();
        k.a aVar = new k.a(a, this.g, jVar.B);
        Object obj = new Object[]{this.c, aVar};
        jVar.a.setTag(obj);
        jVar.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Object[] objArr = (Object[]) view.getTag();
                if (objArr != null) {
                    k.a(this.a.d).a((ListItemObject) objArr[0], (k.a) objArr[1], null);
                }
            }
        });
        jVar.C.setTag(obj);
        jVar.A.setVisibility(0);
        int a2 = this.a.a(jVar.A);
        jVar.y.setVisibility(0);
        this.a.a(jVar.y, a2);
        jVar.y.setText(ac.a((long) (Integer.parseInt(this.c.getVideotime()) * 1000)));
        if (TextUtils.isEmpty(this.c.getPlaycount())) {
            jVar.z.setText("");
        } else {
            this.a.b(jVar.z, a2);
            jVar.z.setText(this.c.getPlaycount() + "次播放");
        }
        jVar.d.setLayoutParams(new LayoutParams(-1, -1));
    }

    protected void a(c cVar) {
        ((j) cVar).C.setVisibility(0);
    }
}
