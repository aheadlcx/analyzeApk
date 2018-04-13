package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.Cache;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor.ConnectionType;
import android.support.constraint.solver.widgets.ConstraintAnchor.Strength;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import android.support.media.ExifInterface;
import java.util.ArrayList;

public class ConstraintWidget {
    private static final boolean AUTOTAG_CENTER = false;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    protected ArrayList<ConstraintAnchor> mAnchors;
    ConstraintAnchor mBaseline;
    int mBaselineDistance;
    ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    protected float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    private int mDrawHeight;
    private int mDrawWidth;
    private int mDrawX;
    private int mDrawY;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    DimensionBehaviour mHorizontalDimensionBehaviour;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    float mHorizontalWeight;
    boolean mHorizontalWrapVisited;
    ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    int mMatchConstraintDefaultHeight;
    int mMatchConstraintDefaultWidth;
    int mMatchConstraintMaxHeight;
    int mMatchConstraintMaxWidth;
    int mMatchConstraintMinHeight;
    int mMatchConstraintMinWidth;
    protected int mMinHeight;
    protected int mMinWidth;
    protected int mOffsetX;
    protected int mOffsetY;
    ConstraintWidget mParent;
    ConstraintAnchor mRight;
    boolean mRightHasCentered;
    private int mSolverBottom;
    private int mSolverLeft;
    private int mSolverRight;
    private int mSolverTop;
    ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    DimensionBehaviour mVerticalDimensionBehaviour;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    float mVerticalWeight;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    int mWidth;
    private int mWrapHeight;
    private int mWrapWidth;
    protected int mX;
    protected int mY;

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

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        this.mHorizontalWeight = 0.0f;
        this.mVerticalWeight = 0.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
    }

    public ConstraintWidget() {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mLeft = new ConstraintAnchor(this, Type.LEFT);
        this.mTop = new ConstraintAnchor(this, Type.TOP);
        this.mRight = new ConstraintAnchor(this, Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, Type.CENTER);
        this.mAnchors = new ArrayList();
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mSolverLeft = 0;
        this.mSolverTop = 0;
        this.mSolverRight = 0;
        this.mSolverBottom = 0;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalWeight = 0.0f;
        this.mVerticalWeight = 0.0f;
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        addAnchors();
    }

    public ConstraintWidget(int i, int i2, int i3, int i4) {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mLeft = new ConstraintAnchor(this, Type.LEFT);
        this.mTop = new ConstraintAnchor(this, Type.TOP);
        this.mRight = new ConstraintAnchor(this, Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, Type.CENTER);
        this.mAnchors = new ArrayList();
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mSolverLeft = 0;
        this.mSolverTop = 0;
        this.mSolverRight = 0;
        this.mSolverBottom = 0;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalWeight = 0.0f;
        this.mVerticalWeight = 0.0f;
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.mX = i;
        this.mY = i2;
        this.mWidth = i3;
        this.mHeight = i4;
        addAnchors();
        forceUpdateDrawPosition();
    }

    public ConstraintWidget(int i, int i2) {
        this(0, 0, i, i2);
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    public void resetGroups() {
        int size = this.mAnchors.size();
        for (int i = 0; i < size; i++) {
            ((ConstraintAnchor) this.mAnchors.get(i)).mGroup = Integer.MAX_VALUE;
        }
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public boolean isRootContainer() {
        return (this instanceof ConstraintWidgetContainer) && (this.mParent == null || !(this.mParent instanceof ConstraintWidgetContainer));
    }

    public boolean isInsideConstraintLayout() {
        ConstraintWidget parent = getParent();
        if (parent == null) {
            return false;
        }
        while (parent != null) {
            if (parent instanceof ConstraintWidgetContainer) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public boolean hasAncestor(ConstraintWidget constraintWidget) {
        ConstraintWidget parent = getParent();
        if (parent == constraintWidget) {
            return true;
        }
        if (parent == constraintWidget.getParent()) {
            return false;
        }
        while (parent != null) {
            if (parent == constraintWidget) {
                return true;
            }
            if (parent == constraintWidget.getParent()) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public WidgetContainer getRootWidgetContainer() {
        ConstraintWidget constraintWidget = this;
        while (constraintWidget.getParent() != null) {
            constraintWidget = constraintWidget.getParent();
        }
        if (constraintWidget instanceof WidgetContainer) {
            return (WidgetContainer) constraintWidget;
        }
        return null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.mDebugName = str;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        createObjectVariable.setName(str + ".left");
        createObjectVariable2.setName(str + ".top");
        createObjectVariable3.setName(str + ".right");
        createObjectVariable4.setName(str + ".bottom");
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline).setName(str + ".baseline");
        }
    }

    public String toString() {
        return (this.mType != null ? "type: " + this.mType + " " : "") + (this.mDebugName != null ? "id: " + this.mDebugName + " " : "") + "(" + this.mX + ", " + this.mY + ") - (" + this.mWidth + " x " + this.mHeight + ")" + " wrap: (" + this.mWrapWidth + " x " + this.mWrapHeight + ")";
    }

    int getInternalDrawX() {
        return this.mDrawX;
    }

    int getInternalDrawY() {
        return this.mDrawY;
    }

    public int getInternalDrawRight() {
        return this.mDrawX + this.mDrawWidth;
    }

    public int getInternalDrawBottom() {
        return this.mDrawY + this.mDrawHeight;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int i = this.mWidth;
        if (this.mHorizontalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i;
        }
        if (this.mMatchConstraintDefaultWidth == 1) {
            i = Math.max(this.mMatchConstraintMinWidth, i);
        } else if (this.mMatchConstraintMinWidth > 0) {
            i = this.mMatchConstraintMinWidth;
            this.mWidth = i;
        } else {
            i = 0;
        }
        if (this.mMatchConstraintMaxWidth <= 0 || this.mMatchConstraintMaxWidth >= i) {
            return i;
        }
        return this.mMatchConstraintMaxWidth;
    }

    public int getOptimizerWrapHeight() {
        int i = this.mHeight;
        if (this.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i;
        }
        if (this.mMatchConstraintDefaultHeight == 1) {
            i = Math.max(this.mMatchConstraintMinHeight, i);
        } else if (this.mMatchConstraintMinHeight > 0) {
            i = this.mMatchConstraintMinHeight;
            this.mHeight = i;
        } else {
            i = 0;
        }
        if (this.mMatchConstraintMaxHeight <= 0 || this.mMatchConstraintMaxHeight >= i) {
            return i;
        }
        return this.mMatchConstraintMaxHeight;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    public int getDrawWidth() {
        return this.mDrawWidth;
    }

    public int getDrawHeight() {
        return this.mDrawHeight;
    }

    public int getDrawBottom() {
        return getDrawY() + this.mDrawHeight;
    }

    public int getDrawRight() {
        return getDrawX() + this.mDrawWidth;
    }

    protected int getRootX() {
        return this.mX + this.mOffsetX;
    }

    protected int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public boolean hasBaseline() {
        return this.mBaselineDistance > 0;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int i) {
        this.mX = i;
    }

    public void setY(int i) {
        this.mY = i;
    }

    public void setOrigin(int i, int i2) {
        this.mX = i;
        this.mY = i2;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public void setGoneMargin(Type type, int i) {
        switch (type) {
            case LEFT:
                this.mLeft.mGoneMargin = i;
                return;
            case TOP:
                this.mTop.mGoneMargin = i;
                return;
            case RIGHT:
                this.mRight.mGoneMargin = i;
                return;
            case BOTTOM:
                this.mBottom.mGoneMargin = i;
                return;
            default:
                return;
        }
    }

    public void updateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mX + this.mWidth;
        int i4 = this.mY + this.mHeight;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = i3 - i;
        this.mDrawHeight = i4 - i2;
    }

    public void forceUpdateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mX + this.mWidth;
        int i4 = this.mY + this.mHeight;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = i3 - i;
        this.mDrawHeight = i4 - i2;
    }

    public void setDrawOrigin(int i, int i2) {
        this.mDrawX = i - this.mOffsetX;
        this.mDrawY = i2 - this.mOffsetY;
        this.mX = this.mDrawX;
        this.mY = this.mDrawY;
    }

    public void setDrawX(int i) {
        this.mDrawX = i - this.mOffsetX;
        this.mX = this.mDrawX;
    }

    public void setDrawY(int i) {
        this.mDrawY = i - this.mOffsetY;
        this.mY = this.mDrawY;
    }

    public void setDrawWidth(int i) {
        this.mDrawWidth = i;
    }

    public void setDrawHeight(int i) {
        this.mDrawHeight = i;
    }

    public void setWidth(int i) {
        this.mWidth = i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        this.mMatchConstraintMaxWidth = i3;
    }

    public void setVerticalMatchStyle(int i, int i2, int i3) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        this.mMatchConstraintMaxHeight = i3;
    }

    public void setDimensionRatio(String str) {
        int i = 0;
        if (str == null || str.length() == 0) {
            this.mDimensionRatio = 0.0f;
            return;
        }
        float parseFloat;
        int i2 = -1;
        int length = str.length();
        int indexOf = str.indexOf(44);
        if (indexOf > 0 && indexOf < length - 1) {
            String substring = str.substring(0, indexOf);
            if (!substring.equalsIgnoreCase(ExifInterface.LONGITUDE_WEST)) {
                if (substring.equalsIgnoreCase("H")) {
                    i = 1;
                } else {
                    i = -1;
                }
            }
            i2 = i;
            i = indexOf + 1;
        }
        indexOf = str.indexOf(58);
        String substring2;
        if (indexOf < 0 || indexOf >= length - 1) {
            substring2 = str.substring(i);
            if (substring2.length() > 0) {
                try {
                    parseFloat = Float.parseFloat(substring2);
                } catch (NumberFormatException e) {
                    parseFloat = 0.0f;
                }
            }
            parseFloat = 0.0f;
        } else {
            substring2 = str.substring(i, indexOf);
            String substring3 = str.substring(indexOf + 1);
            if (substring2.length() > 0 && substring3.length() > 0) {
                try {
                    parseFloat = Float.parseFloat(substring2);
                    float parseFloat2 = Float.parseFloat(substring3);
                    if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                        parseFloat = i2 == 1 ? Math.abs(parseFloat2 / parseFloat) : Math.abs(parseFloat / parseFloat2);
                    }
                } catch (NumberFormatException e2) {
                    parseFloat = 0.0f;
                }
            }
            parseFloat = 0.0f;
        }
        if (parseFloat > 0.0f) {
            this.mDimensionRatio = parseFloat;
            this.mDimensionRatioSide = i2;
        }
    }

    public void setDimensionRatio(float f, int i) {
        this.mDimensionRatio = f;
        this.mDimensionRatioSide = i;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setWrapWidth(int i) {
        this.mWrapWidth = i;
    }

    public void setWrapHeight(int i) {
        this.mWrapHeight = i;
    }

    public void setDimension(int i, int i2) {
        this.mWidth = i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
        this.mHeight = i2;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        this.mX = i;
        this.mY = i2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.FIXED && i5 < this.mWidth) {
            i5 = this.mWidth;
        }
        if (this.mVerticalDimensionBehaviour == DimensionBehaviour.FIXED && i6 < this.mHeight) {
            i6 = this.mHeight;
        }
        this.mWidth = i5;
        this.mHeight = i6;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHorizontalDimension(int i, int i2) {
        this.mX = i;
        this.mWidth = i2 - i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setVerticalDimension(int i, int i2) {
        this.mY = i;
        this.mHeight = i2 - i;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i) {
        if (i >= 0) {
            this.mContainerItemSkip = i;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float f) {
        this.mHorizontalWeight = f;
    }

    public void setVerticalWeight(float f) {
        this.mVerticalWeight = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public void connectedTo(ConstraintWidget constraintWidget) {
    }

    public void immediateConnect(Type type, ConstraintWidget constraintWidget, Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, Strength.STRONG, 0, true);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, int i2) {
        connect(constraintAnchor, constraintAnchor2, i, Strength.STRONG, i2);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        connect(constraintAnchor, constraintAnchor2, i, Strength.STRONG, 0);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, Strength strength, int i2) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i, strength, i2);
        }
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2, int i) {
        connect(type, constraintWidget, type2, i, Strength.STRONG);
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2) {
        connect(type, constraintWidget, type2, 0, Strength.STRONG);
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2, int i, Strength strength) {
        connect(type, constraintWidget, type2, i, strength, 0);
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2, int i, Strength strength, int i2) {
        ConstraintAnchor anchor;
        ConstraintAnchor anchor2;
        if (type == Type.CENTER) {
            if (type2 == Type.CENTER) {
                Object obj;
                Object obj2;
                anchor = getAnchor(Type.LEFT);
                anchor2 = getAnchor(Type.RIGHT);
                ConstraintAnchor anchor3 = getAnchor(Type.TOP);
                ConstraintAnchor anchor4 = getAnchor(Type.BOTTOM);
                if (anchor != null && anchor.isConnected()) {
                    obj = null;
                } else if (anchor2 == null || !anchor2.isConnected()) {
                    connect(Type.LEFT, constraintWidget, Type.LEFT, 0, strength, i2);
                    connect(Type.RIGHT, constraintWidget, Type.RIGHT, 0, strength, i2);
                    int i3 = 1;
                } else {
                    obj = null;
                }
                if (anchor3 != null && anchor3.isConnected()) {
                    obj2 = null;
                } else if (anchor4 == null || !anchor4.isConnected()) {
                    connect(Type.TOP, constraintWidget, Type.TOP, 0, strength, i2);
                    connect(Type.BOTTOM, constraintWidget, Type.BOTTOM, 0, strength, i2);
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                if (obj != null && obj2 != null) {
                    getAnchor(Type.CENTER).connect(constraintWidget.getAnchor(Type.CENTER), 0, i2);
                } else if (obj != null) {
                    getAnchor(Type.CENTER_X).connect(constraintWidget.getAnchor(Type.CENTER_X), 0, i2);
                } else if (obj2 != null) {
                    getAnchor(Type.CENTER_Y).connect(constraintWidget.getAnchor(Type.CENTER_Y), 0, i2);
                }
            } else if (type2 == Type.LEFT || type2 == Type.RIGHT) {
                connect(Type.LEFT, constraintWidget, type2, 0, strength, i2);
                connect(Type.RIGHT, constraintWidget, type2, 0, strength, i2);
                getAnchor(Type.CENTER).connect(constraintWidget.getAnchor(type2), 0, i2);
            } else if (type2 == Type.TOP || type2 == Type.BOTTOM) {
                connect(Type.TOP, constraintWidget, type2, 0, strength, i2);
                connect(Type.BOTTOM, constraintWidget, type2, 0, strength, i2);
                getAnchor(Type.CENTER).connect(constraintWidget.getAnchor(type2), 0, i2);
            }
        } else if (type == Type.CENTER_X && (type2 == Type.LEFT || type2 == Type.RIGHT)) {
            r3 = getAnchor(Type.LEFT);
            anchor = constraintWidget.getAnchor(type2);
            anchor2 = getAnchor(Type.RIGHT);
            r3.connect(anchor, 0, i2);
            anchor2.connect(anchor, 0, i2);
            getAnchor(Type.CENTER_X).connect(anchor, 0, i2);
        } else if (type == Type.CENTER_Y && (type2 == Type.TOP || type2 == Type.BOTTOM)) {
            r3 = constraintWidget.getAnchor(type2);
            getAnchor(Type.TOP).connect(r3, 0, i2);
            getAnchor(Type.BOTTOM).connect(r3, 0, i2);
            getAnchor(Type.CENTER_Y).connect(r3, 0, i2);
        } else if (type == Type.CENTER_X && type2 == Type.CENTER_X) {
            getAnchor(Type.LEFT).connect(constraintWidget.getAnchor(Type.LEFT), 0, i2);
            getAnchor(Type.RIGHT).connect(constraintWidget.getAnchor(Type.RIGHT), 0, i2);
            getAnchor(Type.CENTER_X).connect(constraintWidget.getAnchor(type2), 0, i2);
        } else if (type == Type.CENTER_Y && type2 == Type.CENTER_Y) {
            getAnchor(Type.TOP).connect(constraintWidget.getAnchor(Type.TOP), 0, i2);
            getAnchor(Type.BOTTOM).connect(constraintWidget.getAnchor(Type.BOTTOM), 0, i2);
            getAnchor(Type.CENTER_Y).connect(constraintWidget.getAnchor(type2), 0, i2);
        } else {
            r3 = getAnchor(type);
            anchor = constraintWidget.getAnchor(type2);
            if (r3.isValidConnection(anchor)) {
                ConstraintAnchor anchor5;
                if (type == Type.BASELINE) {
                    anchor2 = getAnchor(Type.TOP);
                    anchor5 = getAnchor(Type.BOTTOM);
                    if (anchor2 != null) {
                        anchor2.reset();
                    }
                    if (anchor5 != null) {
                        anchor5.reset();
                    }
                    i = 0;
                } else if (type == Type.TOP || type == Type.BOTTOM) {
                    anchor2 = getAnchor(Type.BASELINE);
                    if (anchor2 != null) {
                        anchor2.reset();
                    }
                    anchor2 = getAnchor(Type.CENTER);
                    if (anchor2.getTarget() != anchor) {
                        anchor2.reset();
                    }
                    anchor2 = getAnchor(type).getOpposite();
                    anchor5 = getAnchor(Type.CENTER_Y);
                    if (anchor5.isConnected()) {
                        anchor2.reset();
                        anchor5.reset();
                    }
                } else if (type == Type.LEFT || type == Type.RIGHT) {
                    anchor2 = getAnchor(Type.CENTER);
                    if (anchor2.getTarget() != anchor) {
                        anchor2.reset();
                    }
                    anchor2 = getAnchor(type).getOpposite();
                    anchor5 = getAnchor(Type.CENTER_X);
                    if (anchor5.isConnected()) {
                        anchor2.reset();
                        anchor5.reset();
                    }
                }
                r3.connect(anchor, i, strength, i2);
                anchor.getOwner().connectedTo(r3.getOwner());
            }
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
        if (!(this instanceof ConstraintWidgetContainer)) {
            if (getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                if (getWidth() == getWrapWidth()) {
                    setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                } else if (getWidth() > getMinWidth()) {
                    setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
            }
            if (getVerticalDimensionBehaviour() != DimensionBehaviour.MATCH_CONSTRAINT) {
                return;
            }
            if (getHeight() == getWrapHeight()) {
                setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
            } else if (getHeight() > getMinHeight()) {
                setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
        }
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() == null || !(getParent() instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            ConstraintAnchor anchor = getAnchor(Type.LEFT);
            ConstraintAnchor anchor2 = getAnchor(Type.RIGHT);
            ConstraintAnchor anchor3 = getAnchor(Type.TOP);
            ConstraintAnchor anchor4 = getAnchor(Type.BOTTOM);
            ConstraintAnchor anchor5 = getAnchor(Type.CENTER);
            ConstraintAnchor anchor6 = getAnchor(Type.CENTER_X);
            ConstraintAnchor anchor7 = getAnchor(Type.CENTER_Y);
            if (constraintAnchor == anchor5) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor.reset();
                    anchor2.reset();
                }
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor6) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                    anchor.reset();
                    anchor2.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor7) {
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor || constraintAnchor == anchor2) {
                if (anchor.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor5.reset();
                }
            } else if ((constraintAnchor == anchor3 || constraintAnchor == anchor4) && anchor3.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                anchor5.reset();
            }
            constraintAnchor.reset();
        }
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i = 0; i < size; i++) {
                ((ConstraintAnchor) this.mAnchors.get(i)).reset();
            }
        }
    }

    public void resetAnchors(int i) {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintAnchor constraintAnchor = (ConstraintAnchor) this.mAnchors.get(i2);
                if (i == constraintAnchor.getConnectionCreator()) {
                    if (constraintAnchor.isVerticalAnchor()) {
                        setVerticalBiasPercent(DEFAULT_BIAS);
                    } else {
                        setHorizontalBiasPercent(DEFAULT_BIAS);
                    }
                    constraintAnchor.reset();
                }
            }
        }
    }

    public void disconnectWidget(ConstraintWidget constraintWidget) {
        ArrayList anchors = getAnchors();
        int size = anchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) anchors.get(i);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget) {
                constraintAnchor.reset();
            }
        }
    }

    public void disconnectUnlockedWidget(ConstraintWidget constraintWidget) {
        ArrayList anchors = getAnchors();
        int size = anchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) anchors.get(i);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget && constraintAnchor.getConnectionCreator() == 2) {
                constraintAnchor.reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(Type type) {
        switch (type) {
            case LEFT:
                return this.mLeft;
            case TOP:
                return this.mTop;
            case RIGHT:
                return this.mRight;
            case BOTTOM:
                return this.mBottom;
            case BASELINE:
                return this.mBaseline;
            case CENTER_X:
                return this.mCenterX;
            case CENTER_Y:
                return this.mCenterY;
            case CENTER:
                return this.mCenter;
            default:
                return null;
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mHorizontalDimensionBehaviour;
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mVerticalDimensionBehaviour;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mHorizontalDimensionBehaviour = dimensionBehaviour;
        if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mVerticalDimensionBehaviour = dimensionBehaviour;
        if (this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    public boolean isInHorizontalChain() {
        if ((this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != this.mLeft) && (this.mRight.mTarget == null || this.mRight.mTarget.mTarget != this.mRight)) {
            return false;
        }
        return true;
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget = this;
        ConstraintWidget constraintWidget2 = null;
        while (constraintWidget2 == null && constraintWidget != null) {
            ConstraintAnchor anchor = constraintWidget.getAnchor(Type.LEFT);
            anchor = anchor == null ? null : anchor.getTarget();
            ConstraintWidget owner = anchor == null ? null : anchor.getOwner();
            if (owner == getParent()) {
                return constraintWidget;
            }
            ConstraintAnchor target = owner == null ? null : owner.getAnchor(Type.RIGHT).getTarget();
            if (target == null || target.getOwner() == constraintWidget) {
                constraintWidget = owner;
                owner = constraintWidget2;
            } else {
                owner = constraintWidget;
            }
            constraintWidget2 = owner;
        }
        return constraintWidget2;
    }

    public boolean isInVerticalChain() {
        if ((this.mTop.mTarget == null || this.mTop.mTarget.mTarget != this.mTop) && (this.mBottom.mTarget == null || this.mBottom.mTarget.mTarget != this.mBottom)) {
            return false;
        }
        return true;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget = this;
        ConstraintWidget constraintWidget2 = null;
        while (constraintWidget2 == null && constraintWidget != null) {
            ConstraintAnchor anchor = constraintWidget.getAnchor(Type.TOP);
            anchor = anchor == null ? null : anchor.getTarget();
            ConstraintWidget owner = anchor == null ? null : anchor.getOwner();
            if (owner == getParent()) {
                return constraintWidget;
            }
            ConstraintAnchor target = owner == null ? null : owner.getAnchor(Type.BOTTOM).getTarget();
            if (target == null || target.getOwner() == constraintWidget) {
                constraintWidget = owner;
                owner = constraintWidget2;
            } else {
                owner = constraintWidget;
            }
            constraintWidget2 = owner;
        }
        return constraintWidget2;
    }

    public void addToSolver(LinearSystem linearSystem) {
        addToSolver(linearSystem, Integer.MAX_VALUE);
    }

    public void addToSolver(LinearSystem linearSystem, int i) {
        SolverVariable createObjectVariable;
        SolverVariable createObjectVariable2;
        SolverVariable createObjectVariable3;
        SolverVariable createObjectVariable4;
        SolverVariable createObjectVariable5;
        SolverVariable createObjectVariable6;
        boolean z;
        boolean z2;
        boolean z3;
        float f;
        int i2;
        Object obj;
        boolean z4;
        int i3;
        int i4;
        boolean z5;
        ConstraintAnchor constraintAnchor;
        ArrayRow createRow;
        SolverVariable createErrorVariable;
        if (i == Integer.MAX_VALUE || this.mLeft.mGroup == i) {
            createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        } else {
            createObjectVariable = null;
        }
        if (i == Integer.MAX_VALUE || this.mRight.mGroup == i) {
            createObjectVariable2 = linearSystem.createObjectVariable(this.mRight);
        } else {
            createObjectVariable2 = null;
        }
        if (i == Integer.MAX_VALUE || this.mTop.mGroup == i) {
            createObjectVariable3 = linearSystem.createObjectVariable(this.mTop);
        } else {
            createObjectVariable3 = null;
        }
        if (i == Integer.MAX_VALUE || this.mBottom.mGroup == i) {
            createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        } else {
            createObjectVariable4 = null;
        }
        if (i == Integer.MAX_VALUE || this.mBaseline.mGroup == i) {
            createObjectVariable5 = linearSystem.createObjectVariable(this.mBaseline);
        } else {
            createObjectVariable5 = null;
        }
        if (this.mParent != null) {
            boolean z6;
            boolean z7;
            ArrayRow createRow2;
            if ((this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != this.mLeft) && (this.mRight.mTarget == null || this.mRight.mTarget.mTarget != this.mRight)) {
                z6 = false;
            } else {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 0);
                z6 = true;
            }
            if ((this.mTop.mTarget == null || this.mTop.mTarget.mTarget != this.mTop) && (this.mBottom.mTarget == null || this.mBottom.mTarget.mTarget != this.mBottom)) {
                z7 = false;
            } else {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 1);
                z7 = true;
            }
            if (this.mParent.getHorizontalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT && !z6) {
                if (this.mLeft.mTarget == null || this.mLeft.mTarget.mOwner != this.mParent) {
                    createObjectVariable6 = linearSystem.createObjectVariable(this.mParent.mLeft);
                    createRow2 = linearSystem.createRow();
                    createRow2.createRowGreaterThan(createObjectVariable, createObjectVariable6, linearSystem.createSlackVariable(), 0);
                    linearSystem.addConstraint(createRow2);
                } else if (this.mLeft.mTarget != null && this.mLeft.mTarget.mOwner == this.mParent) {
                    this.mLeft.setConnectionType(ConnectionType.STRICT);
                }
                if (this.mRight.mTarget == null || this.mRight.mTarget.mOwner != this.mParent) {
                    createObjectVariable6 = linearSystem.createObjectVariable(this.mParent.mRight);
                    createRow2 = linearSystem.createRow();
                    createRow2.createRowGreaterThan(createObjectVariable6, createObjectVariable2, linearSystem.createSlackVariable(), 0);
                    linearSystem.addConstraint(createRow2);
                } else if (this.mRight.mTarget != null && this.mRight.mTarget.mOwner == this.mParent) {
                    this.mRight.setConnectionType(ConnectionType.STRICT);
                }
            }
            if (this.mParent.getVerticalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT && !z7) {
                if (this.mTop.mTarget == null || this.mTop.mTarget.mOwner != this.mParent) {
                    createObjectVariable6 = linearSystem.createObjectVariable(this.mParent.mTop);
                    createRow2 = linearSystem.createRow();
                    createRow2.createRowGreaterThan(createObjectVariable3, createObjectVariable6, linearSystem.createSlackVariable(), 0);
                    linearSystem.addConstraint(createRow2);
                } else if (this.mTop.mTarget != null && this.mTop.mTarget.mOwner == this.mParent) {
                    this.mTop.setConnectionType(ConnectionType.STRICT);
                }
                if (this.mBottom.mTarget == null || this.mBottom.mTarget.mOwner != this.mParent) {
                    createObjectVariable6 = linearSystem.createObjectVariable(this.mParent.mBottom);
                    createRow2 = linearSystem.createRow();
                    createRow2.createRowGreaterThan(createObjectVariable6, createObjectVariable4, linearSystem.createSlackVariable(), 0);
                    linearSystem.addConstraint(createRow2);
                    z = z7;
                    z2 = z6;
                } else if (this.mBottom.mTarget != null && this.mBottom.mTarget.mOwner == this.mParent) {
                    this.mBottom.setConnectionType(ConnectionType.STRICT);
                }
            }
            z = z7;
            z2 = z6;
        } else {
            z = false;
            z2 = false;
        }
        int i5 = this.mWidth;
        if (i5 < this.mMinWidth) {
            i5 = this.mMinWidth;
        }
        int i6 = this.mHeight;
        if (i6 < this.mMinHeight) {
            i6 = this.mMinHeight;
        }
        boolean z8 = this.mHorizontalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z9 = this.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT;
        if (z8 || this.mLeft == null || this.mRight == null || !(this.mLeft.mTarget == null || this.mRight.mTarget == null)) {
            z3 = z8;
        } else {
            z3 = true;
        }
        if (z9 || this.mTop == null || this.mBottom == null || (!(this.mTop.mTarget == null || this.mBottom.mTarget == null) || (this.mBaselineDistance != 0 && (this.mBaseline == null || !(this.mTop.mTarget == null || this.mBaseline.mTarget == null))))) {
            z8 = z9;
        } else {
            z8 = true;
        }
        Object obj2 = null;
        int i7 = this.mDimensionRatioSide;
        float f2 = this.mDimensionRatio;
        if (this.mDimensionRatio > 0.0f && this.mVisibility != 8) {
            if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && this.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                obj2 = 1;
                if (z3 && !z8) {
                    f = f2;
                    i2 = 0;
                    obj = 1;
                    z4 = z8;
                    i3 = i6;
                    i4 = i5;
                    z9 = z3;
                    if (obj == null) {
                    }
                    if (this.mHorizontalDimensionBehaviour != DimensionBehaviour.WRAP_CONTENT) {
                    }
                    if (z5) {
                    }
                    applyConstraints(linearSystem, z8, z9, this.mLeft, this.mRight, this.mX, this.mX + i4, i4, this.mMinWidth, this.mHorizontalBiasPercent, z5, z2, this.mMatchConstraintDefaultWidth, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth);
                    if (this.mVerticalResolution != 2) {
                        if (this.mVerticalDimensionBehaviour != DimensionBehaviour.WRAP_CONTENT) {
                        }
                        if (obj == null) {
                        }
                        if (this.mBaselineDistance <= 0) {
                            constraintAnchor = this.mBottom;
                            linearSystem.addEquality(createObjectVariable5, createObjectVariable3, getBaselineDistance(), 5);
                            if (this.mBaseline.mTarget == null) {
                                i4 = this.mBaselineDistance;
                                constraintAnchor = this.mBaseline;
                            } else {
                                i4 = i3;
                            }
                            if (z5) {
                            }
                            applyConstraints(linearSystem, z8, z4, this.mTop, constraintAnchor, this.mY, this.mY + i4, i4, this.mMinHeight, this.mVerticalBiasPercent, z5, z, this.mMatchConstraintDefaultHeight, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight);
                            linearSystem.addEquality(createObjectVariable4, createObjectVariable3, i3, 5);
                        } else {
                            if (z5) {
                            }
                            applyConstraints(linearSystem, z8, z4, this.mTop, this.mBottom, this.mY, this.mY + i3, i3, this.mMinHeight, this.mVerticalBiasPercent, z5, z, this.mMatchConstraintDefaultHeight, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight);
                        }
                        if (obj == null) {
                            createRow = linearSystem.createRow();
                            if (i == Integer.MAX_VALUE) {
                            }
                            if (i2 != 0) {
                                linearSystem.addConstraint(createRow.createRowDimensionRatio(createObjectVariable2, createObjectVariable, createObjectVariable4, createObjectVariable3, f));
                            } else if (i2 != 1) {
                                linearSystem.addConstraint(createRow.createRowDimensionRatio(createObjectVariable4, createObjectVariable3, createObjectVariable2, createObjectVariable, f));
                            } else {
                                if (this.mMatchConstraintMinWidth > 0) {
                                    linearSystem.addGreaterThan(createObjectVariable2, createObjectVariable, this.mMatchConstraintMinWidth, 3);
                                }
                                if (this.mMatchConstraintMinHeight > 0) {
                                    linearSystem.addGreaterThan(createObjectVariable4, createObjectVariable3, this.mMatchConstraintMinHeight, 3);
                                }
                                createRow.createRowDimensionRatio(createObjectVariable2, createObjectVariable, createObjectVariable4, createObjectVariable3, f);
                                createErrorVariable = linearSystem.createErrorVariable();
                                createObjectVariable6 = linearSystem.createErrorVariable();
                                createErrorVariable.strength = 4;
                                createObjectVariable6.strength = 4;
                                createRow.addError(createErrorVariable, createObjectVariable6);
                                linearSystem.addConstraint(createRow);
                            }
                        }
                    }
                } else if (!z3 && z8) {
                    i7 = 1;
                    if (this.mDimensionRatioSide == -1) {
                        f = 1.0f / f2;
                        i2 = 1;
                        int i8 = 1;
                        z4 = z8;
                        i3 = i6;
                        i4 = i5;
                        z9 = z3;
                        if (obj == null) {
                        }
                        if (this.mHorizontalDimensionBehaviour != DimensionBehaviour.WRAP_CONTENT) {
                        }
                        if (z5) {
                        }
                        applyConstraints(linearSystem, z8, z9, this.mLeft, this.mRight, this.mX, this.mX + i4, i4, this.mMinWidth, this.mHorizontalBiasPercent, z5, z2, this.mMatchConstraintDefaultWidth, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth);
                        if (this.mVerticalResolution != 2) {
                            if (this.mVerticalDimensionBehaviour != DimensionBehaviour.WRAP_CONTENT) {
                            }
                            if (obj == null) {
                            }
                            if (this.mBaselineDistance <= 0) {
                                if (z5) {
                                }
                                applyConstraints(linearSystem, z8, z4, this.mTop, this.mBottom, this.mY, this.mY + i3, i3, this.mMinHeight, this.mVerticalBiasPercent, z5, z, this.mMatchConstraintDefaultHeight, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight);
                            } else {
                                constraintAnchor = this.mBottom;
                                linearSystem.addEquality(createObjectVariable5, createObjectVariable3, getBaselineDistance(), 5);
                                if (this.mBaseline.mTarget == null) {
                                    i4 = i3;
                                } else {
                                    i4 = this.mBaselineDistance;
                                    constraintAnchor = this.mBaseline;
                                }
                                if (z5) {
                                }
                                applyConstraints(linearSystem, z8, z4, this.mTop, constraintAnchor, this.mY, this.mY + i4, i4, this.mMinHeight, this.mVerticalBiasPercent, z5, z, this.mMatchConstraintDefaultHeight, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight);
                                linearSystem.addEquality(createObjectVariable4, createObjectVariable3, i3, 5);
                            }
                            if (obj == null) {
                                createRow = linearSystem.createRow();
                                if (i == Integer.MAX_VALUE) {
                                }
                                if (i2 != 0) {
                                    linearSystem.addConstraint(createRow.createRowDimensionRatio(createObjectVariable2, createObjectVariable, createObjectVariable4, createObjectVariable3, f));
                                } else if (i2 != 1) {
                                    if (this.mMatchConstraintMinWidth > 0) {
                                        linearSystem.addGreaterThan(createObjectVariable2, createObjectVariable, this.mMatchConstraintMinWidth, 3);
                                    }
                                    if (this.mMatchConstraintMinHeight > 0) {
                                        linearSystem.addGreaterThan(createObjectVariable4, createObjectVariable3, this.mMatchConstraintMinHeight, 3);
                                    }
                                    createRow.createRowDimensionRatio(createObjectVariable2, createObjectVariable, createObjectVariable4, createObjectVariable3, f);
                                    createErrorVariable = linearSystem.createErrorVariable();
                                    createObjectVariable6 = linearSystem.createErrorVariable();
                                    createErrorVariable.strength = 4;
                                    createObjectVariable6.strength = 4;
                                    createRow.addError(createErrorVariable, createObjectVariable6);
                                    linearSystem.addConstraint(createRow);
                                } else {
                                    linearSystem.addConstraint(createRow.createRowDimensionRatio(createObjectVariable4, createObjectVariable3, createObjectVariable2, createObjectVariable, f));
                                }
                            }
                        }
                    }
                }
            }
            SolverVariable createObjectVariable7;
            SolverVariable createObjectVariable8;
            if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                i4 = (int) (((float) this.mHeight) * f2);
                f = f2;
                i2 = 0;
                obj = null;
                z4 = z8;
                i3 = i6;
                z9 = true;
            } else if (this.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                float f3;
                if (this.mDimensionRatioSide == -1) {
                    f3 = 1.0f / f2;
                } else {
                    f3 = f2;
                }
                i3 = (int) (((float) this.mWidth) * f3);
                f = f3;
                i2 = 1;
                obj = null;
                z4 = true;
                i4 = i5;
                z9 = z3;
            }
            z5 = obj == null && (i2 == 0 || i2 == -1);
            z8 = this.mHorizontalDimensionBehaviour != DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
            if (this.mHorizontalResolution != 2 && (i == Integer.MAX_VALUE || (this.mLeft.mGroup == i && this.mRight.mGroup == i))) {
                if (z5 || this.mLeft.mTarget == null || this.mRight.mTarget == null) {
                    applyConstraints(linearSystem, z8, z9, this.mLeft, this.mRight, this.mX, this.mX + i4, i4, this.mMinWidth, this.mHorizontalBiasPercent, z5, z2, this.mMatchConstraintDefaultWidth, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth);
                } else {
                    createErrorVariable = linearSystem.createObjectVariable(this.mLeft);
                    createObjectVariable7 = linearSystem.createObjectVariable(this.mRight);
                    createObjectVariable6 = linearSystem.createObjectVariable(this.mLeft.getTarget());
                    createObjectVariable8 = linearSystem.createObjectVariable(this.mRight.getTarget());
                    linearSystem.addGreaterThan(createErrorVariable, createObjectVariable6, this.mLeft.getMargin(), 3);
                    linearSystem.addLowerThan(createObjectVariable7, createObjectVariable8, this.mRight.getMargin() * -1, 3);
                    if (!z2) {
                        linearSystem.addCentering(createErrorVariable, createObjectVariable6, this.mLeft.getMargin(), this.mHorizontalBiasPercent, createObjectVariable8, createObjectVariable7, this.mRight.getMargin(), 4);
                    }
                }
            }
            if (this.mVerticalResolution != 2) {
                z8 = this.mVerticalDimensionBehaviour != DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
                z5 = obj == null && (i2 == 1 || i2 == -1);
                if (this.mBaselineDistance <= 0) {
                    constraintAnchor = this.mBottom;
                    if (i == Integer.MAX_VALUE || (this.mBottom.mGroup == i && this.mBaseline.mGroup == i)) {
                        linearSystem.addEquality(createObjectVariable5, createObjectVariable3, getBaselineDistance(), 5);
                    }
                    if (this.mBaseline.mTarget == null) {
                        i4 = this.mBaselineDistance;
                        constraintAnchor = this.mBaseline;
                    } else {
                        i4 = i3;
                    }
                    if (i == Integer.MAX_VALUE || (this.mTop.mGroup == i && constraintAnchor.mGroup == i)) {
                        if (z5 || this.mTop.mTarget == null || this.mBottom.mTarget == null) {
                            applyConstraints(linearSystem, z8, z4, this.mTop, constraintAnchor, this.mY, this.mY + i4, i4, this.mMinHeight, this.mVerticalBiasPercent, z5, z, this.mMatchConstraintDefaultHeight, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight);
                            linearSystem.addEquality(createObjectVariable4, createObjectVariable3, i3, 5);
                        } else {
                            createErrorVariable = linearSystem.createObjectVariable(this.mTop);
                            createObjectVariable7 = linearSystem.createObjectVariable(this.mBottom);
                            createObjectVariable6 = linearSystem.createObjectVariable(this.mTop.getTarget());
                            createObjectVariable8 = linearSystem.createObjectVariable(this.mBottom.getTarget());
                            linearSystem.addGreaterThan(createErrorVariable, createObjectVariable6, this.mTop.getMargin(), 3);
                            linearSystem.addLowerThan(createObjectVariable7, createObjectVariable8, this.mBottom.getMargin() * -1, 3);
                            if (!z) {
                                linearSystem.addCentering(createErrorVariable, createObjectVariable6, this.mTop.getMargin(), this.mVerticalBiasPercent, createObjectVariable8, createObjectVariable7, this.mBottom.getMargin(), 4);
                            }
                        }
                    }
                } else if (i == Integer.MAX_VALUE || (this.mTop.mGroup == i && this.mBottom.mGroup == i)) {
                    if (z5 || this.mTop.mTarget == null || this.mBottom.mTarget == null) {
                        applyConstraints(linearSystem, z8, z4, this.mTop, this.mBottom, this.mY, this.mY + i3, i3, this.mMinHeight, this.mVerticalBiasPercent, z5, z, this.mMatchConstraintDefaultHeight, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight);
                    } else {
                        createErrorVariable = linearSystem.createObjectVariable(this.mTop);
                        createObjectVariable7 = linearSystem.createObjectVariable(this.mBottom);
                        createObjectVariable6 = linearSystem.createObjectVariable(this.mTop.getTarget());
                        createObjectVariable8 = linearSystem.createObjectVariable(this.mBottom.getTarget());
                        linearSystem.addGreaterThan(createErrorVariable, createObjectVariable6, this.mTop.getMargin(), 3);
                        linearSystem.addLowerThan(createObjectVariable7, createObjectVariable8, this.mBottom.getMargin() * -1, 3);
                        if (!z) {
                            linearSystem.addCentering(createErrorVariable, createObjectVariable6, this.mTop.getMargin(), this.mVerticalBiasPercent, createObjectVariable8, createObjectVariable7, this.mBottom.getMargin(), 4);
                        }
                    }
                }
                if (obj == null) {
                    createRow = linearSystem.createRow();
                    if (i == Integer.MAX_VALUE && (this.mLeft.mGroup != i || this.mRight.mGroup != i)) {
                        return;
                    }
                    if (i2 != 0) {
                        linearSystem.addConstraint(createRow.createRowDimensionRatio(createObjectVariable2, createObjectVariable, createObjectVariable4, createObjectVariable3, f));
                    } else if (i2 != 1) {
                        linearSystem.addConstraint(createRow.createRowDimensionRatio(createObjectVariable4, createObjectVariable3, createObjectVariable2, createObjectVariable, f));
                    } else {
                        if (this.mMatchConstraintMinWidth > 0) {
                            linearSystem.addGreaterThan(createObjectVariable2, createObjectVariable, this.mMatchConstraintMinWidth, 3);
                        }
                        if (this.mMatchConstraintMinHeight > 0) {
                            linearSystem.addGreaterThan(createObjectVariable4, createObjectVariable3, this.mMatchConstraintMinHeight, 3);
                        }
                        createRow.createRowDimensionRatio(createObjectVariable2, createObjectVariable, createObjectVariable4, createObjectVariable3, f);
                        createErrorVariable = linearSystem.createErrorVariable();
                        createObjectVariable6 = linearSystem.createErrorVariable();
                        createErrorVariable.strength = 4;
                        createObjectVariable6.strength = 4;
                        createRow.addError(createErrorVariable, createObjectVariable6);
                        linearSystem.addConstraint(createRow);
                    }
                }
            }
        }
        f = f2;
        i2 = i7;
        obj = obj2;
        z4 = z8;
        i3 = i6;
        i4 = i5;
        z9 = z3;
        if (obj == null) {
        }
        if (this.mHorizontalDimensionBehaviour != DimensionBehaviour.WRAP_CONTENT) {
        }
        if (z5) {
        }
        applyConstraints(linearSystem, z8, z9, this.mLeft, this.mRight, this.mX, this.mX + i4, i4, this.mMinWidth, this.mHorizontalBiasPercent, z5, z2, this.mMatchConstraintDefaultWidth, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth);
        if (this.mVerticalResolution != 2) {
            if (this.mVerticalDimensionBehaviour != DimensionBehaviour.WRAP_CONTENT) {
            }
            if (obj == null) {
            }
            if (this.mBaselineDistance <= 0) {
                if (z5) {
                }
                applyConstraints(linearSystem, z8, z4, this.mTop, this.mBottom, this.mY, this.mY + i3, i3, this.mMinHeight, this.mVerticalBiasPercent, z5, z, this.mMatchConstraintDefaultHeight, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight);
            } else {
                constraintAnchor = this.mBottom;
                linearSystem.addEquality(createObjectVariable5, createObjectVariable3, getBaselineDistance(), 5);
                if (this.mBaseline.mTarget == null) {
                    i4 = i3;
                } else {
                    i4 = this.mBaselineDistance;
                    constraintAnchor = this.mBaseline;
                }
                if (z5) {
                }
                applyConstraints(linearSystem, z8, z4, this.mTop, constraintAnchor, this.mY, this.mY + i4, i4, this.mMinHeight, this.mVerticalBiasPercent, z5, z, this.mMatchConstraintDefaultHeight, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight);
                linearSystem.addEquality(createObjectVariable4, createObjectVariable3, i3, 5);
            }
            if (obj == null) {
                createRow = linearSystem.createRow();
                if (i == Integer.MAX_VALUE) {
                }
                if (i2 != 0) {
                    linearSystem.addConstraint(createRow.createRowDimensionRatio(createObjectVariable2, createObjectVariable, createObjectVariable4, createObjectVariable3, f));
                } else if (i2 != 1) {
                    if (this.mMatchConstraintMinWidth > 0) {
                        linearSystem.addGreaterThan(createObjectVariable2, createObjectVariable, this.mMatchConstraintMinWidth, 3);
                    }
                    if (this.mMatchConstraintMinHeight > 0) {
                        linearSystem.addGreaterThan(createObjectVariable4, createObjectVariable3, this.mMatchConstraintMinHeight, 3);
                    }
                    createRow.createRowDimensionRatio(createObjectVariable2, createObjectVariable, createObjectVariable4, createObjectVariable3, f);
                    createErrorVariable = linearSystem.createErrorVariable();
                    createObjectVariable6 = linearSystem.createErrorVariable();
                    createErrorVariable.strength = 4;
                    createObjectVariable6.strength = 4;
                    createRow.addError(createErrorVariable, createObjectVariable6);
                    linearSystem.addConstraint(createRow);
                } else {
                    linearSystem.addConstraint(createRow.createRowDimensionRatio(createObjectVariable4, createObjectVariable3, createObjectVariable2, createObjectVariable, f));
                }
            }
        }
    }

    private void applyConstraints(LinearSystem linearSystem, boolean z, boolean z2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, int i2, int i3, int i4, float f, boolean z3, boolean z4, int i5, int i6, int i7) {
        int i8;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintAnchor);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(constraintAnchor2);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(constraintAnchor.getTarget());
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(constraintAnchor2.getTarget());
        int margin = constraintAnchor.getMargin();
        int margin2 = constraintAnchor2.getMargin();
        if (this.mVisibility == 8) {
            i8 = 0;
            z2 = true;
        } else {
            i8 = i3;
        }
        if (createObjectVariable3 == null && createObjectVariable4 == null) {
            linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, i));
            if (!z3) {
                if (z) {
                    linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i4, true));
                } else if (z2) {
                    linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i8, false));
                } else {
                    linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, i2));
                }
            }
        } else if (createObjectVariable3 != null && createObjectVariable4 == null) {
            linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, createObjectVariable3, margin));
            if (z) {
                linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i4, true));
            } else if (!z3) {
                if (z2) {
                    linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable, i8));
                } else {
                    linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, i2));
                }
            }
        } else if (createObjectVariable3 == null && createObjectVariable4 != null) {
            linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable4, margin2 * -1));
            if (z) {
                linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i4, true));
            } else if (!z3) {
                if (z2) {
                    linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable, i8));
                } else {
                    linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, i));
                }
            }
        } else if (z2) {
            if (z) {
                linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i4, true));
            } else {
                linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable, i8));
            }
            if (constraintAnchor.getStrength() != constraintAnchor2.getStrength()) {
                SolverVariable createSlackVariable;
                if (constraintAnchor.getStrength() == Strength.STRONG) {
                    linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, createObjectVariable3, margin));
                    createSlackVariable = linearSystem.createSlackVariable();
                    ArrayRow createRow = linearSystem.createRow();
                    createRow.createRowLowerThan(createObjectVariable2, createObjectVariable4, createSlackVariable, margin2 * -1);
                    linearSystem.addConstraint(createRow);
                    return;
                }
                createSlackVariable = linearSystem.createSlackVariable();
                ArrayRow createRow2 = linearSystem.createRow();
                createRow2.createRowGreaterThan(createObjectVariable, createObjectVariable3, createSlackVariable, margin);
                linearSystem.addConstraint(createRow2);
                linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable4, margin2 * -1));
            } else if (createObjectVariable3 == createObjectVariable4) {
                linearSystem.addConstraint(LinearSystem.createRowCentering(linearSystem, createObjectVariable, createObjectVariable3, 0, 0.5f, createObjectVariable4, createObjectVariable2, 0, true));
            } else if (!z4) {
                linearSystem.addConstraint(LinearSystem.createRowGreaterThan(linearSystem, createObjectVariable, createObjectVariable3, margin, constraintAnchor.getConnectionType() != ConnectionType.STRICT));
                linearSystem.addConstraint(LinearSystem.createRowLowerThan(linearSystem, createObjectVariable2, createObjectVariable4, margin2 * -1, constraintAnchor2.getConnectionType() != ConnectionType.STRICT));
                linearSystem.addConstraint(LinearSystem.createRowCentering(linearSystem, createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2, false));
            }
        } else if (z3) {
            linearSystem.addGreaterThan(createObjectVariable, createObjectVariable3, margin, 3);
            linearSystem.addLowerThan(createObjectVariable2, createObjectVariable4, margin2 * -1, 3);
            linearSystem.addConstraint(LinearSystem.createRowCentering(linearSystem, createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2, true));
        } else if (!z4) {
            if (i5 == 1) {
                if (i6 > i8) {
                    i8 = i6;
                }
                if (i7 > 0) {
                    if (i7 >= i8) {
                        linearSystem.addLowerThan(createObjectVariable2, createObjectVariable, i7, 3);
                    }
                    linearSystem.addEquality(createObjectVariable2, createObjectVariable, i7, 3);
                    linearSystem.addGreaterThan(createObjectVariable, createObjectVariable3, margin, 2);
                    linearSystem.addLowerThan(createObjectVariable2, createObjectVariable4, -margin2, 2);
                    linearSystem.addCentering(createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2, 4);
                }
                i7 = i8;
                linearSystem.addEquality(createObjectVariable2, createObjectVariable, i7, 3);
                linearSystem.addGreaterThan(createObjectVariable, createObjectVariable3, margin, 2);
                linearSystem.addLowerThan(createObjectVariable2, createObjectVariable4, -margin2, 2);
                linearSystem.addCentering(createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2, 4);
            } else if (i6 == 0 && i7 == 0) {
                linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable, createObjectVariable3, margin));
                linearSystem.addConstraint(linearSystem.createRow().createRowEquals(createObjectVariable2, createObjectVariable4, margin2 * -1));
            } else {
                if (i7 > 0) {
                    linearSystem.addLowerThan(createObjectVariable2, createObjectVariable, i7, 3);
                }
                linearSystem.addGreaterThan(createObjectVariable, createObjectVariable3, margin, 2);
                linearSystem.addLowerThan(createObjectVariable2, createObjectVariable4, -margin2, 2);
                linearSystem.addCentering(createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2, 4);
            }
        }
    }

    public void updateFromSolver(LinearSystem linearSystem, int i) {
        if (i == Integer.MAX_VALUE) {
            setFrame(linearSystem.getObjectVariableValue(this.mLeft), linearSystem.getObjectVariableValue(this.mTop), linearSystem.getObjectVariableValue(this.mRight), linearSystem.getObjectVariableValue(this.mBottom));
        } else if (i == -2) {
            setFrame(this.mSolverLeft, this.mSolverTop, this.mSolverRight, this.mSolverBottom);
        } else {
            if (this.mLeft.mGroup == i) {
                this.mSolverLeft = linearSystem.getObjectVariableValue(this.mLeft);
            }
            if (this.mTop.mGroup == i) {
                this.mSolverTop = linearSystem.getObjectVariableValue(this.mTop);
            }
            if (this.mRight.mGroup == i) {
                this.mSolverRight = linearSystem.getObjectVariableValue(this.mRight);
            }
            if (this.mBottom.mGroup == i) {
                this.mSolverBottom = linearSystem.getObjectVariableValue(this.mBottom);
            }
        }
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        updateFromSolver(linearSystem, Integer.MAX_VALUE);
    }
}
