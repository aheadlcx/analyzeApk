package com.ali.auth.third.core.service.impl;

import com.tencent.connect.common.Constants;
import java.util.Collections;
import java.util.Map;

public class CredentialManagerPolicy {
    public Map<String, String> filterProperties = Collections.singletonMap(Constants.PARAM_SCOPE, "Taobao");
    public String seedKey = "seed_key";
}
