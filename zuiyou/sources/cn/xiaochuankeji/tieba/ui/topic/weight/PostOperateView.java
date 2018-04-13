package cn.xiaochuankeji.tieba.ui.topic.weight;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.data.UgcDataBean;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView.a;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class PostOperateView extends LinearLayout implements OnLongClickListener {
    private static final int a = e.a(13.0f);
    private PostItemUpDownView b;
    private TextView c;
    private TextView d;
    private OnLongClickListener e;
    private long f;

    private enum MiddleViewType {
        REPLY("评论", R.drawable.ic_comment),
        UGC("跟拍", R.drawable.ic_ugc_count);
        
        private int icon;
        private String info;

        private MiddleViewType(String str, int i) {
            this.info = str;
            this.icon = i;
        }
    }

    public PostOperateView(Context context) {
        super(context);
        a();
    }

    public PostOperateView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public PostOperateView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_post_operate, this);
        this.b = (PostItemUpDownView) findViewById(R.id.operate_up_down);
        this.d = (TextView) findViewById(R.id.operate_share);
        this.c = (TextView) findViewById(R.id.middle_view);
    }

    public void a(c cVar) {
        this.d.setText(cVar.getShareNum() == 0 ? "分享" : "" + cVar.getShareNum());
    }

    public void a(c cVar, OnClickListener onClickListener, OnClickListener onClickListener2, a aVar) {
        if (cVar != null) {
            switch (cVar.localPostType()) {
                case 1:
                case 2:
                    a(cVar, aVar);
                    break;
                case 3:
                    b(cVar, aVar);
                    break;
            }
            this.c.setOnClickListener(onClickListener2);
            this.c.setOnLongClickListener(this);
            this.d.setOnClickListener(onClickListener);
            this.d.setOnLongClickListener(this);
            this.f = cVar.getId();
        }
    }

    private void a(c cVar, a aVar) {
        if (cVar instanceof PostDataBean) {
            CharSequence access$100;
            PostDataBean postDataBean = (PostDataBean) cVar;
            setMiddleViewIcon(MiddleViewType.REPLY.icon);
            TextView textView = this.c;
            if (postDataBean.reviewCount == 0) {
                access$100 = MiddleViewType.REPLY.info;
            } else {
                access$100 = d.b(postDataBean.reviewCount);
            }
            textView.setText(access$100);
            this.d.setText(postDataBean.shareCount == 0 ? "分享" : "" + postDataBean.shareCount);
            this.b.a(postDataBean.isLiked, postDataBean.likeCount, aVar);
        }
    }

    private void b(c cVar, a aVar) {
        if (cVar instanceof UgcDataBean) {
            CharSequence access$100;
            UgcDataBean ugcDataBean = (UgcDataBean) cVar;
            setMiddleViewIcon(MiddleViewType.UGC.icon);
            TextView textView = this.c;
            if (ugcDataBean.followCount == 0) {
                access$100 = MiddleViewType.UGC.info;
            } else {
                access$100 = d.b((int) ugcDataBean.followCount);
            }
            textView.setText(access$100);
            this.d.setText(ugcDataBean.shareCount == 0 ? "分享" : "" + ugcDataBean.shareCount);
            this.b.a(ugcDataBean.isLiked, (int) ugcDataBean.likeCount, aVar);
        }
    }

    private void setMiddleViewIcon(int i) {
        Drawable drawable = getResources().getDrawable(i);
        drawable.setBounds(0, 0, a, a);
        this.c.setCompoundDrawables(drawable, null, null, null);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!org.greenrobot.eventbus.c.a().b(this)) {
            org.greenrobot.eventbus.c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        org.greenrobot.eventbus.c.a().c(this);
    }

    @Keep
    @l(a = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        if ((messageEvent.getEventType() == MessageEventType.MESSAGE_POST_CANCEL_LIKE || messageEvent.getEventType() == MessageEventType.MESSAGE_UGC_CANCEL_LIKE) && ((Long) messageEvent.getData()).longValue() == this.f) {
            this.b.b();
        }
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.e = onLongClickListener;
    }

    public boolean onLongClick(View view) {
        if (this.e == null) {
            return false;
        }
        this.e.onLongClick(view);
        return true;
    }
}
