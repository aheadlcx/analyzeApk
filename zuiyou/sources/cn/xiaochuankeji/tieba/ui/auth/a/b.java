package cn.xiaochuankeji.tieba.ui.auth.a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.modules.a.i;
import cn.xiaochuankeji.tieba.background.utils.share.c;
import com.izuiyou.auth.api.AuthService;
import com.izuiyou.auth.api.entity.WXToken;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;
import com.tencent.mm.opensdk.modelmsg.SendAuth.Resp;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import okhttp3.aa;
import okhttp3.w;
import okhttp3.y$a;
import org.json.JSONObject;
import rx.d;
import rx.d$a;
import rx.e;
import rx.j;

public class b {
    private a a;
    private a b;
    private i c;
    private int d;
    private Activity e;
    private boolean f = false;
    private IUiListener g = new IUiListener(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onComplete(Object obj) {
            com.izuiyou.a.a.b.c("onComplete: " + obj.toString());
            if (obj instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) obj;
                String optString = jSONObject.optString("openid");
                String optString2 = jSONObject.optString("access_token");
                String optString3 = jSONObject.optString("expires_in");
                if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                    this.a.a(1, false, 0, "QQ返回数据不合法");
                    return;
                }
                com.izuiyou.auth.a.a.a().a(optString, optString2, optString3);
                this.a.a(optString2, optString);
                return;
            }
            this.a.a(1, false, 0, "QQ返回数据格式错误");
        }

        public void onError(UiError uiError) {
            com.izuiyou.a.a.b.d("onError: " + uiError);
            this.a.a(1, false, 0, "授权失败");
        }

        public void onCancel() {
            com.izuiyou.a.a.b.c("onCancel");
            this.a.a(1, false, 0, null);
        }
    };

    public interface a {
        void e();
    }

    public void a() {
        this.b = null;
        this.c = null;
        this.e = null;
        if (this.a != null) {
            this.a.b();
            this.a = null;
        }
    }

    public void a(Activity activity, int i, a aVar, i iVar) {
        com.izuiyou.a.a.b.d("loginType: " + i);
        this.e = activity;
        this.d = i;
        this.b = aVar;
        this.c = iVar;
        switch (i) {
            case 1:
                b();
                return;
            case 2:
            case 4:
                c.a().b();
                return;
            case 3:
                a(i);
                return;
            default:
                return;
        }
    }

    private void b() {
        if (this.e != null && !this.e.isFinishing()) {
            com.izuiyou.auth.a.a.a().a(this.e, this.g);
        }
    }

    private void a(final String str, final String str2, final String str3) {
        this.f = false;
        com.izuiyou.auth.a.a.a().a(new IUiListener(this) {
            final /* synthetic */ b d;

            public void onComplete(Object obj) {
                if (obj == null || !(obj instanceof JSONObject)) {
                    this.d.a(1, false, 0, "获取QQ详细信息返回格式失败!");
                    return;
                }
                this.d.c();
                JSONObject jSONObject = (JSONObject) obj;
                int optInt = jSONObject.optInt("ret", -1);
                if (optInt == 0) {
                    com.izuiyou.a.a.b.d("qq auth ok, with open id:" + str);
                    if (TextUtils.isEmpty(jSONObject.optString("nickname", ""))) {
                        com.izuiyou.a.a.b.d("no qq user info:" + obj.toString());
                    }
                    try {
                        jSONObject.put("openid", str);
                    } catch (Exception e) {
                    }
                    this.d.a(str, str2, str3, (JSONObject) obj);
                } else if (optInt == 100030) {
                    Runnable anonymousClass1 = new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            com.izuiyou.auth.a.a.a().b(this.a.d.e, new IUiListener(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onComplete(Object obj) {
                                    this.a.a.d.a(str, str2, str3, (JSONObject) obj);
                                }

                                public void onError(UiError uiError) {
                                }

                                public void onCancel() {
                                }
                            });
                        }
                    };
                    if (this.d.e != null && !this.d.e.isFinishing()) {
                        this.d.e.runOnUiThread(anonymousClass1);
                    }
                } else {
                    this.d.a(1, false, 0, ((JSONObject) obj).optString("msg", "QQ用户数据请求失败"));
                }
            }

            public void onError(UiError uiError) {
                this.d.a(1, false, 0, "获取QQ用户详细信息失败: " + uiError.errorCode);
            }

            public void onCancel() {
                this.d.a(1, false, 0, "获取QQ用户详细信息失败!");
            }
        });
    }

    private void a(final String str, final String str2) {
        d.b(new d$a<JSONObject>(this) {
            final /* synthetic */ b b;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super JSONObject> jVar) {
                try {
                    aa a = new w().a(new y$a().a("https://graph.qq.com/oauth2.0/me?unionid=1&access_token=" + str).a().d()).a();
                    if (a.c()) {
                        String string = a.g().string();
                        jVar.onNext(new JSONObject(string.substring(string.indexOf(40) + 1, string.lastIndexOf(41))));
                    } else {
                        jVar.onError(new IOException(a.d()));
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                    jVar.onError(e);
                } catch (Throwable e2) {
                    jVar.onError(e2);
                }
                jVar.onCompleted();
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new j<JSONObject>(this) {
            final /* synthetic */ b c;

            public /* synthetic */ void onNext(Object obj) {
                a((JSONObject) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.c.a(1, false, 0, "解析unionid失败");
            }

            public void a(JSONObject jSONObject) {
                this.c.a(str2, str, jSONObject.optString("unionid"));
            }
        });
    }

    private void a(final int i) {
        com.izuiyou.auth.sina.c.a().a(this.e, new WeiboAuthListener(this) {
            final /* synthetic */ b b;

            public void onComplete(Bundle bundle) {
                Oauth2AccessToken parseAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
                com.izuiyou.a.a.b.c("onComplete");
                if (parseAccessToken == null || !parseAccessToken.isSessionValid()) {
                    com.izuiyou.a.a.b.e("data invalid");
                    this.b.a(i, false, 0, "微博返回数据错误");
                    return;
                }
                this.b.c();
                this.b.a(i, parseAccessToken.getUid(), parseAccessToken.getToken());
            }

            public void onWeiboException(WeiboException weiboException) {
                com.izuiyou.a.a.b.d("onWeiboException");
                this.b.a(i, false, 0, "授权失败");
            }

            public void onCancel() {
                com.izuiyou.a.a.b.c("onCancel");
                this.b.a(i, false, 0, null);
            }
        });
    }

    public void a(Resp resp) {
        switch (resp.errCode) {
            case -2:
                a(2, false, 0, null);
                return;
            case 0:
                a(resp.code);
                return;
            default:
                a(2, false, 0, "授权失败");
                return;
        }
    }

    private void a(String str) {
        com.izuiyou.a.a.b.c("getWXTokenInfo");
        c();
        ((AuthService) cn.xiaochuankeji.tieba.network.c.b(AuthService.class)).wxAuth("wx16516ad81c31d872", "e775e0d0290030256c55970c1b097694", str, "authorization_code").a(rx.f.a.c()).a(new e<WXToken>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((WXToken) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                com.izuiyou.a.a.b.e(th);
                this.a.a(2, false, 0, "获取授权信息失败，" + (th != null ? th.getMessage() : null));
            }

            public void a(WXToken wXToken) {
                com.izuiyou.a.a.b.c(wXToken);
                if (wXToken == null) {
                    this.a.a(2, false, 0, "获取授权信息失败，null");
                } else if (TextUtils.isEmpty(wXToken.openid) || TextUtils.isEmpty(wXToken.access_token)) {
                    this.a.a(2, false, 0, "解析授权信息失败");
                } else {
                    this.a.a(2, wXToken.openid, wXToken.access_token);
                }
            }
        });
    }

    private void a(String str, String str2, String str3, JSONObject jSONObject) {
        if (this.c != null && this.a == null) {
            this.a = new a(1, "1103537147", str, str2, b(jSONObject.toString()), new i(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a(int i, boolean z, int i2, String str) {
                    this.a.a = null;
                    if (this.a.c != null) {
                        this.a.c.a(2, z, i2, str);
                        com.izuiyou.a.a.b.d("server login result:" + z);
                        this.a.c = null;
                    }
                }

                public void a(int i) {
                    this.a.c.a(2);
                    this.a.c = null;
                }
            });
            this.a.a(str3);
            this.a.a();
        }
    }

    private String b(String str) {
        String a = cn.htjyb.c.e.a(BaseApplication.getAppContext());
        return cn.htjyb.c.d.a(str, cn.htjyb.c.d.c(a + AppController.instance().deviceID()));
    }

    private void a(final int i, String str, String str2) {
        if (this.c != null && this.a == null) {
            String str3 = (i == 1 || i == 5) ? "1103537147" : i == 3 ? "4117400114" : (i == 2 || i == 4) ? "wx16516ad81c31d872" : null;
            this.a = new a(i, str3, str, str2, null, new i(this) {
                final /* synthetic */ b b;

                public void a(int i, boolean z, int i2, String str) {
                    this.b.a = null;
                    if (this.b.c != null) {
                        this.b.c.a(i, z, i2, str);
                        this.b.c = null;
                    }
                }

                public void a(int i) {
                    this.b.c.a(i);
                    this.b.c = null;
                    this.b.a = null;
                }
            });
            this.a.a();
        }
    }

    private void c() {
        if (this.b != null) {
            this.b.e();
            this.b = null;
        }
    }

    private void a(int i, boolean z, int i2, String str) {
        if (this.c != null) {
            this.c.a(i, z, i2, str);
        }
    }

    public void a(int i, int i2, Intent intent) {
        if (3 == this.d) {
            com.izuiyou.auth.sina.c.a().a(i, i2, intent);
        } else if (1 == this.d || 5 == this.d) {
            com.izuiyou.auth.a.a.a().a(i, i2, intent, this.g);
        }
    }
}
