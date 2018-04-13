package com.baidu.mobads.vo.a;

import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdProdInfo;

public class a$a {
    String a = "";
    String b = "";
    String c = "";
    String d = "";
    String e = "";

    public a$a(IXAdInstanceInfo iXAdInstanceInfo, IXAdProdInfo iXAdProdInfo) {
        if (iXAdInstanceInfo != null) {
            this.a = iXAdInstanceInfo.getAdId();
            this.b = iXAdInstanceInfo.getQueryKey();
            this.d = iXAdInstanceInfo.getCreativeType().getValue();
        }
        if (iXAdProdInfo != null) {
            this.e = iXAdProdInfo.getAdPlacementId();
            this.c = iXAdProdInfo.getProdType();
        }
    }
}
