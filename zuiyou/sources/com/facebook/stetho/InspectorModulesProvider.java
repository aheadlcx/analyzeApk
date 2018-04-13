package com.facebook.stetho;

import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;

public interface InspectorModulesProvider {
    Iterable<ChromeDevtoolsDomain> get();
}
