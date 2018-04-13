package com.google.tagmanager;

import com.google.analytics.containertag.common.Key;
import com.google.analytics.containertag.proto.Serving.FunctionCall;
import com.google.analytics.containertag.proto.Serving.Property;
import com.google.analytics.containertag.proto.Serving.Resource;
import com.google.analytics.containertag.proto.Serving.Rule;
import com.google.analytics.containertag.proto.Serving.ServingValue;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class ResourceUtil {
    private static final int BUFFER_SIZE = 1024;

    public static class ExpandedFunctionCall {
        private final Map<String, Value> mPropertiesMap;
        private final Value mPushAfterEvaluate;

        private ExpandedFunctionCall(Map<String, Value> map, Value value) {
            this.mPropertiesMap = map;
            this.mPushAfterEvaluate = value;
        }

        public static ExpandedFunctionCallBuilder newBuilder() {
            return new ExpandedFunctionCallBuilder();
        }

        public void updateCacheableProperty(String str, Value value) {
            this.mPropertiesMap.put(str, value);
        }

        public Map<String, Value> getProperties() {
            return Collections.unmodifiableMap(this.mPropertiesMap);
        }

        public Value getPushAfterEvaluate() {
            return this.mPushAfterEvaluate;
        }

        public String toString() {
            return "Properties: " + getProperties() + " pushAfterEvaluate: " + this.mPushAfterEvaluate;
        }
    }

    public static class ExpandedFunctionCallBuilder {
        private final Map<String, Value> mPropertiesMap;
        private Value mPushAfterEvaluate;

        private ExpandedFunctionCallBuilder() {
            this.mPropertiesMap = new HashMap();
        }

        public ExpandedFunctionCallBuilder addProperty(String str, Value value) {
            this.mPropertiesMap.put(str, value);
            return this;
        }

        public ExpandedFunctionCallBuilder setPushAfterEvaluate(Value value) {
            this.mPushAfterEvaluate = value;
            return this;
        }

        public ExpandedFunctionCall build() {
            return new ExpandedFunctionCall(this.mPropertiesMap, this.mPushAfterEvaluate);
        }
    }

    public static class ExpandedResource {
        private final Map<String, List<ExpandedFunctionCall>> mMacros;
        private final int mResourceFormatVersion;
        private final List<ExpandedRule> mRules;
        private final String mVersion;

        private ExpandedResource(List<ExpandedRule> list, Map<String, List<ExpandedFunctionCall>> map, String str, int i) {
            this.mRules = Collections.unmodifiableList(list);
            this.mMacros = Collections.unmodifiableMap(map);
            this.mVersion = str;
            this.mResourceFormatVersion = i;
        }

        public static ExpandedResourceBuilder newBuilder() {
            return new ExpandedResourceBuilder();
        }

        public List<ExpandedRule> getRules() {
            return this.mRules;
        }

        public String getVersion() {
            return this.mVersion;
        }

        public int getResourceFormatVersion() {
            return this.mResourceFormatVersion;
        }

        public List<ExpandedFunctionCall> getMacros(String str) {
            return (List) this.mMacros.get(str);
        }

        public Map<String, List<ExpandedFunctionCall>> getAllMacros() {
            return this.mMacros;
        }

        public String toString() {
            return "Rules: " + getRules() + "  Macros: " + this.mMacros;
        }
    }

    public static class ExpandedResourceBuilder {
        private final Map<String, List<ExpandedFunctionCall>> mMacros;
        private int mResourceFormatVersion;
        private final List<ExpandedRule> mRules;
        private String mVersion;

        private ExpandedResourceBuilder() {
            this.mRules = new ArrayList();
            this.mMacros = new HashMap();
            this.mVersion = "";
            this.mResourceFormatVersion = 0;
        }

        public ExpandedResourceBuilder addRule(ExpandedRule expandedRule) {
            this.mRules.add(expandedRule);
            return this;
        }

        public ExpandedResourceBuilder addMacro(ExpandedFunctionCall expandedFunctionCall) {
            String valueToString = Types.valueToString((Value) expandedFunctionCall.getProperties().get(Key.INSTANCE_NAME.toString()));
            List list = (List) this.mMacros.get(valueToString);
            if (list == null) {
                list = new ArrayList();
                this.mMacros.put(valueToString, list);
            }
            list.add(expandedFunctionCall);
            return this;
        }

        public ExpandedResourceBuilder setVersion(String str) {
            this.mVersion = str;
            return this;
        }

        public ExpandedResourceBuilder setResourceFormatVersion(int i) {
            this.mResourceFormatVersion = i;
            return this;
        }

        public ExpandedResource build() {
            return new ExpandedResource(this.mRules, this.mMacros, this.mVersion, this.mResourceFormatVersion);
        }
    }

    public static class ExpandedRule {
        private final List<String> mAddMacroRuleNames;
        private final List<ExpandedFunctionCall> mAddMacros;
        private final List<String> mAddTagRuleNames;
        private final List<ExpandedFunctionCall> mAddTags;
        private final List<ExpandedFunctionCall> mNegativePredicates;
        private final List<ExpandedFunctionCall> mPositivePredicates;
        private final List<String> mRemoveMacroRuleNames;
        private final List<ExpandedFunctionCall> mRemoveMacros;
        private final List<String> mRemoveTagRuleNames;
        private final List<ExpandedFunctionCall> mRemoveTags;

        private ExpandedRule(List<ExpandedFunctionCall> list, List<ExpandedFunctionCall> list2, List<ExpandedFunctionCall> list3, List<ExpandedFunctionCall> list4, List<ExpandedFunctionCall> list5, List<ExpandedFunctionCall> list6, List<String> list7, List<String> list8, List<String> list9, List<String> list10) {
            this.mPositivePredicates = Collections.unmodifiableList(list);
            this.mNegativePredicates = Collections.unmodifiableList(list2);
            this.mAddTags = Collections.unmodifiableList(list3);
            this.mRemoveTags = Collections.unmodifiableList(list4);
            this.mAddMacros = Collections.unmodifiableList(list5);
            this.mRemoveMacros = Collections.unmodifiableList(list6);
            this.mAddMacroRuleNames = Collections.unmodifiableList(list7);
            this.mRemoveMacroRuleNames = Collections.unmodifiableList(list8);
            this.mAddTagRuleNames = Collections.unmodifiableList(list9);
            this.mRemoveTagRuleNames = Collections.unmodifiableList(list10);
        }

        public static ExpandedRuleBuilder newBuilder() {
            return new ExpandedRuleBuilder();
        }

        public List<ExpandedFunctionCall> getPositivePredicates() {
            return this.mPositivePredicates;
        }

        public List<ExpandedFunctionCall> getNegativePredicates() {
            return this.mNegativePredicates;
        }

        public List<ExpandedFunctionCall> getAddTags() {
            return this.mAddTags;
        }

        public List<ExpandedFunctionCall> getRemoveTags() {
            return this.mRemoveTags;
        }

        public List<ExpandedFunctionCall> getAddMacros() {
            return this.mAddMacros;
        }

        public List<String> getAddMacroRuleNames() {
            return this.mAddMacroRuleNames;
        }

        public List<String> getRemoveMacroRuleNames() {
            return this.mRemoveMacroRuleNames;
        }

        public List<String> getAddTagRuleNames() {
            return this.mAddTagRuleNames;
        }

        public List<String> getRemoveTagRuleNames() {
            return this.mRemoveTagRuleNames;
        }

        public List<ExpandedFunctionCall> getRemoveMacros() {
            return this.mRemoveMacros;
        }

        public String toString() {
            return "Positive predicates: " + getPositivePredicates() + "  Negative predicates: " + getNegativePredicates() + "  Add tags: " + getAddTags() + "  Remove tags: " + getRemoveTags() + "  Add macros: " + getAddMacros() + "  Remove macros: " + getRemoveMacros();
        }
    }

    public static class ExpandedRuleBuilder {
        private final List<String> mAddMacroRuleNames;
        private final List<ExpandedFunctionCall> mAddMacros;
        private final List<String> mAddTagRuleNames;
        private final List<ExpandedFunctionCall> mAddTags;
        private final List<ExpandedFunctionCall> mNegativePredicates;
        private final List<ExpandedFunctionCall> mPositivePredicates;
        private final List<String> mRemoveMacroRuleNames;
        private final List<ExpandedFunctionCall> mRemoveMacros;
        private final List<String> mRemoveTagRuleNames;
        private final List<ExpandedFunctionCall> mRemoveTags;

        private ExpandedRuleBuilder() {
            this.mPositivePredicates = new ArrayList();
            this.mNegativePredicates = new ArrayList();
            this.mAddTags = new ArrayList();
            this.mRemoveTags = new ArrayList();
            this.mAddMacros = new ArrayList();
            this.mRemoveMacros = new ArrayList();
            this.mAddMacroRuleNames = new ArrayList();
            this.mRemoveMacroRuleNames = new ArrayList();
            this.mAddTagRuleNames = new ArrayList();
            this.mRemoveTagRuleNames = new ArrayList();
        }

        public ExpandedRuleBuilder addPositivePredicate(ExpandedFunctionCall expandedFunctionCall) {
            this.mPositivePredicates.add(expandedFunctionCall);
            return this;
        }

        public ExpandedRuleBuilder addNegativePredicate(ExpandedFunctionCall expandedFunctionCall) {
            this.mNegativePredicates.add(expandedFunctionCall);
            return this;
        }

        public ExpandedRuleBuilder addAddTag(ExpandedFunctionCall expandedFunctionCall) {
            this.mAddTags.add(expandedFunctionCall);
            return this;
        }

        public ExpandedRuleBuilder addAddTagRuleName(String str) {
            this.mAddTagRuleNames.add(str);
            return this;
        }

        public ExpandedRuleBuilder addRemoveTag(ExpandedFunctionCall expandedFunctionCall) {
            this.mRemoveTags.add(expandedFunctionCall);
            return this;
        }

        public ExpandedRuleBuilder addRemoveTagRuleName(String str) {
            this.mRemoveTagRuleNames.add(str);
            return this;
        }

        public ExpandedRuleBuilder addAddMacro(ExpandedFunctionCall expandedFunctionCall) {
            this.mAddMacros.add(expandedFunctionCall);
            return this;
        }

        public ExpandedRuleBuilder addAddMacroRuleName(String str) {
            this.mAddMacroRuleNames.add(str);
            return this;
        }

        public ExpandedRuleBuilder addRemoveMacro(ExpandedFunctionCall expandedFunctionCall) {
            this.mRemoveMacros.add(expandedFunctionCall);
            return this;
        }

        public ExpandedRuleBuilder addRemoveMacroRuleName(String str) {
            this.mRemoveMacroRuleNames.add(str);
            return this;
        }

        public ExpandedRule build() {
            return new ExpandedRule(this.mPositivePredicates, this.mNegativePredicates, this.mAddTags, this.mRemoveTags, this.mAddMacros, this.mRemoveMacros, this.mAddMacroRuleNames, this.mRemoveMacroRuleNames, this.mAddTagRuleNames, this.mRemoveTagRuleNames);
        }
    }

    public static class InvalidResourceException extends Exception {
        public InvalidResourceException(String str) {
            super(str);
        }
    }

    private ResourceUtil() {
    }

    public static ExpandedResource getExpandedResource(Resource resource) throws InvalidResourceException {
        int i;
        int i2 = 0;
        Value[] valueArr = new Value[resource.value.length];
        for (i = 0; i < resource.value.length; i++) {
            expandValue(i, resource, valueArr, new HashSet(0));
        }
        ExpandedResourceBuilder newBuilder = ExpandedResource.newBuilder();
        List arrayList = new ArrayList();
        for (i = 0; i < resource.tag.length; i++) {
            arrayList.add(expandFunctionCall(resource.tag[i], resource, valueArr, i));
        }
        List arrayList2 = new ArrayList();
        for (i = 0; i < resource.predicate.length; i++) {
            arrayList2.add(expandFunctionCall(resource.predicate[i], resource, valueArr, i));
        }
        List arrayList3 = new ArrayList();
        for (i = 0; i < resource.macro.length; i++) {
            ExpandedFunctionCall expandFunctionCall = expandFunctionCall(resource.macro[i], resource, valueArr, i);
            newBuilder.addMacro(expandFunctionCall);
            arrayList3.add(expandFunctionCall);
        }
        Rule[] ruleArr = resource.rule;
        int length = ruleArr.length;
        while (i2 < length) {
            newBuilder.addRule(expandRule(ruleArr[i2], arrayList, arrayList3, arrayList2, resource));
            i2++;
        }
        newBuilder.setVersion(resource.version);
        newBuilder.setResourceFormatVersion(resource.resourceFormatVersion);
        return newBuilder.build();
    }

    public static Value newValueBasedOnValue(Value value) {
        Value value2 = new Value();
        value2.type = value.type;
        value2.escaping = (int[]) value.escaping.clone();
        if (value.containsReferences) {
            value2.containsReferences = value.containsReferences;
        }
        return value2;
    }

    private static Value expandValue(int i, Resource resource, Value[] valueArr, Set<Integer> set) throws InvalidResourceException {
        int i2 = 0;
        if (set.contains(Integer.valueOf(i))) {
            logAndThrow("Value cycle detected.  Current value reference: " + i + "." + "  Previous value references: " + set + ".");
        }
        Value value = (Value) getWithBoundsCheck(resource.value, i, "values");
        if (valueArr[i] != null) {
            return valueArr[i];
        }
        Value value2 = null;
        set.add(Integer.valueOf(i));
        ServingValue servingValue;
        int[] iArr;
        int length;
        int i3;
        int i4;
        switch (value.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                value2 = value;
                break;
            case 2:
                servingValue = getServingValue(value);
                value2 = newValueBasedOnValue(value);
                value2.listItem = new Value[servingValue.listItem.length];
                iArr = servingValue.listItem;
                length = iArr.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    value2.listItem[i3] = expandValue(iArr[i2], resource, valueArr, set);
                    i2++;
                    i3 = i4;
                }
                break;
            case 3:
                value2 = newValueBasedOnValue(value);
                ServingValue servingValue2 = getServingValue(value);
                if (servingValue2.mapKey.length != servingValue2.mapValue.length) {
                    logAndThrow("Uneven map keys (" + servingValue2.mapKey.length + ") and map values (" + servingValue2.mapValue.length + ")");
                }
                value2.mapKey = new Value[servingValue2.mapKey.length];
                value2.mapValue = new Value[servingValue2.mapKey.length];
                int[] iArr2 = servingValue2.mapKey;
                int length2 = iArr2.length;
                i3 = 0;
                i4 = 0;
                while (i3 < length2) {
                    int i5 = i4 + 1;
                    value2.mapKey[i4] = expandValue(iArr2[i3], resource, valueArr, set);
                    i3++;
                    i4 = i5;
                }
                iArr = servingValue2.mapValue;
                length = iArr.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    value2.mapValue[i3] = expandValue(iArr[i2], resource, valueArr, set);
                    i2++;
                    i3 = i4;
                }
                break;
            case 4:
                value2 = newValueBasedOnValue(value);
                value2.macroReference = Types.valueToString(expandValue(getServingValue(value).macroNameReference, resource, valueArr, set));
                break;
            case 7:
                value2 = newValueBasedOnValue(value);
                servingValue = getServingValue(value);
                value2.templateToken = new Value[servingValue.templateToken.length];
                iArr = servingValue.templateToken;
                length = iArr.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    value2.templateToken[i3] = expandValue(iArr[i2], resource, valueArr, set);
                    i2++;
                    i3 = i4;
                }
                break;
        }
        if (value2 == null) {
            logAndThrow("Invalid value: " + value);
        }
        valueArr[i] = value2;
        set.remove(Integer.valueOf(i));
        return value2;
    }

    private static ServingValue getServingValue(Value value) throws InvalidResourceException {
        if (((ServingValue) value.getExtension(ServingValue.ext)) == null) {
            logAndThrow("Expected a ServingValue and didn't get one. Value is: " + value);
        }
        return (ServingValue) value.getExtension(ServingValue.ext);
    }

    private static void logAndThrow(String str) throws InvalidResourceException {
        Log.e(str);
        throw new InvalidResourceException(str);
    }

    private static <T> T getWithBoundsCheck(T[] tArr, int i, String str) throws InvalidResourceException {
        if (i < 0 || i >= tArr.length) {
            logAndThrow("Index out of bounds detected: " + i + " in " + str);
        }
        return tArr[i];
    }

    private static <T> T getWithBoundsCheck(List<T> list, int i, String str) throws InvalidResourceException {
        if (i < 0 || i >= list.size()) {
            logAndThrow("Index out of bounds detected: " + i + " in " + str);
        }
        return list.get(i);
    }

    private static ExpandedFunctionCall expandFunctionCall(FunctionCall functionCall, Resource resource, Value[] valueArr, int i) throws InvalidResourceException {
        ExpandedFunctionCallBuilder newBuilder = ExpandedFunctionCall.newBuilder();
        for (int valueOf : functionCall.property) {
            Property property = (Property) getWithBoundsCheck(resource.property, Integer.valueOf(valueOf).intValue(), "properties");
            String str = (String) getWithBoundsCheck(resource.key, property.key, "keys");
            Value value = (Value) getWithBoundsCheck((Object[]) valueArr, property.value, "values");
            if (Key.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                newBuilder.setPushAfterEvaluate(value);
            } else {
                newBuilder.addProperty(str, value);
            }
        }
        return newBuilder.build();
    }

    private static ExpandedRule expandRule(Rule rule, List<ExpandedFunctionCall> list, List<ExpandedFunctionCall> list2, List<ExpandedFunctionCall> list3, Resource resource) {
        ExpandedRuleBuilder newBuilder = ExpandedRule.newBuilder();
        for (int valueOf : rule.positivePredicate) {
            newBuilder.addPositivePredicate((ExpandedFunctionCall) list3.get(Integer.valueOf(valueOf).intValue()));
        }
        for (int valueOf2 : rule.negativePredicate) {
            newBuilder.addNegativePredicate((ExpandedFunctionCall) list3.get(Integer.valueOf(valueOf2).intValue()));
        }
        for (int valueOf22 : rule.addTag) {
            newBuilder.addAddTag((ExpandedFunctionCall) list.get(Integer.valueOf(valueOf22).intValue()));
        }
        for (int valueOf3 : rule.addTagRuleName) {
            newBuilder.addAddTagRuleName(resource.value[Integer.valueOf(valueOf3).intValue()].string);
        }
        for (int valueOf222 : rule.removeTag) {
            newBuilder.addRemoveTag((ExpandedFunctionCall) list.get(Integer.valueOf(valueOf222).intValue()));
        }
        for (int valueOf32 : rule.removeTagRuleName) {
            newBuilder.addRemoveTagRuleName(resource.value[Integer.valueOf(valueOf32).intValue()].string);
        }
        for (int valueOf2222 : rule.addMacro) {
            newBuilder.addAddMacro((ExpandedFunctionCall) list2.get(Integer.valueOf(valueOf2222).intValue()));
        }
        for (int valueOf322 : rule.addMacroRuleName) {
            newBuilder.addAddMacroRuleName(resource.value[Integer.valueOf(valueOf322).intValue()].string);
        }
        for (int valueOf22222 : rule.removeMacro) {
            newBuilder.addRemoveMacro((ExpandedFunctionCall) list2.get(Integer.valueOf(valueOf22222).intValue()));
        }
        for (int valueOf4 : rule.removeMacroRuleName) {
            newBuilder.addRemoveMacroRuleName(resource.value[Integer.valueOf(valueOf4).intValue()].string);
        }
        return newBuilder.build();
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }
}
