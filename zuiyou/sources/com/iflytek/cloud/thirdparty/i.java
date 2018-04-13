package com.iflytek.cloud.thirdparty;

import com.iflytek.aiui.AIUIConstant;

public class i {
    public static final String[][] a;

    static {
        String[] strArr = new String[]{AIUIConstant.KEY_AUDIO_ENCODE, "dte"};
        String[] strArr2 = new String[]{AIUIConstant.KEY_DATA_SOURCE, "dsrc"};
        String[] strArr3 = new String[]{"client_timestamp", "ctimestamp"};
        String[][] strArr4 = new String[9][];
        strArr4[0] = new String[]{"data_status", "dts"};
        strArr4[1] = new String[]{"data_type", "dtype"};
        strArr4[2] = new String[]{"data_format", "dtf"};
        strArr4[3] = strArr;
        strArr4[4] = strArr2;
        strArr4[5] = new String[]{"stream_id", "stmid"};
        strArr4[6] = new String[]{"associate_id", "asid"};
        strArr4[7] = strArr3;
        strArr4[8] = new String[]{"event_id", "evid"};
        a = strArr4;
    }
}
