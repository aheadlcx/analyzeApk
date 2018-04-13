package cn.xiaochuankeji.tieba.network.custom.exception;

import java.io.IOException;

public class VerifyErrorException extends IOException {
    public VerifyErrorException(String str) {
        super(str);
    }
}
