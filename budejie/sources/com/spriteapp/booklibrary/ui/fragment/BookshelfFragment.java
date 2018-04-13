package com.spriteapp.booklibrary.ui.fragment;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.spriteapp.booklibrary.a;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.base.Base;
import com.spriteapp.booklibrary.base.BaseFragment;
import com.spriteapp.booklibrary.config.HuaXiSDK;
import com.spriteapp.booklibrary.d.b;
import com.spriteapp.booklibrary.d.c;
import com.spriteapp.booklibrary.d.d;
import com.spriteapp.booklibrary.enumeration.BookEnum;
import com.spriteapp.booklibrary.enumeration.UpdaterPayEnum;
import com.spriteapp.booklibrary.enumeration.UpdaterShelfEnum;
import com.spriteapp.booklibrary.listener.DeleteBookListener;
import com.spriteapp.booklibrary.model.AddBookModel;
import com.spriteapp.booklibrary.model.RegisterModel;
import com.spriteapp.booklibrary.model.UpdateProgressModel;
import com.spriteapp.booklibrary.model.UserModel;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.model.response.BookStoreResponse;
import com.spriteapp.booklibrary.model.response.LoginResponse;
import com.spriteapp.booklibrary.ui.adapter.BookShelfAdapter;
import com.spriteapp.booklibrary.ui.presenter.BookShelfPresenter;
import com.spriteapp.booklibrary.ui.view.BookShelfView;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.util.BookUtil;
import com.spriteapp.booklibrary.util.CollectionUtil;
import com.spriteapp.booklibrary.util.DialogUtil;
import com.spriteapp.booklibrary.util.ScreenUtil;
import com.spriteapp.booklibrary.util.SharedPreferencesUtil;
import com.spriteapp.booklibrary.util.ToastUtil;
import com.spriteapp.booklibrary.widget.recyclerview.SpaceItemDecoration;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

public class BookshelfFragment extends BaseFragment implements BookShelfView {
    private static final int HORIZONTAL_SPACE = 2;
    private static final int SHELF_SPAN_COUNT = 3;
    private static final String TAG = "BookshelfFragment";
    private static final int VERTICAL_SPACE = 5;
    private static Handler mHandler = new BookshelfFragment$2();
    private boolean isRecommendData;
    private BookShelfAdapter mAdapter;
    private b mBookDb;
    private List<BookDetailResponse> mBookList;
    private c mChapterDb;
    private d mContentDb;
    private AlertDialog mDeleteBookDialog;
    private int mDeleteBookId;
    private DeleteBookListener mDeleteListener = new BookshelfFragment$1(this);
    private int mDeletePosition;
    private BookShelfPresenter mPresenter;
    private RecyclerView mRecyclerView;

    public int getLayoutResId() {
        return e.book_reader_fragment_bookshelf;
    }

    public void initData() {
        SharedPreferencesUtil.getInstance().putBoolean("hua_xi_has_init_book_shelf", true);
        EventBus.getDefault().register(this);
        this.mBookDb = new b(getContext());
        this.mContentDb = new d(getMyContext());
        this.mBookList = new ArrayList();
        this.mBookList = this.mBookDb.c();
        this.mChapterDb = new c(getMyContext());
        this.mPresenter = new BookShelfPresenter();
        DialogUtil.setDialogListener(this.mDeleteListener);
        this.mPresenter.attachView(this);
        initRecyclerView();
        synchronizeBookProgress();
        if (!AppUtil.isLogin()) {
            RegisterModel registerModel = HuaXiSDK.getInstance().getRegisterModel();
            if (registerModel != null) {
                this.mPresenter.getLoginInfo(registerModel);
                return;
            }
        }
        this.mPresenter.getBookShelf();
    }

    private void initRecyclerView() {
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3, 1, false));
        this.mRecyclerView.addItemDecoration(new SpaceItemDecoration(ScreenUtil.dpToPxInt(2.0f), ScreenUtil.dpToPxInt(5.0f)));
        setAdapter();
    }

    private void setAdapter() {
        if (this.mBookList == null) {
            this.mBookList = new ArrayList();
        }
        if (this.mAdapter == null) {
            this.mAdapter = new BookShelfAdapter(getContext(), this.mBookList, 3, 2, false);
            this.mAdapter.setIsRecommendData(this.isRecommendData);
            this.mRecyclerView.setAdapter(this.mAdapter);
            this.mAdapter.setDeleteBook(false);
            this.mAdapter.setDeleteListener(this.mDeleteListener);
            return;
        }
        this.mAdapter.setDeleteBook(false);
        this.mAdapter.setIsRecommendData(this.isRecommendData);
        this.mAdapter.notifyDataSetChanged();
    }

    public boolean isDeleteBook() {
        return this.mAdapter != null && this.mAdapter.isDeleteBook();
    }

    public void setDeleteBook() {
        if (this.mAdapter != null) {
            this.mAdapter.setDeleteBook(false);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void configViews() {
    }

    public void findViewId() {
        this.mRecyclerView = (RecyclerView) this.mParentView.findViewById(a.d.book_reader_recycler_view);
    }

    protected void lazyLoad() {
        if (!this.mHasLoadedOnce && this.isPrepared) {
            this.mHasLoadedOnce = true;
            initData();
        }
    }

    public void onError(Throwable th) {
    }

    public void setData(Base<List<BookDetailResponse>> base) {
        List list = (List) base.getData();
        if (!CollectionUtil.isEmpty(list)) {
            BookEnum bookEnum;
            if (this.mBookList == null) {
                this.mBookList = new ArrayList();
            }
            int code = base.getCode();
            BookEnum bookEnum2;
            if (code == BookEnum.RECOMMEND_DATA.getValue()) {
                bookEnum2 = BookEnum.RECOMMEND_BOOK;
                this.isRecommendData = true;
                bookEnum = bookEnum2;
            } else if (code == BookEnum.MY_SHELF_DATA.getValue()) {
                bookEnum2 = BookEnum.MY_BOOK;
                this.isRecommendData = false;
                bookEnum = bookEnum2;
            } else {
                bookEnum = null;
            }
            BookEnum bookEnum3 = BookEnum.ADD_SHELF;
            if (CollectionUtil.isEmpty(this.mBookList)) {
                if (this.mBookList == null) {
                    this.mBookList = new ArrayList();
                }
                if (bookEnum != null) {
                    ((BookDetailResponse) list.get(0)).setIs_recommend_book(bookEnum.getValue());
                }
                this.mBookList.addAll(list);
                this.mBookDb.a(this.mBookList, bookEnum3, bookEnum);
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
            setAdapter();
        }
    }

    public void showNetWorkProgress() {
        showDialog();
    }

    public void disMissProgress() {
        dismissDialog();
    }

    public Context getMyContext() {
        return getContext();
    }

    public void setLoginInfo(LoginResponse loginResponse) {
        ToastUtil.showSingleToast("登录成功");
        this.mPresenter.getBookShelf();
        EventBus.getDefault().post(UpdaterPayEnum.UPDATE_LOGIN_INFO);
    }

    public void setDeleteBookResponse() {
        this.mBookDb.d(this.mDeleteBookId);
        this.mChapterDb.b(this.mDeleteBookId);
        this.mContentDb.a(this.mDeleteBookId);
        this.mAdapter.notifyItemRemoved(this.mDeletePosition);
        com.spriteapp.booklibrary.e.c.a().c(String.valueOf(this.mDeleteBookId));
        if (!CollectionUtil.isEmpty(this.mBookList)) {
            if (this.mDeletePosition >= 0 && this.mDeletePosition < this.mBookList.size()) {
                this.mBookList.remove(this.mDeletePosition);
            }
            if (CollectionUtil.isEmpty(this.mBookList)) {
                onEventMainThread(UpdaterShelfEnum.UPDATE_SHELF);
            }
        }
    }

    public void setAddShelfResponse() {
    }

    public void setBookDetail(BookDetailResponse bookDetailResponse) {
        if (bookDetailResponse != null) {
            ToastUtil.showSingleToast("加入书架成功");
            this.mBookDb.a(bookDetailResponse, BookEnum.ADD_SHELF);
            this.mBookDb.d();
            onEventMainThread(UpdaterShelfEnum.UPDATE_SHELF);
        }
    }

    public void setBookStoreData(BookStoreResponse bookStoreResponse) {
    }

    public void setUserInfo(UserModel userModel) {
    }

    public void onEventMainThread(UpdaterPayEnum updaterPayEnum) {
        if (updaterPayEnum == UpdaterPayEnum.UPDATE_LOGIN_OUT) {
            this.mBookList.clear();
            this.mPresenter.getBookShelf();
        }
    }

    public void onEventMainThread(UpdaterShelfEnum updaterShelfEnum) {
        Collection c = this.mBookDb.c();
        if (CollectionUtil.isEmpty(c)) {
            this.mPresenter.getBookShelf();
            return;
        }
        boolean z;
        if (this.mBookList == null) {
            this.mBookList = new ArrayList();
        }
        if (((BookDetailResponse) c.get(0)).getIs_recommend_book() == BookEnum.RECOMMEND_BOOK.getValue()) {
            z = true;
        } else {
            z = false;
        }
        this.isRecommendData = z;
        this.mBookList.clear();
        this.mBookList.addAll(c);
        setAdapter();
    }

    public void onEventMainThread(RegisterModel registerModel) {
        if (registerModel != null) {
            this.mPresenter.getLoginInfo(registerModel);
        }
    }

    public void onEventMainThread(AddBookModel addBookModel) {
        int i = 0;
        if (AppUtil.isLogin()) {
            int bookId = addBookModel.getBookId();
            BookDetailResponse c = this.mBookDb.c(bookId);
            if (BookUtil.isBookAddShelf(c)) {
                this.mBookDb.d();
                this.mAdapter.setDeleteBook(false);
                Collection c2 = this.mBookDb.c();
                this.mBookList.clear();
                this.mBookList.addAll(c2);
                this.mAdapter.notifyDataSetChanged();
                ToastUtil.showSingleToast("书架中已存在");
                return;
            }
            if (c != null && c.getIs_recommend_book() == BookEnum.RECOMMEND_BOOK.getValue()) {
                this.mBookDb.a(bookId, BookEnum.MY_BOOK.getValue());
            }
            this.mAdapter.setDeleteBook(false);
            BookShelfPresenter bookShelfPresenter = this.mPresenter;
            String str = "add";
            if (c != null) {
                i = c.getChapter_id();
            }
            bookShelfPresenter.addToShelf(bookId, str, i);
            if (c != null) {
                this.mBookDb.d();
                ToastUtil.showSingleToast("加入书架成功");
                this.mBookDb.a(bookId, BookEnum.ADD_SHELF);
                onEventMainThread(UpdaterShelfEnum.UPDATE_SHELF);
                return;
            }
            this.mPresenter.getBookDetail(bookId);
            return;
        }
        HuaXiSDK.getInstance().toLoginPage(this.mContext);
    }

    public void onEventMainThread(UpdateProgressModel updateProgressModel) {
        int bookId = updateProgressModel.getBookId();
        int chapterId = updateProgressModel.getChapterId();
        BookDetailResponse c = this.mBookDb.c(bookId);
        if (c != null) {
            c.setChapter_id(chapterId);
            c.setBook_chapter_total(updateProgressModel.getChapterTotal());
            c.setLast_chapter_index(updateProgressModel.getChapterIndex());
            this.mBookDb.a(c);
            mHandler.sendEmptyMessageDelayed(0, 1000);
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

    private void synchronizeBookProgress() {
        if (!CollectionUtil.isEmpty(this.mBookList) && ((BookDetailResponse) this.mBookList.get(0)).getIs_recommend_book() != BookEnum.RECOMMEND_BOOK.getValue()) {
            this.mPresenter.addOneMoreBookToShelf(BookUtil.getBookJson(this.mBookList));
        }
    }

    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        SharedPreferencesUtil.getInstance().putBoolean("hua_xi_has_init_book_shelf", false);
        if (this.mPresenter != null) {
            this.mPresenter.detachView();
        }
    }
}
