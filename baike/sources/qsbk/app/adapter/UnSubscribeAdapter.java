package qsbk.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.HttpTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.UIHelper;

public class UnSubscribeAdapter extends BaseAdapter {
    private Context a;
    private List<BaseUserInfo> b = new ArrayList();

    class a {
        final /* synthetic */ UnSubscribeAdapter a;
        public TextView mAgeTV;
        public ImageView mAvatarIV;
        public LinearLayout mGenderAgeLL;
        public ImageView mGenderIV;
        public TextView mNameTV;
        public TextView mSubscribeIV;

        a(UnSubscribeAdapter unSubscribeAdapter) {
            this.a = unSubscribeAdapter;
        }
    }

    public UnSubscribeAdapter(Context context, List<BaseUserInfo> list) {
        this.a = context;
        this.b = list;
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
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.a).inflate(R.layout.unsubscribe_item, null);
            aVar.mAvatarIV = (ImageView) view.findViewById(R.id.avatar);
            aVar.mNameTV = (TextView) view.findViewById(R.id.name);
            aVar.mGenderAgeLL = (LinearLayout) view.findViewById(R.id.gender_age);
            aVar.mGenderIV = (ImageView) view.findViewById(R.id.gender);
            aVar.mAgeTV = (TextView) view.findViewById(R.id.age);
            aVar.mSubscribeIV = (TextView) view.findViewById(R.id.subscribe);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        BaseUserInfo baseUserInfo = (BaseUserInfo) this.b.get(i);
        FrescoImageloader.displayAvatar(aVar.mAvatarIV, QsbkApp.absoluteUrlOfMediumUserIcon(baseUserInfo.userIcon, baseUserInfo.userId));
        aVar.mNameTV.setText(baseUserInfo.userName);
        if (!UIHelper.isNightTheme()) {
            if ("F".equalsIgnoreCase(baseUserInfo.gender)) {
                aVar.mGenderAgeLL.setBackgroundResource(R.drawable.pinfo_female_bg);
                aVar.mGenderIV.setImageResource(R.drawable.pinfo_female);
            } else {
                aVar.mGenderAgeLL.setBackgroundResource(R.drawable.pinfo_man_bg);
                aVar.mGenderIV.setImageResource(R.drawable.pinfo_male);
            }
            aVar.mAgeTV.setTextColor(-1);
        } else if ("F".equalsIgnoreCase(baseUserInfo.gender)) {
            aVar.mGenderIV.setImageResource(R.drawable.pinfo_female_dark);
            aVar.mAgeTV.setTextColor(this.a.getResources().getColor(R.color.age_female));
        } else {
            aVar.mGenderIV.setImageResource(R.drawable.pinfo_male_dark);
            aVar.mAgeTV.setTextColor(this.a.getResources().getColor(R.color.age_male));
        }
        aVar.mAgeTV.setText(baseUserInfo.age + "");
        aVar.mSubscribeIV.setOnClickListener(new dq(this, baseUserInfo, i));
        return view;
    }

    private void a(String str, String str2, Map<String, Object> map, int i) {
        HttpTask httpTask = new HttpTask(str, str2, new dr(this, i));
        if (map != null) {
            httpTask.setMapParams(map);
        }
        httpTask.execute(new Void[0]);
    }
}
