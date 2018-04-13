package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.BillBean;
import cn.v6.sixrooms.utils.LogUtils;
import com.budejie.www.R$styleable;
import com.emilsjolander.components.stickylistheaders.StickyListHeadersAdapter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BillAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private List<BillBean> a = new ArrayList();
    private LayoutInflater b;
    private Context c;
    private int d;
    private SparseArray<String> e = new SparseArray();
    private List<Long> f = new ArrayList();
    private List<Long> g = new ArrayList();

    public BillAdapter(Context context) {
        this.b = LayoutInflater.from(context);
        this.c = context;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        BillAdapter$b billAdapter$b;
        if (view == null) {
            view = this.b.inflate(R.layout.list_item_bill, null);
            billAdapter$b = new BillAdapter$b();
            billAdapter$b.a = (TextView) view.findViewById(R.id.item_bill_memo);
            billAdapter$b.b = (TextView) view.findViewById(R.id.item_bill_num);
            billAdapter$b.c = (TextView) view.findViewById(R.id.item_bill_time);
            billAdapter$b.d = (TextView) view.findViewById(R.id.item_bill_title);
            view.setTag(billAdapter$b);
        } else {
            billAdapter$b = (BillAdapter$b) view.getTag();
        }
        BillBean billBean = (BillBean) this.a.get(i);
        if (this.d == 3) {
            billAdapter$b.b.setText(billBean.getMoney() + "个六币");
            billAdapter$b.a.setText("(" + billBean.getMemo() + ")");
            billAdapter$b.d.setText(billBean.getTitle() + billBean.getNum() + "个");
            billAdapter$b.c.setText(a(billBean.getTm(), "HH:mm"));
        } else if (this.d == 2) {
            CharSequence spannableString;
            billAdapter$b.d.setText("支付" + billBean.getRmb() + "元");
            billAdapter$b.b.setText(billBean.getOvalue() + "个六币");
            String str = "未完成";
            if (billBean.getStatus().equals("1")) {
                spannableString = new SpannableString(billBean.getChannel() + "(" + "完成" + ")");
            } else {
                spannableString = new SpannableString(billBean.getChannel() + "(" + str + ")");
                spannableString.setSpan(new ForegroundColorSpan(SupportMenu.CATEGORY_MASK), billBean.getChannel().length(), spannableString.length(), 33);
            }
            billAdapter$b.a.setText(spannableString);
            billAdapter$b.c.setText(a(billBean.getTime(), "HH:mm"));
        } else {
            billAdapter$b.b.setText(billBean.getNum() + "个");
            billAdapter$b.a.setText(billBean.getUsername() + " (" + billBean.getMemo() + ")");
            billAdapter$b.d.setText(billBean.getTitle());
            billAdapter$b.c.setText(a(billBean.getTm(), "HH:mm"));
        }
        return view;
    }

    public void setListGiftBill(List<BillBean> list, int i, SparseArray<String> sparseArray) {
        this.a = list;
        this.d = i;
        this.e = sparseArray;
        LogUtils.i("BillAdapter", "setListBill");
        this.f.clear();
        if (i == 2) {
            for (BillBean time : list) {
                String[] split = a(time.getTime(), "yyyy#M#d#").split("#");
                long parseInt = (long) (Integer.parseInt(split[2]) + ((Integer.parseInt(split[0]) * R$styleable.Theme_Custom_share_dialog_background) + (Integer.parseInt(split[1]) * 30)));
                this.f.add(Long.valueOf(parseInt));
                if (!this.g.contains(Long.valueOf(parseInt))) {
                    this.g.add(Long.valueOf(parseInt));
                }
            }
            LogUtils.i("BillAdapter", "listTimeLong:" + this.f.size());
            LogUtils.i("BillAdapter", "listOnlyTimeLong:" + this.g.size());
        }
    }

    private static String a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        return new SimpleDateFormat(str2).format(new Date(Long.valueOf(str).longValue() * 1000));
    }

    public boolean isEnabled(int i) {
        return false;
    }

    public long getHeaderId(int i) {
        if (this.d == 2) {
            return ((Long) this.f.get(i)).longValue();
        }
        return 0;
    }

    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        BillAdapter$a billAdapter$a;
        int i2 = 0;
        if (view == null) {
            billAdapter$a = new BillAdapter$a();
            view = this.b.inflate(R.layout.list_item_bill_title, viewGroup, false);
            billAdapter$a.a = (TextView) view.findViewById(R.id.item_bill_title_content);
            billAdapter$a.b = (TextView) view.findViewById(R.id.item_bill_title_content_left);
            view.setTag(billAdapter$a);
        } else {
            billAdapter$a = (BillAdapter$a) view.getTag();
        }
        if (this.d != 2) {
            billAdapter$a.a.setText(getWeekTime((String) this.e.get(0), "yyyy年MM月dd日"));
            billAdapter$a.b.setVisibility(8);
            billAdapter$a.a.setVisibility(0);
        } else {
            billAdapter$a.b.setVisibility(0);
            billAdapter$a.a.setVisibility(8);
            if (this.f.size() > i) {
                while (i2 < this.g.size()) {
                    if (((Long) this.g.get(i2)).equals(this.f.get(i))) {
                        billAdapter$a.b.setText(a(((BillBean) this.a.get(i)).getTime(), "M月d日"));
                    }
                    i2++;
                }
            }
        }
        return view;
    }

    public String getWeekTime(String str, String str2) {
        int i = 7;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        String str3;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        Date date = new Date(Long.valueOf(str).longValue() * 1000);
        String format = simpleDateFormat.format(date);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        if (instance.get(7) != 1) {
            i = instance.get(7) - 1;
        }
        StringBuilder append = new StringBuilder().append(format).append(" ");
        switch (i) {
            case 1:
                str3 = "星期一";
                break;
            case 2:
                str3 = "星期二";
                break;
            case 3:
                str3 = "星期三";
                break;
            case 4:
                str3 = "星期四";
                break;
            case 5:
                str3 = "星期五";
                break;
            case 6:
                str3 = "星期六";
                break;
            case 7:
                str3 = "星期日";
                break;
            default:
                str3 = "";
                break;
        }
        return append.append(str3).toString();
    }
}
