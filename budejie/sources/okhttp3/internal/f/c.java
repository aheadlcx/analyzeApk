package okhttp3.internal.f;

import com.budejie.www.R$styleable;
import javax.security.auth.x500.X500Principal;

final class c {
    private final String a;
    private final int b = this.a.length();
    private int c;
    private int d;
    private int e;
    private int f;
    private char[] g;

    public c(X500Principal x500Principal) {
        this.a = x500Principal.getName("RFC2253");
    }

    private String a() {
        while (this.c < this.b && this.g[this.c] == ' ') {
            this.c++;
        }
        if (this.c == this.b) {
            return null;
        }
        this.d = this.c;
        this.c++;
        while (this.c < this.b && this.g[this.c] != '=' && this.g[this.c] != ' ') {
            this.c++;
        }
        if (this.c >= this.b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.a);
        }
        this.e = this.c;
        if (this.g[this.c] == ' ') {
            while (this.c < this.b && this.g[this.c] != '=' && this.g[this.c] == ' ') {
                this.c++;
            }
            if (this.g[this.c] != '=' || this.c == this.b) {
                throw new IllegalStateException("Unexpected end of DN: " + this.a);
            }
        }
        this.c++;
        while (this.c < this.b && this.g[this.c] == ' ') {
            this.c++;
        }
        if (this.e - this.d > 4 && this.g[this.d + 3] == '.' && ((this.g[this.d] == 'O' || this.g[this.d] == 'o') && ((this.g[this.d + 1] == 'I' || this.g[this.d + 1] == 'i') && (this.g[this.d + 2] == 'D' || this.g[this.d + 2] == 'd')))) {
            this.d += 4;
        }
        return new String(this.g, this.d, this.e - this.d);
    }

    private String b() {
        this.c++;
        this.d = this.c;
        this.e = this.d;
        while (this.c != this.b) {
            if (this.g[this.c] == '\"') {
                this.c++;
                while (this.c < this.b && this.g[this.c] == ' ') {
                    this.c++;
                }
                return new String(this.g, this.d, this.e - this.d);
            }
            if (this.g[this.c] == '\\') {
                this.g[this.e] = e();
            } else {
                this.g[this.e] = this.g[this.c];
            }
            this.c++;
            this.e++;
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.a);
    }

    private String c() {
        if (this.c + 4 >= this.b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.a);
        }
        int i;
        this.d = this.c;
        this.c++;
        while (this.c != this.b && this.g[this.c] != '+' && this.g[this.c] != ',' && this.g[this.c] != ';') {
            int i2;
            if (this.g[this.c] == ' ') {
                this.e = this.c;
                this.c++;
                while (this.c < this.b && this.g[this.c] == ' ') {
                    this.c++;
                }
                i = this.e - this.d;
                if (i >= 5 || (i & 1) == 0) {
                    throw new IllegalStateException("Unexpected end of DN: " + this.a);
                }
                byte[] bArr = new byte[(i / 2)];
                int i3 = this.d + 1;
                for (i2 = 0; i2 < bArr.length; i2++) {
                    bArr[i2] = (byte) a(i3);
                    i3 += 2;
                }
                return new String(this.g, this.d, i);
            }
            if (this.g[this.c] >= 'A' && this.g[this.c] <= 'F') {
                char[] cArr = this.g;
                i2 = this.c;
                cArr[i2] = (char) (cArr[i2] + 32);
            }
            this.c++;
        }
        this.e = this.c;
        i = this.e - this.d;
        if (i >= 5) {
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.a);
    }

    private String d() {
        this.d = this.c;
        this.e = this.c;
        while (this.c < this.b) {
            char[] cArr;
            int i;
            switch (this.g[this.c]) {
                case ' ':
                    this.f = this.e;
                    this.c++;
                    cArr = this.g;
                    i = this.e;
                    this.e = i + 1;
                    cArr[i] = ' ';
                    while (this.c < this.b && this.g[this.c] == ' ') {
                        cArr = this.g;
                        i = this.e;
                        this.e = i + 1;
                        cArr[i] = ' ';
                        this.c++;
                    }
                    if (this.c != this.b && this.g[this.c] != ',' && this.g[this.c] != '+' && this.g[this.c] != ';') {
                        break;
                    }
                    return new String(this.g, this.d, this.f - this.d);
                    break;
                case '+':
                case ',':
                case ';':
                    return new String(this.g, this.d, this.e - this.d);
                case '\\':
                    cArr = this.g;
                    i = this.e;
                    this.e = i + 1;
                    cArr[i] = e();
                    this.c++;
                    break;
                default:
                    cArr = this.g;
                    i = this.e;
                    this.e = i + 1;
                    cArr[i] = this.g[this.c];
                    this.c++;
                    break;
            }
        }
        return new String(this.g, this.d, this.e - this.d);
    }

    private char e() {
        this.c++;
        if (this.c == this.b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.a);
        }
        switch (this.g[this.c]) {
            case ' ':
            case '\"':
            case '#':
            case '%':
            case '*':
            case '+':
            case ',':
            case ';':
            case '<':
            case '=':
            case '>':
            case '\\':
            case '_':
                return this.g[this.c];
            default:
                return f();
        }
    }

    private char f() {
        int a = a(this.c);
        this.c++;
        if (a < 128) {
            return (char) a;
        }
        if (a < 192 || a > R$styleable.Theme_Custom_top_suiji_btn_bg) {
            return '?';
        }
        int i;
        if (a <= 223) {
            i = 1;
            a &= 31;
        } else if (a <= R$styleable.Theme_Custom_shape_cmt_like3_bg) {
            i = 2;
            a &= 15;
        } else {
            i = 3;
            a &= 7;
        }
        int i2 = a;
        for (a = 0; a < i; a++) {
            this.c++;
            if (this.c == this.b || this.g[this.c] != '\\') {
                return '?';
            }
            this.c++;
            int a2 = a(this.c);
            this.c++;
            if ((a2 & 192) != 128) {
                return '?';
            }
            i2 = (i2 << 6) + (a2 & 63);
        }
        return (char) i2;
    }

    private int a(int i) {
        if (i + 1 >= this.b) {
            throw new IllegalStateException("Malformed DN: " + this.a);
        }
        int i2;
        int i3;
        char c = this.g[i];
        if (c >= '0' && c <= '9') {
            i2 = c - 48;
        } else if (c >= 'a' && c <= 'f') {
            i2 = c - 87;
        } else if (c < 'A' || c > 'F') {
            throw new IllegalStateException("Malformed DN: " + this.a);
        } else {
            i2 = c - 55;
        }
        char c2 = this.g[i + 1];
        if (c2 >= '0' && c2 <= '9') {
            i3 = c2 - 48;
        } else if (c2 >= 'a' && c2 <= 'f') {
            i3 = c2 - 87;
        } else if (c2 < 'A' || c2 > 'F') {
            throw new IllegalStateException("Malformed DN: " + this.a);
        } else {
            i3 = c2 - 55;
        }
        return (i2 << 4) + i3;
    }

    public String a(String str) {
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = this.a.toCharArray();
        String a = a();
        if (a == null) {
            return null;
        }
        do {
            String str2 = "";
            if (this.c == this.b) {
                return null;
            }
            switch (this.g[this.c]) {
                case '\"':
                    str2 = b();
                    break;
                case '#':
                    str2 = c();
                    break;
                case '+':
                case ',':
                case ';':
                    break;
                default:
                    str2 = d();
                    break;
            }
            if (str.equalsIgnoreCase(a)) {
                return str2;
            }
            if (this.c >= this.b) {
                return null;
            }
            if (this.g[this.c] == ',' || this.g[this.c] == ';' || this.g[this.c] == '+') {
                this.c++;
                a = a();
            } else {
                throw new IllegalStateException("Malformed DN: " + this.a);
            }
        } while (a != null);
        throw new IllegalStateException("Malformed DN: " + this.a);
    }
}
