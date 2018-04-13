package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.base.a;
import com.budejie.www.activity.video.k;
import com.budejie.www.g.b;
import com.budejie.www.h.c;
import com.budejie.www.http.i;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.j;
import com.umeng.analytics.MobclickAgent;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

@Deprecated
public class SensorBaseActivity extends FragmentActivity {
    protected static boolean portraitShow = false;
    public static int textSize;
    public static int themeState = 0;
    private a budejieSettings;
    private Activity instance;
    public boolean isrefresh;
    public int mTheme = 0;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        an.f((Activity) this);
        this.budejieSettings = ((BudejieApplication) getApplication()).g();
        themeState = ai.a(this);
        setTheme(c.a().a(themeState));
        this.instance = this;
        this.isrefresh = false;
        textSize = an.q(this.instance);
        changeThemeStyle();
        if (themeState == 0) {
            onChangeDefault();
        } else {
            onChangeNight();
        }
    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        onrefreshTheme();
        textSize = an.q(this.instance);
        i.a();
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public a getBudejieSettings() {
        if (this.budejieSettings == null) {
            this.budejieSettings = ((BudejieApplication) getApplication()).g();
        }
        return this.budejieSettings;
    }

    private void initportraitShow() {
        if (an.n(this.instance) && an.a(this.instance) && !"1".equals(an.b(this.instance))) {
            portraitShow = true;
        } else {
            portraitShow = false;
        }
    }

    public void changeCurrentTheme() {
        this.isrefresh = true;
        themeState = ai.a(this.instance);
        if (themeState == 0) {
            themeState = 1;
            ai.a(this.instance, themeState);
        } else {
            themeState = 0;
            ai.a(this.instance, themeState);
        }
        c.a().d();
    }

    public void onChangeNight() {
        j.k = R.drawable.myinfo_setting_night;
        j.a = R.drawable.top_navigation_bar_night;
        j.b = R.color.title_text_color_night;
        j.c = R.drawable.collect_left_btn_night;
        j.e = R.drawable.title_left_back_font_style;
        j.f = R.drawable.main_btn_night;
        j.g = R.drawable.title_left_back_font_style_night;
        j.h = R.drawable.title_left_back_font_style_red_night;
        j.i = R.drawable.down_triangle_night;
        j.n = R.drawable.refresh_button_night;
        j.o = R.drawable.publish_left_btn_night;
        j.p = R.drawable.my_coins_btn_night;
        j.q = R.drawable.suiji_btn_bg_set_night;
        j.m = R.color.new_main_background_color_night;
        j.s = R.color.new_listview_bg_color_night;
        j.t = R.color.post_state_layout_bg_color_black;
        j.u = R.drawable.content_bg_night;
        j.v = R.drawable.list_item_btn_night;
        j.w = R.color.item_title_name_color_night;
        j.x = R.color.item_content_text_color_night;
        j.y = R.drawable.list_item_commend_night_state;
        j.z = R.drawable.list_item_forward_night_state;
        j.A = R.drawable.message_list_bottom_btn_bg_night;
        j.B = R.color.mind_btn_text_color_night;
        j.C = R.color.mind_title_text_color_night;
        j.j = R.color.animation__text_color_night;
        j.D = R.drawable.single_title_night;
        j.E = R.drawable.divider_vertical_night;
        j.F = R.color.item_title_name_color_night;
        j.G = R.color.fun_item_user_color_night;
        j.H = R.color.vip_pay_name_text_color_night;
        j.I = R.raw.members_mark_black;
        j.d = R.color.more_layout_bg_color_night;
        j.J = R.drawable.center_bg_night;
        j.K = R.drawable.up_bg_night;
        j.L = R.drawable.down_bg_night;
        j.M = R.color.item_content_text_color_night;
        j.N = R.drawable.exit_btn_night_selector;
        j.O = R.color.account_text_color_night;
        j.P = R.drawable.edit_bg_night;
        j.Q = R.drawable.person_name_cancel_night;
        j.R = R.color.shenhe_remind_text_color_night;
        j.S = R.color.shenhe_layout_bg_color_night;
        j.T = R.drawable.btn_bg_night;
        j.U = R.drawable.shenhe_ding_bg_night;
        j.V = R.drawable.shenhe_cai_bg_night;
        j.W = R.drawable.shenhe_dorc_bg_night;
        j.Y = R.color.comment_layout_bg_color_night;
        j.Z = R.color.post_state_layout_bg_color_black;
        j.ab = R.drawable.commend_buttom_bg_night;
        j.aa = R.drawable.content_bg_night;
        j.ac = R.color.post_state_layout_bg_color_black;
        j.ad = R.drawable.divider_h_night;
        j.ae = R.drawable.comment_write_btn_bg_night;
        j.af = R.drawable.record_btn_bg_night;
        j.ag = R.drawable.cmt_pop_bg_night;
        j.X = R.drawable.commend_detail_top_forward_bg_night;
        j.l = R.color.comment_layout_bg_color_night;
        j.ah = R.drawable.write_commend_tongshi_bg_night;
        j.aj = R.color.comment_write_limit_text_color_night;
        j.ai = R.color.comment_write_layout_buttom_text_color_night;
        j.ak = R.color.comment_reply_buttom_buttom_bg_color_night;
        j.al = R.color.voice_commend_color_night;
        j.am = R.color.voice_commend_color_night;
        j.an = R.color.tougao_layout_bg_color_night;
        j.ao = R.color.tougao_content_hit;
        j.ap = R.color.tougao_content;
        j.aq = R.drawable.publishposts_pic_selector_night;
        j.ar = R.drawable.shenhe_status_night;
        j.as = R.drawable.tougao_item_bg_night;
        j.at = R.drawable.shenhe_ding_pic_night;
        j.au = R.drawable.shenhe_cai_pic_night;
        j.av = R.color.mytougao_state_text_color_night;
        j.ax = R.color.mynews_time_color_night;
        j.ay = R.color.mynews_contenttext_color_night;
        j.aw = R.color.mynews_titletext_color_night;
        j.az = R.color.mynews_comment_content_text_color_night;
        j.aA = R.drawable.bottom_bg_night;
        j.aC = R.drawable.bg_common_circularbutton_state_night;
        j.aD = R.color.transparent;
        j.aE = R.drawable.title_tab_essence_state_night;
        j.aF = R.drawable.title_tab_newissue_state_night;
        j.aG = R.drawable.title_tab_suiji_state_night;
        j.aH = R.drawable.title_tab_nearby_state_night;
        j.aI = R.drawable.title_tab_font_style_night;
        j.aJ = R.drawable.bg_record_state_normal_factory_night;
        j.aK = R.drawable.ic_mic_white;
        j.aL = R.color.background_dark;
        j.aM = R.drawable.list_item_right_more_night_state;
        j.aR = R.drawable.login_qqicon_night;
        j.aS = R.drawable.login_sina_icon_night;
        j.aT = R.drawable.login_tencent_icon_night;
        j.aN = R.drawable.login_bg_night;
        j.aO = R.drawable.login_bg_night;
        j.aP = R.drawable.login_bg_night;
        j.aQ = R.color.dark_gray;
        j.aU = R.drawable.login_phone_night;
        j.aV = R.color.sina_text_color;
        j.aW = R.color.tencent_text_color;
        j.aX = R.color.qq_text_color;
        j.aY = R.color.phone_text_color;
        j.aZ = R.drawable.myinfo_setting_night_selector;
        j.ba = R.drawable.myinfo_night_model_night;
        j.bl = R.drawable.select_label_edit_bg;
        j.bg = R.drawable.tv_select_bg_selector_night;
        j.bh = R.color.select_label_text_color_night;
        j.bj = R.drawable.tv_bg_selected_night;
        j.bk = R.drawable.tv_bg_normal_night;
        j.bi = R.color.label_text_color_night;
        j.bm = R.drawable.add_vote_add_selector_black;
        j.bb = R.attr.vpiTabPageIndicatorStyleNight;
        j.bc = R.drawable.f5_toast_night_bg;
        j.bd = R.color.head_toast_text_night;
        j.be = R.color.news_feed_text_color;
        j.bf = R.drawable.skip_recommend_selector_night;
        j.bn = R.drawable.rich_comment_selector_night;
        j.bo = R.drawable.bg_user_v_night;
        j.bp = R.drawable.bg_user_level_night;
        j.bq = R.drawable.btn_info_selector_night;
        j.br = R.drawable.sex_women_night;
        j.bs = R.drawable.sex_men_night;
        j.bt = R.drawable.ic_user_night_lv1;
        j.bu = R.drawable.ic_user_night_lv2;
        j.bv = R.drawable.ic_user_night_lv3;
        j.bw = R.drawable.ic_user_night_lv4;
        j.bx = R.drawable.ic_user_night_lv5;
        j.by = R.drawable.ic_user_night_lv6;
        j.bz = R.drawable.ic_user_night_lv7;
        j.bA = R.drawable.ic_user_night_lv8;
        j.bB = R.drawable.ic_user_night_lv9;
        j.bC = R.drawable.ic_user_night_lv10;
        j.bD = R.drawable.ic_user_night_lv11;
        j.bE = R.drawable.ic_user_night_lv12;
        j.bF = R.drawable.ic_user_night_lv13;
        j.bG = R.drawable.ic_user_night_lv14;
        j.bH = R.drawable.ic_user_night_lv15;
        j.bI = R.color.parse_tag_text_color_night;
        j.bJ = R.drawable.top_navigation_scroll_end_night;
        j.bK = R.drawable.top_navigation_scroll_left_night;
        j.bL = R.drawable.top_navigation_scroll_right_night;
        j.bM = R.color.post_hot_comment_reply_name_color_night;
        j.bN = R.color.post_hot_comment_reply_content_color_night;
        j.bO = R.drawable.main_bottom_special_offers_selector_night;
        if (this.isrefresh) {
            onrefreshTheme();
        }
    }

    public void onChangeDefault() {
        j.a = R.drawable.title_bar_bg;
        j.k = R.drawable.myinfo_setting_normal;
        j.b = R.color.title_text_color;
        j.c = R.drawable.collect_left_btn;
        j.e = R.drawable.title_left_back_font_style;
        j.f = R.drawable.main_btn;
        j.g = R.drawable.title_left_back_font_style;
        j.h = R.drawable.title_left_back_font_style_red;
        j.i = R.drawable.down_triangle;
        j.n = R.drawable.refresh_button;
        j.o = R.drawable.publish_left_btn;
        j.p = R.drawable.my_coins_btn_light;
        j.q = R.drawable.suiji_btn_bg_set;
        j.m = R.color.new_main_background_color;
        j.s = R.color.new_listview_bg_color;
        j.t = R.drawable.title_bg;
        j.u = R.drawable.content_bg;
        j.v = R.drawable.list_item_btn;
        j.w = R.color.item_title_name_color;
        j.x = R.color.item_content_text_color;
        j.y = R.drawable.list_item_commend_state;
        j.z = R.drawable.list_item_forward_state;
        j.A = R.drawable.message_list_bottom_btn_bg;
        j.B = R.color.mind_btn_text_color;
        j.C = R.color.mind_title_text_color;
        j.j = R.color.text_gray;
        j.D = R.drawable.single_title;
        j.E = R.drawable.divider_vertical;
        j.F = R.color.item_user_name_color;
        j.G = R.color.fun_item_user_color;
        j.H = R.color.vip_pay_name_text_color;
        j.I = R.raw.members_mark;
        j.d = R.color.more_layout_bg_color;
        j.J = R.drawable.center_bg;
        j.K = R.drawable.up_bg;
        j.L = R.drawable.down_bg;
        j.M = R.color.more_text_color;
        j.N = R.drawable.exit_btn_selector;
        j.O = R.color.account_text_color;
        j.P = R.drawable.edit_bg;
        j.Q = R.drawable.person_name_cancel;
        j.R = R.color.shenhe_remind_text_color;
        j.S = R.color.shenhe_layout_bg_color;
        j.T = R.drawable.btn_bg;
        j.U = R.drawable.shenhe_ding_bg;
        j.V = R.drawable.shenhe_cai_bg;
        j.W = R.drawable.shenhe_dorc_bg;
        j.Y = R.color.comment_layout_bg_color;
        j.Z = R.drawable.title_bg;
        j.aa = R.drawable.content_bg;
        j.ab = R.drawable.commend_buttom_bg;
        j.ac = R.drawable.title_bg;
        j.ae = R.drawable.comment_write_btn_bg;
        j.af = R.drawable.record_btn_bg;
        j.ag = R.drawable.cmt_pop_bg;
        j.X = R.drawable.commend_detail_top_forward_bg;
        j.l = R.color.list_item_bg;
        j.ah = R.drawable.write_commend_tongshi_bg;
        j.aj = R.color.comment_write_limit_text_color;
        j.ai = R.color.comment_write_layout_buttom_text_color;
        j.ak = R.color.comment_reply_buttom_buttom_bg_color;
        j.al = R.color.voice_commend_color;
        j.am = R.color.voice_commend_press_color_night;
        j.an = R.color.tougao_layout_bg_color;
        j.ao = R.color.tougao_content;
        j.ap = R.color.tougao_content_hit;
        j.aq = R.drawable.publishposts_pic_selector;
        j.ar = R.drawable.shenhe_status;
        j.as = R.drawable.tougao_item_bg;
        j.at = R.drawable.shenhe_ding_pic;
        j.au = R.drawable.shenhe_cai_pic;
        j.av = R.color.mytougao_state_text_color;
        j.ax = R.color.mynews_time_color;
        j.ay = R.color.mynews_contenttext_color;
        j.az = R.color.mynews_comment_content_text_color;
        j.aw = R.color.mynews_titletext_color;
        j.aA = R.drawable.bottom_bg;
        j.aC = R.drawable.bg_common_circularbutton_state;
        j.aD = R.drawable.title_tab_state;
        j.aE = R.drawable.title_tab_essence_state;
        j.aF = R.drawable.title_tab_newissue_state;
        j.aG = R.drawable.title_tab_suiji_state;
        j.aH = R.drawable.title_tab_nearby_state;
        j.aI = R.drawable.title_tab_font_style;
        j.aJ = R.drawable.bg_record_state_normal_factory;
        j.aK = R.drawable.ic_record_normal;
        j.aL = R.color.background_light;
        j.aM = R.drawable.list_item_right_more_state;
        j.aR = R.drawable.login_qqicon;
        j.aS = R.drawable.login_sina_icon;
        j.aT = R.drawable.login_tencent_icon;
        j.aN = R.drawable.login_qqbg;
        j.aO = R.drawable.login_sinabg;
        j.aP = R.drawable.login_tencentbg;
        j.aQ = R.color.white;
        j.aU = R.drawable.login_phone_n;
        j.aV = R.color.login_sinatext;
        j.aW = R.color.login_tencenttext;
        j.aX = R.color.login_qqtext;
        j.aY = R.color.login_phonetext;
        j.aZ = R.drawable.myinfo_setting_selector;
        j.ba = R.drawable.myinfo_night_model_normal;
        j.bl = R.drawable.select_label_edit_bg;
        j.bg = R.drawable.tv_select_bg_selector;
        j.bh = R.color.select_label_text_color;
        j.bj = R.drawable.tv_bg_selected;
        j.bk = R.drawable.tv_bg_normal;
        j.bi = R.color.label_text_color;
        j.bm = R.drawable.add_vote_add_selector;
        j.bb = R.attr.vpiTabPageIndicatorStyle;
        j.bc = R.drawable.f5_toast_bg;
        j.bd = R.color.toast_text;
        j.be = R.color.white;
        j.bf = R.drawable.skip_recommend_selector;
        j.bn = R.drawable.rich_comment_selector;
        j.bo = R.drawable.bg_user_v;
        j.bp = R.drawable.bg_user_level;
        j.bq = R.drawable.btn_info_selector;
        j.br = R.drawable.sex_women;
        j.bs = R.drawable.sex_men;
        j.bt = R.drawable.ic_user_lv1;
        j.bu = R.drawable.ic_user_lv2;
        j.bv = R.drawable.ic_user_lv3;
        j.bw = R.drawable.ic_user_lv4;
        j.bx = R.drawable.ic_user_lv5;
        j.by = R.drawable.ic_user_lv6;
        j.bz = R.drawable.ic_user_lv7;
        j.bA = R.drawable.ic_user_lv8;
        j.bB = R.drawable.ic_user_lv9;
        j.bC = R.drawable.ic_user_lv10;
        j.bD = R.drawable.ic_user_lv11;
        j.bE = R.drawable.ic_user_lv12;
        j.bF = R.drawable.ic_user_lv13;
        j.bG = R.drawable.ic_user_lv14;
        j.bH = R.drawable.ic_user_lv15;
        j.bI = R.color.parse_tag_text_color;
        j.bJ = R.drawable.top_navigation_scroll_end;
        j.bK = R.drawable.top_navigation_scroll_left;
        j.bL = R.drawable.top_navigation_scroll_right;
        j.bM = R.color.post_hot_comment_reply_name_color_light;
        j.bN = R.color.post_hot_comment_reply_content_color_light;
        j.bO = R.drawable.main_bottom_special_offers_selector;
        if (this.isrefresh) {
            onrefreshTheme();
        }
    }

    public void changeThemeStyle() {
        String p = an.p(this.instance);
        if (p.equals("deep_colour")) {
            j.aB = R.drawable.bottom_bg_deeptheme;
        } else if (p.equals("light_colour")) {
            j.aB = R.drawable.bottom_bg;
        }
    }

    public void onrefreshTheme() {
    }

    public void onRefreshTitleFontTheme(TextView textView, boolean z) {
        if (z) {
            textView.setCompoundDrawablesWithIntrinsicBounds(j.c, 0, 0, 0);
        }
        try {
            textView.setTextColor(ColorStateList.createFromXml(getResources(), getResources().getXml(j.g)));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 4 && k.a((Context) this).e == this && k.a((Context) this).f != null && k.a((Context) this).f.b(4)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void onRefreshTitleFontThemeRed(TextView textView, boolean z) {
        if (z) {
            textView.setCompoundDrawablesWithIntrinsicBounds(j.c, 0, 0, 0);
        }
        try {
            textView.setTextColor(ColorStateList.createFromXml(getResources(), getResources().getXml(j.h)));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        b.a((Activity) this);
    }
}
