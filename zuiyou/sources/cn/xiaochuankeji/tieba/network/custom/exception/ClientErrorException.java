package cn.xiaochuankeji.tieba.network.custom.exception;

import java.io.IOException;
import org.json.JSONObject;

public class ClientErrorException extends IOException {
    private final int mErrCode;
    private final JSONObject mErrData;

    public ClientErrorException(int i, String str, JSONObject jSONObject) {
        super(str);
        this.mErrCode = i;
        this.mErrData = jSONObject;
    }

    public int errCode() {
        return this.mErrCode;
    }

    public String errMessage() {
        return getMessage();
    }

    public JSONObject errData() {
        return this.mErrData;
    }
}
