package com.spriteapp.booklibrary.config;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.spriteapp.booklibrary.e.b;
import com.spriteapp.booklibrary.enumeration.LoginStateEnum;
import com.spriteapp.booklibrary.enumeration.UpdateNightMode;
import com.spriteapp.booklibrary.model.RegisterModel;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.util.FileUtils;
import com.spriteapp.booklibrary.util.SharedPreferencesUtil;
import com.spriteapp.booklibrary.util.ToastUtil;
import de.greenrobot.event.EventBus;

public class HuaXiSDK {
    private static final String TAG = "HuaXiSDK";
    private static HuaXiSDK mHuaXiSDK;
    public static LoginStateEnum mLoginState;
    private HuaXiConfig mConfig;
    private RegisterModel mRegisterModel;

    private HuaXiSDK() {
    }

    public static HuaXiSDK getInstance() {
        if (mHuaXiSDK == null) {
            synchronized (HuaXiSDK.class) {
                if (mHuaXiSDK == null) {
                    mHuaXiSDK = new HuaXiSDK();
                }
            }
        }
        return mHuaXiSDK;
    }

    public synchronized void init(HuaXiConfig huaXiConfig) {
        if (huaXiConfig == null) {
            throw new IllegalArgumentException("HuaXiConfig不能为null");
        }
        this.mConfig = huaXiConfig;
        initSaveDir();
        changeMode(this.mConfig.isNightMode);
    }

    private void initSaveDir() {
        AppUtil.init(this.mConfig.context);
        FileUtils.createRootPath(this.mConfig.context);
        SharedPreferencesUtil.init(this.mConfig.context.getApplicationContext(), this.mConfig.context.getPackageName() + "hua_xi_preference", 0);
    }

    public void toLoginPage(Context context) {
        if (this.mConfig.channelListener == null) {
            throw new NullPointerException("HuaXiConfig ChannelListener不能为空");
        } else if (mLoginState == LoginStateEnum.LOADING) {
            ToastUtil.showSingleToast("登录中");
        } else {
            this.mConfig.channelListener.toLoginPage(context);
        }
    }

    public void showShareDialog(Context context, BookDetailResponse bookDetailResponse, boolean z) {
        if (this.mConfig.channelListener == null) {
            throw new NullPointerException("HuaXiConfig ChannelListener不能为空");
        }
        this.mConfig.channelListener.showShareDialog(context, bookDetailResponse, z);
    }

    public int getChannelId() {
        return this.mConfig.channelId;
    }

    public void syncLoginStatus(RegisterModel registerModel) {
        mLoginState = AppUtil.isLogin() ? LoginStateEnum.SUCCESS : LoginStateEnum.UN_LOGIN;
        if (registerModel != null) {
            if (TextUtils.isEmpty(registerModel.getUserName()) || TextUtils.isEmpty(registerModel.getUserId())) {
                Log.d(TAG, "syncLoginStatus: 用户名或id不能为空");
            } else {
                registerUser(registerModel);
            }
        }
    }

    private void registerUser(RegisterModel registerModel) {
        this.mRegisterModel = registerModel;
        EventBus.getDefault().post(this.mRegisterModel);
    }

    public RegisterModel getRegisterModel() {
        return this.mRegisterModel;
    }

    public void setRegisterModelNull() {
        this.mRegisterModel = null;
    }

    public String getSignSecret() {
        if (this.mConfig != null) {
            return this.mConfig.signSecret;
        }
        throw new NullPointerException("HuaXiConfig 不能为空");
    }

    public String getClientId() {
        if (this.mConfig != null) {
            return String.valueOf(this.mConfig.clientId);
        }
        throw new NullPointerException("HuaXiConfig 不能为空");
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mConfig == null) {
            throw new NullPointerException("HuaXiConfig 不能为空");
        } else if (this.mConfig.resultListener != null) {
            this.mConfig.resultListener.onActivityResult(i, i2, intent);
        }
    }

    public HuaXiConfig getConfig() {
        if (this.mConfig != null) {
            return this.mConfig;
        }
        throw new NullPointerException("HuaXiConfig 不能为空");
    }

    public void changeMode(boolean z) {
        b.a(z);
        EventBus.getDefault().post(UpdateNightMode.UPDATE_NIGHT_MODE);
    }

    public void loginOut() {
        AppUtil.loginOut();
    }
}
