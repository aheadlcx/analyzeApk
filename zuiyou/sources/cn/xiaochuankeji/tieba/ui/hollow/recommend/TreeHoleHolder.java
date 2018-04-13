package cn.xiaochuankeji.tieba.ui.hollow.recommend;

import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.ui.hollow.data.HollowRecommendItemBean;
import cn.xiaochuankeji.tieba.ui.hollow.detail.HollowDetailActivity;
import cn.xiaochuankeji.tieba.ui.hollow.widget.HollowFeedHugView;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.c;
import rx.b.b;

public class TreeHoleHolder extends ViewHolder {
    private static int a = 0;
    private static int b = 0;
    @BindView
    ImageView anchor1;
    @BindView
    ImageView anchor2;
    @BindView
    ImageView anchor3;
    @BindView
    ImageView anchor4;
    @BindView
    View audioBg1;
    @BindView
    View audioBg2;
    @BindView
    View audioBg3;
    @BindView
    View audioBg4;
    @BindView
    View audioIndicator1;
    @BindView
    View audioIndicator2;
    @BindView
    View audioIndicator3;
    @BindView
    View audioIndicator4;
    private int c;
    @BindView
    ConstraintLayout contentLayout1;
    @BindView
    ConstraintLayout contentLayout2;
    @BindView
    ConstraintLayout contentLayout3;
    @BindView
    ConstraintLayout contentLayout4;
    private ArrayList<HollowRecommendItemBean> d;
    @BindView
    Guideline guideline6;
    @BindView
    Guideline guideline7;
    @BindView
    Guideline guideline8;
    @BindView
    HollowFeedHugView hugView1;
    @BindView
    HollowFeedHugView hugView2;
    @BindView
    HollowFeedHugView hugView3;
    @BindView
    HollowFeedHugView hugView4;
    @BindView
    ConstraintLayout layout1;
    @BindView
    ConstraintLayout layout2;
    @BindView
    ConstraintLayout layout3;
    @BindView
    ConstraintLayout layout4;
    @BindView
    ConstraintLayout parentView;
    @BindView
    View playBtn1;
    @BindView
    View playBtn2;
    @BindView
    View playBtn3;
    @BindView
    View playBtn4;
    @BindView
    WebImageView role1;
    @BindView
    WebImageView role2;
    @BindView
    WebImageView role3;
    @BindView
    WebImageView role4;
    @BindView
    TextView textComment1;
    @BindView
    TextView textComment2;
    @BindView
    TextView textComment3;
    @BindView
    TextView textComment4;
    @BindView
    AppCompatTextView textContent1;
    @BindView
    AppCompatTextView textContent2;
    @BindView
    AppCompatTextView textContent3;
    @BindView
    AppCompatTextView textContent4;
    @BindView
    TextView textDur1;
    @BindView
    TextView textDur2;
    @BindView
    TextView textDur3;
    @BindView
    TextView textDur4;
    @BindView
    ImageView treeDown1;
    @BindView
    ImageView treeDown2;
    @BindView
    ImageView treeDown3;
    @BindView
    ImageView treeDown4;
    @BindView
    ImageView treeDown5;
    @BindView
    ImageView treeUp1;
    @BindView
    ImageView treeUp2;
    @BindView
    ImageView treeUp3;
    @BindView
    ImageView treeUp4;
    @BindView
    ImageView treeUp5;

    public TreeHoleHolder(View view) {
        super(view);
        if (a <= 0 || b <= 0) {
            int c = e.c(view.getContext());
            a = (int) (((float) c) * 0.85f);
            b = (int) (((float) c) * 0.68f);
        }
        ButterKnife.a(this, view);
        b();
    }

    private void b() {
        h.a(this.role1, new Runnable(this) {
            final /* synthetic */ TreeHoleHolder a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.d.size() >= 1) {
                    HollowDetailActivity.a(this.a.itemView.getContext(), (HollowRecommendItemBean) this.a.d.get(0), "navigator");
                }
            }
        });
        h.a(this.role2, new Runnable(this) {
            final /* synthetic */ TreeHoleHolder a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.d.size() >= 2) {
                    HollowDetailActivity.a(this.a.itemView.getContext(), (HollowRecommendItemBean) this.a.d.get(1), "navigator");
                }
            }
        });
        h.a(this.role3, new Runnable(this) {
            final /* synthetic */ TreeHoleHolder a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.d.size() >= 3) {
                    HollowDetailActivity.a(this.a.itemView.getContext(), (HollowRecommendItemBean) this.a.d.get(2), "navigator");
                }
            }
        });
        h.a(this.role4, new Runnable(this) {
            final /* synthetic */ TreeHoleHolder a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.d.size() == 4) {
                    HollowDetailActivity.a(this.a.itemView.getContext(), (HollowRecommendItemBean) this.a.d.get(3), "navigator");
                }
            }
        });
    }

    @SuppressLint({"SetTextI18n"})
    public void a(final int i, List<HollowRecommendItemBean> list) {
        this.c = i;
        this.d = (ArrayList) list;
        if (i == 0) {
            this.treeDown1.setVisibility(8);
            this.treeDown2.setVisibility(8);
            this.treeDown3.setVisibility(8);
            this.treeDown4.setVisibility(8);
            this.treeDown5.setVisibility(8);
        } else {
            this.treeDown1.setVisibility(0);
            this.treeDown2.setVisibility(0);
            this.treeDown3.setVisibility(0);
            this.treeDown4.setVisibility(0);
            this.treeDown5.setVisibility(0);
        }
        a();
        float a = (e.a() > 0.0f ? e.a() : 3.0f) / 1.15f;
        c();
        if (list != null && list.size() > 0) {
            int i2;
            LayoutParams layoutParams;
            ConstraintLayout.LayoutParams layoutParams2;
            final HollowRecommendItemBean hollowRecommendItemBean = (HollowRecommendItemBean) list.get(0);
            this.textContent1.setText(hollowRecommendItemBean.subject);
            this.hugView1.setRoomData(hollowRecommendItemBean);
            this.textComment1.setText(e.a((long) hollowRecommendItemBean.msgCount) + " 回复");
            this.role1.setImageURI(a.a("/img/png/id/", hollowRecommendItemBean.emotion.imageId, ""));
            if (hollowRecommendItemBean.emotion.width > 0 && hollowRecommendItemBean.emotion.width > 0) {
                i2 = (int) (((float) hollowRecommendItemBean.emotion.height) * a);
                layoutParams = (ConstraintLayout.LayoutParams) this.role1.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = new ConstraintLayout.LayoutParams((int) (((float) hollowRecommendItemBean.emotion.width) * a), (int) (((float) hollowRecommendItemBean.emotion.height) * a));
                } else {
                    layoutParams.width = (int) (((float) hollowRecommendItemBean.emotion.width) * a);
                    layoutParams.height = (int) (((float) hollowRecommendItemBean.emotion.height) * a);
                }
                this.role1.setLayoutParams(layoutParams);
                layoutParams2 = (ConstraintLayout.LayoutParams) this.layout1.getLayoutParams();
                layoutParams2.height = (int) (((double) (this.treeUp1.getLayoutParams().height + i2)) - 36.6d);
                this.layout1.setLayoutParams(layoutParams2);
                layoutParams2 = (ConstraintLayout.LayoutParams) this.contentLayout1.getLayoutParams();
                layoutParams2.bottomToTop = this.layout1.getId();
                layoutParams2.bottomMargin = 15;
                this.contentLayout1.setLayoutParams(layoutParams2);
            }
            this.contentLayout1.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ TreeHoleHolder b;

                public void onClick(View view) {
                    HollowDetailActivity.a(this.b.itemView.getContext(), hollowRecommendItemBean, "navigator");
                }
            });
            if (hollowRecommendItemBean.audio == null || (TextUtils.isEmpty(hollowRecommendItemBean.audio.url) && hollowRecommendItemBean.audio.dur == 0)) {
                this.audioBg1.setVisibility(8);
                this.textDur1.setVisibility(8);
                this.playBtn1.setVisibility(8);
                this.audioIndicator1.setVisibility(8);
            } else {
                this.audioBg1.setVisibility(0);
                this.textDur1.setVisibility(0);
                this.playBtn1.setVisibility(0);
                this.audioIndicator1.setVisibility(0);
                this.textDur1.setText(hollowRecommendItemBean.audio.dur + "\"");
            }
            com.jakewharton.a.b.a.a(this.audioBg1).d(1000, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new b<Void>(this) {
                final /* synthetic */ TreeHoleHolder c;

                public /* synthetic */ void call(Object obj) {
                    a((Void) obj);
                }

                public void a(Void voidR) {
                    c.a().d(new e(i, hollowRecommendItemBean));
                }
            });
            if (list.size() >= 2) {
                hollowRecommendItemBean = (HollowRecommendItemBean) list.get(1);
                this.textContent2.setText(hollowRecommendItemBean.subject);
                this.hugView2.setRoomData(hollowRecommendItemBean);
                this.textComment2.setText(e.a((long) hollowRecommendItemBean.msgCount) + " 回复");
                this.role2.setImageURI(a.a("/img/png/id/", hollowRecommendItemBean.emotion.imageId, ""));
                if (hollowRecommendItemBean.emotion.width > 0 && hollowRecommendItemBean.emotion.width > 0) {
                    i2 = (int) (((float) hollowRecommendItemBean.emotion.height) * a);
                    layoutParams = (ConstraintLayout.LayoutParams) this.role2.getLayoutParams();
                    if (layoutParams == null) {
                        layoutParams = new ConstraintLayout.LayoutParams((int) (((float) hollowRecommendItemBean.emotion.width) * a), (int) (((float) hollowRecommendItemBean.emotion.height) * a));
                    } else {
                        layoutParams.width = (int) (((float) hollowRecommendItemBean.emotion.width) * a);
                        layoutParams.height = (int) (((float) hollowRecommendItemBean.emotion.height) * a);
                    }
                    this.role2.setLayoutParams(layoutParams);
                    layoutParams2 = (ConstraintLayout.LayoutParams) this.layout2.getLayoutParams();
                    layoutParams2.height = (int) (((double) (this.treeUp2.getLayoutParams().height + i2)) - 36.6d);
                    this.layout2.setLayoutParams(layoutParams2);
                    layoutParams2 = (ConstraintLayout.LayoutParams) this.contentLayout2.getLayoutParams();
                    layoutParams2.bottomToTop = this.layout2.getId();
                    layoutParams2.bottomMargin = 15;
                    this.contentLayout2.setLayoutParams(layoutParams2);
                }
                this.contentLayout2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ TreeHoleHolder b;

                    public void onClick(View view) {
                        HollowDetailActivity.a(this.b.itemView.getContext(), hollowRecommendItemBean, "navigator");
                    }
                });
                if (hollowRecommendItemBean.audio == null || (TextUtils.isEmpty(hollowRecommendItemBean.audio.url) && hollowRecommendItemBean.audio.dur == 0)) {
                    this.audioBg2.setVisibility(8);
                    this.textDur2.setVisibility(8);
                    this.playBtn2.setVisibility(8);
                    this.audioIndicator2.setVisibility(8);
                } else {
                    this.audioBg2.setVisibility(0);
                    this.textDur2.setVisibility(0);
                    this.playBtn2.setVisibility(0);
                    this.audioIndicator2.setVisibility(0);
                    this.textDur2.setText(hollowRecommendItemBean.audio.dur + "\"");
                }
                com.jakewharton.a.b.a.a(this.audioBg2).d(1000, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new b<Void>(this) {
                    final /* synthetic */ TreeHoleHolder c;

                    public /* synthetic */ void call(Object obj) {
                        a((Void) obj);
                    }

                    public void a(Void voidR) {
                        c.a().d(new e(i, hollowRecommendItemBean));
                    }
                });
                if (list.size() >= 3) {
                    hollowRecommendItemBean = (HollowRecommendItemBean) list.get(2);
                    this.hugView3.setRoomData(hollowRecommendItemBean);
                    this.textContent3.setText(hollowRecommendItemBean.subject);
                    this.textComment3.setText(e.a((long) hollowRecommendItemBean.msgCount) + " 回复");
                    this.role3.setImageURI(a.a("/img/png/id/", hollowRecommendItemBean.emotion.imageId, ""));
                    if (hollowRecommendItemBean.emotion.width > 0 && hollowRecommendItemBean.emotion.width > 0) {
                        i2 = (int) (((float) hollowRecommendItemBean.emotion.height) * a);
                        layoutParams = (ConstraintLayout.LayoutParams) this.role3.getLayoutParams();
                        if (layoutParams == null) {
                            layoutParams = new ConstraintLayout.LayoutParams((int) (((float) hollowRecommendItemBean.emotion.width) * a), (int) (((float) hollowRecommendItemBean.emotion.height) * a));
                        } else {
                            layoutParams.width = (int) (((float) hollowRecommendItemBean.emotion.width) * a);
                            layoutParams.height = (int) (((float) hollowRecommendItemBean.emotion.height) * a);
                        }
                        this.role3.setLayoutParams(layoutParams);
                        layoutParams2 = (ConstraintLayout.LayoutParams) this.layout3.getLayoutParams();
                        layoutParams2.height = (int) (((double) (this.treeUp3.getLayoutParams().height + i2)) - 36.6d);
                        this.layout3.setLayoutParams(layoutParams2);
                        layoutParams2 = (ConstraintLayout.LayoutParams) this.contentLayout3.getLayoutParams();
                        layoutParams2.bottomToTop = this.layout3.getId();
                        layoutParams2.bottomMargin = 15;
                        this.contentLayout3.setLayoutParams(layoutParams2);
                    }
                    this.contentLayout3.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ TreeHoleHolder b;

                        public void onClick(View view) {
                            HollowDetailActivity.a(this.b.itemView.getContext(), hollowRecommendItemBean, "navigator");
                        }
                    });
                    if (hollowRecommendItemBean.audio == null || (TextUtils.isEmpty(hollowRecommendItemBean.audio.url) && hollowRecommendItemBean.audio.dur == 0)) {
                        this.audioBg3.setVisibility(8);
                        this.textDur3.setVisibility(8);
                        this.playBtn3.setVisibility(8);
                        this.audioIndicator3.setVisibility(8);
                    } else {
                        this.audioBg3.setVisibility(0);
                        this.textDur3.setVisibility(0);
                        this.playBtn3.setVisibility(0);
                        this.audioIndicator3.setVisibility(0);
                        this.textDur3.setText(hollowRecommendItemBean.audio.dur + "\"");
                    }
                    com.jakewharton.a.b.a.a(this.audioBg3).d(1000, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new b<Void>(this) {
                        final /* synthetic */ TreeHoleHolder c;

                        public /* synthetic */ void call(Object obj) {
                            a((Void) obj);
                        }

                        public void a(Void voidR) {
                            c.a().d(new e(i, hollowRecommendItemBean));
                        }
                    });
                    if (list.size() >= 4) {
                        hollowRecommendItemBean = (HollowRecommendItemBean) list.get(3);
                        this.hugView4.setRoomData(hollowRecommendItemBean);
                        this.textContent4.setText(hollowRecommendItemBean.subject);
                        this.textComment4.setText(e.a((long) hollowRecommendItemBean.msgCount) + " 回复");
                        this.role4.setImageURI(a.a("/img/png/id/", hollowRecommendItemBean.emotion.imageId, ""));
                        if (hollowRecommendItemBean.emotion.width > 0 && hollowRecommendItemBean.emotion.width > 0) {
                            i2 = (int) (((float) hollowRecommendItemBean.emotion.height) * a);
                            layoutParams = (ConstraintLayout.LayoutParams) this.role4.getLayoutParams();
                            if (layoutParams == null) {
                                layoutParams = new ConstraintLayout.LayoutParams((int) (((float) hollowRecommendItemBean.emotion.width) * a), (int) (a * ((float) hollowRecommendItemBean.emotion.height)));
                            } else {
                                layoutParams.width = (int) (((float) hollowRecommendItemBean.emotion.width) * a);
                                layoutParams.height = (int) (a * ((float) hollowRecommendItemBean.emotion.height));
                            }
                            this.role4.setLayoutParams(layoutParams);
                            layoutParams2 = (ConstraintLayout.LayoutParams) this.layout4.getLayoutParams();
                            layoutParams2.height = (int) (((double) (this.treeUp4.getLayoutParams().height + i2)) - 36.6d);
                            this.layout4.setLayoutParams(layoutParams2);
                            layoutParams2 = (ConstraintLayout.LayoutParams) this.contentLayout4.getLayoutParams();
                            layoutParams2.bottomToTop = this.layout4.getId();
                            layoutParams2.bottomMargin = 15;
                            this.contentLayout4.setLayoutParams(layoutParams2);
                        }
                        this.contentLayout4.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ TreeHoleHolder b;

                            public void onClick(View view) {
                                HollowDetailActivity.a(this.b.itemView.getContext(), hollowRecommendItemBean, "navigator");
                            }
                        });
                        if (hollowRecommendItemBean.audio == null || (TextUtils.isEmpty(hollowRecommendItemBean.audio.url) && hollowRecommendItemBean.audio.dur == 0)) {
                            this.audioBg4.setVisibility(8);
                            this.textDur4.setVisibility(8);
                            this.playBtn4.setVisibility(8);
                            this.audioIndicator4.setVisibility(8);
                        } else {
                            this.audioBg4.setVisibility(0);
                            this.textDur4.setVisibility(0);
                            this.playBtn4.setVisibility(0);
                            this.audioIndicator4.setVisibility(0);
                            this.textDur4.setText(hollowRecommendItemBean.audio.dur + "\"");
                        }
                        com.jakewharton.a.b.a.a(this.audioBg4).d(1000, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new b<Void>(this) {
                            final /* synthetic */ TreeHoleHolder c;

                            public /* synthetic */ void call(Object obj) {
                                a((Void) obj);
                            }

                            public void a(Void voidR) {
                                c.a().d(new e(i, hollowRecommendItemBean));
                            }
                        });
                    } else {
                        return;
                    }
                }
                return;
            }
            return;
        }
        this.itemView.requestLayout();
    }

    private void c() {
        this.contentLayout1.setVisibility(4);
        this.anchor1.setVisibility(4);
        this.contentLayout2.setVisibility(4);
        this.anchor2.setVisibility(4);
        this.contentLayout3.setVisibility(4);
        this.anchor3.setVisibility(4);
        this.contentLayout4.setVisibility(4);
        this.anchor4.setVisibility(4);
        this.role1.setVisibility(4);
        this.role2.setVisibility(4);
        this.role3.setVisibility(4);
        this.role4.setVisibility(4);
        if (this.d.size() >= 1) {
            this.contentLayout1.setVisibility(0);
            this.anchor1.setVisibility(0);
            this.role1.setVisibility(0);
        }
        if (this.d.size() >= 2) {
            this.contentLayout2.setVisibility(0);
            this.anchor2.setVisibility(0);
            this.role2.setVisibility(0);
        }
        if (this.d.size() >= 3) {
            this.contentLayout3.setVisibility(0);
            this.anchor3.setVisibility(0);
            this.role3.setVisibility(0);
        }
        if (this.d.size() >= 4) {
            this.contentLayout4.setVisibility(0);
            this.anchor4.setVisibility(0);
            this.role4.setVisibility(0);
        }
    }

    public void a() {
        a(this.layout1, true);
        a(this.layout2, true);
        a(this.layout3, true);
        a(this.treeUp5, true);
        a(this.anchor1, false);
        a(this.contentLayout1, false);
        a(this.anchor2, false);
        a(this.contentLayout2, false);
        a(this.anchor3, false);
        a(this.contentLayout3, false);
        a(this.anchor4, false);
        a(this.contentLayout4, false);
    }

    private void a(View view, boolean z) {
        int i = b - a;
        if (view != null) {
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            int i2 = iArr[1];
            if (i2 <= b || this.c == 0) {
                view.setAlpha(1.0f);
            } else if (i2 > a) {
                view.setAlpha(0.0f);
            } else {
                float f = (((float) (i2 - a)) * 1.0f) / ((float) i);
                float f2 = 0.8f + (0.2f * f);
                view.setAlpha(f);
            }
        }
    }

    public void a(HollowRecommendItemBean hollowRecommendItemBean) {
        switch (this.d.indexOf(hollowRecommendItemBean)) {
            case 0:
                this.playBtn1.setSelected(true);
                a(this.audioBg1);
                return;
            case 1:
                this.playBtn2.setSelected(true);
                a(this.audioBg2);
                return;
            case 2:
                this.playBtn3.setSelected(true);
                a(this.audioBg3);
                return;
            case 3:
                this.playBtn4.setSelected(true);
                a(this.audioBg4);
                return;
            default:
                return;
        }
    }

    public void b(HollowRecommendItemBean hollowRecommendItemBean) {
        switch (this.d.indexOf(hollowRecommendItemBean)) {
            case 0:
                this.playBtn1.setSelected(false);
                this.textDur1.setText(hollowRecommendItemBean.audio.dur + "\"");
                b(this.audioBg1);
                return;
            case 1:
                this.playBtn2.setSelected(false);
                this.textDur2.setText(hollowRecommendItemBean.audio.dur + "\"");
                b(this.audioBg2);
                return;
            case 2:
                this.playBtn3.setSelected(false);
                this.textDur3.setText(hollowRecommendItemBean.audio.dur + "\"");
                b(this.audioBg3);
                return;
            case 3:
                this.playBtn4.setSelected(false);
                this.textDur4.setText(hollowRecommendItemBean.audio.dur + "\"");
                b(this.audioBg4);
                return;
            default:
                return;
        }
    }

    public void a(HollowRecommendItemBean hollowRecommendItemBean, long j) {
        long j2 = 0;
        int indexOf = this.d.indexOf(hollowRecommendItemBean);
        long j3 = hollowRecommendItemBean.audio.dur - j;
        if (j3 >= 0) {
            j2 = j3;
        }
        switch (indexOf) {
            case 0:
                this.textDur1.setText(j2 + "\"");
                return;
            case 1:
                this.textDur2.setText(j2 + "\"");
                return;
            case 2:
                this.textDur3.setText(j2 + "\"");
                return;
            case 3:
                this.textDur4.setText(j2 + "\"");
                return;
            default:
                return;
        }
    }

    public void a(View view) {
        if (view != null) {
            Animation alphaAnimation = new AlphaAnimation(0.4f, 0.1f);
            alphaAnimation.setDuration(1000);
            alphaAnimation.setRepeatCount(-1);
            alphaAnimation.setRepeatMode(2);
            alphaAnimation.setInterpolator(new LinearInterpolator());
            view.startAnimation(alphaAnimation);
        }
    }

    public void b(View view) {
        if (view != null) {
            view.clearAnimation();
        }
    }
}
