package com.spriteapp.booklibrary.recyclerView.factory;

import android.content.Context;
import android.view.View;
import com.spriteapp.booklibrary.model.BookList;
import com.spriteapp.booklibrary.recyclerView.model.HotSellModel;
import com.spriteapp.booklibrary.recyclerView.model.StoreUser;
import com.spriteapp.booklibrary.recyclerView.model.TypeModel;
import com.spriteapp.booklibrary.recyclerView.viewholder.BaseViewHolder;

public interface TypeFactory {
    BaseViewHolder createViewHolder(int i, View view, Context context);

    int type(BookList bookList, boolean z);

    int type(HotSellModel hotSellModel);

    int type(StoreUser storeUser);

    int type(TypeModel typeModel);
}
