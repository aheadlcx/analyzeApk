package com.spriteapp.booklibrary.util;

import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollectionUtil {
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static List<BookDetailResponse> getDiffBook(List<BookDetailResponse> list, List<BookDetailResponse> list2) {
        for (int i = 0; i < list.size(); i++) {
            BookDetailResponse bookDetailResponse = (BookDetailResponse) list.get(i);
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                if (((BookDetailResponse) it.next()).equals(bookDetailResponse)) {
                    it.remove();
                    break;
                }
            }
        }
        return list2;
    }
}
