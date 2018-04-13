package com.facebook.stetho.inspector.elements.android;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import com.facebook.stetho.common.android.AccessibilityUtil;

public final class AccessibilityNodeInfoWrapper {
    public static AccessibilityNodeInfoCompat createNodeInfoFromView(View view) {
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
        ViewCompat.onInitializeAccessibilityNodeInfo(view, obtain);
        return obtain;
    }

    public static boolean getIsAccessibilityFocused(View view) {
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        boolean isAccessibilityFocused = createNodeInfoFromView.isAccessibilityFocused();
        createNodeInfoFromView.recycle();
        return isAccessibilityFocused;
    }

    public static boolean getIgnored(View view) {
        int importantForAccessibility = ViewCompat.getImportantForAccessibility(view);
        if (importantForAccessibility == 2 || importantForAccessibility == 4) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (ViewCompat.getImportantForAccessibility((View) parent) == 4) {
                return true;
            }
        }
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            if (!createNodeInfoFromView.isVisibleToUser()) {
                return true;
            }
            if (AccessibilityUtil.isAccessibilityFocusable(createNodeInfoFromView, view)) {
                if (createNodeInfoFromView.getChildCount() <= 0) {
                    createNodeInfoFromView.recycle();
                    return false;
                } else if (AccessibilityUtil.isSpeakingNode(createNodeInfoFromView, view)) {
                    createNodeInfoFromView.recycle();
                    return false;
                } else {
                    createNodeInfoFromView.recycle();
                    return true;
                }
            } else if (AccessibilityUtil.hasFocusableAncestor(createNodeInfoFromView, view) || !AccessibilityUtil.hasText(createNodeInfoFromView)) {
                createNodeInfoFromView.recycle();
                return true;
            } else {
                createNodeInfoFromView.recycle();
                return false;
            }
        } finally {
            createNodeInfoFromView.recycle();
        }
    }

    public static String getIgnoredReasons(View view) {
        int importantForAccessibility = ViewCompat.getImportantForAccessibility(view);
        if (importantForAccessibility == 2) {
            return "View has importantForAccessibility set to 'NO'.";
        }
        if (importantForAccessibility == 4) {
            return "View has importantForAccessibility set to 'NO_HIDE_DESCENDANTS'.";
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (ViewCompat.getImportantForAccessibility((View) parent) == 4) {
                return "An ancestor View has importantForAccessibility set to 'NO_HIDE_DESCENDANTS'.";
            }
        }
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            String str;
            if (!createNodeInfoFromView.isVisibleToUser()) {
                str = "View is not visible.";
                return str;
            } else if (AccessibilityUtil.isAccessibilityFocusable(createNodeInfoFromView, view)) {
                str = "View is actionable, but has no description.";
                createNodeInfoFromView.recycle();
                return str;
            } else if (AccessibilityUtil.hasText(createNodeInfoFromView)) {
                str = "View is not actionable, and an ancestor View has co-opted its description.";
                createNodeInfoFromView.recycle();
                return str;
            } else {
                str = "View is not actionable and has no description.";
                createNodeInfoFromView.recycle();
                return str;
            }
        } finally {
            createNodeInfoFromView.recycle();
        }
    }

    @Nullable
    public static String getFocusableReasons(View view) {
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            String str;
            boolean hasText = AccessibilityUtil.hasText(createNodeInfoFromView);
            boolean isCheckable = createNodeInfoFromView.isCheckable();
            boolean hasNonActionableSpeakingDescendants = AccessibilityUtil.hasNonActionableSpeakingDescendants(createNodeInfoFromView, view);
            if (AccessibilityUtil.isActionableForAccessibility(createNodeInfoFromView)) {
                if (createNodeInfoFromView.getChildCount() <= 0) {
                    str = "View is actionable and has no children.";
                    return str;
                } else if (hasText) {
                    str = "View is actionable and has a description.";
                    createNodeInfoFromView.recycle();
                    return str;
                } else if (isCheckable) {
                    str = "View is actionable and checkable.";
                    createNodeInfoFromView.recycle();
                    return str;
                } else if (hasNonActionableSpeakingDescendants) {
                    str = "View is actionable and has non-actionable descendants with descriptions.";
                    createNodeInfoFromView.recycle();
                    return str;
                }
            }
            if (AccessibilityUtil.isTopLevelScrollItem(createNodeInfoFromView, view)) {
                if (hasText) {
                    str = "View is a direct child of a scrollable container and has a description.";
                    createNodeInfoFromView.recycle();
                    return str;
                } else if (isCheckable) {
                    str = "View is a direct child of a scrollable container and is checkable.";
                    createNodeInfoFromView.recycle();
                    return str;
                } else if (hasNonActionableSpeakingDescendants) {
                    str = "View is a direct child of a scrollable container and has non-actionable descendants with descriptions.";
                    createNodeInfoFromView.recycle();
                    return str;
                }
            }
            if (hasText) {
                str = "View has a description and is not actionable, but has no actionable ancestor.";
                createNodeInfoFromView.recycle();
                return str;
            }
            createNodeInfoFromView.recycle();
            return null;
        } finally {
            createNodeInfoFromView.recycle();
        }
    }

    @Nullable
    public static String getActions(View view) {
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String str = ", ";
            for (AccessibilityActionCompat accessibilityActionCompat : createNodeInfoFromView.getActionList()) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                switch (accessibilityActionCompat.getId()) {
                    case 1:
                        stringBuilder.append("focus");
                        break;
                    case 2:
                        stringBuilder.append("clear-focus");
                        break;
                    case 4:
                        stringBuilder.append("select");
                        break;
                    case 8:
                        stringBuilder.append("clear-selection");
                        break;
                    case 16:
                        stringBuilder.append("click");
                        break;
                    case 32:
                        stringBuilder.append("long-click");
                        break;
                    case 64:
                        stringBuilder.append("accessibility-focus");
                        break;
                    case 128:
                        stringBuilder.append("clear-accessibility-focus");
                        break;
                    case 256:
                        stringBuilder.append("next-at-movement-granularity");
                        break;
                    case 512:
                        stringBuilder.append("previous-at-movement-granularity");
                        break;
                    case 1024:
                        stringBuilder.append("next-html-element");
                        break;
                    case 2048:
                        stringBuilder.append("previous-html-element");
                        break;
                    case 4096:
                        stringBuilder.append("scroll-forward");
                        break;
                    case 8192:
                        stringBuilder.append("scroll-backward");
                        break;
                    case 16384:
                        stringBuilder.append("copy");
                        break;
                    case 32768:
                        stringBuilder.append("paste");
                        break;
                    case 65536:
                        stringBuilder.append("cut");
                        break;
                    case 131072:
                        stringBuilder.append("set-selection");
                        break;
                    default:
                        CharSequence label = accessibilityActionCompat.getLabel();
                        if (label == null) {
                            stringBuilder.append("unknown");
                            break;
                        }
                        stringBuilder.append(label);
                        break;
                }
            }
            if (stringBuilder.length() > 0) {
                str = stringBuilder.toString();
            } else {
                str = null;
            }
            createNodeInfoFromView.recycle();
            return str;
        } catch (Throwable th) {
            createNodeInfoFromView.recycle();
        }
    }

    @Nullable
    public static CharSequence getDescription(View view) {
        CharSequence charSequence = null;
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            CharSequence contentDescription = createNodeInfoFromView.getContentDescription();
            CharSequence text = createNodeInfoFromView.getText();
            Object obj = !TextUtils.isEmpty(text) ? 1 : null;
            boolean z = view instanceof EditText;
            if (!TextUtils.isEmpty(contentDescription) && (!z || obj == null)) {
                createNodeInfoFromView.recycle();
                return contentDescription;
            } else if (obj != null) {
                createNodeInfoFromView.recycle();
                return text;
            } else if (view instanceof ViewGroup) {
                StringBuilder stringBuilder = new StringBuilder();
                String str = ", ";
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
                    ViewCompat.onInitializeAccessibilityNodeInfo(childAt, obtain);
                    if (!AccessibilityUtil.isSpeakingNode(obtain, childAt) || AccessibilityUtil.isAccessibilityFocusable(obtain, childAt)) {
                        contentDescription = null;
                    } else {
                        contentDescription = getDescription(childAt);
                    }
                    if (!TextUtils.isEmpty(contentDescription)) {
                        if (stringBuilder.length() > 0) {
                            stringBuilder.append(", ");
                        }
                        stringBuilder.append(contentDescription);
                    }
                    obtain.recycle();
                }
                if (stringBuilder.length() > 0) {
                    charSequence = stringBuilder.toString();
                }
                createNodeInfoFromView.recycle();
                return charSequence;
            } else {
                createNodeInfoFromView.recycle();
                return null;
            }
        } catch (Throwable th) {
            createNodeInfoFromView.recycle();
        }
    }
}
