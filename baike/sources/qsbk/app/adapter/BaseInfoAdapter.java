package qsbk.app.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import qsbk.app.R;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.AstrologyUtils;
import qsbk.app.utils.UIHelper;

@Deprecated
public class BaseInfoAdapter extends DefaultAdapter<UserInfo> {
    private Drawable a;
    private Drawable b;

    public BaseInfoAdapter(ArrayList<UserInfo> arrayList, Activity activity) {
        super(arrayList, activity);
        if (this.m == null || this.m.size() != 1) {
            throw new IllegalArgumentException("Data Source can not be null, and must have only one item.");
        }
        this.a = this.k.getResources().getDrawable(UIHelper.getMale());
        this.b = this.k.getResources().getDrawable(UIHelper.getFemale());
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.n.inflate(R.layout.one_profile_base_info, null);
        }
        a(BaseInfoAdapter$ViewHolder.a(view), (UserInfo) this.m.get(0));
        return view;
    }

    private void a(BaseInfoAdapter$ViewHolder baseInfoAdapter$ViewHolder, UserInfo userInfo) {
        c(baseInfoAdapter$ViewHolder.a, userInfo);
        b(baseInfoAdapter$ViewHolder.b, userInfo);
        a(baseInfoAdapter$ViewHolder.g, userInfo);
        a(baseInfoAdapter$ViewHolder.c, userInfo.haunt);
    }

    private void a(TextView textView, UserInfo userInfo) {
        if (userInfo.regTime > 0) {
            textView.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(userInfo.regTime * 1000)));
            return;
        }
        textView.setText("");
    }

    private void b(TextView textView, UserInfo userInfo) {
        if (!TextUtils.isEmpty(userInfo.astrology)) {
            textView.setText(userInfo.astrology);
        } else if (userInfo.birthday > 0) {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(userInfo.birthday * 1000);
            textView.setText(AstrologyUtils.date2Astrology(instance));
        } else {
            textView.setText("");
        }
    }

    private void c(TextView textView, UserInfo userInfo) {
        int i = 99;
        int i2 = 12;
        textView.setVisibility(0);
        int paddingBottom = textView.getPaddingBottom();
        int paddingTop = textView.getPaddingTop();
        int paddingRight = textView.getPaddingRight();
        int paddingLeft = textView.getPaddingLeft();
        if ("F".equals(userInfo.gender)) {
            textView.setCompoundDrawablesWithIntrinsicBounds(this.b, null, null, null);
            textView.setBackgroundResource(UIHelper.getFemaleBackground());
            textView.setTextColor(UIHelper.getSexTexColor(this.k.getResources(), userInfo.gender));
        } else if ("M".equals(userInfo.gender)) {
            textView.setCompoundDrawablesWithIntrinsicBounds(this.a, null, null, null);
            textView.setBackgroundResource(UIHelper.getMaleBackground());
            textView.setTextColor(UIHelper.getSexTexColor(this.k.getResources(), userInfo.gender));
        } else {
            textView.setVisibility(8);
        }
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        if (userInfo.age > 0) {
            if (userInfo.age <= 99) {
                i = userInfo.age;
            }
            if (i >= 12) {
                i2 = i;
            }
            textView.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i2);
        } else if (userInfo.birthday > 0) {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(userInfo.birthday * 1000);
            textView.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + String.valueOf(AstrologyUtils.getAge(instance)));
        }
    }

    private void a(TextView textView, String str) {
        if (str == null || str.trim().length() == 0) {
            textView.setText("");
        } else {
            textView.setText(str);
        }
    }
}
