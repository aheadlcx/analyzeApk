package cn.xiaochuankeji.tieba.ui.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.topic.BlockTopicList;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.ui.base.n;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.HashSet;

public class MyBlockTopicActivity extends n {
    private BlockTopicList g;
    private a h;
    private HashSet<Long> i = new HashSet();
    private QueryListView j;

    class a extends BaseAdapter {
        final /* synthetic */ MyBlockTopicActivity a;

        a(MyBlockTopicActivity myBlockTopicActivity) {
            this.a = myBlockTopicActivity;
        }

        public int getCount() {
            return this.a.g.itemCount();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = new e(this.a, this.a.i);
            } else {
                e eVar = (e) view;
            }
            view.setData((Topic) this.a.g.itemAt(i));
            return view;
        }
    }

    public static void a(Context context, int i) {
        Intent intent = new Intent(context, MyBlockTopicActivity.class);
        intent.putExtra("key_count", i);
        context.startActivity(intent);
    }

    protected void j() {
        this.j.h();
    }

    protected boolean a(Bundle bundle) {
        this.g = new BlockTopicList();
        this.h = new a(this);
        return true;
    }

    protected QueryListView k() {
        this.j = new QueryListView(this);
        this.j.m().setPadding(0, e.a(11.0f), 0, 0);
        this.j.m().setClipToPadding(false);
        return this.j;
    }

    protected void c() {
        super.c();
        this.j.a("暂时没有屏蔽的话题", (int) R.drawable.ic_topic_empty_post, EmptyPaddingStyle.GoldenSection);
    }

    protected String v() {
        return null;
    }

    protected void w() {
        this.f.setTitle("推荐中屏蔽的话题");
        this.j.a(this.g, this.h);
        this.j.f();
    }
}
