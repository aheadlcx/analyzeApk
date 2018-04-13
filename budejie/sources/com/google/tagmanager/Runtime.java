package com.google.tagmanager;

import android.content.Context;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.containertag.proto.Serving.Supplemental;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.tagmanager.CacheFactory.CacheSizeManager;
import com.google.tagmanager.CustomFunctionCall.CustomEvaluator;
import com.google.tagmanager.ResourceUtil.ExpandedFunctionCall;
import com.google.tagmanager.ResourceUtil.ExpandedResource;
import com.google.tagmanager.ResourceUtil.ExpandedRule;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Runtime {
    static final String DEFAULT_RULE_NAME = "Unknown";
    private static final ObjectAndStatic<Value> DEFAULT_VALUE_AND_STATIC = new ObjectAndStatic(Types.getDefaultValue(), true);
    static final String EXPERIMENT_SUPPLEMENTAL_NAME_PREFIX = "gaExperiment:";
    private static final int MAX_CACHE_SIZE = 1048576;
    private final EventInfoDistributor eventInfoDistributor;
    private volatile String mCurrentEventName;
    private final DataLayer mDataLayer;
    private final Cache<ExpandedFunctionCall, ObjectAndStatic<Value>> mFunctionCallCache;
    private final Cache<String, CachedMacro> mMacroEvaluationCache;
    private final Map<String, MacroInfo> mMacroLookup;
    private final Map<String, FunctionCallImplementation> mMacroMap;
    private final Map<String, FunctionCallImplementation> mPredicateMap;
    private final ExpandedResource mResource;
    private final Set<ExpandedRule> mRules;
    private final Map<String, FunctionCallImplementation> mTrackingTagMap;

    interface AddRemoveSetPopulator {
        void rulePassed(ExpandedRule expandedRule, Set<ExpandedFunctionCall> set, Set<ExpandedFunctionCall> set2, ResolvedRuleBuilder resolvedRuleBuilder);
    }

    private static class CachedMacro {
        private ObjectAndStatic<Value> mObjectAndStatic;
        private Value mPushAfterEvaluate;

        public CachedMacro(ObjectAndStatic<Value> objectAndStatic) {
            this(objectAndStatic, null);
        }

        public CachedMacro(ObjectAndStatic<Value> objectAndStatic, Value value) {
            this.mObjectAndStatic = objectAndStatic;
            this.mPushAfterEvaluate = value;
        }

        public ObjectAndStatic<Value> getObjectAndStatic() {
            return this.mObjectAndStatic;
        }

        public Value getPushAfterEvaluate() {
            return this.mPushAfterEvaluate;
        }

        public int getSize() {
            return (this.mPushAfterEvaluate == null ? 0 : this.mPushAfterEvaluate.getCachedSize()) + ((Value) this.mObjectAndStatic.getObject()).getCachedSize();
        }
    }

    private static class MacroInfo {
        private final Map<ExpandedRule, List<String>> mAddMacroNames = new HashMap();
        private final Map<ExpandedRule, List<ExpandedFunctionCall>> mAddMacros = new HashMap();
        private ExpandedFunctionCall mDefault;
        private final Map<ExpandedRule, List<String>> mRemoveMacroNames = new HashMap();
        private final Map<ExpandedRule, List<ExpandedFunctionCall>> mRemoveMacros = new HashMap();
        private final Set<ExpandedRule> mRules = new HashSet();

        public Set<ExpandedRule> getRules() {
            return this.mRules;
        }

        public void addRule(ExpandedRule expandedRule) {
            this.mRules.add(expandedRule);
        }

        public Map<ExpandedRule, List<ExpandedFunctionCall>> getAddMacros() {
            return this.mAddMacros;
        }

        public Map<ExpandedRule, List<String>> getAddMacroRuleNames() {
            return this.mAddMacroNames;
        }

        public Map<ExpandedRule, List<String>> getRemoveMacroRuleNames() {
            return this.mRemoveMacroNames;
        }

        public void addAddMacroForRule(ExpandedRule expandedRule, ExpandedFunctionCall expandedFunctionCall) {
            List list = (List) this.mAddMacros.get(expandedRule);
            if (list == null) {
                list = new ArrayList();
                this.mAddMacros.put(expandedRule, list);
            }
            list.add(expandedFunctionCall);
        }

        public void addAddMacroRuleNameForRule(ExpandedRule expandedRule, String str) {
            List list = (List) this.mAddMacroNames.get(expandedRule);
            if (list == null) {
                list = new ArrayList();
                this.mAddMacroNames.put(expandedRule, list);
            }
            list.add(str);
        }

        public Map<ExpandedRule, List<ExpandedFunctionCall>> getRemoveMacros() {
            return this.mRemoveMacros;
        }

        public void addRemoveMacroForRule(ExpandedRule expandedRule, ExpandedFunctionCall expandedFunctionCall) {
            List list = (List) this.mRemoveMacros.get(expandedRule);
            if (list == null) {
                list = new ArrayList();
                this.mRemoveMacros.put(expandedRule, list);
            }
            list.add(expandedFunctionCall);
        }

        public void addRemoveMacroRuleNameForRule(ExpandedRule expandedRule, String str) {
            List list = (List) this.mRemoveMacroNames.get(expandedRule);
            if (list == null) {
                list = new ArrayList();
                this.mRemoveMacroNames.put(expandedRule, list);
            }
            list.add(str);
        }

        public ExpandedFunctionCall getDefault() {
            return this.mDefault;
        }

        public void setDefault(ExpandedFunctionCall expandedFunctionCall) {
            this.mDefault = expandedFunctionCall;
        }
    }

    public Runtime(Context context, ExpandedResource expandedResource, DataLayer dataLayer, CustomEvaluator customEvaluator, CustomEvaluator customEvaluator2, EventInfoDistributor eventInfoDistributor) {
        if (expandedResource == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.mResource = expandedResource;
        this.mRules = new HashSet(expandedResource.getRules());
        this.mDataLayer = dataLayer;
        this.eventInfoDistributor = eventInfoDistributor;
        this.mFunctionCallCache = new CacheFactory().createCache(1048576, new CacheSizeManager<ExpandedFunctionCall, ObjectAndStatic<Value>>() {
            public int sizeOf(ExpandedFunctionCall expandedFunctionCall, ObjectAndStatic<Value> objectAndStatic) {
                return ((Value) objectAndStatic.getObject()).getCachedSize();
            }
        });
        this.mMacroEvaluationCache = new CacheFactory().createCache(1048576, new CacheSizeManager<String, CachedMacro>() {
            public int sizeOf(String str, CachedMacro cachedMacro) {
                return str.length() + cachedMacro.getSize();
            }
        });
        this.mTrackingTagMap = new HashMap();
        addTrackingTag(new ArbitraryPixelTag(context));
        addTrackingTag(new CustomFunctionCall(customEvaluator2));
        addTrackingTag(new DataLayerWriteTag(dataLayer));
        addTrackingTag(new UniversalAnalyticsTag(context, dataLayer));
        this.mPredicateMap = new HashMap();
        addPredicate(new ContainsPredicate());
        addPredicate(new EndsWithPredicate());
        addPredicate(new EqualsPredicate());
        addPredicate(new GreaterEqualsPredicate());
        addPredicate(new GreaterThanPredicate());
        addPredicate(new LessEqualsPredicate());
        addPredicate(new LessThanPredicate());
        addPredicate(new RegexPredicate());
        addPredicate(new StartsWithPredicate());
        this.mMacroMap = new HashMap();
        addMacro(new AdvertiserIdMacro(context));
        addMacro(new AdvertisingTrackingEnabledMacro());
        addMacro(new AdwordsClickReferrerMacro(context));
        addMacro(new AppIdMacro(context));
        addMacro(new AppNameMacro(context));
        addMacro(new AppVersionMacro(context));
        addMacro(new ConstantMacro());
        addMacro(new ContainerVersionMacro(this));
        addMacro(new CustomFunctionCall(customEvaluator));
        addMacro(new DataLayerMacro(dataLayer));
        addMacro(new DeviceIdMacro(context));
        addMacro(new DeviceNameMacro());
        addMacro(new EncodeMacro());
        addMacro(new EventMacro(this));
        addMacro(new GtmVersionMacro());
        addMacro(new HashMacro());
        addMacro(new InstallReferrerMacro(context));
        addMacro(new JoinerMacro());
        addMacro(new LanguageMacro());
        addMacro(new MobileAdwordsUniqueIdMacro(context));
        addMacro(new OsVersionMacro());
        addMacro(new PlatformMacro());
        addMacro(new RandomMacro());
        addMacro(new RegexGroupMacro());
        addMacro(new ResolutionMacro(context));
        addMacro(new RuntimeVersionMacro());
        addMacro(new SdkVersionMacro());
        addMacro(new TimeMacro());
        this.mMacroLookup = new HashMap();
        for (ExpandedRule expandedRule : this.mRules) {
            if (eventInfoDistributor.debugMode()) {
                verifyFunctionAndNameListSizes(expandedRule.getAddMacros(), expandedRule.getAddMacroRuleNames(), "add macro");
                verifyFunctionAndNameListSizes(expandedRule.getRemoveMacros(), expandedRule.getRemoveMacroRuleNames(), "remove macro");
                verifyFunctionAndNameListSizes(expandedRule.getAddTags(), expandedRule.getAddTagRuleNames(), "add tag");
                verifyFunctionAndNameListSizes(expandedRule.getRemoveTags(), expandedRule.getRemoveTagRuleNames(), "remove tag");
            }
            int i = 0;
            while (i < expandedRule.getAddMacros().size()) {
                ExpandedFunctionCall expandedFunctionCall = (ExpandedFunctionCall) expandedRule.getAddMacros().get(i);
                String str = DEFAULT_RULE_NAME;
                if (eventInfoDistributor.debugMode() && i < expandedRule.getAddMacroRuleNames().size()) {
                    str = (String) expandedRule.getAddMacroRuleNames().get(i);
                }
                MacroInfo orAddMacroInfo = getOrAddMacroInfo(this.mMacroLookup, getFunctionName(expandedFunctionCall));
                orAddMacroInfo.addRule(expandedRule);
                orAddMacroInfo.addAddMacroForRule(expandedRule, expandedFunctionCall);
                orAddMacroInfo.addAddMacroRuleNameForRule(expandedRule, str);
                i++;
            }
            i = 0;
            while (i < expandedRule.getRemoveMacros().size()) {
                expandedFunctionCall = (ExpandedFunctionCall) expandedRule.getRemoveMacros().get(i);
                str = DEFAULT_RULE_NAME;
                if (eventInfoDistributor.debugMode() && i < expandedRule.getRemoveMacroRuleNames().size()) {
                    str = (String) expandedRule.getRemoveMacroRuleNames().get(i);
                }
                orAddMacroInfo = getOrAddMacroInfo(this.mMacroLookup, getFunctionName(expandedFunctionCall));
                orAddMacroInfo.addRule(expandedRule);
                orAddMacroInfo.addRemoveMacroForRule(expandedRule, expandedFunctionCall);
                orAddMacroInfo.addRemoveMacroRuleNameForRule(expandedRule, str);
                i++;
            }
        }
        for (Entry entry : this.mResource.getAllMacros().entrySet()) {
            for (ExpandedFunctionCall expandedFunctionCall2 : (List) entry.getValue()) {
                if (!Types.valueToBoolean((Value) expandedFunctionCall2.getProperties().get(Key.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                    getOrAddMacroInfo(this.mMacroLookup, (String) entry.getKey()).setDefault(expandedFunctionCall2);
                }
            }
        }
    }

    public Runtime(Context context, ExpandedResource expandedResource, DataLayer dataLayer, CustomEvaluator customEvaluator, CustomEvaluator customEvaluator2) {
        this(context, expandedResource, dataLayer, customEvaluator, customEvaluator2, new NoopEventInfoDistributor());
    }

    public ExpandedResource getResource() {
        return this.mResource;
    }

    public synchronized void setSupplementals(List<Supplemental> list) {
        for (Supplemental supplemental : list) {
            if (supplemental.name == null || !supplemental.name.startsWith(EXPERIMENT_SUPPLEMENTAL_NAME_PREFIX)) {
                Log.v("Ignored supplemental: " + supplemental);
            } else {
                ExperimentMacroHelper.handleExperimentSupplemental(this.mDataLayer, supplemental);
            }
        }
    }

    public synchronized void evaluateTags(String str) {
        setCurrentEventName(str);
        EventInfoBuilder createDataLayerEventEvaluationEventInfo = this.eventInfoDistributor.createDataLayerEventEvaluationEventInfo(str);
        DataLayerEventEvaluationInfoBuilder createDataLayerEventEvaluationInfoBuilder = createDataLayerEventEvaluationEventInfo.createDataLayerEventEvaluationInfoBuilder();
        for (ExpandedFunctionCall executeFunction : (Set) calculateTagsToRun(this.mRules, createDataLayerEventEvaluationInfoBuilder.createRulesEvaluation()).getObject()) {
            executeFunction(this.mTrackingTagMap, executeFunction, new HashSet(), createDataLayerEventEvaluationInfoBuilder.createAndAddResult());
        }
        createDataLayerEventEvaluationEventInfo.processEventInfo();
        setCurrentEventName(null);
    }

    public ObjectAndStatic<Value> evaluateMacroReference(String str) {
        EventInfoBuilder createMacroEvalutionEventInfo = this.eventInfoDistributor.createMacroEvalutionEventInfo(str);
        ObjectAndStatic<Value> evaluateMacroReferenceCycleDetection = evaluateMacroReferenceCycleDetection(str, new HashSet(), createMacroEvalutionEventInfo.createMacroEvaluationInfoBuilder());
        createMacroEvalutionEventInfo.processEventInfo();
        return evaluateMacroReferenceCycleDetection;
    }

    @VisibleForTesting
    synchronized void setCurrentEventName(String str) {
        this.mCurrentEventName = str;
    }

    synchronized String getCurrentEventName() {
        return this.mCurrentEventName;
    }

    @VisibleForTesting
    ObjectAndStatic<Set<ExpandedFunctionCall>> calculateMacrosToRun(String str, Set<ExpandedRule> set, Map<ExpandedRule, List<ExpandedFunctionCall>> map, Map<ExpandedRule, List<String>> map2, Map<ExpandedRule, List<ExpandedFunctionCall>> map3, Map<ExpandedRule, List<String>> map4, Set<String> set2, RuleEvaluationStepInfoBuilder ruleEvaluationStepInfoBuilder) {
        final Map<ExpandedRule, List<ExpandedFunctionCall>> map5 = map;
        final Map<ExpandedRule, List<String>> map6 = map2;
        final Map<ExpandedRule, List<ExpandedFunctionCall>> map7 = map3;
        final Map<ExpandedRule, List<String>> map8 = map4;
        return calculateGenericToRun(set, set2, new AddRemoveSetPopulator() {
            public void rulePassed(ExpandedRule expandedRule, Set<ExpandedFunctionCall> set, Set<ExpandedFunctionCall> set2, ResolvedRuleBuilder resolvedRuleBuilder) {
                List list = (List) map5.get(expandedRule);
                List list2 = (List) map6.get(expandedRule);
                if (list != null) {
                    set.addAll(list);
                    resolvedRuleBuilder.getAddedMacroFunctions().translateAndAddAll(list, list2);
                }
                list = (List) map7.get(expandedRule);
                list2 = (List) map8.get(expandedRule);
                if (list != null) {
                    set2.addAll(list);
                    resolvedRuleBuilder.getRemovedMacroFunctions().translateAndAddAll(list, list2);
                }
            }
        }, ruleEvaluationStepInfoBuilder);
    }

    @VisibleForTesting
    ObjectAndStatic<Set<ExpandedFunctionCall>> calculateTagsToRun(Set<ExpandedRule> set, RuleEvaluationStepInfoBuilder ruleEvaluationStepInfoBuilder) {
        return calculateGenericToRun(set, new HashSet(), new AddRemoveSetPopulator() {
            public void rulePassed(ExpandedRule expandedRule, Set<ExpandedFunctionCall> set, Set<ExpandedFunctionCall> set2, ResolvedRuleBuilder resolvedRuleBuilder) {
                set.addAll(expandedRule.getAddTags());
                set2.addAll(expandedRule.getRemoveTags());
                resolvedRuleBuilder.getAddedTagFunctions().translateAndAddAll(expandedRule.getAddTags(), expandedRule.getAddTagRuleNames());
                resolvedRuleBuilder.getRemovedTagFunctions().translateAndAddAll(expandedRule.getRemoveTags(), expandedRule.getRemoveTagRuleNames());
            }
        }, ruleEvaluationStepInfoBuilder);
    }

    private static MacroInfo getOrAddMacroInfo(Map<String, MacroInfo> map, String str) {
        MacroInfo macroInfo = (MacroInfo) map.get(str);
        if (macroInfo != null) {
            return macroInfo;
        }
        macroInfo = new MacroInfo();
        map.put(str, macroInfo);
        return macroInfo;
    }

    private ObjectAndStatic<Set<ExpandedFunctionCall>> calculateGenericToRun(Set<ExpandedRule> set, Set<String> set2, AddRemoveSetPopulator addRemoveSetPopulator, RuleEvaluationStepInfoBuilder ruleEvaluationStepInfoBuilder) {
        Set hashSet = new HashSet();
        Collection hashSet2 = new HashSet();
        boolean z = true;
        for (ExpandedRule expandedRule : set) {
            ResolvedRuleBuilder createResolvedRuleBuilder = ruleEvaluationStepInfoBuilder.createResolvedRuleBuilder();
            ObjectAndStatic evaluatePredicatesInRule = evaluatePredicatesInRule(expandedRule, set2, createResolvedRuleBuilder);
            if (((Boolean) evaluatePredicatesInRule.getObject()).booleanValue()) {
                addRemoveSetPopulator.rulePassed(expandedRule, hashSet, hashSet2, createResolvedRuleBuilder);
            }
            boolean z2 = z && evaluatePredicatesInRule.isStatic();
            z = z2;
        }
        hashSet.removeAll(hashSet2);
        ruleEvaluationStepInfoBuilder.setEnabledFunctions(hashSet);
        return new ObjectAndStatic(hashSet, z);
    }

    private static String getFunctionName(ExpandedFunctionCall expandedFunctionCall) {
        return Types.valueToString((Value) expandedFunctionCall.getProperties().get(Key.INSTANCE_NAME.toString()));
    }

    private static void addFunctionImplToMap(Map<String, FunctionCallImplementation> map, FunctionCallImplementation functionCallImplementation) {
        if (map.containsKey(functionCallImplementation.getInstanceFunctionId())) {
            throw new IllegalArgumentException("Duplicate function type name: " + functionCallImplementation.getInstanceFunctionId());
        }
        map.put(functionCallImplementation.getInstanceFunctionId(), functionCallImplementation);
    }

    @VisibleForTesting
    void addMacro(FunctionCallImplementation functionCallImplementation) {
        addFunctionImplToMap(this.mMacroMap, functionCallImplementation);
    }

    @VisibleForTesting
    void addTrackingTag(FunctionCallImplementation functionCallImplementation) {
        addFunctionImplToMap(this.mTrackingTagMap, functionCallImplementation);
    }

    @VisibleForTesting
    void addPredicate(FunctionCallImplementation functionCallImplementation) {
        addFunctionImplToMap(this.mPredicateMap, functionCallImplementation);
    }

    @VisibleForTesting
    ObjectAndStatic<Boolean> evaluatePredicate(ExpandedFunctionCall expandedFunctionCall, Set<String> set, ResolvedFunctionCallBuilder resolvedFunctionCallBuilder) {
        ObjectAndStatic executeFunction = executeFunction(this.mPredicateMap, expandedFunctionCall, set, resolvedFunctionCallBuilder);
        Boolean valueToBoolean = Types.valueToBoolean((Value) executeFunction.getObject());
        resolvedFunctionCallBuilder.setFunctionResult(Types.objectToValue(valueToBoolean));
        return new ObjectAndStatic(valueToBoolean, executeFunction.isStatic());
    }

    @VisibleForTesting
    ObjectAndStatic<Boolean> evaluatePredicatesInRule(ExpandedRule expandedRule, Set<String> set, ResolvedRuleBuilder resolvedRuleBuilder) {
        boolean z = true;
        for (ExpandedFunctionCall evaluatePredicate : expandedRule.getNegativePredicates()) {
            ObjectAndStatic evaluatePredicate2 = evaluatePredicate(evaluatePredicate, set, resolvedRuleBuilder.createNegativePredicate());
            if (((Boolean) evaluatePredicate2.getObject()).booleanValue()) {
                resolvedRuleBuilder.setValue(Types.objectToValue(Boolean.valueOf(false)));
                return new ObjectAndStatic(Boolean.valueOf(false), evaluatePredicate2.isStatic());
            }
            boolean z2;
            if (z && evaluatePredicate2.isStatic()) {
                z2 = true;
            } else {
                z2 = false;
            }
            z = z2;
        }
        for (ExpandedFunctionCall evaluatePredicate3 : expandedRule.getPositivePredicates()) {
            evaluatePredicate2 = evaluatePredicate(evaluatePredicate3, set, resolvedRuleBuilder.createPositivePredicate());
            if (((Boolean) evaluatePredicate2.getObject()).booleanValue()) {
                z = z && evaluatePredicate2.isStatic();
            } else {
                resolvedRuleBuilder.setValue(Types.objectToValue(Boolean.valueOf(false)));
                return new ObjectAndStatic(Boolean.valueOf(false), evaluatePredicate2.isStatic());
            }
        }
        resolvedRuleBuilder.setValue(Types.objectToValue(Boolean.valueOf(true)));
        return new ObjectAndStatic(Boolean.valueOf(true), z);
    }

    private ObjectAndStatic<Value> evaluateMacroReferenceCycleDetection(String str, Set<String> set, MacroEvaluationInfoBuilder macroEvaluationInfoBuilder) {
        CachedMacro cachedMacro = (CachedMacro) this.mMacroEvaluationCache.get(str);
        if (cachedMacro == null || this.eventInfoDistributor.debugMode()) {
            MacroInfo macroInfo = (MacroInfo) this.mMacroLookup.get(str);
            if (macroInfo == null) {
                Log.e("Invalid macro: " + str);
                return DEFAULT_VALUE_AND_STATIC;
            }
            ExpandedFunctionCall expandedFunctionCall;
            ObjectAndStatic calculateMacrosToRun = calculateMacrosToRun(str, macroInfo.getRules(), macroInfo.getAddMacros(), macroInfo.getAddMacroRuleNames(), macroInfo.getRemoveMacros(), macroInfo.getRemoveMacroRuleNames(), set, macroEvaluationInfoBuilder.createRulesEvaluation());
            if (((Set) calculateMacrosToRun.getObject()).isEmpty()) {
                expandedFunctionCall = macroInfo.getDefault();
            } else {
                if (((Set) calculateMacrosToRun.getObject()).size() > 1) {
                    Log.w("Multiple macros active for macroName " + str);
                }
                expandedFunctionCall = (ExpandedFunctionCall) ((Set) calculateMacrosToRun.getObject()).iterator().next();
            }
            if (expandedFunctionCall == null) {
                return DEFAULT_VALUE_AND_STATIC;
            }
            ObjectAndStatic executeFunction = executeFunction(this.mMacroMap, expandedFunctionCall, set, macroEvaluationInfoBuilder.createResult());
            boolean z = calculateMacrosToRun.isStatic() && executeFunction.isStatic();
            ObjectAndStatic<Value> objectAndStatic = executeFunction == DEFAULT_VALUE_AND_STATIC ? DEFAULT_VALUE_AND_STATIC : new ObjectAndStatic(executeFunction.getObject(), z);
            Value pushAfterEvaluate = expandedFunctionCall.getPushAfterEvaluate();
            if (objectAndStatic.isStatic()) {
                this.mMacroEvaluationCache.put(str, new CachedMacro(objectAndStatic, pushAfterEvaluate));
            }
            pushUnevaluatedValueToDataLayer(pushAfterEvaluate, set);
            return objectAndStatic;
        }
        pushUnevaluatedValueToDataLayer(cachedMacro.getPushAfterEvaluate(), set);
        return cachedMacro.getObjectAndStatic();
    }

    private void pushUnevaluatedValueToDataLayer(Value value, Set<String> set) {
        if (value != null) {
            ObjectAndStatic macroExpandValue = macroExpandValue(value, set, new NoopValueBuilder());
            if (macroExpandValue != DEFAULT_VALUE_AND_STATIC) {
                Object valueToObject = Types.valueToObject((Value) macroExpandValue.getObject());
                if (valueToObject instanceof Map) {
                    this.mDataLayer.push((Map) valueToObject);
                } else if (valueToObject instanceof List) {
                    for (Object valueToObject2 : (List) valueToObject2) {
                        if (valueToObject2 instanceof Map) {
                            this.mDataLayer.push((Map) valueToObject2);
                        } else {
                            Log.w("pushAfterEvaluate: value not a Map");
                        }
                    }
                } else {
                    Log.w("pushAfterEvaluate: value not a Map or List");
                }
            }
        }
    }

    private ObjectAndStatic<Value> macroExpandValue(Value value, Set<String> set, ValueBuilder valueBuilder) {
        if (!value.containsReferences) {
            return new ObjectAndStatic(value, true);
        }
        Value newValueBasedOnValue;
        int i;
        ObjectAndStatic macroExpandValue;
        switch (value.type) {
            case 2:
                newValueBasedOnValue = ResourceUtil.newValueBasedOnValue(value);
                newValueBasedOnValue.listItem = new Value[value.listItem.length];
                for (i = 0; i < value.listItem.length; i++) {
                    macroExpandValue = macroExpandValue(value.listItem[i], set, valueBuilder.getListItem(i));
                    if (macroExpandValue == DEFAULT_VALUE_AND_STATIC) {
                        return DEFAULT_VALUE_AND_STATIC;
                    }
                    newValueBasedOnValue.listItem[i] = (Value) macroExpandValue.getObject();
                }
                return new ObjectAndStatic(newValueBasedOnValue, false);
            case 3:
                newValueBasedOnValue = ResourceUtil.newValueBasedOnValue(value);
                if (value.mapKey.length != value.mapValue.length) {
                    Log.e("Invalid serving value: " + value.toString());
                    return DEFAULT_VALUE_AND_STATIC;
                }
                newValueBasedOnValue.mapKey = new Value[value.mapKey.length];
                newValueBasedOnValue.mapValue = new Value[value.mapKey.length];
                for (i = 0; i < value.mapKey.length; i++) {
                    macroExpandValue = macroExpandValue(value.mapKey[i], set, valueBuilder.getMapKey(i));
                    ObjectAndStatic macroExpandValue2 = macroExpandValue(value.mapValue[i], set, valueBuilder.getMapValue(i));
                    if (macroExpandValue == DEFAULT_VALUE_AND_STATIC || macroExpandValue2 == DEFAULT_VALUE_AND_STATIC) {
                        return DEFAULT_VALUE_AND_STATIC;
                    }
                    newValueBasedOnValue.mapKey[i] = (Value) macroExpandValue.getObject();
                    newValueBasedOnValue.mapValue[i] = (Value) macroExpandValue2.getObject();
                }
                return new ObjectAndStatic(newValueBasedOnValue, false);
            case 4:
                if (set.contains(value.macroReference)) {
                    Log.e("Macro cycle detected.  Current macro reference: " + value.macroReference + "." + "  Previous macro references: " + set.toString() + ".");
                    return DEFAULT_VALUE_AND_STATIC;
                }
                set.add(value.macroReference);
                ObjectAndStatic<Value> applyEscapings = ValueEscapeUtil.applyEscapings(evaluateMacroReferenceCycleDetection(value.macroReference, set, valueBuilder.createValueMacroEvaluationInfoExtension()), value.escaping);
                set.remove(value.macroReference);
                return applyEscapings;
            case 7:
                newValueBasedOnValue = ResourceUtil.newValueBasedOnValue(value);
                newValueBasedOnValue.templateToken = new Value[value.templateToken.length];
                for (i = 0; i < value.templateToken.length; i++) {
                    macroExpandValue = macroExpandValue(value.templateToken[i], set, valueBuilder.getTemplateToken(i));
                    if (macroExpandValue == DEFAULT_VALUE_AND_STATIC) {
                        return DEFAULT_VALUE_AND_STATIC;
                    }
                    newValueBasedOnValue.templateToken[i] = (Value) macroExpandValue.getObject();
                }
                return new ObjectAndStatic(newValueBasedOnValue, false);
            default:
                Log.e("Unknown type: " + value.type);
                return DEFAULT_VALUE_AND_STATIC;
        }
    }

    private ObjectAndStatic<Value> executeFunction(Map<String, FunctionCallImplementation> map, ExpandedFunctionCall expandedFunctionCall, Set<String> set, ResolvedFunctionCallBuilder resolvedFunctionCallBuilder) {
        boolean z = true;
        Value value = (Value) expandedFunctionCall.getProperties().get(Key.FUNCTION.toString());
        if (value == null) {
            Log.e("No function id in properties");
            return DEFAULT_VALUE_AND_STATIC;
        }
        String str = value.functionId;
        FunctionCallImplementation functionCallImplementation = (FunctionCallImplementation) map.get(str);
        if (functionCallImplementation == null) {
            Log.e(str + " has no backing implementation.");
            return DEFAULT_VALUE_AND_STATIC;
        }
        ObjectAndStatic<Value> objectAndStatic = (ObjectAndStatic) this.mFunctionCallCache.get(expandedFunctionCall);
        if (objectAndStatic != null && !this.eventInfoDistributor.debugMode()) {
            return objectAndStatic;
        }
        Map hashMap = new HashMap();
        boolean z2 = true;
        for (Entry entry : expandedFunctionCall.getProperties().entrySet()) {
            ObjectAndStatic macroExpandValue = macroExpandValue((Value) entry.getValue(), set, resolvedFunctionCallBuilder.createResolvedPropertyBuilder((String) entry.getKey()).createPropertyValueBuilder((Value) entry.getValue()));
            if (macroExpandValue == DEFAULT_VALUE_AND_STATIC) {
                return DEFAULT_VALUE_AND_STATIC;
            }
            boolean z3;
            if (macroExpandValue.isStatic()) {
                expandedFunctionCall.updateCacheableProperty((String) entry.getKey(), (Value) macroExpandValue.getObject());
                z3 = z2;
            } else {
                z3 = false;
            }
            hashMap.put(entry.getKey(), macroExpandValue.getObject());
            z2 = z3;
        }
        if (functionCallImplementation.hasRequiredKeys(hashMap.keySet())) {
            if (!(z2 && functionCallImplementation.isCacheable())) {
                z = false;
            }
            objectAndStatic = new ObjectAndStatic(functionCallImplementation.evaluate(hashMap), z);
            if (z) {
                this.mFunctionCallCache.put(expandedFunctionCall, objectAndStatic);
            }
            resolvedFunctionCallBuilder.setFunctionResult((Value) objectAndStatic.getObject());
            return objectAndStatic;
        }
        Log.e("Incorrect keys for function " + str + " required " + functionCallImplementation.getRequiredKeys() + " had " + hashMap.keySet());
        return DEFAULT_VALUE_AND_STATIC;
    }

    private static void verifyFunctionAndNameListSizes(List<ExpandedFunctionCall> list, List<String> list2, String str) {
        if (list.size() != list2.size()) {
            Log.i("Invalid resource: imbalance of rule names of functions for " + str + " operation. Using default rule name instead");
        }
    }
}
