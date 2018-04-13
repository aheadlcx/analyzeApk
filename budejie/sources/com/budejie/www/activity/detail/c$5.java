package com.budejie.www.activity.detail;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.ali.auth.third.core.model.Constants;
import com.alipay.sdk.util.j;
import com.budejie.www.R;
import com.budejie.www.activity.OauthWeiboBaseAct;
import com.budejie.www.busevent.UpdateCommentAction;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.tencent.open.SocialConstants;
import com.umeng.analytics.MobclickAgent;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;

class c$5 extends Handler {
    final /* synthetic */ c a;

    c$5(c cVar) {
        this.a = cVar;
    }

    public void handleMessage(Message message) {
        int i = 0;
        try {
            String str;
            Map c;
            String str2;
            switch (message.what) {
                case 4:
                    str = (String) message.obj;
                    if (TextUtils.isEmpty(str)) {
                        an.a(c.c(this.a), c.c(this.a).getString(R.string.bind_failed), -1).show();
                        MobclickAgent.onEvent(c.c(this.a), "weibo_bind", "sina_faild");
                    } else {
                        try {
                            i = Integer.parseInt(str);
                        } catch (NumberFormatException e) {
                        }
                        if (i < 0) {
                            an.a(c.c(this.a), c.c(this.a).getString(R.string.bind_failed), -1).show();
                            MobclickAgent.onEvent(c.c(this.a), "weibo_bind", "sina_faild");
                        } else {
                            c = z.c(str);
                            if (c == null || c.isEmpty()) {
                                MobclickAgent.onEvent(c.c(this.a), "weibo_bind", "sina_faild");
                            } else {
                                str2 = (String) c.get("result_msg");
                                if ("0".equals((String) c.get(j.c))) {
                                    MobclickAgent.onEvent(c.c(this.a), "weibo_bind", "sina_success");
                                    c.e(this.a, (String) c.get("id"));
                                    c.C(this.a).a(c.B(this.a), c);
                                    ai.a(c.c(this.a), c.B(this.a), Constants.SERVICE_SCOPE_FLAG_VALUE);
                                    if (OauthWeiboBaseAct.mAccessToken != null) {
                                        c.C(this.a).a(c.B(this.a), OauthWeiboBaseAct.mAccessToken.e());
                                    }
                                    c.a(this.a, c.D(this.a).a(c.B(this.a)));
                                    c.D(this.a).a(c.c(this.a), c.d(this.a), "sina", c.B(this.a), c.E(this.a), c.F(this.a), c.G(this.a));
                                } else {
                                    an.a(c.c(this.a), str2, -1).show();
                                }
                            }
                        }
                    }
                    c.H(this.a).dismiss();
                    return;
                case 8:
                    str = (String) message.obj;
                    if (TextUtils.isEmpty(str)) {
                        c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.forwarfail), -1));
                        c.I(this.a).show();
                        return;
                    } else if (!"0".equals(str)) {
                        return;
                    } else {
                        return;
                    }
                case 9:
                    str = (String) message.obj;
                    if (TextUtils.isEmpty(str)) {
                        c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.forwarfail), -1));
                        c.I(this.a).show();
                        return;
                    } else if (!"0".equals(str)) {
                        c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.forwarfail), -1));
                        c.I(this.a).show();
                        return;
                    } else {
                        return;
                    }
                case 10:
                    str = (String) message.obj;
                    if (TextUtils.isEmpty((String) message.obj)) {
                        c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.bind_failed), -1));
                        c.I(this.a).show();
                        MobclickAgent.onEvent(c.c(this.a), "weibo_bind", "qzone_faild");
                    } else {
                        int parseInt;
                        try {
                            parseInt = Integer.parseInt(str);
                        } catch (NumberFormatException e2) {
                            parseInt = i;
                        }
                        if (parseInt < 0) {
                            c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.bind_failed), -1));
                            c.I(this.a).show();
                            MobclickAgent.onEvent(c.c(this.a), "weibo_bind", "qzone_faild");
                        } else {
                            c = z.c(str);
                            if (c == null || c.isEmpty()) {
                                MobclickAgent.onEvent(c.c(this.a), "weibo_bind", "qzone_faild");
                            } else {
                                str2 = (String) c.get("result_msg");
                                if ("0".equals((String) c.get(j.c))) {
                                    MobclickAgent.onEvent(c.c(this.a), "weibo_bind", "qzone_success");
                                    c.e(this.a, (String) c.get("id"));
                                    c.C(this.a).a(c.B(this.a), c);
                                    ai.a(c.c(this.a), c.B(this.a), Constants.SERVICE_SCOPE_FLAG_VALUE);
                                } else {
                                    an.a(c.c(this.a), str2, -1).show();
                                }
                            }
                        }
                    }
                    c.H(this.a).dismiss();
                    return;
                case 11:
                    str = (String) message.obj;
                    if (TextUtils.isEmpty(str)) {
                        c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.forwarfail), -1));
                        c.I(this.a).show();
                        return;
                    } else if (!"0".equals(str)) {
                        c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.forwarfail), -1));
                        c.I(this.a).show();
                        return;
                    } else {
                        return;
                    }
                case 12:
                    Map u = z.u((String) message.obj);
                    if (u != null) {
                        if ("1".equals((String) u.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY))) {
                            if (c.J(this.a) != null) {
                                c.J(this.a).a(true, (String) u.get(SocialConstants.PARAM_APP_DESC));
                            }
                            this.a.onEventMainThread(UpdateCommentAction.UPDATE_COMMENT);
                            c.q(this.a).setSelection(2);
                            return;
                        } else if (c.J(this.a) != null) {
                            c.J(this.a).a(false, (String) u.get(SocialConstants.PARAM_APP_DESC));
                            return;
                        } else {
                            return;
                        }
                    } else if (c.J(this.a) != null) {
                        c.J(this.a).a(false, "");
                        return;
                    } else {
                        return;
                    }
                case 13:
                    str = (String) message.obj;
                    if (TextUtils.isEmpty(str)) {
                        c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.commend_fail), -1));
                        c.I(this.a).show();
                    } else if ("0".equals(str)) {
                        c.F(this.a).a(c.K(this.a), true, R.string.sendsuccess);
                        c.d(this.a, true);
                        return;
                    } else {
                        c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.commend_fail), -1));
                        c.I(this.a).show();
                    }
                    c.d(this.a, false);
                    return;
                case 14:
                    c.c(this.a).finish();
                    return;
                case 19:
                    c.q(this.a).smoothScrollBy(1, 10);
                    c.q(this.a).smoothScrollBy(-1, 10);
                    c.L(this.a).requestLayout();
                    return;
                case 816:
                    CharSequence string = ((Bundle) message.obj).getString(j.c);
                    if (TextUtils.isEmpty(string)) {
                        c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.forwarfail), -1));
                        c.I(this.a).show();
                        return;
                    } else if ("0".equals(string)) {
                        c.x(this.a).sendEmptyMessage(9);
                        return;
                    } else {
                        c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.forwarfail), -1));
                        c.I(this.a).show();
                        return;
                    }
                case 817:
                    c.F(this.a).a(((Integer) message.obj).intValue());
                    return;
                case 930:
                    c.F(this.a).a(c.K(this.a), false, R.string.sendfail);
                    c.d(this.a, false);
                    return;
                case 931:
                    c.F(this.a).a(c.K(this.a));
                    if (!((Boolean) message.obj).booleanValue()) {
                        return;
                    }
                    return;
                default:
                    return;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        e3.printStackTrace();
    }
}
