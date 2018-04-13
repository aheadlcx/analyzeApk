package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;

public class Optimizer {
    static void applyDirectResolutionHorizontalChain(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i, ConstraintWidget constraintWidget) {
        int i2;
        float f;
        int i3 = 0;
        int i4 = 0;
        float f2 = 0.0f;
        ConstraintWidget constraintWidget2 = null;
        ConstraintWidget constraintWidget3 = constraintWidget;
        while (constraintWidget3 != null) {
            if ((constraintWidget3.getVisibility() == 8 ? 1 : null) == null) {
                i2 = i4 + 1;
                if (constraintWidget3.mHorizontalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
                    i3 = (constraintWidget3.mRight.mTarget != null ? constraintWidget3.mRight.getMargin() : 0) + ((i3 + constraintWidget3.getWidth()) + (constraintWidget3.mLeft.mTarget != null ? constraintWidget3.mLeft.getMargin() : 0));
                } else {
                    f2 = constraintWidget3.mHorizontalWeight + f2;
                }
            } else {
                i2 = i4;
            }
            ConstraintWidget constraintWidget4 = constraintWidget3.mRight.mTarget != null ? constraintWidget3.mRight.mTarget.mOwner : null;
            if (constraintWidget4 != null && (constraintWidget4.mLeft.mTarget == null || !(constraintWidget4.mLeft.mTarget == null || constraintWidget4.mLeft.mTarget.mOwner == constraintWidget3))) {
                constraintWidget4 = null;
            }
            constraintWidget2 = constraintWidget3;
            constraintWidget3 = constraintWidget4;
            i4 = i2;
        }
        i2 = 0;
        if (constraintWidget2 != null) {
            i2 = constraintWidget2.mRight.mTarget != null ? constraintWidget2.mRight.mTarget.mOwner.getX() : 0;
            if (constraintWidget2.mRight.mTarget != null && constraintWidget2.mRight.mTarget.mOwner == constraintWidgetContainer) {
                i2 = constraintWidgetContainer.getRight();
            }
        }
        float f3 = ((float) (i2 - 0)) - ((float) i3);
        float f4 = f3 / ((float) (i4 + 1));
        if (i == 0) {
            f = f4;
        } else {
            f = f3 / ((float) i);
            f4 = 0.0f;
        }
        while (constraintWidget != null) {
            float width;
            i4 = constraintWidget.mLeft.mTarget != null ? constraintWidget.mLeft.getMargin() : 0;
            i2 = constraintWidget.mRight.mTarget != null ? constraintWidget.mRight.getMargin() : 0;
            if (constraintWidget.getVisibility() != 8) {
                f4 += (float) i4;
                linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, (int) (0.5f + f4));
                if (constraintWidget.mHorizontalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
                    width = ((float) constraintWidget.getWidth()) + f4;
                } else if (f2 == 0.0f) {
                    width = ((f - ((float) i4)) - ((float) i2)) + f4;
                } else {
                    width = ((((constraintWidget.mHorizontalWeight * f3) / f2) - ((float) i4)) - ((float) i2)) + f4;
                }
                linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, (int) (0.5f + width));
                if (i == 0) {
                    width += f;
                }
                width += (float) i2;
            } else {
                width = f4 - (f / 2.0f);
                linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, (int) (0.5f + width));
                linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, (int) (width + 0.5f));
                width = f4;
            }
            ConstraintWidget constraintWidget5 = constraintWidget.mRight.mTarget != null ? constraintWidget.mRight.mTarget.mOwner : null;
            if (!(constraintWidget5 == null || constraintWidget5.mLeft.mTarget == null || constraintWidget5.mLeft.mTarget.mOwner == constraintWidget)) {
                constraintWidget5 = null;
            }
            if (constraintWidget5 == constraintWidgetContainer) {
                constraintWidget5 = null;
            }
            f4 = width;
            constraintWidget = constraintWidget5;
        }
    }

    static void applyDirectResolutionVerticalChain(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i, ConstraintWidget constraintWidget) {
        int i2;
        float f;
        int i3 = 0;
        int i4 = 0;
        float f2 = 0.0f;
        ConstraintWidget constraintWidget2 = null;
        ConstraintWidget constraintWidget3 = constraintWidget;
        while (constraintWidget3 != null) {
            if ((constraintWidget3.getVisibility() == 8 ? 1 : null) == null) {
                i2 = i4 + 1;
                if (constraintWidget3.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
                    i3 = (constraintWidget3.mBottom.mTarget != null ? constraintWidget3.mBottom.getMargin() : 0) + ((i3 + constraintWidget3.getHeight()) + (constraintWidget3.mTop.mTarget != null ? constraintWidget3.mTop.getMargin() : 0));
                } else {
                    f2 = constraintWidget3.mVerticalWeight + f2;
                }
            } else {
                i2 = i4;
            }
            ConstraintWidget constraintWidget4 = constraintWidget3.mBottom.mTarget != null ? constraintWidget3.mBottom.mTarget.mOwner : null;
            if (constraintWidget4 != null && (constraintWidget4.mTop.mTarget == null || !(constraintWidget4.mTop.mTarget == null || constraintWidget4.mTop.mTarget.mOwner == constraintWidget3))) {
                constraintWidget4 = null;
            }
            constraintWidget2 = constraintWidget3;
            constraintWidget3 = constraintWidget4;
            i4 = i2;
        }
        i2 = 0;
        if (constraintWidget2 != null) {
            i2 = constraintWidget2.mBottom.mTarget != null ? constraintWidget2.mBottom.mTarget.mOwner.getX() : 0;
            if (constraintWidget2.mBottom.mTarget != null && constraintWidget2.mBottom.mTarget.mOwner == constraintWidgetContainer) {
                i2 = constraintWidgetContainer.getBottom();
            }
        }
        float f3 = ((float) (i2 - 0)) - ((float) i3);
        float f4 = f3 / ((float) (i4 + 1));
        if (i == 0) {
            f = f4;
        } else {
            f = f3 / ((float) i);
            f4 = 0.0f;
        }
        while (constraintWidget != null) {
            float height;
            i4 = constraintWidget.mTop.mTarget != null ? constraintWidget.mTop.getMargin() : 0;
            i2 = constraintWidget.mBottom.mTarget != null ? constraintWidget.mBottom.getMargin() : 0;
            if (constraintWidget.getVisibility() != 8) {
                f4 += (float) i4;
                linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, (int) (0.5f + f4));
                if (constraintWidget.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
                    height = ((float) constraintWidget.getHeight()) + f4;
                } else if (f2 == 0.0f) {
                    height = ((f - ((float) i4)) - ((float) i2)) + f4;
                } else {
                    height = ((((constraintWidget.mVerticalWeight * f3) / f2) - ((float) i4)) - ((float) i2)) + f4;
                }
                linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, (int) (0.5f + height));
                if (i == 0) {
                    height += f;
                }
                height += (float) i2;
            } else {
                height = f4 - (f / 2.0f);
                linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, (int) (0.5f + height));
                linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, (int) (height + 0.5f));
                height = f4;
            }
            ConstraintWidget constraintWidget5 = constraintWidget.mBottom.mTarget != null ? constraintWidget.mBottom.mTarget.mOwner : null;
            if (!(constraintWidget5 == null || constraintWidget5.mTop.mTarget == null || constraintWidget5.mTop.mTarget.mOwner == constraintWidget)) {
                constraintWidget5 = null;
            }
            if (constraintWidget5 == constraintWidgetContainer) {
                constraintWidget5 = null;
            }
            f4 = height;
            constraintWidget = constraintWidget5;
        }
    }

    static void checkMatchParent(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ConstraintWidget constraintWidget) {
        if (constraintWidgetContainer.mHorizontalDimensionBehaviour != DimensionBehaviour.WRAP_CONTENT && constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_PARENT) {
            constraintWidget.mLeft.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mLeft);
            constraintWidget.mRight.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mRight);
            int i = constraintWidget.mLeft.mMargin;
            int width = constraintWidgetContainer.getWidth() - constraintWidget.mRight.mMargin;
            linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, i);
            linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, width);
            constraintWidget.setHorizontalDimension(i, width);
            constraintWidget.mHorizontalResolution = 2;
        }
        if (constraintWidgetContainer.mVerticalDimensionBehaviour != DimensionBehaviour.WRAP_CONTENT && constraintWidget.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_PARENT) {
            constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
            constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
            i = constraintWidget.mTop.mMargin;
            width = constraintWidgetContainer.getHeight() - constraintWidget.mBottom.mMargin;
            linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, i);
            linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, width);
            if (constraintWidget.mBaselineDistance > 0 || constraintWidget.getVisibility() == 8) {
                constraintWidget.mBaseline.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBaseline);
                linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + i);
            }
            constraintWidget.setVerticalDimension(i, width);
            constraintWidget.mVerticalResolution = 2;
        }
    }

    static void checkHorizontalSimpleDependency(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ConstraintWidget constraintWidget) {
        if (constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
            constraintWidget.mHorizontalResolution = 1;
        } else if (constraintWidgetContainer.mHorizontalDimensionBehaviour != DimensionBehaviour.WRAP_CONTENT && constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_PARENT) {
            constraintWidget.mLeft.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mLeft);
            constraintWidget.mRight.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mRight);
            r0 = constraintWidget.mLeft.mMargin;
            r1 = constraintWidgetContainer.getWidth() - constraintWidget.mRight.mMargin;
            linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, r0);
            linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, r1);
            constraintWidget.setHorizontalDimension(r0, r1);
            constraintWidget.mHorizontalResolution = 2;
        } else if (constraintWidget.mLeft.mTarget == null || constraintWidget.mRight.mTarget == null) {
            if (constraintWidget.mLeft.mTarget != null && constraintWidget.mLeft.mTarget.mOwner == constraintWidgetContainer) {
                r0 = constraintWidget.mLeft.getMargin();
                r1 = constraintWidget.getWidth() + r0;
                constraintWidget.mLeft.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mLeft);
                constraintWidget.mRight.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mRight);
                linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, r0);
                linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, r1);
                constraintWidget.mHorizontalResolution = 2;
                constraintWidget.setHorizontalDimension(r0, r1);
            } else if (constraintWidget.mRight.mTarget != null && constraintWidget.mRight.mTarget.mOwner == constraintWidgetContainer) {
                constraintWidget.mLeft.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mLeft);
                constraintWidget.mRight.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mRight);
                r0 = constraintWidgetContainer.getWidth() - constraintWidget.mRight.getMargin();
                r1 = r0 - constraintWidget.getWidth();
                linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, r1);
                linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, r0);
                constraintWidget.mHorizontalResolution = 2;
                constraintWidget.setHorizontalDimension(r1, r0);
            } else if (constraintWidget.mLeft.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mHorizontalResolution == 2) {
                r0 = constraintWidget.mLeft.mTarget.mSolverVariable;
                constraintWidget.mLeft.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mLeft);
                constraintWidget.mRight.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mRight);
                r0 = (int) ((r0.computedValue + ((float) constraintWidget.mLeft.getMargin())) + 0.5f);
                r1 = constraintWidget.getWidth() + r0;
                linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, r0);
                linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, r1);
                constraintWidget.mHorizontalResolution = 2;
                constraintWidget.setHorizontalDimension(r0, r1);
            } else if (constraintWidget.mRight.mTarget == null || constraintWidget.mRight.mTarget.mOwner.mHorizontalResolution != 2) {
                r0 = constraintWidget.mLeft.mTarget != null ? 1 : 0;
                int i;
                if (constraintWidget.mRight.mTarget != null) {
                    i = 1;
                } else {
                    i = 0;
                }
                if (r0 != 0 || r3 != 0) {
                    return;
                }
                if (constraintWidget instanceof Guideline) {
                    Guideline guideline = (Guideline) constraintWidget;
                    if (guideline.getOrientation() == 1) {
                        float relativeBegin;
                        constraintWidget.mLeft.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mLeft);
                        constraintWidget.mRight.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mRight);
                        if (guideline.getRelativeBegin() != -1) {
                            relativeBegin = (float) guideline.getRelativeBegin();
                        } else if (guideline.getRelativeEnd() != -1) {
                            relativeBegin = (float) (constraintWidgetContainer.getWidth() - guideline.getRelativeEnd());
                        } else {
                            relativeBegin = guideline.getRelativePercent() * ((float) constraintWidgetContainer.getWidth());
                        }
                        r0 = (int) (relativeBegin + 0.5f);
                        linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, r0);
                        linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, r0);
                        constraintWidget.mHorizontalResolution = 2;
                        constraintWidget.mVerticalResolution = 2;
                        constraintWidget.setHorizontalDimension(r0, r0);
                        constraintWidget.setVerticalDimension(0, constraintWidgetContainer.getHeight());
                        return;
                    }
                    return;
                }
                constraintWidget.mLeft.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mLeft);
                constraintWidget.mRight.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mRight);
                r0 = constraintWidget.getX();
                r1 = constraintWidget.getWidth() + r0;
                linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, r0);
                linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, r1);
                constraintWidget.mHorizontalResolution = 2;
            } else {
                r0 = constraintWidget.mRight.mTarget.mSolverVariable;
                constraintWidget.mLeft.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mLeft);
                constraintWidget.mRight.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mRight);
                r0 = (int) ((r0.computedValue - ((float) constraintWidget.mRight.getMargin())) + 0.5f);
                r1 = r0 - constraintWidget.getWidth();
                linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, r1);
                linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, r0);
                constraintWidget.mHorizontalResolution = 2;
                constraintWidget.setHorizontalDimension(r1, r0);
            }
        } else if (constraintWidget.mLeft.mTarget.mOwner == constraintWidgetContainer && constraintWidget.mRight.mTarget.mOwner == constraintWidgetContainer) {
            r1 = constraintWidget.mLeft.getMargin();
            r0 = constraintWidget.mRight.getMargin();
            if (constraintWidgetContainer.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                r0 = constraintWidgetContainer.getWidth() - r0;
            } else {
                r1 += (int) ((((float) (((constraintWidgetContainer.getWidth() - r1) - r0) - constraintWidget.getWidth())) * constraintWidget.mHorizontalBiasPercent) + 0.5f);
                r0 = constraintWidget.getWidth() + r1;
            }
            constraintWidget.mLeft.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mLeft);
            constraintWidget.mRight.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mRight);
            linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, r1);
            linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, r0);
            constraintWidget.mHorizontalResolution = 2;
            constraintWidget.setHorizontalDimension(r1, r0);
        } else {
            constraintWidget.mHorizontalResolution = 1;
        }
    }

    static void checkVerticalSimpleDependency(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ConstraintWidget constraintWidget) {
        int i = 1;
        if (constraintWidget.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
            constraintWidget.mVerticalResolution = 1;
        } else if (constraintWidgetContainer.mVerticalDimensionBehaviour != DimensionBehaviour.WRAP_CONTENT && constraintWidget.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_PARENT) {
            constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
            constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
            r0 = constraintWidget.mTop.mMargin;
            i = constraintWidgetContainer.getHeight() - constraintWidget.mBottom.mMargin;
            linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, r0);
            linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, i);
            if (constraintWidget.mBaselineDistance > 0 || constraintWidget.getVisibility() == 8) {
                constraintWidget.mBaseline.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBaseline);
                linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + r0);
            }
            constraintWidget.setVerticalDimension(r0, i);
            constraintWidget.mVerticalResolution = 2;
        } else if (constraintWidget.mTop.mTarget == null || constraintWidget.mBottom.mTarget == null) {
            if (constraintWidget.mTop.mTarget != null && constraintWidget.mTop.mTarget.mOwner == constraintWidgetContainer) {
                r0 = constraintWidget.mTop.getMargin();
                i = constraintWidget.getHeight() + r0;
                constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
                constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
                linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, r0);
                linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, i);
                if (constraintWidget.mBaselineDistance > 0 || constraintWidget.getVisibility() == 8) {
                    constraintWidget.mBaseline.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBaseline);
                    linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + r0);
                }
                constraintWidget.mVerticalResolution = 2;
                constraintWidget.setVerticalDimension(r0, i);
            } else if (constraintWidget.mBottom.mTarget != null && constraintWidget.mBottom.mTarget.mOwner == constraintWidgetContainer) {
                constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
                constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
                r0 = constraintWidgetContainer.getHeight() - constraintWidget.mBottom.getMargin();
                i = r0 - constraintWidget.getHeight();
                linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, i);
                linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, r0);
                if (constraintWidget.mBaselineDistance > 0 || constraintWidget.getVisibility() == 8) {
                    constraintWidget.mBaseline.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBaseline);
                    linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + i);
                }
                constraintWidget.mVerticalResolution = 2;
                constraintWidget.setVerticalDimension(i, r0);
            } else if (constraintWidget.mTop.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mVerticalResolution == 2) {
                r0 = constraintWidget.mTop.mTarget.mSolverVariable;
                constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
                constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
                r0 = (int) ((r0.computedValue + ((float) constraintWidget.mTop.getMargin())) + 0.5f);
                i = constraintWidget.getHeight() + r0;
                linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, r0);
                linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, i);
                if (constraintWidget.mBaselineDistance > 0 || constraintWidget.getVisibility() == 8) {
                    constraintWidget.mBaseline.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBaseline);
                    linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + r0);
                }
                constraintWidget.mVerticalResolution = 2;
                constraintWidget.setVerticalDimension(r0, i);
            } else if (constraintWidget.mBottom.mTarget != null && constraintWidget.mBottom.mTarget.mOwner.mVerticalResolution == 2) {
                r0 = constraintWidget.mBottom.mTarget.mSolverVariable;
                constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
                constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
                r0 = (int) ((r0.computedValue - ((float) constraintWidget.mBottom.getMargin())) + 0.5f);
                i = r0 - constraintWidget.getHeight();
                linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, i);
                linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, r0);
                if (constraintWidget.mBaselineDistance > 0 || constraintWidget.getVisibility() == 8) {
                    constraintWidget.mBaseline.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBaseline);
                    linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + i);
                }
                constraintWidget.mVerticalResolution = 2;
                constraintWidget.setVerticalDimension(i, r0);
            } else if (constraintWidget.mBaseline.mTarget == null || constraintWidget.mBaseline.mTarget.mOwner.mVerticalResolution != 2) {
                r0 = constraintWidget.mBaseline.mTarget != null ? 1 : 0;
                int i2;
                if (constraintWidget.mTop.mTarget != null) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (constraintWidget.mBottom.mTarget == null) {
                    i = 0;
                }
                if (r0 != 0 || r3 != 0 || r1 != 0) {
                    return;
                }
                if (constraintWidget instanceof Guideline) {
                    Guideline guideline = (Guideline) constraintWidget;
                    if (guideline.getOrientation() == 0) {
                        float relativeBegin;
                        constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
                        constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
                        if (guideline.getRelativeBegin() != -1) {
                            relativeBegin = (float) guideline.getRelativeBegin();
                        } else if (guideline.getRelativeEnd() != -1) {
                            relativeBegin = (float) (constraintWidgetContainer.getHeight() - guideline.getRelativeEnd());
                        } else {
                            relativeBegin = guideline.getRelativePercent() * ((float) constraintWidgetContainer.getHeight());
                        }
                        r0 = (int) (relativeBegin + 0.5f);
                        linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, r0);
                        linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, r0);
                        constraintWidget.mVerticalResolution = 2;
                        constraintWidget.mHorizontalResolution = 2;
                        constraintWidget.setVerticalDimension(r0, r0);
                        constraintWidget.setHorizontalDimension(0, constraintWidgetContainer.getWidth());
                        return;
                    }
                    return;
                }
                constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
                constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
                r0 = constraintWidget.getY();
                i = constraintWidget.getHeight() + r0;
                linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, r0);
                linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, i);
                if (constraintWidget.mBaselineDistance > 0 || constraintWidget.getVisibility() == 8) {
                    constraintWidget.mBaseline.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBaseline);
                    linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, r0 + constraintWidget.mBaselineDistance);
                }
                constraintWidget.mVerticalResolution = 2;
            } else {
                r0 = constraintWidget.mBaseline.mTarget.mSolverVariable;
                constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
                constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
                r0 = (int) ((r0.computedValue - ((float) constraintWidget.mBaselineDistance)) + 0.5f);
                i = constraintWidget.getHeight() + r0;
                linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, r0);
                linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, i);
                constraintWidget.mBaseline.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBaseline);
                linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + r0);
                constraintWidget.mVerticalResolution = 2;
                constraintWidget.setVerticalDimension(r0, i);
            }
        } else if (constraintWidget.mTop.mTarget.mOwner == constraintWidgetContainer && constraintWidget.mBottom.mTarget.mOwner == constraintWidgetContainer) {
            i = constraintWidget.mTop.getMargin();
            r0 = constraintWidget.mBottom.getMargin();
            if (constraintWidgetContainer.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                r0 = constraintWidget.getHeight() + i;
            } else {
                i = (int) (((((float) (((constraintWidgetContainer.getHeight() - i) - r0) - constraintWidget.getHeight())) * constraintWidget.mVerticalBiasPercent) + ((float) i)) + 0.5f);
                r0 = constraintWidget.getHeight() + i;
            }
            constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
            constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
            linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, i);
            linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, r0);
            if (constraintWidget.mBaselineDistance > 0 || constraintWidget.getVisibility() == 8) {
                constraintWidget.mBaseline.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBaseline);
                linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + i);
            }
            constraintWidget.mVerticalResolution = 2;
            constraintWidget.setVerticalDimension(i, r0);
        } else {
            constraintWidget.mVerticalResolution = 1;
        }
    }
}
