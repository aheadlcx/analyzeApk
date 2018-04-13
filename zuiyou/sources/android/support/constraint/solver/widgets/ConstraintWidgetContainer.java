package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;
import java.util.ArrayList;
import java.util.Arrays;

public class ConstraintWidgetContainer extends WidgetContainer {
    static boolean ALLOW_ROOT_GROUP = true;
    private static final int CHAIN_FIRST = 0;
    private static final int CHAIN_FIRST_VISIBLE = 2;
    private static final int CHAIN_LAST = 1;
    private static final int CHAIN_LAST_VISIBLE = 3;
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final boolean DEBUG_OPTIMIZE = false;
    private static final int FLAG_CHAIN_DANGLING = 1;
    private static final int FLAG_CHAIN_OPTIMIZE = 0;
    private static final int FLAG_RECOMPUTE_BOUNDS = 2;
    private static final int MAX_ITERATIONS = 8;
    public static final int OPTIMIZATION_ALL = 2;
    public static final int OPTIMIZATION_BASIC = 4;
    public static final int OPTIMIZATION_CHAIN = 8;
    public static final int OPTIMIZATION_NONE = 1;
    private static final boolean USE_SNAPSHOT = true;
    private static final boolean USE_THREAD = false;
    private boolean[] flags;
    protected LinearSystem mBackgroundSystem;
    private ConstraintWidget[] mChainEnds;
    private boolean mHeightMeasuredTooSmall;
    private ConstraintWidget[] mHorizontalChainsArray;
    private int mHorizontalChainsSize;
    private ConstraintWidget[] mMatchConstraintsChainedWidgets;
    private int mOptimizationLevel;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    private Snapshot mSnapshot;
    protected LinearSystem mSystem;
    private ConstraintWidget[] mVerticalChainsArray;
    private int mVerticalChainsSize;
    private boolean mWidthMeasuredTooSmall;
    int mWrapHeight;
    int mWrapWidth;

    public ConstraintWidgetContainer() {
        this.mSystem = new LinearSystem();
        this.mBackgroundSystem = null;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 2;
        this.flags = new boolean[3];
        this.mChainEnds = new ConstraintWidget[4];
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
    }

    public ConstraintWidgetContainer(int i, int i2, int i3, int i4) {
        super(i, i2, i3, i4);
        this.mSystem = new LinearSystem();
        this.mBackgroundSystem = null;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 2;
        this.flags = new boolean[3];
        this.mChainEnds = new ConstraintWidget[4];
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
    }

    public ConstraintWidgetContainer(int i, int i2) {
        super(i, i2);
        this.mSystem = new LinearSystem();
        this.mBackgroundSystem = null;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 2;
        this.flags = new boolean[3];
        this.mChainEnds = new ConstraintWidget[4];
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
    }

    public String getType() {
        return "ConstraintLayout";
    }

    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        super.reset();
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public static ConstraintWidgetContainer createContainer(ConstraintWidgetContainer constraintWidgetContainer, String str, ArrayList<ConstraintWidget> arrayList, int i) {
        int i2 = 0;
        Rectangle bounds = WidgetContainer.getBounds(arrayList);
        if (bounds.width == 0 || bounds.height == 0) {
            return null;
        }
        if (i > 0) {
            int min = Math.min(bounds.x, bounds.y);
            if (i > min) {
                i = min;
            }
            bounds.grow(i, i);
        }
        constraintWidgetContainer.setOrigin(bounds.x, bounds.y);
        constraintWidgetContainer.setDimension(bounds.width, bounds.height);
        constraintWidgetContainer.setDebugName(str);
        ConstraintWidget parent = ((ConstraintWidget) arrayList.get(0)).getParent();
        int size = arrayList.size();
        while (i2 < size) {
            ConstraintWidget constraintWidget = (ConstraintWidget) arrayList.get(i2);
            if (constraintWidget.getParent() == parent) {
                constraintWidgetContainer.add(constraintWidget);
                constraintWidget.setX(constraintWidget.getX() - bounds.x);
                constraintWidget.setY(constraintWidget.getY() - bounds.y);
            }
            i2++;
        }
        return constraintWidgetContainer;
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem, int i) {
        boolean z;
        addToSolver(linearSystem, i);
        int size = this.mChildren.size();
        if (this.mOptimizationLevel != 2 && this.mOptimizationLevel != 4) {
            z = true;
        } else if (optimize(linearSystem)) {
            return false;
        } else {
            z = false;
        }
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i2);
            if (constraintWidget instanceof ConstraintWidgetContainer) {
                DimensionBehaviour dimensionBehaviour = constraintWidget.mHorizontalDimensionBehaviour;
                DimensionBehaviour dimensionBehaviour2 = constraintWidget.mVerticalDimensionBehaviour;
                if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
                if (dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
                constraintWidget.addToSolver(linearSystem, i);
                if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            } else {
                if (z) {
                    Optimizer.checkMatchParent(this, linearSystem, constraintWidget);
                }
                constraintWidget.addToSolver(linearSystem, i);
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            applyHorizontalChain(linearSystem);
        }
        if (this.mVerticalChainsSize > 0) {
            applyVerticalChain(linearSystem);
        }
        return true;
    }

    private boolean optimize(LinearSystem linearSystem) {
        int i;
        int i2;
        int size = this.mChildren.size();
        for (i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            constraintWidget.mHorizontalResolution = -1;
            constraintWidget.mVerticalResolution = -1;
            if (constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                constraintWidget.mHorizontalResolution = 1;
                constraintWidget.mVerticalResolution = 1;
            }
        }
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        boolean z = false;
        while (!z) {
            boolean z2;
            int i6 = i3 + 1;
            int i7 = 0;
            i2 = 0;
            i = 0;
            while (i7 < size) {
                constraintWidget = (ConstraintWidget) this.mChildren.get(i7);
                if (constraintWidget.mHorizontalResolution == -1) {
                    if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget.mHorizontalResolution = 1;
                    } else {
                        Optimizer.checkHorizontalSimpleDependency(this, linearSystem, constraintWidget);
                    }
                }
                if (constraintWidget.mVerticalResolution == -1) {
                    if (this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget.mVerticalResolution = 1;
                    } else {
                        Optimizer.checkVerticalSimpleDependency(this, linearSystem, constraintWidget);
                    }
                }
                if (constraintWidget.mVerticalResolution == -1) {
                    i++;
                }
                if (constraintWidget.mHorizontalResolution == -1) {
                    i3 = i2 + 1;
                } else {
                    i3 = i2;
                }
                i7++;
                i2 = i3;
            }
            if (i == 0 && i2 == 0) {
                z2 = true;
            } else if (i5 == i && r8 == i2) {
                z2 = true;
            } else {
                z2 = z;
            }
            i4 = i2;
            i5 = i;
            z = z2;
            i3 = i6;
        }
        int i8 = 0;
        i2 = 0;
        i = 0;
        while (i8 < size) {
            constraintWidget = (ConstraintWidget) this.mChildren.get(i8);
            if (constraintWidget.mHorizontalResolution == 1 || constraintWidget.mHorizontalResolution == -1) {
                i++;
            }
            if (constraintWidget.mVerticalResolution == 1 || constraintWidget.mVerticalResolution == -1) {
                i3 = i2 + 1;
            } else {
                i3 = i2;
            }
            i8++;
            i2 = i3;
        }
        if (i == 0 && i2 == 0) {
            return true;
        }
        return false;
    }

    private void applyHorizontalChain(LinearSystem linearSystem) {
        for (int i = 0; i < this.mHorizontalChainsSize; i++) {
            ConstraintWidget constraintWidget = this.mHorizontalChainsArray[i];
            int countMatchConstraintsChainedWidgets = countMatchConstraintsChainedWidgets(linearSystem, this.mChainEnds, this.mHorizontalChainsArray[i], 0, this.flags);
            ConstraintWidget constraintWidget2 = this.mChainEnds[2];
            if (constraintWidget2 != null) {
                int drawX;
                if (this.flags[1]) {
                    drawX = constraintWidget.getDrawX();
                    while (constraintWidget2 != null) {
                        linearSystem.addEquality(constraintWidget2.mLeft.mSolverVariable, drawX);
                        drawX += (constraintWidget2.mLeft.getMargin() + constraintWidget2.getWidth()) + constraintWidget2.mRight.getMargin();
                        constraintWidget2 = constraintWidget2.mHorizontalNextWidget;
                    }
                } else {
                    Object obj = constraintWidget.mHorizontalChainStyle == 0 ? 1 : null;
                    Object obj2 = constraintWidget.mHorizontalChainStyle == 2 ? 1 : null;
                    Object obj3 = this.mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT ? 1 : null;
                    if ((this.mOptimizationLevel == 2 || this.mOptimizationLevel == 8) && this.flags[0] && constraintWidget.mHorizontalChainFixedPosition && obj2 == null && obj3 == null && constraintWidget.mHorizontalChainStyle == 0) {
                        Optimizer.applyDirectResolutionHorizontalChain(this, linearSystem, countMatchConstraintsChainedWidgets, constraintWidget);
                    } else if (countMatchConstraintsChainedWidgets == 0 || obj2 != null) {
                        ConstraintAnchor constraintAnchor;
                        int margin;
                        int margin2;
                        SolverVariable solverVariable;
                        ConstraintWidget constraintWidget3 = null;
                        obj3 = null;
                        ConstraintWidget constraintWidget4 = null;
                        ConstraintWidget constraintWidget5 = constraintWidget2;
                        while (constraintWidget5 != null) {
                            Object obj4;
                            ConstraintWidget constraintWidget6;
                            ConstraintWidget constraintWidget7;
                            r5 = constraintWidget5.mHorizontalNextWidget;
                            if (r5 == null) {
                                obj4 = 1;
                                constraintWidget6 = this.mChainEnds[1];
                            } else {
                                obj4 = obj3;
                                constraintWidget6 = constraintWidget3;
                            }
                            if (obj2 != null) {
                                int margin3;
                                ConstraintAnchor constraintAnchor2 = constraintWidget5.mLeft;
                                drawX = constraintAnchor2.getMargin();
                                if (constraintWidget4 != null) {
                                    margin3 = drawX + constraintWidget4.mRight.getMargin();
                                } else {
                                    margin3 = drawX;
                                }
                                drawX = 1;
                                if (constraintWidget2 != constraintWidget5) {
                                    drawX = 3;
                                }
                                linearSystem.addGreaterThan(constraintAnchor2.mSolverVariable, constraintAnchor2.mTarget.mSolverVariable, margin3, drawX);
                                if (constraintWidget5.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                                    r4 = constraintWidget5.mRight;
                                    if (constraintWidget5.mMatchConstraintDefaultWidth == 1) {
                                        linearSystem.addEquality(r4.mSolverVariable, constraintAnchor2.mSolverVariable, Math.max(constraintWidget5.mMatchConstraintMinWidth, constraintWidget5.getWidth()), 3);
                                    } else {
                                        linearSystem.addGreaterThan(constraintAnchor2.mSolverVariable, constraintAnchor2.mTarget.mSolverVariable, constraintAnchor2.mMargin, 3);
                                        linearSystem.addLowerThan(r4.mSolverVariable, constraintAnchor2.mSolverVariable, constraintWidget5.mMatchConstraintMinWidth, 3);
                                    }
                                }
                                constraintWidget7 = r5;
                            } else if (obj != null || obj4 == null || constraintWidget4 == null) {
                                if (obj != null || obj4 != null || constraintWidget4 != null) {
                                    ConstraintWidget constraintWidget8;
                                    ConstraintAnchor constraintAnchor3 = constraintWidget5.mLeft;
                                    constraintAnchor = constraintWidget5.mRight;
                                    margin = constraintAnchor3.getMargin();
                                    margin2 = constraintAnchor.getMargin();
                                    linearSystem.addGreaterThan(constraintAnchor3.mSolverVariable, constraintAnchor3.mTarget.mSolverVariable, margin, 1);
                                    linearSystem.addLowerThan(constraintAnchor.mSolverVariable, constraintAnchor.mTarget.mSolverVariable, -margin2, 1);
                                    r6 = constraintAnchor3.mTarget != null ? constraintAnchor3.mTarget.mSolverVariable : null;
                                    if (constraintWidget4 == null) {
                                        r6 = constraintWidget.mLeft.mTarget != null ? constraintWidget.mLeft.mTarget.mSolverVariable : null;
                                    }
                                    if (r5 == null) {
                                        constraintWidget8 = constraintWidget6.mRight.mTarget != null ? constraintWidget6.mRight.mTarget.mOwner : null;
                                    } else {
                                        constraintWidget8 = r5;
                                    }
                                    if (constraintWidget8 != null) {
                                        solverVariable = constraintWidget8.mLeft.mSolverVariable;
                                        if (obj4 != null) {
                                            solverVariable = constraintWidget6.mRight.mTarget != null ? constraintWidget6.mRight.mTarget.mSolverVariable : null;
                                        }
                                        if (!(r6 == null || solverVariable == null)) {
                                            linearSystem.addCentering(constraintAnchor3.mSolverVariable, r6, margin, 0.5f, solverVariable, constraintAnchor.mSolverVariable, margin2, 4);
                                        }
                                    }
                                    constraintWidget7 = constraintWidget8;
                                } else if (constraintWidget5.mLeft.mTarget == null) {
                                    linearSystem.addEquality(constraintWidget5.mLeft.mSolverVariable, constraintWidget5.getDrawX());
                                    constraintWidget7 = r5;
                                } else {
                                    linearSystem.addEquality(constraintWidget5.mLeft.mSolverVariable, constraintWidget.mLeft.mTarget.mSolverVariable, constraintWidget5.mLeft.getMargin(), 5);
                                    constraintWidget7 = r5;
                                }
                            } else if (constraintWidget5.mRight.mTarget == null) {
                                linearSystem.addEquality(constraintWidget5.mRight.mSolverVariable, constraintWidget5.getDrawRight());
                                constraintWidget7 = r5;
                            } else {
                                linearSystem.addEquality(constraintWidget5.mRight.mSolverVariable, constraintWidget6.mRight.mTarget.mSolverVariable, -constraintWidget5.mRight.getMargin(), 5);
                                constraintWidget7 = r5;
                            }
                            if (obj4 != null) {
                                constraintWidget7 = null;
                            }
                            constraintWidget3 = constraintWidget6;
                            constraintWidget4 = constraintWidget5;
                            constraintWidget5 = constraintWidget7;
                            obj3 = obj4;
                        }
                        if (obj2 != null) {
                            r4 = constraintWidget2.mLeft;
                            constraintAnchor = constraintWidget3.mRight;
                            margin = r4.getMargin();
                            margin2 = constraintAnchor.getMargin();
                            r6 = constraintWidget.mLeft.mTarget != null ? constraintWidget.mLeft.mTarget.mSolverVariable : null;
                            solverVariable = constraintWidget3.mRight.mTarget != null ? constraintWidget3.mRight.mTarget.mSolverVariable : null;
                            if (!(r6 == null || solverVariable == null)) {
                                linearSystem.addLowerThan(constraintAnchor.mSolverVariable, solverVariable, -margin2, 1);
                                linearSystem.addCentering(r4.mSolverVariable, r6, margin, constraintWidget.mHorizontalBiasPercent, solverVariable, constraintAnchor.mSolverVariable, margin2, 4);
                            }
                        }
                    } else {
                        int i2;
                        float f = 0.0f;
                        r5 = null;
                        ConstraintWidget constraintWidget9 = constraintWidget2;
                        while (constraintWidget9 != null) {
                            if (constraintWidget9.mHorizontalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
                                drawX = constraintWidget9.mLeft.getMargin();
                                if (r5 != null) {
                                    drawX += r5.mRight.getMargin();
                                }
                                i2 = 3;
                                if (constraintWidget9.mLeft.mTarget.mOwner.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                                    i2 = 2;
                                }
                                linearSystem.addGreaterThan(constraintWidget9.mLeft.mSolverVariable, constraintWidget9.mLeft.mTarget.mSolverVariable, drawX, i2);
                                drawX = constraintWidget9.mRight.getMargin();
                                if (constraintWidget9.mRight.mTarget.mOwner.mLeft.mTarget != null && constraintWidget9.mRight.mTarget.mOwner.mLeft.mTarget.mOwner == constraintWidget9) {
                                    drawX += constraintWidget9.mRight.mTarget.mOwner.mLeft.getMargin();
                                }
                                i2 = 3;
                                if (constraintWidget9.mRight.mTarget.mOwner.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                                    i2 = 2;
                                }
                                linearSystem.addLowerThan(constraintWidget9.mRight.mSolverVariable, constraintWidget9.mRight.mTarget.mSolverVariable, -drawX, i2);
                            } else {
                                f += constraintWidget9.mHorizontalWeight;
                                drawX = 0;
                                if (constraintWidget9.mRight.mTarget != null) {
                                    drawX = constraintWidget9.mRight.getMargin();
                                    if (constraintWidget9 != this.mChainEnds[3]) {
                                        drawX += constraintWidget9.mRight.mTarget.mOwner.mLeft.getMargin();
                                    }
                                }
                                linearSystem.addGreaterThan(constraintWidget9.mRight.mSolverVariable, constraintWidget9.mLeft.mSolverVariable, 0, 1);
                                linearSystem.addLowerThan(constraintWidget9.mRight.mSolverVariable, constraintWidget9.mRight.mTarget.mSolverVariable, -drawX, 1);
                            }
                            r5 = constraintWidget9;
                            constraintWidget9 = constraintWidget9.mHorizontalNextWidget;
                        }
                        if (countMatchConstraintsChainedWidgets == 1) {
                            constraintWidget9 = this.mMatchConstraintsChainedWidgets[0];
                            drawX = constraintWidget9.mLeft.getMargin();
                            if (constraintWidget9.mLeft.mTarget != null) {
                                drawX += constraintWidget9.mLeft.mTarget.getMargin();
                            }
                            i2 = constraintWidget9.mRight.getMargin();
                            if (constraintWidget9.mRight.mTarget != null) {
                                i2 += constraintWidget9.mRight.mTarget.getMargin();
                            }
                            r6 = constraintWidget.mRight.mTarget.mSolverVariable;
                            if (constraintWidget9 == this.mChainEnds[3]) {
                                r6 = this.mChainEnds[1].mRight.mTarget.mSolverVariable;
                            }
                            if (constraintWidget9.mMatchConstraintDefaultWidth == 1) {
                                linearSystem.addGreaterThan(constraintWidget.mLeft.mSolverVariable, constraintWidget.mLeft.mTarget.mSolverVariable, drawX, 1);
                                linearSystem.addLowerThan(constraintWidget.mRight.mSolverVariable, r6, -i2, 1);
                                linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, constraintWidget.mLeft.mSolverVariable, constraintWidget.getWidth(), 2);
                            } else {
                                linearSystem.addEquality(constraintWidget9.mLeft.mSolverVariable, constraintWidget9.mLeft.mTarget.mSolverVariable, drawX, 1);
                                linearSystem.addEquality(constraintWidget9.mRight.mSolverVariable, r6, -i2, 1);
                            }
                        } else {
                            for (int i3 = 0; i3 < countMatchConstraintsChainedWidgets - 1; i3++) {
                                ConstraintWidget constraintWidget10 = this.mMatchConstraintsChainedWidgets[i3];
                                constraintWidget2 = this.mMatchConstraintsChainedWidgets[i3 + 1];
                                SolverVariable solverVariable2 = constraintWidget10.mLeft.mSolverVariable;
                                SolverVariable solverVariable3 = constraintWidget10.mRight.mSolverVariable;
                                SolverVariable solverVariable4 = constraintWidget2.mLeft.mSolverVariable;
                                SolverVariable solverVariable5 = constraintWidget2.mRight.mSolverVariable;
                                if (constraintWidget2 == this.mChainEnds[3]) {
                                    solverVariable5 = this.mChainEnds[1].mRight.mSolverVariable;
                                }
                                drawX = constraintWidget10.mLeft.getMargin();
                                if (!(constraintWidget10.mLeft.mTarget == null || constraintWidget10.mLeft.mTarget.mOwner.mRight.mTarget == null || constraintWidget10.mLeft.mTarget.mOwner.mRight.mTarget.mOwner != constraintWidget10)) {
                                    drawX += constraintWidget10.mLeft.mTarget.mOwner.mRight.getMargin();
                                }
                                linearSystem.addGreaterThan(solverVariable2, constraintWidget10.mLeft.mTarget.mSolverVariable, drawX, 2);
                                i2 = constraintWidget10.mRight.getMargin();
                                if (constraintWidget10.mRight.mTarget == null || constraintWidget10.mHorizontalNextWidget == null) {
                                    drawX = i2;
                                } else {
                                    drawX = (constraintWidget10.mHorizontalNextWidget.mLeft.mTarget != null ? constraintWidget10.mHorizontalNextWidget.mLeft.getMargin() : 0) + i2;
                                }
                                linearSystem.addLowerThan(solverVariable3, constraintWidget10.mRight.mTarget.mSolverVariable, -drawX, 2);
                                if (i3 + 1 == countMatchConstraintsChainedWidgets - 1) {
                                    drawX = constraintWidget2.mLeft.getMargin();
                                    if (!(constraintWidget2.mLeft.mTarget == null || constraintWidget2.mLeft.mTarget.mOwner.mRight.mTarget == null || constraintWidget2.mLeft.mTarget.mOwner.mRight.mTarget.mOwner != constraintWidget2)) {
                                        drawX += constraintWidget2.mLeft.mTarget.mOwner.mRight.getMargin();
                                    }
                                    linearSystem.addGreaterThan(solverVariable4, constraintWidget2.mLeft.mTarget.mSolverVariable, drawX, 2);
                                    r4 = constraintWidget2.mRight;
                                    if (constraintWidget2 == this.mChainEnds[3]) {
                                        r4 = this.mChainEnds[1].mRight;
                                    }
                                    i2 = r4.getMargin();
                                    if (!(r4.mTarget == null || r4.mTarget.mOwner.mLeft.mTarget == null || r4.mTarget.mOwner.mLeft.mTarget.mOwner != constraintWidget2)) {
                                        i2 += r4.mTarget.mOwner.mLeft.getMargin();
                                    }
                                    linearSystem.addLowerThan(solverVariable5, r4.mTarget.mSolverVariable, -i2, 2);
                                }
                                if (constraintWidget.mMatchConstraintMaxWidth > 0) {
                                    linearSystem.addLowerThan(solverVariable3, solverVariable2, constraintWidget.mMatchConstraintMaxWidth, 2);
                                }
                                ArrayRow createRow = linearSystem.createRow();
                                createRow.createRowEqualDimension(constraintWidget10.mHorizontalWeight, f, constraintWidget2.mHorizontalWeight, solverVariable2, constraintWidget10.mLeft.getMargin(), solverVariable3, constraintWidget10.mRight.getMargin(), solverVariable4, constraintWidget2.mLeft.getMargin(), solverVariable5, constraintWidget2.mRight.getMargin());
                                linearSystem.addConstraint(createRow);
                            }
                        }
                    }
                }
            }
        }
    }

    private void applyVerticalChain(LinearSystem linearSystem) {
        for (int i = 0; i < this.mVerticalChainsSize; i++) {
            ConstraintWidget constraintWidget = this.mVerticalChainsArray[i];
            int countMatchConstraintsChainedWidgets = countMatchConstraintsChainedWidgets(linearSystem, this.mChainEnds, this.mVerticalChainsArray[i], 1, this.flags);
            ConstraintWidget constraintWidget2 = this.mChainEnds[2];
            if (constraintWidget2 != null) {
                int drawY;
                if (this.flags[1]) {
                    drawY = constraintWidget.getDrawY();
                    while (constraintWidget2 != null) {
                        linearSystem.addEquality(constraintWidget2.mTop.mSolverVariable, drawY);
                        drawY += (constraintWidget2.mTop.getMargin() + constraintWidget2.getHeight()) + constraintWidget2.mBottom.getMargin();
                        constraintWidget2 = constraintWidget2.mVerticalNextWidget;
                    }
                } else {
                    Object obj = constraintWidget.mVerticalChainStyle == 0 ? 1 : null;
                    Object obj2 = constraintWidget.mVerticalChainStyle == 2 ? 1 : null;
                    Object obj3 = this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT ? 1 : null;
                    if ((this.mOptimizationLevel == 2 || this.mOptimizationLevel == 8) && this.flags[0] && constraintWidget.mVerticalChainFixedPosition && obj2 == null && obj3 == null && constraintWidget.mVerticalChainStyle == 0) {
                        Optimizer.applyDirectResolutionVerticalChain(this, linearSystem, countMatchConstraintsChainedWidgets, constraintWidget);
                    } else if (countMatchConstraintsChainedWidgets == 0 || obj2 != null) {
                        ConstraintAnchor constraintAnchor;
                        int margin;
                        int margin2;
                        SolverVariable solverVariable;
                        ConstraintWidget constraintWidget3 = null;
                        obj3 = null;
                        ConstraintWidget constraintWidget4 = null;
                        ConstraintWidget constraintWidget5 = constraintWidget2;
                        while (constraintWidget5 != null) {
                            Object obj4;
                            ConstraintWidget constraintWidget6;
                            ConstraintWidget constraintWidget7;
                            r5 = constraintWidget5.mVerticalNextWidget;
                            if (r5 == null) {
                                obj4 = 1;
                                constraintWidget6 = this.mChainEnds[1];
                            } else {
                                obj4 = obj3;
                                constraintWidget6 = constraintWidget3;
                            }
                            if (obj2 != null) {
                                constraintAnchor = constraintWidget5.mTop;
                                int margin3 = constraintAnchor.getMargin();
                                if (constraintWidget4 != null) {
                                    margin3 += constraintWidget4.mBottom.getMargin();
                                }
                                drawY = 1;
                                if (constraintWidget2 != constraintWidget5) {
                                    drawY = 3;
                                }
                                SolverVariable solverVariable2 = null;
                                r6 = null;
                                if (constraintAnchor.mTarget != null) {
                                    solverVariable2 = constraintAnchor.mSolverVariable;
                                    r6 = constraintAnchor.mTarget.mSolverVariable;
                                } else if (constraintWidget5.mBaseline.mTarget != null) {
                                    solverVariable2 = constraintWidget5.mBaseline.mSolverVariable;
                                    r6 = constraintWidget5.mBaseline.mTarget.mSolverVariable;
                                    margin3 -= constraintAnchor.getMargin();
                                }
                                if (!(solverVariable2 == null || r6 == null)) {
                                    linearSystem.addGreaterThan(solverVariable2, r6, margin3, drawY);
                                }
                                if (constraintWidget5.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                                    r4 = constraintWidget5.mBottom;
                                    if (constraintWidget5.mMatchConstraintDefaultHeight == 1) {
                                        linearSystem.addEquality(r4.mSolverVariable, constraintAnchor.mSolverVariable, Math.max(constraintWidget5.mMatchConstraintMinHeight, constraintWidget5.getHeight()), 3);
                                    } else {
                                        linearSystem.addGreaterThan(constraintAnchor.mSolverVariable, constraintAnchor.mTarget.mSolverVariable, constraintAnchor.mMargin, 3);
                                        linearSystem.addLowerThan(r4.mSolverVariable, constraintAnchor.mSolverVariable, constraintWidget5.mMatchConstraintMinHeight, 3);
                                    }
                                }
                                constraintWidget7 = r5;
                            } else if (obj != null || obj4 == null || constraintWidget4 == null) {
                                if (obj != null || obj4 != null || constraintWidget4 != null) {
                                    ConstraintWidget constraintWidget8;
                                    ConstraintAnchor constraintAnchor2 = constraintWidget5.mTop;
                                    constraintAnchor = constraintWidget5.mBottom;
                                    margin = constraintAnchor2.getMargin();
                                    margin2 = constraintAnchor.getMargin();
                                    linearSystem.addGreaterThan(constraintAnchor2.mSolverVariable, constraintAnchor2.mTarget.mSolverVariable, margin, 1);
                                    linearSystem.addLowerThan(constraintAnchor.mSolverVariable, constraintAnchor.mTarget.mSolverVariable, -margin2, 1);
                                    r6 = constraintAnchor2.mTarget != null ? constraintAnchor2.mTarget.mSolverVariable : null;
                                    if (constraintWidget4 == null) {
                                        r6 = constraintWidget.mTop.mTarget != null ? constraintWidget.mTop.mTarget.mSolverVariable : null;
                                    }
                                    if (r5 == null) {
                                        constraintWidget8 = constraintWidget6.mBottom.mTarget != null ? constraintWidget6.mBottom.mTarget.mOwner : null;
                                    } else {
                                        constraintWidget8 = r5;
                                    }
                                    if (constraintWidget8 != null) {
                                        solverVariable = constraintWidget8.mTop.mSolverVariable;
                                        if (obj4 != null) {
                                            solverVariable = constraintWidget6.mBottom.mTarget != null ? constraintWidget6.mBottom.mTarget.mSolverVariable : null;
                                        }
                                        if (!(r6 == null || solverVariable == null)) {
                                            linearSystem.addCentering(constraintAnchor2.mSolverVariable, r6, margin, 0.5f, solverVariable, constraintAnchor.mSolverVariable, margin2, 4);
                                        }
                                    }
                                    constraintWidget7 = constraintWidget8;
                                } else if (constraintWidget5.mTop.mTarget == null) {
                                    linearSystem.addEquality(constraintWidget5.mTop.mSolverVariable, constraintWidget5.getDrawY());
                                    constraintWidget7 = r5;
                                } else {
                                    linearSystem.addEquality(constraintWidget5.mTop.mSolverVariable, constraintWidget.mTop.mTarget.mSolverVariable, constraintWidget5.mTop.getMargin(), 5);
                                    constraintWidget7 = r5;
                                }
                            } else if (constraintWidget5.mBottom.mTarget == null) {
                                linearSystem.addEquality(constraintWidget5.mBottom.mSolverVariable, constraintWidget5.getDrawBottom());
                                constraintWidget7 = r5;
                            } else {
                                linearSystem.addEquality(constraintWidget5.mBottom.mSolverVariable, constraintWidget6.mBottom.mTarget.mSolverVariable, -constraintWidget5.mBottom.getMargin(), 5);
                                constraintWidget7 = r5;
                            }
                            if (obj4 != null) {
                                constraintWidget7 = null;
                            }
                            constraintWidget3 = constraintWidget6;
                            constraintWidget4 = constraintWidget5;
                            constraintWidget5 = constraintWidget7;
                            obj3 = obj4;
                        }
                        if (obj2 != null) {
                            r4 = constraintWidget2.mTop;
                            constraintAnchor = constraintWidget3.mBottom;
                            margin = r4.getMargin();
                            margin2 = constraintAnchor.getMargin();
                            r6 = constraintWidget.mTop.mTarget != null ? constraintWidget.mTop.mTarget.mSolverVariable : null;
                            solverVariable = constraintWidget3.mBottom.mTarget != null ? constraintWidget3.mBottom.mTarget.mSolverVariable : null;
                            if (!(r6 == null || solverVariable == null)) {
                                linearSystem.addLowerThan(constraintAnchor.mSolverVariable, solverVariable, -margin2, 1);
                                linearSystem.addCentering(r4.mSolverVariable, r6, margin, constraintWidget.mVerticalBiasPercent, solverVariable, constraintAnchor.mSolverVariable, margin2, 4);
                            }
                        }
                    } else {
                        int i2;
                        float f = 0.0f;
                        r5 = null;
                        ConstraintWidget constraintWidget9 = constraintWidget2;
                        while (constraintWidget9 != null) {
                            if (constraintWidget9.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
                                drawY = constraintWidget9.mTop.getMargin();
                                if (r5 != null) {
                                    drawY += r5.mBottom.getMargin();
                                }
                                i2 = 3;
                                if (constraintWidget9.mTop.mTarget.mOwner.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                                    i2 = 2;
                                }
                                linearSystem.addGreaterThan(constraintWidget9.mTop.mSolverVariable, constraintWidget9.mTop.mTarget.mSolverVariable, drawY, i2);
                                drawY = constraintWidget9.mBottom.getMargin();
                                if (constraintWidget9.mBottom.mTarget.mOwner.mTop.mTarget != null && constraintWidget9.mBottom.mTarget.mOwner.mTop.mTarget.mOwner == constraintWidget9) {
                                    drawY += constraintWidget9.mBottom.mTarget.mOwner.mTop.getMargin();
                                }
                                i2 = 3;
                                if (constraintWidget9.mBottom.mTarget.mOwner.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                                    i2 = 2;
                                }
                                linearSystem.addLowerThan(constraintWidget9.mBottom.mSolverVariable, constraintWidget9.mBottom.mTarget.mSolverVariable, -drawY, i2);
                            } else {
                                f += constraintWidget9.mVerticalWeight;
                                drawY = 0;
                                if (constraintWidget9.mBottom.mTarget != null) {
                                    drawY = constraintWidget9.mBottom.getMargin();
                                    if (constraintWidget9 != this.mChainEnds[3]) {
                                        drawY += constraintWidget9.mBottom.mTarget.mOwner.mTop.getMargin();
                                    }
                                }
                                linearSystem.addGreaterThan(constraintWidget9.mBottom.mSolverVariable, constraintWidget9.mTop.mSolverVariable, 0, 1);
                                linearSystem.addLowerThan(constraintWidget9.mBottom.mSolverVariable, constraintWidget9.mBottom.mTarget.mSolverVariable, -drawY, 1);
                            }
                            r5 = constraintWidget9;
                            constraintWidget9 = constraintWidget9.mVerticalNextWidget;
                        }
                        if (countMatchConstraintsChainedWidgets == 1) {
                            constraintWidget9 = this.mMatchConstraintsChainedWidgets[0];
                            drawY = constraintWidget9.mTop.getMargin();
                            if (constraintWidget9.mTop.mTarget != null) {
                                drawY += constraintWidget9.mTop.mTarget.getMargin();
                            }
                            i2 = constraintWidget9.mBottom.getMargin();
                            if (constraintWidget9.mBottom.mTarget != null) {
                                i2 += constraintWidget9.mBottom.mTarget.getMargin();
                            }
                            r6 = constraintWidget.mBottom.mTarget.mSolverVariable;
                            if (constraintWidget9 == this.mChainEnds[3]) {
                                r6 = this.mChainEnds[1].mBottom.mTarget.mSolverVariable;
                            }
                            if (constraintWidget9.mMatchConstraintDefaultHeight == 1) {
                                linearSystem.addGreaterThan(constraintWidget.mTop.mSolverVariable, constraintWidget.mTop.mTarget.mSolverVariable, drawY, 1);
                                linearSystem.addLowerThan(constraintWidget.mBottom.mSolverVariable, r6, -i2, 1);
                                linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, constraintWidget.mTop.mSolverVariable, constraintWidget.getHeight(), 2);
                            } else {
                                linearSystem.addEquality(constraintWidget9.mTop.mSolverVariable, constraintWidget9.mTop.mTarget.mSolverVariable, drawY, 1);
                                linearSystem.addEquality(constraintWidget9.mBottom.mSolverVariable, r6, -i2, 1);
                            }
                        } else {
                            for (int i3 = 0; i3 < countMatchConstraintsChainedWidgets - 1; i3++) {
                                ConstraintWidget constraintWidget10 = this.mMatchConstraintsChainedWidgets[i3];
                                constraintWidget2 = this.mMatchConstraintsChainedWidgets[i3 + 1];
                                SolverVariable solverVariable3 = constraintWidget10.mTop.mSolverVariable;
                                SolverVariable solverVariable4 = constraintWidget10.mBottom.mSolverVariable;
                                SolverVariable solverVariable5 = constraintWidget2.mTop.mSolverVariable;
                                SolverVariable solverVariable6 = constraintWidget2.mBottom.mSolverVariable;
                                if (constraintWidget2 == this.mChainEnds[3]) {
                                    solverVariable6 = this.mChainEnds[1].mBottom.mSolverVariable;
                                }
                                drawY = constraintWidget10.mTop.getMargin();
                                if (!(constraintWidget10.mTop.mTarget == null || constraintWidget10.mTop.mTarget.mOwner.mBottom.mTarget == null || constraintWidget10.mTop.mTarget.mOwner.mBottom.mTarget.mOwner != constraintWidget10)) {
                                    drawY += constraintWidget10.mTop.mTarget.mOwner.mBottom.getMargin();
                                }
                                linearSystem.addGreaterThan(solverVariable3, constraintWidget10.mTop.mTarget.mSolverVariable, drawY, 2);
                                i2 = constraintWidget10.mBottom.getMargin();
                                if (constraintWidget10.mBottom.mTarget == null || constraintWidget10.mVerticalNextWidget == null) {
                                    drawY = i2;
                                } else {
                                    drawY = (constraintWidget10.mVerticalNextWidget.mTop.mTarget != null ? constraintWidget10.mVerticalNextWidget.mTop.getMargin() : 0) + i2;
                                }
                                linearSystem.addLowerThan(solverVariable4, constraintWidget10.mBottom.mTarget.mSolverVariable, -drawY, 2);
                                if (i3 + 1 == countMatchConstraintsChainedWidgets - 1) {
                                    drawY = constraintWidget2.mTop.getMargin();
                                    if (!(constraintWidget2.mTop.mTarget == null || constraintWidget2.mTop.mTarget.mOwner.mBottom.mTarget == null || constraintWidget2.mTop.mTarget.mOwner.mBottom.mTarget.mOwner != constraintWidget2)) {
                                        drawY += constraintWidget2.mTop.mTarget.mOwner.mBottom.getMargin();
                                    }
                                    linearSystem.addGreaterThan(solverVariable5, constraintWidget2.mTop.mTarget.mSolverVariable, drawY, 2);
                                    r4 = constraintWidget2.mBottom;
                                    if (constraintWidget2 == this.mChainEnds[3]) {
                                        r4 = this.mChainEnds[1].mBottom;
                                    }
                                    i2 = r4.getMargin();
                                    if (!(r4.mTarget == null || r4.mTarget.mOwner.mTop.mTarget == null || r4.mTarget.mOwner.mTop.mTarget.mOwner != constraintWidget2)) {
                                        i2 += r4.mTarget.mOwner.mTop.getMargin();
                                    }
                                    linearSystem.addLowerThan(solverVariable6, r4.mTarget.mSolverVariable, -i2, 2);
                                }
                                if (constraintWidget.mMatchConstraintMaxHeight > 0) {
                                    linearSystem.addLowerThan(solverVariable4, solverVariable3, constraintWidget.mMatchConstraintMaxHeight, 2);
                                }
                                ArrayRow createRow = linearSystem.createRow();
                                createRow.createRowEqualDimension(constraintWidget10.mVerticalWeight, f, constraintWidget2.mVerticalWeight, solverVariable3, constraintWidget10.mTop.getMargin(), solverVariable4, constraintWidget10.mBottom.getMargin(), solverVariable5, constraintWidget2.mTop.getMargin(), solverVariable6, constraintWidget2.mBottom.getMargin());
                                linearSystem.addConstraint(createRow);
                            }
                        }
                    }
                }
            }
        }
    }

    public void updateChildrenFromSolver(LinearSystem linearSystem, int i, boolean[] zArr) {
        zArr[2] = false;
        updateFromSolver(linearSystem, i);
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i2);
            constraintWidget.updateFromSolver(linearSystem, i);
            if (constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getWidth() < constraintWidget.getWrapWidth()) {
                zArr[2] = true;
            }
            if (constraintWidget.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getHeight() < constraintWidget.getWrapHeight()) {
                zArr[2] = true;
            }
        }
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingTop = i2;
        this.mPaddingRight = i3;
        this.mPaddingBottom = i4;
    }

    public void layout() {
        boolean z;
        int size;
        int i;
        ConstraintWidget constraintWidget;
        int i2;
        boolean z2;
        int max;
        int i3 = this.mX;
        int i4 = this.mY;
        int max2 = Math.max(0, getWidth());
        int max3 = Math.max(0, getHeight());
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        if (this.mParent != null) {
            if (this.mSnapshot == null) {
                this.mSnapshot = new Snapshot(this);
            }
            this.mSnapshot.updateFrom(this);
            setX(this.mPaddingLeft);
            setY(this.mPaddingTop);
            resetAnchors();
            resetSolverVariables(this.mSystem.getCache());
        } else {
            this.mX = 0;
            this.mY = 0;
        }
        boolean z3 = false;
        DimensionBehaviour dimensionBehaviour = this.mVerticalDimensionBehaviour;
        DimensionBehaviour dimensionBehaviour2 = this.mHorizontalDimensionBehaviour;
        if (this.mOptimizationLevel == 2 && (this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT || this.mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT)) {
            findWrapSize(this.mChildren, this.flags);
            z3 = this.flags[0];
            if (max2 > 0 && max3 > 0 && (this.mWrapWidth > max2 || this.mWrapHeight > max3)) {
                z3 = false;
            }
            if (z3) {
                if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                    this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
                    if (max2 <= 0 || max2 >= this.mWrapWidth) {
                        setWidth(Math.max(this.mMinWidth, this.mWrapWidth));
                    } else {
                        this.mWidthMeasuredTooSmall = true;
                        setWidth(max2);
                    }
                }
                if (this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                    this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
                    if (max3 <= 0 || max3 >= this.mWrapHeight) {
                        setHeight(Math.max(this.mMinHeight, this.mWrapHeight));
                    } else {
                        this.mHeightMeasuredTooSmall = true;
                        setHeight(max3);
                        z = z3;
                        resetChains();
                        size = this.mChildren.size();
                        for (i = 0; i < size; i++) {
                            constraintWidget = (ConstraintWidget) this.mChildren.get(i);
                            if (constraintWidget instanceof WidgetContainer) {
                                ((WidgetContainer) constraintWidget).layout();
                            }
                        }
                        i2 = 0;
                        z2 = z;
                        z = true;
                        while (z) {
                            int i5;
                            i5 = i2 + 1;
                            try {
                                this.mSystem.reset();
                                z = addChildrenToSolver(this.mSystem, Integer.MAX_VALUE);
                                if (z) {
                                    this.mSystem.minimize();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (z) {
                                updateFromSolver(this.mSystem, Integer.MAX_VALUE);
                                while (max < size) {
                                    constraintWidget = (ConstraintWidget) this.mChildren.get(max);
                                    if (constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.getWidth() >= constraintWidget.getWrapWidth()) {
                                        if (constraintWidget.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getHeight() < constraintWidget.getWrapHeight()) {
                                            this.flags[2] = true;
                                            break;
                                        }
                                    } else {
                                        this.flags[2] = true;
                                        break;
                                    }
                                }
                            }
                            updateChildrenFromSolver(this.mSystem, Integer.MAX_VALUE, this.flags);
                            if (i5 < 8 || !this.flags[2]) {
                                z3 = false;
                                z = z2;
                            } else {
                                int i6;
                                int i7 = 0;
                                int i8 = 0;
                                for (i6 = 0; i6 < size; i6++) {
                                    constraintWidget = (ConstraintWidget) this.mChildren.get(i6);
                                    i7 = Math.max(i7, constraintWidget.mX + constraintWidget.getWidth());
                                    i8 = Math.max(i8, constraintWidget.getHeight() + constraintWidget.mY);
                                }
                                i2 = Math.max(this.mMinWidth, i7);
                                i6 = Math.max(this.mMinHeight, i8);
                                if (dimensionBehaviour2 != DimensionBehaviour.WRAP_CONTENT || getWidth() >= i2) {
                                    z3 = false;
                                    z = z2;
                                } else {
                                    setWidth(i2);
                                    this.mHorizontalDimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
                                    z = true;
                                    z3 = true;
                                }
                                if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT && getHeight() < i6) {
                                    setHeight(i6);
                                    this.mVerticalDimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
                                    z = true;
                                    z3 = true;
                                }
                            }
                            i = Math.max(this.mMinWidth, getWidth());
                            if (i > getWidth()) {
                                setWidth(i);
                                this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
                                z = true;
                                z3 = true;
                            }
                            i = Math.max(this.mMinHeight, getHeight());
                            if (i > getHeight()) {
                                setHeight(i);
                                this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
                                z = true;
                                z3 = true;
                            }
                            if (z) {
                                if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT && max2 > 0 && getWidth() > max2) {
                                    this.mWidthMeasuredTooSmall = true;
                                    z = true;
                                    this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
                                    setWidth(max2);
                                    z3 = true;
                                }
                                if (this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT && max3 > 0 && getHeight() > max3) {
                                    this.mHeightMeasuredTooSmall = true;
                                    z = true;
                                    this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
                                    setHeight(max3);
                                    z3 = true;
                                }
                            }
                            z2 = z;
                            z = z3;
                            i2 = i5;
                        }
                        if (this.mParent == null) {
                            i2 = Math.max(this.mMinWidth, getWidth());
                            max = Math.max(this.mMinHeight, getHeight());
                            this.mSnapshot.applyTo(this);
                            setWidth((i2 + this.mPaddingLeft) + this.mPaddingRight);
                            setHeight((this.mPaddingTop + max) + this.mPaddingBottom);
                        } else {
                            this.mX = i3;
                            this.mY = i4;
                        }
                        if (z2) {
                            this.mHorizontalDimensionBehaviour = dimensionBehaviour2;
                            this.mVerticalDimensionBehaviour = dimensionBehaviour;
                        }
                        resetSolverVariables(this.mSystem.getCache());
                        if (this == getRootConstraintContainer()) {
                            updateDrawPosition();
                        }
                    }
                }
            }
        }
        z = z3;
        resetChains();
        size = this.mChildren.size();
        for (i = 0; i < size; i++) {
            constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof WidgetContainer) {
                ((WidgetContainer) constraintWidget).layout();
            }
        }
        i2 = 0;
        z2 = z;
        z = true;
        while (z) {
            i5 = i2 + 1;
            this.mSystem.reset();
            z = addChildrenToSolver(this.mSystem, Integer.MAX_VALUE);
            if (z) {
                this.mSystem.minimize();
            }
            if (z) {
                updateFromSolver(this.mSystem, Integer.MAX_VALUE);
                for (max = 0; max < size; max++) {
                    constraintWidget = (ConstraintWidget) this.mChildren.get(max);
                    if (constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                    }
                    if (constraintWidget.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
                    }
                }
            } else {
                updateChildrenFromSolver(this.mSystem, Integer.MAX_VALUE, this.flags);
            }
            if (i5 < 8) {
            }
            z3 = false;
            z = z2;
            i = Math.max(this.mMinWidth, getWidth());
            if (i > getWidth()) {
                setWidth(i);
                this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
                z = true;
                z3 = true;
            }
            i = Math.max(this.mMinHeight, getHeight());
            if (i > getHeight()) {
                setHeight(i);
                this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
                z = true;
                z3 = true;
            }
            if (z) {
                this.mWidthMeasuredTooSmall = true;
                z = true;
                this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
                setWidth(max2);
                z3 = true;
                this.mHeightMeasuredTooSmall = true;
                z = true;
                this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
                setHeight(max3);
                z3 = true;
            }
            z2 = z;
            z = z3;
            i2 = i5;
        }
        if (this.mParent == null) {
            this.mX = i3;
            this.mY = i4;
        } else {
            i2 = Math.max(this.mMinWidth, getWidth());
            max = Math.max(this.mMinHeight, getHeight());
            this.mSnapshot.applyTo(this);
            setWidth((i2 + this.mPaddingLeft) + this.mPaddingRight);
            setHeight((this.mPaddingTop + max) + this.mPaddingBottom);
        }
        if (z2) {
            this.mHorizontalDimensionBehaviour = dimensionBehaviour2;
            this.mVerticalDimensionBehaviour = dimensionBehaviour;
        }
        resetSolverVariables(this.mSystem.getCache());
        if (this == getRootConstraintContainer()) {
            updateDrawPosition();
        }
    }

    static int setGroup(ConstraintAnchor constraintAnchor, int i) {
        int i2 = constraintAnchor.mGroup;
        if (constraintAnchor.mOwner.getParent() == null) {
            return i;
        }
        if (i2 <= i) {
            return i2;
        }
        constraintAnchor.mGroup = i;
        ConstraintAnchor opposite = constraintAnchor.getOpposite();
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        i2 = opposite != null ? setGroup(opposite, i) : i;
        if (constraintAnchor2 != null) {
            i2 = setGroup(constraintAnchor2, i2);
        }
        if (opposite != null) {
            i2 = setGroup(opposite, i2);
        }
        constraintAnchor.mGroup = i2;
        return i2;
    }

    public int layoutFindGroupsSimple() {
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            constraintWidget.mLeft.mGroup = 0;
            constraintWidget.mRight.mGroup = 0;
            constraintWidget.mTop.mGroup = 1;
            constraintWidget.mBottom.mGroup = 1;
            constraintWidget.mBaseline.mGroup = 1;
        }
        return 2;
    }

    public void findHorizontalWrapRecursive(ConstraintWidget constraintWidget, boolean[] zArr) {
        ConstraintWidget constraintWidget2 = null;
        boolean z = false;
        if (constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mDimensionRatio > 0.0f) {
            zArr[0] = false;
            return;
        }
        boolean optimizerWrapWidth = constraintWidget.getOptimizerWrapWidth();
        if (constraintWidget.mHorizontalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mDimensionRatio <= 0.0f) {
            int i;
            int i2;
            constraintWidget.mHorizontalWrapVisited = true;
            if (constraintWidget instanceof Guideline) {
                int i3;
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() != 1) {
                    i3 = optimizerWrapWidth;
                    z = optimizerWrapWidth;
                } else if (guideline.getRelativeBegin() != -1) {
                    i3 = guideline.getRelativeBegin();
                } else if (guideline.getRelativeEnd() != -1) {
                    optimizerWrapWidth = guideline.getRelativeEnd();
                    i3 = 0;
                    z = optimizerWrapWidth;
                } else {
                    i3 = 0;
                }
                i = i3;
                i2 = z;
            } else if (!constraintWidget.mRight.isConnected() && !constraintWidget.mLeft.isConnected()) {
                boolean z2 = optimizerWrapWidth;
                i = constraintWidget.getX() + optimizerWrapWidth;
            } else if (constraintWidget.mRight.mTarget == null || constraintWidget.mLeft.mTarget == null || (constraintWidget.mRight.mTarget != constraintWidget.mLeft.mTarget && (constraintWidget.mRight.mTarget.mOwner != constraintWidget.mLeft.mTarget.mOwner || constraintWidget.mRight.mTarget.mOwner == constraintWidget.mParent))) {
                ConstraintWidget constraintWidget3;
                if (constraintWidget.mRight.mTarget != null) {
                    constraintWidget3 = constraintWidget.mRight.mTarget.mOwner;
                    i2 = constraintWidget.mRight.getMargin() + optimizerWrapWidth;
                    if (!(constraintWidget3.isRoot() || constraintWidget3.mHorizontalWrapVisited)) {
                        findHorizontalWrapRecursive(constraintWidget3, zArr);
                    }
                } else {
                    constraintWidget3 = null;
                    i2 = optimizerWrapWidth;
                }
                if (constraintWidget.mLeft.mTarget != null) {
                    constraintWidget2 = constraintWidget.mLeft.mTarget.mOwner;
                    i = optimizerWrapWidth + constraintWidget.mLeft.getMargin();
                    if (!(constraintWidget2.isRoot() || constraintWidget2.mHorizontalWrapVisited)) {
                        findHorizontalWrapRecursive(constraintWidget2, zArr);
                    }
                }
                if (!(constraintWidget.mRight.mTarget == null || constraintWidget3.isRoot())) {
                    boolean z3;
                    if (constraintWidget.mRight.mTarget.mType == Type.RIGHT) {
                        i2 += constraintWidget3.mDistToRight - constraintWidget3.getOptimizerWrapWidth();
                    } else if (constraintWidget.mRight.mTarget.getType() == Type.LEFT) {
                        i2 += constraintWidget3.mDistToRight;
                    }
                    if (constraintWidget3.mRightHasCentered || !(constraintWidget3.mLeft.mTarget == null || constraintWidget3.mRight.mTarget == null || constraintWidget3.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT)) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    constraintWidget.mRightHasCentered = z3;
                    if (constraintWidget.mRightHasCentered) {
                        if (constraintWidget3.mLeft.mTarget != null) {
                            if (constraintWidget3.mLeft.mTarget.mOwner != constraintWidget) {
                            }
                        }
                        i2 += i2 - constraintWidget3.mDistToRight;
                    }
                }
                if (!(constraintWidget.mLeft.mTarget == null || constraintWidget2.isRoot())) {
                    if (constraintWidget.mLeft.mTarget.getType() == Type.LEFT) {
                        i += constraintWidget2.mDistToLeft - constraintWidget2.getOptimizerWrapWidth();
                    } else if (constraintWidget.mLeft.mTarget.getType() == Type.RIGHT) {
                        i += constraintWidget2.mDistToLeft;
                    }
                    if (constraintWidget2.mLeftHasCentered || !(constraintWidget2.mLeft.mTarget == null || constraintWidget2.mRight.mTarget == null || constraintWidget2.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT)) {
                        z = true;
                    }
                    constraintWidget.mLeftHasCentered = z;
                    if (constraintWidget.mLeftHasCentered) {
                        if (constraintWidget2.mRight.mTarget != null) {
                            if (constraintWidget2.mRight.mTarget.mOwner != constraintWidget) {
                            }
                        }
                        i += i - constraintWidget2.mDistToLeft;
                    }
                }
            } else {
                zArr[0] = false;
                return;
            }
            if (constraintWidget.getVisibility() == 8) {
                i -= constraintWidget.mWidth;
                i2 -= constraintWidget.mWidth;
            }
            constraintWidget.mDistToLeft = i;
            constraintWidget.mDistToRight = i2;
            return;
        }
        zArr[0] = false;
    }

    public void findVerticalWrapRecursive(ConstraintWidget constraintWidget, boolean[] zArr) {
        ConstraintWidget constraintWidget2 = null;
        boolean z = false;
        if (constraintWidget.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mDimensionRatio <= 0.0f) {
            int i;
            int i2;
            boolean optimizerWrapHeight = constraintWidget.getOptimizerWrapHeight();
            constraintWidget.mVerticalWrapVisited = true;
            int i3;
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() != 0) {
                    i3 = optimizerWrapHeight;
                    z = optimizerWrapHeight;
                } else if (guideline.getRelativeBegin() != -1) {
                    optimizerWrapHeight = guideline.getRelativeBegin();
                    i3 = 0;
                    z = optimizerWrapHeight;
                } else {
                    i3 = guideline.getRelativeEnd() != -1 ? guideline.getRelativeEnd() : 0;
                }
                i = i3;
                i2 = z;
            } else if (constraintWidget.mBaseline.mTarget == null && constraintWidget.mTop.mTarget == null && constraintWidget.mBottom.mTarget == null) {
                i2 = optimizerWrapHeight + constraintWidget.getY();
            } else if (constraintWidget.mBottom.mTarget != null && constraintWidget.mTop.mTarget != null && (constraintWidget.mBottom.mTarget == constraintWidget.mTop.mTarget || (constraintWidget.mBottom.mTarget.mOwner == constraintWidget.mTop.mTarget.mOwner && constraintWidget.mBottom.mTarget.mOwner != constraintWidget.mParent))) {
                zArr[0] = false;
                return;
            } else if (constraintWidget.mBaseline.isConnected()) {
                r0 = constraintWidget.mBaseline.mTarget.getOwner();
                if (!r0.mVerticalWrapVisited) {
                    findVerticalWrapRecursive(r0, zArr);
                }
                int max = Math.max((r0.mDistToTop - r0.mHeight) + optimizerWrapHeight, optimizerWrapHeight);
                i3 = Math.max((r0.mDistToBottom - r0.mHeight) + optimizerWrapHeight, optimizerWrapHeight);
                if (constraintWidget.getVisibility() == 8) {
                    max -= constraintWidget.mHeight;
                    i3 -= constraintWidget.mHeight;
                }
                constraintWidget.mDistToTop = max;
                constraintWidget.mDistToBottom = i3;
                return;
            } else {
                if (constraintWidget.mTop.isConnected()) {
                    r0 = constraintWidget.mTop.mTarget.getOwner();
                    i2 = constraintWidget.mTop.getMargin() + optimizerWrapHeight;
                    if (!(r0.isRoot() || r0.mVerticalWrapVisited)) {
                        findVerticalWrapRecursive(r0, zArr);
                    }
                } else {
                    r0 = null;
                    i2 = optimizerWrapHeight;
                }
                if (constraintWidget.mBottom.isConnected()) {
                    constraintWidget2 = constraintWidget.mBottom.mTarget.getOwner();
                    i = optimizerWrapHeight + constraintWidget.mBottom.getMargin();
                    if (!(constraintWidget2.isRoot() || constraintWidget2.mVerticalWrapVisited)) {
                        findVerticalWrapRecursive(constraintWidget2, zArr);
                    }
                }
                if (!(constraintWidget.mTop.mTarget == null || r0.isRoot())) {
                    boolean z2;
                    if (constraintWidget.mTop.mTarget.getType() == Type.TOP) {
                        i2 += r0.mDistToTop - r0.getOptimizerWrapHeight();
                    } else if (constraintWidget.mTop.mTarget.getType() == Type.BOTTOM) {
                        i2 += r0.mDistToTop;
                    }
                    if (r0.mTopHasCentered || !(r0.mTop.mTarget == null || r0.mTop.mTarget.mOwner == constraintWidget || r0.mBottom.mTarget == null || r0.mBottom.mTarget.mOwner == constraintWidget || r0.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT)) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    constraintWidget.mTopHasCentered = z2;
                    if (constraintWidget.mTopHasCentered) {
                        if (r0.mBottom.mTarget != null) {
                            if (r0.mBottom.mTarget.mOwner != constraintWidget) {
                            }
                        }
                        i2 += i2 - r0.mDistToTop;
                    }
                }
                if (!(constraintWidget.mBottom.mTarget == null || constraintWidget2.isRoot())) {
                    if (constraintWidget.mBottom.mTarget.getType() == Type.BOTTOM) {
                        i += constraintWidget2.mDistToBottom - constraintWidget2.getOptimizerWrapHeight();
                    } else if (constraintWidget.mBottom.mTarget.getType() == Type.TOP) {
                        i += constraintWidget2.mDistToBottom;
                    }
                    if (constraintWidget2.mBottomHasCentered || !(constraintWidget2.mTop.mTarget == null || constraintWidget2.mTop.mTarget.mOwner == constraintWidget || constraintWidget2.mBottom.mTarget == null || constraintWidget2.mBottom.mTarget.mOwner == constraintWidget || constraintWidget2.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT)) {
                        z = true;
                    }
                    constraintWidget.mBottomHasCentered = z;
                    if (constraintWidget.mBottomHasCentered) {
                        if (constraintWidget2.mTop.mTarget != null) {
                            if (constraintWidget2.mTop.mTarget.mOwner != constraintWidget) {
                            }
                        }
                        i += i - constraintWidget2.mDistToBottom;
                    }
                }
            }
            if (constraintWidget.getVisibility() == 8) {
                i2 -= constraintWidget.mHeight;
                i -= constraintWidget.mHeight;
            }
            constraintWidget.mDistToTop = i2;
            constraintWidget.mDistToBottom = i;
            return;
        }
        zArr[0] = false;
    }

    public void findWrapSize(ArrayList<ConstraintWidget> arrayList, boolean[] zArr) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int size = arrayList.size();
        zArr[0] = true;
        int i7 = 0;
        while (i7 < size) {
            int i8;
            int i9;
            int i10;
            ConstraintWidget constraintWidget = (ConstraintWidget) arrayList.get(i7);
            if (constraintWidget.isRoot()) {
                i8 = i6;
                i9 = i5;
                i10 = i4;
                i6 = i3;
                i5 = i2;
                i4 = i;
            } else {
                if (!constraintWidget.mHorizontalWrapVisited) {
                    findHorizontalWrapRecursive(constraintWidget, zArr);
                }
                if (!constraintWidget.mVerticalWrapVisited) {
                    findVerticalWrapRecursive(constraintWidget, zArr);
                }
                if (zArr[0]) {
                    i9 = (constraintWidget.mDistToLeft + constraintWidget.mDistToRight) - constraintWidget.getWidth();
                    i10 = (constraintWidget.mDistToTop + constraintWidget.mDistToBottom) - constraintWidget.getHeight();
                    if (constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_PARENT) {
                        i9 = (constraintWidget.getWidth() + constraintWidget.mLeft.mMargin) + constraintWidget.mRight.mMargin;
                    }
                    if (constraintWidget.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_PARENT) {
                        i10 = (constraintWidget.getHeight() + constraintWidget.mTop.mMargin) + constraintWidget.mBottom.mMargin;
                    }
                    if (constraintWidget.getVisibility() == 8) {
                        i9 = 0;
                        i10 = 0;
                    }
                    i2 = Math.max(i2, constraintWidget.mDistToLeft);
                    i3 = Math.max(i3, constraintWidget.mDistToRight);
                    i4 = Math.max(i4, constraintWidget.mDistToBottom);
                    i = Math.max(i, constraintWidget.mDistToTop);
                    i9 = Math.max(i5, i9);
                    i8 = Math.max(i6, i10);
                    i10 = i4;
                    i6 = i3;
                    i5 = i2;
                    i4 = i;
                } else {
                    return;
                }
            }
            i7++;
            i2 = i5;
            i = i4;
            i4 = i10;
            i3 = i6;
            i5 = i9;
            i6 = i8;
        }
        this.mWrapWidth = Math.max(this.mMinWidth, Math.max(Math.max(i2, i3), i5));
        this.mWrapHeight = Math.max(this.mMinHeight, Math.max(Math.max(i, i4), i6));
        for (i9 = 0; i9 < size; i9++) {
            constraintWidget = (ConstraintWidget) arrayList.get(i9);
            constraintWidget.mHorizontalWrapVisited = false;
            constraintWidget.mVerticalWrapVisited = false;
            constraintWidget.mLeftHasCentered = false;
            constraintWidget.mRightHasCentered = false;
            constraintWidget.mTopHasCentered = false;
            constraintWidget.mBottomHasCentered = false;
        }
    }

    public int layoutFindGroups() {
        int i;
        int i2;
        Type[] typeArr = new Type[]{Type.LEFT, Type.RIGHT, Type.TOP, Type.BASELINE, Type.BOTTOM};
        int i3 = 1;
        int size = this.mChildren.size();
        for (i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
            if (constraintAnchor.mTarget == null) {
                constraintAnchor.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor, i3) == i3) {
                i3++;
            }
            constraintAnchor = constraintWidget.mTop;
            if (constraintAnchor.mTarget == null) {
                constraintAnchor.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor, i3) == i3) {
                i3++;
            }
            constraintAnchor = constraintWidget.mRight;
            if (constraintAnchor.mTarget == null) {
                constraintAnchor.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor, i3) == i3) {
                i3++;
            }
            constraintAnchor = constraintWidget.mBottom;
            if (constraintAnchor.mTarget == null) {
                constraintAnchor.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor, i3) == i3) {
                i3++;
            }
            ConstraintAnchor constraintAnchor2 = constraintWidget.mBaseline;
            if (constraintAnchor2.mTarget == null) {
                constraintAnchor2.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor2, i3) == i3) {
                i3++;
            }
        }
        Object obj = 1;
        int i4 = 0;
        i = 0;
        while (obj != null) {
            int i5;
            obj = null;
            int i6 = i4 + 1;
            for (i5 = 0; i5 < size; i5++) {
                constraintWidget = (ConstraintWidget) this.mChildren.get(i5);
                for (Type type : typeArr) {
                    ConstraintAnchor constraintAnchor3 = null;
                    switch (type) {
                        case LEFT:
                            constraintAnchor3 = constraintWidget.mLeft;
                            break;
                        case TOP:
                            constraintAnchor3 = constraintWidget.mTop;
                            break;
                        case RIGHT:
                            constraintAnchor3 = constraintWidget.mRight;
                            break;
                        case BOTTOM:
                            constraintAnchor3 = constraintWidget.mBottom;
                            break;
                        case BASELINE:
                            constraintAnchor3 = constraintWidget.mBaseline;
                            break;
                    }
                    ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
                    if (constraintAnchor4 != null) {
                        if (!(constraintAnchor4.mOwner.getParent() == null || constraintAnchor4.mGroup == constraintAnchor3.mGroup)) {
                            i2 = constraintAnchor3.mGroup > constraintAnchor4.mGroup ? constraintAnchor4.mGroup : constraintAnchor3.mGroup;
                            constraintAnchor3.mGroup = i2;
                            constraintAnchor4.mGroup = i2;
                            i++;
                            obj = 1;
                        }
                        constraintAnchor4 = constraintAnchor4.getOpposite();
                        if (!(constraintAnchor4 == null || constraintAnchor4.mGroup == constraintAnchor3.mGroup)) {
                            i2 = constraintAnchor3.mGroup > constraintAnchor4.mGroup ? constraintAnchor4.mGroup : constraintAnchor3.mGroup;
                            constraintAnchor3.mGroup = i2;
                            constraintAnchor4.mGroup = i2;
                            i++;
                            obj = 1;
                        }
                    }
                }
            }
            i4 = i6;
        }
        i = 0;
        int[] iArr = new int[((this.mChildren.size() * typeArr.length) + 1)];
        Arrays.fill(iArr, -1);
        i2 = 0;
        while (i2 < size) {
            constraintWidget = (ConstraintWidget) this.mChildren.get(i2);
            ConstraintAnchor constraintAnchor5 = constraintWidget.mLeft;
            if (constraintAnchor5.mGroup != Integer.MAX_VALUE) {
                i6 = constraintAnchor5.mGroup;
                if (iArr[i6] == -1) {
                    i3 = i + 1;
                    iArr[i6] = i;
                } else {
                    i3 = i;
                }
                constraintAnchor5.mGroup = iArr[i6];
            } else {
                i3 = i;
            }
            constraintAnchor5 = constraintWidget.mTop;
            if (constraintAnchor5.mGroup != Integer.MAX_VALUE) {
                i6 = constraintAnchor5.mGroup;
                if (iArr[i6] == -1) {
                    i = i3 + 1;
                    iArr[i6] = i3;
                    i3 = i;
                }
                constraintAnchor5.mGroup = iArr[i6];
            }
            constraintAnchor5 = constraintWidget.mRight;
            if (constraintAnchor5.mGroup != Integer.MAX_VALUE) {
                i6 = constraintAnchor5.mGroup;
                if (iArr[i6] == -1) {
                    i = i3 + 1;
                    iArr[i6] = i3;
                    i3 = i;
                }
                constraintAnchor5.mGroup = iArr[i6];
            }
            constraintAnchor5 = constraintWidget.mBottom;
            if (constraintAnchor5.mGroup != Integer.MAX_VALUE) {
                i6 = constraintAnchor5.mGroup;
                if (iArr[i6] == -1) {
                    i = i3 + 1;
                    iArr[i6] = i3;
                    i3 = i;
                }
                constraintAnchor5.mGroup = iArr[i6];
            }
            ConstraintAnchor constraintAnchor6 = constraintWidget.mBaseline;
            if (constraintAnchor6.mGroup != Integer.MAX_VALUE) {
                i5 = constraintAnchor6.mGroup;
                if (iArr[i5] == -1) {
                    i4 = i3 + 1;
                    iArr[i5] = i3;
                    i3 = i4;
                }
                constraintAnchor6.mGroup = iArr[i5];
            }
            i2++;
            i = i3;
        }
        return i;
    }

    public void layoutWithGroup(int i) {
        int i2 = 0;
        int i3 = this.mX;
        int i4 = this.mY;
        if (this.mParent != null) {
            if (this.mSnapshot == null) {
                this.mSnapshot = new Snapshot(this);
            }
            this.mSnapshot.updateFrom(this);
            this.mX = 0;
            this.mY = 0;
            resetAnchors();
            resetSolverVariables(this.mSystem.getCache());
        } else {
            this.mX = 0;
            this.mY = 0;
        }
        int size = this.mChildren.size();
        for (int i5 = 0; i5 < size; i5++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i5);
            if (constraintWidget instanceof WidgetContainer) {
                ((WidgetContainer) constraintWidget).layout();
            }
        }
        this.mLeft.mGroup = 0;
        this.mRight.mGroup = 0;
        this.mTop.mGroup = 1;
        this.mBottom.mGroup = 1;
        this.mSystem.reset();
        while (i2 < i) {
            try {
                addToSolver(this.mSystem, i2);
                this.mSystem.minimize();
                updateFromSolver(this.mSystem, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            updateFromSolver(this.mSystem, -2);
            i2++;
        }
        if (this.mParent != null) {
            int width = getWidth();
            i2 = getHeight();
            this.mSnapshot.applyTo(this);
            setWidth(width);
            setHeight(i2);
        } else {
            this.mX = i3;
            this.mY = i4;
        }
        if (this == getRootConstraintContainer()) {
            updateDrawPosition();
        }
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 0) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            while (constraintWidget.mLeft.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mRight.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mRight.mTarget == constraintWidget.mLeft && constraintWidget.mLeft.mTarget.mOwner != constraintWidget) {
                constraintWidget = constraintWidget.mLeft.mTarget.mOwner;
            }
            addHorizontalChain(constraintWidget);
        } else if (i == 1) {
            while (constraintWidget.mTop.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mBottom.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mBottom.mTarget == constraintWidget.mTop && constraintWidget.mTop.mTarget.mOwner != constraintWidget) {
                constraintWidget = constraintWidget.mTop.mTarget.mOwner;
            }
            addVerticalChain(constraintWidget);
        }
    }

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        int i = 0;
        while (i < this.mHorizontalChainsSize) {
            if (this.mHorizontalChainsArray[i] != constraintWidget) {
                i++;
            } else {
                return;
            }
        }
        if (this.mHorizontalChainsSize + 1 >= this.mHorizontalChainsArray.length) {
            this.mHorizontalChainsArray = (ConstraintWidget[]) Arrays.copyOf(this.mHorizontalChainsArray, this.mHorizontalChainsArray.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = constraintWidget;
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        int i = 0;
        while (i < this.mVerticalChainsSize) {
            if (this.mVerticalChainsArray[i] != constraintWidget) {
                i++;
            } else {
                return;
            }
        }
        if (this.mVerticalChainsSize + 1 >= this.mVerticalChainsArray.length) {
            this.mVerticalChainsArray = (ConstraintWidget[]) Arrays.copyOf(this.mVerticalChainsArray, this.mVerticalChainsArray.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = constraintWidget;
        this.mVerticalChainsSize++;
    }

    private int countMatchConstraintsChainedWidgets(LinearSystem linearSystem, ConstraintWidget[] constraintWidgetArr, ConstraintWidget constraintWidget, int i, boolean[] zArr) {
        int i2;
        zArr[0] = true;
        zArr[1] = false;
        constraintWidgetArr[0] = null;
        constraintWidgetArr[2] = null;
        constraintWidgetArr[1] = null;
        constraintWidgetArr[3] = null;
        boolean z;
        ConstraintWidget constraintWidget2;
        ConstraintWidget constraintWidget3;
        ConstraintWidget constraintWidget4;
        ConstraintWidget constraintWidget5;
        ConstraintWidget constraintWidget6;
        int i3;
        if (i == 0) {
            if (constraintWidget.mLeft.mTarget == null || constraintWidget.mLeft.mTarget.mOwner == this) {
                z = true;
            } else {
                z = false;
            }
            constraintWidget.mHorizontalNextWidget = null;
            constraintWidget2 = null;
            if (constraintWidget.getVisibility() != 8) {
                constraintWidget2 = constraintWidget;
            }
            constraintWidget3 = null;
            i2 = 0;
            constraintWidget4 = constraintWidget;
            constraintWidget5 = constraintWidget2;
            while (constraintWidget4.mRight.mTarget != null) {
                constraintWidget4.mHorizontalNextWidget = null;
                if (constraintWidget4.getVisibility() != 8) {
                    if (constraintWidget5 == null) {
                        constraintWidget6 = constraintWidget4;
                    } else {
                        constraintWidget6 = constraintWidget5;
                    }
                    if (!(constraintWidget2 == null || constraintWidget2 == constraintWidget4)) {
                        constraintWidget2.mHorizontalNextWidget = constraintWidget4;
                    }
                    constraintWidget5 = constraintWidget4;
                } else {
                    linearSystem.addEquality(constraintWidget4.mLeft.mSolverVariable, constraintWidget4.mLeft.mTarget.mSolverVariable, 0, 5);
                    linearSystem.addEquality(constraintWidget4.mRight.mSolverVariable, constraintWidget4.mLeft.mSolverVariable, 0, 5);
                    constraintWidget6 = constraintWidget5;
                    constraintWidget5 = constraintWidget2;
                }
                if (constraintWidget4.getVisibility() != 8 && constraintWidget4.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                    if (constraintWidget4.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                        zArr[0] = false;
                    }
                    if (constraintWidget4.mDimensionRatio <= 0.0f) {
                        zArr[0] = false;
                        if (i2 + 1 >= this.mMatchConstraintsChainedWidgets.length) {
                            this.mMatchConstraintsChainedWidgets = (ConstraintWidget[]) Arrays.copyOf(this.mMatchConstraintsChainedWidgets, this.mMatchConstraintsChainedWidgets.length * 2);
                        }
                        i3 = i2 + 1;
                        this.mMatchConstraintsChainedWidgets[i2] = constraintWidget4;
                        i2 = i3;
                    }
                }
                if (constraintWidget4.mRight.mTarget.mOwner.mLeft.mTarget == null || constraintWidget4.mRight.mTarget.mOwner.mLeft.mTarget.mOwner != constraintWidget4 || constraintWidget4.mRight.mTarget.mOwner == constraintWidget4) {
                    break;
                }
                constraintWidget2 = constraintWidget4.mRight.mTarget.mOwner;
                constraintWidget3 = constraintWidget2;
                constraintWidget4 = constraintWidget2;
                constraintWidget2 = constraintWidget5;
                constraintWidget5 = constraintWidget6;
            }
            constraintWidget6 = constraintWidget5;
            constraintWidget5 = constraintWidget2;
            if (!(constraintWidget4.mRight.mTarget == null || constraintWidget4.mRight.mTarget.mOwner == this)) {
                z = false;
            }
            if (constraintWidget.mLeft.mTarget == null || constraintWidget3.mRight.mTarget == null) {
                zArr[1] = true;
            }
            constraintWidget.mHorizontalChainFixedPosition = z;
            constraintWidget3.mHorizontalNextWidget = null;
            constraintWidgetArr[0] = constraintWidget;
            constraintWidgetArr[2] = constraintWidget6;
            constraintWidgetArr[1] = constraintWidget3;
            constraintWidgetArr[3] = constraintWidget5;
        } else {
            if (constraintWidget.mTop.mTarget == null || constraintWidget.mTop.mTarget.mOwner == this) {
                z = true;
            } else {
                z = false;
            }
            constraintWidget.mVerticalNextWidget = null;
            constraintWidget2 = null;
            if (constraintWidget.getVisibility() != 8) {
                constraintWidget2 = constraintWidget;
            }
            constraintWidget3 = null;
            i2 = 0;
            constraintWidget4 = constraintWidget;
            constraintWidget5 = constraintWidget2;
            while (constraintWidget4.mBottom.mTarget != null) {
                constraintWidget4.mVerticalNextWidget = null;
                if (constraintWidget4.getVisibility() != 8) {
                    if (constraintWidget5 == null) {
                        constraintWidget6 = constraintWidget4;
                    } else {
                        constraintWidget6 = constraintWidget5;
                    }
                    if (!(constraintWidget2 == null || constraintWidget2 == constraintWidget4)) {
                        constraintWidget2.mVerticalNextWidget = constraintWidget4;
                    }
                    constraintWidget5 = constraintWidget4;
                } else {
                    linearSystem.addEquality(constraintWidget4.mTop.mSolverVariable, constraintWidget4.mTop.mTarget.mSolverVariable, 0, 5);
                    linearSystem.addEquality(constraintWidget4.mBottom.mSolverVariable, constraintWidget4.mTop.mSolverVariable, 0, 5);
                    constraintWidget6 = constraintWidget5;
                    constraintWidget5 = constraintWidget2;
                }
                if (constraintWidget4.getVisibility() != 8 && constraintWidget4.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                    if (constraintWidget4.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                        zArr[0] = false;
                    }
                    if (constraintWidget4.mDimensionRatio <= 0.0f) {
                        zArr[0] = false;
                        if (i2 + 1 >= this.mMatchConstraintsChainedWidgets.length) {
                            this.mMatchConstraintsChainedWidgets = (ConstraintWidget[]) Arrays.copyOf(this.mMatchConstraintsChainedWidgets, this.mMatchConstraintsChainedWidgets.length * 2);
                        }
                        i3 = i2 + 1;
                        this.mMatchConstraintsChainedWidgets[i2] = constraintWidget4;
                        i2 = i3;
                    }
                }
                if (constraintWidget4.mBottom.mTarget.mOwner.mTop.mTarget == null || constraintWidget4.mBottom.mTarget.mOwner.mTop.mTarget.mOwner != constraintWidget4 || constraintWidget4.mBottom.mTarget.mOwner == constraintWidget4) {
                    break;
                }
                constraintWidget2 = constraintWidget4.mBottom.mTarget.mOwner;
                constraintWidget3 = constraintWidget2;
                constraintWidget4 = constraintWidget2;
                constraintWidget2 = constraintWidget5;
                constraintWidget5 = constraintWidget6;
            }
            constraintWidget6 = constraintWidget5;
            constraintWidget5 = constraintWidget2;
            if (!(constraintWidget4.mBottom.mTarget == null || constraintWidget4.mBottom.mTarget.mOwner == this)) {
                z = false;
            }
            if (constraintWidget.mTop.mTarget == null || constraintWidget3.mBottom.mTarget == null) {
                zArr[1] = true;
            }
            constraintWidget.mVerticalChainFixedPosition = z;
            constraintWidget3.mVerticalNextWidget = null;
            constraintWidgetArr[0] = constraintWidget;
            constraintWidgetArr[2] = constraintWidget6;
            constraintWidgetArr[1] = constraintWidget3;
            constraintWidgetArr[3] = constraintWidget5;
        }
        return i2;
    }
}
