package org.mozilla.javascript.regexp;

import java.io.Serializable;

class RECompiled implements Serializable {
    static final long serialVersionUID = -6144956577595844213L;
    int anchorCh = -1;
    int classCount;
    RECharSet[] classList;
    int flags;
    int parenCount;
    byte[] program;
    final char[] source;

    RECompiled(String str) {
        this.source = str.toCharArray();
    }
}
