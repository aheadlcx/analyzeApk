package com.tencent.connect.common;

import android.content.Intent;
import com.alipay.sdk.util.j;
import com.tencent.open.a.f;
import com.tencent.open.utils.g;
import com.tencent.open.utils.i;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.umeng.analytics.pro.b;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.security.ActionBarSecurityBindActivity;

public class UIListenerManager {
    private static UIListenerManager a = null;
    private Map<String, ApiTask> b = Collections.synchronizedMap(new HashMap());

    public class ApiTask {
        final /* synthetic */ UIListenerManager a;
        public IUiListener mListener;
        public int mRequestCode;

        public ApiTask(UIListenerManager uIListenerManager, int i, IUiListener iUiListener) {
            this.a = uIListenerManager;
            this.mRequestCode = i;
            this.mListener = iUiListener;
        }
    }

    public static UIListenerManager getInstance() {
        if (a == null) {
            a = new UIListenerManager();
        }
        return a;
    }

    private UIListenerManager() {
        if (this.b == null) {
            this.b = Collections.synchronizedMap(new HashMap());
        }
    }

    public Object setListenerWithRequestcode(int i, IUiListener iUiListener) {
        String a = g.a(i);
        if (a == null) {
            f.e("openSDK_LOG.UIListenerManager", "setListener action is null! rquestCode=" + i);
            return null;
        }
        synchronized (this.b) {
            ApiTask apiTask = (ApiTask) this.b.put(a, new ApiTask(this, i, iUiListener));
        }
        if (apiTask == null) {
            return null;
        }
        return apiTask.mListener;
    }

    public Object setListnerWithAction(String str, IUiListener iUiListener) {
        int a = g.a(str);
        if (a == -1) {
            f.e("openSDK_LOG.UIListenerManager", "setListnerWithAction fail, action = " + str);
            return null;
        }
        synchronized (this.b) {
            ApiTask apiTask = (ApiTask) this.b.put(str, new ApiTask(this, a, iUiListener));
        }
        if (apiTask == null) {
            return null;
        }
        return apiTask.mListener;
    }

    public IUiListener getListnerWithRequestCode(int i) {
        String a = g.a(i);
        if (a != null) {
            return getListnerWithAction(a);
        }
        f.e("openSDK_LOG.UIListenerManager", "getListner action is null! rquestCode=" + i);
        return null;
    }

    public IUiListener getListnerWithAction(String str) {
        if (str == null) {
            f.e("openSDK_LOG.UIListenerManager", "getListnerWithAction action is null!");
            return null;
        }
        synchronized (this.b) {
            ApiTask apiTask = (ApiTask) this.b.get(str);
            this.b.remove(str);
        }
        if (apiTask == null) {
            return null;
        }
        return apiTask.mListener;
    }

    public void handleDataToListener(Intent intent, IUiListener iUiListener) {
        String stringExtra;
        f.c("openSDK_LOG.UIListenerManager", "handleDataToListener");
        if (intent == null) {
            iUiListener.onCancel();
            return;
        }
        String stringExtra2 = intent.getStringExtra(Constants.KEY_ACTION);
        if ("action_login".equals(stringExtra2)) {
            int intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
            if (intExtra == 0) {
                stringExtra = intent.getStringExtra(Constants.KEY_RESPONSE);
                if (stringExtra != null) {
                    try {
                        iUiListener.onComplete(i.d(stringExtra));
                        return;
                    } catch (Throwable e) {
                        iUiListener.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra));
                        f.b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, json error", e);
                        return;
                    }
                }
                f.b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onComplete");
                iUiListener.onComplete(new JSONObject());
                return;
            }
            f.e("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onError = " + intExtra + "");
            iUiListener.onError(new UiError(intExtra, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
        } else if ("action_share".equals(stringExtra2)) {
            stringExtra2 = intent.getStringExtra(j.c);
            stringExtra = intent.getStringExtra(ActionBarSecurityBindActivity.KEY_RESPONSE);
            if ("cancel".equals(stringExtra2)) {
                iUiListener.onCancel();
            } else if (b.J.equals(stringExtra2)) {
                iUiListener.onError(new UiError(-6, "unknown error", stringExtra + ""));
            } else if (com.baidu.mobads.openad.c.b.COMPLETE.equals(stringExtra2)) {
                try {
                    if (stringExtra == null) {
                        stringExtra2 = "{\"ret\": 0}";
                    } else {
                        stringExtra2 = stringExtra;
                    }
                    iUiListener.onComplete(new JSONObject(stringExtra2));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    iUiListener.onError(new UiError(-4, "json error", stringExtra + ""));
                }
            }
        }
    }

    private IUiListener a(int i, IUiListener iUiListener) {
        if (i == Constants.REQUEST_LOGIN) {
            f.e("openSDK_LOG.UIListenerManager", "登录的接口回调不能重新构建，暂时无法提供，先记录下来这种情况是否存在");
        } else if (i == Constants.REQUEST_SOCIAL_API) {
            f.e("openSDK_LOG.UIListenerManager", "Social Api 的接口回调需要使用param来重新构建，暂时无法提供，先记录下来这种情况是否存在");
        } else if (i == Constants.REQUEST_SOCIAL_H5) {
            f.e("openSDK_LOG.UIListenerManager", "Social Api 的H5接口回调需要使用param来重新构建，暂时无法提供，先记录下来这种情况是否存在");
        }
        return iUiListener;
    }

    public boolean onActivityResult(int i, int i2, Intent intent, IUiListener iUiListener) {
        IUiListener iUiListener2;
        f.c("openSDK_LOG.UIListenerManager", "onActivityResult req=" + i + " res=" + i2);
        IUiListener listnerWithRequestCode = getListnerWithRequestCode(i);
        if (listnerWithRequestCode != null) {
            iUiListener2 = listnerWithRequestCode;
        } else if (iUiListener != null) {
            iUiListener2 = a(i, iUiListener);
        } else {
            f.e("openSDK_LOG.UIListenerManager", "onActivityResult can't find the listener");
            return false;
        }
        if (i2 != -1) {
            iUiListener2.onCancel();
        } else if (intent == null) {
            iUiListener2.onError(new UiError(-6, "onActivityResult intent data is null.", "onActivityResult intent data is null."));
            return true;
        } else {
            String stringExtra = intent.getStringExtra(Constants.KEY_ACTION);
            int intExtra;
            if ("action_login".equals(stringExtra)) {
                intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                if (intExtra == 0) {
                    stringExtra = intent.getStringExtra(Constants.KEY_RESPONSE);
                    if (stringExtra != null) {
                        try {
                            iUiListener2.onComplete(i.d(stringExtra));
                        } catch (Throwable e) {
                            iUiListener2.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra));
                            f.b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, json error", e);
                        }
                    } else {
                        f.b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onComplete");
                        iUiListener2.onComplete(new JSONObject());
                    }
                } else {
                    f.e("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onError = " + intExtra + "");
                    iUiListener2.onError(new UiError(intExtra, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
                }
            } else if ("action_share".equals(stringExtra)) {
                r0 = intent.getStringExtra(j.c);
                stringExtra = intent.getStringExtra(ActionBarSecurityBindActivity.KEY_RESPONSE);
                if ("cancel".equals(r0)) {
                    iUiListener2.onCancel();
                } else if (b.J.equals(r0)) {
                    iUiListener2.onError(new UiError(-6, "unknown error", stringExtra + ""));
                } else if (com.baidu.mobads.openad.c.b.COMPLETE.equals(r0)) {
                    try {
                        if (stringExtra == null) {
                            r0 = "{\"ret\": 0}";
                        } else {
                            r0 = stringExtra;
                        }
                        iUiListener2.onComplete(new JSONObject(r0));
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        iUiListener2.onError(new UiError(-4, "json error", stringExtra + ""));
                    }
                }
            } else {
                intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                if (intExtra == 0) {
                    r0 = intent.getStringExtra(Constants.KEY_RESPONSE);
                    if (r0 != null) {
                        try {
                            iUiListener2.onComplete(i.d(r0));
                        } catch (JSONException e3) {
                            iUiListener2.onError(new UiError(-4, Constants.MSG_JSON_ERROR, r0));
                        }
                    } else {
                        iUiListener2.onComplete(new JSONObject());
                    }
                } else {
                    iUiListener2.onError(new UiError(intExtra, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
                }
            }
        }
        return true;
    }
}
