package android.support.transition;

import android.view.View;

public abstract class VisibilityPropagation extends TransitionPropagation {
    private static final String PROPNAME_VIEW_CENTER = "android:visibilityPropagation:center";
    private static final String PROPNAME_VISIBILITY = "android:visibilityPropagation:visibility";
    private static final String[] VISIBILITY_PROPAGATION_VALUES = new String[]{PROPNAME_VISIBILITY, PROPNAME_VIEW_CENTER};

    public void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        Object obj = (Integer) transitionValues.values.get("android:visibility:visibility");
        if (obj == null) {
            obj = Integer.valueOf(view.getVisibility());
        }
        transitionValues.values.put(PROPNAME_VISIBILITY, obj);
        obj = new int[2];
        view.getLocationOnScreen(obj);
        obj[0] = obj[0] + Math.round(view.getTranslationX());
        obj[0] = obj[0] + (view.getWidth() / 2);
        obj[1] = obj[1] + Math.round(view.getTranslationY());
        obj[1] = (view.getHeight() / 2) + obj[1];
        transitionValues.values.put(PROPNAME_VIEW_CENTER, obj);
    }

    public String[] getPropagationProperties() {
        return VISIBILITY_PROPAGATION_VALUES;
    }

    public int getViewVisibility(TransitionValues transitionValues) {
        if (transitionValues == null) {
            return 8;
        }
        Integer num = (Integer) transitionValues.values.get(PROPNAME_VISIBILITY);
        if (num == null) {
            return 8;
        }
        return num.intValue();
    }

    public int getViewX(TransitionValues transitionValues) {
        return getViewCoordinate(transitionValues, 0);
    }

    public int getViewY(TransitionValues transitionValues) {
        return getViewCoordinate(transitionValues, 1);
    }

    private static int getViewCoordinate(TransitionValues transitionValues, int i) {
        if (transitionValues == null) {
            return -1;
        }
        int[] iArr = (int[]) transitionValues.values.get(PROPNAME_VIEW_CENTER);
        if (iArr == null) {
            return -1;
        }
        return iArr[i];
    }
}
