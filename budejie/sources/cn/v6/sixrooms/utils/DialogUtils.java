package cn.v6.sixrooms.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.room.view.EditDialog;
import cn.v6.sixrooms.room.view.EditDialog.Callback;

public class DialogUtils {
    private Context a;

    public interface DialogListener {
        void negative(int i);

        void positive(int i);
    }

    public DialogUtils(Context context) {
        this.a = context;
    }

    public static Dialog createProgressDialog(Context context, String str) {
        return new ImprovedProgressDialog(context, str);
    }

    public static ImprovedDialogForSofa createSofaDialog(Context context) {
        return new ImprovedDialogForSofa(context, 2, 1);
    }

    public Dialog createConfirmDialog(int i, String str, String str2, String str3, String str4, DialogListener dialogListener) {
        if (TextUtils.isEmpty(str4)) {
            return createConfirmDialogs(i, str, str2, str3, dialogListener);
        }
        if (TextUtils.isEmpty(str3)) {
            return createConfirmDialogs(i, str, str2, str4, dialogListener);
        }
        Dialog improvedDialog = new ImprovedDialog(this.a, 2, 1);
        improvedDialog.setImprovedTitle(str);
        improvedDialog.setImprovedContent(str2);
        improvedDialog.setImprovedConfirmText(str4);
        improvedDialog.setImprovedCancelText(str3);
        improvedDialog.setImprovedDialogListener(new c(this, dialogListener, i));
        return improvedDialog;
    }

    public Dialog createConfirmDialog(int i, String str, DialogListener dialogListener) {
        return createConfirmDialog(i, "提示", str, this.a.getString(17039360), this.a.getString(17039370), dialogListener);
    }

    public Dialog createDiaglog(String str) {
        return createDiaglog(str, "提示");
    }

    public Dialog createDiaglog(String str, String str2) {
        return createDiaglog(str, str2, null);
    }

    public Dialog createDiaglog(String str, String str2, DialogListener dialogListener) {
        Dialog improvedDialog = new ImprovedDialog(this.a, 1, 1);
        improvedDialog.setImprovedTitle("提示");
        improvedDialog.setImprovedContent(str);
        improvedDialog.setImprovedDialogListener(new d(this, dialogListener));
        return improvedDialog;
    }

    public Dialog createConfirmDialogs(int i, String str, String str2, String str3, DialogListener dialogListener) {
        Dialog improvedDialog = new ImprovedDialog(this.a, 1, 1);
        improvedDialog.setImprovedTitle(str);
        improvedDialog.setImprovedContent(str2);
        improvedDialog.setImprovedConfirmText(str3);
        improvedDialog.setImprovedDialogListener(new e(this, dialogListener, i));
        return improvedDialog;
    }

    private ImprovedDialog a(int i, String str, String str2, String str3, String str4, int i2, DialogListener dialogListener) {
        ImprovedDialog improvedDialog = new ImprovedDialog(this.a, i2, 2);
        improvedDialog.setImprovedTitle(str);
        improvedDialog.setImprovedContent(str2);
        improvedDialog.setImprovedConfirmText(str3);
        improvedDialog.setImprovedCancelText(str4);
        improvedDialog.setImprovedDialogListener(new f(this, dialogListener, i));
        return improvedDialog;
    }

    public Dialog createLeftMessageWithTwoButtons(int i, String str, String str2, String str3, String str4, DialogListener dialogListener) {
        return a(i, str, str2, str4, str3, 2, dialogListener);
    }

    public Dialog createLeftMessageWithTwoButtonsInvalidBack(int i, String str, String str2, String str3, String str4, DialogListener dialogListener) {
        Dialog a = a(i, str, str2, str4, str3, 2, dialogListener);
        a.setCancelable(false);
        return a;
    }

    public Dialog createLeftMessageWithOneButtons(int i, String str, String str2, String str3, DialogListener dialogListener) {
        return a(i, str, str2, str3, "", 1, dialogListener);
    }

    public Dialog createConfirmDialog_shopCarSpecial(int i, int i2, String str, String str2, String str3, String str4, DialogListener dialogListener) {
        Dialog improvedDialogShopCarPrivate = new ImprovedDialogShopCarPrivate(this.a);
        improvedDialogShopCarPrivate.setImprovedTitle(str);
        improvedDialogShopCarPrivate.setImprovedConfirmText(str4);
        improvedDialogShopCarPrivate.setImprovedCancelText(str3);
        improvedDialogShopCarPrivate.setImprovedContent(str2);
        improvedDialogShopCarPrivate.setImprovedImageResource(i2);
        improvedDialogShopCarPrivate.setImprovedDialogListener(new g(this, dialogListener, i));
        return improvedDialogShopCarPrivate;
    }

    public Dialog createLoadingDialog() {
        Dialog dialog = new Dialog(this.a, R.style.fullscreendialog);
        dialog.setContentView(R.layout.sixrooms_phone_custom_progressbar);
        return dialog;
    }

    public static Dialog createBundleDialog(Activity activity, int i, String str, String str2, String str3, String str4, DialogListener dialogListener) {
        Dialog improvedDialog = new ImprovedDialog(activity, 2, 1);
        improvedDialog.setImprovedTitle(str);
        improvedDialog.setImprovedContent(str2);
        improvedDialog.setImprovedConfirmText(str4);
        improvedDialog.setImprovedCancelText(str3);
        improvedDialog.setImprovedDialogListener(new h(dialogListener, i));
        return improvedDialog;
    }

    public static EditDialog createEditDialog(Context context, Callback callback) {
        EditDialog editDialog = new EditDialog(context);
        editDialog.setCallback(callback);
        return editDialog;
    }
}
