package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.Document$UpdateListener;
import com.facebook.stetho.inspector.elements.DocumentView;
import com.facebook.stetho.inspector.protocol.module.DOM.AttributeModifiedEvent;
import com.facebook.stetho.inspector.protocol.module.DOM.AttributeRemovedEvent;
import com.facebook.stetho.inspector.protocol.module.DOM.ChildNodeInsertedEvent;
import com.facebook.stetho.inspector.protocol.module.DOM.ChildNodeRemovedEvent;
import com.facebook.stetho.inspector.protocol.module.DOM.InspectNodeRequestedEvent;

final class DOM$DocumentUpdateListener implements Document$UpdateListener {
    final /* synthetic */ DOM this$0;

    private DOM$DocumentUpdateListener(DOM dom) {
        this.this$0 = dom;
    }

    public void onAttributeModified(Object obj, String str, String str2) {
        AttributeModifiedEvent attributeModifiedEvent = new AttributeModifiedEvent(null);
        attributeModifiedEvent.nodeId = DOM.access$300(this.this$0).getNodeIdForElement(obj).intValue();
        attributeModifiedEvent.name = str;
        attributeModifiedEvent.value = str2;
        DOM.access$1200(this.this$0).sendNotificationToPeers("DOM.attributeModified", attributeModifiedEvent);
    }

    public void onAttributeRemoved(Object obj, String str) {
        AttributeRemovedEvent attributeRemovedEvent = new AttributeRemovedEvent(null);
        attributeRemovedEvent.nodeId = DOM.access$300(this.this$0).getNodeIdForElement(obj).intValue();
        attributeRemovedEvent.name = str;
        DOM.access$1200(this.this$0).sendNotificationToPeers("DOM.attributeRemoved", attributeRemovedEvent);
    }

    public void onInspectRequested(Object obj) {
        Integer nodeIdForElement = DOM.access$300(this.this$0).getNodeIdForElement(obj);
        if (nodeIdForElement == null) {
            LogUtil.d("DocumentProvider.Listener.onInspectRequested() called for a non-mapped node: element=%s", obj);
            return;
        }
        InspectNodeRequestedEvent inspectNodeRequestedEvent = new InspectNodeRequestedEvent(null);
        inspectNodeRequestedEvent.nodeId = nodeIdForElement.intValue();
        DOM.access$1200(this.this$0).sendNotificationToPeers("DOM.inspectNodeRequested", inspectNodeRequestedEvent);
    }

    public void onChildNodeRemoved(int i, int i2) {
        ChildNodeRemovedEvent access$1500 = DOM.access$1500(this.this$0);
        access$1500.parentNodeId = i;
        access$1500.nodeId = i2;
        DOM.access$1200(this.this$0).sendNotificationToPeers("DOM.childNodeRemoved", access$1500);
        DOM.access$1600(this.this$0, access$1500);
    }

    public void onChildNodeInserted(DocumentView documentView, Object obj, int i, int i2, Accumulator<Object> accumulator) {
        ChildNodeInsertedEvent access$1700 = DOM.access$1700(this.this$0);
        access$1700.parentNodeId = i;
        access$1700.previousNodeId = i2;
        access$1700.node = DOM.access$400(this.this$0, obj, documentView, accumulator);
        DOM.access$1200(this.this$0).sendNotificationToPeers("DOM.childNodeInserted", access$1700);
        DOM.access$1800(this.this$0, access$1700);
    }
}
