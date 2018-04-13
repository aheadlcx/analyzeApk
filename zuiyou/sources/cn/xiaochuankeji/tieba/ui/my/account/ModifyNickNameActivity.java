package cn.xiaochuankeji.tieba.ui.my.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.account.ModifyNicknameJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.my.a;
import cn.xiaochuankeji.tieba.ui.widget.f;
import org.aspectj.a.b.b;
import rx.j;

public class ModifyNickNameActivity extends a implements TextWatcher {
    private static final org.aspectj.lang.a.a o = null;
    private cn.xiaochuankeji.tieba.api.account.a l;
    private String m;
    private String n;

    static {
        k();
    }

    private static void k() {
        b bVar = new b("ModifyNickNameActivity.java", ModifyNickNameActivity.class);
        o = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.my.account.ModifyNickNameActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 41);
    }

    public static void a(Activity activity, String str, String str2, int i) {
        Intent intent = new Intent(activity, ModifyNickNameActivity.class);
        intent.putExtra("keyName", str);
        intent.putExtra("key_prompt", str2);
        activity.startActivityForResult(intent, i);
    }

    static final void a(ModifyNickNameActivity modifyNickNameActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        modifyNickNameActivity.l = new cn.xiaochuankeji.tieba.api.account.a();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(o, this, this, bundle);
        c.c().a(new a(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected boolean a(Bundle bundle) {
        this.d = "昵称";
        this.f = "完成";
        return true;
    }

    protected void d_() {
        this.i.setMaxLines(1);
        this.m = getIntent().getStringExtra("keyName");
        this.n = this.m;
        this.i.setText(this.m);
        this.i.setSelection(this.i.length());
        this.j.setText(getIntent().getStringExtra("key_prompt"));
    }

    protected void onResume() {
        super.onResume();
        this.i.addTextChangedListener(this);
    }

    protected void onPause() {
        super.onPause();
        this.i.removeTextChangedListener(this);
    }

    protected void a(String str) {
        final String trim = str.trim();
        if (b(trim) > 20) {
            g.a("昵称长度最大不能超过20个字符");
        } else if (TextUtils.isEmpty(str)) {
            g.a("昵称不能为空");
        } else {
            f.a("提示", "确认修改昵称?", this, new f.a(this) {
                final /* synthetic */ ModifyNickNameActivity b;

                public void a(boolean z) {
                    if (z) {
                        cn.xiaochuankeji.tieba.ui.widget.g.a(this.b);
                        this.b.l.a(trim).a(rx.a.b.a.a()).b(new j<ModifyNicknameJson>(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public /* synthetic */ void onNext(Object obj) {
                                a((ModifyNicknameJson) obj);
                            }

                            public void onCompleted() {
                            }

                            public void onError(Throwable th) {
                                com.izuiyou.a.a.b.e(th);
                                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a.b);
                                if (th instanceof ClientErrorException) {
                                    g.a(th.getMessage());
                                } else {
                                    g.a("网络错误:" + th.getMessage());
                                }
                            }

                            public void a(ModifyNicknameJson modifyNicknameJson) {
                                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a.b);
                                if (modifyNicknameJson != null) {
                                    cn.xiaochuankeji.tieba.background.modules.a.b i = cn.xiaochuankeji.tieba.background.a.i();
                                    i.a(modifyNicknameJson.mid);
                                    try {
                                        i.q().setName(modifyNicknameJson.memberInfo.getNickName());
                                        cn.xiaochuankeji.tieba.background.a.i().t();
                                        g.a("昵称修改成功");
                                        this.a.b.setResult(-1);
                                        this.a.b.finish();
                                    } catch (Exception e) {
                                        com.izuiyou.a.a.b.e(e);
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        String obj = editable.toString();
        if (TextUtils.isEmpty(obj) || obj.length() <= 10) {
            this.n = editable.toString();
        } else if (!this.n.equalsIgnoreCase(obj)) {
            if (b(obj) <= 20) {
                this.n = editable.toString();
            } else if (obj.length() < this.n.length()) {
                this.n = editable.toString();
            } else {
                this.i.setText(this.n);
                this.i.setSelection(this.n.length());
            }
        }
    }

    private int b(String str) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        if (str.length() <= 10) {
            return str.length();
        }
        for (char c : str.toCharArray()) {
            if (c <= 'ÿ') {
                i++;
            } else {
                i += 2;
            }
        }
        return i;
    }
}
