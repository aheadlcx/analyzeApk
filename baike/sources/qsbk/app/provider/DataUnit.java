package qsbk.app.provider;

import qsbk.app.utils.DataParse.StampContent;

public class DataUnit {
    private int a;
    private String b;
    private String c;
    private String d;
    private DataUnit e;
    private float f;
    private StampContent g;

    public DataUnit(int i, String str, String str2, String str3, DataUnit dataUnit, float f, StampContent stampContent) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = dataUnit;
        this.f = f;
        this.g = stampContent;
    }

    public int getIndex() {
        return this.a;
    }

    public void setIndex(int i) {
        this.a = i;
    }

    public String getContent() {
        return this.b;
    }

    public void setContent(String str) {
        this.b = str;
    }

    public String getTag() {
        return this.d;
    }

    public void setTag(String str) {
        this.d = str;
    }

    public StampContent getEvaluate() {
        return this.g;
    }

    public void setEvaluate(StampContent stampContent) {
        this.g = stampContent;
    }

    public String getImage() {
        return this.c;
    }

    public void setImage(String str) {
        this.c = str;
    }

    public DataUnit getSimilar_art() {
        return this.e;
    }

    public void setSimilar_art(DataUnit dataUnit) {
        this.e = dataUnit;
    }

    public float getSimilar_value() {
        return this.f;
    }

    public void setSimilar_value(float f) {
        this.f = f;
    }

    public boolean hasEvaluate() {
        if (this.g == null || this.g.equals(StampContent.NONE)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.a + 31;
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
        if (this.a != ((DataUnit) obj).a) {
            return false;
        }
        return true;
    }
}
