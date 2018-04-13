package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.protocol.module.Page.ResourceType;

public class ResourceTypeHelper {
    private final MimeMatcher<ResourceType> mMimeMatcher = new MimeMatcher();

    public ResourceTypeHelper() {
        this.mMimeMatcher.addRule("text/css", ResourceType.STYLESHEET);
        this.mMimeMatcher.addRule("image/*", ResourceType.IMAGE);
        this.mMimeMatcher.addRule("application/x-javascript", ResourceType.SCRIPT);
        this.mMimeMatcher.addRule("text/javascript", ResourceType.XHR);
        this.mMimeMatcher.addRule("application/json", ResourceType.XHR);
        this.mMimeMatcher.addRule("text/*", ResourceType.DOCUMENT);
        this.mMimeMatcher.addRule("*", ResourceType.OTHER);
    }

    public ResourceType determineResourceType(String str) {
        return (ResourceType) this.mMimeMatcher.match(stripContentExtras(str));
    }

    public String stripContentExtras(String str) {
        int indexOf = str.indexOf(59);
        return indexOf >= 0 ? str.substring(0, indexOf) : str;
    }
}
