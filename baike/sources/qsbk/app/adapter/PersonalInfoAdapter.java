package qsbk.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import java.util.ArrayList;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.NewFan;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.DebugUtil;
import qsbk.app.widget.PersonalInfoView;

public class PersonalInfoAdapter extends BaseAdapter {
    private static final String a = PersonalInfoAdapter.class.getSimpleName();
    private ArrayList<NewFan> b = new ArrayList();
    private int c = 0;
    private Context d;

    public PersonalInfoAdapter(Context context, ArrayList<NewFan> arrayList) {
        this.b = arrayList;
        this.d = context;
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View personalInfoView;
        DebugUtil.debug(a, "getView position:" + i);
        if (view == null) {
            personalInfoView = new PersonalInfoView(this.d, null);
            personalInfoView.setLayoutParams(new LayoutParams(QsbkApp.getInstance().getApplicationContext().getResources().getDimensionPixelSize(R.dimen.newfans_info_normal_width), -1));
        } else {
            personalInfoView = view;
        }
        ((PersonalInfoView) personalInfoView).setView((UserInfo) this.b.get(i), 2);
        return personalInfoView;
    }

    public void setSelection(int i) {
        DebugUtil.debug(a, "setSelection position:" + i);
        this.c = i;
    }
}
