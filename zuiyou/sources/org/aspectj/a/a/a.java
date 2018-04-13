package org.aspectj.a.a;

import org.aspectj.lang.b;

public abstract class a {
    protected int bitflags = 1048576;
    protected Object[] preInitializationState;
    protected Object[] state;

    public abstract Object run(Object[] objArr) throws Throwable;

    public a(Object[] objArr) {
        this.state = objArr;
    }

    public int getFlags() {
        return this.bitflags;
    }

    public Object[] getState() {
        return this.state;
    }

    public Object[] getPreInitializationState() {
        return this.preInitializationState;
    }

    public b linkClosureAndJoinPoint() {
        b bVar = (b) this.state[this.state.length - 1];
        bVar.a(this);
        return bVar;
    }

    public b linkClosureAndJoinPoint(int i) {
        b bVar = (b) this.state[this.state.length - 1];
        bVar.a(this);
        this.bitflags = i;
        return bVar;
    }
}
