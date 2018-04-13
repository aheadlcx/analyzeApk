package qsbk.app.im.group.vote.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.im.group.vote.bean.GroupManagerInfo;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.UIHelper;

public class GroupManagerHistoryAdapter extends BaseAdapter {
    private static final String a = GroupManagerHistoryAdapter.class.getSimpleName();
    private Context b;
    private ArrayList<GroupManagerInfo> c = new ArrayList();

    class a {
        ImageView a;
        TextView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        View g;
        final /* synthetic */ GroupManagerHistoryAdapter h;

        a(GroupManagerHistoryAdapter groupManagerHistoryAdapter) {
            this.h = groupManagerHistoryAdapter;
        }
    }

    public GroupManagerHistoryAdapter(Context context) {
        this.b = context;
        a();
    }

    public GroupManagerHistoryAdapter(Context context, ArrayList<GroupManagerInfo> arrayList) {
        this.b = context;
        this.c = arrayList;
        a();
    }

    private void a() {
    }

    public void setGMHistoryInfo(ArrayList<GroupManagerInfo> arrayList) {
        this.c = arrayList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.layout_group_manager_history_item, viewGroup, false);
            aVar = new a(this);
            aVar.a = (ImageView) view.findViewById(R.id.avatar);
            aVar.b = (TextView) view.findViewById(R.id.tv_candidate_name);
            aVar.c = (TextView) view.findViewById(R.id.tv_group_take_date);
            aVar.d = (TextView) view.findViewById(R.id.tv_group_take_mem_num);
            aVar.g = view.findViewById(R.id.ll_group_retire);
            aVar.e = (TextView) view.findViewById(R.id.tv_group_retire_date);
            aVar.f = (TextView) view.findViewById(R.id.tv_group_retire_mem_num);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        GroupManagerInfo groupManagerInfo = (GroupManagerInfo) this.c.get(i);
        if (TextUtils.isEmpty(groupManagerInfo.mGroupIconUrl)) {
            aVar.a.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            FrescoImageloader.displayAvatar(aVar.a, groupManagerInfo.mGroupIconUrl);
        }
        aVar.b.setText(groupManagerInfo.mGroupName);
        aVar.c.setText(groupManagerInfo.mGroupManagerTakeDate);
        aVar.d.setText(String.valueOf(groupManagerInfo.mGroupManagerTakeMemberNum));
        if (groupManagerInfo.mGroupManagerRetireDate != null) {
            aVar.g.setVisibility(0);
            aVar.e.setText(groupManagerInfo.mGroupManagerRetireDate);
            aVar.f.setText(String.valueOf(groupManagerInfo.mGroupManagerRetireMemberNum));
        } else {
            aVar.g.setVisibility(4);
        }
        return view;
    }
}
