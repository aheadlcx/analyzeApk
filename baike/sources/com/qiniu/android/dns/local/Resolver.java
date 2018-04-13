package com.qiniu.android.dns.local;

import com.qiniu.android.dns.DnsException;
import com.qiniu.android.dns.Domain;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.Record;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public final class Resolver implements IResolver {
    private static final Random b = new Random();
    final InetAddress a;
    private final int c;

    public Resolver(InetAddress inetAddress) {
        this(inetAddress, 10);
    }

    public Resolver(InetAddress inetAddress, int i) {
        this.a = inetAddress;
        this.c = i;
    }

    public Record[] resolve(Domain domain, NetworkInfo networkInfo) throws IOException {
        int nextInt;
        synchronized (b) {
            nextInt = b.nextInt() & 255;
        }
        byte[] a = a(DnsMessage.buildQuery(domain.domain, nextInt));
        if (a != null) {
            return DnsMessage.parseResponse(a, nextInt, domain.domain);
        }
        throw new DnsException(domain.domain, "cant get answer");
    }

    private byte[] a(byte[] bArr) throws IOException {
        Throwable th;
        DatagramSocket datagramSocket;
        try {
            datagramSocket = new DatagramSocket();
            try {
                DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, this.a, 53);
                datagramSocket.setSoTimeout(this.c * 1000);
                datagramSocket.send(datagramPacket);
                datagramPacket = new DatagramPacket(new byte[1500], 1500);
                datagramSocket.receive(datagramPacket);
                byte[] data = datagramPacket.getData();
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
                return data;
            } catch (Throwable th2) {
                th = th2;
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            datagramSocket = null;
            if (datagramSocket != null) {
                datagramSocket.close();
            }
            throw th;
        }
    }
}
