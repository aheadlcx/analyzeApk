package com.ta.utdid2.c.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import com.ta.utdid2.b.a.i;
import com.ta.utdid2.c.a.b.a;
import java.io.File;
import java.util.Map.Entry;

public class c {
    private Editor a = null;
    /* renamed from: a */
    private SharedPreferences f31a = null;
    /* renamed from: a */
    private a f32a = null;
    /* renamed from: a */
    private b f33a = null;
    /* renamed from: a */
    private d f34a = null;
    private String e = "";
    private String f = "";
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private Context mContext = null;

    public c(Context context, String str, String str2, boolean z, boolean z2) {
        long j;
        long j2;
        Editor edit;
        a a;
        this.g = z;
        this.j = z2;
        this.e = str2;
        this.f = str;
        this.mContext = context;
        long j3 = 0;
        if (context != null) {
            this.f31a = context.getSharedPreferences(str2, 0);
            j3 = this.f31a.getLong("t", 0);
        }
        String str3 = null;
        try {
            str3 = Environment.getExternalStorageState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i.a(str3)) {
            this.i = false;
            this.h = false;
        } else if (str3.equals("mounted")) {
            this.i = true;
            this.h = true;
        } else if (str3.equals("mounted_ro")) {
            this.h = true;
            this.i = false;
        } else {
            this.i = false;
            this.h = false;
        }
        if (!((!this.h && !this.i) || context == null || i.a(str))) {
            this.f34a = a(str);
            if (this.f34a != null) {
                try {
                    this.f33a = this.f34a.a(str2, 0);
                    j = this.f33a.getLong("t", 0);
                    if (z2) {
                        j2 = this.f31a.getLong("t2", 0);
                        try {
                            j3 = this.f33a.getLong("t2", 0);
                            if (j2 < j3 && j2 > 0) {
                                try {
                                    a(this.f31a, this.f33a);
                                    this.f33a = this.f34a.a(str2, 0);
                                } catch (Exception e2) {
                                    j = j2;
                                    j2 = j;
                                    if (j2 == j3) {
                                    }
                                    j = System.currentTimeMillis();
                                    if (this.j) {
                                    }
                                    if (this.f31a != null) {
                                        edit = this.f31a.edit();
                                        edit.putLong("t2", j);
                                        edit.commit();
                                    }
                                    if (this.f33a == null) {
                                        a = this.f33a.a();
                                        a.a("t2", j);
                                        a.commit();
                                    }
                                }
                                if (j2 == j3) {
                                }
                                j = System.currentTimeMillis();
                                if (this.j) {
                                }
                                if (this.f31a != null) {
                                    edit = this.f31a.edit();
                                    edit.putLong("t2", j);
                                    edit.commit();
                                }
                                if (this.f33a == null) {
                                    a = this.f33a.a();
                                    a.a("t2", j);
                                    a.commit();
                                }
                            } else if (j2 > j3 && j3 > 0) {
                                a(this.f33a, this.f31a);
                                this.f31a = context.getSharedPreferences(str2, 0);
                                if (j2 == j3) {
                                }
                                j = System.currentTimeMillis();
                                if (this.j) {
                                }
                                if (this.f31a != null) {
                                    edit = this.f31a.edit();
                                    edit.putLong("t2", j);
                                    edit.commit();
                                }
                                if (this.f33a == null) {
                                    a = this.f33a.a();
                                    a.a("t2", j);
                                    a.commit();
                                }
                            } else if (j2 == 0 && j3 > 0) {
                                a(this.f33a, this.f31a);
                                this.f31a = context.getSharedPreferences(str2, 0);
                                if (j2 == j3) {
                                }
                                j = System.currentTimeMillis();
                                if (this.j) {
                                }
                                if (this.f31a != null) {
                                    edit = this.f31a.edit();
                                    edit.putLong("t2", j);
                                    edit.commit();
                                }
                                if (this.f33a == null) {
                                    a = this.f33a.a();
                                    a.a("t2", j);
                                    a.commit();
                                }
                            } else if (j3 != 0 || j2 <= 0) {
                                if (j2 == j3) {
                                    a(this.f31a, this.f33a);
                                    this.f33a = this.f34a.a(str2, 0);
                                }
                                if (j2 == j3) {
                                }
                                j = System.currentTimeMillis();
                                if (this.j) {
                                }
                                if (this.f31a != null) {
                                    edit = this.f31a.edit();
                                    edit.putLong("t2", j);
                                    edit.commit();
                                }
                                if (this.f33a == null) {
                                    a = this.f33a.a();
                                    a.a("t2", j);
                                    a.commit();
                                }
                            } else {
                                a(this.f31a, this.f33a);
                                this.f33a = this.f34a.a(str2, 0);
                                if (j2 == j3) {
                                }
                                j = System.currentTimeMillis();
                                if (this.j) {
                                }
                                if (this.f31a != null) {
                                    edit = this.f31a.edit();
                                    edit.putLong("t2", j);
                                    edit.commit();
                                }
                                if (this.f33a == null) {
                                    a = this.f33a.a();
                                    a.a("t2", j);
                                    a.commit();
                                }
                            }
                        } catch (Exception e3) {
                            j3 = j;
                            j = j2;
                            j2 = j;
                            if (j2 == j3) {
                            }
                            j = System.currentTimeMillis();
                            if (this.j) {
                            }
                            if (this.f31a != null) {
                                edit = this.f31a.edit();
                                edit.putLong("t2", j);
                                edit.commit();
                            }
                            if (this.f33a == null) {
                                a = this.f33a.a();
                                a.a("t2", j);
                                a.commit();
                            }
                        }
                    } else if (j3 > j) {
                        try {
                            a(this.f31a, this.f33a);
                            this.f33a = this.f34a.a(str2, 0);
                            j2 = j3;
                            j3 = j;
                        } catch (Exception e4) {
                            long j4 = j;
                            j = j3;
                            j3 = j4;
                            j2 = j;
                            if (j2 == j3) {
                            }
                            j = System.currentTimeMillis();
                            if (this.j) {
                            }
                            if (this.f31a != null) {
                                edit = this.f31a.edit();
                                edit.putLong("t2", j);
                                edit.commit();
                            }
                            if (this.f33a == null) {
                                a = this.f33a.a();
                                a.a("t2", j);
                                a.commit();
                            }
                        }
                        if (j2 == j3 || (j2 == 0 && j3 == 0)) {
                            j = System.currentTimeMillis();
                            if (this.j || (this.j && j2 == 0 && j3 == 0)) {
                                if (this.f31a != null) {
                                    edit = this.f31a.edit();
                                    edit.putLong("t2", j);
                                    edit.commit();
                                }
                                if (this.f33a == null) {
                                    a = this.f33a.a();
                                    a.a("t2", j);
                                    a.commit();
                                }
                            }
                            return;
                        }
                        return;
                    } else {
                        if (j3 < j) {
                            a(this.f33a, this.f31a);
                            this.f31a = context.getSharedPreferences(str2, 0);
                            j2 = j3;
                            j3 = j;
                        } else if (j3 == j) {
                            a(this.f31a, this.f33a);
                            this.f33a = this.f34a.a(str2, 0);
                            j2 = j3;
                            j3 = j;
                        } else {
                            j2 = j3;
                            j3 = j;
                        }
                        if (j2 == j3) {
                        }
                        j = System.currentTimeMillis();
                        if (this.j) {
                        }
                        if (this.f31a != null) {
                            edit = this.f31a.edit();
                            edit.putLong("t2", j);
                            edit.commit();
                        }
                        if (this.f33a == null) {
                            a = this.f33a.a();
                            a.a("t2", j);
                            a.commit();
                        }
                    }
                } catch (Exception e5) {
                    j = j3;
                    j3 = 0;
                    j2 = j;
                    if (j2 == j3) {
                    }
                    j = System.currentTimeMillis();
                    if (this.j) {
                    }
                    if (this.f31a != null) {
                        edit = this.f31a.edit();
                        edit.putLong("t2", j);
                        edit.commit();
                    }
                    if (this.f33a == null) {
                        a = this.f33a.a();
                        a.a("t2", j);
                        a.commit();
                    }
                }
            }
        }
        j2 = j3;
        j3 = 0;
        if (j2 == j3) {
        }
        j = System.currentTimeMillis();
        if (this.j) {
        }
        if (this.f31a != null) {
            edit = this.f31a.edit();
            edit.putLong("t2", j);
            edit.commit();
        }
        try {
            if (this.f33a == null) {
                a = this.f33a.a();
                a.a("t2", j);
                a.commit();
            }
        } catch (Exception e6) {
        }
    }

    private d a(String str) {
        File a = a(str);
        if (a == null) {
            return null;
        }
        this.f34a = new d(a.getAbsolutePath());
        return this.f34a;
    }

    /* renamed from: a */
    private File m17a(String str) {
        if (Environment.getExternalStorageDirectory() == null) {
            return null;
        }
        File file = new File(String.format("%s%s%s", new Object[]{Environment.getExternalStorageDirectory().getAbsolutePath(), File.separator, str}));
        if (file == null || file.exists()) {
            return file;
        }
        file.mkdirs();
        return file;
    }

    private void a(SharedPreferences sharedPreferences, b bVar) {
        if (sharedPreferences != null && bVar != null) {
            a a = bVar.a();
            if (a != null) {
                a.b();
                for (Entry entry : sharedPreferences.getAll().entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        a.a(str, (String) value);
                    } else if (value instanceof Integer) {
                        a.a(str, ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        a.a(str, ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        a.a(str, ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        a.a(str, ((Boolean) value).booleanValue());
                    }
                }
                a.commit();
            }
        }
    }

    private void a(b bVar, SharedPreferences sharedPreferences) {
        if (bVar != null && sharedPreferences != null) {
            Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.clear();
                for (Entry entry : bVar.getAll().entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        edit.putFloat(str, ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    }
                }
                edit.commit();
            }
        }
    }

    private boolean b() {
        if (this.f33a == null) {
            return false;
        }
        boolean a = this.f33a.a();
        if (a) {
            return a;
        }
        commit();
        return a;
    }

    private void c() {
        if (this.a == null && this.f31a != null) {
            this.a = this.f31a.edit();
        }
        if (this.i && this.f32a == null && this.f33a != null) {
            this.f32a = this.f33a.a();
        }
        b();
    }

    public void putString(String str, String str2) {
        if (!i.a(str) && !str.equals("t")) {
            c();
            if (this.a != null) {
                this.a.putString(str, str2);
            }
            if (this.f32a != null) {
                this.f32a.a(str, str2);
            }
        }
    }

    public void remove(String str) {
        if (!i.a(str) && !str.equals("t")) {
            c();
            if (this.a != null) {
                this.a.remove(str);
            }
            if (this.f32a != null) {
                this.f32a.a(str);
            }
        }
    }

    public boolean commit() {
        boolean z = true;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.a != null) {
            if (!(this.j || this.f31a == null)) {
                this.a.putLong("t", currentTimeMillis);
            }
            if (!this.a.commit()) {
                z = false;
            }
        }
        if (!(this.f31a == null || this.mContext == null)) {
            this.f31a = this.mContext.getSharedPreferences(this.e, 0);
        }
        String str = null;
        try {
            str = Environment.getExternalStorageState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!i.a(str)) {
            if (str.equals("mounted")) {
                if (this.f33a == null) {
                    d a = a(this.f);
                    if (a != null) {
                        this.f33a = a.a(this.e, 0);
                        if (this.j) {
                            a(this.f33a, this.f31a);
                        } else {
                            a(this.f31a, this.f33a);
                        }
                        this.f32a = this.f33a.a();
                    }
                } else if (!(this.f32a == null || this.f32a.commit())) {
                    z = false;
                }
            }
            if (str.equals("mounted") || (str.equals("mounted_ro") && this.f33a != null)) {
                try {
                    if (this.f34a != null) {
                        this.f33a = this.f34a.a(this.e, 0);
                    }
                } catch (Exception e2) {
                }
            }
        }
        return z;
    }

    public String getString(String str) {
        b();
        if (this.f31a != null) {
            String string = this.f31a.getString(str, "");
            if (!i.a(string)) {
                return string;
            }
        }
        if (this.f33a != null) {
            return this.f33a.getString(str, "");
        }
        return "";
    }
}