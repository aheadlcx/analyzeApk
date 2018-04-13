package com.iflytek.cloud;

import com.iflytek.cloud.resource.Resource;
import com.iflytek.cloud.thirdparty.cb;
import com.tencent.bugly.BuglyStrategy$a;

public class SpeechError extends Exception {
    public static final int TIP_ERROR_ALREADY_EXIST = 66;
    public static final int TIP_ERROR_GROUP_EMPTY = 68;
    public static final int TIP_ERROR_IVP_EXTRA_RGN_SOPPORT = 56;
    public static final int TIP_ERROR_IVP_GENERAL = 55;
    public static final int TIP_ERROR_IVP_MUCH_NOISE = 58;
    public static final int TIP_ERROR_IVP_NO_ENOUGH_AUDIO = 63;
    public static final int TIP_ERROR_IVP_TEXT_NOT_MATCH = 62;
    public static final int TIP_ERROR_IVP_TOO_LOW = 59;
    public static final int TIP_ERROR_IVP_TRUNCATED = 57;
    public static final int TIP_ERROR_IVP_UTTER_TOO_SHORT = 61;
    public static final int TIP_ERROR_IVP_ZERO_AUDIO = 60;
    public static final int TIP_ERROR_MODEL_IS_CREATING = 65;
    public static final int TIP_ERROR_MODEL_NOT_FOUND = 64;
    public static final int TIP_ERROR_NO_GROUP = 67;
    public static final int TIP_ERROR_NO_USER = 69;
    public static final int TIP_ERROR_OVERFLOW_IN_GROUP = 70;
    private static final long serialVersionUID = 4434424251478985596L;
    private int a;
    private String b;

    public SpeechError(int i) {
        int i2 = 11;
        int i3 = 64;
        this.a = 0;
        this.b = "";
        this.a = i;
        if (i >= 20001) {
            if (this.a < BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH) {
                if (this.a == 20001) {
                    i2 = 1;
                } else if (this.a == 20002) {
                    i2 = 2;
                } else if (this.a == 20003) {
                    i2 = 3;
                } else if (this.a == 20004) {
                    i2 = 5;
                } else if (this.a == 20005) {
                    i2 = 10;
                } else if (this.a == 20006) {
                    i2 = 9;
                } else if (this.a == 20007) {
                    i2 = 12;
                } else if (this.a != 20008) {
                    i2 = this.a == 20009 ? 13 : this.a == 20010 ? 14 : this.a == 20012 ? 7 : this.a == 21003 ? 28 : (this.a == 21002 || this.a == 21001) ? 29 : 30;
                }
            }
            i2 = 3;
        } else if (this.a != 10118) {
            if (10106 == this.a || 10107 == this.a || 10124 == this.a) {
                cb.a("sdk errorcode", this.a + "");
                i2 = 7;
            } else if (this.a == 10110) {
                i2 = 32;
            } else if (this.a == 10111) {
                i2 = 28;
            } else if (this.a >= 10200 && this.a < 10300) {
                i2 = 3;
            } else if (this.a == 10117 || this.a == 10101) {
                i2 = 16;
            } else if (this.a == 10113) {
                i2 = 17;
            } else if (this.a == 10116) {
                i2 = 64;
            } else if (this.a == 10121) {
                i2 = 66;
            } else if (this.a >= 10400 && this.a <= 10407) {
                i2 = 18;
            } else if (this.a >= 11000 && this.a < 11099) {
                i2 = this.a == 11005 ? 23 : this.a == 11006 ? 24 : 18;
            } else if (this.a == 10129) {
                i2 = 19;
            } else if (this.a == 10109) {
                i2 = 20;
            } else if (this.a == 10702) {
                i2 = 21;
            } else if (this.a >= 10500 && this.a < 10600) {
                i2 = 22;
            } else if (this.a >= 11200 && this.a <= 11250) {
                i2 = 25;
            } else if (this.a >= 14000 && this.a <= 14006) {
                i2 = 31;
            } else if (this.a >= 16000 && this.a <= 16006) {
                i2 = 31;
            } else if (11401 == this.a) {
                i2 = 35;
            } else if (11402 == this.a) {
                i2 = 36;
            } else if (11403 == this.a) {
                i2 = 37;
            } else if (11404 == this.a) {
                i2 = 38;
            } else if (11405 == this.a) {
                i2 = 39;
            } else if (11406 == this.a) {
                i2 = 40;
            } else if (11407 == this.a) {
                i2 = 41;
            } else if (11408 == this.a) {
                i2 = 42;
            } else if (this.a == 11501) {
                i2 = 65;
            } else if (this.a == 11502) {
                i2 = 64;
            } else {
                if (this.a == 11503) {
                    i2 = 19;
                }
                i2 = 3;
            }
        }
        switch (this.a) {
            case 10031:
                i3 = 65;
                break;
            case 10141:
                i3 = 68;
                break;
            case 10142:
                i3 = 69;
                break;
            case 10143:
                i3 = 67;
                break;
            case 10144:
                i3 = 70;
                break;
            case 11600:
                i3 = 55;
                break;
            case 11601:
                i3 = 56;
                break;
            case 11602:
                i3 = 57;
                break;
            case 11603:
                i3 = 58;
                break;
            case 11604:
                i3 = 59;
                break;
            case 11605:
                i3 = 60;
                break;
            case 11606:
                i3 = 61;
                break;
            case 11607:
                i3 = 62;
                break;
            case 11608:
                i3 = 63;
                break;
            case 11610:
            case 11712:
                break;
            case 11700:
                i3 = 43;
                break;
            case 11701:
                i3 = 44;
                break;
            case 11702:
                i3 = 45;
                break;
            case 11703:
                i3 = 46;
                break;
            case 11704:
                i3 = 47;
                break;
            case 11705:
                i3 = 48;
                break;
            case 11706:
                i3 = 49;
                break;
            case 11707:
                i3 = 50;
                break;
            case 11708:
                i3 = 51;
                break;
            case 11709:
                i3 = 52;
                break;
            case 11710:
                i3 = 53;
                break;
            case 11711:
                i3 = 54;
                break;
            default:
                i3 = i2;
                break;
        }
        this.b = Resource.getErrorDescription(i3);
    }

    public SpeechError(int i, String str) {
        this(i);
        if (!SpeechConstant.ENG_WFR.equals(str)) {
            return;
        }
        if (10118 == i) {
            this.b = Resource.getErrorDescription(33);
        } else if (10119 == i) {
            this.b = Resource.getErrorDescription(34);
        }
    }

    public SpeechError(Exception exception) {
        this.a = 0;
        this.b = "";
        this.a = 20999;
        this.b = exception.toString();
    }

    public SpeechError(Throwable th, int i) {
        this(i);
        initCause(th);
    }

    public int getErrorCode() {
        return this.a;
    }

    public String getErrorDescription() {
        return this.b;
    }

    public String getHtmlDescription(boolean z) {
        String str = this.b + "...";
        if (!z) {
            return str;
        }
        return ((str + "<br>(") + Resource.getErrorTag(0) + ":") + this.a + ")";
    }

    public String getPlainDescription(boolean z) {
        String str = this.b;
        if (!z) {
            return str;
        }
        return ((str + ".") + "(" + Resource.getErrorTag(0) + ":") + this.a + ")";
    }

    public String toString() {
        return getPlainDescription(true);
    }
}
