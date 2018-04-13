package cn.xiaochuankeji.tieba.ui.hollow.recommend;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.hollow.widget.HollowFeedHugView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class TreeHoleHolder_ViewBinding implements Unbinder {
    private TreeHoleHolder b;

    @UiThread
    public TreeHoleHolder_ViewBinding(TreeHoleHolder treeHoleHolder, View view) {
        this.b = treeHoleHolder;
        treeHoleHolder.treeDown1 = (ImageView) b.b(view, R.id.tree_down1, "field 'treeDown1'", ImageView.class);
        treeHoleHolder.treeDown2 = (ImageView) b.b(view, R.id.tree_down2, "field 'treeDown2'", ImageView.class);
        treeHoleHolder.treeDown3 = (ImageView) b.b(view, R.id.tree_down3, "field 'treeDown3'", ImageView.class);
        treeHoleHolder.treeDown4 = (ImageView) b.b(view, R.id.tree_down4, "field 'treeDown4'", ImageView.class);
        treeHoleHolder.treeDown5 = (ImageView) b.b(view, R.id.tree_down5, "field 'treeDown5'", ImageView.class);
        treeHoleHolder.treeUp1 = (ImageView) b.b(view, R.id.tree_up1, "field 'treeUp1'", ImageView.class);
        treeHoleHolder.treeUp2 = (ImageView) b.b(view, R.id.tree_up2, "field 'treeUp2'", ImageView.class);
        treeHoleHolder.treeUp3 = (ImageView) b.b(view, R.id.tree_up3, "field 'treeUp3'", ImageView.class);
        treeHoleHolder.treeUp4 = (ImageView) b.b(view, R.id.tree_up4, "field 'treeUp4'", ImageView.class);
        treeHoleHolder.treeUp5 = (ImageView) b.b(view, R.id.tree_up5, "field 'treeUp5'", ImageView.class);
        treeHoleHolder.role1 = (WebImageView) b.b(view, R.id.role1, "field 'role1'", WebImageView.class);
        treeHoleHolder.role2 = (WebImageView) b.b(view, R.id.role2, "field 'role2'", WebImageView.class);
        treeHoleHolder.role3 = (WebImageView) b.b(view, R.id.role3, "field 'role3'", WebImageView.class);
        treeHoleHolder.role4 = (WebImageView) b.b(view, R.id.role4, "field 'role4'", WebImageView.class);
        treeHoleHolder.parentView = (ConstraintLayout) b.b(view, R.id.rootView, "field 'parentView'", ConstraintLayout.class);
        treeHoleHolder.layout1 = (ConstraintLayout) b.b(view, R.id.layout1, "field 'layout1'", ConstraintLayout.class);
        treeHoleHolder.layout2 = (ConstraintLayout) b.b(view, R.id.layout2, "field 'layout2'", ConstraintLayout.class);
        treeHoleHolder.layout3 = (ConstraintLayout) b.b(view, R.id.layout3, "field 'layout3'", ConstraintLayout.class);
        treeHoleHolder.layout4 = (ConstraintLayout) b.b(view, R.id.layout4, "field 'layout4'", ConstraintLayout.class);
        treeHoleHolder.guideline6 = (Guideline) b.b(view, R.id.guide_line6, "field 'guideline6'", Guideline.class);
        treeHoleHolder.guideline7 = (Guideline) b.b(view, R.id.guide_line7, "field 'guideline7'", Guideline.class);
        treeHoleHolder.guideline8 = (Guideline) b.b(view, R.id.guide_line8, "field 'guideline8'", Guideline.class);
        treeHoleHolder.anchor1 = (ImageView) b.b(view, R.id.iv_anchor1, "field 'anchor1'", ImageView.class);
        treeHoleHolder.anchor2 = (ImageView) b.b(view, R.id.iv_anchor2, "field 'anchor2'", ImageView.class);
        treeHoleHolder.anchor3 = (ImageView) b.b(view, R.id.iv_anchor3, "field 'anchor3'", ImageView.class);
        treeHoleHolder.anchor4 = (ImageView) b.b(view, R.id.iv_anchor4, "field 'anchor4'", ImageView.class);
        treeHoleHolder.contentLayout1 = (ConstraintLayout) b.b(view, R.id.content1, "field 'contentLayout1'", ConstraintLayout.class);
        treeHoleHolder.contentLayout2 = (ConstraintLayout) b.b(view, R.id.content2, "field 'contentLayout2'", ConstraintLayout.class);
        treeHoleHolder.contentLayout3 = (ConstraintLayout) b.b(view, R.id.content3, "field 'contentLayout3'", ConstraintLayout.class);
        treeHoleHolder.contentLayout4 = (ConstraintLayout) b.b(view, R.id.content4, "field 'contentLayout4'", ConstraintLayout.class);
        treeHoleHolder.textContent1 = (AppCompatTextView) b.b(view, R.id.content_text1, "field 'textContent1'", AppCompatTextView.class);
        treeHoleHolder.textContent2 = (AppCompatTextView) b.b(view, R.id.content_text2, "field 'textContent2'", AppCompatTextView.class);
        treeHoleHolder.textContent3 = (AppCompatTextView) b.b(view, R.id.content_text3, "field 'textContent3'", AppCompatTextView.class);
        treeHoleHolder.textContent4 = (AppCompatTextView) b.b(view, R.id.content_text4, "field 'textContent4'", AppCompatTextView.class);
        treeHoleHolder.textDur1 = (TextView) b.b(view, R.id.tv_dur1, "field 'textDur1'", TextView.class);
        treeHoleHolder.textDur2 = (TextView) b.b(view, R.id.tv_dur2, "field 'textDur2'", TextView.class);
        treeHoleHolder.textDur3 = (TextView) b.b(view, R.id.tv_dur3, "field 'textDur3'", TextView.class);
        treeHoleHolder.textDur4 = (TextView) b.b(view, R.id.tv_dur4, "field 'textDur4'", TextView.class);
        treeHoleHolder.textComment1 = (TextView) b.b(view, R.id.tv_comment1, "field 'textComment1'", TextView.class);
        treeHoleHolder.textComment2 = (TextView) b.b(view, R.id.tv_comment2, "field 'textComment2'", TextView.class);
        treeHoleHolder.textComment3 = (TextView) b.b(view, R.id.tv_comment3, "field 'textComment3'", TextView.class);
        treeHoleHolder.textComment4 = (TextView) b.b(view, R.id.tv_comment4, "field 'textComment4'", TextView.class);
        treeHoleHolder.hugView1 = (HollowFeedHugView) b.b(view, R.id.hollow_feed_hug_view1, "field 'hugView1'", HollowFeedHugView.class);
        treeHoleHolder.hugView2 = (HollowFeedHugView) b.b(view, R.id.hollow_feed_hug_view2, "field 'hugView2'", HollowFeedHugView.class);
        treeHoleHolder.hugView3 = (HollowFeedHugView) b.b(view, R.id.hollow_feed_hug_view3, "field 'hugView3'", HollowFeedHugView.class);
        treeHoleHolder.hugView4 = (HollowFeedHugView) b.b(view, R.id.hollow_feed_hug_view4, "field 'hugView4'", HollowFeedHugView.class);
        treeHoleHolder.audioBg1 = b.a(view, R.id.audio_bg1, "field 'audioBg1'");
        treeHoleHolder.audioBg2 = b.a(view, R.id.audio_bg2, "field 'audioBg2'");
        treeHoleHolder.audioBg3 = b.a(view, R.id.audio_bg3, "field 'audioBg3'");
        treeHoleHolder.audioBg4 = b.a(view, R.id.audio_bg4, "field 'audioBg4'");
        treeHoleHolder.playBtn1 = b.a(view, R.id.btn_play_audio1, "field 'playBtn1'");
        treeHoleHolder.playBtn2 = b.a(view, R.id.btn_play_audio2, "field 'playBtn2'");
        treeHoleHolder.playBtn3 = b.a(view, R.id.btn_play_audio3, "field 'playBtn3'");
        treeHoleHolder.playBtn4 = b.a(view, R.id.btn_play_audio4, "field 'playBtn4'");
        treeHoleHolder.audioIndicator1 = b.a(view, R.id.audio_indicator1, "field 'audioIndicator1'");
        treeHoleHolder.audioIndicator2 = b.a(view, R.id.audio_indicator2, "field 'audioIndicator2'");
        treeHoleHolder.audioIndicator3 = b.a(view, R.id.audio_indicator3, "field 'audioIndicator3'");
        treeHoleHolder.audioIndicator4 = b.a(view, R.id.audio_indicator4, "field 'audioIndicator4'");
    }

    @CallSuper
    public void a() {
        TreeHoleHolder treeHoleHolder = this.b;
        if (treeHoleHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        treeHoleHolder.treeDown1 = null;
        treeHoleHolder.treeDown2 = null;
        treeHoleHolder.treeDown3 = null;
        treeHoleHolder.treeDown4 = null;
        treeHoleHolder.treeDown5 = null;
        treeHoleHolder.treeUp1 = null;
        treeHoleHolder.treeUp2 = null;
        treeHoleHolder.treeUp3 = null;
        treeHoleHolder.treeUp4 = null;
        treeHoleHolder.treeUp5 = null;
        treeHoleHolder.role1 = null;
        treeHoleHolder.role2 = null;
        treeHoleHolder.role3 = null;
        treeHoleHolder.role4 = null;
        treeHoleHolder.parentView = null;
        treeHoleHolder.layout1 = null;
        treeHoleHolder.layout2 = null;
        treeHoleHolder.layout3 = null;
        treeHoleHolder.layout4 = null;
        treeHoleHolder.guideline6 = null;
        treeHoleHolder.guideline7 = null;
        treeHoleHolder.guideline8 = null;
        treeHoleHolder.anchor1 = null;
        treeHoleHolder.anchor2 = null;
        treeHoleHolder.anchor3 = null;
        treeHoleHolder.anchor4 = null;
        treeHoleHolder.contentLayout1 = null;
        treeHoleHolder.contentLayout2 = null;
        treeHoleHolder.contentLayout3 = null;
        treeHoleHolder.contentLayout4 = null;
        treeHoleHolder.textContent1 = null;
        treeHoleHolder.textContent2 = null;
        treeHoleHolder.textContent3 = null;
        treeHoleHolder.textContent4 = null;
        treeHoleHolder.textDur1 = null;
        treeHoleHolder.textDur2 = null;
        treeHoleHolder.textDur3 = null;
        treeHoleHolder.textDur4 = null;
        treeHoleHolder.textComment1 = null;
        treeHoleHolder.textComment2 = null;
        treeHoleHolder.textComment3 = null;
        treeHoleHolder.textComment4 = null;
        treeHoleHolder.hugView1 = null;
        treeHoleHolder.hugView2 = null;
        treeHoleHolder.hugView3 = null;
        treeHoleHolder.hugView4 = null;
        treeHoleHolder.audioBg1 = null;
        treeHoleHolder.audioBg2 = null;
        treeHoleHolder.audioBg3 = null;
        treeHoleHolder.audioBg4 = null;
        treeHoleHolder.playBtn1 = null;
        treeHoleHolder.playBtn2 = null;
        treeHoleHolder.playBtn3 = null;
        treeHoleHolder.playBtn4 = null;
        treeHoleHolder.audioIndicator1 = null;
        treeHoleHolder.audioIndicator2 = null;
        treeHoleHolder.audioIndicator3 = null;
        treeHoleHolder.audioIndicator4 = null;
    }
}
