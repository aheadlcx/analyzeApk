package com.scwang.smartrefresh.layout.constant;

public enum DimensionStatus {
    DefaultUnNotify(false),
    Default(true),
    XmlWrapUnNotify(false),
    XmlWrap(true),
    XmlExactUnNotify(false),
    XmlExact(true),
    XmlLayoutUnNotify(false),
    XmlLayout(true),
    CodeExactUnNotify(false),
    CodeExact(true),
    DeadLockUnNotify(false),
    DeadLock(true);
    
    public final boolean notifyed;

    private DimensionStatus(boolean z) {
        this.notifyed = z;
    }

    public DimensionStatus unNotify() {
        if (!this.notifyed) {
            return this;
        }
        if (values()[ordinal() - 1].notifyed) {
            return DefaultUnNotify;
        }
        return values()[ordinal() - 1];
    }

    public DimensionStatus notifyed() {
        if (this.notifyed) {
            return this;
        }
        return values()[ordinal() + 1];
    }

    public boolean canReplaceWith(DimensionStatus dimensionStatus) {
        return ordinal() < dimensionStatus.ordinal() || ((!this.notifyed || CodeExact == this) && ordinal() == dimensionStatus.ordinal());
    }

    public boolean gteReplaceWith(DimensionStatus dimensionStatus) {
        return ordinal() >= dimensionStatus.ordinal();
    }
}
