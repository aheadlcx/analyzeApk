package com.sensetime.stmobile;

public class STFaceAttribute {
    public Attribute[] arrayAttribute;
    public int attribute_count;

    public static class Attribute {
        public String category;
        public String label;
        public float score;
    }
}
