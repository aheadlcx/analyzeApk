package qsbk.app.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.adapter.CircleTopicWeeklyAdapter;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.CircleArticle;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.CircleArticleBus.OnArticleUpdateListener;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.ArticleMoreOperationbar;
import qsbk.app.widget.LoadingLayout;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TopicHeader;
import qsbk.app.widget.TopicScrollView;
import qsbk.app.widget.qiuyoucircle.NormalCell;

public class CircleTopicWeeklyFragment extends Fragment implements ShareUtils$OnCircleShareStartListener, OnArticleUpdateListener, PtrListener {
    PtrLayout a;
    ListView b;
    ArrayList<Object> c = new ArrayList();
    String d;
    LoadingLayout e;
    private BaseAdapter f;
    private SimpleHttpTask g;
    private int h;
    private boolean i;
    private View j;
    private TopicHeader k;
    private TopicScrollView l;
    private View m;
    private int n;
    private View o;
    private TextView p;
    private View q;
    private TextView r;
    private TextView s;
    private ImageView t;
    private TextView u;

    public static CircleTopicWeeklyFragment newInstance(String str) {
        CircleTopicWeeklyFragment circleTopicWeeklyFragment = new CircleTopicWeeklyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", str);
        circleTopicWeeklyFragment.setArguments(bundle);
        return circleTopicWeeklyFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.d = getArguments().getString("id");
        }
        CircleArticleBus.register(this);
    }

    public void onDestroy() {
        CircleArticleBus.unregister(this);
        super.onDestroy();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.layout_circle_topic_weekly, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(view);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.a.autoRefresh();
    }

    private void a(View view) {
        this.e = (LoadingLayout) view.findViewById(R.id.loading);
        this.e.onLoading();
        this.a = (PtrLayout) view.findViewById(R.id.ptr);
        this.b = (ListView) view.findViewById(R.id.listview);
        this.n = UIHelper.dip2px(getActivity(), 30.0f);
        this.o = view.findViewById(R.id.custom_action_bar);
        this.p = (TextView) view.findViewById(R.id.back);
        this.p.setText("话题");
        this.p.setOnClickListener(new as(this));
        this.q = view.findViewById(R.id.more);
        this.q.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.ic_weekly_other_dark : R.drawable.ic_weekly_other);
        this.q.setOnClickListener(new at(this));
        this.a.setLoadMoreEnable(false);
        this.a.setPtrListener(this);
        this.k = (TopicHeader) view.findViewById(R.id.header);
        this.l = (TopicScrollView) view.findViewById(R.id.header_scroll);
        this.t = (ImageView) view.findViewById(R.id.header_image);
        this.r = (TextView) view.findViewById(R.id.episode);
        this.s = (TextView) view.findViewById(R.id.title);
        this.m = new au(this, getActivity());
        this.b.addHeaderView(this.m);
        this.u = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.layout_circle_topic_weekly_intro, null);
        this.b.addHeaderView(this.u);
        this.j = LayoutInflater.from(getActivity()).inflate(R.layout.qiushi_listview_foot, null);
        LinearLayout linearLayout = (LinearLayout) this.j.findViewById(R.id.foot_lin);
        View findViewById = this.j.findViewById(R.id.foot_left_line);
        View findViewById2 = this.j.findViewById(R.id.foot_right_line);
        TextView textView = (TextView) this.j.findViewById(R.id.foot_remind);
        linearLayout.setBackgroundColor(UIHelper.isNightTheme() ? -15132387 : -855310);
        findViewById.setBackgroundColor(0);
        findViewById2.setBackgroundColor(0);
        textView.setPadding(UIHelper.dip2px(getActivity(), 15.0f), 0, 0, UIHelper.dip2px(getActivity(), 15.0f));
        textView.setTextColor(UIHelper.isNightTheme() ? -12829625 : -5197644);
        textView.setMaxLines(Integer.MAX_VALUE);
        CharSequence spannableStringBuilder = new SpannableStringBuilder("感谢你的阅读，遇到你觉得有意思的话题，可以推荐给糗我们哦，推荐群");
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append("481387897");
        spannableStringBuilder.setSpan(new av(this), length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#ff639ce0")), length, spannableStringBuilder.length(), 33);
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.b.addFooterView(this.j);
        this.j.setOnClickListener(new aw(this));
        this.j.setVisibility(8);
        this.f = b();
        this.b.setAdapter(this.f);
        this.a.setOnScrollOffsetListener(new ax(this));
        int a = a();
        this.o.setPadding(0, a, 0, 0);
        this.k.setStatusBarHeight(a);
    }

    private BaseAdapter b() {
        return new CircleTopicWeeklyAdapter(this.c, getActivity(), this);
    }

    public void onRefresh() {
        a(1);
        this.i = true;
    }

    public void onLoadMore() {
        if (!this.i) {
            a(this.h + 1);
            this.i = true;
        }
    }

    private void a(int i) {
        this.g = new SimpleHttpTask(String.format(Constants.CIRCLE_TOPIC_COLLECTION_INFO, new Object[]{this.d, Integer.valueOf(i)}), new az(this, i));
        this.g.execute();
        this.f.notifyDataSetChanged();
    }

    private void c() {
        this.e.hide();
    }

    int a() {
        if (VERSION.SDK_INT < 19) {
            return 0;
        }
        try {
            return Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
        } catch (Exception e) {
            return 23;
        }
    }

    public void onCircleShareStart(CircleArticle circleArticle) {
        ShareUtils.openShareDialog(this, 1, circleArticle);
    }

    public void onCircleShareStart(CircleArticle circleArticle, String str, View view) {
        ShareUtils.openShareDialog(this, 1, circleArticle, str);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        super.onActivityResult(i, i2, intent);
        if (i == 1 && intent != null) {
            CircleArticle circleArticle = (CircleArticle) intent.getSerializableExtra("circleArticle");
            if (circleArticle != null) {
                if (i2 == 12) {
                    a(circleArticle);
                } else {
                    ArticleMoreOperationbar.handleShare(i2, (Fragment) this, circleArticle);
                }
            }
        }
    }

    private void a(CircleArticle circleArticle) {
        if (this.c.contains(circleArticle) && circleArticle.isVideoArticle()) {
            View childAt = this.b.getChildAt((this.c.indexOf(circleArticle) - this.b.getFirstVisiblePosition()) + this.b.getHeaderViewsCount());
            if (childAt != null && (childAt.getTag() instanceof NormalCell)) {
                NormalCell normalCell = (NormalCell) childAt.getTag();
                if (QsbkApp.getInstance().isAutoPlayVideo()) {
                    normalCell.playerView.download();
                } else {
                    normalCell.playerView.downloadWithoutPlay();
                }
            }
        }
    }

    public void onArticleCreate(CircleArticle circleArticle) {
    }

    public void onArticleUpdate(CircleArticle circleArticle) {
        if (this.c != null && this.c.size() != 0) {
            for (int i = 0; i < this.c.size(); i++) {
                Object obj = this.c.get(i);
                if (obj instanceof CircleArticle) {
                    CircleArticle circleArticle2 = (CircleArticle) obj;
                    if (TextUtils.equals(circleArticle2.id, circleArticle.id)) {
                        circleArticle2.updateWith(circleArticle);
                        break;
                    }
                }
            }
            if (this.f != null) {
                this.f.notifyDataSetChanged();
            }
        }
    }

    public void onArticleDelete(CircleArticle circleArticle) {
        if (this.c != null && this.c.size() != 0 && this.c.contains(circleArticle)) {
            this.c.remove(circleArticle);
            if (this.f != null) {
                this.f.notifyDataSetChanged();
            }
        }
    }
}
