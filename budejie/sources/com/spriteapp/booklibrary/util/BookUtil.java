package com.spriteapp.booklibrary.util;

import com.spriteapp.booklibrary.enumeration.BookEnum;
import com.spriteapp.booklibrary.model.response.BookChapterResponse;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookUtil {
    public static String getBookJson(List<BookDetailResponse> list) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            BookDetailResponse bookDetailResponse = (BookDetailResponse) list.get(i);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("book_id", bookDetailResponse.getBook_id());
                jSONObject.put("chapter_id", bookDetailResponse.getChapter_id());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray.toString();
    }

    public static int getCurrentChapterPosition(List<BookChapterResponse> list, int i) {
        if (CollectionUtil.isEmpty(list)) {
            return 0;
        }
        int i2 = 0;
        while (i2 < list.size()) {
            if (((BookChapterResponse) list.get(i2)).getChapter_id() == i) {
                break;
            }
            i2++;
        }
        i2 = 0;
        return i2;
    }

    public static boolean isBookAddShelf(BookDetailResponse bookDetailResponse) {
        return bookDetailResponse != null && bookDetailResponse.getBook_add_shelf() == BookEnum.ADD_SHELF.getValue() && bookDetailResponse.getIs_recommend_book() == BookEnum.MY_BOOK.getValue();
    }
}
