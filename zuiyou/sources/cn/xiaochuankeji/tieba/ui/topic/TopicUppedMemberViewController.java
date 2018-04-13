package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.topic.TopicDetail;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.ArrayList;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.c;
import org.greenrobot.eventbus.l;

public class TopicUppedMemberViewController extends LinearLayout implements OnClickListener {
    private a a;
    private TopicDetail b;
    private ArrayList<b> c;

    public interface a {
        void a();
    }

    private static class b {
        View a;
        WebImageView b;
        ImageView c;

        b(View view) {
            this.a = view;
            this.b = (WebImageView) view.findViewById(R.id.avatar);
            this.c = (ImageView) view.findViewById(R.id.flag);
        }
    }

    public TopicUppedMemberViewController(Context context) {
        this(context, null);
    }

    public TopicUppedMemberViewController(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TopicUppedMemberViewController(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new ArrayList();
        setOrientation(0);
        b();
    }

    private void b() {
        for (int i = 0; i < 6; i++) {
            View inflate = inflate(getContext(), R.layout.layout_topic_upper, null);
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int applyDimension = (int) TypedValue.applyDimension(1, 40.0f, displayMetrics);
            int applyDimension2 = (int) TypedValue.applyDimension(1, 33.0f, displayMetrics);
            LayoutParams layoutParams = new LayoutParams(applyDimension, applyDimension2);
            addView(inflate, applyDimension, applyDimension2);
            this.c.add(new b(inflate));
        }
        setOnClickListener(this);
    }

    public void setData(TopicDetail topicDetail) {
        this.b = topicDetail;
        a();
    }

    @l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_TOPIC_FOLLOWED_USERS) {
            a();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (c.a().b(this)) {
            c.a().c(this);
        }
    }

    public void setOnMemberListener(a aVar) {
        this.a = aVar;
    }

    public void a() {
        if (this.b.mUppedMembers.size() <= 0) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        post(new Runnable(this) {
            final /* synthetic */ TopicUppedMemberViewController a;

            {
                this.a = r1;
            }

            public void run() {
                int min = Math.min(Math.min(6, this.a.getMeasuredWidth() / ((int) TypedValue.applyDimension(1, 40.0f, this.a.getResources().getDisplayMetrics()))), this.a.b.mUppedMembers.size() + 1);
                for (int i = 0; i < 6; i++) {
                    if (i < min) {
                        boolean z;
                        TopicUppedMemberViewController topicUppedMemberViewController = this.a;
                        if (i == min - 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        topicUppedMemberViewController.a(i, z);
                    } else {
                        ((b) this.a.c.get(i)).a.setVisibility(8);
                    }
                }
                this.a.requestLayout();
            }
        });
    }

    private void a(int i, boolean z) {
        b bVar = (b) this.c.get(i);
        bVar.a.setVisibility(0);
        if (z) {
            bVar.b.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_follow_more));
            bVar.c.setVisibility(4);
            bVar.a.setTag(null);
            return;
        }
        Member member = (Member) this.b.mUppedMembers.get(i);
        bVar.b.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(member.getId(), member.getAvatarID()));
        bVar.c.setVisibility(0);
        bVar.a.setTag(member);
        if (member.getTopicRole() == 1) {
            bVar.c.setImageResource(R.drawable.topic_talent_small_icon);
        } else if (member.getTopicRole() == 2) {
            bVar.c.setImageResource(R.drawable.topic_admin_small_icon);
        } else if (member.getTopicRole() == 4) {
            bVar.c.setImageResource(R.drawable.topic_holder_small_icon);
        } else if (member.getTopicRole() == 8) {
            bVar.c.setImageResource(R.drawable.topic_guard_small_icon);
        } else {
            bVar.c.setVisibility(4);
        }
    }

    public void onClick(View view) {
        if (this.a != null) {
            this.a.a();
        }
    }
}
