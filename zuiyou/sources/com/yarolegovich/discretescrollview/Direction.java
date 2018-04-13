package com.yarolegovich.discretescrollview;

enum Direction {
    START {
        public int applyTo(int i) {
            return i * -1;
        }

        public boolean sameAs(int i) {
            return i < 0;
        }
    },
    END {
        public int applyTo(int i) {
            return i;
        }

        public boolean sameAs(int i) {
            return i > 0;
        }
    };

    public abstract int applyTo(int i);

    public abstract boolean sameAs(int i);

    public static Direction fromDelta(int i) {
        return i > 0 ? END : START;
    }
}
