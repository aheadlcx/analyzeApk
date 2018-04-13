package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import android.view.ViewDebug.ExportedProperty;
import com.facebook.stetho.inspector.elements.android.ViewDescriptor.ViewCSSProperty;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

final class ViewDescriptor$MethodBackedCSSProperty extends ViewCSSProperty {
    private final Method mMethod;
    final /* synthetic */ ViewDescriptor this$0;

    public ViewDescriptor$MethodBackedCSSProperty(ViewDescriptor viewDescriptor, Method method, String str, @Nullable ExportedProperty exportedProperty) {
        this.this$0 = viewDescriptor;
        super(viewDescriptor, str, exportedProperty);
        this.mMethod = method;
        this.mMethod.setAccessible(true);
    }

    public Object getValue(View view) throws InvocationTargetException, IllegalAccessException {
        return this.mMethod.invoke(view, new Object[0]);
    }
}
