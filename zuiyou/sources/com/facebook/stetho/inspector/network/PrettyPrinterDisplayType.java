package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.protocol.module.Page.ResourceType;

public enum PrettyPrinterDisplayType {
    JSON(ResourceType.XHR),
    HTML(ResourceType.DOCUMENT),
    TEXT(ResourceType.DOCUMENT);
    
    private final ResourceType mResourceType;

    private PrettyPrinterDisplayType(ResourceType resourceType) {
        this.mResourceType = resourceType;
    }

    public ResourceType getResourceType() {
        return this.mResourceType;
    }
}
