package mtopsdk.mtop.unit;

import java.io.Serializable;
import mtopsdk.mtop.domain.b;

public class UserUnit implements Serializable, b {
    private static final long serialVersionUID = 2326301535071107548L;
    public String unitPrefix;
    public UnitType unitType = UnitType.CENTER;
    public String userId;

    public enum UnitType {
        CENTER("center"),
        UNIT("unit");
        
        private String unitType;

        private UnitType(String str) {
            this.unitType = str;
        }

        public final String getUnitType() {
            return this.unitType;
        }

        public final void setUnitType(String str) {
            this.unitType = str;
        }
    }

    public UserUnit(String str, UnitType unitType, String str2) {
        this.userId = str;
        if (unitType != null) {
            this.unitType = unitType;
        }
        this.unitPrefix = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserUnit userUnit = (UserUnit) obj;
        if (this.unitPrefix == null) {
            if (userUnit.unitPrefix != null) {
                return false;
            }
        } else if (!this.unitPrefix.equals(userUnit.unitPrefix)) {
            return false;
        }
        return this.unitType != userUnit.unitType ? false : this.userId == null ? userUnit.userId == null : this.userId.equals(userUnit.userId);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.unitType == null ? 0 : this.unitType.hashCode()) + (((this.unitPrefix == null ? 0 : this.unitPrefix.hashCode()) + 31) * 31)) * 31;
        if (this.userId != null) {
            i = this.userId.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(48);
        stringBuilder.append("UserUnit [userId=").append(this.userId);
        stringBuilder.append(",unitType=").append(this.unitType);
        stringBuilder.append(",unitPrefix=").append(this.unitPrefix);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
