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
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.im.group.vote.bean.GroupManagerCandidate;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.UIHelper;

public class GroupManagerCandidateVotingAdapter extends BaseAdapter {
    private static final String a = GroupManagerCandidateVotingAdapter.class.getSimpleName();
    private Context b;
    private ArrayList<GroupManagerCandidate> c = new ArrayList();
    private int d = -1;

    class a {
        ImageView a;
        ImageView b;
        ImageView c;
        TextView d;
        final /* synthetic */ GroupManagerCandidateVotingAdapter e;

        a(GroupManagerCandidateVotingAdapter groupManagerCandidateVotingAdapter) {
            this.e = groupManagerCandidateVotingAdapter;
        }
    }

    public GroupManagerCandidateVotingAdapter() {
        a();
    }

    public GroupManagerCandidateVotingAdapter(Context context) {
        this.b = context;
        a();
    }

    public GroupManagerCandidateVotingAdapter(Context context, ArrayList<GroupManagerCandidate> arrayList) {
        this.b = context;
        this.c = arrayList;
        a();
    }

    public void setGMCandidatesData(ArrayList<GroupManagerCandidate> arrayList) {
        this.c = arrayList;
    }

    private void a() {
    }

    public void setItemSelection(int i) {
        DebugUtil.debug(a, "setItemSelection, mSelectedItemPosition=" + i);
        this.d = i;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.c.size() - 1;
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
            view = LayoutInflater.from(this.b).inflate(R.layout.layout_group_manager_vote_item, null, false);
            aVar = new a(this);
            aVar.a = (ImageView) view.findViewById(R.id.iv_avatar_bg);
            aVar.b = (ImageView) view.findViewById(R.id.avatar);
            aVar.c = (ImageView) view.findViewById(R.id.iv_vote_checked);
            aVar.d = (TextView) view.findViewById(R.id.tv_candidate_name);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        GroupManagerCandidate groupManagerCandidate = (GroupManagerCandidate) this.c.get(i);
        Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(groupManagerCandidate.avatar, String.valueOf(groupManagerCandidate.uid));
        if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
            aVar.b.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            FrescoImageloader.displayAvatar(aVar.b, absoluteUrlOfMediumUserIcon);
        }
        aVar.d.setText(groupManagerCandidate.nickName);
        if (this.d == i) {
            aVar.a.setVisibility(0);
            aVar.c.setVisibility(0);
            aVar.c.setImageResource(R.drawable.vote_checked);
        } else {
            aVar.a.setVisibility(4);
            aVar.c.setVisibility(4);
        }
        return view;
    }
}
