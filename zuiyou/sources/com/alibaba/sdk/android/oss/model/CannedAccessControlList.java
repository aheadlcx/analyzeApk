package com.alibaba.sdk.android.oss.model;

import com.meizu.cloud.pushsdk.constants.PushConstants;

public enum CannedAccessControlList {
    Private(PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_PRIVATE),
    PublicRead("public-read"),
    PublicReadWrite("public-read-write");
    
    private String ACLString;

    private CannedAccessControlList(String str) {
        this.ACLString = str;
    }

    public String toString() {
        return this.ACLString;
    }

    public static CannedAccessControlList parseACL(String str) {
        for (CannedAccessControlList cannedAccessControlList : values()) {
            if (cannedAccessControlList.toString().equals(str)) {
                return cannedAccessControlList;
            }
        }
        throw new IllegalArgumentException("Unable to parse the provided acl " + str);
    }
}
