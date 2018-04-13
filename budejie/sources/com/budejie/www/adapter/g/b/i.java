package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.mycomment.MyCommentInfo;
import com.budejie.www.activity.view.AudioLayout;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.aa;
import com.budejie.www.util.ac;
import com.budejie.www.util.an;
import com.budejie.www.util.j;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;

public class i extends a<ListItemObject> {
    private RelativeLayout e;
    private LinearLayout f;
    private long g;
    private boolean h;
    private ClickableSpan i = new ClickableSpan(this) {
        final /* synthetic */ i a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            aa.b("clickableSpan", "clickableSpan onClick");
            if (!this.a.h) {
                try {
                    MyCommentInfo myCommentInfo = (MyCommentInfo) view.getTag();
                    if (myCommentInfo != null) {
                        this.a.g = System.currentTimeMillis();
                        Intent intent = new Intent(this.a.a, PersonalProfileActivity.class);
                        intent.putExtra(PersonalProfileActivity.c, myCommentInfo.user.id);
                        this.a.a.startActivity(intent);
                    }
                } catch (Exception e) {
                }
            }
            this.a.h = false;
        }

        public void updateDrawState(@NonNull TextPaint textPaint) {
            super.updateDrawState(textPaint);
            aa.b("clickableSpan", "clickableSpan updateDrawState");
            textPaint.setColor(this.a.a.getResources().getColor(R.color.post_hot_comment_name_color));
            textPaint.setUnderlineText(false);
            textPaint.clearShadowLayer();
        }
    };
    private ClickableSpan j = new ClickableSpan(this) {
        final /* synthetic */ i a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (!this.a.h) {
                MobclickAgent.onEvent(this.a.a, "帖子流热门评论", "点击次数");
                this.a.g = System.currentTimeMillis();
                this.a.b.c.d(view, (ListItemObject) this.a.c);
            }
            this.a.h = false;
        }

        public void updateDrawState(@NonNull TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(this.a.a.getResources().getColor(an.H(this.a.a) ? R.color.post_hot_comment_content_color_light : R.color.post_hot_comment_content_color_night));
            textPaint.setUnderlineText(false);
            textPaint.clearShadowLayer();
        }
    };
    private ClickableSpan k = new ClickableSpan(this) {
        final /* synthetic */ i a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (!this.a.h) {
                MyCommentInfo myCommentInfo = (MyCommentInfo) view.getTag();
                if (myCommentInfo != null) {
                    this.a.g = System.currentTimeMillis();
                    Intent intent = new Intent(this.a.a, PersonalProfileActivity.class);
                    intent.putExtra(PersonalProfileActivity.c, myCommentInfo.precmt.user.id);
                    this.a.a.startActivity(intent);
                }
            }
            this.a.h = false;
        }

        public void updateDrawState(@NonNull TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(this.a.a.getResources().getColor(j.bM));
            textPaint.setUnderlineText(false);
            textPaint.clearShadowLayer();
        }
    };
    private ClickableSpan l = new ClickableSpan(this) {
        final /* synthetic */ i a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (!this.a.h) {
                MobclickAgent.onEvent(this.a.a, "帖子流热门评论", "点击次数");
                this.a.g = System.currentTimeMillis();
                this.a.b.c.d(view, (ListItemObject) this.a.c);
            }
            this.a.h = false;
        }

        public void updateDrawState(@NonNull TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(this.a.a.getResources().getColor(j.bN));
            textPaint.setUnderlineText(false);
            textPaint.clearShadowLayer();
        }
    };
    private ClickableSpan m = new ClickableSpan(this) {
        final /* synthetic */ i a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (!this.a.h) {
                MobclickAgent.onEvent(this.a.a, "帖子流热门评论", "点击次数");
                this.a.g = System.currentTimeMillis();
                this.a.b.c.d(view, (ListItemObject) this.a.c);
            }
            this.a.h = false;
        }

        public void updateDrawState(@NonNull TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(this.a.a.getResources().getColor(j.bN));
            textPaint.setUnderlineText(false);
            textPaint.clearShadowLayer();
        }
    };

    public i(Context context, b bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, R.layout.new_new_list_item_hot_comment_layout, viewGroup);
        this.e = (RelativeLayout) inflate.findViewById(R.id.hot_cmt_rl);
        this.f = (LinearLayout) inflate.findViewById(R.id.hot_cmt_layout);
        return inflate;
    }

    public void c() {
        ArrayList arrayList = (ArrayList) ((ListItemObject) this.c).getHotComments();
        if (arrayList == null || arrayList.size() == 0) {
            this.e.setVisibility(8);
            return;
        }
        this.e.setVisibility(0);
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b.c.d(view, (ListItemObject) this.a.c);
            }
        });
        this.f.removeAllViews();
        for (int i = 0; i < arrayList.size(); i++) {
            a((MyCommentInfo) arrayList.get(i));
        }
    }

    public void a(MyCommentInfo myCommentInfo) {
        View inflate = View.inflate(this.a, R.layout.new_new_list_item_hot_comment, null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.hot_cmt_content_layout);
        AudioLayout audioLayout = (AudioLayout) inflate.findViewById(R.id.hot_cmt_content_voice);
        TextView textView = (TextView) inflate.findViewById(R.id.hot_cmt_content_textview);
        TextView textView2 = (TextView) inflate.findViewById(R.id.hot_cmt_username_textview);
        CharSequence charSequence = "";
        if (!(myCommentInfo == null || myCommentInfo.user == null)) {
            charSequence = myCommentInfo.user.username;
        }
        if (TextUtils.isEmpty(charSequence)) {
            linearLayout.setVisibility(8);
        } else {
            MobclickAgent.onEvent(this.a, "帖子流热门评论", "出现次数");
            linearLayout.setVisibility(0);
            textView2.setVisibility(0);
            textView2.setText(myCommentInfo.user.username + "：");
            textView2.setTag(myCommentInfo);
            textView2.setOnClickListener(this);
            if (TextUtils.isEmpty(myCommentInfo.voiceuri)) {
                audioLayout.setVisibility(8);
                textView.setVisibility(0);
                textView.setHighlightColor(this.a.getResources().getColor(17170445));
                textView.setText(myCommentInfo.content);
                textView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ i a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        aa.b("PostHotCommentView", "hot_cmt_content_textview onClick");
                        if (System.currentTimeMillis() - this.a.g > 500) {
                            this.a.b.c.d(view, (ListItemObject) this.a.c);
                        }
                    }
                });
                CharSequence spannableStringBuilder;
                if (myCommentInfo.precmt == null) {
                    textView2.setVisibility(8);
                    spannableStringBuilder = new SpannableStringBuilder(myCommentInfo.user.username + "：" + myCommentInfo.content);
                    spannableStringBuilder.setSpan(this.i, 0, myCommentInfo.user.username.length(), 33);
                    spannableStringBuilder.setSpan(this.j, myCommentInfo.user.username.length(), (myCommentInfo.user.username.length() + myCommentInfo.content.length()) + 1, 33);
                    textView.setText(spannableStringBuilder);
                    textView.setTag(myCommentInfo);
                    textView.setMovementMethod(LinkMovementMethod.getInstance());
                } else if (!TextUtils.isEmpty(myCommentInfo.precmt.content)) {
                    textView2.setVisibility(8);
                    spannableStringBuilder = new SpannableStringBuilder(myCommentInfo.user.username + "：" + myCommentInfo.content + "//@" + myCommentInfo.precmt.user.username + "：" + myCommentInfo.precmt.content);
                    spannableStringBuilder.setSpan(this.i, 0, myCommentInfo.user.username.length(), 33);
                    spannableStringBuilder.setSpan(this.j, myCommentInfo.user.username.length(), (myCommentInfo.user.username.length() + myCommentInfo.content.length()) + 1, 33);
                    spannableStringBuilder.setSpan(this.m, (myCommentInfo.user.username.length() + myCommentInfo.content.length()) + 1, (myCommentInfo.user.username.length() + myCommentInfo.content.length()) + 3, 33);
                    spannableStringBuilder.setSpan(this.k, (myCommentInfo.user.username.length() + myCommentInfo.content.length()) + 3, (((myCommentInfo.user.username.length() + myCommentInfo.content.length()) + 3) + myCommentInfo.precmt.user.username.length()) + 1, 33);
                    spannableStringBuilder.setSpan(this.l, (((myCommentInfo.user.username.length() + myCommentInfo.content.length()) + 3) + myCommentInfo.precmt.user.username.length()) + 1, (((((myCommentInfo.user.username.length() + myCommentInfo.content.length()) + 3) + myCommentInfo.precmt.user.username.length()) + 1) + myCommentInfo.precmt.content.length()) + 1, 33);
                    textView.setText(spannableStringBuilder);
                    textView.setTag(myCommentInfo);
                    textView.setMovementMethod(LinkMovementMethod.getInstance());
                }
            } else {
                audioLayout.setVisibility(0);
                audioLayout.c();
                audioLayout.setPlayPath(myCommentInfo.voiceuri);
                audioLayout.setAudioTime(myCommentInfo.voicetime);
                if (!ac.a(this.a).c()) {
                    audioLayout.c();
                } else if (ac.a(this.a).m().equals(myCommentInfo.voiceuri)) {
                    audioLayout.d();
                } else {
                    audioLayout.c();
                }
                textView.setVisibility(8);
            }
        }
        this.f.addView(inflate);
    }

    public void onClick(View view) {
        if (R.id.hot_cmt_username_textview == view.getId()) {
            try {
                MyCommentInfo myCommentInfo = (MyCommentInfo) view.getTag();
                if (myCommentInfo != null) {
                    Intent intent = new Intent(this.a, PersonalProfileActivity.class);
                    intent.putExtra(PersonalProfileActivity.c, myCommentInfo.user.id);
                    this.a.startActivity(intent);
                }
            } catch (Exception e) {
            }
        } else if (R.id.hot_cmt_content_textview == view.getId()) {
            MobclickAgent.onEvent(this.a, "帖子流热门评论", "点击次数");
            this.b.c.d(view, (ListItemObject) this.c);
        }
    }
}
