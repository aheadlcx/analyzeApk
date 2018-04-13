package com.spriteapp.booklibrary.e;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.spriteapp.booklibrary.a.a;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.util.ScreenUtil;

public class e {
    public static Bitmap a(int i) {
        Bitmap createBitmap = Bitmap.createBitmap(ScreenUtil.getScreenWidth(), ScreenUtil.getScreenHeight(), Config.ARGB_8888);
        switch (i) {
            case 0:
                createBitmap.eraseColor(AppUtil.getAppContext().getResources().getColor(a.book_reader_read_day_background));
                break;
            case 1:
                createBitmap.eraseColor(AppUtil.getAppContext().getResources().getColor(a.book_reader_read_night_background));
                break;
        }
        return createBitmap;
    }
}
