package com.baidu.mobads.interfaces;

public enum IXAdInstanceInfo$CreativeType {
    NONE("none"),
    TEXT("text"),
    STATIC_IMAGE("static_image"),
    GIF("gif"),
    RM("rich_media"),
    HTML("html"),
    HYBRID("hybrid"),
    VIDEO("video");
    
    private final String a;

    private IXAdInstanceInfo$CreativeType(String str) {
        this.a = str;
    }

    public String getValue() {
        return this.a;
    }

    public static IXAdInstanceInfo$CreativeType parse(String str) {
        for (IXAdInstanceInfo$CreativeType iXAdInstanceInfo$CreativeType : values()) {
            if (iXAdInstanceInfo$CreativeType.a.equalsIgnoreCase(str)) {
                return iXAdInstanceInfo$CreativeType;
            }
        }
        return null;
    }
}
