package com.budejie.www.i.a;

import android.os.Build;
import android.text.TextUtils;
import com.budejie.www.a.a.a;
import com.budejie.www.a.b;
import com.budejie.www.activity.label.enumeration.LabelUserEnum;
import com.budejie.www.activity.label.response.CommonInfoBean;
import com.budejie.www.activity.label.response.LabelUser;
import com.budejie.www.http.j;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.umeng.analytics.MobclickAgent;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import okhttp3.e;

public class d {
    private e a;
    private com.budejie.www.i.b.d b;
    private Disposable c;

    public void a(com.budejie.www.i.b.d dVar) {
        this.b = dVar;
    }

    public void a() {
        if (this.a != null) {
            this.a.c();
            this.a = null;
        }
        if (this.c != null) {
            this.c.dispose();
        }
        this.b = null;
    }

    public void a(String str) {
        if (this.a != null) {
            this.a.c();
            this.a = null;
        }
        if (this.b != null) {
            this.b.a_();
            this.a = b.a().a(j.n(str), new a(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void a(e eVar, IOException iOException) {
                    if (this.a.b != null) {
                        this.a.b.g();
                    }
                }

                public void a(String str) {
                    if (this.a.b != null) {
                        this.a.b.g();
                    }
                    if (!TextUtils.isEmpty(str)) {
                        Object obj;
                        try {
                            obj = (LabelUser) new Gson().fromJson(str, LabelUser.class);
                        } catch (JsonSyntaxException e) {
                            if (!(this.a.b == null || this.a.b.h() == null)) {
                                MobclickAgent.onEvent(this.a.b.h(), "E03-A07", "版块成员列表解析异常json:" + str + " 机型:" + Build.MODEL);
                            }
                            obj = null;
                        }
                        if (this.a.b != null && obj != null) {
                            this.a.b.a(obj);
                        }
                    }
                }
            });
        }
    }

    public void b(String str) {
        if (this.b != null) {
            b.b().a.a(str, LabelUserEnum.APPLY_MODERATOR.getValue()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CommonInfoBean>(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(@NonNull Object obj) {
                    a((CommonInfoBean) obj);
                }

                public void onSubscribe(@NonNull Disposable disposable) {
                    this.a.c = disposable;
                }

                public void a(@NonNull CommonInfoBean commonInfoBean) {
                    if (this.a.b != null) {
                        this.a.b.a(commonInfoBean);
                    }
                }

                public void onError(@NonNull Throwable th) {
                    if (this.a.c != null) {
                        this.a.c.dispose();
                    }
                }

                public void onComplete() {
                    if (this.a.c != null) {
                        this.a.c.dispose();
                    }
                }
            });
        }
    }
}
