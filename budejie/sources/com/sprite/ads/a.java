package com.sprite.ads;

import com.sprite.ads.internal.bean.ResponseBody;
import com.sprite.ads.internal.bean.ResponseData;
import com.sprite.ads.internal.bean.ResponseHead;
import com.sprite.ads.internal.exception.ErrorCode;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.internal.net.ADNetException;
import com.sprite.ads.internal.net.b;
import okhttp3.e;
import org.json.JSONException;

public class a implements Constants {
    private e a;
    private boolean b = false;
    private boolean c = false;

    public interface a {
        void loadFailure(ErrorCode errorCode);

        void loadSuccess(ResponseBody responseBody);
    }

    private boolean a(ResponseBody responseBody, a aVar) {
        if (responseBody.config == null) {
            ADLog.d("confing 为空");
            aVar.loadFailure(new ErrorCode(12));
            return false;
        } else if (responseBody.config.sequence == null || responseBody.config.sequence.isEmpty()) {
            ADLog.d("广告轮播列表[sequence]为空");
            aVar.loadFailure(new ErrorCode(12));
            return false;
        } else if (responseBody.data != null && !responseBody.data.isEmpty()) {
            return true;
        } else {
            ADLog.d("广告数据为空");
            aVar.loadFailure(new ErrorCode(12));
            return false;
        }
    }

    protected void a(ResponseData responseData, a aVar) {
        try {
            ResponseHead parseResponseHead = responseData.parseResponseHead(responseData.getRespData());
            if (parseResponseHead.isOk()) {
                ResponseBody parseResponseBody = responseData.parseResponseBody(responseData.getRespData());
                if (!this.c && a(parseResponseBody, aVar)) {
                    aVar.loadSuccess(parseResponseBody);
                    return;
                }
                return;
            }
            ADLog.d("服务器返回状态错误:" + parseResponseHead.status + "," + parseResponseHead.msg);
            if (!this.c) {
                aVar.loadFailure(new ErrorCode(11));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ADLog.d("JSON 解析失败");
            if (!this.c) {
                aVar.loadFailure(new ErrorCode(13));
            }
        }
    }

    public void a(String str, final a aVar) {
        if (SpriteAD.isInit()) {
            this.a = com.sprite.ads.internal.net.a.b(str, new b(this) {
                final /* synthetic */ a b;

                public void a(ResponseData responseData) {
                    this.b.b = true;
                    this.b.a(responseData, aVar);
                }

                public void a(ADNetException aDNetException) {
                    aDNetException.printStackTrace();
                    ADLog.d("广告请求失败" + aDNetException.toString());
                    this.b.b = true;
                    if (!this.b.c) {
                        aVar.loadFailure(new ErrorCode(11));
                    }
                }
            });
        } else {
            aVar.loadFailure(new ErrorCode(10));
        }
    }

    public boolean a() {
        return this.b;
    }

    public void b() {
        if (this.a != null) {
            this.a.c();
        }
        this.c = true;
        ADLog.d("取消广告请求");
    }
}
