package com.iflytek.speech;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.thirdparty.cb;
import com.iflytek.cloud.thirdparty.ce;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;

abstract class SpeechModuleAidl<I extends IInterface> implements ISpeechModule {
    private String mBindAction = null;
    private ServiceConnection mConnection = null;
    protected Context mContext = null;
    private InitListener mInitListener = null;
    private HashMap<String, String> mParams = new HashMap();
    protected I mService;
    protected Object mSynLock = new Object();
    private Handler mUiHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (SpeechModuleAidl.this.mInitListener != null) {
                SpeechModuleAidl.this.mInitListener.onInit(message.what);
            }
        }
    };
    private volatile boolean userDestroy = false;

    public SpeechModuleAidl(Context context, InitListener initListener, String str) {
        this.mContext = context;
        this.mInitListener = initListener;
        this.mBindAction = str;
        bindService();
    }

    private void bindService() {
        if (isActionInstalled(this.mContext, this.mBindAction)) {
            Intent intent = getIntent();
            intent.setAction(this.mBindAction);
            intent.setPackage(UtilityConfig.COMPONENT_PKG);
            this.mConnection = new ServiceConnection() {
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    synchronized (SpeechModuleAidl.this.mSynLock) {
                        Log.d(SpeechModuleAidl.this.getTag(), "init success");
                        SpeechModuleAidl.this.mService = SpeechModuleAidl.this.getService(iBinder);
                        Log.d(SpeechModuleAidl.this.getTag(), "mService :" + SpeechModuleAidl.this.mService);
                        if (SpeechModuleAidl.this.mInitListener != null) {
                            Message.obtain(SpeechModuleAidl.this.mUiHandler, 0, 0, 0, null).sendToTarget();
                        }
                    }
                }

                public void onServiceDisconnected(ComponentName componentName) {
                    Log.d(SpeechModuleAidl.this.getTag(), "onServiceDisconnected");
                    SpeechModuleAidl.this.mService = null;
                    if (!SpeechModuleAidl.this.userDestroy) {
                        try {
                            SpeechModuleAidl.this.bindService();
                        } catch (Exception e) {
                            Log.e(SpeechModuleAidl.this.getTag(), "rebindService error = " + e.toString());
                        }
                    }
                }
            };
            try {
                this.mContext.bindService(intent, this.mConnection, 1);
            } catch (Throwable e) {
                cb.a(e);
            }
        } else if (this.mInitListener != null) {
            Message.obtain(this.mUiHandler, 21001, 0, 0, null).sendToTarget();
        }
    }

    private I getService(IBinder iBinder) {
        try {
            String name = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
            Log.d(getTag(), "className = " + name);
            return (IInterface) Class.forName(name + "$Stub").getDeclaredMethod("asInterface", new Class[]{IBinder.class}).invoke(null, new Object[]{iBinder});
        } catch (Throwable e) {
            cb.a(e);
        } catch (Throwable e2) {
            cb.a(e2);
        } catch (Throwable e22) {
            cb.a(e22);
        } catch (Throwable e222) {
            cb.a(e222);
        } catch (Throwable e2222) {
            cb.a(e2222);
        } catch (Throwable e22222) {
            cb.a(e22222);
        } catch (Throwable e222222) {
            cb.a(e222222);
        }
        return null;
    }

    public boolean destory() {
        Log.d(getTag(), "destory");
        try {
            this.userDestroy = true;
            if (this.mConnection == null) {
                return true;
            }
            this.mContext.unbindService(this.mConnection);
            this.mConnection = null;
            return true;
        } catch (Throwable e) {
            cb.a(e);
            return false;
        }
    }

    public Intent getIntent() {
        Intent intent = new Intent();
        if (!this.mParams.isEmpty()) {
            for (String str : this.mParams.keySet()) {
                intent.putExtra(str, (String) this.mParams.get(str));
            }
            HashMap c = new ce((String) this.mParams.get(SpeechConstant.PARAMS), (String[][]) null).c();
            if (!(c == null || c.isEmpty())) {
                for (String str2 : c.keySet()) {
                    intent.putExtra(str2, (String) c.get(str2));
                }
            }
        }
        intent.putExtra(UtilityConfig.KEY_CALLER_APPID, SpeechUtility.getUtility().getParameter("appid"));
        intent.putExtra(UtilityConfig.KEY_CALLER_NAME, UtilityConfig.getCallerInfo(this.mContext, UtilityConfig.KEY_CALLER_NAME));
        intent.putExtra(UtilityConfig.KEY_CALLER_PKG_NAME, UtilityConfig.getCallerInfo(this.mContext, UtilityConfig.KEY_CALLER_PKG_NAME));
        intent.putExtra(UtilityConfig.KEY_CALLER_VER_NAME, UtilityConfig.getCallerInfo(this.mContext, UtilityConfig.KEY_CALLER_VER_NAME));
        intent.putExtra(UtilityConfig.KEY_CALLER_VER_CODE, UtilityConfig.getCallerInfo(this.mContext, UtilityConfig.KEY_CALLER_VER_CODE));
        return intent;
    }

    public String getParameter(String str) {
        return (String) this.mParams.get(str);
    }

    protected final String getTag() {
        return getClass().toString();
    }

    public boolean isActionInstalled(Context context, String str) {
        return (context == null || TextUtils.isEmpty(str) || context.getPackageManager().resolveService(new Intent(str), 0) == null) ? false : true;
    }

    public boolean isAvailable() {
        return this.mService != null;
    }

    public int setParameter(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return 20012;
        }
        if (TextUtils.isEmpty(str2)) {
            this.mParams.remove(str);
            return 0;
        }
        this.mParams.put(str, str2);
        return 0;
    }
}
