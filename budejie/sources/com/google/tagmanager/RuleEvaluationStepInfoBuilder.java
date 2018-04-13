package com.google.tagmanager;

import com.google.tagmanager.ResourceUtil.ExpandedFunctionCall;
import java.util.Set;

interface RuleEvaluationStepInfoBuilder {
    ResolvedRuleBuilder createResolvedRuleBuilder();

    void setEnabledFunctions(Set<ExpandedFunctionCall> set);
}
