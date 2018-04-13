package com.spriteapp.booklibrary.recyclerView.factory;

import android.content.Context;
import android.view.View;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.model.BookList;
import com.spriteapp.booklibrary.recyclerView.model.HotSellModel;
import com.spriteapp.booklibrary.recyclerView.model.StoreUser;
import com.spriteapp.booklibrary.recyclerView.model.TypeModel;
import com.spriteapp.booklibrary.recyclerView.viewholder.BaseViewHolder;
import com.spriteapp.booklibrary.recyclerView.viewholder.BookViewHolder;
import com.spriteapp.booklibrary.recyclerView.viewholder.HotSellViewHolder;
import com.spriteapp.booklibrary.recyclerView.viewholder.ShelfViewHolder;
import com.spriteapp.booklibrary.recyclerView.viewholder.TypeViewHolder;
import com.spriteapp.booklibrary.recyclerView.viewholder.UserViewHolder;

public class ItemTypeFactory implements TypeFactory {
    private static final int BOOK_LAYOUT = e.book_reader_store_book_item;
    private static final int HOT_SELL_LAYOUT = e.book_reader_store_hot_sell_item;
    private static final int SHELF_LAYOUT = e.book_reader_store_my_shelf_item;
    private static final int TYPE_LAYOUT = e.book_reader_store_type_item;
    private static final int USER_ACCOUNT_LAYOUT = e.book_reader_store_user_account_item;

    public int type(StoreUser storeUser) {
        return USER_ACCOUNT_LAYOUT;
    }

    public int type(BookList bookList, boolean z) {
        return z ? SHELF_LAYOUT : BOOK_LAYOUT;
    }

    public int type(TypeModel typeModel) {
        return TYPE_LAYOUT;
    }

    public int type(HotSellModel hotSellModel) {
        return HOT_SELL_LAYOUT;
    }

    public BaseViewHolder createViewHolder(int i, View view, Context context) {
        if (i == USER_ACCOUNT_LAYOUT) {
            return new UserViewHolder(view, context);
        }
        if (i == SHELF_LAYOUT) {
            return new ShelfViewHolder(view, context);
        }
        if (i == HOT_SELL_LAYOUT) {
            return new HotSellViewHolder(view, context);
        }
        if (i == TYPE_LAYOUT) {
            return new TypeViewHolder(view, context);
        }
        if (i == BOOK_LAYOUT) {
            return new BookViewHolder(view, context);
        }
        return null;
    }
}
