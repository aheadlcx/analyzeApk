package com.facebook.stetho.inspector.elements.android;

import android.view.Window;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.Descriptor;

class AndroidDocumentProvider$2 implements Accumulator<Object> {
    final /* synthetic */ AndroidDocumentProvider this$0;
    final /* synthetic */ Accumulator val$accumulator;

    AndroidDocumentProvider$2(AndroidDocumentProvider androidDocumentProvider, Accumulator accumulator) {
        this.this$0 = androidDocumentProvider;
        this.val$accumulator = accumulator;
    }

    public void store(Object obj) {
        if (obj instanceof Window) {
            this.val$accumulator.store((Window) obj);
            return;
        }
        Descriptor descriptor = this.this$0.getDescriptor(obj);
        if (descriptor != null) {
            descriptor.getChildren(obj, this);
        }
    }
}
