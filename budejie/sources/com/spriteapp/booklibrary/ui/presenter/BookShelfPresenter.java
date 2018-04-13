package com.spriteapp.booklibrary.ui.presenter;

import com.google.gson.Gson;
import com.spriteapp.booklibrary.a.a;
import com.spriteapp.booklibrary.base.Base;
import com.spriteapp.booklibrary.base.BasePresenter;
import com.spriteapp.booklibrary.config.HuaXiSDK;
import com.spriteapp.booklibrary.enumeration.ApiCodeEnum;
import com.spriteapp.booklibrary.enumeration.LoginStateEnum;
import com.spriteapp.booklibrary.enumeration.UpdateTextStateEnum;
import com.spriteapp.booklibrary.model.RegisterModel;
import com.spriteapp.booklibrary.model.UserModel;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.model.response.BookStoreResponse;
import com.spriteapp.booklibrary.model.response.LoginResponse;
import com.spriteapp.booklibrary.ui.view.BookShelfView;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.util.NetworkUtil;
import de.greenrobot.event.EventBus;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import retrofit2.b;
import retrofit2.d;
import retrofit2.l;

public class BookShelfPresenter implements BasePresenter<BookShelfView> {
    private static final String TAG = "BookShelfPresenter";
    private Disposable mDisposable;
    private b<Base<LoginResponse>> mLoginCall;
    private b<BookStoreResponse> mStoreCall;
    private BookShelfView mView;

    public void attachView(BookShelfView bookShelfView) {
        if (this.mView == null) {
            this.mView = bookShelfView;
        }
    }

    public void detachView() {
        this.mView = null;
        if (this.mDisposable != null) {
            this.mDisposable.dispose();
            this.mDisposable = null;
        }
        if (this.mLoginCall != null) {
            this.mLoginCall.b();
            this.mLoginCall = null;
        }
        if (this.mStoreCall != null) {
            this.mStoreCall.b();
            this.mStoreCall = null;
        }
    }

    public void getBookShelf() {
        if (this.mView != null && AppUtil.isNetAvailable(this.mView.getMyContext())) {
            a.a().a.a().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Base<List<BookDetailResponse>>>() {
                public void onSubscribe(@NonNull Disposable disposable) {
                    BookShelfPresenter.this.mDisposable = disposable;
                }

                public void onNext(@NonNull Base<List<BookDetailResponse>> base) {
                    if (BookShelfPresenter.this.mView != null) {
                        BookShelfPresenter.this.mView.setData(base);
                    }
                }

                public void onError(@NonNull Throwable th) {
                }

                public void onComplete() {
                }
            });
        }
    }

    public void getLoginInfo(RegisterModel registerModel) {
        getLoginInfo(registerModel, true);
    }

    public void getLoginInfo(RegisterModel registerModel, final boolean z) {
        if (this.mView != null && AppUtil.isNetAvailable(this.mView.getMyContext())) {
            if (z) {
                this.mView.showNetWorkProgress();
            }
            this.mLoginCall = a.a().a.a(HuaXiSDK.getInstance().getChannelId(), registerModel.getUserId(), registerModel.getUserName(), registerModel.getAvatar(), registerModel.getMobile());
            HuaXiSDK.mLoginState = LoginStateEnum.LOADING;
            EventBus.getDefault().post(UpdateTextStateEnum.UPDATE_TEXT_STATE);
            this.mLoginCall.a(new d<Base<LoginResponse>>() {
                public void onResponse(b<Base<LoginResponse>> bVar, l<Base<LoginResponse>> lVar) {
                    HuaXiSDK.mLoginState = LoginStateEnum.SUCCESS;
                    EventBus.getDefault().post(UpdateTextStateEnum.UPDATE_TEXT_STATE);
                    if (BookShelfPresenter.this.mView != null) {
                        if (z) {
                            BookShelfPresenter.this.mView.disMissProgress();
                        }
                        Base base = (Base) lVar.e();
                        if (base != null) {
                            LoginResponse loginResponse = (LoginResponse) base.getData();
                            if (loginResponse != null) {
                                BookShelfPresenter.this.mView.setLoginInfo(loginResponse);
                            }
                        }
                    }
                }

                public void onFailure(b<Base<LoginResponse>> bVar, Throwable th) {
                    if (BookShelfPresenter.this.mView != null && z) {
                        BookShelfPresenter.this.mView.disMissProgress();
                    }
                    if (BookShelfPresenter.this.mView != null) {
                        BookShelfPresenter.this.mView.onError(th);
                    }
                    HuaXiSDK.mLoginState = LoginStateEnum.FAILED;
                    EventBus.getDefault().post(UpdateTextStateEnum.UPDATE_TEXT_STATE);
                }
            });
        }
    }

    public void deleteBook(int i) {
        if (this.mView != null && AppUtil.isNetAvailable(this.mView.getMyContext())) {
            a.a().a.a(i, "del").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Base<Void>>() {
                public void onComplete() {
                }

                public void onSubscribe(@NonNull Disposable disposable) {
                    BookShelfPresenter.this.mDisposable = disposable;
                }

                public void onError(Throwable th) {
                }

                public void onNext(Base<Void> base) {
                    if (BookShelfPresenter.this.mView != null && base.getCode() == ApiCodeEnum.SUCCESS.getValue()) {
                        BookShelfPresenter.this.mView.setDeleteBookResponse();
                    }
                }
            });
        }
    }

    public void addToShelf(int i, String str, int i2) {
        addToShelf(i, str, i2, true);
    }

    public void addToShelf(int i, String str, int i2, final boolean z) {
        if (this.mView != null && NetworkUtil.isAvailable(this.mView.getMyContext())) {
            a.a().a.a(i, str, i2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Base<Void>>() {
                public void onComplete() {
                }

                public void onSubscribe(@NonNull Disposable disposable) {
                    BookShelfPresenter.this.mDisposable = disposable;
                }

                public void onError(Throwable th) {
                }

                public void onNext(Base<Void> base) {
                    if (BookShelfPresenter.this.mView != null && base.getCode() == ApiCodeEnum.SUCCESS.getValue() && z) {
                        BookShelfPresenter.this.mView.setAddShelfResponse();
                    }
                }
            });
        }
    }

    public void getBookDetail(int i) {
        if (this.mView != null && AppUtil.isNetAvailable(this.mView.getMyContext())) {
            a.a().a.a(i).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Base<BookDetailResponse>>() {
                public void onComplete() {
                }

                public void onSubscribe(@NonNull Disposable disposable) {
                    BookShelfPresenter.this.mDisposable = disposable;
                }

                public void onError(Throwable th) {
                }

                public void onNext(Base<BookDetailResponse> base) {
                    if (base.getCode() == ApiCodeEnum.SUCCESS.getValue() && BookShelfPresenter.this.mView != null) {
                        BookShelfPresenter.this.mView.setBookDetail((BookDetailResponse) base.getData());
                    }
                }
            });
        }
    }

    public void addOneMoreBookToShelf(String str) {
        if (this.mView != null && NetworkUtil.isAvailable(this.mView.getMyContext())) {
            a.a().a.a("multi", str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Base<Void>>() {
                public void onComplete() {
                }

                public void onSubscribe(@NonNull Disposable disposable) {
                    BookShelfPresenter.this.mDisposable = disposable;
                }

                public void onError(Throwable th) {
                }

                public void onNext(Base<Void> base) {
                }
            });
        }
    }

    public void getBookStoreData() {
        if (this.mView != null) {
            if (NetworkUtil.isAvailable(this.mView.getMyContext())) {
                this.mStoreCall = a.a().a.b("json");
                this.mStoreCall.a(new d<BookStoreResponse>() {
                    public void onResponse(b<BookStoreResponse> bVar, l<BookStoreResponse> lVar) {
                        if (BookShelfPresenter.this.mView != null) {
                            BookStoreResponse bookStoreResponse = (BookStoreResponse) lVar.e();
                            if (bookStoreResponse != null && bookStoreResponse.getCode() == ApiCodeEnum.SUCCESS.getValue()) {
                                com.spriteapp.booklibrary.e.a.a(new Gson().toJson(bookStoreResponse));
                                BookShelfPresenter.this.mView.setBookStoreData(bookStoreResponse);
                            }
                        }
                    }

                    public void onFailure(b<BookStoreResponse> bVar, Throwable th) {
                        if (BookShelfPresenter.this.mView != null) {
                            BookShelfPresenter.this.mView.onError(th);
                        }
                    }
                });
                return;
            }
            this.mView.onError(null);
        }
    }

    public void getUserInfo() {
        if (this.mView != null && NetworkUtil.isAvailable(this.mView.getMyContext())) {
            a.a().a.c("json").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Base<UserModel>>() {
                public void onComplete() {
                }

                public void onSubscribe(@NonNull Disposable disposable) {
                    BookShelfPresenter.this.mDisposable = disposable;
                }

                public void onError(Throwable th) {
                    if (BookShelfPresenter.this.mView != null) {
                        BookShelfPresenter.this.mView.onError(th);
                    }
                }

                public void onNext(Base<UserModel> base) {
                    if (BookShelfPresenter.this.mView != null) {
                        if (base.getCode() == ApiCodeEnum.SUCCESS.getValue()) {
                            BookShelfPresenter.this.mView.setUserInfo((UserModel) base.getData());
                        } else {
                            BookShelfPresenter.this.mView.setUserInfo(null);
                        }
                    }
                }
            });
        }
    }
}
