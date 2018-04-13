package com.spriteapp.booklibrary.ui.view;

import com.spriteapp.booklibrary.base.BaseView;
import com.spriteapp.booklibrary.model.UserModel;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.model.response.BookStoreResponse;
import com.spriteapp.booklibrary.model.response.LoginResponse;
import java.util.List;

public interface BookShelfView extends BaseView<List<BookDetailResponse>> {
    void setAddShelfResponse();

    void setBookDetail(BookDetailResponse bookDetailResponse);

    void setBookStoreData(BookStoreResponse bookStoreResponse);

    void setDeleteBookResponse();

    void setLoginInfo(LoginResponse loginResponse);

    void setUserInfo(UserModel userModel);
}
