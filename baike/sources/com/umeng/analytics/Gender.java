package com.umeng.analytics;

public enum Gender {
    Male(1),
    Female(2),
    Unknown(0);
    
    public int value;

    private Gender(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static Gender getGender(int i) {
        switch (i) {
            case 1:
                return Male;
            case 2:
                return Female;
            default:
                return Unknown;
        }
    }

    public static com.umeng.commonsdk.statistics.proto.Gender transGender(Gender gender) {
        switch (h.a[gender.ordinal()]) {
            case 1:
                return com.umeng.commonsdk.statistics.proto.Gender.MALE;
            case 2:
                return com.umeng.commonsdk.statistics.proto.Gender.FEMALE;
            default:
                return com.umeng.commonsdk.statistics.proto.Gender.UNKNOWN;
        }
    }
}
