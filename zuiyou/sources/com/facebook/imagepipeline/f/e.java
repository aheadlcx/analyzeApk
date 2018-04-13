package com.facebook.imagepipeline.f;

import com.facebook.common.internal.b;
import com.facebook.common.internal.g;
import com.facebook.common.internal.k;
import com.facebook.common.memory.a;
import com.facebook.common.memory.f;
import com.facebook.common.util.c;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.IOException;
import java.io.InputStream;

public class e {
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private boolean g;
    private final a h;

    public e(a aVar) {
        this.h = (a) g.a((Object) aVar);
    }

    public boolean a(com.facebook.imagepipeline.g.e eVar) {
        if (this.a == 6) {
            return false;
        }
        if (eVar.k() <= this.c) {
            return false;
        }
        InputStream fVar = new f(eVar.d(), (byte[]) this.h.get(16384), this.h);
        boolean a;
        try {
            c.a(fVar, (long) this.c);
            a = a(fVar);
            return a;
        } catch (IOException e) {
            a = e;
            k.b(a);
            return false;
        } finally {
            b.a(fVar);
        }
    }

    private boolean a(InputStream inputStream) {
        int i = this.e;
        while (this.a != 6) {
            int read = inputStream.read();
            if (read != -1) {
                this.c++;
                switch (this.a) {
                    case 0:
                        if (read != 255) {
                            this.a = 6;
                            break;
                        }
                        try {
                            this.a = 1;
                            break;
                        } catch (Throwable e) {
                            k.b(e);
                            break;
                        }
                    case 1:
                        if (read != Opcodes.ADD_INT_LIT8) {
                            this.a = 6;
                            break;
                        }
                        this.a = 2;
                        break;
                    case 2:
                        if (read == 255) {
                            this.a = 3;
                            break;
                        }
                        break;
                    case 3:
                        if (read != 255) {
                            if (read != 0) {
                                if (read != Opcodes.RSUB_INT_LIT8) {
                                    if (read == Opcodes.MUL_INT_LIT8) {
                                        b(this.c - 2);
                                    }
                                    if (!a(read)) {
                                        this.a = 2;
                                        break;
                                    }
                                    this.a = 4;
                                    break;
                                }
                                this.g = true;
                                b(this.c - 2);
                                this.a = 2;
                                break;
                            }
                            this.a = 2;
                            break;
                        }
                        this.a = 3;
                        break;
                    case 4:
                        this.a = 5;
                        break;
                    case 5:
                        int i2 = ((this.b << 8) + read) - 2;
                        c.a(inputStream, (long) i2);
                        this.c = i2 + this.c;
                        this.a = 2;
                        break;
                    default:
                        g.b(false);
                        break;
                }
                this.b = read;
            } else if (this.a != 6 || this.e == i) {
                return false;
            } else {
                return true;
            }
        }
        if (this.a != 6) {
        }
        return false;
    }

    private static boolean a(int i) {
        boolean z = true;
        if (i == 1) {
            return false;
        }
        if (i >= 208 && i <= Opcodes.XOR_INT_LIT16) {
            return false;
        }
        if (i == Opcodes.RSUB_INT_LIT8 || i == Opcodes.ADD_INT_LIT8) {
            z = false;
        }
        return z;
    }

    private void b(int i) {
        if (this.d > 0) {
            this.f = i;
        }
        int i2 = this.d;
        this.d = i2 + 1;
        this.e = i2;
    }

    public int a() {
        return this.f;
    }

    public int b() {
        return this.e;
    }

    public boolean c() {
        return this.g;
    }
}
