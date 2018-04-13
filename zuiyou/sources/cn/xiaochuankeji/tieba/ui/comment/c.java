package cn.xiaochuankeji.tieba.ui.comment;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;
import c.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.member.MemberCommentInfo;
import cn.xiaochuankeji.tieba.background.member.a;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.ui.my.MyCommentActivity;
import cn.xiaochuankeji.tieba.ui.widget.SDEditSheet;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.f;
import java.util.HashMap;

public class c extends BaseAdapter {
    private static int d = 0;
    private static int e = 1;
    public Context a;
    public boolean b;
    public boolean c = true;
    private a f;
    private HashMap<Long, f> g;
    private TextView h;

    public c(Context context, a aVar) {
        this.a = context;
        this.f = aVar;
        this.g = new HashMap();
    }

    public int getCount() {
        int itemCount = this.f.itemCount();
        if (!this.c) {
            return itemCount;
        }
        if (itemCount > 0) {
            return itemCount + 1;
        }
        return 0;
    }

    public Object getItem(int i) {
        return this.f.itemAt(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getViewTypeCount() {
        if (this.c) {
            return 2;
        }
        return 1;
    }

    public int getItemViewType(int i) {
        if (!this.c) {
            return d;
        }
        if (this.f.itemCount() <= 0) {
            return d;
        }
        if (i == 0) {
            return e;
        }
        return d;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (getItemViewType(i) != e) {
            d dVar;
            int i2;
            if (view == null || view.getTag() == null) {
                d dVar2 = new d(this.a, this.f);
                view = dVar2.f_();
                view.setTag(dVar2);
                dVar = dVar2;
            } else {
                dVar = (d) view.getTag();
            }
            dVar.c();
            if (getItemViewType(0) == e) {
                i2 = i - 1;
            } else {
                i2 = i;
            }
            MemberCommentInfo memberCommentInfo = (MemberCommentInfo) this.f.itemAt(i2);
            long j = memberCommentInfo.comment._id;
            f fVar = (f) this.g.get(Long.valueOf(j));
            if (fVar == null) {
                fVar = new f();
                this.g.put(Long.valueOf(j), fVar);
            }
            dVar.a(memberCommentInfo, fVar, this.b);
            if (i == getCount() - 1) {
                dVar.b(8);
                return view;
            }
            dVar.b(0);
            return view;
        } else if (this.a instanceof MyCommentActivity) {
            return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_my_comment, null);
        } else {
            if (view != null) {
                return view;
            }
            view = a();
            if (this.f.a().equals("hot")) {
                view.setSelected(false);
                return view;
            }
            view.setSelected(true);
            return view;
        }
    }

    private TextView a() {
        if (this.h != null) {
            return this.h;
        }
        this.h = new TextView(this.a);
        this.h.setLayoutParams(new LayoutParams(-1, cn.htjyb.c.a.a(30.0f, this.a)));
        this.h.setBackgroundColor(c.a.d.a.a.a().a((int) R.color.CB_1));
        this.h.setText("最新");
        b.a(this.h, 0, 0, R.drawable.ic_comment_new, 0);
        this.h.setTextSize(2, 12.0f);
        this.h.setTextColor(c.a.d.a.a.a().a((int) R.color.CT_4));
        this.h.setGravity(21);
        this.h.setPadding(cn.htjyb.c.a.a(12.0f, this.a), 0, cn.htjyb.c.a.a(12.0f, this.a), 0);
        this.h.setClickable(true);
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                SDEditSheet sDEditSheet = new SDEditSheet((Activity) this.a.a, new SDEditSheet.a(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a(int i) {
                        Object a = this.a.a.f.a();
                        switch (i) {
                            case 1:
                                if (!TextUtils.isEmpty(a) && a.equals("hot")) {
                                    this.a.a.h.setText("最新");
                                    this.a.a.f.a("new");
                                    b.a(this.a.a.h, 0, 0, R.drawable.ic_comment_new, 0);
                                    this.a.a.f.refresh();
                                    h.a(this.a.a.a, "zy_event_memberdetail_page", "评论tab_点击按最新排序");
                                    return;
                                }
                                return;
                            case 2:
                                if (!TextUtils.isEmpty(a) && a.equals("new")) {
                                    this.a.a.h.setText("最热");
                                    this.a.a.f.a("hot");
                                    b.a(this.a.a.h, 0, 0, R.drawable.ic_comment_hot, 0);
                                    this.a.a.f.refresh();
                                    h.a(this.a.a.a, "zy_event_memberdetail_page", "评论tab_点击按最热排序");
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                    }
                }, null);
                sDEditSheet.a("最新", 1);
                sDEditSheet.a("最热", 2, true);
                sDEditSheet.b();
            }
        });
        return this.h;
    }
}
