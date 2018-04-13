package org.mozilla.classfile;

final class ExceptionTableEntry {
    short itsCatchType;
    int itsEndLabel;
    int itsHandlerLabel;
    int itsStartLabel;

    ExceptionTableEntry(int i, int i2, int i3, short s) {
        this.itsStartLabel = i;
        this.itsEndLabel = i2;
        this.itsHandlerLabel = i3;
        this.itsCatchType = s;
    }
}
