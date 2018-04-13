package cn.xiaochuankeji.tieba.ui.comment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.d.h;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class CommentTopMemberView extends RelativeLayout implements OnClickListener {
    private Context a;
    private a b;
    private WebImageView c;
    private TextView d;
    private TextView e;
    private ImageView f;
    private LinearLayout g;

    public CommentTopMemberView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        b();
    }

    private void b() {
        LayoutInflater.from(this.a).inflate(R.layout.comment_top_member_view_layout, this);
        getViews();
        this.c.setOnClickListener(this);
        this.g.setOnClickListener(this);
    }

    private void getViews() {
        this.g = (LinearLayout) findViewById(R.id.llMsg);
        this.c = (WebImageView) findViewById(R.id.pvAvatar);
        this.d = (TextView) findViewById(R.id.tvName);
        this.e = (TextView) findViewById(R.id.tvTime);
        this.f = (ImageView) findViewById(R.id.ivOwnerFlag);
    }

    public void a(a aVar, long j, boolean z) {
        a();
        this.b = aVar;
        this.c.setWebImage(b.a(aVar.a, aVar.a()));
        this.d.setText(d.b(aVar.c));
        if (z) {
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(8);
        }
        if (0 != j) {
            this.e.setText(h.a(1000 * j));
            this.e.setVisibility(0);
            return;
        }
        this.e.setVisibility(8);
    }

    public void a() {
        this.b = null;
    }

    public void onClick(View view) {
        MemberDetailActivity.a(this.a, this.b.a, this.b.d, 2, this.b.e);
    }
}
