package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class POJOPropertiesCollector {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected LinkedList<AnnotatedMember> _anyGetters;
    protected LinkedList<AnnotatedMethod> _anySetters;
    protected final AnnotatedClass _classDef;
    protected boolean _collected;
    protected final MapperConfig<?> _config;
    protected LinkedList<POJOPropertyBuilder> _creatorProperties;
    protected final boolean _forSerialization;
    protected HashSet<String> _ignoredPropertyNames;
    protected LinkedHashMap<Object, AnnotatedMember> _injectables;
    protected LinkedList<AnnotatedMethod> _jsonValueGetters;
    protected final String _mutatorPrefix;
    protected LinkedHashMap<String, POJOPropertyBuilder> _properties;
    protected final boolean _stdBeanNaming;
    protected final JavaType _type;
    protected final VisibilityChecker<?> _visibilityChecker;

    protected POJOPropertiesCollector(MapperConfig<?> mapperConfig, boolean z, JavaType javaType, AnnotatedClass annotatedClass, String str) {
        this._config = mapperConfig;
        this._stdBeanNaming = mapperConfig.isEnabled(MapperFeature.USE_STD_BEAN_NAMING);
        this._forSerialization = z;
        this._type = javaType;
        this._classDef = annotatedClass;
        if (str == null) {
            str = "set";
        }
        this._mutatorPrefix = str;
        this._annotationIntrospector = mapperConfig.isAnnotationProcessingEnabled() ? this._config.getAnnotationIntrospector() : null;
        if (this._annotationIntrospector == null) {
            this._visibilityChecker = this._config.getDefaultVisibilityChecker();
        } else {
            this._visibilityChecker = this._annotationIntrospector.findAutoDetectVisibility(annotatedClass, this._config.getDefaultVisibilityChecker());
        }
    }

    public MapperConfig<?> getConfig() {
        return this._config;
    }

    public JavaType getType() {
        return this._type;
    }

    public AnnotatedClass getClassDef() {
        return this._classDef;
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._annotationIntrospector;
    }

    public List<BeanPropertyDefinition> getProperties() {
        return new ArrayList(getPropertyMap().values());
    }

    public Map<Object, AnnotatedMember> getInjectables() {
        if (!this._collected) {
            collectAll();
        }
        return this._injectables;
    }

    public AnnotatedMethod getJsonValueMethod() {
        if (!this._collected) {
            collectAll();
        }
        if (this._jsonValueGetters == null) {
            return null;
        }
        if (this._jsonValueGetters.size() > 1) {
            reportProblem("Multiple value properties defined (" + this._jsonValueGetters.get(0) + " vs " + this._jsonValueGetters.get(1) + ")");
        }
        return (AnnotatedMethod) this._jsonValueGetters.get(0);
    }

    public AnnotatedMember getAnyGetter() {
        if (!this._collected) {
            collectAll();
        }
        if (this._anyGetters == null) {
            return null;
        }
        if (this._anyGetters.size() > 1) {
            reportProblem("Multiple 'any-getters' defined (" + this._anyGetters.get(0) + " vs " + this._anyGetters.get(1) + ")");
        }
        return (AnnotatedMember) this._anyGetters.getFirst();
    }

    public AnnotatedMethod getAnySetterMethod() {
        if (!this._collected) {
            collectAll();
        }
        if (this._anySetters == null) {
            return null;
        }
        if (this._anySetters.size() > 1) {
            reportProblem("Multiple 'any-setters' defined (" + this._anySetters.get(0) + " vs " + this._anySetters.get(1) + ")");
        }
        return (AnnotatedMethod) this._anySetters.getFirst();
    }

    public Set<String> getIgnoredPropertyNames() {
        return this._ignoredPropertyNames;
    }

    public ObjectIdInfo getObjectIdInfo() {
        if (this._annotationIntrospector == null) {
            return null;
        }
        ObjectIdInfo findObjectIdInfo = this._annotationIntrospector.findObjectIdInfo(this._classDef);
        if (findObjectIdInfo != null) {
            return this._annotationIntrospector.findObjectReferenceInfo(this._classDef, findObjectIdInfo);
        }
        return findObjectIdInfo;
    }

    public Class<?> findPOJOBuilderClass() {
        return this._annotationIntrospector.findPOJOBuilder(this._classDef);
    }

    protected Map<String, POJOPropertyBuilder> getPropertyMap() {
        if (!this._collected) {
            collectAll();
        }
        return this._properties;
    }

    @Deprecated
    public POJOPropertiesCollector collect() {
        return this;
    }

    protected void collectAll() {
        Object linkedHashMap = new LinkedHashMap();
        _addFields(linkedHashMap);
        _addMethods(linkedHashMap);
        _addCreators(linkedHashMap);
        _addInjectables(linkedHashMap);
        _removeUnwantedProperties(linkedHashMap);
        for (POJOPropertyBuilder mergeAnnotations : linkedHashMap.values()) {
            mergeAnnotations.mergeAnnotations(this._forSerialization);
        }
        _removeUnwantedAccessor(linkedHashMap);
        _renameProperties(linkedHashMap);
        PropertyNamingStrategy _findNamingStrategy = _findNamingStrategy();
        if (_findNamingStrategy != null) {
            _renameUsing(linkedHashMap, _findNamingStrategy);
        }
        for (POJOPropertyBuilder mergeAnnotations2 : linkedHashMap.values()) {
            mergeAnnotations2.trimByVisibility();
        }
        if (this._config.isEnabled(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME)) {
            _renameWithWrappers(linkedHashMap);
        }
        _sortProperties(linkedHashMap);
        this._properties = linkedHashMap;
        this._collected = true;
    }

    protected void _addFields(Map<String, POJOPropertyBuilder> map) {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        Object obj = (this._forSerialization || this._config.isEnabled(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS)) ? null : 1;
        boolean isEnabled = this._config.isEnabled(MapperFeature.PROPAGATE_TRANSIENT_MARKER);
        for (AnnotatedField annotatedField : this._classDef.fields()) {
            String name;
            PropertyName propertyName;
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4;
            String findImplicitPropertyName = annotationIntrospector == null ? null : annotationIntrospector.findImplicitPropertyName(annotatedField);
            if (findImplicitPropertyName == null) {
                name = annotatedField.getName();
            } else {
                name = findImplicitPropertyName;
            }
            if (annotationIntrospector == null) {
                propertyName = null;
            } else if (this._forSerialization) {
                propertyName = annotationIntrospector.findNameForSerialization(annotatedField);
            } else {
                propertyName = annotationIntrospector.findNameForDeserialization(annotatedField);
            }
            if (propertyName != null) {
                z = true;
            } else {
                z = false;
            }
            if (z && propertyName.isEmpty()) {
                propertyName = _propNameFromSimple(name);
                z = false;
            }
            if (propertyName != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                z2 = this._visibilityChecker.isFieldVisible(annotatedField);
            }
            if (annotationIntrospector == null || !annotationIntrospector.hasIgnoreMarker(annotatedField)) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (!annotatedField.isTransient()) {
                z4 = z3;
            } else if (isEnabled) {
                z4 = true;
                z2 = false;
            } else {
                z4 = z3;
                z2 = false;
            }
            if (obj == null || propertyName != null || z4 || !Modifier.isFinal(annotatedField.getModifiers())) {
                _property((Map) map, name).addField(annotatedField, propertyName, z, z2, z4);
            }
        }
    }

    protected void _addCreators(Map<String, POJOPropertyBuilder> map) {
        if (this._annotationIntrospector != null) {
            int parameterCount;
            int i;
            for (AnnotatedConstructor annotatedConstructor : this._classDef.getConstructors()) {
                if (this._creatorProperties == null) {
                    this._creatorProperties = new LinkedList();
                }
                parameterCount = annotatedConstructor.getParameterCount();
                for (i = 0; i < parameterCount; i++) {
                    _addCreatorParam(map, annotatedConstructor.getParameter(i));
                }
            }
            for (AnnotatedMethod annotatedMethod : this._classDef.getStaticMethods()) {
                if (this._creatorProperties == null) {
                    this._creatorProperties = new LinkedList();
                }
                parameterCount = annotatedMethod.getParameterCount();
                for (i = 0; i < parameterCount; i++) {
                    _addCreatorParam(map, annotatedMethod.getParameter(i));
                }
            }
        }
    }

    protected void _addCreatorParam(Map<String, POJOPropertyBuilder> map, AnnotatedParameter annotatedParameter) {
        String findImplicitPropertyName = this._annotationIntrospector.findImplicitPropertyName(annotatedParameter);
        if (findImplicitPropertyName == null) {
            findImplicitPropertyName = "";
        }
        PropertyName findNameForDeserialization = this._annotationIntrospector.findNameForDeserialization(annotatedParameter);
        boolean z = (findNameForDeserialization == null || findNameForDeserialization.isEmpty()) ? false : true;
        if (!z) {
            if (!findImplicitPropertyName.isEmpty() && this._annotationIntrospector.hasCreatorAnnotation(annotatedParameter.getOwner())) {
                findNameForDeserialization = PropertyName.construct(findImplicitPropertyName);
            } else {
                return;
            }
        }
        POJOPropertyBuilder _property = (z && findImplicitPropertyName.isEmpty()) ? _property((Map) map, findNameForDeserialization) : _property((Map) map, findImplicitPropertyName);
        _property.addCtor(annotatedParameter, findNameForDeserialization, z, true, false);
        this._creatorProperties.add(_property);
    }

    protected void _addMethods(Map<String, POJOPropertyBuilder> map) {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        for (AnnotatedMethod annotatedMethod : this._classDef.memberMethods()) {
            int parameterCount = annotatedMethod.getParameterCount();
            if (parameterCount == 0) {
                _addGetterMethod(map, annotatedMethod, annotationIntrospector);
            } else if (parameterCount == 1) {
                _addSetterMethod(map, annotatedMethod, annotationIntrospector);
            } else if (parameterCount == 2 && annotationIntrospector != null && annotationIntrospector.hasAnySetterAnnotation(annotatedMethod)) {
                if (this._anySetters == null) {
                    this._anySetters = new LinkedList();
                }
                this._anySetters.add(annotatedMethod);
            }
        }
    }

    protected void _addGetterMethod(Map<String, POJOPropertyBuilder> map, AnnotatedMethod annotatedMethod, AnnotationIntrospector annotationIntrospector) {
        boolean z = true;
        String str = null;
        boolean z2 = false;
        if (annotatedMethod.hasReturnType()) {
            boolean z3;
            boolean z4;
            if (annotationIntrospector != null) {
                if (annotationIntrospector.hasAnyGetterAnnotation(annotatedMethod)) {
                    if (this._anyGetters == null) {
                        this._anyGetters = new LinkedList();
                    }
                    this._anyGetters.add(annotatedMethod);
                    return;
                } else if (annotationIntrospector.hasAsValueAnnotation(annotatedMethod)) {
                    if (this._jsonValueGetters == null) {
                        this._jsonValueGetters = new LinkedList();
                    }
                    this._jsonValueGetters.add(annotatedMethod);
                    return;
                }
            }
            PropertyName findNameForSerialization = annotationIntrospector == null ? null : annotationIntrospector.findNameForSerialization(annotatedMethod);
            if (findNameForSerialization != null) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                if (annotationIntrospector != null) {
                    str = annotationIntrospector.findImplicitPropertyName(annotatedMethod);
                }
                if (str == null) {
                    str = BeanUtil.okNameForGetter(annotatedMethod, this._stdBeanNaming);
                }
                if (str == null) {
                    str = annotatedMethod.getName();
                }
                if (findNameForSerialization.isEmpty()) {
                    findNameForSerialization = _propNameFromSimple(str);
                    z3 = false;
                }
                z4 = z3;
            } else {
                if (annotationIntrospector != null) {
                    str = annotationIntrospector.findImplicitPropertyName(annotatedMethod);
                }
                if (str == null) {
                    str = BeanUtil.okNameForRegularGetter(annotatedMethod, annotatedMethod.getName(), this._stdBeanNaming);
                }
                if (str == null) {
                    str = BeanUtil.okNameForIsGetter(annotatedMethod, annotatedMethod.getName(), this._stdBeanNaming);
                    if (str != null) {
                        z = this._visibilityChecker.isIsGetterVisible(annotatedMethod);
                        z4 = z3;
                    } else {
                        return;
                    }
                }
                z = this._visibilityChecker.isGetterVisible(annotatedMethod);
                z4 = z3;
            }
            if (annotationIntrospector != null) {
                z2 = annotationIntrospector.hasIgnoreMarker(annotatedMethod);
            }
            _property((Map) map, str).addGetter(annotatedMethod, findNameForSerialization, z4, z, z2);
        }
    }

    protected void _addSetterMethod(Map<String, POJOPropertyBuilder> map, AnnotatedMethod annotatedMethod, AnnotationIntrospector annotationIntrospector) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        String str = null;
        boolean z4 = false;
        PropertyName findNameForDeserialization = annotationIntrospector == null ? null : annotationIntrospector.findNameForDeserialization(annotatedMethod);
        if (findNameForDeserialization != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (annotationIntrospector != null) {
                str = annotationIntrospector.findImplicitPropertyName(annotatedMethod);
            }
            if (str == null) {
                str = BeanUtil.okNameForMutator(annotatedMethod, this._mutatorPrefix, this._stdBeanNaming);
            }
            if (str == null) {
                str = annotatedMethod.getName();
            }
            if (findNameForDeserialization.isEmpty()) {
                findNameForDeserialization = _propNameFromSimple(str);
                z = false;
            }
            z2 = z;
        } else {
            if (annotationIntrospector != null) {
                str = annotationIntrospector.findImplicitPropertyName(annotatedMethod);
            }
            if (str == null) {
                str = BeanUtil.okNameForMutator(annotatedMethod, this._mutatorPrefix, this._stdBeanNaming);
            }
            if (str != null) {
                z3 = this._visibilityChecker.isSetterVisible(annotatedMethod);
                z2 = z;
            } else {
                return;
            }
        }
        if (annotationIntrospector != null) {
            z4 = annotationIntrospector.hasIgnoreMarker(annotatedMethod);
        }
        _property((Map) map, str).addSetter(annotatedMethod, findNameForDeserialization, z2, z3, z4);
    }

    protected void _addInjectables(Map<String, POJOPropertyBuilder> map) {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector != null) {
            for (AnnotatedField annotatedField : this._classDef.fields()) {
                _doAddInjectable(annotationIntrospector.findInjectableValueId(annotatedField), annotatedField);
            }
            for (AnnotatedMethod annotatedMethod : this._classDef.memberMethods()) {
                if (annotatedMethod.getParameterCount() == 1) {
                    _doAddInjectable(annotationIntrospector.findInjectableValueId(annotatedMethod), annotatedMethod);
                }
            }
        }
    }

    protected void _doAddInjectable(Object obj, AnnotatedMember annotatedMember) {
        if (obj != null) {
            if (this._injectables == null) {
                this._injectables = new LinkedHashMap();
            }
            if (((AnnotatedMember) this._injectables.put(obj, annotatedMember)) != null) {
                throw new IllegalArgumentException("Duplicate injectable value with id '" + String.valueOf(obj) + "' (of type " + obj.getClass().getName() + ")");
            }
        }
    }

    private PropertyName _propNameFromSimple(String str) {
        return PropertyName.construct(str, null);
    }

    protected void _removeUnwantedProperties(Map<String, POJOPropertyBuilder> map) {
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) it.next();
            if (!pOJOPropertyBuilder.anyVisible()) {
                it.remove();
            } else if (pOJOPropertyBuilder.anyIgnorals()) {
                if (pOJOPropertyBuilder.isExplicitlyIncluded()) {
                    pOJOPropertyBuilder.removeIgnored();
                    if (!(this._forSerialization || pOJOPropertyBuilder.couldDeserialize())) {
                        _collectIgnorals(pOJOPropertyBuilder.getName());
                    }
                } else {
                    it.remove();
                    _collectIgnorals(pOJOPropertyBuilder.getName());
                }
            }
        }
    }

    protected void _removeUnwantedAccessor(Map<String, POJOPropertyBuilder> map) {
        boolean isEnabled = this._config.isEnabled(MapperFeature.INFER_PROPERTY_MUTATORS);
        for (POJOPropertyBuilder removeNonVisible : map.values()) {
            removeNonVisible.removeNonVisible(isEnabled);
        }
    }

    private void _collectIgnorals(String str) {
        if (!this._forSerialization) {
            if (this._ignoredPropertyNames == null) {
                this._ignoredPropertyNames = new HashSet();
            }
            this._ignoredPropertyNames.add(str);
        }
    }

    protected void _renameProperties(Map<String, POJOPropertyBuilder> map) {
        Iterator it = map.entrySet().iterator();
        LinkedList linkedList = null;
        while (it.hasNext()) {
            POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) ((Entry) it.next()).getValue();
            Collection findExplicitNames = pOJOPropertyBuilder.findExplicitNames();
            if (!findExplicitNames.isEmpty()) {
                it.remove();
                if (linkedList == null) {
                    linkedList = new LinkedList();
                }
                if (findExplicitNames.size() == 1) {
                    linkedList.add(pOJOPropertyBuilder.withName((PropertyName) findExplicitNames.iterator().next()));
                } else {
                    linkedList.addAll(pOJOPropertyBuilder.explode(findExplicitNames));
                }
            }
        }
        if (linkedList != null) {
            Iterator it2 = linkedList.iterator();
            while (it2.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) it2.next();
                String name = pOJOPropertyBuilder2.getName();
                pOJOPropertyBuilder = (POJOPropertyBuilder) map.get(name);
                if (pOJOPropertyBuilder == null) {
                    map.put(name, pOJOPropertyBuilder2);
                } else {
                    pOJOPropertyBuilder.addAll(pOJOPropertyBuilder2);
                }
                _updateCreatorProperty(pOJOPropertyBuilder2, this._creatorProperties);
            }
        }
    }

    protected void _renameUsing(Map<String, POJOPropertyBuilder> map, PropertyNamingStrategy propertyNamingStrategy) {
        POJOPropertyBuilder[] pOJOPropertyBuilderArr = (POJOPropertyBuilder[]) map.values().toArray(new POJOPropertyBuilder[map.size()]);
        map.clear();
        for (POJOPropertyBuilder pOJOPropertyBuilder : pOJOPropertyBuilderArr) {
            POJOPropertyBuilder pOJOPropertyBuilder2;
            Object obj;
            PropertyName fullName = pOJOPropertyBuilder.getFullName();
            String str = null;
            if (!pOJOPropertyBuilder.isExplicitlyNamed() || this._config.isEnabled(MapperFeature.ALLOW_EXPLICIT_PROPERTY_RENAMING)) {
                if (this._forSerialization) {
                    if (pOJOPropertyBuilder.hasGetter()) {
                        str = propertyNamingStrategy.nameForGetterMethod(this._config, pOJOPropertyBuilder.getGetter(), fullName.getSimpleName());
                    } else if (pOJOPropertyBuilder.hasField()) {
                        str = propertyNamingStrategy.nameForField(this._config, pOJOPropertyBuilder.getField(), fullName.getSimpleName());
                    }
                } else if (pOJOPropertyBuilder.hasSetter()) {
                    str = propertyNamingStrategy.nameForSetterMethod(this._config, pOJOPropertyBuilder.getSetter(), fullName.getSimpleName());
                } else if (pOJOPropertyBuilder.hasConstructorParameter()) {
                    str = propertyNamingStrategy.nameForConstructorParameter(this._config, pOJOPropertyBuilder.getConstructorParameter(), fullName.getSimpleName());
                } else if (pOJOPropertyBuilder.hasField()) {
                    str = propertyNamingStrategy.nameForField(this._config, pOJOPropertyBuilder.getField(), fullName.getSimpleName());
                } else if (pOJOPropertyBuilder.hasGetter()) {
                    str = propertyNamingStrategy.nameForGetterMethod(this._config, pOJOPropertyBuilder.getGetter(), fullName.getSimpleName());
                }
            }
            if (str == null || fullName.hasSimpleName(str)) {
                str = fullName.getSimpleName();
                pOJOPropertyBuilder2 = pOJOPropertyBuilder;
                String str2 = str;
            } else {
                pOJOPropertyBuilder2 = pOJOPropertyBuilder.withSimpleName(str);
                obj = str;
            }
            POJOPropertyBuilder pOJOPropertyBuilder3 = (POJOPropertyBuilder) map.get(obj);
            if (pOJOPropertyBuilder3 == null) {
                map.put(obj, pOJOPropertyBuilder2);
            } else {
                pOJOPropertyBuilder3.addAll(pOJOPropertyBuilder2);
            }
            _updateCreatorProperty(pOJOPropertyBuilder2, this._creatorProperties);
        }
    }

    protected void _renameWithWrappers(Map<String, POJOPropertyBuilder> map) {
        Iterator it = map.entrySet().iterator();
        LinkedList linkedList = null;
        while (it.hasNext()) {
            POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) ((Entry) it.next()).getValue();
            Annotated primaryMember = pOJOPropertyBuilder.getPrimaryMember();
            if (primaryMember != null) {
                PropertyName findWrapperName = this._annotationIntrospector.findWrapperName(primaryMember);
                if (!(findWrapperName == null || !findWrapperName.hasSimpleName() || findWrapperName.equals(pOJOPropertyBuilder.getFullName()))) {
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                    }
                    linkedList.add(pOJOPropertyBuilder.withName(findWrapperName));
                    it.remove();
                }
            }
        }
        if (linkedList != null) {
            it = linkedList.iterator();
            while (it.hasNext()) {
                pOJOPropertyBuilder = (POJOPropertyBuilder) it.next();
                String name = pOJOPropertyBuilder.getName();
                POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) map.get(name);
                if (pOJOPropertyBuilder2 == null) {
                    map.put(name, pOJOPropertyBuilder);
                } else {
                    pOJOPropertyBuilder2.addAll(pOJOPropertyBuilder);
                }
            }
        }
    }

    protected void _sortProperties(Map<String, POJOPropertyBuilder> map) {
        boolean shouldSortPropertiesAlphabetically;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        Boolean findSerializationSortAlphabetically = annotationIntrospector == null ? null : annotationIntrospector.findSerializationSortAlphabetically(this._classDef);
        if (findSerializationSortAlphabetically == null) {
            shouldSortPropertiesAlphabetically = this._config.shouldSortPropertiesAlphabetically();
        } else {
            shouldSortPropertiesAlphabetically = findSerializationSortAlphabetically.booleanValue();
        }
        String[] findSerializationPropertyOrder = annotationIntrospector == null ? null : annotationIntrospector.findSerializationPropertyOrder(this._classDef);
        if (shouldSortPropertiesAlphabetically || this._creatorProperties != null || findSerializationPropertyOrder != null) {
            Map treeMap;
            Iterator it;
            POJOPropertyBuilder pOJOPropertyBuilder;
            int size = map.size();
            if (shouldSortPropertiesAlphabetically) {
                treeMap = new TreeMap();
            } else {
                Object linkedHashMap = new LinkedHashMap(size + size);
            }
            for (POJOPropertyBuilder pOJOPropertyBuilder2 : map.values()) {
                treeMap.put(pOJOPropertyBuilder2.getName(), pOJOPropertyBuilder2);
            }
            Map linkedHashMap2 = new LinkedHashMap(size + size);
            if (findSerializationPropertyOrder != null) {
                for (String str : findSerializationPropertyOrder) {
                    Object name;
                    Object obj = (POJOPropertyBuilder) treeMap.get(str);
                    if (obj == null) {
                        for (POJOPropertyBuilder pOJOPropertyBuilder3 : map.values()) {
                            if (str.equals(pOJOPropertyBuilder3.getInternalName())) {
                                POJOPropertyBuilder pOJOPropertyBuilder4 = pOJOPropertyBuilder3;
                                name = pOJOPropertyBuilder3.getName();
                                obj = pOJOPropertyBuilder4;
                                break;
                            }
                        }
                    }
                    String str2 = str;
                    if (obj != null) {
                        linkedHashMap2.put(name, obj);
                    }
                }
            }
            if (this._creatorProperties != null) {
                Collection values;
                if (shouldSortPropertiesAlphabetically) {
                    TreeMap treeMap2 = new TreeMap();
                    it = this._creatorProperties.iterator();
                    while (it.hasNext()) {
                        pOJOPropertyBuilder2 = (POJOPropertyBuilder) it.next();
                        treeMap2.put(pOJOPropertyBuilder2.getName(), pOJOPropertyBuilder2);
                    }
                    values = treeMap2.values();
                } else {
                    values = this._creatorProperties;
                }
                for (POJOPropertyBuilder pOJOPropertyBuilder22 : r0) {
                    linkedHashMap2.put(pOJOPropertyBuilder22.getName(), pOJOPropertyBuilder22);
                }
            }
            linkedHashMap2.putAll(treeMap);
            map.clear();
            map.putAll(linkedHashMap2);
        }
    }

    protected void reportProblem(String str) {
        throw new IllegalArgumentException("Problem with definition of " + this._classDef + ": " + str);
    }

    protected POJOPropertyBuilder _property(Map<String, POJOPropertyBuilder> map, PropertyName propertyName) {
        return _property((Map) map, propertyName.getSimpleName());
    }

    protected POJOPropertyBuilder _property(Map<String, POJOPropertyBuilder> map, String str) {
        POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) map.get(str);
        if (pOJOPropertyBuilder != null) {
            return pOJOPropertyBuilder;
        }
        pOJOPropertyBuilder = new POJOPropertyBuilder(this._config, this._annotationIntrospector, this._forSerialization, PropertyName.construct(str));
        map.put(str, pOJOPropertyBuilder);
        return pOJOPropertyBuilder;
    }

    private PropertyNamingStrategy _findNamingStrategy() {
        Object findNamingStrategy = this._annotationIntrospector == null ? null : this._annotationIntrospector.findNamingStrategy(this._classDef);
        if (findNamingStrategy == null) {
            return this._config.getPropertyNamingStrategy();
        }
        if (findNamingStrategy instanceof PropertyNamingStrategy) {
            return (PropertyNamingStrategy) findNamingStrategy;
        }
        if (findNamingStrategy instanceof Class) {
            Class cls = (Class) findNamingStrategy;
            if (cls == PropertyNamingStrategy.class) {
                return null;
            }
            if (PropertyNamingStrategy.class.isAssignableFrom(cls)) {
                HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
                if (handlerInstantiator != null) {
                    PropertyNamingStrategy namingStrategyInstance = handlerInstantiator.namingStrategyInstance(this._config, this._classDef, cls);
                    if (namingStrategyInstance != null) {
                        return namingStrategyInstance;
                    }
                }
                return (PropertyNamingStrategy) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers());
            }
            throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<PropertyNamingStrategy>");
        }
        throw new IllegalStateException("AnnotationIntrospector returned PropertyNamingStrategy definition of type " + findNamingStrategy.getClass().getName() + "; expected type PropertyNamingStrategy or Class<PropertyNamingStrategy> instead");
    }

    protected void _updateCreatorProperty(POJOPropertyBuilder pOJOPropertyBuilder, List<POJOPropertyBuilder> list) {
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (((POJOPropertyBuilder) list.get(i)).getInternalName().equals(pOJOPropertyBuilder.getInternalName())) {
                    list.set(i, pOJOPropertyBuilder);
                    return;
                }
            }
        }
    }
}
