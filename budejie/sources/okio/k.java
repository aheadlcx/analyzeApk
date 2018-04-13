package okio;

import com.alipay.sdk.data.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class k {
    static final Logger a = Logger.getLogger(k.class.getName());

    private k() {
    }

    public static e a(r rVar) {
        return new m(rVar);
    }

    public static d a(q qVar) {
        return new l(qVar);
    }

    public static q a(OutputStream outputStream) {
        return a(outputStream, new s());
    }

    private static q a(final OutputStream outputStream, final s sVar) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        } else if (sVar != null) {
            return new q() {
                public void a_(c cVar, long j) throws IOException {
                    t.a(cVar.b, 0, j);
                    while (j > 0) {
                        sVar.g();
                        n nVar = cVar.a;
                        int min = (int) Math.min(j, (long) (nVar.c - nVar.b));
                        outputStream.write(nVar.a, nVar.b, min);
                        nVar.b += min;
                        j -= (long) min;
                        cVar.b -= (long) min;
                        if (nVar.b == nVar.c) {
                            cVar.a = nVar.a();
                            o.a(nVar);
                        }
                    }
                }

                public void flush() throws IOException {
                    outputStream.flush();
                }

                public void close() throws IOException {
                    outputStream.close();
                }

                public s a() {
                    return sVar;
                }

                public String toString() {
                    return "sink(" + outputStream + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static q a(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        s c = c(socket);
        return c.a(a(socket.getOutputStream(), c));
    }

    public static r a(InputStream inputStream) {
        return a(inputStream, new s());
    }

    private static r a(final InputStream inputStream, final s sVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        } else if (sVar != null) {
            return new r() {
                public long a(c cVar, long j) throws IOException {
                    if (j < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    } else if (j == 0) {
                        return 0;
                    } else {
                        try {
                            sVar.g();
                            n e = cVar.e(1);
                            int read = inputStream.read(e.a, e.c, (int) Math.min(j, (long) (8192 - e.c)));
                            if (read == -1) {
                                return -1;
                            }
                            e.c += read;
                            cVar.b += (long) read;
                            return (long) read;
                        } catch (AssertionError e2) {
                            if (k.a(e2)) {
                                throw new IOException(e2);
                            }
                            throw e2;
                        }
                    }
                }

                public void close() throws IOException {
                    inputStream.close();
                }

                public s a() {
                    return sVar;
                }

                public String toString() {
                    return "source(" + inputStream + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static r a(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static q b(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static q c(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static q a() {
        return new q() {
            public void a_(c cVar, long j) throws IOException {
                cVar.g(j);
            }

            public void flush() throws IOException {
            }

            public s a() {
                return s.b;
            }

            public void close() throws IOException {
            }
        };
    }

    public static r b(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        s c = c(socket);
        return c.a(a(socket.getInputStream(), c));
    }

    private static a c(final Socket socket) {
        return new a() {
            protected IOException a(IOException iOException) {
                IOException socketTimeoutException = new SocketTimeoutException(a.f);
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            protected void a() {
                try {
                    socket.close();
                } catch (Throwable e) {
                    k.a.log(Level.WARNING, "Failed to close timed out socket " + socket, e);
                } catch (AssertionError e2) {
                    if (k.a(e2)) {
                        k.a.log(Level.WARNING, "Failed to close timed out socket " + socket, e2);
                        return;
                    }
                    throw e2;
                }
            }
        };
    }

    static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
