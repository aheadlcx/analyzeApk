package qsbk.app.im.group.vote.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import qsbk.app.im.group.vote.bean.GroupManagerCandidate;
import qsbk.app.im.group.vote.view.GroupManagerCandidateProfileView;
import qsbk.app.im.group.vote.view.GroupManagerVoteView;
import qsbk.app.utils.DebugUtil;

public class GroupManagerCandidateProfileAdapter extends PagerAdapter {
    private static final String a = GroupManagerCandidateProfileAdapter.class.getSimpleName();
    private ArrayList<GroupManagerCandidate> b = new ArrayList();
    private ArrayList<GroupManagerCandidateProfileView> c = new ArrayList();
    private GroupManagerVoteView d;
    private Context e;
    private int f;
    private boolean g;
    private int h;

    public GroupManagerCandidateProfileAdapter(Context context, int i, ArrayList<GroupManagerCandidate> arrayList) {
        this.e = context;
        this.b = arrayList;
        this.f = i;
    }

    public void setCanVote(boolean z, int i) {
        this.g = z;
        this.h = i;
    }

    public void setGMCandidates(ArrayList<GroupManagerCandidate> arrayList) {
        this.b = arrayList;
    }

    public void appendGMCandidates(GroupManagerCandidate groupManagerCandidate) {
        this.b.add(groupManagerCandidate);
    }

    public int getCount() {
        return this.b.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
        if (i < this.b.size() - 1) {
            this.c.add((GroupManagerCandidateProfileView) obj);
        }
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view;
        DebugUtil.debug(a, "getView position=" + i + ",size=" + this.b.size());
        if (i < this.b.size() - 1) {
            if (this.c.size() > 0) {
                view = (GroupManagerCandidateProfileView) this.c.remove(0);
            } else {
                view = new GroupManagerCandidateProfileView(this.e, null);
            }
            view.setView((GroupManagerCandidate) this.b.get(i));
        } else {
            if (this.d == null) {
                this.d = new GroupManagerVoteView(this.e, this.f, this.b);
                this.d.setData(this.g, this.h, this.b);
            }
            view = this.d;
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        viewGroup.addView(view);
        return view;
    }
}
