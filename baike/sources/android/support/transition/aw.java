package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Property;

interface aw {
    PropertyValuesHolder ofPointF(Property<?, PointF> property, Path path);
}
