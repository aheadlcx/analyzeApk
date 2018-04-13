package qsbk.app.adapter;

import android.app.Activity;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.UIHelper;

public class MemberAdapter extends BaseImageAdapter {
    boolean a;
    private final int b;
    private boolean c = true;
    private OnActionListener e;
    private int f;
    private String[] g;
    private int h = -1;
    private boolean i;

    public interface OnActionListener {
        void onAction(int i);
    }

    public static class OtherItem {
        public String msg;
        public int type;
    }

    private class a {
        int a;
        ImageView b;
        TextView c;
        View d;
        ImageView e;
        TextView f;
        View g;
        View h;
        View i;
        TextView j;
        final /* synthetic */ MemberAdapter k;

        private a(MemberAdapter memberAdapter) {
            this.k = memberAdapter;
        }
    }

    public MemberAdapter(ArrayList<Object> arrayList, Activity activity, String[] strArr, OnActionListener onActionListener, boolean z) {
        super(arrayList, activity);
        this.e = onActionListener;
        this.g = strArr;
        this.a = z;
        this.f = onActionListener == null ? Integer.MAX_VALUE : 0;
        this.b = UIHelper.dip2px(activity, 3.0f);
    }

    public void setSearchModeEnable(boolean z) {
        this.i = z;
        notifyDataSetChanged();
    }

    public int getCount() {
        if (this.a && this.c && !this.i) {
            return super.getCount() + 1;
        }
        return super.getCount();
    }

    public void setAtAllCount(int i) {
        this.h = i;
    }

    public void setAdmin(boolean z) {
        this.a = z;
    }

    public void setOnActionListener(OnActionListener onActionListener) {
        this.e = onActionListener;
        this.f = onActionListener == null ? Integer.MAX_VALUE : 0;
    }

    public void setAtType(boolean z) {
        this.c = z;
    }

    public void setMinShowActionPos(int i) {
        this.f = i;
    }

    public int getViewTypeCount() {
        return 4;
    }

    public int getItemViewType(int i) {
        Object item = getItem(i);
        if (item instanceof BaseUserInfo) {
            return 0;
        }
        if (item instanceof OtherItem) {
            return ((OtherItem) item).type;
        }
        if (item.equals(Integer.valueOf(3))) {
            return 3;
        }
        return 2;
    }

    public Object getItem(int i) {
        if (this.a && this.c && !this.i && i == 0) {
            return Integer.valueOf(3);
        }
        if (this.a && this.c && !this.i) {
            i--;
        }
        if (i >= this.m.size()) {
            return Integer.valueOf(3);
        }
        return super.getItem(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        switch (getItemViewType(i)) {
            case 0:
                return getUserView(i, view, viewGroup);
            case 1:
                return getLabelView(i, view, viewGroup);
            case 3:
                return a(i, view, viewGroup);
            default:
                return getNoneView(i, view, viewGroup);
        }
    }

    private View a(int i, View view, ViewGroup viewGroup) {
        boolean z;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_at_all, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.desc);
        if (this.h != -1) {
            CharSequence spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append("剩余");
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append(this.h + "");
            int length2 = spannableStringBuilder.length();
            spannableStringBuilder.append("次");
            spannableStringBuilder.setSpan(new ForegroundColorSpan(this.k.getResources().getColor(R.color.yellow)), length, length2, 33);
            textView.setText(spannableStringBuilder);
        }
        if (this.h > 0) {
            z = true;
        } else {
            z = false;
        }
        view.setEnabled(z);
        return view;
    }

    public View getNoneView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_member_none, viewGroup, false);
            TextView textView2 = (TextView) view.findViewById(R.id.no_member_tips);
            view.setTag(textView2);
            textView = textView2;
        } else {
            textView = (TextView) view.getTag();
        }
        Object item = getItem(i);
        if (item == null || !(item instanceof OtherItem)) {
            textView.setText("");
        } else {
            textView.setText(((OtherItem) item).msg);
        }
        return view;
    }

    public View getLabelView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if (view == null) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_member_label, viewGroup, false);
            view = inflate;
            textView = (TextView) inflate;
        } else {
            textView = (TextView) view;
        }
        textView.setText(((OtherItem) getItem(i)).msg);
        return view;
    }

    public View getUserView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_member, viewGroup, false);
            aVar = new a();
            aVar.b = (ImageView) view.findViewById(R.id.avatar);
            aVar.c = (TextView) view.findViewById(R.id.name);
            aVar.d = view.findViewById(R.id.gender_age);
            aVar.e = (ImageView) view.findViewById(R.id.gender);
            aVar.f = (TextView) view.findViewById(R.id.age);
            aVar.j = (TextView) view.findViewById(R.id.role);
            aVar.i = view.findViewById(R.id.divider);
            aVar.g = view.findViewById(R.id.me);
            aVar.h = view.findViewById(R.id.action);
            if (this.e != null) {
                aVar.h.setOnClickListener(new ck(this, aVar));
            } else {
                aVar.h.setVisibility(4);
            }
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a = i;
        if (i < this.f) {
            aVar.h.setVisibility(4);
        } else {
            aVar.h.setVisibility(0);
        }
        BaseUserInfo baseUserInfo = (BaseUserInfo) getItem(i);
        Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(baseUserInfo.userIcon, baseUserInfo.userId);
        if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
            aVar.b.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            b(aVar.b, absoluteUrlOfMediumUserIcon);
        }
        CharSequence remark = RemarkManager.getRemark(baseUserInfo.userId);
        if (TextUtils.isEmpty(remark)) {
            aVar.c.setText(baseUserInfo.userName);
        } else {
            aVar.c.setText(remark);
        }
        aVar.i.setBackgroundColor(UIHelper.isNightTheme() ? -15329253 : -1184275);
        aVar.f.setText(String.valueOf(baseUserInfo.age));
        if (UIHelper.isNightTheme()) {
            if ("F".equalsIgnoreCase(baseUserInfo.gender)) {
                aVar.e.setImageResource(R.drawable.pinfo_female_dark);
                aVar.f.setTextColor(view.getResources().getColor(R.color.age_female));
                aVar.j.setBackgroundResource(R.drawable.pinfo_female_bg_night);
            } else {
                aVar.e.setImageResource(R.drawable.pinfo_male_dark);
                aVar.f.setTextColor(view.getResources().getColor(R.color.age_male));
                aVar.j.setBackgroundResource(R.drawable.pinfo_man_bg_night);
            }
            aVar.j.setPadding(this.b, 0, this.b, 0);
            aVar.j.setTextColor(-5066062);
        } else {
            if ("F".equalsIgnoreCase(baseUserInfo.gender)) {
                aVar.d.setBackgroundResource(R.drawable.pinfo_female_bg);
                aVar.e.setImageResource(R.drawable.pinfo_female);
                aVar.j.setBackgroundResource(R.drawable.pinfo_female_bg);
            } else {
                aVar.d.setBackgroundResource(R.drawable.pinfo_man_bg);
                aVar.e.setImageResource(R.drawable.pinfo_male);
                aVar.j.setBackgroundResource(R.drawable.pinfo_man_bg);
            }
            aVar.j.setPadding(this.b, 0, this.b, 0);
            aVar.f.setTextColor(-1);
            aVar.j.setTextColor(-1);
        }
        if (QsbkApp.currentUser.userId.equals(baseUserInfo.userId)) {
            aVar.g.setVisibility(0);
        } else {
            aVar.g.setVisibility(4);
        }
        CharSequence roleTitle = baseUserInfo.getRoleTitle(this.g);
        if (roleTitle != null) {
            aVar.j.setVisibility(0);
            aVar.j.setText(roleTitle);
        } else {
            aVar.j.setVisibility(8);
        }
        return view;
    }
}
