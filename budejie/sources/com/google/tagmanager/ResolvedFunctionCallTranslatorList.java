package com.google.tagmanager;

import com.google.tagmanager.ResourceUtil.ExpandedFunctionCall;
import java.util.List;

interface ResolvedFunctionCallTranslatorList {
    void translateAndAddAll(List<ExpandedFunctionCall> list, List<String> list2);
}
