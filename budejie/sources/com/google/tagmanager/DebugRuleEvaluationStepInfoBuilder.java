package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug.ResolvedRule;
import com.google.analytics.containertag.proto.Debug.RuleEvaluationStepInfo;
import com.google.tagmanager.ResourceUtil.ExpandedFunctionCall;
import java.util.Set;

class DebugRuleEvaluationStepInfoBuilder implements RuleEvaluationStepInfoBuilder {
    private RuleEvaluationStepInfo ruleEvaluationStepInfo;

    public DebugRuleEvaluationStepInfoBuilder(RuleEvaluationStepInfo ruleEvaluationStepInfo) {
        this.ruleEvaluationStepInfo = ruleEvaluationStepInfo;
    }

    public void setEnabledFunctions(Set<ExpandedFunctionCall> set) {
        for (ExpandedFunctionCall expandedFunctionCall : set) {
            this.ruleEvaluationStepInfo.enabledFunctions = ArrayUtils.appendToArray(this.ruleEvaluationStepInfo.enabledFunctions, DebugResolvedRuleBuilder.translateExpandedFunctionCall(expandedFunctionCall));
        }
    }

    public ResolvedRuleBuilder createResolvedRuleBuilder() {
        ResolvedRule resolvedRule = new ResolvedRule();
        this.ruleEvaluationStepInfo.rules = ArrayUtils.appendToArray(this.ruleEvaluationStepInfo.rules, resolvedRule);
        return new DebugResolvedRuleBuilder(resolvedRule);
    }
}
