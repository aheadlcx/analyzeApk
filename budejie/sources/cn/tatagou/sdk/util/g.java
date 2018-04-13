package cn.tatagou.sdk.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.view.IconTextView;

public class g {

    public abstract class a {
        final /* synthetic */ g b;

        public a(g gVar) {
            this.b = gVar;
        }

        public void onOK() {
        }

        public void onCancel() {
        }

        public void onChooseSex(String str) {
        }
    }

    public static void showSingleDialog(Context context, int i, String str, String str2) {
        showDialog(context, i, str, str2, null, null);
    }

    public static void showDialog(final Context context, int i, String str, String str2, String str3, final a aVar) {
        View inflate = LayoutInflater.from(context).inflate(i, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.color.transparent_dark_gray));
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        popupWindow.showAtLocation(inflate, 17, 0, 0);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_dialog_content);
        if (!p.isEmpty(str)) {
            textView.setText(str);
        }
        textView = (TextView) inflate.findViewById(R.id.btn_ok);
        if (!p.isEmpty(str2)) {
            textView.setText(str2);
        }
        TextView textView2 = (TextView) inflate.findViewById(R.id.btn_cancel);
        if (p.isEmpty(str3)) {
            textView2.setVisibility(8);
        } else {
            textView2.setText(str3);
        }
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!p.isNetworkOpen(context)) {
                    l.showToastCenter(context, context.getResources().getString(R.string.ttg_net_bad));
                } else if (aVar != null) {
                    aVar.onOK();
                }
                popupWindow.dismiss();
            }
        });
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (aVar != null) {
                    aVar.onCancel();
                }
                popupWindow.dismiss();
            }
        });
    }

    public static void showSexDialog(final Context context, final a aVar) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.ttg_mine_indentity_ppw, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1);
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.color.transparent_dark_gray));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        popupWindow.showAtLocation(inflate, 17, 0, 0);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.ttg_linear_item1);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.ttg_linear_item2);
        LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.ttg_linear_item3);
        LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(R.id.ttv_ly_identity);
        IconTextView iconTextView = (IconTextView) inflate.findViewById(R.id.ttg_itv_item1);
        IconTextView iconTextView2 = (IconTextView) inflate.findViewById(R.id.ttg_itv_item2);
        IconTextView iconTextView3 = (IconTextView) inflate.findViewById(R.id.ttg_itv_item3);
        String sex = b.getSex();
        if ("F".equals(sex)) {
            iconTextView.setText(R.string.ttg_icon_shenfeng_radiocheck);
            iconTextView.setTextColor(TtgConfig.getInstance().getThemeColor());
        } else if ("M".equals(sex)) {
            iconTextView2.setText(R.string.ttg_icon_shenfeng_radiocheck);
            iconTextView2.setTextColor(TtgConfig.getInstance().getThemeColor());
        } else if ("L".equals(sex)) {
            iconTextView3.setText(R.string.ttg_icon_shenfeng_radiocheck);
            iconTextView3.setTextColor(TtgConfig.getInstance().getThemeColor());
        }
        OnClickListener anonymousClass3 = new OnClickListener() {
            public void onClick(View view) {
                int id = view.getId();
                if (id != R.id.ttv_ly_identity) {
                    if (!p.isNetworkOpen(context)) {
                        l.showToastCenter(context, context.getResources().getString(R.string.ttg_net_bad));
                    } else if (aVar != null) {
                        String str = null;
                        if (id == R.id.ttg_linear_item1) {
                            str = "F";
                        } else if (id == R.id.ttg_linear_item2) {
                            str = "M";
                        } else if (id == R.id.ttg_linear_item3) {
                            str = "L";
                        }
                        aVar.onChooseSex(str);
                    }
                }
                popupWindow.dismiss();
            }
        };
        linearLayout.setOnClickListener(anonymousClass3);
        linearLayout2.setOnClickListener(anonymousClass3);
        linearLayout3.setOnClickListener(anonymousClass3);
        linearLayout4.setOnClickListener(anonymousClass3);
    }
}
