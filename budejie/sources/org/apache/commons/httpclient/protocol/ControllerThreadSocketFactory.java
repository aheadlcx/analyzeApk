package org.apache.commons.httpclient.protocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.util.TimeoutController;
import org.apache.commons.httpclient.util.TimeoutController.TimeoutException;

public final class ControllerThreadSocketFactory {

    public static abstract class SocketTask implements Runnable {
        private IOException exception;
        private Socket socket;

        public abstract void doit() throws IOException;

        static IOException access$000(SocketTask socketTask) {
            return socketTask.exception;
        }

        protected void setSocket(Socket socket) {
            this.socket = socket;
        }

        protected Socket getSocket() {
            return this.socket;
        }

        public void run() {
            try {
                doit();
            } catch (IOException e) {
                this.exception = e;
            }
        }
    }

    /* renamed from: org.apache.commons.httpclient.protocol.ControllerThreadSocketFactory$1 */
    class AnonymousClass1 extends SocketTask {
        private final String val$host;
        private final InetAddress val$localAddress;
        private final int val$localPort;
        private final int val$port;
        private final ProtocolSocketFactory val$socketfactory;

        AnonymousClass1(ProtocolSocketFactory protocolSocketFactory, String str, int i, InetAddress inetAddress, int i2) {
            this.val$socketfactory = protocolSocketFactory;
            this.val$host = str;
            this.val$port = i;
            this.val$localAddress = inetAddress;
            this.val$localPort = i2;
        }

        public void doit() throws IOException {
            setSocket(this.val$socketfactory.createSocket(this.val$host, this.val$port, this.val$localAddress, this.val$localPort));
        }
    }

    private ControllerThreadSocketFactory() {
    }

    public static Socket createSocket(ProtocolSocketFactory protocolSocketFactory, String str, int i, InetAddress inetAddress, int i2, int i3) throws IOException, UnknownHostException, ConnectTimeoutException {
        Runnable anonymousClass1 = new AnonymousClass1(protocolSocketFactory, str, i, inetAddress, i2);
        try {
            TimeoutController.execute(anonymousClass1, (long) i3);
            Socket socket = anonymousClass1.getSocket();
            if (SocketTask.access$000(anonymousClass1) == null) {
                return socket;
            }
            throw SocketTask.access$000(anonymousClass1);
        } catch (TimeoutException e) {
            throw new ConnectTimeoutException(new StringBuffer().append("The host did not accept the connection within timeout of ").append(i3).append(" ms").toString());
        }
    }

    public static Socket createSocket(SocketTask socketTask, int i) throws IOException, UnknownHostException, ConnectTimeoutException {
        try {
            TimeoutController.execute((Runnable) socketTask, (long) i);
            Socket socket = socketTask.getSocket();
            if (SocketTask.access$000(socketTask) == null) {
                return socket;
            }
            throw SocketTask.access$000(socketTask);
        } catch (TimeoutException e) {
            throw new ConnectTimeoutException(new StringBuffer().append("The host did not accept the connection within timeout of ").append(i).append(" ms").toString());
        }
    }
}
