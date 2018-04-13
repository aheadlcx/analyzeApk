package cn.xiaochuankeji.tieba.ui.voice;

import android.app.Activity;
import android.arch.lifecycle.q;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.recommend.b;
import cn.xiaochuankeji.tieba.ui.voice.adapter.VoiceTopicAdapter;
import cn.xiaochuankeji.tieba.ui.voice.model.VoiceTopicModel;
import com.alibaba.fastjson.JSON;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class VoiceSelectTopicActivity extends a implements b {
    private VoiceTopicModel a;
    private VoiceTopicAdapter b;
    @BindView
    View back;
    @BindView
    RecyclerView recycler_view;
    @BindView
    SmartRefreshLayout refreshLayout;

    public static void a(Activity activity, int i) {
        activity.startActivityForResult(new Intent(activity, VoiceSelectTopicActivity.class), i);
    }

    protected int a() {
        return R.layout.voice_select_topic;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.recycler_view.setLayoutManager(linearLayoutManager);
        this.b = new VoiceTopicAdapter(this);
        this.recycler_view.setAdapter(this.b);
        this.a = (VoiceTopicModel) q.a((FragmentActivity) this).a(VoiceTopicModel.class);
        this.a.a(this.b);
        this.a.a((b) this);
        this.refreshLayout.b(false);
        this.refreshLayout.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ VoiceSelectTopicActivity a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.a.c();
            }
        });
        this.a.b();
    }

    public void a(boolean z, String str, int i, boolean z2) {
        if (!z) {
            g.a(str);
        }
        if (z2) {
            this.refreshLayout.a(true);
        } else {
            this.refreshLayout.a(false);
        }
    }

    public void a(boolean z, String str, boolean z2) {
        if (this.refreshLayout != null) {
            if (z2) {
                this.refreshLayout.n();
            } else {
                this.refreshLayout.o();
            }
        }
        if (!z) {
            g.a(str);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void selectTopic(cn.xiaochuankeji.tieba.ui.voice.a.a aVar) {
        if (aVar != null && aVar.a != null) {
            Intent intent = new Intent();
            intent.putExtra("topic", JSON.toJSONString(aVar.a));
            setResult(-1, intent);
            finish();
        }
    }

    @OnClick
    public void onClick(View view) {
        finish();
    }
}
