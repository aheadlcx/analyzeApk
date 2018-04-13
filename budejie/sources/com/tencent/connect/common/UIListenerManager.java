package com.tencent.connect.common;

import android.content.Intent;
import com.alipay.sdk.util.j;
import com.baidu.mobads.openad.c.b;
import com.tencent.open.a.f;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.umeng.analytics.pro.x;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class UIListenerManager {
    private static final String TAG = "openSDK_LOG.UIListenerManager";
    private static UIListenerManager mInstance = null;
    private Map<String, UIListenerManager$ApiTask> mListenerMap = Collections.synchronizedMap(new HashMap());

    public static UIListenerManager getInstance() {
        if (mInstance == null) {
            mInstance = new UIListenerManager();
        }
        return mInstance;
    }

    private UIListenerManager() {
        if (this.mListenerMap == null) {
            this.mListenerMap = Collections.synchronizedMap(new HashMap());
        }
    }

    public Object setListenerWithRequestcode(int i, IUiListener iUiListener) {
        String actionFromRequestcode = SystemUtils.getActionFromRequestcode(i);
        if (actionFromRequestcode == null) {
            f.e(TAG, "setListener action is null! rquestCode=" + i);
            return null;
        }
        synchronized (this.mListenerMap) {
            UIListenerManager$ApiTask uIListenerManager$ApiTask = (UIListenerManager$ApiTask) this.mListenerMap.put(actionFromRequestcode, new UIListenerManager$ApiTask(this, i, iUiListener));
        }
        if (uIListenerManager$ApiTask == null) {
            return null;
        }
        return uIListenerManager$ApiTask.mListener;
    }

    public Object setListnerWithAction(String str, IUiListener iUiListener) {
        int requestCodeFromCallback = SystemUtils.getRequestCodeFromCallback(str);
        if (requestCodeFromCallback == -1) {
            f.e(TAG, "setListnerWithAction fail, action = " + str);
            return null;
        }
        synchronized (this.mListenerMap) {
            UIListenerManager$ApiTask uIListenerManager$ApiTask = (UIListenerManager$ApiTask) this.mListenerMap.put(str, new UIListenerManager$ApiTask(this, requestCodeFromCallback, iUiListener));
        }
        if (uIListenerManager$ApiTask == null) {
            return null;
        }
        return uIListenerManager$ApiTask.mListener;
    }

    public IUiListener getListnerWithRequestCode(int i) {
        String actionFromRequestcode = SystemUtils.getActionFromRequestcode(i);
        if (actionFromRequestcode != null) {
            return getListnerWithAction(actionFromRequestcode);
        }
        f.e(TAG, "getListner action is null! rquestCode=" + i);
        return null;
    }

    public IUiListener getListnerWithAction(String str) {
        if (str == null) {
            f.e(TAG, "getListnerWithAction action is null!");
            return null;
        }
        synchronized (this.mListenerMap) {
            UIListenerManager$ApiTask uIListenerManager$ApiTask = (UIListenerManager$ApiTask) this.mListenerMap.get(str);
            this.mListenerMap.remove(str);
        }
        if (uIListenerManager$ApiTask == null) {
            return null;
        }
        return uIListenerManager$ApiTask.mListener;
    }

    public void handleDataToListener(Intent intent, IUiListener iUiListener) {
        String stringExtra;
        f.c(TAG, "handleDataToListener");
        if (intent == null) {
            iUiListener.onCancel();
            return;
        }
        String stringExtra2 = intent.getStringExtra(Constants.KEY_ACTION);
        if (SystemUtils.ACTION_LOGIN.equals(stringExtra2)) {
            int intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
            if (intExtra == 0) {
                stringExtra = intent.getStringExtra(Constants.KEY_RESPONSE);
                if (stringExtra != null) {
                    try {
                        iUiListener.onComplete(Util.parseJson(stringExtra));
                        return;
                    } catch (Throwable e) {
                        iUiListener.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra));
                        f.b(TAG, "OpenUi, onActivityResult, json error", e);
                        return;
                    }
                }
                f.b(TAG, "OpenUi, onActivityResult, onComplete");
                iUiListener.onComplete(new JSONObject());
                return;
            }
            f.e(TAG, "OpenUi, onActivityResult, onError = " + intExtra + "");
            iUiListener.onError(new UiError(intExtra, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
        } else if (SystemUtils.ACTION_SHARE.equals(stringExtra2)) {
            stringExtra2 = intent.getStringExtra(j.c);
            stringExtra = intent.getStringExtra("response");
            if (com.umeng.update.net.f.c.equals(stringExtra2)) {
                iUiListener.onCancel();
            } else if (x.aF.equals(stringExtra2)) {
                iUiListener.onError(new UiError(-6, "unknown error", stringExtra + ""));
            } else if (b.COMPLETE.equals(stringExtra2)) {
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

    private IUiListener buildListener(int i, IUiListener iUiListener) {
        if (i == Constants.REQUEST_LOGIN) {
            f.e(TAG, "登录的接口回调不能重新构建，暂时无法提供，先记录下来这种情况是否存在");
        } else if (i == Constants.REQUEST_SOCIAL_API) {
            f.e(TAG, "Social Api 的接口回调需要使用param来重新构建，暂时无法提供，先记录下来这种情况是否存在");
        } else if (i == Constants.REQUEST_SOCIAL_H5) {
            f.e(TAG, "Social Api 的H5接口回调需要使用param来重新构建，暂时无法提供，先记录下来这种情况是否存在");
        }
        return iUiListener;
    }

    public boolean onActivityResult(int i, int i2, Intent intent, IUiListener iUiListener) {
        IUiListener iUiListener2;
        f.c(TAG, "onActivityResult req=" + i + " res=" + i2);
        IUiListener listnerWithRequestCode = getListnerWithRequestCode(i);
        if (listnerWithRequestCode != null) {
            iUiListener2 = listnerWithRequestCode;
        } else if (iUiListener != null) {
            iUiListener2 = buildListener(i, iUiListener);
        } else {
            f.e(TAG, "onActivityResult can't find the listener");
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
            if (SystemUtils.ACTION_LOGIN.equals(stringExtra)) {
                intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                if (intExtra == 0) {
                    stringExtra = intent.getStringExtra(Constants.KEY_RESPONSE);
                    if (stringExtra != null) {
                        try {
                            iUiListener2.onComplete(Util.parseJson(stringExtra));
                        } catch (Throwable e) {
                            iUiListener2.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra));
                            f.b(TAG, "OpenUi, onActivityResult, json error", e);
                        }
                    } else {
                        f.b(TAG, "OpenUi, onActivityResult, onComplete");
                        iUiListener2.onComplete(new JSONObject());
                    }
                } else {
                    f.e(TAG, "OpenUi, onActivityResult, onError = " + intExtra + "");
                    iUiListener2.onError(new UiError(intExtra, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
                }
            } else if (SystemUtils.ACTION_SHARE.equals(stringExtra)) {
                r0 = intent.getStringExtra(j.c);
                stringExtra = intent.getStringExtra("response");
                if (com.umeng.update.net.f.c.equals(r0)) {
                    iUiListener2.onCancel();
                } else if (x.aF.equals(r0)) {
                    iUiListener2.onError(new UiError(-6, "unknown error", stringExtra + ""));
                } else if (b.COMPLETE.equals(r0)) {
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
                            iUiListener2.onComplete(Util.parseJson(r0));
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
