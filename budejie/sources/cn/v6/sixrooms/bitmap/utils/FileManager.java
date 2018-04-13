package cn.v6.sixrooms.bitmap.utils;

public class FileManager {
    private static String a = "6rooms/audio/recoder/";
    private static String b = "6rooms/audio/receive/";
    public static String path = "cn.v6/files/";

    public static String getSaveFilePath() {
        if (CommonUtil.hasSDCard()) {
            return CommonUtil.getRootFilePath() + path;
        }
        return CommonUtil.getRootFilePath() + path;
    }

    public static String getAudioRecoderPath() {
        return CommonUtil.getRootFilePath() + a;
    }

    public static String getAudioPlayPath() {
        return CommonUtil.getRootFilePath() + b;
    }
}
