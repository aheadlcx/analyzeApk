package com.spriteapp.booklibrary.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.base.Base;
import com.spriteapp.booklibrary.base.BaseFragment;
import com.spriteapp.booklibrary.config.HuaXiSDK;
import com.spriteapp.booklibrary.d.b;
import com.spriteapp.booklibrary.e.a;
import com.spriteapp.booklibrary.e.d;
import com.spriteapp.booklibrary.enumeration.BookEnum;
import com.spriteapp.booklibrary.enumeration.UpdateNightMode;
import com.spriteapp.booklibrary.enumeration.UpdateTextStateEnum;
import com.spriteapp.booklibrary.enumeration.UpdaterPayEnum;
import com.spriteapp.booklibrary.model.AddBookModel;
import com.spriteapp.booklibrary.model.BookList;
import com.spriteapp.booklibrary.model.RegisterModel;
import com.spriteapp.booklibrary.model.UpdateProgressModel;
import com.spriteapp.booklibrary.model.UserModel;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.model.response.BookStoreResponse;
import com.spriteapp.booklibrary.model.response.LoginResponse;
import com.spriteapp.booklibrary.model.store.BookTypeResponse;
import com.spriteapp.booklibrary.model.store.HotSellResponse;
import com.spriteapp.booklibrary.recyclerView.adapter.MultiAdapter;
import com.spriteapp.booklibrary.recyclerView.decorate.Visitable;
import com.spriteapp.booklibrary.recyclerView.model.HotSellModel;
import com.spriteapp.booklibrary.recyclerView.model.StoreUser;
import com.spriteapp.booklibrary.ui.presenter.BookShelfPresenter;
import com.spriteapp.booklibrary.ui.view.BookShelfView;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.util.BookUtil;
import com.spriteapp.booklibrary.util.CollectionUtil;
import com.spriteapp.booklibrary.util.SharedPreferencesUtil;
import com.spriteapp.booklibrary.util.StringUtil;
import com.spriteapp.booklibrary.util.ToastUtil;
import com.spriteapp.booklibrary.widget.xrecyclerview.XRecyclerView;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

public class NativeBookStoreFragment extends BaseFragment implements BookShelfView {
    private static final String TAG = "NativeBookStoreFragment";
    private boolean isFirstInitData;
    private b mBookDb;
    private List<BookDetailResponse> mBookList;
    private List<HotSellResponse> mClasses;
    private Context mContext;
    private List<Visitable> mDataList;
    private HotSellModel mHotSellModel;
    private MultiAdapter mMultiAdapter;
    private BookList mMyBook;
    private BookShelfPresenter mPresenter;
    private XRecyclerView mRecyclerView;
    private String mStoreJson;
    private List<BookList> mTypeBookList;
    private List<BookTypeResponse> mTypeList;
    private StoreUser mUserModel;
    private View mView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView != null) {
            if (this.mView.getParent() != null) {
                ((ViewGroup) this.mView.getParent()).removeView(this.mView);
            }
            if (this.mPresenter == null) {
                this.mPresenter = new BookShelfPresenter();
            }
            this.mPresenter.attachView(this);
            EventBus.getDefault().register(this);
            if (!AppUtil.isLogin()) {
                RegisterModel registerModel = HuaXiSDK.getInstance().getRegisterModel();
                if (registerModel != null) {
                    this.mPresenter.getLoginInfo(registerModel, false);
                    return this.mView;
                }
            }
            this.mPresenter.getUserInfo();
            return this.mView;
        }
        this.mView = layoutInflater.inflate(e.book_reader_fragment_native_book_store, viewGroup, false);
        findViewId();
        configViews();
        initData();
        return this.mView;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
    }

    public int getLayoutResId() {
        return e.book_reader_fragment_native_book_store;
    }

    public void initData() {
        EventBus.getDefault().register(this);
        this.mContext = getContext();
        this.mPresenter = new BookShelfPresenter();
        this.mPresenter.attachView(this);
        this.mBookDb = new b(this.mContext);
        this.mBookList = new ArrayList();
        this.isFirstInitData = true;
        this.mBookList = this.mBookDb.c();
        initSourceData();
        if (!AppUtil.isLogin()) {
            RegisterModel registerModel = HuaXiSDK.getInstance().getRegisterModel();
            if (registerModel != null) {
                this.mPresenter.getLoginInfo(registerModel, false);
                return;
            }
        }
        if (!StringUtil.isEmpty(this.mStoreJson)) {
            this.mPresenter.getUserInfo();
        }
        this.mPresenter.getBookShelf();
    }

    private void initSourceData() {
        this.mStoreJson = a.a();
        if (!StringUtil.isEmpty(this.mStoreJson)) {
            try {
                BookStoreResponse bookStoreResponse = (BookStoreResponse) new Gson().fromJson(this.mStoreJson, BookStoreResponse.class);
                if (bookStoreResponse != null) {
                    this.mClasses = bookStoreResponse.getClasses();
                    this.mTypeList = bookStoreResponse.getLists();
                }
            } catch (Exception e) {
            }
        }
        this.mDataList = new ArrayList();
        this.mUserModel = new StoreUser();
        this.mDataList.add(this.mUserModel);
        this.mMyBook = new BookList();
        this.mMyBook.setMyShelf(true);
        this.mMyBook.setDetailResponseList(this.mBookList);
        this.mDataList.add(this.mMyBook);
        this.mHotSellModel = new HotSellModel();
        this.mHotSellModel.setResponseList(this.mClasses);
        this.mDataList.add(this.mHotSellModel);
        if (this.mTypeList == null) {
            this.mTypeList = new ArrayList();
        }
        this.mTypeBookList = new ArrayList();
        if (!CollectionUtil.isEmpty(this.mTypeList)) {
            for (BookTypeResponse bookTypeResponse : this.mTypeList) {
                BookList bookList = new BookList();
                bookList.setTypeResponse(bookTypeResponse);
                this.mTypeBookList.add(bookList);
                this.mDataList.add(bookList);
            }
        }
        if (StringUtil.isEmpty(this.mStoreJson)) {
            this.mRecyclerView.refresh();
            return;
        }
        setAdapter();
        this.mPresenter.getBookStoreData();
    }

    private void setAdapter() {
        if (this.mDataList == null) {
            this.mDataList = new ArrayList();
        }
        if (this.mMultiAdapter == null) {
            this.mMultiAdapter = new MultiAdapter(getContext(), this.mDataList);
            this.mRecyclerView.setAdapter(this.mMultiAdapter);
            return;
        }
        this.mMultiAdapter.notifyDataSetChanged();
    }

    public XRecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    public void configViews() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.mRecyclerView.setLoadingMoreEnabled(false);
        this.mRecyclerView.setPullRefreshEnabled(true);
        this.mRecyclerView.setLoadingListener(new NativeBookStoreFragment$1(this));
        d a = com.spriteapp.booklibrary.e.b.a();
        if (a != null) {
            this.mRecyclerView.setBackgroundColor(a.a());
            this.mRecyclerView.setRefreshHeaderBackground();
        }
    }

    public void findViewId() {
        this.mRecyclerView = (XRecyclerView) this.mView.findViewById(com.spriteapp.booklibrary.a.d.book_reader_store_recycler_view);
    }

    protected void lazyLoad() {
    }

    public void onError(Throwable th) {
        this.mRecyclerView.refreshComplete();
    }

    public void setData(Base<List<BookDetailResponse>> base) {
        this.mRecyclerView.refreshComplete();
        List list = (List) base.getData();
        if (!CollectionUtil.isEmpty(list)) {
            BookEnum bookEnum;
            if (this.mBookList == null) {
                this.mBookList = new ArrayList();
            }
            int code = base.getCode();
            if (code == BookEnum.RECOMMEND_DATA.getValue()) {
                bookEnum = BookEnum.RECOMMEND_BOOK;
            } else if (code == BookEnum.MY_SHELF_DATA.getValue()) {
                bookEnum = BookEnum.MY_BOOK;
            } else {
                bookEnum = null;
            }
            BookEnum bookEnum2 = BookEnum.ADD_SHELF;
            if (CollectionUtil.isEmpty(this.mBookList)) {
                if (this.mBookList == null) {
                    this.mBookList = new ArrayList();
                }
                if (bookEnum != null) {
                    ((BookDetailResponse) list.get(0)).setIs_recommend_book(bookEnum.getValue());
                }
                this.mBookList.addAll(list);
                this.mBookDb.a(this.mBookList, bookEnum2, bookEnum);
            } else if (((BookDetailResponse) this.mBookList.get(0)).getIs_recommend_book() != BookEnum.RECOMMEND_BOOK.getValue()) {
                if (code == BookEnum.RECOMMEND_DATA.getValue()) {
                    list.clear();
                }
                synchronizeMyBook(list);
            } else if (code == BookEnum.MY_SHELF_DATA.getValue()) {
                this.mBookDb.e();
                this.mBookList.clear();
                this.mBookList.addAll(this.mBookDb.c());
                this.mBookDb.d();
                this.mBookList.addAll(list);
                synchronizeLoginBook();
            } else if (code == BookEnum.RECOMMEND_DATA.getValue()) {
                synchronizeRecommendBook(list);
            }
            if (!CollectionUtil.isEmpty(this.mBookList)) {
                this.mMyBook.setDetailResponseList(this.mBookList);
            }
            if (!(CollectionUtil.isEmpty(this.mBookList) || this.mDataList.contains(this.mMyBook))) {
                this.mDataList.add(1, this.mMyBook);
            }
            setAdapter();
        } else if (CollectionUtil.isEmpty(this.mBookList) && this.mDataList.contains(this.mMyBook)) {
            this.mDataList.remove(this.mMyBook);
            setAdapter();
        }
    }

    private void synchronizeLoginBook() {
        Collection linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(this.mBookList);
        this.mBookList.clear();
        this.mBookList.addAll(linkedHashSet);
        if (!CollectionUtil.isEmpty(this.mBookList)) {
            this.mBookDb.a(this.mBookList, BookEnum.ADD_SHELF, BookEnum.MY_BOOK);
            ((BookDetailResponse) this.mBookList.get(0)).setIs_recommend_book(BookEnum.MY_BOOK.getValue());
            synchronizeBookProgress();
        }
    }

    private void synchronizeBookProgress() {
        if (!CollectionUtil.isEmpty(this.mBookList) && ((BookDetailResponse) this.mBookList.get(0)).getIs_recommend_book() != BookEnum.RECOMMEND_BOOK.getValue()) {
            this.mPresenter.addOneMoreBookToShelf(BookUtil.getBookJson(this.mBookList));
        }
    }

    private void synchronizeMyBook(List<BookDetailResponse> list) {
        if (this.mBookList.size() < list.size()) {
            Object diffBook = CollectionUtil.getDiffBook(this.mBookList, list);
            this.mBookList.addAll(diffBook);
            this.mBookDb.a(diffBook, BookEnum.ADD_SHELF, BookEnum.MY_BOOK);
        } else if (this.mBookList.size() > list.size()) {
            List arrayList = new ArrayList();
            arrayList.addAll(this.mBookList);
            this.mPresenter.addOneMoreBookToShelf(BookUtil.getBookJson(CollectionUtil.getDiffBook(list, arrayList)));
        }
    }

    private void synchronizeRecommendBook(List<BookDetailResponse> list) {
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        arrayList.addAll(this.mBookList);
        arrayList2.addAll(list);
        this.mBookList.addAll(CollectionUtil.getDiffBook(arrayList, arrayList2));
        if (this.mBookList.size() > list.size()) {
            arrayList.clear();
            arrayList.addAll(this.mBookList);
            arrayList = CollectionUtil.getDiffBook(list, arrayList);
            this.mBookDb.a(arrayList);
            this.mBookList = CollectionUtil.getDiffBook(arrayList, this.mBookList);
        }
        this.mBookDb.a(this.mBookList, BookEnum.NOT_ADD_SHELF, BookEnum.RECOMMEND_BOOK);
    }

    public void onEventMainThread(RegisterModel registerModel) {
        if (registerModel != null) {
            this.mPresenter.getLoginInfo(registerModel, false);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.isFirstInitData) {
            this.isFirstInitData = false;
        } else {
            queryBookData();
        }
    }

    private void queryBookData() {
        Collection c = this.mBookDb.c();
        if (this.mBookList == null) {
            this.mBookList = new ArrayList();
        }
        this.mBookList.clear();
        this.mBookList.addAll(c);
        this.mMyBook.setDetailResponseList(this.mBookList);
        if (CollectionUtil.isEmpty(this.mBookList) && this.mDataList.contains(this.mMyBook)) {
            this.mDataList.remove(this.mMyBook);
        }
        if (CollectionUtil.isEmpty(this.mBookList) && this.mPresenter != null) {
            this.mPresenter.getBookShelf();
        }
        if (!(CollectionUtil.isEmpty(this.mBookList) || this.mDataList.contains(this.mMyBook))) {
            this.mDataList.add(1, this.mMyBook);
        }
        setAdapter();
    }

    public void onEventMainThread(UpdateNightMode updateNightMode) {
        d a = com.spriteapp.booklibrary.e.b.a();
        if (a != null) {
            this.mRecyclerView.setBackgroundColor(a.a());
            if (this.mMultiAdapter != null) {
                this.mMultiAdapter.notifyDataSetChanged();
            }
        }
    }

    public void onEventMainThread(AddBookModel addBookModel) {
        if (!SharedPreferencesUtil.getInstance().getBoolean("hua_xi_has_init_book_shelf")) {
            if (AppUtil.isLogin()) {
                int bookId = addBookModel.getBookId();
                BookDetailResponse c = this.mBookDb.c(bookId);
                if (BookUtil.isBookAddShelf(c)) {
                    this.mBookDb.d();
                    Collection c2 = this.mBookDb.c();
                    this.mBookList.clear();
                    this.mBookList.addAll(c2);
                    this.mMyBook.setDetailResponseList(this.mBookList);
                    setAdapter();
                    ToastUtil.showSingleToast("书架中已存在");
                    return;
                }
                if (c != null && c.getIs_recommend_book() == BookEnum.RECOMMEND_BOOK.getValue()) {
                    this.mBookDb.a(bookId, BookEnum.MY_BOOK.getValue());
                }
                this.mPresenter.addToShelf(bookId, "add", c == null ? 0 : c.getChapter_id());
                if (c != null) {
                    this.mBookDb.d();
                    ToastUtil.showSingleToast("加入书架成功");
                    this.mBookDb.a(bookId, BookEnum.ADD_SHELF);
                    return;
                }
                this.mPresenter.getBookDetail(bookId);
                return;
            }
            HuaXiSDK.getInstance().toLoginPage(this.mContext);
        }
    }

    public void onEventMainThread(UpdaterPayEnum updaterPayEnum) {
        this.mPresenter.getUserInfo();
        if (updaterPayEnum == UpdaterPayEnum.UPDATE_LOGIN_INFO || updaterPayEnum == UpdaterPayEnum.UPDATE_LOGIN_OUT) {
            if (updaterPayEnum == UpdaterPayEnum.UPDATE_LOGIN_OUT && this.mBookList != null) {
                this.mBookList.clear();
            }
            this.mPresenter.getBookShelf();
        }
    }

    public void onEventMainThread(UpdateTextStateEnum updateTextStateEnum) {
        if (this.mMultiAdapter != null) {
            this.mMultiAdapter.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(UpdateProgressModel updateProgressModel) {
        if (!SharedPreferencesUtil.getInstance().getBoolean("hua_xi_has_init_book_shelf")) {
            int bookId = updateProgressModel.getBookId();
            int chapterId = updateProgressModel.getChapterId();
            BookDetailResponse c = this.mBookDb.c(bookId);
            if (c != null) {
                c.setChapter_id(chapterId);
                c.setBook_chapter_total(updateProgressModel.getChapterTotal());
                c.setLast_chapter_index(updateProgressModel.getChapterIndex());
                this.mBookDb.a(c);
            }
        }
    }

    public void showNetWorkProgress() {
    }

    public void disMissProgress() {
    }

    public Context getMyContext() {
        return this.mContext;
    }

    public void setLoginInfo(LoginResponse loginResponse) {
        EventBus.getDefault().post(UpdaterPayEnum.UPDATE_LOGIN_INFO);
    }

    public void setDeleteBookResponse() {
    }

    public void setAddShelfResponse() {
    }

    public void setBookDetail(BookDetailResponse bookDetailResponse) {
        if (bookDetailResponse != null) {
            ToastUtil.showSingleToast("加入书架成功");
            this.mBookDb.a(bookDetailResponse, BookEnum.ADD_SHELF);
            this.mBookDb.d();
        }
    }

    public void setBookStoreData(BookStoreResponse bookStoreResponse) {
        this.mRecyclerView.refreshComplete();
        if (bookStoreResponse != null) {
            this.mClasses = bookStoreResponse.getClasses();
            this.mHotSellModel.setResponseList(this.mClasses);
            Collection lists = bookStoreResponse.getLists();
            if (!CollectionUtil.isEmpty(lists)) {
                if (this.mTypeList == null) {
                    this.mTypeList = new ArrayList();
                }
                this.mTypeList.clear();
                this.mTypeList.addAll(lists);
                if (!CollectionUtil.isEmpty(this.mTypeBookList)) {
                    for (BookList bookList : this.mTypeBookList) {
                        if (this.mDataList.contains(bookList)) {
                            this.mDataList.remove(bookList);
                        }
                    }
                }
                if (this.mTypeBookList == null) {
                    this.mTypeBookList = new ArrayList();
                }
                this.mTypeBookList.clear();
                for (BookTypeResponse bookTypeResponse : this.mTypeList) {
                    BookList bookList2 = new BookList();
                    bookList2.setTypeResponse(bookTypeResponse);
                    this.mTypeBookList.add(bookList2);
                    this.mDataList.add(bookList2);
                }
                setAdapter();
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (this.mPresenter != null) {
            this.mPresenter.detachView();
        }
    }

    public void setUserInfo(UserModel userModel) {
        this.mUserModel.setUserModel(userModel);
        setAdapter();
    }
}
