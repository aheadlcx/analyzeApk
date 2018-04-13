package com.ali.auth.third.login.task;

import cn.v6.sixrooms.constants.CommonInts;
import com.ali.auth.third.core.message.Message;
import com.ali.auth.third.core.message.MessageUtils;
import com.ali.auth.third.core.model.KernelMessageConstants;
import com.ali.auth.third.core.model.LoginReturnData;
import com.ali.auth.third.core.model.ResultCode;
import com.ali.auth.third.core.model.RpcResponse;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import com.ali.auth.third.core.task.AbsAsyncTask;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.core.util.CommonUtils;
import com.ali.auth.third.core.util.ResourceUtils;
import com.ali.auth.third.login.LoginComponent;
import com.ali.auth.third.login.context.LoginContext;
import com.ali.auth.third.ui.LoginWebViewActivity;
import com.ali.auth.third.ui.context.BridgeCallbackContext;
import com.baidu.mobads.openad.c.b;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONException;
import org.json.JSONObject;

public class BindByUsernameTask extends AbsAsyncTask<String, Void, Void> {
    private static final String TAG = "login";
    private BridgeCallbackContext bridgeCallbackContext;

    public BindByUsernameTask(BridgeCallbackContext bridgeCallbackContext) {
        this.bridgeCallbackContext = bridgeCallbackContext;
    }

    protected Void asyncExecute(String... strArr) {
        if (CommonUtils.isNetworkAvailable()) {
            RpcResponse loginByUserName = LoginComponent.INSTANCE.loginByUserName(strArr[0]);
            if (loginByUserName == null) {
                this.bridgeCallbackContext.onFailure("");
            } else {
                try {
                    if (loginByUserName.code == ErrorCode.SERVER_OPERATIONTYPEMISSED) {
                        LoginContext.credentialService.refreshWhenLogin((LoginReturnData) loginByUserName.returnValue);
                        this.bridgeCallbackContext.getActivity().setResult(ResultCode.SUCCESS.code);
                        this.bridgeCallbackContext.getActivity().finish();
                    } else if (loginByUserName.code == 13010) {
                        r2 = new JSONObject();
                        r2.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, 1007);
                        r2.put(b.EVENT_MESSAGE, loginByUserName.message);
                        r2.put("success", false);
                        r3 = new JSONObject();
                        if (loginByUserName.returnValue != null) {
                            r3.put("checkCodeId", ((LoginReturnData) loginByUserName.returnValue).checkCodeId);
                            r3.put("checkCodeUrl", ((LoginReturnData) loginByUserName.returnValue).checkCodeUrl);
                        }
                        r2.put("data", r3);
                        this.bridgeCallbackContext.success(r2.toString());
                    } else if (loginByUserName.code == 13011) {
                        r2 = new JSONObject();
                        r2.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, 1008);
                        r2.put(b.EVENT_MESSAGE, loginByUserName.message);
                        r3 = new JSONObject();
                        if (loginByUserName.returnValue != null) {
                            r3.put("checkCodeId", ((LoginReturnData) loginByUserName.returnValue).checkCodeId);
                            r3.put("checkCodeUrl", ((LoginReturnData) loginByUserName.returnValue).checkCodeUrl);
                        }
                        r2.put("data", r3);
                        this.bridgeCallbackContext.success(r2.toString());
                    } else if (loginByUserName.code == 13060) {
                        r2 = new JSONObject();
                        r2.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, CommonInts.REPLY_RESULT);
                        r2.put(b.EVENT_MESSAGE, loginByUserName.message);
                        r3 = new JSONObject();
                        if (loginByUserName.returnValue != null) {
                            r3.put("doubleCheckUrl", ((LoginReturnData) loginByUserName.returnValue).h5Url);
                        }
                        r2.put("data", r3);
                        if (loginByUserName.returnValue != null) {
                            LoginWebViewActivity loginWebViewActivity = (LoginWebViewActivity) this.bridgeCallbackContext.getActivity();
                            LoginWebViewActivity.token = ((LoginReturnData) loginByUserName.returnValue).token;
                            loginWebViewActivity = (LoginWebViewActivity) this.bridgeCallbackContext.getActivity();
                            LoginWebViewActivity.scene = ((LoginReturnData) loginByUserName.returnValue).scene;
                        }
                        this.bridgeCallbackContext.success(r2.toString());
                    } else {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, loginByUserName.code);
                        jSONObject.put(b.EVENT_MESSAGE, loginByUserName.message);
                        this.bridgeCallbackContext.success(jSONObject.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            RpcResponse rpcResponse = new RpcResponse();
            rpcResponse.code = -1;
            rpcResponse.message = ResourceUtils.getString("com_taobao_tae_sdk_network_not_available_message");
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, rpcResponse.code);
                jSONObject2.put(b.EVENT_MESSAGE, rpcResponse.message);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.bridgeCallbackContext.success(jSONObject2.toString());
        }
        return null;
    }

    protected void doWhenException(Throwable th) {
        Message createMessage = MessageUtils.createMessage(KernelMessageConstants.GENERIC_SYSTEM_ERROR, th.getMessage());
        SDKLogger.d("login", createMessage.toString());
        this.bridgeCallbackContext.onFailure(createMessage.code, createMessage.message);
    }

    protected void doFinally() {
    }
}
