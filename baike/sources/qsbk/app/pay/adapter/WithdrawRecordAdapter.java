package qsbk.app.pay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import qsbk.app.pay.R;
import qsbk.app.pay.model.WithRecordData;

public class WithdrawRecordAdapter extends Adapter<ViewHolder> {
    private static final ThreadLocal<SimpleDateFormat> e = new f();
    private int a = 0;
    private String b = "0";
    private ArrayList<WithRecordData> c;
    private Context d;

    public static class ContentViewHolder extends ViewHolder {
        public TextView tv_day;
        public TextView tv_money;
        public TextView tv_result;
        public TextView tv_time;

        public ContentViewHolder(View view) {
            super(view);
            this.tv_day = (TextView) view.findViewById(R.id.tv_day);
            this.tv_time = (TextView) view.findViewById(R.id.tv_time);
            this.tv_money = (TextView) view.findViewById(R.id.tv_money);
            this.tv_result = (TextView) view.findViewById(R.id.tv_result);
        }
    }

    public static class HeaderViewHolder extends ViewHolder {
        public TextView tv_tips_cumulative;
        public TextView tv_tips_record;
        public TextView tv_total;

        public HeaderViewHolder(View view) {
            super(view);
            this.tv_tips_cumulative = (TextView) view.findViewById(R.id.tv_tips_cumulative);
            this.tv_tips_record = (TextView) view.findViewById(R.id.tv_tips_record);
            this.tv_total = (TextView) view.findViewById(R.id.tv_total);
        }
    }

    public WithdrawRecordAdapter(ArrayList<WithRecordData> arrayList, String str, int i, Context context) {
        this.a = i;
        this.b = str;
        this.c = arrayList;
        this.d = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new HeaderViewHolder(LayoutInflater.from(this.d).inflate(R.layout.pay_withdrawr_head_item, viewGroup, false));
        }
        if (i == 1) {
            return new ContentViewHolder(LayoutInflater.from(this.d).inflate(R.layout.pay_withdraw_record_item, viewGroup, false));
        }
        throw new RuntimeException("there is no type that matches the type: " + i);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
            headerViewHolder.tv_tips_cumulative.setText(this.a == 0 ? R.string.pay_withdraw_record_total : R.string.pay_exchange_record_total);
            headerViewHolder.tv_tips_record.setText(this.a == 0 ? R.string.pay_withdraw_record : R.string.pay_exchange_record);
            headerViewHolder.tv_total.setText(this.b + "元");
        } else if (viewHolder instanceof ContentViewHolder) {
            ContentViewHolder contentViewHolder = (ContentViewHolder) viewHolder;
            if (i - 1 >= 0 && i - 1 < this.c.size()) {
                WithRecordData withRecordData = (WithRecordData) this.c.get(i - 1);
                if (a(withRecordData.withdraw_time)) {
                    contentViewHolder.tv_day.setText("今天");
                    contentViewHolder.tv_time.setText(e(withRecordData.withdraw_time));
                } else if (b(withRecordData.withdraw_time)) {
                    contentViewHolder.tv_day.setText("昨天");
                    contentViewHolder.tv_time.setText(e(withRecordData.withdraw_time));
                } else {
                    contentViewHolder.tv_day.setText(d(withRecordData.withdraw_time));
                    contentViewHolder.tv_time.setText(e(withRecordData.withdraw_time));
                }
                contentViewHolder.tv_money.setText(withRecordData.money + "元");
                if (withRecordData.status == 0) {
                    contentViewHolder.tv_result.setTextColor(this.d.getResources().getColor(R.color.pay_withdraw_pending));
                    contentViewHolder.tv_result.setText(withRecordData.result);
                } else if (withRecordData.status == 1) {
                    contentViewHolder.tv_result.setTextColor(this.d.getResources().getColor(R.color.pay_withdraw_pending));
                    contentViewHolder.tv_result.setText(withRecordData.result);
                } else if (withRecordData.status == 2) {
                    contentViewHolder.tv_result.setTextColor(this.d.getResources().getColor(R.color.pay_withdraw_fail));
                    contentViewHolder.tv_result.setText(this.d.getString(R.string.pay_withdraw_record_fail));
                } else {
                    contentViewHolder.tv_result.setTextColor(this.d.getResources().getColor(R.color.pay_withdraw_success));
                    contentViewHolder.tv_result.setText(withRecordData.result);
                }
            }
        }
    }

    public int getItemCount() {
        return this.c.size() + 1;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        return 1;
    }

    public void setTotal(String str) {
        this.b = str;
        notifyDataSetChanged();
    }

    private boolean a(String str) {
        Date c = c(str);
        Date date = new Date();
        if (c == null || !((SimpleDateFormat) e.get()).format(date).equals(((SimpleDateFormat) e.get()).format(c))) {
            return false;
        }
        return true;
    }

    private boolean b(String str) {
        Date c = c(str);
        Date date = new Date();
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.add(5, -1);
        Date time = gregorianCalendar.getTime();
        if (c == null || !((SimpleDateFormat) e.get()).format(time).equals(((SimpleDateFormat) e.get()).format(c))) {
            return false;
        }
        return true;
    }

    private Date c(String str) {
        try {
            return ((SimpleDateFormat) e.get()).parse(str);
        } catch (Exception e) {
            return null;
        }
    }

    private String d(String str) {
        if (str != null) {
            String[] split = str.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            if (split.length == 2) {
                return split[0].substring(5, 10);
            }
        }
        return null;
    }

    private String e(String str) {
        if (str != null) {
            String[] split = str.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            if (split.length == 2) {
                return split[1].substring(0, split[1].length() - 3);
            }
        }
        return null;
    }
}
