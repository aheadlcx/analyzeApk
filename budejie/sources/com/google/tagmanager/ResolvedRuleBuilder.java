package com.google.tagmanager;

import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;

interface ResolvedRuleBuilder {
    ResolvedFunctionCallBuilder createNegativePredicate();

    ResolvedFunctionCallBuilder createPositivePredicate();

    ResolvedFunctionCallTranslatorList getAddedMacroFunctions();

    ResolvedFunctionCallTranslatorList getAddedTagFunctions();

    ResolvedFunctionCallTranslatorList getRemovedMacroFunctions();

    ResolvedFunctionCallTranslatorList getRemovedTagFunctions();

    void setValue(Value value);
}
