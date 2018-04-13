package com.facebook.stetho.inspector.elements.android;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Predicate;
import java.util.ArrayList;
import java.util.List;

final class AndroidDocumentProvider$InspectModeHandler {
    private List<View> mOverlays;
    private final Predicate<View> mViewSelector;
    final /* synthetic */ AndroidDocumentProvider this$0;

    private final class OverlayView extends DocumentHiddenView {
        public OverlayView(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawColor(1090519039);
            super.onDraw(canvas);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            Object parent = getParent();
            int i = x;
            x = y;
            while (true) {
                HighlightableDescriptor highlightableDescriptor = AndroidDocumentProvider$InspectModeHandler.this.this$0.getHighlightableDescriptor(parent);
                if (highlightableDescriptor != null) {
                    AndroidDocumentProvider.access$600(AndroidDocumentProvider$InspectModeHandler.this.this$0).setEmpty();
                    Object elementToHighlightAtPosition = highlightableDescriptor.getElementToHighlightAtPosition(parent, i, x, AndroidDocumentProvider.access$600(AndroidDocumentProvider$InspectModeHandler.this.this$0));
                    i -= AndroidDocumentProvider.access$600(AndroidDocumentProvider$InspectModeHandler.this.this$0).left;
                    x -= AndroidDocumentProvider.access$600(AndroidDocumentProvider$InspectModeHandler.this.this$0).top;
                    if (elementToHighlightAtPosition == parent) {
                        break;
                    }
                    parent = elementToHighlightAtPosition;
                } else {
                    break;
                }
            }
            if (parent != null) {
                highlightableDescriptor = AndroidDocumentProvider$InspectModeHandler.this.this$0.getHighlightableDescriptor(parent);
                if (highlightableDescriptor != null) {
                    View viewAndBoundsForHighlighting = highlightableDescriptor.getViewAndBoundsForHighlighting(parent, AndroidDocumentProvider.access$700(AndroidDocumentProvider$InspectModeHandler.this.this$0));
                    if (!(motionEvent.getAction() == 3 || viewAndBoundsForHighlighting == null)) {
                        AndroidDocumentProvider.access$800(AndroidDocumentProvider$InspectModeHandler.this.this$0).setHighlightedView(viewAndBoundsForHighlighting, AndroidDocumentProvider.access$700(AndroidDocumentProvider$InspectModeHandler.this.this$0), 1077952767);
                        if (motionEvent.getAction() == 1 && AndroidDocumentProvider.access$100(AndroidDocumentProvider$InspectModeHandler.this.this$0) != null) {
                            AndroidDocumentProvider.access$100(AndroidDocumentProvider$InspectModeHandler.this.this$0).onInspectRequested(parent);
                        }
                    }
                }
            }
            return true;
        }
    }

    private AndroidDocumentProvider$InspectModeHandler(AndroidDocumentProvider androidDocumentProvider) {
        this.this$0 = androidDocumentProvider;
        this.mViewSelector = new Predicate<View>() {
            public boolean apply(View view) {
                return !(view instanceof DocumentHiddenView);
            }
        };
    }

    public void enable() {
        this.this$0.verifyThreadAccess();
        if (this.mOverlays != null) {
            disable();
        }
        this.mOverlays = new ArrayList();
        AndroidDocumentProvider.access$500(this.this$0, new Accumulator<Window>() {
            public void store(Window window) {
                if (window.peekDecorView() instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) window.peekDecorView();
                    View overlayView = new OverlayView(AndroidDocumentProvider.access$300(AndroidDocumentProvider$InspectModeHandler.this.this$0));
                    LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = -1;
                    layoutParams.height = -1;
                    viewGroup.addView(overlayView, layoutParams);
                    viewGroup.bringChildToFront(overlayView);
                    AndroidDocumentProvider$InspectModeHandler.this.mOverlays.add(overlayView);
                }
            }
        });
    }

    public void disable() {
        this.this$0.verifyThreadAccess();
        if (this.mOverlays != null) {
            for (int i = 0; i < this.mOverlays.size(); i++) {
                View view = (View) this.mOverlays.get(i);
                ((ViewGroup) view.getParent()).removeView(view);
            }
            this.mOverlays = null;
        }
    }
}
