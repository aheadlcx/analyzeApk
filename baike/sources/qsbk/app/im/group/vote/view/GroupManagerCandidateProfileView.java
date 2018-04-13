package qsbk.app.im.group.vote.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.im.group.vote.adapter.GroupManagerHistoryAdapter;
import qsbk.app.im.group.vote.bean.GroupManagerCandidate;
import qsbk.app.widget.card.GroupActiveLayout;

public class GroupManagerCandidateProfileView extends RelativeLayout {
    private TextView a;
    private TextView b;
    private TextView c;
    private GroupActiveLayout d;
    private TextView e;
    private ListView f;
    private GroupManagerHistoryAdapter g;
    private TextView h;

    public GroupManagerCandidateProfileView(Context context) {
        super(context);
    }

    public GroupManagerCandidateProfileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public GroupManagerCandidateProfileView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        float f = getResources().getDisplayMetrics().density;
        this.f = new ListView(getContext());
        this.f.setBackgroundResource(R.drawable.gm_vote_profile_bg);
        this.f.setCacheColorHint(0);
        this.f.setSelector(new ColorDrawable(0));
        this.f.setDividerHeight(0);
        int i = (int) (10.0f * f);
        this.f.setPadding(0, i, 0, i);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        int i2 = (int) (17.0f * f);
        layoutParams.leftMargin = i2;
        layoutParams.rightMargin = i2;
        layoutParams.topMargin = (int) (f * 10.0f);
        addView(this.f, layoutParams);
        this.h = new TextView(getContext());
        this.h.setBackgroundResource(R.drawable.last_group_manager_bg);
        this.h.setText("上届\n群大");
        this.h.setTextSize(1, 12.0f);
        this.h.setTextColor(-1);
        this.h.setGravity(17);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(10);
        layoutParams2.addRule(11);
        layoutParams2.rightMargin = (int) getResources().getDisplayMetrics().density;
        addView(this.h, layoutParams2);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_group_manager_candidate_profile_view, this.f, false);
        this.f.addHeaderView(inflate);
        a(inflate);
        b();
    }

    private void a(View view) {
        this.a = (TextView) view.findViewById(R.id.tv_content_election_manifesto);
        this.b = (TextView) view.findViewById(R.id.tv_age_qsbk_value);
        this.c = (TextView) view.findViewById(R.id.tv_age_group_value);
        this.d = (GroupActiveLayout) view.findViewById(R.id.active);
        this.e = (TextView) view.findViewById(R.id.owner_history_label);
    }

    private void b() {
        this.g = new GroupManagerHistoryAdapter(getContext());
        this.f.setAdapter(this.g);
    }

    public void setView(GroupManagerCandidate groupManagerCandidate) {
        int i = 1;
        this.g.setGMHistoryInfo(groupManagerCandidate.ownerHistory);
        this.g.notifyDataSetChanged();
        this.e.setText(groupManagerCandidate.ownerHistory.size() == 0 ? "无群大任职经历" : "群大任职经历");
        this.d.setMouthActives(groupManagerCandidate.monthActives);
        this.h.setVisibility(groupManagerCandidate.isOwner ? 0 : 4);
        this.a.setText(groupManagerCandidate.manifesto);
        if (groupManagerCandidate.qbAge >= 365) {
            int i2;
            TextView textView = this.b;
            StringBuilder stringBuilder = new StringBuilder();
            int i3 = groupManagerCandidate.qbAge / 365;
            if (groupManagerCandidate.qbAge % 365 > 200) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            textView.setText(stringBuilder.append(i2 + i3).append("年").toString());
        } else {
            this.b.setText((groupManagerCandidate.qbAge / 30 > 0 ? groupManagerCandidate.qbAge / 30 : 1) + "个月");
        }
        if (groupManagerCandidate.groupAge >= 365) {
            TextView textView2 = this.c;
            StringBuilder stringBuilder2 = new StringBuilder();
            int i4 = groupManagerCandidate.groupAge / 365;
            if (groupManagerCandidate.groupAge % 365 <= 200) {
                i = 0;
            }
            textView2.setText(stringBuilder2.append(i4 + i).append("年").toString());
            return;
        }
        textView2 = this.c;
        StringBuilder stringBuilder3 = new StringBuilder();
        if (groupManagerCandidate.groupAge / 30 > 0) {
            i = groupManagerCandidate.groupAge / 30;
        }
        textView2.setText(stringBuilder3.append(i).append("个月").toString());
    }
}
