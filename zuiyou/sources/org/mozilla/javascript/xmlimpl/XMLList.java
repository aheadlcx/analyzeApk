package org.mozilla.javascript.xmlimpl;

import android.support.v4.app.NotificationCompat;
import java.util.ArrayList;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xml.XMLObject;

class XMLList extends XMLObjectImpl implements Function {
    static final long serialVersionUID = -4543618751670781135L;
    private InternalList _annos = new InternalList();
    private XMLObjectImpl targetObject = null;
    private XmlNode$QName targetProperty = null;

    XMLList(XMLLibImpl xMLLibImpl, Scriptable scriptable, XMLObject xMLObject) {
        super(xMLLibImpl, scriptable, xMLObject);
    }

    InternalList getNodeList() {
        return this._annos;
    }

    void setTargets(XMLObjectImpl xMLObjectImpl, XmlNode$QName xmlNode$QName) {
        this.targetObject = xMLObjectImpl;
        this.targetProperty = xmlNode$QName;
    }

    private XML getXmlFromAnnotation(int i) {
        return getXML(this._annos, i);
    }

    XML getXML() {
        if (length() == 1) {
            return getXmlFromAnnotation(0);
        }
        return null;
    }

    private void internalRemoveFromList(int i) {
        this._annos.remove(i);
    }

    void replace(int i, XML xml) {
        if (i < length()) {
            InternalList internalList = new InternalList();
            internalList.add(this._annos, 0, i);
            internalList.add(xml);
            internalList.add(this._annos, i + 1, length());
            this._annos = internalList;
        }
    }

    private void insert(int i, XML xml) {
        if (i < length()) {
            InternalList internalList = new InternalList();
            internalList.add(this._annos, 0, i);
            internalList.add(xml);
            internalList.add(this._annos, i, length());
            this._annos = internalList;
        }
    }

    public String getClassName() {
        return "XMLList";
    }

    public Object get(int i, Scriptable scriptable) {
        if (i < 0 || i >= length()) {
            return Scriptable.NOT_FOUND;
        }
        return getXmlFromAnnotation(i);
    }

    boolean hasXMLProperty(XMLName xMLName) {
        return getPropertyList(xMLName).length() > 0;
    }

    public boolean has(int i, Scriptable scriptable) {
        return i >= 0 && i < length();
    }

    void putXMLProperty(XMLName xMLName, Object obj) {
        if (obj == null) {
            obj = "null";
        } else if (obj instanceof Undefined) {
            obj = "undefined";
        }
        if (length() > 1) {
            throw ScriptRuntime.typeError("Assignment to lists with more than one item is not supported");
        } else if (length() == 0) {
            if (this.targetObject == null || this.targetProperty == null || this.targetProperty.getLocalName() == null || this.targetProperty.getLocalName().length() <= 0) {
                throw ScriptRuntime.typeError("Assignment to empty XMLList without targets not supported");
            }
            addToList(newTextElementXML(null, this.targetProperty, null));
            if (xMLName.isAttributeName()) {
                setAttribute(xMLName, obj);
            } else {
                item(0).putXMLProperty(xMLName, obj);
                replace(0, item(0));
            }
            this.targetObject.putXMLProperty(XMLName.formProperty(this.targetProperty.getNamespace().getUri(), this.targetProperty.getLocalName()), this);
            replace(0, this.targetObject.getXML().getLastXmlChild());
        } else if (xMLName.isAttributeName()) {
            setAttribute(xMLName, obj);
        } else {
            item(0).putXMLProperty(xMLName, obj);
            replace(0, item(0));
        }
    }

    Object getXMLProperty(XMLName xMLName) {
        return getPropertyList(xMLName);
    }

    private void replaceNode(XML xml, XML xml2) {
        xml.replaceWith(xml2);
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        Object obj2;
        int i2 = 1;
        Object obj3 = Undefined.instance;
        if (obj == null) {
            obj3 = "null";
        } else if (obj instanceof Undefined) {
            obj3 = "undefined";
        } else {
            obj3 = obj;
        }
        if (obj3 instanceof XMLObject) {
            obj2 = (XMLObject) obj3;
        } else if (this.targetProperty == null) {
            obj2 = newXMLFromJs(obj3.toString());
        } else {
            XML item;
            XML item2 = item(i);
            if (item2 == null) {
                item = item(0);
                item2 = item == null ? newTextElementXML(null, this.targetProperty, null) : item.copy();
            }
            item2.setChildren(obj3);
            item = item2;
        }
        obj3 = i < length() ? item(i).parent() : length() == 0 ? this.targetObject != null ? this.targetObject.getXML() : parent() : parent();
        XML xml;
        XMLList xMLList;
        if (obj3 instanceof XML) {
            xml = (XML) obj3;
            if (i < length()) {
                XML xmlFromAnnotation = getXmlFromAnnotation(i);
                if (obj2 instanceof XML) {
                    replaceNode(xmlFromAnnotation, (XML) obj2);
                    replace(i, xmlFromAnnotation);
                    return;
                } else if (obj2 instanceof XMLList) {
                    xMLList = (XMLList) obj2;
                    if (xMLList.length() > 0) {
                        int childIndex = xmlFromAnnotation.childIndex();
                        replaceNode(xmlFromAnnotation, xMLList.item(0));
                        replace(i, xMLList.item(0));
                        i2 = childIndex;
                        for (childIndex = 1; childIndex < xMLList.length(); childIndex++) {
                            xml.insertChildAfter(xml.getXmlChild(i2), xMLList.item(childIndex));
                            i2++;
                            insert(i + childIndex, xMLList.item(childIndex));
                        }
                        return;
                    }
                    return;
                } else {
                    return;
                }
            }
            xml.appendChild(obj2);
            addToList(xml.getLastXmlChild());
        } else if (i < length()) {
            xml = getXML(this._annos, i);
            if (obj2 instanceof XML) {
                replaceNode(xml, (XML) obj2);
                replace(i, xml);
            } else if (obj2 instanceof XMLList) {
                xMLList = (XMLList) obj2;
                if (xMLList.length() > 0) {
                    replaceNode(xml, xMLList.item(0));
                    replace(i, xMLList.item(0));
                    while (i2 < xMLList.length()) {
                        insert(i + i2, xMLList.item(i2));
                        i2++;
                    }
                }
            }
        } else {
            addToList(obj2);
        }
    }

    private XML getXML(InternalList internalList, int i) {
        if (i < 0 || i >= length()) {
            return null;
        }
        return xmlFromNode(internalList.item(i));
    }

    void deleteXMLProperty(XMLName xMLName) {
        for (int i = 0; i < length(); i++) {
            XML xmlFromAnnotation = getXmlFromAnnotation(i);
            if (xmlFromAnnotation.isElement()) {
                xmlFromAnnotation.deleteXMLProperty(xMLName);
            }
        }
    }

    public void delete(int i) {
        if (i >= 0 && i < length()) {
            getXmlFromAnnotation(i).remove();
            internalRemoveFromList(i);
        }
    }

    public Object[] getIds() {
        int i = 0;
        if (isPrototype()) {
            return new Object[0];
        }
        Object[] objArr = new Object[length()];
        while (i < objArr.length) {
            objArr[i] = Integer.valueOf(i);
            i++;
        }
        return objArr;
    }

    public Object[] getIdsForDebug() {
        return getIds();
    }

    void remove() {
        for (int length = length() - 1; length >= 0; length--) {
            XML xmlFromAnnotation = getXmlFromAnnotation(length);
            if (xmlFromAnnotation != null) {
                xmlFromAnnotation.remove();
                internalRemoveFromList(length);
            }
        }
    }

    XML item(int i) {
        return this._annos != null ? getXmlFromAnnotation(i) : createEmptyXML();
    }

    private void setAttribute(XMLName xMLName, Object obj) {
        for (int i = 0; i < length(); i++) {
            getXmlFromAnnotation(i).setAttribute(xMLName, obj);
        }
    }

    void addToList(Object obj) {
        this._annos.addToList(obj);
    }

    XMLList child(int i) {
        XMLList newXMLList = newXMLList();
        for (int i2 = 0; i2 < length(); i2++) {
            newXMLList.addToList(getXmlFromAnnotation(i2).child(i));
        }
        return newXMLList;
    }

    XMLList child(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        for (int i = 0; i < length(); i++) {
            newXMLList.addToList(getXmlFromAnnotation(i).child(xMLName));
        }
        return newXMLList;
    }

    void addMatches(XMLList xMLList, XMLName xMLName) {
        for (int i = 0; i < length(); i++) {
            getXmlFromAnnotation(i).addMatches(xMLList, xMLName);
        }
    }

    XMLList children() {
        int i;
        int i2 = 0;
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < length(); i3++) {
            XML xmlFromAnnotation = getXmlFromAnnotation(i3);
            if (xmlFromAnnotation != null) {
                XMLList children = xmlFromAnnotation.children();
                int length = children.length();
                for (i = 0; i < length; i++) {
                    arrayList.add(children.item(i));
                }
            }
        }
        XMLList newXMLList = newXMLList();
        i = arrayList.size();
        while (i2 < i) {
            newXMLList.addToList(arrayList.get(i2));
            i2++;
        }
        return newXMLList;
    }

    XMLList comments() {
        XMLList newXMLList = newXMLList();
        for (int i = 0; i < length(); i++) {
            newXMLList.addToList(getXmlFromAnnotation(i).comments());
        }
        return newXMLList;
    }

    XMLList elements(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        for (int i = 0; i < length(); i++) {
            newXMLList.addToList(getXmlFromAnnotation(i).elements(xMLName));
        }
        return newXMLList;
    }

    boolean contains(Object obj) {
        for (int i = 0; i < length(); i++) {
            if (getXmlFromAnnotation(i).equivalentXml(obj)) {
                return true;
            }
        }
        return false;
    }

    XMLObjectImpl copy() {
        XMLObjectImpl newXMLList = newXMLList();
        for (int i = 0; i < length(); i++) {
            newXMLList.addToList(getXmlFromAnnotation(i).copy());
        }
        return newXMLList;
    }

    boolean hasOwnProperty(XMLName xMLName) {
        if (isPrototype()) {
            if (findPrototypeId(xMLName.localName()) != 0) {
                return true;
            }
            return false;
        } else if (getPropertyList(xMLName).length() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    boolean hasComplexContent() {
        int length = length();
        if (length == 0) {
            return false;
        }
        if (length == 1) {
            return getXmlFromAnnotation(0).hasComplexContent();
        }
        for (int i = 0; i < length; i++) {
            if (getXmlFromAnnotation(i).isElement()) {
                return true;
            }
        }
        return false;
    }

    boolean hasSimpleContent() {
        if (length() == 0) {
            return true;
        }
        if (length() == 1) {
            return getXmlFromAnnotation(0).hasSimpleContent();
        }
        for (int i = 0; i < length(); i++) {
            if (getXmlFromAnnotation(i).isElement()) {
                return false;
            }
        }
        return true;
    }

    int length() {
        if (this._annos != null) {
            return this._annos.length();
        }
        return 0;
    }

    void normalize() {
        for (int i = 0; i < length(); i++) {
            getXmlFromAnnotation(i).normalize();
        }
    }

    Object parent() {
        if (length() == 0) {
            return Undefined.instance;
        }
        XML xml = null;
        int i = 0;
        while (i < length()) {
            Object parent = getXmlFromAnnotation(i).parent();
            if (!(parent instanceof XML)) {
                return Undefined.instance;
            }
            XML xml2 = (XML) parent;
            if (i != 0) {
                if (!xml.is(xml2)) {
                    return Undefined.instance;
                }
                xml2 = xml;
            }
            i++;
            xml = xml2;
        }
        return xml;
    }

    XMLList processingInstructions(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        for (int i = 0; i < length(); i++) {
            newXMLList.addToList(getXmlFromAnnotation(i).processingInstructions(xMLName));
        }
        return newXMLList;
    }

    boolean propertyIsEnumerable(Object obj) {
        long intValue;
        if (obj instanceof Integer) {
            intValue = (long) ((Integer) obj).intValue();
        } else if (obj instanceof Number) {
            double doubleValue = ((Number) obj).doubleValue();
            intValue = (long) doubleValue;
            if (((double) intValue) != doubleValue) {
                return false;
            }
            if (intValue == 0 && 1.0d / doubleValue < 0.0d) {
                return false;
            }
        } else {
            intValue = ScriptRuntime.testUint32String(ScriptRuntime.toString(obj));
        }
        if (0 > intValue || intValue >= ((long) length())) {
            return false;
        }
        return true;
    }

    XMLList text() {
        XMLList newXMLList = newXMLList();
        for (int i = 0; i < length(); i++) {
            newXMLList.addToList(getXmlFromAnnotation(i).text());
        }
        return newXMLList;
    }

    public String toString() {
        if (!hasSimpleContent()) {
            return toXMLString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length(); i++) {
            XML xmlFromAnnotation = getXmlFromAnnotation(i);
            if (!(xmlFromAnnotation.isComment() || xmlFromAnnotation.isProcessingInstruction())) {
                stringBuilder.append(xmlFromAnnotation.toString());
            }
        }
        return stringBuilder.toString();
    }

    String toSource(int i) {
        return toXMLString();
    }

    String toXMLString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < length()) {
            if (getProcessor().isPrettyPrinting() && i != 0) {
                stringBuilder.append('\n');
            }
            stringBuilder.append(getXmlFromAnnotation(i).toXMLString());
            i++;
        }
        return stringBuilder.toString();
    }

    Object valueOf() {
        return this;
    }

    boolean equivalentXml(Object obj) {
        if ((obj instanceof Undefined) && length() == 0) {
            return true;
        }
        if (length() == 1) {
            return getXmlFromAnnotation(0).equivalentXml(obj);
        }
        if (!(obj instanceof XMLList)) {
            return false;
        }
        XMLList xMLList = (XMLList) obj;
        if (xMLList.length() != length()) {
            return false;
        }
        for (int i = 0; i < length(); i++) {
            if (!getXmlFromAnnotation(i).equivalentXml(xMLList.getXmlFromAnnotation(i))) {
                return false;
            }
        }
        return true;
    }

    private XMLList getPropertyList(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        XmlNode$QName xmlNode$QName = null;
        if (!(xMLName.isDescendants() || xMLName.isAttributeName())) {
            xmlNode$QName = xMLName.toQname();
        }
        newXMLList.setTargets(this, xmlNode$QName);
        for (int i = 0; i < length(); i++) {
            newXMLList.addToList(getXmlFromAnnotation(i).getPropertyList(xMLName));
        }
        return newXMLList;
    }

    private Object applyOrCall(boolean z, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        String str = z ? "apply" : NotificationCompat.CATEGORY_CALL;
        if ((scriptable2 instanceof XMLList) && ((XMLList) scriptable2).targetProperty != null) {
            return ScriptRuntime.applyOrCall(z, context, scriptable, scriptable2, objArr);
        }
        throw ScriptRuntime.typeError1("msg.isnt.function", str);
    }

    protected Object jsConstructor(Context context, boolean z, Object[] objArr) {
        if (objArr.length == 0) {
            return newXMLList();
        }
        Object obj = objArr[0];
        return (z || !(obj instanceof XMLList)) ? newXMLListFrom(obj) : obj;
    }

    public Scriptable getExtraMethodSource(Context context) {
        if (length() == 1) {
            return getXmlFromAnnotation(0);
        }
        return null;
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (this.targetProperty == null) {
            throw ScriptRuntime.notFunctionError(this);
        }
        String localName = this.targetProperty.getLocalName();
        boolean equals = localName.equals("apply");
        if (equals || localName.equals(NotificationCompat.CATEGORY_CALL)) {
            return applyOrCall(equals, context, scriptable, scriptable2, objArr);
        }
        if (scriptable2 instanceof XMLObject) {
            Object obj;
            Object obj2 = null;
            Scriptable scriptable3 = scriptable2;
            Scriptable scriptable4 = scriptable2;
            while (scriptable4 instanceof XMLObject) {
                XMLObject xMLObject = (XMLObject) scriptable4;
                obj2 = xMLObject.getFunctionProperty(context, localName);
                if (obj2 != Scriptable.NOT_FOUND) {
                    obj = obj2;
                    break;
                }
                Scriptable scriptable5;
                Scriptable extraMethodSource = xMLObject.getExtraMethodSource(context);
                if (extraMethodSource == null) {
                    obj = obj2;
                    scriptable5 = scriptable3;
                } else if (extraMethodSource instanceof XMLObject) {
                    obj = obj2;
                    scriptable5 = extraMethodSource;
                } else {
                    obj = ScriptableObject.getProperty(extraMethodSource, localName);
                    scriptable5 = extraMethodSource;
                }
                scriptable3 = scriptable5;
                obj2 = obj;
                scriptable4 = extraMethodSource;
            }
            obj = obj2;
            if (obj instanceof Callable) {
                return ((Callable) obj).call(context, scriptable, scriptable3, objArr);
            }
            throw ScriptRuntime.notFunctionError(scriptable3, obj, localName);
        }
        throw ScriptRuntime.typeError1("msg.incompat.call", localName);
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        throw ScriptRuntime.typeError1("msg.not.ctor", "XMLList");
    }
}
