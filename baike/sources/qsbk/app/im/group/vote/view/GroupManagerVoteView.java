package qsbk.app.im.group.vote.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.sdk.cons.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.group.vote.adapter.GroupManagerCandidateVotingAdapter;
import qsbk.app.im.group.vote.bean.GroupManagerCandidate;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.widget.CustomGridView;

public class GroupManagerVoteView extends RelativeLayout {
    private Context a;
    private CustomGridView b;
    private TextView c;
    private GroupManagerCandidateVotingAdapter d;
    private ArrayList<GroupManagerCandidate> e = new ArrayList();
    private int f;
    private int g = -1;
    private boolean h;

    public GroupManagerVoteView(Context context, int i) {
        super(context);
        this.f = i;
        this.a = context;
    }

    public GroupManagerVoteView(Context context, int i, ArrayList<GroupManagerCandidate> arrayList) {
        super(context);
        this.f = i;
        this.a = context;
        this.e = arrayList;
        a();
        b();
        c();
    }

    public GroupManagerVoteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        a();
        b();
        c();
    }

    public GroupManagerVoteView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = context;
        a();
        b();
        c();
    }

    private void a() {
        a(LayoutInflater.from(this.a).inflate(R.layout.layout_group_manager_vote, this));
    }

    private void a(View view) {
        this.b = (CustomGridView) view.findViewById(R.id.gv_manager_candidate);
        this.c = (TextView) view.findViewById(R.id.tv_vote_btn);
    }

    private void b() {
        this.d = new GroupManagerCandidateVotingAdapter(this.a);
        this.d.setGMCandidatesData(this.e);
        this.b.setAdapter(this.d);
    }

    public void setData(boolean z, int i, ArrayList<GroupManagerCandidate> arrayList) {
        this.h = z;
        this.g = i;
        this.e = arrayList;
        this.d.setGMCandidatesData(this.e);
        int size = arrayList.size();
        int i2 = 0;
        int i3 = -1;
        while (i2 < size) {
            int i4;
            if (i == ((GroupManagerCandidate) arrayList.get(i2)).uid) {
                i4 = i2;
            } else {
                i4 = i3;
            }
            i2++;
            i3 = i4;
        }
        this.d.setItemSelection(i3);
        this.d.notifyDataSetChanged();
        if (z) {
            setVoteButtonStyle(0);
        } else {
            setVoteButtonStyle(-1);
        }
    }

    private void c() {
        this.b.setOnItemClickListener(new a(this));
        this.c.setOnClickListener(new b(this));
    }

    public void setVoteButtonStyle(int i) {
        switch (i) {
            case -1:
                this.c.setText(this.g == 0 ? "您是参选人员不能投票" : "已投");
                this.c.setEnabled(false);
                return;
            case 0:
                this.c.setText("点击头像选择候选人");
                this.c.setEnabled(false);
                return;
            case 1:
                this.c.setText("我要投TA");
                this.c.setEnabled(true);
                return;
            case 2:
                this.c.setText("已投");
                this.c.setEnabled(false);
                return;
            default:
                return;
        }
    }

    private void d() {
        if (!this.h) {
            return;
        }
        if (this.g == -1) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "请选择要投票的人员", Integer.valueOf(0)).show();
            return;
        }
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("处理中...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(this.f));
        hashMap.put("iid", String.valueOf(this.g));
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.URL_GROUP_MANAGER_VOTE, new Object[]{String.valueOf(this.f)}), new c(this, progressDialog));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }
}
