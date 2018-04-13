package qsbk.app.utils;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.ticker.TickerView;

public class UIHelper {
    public static final int DAY = 0;
    public static final int NIGHT = 1;
    private static final int[] a = new int[]{R.drawable.found_ic_group, R.drawable.found_ic_nearby, R.drawable.found_random_door, R.drawable.found_ic_store, R.drawable.found_ic_game};
    private static final int[] b = new int[]{R.drawable.found_ic_group_night, R.drawable.found_ic_nearby_night, R.drawable.found_random_door_night, R.drawable.found_ic_store_night, R.drawable.found_ic_game_night};
    private static Boolean c = null;
    private static String d = null;
    private static DisplayMetrics e;
    public static int[] nameColors = new int[]{-9474193, -508121};
    public static int[] nameColorsForComment = new int[]{-4934476, -508121};
    public static int[] nameColorsNight = new int[]{-12171438, -508121};
    public static int[] nameColorsNightForComment = new int[]{-12629931, -508121};
    public static int[][] topicColors = new int[][]{new int[]{-13944741, -11829761}, new int[]{-689219, -1150796}, new int[]{-12069721, -13251685}, new int[]{-10120961, -10120961}, new int[]{-17899, -218354}, new int[]{-5917487, -7364416}};
    public static final int[] topicImageIds = new int[]{R.drawable.circle_topic_default_bg1, R.drawable.circle_topic_default_bg2, R.drawable.circle_topic_default_bg3, R.drawable.circle_topic_default_bg4, R.drawable.circle_topic_default_bg5};

    public static float sp2px(Context context, float f) {
        Resources system;
        if (context == null) {
            system = Resources.getSystem();
        } else {
            system = context.getResources();
        }
        return system.getDisplayMetrics().scaledDensity * f;
    }

    public static Drawable getDrawable(int i) {
        return QsbkApp.mContext.getResources().getDrawable(i);
    }

    public static int getColor(int i) {
        return QsbkApp.mContext.getResources().getColor(i);
    }

    public static ColorStateList getColorStateList(int i) {
        return QsbkApp.mContext.getResources().getColorStateList(i);
    }

    public static void init() {
        isNightTheme();
    }

    public static void setActivityFullscreen(Activity activity, boolean z) {
        LayoutParams attributes = activity.getWindow().getAttributes();
        if (z) {
            attributes.flags |= 1024;
        } else {
            attributes.flags &= -1025;
            if (VERSION.SDK_INT >= 19) {
                attributes.flags |= 67108864;
            }
        }
        activity.getWindow().setAttributes(attributes);
        activity.getWindow().addFlags(512);
    }

    public static boolean isNightTheme() {
        if (c != null) {
            return c.booleanValue();
        }
        try {
            d = FilePreferenceUtils.getPreferencesValue(UIHelper$Theme.THEME_ID);
            if (TextUtils.isEmpty(d) || "day".equalsIgnoreCase(d)) {
                c = Boolean.FALSE;
            } else {
                c = Boolean.TRUE;
            }
            return c.booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String toggleTheme() {
        if (d == null) {
            d = FilePreferenceUtils.getPreferencesValue(UIHelper$Theme.THEME_ID);
        }
        if (UIHelper$Theme.THEME_NIGHT.equalsIgnoreCase(d)) {
            d = "day";
            c = Boolean.FALSE;
        } else {
            d = UIHelper$Theme.THEME_NIGHT;
            c = Boolean.TRUE;
        }
        FilePreferenceUtils.setPreferencesValue(UIHelper$Theme.THEME_ID, d);
        return d;
    }

    public static String getTheme() {
        if (d == null) {
            d = FilePreferenceUtils.getPreferencesValue(UIHelper$Theme.THEME_ID);
        }
        return UIHelper$Theme.THEME_NIGHT.equalsIgnoreCase(d) ? UIHelper$Theme.THEME_NIGHT : "day";
    }

    public static void setTheme(String str) {
        d = str;
        FilePreferenceUtils.setPreferencesValue(UIHelper$Theme.THEME_ID, str);
        if ("day".equalsIgnoreCase(str)) {
            c = Boolean.FALSE;
        } else {
            c = Boolean.TRUE;
        }
    }

    public static int dip2px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int dip2px(Context context, double d) {
        return (int) ((((double) context.getResources().getDisplayMetrics().density) * d) + 0.5d);
    }

    public static int getDimensionPixelSize(Context context, int i) {
        return context.getResources().getDimensionPixelSize(i);
    }

    public static void showKeyboard(Context context) {
        ((InputMethodManager) context.getSystemService("input_method")).toggleSoftInput(2, 0);
    }

    public static void showKeyboard(Activity activity) {
        IBinder windowToken;
        if (activity != null) {
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus != null) {
                windowToken = currentFocus.getWindowToken();
                if (windowToken != null) {
                    ((InputMethodManager) activity.getSystemService("input_method")).toggleSoftInputFromWindow(windowToken, 2, 1);
                }
            }
        }
        windowToken = null;
        if (windowToken != null) {
            ((InputMethodManager) activity.getSystemService("input_method")).toggleSoftInputFromWindow(windowToken, 2, 1);
        }
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null) {
            hideKeyboard(activity, activity.getWindow());
        }
    }

    public static void hideKeyboard(Activity activity, Window window) {
        View decorView;
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        if (window != null) {
            decorView = window.getDecorView();
        } else {
            decorView = activity.getWindow().getCurrentFocus();
        }
        if (decorView != null) {
            inputMethodManager.hideSoftInputFromWindow(decorView.getWindowToken(), 0);
        }
    }

    public static int getQiushiCircleTabSelector() {
        return isNightTheme() ? R.drawable.ic_qiuyoucircle_selector_dark : R.drawable.ic_qiuyoucircle_selector;
    }

    public static int getNewMessageTips() {
        return isNightTheme() ? R.drawable.new_msg_tips_night : R.drawable.new_msg_tips;
    }

    public static int getTabDescriptionTextColor() {
        return isNightTheme() ? R.color.tab_description_text_dark : R.color.tab_description_text;
    }

    public static int getNewMessageTipsTextColor() {
        return isNightTheme() ? R.color.txt_color_dark_02 : R.color.white;
    }

    public static int getQiushiTabSelector() {
        return isNightTheme() ? R.drawable.ic_qiushi_selector_dark : R.drawable.ic_qiushi_selector;
    }

    public static int getMessageTabSelector() {
        return isNightTheme() ? R.drawable.ic_message_selector_dark : R.drawable.ic_message_selector;
    }

    public static int getLiveTabSelector() {
        return isNightTheme() ? R.drawable.ic_live_selector_dark : R.drawable.ic_live_selector;
    }

    public static int getMineTabSelector() {
        return isNightTheme() ? R.drawable.ic_mine_selector_dark : R.drawable.ic_mine_selector;
    }

    public static int getBottomTabBackgroundSelector() {
        return 0;
    }

    public static int getOneProfileEditSelector() {
        return isNightTheme() ? R.drawable.my_profile_edit_normal_dark : R.drawable.my_profile_edit_normal;
    }

    public static int getQiubaihuoSelector() {
        return isNightTheme() ? R.drawable.ic_qiubaihuo_dark : R.drawable.ic_qiubaihuo;
    }

    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        if (e == null) {
            e = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(e);
        }
        return e;
    }

    public static DisplayMetrics getDisPlayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context != null) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics;
    }

    public static int getScreenWidth(Context context) {
        return getDisPlayMetrics(context).widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return getDisPlayMetrics(context).heightPixels;
    }

    public static float getDensity(Context context) {
        return getDisPlayMetrics(context).density;
    }

    public static int getDensityDpi(Context context) {
        return getDisPlayMetrics(context).densityDpi;
    }

    public static int getProfileSelectedTabSelector() {
        return isNightTheme() ? R.drawable.profile_tab_active_item_selector_dark : R.drawable.profile_tab_active_item_selector;
    }

    public static int getProfileUnselectedTabSelector() {
        return isNightTheme() ? R.drawable.profile_tab_inactive_item_selector_dark : R.drawable.profile_tab_inactive_item_selector;
    }

    public static int getDefaultImageTileBackground() {
        return isNightTheme() ? R.drawable.bg_dark_tile : R.drawable.bg_light_tile;
    }

    public static int getDefaultShareImageTileBackground() {
        return R.drawable.share_default_icon;
    }

    public static int getDefaultAdImageTileBackground() {
        return isNightTheme() ? R.drawable.bg_dark_ad : R.drawable.bg_light_ad;
    }

    public static int getUnloginAvater() {
        return isNightTheme() ? R.drawable.unlogin_night_selector : R.drawable.unlogin_day_selector;
    }

    public static int getDefaultAvatar() {
        return isNightTheme() ? R.drawable.default_users_avatar_night : R.drawable.default_users_avatar;
    }

    public static int getDefaultAvatarIconInOverflow() {
        return isNightTheme() ? R.drawable.ic_default_avatar_dark : R.drawable.ic_default_avatar;
    }

    public static int getDotNormal() {
        return isNightTheme() ? R.drawable.dot_normal_dark : R.drawable.dot_normal;
    }

    public static int getDotSelected() {
        return isNightTheme() ? R.drawable.dot_selected_dark : R.drawable.dot_selected;
    }

    public static int[] getFoundIcResource() {
        return isNightTheme() ? b : a;
    }

    public static int getMale() {
        return isNightTheme() ? R.drawable.one_profile_male_left_dark : R.drawable.one_profile_male_left;
    }

    public static int getFemale() {
        return isNightTheme() ? R.drawable.one_profile_female_left_dark : R.drawable.one_profile_female_left;
    }

    public static int getMaleBackground() {
        return isNightTheme() ? 0 : R.drawable.one_profile_gender_male;
    }

    public static int getFemaleBackground() {
        return isNightTheme() ? 0 : R.drawable.one_profile_gender_female;
    }

    public static int getSexTexColor(Resources resources, String str) {
        if (isNightTheme()) {
            return "M".equalsIgnoreCase(str) ? resources.getColor(R.color.male_dark) : resources.getColor(R.color.female_dark);
        } else {
            return resources.getColor(R.color.g_txt_big);
        }
    }

    public static int getSelectedTabTextColor() {
        return isNightTheme() ? -4359140 : -17899;
    }

    public static int getNewSelectedTabTextColor() {
        return isNightTheme() ? -4359140 : -17899;
    }

    public static ForegroundColorSpan getSupportAndCommentTextColor() {
        if (isNightTheme()) {
            return new ForegroundColorSpan(Color.parseColor("#6a6c7e"));
        }
        return new ForegroundColorSpan(Color.parseColor("#63625e"));
    }

    public static String getDot() {
        if (SystemBarTintManager.sIsMiuiV6) {
            return "·";
        }
        return " · ";
    }

    public static void setSupportAndCommentText(TextView textView, int i, int i2, int i3) {
        String format = String.format("好笑 %1$s", new Object[]{Integer.valueOf(i)});
        String str = "";
        if (i2 > 0) {
            str = String.format(getDot() + "评论 %1$s", new Object[]{Integer.valueOf(i2)});
        }
        String str2 = "";
        if (i3 > 0) {
            str2 = String.format(getDot() + "分享 %1$s", new Object[]{Integer.valueOf(i3)});
        }
        textView.setText(format + str + str2);
    }

    public static void setSupportAndCommentText(TickerView tickerView, TextView textView, int i, int i2, int i3, boolean z) {
        String str = "";
        if (i2 > 0) {
            str = String.format(getDot() + "评论 %1$s", new Object[]{Integer.valueOf(i2)});
        }
        String str2 = "";
        if (i3 > 0) {
            str2 = String.format(getDot() + "分享 %1$s", new Object[]{Integer.valueOf(i3)});
        }
        if (textView != null) {
            textView.setText(str + str2);
        }
        if (i != 0 && tickerView != null) {
            tickerView.setCharacterList(getDefaultNumberList(true));
            tickerView.setTypeface(textView != null ? textView.getTypeface() : Typeface.SANS_SERIF);
            tickerView.setAnimationDuration(a());
            tickerView.setText("" + i, z);
        } else if (tickerView != null) {
            tickerView.setCharacterList(getDefaultNumberList(true));
            tickerView.setText("0", false);
        }
    }

    public static void setSupportAndCommentTextHighlight(TextView textView, int i, int i2, int i3, int i4) {
        setSupportAndCommentText(textView, i2, i3, i4);
    }

    public static void setSupportAndCommentTextHighlight(TickerView tickerView, TextView textView, int i, int i2, int i3, boolean z) {
        setSupportAndCommentText(tickerView, textView, i, i2, i3, z);
    }

    public static int getSendStatusRes(int i) {
        if (i == 5) {
            if (isNightTheme()) {
                return R.drawable.im_ic_read_night;
            }
            return R.drawable.im_ic_read;
        } else if (i == 1 || i == 3) {
            return isNightTheme() ? R.drawable.im_ic_sending_night : R.drawable.im_ic_sending;
        } else {
            if (i == 2 || i == 4) {
                return isNightTheme() ? R.drawable.im_ic_send_night : R.drawable.im_ic_send;
            } else {
                if (i == 6) {
                    return isNightTheme() ? R.drawable.im_ic_draft_night : R.drawable.im_ic_draft;
                } else {
                    return 0;
                }
            }
        }
    }

    public static int getSubmitMenuIcon() {
        return isNightTheme() ? R.drawable.submit_night : R.drawable.submit;
    }

    public static int getAddChatMenuIcon() {
        return isNightTheme() ? R.drawable.ic_add_chat_dark : R.drawable.ic_add_chat;
    }

    public static int getExamineMenuIcon() {
        return isNightTheme() ? R.drawable.examine_night : R.drawable.examine;
    }

    public static int getNewSubmitMenuIcon() {
        return isNightTheme() ? R.drawable.group_create_night : R.drawable.group_create;
    }

    public static int getEventSubmitMenuIcon() {
        return isNightTheme() ? R.drawable.group_create_night_event : R.drawable.group_create_event;
    }

    public static int getLiveRankingMenuIcon() {
        return isNightTheme() ? R.drawable.live_ranking_night : R.drawable.live_ranking;
    }

    public static int getNewSubmitMenuVideoIcon() {
        return isNightTheme() ? R.drawable.ic_video_night : R.drawable.ic_video;
    }

    public static int getEventSubmitMenuVideoIcon() {
        return isNightTheme() ? R.drawable.ic_video_night_event : R.drawable.ic_video_event;
    }

    public static int getNewExamineMenuIcon() {
        return isNightTheme() ? R.drawable.ic_audit_dark : R.drawable.ic_audit;
    }

    public static int getUnloginMenuIcon() {
        return isNightTheme() ? R.drawable.unlogin_night_normal : R.drawable.meizu_unlogin;
    }

    public static int getFakeOverflowMenuIcon() {
        return isNightTheme() ? R.drawable.actionbar_over_flow_menu_dark : R.drawable.actionbar_over_flow_menu;
    }

    public static int getManageMyQiushiIcon() {
        return isNightTheme() ? R.drawable.ic_manage_my_qiushi_dark : R.drawable.ic_manage_my_qiushi;
    }

    public static int getMyMedalIcon() {
        return isNightTheme() ? R.drawable.medal_night : R.drawable.medal;
    }

    public static int getMyCollectIcon() {
        return isNightTheme() ? R.drawable.my_collect_night : R.drawable.my_collect;
    }

    public static int getMyPaticpateIcon() {
        return isNightTheme() ? R.drawable.my_particpate_night : R.drawable.my_particpate;
    }

    public static int getMyDynamicIcon() {
        return isNightTheme() ? R.drawable.my_dynamic_night : R.drawable.my_dynamic;
    }

    public static int getQiuYouIcon() {
        return isNightTheme() ? R.drawable.qiuyou_dark : R.drawable.qiuyou;
    }

    public static int getAddQiuYouIcon() {
        return isNightTheme() ? R.drawable.ic_add_qiuyou_dark : R.drawable.ic_add_qiuyou;
    }

    public static int getSettingIcon() {
        return isNightTheme() ? R.drawable.ic_setting_dark : R.drawable.ic_setting;
    }

    public static int getFeedBackIcon() {
        return isNightTheme() ? R.drawable.ic_feedback_dark : R.drawable.ic_feedback;
    }

    public static int getThemeModeIcon() {
        return isNightTheme() ? R.drawable.ic_theme_mode_dark : R.drawable.ic_theme_mode;
    }

    public static int getSilentSoundModeOpenIcon() {
        return isNightTheme() ? R.drawable.ic_sound_mode_open_dark : R.drawable.ic_sound_mode_open;
    }

    public static int getSilentSoundModeCloseIcon() {
        return isNightTheme() ? R.drawable.ic_sound_mode_close_dark : R.drawable.ic_sound_mode_close;
    }

    public static int getWalletIcon() {
        return isNightTheme() ? R.drawable.ic_wallet_profile_night : R.drawable.ic_wallet_profile;
    }

    public static int getSmallBigCover() {
        return isNightTheme() ? R.drawable.info_complete_default_big_cover_night : R.drawable.info_complete_default_big_cover;
    }

    public static int getInfoEditable() {
        return isNightTheme() ? R.drawable.info_editable_night : R.drawable.info_editable;
    }

    public static int getInfoLocked() {
        return isNightTheme() ? R.drawable.info_locked_night : R.drawable.info_locked;
    }

    public static int getInfoCountdown() {
        return isNightTheme() ? R.drawable.info_countdown_night : R.drawable.info_countdown;
    }

    public static int getIconRssHot() {
        return isNightTheme() ? R.drawable.ic_rss_hot_night : R.drawable.ic_rss_hot;
    }

    public static int getIconRssNearby() {
        return isNightTheme() ? R.drawable.ic_rss_nearby_night : R.drawable.ic_rss_nearby;
    }

    public static int getIconRssFaned() {
        return isNightTheme() ? R.drawable.ic_rss_faned_night : R.drawable.ic_rss_faned;
    }

    public static int getIconRssAnonymousUser() {
        return isNightTheme() ? R.drawable.default_anonymous_users_avatar_night : R.drawable.default_anonymous_users_avatar;
    }

    public static void setIndeterminateProgressBarPadding(Activity activity, int i, int i2, int i3, int i4) {
        try {
            View findViewById = activity.findViewById(Resources.getSystem().getIdentifier("progress_circular", "id", "android"));
            if (findViewById != null && (findViewById instanceof ProgressBar)) {
                findViewById.setPadding(i, i2, i3, i4);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void sendAppCrashReport(Context context, String str) {
        Builder builder = new Builder(context);
        builder.setIcon(17301659);
        builder.setTitle(R.string.app_error);
        builder.setMessage(R.string.app_error_message);
        builder.setPositiveButton(R.string.submit_report, new bf(str, context));
        builder.setNegativeButton(R.string.sure, new bg(context));
        builder.show();
    }

    public static int getSendEmotionResource() {
        return isNightTheme() ? R.drawable.im_ic_send_emotion_dark : R.drawable.im_ic_send_emotion;
    }

    public static int getSendTextResource() {
        return isNightTheme() ? R.drawable.im_ic_send_text_dark : R.drawable.im_ic_send_text;
    }

    public static int getUserRemind() {
        return isNightTheme() ? R.drawable.manage_qiushi_info_dark : R.drawable.manage_qiushi_info_light;
    }

    public static int getChatImageDefault() {
        return isNightTheme() ? R.drawable.im_chat_image_default_dark : R.drawable.im_chat_image_default;
    }

    public static int getChatImageFail() {
        return isNightTheme() ? R.drawable.im_chat_image_fail_dark : R.drawable.im_chat_image_fail;
    }

    public static int getActionBarQiushiIC() {
        return isNightTheme() ? R.drawable.ic_ab_qiushi_dark : R.drawable.ic_ab_qiushi;
    }

    public static int getPublishLocationDefaultBackground() {
        return isNightTheme() ? R.drawable.publish_location_bg_default_night : R.drawable.publish_location_bg_default;
    }

    public static int getPublishLocationSuccessBackground() {
        return isNightTheme() ? R.drawable.publish_location_bg_success_night : R.drawable.publish_location_bg_success;
    }

    public static int getCircleVoteLeftOn() {
        return isNightTheme() ? R.drawable.qiuyoucircle_vote_on_night : R.drawable.qiuyoucircle_vote_on;
    }

    public static int getCircleVoteOnTextColor() {
        return isNightTheme() ? Color.parseColor("#1e1e23") : Color.parseColor("#ffffffff");
    }

    public static int getCircleVoteTextColor() {
        return isNightTheme() ? Color.parseColor("#6a6c7e") : Color.parseColor("#ff969696");
    }

    public static int getCircleVoteRightOn() {
        return isNightTheme() ? R.drawable.qiuyoucircle_vote_on_night : R.drawable.qiuyoucircle_vote_on;
    }

    public static int getCircleVoteVs() {
        return isNightTheme() ? R.drawable.qiuyoucircle_vote_vs_night : R.drawable.qiuyoucircle_vote_vs;
    }

    public static int getCircleVoteVsOn() {
        return isNightTheme() ? R.drawable.qiuyoucircle_vote_vs_on_night : R.drawable.qiuyoucircle_vote_vs_on;
    }

    public static int getTopicBgColor(int i) {
        int i2;
        if (isNightTheme()) {
            i2 = 0;
        } else {
            i2 = i;
        }
        if (i2 < 0 || i2 >= 6) {
            i2 = 5;
        }
        return topicColors[i2][0];
    }

    public static int getTopicBtnColor(int i) {
        int i2;
        if (isNightTheme()) {
            i2 = 0;
        } else {
            i2 = i;
        }
        if (i2 < 0 || i2 >= 6) {
            i2 = 5;
        }
        return topicColors[i2][1];
    }

    public static int getNameColor(int i) {
        return (i < 0 || i >= nameColors.length) ? isNightTheme() ? nameColorsNight[0] : nameColors[0] : isNightTheme() ? nameColorsNight[i] : nameColors[i];
    }

    public static int getNameColorForComment(int i) {
        return (i < 0 || i >= nameColorsForComment.length) ? isNightTheme() ? nameColorsNightForComment[0] : nameColorsForComment[0] : isNightTheme() ? nameColorsNightForComment[i] : nameColorsForComment[i];
    }

    public static int getFailImg() {
        return isNightTheme() ? R.drawable.fail_img_night : R.drawable.fail_img;
    }

    public static int getEmptyImg() {
        return isNightTheme() ? R.drawable.empty_list_icon_dark : R.drawable.empty_list_icon;
    }

    public static int getCommentEmptyImg() {
        return R.drawable.comment_empty_image;
    }

    public static int getTopicLinkColor() {
        return isNightTheme() ? -11504994 : -9922091;
    }

    public static int getTopicDefaultBg(int i) {
        if (i < 0) {
            i = (int) (Math.random() * ((double) topicImageIds.length));
        }
        return topicImageIds[i];
    }

    public static int getTopicAnonymous() {
        return isNightTheme() ? R.drawable.ic_topic_anonymous_night : R.drawable.ic_topic_anonymous;
    }

    public static int getShare2IMIcon() {
        return isNightTheme() ? R.drawable.qiushi_to_im_icon_other_night : R.drawable.qiushi_to_im_icon_other;
    }

    public static int getAttrColor(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.data;
    }

    public static void imageViewFilter(ImageView imageView) {
        if (imageView != null) {
            if (isNightTheme()) {
                imageView.setColorFilter(-7829368, Mode.MULTIPLY);
            } else {
                imageView.setColorFilter(null);
            }
        }
    }

    public static char[] getDefaultNumberList(boolean z) {
        if (z) {
            return "9876543210\u0000".toCharArray();
        }
        return "\u00000123456789".toCharArray();
    }

    private static long a() {
        if (QsbkApp.mContext != null) {
            return (long) QsbkApp.mContext.getResources().getInteger(R.integer.vote_duration);
        }
        return 400;
    }

    public static Rect getRectOnScreen(View view) {
        if (view == null) {
            return null;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new Rect(iArr[0], iArr[1], iArr[0] + view.getWidth(), iArr[1] + view.getHeight());
    }

    public static Rect getViewVisibleRect(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return rect;
    }

    public static boolean hasSoftNavigationBar(Activity activity) {
        boolean z = true;
        if (VERSION.SDK_INT >= 17) {
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            int i = displayMetrics.heightPixels;
            int i2 = displayMetrics.widthPixels;
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics2);
            int i3 = displayMetrics2.heightPixels;
            if (i2 - displayMetrics2.widthPixels > 0 || i - i3 > 0) {
                return true;
            }
            return false;
        }
        boolean hasPermanentMenuKey = ViewConfiguration.get(activity).hasPermanentMenuKey();
        boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        if (hasPermanentMenuKey || deviceHasKey) {
            z = false;
        }
        return z;
    }
}
