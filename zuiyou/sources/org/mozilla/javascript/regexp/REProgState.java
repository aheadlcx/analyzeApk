package org.mozilla.javascript.regexp;

class REProgState {
    final REBackTrackData backTrack;
    final int continuationOp;
    final int continuationPc;
    final int index;
    final int max;
    final int min;
    final REProgState previous;

    REProgState(REProgState rEProgState, int i, int i2, int i3, REBackTrackData rEBackTrackData, int i4, int i5) {
        this.previous = rEProgState;
        this.min = i;
        this.max = i2;
        this.index = i3;
        this.continuationOp = i4;
        this.continuationPc = i5;
        this.backTrack = rEBackTrackData;
    }
}
