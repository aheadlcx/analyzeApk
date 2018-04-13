package cn.xiaochuankeji.tieba.ui.comment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class PostOrPgcViewInComment extends RelativeLayout implements OnClickListener {
    private Context a;
    private Post b;
    private WebImageView c;
    private TextView d;
    private TextView e;
    private boolean f;

    public PostOrPgcViewInComment(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        a();
    }

    private void a() {
        b();
        getViews();
        c();
    }

    private void b() {
        LayoutInflater.from(this.a).inflate(R.layout.post_view_in_comment, this);
    }

    private void getViews() {
        this.c = (WebImageView) findViewById(R.id.pvAvatar);
        this.d = (TextView) findViewById(R.id.tvPostContent);
        this.e = (TextView) findViewById(R.id.tvTopicName);
    }

    private void c() {
        this.e.setOnClickListener(this);
    }

    public void a(Post post, boolean z) {
        this.b = post;
        this.f = z;
        if (post.hasImage()) {
            this.c.setWebImage(b.a(((ServerImage) post._imgList.get(0)).postImageId, false));
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
        this.d.setText(post._postContent);
        this.e.setText(post._topic._topicName);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvTopicName:
                if (!this.f) {
                    TopicDetailActivity.a(this.a, this.b._topic, "other", this.b._ID);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
