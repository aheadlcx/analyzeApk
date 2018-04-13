package com.spriteapp.booklibrary.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import com.spriteapp.booklibrary.a$g;
import com.spriteapp.booklibrary.a.f;
import com.spriteapp.booklibrary.listener.BaseDialogListener;
import com.spriteapp.booklibrary.listener.DeleteBookListener;
import com.spriteapp.booklibrary.listener.DialogListener;
import com.spriteapp.booklibrary.listener.ReadDialogListener;

public class DialogUtil {
    private static DeleteBookListener mDeleteBookListener;
    private static ReadDialogListener mReadListener;

    public static AlertDialog getPayChapterDialog(Context context, String str, String str2, String str3, String str4) {
        AlertDialog create = new Builder(context, a$g.DialogTheme).setTitle((CharSequence) str).setMessage((CharSequence) str2).setNegativeButton((CharSequence) str3, new DialogUtil$2()).setPositiveButton((CharSequence) str4, new DialogUtil$1()).create();
        create.setCanceledOnTouchOutside(false);
        create.setOnKeyListener(new DialogUtil$3());
        return create;
    }

    public static AlertDialog getAutoSubDialog(Context context) {
        AlertDialog create = new Builder(context, a$g.DialogTheme).setTitle(f.book_reader_auto_buy).setMessage(f.book_reader_auto_buy_prompt).setNegativeButton(f.book_reader_hand_buy, new DialogUtil$5()).setPositiveButton(f.book_reader_setting_text, new DialogUtil$4()).create();
        create.setCanceledOnTouchOutside(false);
        create.setOnKeyListener(new DialogUtil$6());
        return create;
    }

    public static AlertDialog getDeleteBookDialog(Context context) {
        return new Builder(context, a$g.DialogTheme).setTitle(f.book_reader_prompt).setMessage(f.book_reader_delete_book_prompt).setNegativeButton(f.book_reader_cancel_text, new DialogUtil$8()).setPositiveButton(f.book_reader_sure, new DialogUtil$7()).create();
    }

    public static AlertDialog showCommonDialog(Context context, String str, DialogListener dialogListener) {
        AlertDialog create = new Builder(context, a$g.DialogTheme).setTitle(f.book_reader_prompt_text).setMessage((CharSequence) str).setNegativeButton(f.book_reader_cancel_text, new DialogUtil$10(dialogListener)).setPositiveButton(f.book_reader_sure, new DialogUtil$9(dialogListener)).create();
        create.setCanceledOnTouchOutside(true);
        create.show();
        return create;
    }

    public static void setDialogListener(BaseDialogListener baseDialogListener) {
        if (baseDialogListener instanceof ReadDialogListener) {
            mReadListener = (ReadDialogListener) baseDialogListener;
        } else if (baseDialogListener instanceof DeleteBookListener) {
            mDeleteBookListener = (DeleteBookListener) baseDialogListener;
        }
    }
}
