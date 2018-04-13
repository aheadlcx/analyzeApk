package com.google.tagmanager;

import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import com.google.tagmanager.ResourceUtil.ExpandedFunctionCall;
import java.util.List;

class NoopResolvedRuleBuilder implements ResolvedRuleBuilder {

    public class NoopResolvedFunctionCallTranslatorList implements ResolvedFunctionCallTranslatorList {
        public void translateAndAddAll(List<ExpandedFunctionCall> list, List<String> list2) {
        }
    }

    NoopResolvedRuleBuilder() {
    }

    public ResolvedFunctionCallBuilder createNegativePredicate() {
        return new NoopResolvedFunctionCallBuilder();
    }

    public ResolvedFunctionCallBuilder createPositivePredicate() {
        return new NoopResolvedFunctionCallBuilder();
    }

    public ResolvedFunctionCallTranslatorList getAddedMacroFunctions() {
        return new NoopResolvedFunctionCallTranslatorList();
    }

    public ResolvedFunctionCallTranslatorList getRemovedMacroFunctions() {
        return new NoopResolvedFunctionCallTranslatorList();
    }

    public ResolvedFunctionCallTranslatorList getAddedTagFunctions() {
        return new NoopResolvedFunctionCallTranslatorList();
    }

    public ResolvedFunctionCallTranslatorList getRemovedTagFunctions() {
        return new NoopResolvedFunctionCallTranslatorList();
    }

    public void setValue(Value value) {
    }
}
