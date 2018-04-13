package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.HttpTask;
import qsbk.app.im.TimeUtils;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.live.ui.helper.LevelHelper;
import qsbk.app.model.LiveRoom;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;

public class LiveFollowActivity extends BaseActionBarActivity implements PtrListener {
    BaseImageAdapter a;
    private PtrLayout b;
    private ListView c;
    private TipsHelper d;
    private boolean e;
    private HttpTask f;
    private int g = 1;
    private ArrayList<LiveRoom> h = new ArrayList();

    private class a extends BaseImageAdapter {
        final /* synthetic */ LiveFollowActivity a;

        public a(LiveFollowActivity liveFollowActivity, ArrayList<?> arrayList, Activity activity) {
            this.a = liveFollowActivity;
            super(arrayList, activity);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            int i2 = 4;
            int i3 = 0;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_live_follow_item, null);
                b bVar2 = new b(this.a, view);
                view.setTag(bVar2);
                bVar = bVar2;
            } else {
                bVar = (b) view.getTag();
            }
            Object item = getItem(i);
            if (item != null && (item instanceof LiveRoom)) {
                LiveRoom liveRoom = (LiveRoom) item;
                if (liveRoom != null) {
                    if (liveRoom.author != null) {
                        bVar.a.setText(liveRoom.author.name);
                        bVar.b.setText(liveRoom.author.intro);
                        LevelHelper.setLevelText(bVar.f, liveRoom.author.level);
                        FrescoImageloader.displayAvatar(bVar.e, liveRoom.author.headurl);
                        bVar.g.setText(liveRoom.author.badge);
                    }
                    bVar.d.setVisibility(liveRoom.isLiveBegin() ? 0 : 4);
                    TextView textView = bVar.c;
                    if (!liveRoom.isLiveBegin()) {
                        i2 = 0;
                    }
                    textView.setVisibility(i2);
                    bVar.c.setText(TimeUtils.getLastLiveStr(liveRoom.update_at * 1000));
                    TextView textView2 = bVar.g;
                    if (liveRoom.author == null || TextUtils.isEmpty(liveRoom.author.badge)) {
                        i3 = 8;
                    }
                    textView2.setVisibility(i3);
                }
                view.setOnClickListener(new qz(this, liveRoom, view));
            }
            return view;
        }
    }

    class b {
        TextView a;
        TextView b;
        TextView c;
        TextView d;
        ImageView e;
        TextView f;
        TextView g;
        final /* synthetic */ LiveFollowActivity h;

        public b(LiveFollowActivity liveFollowActivity, View view) {
            this.h = liveFollowActivity;
            this.a = (TextView) view.findViewById(R.id.name);
            this.b = (TextView) view.findViewById(R.id.desc);
            this.c = (TextView) view.findViewById(R.id.end_time);
            this.d = (TextView) view.findViewById(R.id.begin);
            this.e = (ImageView) view.findViewById(R.id.avatar);
            this.f = (TextView) view.findViewById(R.id.level);
            this.g = (TextView) view.findViewById(R.id.family);
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, LiveFollowActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    protected String f() {
        return getString(R.string.followed_anchor);
    }

    protected int a() {
        return R.layout.activity_live_follow;
    }

    protected void a(Bundle bundle) {
        if (QsbkApp.currentUser == null) {
            finish();
            return;
        }
        setActionbarBackable();
        this.d = new TipsHelper(findViewById(R.id.tips));
        this.b = (PtrLayout) findViewById(R.id.ptr);
        this.b.setRefreshEnable(true);
        this.b.setLoadMoreEnable(true);
        this.b.setPtrListener(this);
        this.c = (ListView) findViewById(R.id.listview);
        this.b.autoRefresh();
        this.a = new a(this, this.h, this);
        this.c.setAdapter(this.a);
    }

    public void onRefresh() {
        this.g = 1;
        this.b.setLoadMoreEnable(true);
        g();
    }

    public void onLoadMore() {
        if (!this.e) {
            g();
        }
    }

    private synchronized void g() {
        if (!this.e) {
            this.e = true;
            this.f = new HttpTask(null, String.format(Constants.LIVE_FOLLOW_ALL, new Object[]{"20", this.g + ""}), new qy(this));
            this.f.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f != null) {
            this.f.cancel(true);
        }
    }
}
