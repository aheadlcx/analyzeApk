package com.flurry.android;

import java.io.DataInput;
import java.io.DataOutput;

final class c extends aj {
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    byte a;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private int ae;
    private int af;
    private boolean ag;
    String b;
    long c;
    String d;
    int e;
    int f;
    String g;
    int h;
    int i;
    String j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    private long r;
    private String s;
    private int t;
    private int u;
    private String v;
    private int w;
    private int x;
    private String y;
    private int z;

    c() {
    }

    c(DataInput dataInput) {
        c(dataInput);
    }

    private void c(DataInput dataInput) {
        this.a = dataInput.readByte();
        this.b = dataInput.readUTF();
        this.c = dataInput.readLong();
        this.r = dataInput.readLong();
        this.d = dataInput.readUTF();
        this.e = dataInput.readUnsignedShort();
        this.f = dataInput.readInt();
        this.g = dataInput.readUTF();
        this.h = dataInput.readUnsignedShort();
        this.i = dataInput.readInt();
        this.j = dataInput.readUTF();
        this.k = dataInput.readUnsignedShort();
        this.l = dataInput.readInt();
    }

    final void a(DataInput dataInput) {
        this.s = dataInput.readUTF();
        this.t = dataInput.readUnsignedShort();
        this.u = dataInput.readInt();
        this.v = dataInput.readUTF();
        this.w = dataInput.readUnsignedShort();
        this.x = dataInput.readInt();
        this.y = dataInput.readUTF();
        this.z = dataInput.readUnsignedShort();
        this.A = dataInput.readInt();
        this.B = dataInput.readInt();
        this.C = dataInput.readInt();
        this.m = dataInput.readInt();
        this.n = dataInput.readInt();
        this.o = dataInput.readInt();
        this.p = dataInput.readInt();
        this.D = dataInput.readInt();
        this.E = dataInput.readInt();
        this.F = dataInput.readInt();
        this.G = dataInput.readInt();
        this.H = dataInput.readInt();
        this.I = dataInput.readInt();
        this.J = dataInput.readInt();
        this.K = dataInput.readInt();
        this.q = dataInput.readInt();
        this.L = dataInput.readInt();
        this.M = dataInput.readInt();
        this.N = dataInput.readInt();
        this.O = dataInput.readInt();
        this.P = dataInput.readInt();
        this.Q = dataInput.readInt();
        this.R = dataInput.readInt();
        this.S = dataInput.readInt();
        this.T = dataInput.readInt();
        this.U = dataInput.readInt();
        this.V = dataInput.readInt();
        this.W = dataInput.readInt();
        this.X = dataInput.readInt();
        this.Y = dataInput.readInt();
        this.Z = dataInput.readInt();
        this.aa = dataInput.readInt();
        this.ab = dataInput.readInt();
        this.ac = dataInput.readInt();
        this.ad = dataInput.readInt();
        this.ae = dataInput.readInt();
        this.af = dataInput.readInt();
        this.ag = true;
    }

    final void b(DataInput dataInput) {
        c(dataInput);
        this.ag = dataInput.readBoolean();
        if (this.ag) {
            a(dataInput);
        }
    }

    final void a(DataOutput dataOutput) {
        dataOutput.writeByte(this.a);
        dataOutput.writeUTF(this.b);
        dataOutput.writeLong(this.c);
        dataOutput.writeLong(this.r);
        dataOutput.writeUTF(this.d);
        dataOutput.writeShort(this.e);
        dataOutput.writeInt(this.f);
        dataOutput.writeUTF(this.g);
        dataOutput.writeShort(this.h);
        dataOutput.writeInt(this.i);
        dataOutput.writeUTF(this.j);
        dataOutput.writeShort(this.k);
        dataOutput.writeInt(this.l);
        dataOutput.writeBoolean(this.ag);
        if (this.ag) {
            dataOutput.writeUTF(this.s);
            dataOutput.writeShort(this.t);
            dataOutput.writeInt(this.u);
            dataOutput.writeUTF(this.v);
            dataOutput.writeShort(this.w);
            dataOutput.writeInt(this.x);
            dataOutput.writeUTF(this.y);
            dataOutput.writeShort(this.z);
            dataOutput.writeInt(this.A);
            dataOutput.writeInt(this.B);
            dataOutput.writeInt(this.C);
            dataOutput.writeInt(this.m);
            dataOutput.writeInt(this.n);
            dataOutput.writeInt(this.o);
            dataOutput.writeInt(this.p);
            dataOutput.writeInt(this.D);
            dataOutput.writeInt(this.E);
            dataOutput.writeInt(this.F);
            dataOutput.writeInt(this.G);
            dataOutput.writeInt(this.H);
            dataOutput.writeInt(this.I);
            dataOutput.writeInt(this.J);
            dataOutput.writeInt(this.K);
            dataOutput.writeInt(this.q);
            dataOutput.writeInt(this.L);
            dataOutput.writeInt(this.M);
            dataOutput.writeInt(this.N);
            dataOutput.writeInt(this.O);
            dataOutput.writeInt(this.P);
            dataOutput.writeInt(this.Q);
            dataOutput.writeInt(this.R);
            dataOutput.writeInt(this.S);
            dataOutput.writeInt(this.T);
            dataOutput.writeInt(this.U);
            dataOutput.writeInt(this.V);
            dataOutput.writeInt(this.W);
            dataOutput.writeInt(this.X);
            dataOutput.writeInt(this.Y);
            dataOutput.writeInt(this.Z);
            dataOutput.writeInt(this.aa);
            dataOutput.writeInt(this.ab);
            dataOutput.writeInt(this.ac);
            dataOutput.writeInt(this.ad);
            dataOutput.writeInt(this.ae);
            dataOutput.writeInt(this.af);
        }
    }
}
