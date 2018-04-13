package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;

public class TopicBlackListActivity extends BaseActionBarActivity implements PtrListener {
    private int a = 1;
    private PtrLayout b;
    private ListView c;
    private a d;
    private String e;
    private ArrayList<Object> f = new ArrayList();
    private TipsHelper g;

    private class a extends BaseImageAdapter {
        final /* synthetic */ TopicBlackListActivity a;

        public a(TopicBlackListActivity topicBlackListActivity, ArrayList<Object> arrayList, Activity activity) {
            this.a = topicBlackListActivity;
            super(arrayList, activity);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null) {
                view = this.a.getLayoutInflater().inflate(R.layout.item_topic_black_list, viewGroup, false);
                bVar = new b(this.a);
                bVar.mAvatarIV = (ImageView) view.findViewById(R.id.avatar);
                bVar.mNameTV = (TextView) view.findViewById(R.id.name);
                bVar.mGenderAgeLL = (LinearLayout) view.findViewById(R.id.gender_age);
                bVar.mGenderIV = (ImageView) view.findViewById(R.id.gender);
                bVar.mAgeTV = (TextView) view.findViewById(R.id.age);
                bVar.mRemove = view.findViewById(R.id.remove);
                bVar.mRemove.setOnClickListener(new adw(this, bVar));
                OnClickListener userClickDelegate = new UserClickDelegate(((BaseUserInfo) getItem(bVar.pos)).userId, new adx(this, bVar));
                bVar.mAvatarIV.setOnClickListener(userClickDelegate);
                bVar.mNameTV.setOnClickListener(userClickDelegate);
                view.setTag(bVar);
            } else {
                bVar = (b) view.getTag();
            }
            bVar.pos = i;
            BaseUserInfo baseUserInfo = (BaseUserInfo) getItem(i);
            Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(baseUserInfo.userIcon, baseUserInfo.userId);
            if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                bVar.mAvatarIV.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                FrescoImageloader.displayAvatar(bVar.mAvatarIV, absoluteUrlOfMediumUserIcon);
            }
            bVar.mNameTV.setText(baseUserInfo.userName);
            if (!UIHelper.isNightTheme()) {
                if ("F".equalsIgnoreCase(baseUserInfo.gender)) {
                    bVar.mGenderAgeLL.setBackgroundResource(R.drawable.pinfo_female_bg);
                    bVar.mGenderIV.setImageResource(R.drawable.pinfo_female);
                } else {
                    bVar.mGenderAgeLL.setBackgroundResource(R.drawable.pinfo_man_bg);
                    bVar.mGenderIV.setImageResource(R.drawable.pinfo_male);
                }
                bVar.mAgeTV.setTextColor(-1);
            } else if ("F".equalsIgnoreCase(baseUserInfo.gender)) {
                bVar.mGenderIV.setImageResource(R.drawable.pinfo_female_dark);
                bVar.mAgeTV.setTextColor(this.k.getResources().getColor(R.color.age_female));
            } else {
                bVar.mGenderIV.setImageResource(R.drawable.pinfo_male_dark);
                bVar.mAgeTV.setTextColor(this.k.getResources().getColor(R.color.age_male));
            }
            bVar.mAgeTV.setText(String.valueOf(baseUserInfo.age));
            return view;
        }
    }

    class b {
        final /* synthetic */ TopicBlackListActivity a;
        public TextView mAgeTV;
        public ImageView mAvatarIV;
        public LinearLayout mGenderAgeLL;
        public ImageView mGenderIV;
        public TextView mNameTV;
        public View mRemove;
        public int pos;

        b(TopicBlackListActivity topicBlackListActivity) {
            this.a = topicBlackListActivity;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context, CircleTopic circleTopic) {
        Intent intent = new Intent(context, TopicBlackListActivity.class);
        intent.putExtra("topic_id", circleTopic.id);
        context.startActivity(intent);
    }

    protected boolean d() {
        return true;
    }

    protected String f() {
        return "屏蔽管理";
    }

    protected int a() {
        return R.layout.layout_ptr_listview;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.e = getIntent().getStringExtra("topic_id");
        this.b = (PtrLayout) findViewById(R.id.ptr);
        this.c = (ListView) findViewById(R.id.listview);
        this.b.setRefreshEnable(true);
        this.b.setLoadMoreEnable(false);
        this.d = new a(this, this.f, this);
        this.c.setAdapter(this.d);
        this.b.setPtrListener(this);
        this.g = new TipsHelper(findViewById(R.id.tips));
        this.b.refresh();
    }

    private void a(int i) {
        this.g.hide();
        new SimpleHttpTask(String.format(Constants.CIRCLE_TOPIC_BLACK_LIST, new Object[]{this.e, Integer.valueOf(i)}), new adn(this, i)).execute();
    }

    public void onRefresh() {
        showLoading();
        this.a = 1;
        a(this.a);
    }

    public void onLoadMore() {
        a(this.a + 1);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topic_black_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.remove_all).setVisible(this.f.size() != 0);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.remove_all:
                g();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void g() {
        new ado(this, this).show();
    }

    private void i() {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.CIRCLE_UNBLOCK_USER_ALL, new adr(this, this, "处理中"));
        Map hashMap = new HashMap();
        hashMap.put("topic_id", this.e);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    private void a(BaseUserInfo baseUserInfo) {
        new ads(this, this, baseUserInfo).show();
    }

    private void b(BaseUserInfo baseUserInfo) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.CIRCLE_UNBLOCK_USER, new adv(this, this, "处理中", baseUserInfo));
        Map hashMap = new HashMap();
        hashMap.put("topic_id", this.e);
        hashMap.put("block_user_id", baseUserInfo.userId);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }
}
