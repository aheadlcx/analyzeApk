package cn.xiaochuankeji.tieba.ui.tale;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.tale.TaleTheme;
import cn.xiaochuankeji.tieba.json.tale.FollowPostArticleJson;
import cn.xiaochuankeji.tieba.json.tale.FollowPostThemeJson;
import cn.xiaochuankeji.tieba.ui.auth.a;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.AvatarListLayout;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class FollowPostAdapter extends Adapter<ViewHolder> {
    private Context a;
    private List<FollowPostThemeJson> b = new ArrayList();

    class FollowPostHolder extends ViewHolder {
        View a;
        @BindView
        AvatarListLayout avatarListLayout;
        FollowPostThemeJson b;
        final /* synthetic */ FollowPostAdapter c;
        @BindView
        View fl_container;
        @BindView
        WebImageView iv_content;
        @BindView
        WebImageView iv_content_first;
        @BindView
        WebImageView iv_content_second;
        @BindView
        LinearLayout ll_card;
        @BindView
        LinearLayout ll_list;
        @BindView
        RelativeLayout rl_img;
        @BindView
        RelativeLayout rl_img_first;
        @BindView
        RelativeLayout rl_img_second;
        @BindView
        View sfl;
        @BindView
        View sfl_first;
        @BindView
        View sfl_second;
        @BindView
        TextView tv_content;
        @BindView
        TextView tv_content_first;
        @BindView
        TextView tv_content_second;
        @BindView
        TextView tv_count;
        @BindView
        TextView tv_create;
        @BindView
        TextView tv_img_count;
        @BindView
        TextView tv_img_count_first;
        @BindView
        TextView tv_img_count_second;
        @BindView
        TextView tv_name;
        @BindView
        TextView tv_name_first;
        @BindView
        TextView tv_name_second;
        @BindView
        TextView tv_subject;

        public FollowPostHolder(FollowPostAdapter followPostAdapter, View view) {
            this.c = followPostAdapter;
            super(view);
            ButterKnife.a(this, view);
            this.a = view;
        }

        public void a(FollowPostThemeJson followPostThemeJson) {
            this.b = followPostThemeJson;
            this.tv_subject.setText(followPostThemeJson.title);
            this.tv_count.setText(this.c.a.getString(R.string.follow_post_count_text, new Object[]{String.valueOf(followPostThemeJson.postCount)}));
            this.avatarListLayout.setAvatarList(followPostThemeJson.authors);
            this.fl_container.setVisibility(0);
            this.avatarListLayout.setVisibility(0);
            this.tv_count.setVisibility(0);
            if (followPostThemeJson.postList == null || followPostThemeJson.postList.size() == 0) {
                this.fl_container.setVisibility(8);
                this.avatarListLayout.setVisibility(8);
                this.tv_count.setVisibility(8);
            } else if (followPostThemeJson.postList.size() > 1) {
                this.ll_list.setVisibility(0);
                this.ll_card.setVisibility(8);
                r0 = (FollowPostArticleJson) followPostThemeJson.postList.get(0);
                if (r0 != null && r0.author != null) {
                    this.tv_name_first.setText(this.c.a.getString(R.string.by_author, new Object[]{d.b(r0.author.name)}));
                    if (r0.coverId != 0) {
                        this.tv_content_first.setPadding(e.a(12.0f), e.a(12.0f), e.a(12.0f), e.a(12.0f));
                        this.tv_content_first.setMaxLines(2);
                        this.rl_img_first.setVisibility(0);
                        this.iv_content_first.setWebImage(b.a(r0.coverId, true));
                        if (r0.imgCount < 2) {
                            this.tv_img_count_first.setVisibility(4);
                        } else {
                            this.tv_img_count_first.setVisibility(0);
                            this.tv_img_count_first.setText(this.c.a.getString(R.string.follow_post_img_count, new Object[]{String.valueOf(r0.imgCount)}));
                        }
                    } else {
                        this.tv_content_first.setPadding(e.a(12.0f), e.a(18.0f), e.a(12.0f), e.a(18.0f));
                        this.tv_content_first.setMaxLines(6);
                        this.rl_img_first.setVisibility(8);
                    }
                    this.tv_content_first.setText(r0.summary);
                    r0 = (FollowPostArticleJson) followPostThemeJson.postList.get(1);
                    if (r0 != null && r0.author != null) {
                        this.tv_name_second.setText(this.c.a.getString(R.string.by_author, new Object[]{d.b(r0.author.name)}));
                        if (r0.coverId != 0) {
                            this.tv_content_second.setPadding(e.a(12.0f), e.a(12.0f), e.a(12.0f), e.a(12.0f));
                            this.tv_content_second.setMaxLines(2);
                            this.rl_img_second.setVisibility(0);
                            this.iv_content_second.setWebImage(b.a(r0.coverId, true));
                            if (r0.imgCount < 2) {
                                this.tv_img_count_second.setVisibility(4);
                            } else {
                                this.tv_img_count_second.setVisibility(0);
                                this.tv_img_count_second.setText(this.c.a.getString(R.string.follow_post_img_count, new Object[]{String.valueOf(r0.imgCount)}));
                            }
                        } else {
                            this.tv_content_second.setPadding(e.a(12.0f), e.a(18.0f), e.a(12.0f), e.a(18.0f));
                            this.tv_content_second.setMaxLines(6);
                            this.rl_img_second.setVisibility(8);
                        }
                        this.tv_content_second.setText(r0.summary);
                    }
                }
            } else if (followPostThemeJson.postList.size() == 1) {
                this.ll_list.setVisibility(8);
                this.ll_card.setVisibility(0);
                r0 = (FollowPostArticleJson) followPostThemeJson.postList.get(0);
                if (r0 != null && r0.author != null) {
                    this.tv_name.setText(this.c.a.getString(R.string.by_author, new Object[]{d.b(r0.author.name)}));
                    if (r0.coverId != 0) {
                        this.tv_content.setPadding(e.a(12.0f), e.a(12.0f), e.a(12.0f), e.a(12.0f));
                        this.tv_content.setMaxLines(2);
                        this.rl_img.setVisibility(0);
                        this.iv_content.setWebImage(b.a(r0.coverId, true));
                        if (r0.imgCount < 2) {
                            this.tv_img_count.setVisibility(4);
                        } else {
                            this.tv_img_count.setVisibility(0);
                            this.tv_img_count.setText(this.c.a.getString(R.string.follow_post_img_count, new Object[]{String.valueOf(r0.imgCount)}));
                        }
                    } else {
                        this.tv_content.setPadding(e.a(12.0f), e.a(18.0f), e.a(12.0f), e.a(18.0f));
                        this.tv_content.setMaxLines(4);
                        this.rl_img.setVisibility(8);
                    }
                    this.tv_content.setText(r0.summary);
                }
            }
        }

        @OnClick
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.root:
                    JSONArray jSONArray = new JSONArray();
                    if (this.b.postList != null) {
                        for (FollowPostArticleJson followPostArticleJson : this.b.postList) {
                            jSONArray.put(followPostArticleJson.id);
                            if (jSONArray.length() >= 2) {
                            }
                        }
                    }
                    TaleListActivity.a(this.c.a, "index", this.b.id, jSONArray.toString());
                    return;
                case R.id.sfl_first:
                    TaleDetailActivity.a(this.c.a, "index", ((FollowPostArticleJson) this.b.postList.get(0)).id);
                    return;
                case R.id.sfl_second:
                    TaleDetailActivity.a(this.c.a, "index", ((FollowPostArticleJson) this.b.postList.get(1)).id);
                    return;
                case R.id.sfl:
                    TaleDetailActivity.a(this.c.a, "index", ((FollowPostArticleJson) this.b.postList.get(0)).id);
                    return;
                case R.id.tv_create:
                    if (a.a((FragmentActivity) this.c.a, "home_tab", 9)) {
                        TaleTheme taleTheme = new TaleTheme();
                        taleTheme.id = this.b.id;
                        taleTheme.ct = this.b.createTime;
                        taleTheme.title = this.b.title;
                        taleTheme.author = this.b.author;
                        taleTheme.articleCnt = this.b.postCount;
                        TaleCreateActivity.a((Activity) this.c.a, "index", taleTheme, 4);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public class FollowPostHolder_ViewBinding implements Unbinder {
        private FollowPostHolder b;
        private View c;
        private View d;
        private View e;
        private View f;
        private View g;

        @UiThread
        public FollowPostHolder_ViewBinding(final FollowPostHolder followPostHolder, View view) {
            this.b = followPostHolder;
            followPostHolder.tv_subject = (TextView) butterknife.a.b.b(view, R.id.tv_subject, "field 'tv_subject'", TextView.class);
            followPostHolder.tv_count = (TextView) butterknife.a.b.b(view, R.id.tv_count, "field 'tv_count'", TextView.class);
            followPostHolder.avatarListLayout = (AvatarListLayout) butterknife.a.b.b(view, R.id.avatar_list, "field 'avatarListLayout'", AvatarListLayout.class);
            followPostHolder.ll_list = (LinearLayout) butterknife.a.b.b(view, R.id.ll_list, "field 'll_list'", LinearLayout.class);
            followPostHolder.tv_name_first = (TextView) butterknife.a.b.b(view, R.id.tv_name_first, "field 'tv_name_first'", TextView.class);
            followPostHolder.tv_content_first = (TextView) butterknife.a.b.b(view, R.id.tv_content_first, "field 'tv_content_first'", TextView.class);
            followPostHolder.rl_img_first = (RelativeLayout) butterknife.a.b.b(view, R.id.rl_img_first, "field 'rl_img_first'", RelativeLayout.class);
            followPostHolder.iv_content_first = (WebImageView) butterknife.a.b.b(view, R.id.iv_content_first, "field 'iv_content_first'", WebImageView.class);
            followPostHolder.tv_img_count_first = (TextView) butterknife.a.b.b(view, R.id.tv_img_count_first, "field 'tv_img_count_first'", TextView.class);
            followPostHolder.tv_name_second = (TextView) butterknife.a.b.b(view, R.id.tv_name_second, "field 'tv_name_second'", TextView.class);
            followPostHolder.tv_content_second = (TextView) butterknife.a.b.b(view, R.id.tv_content_second, "field 'tv_content_second'", TextView.class);
            followPostHolder.rl_img_second = (RelativeLayout) butterknife.a.b.b(view, R.id.rl_img_second, "field 'rl_img_second'", RelativeLayout.class);
            followPostHolder.iv_content_second = (WebImageView) butterknife.a.b.b(view, R.id.iv_content_second, "field 'iv_content_second'", WebImageView.class);
            followPostHolder.tv_img_count_second = (TextView) butterknife.a.b.b(view, R.id.tv_img_count_second, "field 'tv_img_count_second'", TextView.class);
            followPostHolder.ll_card = (LinearLayout) butterknife.a.b.b(view, R.id.ll_card, "field 'll_card'", LinearLayout.class);
            followPostHolder.tv_name = (TextView) butterknife.a.b.b(view, R.id.tv_name, "field 'tv_name'", TextView.class);
            followPostHolder.tv_content = (TextView) butterknife.a.b.b(view, R.id.tv_content, "field 'tv_content'", TextView.class);
            followPostHolder.rl_img = (RelativeLayout) butterknife.a.b.b(view, R.id.rl_img, "field 'rl_img'", RelativeLayout.class);
            followPostHolder.iv_content = (WebImageView) butterknife.a.b.b(view, R.id.iv_content, "field 'iv_content'", WebImageView.class);
            followPostHolder.tv_img_count = (TextView) butterknife.a.b.b(view, R.id.tv_img_count, "field 'tv_img_count'", TextView.class);
            View a = butterknife.a.b.a(view, R.id.tv_create, "field 'tv_create' and method 'onClick'");
            followPostHolder.tv_create = (TextView) butterknife.a.b.c(a, R.id.tv_create, "field 'tv_create'", TextView.class);
            this.c = a;
            a.setOnClickListener(new butterknife.a.a(this) {
                final /* synthetic */ FollowPostHolder_ViewBinding c;

                public void a(View view) {
                    followPostHolder.onClick(view);
                }
            });
            View a2 = butterknife.a.b.a(view, R.id.sfl, "field 'sfl' and method 'onClick'");
            followPostHolder.sfl = a2;
            this.d = a2;
            a2.setOnClickListener(new butterknife.a.a(this) {
                final /* synthetic */ FollowPostHolder_ViewBinding c;

                public void a(View view) {
                    followPostHolder.onClick(view);
                }
            });
            a2 = butterknife.a.b.a(view, R.id.sfl_first, "field 'sfl_first' and method 'onClick'");
            followPostHolder.sfl_first = a2;
            this.e = a2;
            a2.setOnClickListener(new butterknife.a.a(this) {
                final /* synthetic */ FollowPostHolder_ViewBinding c;

                public void a(View view) {
                    followPostHolder.onClick(view);
                }
            });
            a2 = butterknife.a.b.a(view, R.id.sfl_second, "field 'sfl_second' and method 'onClick'");
            followPostHolder.sfl_second = a2;
            this.f = a2;
            a2.setOnClickListener(new butterknife.a.a(this) {
                final /* synthetic */ FollowPostHolder_ViewBinding c;

                public void a(View view) {
                    followPostHolder.onClick(view);
                }
            });
            followPostHolder.fl_container = butterknife.a.b.a(view, R.id.fl_container, "field 'fl_container'");
            a2 = butterknife.a.b.a(view, R.id.root, "method 'onClick'");
            this.g = a2;
            a2.setOnClickListener(new butterknife.a.a(this) {
                final /* synthetic */ FollowPostHolder_ViewBinding c;

                public void a(View view) {
                    followPostHolder.onClick(view);
                }
            });
        }

        @CallSuper
        public void a() {
            FollowPostHolder followPostHolder = this.b;
            if (followPostHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.b = null;
            followPostHolder.tv_subject = null;
            followPostHolder.tv_count = null;
            followPostHolder.avatarListLayout = null;
            followPostHolder.ll_list = null;
            followPostHolder.tv_name_first = null;
            followPostHolder.tv_content_first = null;
            followPostHolder.rl_img_first = null;
            followPostHolder.iv_content_first = null;
            followPostHolder.tv_img_count_first = null;
            followPostHolder.tv_name_second = null;
            followPostHolder.tv_content_second = null;
            followPostHolder.rl_img_second = null;
            followPostHolder.iv_content_second = null;
            followPostHolder.tv_img_count_second = null;
            followPostHolder.ll_card = null;
            followPostHolder.tv_name = null;
            followPostHolder.tv_content = null;
            followPostHolder.rl_img = null;
            followPostHolder.iv_content = null;
            followPostHolder.tv_img_count = null;
            followPostHolder.tv_create = null;
            followPostHolder.sfl = null;
            followPostHolder.sfl_first = null;
            followPostHolder.sfl_second = null;
            followPostHolder.fl_container = null;
            this.c.setOnClickListener(null);
            this.c = null;
            this.d.setOnClickListener(null);
            this.d = null;
            this.e.setOnClickListener(null);
            this.e = null;
            this.f.setOnClickListener(null);
            this.f = null;
            this.g.setOnClickListener(null);
            this.g = null;
        }
    }

    public FollowPostAdapter(Context context) {
        this.a = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FollowPostHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_follow_post, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ((FollowPostHolder) viewHolder).a((FollowPostThemeJson) this.b.get(i));
    }

    public int getItemCount() {
        return this.b.size();
    }

    public void a(List<FollowPostThemeJson> list) {
        this.b = list;
        notifyDataSetChanged();
    }

    public void b(List<FollowPostThemeJson> list) {
        this.b.addAll(list);
        notifyDataSetChanged();
    }

    public void a(FollowPostThemeJson followPostThemeJson) {
        this.b.add(0, followPostThemeJson);
        notifyDataSetChanged();
    }
}
