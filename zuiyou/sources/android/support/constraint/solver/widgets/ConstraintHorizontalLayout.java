package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintAnchor.Strength;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;

public class ConstraintHorizontalLayout extends ConstraintWidgetContainer {
    private ContentAlignment mAlignment = ContentAlignment.MIDDLE;

    public enum ContentAlignment {
        BEGIN,
        MIDDLE,
        END,
        TOP,
        VERTICAL_MIDDLE,
        BOTTOM,
        LEFT,
        RIGHT
    }

    public ConstraintHorizontalLayout(int i, int i2, int i3, int i4) {
        super(i, i2, i3, i4);
    }

    public ConstraintHorizontalLayout(int i, int i2) {
        super(i, i2);
    }

    public void addToSolver(LinearSystem linearSystem, int i) {
        if (this.mChildren.size() != 0) {
            int size = this.mChildren.size();
            int i2 = 0;
            ConstraintWidget constraintWidget = this;
            while (i2 < size) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) this.mChildren.get(i2);
                if (constraintWidget != this) {
                    constraintWidget2.connect(Type.LEFT, constraintWidget, Type.RIGHT);
                    constraintWidget.connect(Type.RIGHT, constraintWidget2, Type.LEFT);
                } else {
                    Strength strength = Strength.STRONG;
                    if (this.mAlignment == ContentAlignment.END) {
                        strength = Strength.WEAK;
                    }
                    constraintWidget2.connect(Type.LEFT, constraintWidget, Type.LEFT, 0, strength);
                }
                constraintWidget2.connect(Type.TOP, (ConstraintWidget) this, Type.TOP);
                constraintWidget2.connect(Type.BOTTOM, (ConstraintWidget) this, Type.BOTTOM);
                i2++;
                constraintWidget = constraintWidget2;
            }
            if (constraintWidget != this) {
                Strength strength2 = Strength.STRONG;
                if (this.mAlignment == ContentAlignment.BEGIN) {
                    strength2 = Strength.WEAK;
                }
                constraintWidget.connect(Type.RIGHT, (ConstraintWidget) this, Type.RIGHT, 0, strength2);
            }
        }
        super.addToSolver(linearSystem, i);
    }
}
