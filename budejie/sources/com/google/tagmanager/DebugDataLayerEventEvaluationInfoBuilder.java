package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug.DataLayerEventEvaluationInfo;
import com.google.analytics.containertag.proto.Debug.ResolvedFunctionCall;
import com.google.analytics.containertag.proto.Debug.RuleEvaluationStepInfo;

class DebugDataLayerEventEvaluationInfoBuilder implements DataLayerEventEvaluationInfoBuilder {
    private DataLayerEventEvaluationInfo dataLayerEvent;

    public DebugDataLayerEventEvaluationInfoBuilder(DataLayerEventEvaluationInfo dataLayerEventEvaluationInfo) {
        this.dataLayerEvent = dataLayerEventEvaluationInfo;
    }

    public ResolvedFunctionCallBuilder createAndAddResult() {
        ResolvedFunctionCall resolvedFunctionCall = new ResolvedFunctionCall();
        this.dataLayerEvent.results = ArrayUtils.appendToArray(this.dataLayerEvent.results, resolvedFunctionCall);
        return new DebugResolvedFunctionCallBuilder(resolvedFunctionCall);
    }

    public RuleEvaluationStepInfoBuilder createRulesEvaluation() {
        this.dataLayerEvent.rulesEvaluation = new RuleEvaluationStepInfo();
        return new DebugRuleEvaluationStepInfoBuilder(this.dataLayerEvent.rulesEvaluation);
    }
}
