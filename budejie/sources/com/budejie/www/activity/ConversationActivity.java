package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.Conversation.SyncListener;
import com.umeng.fb.model.DevReply;
import com.umeng.fb.model.Reply;
import com.umeng.fb.model.UserInfo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConversationActivity extends SensorBaseActivity implements OnClickListener {
    EditText a;
    EditText b;
    private FeedbackAgent c;
    private Conversation d;
    private a e;
    private ListView f;
    private Button g;
    private TextView h;
    private Button i;
    private Toast j;
    private SharedPreferences k;
    private RelativeLayout l;
    private RelativeLayout m;

    class a extends BaseAdapter {
        Context a;
        LayoutInflater b = LayoutInflater.from(this.a);
        final /* synthetic */ ConversationActivity c;

        public a(ConversationActivity conversationActivity, Context context) {
            this.c = conversationActivity;
            this.a = context;
        }

        public int getCount() {
            List replyList = this.c.d.getReplyList();
            return replyList == null ? 1 : replyList.size() + 1;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate;
            ConversationActivity$a$a conversationActivity$a$a;
            if (view == null) {
                inflate = this.b.inflate(R.layout.umeng_fb_list_item, null);
                ConversationActivity$a$a conversationActivity$a$a2 = new ConversationActivity$a$a(this);
                conversationActivity$a$a2.a = (TextView) inflate.findViewById(R.id.umeng_fb_reply_date);
                conversationActivity$a$a2.b = (TextView) inflate.findViewById(R.id.umeng_fb_reply_content);
                inflate.setTag(conversationActivity$a$a2);
                conversationActivity$a$a = conversationActivity$a$a2;
            } else {
                conversationActivity$a$a = (ConversationActivity$a$a) view.getTag();
                inflate = view;
            }
            if (i == 0) {
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.leftMargin = an.a(this.a, 8);
                layoutParams.addRule(9);
                conversationActivity$a$a.b.setLayoutParams(layoutParams);
                conversationActivity$a$a.b.setBackgroundResource(R.drawable.umeng_fb_reply_left_bg);
                long j = this.c.k.getLong("firstTimeUsingFeedback", 0);
                if (j == 0) {
                    j = System.currentTimeMillis();
                }
                conversationActivity$a$a.a.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j)));
                conversationActivity$a$a.b.setText(R.string.umeng_fb_reply_content_default);
                return inflate;
            }
            Reply reply = (Reply) this.c.d.getReplyList().get(i - 1);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            if (reply instanceof DevReply) {
                layoutParams2.leftMargin = an.a(this.a, 8);
                layoutParams2.addRule(9);
                conversationActivity$a$a.b.setLayoutParams(layoutParams2);
                conversationActivity$a$a.b.setBackgroundResource(R.drawable.umeng_fb_reply_left_bg);
            } else {
                layoutParams2.rightMargin = an.a(this.a, 8);
                layoutParams2.addRule(11);
                conversationActivity$a$a.b.setLayoutParams(layoutParams2);
                conversationActivity$a$a.b.setBackgroundResource(R.drawable.umeng_fb_reply_right_bg);
            }
            conversationActivity$a$a.a.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(reply.getDatetime()));
            conversationActivity$a$a.b.setText(reply.getContent());
            return inflate;
        }

        public Object getItem(int i) {
            return this.c.d.getReplyList().get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTheme(R.style.ThemeLight);
        setContentView(R.layout.umeng_fb_activity_conversation);
        com.budejie.www.widget.a.a((Activity) this);
        this.k = getSharedPreferences("weiboprefer", 0);
        b();
        g();
        try {
            this.c = new FeedbackAgent(this);
            this.d = this.c.getDefaultConversation();
            e();
            c();
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    protected void onResume() {
        super.onResume();
        a();
    }

    private void b() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.g = (Button) findViewById(R.id.title_left_btn);
        this.h = (TextView) findViewById(R.id.title_center_txt);
        this.b = (EditText) findViewById(R.id.umeng_fb_contact_info);
        this.l = (RelativeLayout) findViewById(R.id.title);
        this.m = (RelativeLayout) findViewById(R.id.umeng_fb_reply_content_wrapper);
        this.g.setOnClickListener(this);
        this.h.setText(R.string.yijian);
        this.g.setVisibility(0);
        this.a = (EditText) findViewById(R.id.umeng_fb_reply_content);
        this.a.setText(this.k.getString("userLastFeedbackInput", ""));
        this.i = (Button) findViewById(R.id.umeng_fb_send);
        this.i.setOnClickListener(this);
    }

    private void c() {
        this.f = (ListView) findViewById(R.id.umeng_fb_reply_list);
        this.e = new a(this, this);
        this.f.setAdapter(this.e);
    }

    private void d() {
        try {
            f();
            String trim = this.a.getEditableText().toString().trim();
            if ("".equals(trim)) {
                this.j = an.a((Activity) this, getString(R.string.umeng_fb_no_content), -1);
                this.j.show();
                return;
            }
            this.a.getEditableText().clear();
            this.d.addUserReply(trim);
            a();
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(this.a.getWindowToken(), 0);
            }
        } catch (Exception e) {
            aa.e("ConversationActivity", "sendFeedback , error");
            e.printStackTrace();
        }
    }

    private void e() {
        try {
            String str = "";
            UserInfo userInfo = this.c.getUserInfo();
            if (userInfo == null) {
                this.b.setText("");
                return;
            }
            Map contact = userInfo.getContact();
            if (contact == null) {
                this.b.setText("");
                return;
            }
            str = (String) contact.get("plain");
            if (str == null) {
                this.b.setText("");
            } else {
                this.b.setText(str);
            }
        } catch (Exception e) {
            aa.e("ConversationActivity", "showContactInfo , error");
            e.printStackTrace();
        }
    }

    private void f() {
        try {
            UserInfo userInfo;
            UserInfo userInfo2 = this.c.getUserInfo();
            if (userInfo2 == null) {
                userInfo = new UserInfo();
            } else {
                userInfo = userInfo2;
            }
            Map contact = userInfo.getContact();
            if (contact == null) {
                contact = new HashMap();
            }
            contact.put("plain", this.b.getEditableText().toString());
            userInfo.setContact(contact);
            this.c.setUserInfo(userInfo);
        } catch (Exception e) {
            aa.e("ConversationActivity", "saveContactInfo , error");
            e.printStackTrace();
        }
    }

    private void g() {
        if (this.k.getLong("firstTimeUsingFeedback", 0) == 0) {
            this.k.edit().putLong("firstTimeUsingFeedback", System.currentTimeMillis()).commit();
        }
    }

    private void h() {
        this.k.edit().putString("userLastFeedbackInput", this.a.getEditableText().toString().trim()).commit();
    }

    public void onClick(View view) {
        if (view == this.g) {
            finish();
        } else if (view == this.i) {
            d();
        }
    }

    protected void onPause() {
        super.onPause();
        h();
    }

    void a() {
        this.d.sync(new SyncListener(this) {
            final /* synthetic */ ConversationActivity a;

            {
                this.a = r1;
            }

            public void onSendUserReply(List<Reply> list) {
                aa.c("ConversationActivity", "onSendUserReply");
                this.a.e.notifyDataSetChanged();
            }

            public void onReceiveDevReply(List<DevReply> list) {
                aa.c("ConversationActivity", "onReceiveDevReply");
                this.a.e.notifyDataSetChanged();
            }
        });
    }
}
