package cn.xiaochuankeji.tieba.ui.follow;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.htjyb.b.a.e;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.e.b;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import org.json.JSONObject;

public class a extends BaseAdapter {
    private Context a;
    private e<Member> b;
    private boolean c;
    private boolean d;
    private b e;
    private cn.xiaochuankeji.tieba.background.e.a f;

    class a {
        WebImageView a;
        TextView b;
        ImageView c;
        Button d;
        View e;
        final /* synthetic */ a f;

        a(a aVar) {
            this.f = aVar;
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public a(Context context, e<Member> eVar, boolean z, boolean z2) {
        this.a = context;
        this.b = eVar;
        this.d = z2;
        this.c = z;
    }

    public int getCount() {
        return this.b.itemCount();
    }

    public Member a(int i) {
        return (Member) this.b.itemAt(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            a aVar2 = new a(this);
            view = LayoutInflater.from(this.a).inflate(R.layout.view_item_follow_member, viewGroup, false);
            aVar2.a = (WebImageView) view.findViewById(R.id.pv_avatar);
            aVar2.b = (TextView) view.findViewById(R.id.tv_name);
            aVar2.c = (ImageView) view.findViewById(R.id.icon_friend);
            aVar2.d = (Button) view.findViewById(R.id.btn_follow);
            aVar2.e = view.findViewById(R.id.vFansCrumb);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        final Member a = a(i);
        aVar.a.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(a.getId(), a.getAvatarID()));
        aVar.b.setText(a.getName());
        c.a.b.a(aVar.b, 0, 0, a.isFemale() ? R.drawable.ic_female : R.drawable.ic_male, 0);
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                MemberDetailActivity.a(this.b.a, a.getId());
            }
        });
        int atted = a.atted();
        if (this.c && atted == 2) {
            aVar.c.setVisibility(0);
        } else {
            aVar.c.setVisibility(8);
        }
        if (this.c) {
            aVar.d.setVisibility(0);
            if (this.d) {
                if (atted == 0) {
                    aVar.d.setText("关注");
                } else {
                    aVar.d.setText("取消关注");
                }
            } else if (atted == 2) {
                aVar.d.setText("取消关注");
            } else {
                aVar.d.setText("关注");
            }
        } else {
            aVar.d.setVisibility(8);
        }
        aVar.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                int atted = a.atted();
                if (this.b.d) {
                    if (atted == 0) {
                        this.b.a(a.getId());
                        a.setAtted(1);
                    } else {
                        this.b.b(a.getId());
                        a.setAtted(0);
                    }
                } else if (atted == 0) {
                    this.b.a(a.getId());
                    a.setAtted(2);
                } else {
                    this.b.b(a.getId());
                    a.setAtted(0);
                }
                this.b.notifyDataSetChanged();
            }
        });
        if (!this.c || this.d) {
            aVar.e.setVisibility(4);
        } else if (1 == a.newfans()) {
            aVar.e.setVisibility(0);
        } else {
            aVar.e.setVisibility(4);
        }
        return view;
    }

    private void a(long j) {
        if (cn.xiaochuankeji.tieba.ui.auth.a.a((AppCompatActivity) this.a, "follow_list", 10, 0) && this.e == null) {
            this.e = new b(j, null, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    this.a.e = null;
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    this.a.e = null;
                    g.a(xCError.getMessage());
                }
            });
            this.e.execute();
        }
    }

    private void b(long j) {
        if (cn.xiaochuankeji.tieba.ui.auth.a.a((AppCompatActivity) this.a, "follow_list", -10) && this.f == null) {
            this.f = new cn.xiaochuankeji.tieba.background.e.a(j, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    this.a.f = null;
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    this.a.f = null;
                    g.a(xCError.getMessage());
                }
            });
            this.f.execute();
        }
    }
}
