package com.spriteapp.booklibrary.recyclerView.model;

import com.spriteapp.booklibrary.model.UserModel;
import com.spriteapp.booklibrary.recyclerView.decorate.Visitable;
import com.spriteapp.booklibrary.recyclerView.factory.TypeFactory;
import java.io.Serializable;

public class StoreUser implements Visitable, Serializable {
    private UserModel userModel;

    public UserModel getUserModel() {
        return this.userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
