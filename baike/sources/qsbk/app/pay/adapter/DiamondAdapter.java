package qsbk.app.pay.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.core.model.Diamond;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.pay.R;
import qsbk.app.pay.ui.PayActivity;

public class DiamondAdapter extends Adapter<android.support.v7.widget.RecyclerView.ViewHolder> {
    private String a = DiamondAdapter.class.getSimpleName();
    private PayActivity b;
    private ArrayList<Diamond> c;
    private long d;
    private int e;
    private String f;
    private String g;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public FrameLayout fl_money;
        public LinearLayout ll_pay;
        public TextView tv_addition;
        public TextView tv_level_extra;
        public TextView tv_money;
        public TextView tv_num;

        public ViewHolder(View view) {
            super(view);
            this.tv_num = (TextView) view.findViewById(R.id.tv_num);
            this.tv_money = (TextView) view.findViewById(R.id.tv_money);
            this.tv_addition = (TextView) view.findViewById(R.id.tv_addition);
            this.tv_level_extra = (TextView) view.findViewById(R.id.tv_level_extra);
            this.fl_money = (FrameLayout) view.findViewById(R.id.fl_money);
            this.ll_pay = (LinearLayout) view.findViewById(R.id.ll_pay);
        }
    }

    public static class ViewHolderBalance extends android.support.v7.widget.RecyclerView.ViewHolder {
        public FrameLayout fl_ali;
        public FrameLayout fl_qiubai;
        public FrameLayout fl_wechat;
        public ImageView iv_ali_selected;
        public ImageView iv_qiubai_selected;
        public ImageView iv_wechat_selected;
        public TextView tv_balance;

        public ViewHolderBalance(View view) {
            super(view);
            this.tv_balance = (TextView) view.findViewById(R.id.tv_balance);
            this.iv_wechat_selected = (ImageView) view.findViewById(R.id.iv_wechat_selected);
            this.iv_ali_selected = (ImageView) view.findViewById(R.id.iv_ali_selected);
            this.iv_qiubai_selected = (ImageView) view.findViewById(R.id.iv_qiubai_selected);
            this.fl_wechat = (FrameLayout) view.findViewById(R.id.fl_wechat);
            this.fl_ali = (FrameLayout) view.findViewById(R.id.fl_ali);
            this.fl_qiubai = (FrameLayout) view.findViewById(R.id.fl_qiubai);
        }
    }

    public static class ViewHolderTail extends android.support.v7.widget.RecyclerView.ViewHolder {
        public TextView pay_help;

        public ViewHolderTail(View view) {
            super(view);
            this.pay_help = (TextView) view.findViewById(R.id.pay_help);
        }
    }

    public DiamondAdapter(PayActivity payActivity, ArrayList<Diamond> arrayList, long j, int i) {
        this.b = payActivity;
        this.c = arrayList;
        this.d = j;
        this.e = i;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == this.c.size() + 1) {
            return 2;
        }
        return 1;
    }

    public android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new ViewHolderBalance(LayoutInflater.from(this.b).inflate(R.layout.pay_diamond_balance_item, viewGroup, false));
        }
        if (i == 1) {
            return new ViewHolder(LayoutInflater.from(this.b).inflate(R.layout.pay_diamond_item, viewGroup, false));
        }
        return new ViewHolderTail(LayoutInflater.from(this.b).inflate(R.layout.pay_tail_item, viewGroup, false));
    }

    public void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder) {
            Diamond diamond = (Diamond) this.c.get(i - 1);
            ViewHolder viewHolder2 = (ViewHolder) viewHolder;
            viewHolder2.tv_num.setText(diamond.ct.replace("$", Long.toString(diamond.cn)));
            viewHolder2.tv_money.setText("￥" + Float.toString(diamond.pr));
            if (TextUtils.isEmpty(diamond.cd)) {
                viewHolder2.tv_addition.setVisibility(8);
            } else {
                viewHolder2.tv_addition.setText(diamond.cd.replace("$", Long.toString(diamond.ce)));
                viewHolder2.tv_addition.setVisibility(0);
            }
            if (TextUtils.isEmpty(diamond.le)) {
                viewHolder2.tv_level_extra.setVisibility(8);
            } else {
                viewHolder2.tv_level_extra.setText(diamond.le);
                viewHolder2.tv_level_extra.setVisibility(0);
                viewHolder2.tv_addition.requestLayout();
            }
            viewHolder2.ll_pay.setOnClickListener(new a(this, diamond));
            if (this.e == 0) {
                if (diamond.pr > ((float) ConfigInfoUtil.instance().getWechatPayMax())) {
                    viewHolder2.ll_pay.setVisibility(8);
                } else {
                    viewHolder2.ll_pay.setVisibility(0);
                }
            } else if (this.e == 1) {
                if (diamond.pr > ((float) ConfigInfoUtil.instance().getAliPayMax())) {
                    viewHolder2.ll_pay.setVisibility(8);
                } else {
                    viewHolder2.ll_pay.setVisibility(0);
                }
            } else if (this.e == 2) {
                if (diamond.pr > ((float) ConfigInfoUtil.instance().getAliPayMax())) {
                    viewHolder2.ll_pay.setVisibility(8);
                } else {
                    viewHolder2.ll_pay.setVisibility(0);
                }
            }
            if (diamond.fc == 1) {
                viewHolder2.tv_num.setTextColor(Color.parseColor("#FF4468"));
                viewHolder2.tv_addition.setTextColor(-1);
                viewHolder2.tv_addition.setBackgroundColor(Color.parseColor("#FF4468"));
                viewHolder2.tv_money.setBackgroundResource(R.drawable.pay_money__first_charge_bg);
                viewHolder2.tv_money.setTextColor(-1);
                return;
            }
            viewHolder2.tv_num.setTextColor(Color.parseColor("#FF41364F"));
            viewHolder2.tv_addition.setTextColor(Color.parseColor("#FF41364F"));
            viewHolder2.tv_addition.setBackgroundColor(-1);
            viewHolder2.tv_money.setBackgroundResource(R.drawable.pay_money_bg);
            viewHolder2.tv_money.setTextColor(Color.parseColor("#FF41364F"));
        } else if (viewHolder instanceof ViewHolderBalance) {
            ViewHolderBalance viewHolderBalance = (ViewHolderBalance) viewHolder;
            viewHolderBalance.tv_balance.setText(Long.toString(this.d));
            a(viewHolderBalance, this.e, false);
            viewHolderBalance.fl_wechat.setOnClickListener(new b(this, viewHolderBalance));
            viewHolderBalance.fl_ali.setOnClickListener(new c(this, viewHolderBalance));
            viewHolderBalance.fl_qiubai.setOnClickListener(new d(this, viewHolderBalance));
        } else if ((viewHolder instanceof ViewHolderTail) && !TextUtils.isEmpty(this.f) && !TextUtils.isEmpty(this.g)) {
            ViewHolderTail viewHolderTail = (ViewHolderTail) viewHolder;
            String str = "";
            int indexOf = this.f.indexOf("$");
            int lastIndexOf = this.f.lastIndexOf("$");
            if (!(indexOf == -1 || lastIndexOf == -1)) {
                str = this.f.substring(indexOf + 1, lastIndexOf);
            }
            Object replace = this.f.replace("$", "");
            CharSequence spannableString = new SpannableString(replace);
            indexOf = replace.indexOf(str);
            if (indexOf != -1) {
                spannableString.setSpan(new ForegroundColorSpan(this.b.getResources().getColor(R.color.colorPrimary)), indexOf, str.length() + indexOf, 33);
                spannableString.setSpan(new e(this, str), indexOf, str.length() + indexOf, 33);
                viewHolderTail.pay_help.setMovementMethod(LinkMovementMethod.getInstance());
            }
            viewHolderTail.pay_help.setText(spannableString);
        }
    }

    public int getItemCount() {
        return this.c.size() + 2;
    }

    protected void a(float f, float f2) {
        if (AppUtils.getInstance().getUserInfoProvider().getUser() == null) {
            AppUtils.getInstance().getUserInfoProvider().toLogin(this.b, 1001);
        } else if (this.e == 0) {
            this.b.do_wechat_pay(f, f2);
        } else if (this.e == 1) {
            this.b.do_ali_pay(f, f2);
        } else if (this.e == 2) {
            this.b.do_qiubai_pay(f, f2);
        }
    }

    private void a(ViewHolderBalance viewHolderBalance, int i, boolean z) {
        viewHolderBalance.iv_ali_selected.setVisibility(8);
        viewHolderBalance.iv_wechat_selected.setVisibility(8);
        viewHolderBalance.iv_qiubai_selected.setVisibility(8);
        if (i == 0) {
            viewHolderBalance.iv_wechat_selected.setVisibility(0);
            PreferenceUtils.instance().putInt("payType", 0);
            this.e = 0;
        } else if (i == 1) {
            viewHolderBalance.iv_ali_selected.setVisibility(0);
            PreferenceUtils.instance().putInt("payType", 1);
            this.e = 1;
        } else if (i == 2) {
            viewHolderBalance.iv_qiubai_selected.setVisibility(0);
            PreferenceUtils.instance().putInt("payType", 2);
            this.e = 2;
        }
        if (z) {
            notifyDataSetChanged();
        }
    }

    public void setBanlance(long j) {
        this.d = j;
        notifyDataSetChanged();
    }

    public void setHelpMsg(String str) {
        this.f = str;
    }

    public void setHelpUrl(String str) {
        this.g = str;
    }

    public long getBalance() {
        return this.d;
    }
}
