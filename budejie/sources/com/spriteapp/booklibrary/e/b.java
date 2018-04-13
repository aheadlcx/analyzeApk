package com.spriteapp.booklibrary.e;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import com.spriteapp.booklibrary.a.c;
import com.spriteapp.booklibrary.e.d.a;
import com.spriteapp.booklibrary.util.AppUtil;

public class b {
    private static d a;
    private static boolean b;

    public static void a(boolean z) {
        b = z;
        a = new a().a(a(com.spriteapp.booklibrary.a.a.book_reader_white, com.spriteapp.booklibrary.a.a.book_reader_store_container_night_background)).b(a(com.spriteapp.booklibrary.a.a.book_reader_pay_text_day_color, com.spriteapp.booklibrary.a.a.book_reader_pay_text_night_color)).a(b(com.spriteapp.booklibrary.a.a.book_reader_bdj_recharge_text_selector, com.spriteapp.booklibrary.a.a.book_reader_bdj_recharge_text_night_selector)).d(a(com.spriteapp.booklibrary.a.a.book_reader_container_color, com.spriteapp.booklibrary.a.a.book_reader_divide_view_night_background)).e(a(com.spriteapp.booklibrary.a.a.book_reader_divide_line_color, com.spriteapp.booklibrary.a.a.book_reader_divide_line_night_color)).f(a(com.spriteapp.booklibrary.a.a.book_reader_bdj_main_color, com.spriteapp.booklibrary.a.a.book_reader_bdj_main_night_color)).g(a(com.spriteapp.booklibrary.a.a.book_reader_common_text_color, com.spriteapp.booklibrary.a.a.book_reader_book_title_night_color)).h(a(com.spriteapp.booklibrary.a.a.book_reader_book_author_day_color, com.spriteapp.booklibrary.a.a.book_reader_book_author_night_color)).i(a(com.spriteapp.booklibrary.a.a.book_reader_all_book_day_color, com.spriteapp.booklibrary.a.a.book_reader_all_book_night_color)).j(c(c.book_reader_store_item_shape, c.book_reader_store_item_night_shape)).c(c(c.book_reader_recharge_text_selector, c.book_reader_recharge_text_night_selector)).l(a(com.spriteapp.booklibrary.a.a.book_reader_recyclerview_header_day_background, com.spriteapp.booklibrary.a.a.book_reader_recyclerview_header_night_background)).m(a(com.spriteapp.booklibrary.a.a.book_reader_recyclerview_header_text_day_color, com.spriteapp.booklibrary.a.a.book_reader_recyclerview_header_text_night_color)).k(c(c.book_reader_see_all_image, c.book_reader_see_all_image_night)).a();
    }

    public static d a() {
        return a;
    }

    private static int a(int i, int i2) {
        Resources resources = AppUtil.getAppContext().getResources();
        if (!b) {
            i2 = i;
        }
        return resources.getColor(i2);
    }

    private static ColorStateList b(int i, int i2) {
        Resources resources = AppUtil.getAppContext().getResources();
        if (!b) {
            i2 = i;
        }
        return resources.getColorStateList(i2);
    }

    private static int c(int i, int i2) {
        return b ? i2 : i;
    }
}
