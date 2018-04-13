package cn.v6.sixrooms.avsolution.common;

public class SolutionException extends RuntimeException {
    public static final String DeviceError = "Device Error";
    public static final String InvalidParameter = "Invalid Parameter";
    public static final String JNIError = "JNI Error";
    public static final String Uninitialize = "Uninitialize";
    public static final String UnknownError = "Unknown Error";
    private static final long serialVersionUID = 1;

    public SolutionException(String str) {
        super(str);
    }
}
