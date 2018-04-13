package com.facebook.stetho.dumpapp;

public interface DumperPlugin {
    void dump(DumperContext dumperContext) throws DumpException;

    String getName();
}
