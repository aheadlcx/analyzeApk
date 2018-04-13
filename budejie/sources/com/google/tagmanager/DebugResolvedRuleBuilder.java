package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug.ResolvedFunctionCall;
import com.google.analytics.containertag.proto.Debug.ResolvedProperty;
import com.google.analytics.containertag.proto.Debug.ResolvedRule;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import com.google.tagmanager.ResourceUtil.ExpandedFunctionCall;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

class DebugResolvedRuleBuilder implements ResolvedRuleBuilder {
    ResolvedFunctionCallTranslatorList addMacrosHolder = new DebugResolvedFunctionCallListTranslator(1);
    ResolvedFunctionCallTranslatorList addTagsHolder = new DebugResolvedFunctionCallListTranslator(3);
    ResolvedFunctionCallTranslatorList removeMacrosHolder = new DebugResolvedFunctionCallListTranslator(2);
    ResolvedFunctionCallTranslatorList removeTagsHolder = new DebugResolvedFunctionCallListTranslator(4);
    ResolvedRule resolvedRule;

    class DebugResolvedFunctionCallListTranslator implements ResolvedFunctionCallTranslatorList {
        private final int type;

        class Type {
            static final int ADD_MACROS = 1;
            static final int ADD_TAGS = 3;
            static final int REMOVE_MACROS = 2;
            static final int REMOVE_TAGS = 4;

            Type() {
            }
        }

        DebugResolvedFunctionCallListTranslator(int i) {
            this.type = i;
        }

        public void translateAndAddAll(List<ExpandedFunctionCall> list, List<String> list2) {
            List arrayList = new ArrayList(list.size());
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(DebugResolvedRuleBuilder.translateExpandedFunctionCall((ExpandedFunctionCall) list.get(i)));
                if (i < list2.size()) {
                    ((ResolvedFunctionCall) arrayList.get(i)).associatedRuleName = (String) list2.get(i);
                } else {
                    ((ResolvedFunctionCall) arrayList.get(i)).associatedRuleName = "Unknown";
                }
            }
            ResolvedFunctionCall[] resolvedFunctionCallArr = (ResolvedFunctionCall[]) arrayList.toArray(new ResolvedFunctionCall[0]);
            switch (this.type) {
                case 1:
                    DebugResolvedRuleBuilder.this.resolvedRule.addMacros = resolvedFunctionCallArr;
                    return;
                case 2:
                    DebugResolvedRuleBuilder.this.resolvedRule.removeMacros = resolvedFunctionCallArr;
                    return;
                case 3:
                    DebugResolvedRuleBuilder.this.resolvedRule.addTags = resolvedFunctionCallArr;
                    return;
                case 4:
                    DebugResolvedRuleBuilder.this.resolvedRule.removeTags = resolvedFunctionCallArr;
                    return;
                default:
                    Log.e("unknown type in translateAndAddAll: " + this.type);
                    return;
            }
        }
    }

    public DebugResolvedRuleBuilder(ResolvedRule resolvedRule) {
        this.resolvedRule = resolvedRule;
    }

    public ResolvedFunctionCallBuilder createNegativePredicate() {
        ResolvedFunctionCall resolvedFunctionCall = new ResolvedFunctionCall();
        this.resolvedRule.negativePredicates = ArrayUtils.appendToArray(this.resolvedRule.negativePredicates, resolvedFunctionCall);
        return new DebugResolvedFunctionCallBuilder(resolvedFunctionCall);
    }

    public ResolvedFunctionCallBuilder createPositivePredicate() {
        ResolvedFunctionCall resolvedFunctionCall = new ResolvedFunctionCall();
        this.resolvedRule.positivePredicates = ArrayUtils.appendToArray(this.resolvedRule.positivePredicates, resolvedFunctionCall);
        return new DebugResolvedFunctionCallBuilder(resolvedFunctionCall);
    }

    public ResolvedFunctionCallTranslatorList getAddedMacroFunctions() {
        return this.addMacrosHolder;
    }

    public ResolvedFunctionCallTranslatorList getRemovedMacroFunctions() {
        return this.removeMacrosHolder;
    }

    public ResolvedFunctionCallTranslatorList getAddedTagFunctions() {
        return this.addTagsHolder;
    }

    public ResolvedFunctionCallTranslatorList getRemovedTagFunctions() {
        return this.removeTagsHolder;
    }

    public void setValue(Value value) {
        this.resolvedRule.result = DebugValueBuilder.copyImmutableValue(value);
    }

    public static ResolvedFunctionCall translateExpandedFunctionCall(ExpandedFunctionCall expandedFunctionCall) {
        ResolvedFunctionCall resolvedFunctionCall = new ResolvedFunctionCall();
        for (Entry entry : expandedFunctionCall.getProperties().entrySet()) {
            ResolvedProperty resolvedProperty = new ResolvedProperty();
            resolvedProperty.key = (String) entry.getKey();
            resolvedProperty.value = DebugValueBuilder.copyImmutableValue((Value) entry.getValue());
            resolvedFunctionCall.properties = ArrayUtils.appendToArray(resolvedFunctionCall.properties, resolvedProperty);
        }
        return resolvedFunctionCall;
    }
}
