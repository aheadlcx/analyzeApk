package com.umeng.fb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.Conversation.SyncListener;
import com.umeng.fb.model.DevReply;
import com.umeng.fb.model.Reply;
import java.text.SimpleDateFormat;
import java.util.List;
import u.fb.g;
import u.fb.k;
import u.fb.l;
import u.fb.m;
import u.fb.n;

public class ConversationActivity extends Activity {
    private static final String e = ConversationActivity.class.getName();
    RelativeLayout a;
    int b;
    int c;
    EditText d;
    private FeedbackAgent f;
    private Conversation g;
    private a h;
    private ListView i;
    private int j;

    class a extends BaseAdapter {
        Context a;
        LayoutInflater b = LayoutInflater.from(this.a);
        final /* synthetic */ ConversationActivity c;

        class a {
            TextView a;
            TextView b;
            final /* synthetic */ a c;

            a(a aVar) {
                this.c = aVar;
            }
        }

        public a(ConversationActivity conversationActivity, Context context) {
            this.c = conversationActivity;
            this.a = context;
        }

        public int getCount() {
            List replyList = this.c.g.getReplyList();
            return replyList == null ? 0 : replyList.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = this.b.inflate(n.c(this.a), null);
                aVar = new a(this);
                aVar.a = (TextView) view.findViewById(m.f(this.a));
                aVar.b = (TextView) view.findViewById(m.b(this.a));
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            Reply reply = (Reply) this.c.g.getReplyList().get(i);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            if (reply instanceof DevReply) {
                layoutParams.addRule(9);
                aVar.b.setLayoutParams(layoutParams);
                aVar.b.setBackgroundResource(l.b(this.a));
            } else {
                layoutParams.addRule(11);
                aVar.b.setLayoutParams(layoutParams);
                aVar.b.setBackgroundResource(l.a(this.a));
            }
            aVar.a.setText(SimpleDateFormat.getDateTimeInstance().format(reply.getDatetime()));
            aVar.b.setText(reply.getContent());
            return view;
        }

        public Object getItem(int i) {
            return this.c.g.getReplyList().get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(n.b(this));
        try {
            this.f = new FeedbackAgent(this);
            this.g = this.f.getDefaultConversation();
            this.i = (ListView) findViewById(m.a(this));
            b();
            this.h = new a(this, this);
            this.i.setAdapter(this.h);
            a();
            View findViewById = findViewById(m.c(this));
            findViewById.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ConversationActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(this.a, ContactActivity.class);
                    this.a.startActivity(intent);
                    if (VERSION.SDK_INT > 4) {
                        new Object(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void a(Activity activity) {
                                activity.overridePendingTransition(k.b(this.a.a), k.c(this.a.a));
                            }
                        }.a(this.a);
                    }
                }
            });
            if (this.f.getUserInfoLastUpdateAt() > 0) {
                findViewById.setVisibility(8);
            }
            findViewById(m.d(this)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ConversationActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.finish();
                }
            });
            this.d = (EditText) findViewById(m.b(this));
            findViewById(m.e(this)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ConversationActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    String trim = this.a.d.getEditableText().toString().trim();
                    if (!g.b(trim)) {
                        this.a.d.getEditableText().clear();
                        this.a.g.addUserReply(trim);
                        this.a.a();
                        InputMethodManager inputMethodManager = (InputMethodManager) this.a.getSystemService("input_method");
                        if (inputMethodManager != null) {
                            inputMethodManager.hideSoftInputFromWindow(this.a.d.getWindowToken(), 0);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    private void b() {
        this.a = (RelativeLayout) ((LayoutInflater) getSystemService("layout_inflater")).inflate(n.d(this), this.i, false);
        this.i.addHeaderView(this.a);
        a(this.a);
        this.b = this.a.getMeasuredHeight();
        this.c = this.a.getPaddingTop();
        this.a.setPadding(this.a.getPaddingLeft(), -this.b, this.a.getPaddingRight(), this.a.getPaddingBottom());
        this.a.setVisibility(8);
        this.i.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ ConversationActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (this.a.i.getAdapter().getCount() >= 2) {
                    switch (motionEvent.getAction()) {
                        case 0:
                            this.a.j = (int) motionEvent.getY();
                            break;
                        case 1:
                            if (this.a.i.getFirstVisiblePosition() == 0) {
                                if (this.a.a.getBottom() < this.a.b + 20 && this.a.a.getTop() <= 0) {
                                    this.a.i.setSelection(1);
                                    this.a.a.setVisibility(8);
                                    this.a.a.setPadding(this.a.a.getPaddingLeft(), -this.a.b, this.a.a.getPaddingRight(), this.a.a.getPaddingBottom());
                                    break;
                                }
                                this.a.a.setVisibility(0);
                                this.a.a.setPadding(this.a.a.getPaddingLeft(), this.a.c, this.a.a.getPaddingRight(), this.a.a.getPaddingBottom());
                                break;
                            }
                            break;
                        case 2:
                            a(motionEvent);
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }

            private void a(MotionEvent motionEvent) {
                int historySize = motionEvent.getHistorySize();
                for (int i = 0; i < historySize; i++) {
                    if (this.a.i.getFirstVisiblePosition() == 0) {
                        int historicalY = (int) (((double) ((((int) motionEvent.getHistoricalY(i)) - this.a.j) - this.a.b)) / 1.7d);
                        this.a.a.setVisibility(0);
                        this.a.a.setPadding(this.a.a.getPaddingLeft(), historicalY, this.a.a.getPaddingRight(), this.a.a.getPaddingBottom());
                    }
                }
            }
        });
        this.i.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ ConversationActivity a;
            private int b;

            {
                this.a = r1;
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (this.b != 2) {
                }
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                this.b = i;
            }
        });
    }

    private void a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i = layoutParams.height;
        if (i > 0) {
            i = MeasureSpec.makeMeasureSpec(i, 1073741824);
        } else {
            i = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, i);
    }

    void a() {
        this.g.sync(new SyncListener(this) {
            final /* synthetic */ ConversationActivity a;

            {
                this.a = r1;
            }

            public void onSendUserReply(List<Reply> list) {
                this.a.h.notifyDataSetChanged();
            }

            public void onReceiveDevReply(List<DevReply> list) {
            }
        });
    }
}
