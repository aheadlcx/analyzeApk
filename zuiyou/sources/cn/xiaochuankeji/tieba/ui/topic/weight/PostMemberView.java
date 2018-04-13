package cn.xiaochuankeji.tieba.ui.topic.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Medal;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.d.h;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.d;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class PostMemberView extends RelativeLayout implements OnLongClickListener {
    private WebImageView a;
    private ImageView b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private FollowView f;
    private TextView g;
    private View h;
    private View i;
    private View j;
    private View k;
    private View l;
    private View m;
    private View n;
    private a o;

    public interface a {
        void a();

        void a(ViewType viewType);

        void b();

        void c();

        void d();
    }

    public enum ViewType {
        MORE,
        DELETE,
        FOLLOW,
        CANCEL_FOLLOW,
        CANCEL_FAVOR
    }

    public PostMemberView(Context context) {
        super(context);
        a();
    }

    public PostMemberView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public PostMemberView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public boolean onLongClick(View view) {
        if (this.o == null) {
            return false;
        }
        this.o.b();
        return true;
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_post_member_view, this);
        b();
        c();
    }

    private void b() {
        this.a = (WebImageView) findViewById(R.id.post_head_avatar_img);
        this.b = (ImageView) findViewById(R.id.post_head_icon_level);
        this.c = (ImageView) findViewById(R.id.post_head_icon_talent);
        this.d = (TextView) findViewById(R.id.post_head_name);
        this.e = (TextView) findViewById(R.id.post_head_ct);
        this.f = (FollowView) findViewById(R.id.post_head_follow_view);
        this.g = (TextView) findViewById(R.id.post_head_description);
        this.h = findViewById(R.id.post_head_cancel_follow);
        this.i = findViewById(R.id.post_head_cancel_favor);
        this.j = findViewById(R.id.post_head_icon_official);
        this.k = findViewById(R.id.post_head_icon_delete);
        this.l = findViewById(R.id.post_head_icon_follow);
        this.m = findViewById(R.id.post_head_icon_more);
        this.n = findViewById(R.id.post_head_icon_hot);
    }

    private void c() {
        OnClickListener anonymousClass1 = new OnClickListener(this) {
            final /* synthetic */ PostMemberView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.o != null) {
                    this.a.o.a();
                }
            }
        };
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PostMemberView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.o != null) {
                    this.a.o.c();
                }
            }
        });
        this.d.setOnClickListener(anonymousClass1);
        this.a.setOnClickListener(anonymousClass1);
        this.d.setOnLongClickListener(this);
        this.a.setOnLongClickListener(this);
        this.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PostMemberView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                e.d(this.a.getContext());
            }
        });
        this.j.setOnLongClickListener(this);
        setOnLongClickListener(this);
    }

    public void a(MemberInfoBean memberInfoBean, long j, boolean z, String str, ViewType... viewTypeArr) {
        setDescriptionValue(str);
        a(memberInfoBean, j, z);
        a(HolderCreator.a(memberInfoBean.getId()), viewTypeArr);
    }

    private void setDescriptionValue(String str) {
        if (str != null) {
            this.g.setVisibility(0);
            this.g.setText(str);
            this.g.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ PostMemberView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.o != null) {
                        this.a.o.d();
                    }
                }
            });
            return;
        }
        this.g.setOnClickListener(null);
        this.g.setVisibility(8);
    }

    public void a(MemberInfoBean memberInfoBean, long j, boolean z, ViewType... viewTypeArr) {
        a(memberInfoBean, j, z, null, viewTypeArr);
    }

    private void a(MemberInfoBean memberInfoBean, long j, boolean z) {
        int i;
        this.e.setText(j == 0 ? "" : h.a(1000 * j));
        TextView textView = this.e;
        if (j == 0) {
            i = 8;
        } else {
            i = 0;
        }
        textView.setVisibility(i);
        View view = this.n;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        if (memberInfoBean != null) {
            this.a.setWebImage(b.a(memberInfoBean.getId(), memberInfoBean.getAvatarId()));
            this.d.setText(memberInfoBean.getNickName());
            this.j.setVisibility(memberInfoBean.isOfficial() ? 0 : 8);
            final Medal medal = memberInfoBean.getMedal();
            if (medal != null) {
                this.c.setVisibility(0);
                switch (medal.original) {
                    case 1:
                        this.c.setImageResource(c.a.d.a.a.a().d(R.drawable.talent_original));
                        break;
                    case 2:
                        this.c.setImageResource(c.a.d.a.a.a().d(R.drawable.talent));
                        break;
                    case 3:
                        this.c.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_talent_small_icon));
                        break;
                    default:
                        this.c.setVisibility(8);
                        break;
                }
                this.c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ PostMemberView b;

                    public void onClick(View view) {
                        new d(this.b.getContext(), medal).a(this.b.c).show();
                    }
                });
            } else {
                this.c.setOnClickListener(null);
                this.c.setVisibility(8);
            }
            i = memberInfoBean.getTopicRole();
            this.b.setVisibility(0);
            switch (i) {
                case 1:
                    this.b.setImageResource(R.drawable.topic_talent_small_icon);
                    return;
                case 2:
                    this.b.setImageResource(R.drawable.topic_admin_small_icon);
                    return;
                case 4:
                    this.b.setImageResource(R.drawable.topic_holder_small_icon);
                    return;
                case 8:
                    this.b.setImageResource(R.drawable.topic_guard_small_icon);
                    return;
                default:
                    this.b.setVisibility(8);
                    return;
            }
        }
    }

    public void a(boolean z, ViewType... viewTypeArr) {
        this.h.setVisibility(8);
        this.i.setVisibility(8);
        this.f.setVisibility(8);
        this.l.setVisibility(8);
        this.k.setVisibility(8);
        this.m.setVisibility(8);
        if (viewTypeArr != null) {
            if (viewTypeArr.length == 1 || z) {
                a(viewTypeArr[0]);
                return;
            }
            this.f.setVisibility(0);
            this.f.a(new cn.xiaochuankeji.tieba.ui.topic.weight.FollowView.a(this) {
                final /* synthetic */ PostMemberView a;

                {
                    this.a = r1;
                }

                public void onClick(ViewType viewType) {
                    this.a.b(viewType);
                }
            }, viewTypeArr);
        }
    }

    private void a(ViewType viewType) {
        if (viewType != null) {
            switch (viewType) {
                case CANCEL_FOLLOW:
                    this.h.setVisibility(0);
                    this.h.setOnLongClickListener(this);
                    this.h.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ PostMemberView a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            this.a.b(ViewType.CANCEL_FOLLOW);
                        }
                    });
                    return;
                case CANCEL_FAVOR:
                    this.i.setVisibility(0);
                    this.i.setOnLongClickListener(this);
                    this.i.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ PostMemberView a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            this.a.b(ViewType.CANCEL_FAVOR);
                        }
                    });
                    return;
                case FOLLOW:
                    this.l.setVisibility(0);
                    this.l.setOnLongClickListener(this);
                    this.l.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ PostMemberView a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            this.a.b(ViewType.FOLLOW);
                        }
                    });
                    return;
                case DELETE:
                    this.k.setVisibility(0);
                    this.k.setOnLongClickListener(this);
                    this.k.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ PostMemberView a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            this.a.b(ViewType.DELETE);
                        }
                    });
                    return;
                case MORE:
                    this.m.setVisibility(0);
                    this.m.setOnLongClickListener(this);
                    this.m.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ PostMemberView a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            this.a.b(ViewType.MORE);
                        }
                    });
                    return;
                default:
                    return;
            }
        }
    }

    private void b(ViewType viewType) {
        if (this.o != null) {
            switch (viewType) {
                case CANCEL_FOLLOW:
                    this.o.a(ViewType.CANCEL_FOLLOW);
                    return;
                case CANCEL_FAVOR:
                    this.o.a(ViewType.CANCEL_FAVOR);
                    return;
                case FOLLOW:
                    this.o.a(ViewType.FOLLOW);
                    return;
                case DELETE:
                    this.o.a(ViewType.DELETE);
                    return;
                case MORE:
                    this.o.a(ViewType.MORE);
                    return;
                default:
                    return;
            }
        }
    }

    public void setOnMemberViewClickListener(a aVar) {
        this.o = aVar;
    }
}
