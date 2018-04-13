package com.linkedin.urls.detection;

public class DomainNameReader {
    private StringBuilder a;
    private String b;
    private UrlDetectorOptions c;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private final b l;
    private final a m;

    public enum ReaderNextState {
        InvalidDomainName,
        ValidDomainName,
        ReadFragment,
        ReadPath,
        ReadPort,
        ReadQueryString
    }

    interface a {
        void a(char c);
    }

    public DomainNameReader(b bVar, StringBuilder stringBuilder, String str, UrlDetectorOptions urlDetectorOptions, a aVar) {
        this.a = stringBuilder;
        this.b = str;
        this.l = bVar;
        this.c = urlDetectorOptions;
        this.m = aVar;
    }

    private ReaderNextState b() {
        boolean z = true;
        if (this.b == null) {
            this.g = this.a.length();
        } else if (this.b.length() == 1 && a.f(this.b.charAt(0))) {
            return ReaderNextState.InvalidDomainName;
        } else {
            if (this.b.length() == 3 && this.b.equalsIgnoreCase("%2e")) {
                return ReaderNextState.InvalidDomainName;
            }
            this.g = this.a.length() - this.b.length();
            this.h = true;
            char[] toCharArray = this.b.toCharArray();
            int length = toCharArray.length;
            boolean z2 = length > 2 && toCharArray[0] == '0' && (toCharArray[1] == 'x' || toCharArray[1] == 'X');
            if (!z2) {
                z = false;
            }
            int i = z;
            int i2 = 0;
            z = false;
            while (i < length && !r0) {
                char c = toCharArray[i];
                this.e++;
                this.f = this.e;
                if (this.e > 64) {
                    return ReaderNextState.InvalidDomainName;
                }
                if (a.f(c)) {
                    this.d++;
                    this.e = 0;
                } else if (c == '[') {
                    this.i = true;
                    this.h = false;
                } else if (c == '%' && i + 2 < length && a.a(toCharArray[i + 1]) && a.a(toCharArray[i + 2])) {
                    if (toCharArray[i + 1] == '2' && toCharArray[i + 2] == 'e') {
                        this.d++;
                        this.e = 0;
                    } else {
                        this.h = false;
                    }
                    i += 2;
                } else if (z2) {
                    if (!a.a(c)) {
                        this.h = false;
                        i--;
                        z2 = false;
                    }
                } else if (a.b(c) || c == '-' || c >= 'À') {
                    this.h = false;
                } else if (!(a.c(c) || this.c.hasFlag(UrlDetectorOptions.ALLOW_SINGLE_LEVEL_DOMAIN))) {
                    int i3 = i + 1;
                    this.e = 0;
                    this.f = 0;
                    this.h = true;
                    this.d = 0;
                    i2 = i3;
                    z = true;
                }
                i++;
            }
            if (i2 > 0) {
                if (i2 < this.b.length()) {
                    this.a.replace(0, this.a.length(), this.b.substring(i2));
                    this.g = 0;
                }
                if (i2 >= this.b.length() || this.a.toString().equals(".")) {
                    return ReaderNextState.InvalidDomainName;
                }
            }
        }
        return ReaderNextState.ValidDomainName;
    }

    public ReaderNextState a() {
        if (b() == ReaderNextState.InvalidDomainName) {
            return ReaderNextState.InvalidDomainName;
        }
        boolean z = false;
        while (!z && !this.l.b()) {
            char a = this.l.a();
            if (a == '/') {
                return a(ReaderNextState.ReadPath, Character.valueOf(a));
            }
            if (a == ':' && (!this.i || this.j)) {
                return a(ReaderNextState.ReadPort, Character.valueOf(a));
            }
            if (a == '?') {
                return a(ReaderNextState.ReadQueryString, Character.valueOf(a));
            }
            if (a == '#') {
                return a(ReaderNextState.ReadFragment, Character.valueOf(a));
            }
            if (a.f(a) || (a == '%' && this.l.c(2) && this.l.a(2).equalsIgnoreCase("2e"))) {
                if (this.e < 1) {
                    z = true;
                } else {
                    this.a.append(a);
                    if (!a.f(a)) {
                        this.a.append(this.l.a());
                        this.a.append(this.l.a());
                    }
                    if (!this.k) {
                        this.d++;
                        this.e = 0;
                    }
                    if (this.e >= 64) {
                        return ReaderNextState.InvalidDomainName;
                    }
                }
            } else if (this.i && ((a.a(a) || a == ':' || a == '[' || a == ']' || a == '%') && !this.j)) {
                switch (a) {
                    case '%':
                        this.k = true;
                        break;
                    case ':':
                        this.e = 0;
                        break;
                    case '[':
                        this.l.d();
                        return ReaderNextState.InvalidDomainName;
                    case ']':
                        this.j = true;
                        this.k = false;
                        break;
                    default:
                        this.e++;
                        break;
                }
                this.h = false;
                this.a.append(a);
            } else if (a.d(a) || a == '-' || a >= 'À') {
                if (this.j) {
                    this.l.d();
                    z = true;
                } else {
                    if (!(a == 'x' || a == 'X' || a.c(a))) {
                        this.h = false;
                    }
                    this.a.append(a);
                    this.e++;
                    this.f = this.e;
                }
            } else if (a == '[' && !this.i) {
                this.i = true;
                this.h = false;
                this.a.append(a);
            } else if (a == '[' && this.j) {
                this.l.d();
                z = true;
            } else if (a == '%' && this.l.c(2) && a.a(this.l.b(0)) && a.a(this.l.b(1))) {
                this.a.append(a);
                this.a.append(this.l.a());
                this.a.append(this.l.a());
                this.e += 3;
                this.f = this.e;
            } else {
                this.m.a(a);
                z = true;
            }
        }
        return a(ReaderNextState.ValidDomainName, null);
    }

    private ReaderNextState a(ReaderNextState readerNextState, Character ch) {
        int i = 3;
        int i2 = 0;
        if (this.a.length() <= 3 || !this.a.substring(this.a.length() - 3).equalsIgnoreCase("%2e")) {
            i = 1;
        }
        int length = this.a.length() - this.g;
        if (this.e <= 0) {
            i = 0;
        }
        length += i;
        int i3 = this.d;
        if (this.e > 0) {
            i = 1;
        } else {
            i = 0;
        }
        i += i3;
        if (length < 255 && i <= 127) {
            if (this.h) {
                i2 = a(this.a.substring(this.g).toLowerCase());
            } else if (this.i) {
                i2 = b(this.a.substring(this.g).toLowerCase());
            } else if ((this.e > 0 && this.d >= 1) || ((this.d >= 2 && this.e == 0) || (this.c.hasFlag(UrlDetectorOptions.ALLOW_SINGLE_LEVEL_DOMAIN) && this.d == 0))) {
                i = this.a.length() - this.f;
                if (this.e == 0) {
                    i--;
                }
                i = Math.max(i, 0);
                if (this.a.substring(i, Math.min(4, this.a.length() - i) + i).equalsIgnoreCase("xn--") || (this.f >= 2 && this.f <= 22)) {
                    i2 = 1;
                }
            }
        }
        if (i2 == 0) {
            this.l.d();
            return ReaderNextState.InvalidDomainName;
        } else if (ch == null) {
            return readerNextState;
        } else {
            this.a.append(ch);
            return readerNextState;
        }
    }

    private boolean a(String str) {
        boolean z = true;
        if (str.length() <= 0) {
            return false;
        }
        if (this.d == 0) {
            try {
                long parseLong;
                if (str.length() > 2 && str.charAt(0) == '0' && str.charAt(1) == 'x') {
                    parseLong = Long.parseLong(str.substring(2), 16);
                } else if (str.charAt(0) == '0') {
                    parseLong = Long.parseLong(str.substring(1), 8);
                } else {
                    parseLong = Long.parseLong(str);
                }
                if (parseLong > 4294967295L || parseLong < 16843008) {
                    z = false;
                }
                return z;
            } catch (NumberFormatException e) {
                return false;
            }
        } else if (this.d != 3) {
            return false;
        } else {
            String[] a = a.a(str);
            boolean z2 = true;
            for (int i = 0; i < a.length && z2; i++) {
                String str2 = a[i];
                if (str2.length() > 0) {
                    int i2;
                    Integer valueOf;
                    if (str2.length() > 2 && str2.charAt(0) == '0' && str2.charAt(1) == 'x') {
                        str2 = str2.substring(2);
                        i2 = 16;
                    } else if (str2.charAt(0) == '0') {
                        str2 = str2.substring(1);
                        i2 = 8;
                    } else {
                        i2 = 10;
                    }
                    if (str2.length() == 0) {
                        valueOf = Integer.valueOf(0);
                    } else {
                        try {
                            valueOf = Integer.valueOf(Integer.parseInt(str2, i2));
                        } catch (NumberFormatException e2) {
                            return false;
                        }
                    }
                    if (valueOf.intValue() < 0 || valueOf.intValue() > 255) {
                        z2 = false;
                    }
                } else {
                    z2 = false;
                }
            }
            return z2;
        }
    }

    private boolean b(String str) {
        char[] toCharArray = str.toCharArray();
        if (toCharArray.length < 3 || toCharArray[toCharArray.length - 1] != ']' || toCharArray[0] != '[' || (toCharArray[1] == ':' && toCharArray[2] != ':')) {
            return false;
        }
        int i = 1;
        int i2 = 0;
        char c = '\u0000';
        StringBuilder stringBuilder = new StringBuilder();
        Boolean valueOf = Boolean.valueOf(true);
        Object obj = null;
        Object obj2 = null;
        int i3 = 0;
        while (i3 < toCharArray.length) {
            switch (toCharArray[i3]) {
                case '%':
                case ']':
                    if (toCharArray[i3] == '%') {
                        if (toCharArray.length - i3 >= 2 && toCharArray[i3 + 1] == '2' && toCharArray[i3 + 2] == 'e') {
                            stringBuilder.append("%2e");
                            i3 += 2;
                            valueOf = Boolean.valueOf(false);
                            break;
                        }
                        obj = 1;
                    }
                    if (!valueOf.booleanValue() && (r2 == null || toCharArray[i3] == '%')) {
                        if (a(stringBuilder.toString())) {
                            i++;
                            break;
                        }
                        return false;
                    }
                    break;
                case ':':
                    if (c == ':') {
                        if (obj2 != null) {
                            return false;
                        }
                        obj2 = 1;
                    }
                    if (valueOf.booleanValue()) {
                        valueOf = Boolean.valueOf(true);
                        i2 = 0;
                        i++;
                        stringBuilder.delete(0, stringBuilder.length());
                        break;
                    }
                    return false;
                case '[':
                    break;
                default:
                    if (obj == null) {
                        stringBuilder.append(toCharArray[i3]);
                        if (!valueOf.booleanValue() || !a.a(toCharArray[i3])) {
                            valueOf = Boolean.valueOf(false);
                            break;
                        }
                        i2++;
                        break;
                    } else if (!a.e(toCharArray[i3])) {
                        return false;
                    }
                    break;
            }
            if (i2 > 4 || r6 > 8) {
                return false;
            }
            c = toCharArray[i3];
            i3++;
        }
        return i != 1 && (i >= 8 || obj2 != null);
    }
}
