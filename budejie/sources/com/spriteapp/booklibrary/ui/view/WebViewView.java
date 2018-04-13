package com.spriteapp.booklibrary.ui.view;

import com.spriteapp.booklibrary.base.BaseView;
import com.spriteapp.booklibrary.model.response.PayResponse;

public interface WebViewView extends BaseView<Void> {
    void setAliPayResult(PayResponse payResponse);
}
