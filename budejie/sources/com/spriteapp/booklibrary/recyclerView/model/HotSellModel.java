package com.spriteapp.booklibrary.recyclerView.model;

import com.spriteapp.booklibrary.model.store.HotSellResponse;
import com.spriteapp.booklibrary.recyclerView.decorate.Visitable;
import com.spriteapp.booklibrary.recyclerView.factory.TypeFactory;
import java.util.List;

public class HotSellModel implements Visitable {
    private List<HotSellResponse> responseList;

    public List<HotSellResponse> getResponseList() {
        return this.responseList;
    }

    public void setResponseList(List<HotSellResponse> list) {
        this.responseList = list;
    }

    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
