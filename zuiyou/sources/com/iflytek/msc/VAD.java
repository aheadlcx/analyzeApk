package com.iflytek.msc;

public class VAD {

    public static class VadData {
        public int audioQuality;
        public int endByte;
        public int endRemainFrameNum;
        public int firstOutByte;
        public int inSpeech;
        public int startByte;
        public int startRemainFrameNum;
        public int status;
        public int volumeLevel;
        public int waitPauseOrEnd;
        public int waitStart;
        public byte[] wavData;
        public int wavDataSize;
    }

    public static native int AppendData(long j, byte[] bArr, int i);

    public static native int CalcVolumLevel(long j, byte[] bArr, int i, VadData vadData);

    public static native int EndAudioData(long j);

    public static native int FetchData(long j, VadData vadData);

    public static native int GetLastSpeechPos(long j, VadData vadData);

    public static native long Initialize(int i);

    public static native void Reset(long j);

    public static native int SetParam(long j, int i, int i2);

    public static native void Uninitialize(long j);
}
