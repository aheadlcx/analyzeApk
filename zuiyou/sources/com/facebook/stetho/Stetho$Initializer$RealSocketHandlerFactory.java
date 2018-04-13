package com.facebook.stetho;

import com.facebook.stetho.dumpapp.DumpappHttpSocketLikeHandler;
import com.facebook.stetho.dumpapp.DumpappSocketLikeHandler;
import com.facebook.stetho.dumpapp.Dumper;
import com.facebook.stetho.inspector.DevtoolsSocketHandler;
import com.facebook.stetho.server.ProtocolDetectingSocketHandler;
import com.facebook.stetho.server.ProtocolDetectingSocketHandler.AlwaysMatchMatcher;
import com.facebook.stetho.server.ProtocolDetectingSocketHandler.ExactMagicMatcher;
import com.facebook.stetho.server.SocketHandler;
import com.facebook.stetho.server.SocketHandlerFactory;
import com.facebook.stetho.server.SocketLikeHandler;

class Stetho$Initializer$RealSocketHandlerFactory implements SocketHandlerFactory {
    final /* synthetic */ Stetho$Initializer this$0;

    private Stetho$Initializer$RealSocketHandlerFactory(Stetho$Initializer stetho$Initializer) {
        this.this$0 = stetho$Initializer;
    }

    public SocketHandler create() {
        SocketHandler protocolDetectingSocketHandler = new ProtocolDetectingSocketHandler(Stetho$Initializer.access$100(this.this$0));
        Iterable dumperPlugins = this.this$0.getDumperPlugins();
        if (dumperPlugins != null) {
            Dumper dumper = new Dumper(dumperPlugins);
            protocolDetectingSocketHandler.addHandler(new ExactMagicMatcher(DumpappSocketLikeHandler.PROTOCOL_MAGIC), new DumpappSocketLikeHandler(dumper));
            SocketLikeHandler dumpappHttpSocketLikeHandler = new DumpappHttpSocketLikeHandler(dumper);
            protocolDetectingSocketHandler.addHandler(new ExactMagicMatcher("GET /dumpapp".getBytes()), dumpappHttpSocketLikeHandler);
            protocolDetectingSocketHandler.addHandler(new ExactMagicMatcher("POST /dumpapp".getBytes()), dumpappHttpSocketLikeHandler);
        }
        dumperPlugins = this.this$0.getInspectorModules();
        if (dumperPlugins != null) {
            protocolDetectingSocketHandler.addHandler(new AlwaysMatchMatcher(), new DevtoolsSocketHandler(Stetho$Initializer.access$100(this.this$0), dumperPlugins));
        }
        return protocolDetectingSocketHandler;
    }
}
