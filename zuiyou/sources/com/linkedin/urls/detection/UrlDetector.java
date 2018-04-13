package com.linkedin.urls.detection;

import com.linkedin.urls.UrlPart;
import com.linkedin.urls.a;
import com.linkedin.urls.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UrlDetector {
    private static final Set<String> a = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"http://", "https://", "ftp://", "ftps://", "http%3a//", "https%3a//", "ftp%3a//", "ftps%3a//"})));
    private final UrlDetectorOptions b;
    private final b c;
    private StringBuilder d = new StringBuilder();
    private boolean e = false;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private ArrayList<a> i = new ArrayList();
    private HashMap<Character, Integer> j = new HashMap();
    private b k = new b();

    private enum CharacterMatch {
        CharacterNotMatched,
        CharacterMatchStop,
        CharacterMatchStart
    }

    public enum ReadEndState {
        ValidUrl,
        InvalidUrl
    }

    public UrlDetector(String str, UrlDetectorOptions urlDetectorOptions) {
        this.c = new b(str);
        this.b = urlDetectorOptions;
    }

    public List<a> a() {
        b();
        return this.i;
    }

    private void b() {
        int i = 0;
        while (!this.c.b()) {
            char a = this.c.a();
            switch (a) {
                case ' ':
                    if (this.b.hasFlag(UrlDetectorOptions.ALLOW_SINGLE_LEVEL_DOMAIN) && this.d.length() > 0 && this.e) {
                        this.c.d();
                        a(this.d.substring(i));
                    }
                    this.d.append(a);
                    a(ReadEndState.InvalidUrl);
                    i = 0;
                    break;
                case '%':
                    if (this.c.c(2)) {
                        if (!this.c.a(2).equalsIgnoreCase("3a")) {
                            if (a.a(this.c.b(0)) && a.a(this.c.b(1))) {
                                this.d.append(a);
                                this.d.append(this.c.a());
                                this.d.append(this.c.a());
                                a(this.d.substring(i));
                                i = 0;
                                break;
                            }
                        }
                        this.d.append(a);
                        this.d.append(this.c.a());
                        this.d.append(this.c.a());
                        i = a(i);
                        break;
                    }
                    break;
                case '.':
                case '。':
                case '．':
                case '｡':
                    this.d.append(a);
                    a(this.d.substring(i));
                    i = 0;
                    break;
                case '/':
                    if (!this.e && (!this.b.hasFlag(UrlDetectorOptions.ALLOW_SINGLE_LEVEL_DOMAIN) || this.d.length() <= 1)) {
                        a(ReadEndState.InvalidUrl);
                        this.d.append(a);
                        this.e = c();
                        i = this.d.length();
                        break;
                    }
                    this.c.d();
                    a(this.d.substring(i));
                    i = 0;
                    break;
                case ':':
                    this.d.append(a);
                    i = a(i);
                    break;
                case '@':
                    if (this.d.length() <= 0) {
                        break;
                    }
                    this.k.a(UrlPart.USERNAME_PASSWORD, i);
                    this.d.append(a);
                    a(null);
                    i = 0;
                    break;
                case '[':
                    if (this.h && b(a) != CharacterMatch.CharacterNotMatched) {
                        a(ReadEndState.InvalidUrl);
                        i = 0;
                    }
                    int c = this.c.c();
                    if (!this.e) {
                        this.d.delete(0, this.d.length());
                    }
                    this.d.append(a);
                    if (!a(this.d.substring(i))) {
                        this.c.d(c);
                        this.h = true;
                    }
                    i = 0;
                    break;
                default:
                    if (b(a) == CharacterMatch.CharacterNotMatched) {
                        this.d.append(a);
                        break;
                    }
                    a(ReadEndState.InvalidUrl);
                    i = 0;
                    break;
            }
        }
        if (this.b.hasFlag(UrlDetectorOptions.ALLOW_SINGLE_LEVEL_DOMAIN) && this.d.length() > 0 && this.e) {
            a(this.d.substring(i));
        }
    }

    private int a(int i) {
        if (this.e) {
            if (b(i) || this.d.length() <= 0) {
                return i;
            }
            this.c.d();
            this.d.delete(this.d.length() - 1, this.d.length());
            int c = (this.c.c() - this.d.length()) + i;
            if (!a(this.d.substring(i))) {
                this.c.d(c);
                a(ReadEndState.InvalidUrl);
            }
            return 0;
        } else if (d() && this.d.length() > 0) {
            this.e = true;
            return this.d.length();
        } else if (this.d.length() > 0 && this.b.hasFlag(UrlDetectorOptions.ALLOW_SINGLE_LEVEL_DOMAIN) && this.c.c(1)) {
            this.c.d();
            this.d.delete(this.d.length() - 1, this.d.length());
            a(this.d.toString());
            return i;
        } else {
            a(ReadEndState.InvalidUrl);
            return 0;
        }
    }

    private int a(char c) {
        Integer num = (Integer) this.j.get(Character.valueOf(c));
        return num == null ? 0 : num.intValue();
    }

    private CharacterMatch b(char c) {
        char c2 = '[';
        if ((c == '\"' && this.b.hasFlag(UrlDetectorOptions.QUOTE_MATCH)) || (c == '\'' && this.b.hasFlag(UrlDetectorOptions.SINGLE_QUOTE_MATCH))) {
            boolean z;
            if (c == '\"') {
                z = this.f;
                this.f = true;
            } else {
                z = this.g;
                this.g = true;
            }
            Integer valueOf = Integer.valueOf(a(c) + 1);
            this.j.put(Character.valueOf(c), valueOf);
            if (z || valueOf.intValue() % 2 == 0) {
                return CharacterMatch.CharacterMatchStop;
            }
            return CharacterMatch.CharacterMatchStart;
        } else if (this.b.hasFlag(UrlDetectorOptions.BRACKET_MATCH) && (c == '[' || c == '{' || c == '(')) {
            this.j.put(Character.valueOf(c), Integer.valueOf(a(c) + 1));
            return CharacterMatch.CharacterMatchStart;
        } else if (this.b.hasFlag(UrlDetectorOptions.XML) && c == '<') {
            this.j.put(Character.valueOf(c), Integer.valueOf(a(c) + 1));
            return CharacterMatch.CharacterMatchStart;
        } else if ((!this.b.hasFlag(UrlDetectorOptions.BRACKET_MATCH) || (c != ']' && c != '}' && c != ')')) && (!this.b.hasFlag(UrlDetectorOptions.XML) || c != '>')) {
            return CharacterMatch.CharacterNotMatched;
        } else {
            Integer valueOf2 = Integer.valueOf(a(c) + 1);
            this.j.put(Character.valueOf(c), valueOf2);
            switch (c) {
                case ')':
                    c2 = '(';
                    break;
                case '>':
                    c2 = '<';
                    break;
                case ']':
                    break;
                case '}':
                    c2 = '{';
                    break;
                default:
                    c2 = '\u0000';
                    break;
            }
            if (a(c2) > valueOf2.intValue()) {
                return CharacterMatch.CharacterMatchStop;
            }
            return CharacterMatch.CharacterMatchStart;
        }
    }

    private boolean c() {
        if (this.c.b()) {
            return false;
        }
        char a = this.c.a();
        if (a == '/') {
            this.d.append(a);
            return true;
        }
        this.c.d();
        a(ReadEndState.InvalidUrl);
        return false;
    }

    private boolean d() {
        if (this.b.hasFlag(UrlDetectorOptions.HTML) && this.d.length() >= "mailto:".length() && "mailto:".equalsIgnoreCase(this.d.substring(this.d.length() - "mailto:".length()))) {
            return a(ReadEndState.InvalidUrl);
        }
        int length = this.d.length();
        int i = 0;
        while (!this.c.b()) {
            char a = this.c.a();
            if (a == '/') {
                this.d.append(a);
                if (i != 1) {
                    i++;
                } else if (!a.contains(this.d.toString().toLowerCase())) {
                    return false;
                } else {
                    this.k.a(UrlPart.SCHEME, 0);
                    return true;
                }
            } else if (a == ' ' || b(a) != CharacterMatch.CharacterNotMatched) {
                this.d.append(a);
                return false;
            } else if (a == '[') {
                this.c.d();
                return false;
            } else if (length > 0 || i > 0 || !a.b(a)) {
                this.c.d();
                return b(0);
            }
        }
        return false;
    }

    private boolean b(int i) {
        int i2 = 1;
        int length = this.d.length();
        boolean z = false;
        boolean z2 = false;
        while (!z2 && !this.c.b()) {
            char a = this.c.a();
            if (a == '@') {
                this.d.append(a);
                this.k.a(UrlPart.USERNAME_PASSWORD, i);
                return a("");
            } else if (a.f(a) || a == '[') {
                this.d.append(a);
                z = true;
            } else if (a == '#' || a == ' ' || a == '/' || b(a) != CharacterMatch.CharacterNotMatched) {
                z = true;
                z2 = true;
            } else {
                this.d.append(a);
            }
        }
        if (!z) {
            return a(ReadEndState.InvalidUrl);
        }
        int length2 = this.d.length() - length;
        this.d.delete(length, this.d.length());
        length2 = this.c.c() - length2;
        if (!z2) {
            i2 = 0;
        }
        this.c.d(Math.max(length2 - i2, 0));
        return false;
    }

    private boolean a(String str) {
        int length;
        if (str == null) {
            length = this.d.length();
        } else {
            length = this.d.length() - str.length();
        }
        this.k.a(UrlPart.HOST, length);
        switch (new DomainNameReader(this.c, this.d, str, this.b, new a(this) {
            final /* synthetic */ UrlDetector a;

            {
                this.a = r1;
            }

            public void a(char c) {
                this.a.b(c);
            }
        }).a()) {
            case ValidDomainName:
                return a(ReadEndState.ValidUrl);
            case ReadFragment:
                return e();
            case ReadPath:
                return h();
            case ReadPort:
                return g();
            case ReadQueryString:
                return f();
            default:
                return a(ReadEndState.InvalidUrl);
        }
    }

    private boolean e() {
        this.k.a(UrlPart.FRAGMENT, this.d.length() - 1);
        while (!this.c.b()) {
            char a = this.c.a();
            if (a == ' ' || b(a) != CharacterMatch.CharacterNotMatched) {
                return a(ReadEndState.ValidUrl);
            }
            this.d.append(a);
        }
        return a(ReadEndState.ValidUrl);
    }

    private boolean f() {
        this.k.a(UrlPart.QUERY, this.d.length() - 1);
        while (!this.c.b()) {
            char a = this.c.a();
            if (a == '#') {
                this.d.append(a);
                return e();
            } else if (a == ' ' || b(a) != CharacterMatch.CharacterNotMatched) {
                return a(ReadEndState.ValidUrl);
            } else {
                this.d.append(a);
            }
        }
        return a(ReadEndState.ValidUrl);
    }

    private boolean g() {
        this.k.a(UrlPart.PORT, this.d.length());
        int i = 0;
        while (!this.c.b()) {
            char a = this.c.a();
            i++;
            if (a == '/') {
                this.d.append(a);
                return h();
            } else if (a == '?') {
                this.d.append(a);
                return f();
            } else if (a == '#') {
                this.d.append(a);
                return e();
            } else if (b(a) == CharacterMatch.CharacterMatchStop || !a.c(a)) {
                this.c.d();
                if (i == 1) {
                    this.d.delete(this.d.length() - 1, this.d.length());
                }
                this.k.b(UrlPart.PORT);
                return a(ReadEndState.ValidUrl);
            } else {
                this.d.append(a);
            }
        }
        return a(ReadEndState.ValidUrl);
    }

    private boolean h() {
        this.k.a(UrlPart.PATH, this.d.length() - 1);
        while (!this.c.b()) {
            char a = this.c.a();
            if (a == ' ' || b(a) != CharacterMatch.CharacterNotMatched) {
                return a(ReadEndState.ValidUrl);
            }
            this.d.append(a);
            if (a == '?') {
                return f();
            }
            if (a == '#') {
                return e();
            }
        }
        return a(ReadEndState.ValidUrl);
    }

    private boolean a(ReadEndState readEndState) {
        if (readEndState == ReadEndState.ValidUrl && this.d.length() > 0) {
            int length = this.d.length();
            if (this.f && this.d.charAt(length - 1) == '\"') {
                this.d.delete(length - 1, length);
            }
            if (this.d.length() > 0) {
                this.k.a(this.d.toString());
                this.i.add(this.k.a());
            }
        }
        this.d.delete(0, this.d.length());
        this.f = false;
        this.e = false;
        this.h = false;
        this.k = new b();
        if (readEndState == ReadEndState.ValidUrl) {
            return true;
        }
        return false;
    }
}
