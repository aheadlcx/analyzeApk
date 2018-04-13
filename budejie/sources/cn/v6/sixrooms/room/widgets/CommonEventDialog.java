package cn.v6.sixrooms.room.widgets;

import android.app.Dialog;
import android.net.Uri;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoomEventFloatBean;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.engine.FreeVoteNumEngine;
import cn.v6.sixrooms.socket.chat.CommonEventStatusBean;
import cn.v6.sixrooms.socket.chat.CommonEventStatusBean$GiftVoteMsg;
import cn.v6.sixrooms.socket.chat.CommonEventStatusBean$VoteMsgBean;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;

public class CommonEventDialog extends Dialog implements OnClickListener {
    List<TextView> a = new ArrayList();
    private BaseRoomActivity b;
    private RoomEventFloatBean c;
    private CommonEventStatusBean d;
    private VoteClickListener e;
    private CommonEventStatusBean$GiftVoteMsg f;
    private SimpleDraweeView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private TextView u;
    private TextView v;
    private TextView w;
    private TextView x;
    private FreeVoteNumEngine y;

    public interface VoteClickListener {
        void onFreeVoteClick(String str);

        void onVoteClick(String str);
    }

    public void setmEventStatusbean(CommonEventStatusBean commonEventStatusBean) {
        this.d = commonEventStatusBean;
    }

    public CommonEventDialog(BaseRoomActivity baseRoomActivity, RoomEventFloatBean roomEventFloatBean, CommonEventStatusBean commonEventStatusBean, VoteClickListener voteClickListener) {
        super(baseRoomActivity, R.style.CommonEvent_NoTitle);
        this.b = baseRoomActivity;
        this.c = roomEventFloatBean;
        this.d = commonEventStatusBean;
        this.e = voteClickListener;
        getWindow().addFlags(1024);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_common_event);
        this.g = (SimpleDraweeView) findViewById(R.id.iv_banner);
        this.h = (TextView) findViewById(R.id.tv_schtitle);
        this.i = (TextView) findViewById(R.id.tv_ruleurl);
        this.j = (TextView) findViewById(R.id.tv_vote);
        this.k = (TextView) findViewById(R.id.tv_giftVoteMsg);
        this.l = (TextView) findViewById(R.id.tv_freeVoteMsg);
        this.m = (TextView) findViewById(R.id.tv_free_vote_num);
        this.n = (TextView) findViewById(R.id.tv_free_vote);
        this.o = (TextView) findViewById(R.id.line1_left);
        this.p = (TextView) findViewById(R.id.line1_right);
        this.q = (TextView) findViewById(R.id.line2_left);
        this.r = (TextView) findViewById(R.id.line2_right);
        this.s = (TextView) findViewById(R.id.line3_left);
        this.t = (TextView) findViewById(R.id.line3_right);
        this.u = (TextView) findViewById(R.id.line4_left);
        this.v = (TextView) findViewById(R.id.line4_right);
        this.w = (TextView) findViewById(R.id.line5_left);
        this.x = (TextView) findViewById(R.id.line5_right);
        this.a.add(this.o);
        this.a.add(this.p);
        this.a.add(this.q);
        this.a.add(this.r);
        this.a.add(this.s);
        this.a.add(this.t);
        this.a.add(this.u);
        this.a.add(this.v);
        this.a.add(this.w);
        this.a.add(this.x);
        String ebground = this.c.getEbground();
        SimpleDraweeView simpleDraweeView = this.g;
        if (!TextUtils.isEmpty(ebground)) {
            ebground = ebground.replace(".png", "@2x.png");
        }
        simpleDraweeView.setImageURI(Uri.parse(ebground));
        this.h.setText(this.c.getSchtitle());
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.n.setOnClickListener(this);
    }

    public void updateFreeVoteNum(String str) {
        TextView textView = this.m;
        CharSequence spannableString = new SpannableString("您目前已经积累 " + str + " 票");
        spannableString.setSpan(new TextAppearanceSpan(this.b, R.style.common_event_free_desc), 0, 8, 33);
        spannableString.setSpan(new TextAppearanceSpan(this.b, R.style.common_event_free_num), 8, str.length() + 8, 33);
        spannableString.setSpan(new TextAppearanceSpan(this.b, R.style.common_event_free_desc), str.length() + 8, str.length() + 10, 33);
        textView.setText(spannableString, BufferType.SPANNABLE);
    }

    public void updateVoteMsg(List<CommonEventStatusBean$VoteMsgBean> list) {
        if (list != null) {
            for (TextView visibility : this.a) {
                visibility.setVisibility(8);
            }
            int i = 1;
            int i2 = 1;
            int i3 = 1;
            int i4 = 1;
            int i5 = 1;
            for (CommonEventStatusBean$VoteMsgBean commonEventStatusBean$VoteMsgBean : list) {
                switch (Integer.parseInt(TextUtils.isEmpty(commonEventStatusBean$VoteMsgBean.getAtline()) ? "0" : commonEventStatusBean$VoteMsgBean.getAtline())) {
                    case 1:
                        if (i5 == 0) {
                            this.p.setVisibility(0);
                            a(commonEventStatusBean$VoteMsgBean.getTitle(), commonEventStatusBean$VoteMsgBean.getValue(), this.p);
                            break;
                        }
                        this.o.setVisibility(0);
                        a(commonEventStatusBean$VoteMsgBean.getTitle(), commonEventStatusBean$VoteMsgBean.getValue(), this.o);
                        i5 = 0;
                        break;
                    case 2:
                        if (i4 == 0) {
                            this.r.setVisibility(0);
                            a(commonEventStatusBean$VoteMsgBean.getTitle(), commonEventStatusBean$VoteMsgBean.getValue(), this.r);
                            break;
                        }
                        this.q.setVisibility(0);
                        a(commonEventStatusBean$VoteMsgBean.getTitle(), commonEventStatusBean$VoteMsgBean.getValue(), this.q);
                        i4 = 0;
                        break;
                    case 3:
                        if (i3 == 0) {
                            this.t.setVisibility(0);
                            a(commonEventStatusBean$VoteMsgBean.getTitle(), commonEventStatusBean$VoteMsgBean.getValue(), this.t);
                            break;
                        }
                        this.s.setVisibility(0);
                        a(commonEventStatusBean$VoteMsgBean.getTitle(), commonEventStatusBean$VoteMsgBean.getValue(), this.s);
                        i3 = 0;
                        break;
                    case 4:
                        if (i2 == 0) {
                            this.v.setVisibility(0);
                            a(commonEventStatusBean$VoteMsgBean.getTitle(), commonEventStatusBean$VoteMsgBean.getValue(), this.v);
                            break;
                        }
                        this.u.setVisibility(0);
                        a(commonEventStatusBean$VoteMsgBean.getTitle(), commonEventStatusBean$VoteMsgBean.getValue(), this.u);
                        i2 = 0;
                        break;
                    case 5:
                        if (i == 0) {
                            this.x.setVisibility(0);
                            a(commonEventStatusBean$VoteMsgBean.getTitle(), commonEventStatusBean$VoteMsgBean.getValue(), this.x);
                            break;
                        }
                        this.w.setVisibility(0);
                        a(commonEventStatusBean$VoteMsgBean.getTitle(), commonEventStatusBean$VoteMsgBean.getValue(), this.w);
                        i = 0;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_ruleurl) {
            this.b.startEventActivity(this.c.getRuleurl(), "");
        } else if (id == R.id.tv_vote) {
            dismiss();
            this.e.onVoteClick(this.f.getGid() == null ? "" : this.f.getGid());
        } else if (id != R.id.tv_free_vote) {
        } else {
            if (LoginUtils.isLogin()) {
                this.e.onFreeVoteClick(this.c.getEid());
                return;
            }
            dismiss();
            this.b.showLoginDialog();
        }
    }

    private void a(String str, String str2, TextView textView) {
        CharSequence spannableString = new SpannableString(str + ": " + str2);
        spannableString.setSpan(new TextAppearanceSpan(this.b, R.style.common_event_title), 0, str.length() + 2, 33);
        spannableString.setSpan(new TextAppearanceSpan(this.b, R.style.common_event_value), str.length() + 2, (str.length() + 2) + str2.length(), 33);
        textView.setText(spannableString, BufferType.SPANNABLE);
    }

    public void show() {
        CommonEventStatusBean commonEventStatusBean = this.d;
        if (commonEventStatusBean != null) {
            this.f = commonEventStatusBean.getGiftVoteMsg();
            this.k.setText(this.f.getTitle());
            this.l.setText(commonEventStatusBean.getFreeVoteMsg());
            updateVoteMsg(commonEventStatusBean.getVoteMsg());
        }
        if (this.y == null) {
            this.y = new FreeVoteNumEngine(new a(this));
        }
        this.y.getFreeVoteNum(this.c.getEid(), LoginUtils.getLoginUID(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
        super.show();
    }
}
