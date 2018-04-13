package com.spriteapp.booklibrary.model;

import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.model.store.BookTypeResponse;
import com.spriteapp.booklibrary.recyclerView.decorate.Visitable;
import com.spriteapp.booklibrary.recyclerView.factory.TypeFactory;
import java.io.Serializable;
import java.util.List;

public class BookList implements Visitable, Serializable {
    private List<BookDetailResponse> detailResponseList;
    private boolean isMyShelf;
    private BookTypeResponse typeResponse;

    public List<BookDetailResponse> getDetailResponseList() {
        return this.detailResponseList;
    }

    public void setDetailResponseList(List<BookDetailResponse> list) {
        this.detailResponseList = list;
    }

    public boolean isMyShelf() {
        return this.isMyShelf;
    }

    public void setMyShelf(boolean z) {
        this.isMyShelf = z;
    }

    public BookTypeResponse getTypeResponse() {
        return this.typeResponse;
    }

    public void setTypeResponse(BookTypeResponse bookTypeResponse) {
        this.typeResponse = bookTypeResponse;
    }

    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this, this.isMyShelf);
    }
}
