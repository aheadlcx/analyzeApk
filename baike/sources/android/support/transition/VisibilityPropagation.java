package android.support.transition;

import android.view.View;

public abstract class VisibilityPropagation extends TransitionPropagation {
    private static final String[] a = new String[]{"android:visibilityPropagation:visibility", "android:visibilityPropagation:center"};

    public void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        Object obj = (Integer) transitionValues.values.get("android:visibility:visibility");
        if (obj == null) {
            obj = Integer.valueOf(view.getVisibility());
        }
        transitionValues.values.put("android:visibilityPropagation:visibility", obj);
        obj = new int[2];
        view.getLocationOnScreen(obj);
        obj[0] = obj[0] + Math.round(view.getTranslationX());
        obj[0] = obj[0] + (view.getWidth() / 2);
        obj[1] = obj[1] + Math.round(view.getTranslationY());
        obj[1] = (view.getHeight() / 2) + obj[1];
        transitionValues.values.put("android:visibilityPropagation:center", obj);
    }

    public String[] getPropagationProperties() {
        return a;
    }

    public int getViewVisibility(TransitionValues transitionValues) {
        if (transitionValues == null) {
            return 8;
        }
        Integer num = (Integer) transitionValues.values.get("android:visibilityPropagation:visibility");
        if (num == null) {
            return 8;
        }
        return num.intValue();
    }

    public int getViewX(TransitionValues transitionValues) {
        return a(transitionValues, 0);
    }

    public int getViewY(TransitionValues transitionValues) {
        return a(transitionValues, 1);
    }

    private static int a(TransitionValues transitionValues, int i) {
        if (transitionValues == null) {
            return -1;
        }
        int[] iArr = (int[]) transitionValues.values.get("android:visibilityPropagation:center");
        if (iArr == null) {
            return -1;
        }
        return iArr[i];
    }
}
