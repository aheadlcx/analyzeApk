package com.spriteapp.booklibrary.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.alibaba.baichuan.android.trade.adapter.alipay.AlibcAlipay;
import com.alipay.sdk.app.PayTask;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.b.c;
import com.spriteapp.booklibrary.base.Base;
import com.spriteapp.booklibrary.enumeration.PayResultEnum;
import com.spriteapp.booklibrary.enumeration.UpdateCommentEnum;
import com.spriteapp.booklibrary.enumeration.UpdaterPayEnum;
import com.spriteapp.booklibrary.model.AddBookModel;
import com.spriteapp.booklibrary.model.PayResult;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.model.response.PayResponse;
import com.spriteapp.booklibrary.ui.presenter.WebViewPresenter;
import com.spriteapp.booklibrary.ui.view.WebViewView;
import com.spriteapp.booklibrary.util.ActivityUtil;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.util.StringUtil;
import com.spriteapp.booklibrary.util.WebViewUtil;
import com.spriteapp.booklibrary.widget.ReaderWebView;
import de.greenrobot.event.EventBus;
import java.util.Map;

public class WebViewActivity extends TitleActivity implements WebViewView {
    public static final String IS_H5_PAY_TAG = "isH5PayTag";
    public static final String LOAD_URL_TAG = "LoadUrlTag";
    private static final int SDK_PAY_FLAG = 1;
    private static final String TAG = "WebViewActivity";
    private boolean isH5Pay;
    WebChromeClient mChromeClient = new WebChromeClient() {
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            WebViewActivity.this.setTitle(str);
        }
    };
    WebViewClient mClient = new WebViewClient() {
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            WebViewActivity.this.showDialog();
        }

        public void onPageFinished(WebView webView, String str) {
            WebViewActivity.this.dismissDialog();
        }
    };
    private Context mContext;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    PayResult payResult = new PayResult((Map) message.obj);
                    payResult.getResult();
                    if (TextUtils.equals(payResult.getResultStatus(), AlibcAlipay.PAY_SUCCESS_CODE)) {
                        Toast.makeText(WebViewActivity.this, "支付成功", 0).show();
                        EventBus.getDefault().post(UpdaterPayEnum.UPDATE_PAY_RESULT);
                        if (WebViewActivity.this.isH5Pay) {
                            EventBus.getDefault().post(PayResultEnum.SUCCESS);
                            WebViewActivity.this.finish();
                            return;
                        }
                        WebViewActivity.this.mWebView.reload();
                        return;
                    }
                    Toast.makeText(WebViewActivity.this, "支付失败,请重试", 0).show();
                    if (WebViewActivity.this.isH5Pay) {
                        EventBus.getDefault().post(PayResultEnum.FAILED);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private WebViewPresenter mPresenter;
    private String mUrl;
    private ReaderWebView mWebView;
    private c mWebViewCallback = new c() {
        public void getAliPay(String str) {
            WebViewActivity.this.mPresenter.requestAliPay(str);
        }

        public void addBookToShelf(int i) {
            AddBookModel addBookModel = new AddBookModel();
            addBookModel.setBookId(i);
            EventBus.getDefault().post(addBookModel);
        }

        public void freeRead(int i, int i2) {
            BookDetailResponse bookDetailResponse = new BookDetailResponse();
            bookDetailResponse.setBook_id(i);
            bookDetailResponse.setChapter_id(i2);
            ActivityUtil.toReadActivity(WebViewActivity.this.mContext, bookDetailResponse);
        }
    };

    public void initData() {
        this.mContext = this;
        EventBus.getDefault().register(this);
        this.mPresenter = new WebViewPresenter();
        this.mPresenter.attachView((WebViewView) this);
        getDataFromTopPage();
        if (!StringUtil.isEmpty(this.mUrl) && AppUtil.isNetAvailable(this)) {
            this.mWebView.setWebChromeClient(this.mChromeClient);
            this.mWebView.loadPage(this.mUrl, this.mClient);
            WebViewUtil.getInstance().setWebViewCallback(this.mWebViewCallback);
        }
    }

    private void getDataFromTopPage() {
        Intent intent = getIntent();
        if (intent != null) {
            this.mUrl = intent.getStringExtra(LOAD_URL_TAG);
            this.isH5Pay = intent.getBooleanExtra(IS_H5_PAY_TAG, false);
        }
    }

    public void findViewId() {
        super.findViewId();
        this.mWebView = (ReaderWebView) findViewById(d.book_reader_web_view);
    }

    public void addContentView() {
        this.mContainerLayout.addView(getLayoutInflater().inflate(e.book_reader_activity_web_view, null), -1, -1);
    }

    public void onBackPressed() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void onError(Throwable th) {
        disMissProgress();
    }

    public void setData(Base<Void> base) {
    }

    public void showNetWorkProgress() {
        showDialog();
    }

    public void disMissProgress() {
        dismissDialog();
    }

    public Context getMyContext() {
        return this;
    }

    public void setAliPayResult(PayResponse payResponse) {
        if (payResponse != null) {
            final String pay_str = payResponse.getPay_str();
            new Thread(new Runnable() {
                public void run() {
                    Map payV2 = new PayTask(WebViewActivity.this).payV2(pay_str, true);
                    Message message = new Message();
                    message.what = 1;
                    message.obj = payV2;
                    WebViewActivity.this.mHandler.sendMessage(message);
                }
            }).start();
        }
    }

    public void onEventMainThread(UpdateCommentEnum updateCommentEnum) {
        if (this.mWebView != null) {
            this.mWebView.reload();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
        EventBus.getDefault().unregister(this);
        this.mPresenter.detachView();
    }
}
