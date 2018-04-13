package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alibaba.mtl.log.e.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DimensionSet implements Parcelable {
    public static final Creator<DimensionSet> CREATOR = new Creator<DimensionSet>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return b(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public DimensionSet b(Parcel parcel) {
            return DimensionSet.a(parcel);
        }

        public DimensionSet[] a(int i) {
            return new DimensionSet[i];
        }
    };
    private List<Dimension> c = new ArrayList(3);

    public static DimensionSet create() {
        return new DimensionSet();
    }

    public static DimensionSet create(Collection<String> collection) {
        DimensionSet dimensionSet = new DimensionSet();
        if (collection != null) {
            for (String dimension : collection) {
                dimensionSet.addDimension(new Dimension(dimension));
            }
        }
        return dimensionSet;
    }

    public static DimensionSet create(String[] strArr) {
        DimensionSet dimensionSet = new DimensionSet();
        if (strArr != null) {
            for (String dimension : strArr) {
                dimensionSet.addDimension(new Dimension(dimension));
            }
        }
        return dimensionSet;
    }

    private DimensionSet() {
    }

    public boolean valid(DimensionValueSet dimensionValueSet) {
        if (this.c != null) {
            if (dimensionValueSet == null) {
                return false;
            }
            for (Dimension name : this.c) {
                if (!dimensionValueSet.containValue(name.getName())) {
                    return false;
                }
            }
        }
        return true;
    }

    public DimensionSet addDimension(Dimension dimension) {
        if (!this.c.contains(dimension)) {
            this.c.add(dimension);
        }
        return this;
    }

    public DimensionSet addDimension(String str) {
        return addDimension(new Dimension(str));
    }

    public DimensionSet addDimension(String str, String str2) {
        return addDimension(new Dimension(str, str2));
    }

    public Dimension getDimension(String str) {
        for (Dimension dimension : this.c) {
            if (dimension.getName().equals(str)) {
                return dimension;
            }
        }
        return null;
    }

    public void setConstantValue(DimensionValueSet dimensionValueSet) {
        if (this.c != null && dimensionValueSet != null) {
            for (Dimension dimension : this.c) {
                if (dimension.getConstantValue() != null && dimensionValueSet.getValue(dimension.getName()) == null) {
                    dimensionValueSet.setValue(dimension.getName(), dimension.getConstantValue());
                }
            }
        }
    }

    public List<Dimension> getDimensions() {
        return this.c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.c != null) {
            try {
                Object[] toArray = this.c.toArray();
                Parcelable[] parcelableArr = null;
                if (toArray != null) {
                    Dimension[] dimensionArr = new Dimension[toArray.length];
                    for (int i2 = 0; i2 < toArray.length; i2++) {
                        dimensionArr[i2] = (Dimension) toArray[i2];
                    }
                    parcelableArr = dimensionArr;
                }
                parcel.writeParcelableArray(parcelableArr, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static DimensionSet a(Parcel parcel) {
        DimensionSet create = create();
        try {
            Parcelable[] readParcelableArray = parcel.readParcelableArray(DimensionSet.class.getClassLoader());
            if (readParcelableArray != null) {
                if (create.c == null) {
                    create.c = new ArrayList();
                }
                int i = 0;
                while (i < readParcelableArray.length) {
                    if (readParcelableArray[i] == null || !(readParcelableArray[i] instanceof Dimension)) {
                        i.a("DimensionSet", "parcelables[i]:", readParcelableArray[i]);
                    } else {
                        create.c.add((Dimension) readParcelableArray[i]);
                    }
                    i++;
                }
            }
        } catch (Throwable th) {
            i.a("DimensionSet", "[readFromParcel]", th);
        }
        return create;
    }
}
