package cn.xiaochuankeji.tieba.ui.topic.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView.ViewType;

public class FollowView extends LinearLayout {
    private ImageView a;
    private View b;
    private View c;

    public interface a {
        void onClick(ViewType viewType);
    }

    public FollowView(Context context) {
        super(context);
        a();
    }

    public FollowView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public FollowView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_follow_view, this);
        this.a = (ImageView) findViewById(R.id.follow_view_icon_follow);
        this.b = findViewById(R.id.follow_view_icon_delete);
        this.c = findViewById(R.id.follow_view_icon_more);
    }

    public void a(final a aVar, ViewType... viewTypeArr) {
        this.a.setVisibility(8);
        this.b.setVisibility(8);
        this.c.setVisibility(8);
        for (ViewType viewType : viewTypeArr) {
            if (viewType != null) {
                switch (viewType) {
                    case FOLLOW:
                        this.a.setImageResource(R.drawable.icon_feed_follow);
                        this.a.setVisibility(0);
                        this.a.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ FollowView b;

                            public void onClick(View view) {
                                aVar.onClick(ViewType.FOLLOW);
                            }
                        });
                        break;
                    case CANCEL_FOLLOW:
                        this.a.setImageResource(R.drawable.icon_feed_followed);
                        this.a.setVisibility(0);
                        this.a.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ FollowView b;

                            public void onClick(View view) {
                                aVar.onClick(ViewType.CANCEL_FOLLOW);
                            }
                        });
                        break;
                    case MORE:
                        this.c.setVisibility(0);
                        this.c.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ FollowView b;

                            public void onClick(View view) {
                                aVar.onClick(ViewType.MORE);
                            }
                        });
                        break;
                    case DELETE:
                        this.b.setVisibility(0);
                        this.b.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ FollowView b;

                            public void onClick(View view) {
                                aVar.onClick(ViewType.DELETE);
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
