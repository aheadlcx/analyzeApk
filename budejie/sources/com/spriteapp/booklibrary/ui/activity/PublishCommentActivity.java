package com.spriteapp.booklibrary.ui.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.base.Base;
import com.spriteapp.booklibrary.enumeration.UpdateCommentEnum;
import com.spriteapp.booklibrary.ui.presenter.PublishCommentPresenter;
import com.spriteapp.booklibrary.ui.view.PublishCommentView;
import com.spriteapp.booklibrary.util.InputUtil;
import com.spriteapp.booklibrary.util.StringUtil;
import com.spriteapp.booklibrary.util.ToastUtil;
import de.greenrobot.event.EventBus;

public class PublishCommentActivity extends TitleActivity implements PublishCommentView {
    public static final String BOOK_ID_TAG = "bookIdTag";
    private int mBookId;
    private EditText mCommentEditText;
    private PublishCommentPresenter mPresenter;
    private TextView mSendTextView;

    public void initData() {
        setTitle("发表评论");
        addRightView();
        this.mBookId = getIntent().getIntExtra(BOOK_ID_TAG, 0);
        this.mPresenter = new PublishCommentPresenter();
        this.mPresenter.attachView((PublishCommentView) this);
        addListener();
    }

    private void addListener() {
        this.mSendTextView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String obj = PublishCommentActivity.this.mCommentEditText.getText().toString();
                if (StringUtil.isEmpty(obj) || StringUtil.isEmpty(obj.trim())) {
                    ToastUtil.showSingleToast("请输入评论内容");
                } else {
                    PublishCommentActivity.this.mPresenter.sendComment(PublishCommentActivity.this.mBookId, obj);
                }
            }
        });
        this.mContainerLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                InputUtil.showSoftInput(PublishCommentActivity.this, PublishCommentActivity.this.mCommentEditText);
            }
        });
    }

    private void addRightView() {
        View inflate = LayoutInflater.from(this).inflate(e.book_reader_common_right_text_view, null);
        this.mSendTextView = (TextView) inflate.findViewById(d.book_reader_common_right_text_view);
        this.mRightLayout.addView(inflate);
        this.mSendTextView.setText("发送");
    }

    public void addContentView() {
        this.mContainerLayout.addView(getLayoutInflater().inflate(e.book_reader_activity_publish_comment, null), -1, -1);
    }

    public void findViewId() {
        super.findViewId();
        this.mCommentEditText = (EditText) findViewById(d.book_reader_comment_edit_text);
    }

    public void onError(Throwable th) {
    }

    public void setData(Base<Void> base) {
        finish();
        EventBus.getDefault().post(UpdateCommentEnum.UPDATE_COMMENT);
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
}
