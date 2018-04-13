package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.message.TokenParser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Typography;

class af {
    private static final Pattern h = Pattern.compile("(((\\p{Alnum}([\\p{Alnum}-]*\\p{Alnum})?\\.)*\\p{Alpha}([\\p{Alnum}-]*\\p{Alnum})?\\.?)|(\\d+\\.\\d+\\.\\d+\\.\\d+))(\\:\\d*)?");
    private static final Pattern i = Pattern.compile("\"(((Mon|Tue|Wed|Thu|Fri|Sat|Sun), (\\d{2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4}) (\\d{2}:\\d{2}:\\d{2}) GMT)|((Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday), (\\d{2}-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-\\d{2}) (\\d{2}:\\d{2}:\\d{2}) GMT)|((Mon|Tue|Wed|Thu|Fri|Sat|Sun) ((Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) ( |\\d)\\d) (\\d{2}:\\d{2}:\\d{2}) \\d{4}))\"");
    private int a;
    private int b;
    private final String c;
    private int d;
    private String e;
    private String f;
    private Date g;

    af(String str, int i) {
        this.b = i;
        this.a = i;
        this.c = str;
        h();
    }

    public static af[] getWarningValues(Header header) {
        List arrayList = new ArrayList();
        String value = header.getValue();
        int i = 0;
        while (i < value.length()) {
            try {
                af afVar = new af(value, i);
                arrayList.add(afVar);
                i = afVar.a;
            } catch (IllegalArgumentException e) {
                i = value.indexOf(44, i);
                if (i == -1) {
                    break;
                }
                i++;
            }
        }
        return (af[]) arrayList.toArray(new af[0]);
    }

    protected void a() {
        while (this.a < this.c.length()) {
            switch (this.c.charAt(this.a)) {
                case '\t':
                case ' ':
                    break;
                case '\r':
                    if (this.a + 2 < this.c.length() && this.c.charAt(this.a + 1) == '\n') {
                        if (this.c.charAt(this.a + 2) == TokenParser.SP || this.c.charAt(this.a + 2) == '\t') {
                            this.a += 2;
                            break;
                        }
                        return;
                    }
                    return;
                    break;
                default:
                    return;
            }
            this.a++;
        }
    }

    private boolean b(char c) {
        return c >= '\u0000' && c <= '';
    }

    private boolean c(char c) {
        return c == '' || (c >= '\u0000' && c <= '\u001f');
    }

    private boolean d(char c) {
        return c == '(' || c == ')' || c == Typography.less || c == Typography.greater || c == '@' || c == ',' || c == ';' || c == ':' || c == TokenParser.ESCAPE || c == '\"' || c == '/' || c == '[' || c == ']' || c == '?' || c == '=' || c == '{' || c == '}' || c == TokenParser.SP || c == '\t';
    }

    protected void b() {
        if (!e(this.c.charAt(this.a))) {
            j();
        }
        while (this.a < this.c.length() && e(this.c.charAt(this.a))) {
            this.a++;
        }
    }

    private boolean e(char c) {
        return (!b(c) || c(c) || d(c)) ? false : true;
    }

    protected void c() {
        Matcher matcher = h.matcher(this.c.substring(this.a));
        if (!matcher.find()) {
            j();
        }
        if (matcher.start() != 0) {
            j();
        }
        this.a = matcher.end() + this.a;
    }

    protected void d() {
        int i = this.a;
        try {
            c();
            this.e = this.c.substring(i, this.a);
            a(TokenParser.SP);
        } catch (IllegalArgumentException e) {
            this.a = i;
            b();
            this.e = this.c.substring(i, this.a);
            a(TokenParser.SP);
        }
    }

    protected void e() {
        if (this.c.charAt(this.a) != '\"') {
            j();
        }
        this.a++;
        Object obj = null;
        while (this.a < this.c.length() && obj == null) {
            char charAt = this.c.charAt(this.a);
            if (this.a + 1 < this.c.length() && charAt == TokenParser.ESCAPE && b(this.c.charAt(this.a + 1))) {
                this.a += 2;
            } else if (charAt == '\"') {
                obj = 1;
                this.a++;
            } else if (charAt == '\"' || c(charAt)) {
                j();
            } else {
                this.a++;
            }
        }
        if (obj == null) {
            j();
        }
    }

    protected void f() {
        int i = this.a;
        e();
        this.f = this.c.substring(i, this.a);
    }

    protected void g() {
        int i = this.a;
        Matcher matcher = i.matcher(this.c.substring(this.a));
        if (!matcher.lookingAt()) {
            j();
        }
        this.a = matcher.end() + this.a;
        this.g = DateUtils.parseDate(this.c.substring(i + 1, this.a - 1));
    }

    protected void h() {
        a();
        i();
        d();
        f();
        if (this.a + 1 < this.c.length() && this.c.charAt(this.a) == TokenParser.SP && this.c.charAt(this.a + 1) == '\"') {
            a(TokenParser.SP);
            g();
        }
        a();
        if (this.a != this.c.length()) {
            a(',');
        }
    }

    protected void a(char c) {
        if (this.a + 1 > this.c.length() || c != this.c.charAt(this.a)) {
            j();
        }
        this.a++;
    }

    protected void i() {
        if (!(this.a + 4 <= this.c.length() && Character.isDigit(this.c.charAt(this.a)) && Character.isDigit(this.c.charAt(this.a + 1)) && Character.isDigit(this.c.charAt(this.a + 2)) && this.c.charAt(this.a + 3) == TokenParser.SP)) {
            j();
        }
        this.d = Integer.parseInt(this.c.substring(this.a, this.a + 3));
        this.a += 4;
    }

    private void j() {
        throw new IllegalArgumentException("Bad warn code \"" + this.c.substring(this.b) + "\"");
    }

    public int getWarnCode() {
        return this.d;
    }

    public String getWarnAgent() {
        return this.e;
    }

    public String getWarnText() {
        return this.f;
    }

    public Date getWarnDate() {
        return this.g;
    }

    public String toString() {
        if (this.g != null) {
            return String.format("%d %s %s \"%s\"", new Object[]{Integer.valueOf(this.d), this.e, this.f, DateUtils.formatDate(this.g)});
        }
        return String.format("%d %s %s", new Object[]{Integer.valueOf(this.d), this.e, this.f});
    }
}
