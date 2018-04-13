package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug.MacroEvaluationInfo;
import com.google.analytics.containertag.proto.Debug.ResolvedFunctionCall;
import com.google.analytics.containertag.proto.Debug.RuleEvaluationStepInfo;

class DebugMacroEvaluationInfoBuilder implements MacroEvaluationInfoBuilder {
    private MacroEvaluationInfo macroEvaluationInfo;

    public DebugMacroEvaluationInfoBuilder(MacroEvaluationInfo macroEvaluationInfo) {
        this.macroEvaluationInfo = macroEvaluationInfo;
    }

    public ResolvedFunctionCallBuilder createResult() {
        this.macroEvaluationInfo.result = new ResolvedFunctionCall();
        return new DebugResolvedFunctionCallBuilder(this.macroEvaluationInfo.result);
    }

    public RuleEvaluationStepInfoBuilder createRulesEvaluation() {
        this.macroEvaluationInfo.rulesEvaluation = new RuleEvaluationStepInfo();
        return new DebugRuleEvaluationStepInfoBuilder(this.macroEvaluationInfo.rulesEvaluation);
    }
}
