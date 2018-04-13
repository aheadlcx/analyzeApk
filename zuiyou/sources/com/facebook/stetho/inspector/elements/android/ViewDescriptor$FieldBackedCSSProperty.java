package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import android.view.ViewDebug.ExportedProperty;
import com.facebook.stetho.inspector.elements.android.ViewDescriptor.ViewCSSProperty;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;

final class ViewDescriptor$FieldBackedCSSProperty extends ViewCSSProperty {
    private final Field mField;
    final /* synthetic */ ViewDescriptor this$0;

    public ViewDescriptor$FieldBackedCSSProperty(ViewDescriptor viewDescriptor, Field field, String str, @Nullable ExportedProperty exportedProperty) {
        this.this$0 = viewDescriptor;
        super(viewDescriptor, str, exportedProperty);
        this.mField = field;
        this.mField.setAccessible(true);
    }

    public Object getValue(View view) throws InvocationTargetException, IllegalAccessException {
        return this.mField.get(view);
    }
}
