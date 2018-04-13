package org.mozilla.javascript.typedarrays;

import com.iflytek.cloud.SpeechEvent;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Undefined;

public abstract class NativeArrayBufferView extends IdScriptableObject {
    private static final int Id_buffer = 1;
    private static final int Id_byteLength = 3;
    private static final int Id_byteOffset = 2;
    private static final int MAX_INSTANCE_ID = 3;
    private static final long serialVersionUID = 6884475582973958419L;
    protected final NativeArrayBuffer arrayBuffer;
    protected final int byteLength;
    protected final int offset;

    public NativeArrayBufferView() {
        this.arrayBuffer = NativeArrayBuffer.EMPTY_BUFFER;
        this.offset = 0;
        this.byteLength = 0;
    }

    protected NativeArrayBufferView(NativeArrayBuffer nativeArrayBuffer, int i, int i2) {
        this.offset = i;
        this.byteLength = i2;
        this.arrayBuffer = nativeArrayBuffer;
    }

    public NativeArrayBuffer getBuffer() {
        return this.arrayBuffer;
    }

    public int getByteOffset() {
        return this.offset;
    }

    public int getByteLength() {
        return this.byteLength;
    }

    protected static boolean isArg(Object[] objArr, int i) {
        return objArr.length > i && !Undefined.instance.equals(objArr[i]);
    }

    protected int getMaxInstanceId() {
        return 3;
    }

    protected String getInstanceIdName(int i) {
        switch (i) {
            case 1:
                return SpeechEvent.KEY_EVENT_TTS_BUFFER;
            case 2:
                return "byteOffset";
            case 3:
                return "byteLength";
            default:
                return super.getInstanceIdName(i);
        }
    }

    protected Object getInstanceIdValue(int i) {
        switch (i) {
            case 1:
                return this.arrayBuffer;
            case 2:
                return ScriptRuntime.wrapInt(this.offset);
            case 3:
                return ScriptRuntime.wrapInt(this.byteLength);
            default:
                return super.getInstanceIdValue(i);
        }
    }

    protected int findInstanceIdInfo(String str) {
        int i;
        String str2;
        int i2 = 0;
        int length = str.length();
        if (length == 6) {
            i = 1;
            str2 = SpeechEvent.KEY_EVENT_TTS_BUFFER;
        } else {
            if (length == 10) {
                char charAt = str.charAt(4);
                if (charAt == 'L') {
                    i = 3;
                    str2 = "byteLength";
                } else if (charAt == 'O') {
                    i = 2;
                    str2 = "byteOffset";
                }
            }
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            i2 = i;
        }
        if (i2 == 0) {
            return super.findInstanceIdInfo(str);
        }
        return IdScriptableObject.instanceIdInfo(5, i2);
    }
}
