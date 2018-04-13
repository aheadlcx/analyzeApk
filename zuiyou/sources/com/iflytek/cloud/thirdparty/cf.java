package com.iflytek.cloud.thirdparty;

import android.os.Environment;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.cloud.SpeechConstant;

public class cf {
    public static String a = "wtime";
    public static final String b = (Environment.getExternalStorageDirectory().getPath() + "/msc/ist/audio/");
    public static final String[][] c;

    static {
        r0 = new String[24][];
        r0[0] = new String[]{"surl", AIUIConstant.KEY_SERVER_URL};
        r0[1] = new String[]{"besturl_search", "search_best_url"};
        r0[2] = new String[]{"bsts", "search_best_url"};
        r0[3] = new String[]{SpeechConstant.ASR_SCH, "sch"};
        r0[4] = new String[]{SpeechConstant.ASR_NOMATCH_ERROR, "asr_nme"};
        r0[5] = new String[]{"asr_ptt", "ptt"};
        r0[6] = new String[]{SpeechConstant.RESULT_TYPE, "rst"};
        r0[7] = new String[]{SpeechConstant.RESULT_LEVEL, "rst_level"};
        r0[8] = new String[]{"vad_bos", "vad_timeout"};
        r0[9] = new String[]{"bos", "vad_timeout"};
        r0[10] = new String[]{"vad_eos", "vad_speech_tail", "eos"};
        r0[11] = new String[]{"eos", "vad_speech_tail", "eos"};
        r0[12] = new String[]{SpeechConstant.ASR_AUDIO_PATH, "aap"};
        r0[13] = new String[]{SpeechConstant.TTS_BUFFER_TIME, "tbt"};
        r0[14] = new String[]{SpeechConstant.TTS_AUDIO_PATH, "tap"};
        r0[15] = new String[]{SpeechConstant.SUBJECT, "sub"};
        r0[16] = new String[]{"data_type", "dtt"};
        r0[17] = new String[]{SpeechConstant.ASR_NBEST, "nbest"};
        r0[18] = new String[]{SpeechConstant.ASR_WBEST, "wbest"};
        r0[19] = new String[]{SpeechConstant.ASR_DWA, "dwa"};
        r0[20] = new String[]{SpeechConstant.VOICE_NAME, "vcn"};
        r0[21] = new String[]{SpeechConstant.BACKGROUND_SOUND, "bgs"};
        r0[22] = new String[]{SpeechConstant.TEXT_ENCODING, "tte"};
        r0[23] = new String[]{SpeechConstant.IVW_NET_MODE, "ivwnet_mode"};
        c = r0;
    }
}
