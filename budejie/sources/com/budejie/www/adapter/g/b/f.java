package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.SensorBaseActivity;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.g.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.aa;
import com.budejie.www.widget.parsetagview.NewParseTagEditText;
import com.umeng.analytics.MobclickAgent;

public class f extends a<ListItemObject> implements OnLongClickListener {
    private final int e = 7;
    private NewParseTagEditText f;
    private TextView g;
    private Handler h;

    public f(Context context, b<ListItemObject> bVar) {
        super(context, bVar);
    }

    public View a(ViewGroup viewGroup) {
        View inflate = View.inflate(this.a, R.layout.post_content, viewGroup);
        ((ListItemObject) this.c).mcollapsibleState = 0;
        this.f = (NewParseTagEditText) inflate.findViewById(R.id.content);
        this.g = (TextView) inflate.findViewById(R.id.open_or_close);
        return inflate;
    }

    public void c() {
        this.f.setTextSize((float) SensorBaseActivity.textSize);
        this.g.setTextSize((float) SensorBaseActivity.textSize);
        if (RowType.RICH_ROW == this.b.d || RowType.COMMENT_RICH_TXT_ROW == this.b.d) {
            this.f.setText(((ListItemObject) this.c).getRichObject().getTitle());
        } else {
            if (((ListItemObject) this.c).getType() == "61" && TextUtils.isEmpty(((ListItemObject) this.c).getContent())) {
                ((ListItemObject) this.c).setContent(this.a.getString(R.string.reship));
            }
            this.f.setText(((ListItemObject) this.c).getContent());
        }
        if (this.b.e != 14) {
            if (((ListItemObject) this.c).mcollapsibleState == 0) {
                this.f.post(new Runnable(this) {
                    final /* synthetic */ f a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.f.getLineCount() > 7) {
                            ((ListItemObject) this.a.c).mcollapsibleState = 2;
                        } else {
                            ((ListItemObject) this.a.c).mcollapsibleState = 1;
                        }
                        this.a.a();
                    }
                });
            } else {
                a();
            }
            this.g.setOnClickListener(this);
        }
        this.f.setOnClickListener(this);
        this.f.setOnLongClickListener(this);
    }

    public void a() {
        aa.b("postcontentView", "setTextViewCollapsible()");
        if (2 == ((ListItemObject) this.c).mcollapsibleState) {
            aa.b("postcontentView", "setTextViewCollapsible() COLLAPSIBLE_STATE_SHRINKUP");
            this.g.setVisibility(0);
            this.g.setText("全文");
            this.g.setPadding(0, 0, 0, 0);
            this.f.setMaxLines(7);
        } else if (3 == ((ListItemObject) this.c).mcollapsibleState) {
            aa.b("postcontentView", "setTextViewCollapsible() COLLAPSIBLE_STATE_SPREAD");
            this.g.setVisibility(0);
            this.g.setText("收起");
            this.g.setPadding(0, 12, 0, 0);
            this.f.setMaxLines(Integer.MAX_VALUE);
        } else if (1 == ((ListItemObject) this.c).mcollapsibleState) {
            aa.b("postcontentView", "setTextViewCollapsible() COLLAPSIBLE_STATE_NONE");
            this.g.setVisibility(8);
            this.f.setMaxLines(Integer.MAX_VALUE);
        } else {
            aa.b("postcontentView", "setTextViewCollapsible() no");
        }
    }

    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.content:
                if (this.h == null) {
                    this.h = new Handler(this) {
                        final /* synthetic */ f b;

                        public void handleMessage(Message message) {
                            if (message.what == 1) {
                                this.b.b.c.e(view, (ListItemObject) this.b.c);
                            } else if (this.b.b.f.a != null) {
                                this.b.b.f.a.performClick();
                                MobclickAgent.onEvent(this.b.a, this.b.a.getString(R.string.double_click_praise), this.b.a.getString(R.string.double_click_praise_describe));
                            }
                        }
                    };
                }
                if (this.h.hasMessages(1)) {
                    this.h.removeMessages(1);
                    this.h.sendEmptyMessage(2);
                    return;
                }
                this.h.sendEmptyMessageDelayed(1, 0);
                return;
            case R.id.open_or_close:
                if (3 == ((ListItemObject) this.c).mcollapsibleState) {
                    ((ListItemObject) this.c).mcollapsibleState = 2;
                } else if (2 == ((ListItemObject) this.c).mcollapsibleState) {
                    ((ListItemObject) this.c).mcollapsibleState = 3;
                }
                a();
                return;
            default:
                return;
        }
    }

    public boolean onLongClick(View view) {
        if (this.b.f.b != null) {
            this.b.f.b.performClick();
        }
        return true;
    }
}
