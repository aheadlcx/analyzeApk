package com.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.n;
import com.budejie.www.util.an;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;

public class b {
    private String a;
    private String b;
    private String c;
    private String d;

    public void a(BookDetailResponse bookDetailResponse) {
        this.a = bookDetailResponse.getBook_name();
        this.b = bookDetailResponse.getBook_intro();
        this.c = bookDetailResponse.getBook_share_url();
        this.d = bookDetailResponse.getBook_image();
    }

    public void a(final Context context, final IWXAPI iwxapi, final boolean z) {
        Observable.just(Integer.valueOf(1)).map(new Function<Integer, Boolean>(this) {
            final /* synthetic */ b d;

            public /* synthetic */ Object apply(@NonNull Object obj) throws Exception {
                return a((Integer) obj);
            }

            public Boolean a(@NonNull Integer num) throws Exception {
                WXMediaMessage wXMediaMessage = new WXMediaMessage();
                IMediaObject wXWebpageObject = new WXWebpageObject();
                wXWebpageObject.webpageUrl = this.d.c;
                wXMediaMessage.mediaObject = wXWebpageObject;
                wXMediaMessage.title = this.d.a;
                wXMediaMessage.description = this.d.b;
                Bitmap b = an.b(context, this.d.d);
                if (b == null) {
                    b = an.i(context);
                }
                wXMediaMessage.thumbData = an.a(b, 30, false);
                BaseReq req = new Req();
                req.transaction = this.d.a("webpage");
                req.message = wXMediaMessage;
                req.scene = z ? 0 : 1;
                return Boolean.valueOf(iwxapi.sendReq(req));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>(this) {
            final /* synthetic */ b a;
            private Disposable b;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(@NonNull Object obj) {
                a((Boolean) obj);
            }

            public void onSubscribe(@NonNull Disposable disposable) {
                this.b = disposable;
            }

            public void a(@NonNull Boolean bool) {
            }

            public void onError(@NonNull Throwable th) {
                this.b.dispose();
            }

            public void onComplete() {
                this.b.dispose();
            }
        });
    }

    public void a(Context context, Tencent tencent) {
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        bundle.putString("title", this.a);
        bundle.putString("summary", this.b);
        bundle.putString("imageUrl", this.d);
        bundle.putString("targetUrl", this.c);
        bundle.putString("appName", "百思不得姐");
        bundle.putString("site", "百思不得姐100336987");
        tencent.shareToQQ((Activity) context, bundle, new IUiListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onComplete(Object obj) {
            }

            public void onError(UiError uiError) {
            }

            public void onCancel() {
            }
        });
    }

    public void b(Context context, Tencent tencent) {
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        bundle.putString("title", this.a);
        bundle.putString("summary", this.b);
        bundle.putString("targetUrl", this.c);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.d);
        bundle.putStringArrayList("imageUrl", arrayList);
        bundle.putString("appName", "百思不得姐");
        tencent.shareToQzone((Activity) context, bundle, new IUiListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onComplete(Object obj) {
            }

            public void onError(UiError uiError) {
            }

            public void onCancel() {
            }
        });
    }

    public void a(Context context) {
        ListItemObject listItemObject = new ListItemObject();
        listItemObject.setTitle(this.a);
        listItemObject.setContent(this.b);
        listItemObject.setWeixin_url(this.c);
        listItemObject.setImgUrl(this.d);
        n.a(context, listItemObject);
    }

    public void b(Context context) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:"));
        StringBuilder stringBuilder = new StringBuilder();
        intent.putExtra("sms_body", stringBuilder.append("分享自#百思不得姐#").append(this.b).append(this.c).toString());
        context.startActivity(intent);
    }

    private String a(String str) {
        return str == null ? String.valueOf(System.currentTimeMillis()) : str + System.currentTimeMillis();
    }
}
