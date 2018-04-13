package com.facebook.stetho.inspector.elements;

import android.os.SystemClock;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.ArrayListAccumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.ShadowDocument.Update;
import com.facebook.stetho.inspector.elements.ShadowDocument.UpdateBuilder;
import com.facebook.stetho.inspector.helper.ObjectIdMapper;
import com.facebook.stetho.inspector.helper.ThreadBoundProxy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class Document extends ThreadBoundProxy {
    private Document$AttributeListAccumulator mCachedAttributeAccumulator;
    private Document$ChildEventingList mCachedChildEventingList;
    private ArrayListAccumulator<Object> mCachedChildrenAccumulator;
    private final Queue<Object> mCachedUpdateQueue = new ArrayDeque();
    private DocumentProvider mDocumentProvider;
    private final DocumentProviderFactory mFactory;
    private final ObjectIdMapper mObjectIdMapper = new Document$DocumentObjectIdMapper(this, null);
    @GuardedBy
    private int mReferenceCounter = 0;
    private ShadowDocument mShadowDocument;
    private Document$UpdateListenerCollection mUpdateListeners = new Document$UpdateListenerCollection(this);

    public Document(DocumentProviderFactory documentProviderFactory) {
        super(documentProviderFactory);
        this.mFactory = documentProviderFactory;
    }

    public synchronized void addRef() {
        int i = this.mReferenceCounter;
        this.mReferenceCounter = i + 1;
        if (i == 0) {
            init();
        }
    }

    public synchronized void release() {
        if (this.mReferenceCounter > 0) {
            int i = this.mReferenceCounter - 1;
            this.mReferenceCounter = i;
            if (i == 0) {
                cleanUp();
            }
        }
    }

    private void init() {
        this.mDocumentProvider = this.mFactory.create();
        this.mDocumentProvider.postAndWait(new Document$1(this));
    }

    private void cleanUp() {
        this.mDocumentProvider.postAndWait(new Document$2(this));
        this.mUpdateListeners.clear();
    }

    public void addUpdateListener(Document$UpdateListener document$UpdateListener) {
        this.mUpdateListeners.add(document$UpdateListener);
    }

    public void removeUpdateListener(Document$UpdateListener document$UpdateListener) {
        this.mUpdateListeners.remove(document$UpdateListener);
    }

    @Nullable
    public NodeDescriptor getNodeDescriptor(Object obj) {
        verifyThreadAccess();
        return this.mDocumentProvider.getNodeDescriptor(obj);
    }

    public void highlightElement(Object obj, int i) {
        verifyThreadAccess();
        this.mDocumentProvider.highlightElement(obj, i);
    }

    public void hideHighlight() {
        verifyThreadAccess();
        this.mDocumentProvider.hideHighlight();
    }

    public void setInspectModeEnabled(boolean z) {
        verifyThreadAccess();
        this.mDocumentProvider.setInspectModeEnabled(z);
    }

    @Nullable
    public Integer getNodeIdForElement(Object obj) {
        return this.mObjectIdMapper.getIdForObject(obj);
    }

    @Nullable
    public Object getElementForNodeId(int i) {
        return this.mObjectIdMapper.getObjectForId(i);
    }

    public void setAttributesAsText(Object obj, String str) {
        verifyThreadAccess();
        this.mDocumentProvider.setAttributesAsText(obj, str);
    }

    public void getElementStyleRuleNames(Object obj, StyleRuleNameAccumulator styleRuleNameAccumulator) {
        getNodeDescriptor(obj).getStyleRuleNames(obj, styleRuleNameAccumulator);
    }

    public void getElementStyles(Object obj, String str, StyleAccumulator styleAccumulator) {
        getNodeDescriptor(obj).getStyles(obj, str, styleAccumulator);
    }

    public void setElementStyle(Object obj, String str, String str2, String str3) {
        getNodeDescriptor(obj).setStyle(obj, str, str2, str3);
    }

    public void getElementComputedStyles(Object obj, ComputedStyleAccumulator computedStyleAccumulator) {
        getNodeDescriptor(obj).getComputedStyles(obj, computedStyleAccumulator);
    }

    public DocumentView getDocumentView() {
        verifyThreadAccess();
        return this.mShadowDocument;
    }

    public Object getRootElement() {
        verifyThreadAccess();
        Object rootElement = this.mDocumentProvider.getRootElement();
        if (rootElement == null) {
            throw new IllegalStateException();
        } else if (rootElement == this.mShadowDocument.getRootElement()) {
            return rootElement;
        } else {
            throw new IllegalStateException();
        }
    }

    public void findMatchingElements(String str, Accumulator<Integer> accumulator) {
        verifyThreadAccess();
        findMatches(this.mDocumentProvider.getRootElement(), Pattern.compile(Pattern.quote(str), 2), accumulator);
    }

    private void findMatches(Object obj, Pattern pattern, Accumulator<Integer> accumulator) {
        ElementInfo elementInfo = this.mShadowDocument.getElementInfo(obj);
        int size = elementInfo.children.size();
        for (int i = 0; i < size; i++) {
            Object obj2 = elementInfo.children.get(i);
            if (doesElementMatch(obj2, pattern)) {
                accumulator.store(this.mObjectIdMapper.getIdForObject(obj2));
            }
            findMatches(obj2, pattern, accumulator);
        }
    }

    private boolean doesElementMatch(Object obj, Pattern pattern) {
        Document$AttributeListAccumulator acquireCachedAttributeAccumulator = acquireCachedAttributeAccumulator();
        NodeDescriptor nodeDescriptor = this.mDocumentProvider.getNodeDescriptor(obj);
        nodeDescriptor.getAttributes(obj, acquireCachedAttributeAccumulator);
        int size = acquireCachedAttributeAccumulator.size();
        for (int i = 0; i < size; i++) {
            if (pattern.matcher((CharSequence) acquireCachedAttributeAccumulator.get(i)).find()) {
                releaseCachedAttributeAccumulator(acquireCachedAttributeAccumulator);
                return true;
            }
        }
        releaseCachedAttributeAccumulator(acquireCachedAttributeAccumulator);
        return pattern.matcher(nodeDescriptor.getNodeName(obj)).find();
    }

    private Document$ChildEventingList acquireChildEventingList(Object obj, DocumentView documentView) {
        Document$ChildEventingList document$ChildEventingList = this.mCachedChildEventingList;
        if (document$ChildEventingList == null) {
            document$ChildEventingList = new Document$ChildEventingList(this, null);
        }
        this.mCachedChildEventingList = null;
        document$ChildEventingList.acquire(obj, documentView);
        return document$ChildEventingList;
    }

    private void releaseChildEventingList(Document$ChildEventingList document$ChildEventingList) {
        document$ChildEventingList.release();
        if (this.mCachedChildEventingList == null) {
            this.mCachedChildEventingList = document$ChildEventingList;
        }
    }

    private Document$AttributeListAccumulator acquireCachedAttributeAccumulator() {
        Document$AttributeListAccumulator document$AttributeListAccumulator = this.mCachedAttributeAccumulator;
        if (document$AttributeListAccumulator == null) {
            document$AttributeListAccumulator = new Document$AttributeListAccumulator();
        }
        this.mCachedChildrenAccumulator = null;
        return document$AttributeListAccumulator;
    }

    private void releaseCachedAttributeAccumulator(Document$AttributeListAccumulator document$AttributeListAccumulator) {
        document$AttributeListAccumulator.clear();
        if (this.mCachedAttributeAccumulator == null) {
            this.mCachedAttributeAccumulator = document$AttributeListAccumulator;
        }
    }

    private ArrayListAccumulator<Object> acquireChildrenAccumulator() {
        ArrayListAccumulator<Object> arrayListAccumulator = this.mCachedChildrenAccumulator;
        if (arrayListAccumulator == null) {
            arrayListAccumulator = new ArrayListAccumulator();
        }
        this.mCachedChildrenAccumulator = null;
        return arrayListAccumulator;
    }

    private void releaseChildrenAccumulator(ArrayListAccumulator<Object> arrayListAccumulator) {
        arrayListAccumulator.clear();
        if (this.mCachedChildrenAccumulator == null) {
            this.mCachedChildrenAccumulator = arrayListAccumulator;
        }
    }

    private Update createShadowDocumentUpdate() {
        verifyThreadAccess();
        if (this.mDocumentProvider.getRootElement() != this.mShadowDocument.getRootElement()) {
            throw new IllegalStateException();
        }
        ArrayListAccumulator acquireChildrenAccumulator = acquireChildrenAccumulator();
        UpdateBuilder beginUpdate = this.mShadowDocument.beginUpdate();
        this.mCachedUpdateQueue.add(this.mDocumentProvider.getRootElement());
        while (!this.mCachedUpdateQueue.isEmpty()) {
            Object remove = this.mCachedUpdateQueue.remove();
            NodeDescriptor nodeDescriptor = this.mDocumentProvider.getNodeDescriptor(remove);
            this.mObjectIdMapper.putObject(remove);
            nodeDescriptor.getChildren(remove, acquireChildrenAccumulator);
            int size = acquireChildrenAccumulator.size();
            int i = 0;
            while (i < size) {
                Object obj = acquireChildrenAccumulator.get(i);
                if (obj != null) {
                    this.mCachedUpdateQueue.add(obj);
                } else {
                    LogUtil.e("%s.getChildren() emitted a null child at position %s for element %s", new Object[]{nodeDescriptor.getClass().getName(), Integer.toString(i), remove});
                    acquireChildrenAccumulator.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
            beginUpdate.setElementChildren(remove, acquireChildrenAccumulator);
            acquireChildrenAccumulator.clear();
        }
        releaseChildrenAccumulator(acquireChildrenAccumulator);
        return beginUpdate.build();
    }

    private void updateTree() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Update createShadowDocumentUpdate = createShadowDocumentUpdate();
        boolean isEmpty = createShadowDocumentUpdate.isEmpty();
        if (isEmpty) {
            createShadowDocumentUpdate.abandon();
        } else {
            applyDocumentUpdate(createShadowDocumentUpdate);
        }
        String str = "Document.updateTree() completed in %s ms%s";
        Object[] objArr = new Object[2];
        objArr[0] = Long.toString(SystemClock.elapsedRealtime() - elapsedRealtime);
        objArr[1] = isEmpty ? " (no changes)" : "";
        LogUtil.d(str, objArr);
    }

    private void applyDocumentUpdate(Update update) {
        ArrayList arrayList = new ArrayList();
        update.getGarbageElements(new Document$3(this, update, arrayList));
        Collections.sort(arrayList);
        update.getChangedElements(new Document$4(this, arrayList, update));
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            this.mObjectIdMapper.removeObjectById(((Integer) arrayList.get(i)).intValue());
        }
        update.getChangedElements(new Document$5(this, update));
        update.commit();
    }

    private static void updateListenerChildren(Document$ChildEventingList document$ChildEventingList, List<Object> list, Accumulator<Object> accumulator) {
        int i = 0;
        while (i <= document$ChildEventingList.size()) {
            if (i == document$ChildEventingList.size()) {
                if (i != list.size()) {
                    document$ChildEventingList.addWithEvent(i, list.get(i), accumulator);
                    i++;
                } else {
                    return;
                }
            } else if (i == list.size()) {
                document$ChildEventingList.removeWithEvent(i);
            } else {
                Object obj = document$ChildEventingList.get(i);
                Object obj2 = list.get(i);
                if (obj == obj2) {
                    i++;
                } else {
                    int indexOf = document$ChildEventingList.indexOf(obj2);
                    if (indexOf == -1) {
                        document$ChildEventingList.addWithEvent(i, obj2, accumulator);
                        i++;
                    } else {
                        document$ChildEventingList.removeWithEvent(indexOf);
                        document$ChildEventingList.addWithEvent(i, obj2, accumulator);
                        i++;
                    }
                }
            }
        }
    }
}
