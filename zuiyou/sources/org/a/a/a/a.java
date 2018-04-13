package org.a.a.a;

import org.xml.sax.Attributes;

public class a implements Attributes {
    int a;
    String[] b;

    public a() {
        this.a = 0;
        this.b = null;
    }

    public a(Attributes attributes) {
        a(attributes);
    }

    public int getLength() {
        return this.a;
    }

    public String getURI(int i) {
        if (i < 0 || i >= this.a) {
            return null;
        }
        return this.b[i * 5];
    }

    public String getLocalName(int i) {
        if (i < 0 || i >= this.a) {
            return null;
        }
        return this.b[(i * 5) + 1];
    }

    public String getQName(int i) {
        if (i < 0 || i >= this.a) {
            return null;
        }
        return this.b[(i * 5) + 2];
    }

    public String getType(int i) {
        if (i < 0 || i >= this.a) {
            return null;
        }
        return this.b[(i * 5) + 3];
    }

    public String getValue(int i) {
        if (i < 0 || i >= this.a) {
            return null;
        }
        return this.b[(i * 5) + 4];
    }

    public int getIndex(String str, String str2) {
        int i = this.a * 5;
        int i2 = 0;
        while (i2 < i) {
            if (this.b[i2].equals(str) && this.b[i2 + 1].equals(str2)) {
                return i2 / 5;
            }
            i2 += 5;
        }
        return -1;
    }

    public int getIndex(String str) {
        int i = this.a * 5;
        for (int i2 = 0; i2 < i; i2 += 5) {
            if (this.b[i2 + 2].equals(str)) {
                return i2 / 5;
            }
        }
        return -1;
    }

    public String getType(String str, String str2) {
        int i = this.a * 5;
        int i2 = 0;
        while (i2 < i) {
            if (this.b[i2].equals(str) && this.b[i2 + 1].equals(str2)) {
                return this.b[i2 + 3];
            }
            i2 += 5;
        }
        return null;
    }

    public String getType(String str) {
        int i = this.a * 5;
        for (int i2 = 0; i2 < i; i2 += 5) {
            if (this.b[i2 + 2].equals(str)) {
                return this.b[i2 + 3];
            }
        }
        return null;
    }

    public String getValue(String str, String str2) {
        int i = this.a * 5;
        int i2 = 0;
        while (i2 < i) {
            if (this.b[i2].equals(str) && this.b[i2 + 1].equals(str2)) {
                return this.b[i2 + 4];
            }
            i2 += 5;
        }
        return null;
    }

    public String getValue(String str) {
        int i = this.a * 5;
        for (int i2 = 0; i2 < i; i2 += 5) {
            if (this.b[i2 + 2].equals(str)) {
                return this.b[i2 + 4];
            }
        }
        return null;
    }

    public void a() {
        if (this.b != null) {
            for (int i = 0; i < this.a * 5; i++) {
                this.b[i] = null;
            }
        }
        this.a = 0;
    }

    public void a(Attributes attributes) {
        a();
        this.a = attributes.getLength();
        if (this.a > 0) {
            this.b = new String[(this.a * 5)];
            for (int i = 0; i < this.a; i++) {
                this.b[i * 5] = attributes.getURI(i);
                this.b[(i * 5) + 1] = attributes.getLocalName(i);
                this.b[(i * 5) + 2] = attributes.getQName(i);
                this.b[(i * 5) + 3] = attributes.getType(i);
                this.b[(i * 5) + 4] = attributes.getValue(i);
            }
        }
    }

    public void a(String str, String str2, String str3, String str4, String str5) {
        b(this.a + 1);
        this.b[this.a * 5] = str;
        this.b[(this.a * 5) + 1] = str2;
        this.b[(this.a * 5) + 2] = str3;
        this.b[(this.a * 5) + 3] = str4;
        this.b[(this.a * 5) + 4] = str5;
        this.a++;
    }

    public void a(int i, String str, String str2, String str3, String str4, String str5) {
        if (i < 0 || i >= this.a) {
            c(i);
            return;
        }
        this.b[i * 5] = str;
        this.b[(i * 5) + 1] = str2;
        this.b[(i * 5) + 2] = str3;
        this.b[(i * 5) + 3] = str4;
        this.b[(i * 5) + 4] = str5;
    }

    public void a(int i) {
        if (i < 0 || i >= this.a) {
            c(i);
            return;
        }
        if (i < this.a - 1) {
            System.arraycopy(this.b, (i + 1) * 5, this.b, i * 5, ((this.a - i) - 1) * 5);
        }
        int i2 = (this.a - 1) * 5;
        int i3 = i2 + 1;
        this.b[i2] = null;
        int i4 = i3 + 1;
        this.b[i3] = null;
        i3 = i4 + 1;
        this.b[i4] = null;
        i4 = i3 + 1;
        this.b[i3] = null;
        this.b[i4] = null;
        this.a--;
    }

    private void b(int i) {
        if (i > 0) {
            int i2;
            if (this.b == null || this.b.length == 0) {
                i2 = 25;
            } else if (this.b.length < i * 5) {
                i2 = this.b.length;
            } else {
                return;
            }
            while (i2 < i * 5) {
                i2 *= 2;
            }
            Object obj = new String[i2];
            if (this.a > 0) {
                System.arraycopy(this.b, 0, obj, 0, this.a * 5);
            }
            this.b = obj;
        }
    }

    private void c(int i) throws ArrayIndexOutOfBoundsException {
        throw new ArrayIndexOutOfBoundsException(new StringBuffer().append("Attempt to modify attribute at illegal index: ").append(i).toString());
    }
}
