package com.ali.auth.third.mtop.rpc.impl;

import com.ali.auth.third.core.config.ConfigManager;
import com.ali.auth.third.core.config.Environment;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.device.DeviceInfo;
import com.ali.auth.third.core.model.RpcRequest;
import com.ali.auth.third.core.model.RpcResponse;
import com.ali.auth.third.core.service.RpcService;
import com.ali.auth.third.core.util.CommonUtils;
import com.ali.auth.third.mtop.rpc.MTOPWrapper;
import com.ali.auth.third.mtop.rpc.MtopRemoteLoginImpl;
import com.taobao.tao.remotebusiness.login.RemoteLogin;
import mtopsdk.common.util.m;
import mtopsdk.mtop.b.a;
import mtopsdk.mtop.b.d;
import mtopsdk.mtop.domain.EnvModeEnum;

public class MtopRpcServiceImpl implements RpcService {
    public MtopRpcServiceImpl() {
        if (ConfigManager.DEBUG) {
            m.b(false);
            m.a(true);
        }
        d.a(0, 0);
        d.a(CommonUtils.getAppVersion());
        if (KernelContext.getEnvironment() == Environment.TEST) {
            a.a(KernelContext.context).a(EnvModeEnum.TEST);
        } else if (KernelContext.getEnvironment() == Environment.PRE) {
            a.a(KernelContext.context).a(EnvModeEnum.PREPARE);
        } else {
            a.a(KernelContext.context).a(EnvModeEnum.ONLINE);
        }
        RemoteLogin.setLoginImpl(new MtopRemoteLoginImpl());
    }

    public String invoke(RpcRequest rpcRequest) {
        return MTOPWrapper.getInstance().post(rpcRequest);
    }

    public <T> RpcResponse<T> invoke(RpcRequest rpcRequest, Class<T> cls) {
        return MTOPWrapper.getInstance().post(rpcRequest, cls);
    }

    public void registerSessionInfo(String str, String str2) {
        a.a(KernelContext.context).a(str, str2);
    }

    public String getDeviceId() {
        return DeviceInfo.deviceId;
    }

    public void logout() {
        a.a(KernelContext.context).a();
    }
}
