package com.spriteapp.booklibrary.recyclerView.model;

import com.spriteapp.booklibrary.recyclerView.decorate.Visitable;
import com.spriteapp.booklibrary.recyclerView.factory.TypeFactory;

public class TypeModel implements Visitable {
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
