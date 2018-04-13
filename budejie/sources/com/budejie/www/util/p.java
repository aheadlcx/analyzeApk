package com.budejie.www.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.view.InflateException;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.v6.sixrooms.socket.common.SocketUtil;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.MoreActivity;
import com.budejie.www.activity.TougaoActivity;
import com.budejie.www.activity.clip.ClipPictureActivity;
import com.budejie.www.activity.detail.PostDetailActivity;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.label.h;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.view.LayoutEx;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.ReportItem;
import com.budejie.www.bean.UpdateResult;
import com.budejie.www.http.f;
import com.elves.update.d;
import com.facebook.common.util.UriUtil;
import com.google.gson.Gson;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class p {
    public static String a;
    private static LayoutInflater b;

    public interface c {
        void a(String str);
    }

    public interface a {
        void a(int i, Dialog dialog);
    }

    private static class b implements OnClickListener {
        a a;
        Dialog b;

        public b(a aVar, Dialog dialog) {
            this.a = aVar;
            this.b = dialog;
        }

        public void onClick(View view) {
            if (this.a != null) {
                int id = view.getId();
                if (id == 111 || id == 222) {
                    this.a.a(1, this.b);
                } else if (id == 112 || id == 223) {
                    this.a.a(2, this.b);
                } else if (id == 114 || id == 225) {
                    this.a.a(3, this.b);
                } else if (id == 115 || id == 226) {
                    this.a.a(4, this.b);
                } else if (id == 113 || id == 224) {
                    this.a.a(6, this.b);
                } else if (id == R$styleable.Theme_Custom_add_music_album_btn || id == R$styleable.Theme_Custom_choose_contact_item_search_text_color) {
                    this.a.a(7, this.b);
                } else if (id == R$styleable.Theme_Custom_hot_comment_prompt_text_color_bg || id == R$styleable.Theme_Custom_hot_comment_prompt_text_color) {
                    this.a.a(12, this.b);
                } else if (id == 117 || id == R$styleable.Theme_Custom_top_navigation_bar_bg_color) {
                    this.a.a(8, this.b);
                } else if (id == R$styleable.Theme_Custom_choose_contact_item_divider_color || id == R$styleable.Theme_Custom_choose_contact_item_name_text_color) {
                    this.a.a(9, this.b);
                } else if (id == R$styleable.Theme_Custom_choose_contact_item_arrow_icon || id == R$styleable.Theme_Custom_label_name_layout_bg) {
                    this.a.a(10, this.b);
                } else if (id == R$styleable.Theme_Custom_label_add_icon || id == R$styleable.Theme_Custom_tougao_label_name_color) {
                    this.a.a(11, this.b);
                } else if (id == 5345 || id == 5345) {
                    this.a.a(14, this.b);
                } else if (id == R$styleable.Theme_Custom_shape_cmt_like1_bg || id == R$styleable.Theme_Custom_shape_cmt_like2_bg) {
                    this.a.a(15, this.b);
                } else if (id == R$styleable.Theme_Custom_shape_cmt_like3_bg || id == R$styleable.Theme_Custom_shape_cmt_like4_bg) {
                    this.a.a(16, this.b);
                } else if (id == R$styleable.Theme_Custom_top_navigation_middle || id == R$styleable.Theme_Custom_top_refresh_btn) {
                    this.a.a(17, this.b);
                } else if (id == R$styleable.Theme_Custom_top_label_btn_bg || id == R$styleable.Theme_Custom_top_games_btn_bg) {
                    this.a.a(18, this.b);
                } else if (id == R$styleable.Theme_Custom_top_audit_btn_bg || id == R$styleable.Theme_Custom_top_suiji_btn_bg) {
                    this.a.a(19, this.b);
                } else if (id == R$styleable.Theme_Custom_top_suiji_btn_bg_2 || id == R$styleable.Theme_Custom_gray_click_state) {
                    this.a.a(20, this.b);
                }
            }
        }
    }

    public void a(final Context context, String str, final Handler handler) {
        if (TextUtils.isEmpty(str)) {
            b(context);
            return;
        }
        final UpdateResult updateResult = (UpdateResult) new Gson().fromJson(str, UpdateResult.class);
        if (updateResult == null || !updateResult.isAvailable()) {
            b(context);
            return;
        }
        Builder builder = new Builder(context, 5);
        builder.setTitle(updateResult.body.dialog_title);
        builder.setMessage(updateResult.body.dialog_remark);
        CharSequence charSequence = updateResult.body.first_btn;
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = "立即升级";
        }
        builder.setPositiveButton(charSequence, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ p d;

            public void onClick(DialogInterface dialogInterface, int i) {
                if (updateResult.body.status == 3) {
                    try {
                        Field declaredField = dialogInterface.getClass().getSuperclass().getDeclaredField("mShowing");
                        declaredField.setAccessible(true);
                        declaredField.set(dialogInterface, Boolean.valueOf(false));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (d.a()) {
                    com.budejie.www.download.c.a().a(updateResult.body.download_url, context);
                } else {
                    handler.sendEmptyMessage(922);
                }
            }
        });
        if (updateResult.body.status == 3) {
            builder.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ p b;

                public void onCancel(DialogInterface dialogInterface) {
                    handler.sendEmptyMessage(SocketUtil.TYPEID_811);
                }
            });
            builder.setCancelable(false);
            builder.show();
        } else if (updateResult.body.status != 1 && updateResult.body.status != 2) {
            b(context);
        } else if (updateResult.body.version_code != ai.w(context)) {
            charSequence = updateResult.body.second_btn;
            CharSequence charSequence2 = updateResult.body.third_btn;
            if (!TextUtils.isEmpty(charSequence)) {
                builder.setNegativeButton(charSequence, null);
            }
            if (!TextUtils.isEmpty(charSequence2)) {
                builder.setNeutralButton(updateResult.body.third_btn, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ p c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        ai.e(context, updateResult.body.version_code);
                    }
                });
            }
            builder.show();
        } else {
            b(context);
        }
    }

    private void b(Context context) {
        if (context instanceof MoreActivity) {
            an.a((MoreActivity) context, context.getString(R.string.noupdate), -1).show();
        }
    }

    @SuppressLint({"NewApi"})
    private static LinearLayout a(Context context, int i, int i2, int i3, int i4, OnClickListener onClickListener) {
        if (b == null) {
            b = LayoutInflater.from(context.getApplicationContext());
        }
        View inflate = b.inflate(R.layout.share_dialog_item_view, null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.item_layout);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.share_item_imageView);
        TextView textView = (TextView) inflate.findViewById(R.id.share_item_textView);
        imageView.setImageResource(i2);
        textView.setText(i3);
        imageView.setId(i);
        imageView.setOnClickListener(onClickListener);
        linearLayout.setId(i4);
        linearLayout.setOnClickListener(onClickListener);
        return linearLayout;
    }

    public static Dialog a(Bundle bundle, Context context, boolean z, boolean z2, SharedPreferences sharedPreferences, a aVar) {
        return a(bundle, context, z, z2, sharedPreferences, aVar, true);
    }

    public static Dialog a(Bundle bundle, Context context, boolean z, boolean z2, SharedPreferences sharedPreferences, a aVar, boolean z3, boolean z4) {
        return a(bundle, context, z, z2, sharedPreferences, aVar, z3, z4, true);
    }

    public static Dialog a(Bundle bundle, Context context, boolean z, boolean z2, SharedPreferences sharedPreferences, a aVar, boolean z3, boolean z4, boolean z5) {
        final Dialog dialog = new Dialog(context, R.style.custom_dlg);
        try {
            ListItemObject listItemObject;
            int i;
            View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.alert_test, null);
            if (z5) {
                TextView textView = (TextView) inflate.findViewById(R.id.cancel_textView);
                textView.setVisibility(0);
                textView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            } else {
                ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_forward_close);
                imageView.setVisibility(0);
                imageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
            LayoutEx layoutEx = (LayoutEx) inflate.findViewById(R.id.dialogContainer);
            OnClickListener bVar = new b(aVar, dialog);
            View a = a(context, 117, com.budejie.www.h.c.a().b(R.attr.share_qq_theme), (int) R.string.qq, (int) R$styleable.Theme_Custom_top_navigation_bar_bg_color, bVar);
            View a2 = a(context, 111, com.budejie.www.h.c.a().b(R.attr.share_sina_theme), (int) R.string.sinaweibo, 222, bVar);
            View a3 = a(context, 113, com.budejie.www.h.c.a().b(R.attr.share_qzone_theme), (int) R.string.qzone, 224, bVar);
            if (z) {
                layoutEx.addView(a(context, 114, com.budejie.www.h.c.a().b(R.attr.share_wechat_theme), (int) R.string.wxfriend, 225, bVar));
                if (z2) {
                    layoutEx.addView(a(context, 115, com.budejie.www.h.c.a().b(R.attr.share_circlefriend_theme), (int) R.string.wxgroup, 226, bVar));
                }
            }
            if (!sharedPreferences.getBoolean("isRecommend", false)) {
                layoutEx.addView(a);
            }
            if (z4) {
                z3 = false;
            } else {
                if (!sharedPreferences.getBoolean("isRecommend", false)) {
                    layoutEx.addView(a3);
                }
                if (!sharedPreferences.getBoolean("isRecommend", false)) {
                    layoutEx.addView(a2);
                }
                if (an.G(context)) {
                    layoutEx.addView(a(context, (int) R$styleable.Theme_Custom_add_music_album_btn, com.budejie.www.h.c.a().b(R.attr.share_message_theme), (int) R.string.sms, (int) R$styleable.Theme_Custom_choose_contact_item_search_text_color, bVar));
                }
            }
            if (bundle != null) {
                listItemObject = (ListItemObject) bundle.getSerializable("data");
            } else {
                listItemObject = null;
            }
            if (listItemObject == null || !listItemObject.getType().equals("29")) {
                i = R.string.copy_to_clipboard_html;
            } else {
                i = R.string.copy_to_clipboard_text;
            }
            if (bundle != null) {
                layoutEx.addView(a(context, (int) R$styleable.Theme_Custom_hot_comment_prompt_text_color_bg, com.budejie.www.h.c.a().b(R.attr.share_copy_theme), i, (int) R$styleable.Theme_Custom_hot_comment_prompt_text_color, bVar));
            }
            if (listItemObject != null && listItemObject.getType().equals("41")) {
                layoutEx.addView(a(context, (int) R$styleable.Theme_Custom_shape_cmt_like1_bg, com.budejie.www.h.c.a().b(R.attr.share_download_theme), (int) R.string.download_video, (int) R$styleable.Theme_Custom_shape_cmt_like2_bg, bVar));
            }
            if (z3) {
                boolean z6 = false;
                Object obj = 1;
                if (bundle != null) {
                    if (bundle.getSerializable("data") == null) {
                        obj = null;
                    } else if (listItemObject != null) {
                        z6 = listItemObject.isCollect();
                    }
                }
                if (obj != null) {
                    View a4;
                    if (z6) {
                        a4 = a(context, (int) R$styleable.Theme_Custom_choose_contact_item_divider_color, com.budejie.www.h.c.a().b(R.attr.share_cancelcollect_theme), (int) R.string.cancel_collect_tips, (int) R$styleable.Theme_Custom_choose_contact_item_name_text_color, bVar);
                    } else {
                        a4 = a(context, (int) R$styleable.Theme_Custom_choose_contact_item_divider_color, com.budejie.www.h.c.a().b(R.attr.share_collect_theme), (int) R.string.more_collect_tips, (int) R$styleable.Theme_Custom_choose_contact_item_name_text_color, bVar);
                    }
                    layoutEx.addView(a4);
                    if (bundle != null && bundle.getBoolean("has_screen_shoot", false)) {
                        layoutEx.addView(a(context, (int) R$styleable.Theme_Custom_shape_cmt_like3_bg, com.budejie.www.h.c.a().b(R.attr.share_screenshot_theme), (int) R.string.screen_shoot, (int) R$styleable.Theme_Custom_shape_cmt_like4_bg, bVar));
                    }
                    if (an.v(context)) {
                        layoutEx.addView(a(context, (int) R$styleable.Theme_Custom_label_add_icon, com.budejie.www.h.c.a().b(R.attr.share_report_theme), (int) R.string.shenhe_delete, (int) R$styleable.Theme_Custom_tougao_label_name_color, bVar));
                    } else {
                        layoutEx.addView(a(context, (int) R$styleable.Theme_Custom_choose_contact_item_arrow_icon, com.budejie.www.h.c.a().b(R.attr.share_report_theme), (int) R.string.shenhe_report, (int) R$styleable.Theme_Custom_label_name_layout_bg, bVar));
                    }
                }
            }
            dialog.setContentView(inflate);
            Window window = dialog.getWindow();
            if (window != null) {
                window.addFlags(2);
                LayoutParams attributes = window.getAttributes();
                attributes.gravity = 80;
                attributes.height = -2;
                attributes.width = -1;
                attributes.windowAnimations = R.style.PopUpBottomAnimation;
                window.setAttributes(attributes);
            }
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
            return dialog;
        } catch (InflateException e) {
            return null;
        }
    }

    public static Dialog a(Context context, boolean z, boolean z2, SharedPreferences sharedPreferences, boolean z3, a aVar) {
        final Dialog dialog = new Dialog(context, R.style.custom_dlg);
        try {
            View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.huaxi_share_dialog_view, null);
            if (z3) {
                inflate.findViewById(R.id.container_layout).setBackgroundColor(context.getResources().getColor(R.color.share_dialog_night_background));
                ((TextView) inflate.findViewById(R.id.dialogTitle)).setTextColor(context.getResources().getColor(R.color.share_dialog_text_night_color));
                TextView textView = (TextView) inflate.findViewById(R.id.cancel_textView);
                textView.setBackgroundResource(R.drawable.share_dialog_cancel_text_bg_night_selector);
                textView.setTextColor(context.getResources().getColor(R.color.share_dialog_text_night_color));
            }
            ((TextView) inflate.findViewById(R.id.cancel_textView)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    dialog.cancel();
                }
            });
            LayoutEx layoutEx = (LayoutEx) inflate.findViewById(R.id.dialogContainer);
            OnClickListener bVar = new b(aVar, dialog);
            View a = a(context, 117, z3 ? R.drawable.share_qq_night_selector : R.drawable.share_qq_day_selector, (int) R.string.qq, (int) R$styleable.Theme_Custom_top_navigation_bar_bg_color, bVar);
            View a2 = a(context, 111, z3 ? R.drawable.share_sina_night_selector : R.drawable.share_sina_day_selector, (int) R.string.sinaweibo, 222, bVar);
            View a3 = a(context, 113, z3 ? R.drawable.share_qzone_night_selector : R.drawable.share_qzone_day_selector, (int) R.string.qzone, 224, bVar);
            if (z) {
                layoutEx.addView(a(context, 114, z3 ? R.drawable.share_wechat_night_selector : R.drawable.share_wechat_day_selector, (int) R.string.wxfriend, 225, bVar));
                if (z2) {
                    layoutEx.addView(a(context, 115, z3 ? R.drawable.share_circlefriend_night_selector : R.drawable.share_circlefriend_day_selector, (int) R.string.wxgroup, 226, bVar));
                }
            }
            if (!sharedPreferences.getBoolean("isRecommend", false)) {
                layoutEx.addView(a);
            }
            if (!sharedPreferences.getBoolean("isRecommend", false)) {
                layoutEx.addView(a3);
            }
            if (!sharedPreferences.getBoolean("isRecommend", false)) {
                layoutEx.addView(a2);
            }
            if (an.G(context)) {
                layoutEx.addView(a(context, (int) R$styleable.Theme_Custom_add_music_album_btn, z3 ? R.drawable.share_message_night_selector : R.drawable.share_message_day_selector, (int) R.string.sms, (int) R$styleable.Theme_Custom_choose_contact_item_search_text_color, bVar));
            }
            dialog.setContentView(inflate);
            Window window = dialog.getWindow();
            if (window != null) {
                window.addFlags(2);
                LayoutParams attributes = window.getAttributes();
                attributes.gravity = 80;
                attributes.height = -2;
                attributes.width = -1;
                attributes.windowAnimations = R.style.PopUpBottomAnimation;
                window.setAttributes(attributes);
            }
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
            return dialog;
        } catch (InflateException e) {
            return null;
        }
    }

    public static Dialog a(Bundle bundle, Context context, boolean z, boolean z2, SharedPreferences sharedPreferences, a aVar, boolean z3) {
        final Dialog dialog = new Dialog(context, R.style.custom_dlg);
        try {
            ListItemObject listItemObject;
            View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.alert_test_two, null);
            ((TextView) inflate.findViewById(R.id.cancel_textView)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    dialog.cancel();
                }
            });
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.dialogContainer);
            LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.dialogContainer_two);
            OnClickListener bVar = new b(aVar, dialog);
            View a = a(context, 117, com.budejie.www.h.c.a().b(R.attr.share_qq_theme), (int) R.string.qq, (int) R$styleable.Theme_Custom_top_navigation_bar_bg_color, bVar);
            View a2 = a(context, 111, com.budejie.www.h.c.a().b(R.attr.share_sina_theme), (int) R.string.sinaweibo, 222, bVar);
            View a3 = a(context, 113, com.budejie.www.h.c.a().b(R.attr.share_qzone_theme), (int) R.string.qzone, 224, bVar);
            if (z) {
                linearLayout.addView(a(context, 114, com.budejie.www.h.c.a().b(R.attr.share_wechat_theme), (int) R.string.wxfriend, 225, bVar));
                if (z2) {
                    linearLayout.addView(a(context, 115, com.budejie.www.h.c.a().b(R.attr.share_circlefriend_theme), (int) R.string.wxgroup, 226, bVar));
                }
            }
            if (!sharedPreferences.getBoolean("isRecommend", false)) {
                linearLayout.addView(a);
                linearLayout.addView(a3);
            }
            if (!sharedPreferences.getBoolean("isRecommend", false)) {
                linearLayout.addView(a2);
            }
            if (an.G(context)) {
                linearLayout.addView(a(context, (int) R$styleable.Theme_Custom_add_music_album_btn, com.budejie.www.h.c.a().b(R.attr.share_message_theme), (int) R.string.sms, (int) R$styleable.Theme_Custom_choose_contact_item_search_text_color, bVar));
            }
            if (bundle != null) {
                listItemObject = (ListItemObject) bundle.getSerializable("data");
            } else {
                listItemObject = null;
            }
            Object obj = (listItemObject == null || !listItemObject.getType().equals("29")) ? null : 1;
            int i = obj != null ? R.string.copy_to_clipboard_text : R.string.copy_to_clipboard_html;
            if (bundle != null) {
                linearLayout2.addView(a(context, (int) R$styleable.Theme_Custom_hot_comment_prompt_text_color_bg, com.budejie.www.h.c.a().b(R.attr.share_copy_theme), i, (int) R$styleable.Theme_Custom_hot_comment_prompt_text_color, bVar));
            }
            if (c(context)) {
                boolean a4 = h.a(listItemObject, CommonLabelActivity.g);
                boolean b = h.b(listItemObject, CommonLabelActivity.g);
                linearLayout2.addView(a(context, (int) R$styleable.Theme_Custom_top_label_btn_bg, com.budejie.www.h.c.a().b(a4 ? R.attr.share_cancel_essence_theme : R.attr.share_add_essence_theme), a4 ? R.string.label_cancel_add_essence_text : R.string.label_add_essence_text, (int) R$styleable.Theme_Custom_top_games_btn_bg, bVar));
                int b2 = com.budejie.www.h.c.a().b(b ? R.attr.share_cancel_totop_theme : R.attr.share_totop_theme);
                if (b) {
                    i = R.string.label_cancel_to_top_text;
                } else {
                    i = R.string.label_to_top_text;
                }
                linearLayout2.addView(a(context, (int) R$styleable.Theme_Custom_top_audit_btn_bg, b2, i, (int) R$styleable.Theme_Custom_top_suiji_btn_bg, bVar));
                linearLayout2.addView(a(context, (int) R$styleable.Theme_Custom_top_suiji_btn_bg_2, com.budejie.www.h.c.a().b(R.attr.share_delete_theme), (int) R.string.label_remove_post, (int) R$styleable.Theme_Custom_gray_click_state, bVar));
            }
            if (a(listItemObject)) {
                linearLayout2.addView(a(context, (int) R$styleable.Theme_Custom_shape_cmt_like1_bg, com.budejie.www.h.c.a().b(R.attr.share_download_theme), "41".equals(listItemObject.getType()) ? R.string.download_video : R.string.download_image, (int) R$styleable.Theme_Custom_shape_cmt_like2_bg, bVar));
            }
            if (z3) {
                boolean z4 = false;
                obj = 1;
                if (bundle != null) {
                    if (bundle.getSerializable("data") == null) {
                        obj = null;
                    } else if (listItemObject != null) {
                        z4 = listItemObject.isCollect();
                    }
                }
                if (obj != null) {
                    View a5;
                    if (z4) {
                        a5 = a(context, (int) R$styleable.Theme_Custom_choose_contact_item_divider_color, com.budejie.www.h.c.a().b(R.attr.share_cancelcollect_theme), (int) R.string.cancel_collect_tips, (int) R$styleable.Theme_Custom_choose_contact_item_name_text_color, bVar);
                    } else {
                        a5 = a(context, (int) R$styleable.Theme_Custom_choose_contact_item_divider_color, com.budejie.www.h.c.a().b(R.attr.share_collect_theme), (int) R.string.more_collect_tips, (int) R$styleable.Theme_Custom_choose_contact_item_name_text_color, bVar);
                    }
                    linearLayout2.addView(a5);
                    obj = (listItemObject != null && "41".equals(listItemObject.getType()) && com.budejie.www.wallpaper.b.b.b(context, listItemObject)) ? 1 : null;
                    if (!(obj == null || k.a(context).v())) {
                        linearLayout2.addView(a(context, (int) R$styleable.Theme_Custom_top_navigation_middle, com.budejie.www.h.c.a().b(R.attr.share_wall_paper_set_theme), (int) R.string.live_wall_paper_text, (int) R$styleable.Theme_Custom_top_refresh_btn, bVar));
                    }
                    if (bundle != null) {
                        boolean z5 = bundle.getBoolean("has_screen_shoot", false);
                        z4 = k.a(context).v();
                        if (z5 && !z4) {
                            linearLayout2.addView(a(context, (int) R$styleable.Theme_Custom_shape_cmt_like3_bg, com.budejie.www.h.c.a().b(R.attr.share_screenshot_theme), (int) R.string.screen_shoot, (int) R$styleable.Theme_Custom_shape_cmt_like4_bg, bVar));
                        }
                    }
                    if (an.v(context)) {
                        linearLayout2.addView(a(context, (int) R$styleable.Theme_Custom_label_add_icon, com.budejie.www.h.c.a().b(R.attr.share_report_theme), (int) R.string.shenhe_delete, (int) R$styleable.Theme_Custom_tougao_label_name_color, bVar));
                    } else {
                        linearLayout2.addView(a(context, (int) R$styleable.Theme_Custom_choose_contact_item_arrow_icon, com.budejie.www.h.c.a().b(R.attr.share_report_theme), (int) R.string.shenhe_report, (int) R$styleable.Theme_Custom_label_name_layout_bg, bVar));
                    }
                }
            }
            dialog.setContentView(inflate);
            Window window = dialog.getWindow();
            if (window != null) {
                window.addFlags(2);
                LayoutParams attributes = window.getAttributes();
                attributes.gravity = 80;
                attributes.height = -2;
                attributes.width = -1;
                attributes.windowAnimations = R.style.PopUpBottomAnimation;
                window.setAttributes(attributes);
            }
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
            return dialog;
        } catch (InflateException e) {
            return null;
        }
    }

    private static boolean c(Context context) {
        if (!CommonLabelActivity.h) {
            return false;
        }
        if (context instanceof CommonLabelActivity) {
            return true;
        }
        if (context instanceof PostDetailActivity) {
            return ((PostDetailActivity) context).a;
        }
        return false;
    }

    private static boolean a(ListItemObject listItemObject) {
        if (listItemObject == null) {
            return false;
        }
        String type = listItemObject.getType();
        if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(type) || "41".equals(type)) {
            return true;
        }
        return false;
    }

    @Deprecated
    public static void a(Activity activity, String[] strArr, Map<String, Object> map) {
        if (an.a((Context) activity)) {
            b(activity, strArr, map);
        } else {
            an.a(activity, activity.getString(R.string.nonet), -1).show();
        }
    }

    public static void b(final Activity activity, String[] strArr, final Map<String, Object> map) {
        final Dialog dialog = new Dialog(activity, R.style.DialogTheme_CreateUgc);
        OnClickListener anonymousClass2 = new OnClickListener() {
            public void onClick(View view) {
                Object obj;
                if (view.getTag() == null) {
                    obj = "";
                } else {
                    obj = view.getTag().toString();
                }
                Intent intent;
                String str;
                if ("发图片".equals(obj)) {
                    intent = new Intent(activity, TougaoActivity.class);
                    intent.putExtra("TOUGAO_TYPE", 10);
                    if (!(map == null || map.isEmpty())) {
                        str = (String) map.get("theme_name");
                        intent.putExtra("label_id", ((Integer) map.get("theme_id")).intValue());
                        intent.putExtra("label_name", str);
                    }
                    activity.startActivity(intent);
                } else if ("发段子".equals(obj)) {
                    intent = new Intent(activity, TougaoActivity.class);
                    intent.putExtra("TOUGAO_TYPE", 29);
                    if (!(map == null || map.isEmpty())) {
                        str = (String) map.get("theme_name");
                        intent.putExtra("label_id", ((Integer) map.get("theme_id")).intValue());
                        intent.putExtra("label_name", str);
                    }
                    activity.startActivity(intent);
                } else if ("发声音".equals(obj)) {
                    if (!(map == null || map.isEmpty())) {
                        str = (String) map.get("theme_name");
                        h.a().a(((Integer) map.get("theme_id")).intValue());
                        h.a().a(str);
                    }
                    p.a(activity);
                    g.a(activity).d();
                } else if ("发视频".equals(obj)) {
                    av.a(activity, map);
                }
                dialog.dismiss();
            }
        };
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(strArr[i])) {
                strArr2[i] = "发图片";
            } else if ("29".equals(strArr[i])) {
                strArr2[i] = "发段子";
            } else if ("31".equals(strArr[i])) {
                strArr2[i] = "发声音";
            } else if ("41".equals(strArr[i])) {
                strArr2[i] = "发视频";
            }
        }
        LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) activity.getSystemService("layout_inflater")).inflate(R.layout.alert_item_latout, null);
        linearLayout.setMinimumWidth(10000);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.content_list);
        linearLayout2.setBackgroundResource(j.aC);
        Button button = (Button) linearLayout.findViewById(R.id.btn_cancel);
        button.setBackgroundResource(j.aC);
        button.findViewById(R.id.btn_cancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, activity.getResources().getDimensionPixelOffset(R.dimen.list_item_writer_profile));
        for (int i2 = 0; i2 < strArr2.length; i2++) {
            CharSequence charSequence = strArr2[i2];
            if (!TextUtils.isEmpty(charSequence)) {
                View textView = new TextView(activity);
                textView.setGravity(17);
                textView.setText(charSequence);
                textView.setTextSize(2, 18.0f);
                textView.setBackgroundResource(j.aC);
                textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.item_title_name_color));
                textView.setTag(charSequence);
                textView.setOnClickListener(anonymousClass2);
                textView.setLayoutParams(layoutParams);
                if (i2 == 0) {
                    linearLayout2.addView(textView);
                } else {
                    View imageView = new ImageView(activity);
                    imageView.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.divider_horizontal_bg));
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, activity.getResources().getDimensionPixelOffset(R.dimen.divide_line_height)));
                    linearLayout2.addView(imageView);
                    linearLayout2.addView(textView);
                }
            }
        }
        LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = -1000;
        attributes.gravity = 80;
        dialog.onWindowAttributesChanged(attributes);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(linearLayout);
        dialog.show();
    }

    public static void a(Activity activity, ListItemObject listItemObject, Handler handler, int i) {
        a(activity, listItemObject, handler, false, null, true);
    }

    public static void a(Activity activity, ListItemObject listItemObject, Handler handler) {
        a(activity, listItemObject, handler, false);
    }

    public static void a(Activity activity, ListItemObject listItemObject, Handler handler, boolean z) {
        a(activity, listItemObject, handler, z, null);
    }

    public static void a(Activity activity, ListItemObject listItemObject, Handler handler, boolean z, Dialog dialog) {
        a(activity, listItemObject, handler, z, dialog, false);
    }

    public static void a(Activity activity, ListItemObject listItemObject, Handler handler, boolean z, Dialog dialog, boolean z2) {
        if (an.a((Context) activity)) {
            ReportItem reportItem;
            ReportItem reportItem2;
            Object obj;
            Object obj2;
            Object obj3;
            Object obj4;
            Object obj5;
            Object obj6;
            Object obj7;
            ReportItem reportItem3;
            int i;
            int i2;
            final Dialog dialog2 = new Dialog(activity, R.style.DialogTheme_CreateUgc);
            boolean isCollect = listItemObject.isCollect();
            final SharedPreferences sharedPreferences = activity.getSharedPreferences("weiboprefer", 0);
            final Object j = z.j(sharedPreferences.getString("reportdata", ""));
            final List arrayList = new ArrayList();
            final boolean v = an.v(activity);
            if (v) {
                reportItem = new ReportItem();
                reportItem.setReportID("SuperUserSc");
                reportItem.setReportContent("超级账号删除");
                reportItem2 = new ReportItem();
                reportItem2.setReportID(IXAdRequestInfo.QUERY_HEIGHT);
                reportItem2.setReportContent("拉黑");
                obj = reportItem2;
                obj2 = reportItem;
            } else {
                obj = null;
                obj2 = null;
            }
            ReportItem reportItem4;
            if (z) {
                reportItem4 = new ReportItem();
                if (listItemObject.isTopTopic()) {
                    reportItem4.setReportID("qxzd");
                    reportItem4.setReportContent("取消置顶");
                } else {
                    reportItem4.setReportID("zd");
                    reportItem4.setReportContent("置顶");
                }
                reportItem2 = new ReportItem();
                reportItem2.setReportID("sc");
                reportItem2.setReportContent("删除");
                obj3 = reportItem2;
                obj4 = null;
                obj5 = null;
                obj6 = reportItem4;
                obj7 = null;
            } else {
                ReportItem reportItem5 = new ReportItem();
                reportItem5.setReportID("jb");
                reportItem5.setReportContent("举报");
                if (isCollect) {
                    reportItem3 = new ReportItem();
                    reportItem3.setReportID("qxsc");
                    reportItem3.setReportContent("取消收藏");
                } else {
                    reportItem3 = new ReportItem();
                    reportItem3.setReportID("sc");
                    reportItem3.setReportContent("收藏");
                }
                reportItem2 = new ReportItem();
                reportItem2.setReportID("qxgj");
                reportItem2.setReportContent("取消关注");
                ReportItem reportItem6 = reportItem5;
                reportItem = reportItem3;
                obj6 = null;
                obj3 = null;
                reportItem4 = reportItem2;
            }
            if (!z2 || obj7 == null) {
                i = 0;
            } else {
                i = 1;
                arrayList.add(0, obj7);
            }
            if (obj4 != null) {
                i2 = i + 1;
                arrayList.add(i, obj4);
            } else {
                i2 = i;
            }
            if (obj5 != null) {
                i = i2 + 1;
                arrayList.add(i2, obj5);
            } else {
                i = i2;
            }
            if (obj6 != null) {
                i2 = i + 1;
                arrayList.add(i, obj6);
            } else {
                i2 = i;
            }
            if (obj2 != null) {
                i = i2 + 1;
                arrayList.add(i2, obj2);
            } else {
                i = i2;
            }
            if (obj != null) {
                i2 = i + 1;
                arrayList.add(i, obj);
            } else {
                i2 = i;
            }
            if (obj3 != null) {
                i = i2 + 1;
                arrayList.add(i2, obj3);
            }
            if (obj5 != null) {
                arrayList.addAll(j);
            }
            LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) activity.getSystemService("layout_inflater")).inflate(R.layout.alert_item_latout, null);
            linearLayout.setMinimumWidth(10000);
            final LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.content_list);
            linearLayout2.setBackgroundResource(j.aC);
            Button button = (Button) linearLayout.findViewById(R.id.btn_cancel);
            button.setBackgroundResource(j.aC);
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    dialog2.dismiss();
                }
            });
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) (i.a().b((Context) activity) * 45.0f));
            ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, activity.getResources().getDimensionPixelOffset(R.dimen.divide_line_height));
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                reportItem3 = (ReportItem) arrayList.get(i3);
                CharSequence reportContent = reportItem3.getReportContent();
                View imageView = new ImageView(activity);
                imageView.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.divider_horizontal_bg));
                imageView.setLayoutParams(layoutParams2);
                imageView.setTag(reportContent);
                View textView = new TextView(activity);
                textView.setGravity(17);
                textView.setText(reportContent);
                textView.setTextSize(2, 18.0f);
                textView.setBackgroundResource(j.aC);
                textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.item_title_name_color));
                textView.setTag(reportItem3);
                final Activity activity2 = activity;
                final ListItemObject listItemObject2 = listItemObject;
                final Handler handler2 = handler;
                final Dialog dialog3 = dialog;
                textView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        String b = ai.b(activity2);
                        ReportItem reportItem = (ReportItem) (view.getTag() == null ? null : view.getTag());
                        if (reportItem != null) {
                            String reportContent = reportItem.getReportContent();
                            if ("举报".equals(reportContent)) {
                                activity2.runOnUiThread(new p$5$1(this));
                                TextView textView = (TextView) linearLayout2.findViewWithTag(reportItem);
                                textView.setTextColor(activity2.getResources().getColor(R.color.black));
                                textView.setOnClickListener(null);
                                for (ReportItem reportItem2 : j) {
                                    linearLayout2.findViewWithTag(reportItem2).setVisibility(0);
                                    linearLayout2.findViewWithTag(reportItem2.getReportContent()).setVisibility(0);
                                }
                                return;
                            }
                            Bundle bundle;
                            if ("收藏".equals(reportContent)) {
                                if (an.a(sharedPreferences)) {
                                    Map hashMap = new HashMap();
                                    hashMap.put("type", an.g(listItemObject2.getType()));
                                    hashMap.put("title", listItemObject2.getContent());
                                    hashMap.put("label", listItemObject2.getPlateNames());
                                    an.a(activity2, hashMap, "E01_A04");
                                    bundle = new Bundle();
                                    listItemObject2.setForwardAndCollect(false);
                                    listItemObject2.setForwardAndCollect_isweixin(false);
                                    bundle.putSerializable("data", listItemObject2);
                                    m.a(activity2, handler2, bundle);
                                } else {
                                    an.a(activity2, 0, null, null, 0);
                                }
                            } else if ("取消收藏".equals(reportContent)) {
                                if (an.a(sharedPreferences)) {
                                    bundle = new Bundle();
                                    bundle.putString("wid", listItemObject2.getWid());
                                    bundle.putString("imgPath", listItemObject2.getImgPath());
                                    bundle.putSerializable("data", listItemObject2);
                                    m.b(activity2, handler2, bundle);
                                } else {
                                    an.a(activity2, 0, null, null, 0);
                                }
                            } else if ("超级账号删除".equals(reportContent)) {
                                f.a(activity2, handler2, "0", listItemObject2.getWid());
                            } else if ("删除".equals(reportContent)) {
                                f.b(activity2, handler2, listItemObject2.getWid(), listItemObject2.getUid());
                            } else if ("拉黑".equals(reportContent)) {
                                f.a(activity2, handler2, "1", listItemObject2.getWid());
                            } else if ("置顶".equals(reportContent)) {
                                if (dialog3 != null) {
                                    dialog3.show();
                                }
                                f.a(activity2, handler2, listItemObject2.getWid(), listItemObject2.getUid(), true);
                            } else if ("取消置顶".equals(reportContent)) {
                                if (dialog3 != null) {
                                    dialog3.show();
                                }
                                f.a(activity2, handler2, listItemObject2.getWid(), listItemObject2.getUid(), false);
                            } else if ("取消关注".equals(reportContent)) {
                                aa.a("DialogTools", "取消关注");
                                Message obtain = Message.obtain();
                                obtain.obj = listItemObject2;
                                obtain.what = 1006;
                                handler2.sendMessage(obtain);
                            } else {
                                aa.a("DialogTools", "id-->" + reportItem2.getReportID() + "内容-->" + reportItem2.getReportContent());
                                m.a(activity2, reportItem2.getReportID(), b, listItemObject2.getWid());
                            }
                            dialog2.dismiss();
                        }
                    }
                });
                textView.setLayoutParams(layoutParams);
                i2 = 1;
                if (v) {
                    i2 = 2;
                }
                if (z2) {
                    i2++;
                }
                if (i3 > i2) {
                    imageView.setVisibility(8);
                    textView.setVisibility(8);
                }
                if (i3 == 0) {
                    linearLayout2.addView(textView);
                } else {
                    linearLayout2.addView(imageView);
                    linearLayout2.addView(textView);
                }
            }
            LayoutParams attributes = dialog2.getWindow().getAttributes();
            attributes.x = 0;
            attributes.y = -1000;
            attributes.gravity = 80;
            dialog2.onWindowAttributesChanged(attributes);
            dialog2.setCanceledOnTouchOutside(true);
            dialog2.setContentView(linearLayout);
            dialog2.show();
            return;
        }
        an.a(activity, activity.getString(R.string.nonet), -1).show();
    }

    public static void b(final Activity activity, final ListItemObject listItemObject, final Handler handler, boolean z, Dialog dialog) {
        if (an.a((Context) activity)) {
            int i;
            final Dialog dialog2 = new Dialog(activity, R.style.DialogTheme_CreateUgc);
            List arrayList = new ArrayList();
            ReportItem reportItem = new ReportItem();
            reportItem.setReportID("SuperUserSc");
            reportItem.setReportContent("删除帖子");
            ReportItem reportItem2 = new ReportItem();
            reportItem2.setReportID(IXAdRequestInfo.QUERY_HEIGHT);
            reportItem2.setReportContent("删除帖子并拉黑用户");
            if (reportItem != null) {
                i = 1;
                arrayList.add(0, reportItem);
            } else {
                i = 0;
            }
            if (reportItem2 != null) {
                int i2 = i + 1;
                arrayList.add(i, reportItem2);
            }
            LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) activity.getSystemService("layout_inflater")).inflate(R.layout.alert_item_latout, null);
            linearLayout.setMinimumWidth(10000);
            LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.content_list);
            linearLayout2.setBackgroundResource(j.aC);
            Button button = (Button) linearLayout.findViewById(R.id.btn_cancel);
            button.setBackgroundResource(j.aC);
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    dialog2.dismiss();
                }
            });
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) (i.a().b((Context) activity) * 45.0f));
            ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, activity.getResources().getDimensionPixelOffset(R.dimen.divide_line_height));
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                reportItem = (ReportItem) arrayList.get(i3);
                CharSequence reportContent = reportItem.getReportContent();
                View imageView = new ImageView(activity);
                imageView.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.divider_horizontal_bg));
                imageView.setLayoutParams(layoutParams2);
                imageView.setTag(reportContent);
                View textView = new TextView(activity);
                textView.setGravity(17);
                textView.setText(reportContent);
                textView.setTextSize(2, 18.0f);
                textView.setBackgroundResource(j.aC);
                textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.item_title_name_color));
                textView.setTag(reportItem);
                textView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        ai.b(activity);
                        ReportItem reportItem = (ReportItem) (view.getTag() == null ? null : view.getTag());
                        if (reportItem != null) {
                            String reportContent = reportItem.getReportContent();
                            if ("删除帖子".equals(reportContent)) {
                                f.a(activity, handler, "0", listItemObject.getWid());
                                aa.b("DialogTools", "删除帖子");
                            } else if ("删除帖子并拉黑用户".equals(reportContent)) {
                                f.a(activity, handler, "1", listItemObject.getWid());
                                aa.b("DialogTools", "删除帖子并拉黑用户");
                            }
                            dialog2.dismiss();
                        }
                    }
                });
                textView.setLayoutParams(layoutParams);
                if (i3 == 0) {
                    linearLayout2.addView(textView);
                } else {
                    linearLayout2.addView(imageView);
                    linearLayout2.addView(textView);
                }
            }
            LayoutParams attributes = dialog2.getWindow().getAttributes();
            attributes.x = 0;
            attributes.y = -1000;
            attributes.gravity = 80;
            dialog2.onWindowAttributesChanged(attributes);
            dialog2.setCanceledOnTouchOutside(true);
            dialog2.setContentView(linearLayout);
            dialog2.show();
            return;
        }
        an.a(activity, activity.getString(R.string.nonet), -1).show();
    }

    public static void a(Activity activity, String str, String str2, OnClickListener onClickListener) {
        if (an.a((Context) activity)) {
            final Dialog dialog = new Dialog(activity, R.style.DialogTheme_CreateUgc);
            List j = z.j(activity.getSharedPreferences("weiboprefer", 0).getString("reportdata", ""));
            ReportItem reportItem = new ReportItem();
            reportItem.setReportID("jb");
            reportItem.setReportContent("举报");
            j.add(0, reportItem);
            final OnClickListener onClickListener2 = onClickListener;
            final Activity activity2 = activity;
            final String str3 = str2;
            OnClickListener anonymousClass8 = new OnClickListener() {
                public void onClick(View view) {
                    if (onClickListener2 != null) {
                        onClickListener2.onClick(view);
                    }
                    String b = ai.b(activity2);
                    ReportItem reportItem = (ReportItem) (view.getTag() == null ? null : view.getTag());
                    if (reportItem != null) {
                        if (!"举报".equals(reportItem.getReportContent())) {
                            aa.a("DialogTools", "id-->" + reportItem.getReportID() + "-->" + reportItem.getReportContent());
                            m.a(activity2, reportItem.getReportID(), b, str3);
                            dialog.dismiss();
                        }
                    }
                }
            };
            try {
                LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) activity.getSystemService("layout_inflater")).inflate(R.layout.alert_item_latout, null);
                linearLayout.setMinimumWidth(10000);
                LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.content_list);
                linearLayout2.setBackgroundResource(j.aC);
                Button button = (Button) linearLayout.findViewById(R.id.btn_cancel);
                button.setBackgroundResource(j.aC);
                button.findViewById(R.id.btn_cancel).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) (i.a().b((Context) activity) * 45.0f));
                ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, activity.getResources().getDimensionPixelOffset(R.dimen.divide_line_height));
                for (int i = 0; i < j.size(); i++) {
                    ReportItem reportItem2 = (ReportItem) j.get(i);
                    CharSequence reportContent = reportItem2.getReportContent();
                    View imageView = new ImageView(activity);
                    imageView.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.divider_horizontal_bg));
                    imageView.setLayoutParams(layoutParams2);
                    View textView = new TextView(activity);
                    if (reportContent.equals("举报")) {
                        textView.setTextColor(activity.getResources().getColor(R.color.black));
                        textView.setFocusable(false);
                    } else {
                        textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.item_title_name_color));
                        textView.setOnClickListener(anonymousClass8);
                    }
                    textView.setGravity(17);
                    textView.setText(reportContent);
                    textView.setTextSize(2, 18.0f);
                    textView.setBackgroundResource(j.aC);
                    textView.setTag(reportItem2);
                    textView.setLayoutParams(layoutParams);
                    if (i == 0) {
                        linearLayout2.addView(textView);
                    } else {
                        linearLayout2.addView(imageView);
                        linearLayout2.addView(textView);
                    }
                }
                LayoutParams attributes = dialog.getWindow().getAttributes();
                attributes.x = 0;
                attributes.y = -1000;
                attributes.gravity = 80;
                dialog.onWindowAttributesChanged(attributes);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(linearLayout);
                dialog.show();
                return;
            } catch (InflateException e) {
                e.printStackTrace();
                return;
            }
        }
        an.a(activity, activity.getString(R.string.nonet), -1).show();
    }

    public static void a(Activity activity, String str, final c cVar) {
        if (an.a((Context) activity)) {
            String[] strArr;
            final Dialog dialog = new Dialog(activity, R.style.DialogTheme_CreateUgc);
            OnClickListener anonymousClass11 = new OnClickListener() {
                public void onClick(View view) {
                    String obj = view.getTag() == null ? "" : view.getTag().toString();
                    if ("男".equals(obj)) {
                        cVar.a(obj);
                    } else if ("女".equals(obj)) {
                        cVar.a(obj);
                    } else if ("拍照".equals(obj)) {
                        cVar.a(obj);
                    } else if ("从相册中选择".equals(obj)) {
                        cVar.a(obj);
                    }
                    dialog.dismiss();
                }
            };
            if (str.equals("sex")) {
                strArr = new String[]{"男", "女"};
            } else if (str.equals("photo")) {
                strArr = new String[]{"拍照", "从相册中选择"};
            } else {
                strArr = null;
            }
            LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) activity.getSystemService("layout_inflater")).inflate(R.layout.alert_item_latout, null);
            linearLayout.setMinimumWidth(10000);
            LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.content_list);
            linearLayout2.setBackgroundResource(j.aC);
            Button button = (Button) linearLayout.findViewById(R.id.btn_cancel);
            button.setBackgroundResource(j.aC);
            button.findViewById(R.id.btn_cancel).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) (i.a().b((Context) activity) * 45.0f));
            ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, activity.getResources().getDimensionPixelOffset(R.dimen.divide_line_height));
            for (int i = 0; i < strArr.length; i++) {
                CharSequence charSequence = strArr[i];
                View imageView = new ImageView(activity);
                imageView.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.divider_horizontal_bg));
                imageView.setLayoutParams(layoutParams2);
                View textView = new TextView(activity);
                textView.setGravity(17);
                textView.setText(charSequence);
                textView.setTextSize(2, 18.0f);
                textView.setBackgroundResource(j.aC);
                textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.item_title_name_color));
                textView.setTag(charSequence);
                textView.setOnClickListener(anonymousClass11);
                textView.setLayoutParams(layoutParams);
                if (i == 0) {
                    linearLayout2.addView(textView);
                } else {
                    linearLayout2.addView(imageView);
                    linearLayout2.addView(textView);
                }
            }
            LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.x = 0;
            attributes.y = -1000;
            attributes.gravity = 80;
            dialog.onWindowAttributesChanged(attributes);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setContentView(linearLayout);
            dialog.show();
            return;
        }
        an.a(activity, activity.getString(R.string.nonet), -1).show();
    }

    public static void a(final Activity activity) {
        new Builder(activity).setTitle("图片选择").setPositiveButton("拍照", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                p.c(activity);
                dialogInterface.dismiss();
            }
        }).setNegativeButton("相册", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                p.b(activity);
                dialogInterface.dismiss();
            }
        }).create().show();
    }

    public static void b(Activity activity) {
        try {
            Intent intent = new Intent("android.intent.action.PICK");
            intent.setDataAndType(Media.INTERNAL_CONTENT_URI, "image/*");
            activity.startActivityForResult(intent, 714);
        } catch (Exception e) {
            an.a(activity, activity.getString(R.string.no_available_album), -1).show();
        }
    }

    public static void a(Activity activity, Intent intent, int i, int i2) {
        Uri data = intent.getData();
        String path;
        if (data.toString().startsWith(UriUtil.LOCAL_FILE_SCHEME)) {
            path = data.getPath();
            aa.a("DialogTools", "filepath:" + path);
            if (path == null || !path.endsWith(".gif")) {
                a(activity, path, i, i2);
                return;
            }
            an.a(activity, activity.getString(R.string.NoSupportGif), -1).show();
            b(activity);
            return;
        }
        Cursor query = activity.getContentResolver().query(data, null, null, null, null);
        if (query != null) {
            query.moveToFirst();
            path = query.getString(query.getColumnIndex("_data"));
            aa.a("DialogTools", "filepath2:" + path);
            if (path == null || !path.endsWith(".gif")) {
                a(activity, path, i, i2);
                return;
            }
            an.a(activity, activity.getString(R.string.NoSupportGif), -1).show();
            b(activity);
        }
    }

    public static String a(Activity activity, Intent intent) {
        Uri data = intent.getData();
        String str = "";
        if (data.toString().startsWith(UriUtil.LOCAL_FILE_SCHEME)) {
            String path = data.getPath();
            aa.a("DialogTools", "filepath:" + path);
            return path;
        }
        Cursor query = activity.getContentResolver().query(data, null, null, null, null);
        if (query == null) {
            return str;
        }
        query.moveToFirst();
        path = query.getString(query.getColumnIndex("_data"));
        aa.a("DialogTools", "filepath2:" + path);
        return path;
    }

    public static void c(Activity activity) {
        if (com.budejie.www.activity.video.a.a()) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            File file = new File(Environment.getExternalStorageDirectory(), i.a().d() + ".jpg");
            Parcelable fromFile = Uri.fromFile(file);
            a = file.getAbsolutePath();
            intent.putExtra("output", fromFile);
            try {
                activity.startActivityForResult(intent, 716);
                return;
            } catch (Exception e) {
                an.a(activity, activity.getString(R.string.no_camera), -1).show();
                return;
            }
        }
        an.a(activity, activity.getString(R.string.no_sdcard), -1).show();
    }

    public static void a(Activity activity, String str, int i, int i2) {
        Intent intent = new Intent(activity, ClipPictureActivity.class);
        intent.putExtra("image-path", str);
        intent.putExtra("aspectX", 3);
        intent.putExtra("aspectY", 2);
        intent.putExtra("outputX", i);
        intent.putExtra("outputY", i2);
        activity.startActivityForResult(intent, 728);
    }

    public static void a(Intent intent, Activity activity, Class cls, String str) {
        String stringExtra = intent.getStringExtra("image-path");
        Intent intent2 = new Intent(activity, cls);
        intent2.putExtra("TOUGAO_TYPE", 31);
        intent2.putExtra("h5_reserve", str);
        intent2.putExtra("image_path", stringExtra);
        intent2.putExtra("theme_data", intent.getExtras());
        activity.startActivity(intent2);
    }

    public static void a(Activity activity, String[] strArr, final c cVar, boolean z) {
        if (!an.a((Context) activity)) {
            an.a(activity, activity.getString(R.string.nonet), -1).show();
        } else if (z && TextUtils.isEmpty(ai.b(activity))) {
            an.a(activity, 0, null, null, 0);
        } else {
            final Dialog dialog = new Dialog(activity, R.style.DialogTheme_CreateUgc);
            OnClickListener anonymousClass15 = new OnClickListener() {
                public void onClick(View view) {
                    cVar.a(view.getTag() == null ? "" : view.getTag().toString());
                    dialog.dismiss();
                }
            };
            LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) activity.getSystemService("layout_inflater")).inflate(R.layout.alert_item_latout, null);
            linearLayout.setMinimumWidth(10000);
            LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.content_list);
            linearLayout2.setBackgroundResource(j.aC);
            Button button = (Button) linearLayout.findViewById(R.id.btn_cancel);
            button.setBackgroundResource(j.aC);
            button.findViewById(R.id.btn_cancel).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) (i.a().b((Context) activity) * 45.0f));
            ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, activity.getResources().getDimensionPixelOffset(R.dimen.divide_line_height));
            for (int i = 0; i < strArr.length; i++) {
                CharSequence charSequence = strArr[i];
                View imageView = new ImageView(activity);
                imageView.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.divider_horizontal_bg));
                imageView.setLayoutParams(layoutParams2);
                View textView = new TextView(activity);
                textView.setGravity(17);
                textView.setText(charSequence);
                textView.setTextSize(2, 18.0f);
                textView.setBackgroundResource(j.aC);
                textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.item_title_name_color));
                textView.setTag(charSequence);
                textView.setOnClickListener(anonymousClass15);
                textView.setLayoutParams(layoutParams);
                if (i == 0) {
                    linearLayout2.addView(textView);
                } else {
                    linearLayout2.addView(imageView);
                    linearLayout2.addView(textView);
                }
            }
            LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.x = 0;
            attributes.y = -1000;
            attributes.gravity = 80;
            dialog.onWindowAttributesChanged(attributes);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setContentView(linearLayout);
            dialog.show();
        }
    }

    @SuppressLint({"NewApi"})
    public static AlertDialog a(Activity activity, String str, String str2, String str3, String str4, DialogInterface.OnClickListener onClickListener) {
        AlertDialog create = new Builder(activity, 3).create();
        create.setTitle(str);
        create.setMessage(str2);
        create.setButton(str3, onClickListener);
        create.setButton2(str4, onClickListener);
        create.show();
        return create;
    }

    @SuppressLint({"NewApi"})
    public static AlertDialog a(Activity activity, String str, String str2, DialogInterface.OnClickListener onClickListener) {
        final AlertDialog create = new Builder(activity, 3).create();
        create.setMessage(str);
        if (onClickListener == null) {
            create.setButton(str2, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    create.dismiss();
                }
            });
        } else {
            create.setButton(str2, onClickListener);
        }
        create.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4) {
                    return true;
                }
                return false;
            }
        });
        create.show();
        return create;
    }

    public static void a(final Activity activity, final String str) {
        Builder builder = new Builder(activity, 4);
        builder.setTitle(R.string.system_tip);
        builder.setMessage(R.string.download_apk_message);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (com.budejie.www.activity.video.a.a()) {
                    an.a(activity, "开始下载", -1).show();
                    com.budejie.www.download.c.a().a(str, activity);
                    return;
                }
                an.a(activity, activity.getString(R.string.no_sdcard), -1).show();
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.show();
    }

    public static void a(final Context context) {
        Builder builder = new Builder(context);
        builder.setTitle(R.string.request_permission_text).setMessage(R.string.storage_permisson_request_text).setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setNegativeButton(R.string.to_settings_text, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    Intent intent = new Intent();
                    intent.addFlags(268435456);
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                    context.startActivity(intent);
                    dialogInterface.dismiss();
                } catch (Exception e) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }
}
