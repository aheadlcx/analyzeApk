package com.baidu.mobads.command.b;

import com.baidu.mobads.command.b;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdResource;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;

public class a extends b {
    private String f = null;

    public a(IXNonLinearAdSlot iXNonLinearAdSlot, IXAdInstanceInfo iXAdInstanceInfo, IXAdResource iXAdResource, String str) {
        super(iXNonLinearAdSlot, iXAdInstanceInfo, iXAdResource);
        this.f = str;
    }

    public void a() {
        XAdSDKFoundationFacade.getInstance().getCommonUtils().browserOutside(this.a, this.f);
    }
}
