package com.meizu.cloud.pushsdk.handler.impl.fileupload;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.AbstractAppLogicListener;
import com.meizu.cloud.pushsdk.handler.impl.AbstractMessageHandler;
import com.meizu.cloud.pushsdk.handler.impl.model.ControlMessage;
import com.meizu.cloud.pushsdk.handler.impl.model.UploadLogMessage;
import com.meizu.cloud.pushsdk.networking.common.ANResponse;
import com.meizu.cloud.pushsdk.notification.PushNotification;
import com.meizu.cloud.pushsdk.platform.api.PushPlatformManager;
import com.meizu.cloud.pushsdk.util.Connectivity;
import com.meizu.cloud.pushsdk.util.UxIPUtils;
import java.io.File;

public class LogUploadMessageHandler extends AbstractMessageHandler<UploadLogMessage> {
    public LogUploadMessageHandler(Context context, AbstractAppLogicListener abstractAppLogicListener) {
        super(context, abstractAppLogicListener);
    }

    protected UploadLogMessage getMessage(Intent intent) {
        String stringExtra = intent.getStringExtra(PushConstants.MZ_PUSH_CONTROL_MESSAGE);
        String stringExtra2 = intent.getStringExtra(PushConstants.EXTRA_APP_PUSH_SEQ_ID);
        return new UploadLogMessage(intent.getStringExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE), stringExtra, intent.getStringExtra(PushConstants.MZ_PUSH_MESSAGE_STATISTICS_IMEI_KEY), stringExtra2);
    }

    protected void unsafeSend(UploadLogMessage uploadLogMessage, PushNotification pushNotification) {
        File file = null;
        a.a();
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String taskId = uploadLogMessage.getControlMessage().getStatics().getTaskId();
        taskId = absolutePath + "/Android/data/pushSdktmp/" + taskId + "_" + uploadLogMessage.getControlMessage().getStatics().getDeviceId() + ".zip";
        try {
            new ZipTask(taskId).zip(uploadLogMessage.getFileList());
            File file2 = new File(taskId);
            absolutePath = null;
            file = file2;
        } catch (Exception e) {
            absolutePath = e.getMessage();
            a.d("AbstractMessageHandler", "zip error message " + absolutePath);
        }
        if (file != null && file.length() / 1024 > ((long) uploadLogMessage.getMaxSize())) {
            absolutePath = "the upload file exceeds the max size";
        } else if (uploadLogMessage.isWifiUpload() && !Connectivity.isConnectedWifi(context())) {
            absolutePath = "current network not allowed upload log file";
        }
        ANResponse uploadLogFile = PushPlatformManager.getInstance(context()).uploadLogFile(uploadLogMessage.getControlMessage().getStatics().getTaskId(), uploadLogMessage.getControlMessage().getStatics().getDeviceId(), absolutePath, file);
        if (uploadLogFile == null || !uploadLogFile.isSuccess()) {
            a.a("AbstractMessageHandler", "upload error code " + uploadLogFile.getError() + ((String) uploadLogFile.getResult()));
            return;
        }
        if (file != null) {
            file.delete();
        }
        a.d("AbstractMessageHandler", "upload success " + ((String) uploadLogFile.getResult()));
    }

    public boolean messageMatch(Intent intent) {
        int pushType;
        a.a("AbstractMessageHandler", "start LogUploadMessageHandler match");
        Object stringExtra = intent.getStringExtra(PushConstants.MZ_PUSH_CONTROL_MESSAGE);
        if (!TextUtils.isEmpty(stringExtra)) {
            ControlMessage parse = ControlMessage.parse(stringExtra);
            if (parse.getControl() != null) {
                pushType = parse.getControl().getPushType();
                if (!PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && "2".equals(String.valueOf(r0))) {
                    return true;
                }
            }
        }
        pushType = 0;
        return !PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) ? false : false;
    }

    public int getProcessorType() {
        return 65536;
    }

    protected void onBeforeEvent(UploadLogMessage uploadLogMessage) {
        UxIPUtils.onReceivePushMessageEvent(context(), context().getPackageName(), uploadLogMessage.getControlMessage().getStatics().getDeviceId(), uploadLogMessage.getControlMessage().getStatics().getTaskId(), uploadLogMessage.getControlMessage().getStatics().getSeqId(), uploadLogMessage.getControlMessage().getStatics().getTime());
    }
}
