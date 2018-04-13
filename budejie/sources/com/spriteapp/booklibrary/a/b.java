package com.spriteapp.booklibrary.a;

import com.spriteapp.booklibrary.base.Base;
import com.spriteapp.booklibrary.model.UserModel;
import com.spriteapp.booklibrary.model.response.BookChapterResponse;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.model.response.BookStoreResponse;
import com.spriteapp.booklibrary.model.response.LoginResponse;
import com.spriteapp.booklibrary.model.response.PayResponse;
import com.spriteapp.booklibrary.model.response.SubscriberContent;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.b.c;
import retrofit2.b.e;
import retrofit2.b.f;
import retrofit2.b.o;
import retrofit2.b.t;

public interface b {
    @f(a = "book_shelf")
    Observable<Base<List<BookDetailResponse>>> a();

    @f(a = "book_detail?")
    Observable<Base<BookDetailResponse>> a(@t(a = "book_id") int i);

    @f(a = "book_content?")
    Observable<Base<SubscriberContent>> a(@t(a = "book_id") int i, @t(a = "chapter_id") int i2, @t(a = "auto_sub") int i3);

    @e
    @o(a = "book_shelf")
    Observable<Base<Void>> a(@c(a = "book_ids") int i, @c(a = "u_action") String str);

    @e
    @o(a = "book_shelf")
    Observable<Base<Void>> a(@c(a = "book_id") int i, @c(a = "u_action") String str, @c(a = "chapter_id") int i2);

    @f(a = "pay_appalipay")
    Observable<Base<PayResponse>> a(@t(a = "product_id") String str);

    @e
    @o(a = "book_shelf")
    Observable<Base<Void>> a(@c(a = "u_action") String str, @c(a = "data") String str2);

    @f(a = "login_channel?")
    retrofit2.b<Base<LoginResponse>> a(@t(a = "channel_id") int i, @t(a = "user_id") String str, @t(a = "nickname") String str2, @t(a = "avatar") String str3, @t(a = "mobile") String str4);

    @f(a = "book_catalog?")
    Observable<Base<List<BookChapterResponse>>> b(@t(a = "book_id") int i);

    @e
    @o(a = "book_comment")
    Observable<Base<Void>> b(@c(a = "book_id") int i, @c(a = "content") String str);

    @f(a = "login_logout")
    Observable<Base<Void>> b(@t(a = "sn") String str, @t(a = "token") String str2);

    @f(a = "book_store")
    retrofit2.b<BookStoreResponse> b(@t(a = "format") String str);

    @f(a = "user_info")
    Observable<Base<UserModel>> c(@t(a = "format") String str);
}
