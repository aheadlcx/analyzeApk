package com.budejie.www.c;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.analytics.MobclickAgent;

public class c extends SQLiteOpenHelper {
    public static final Object a = new Object();
    private static c b = null;
    private Context c;
    private int d;

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new c(context, "db", null, 48);
            }
            cVar = b;
        }
        return cVar;
    }

    private c(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
        this.c = context;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (a) {
            w(sQLiteDatabase);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onUpgrade(android.database.sqlite.SQLiteDatabase r3, int r4, int r5) {
        /*
        r2 = this;
        r2.d = r4;
        r1 = a;
        monitor-enter(r1);
        switch(r4) {
            case 10: goto L_0x001a;
            case 11: goto L_0x001d;
            case 12: goto L_0x0020;
            case 13: goto L_0x0023;
            case 14: goto L_0x0026;
            case 15: goto L_0x0029;
            case 16: goto L_0x002c;
            case 17: goto L_0x003c;
            case 18: goto L_0x0042;
            case 19: goto L_0x0048;
            case 20: goto L_0x004e;
            case 21: goto L_0x0054;
            case 22: goto L_0x005a;
            case 23: goto L_0x0060;
            case 24: goto L_0x0063;
            case 25: goto L_0x0066;
            case 26: goto L_0x0069;
            case 27: goto L_0x006c;
            case 28: goto L_0x006f;
            case 29: goto L_0x0072;
            case 30: goto L_0x0075;
            case 31: goto L_0x0078;
            case 32: goto L_0x007b;
            case 33: goto L_0x007e;
            case 34: goto L_0x0081;
            case 35: goto L_0x0084;
            case 36: goto L_0x0087;
            case 37: goto L_0x008a;
            case 38: goto L_0x008d;
            case 39: goto L_0x0090;
            case 40: goto L_0x0093;
            case 41: goto L_0x0096;
            case 42: goto L_0x0099;
            case 43: goto L_0x0099;
            case 44: goto L_0x009c;
            case 45: goto L_0x009f;
            case 46: goto L_0x00a2;
            case 47: goto L_0x00a5;
            default: goto L_0x0008;
        };
    L_0x0008:
        r0 = 10;
        if (r4 >= r0) goto L_0x0018;
    L_0x000c:
        r2.x(r3);	 Catch:{ all -> 0x00aa }
        r0 = r2.c;	 Catch:{ all -> 0x00aa }
        if (r0 == 0) goto L_0x0018;
    L_0x0013:
        r0 = r2.c;	 Catch:{ all -> 0x00aa }
        r2.b(r0);	 Catch:{ all -> 0x00aa }
    L_0x0018:
        monitor-exit(r1);	 Catch:{ all -> 0x00aa }
        return;
    L_0x001a:
        r2.R(r3);	 Catch:{ all -> 0x00aa }
    L_0x001d:
        r2.S(r3);	 Catch:{ all -> 0x00aa }
    L_0x0020:
        r2.T(r3);	 Catch:{ all -> 0x00aa }
    L_0x0023:
        r2.U(r3);	 Catch:{ all -> 0x00aa }
    L_0x0026:
        r2.V(r3);	 Catch:{ all -> 0x00aa }
    L_0x0029:
        r2.W(r3);	 Catch:{ all -> 0x00aa }
    L_0x002c:
        r0 = 16;
        if (r4 != r0) goto L_0x0039;
    L_0x0030:
        r0 = r2.a(r3);	 Catch:{ all -> 0x00aa }
        if (r0 == 0) goto L_0x00ad;
    L_0x0036:
        r2.X(r3);	 Catch:{ all -> 0x00aa }
    L_0x0039:
        r2.Y(r3);	 Catch:{ all -> 0x00aa }
    L_0x003c:
        r2.Z(r3);	 Catch:{ all -> 0x00aa }
        r2.aa(r3);	 Catch:{ all -> 0x00aa }
    L_0x0042:
        r2.ab(r3);	 Catch:{ all -> 0x00aa }
        r2.aa(r3);	 Catch:{ all -> 0x00aa }
    L_0x0048:
        r2.aa(r3);	 Catch:{ all -> 0x00aa }
        r2.ac(r3);	 Catch:{ all -> 0x00aa }
    L_0x004e:
        r2.aa(r3);	 Catch:{ all -> 0x00aa }
        r2.ad(r3);	 Catch:{ all -> 0x00aa }
    L_0x0054:
        r2.aa(r3);	 Catch:{ all -> 0x00aa }
        r2.ae(r3);	 Catch:{ all -> 0x00aa }
    L_0x005a:
        r2.aa(r3);	 Catch:{ all -> 0x00aa }
        r2.af(r3);	 Catch:{ all -> 0x00aa }
    L_0x0060:
        r2.ag(r3);	 Catch:{ all -> 0x00aa }
    L_0x0063:
        r2.ah(r3);	 Catch:{ all -> 0x00aa }
    L_0x0066:
        r2.am(r3);	 Catch:{ all -> 0x00aa }
    L_0x0069:
        r2.v(r3);	 Catch:{ all -> 0x00aa }
    L_0x006c:
        r2.t(r3);	 Catch:{ all -> 0x00aa }
    L_0x006f:
        r2.u(r3);	 Catch:{ all -> 0x00aa }
    L_0x0072:
        r2.P(r3);	 Catch:{ all -> 0x00aa }
    L_0x0075:
        r2.s(r3);	 Catch:{ all -> 0x00aa }
    L_0x0078:
        r2.r(r3);	 Catch:{ all -> 0x00aa }
    L_0x007b:
        r2.j(r3);	 Catch:{ all -> 0x00aa }
    L_0x007e:
        r2.k(r3);	 Catch:{ all -> 0x00aa }
    L_0x0081:
        r2.l(r3);	 Catch:{ all -> 0x00aa }
    L_0x0084:
        r2.m(r3);	 Catch:{ all -> 0x00aa }
    L_0x0087:
        r2.i(r3);	 Catch:{ all -> 0x00aa }
    L_0x008a:
        r2.n(r3);	 Catch:{ all -> 0x00aa }
    L_0x008d:
        r2.o(r3);	 Catch:{ all -> 0x00aa }
    L_0x0090:
        r2.p(r3);	 Catch:{ all -> 0x00aa }
    L_0x0093:
        r2.q(r3);	 Catch:{ all -> 0x00aa }
    L_0x0096:
        r2.C(r3);	 Catch:{ all -> 0x00aa }
    L_0x0099:
        r2.b(r3);	 Catch:{ all -> 0x00aa }
    L_0x009c:
        r2.c(r3);	 Catch:{ all -> 0x00aa }
    L_0x009f:
        r2.d(r3);	 Catch:{ all -> 0x00aa }
    L_0x00a2:
        r2.e(r3);	 Catch:{ all -> 0x00aa }
    L_0x00a5:
        r2.f(r3);	 Catch:{ all -> 0x00aa }
        goto L_0x0018;
    L_0x00aa:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00aa }
        throw r0;
    L_0x00ad:
        r2.N(r3);	 Catch:{ all -> 0x00aa }
        goto L_0x0039;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.c.c.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD status int");
            sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD status int");
        } catch (Exception e) {
        }
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD top_comments text");
            sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD top_comments text");
        } catch (Exception e) {
        }
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        try {
            h(sQLiteDatabase);
            sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD votes text");
            sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD votes text");
            sQLiteDatabase.execSQL("ALTER TABLE draft ADD votes text");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE voted ADD cid varchar(20)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void f(SQLiteDatabase sQLiteDatabase) {
        try {
            g(sQLiteDatabase);
            sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD tags text");
            sQLiteDatabase.execSQL("ALTER TABLE draft ADD tags text");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void g(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists plate(");
        stringBuilder.append("id  integer primary key not null, ");
        stringBuilder.append("theme_id varchar(20), ");
        stringBuilder.append("read_num integer, ");
        stringBuilder.append("read_time integer, ");
        stringBuilder.append("read_count integer ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void h(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists voted(");
        stringBuilder.append("id  integer primary key not null, ");
        stringBuilder.append("pid varchar(20), ");
        stringBuilder.append("vid varchar(20) ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void i(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("drop table if exists cacheOffline");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void j(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD cacheTypeNew VARCHAR(20)");
    }

    private void k(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD downloadVideoUris TEXT");
    }

    private void l(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD videouri_back TEXT");
    }

    private void m(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD downloadVideoUris TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD videouri_back TEXT");
    }

    private void n(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD linkurl TEXT");
    }

    private void o(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD downloadImageUris TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD downloadImageUris TEXT");
    }

    private void p(SQLiteDatabase sQLiteDatabase) {
        B(sQLiteDatabase);
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD sina_v VARCHAR(10)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD is_vip VARCHAR(10)");
    }

    private void q(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE recommend_Label ADD post_num TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE subscribe_Label ADD post_num TEXT");
    }

    private void r(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS collectTable");
        D(sQLiteDatabase);
    }

    private void s(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD width integer");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD height integer");
    }

    private void t(SQLiteDatabase sQLiteDatabase) {
        M(sQLiteDatabase);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists operation(");
        stringBuilder.append("id  integer primary key not null, ");
        stringBuilder.append("is_show varchar(4), ");
        stringBuilder.append("show_num varchar(4), ");
        stringBuilder.append("font_color varchar(100), ");
        stringBuilder.append("title text, ");
        stringBuilder.append("words text, ");
        stringBuilder.append("buttons text, ");
        stringBuilder.append("end_time text, ");
        stringBuilder.append("backgroud_pic text, ");
        stringBuilder.append("iphone varchar(20), ");
        stringBuilder.append("ipad varchar(20), ");
        stringBuilder.append("android varchar(20), ");
        stringBuilder.append("recommend_targets text, ");
        stringBuilder.append("order_id varchar(10) ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD grade VARCHAR(4)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD Is_black_user VARCHAR(4)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD Is_black_device VARCHAR(4)");
        O(sQLiteDatabase);
    }

    private void u(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE recommend_Label ADD type VARCHAR(20)");
            sQLiteDatabase.execSQL("ALTER TABLE subscribe_Label ADD type VARCHAR(20)");
        } catch (Exception e) {
        }
    }

    private void v(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD birthday VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD qq VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD level VARCHAR(50)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD credit VARCHAR(50)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD experience VARCHAR(50)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD sina_v VARCHAR(4)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD jie_v VARCHAR(4)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD v_desc text");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD age_group VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD degree VARCHAR(50)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD trade_history text");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD trade_ruler text");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD sina_v VARCHAR(4)");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD jie_v VARCHAR(4)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD sina_v VARCHAR(4)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD jie_v VARCHAR(4)");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD sina_v VARCHAR(4)");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD jie_v VARCHAR(4)");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD sina_v VARCHAR(4)");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD jie_v VARCHAR(4)");
    }

    public synchronized void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        synchronized (a) {
            MobclickAgent.onEvent(this.c, "E07_A01", "onDowngrade oldVersion=" + i + ",newVersion=" + i2);
            x(sQLiteDatabase);
        }
    }

    private void w(SQLiteDatabase sQLiteDatabase) {
        A(sQLiteDatabase);
        z(sQLiteDatabase);
        H(sQLiteDatabase);
        y(sQLiteDatabase);
        E(sQLiteDatabase);
        F(sQLiteDatabase);
        G(sQLiteDatabase);
        J(sQLiteDatabase);
        K(sQLiteDatabase);
        L(sQLiteDatabase);
        N(sQLiteDatabase);
        Z(sQLiteDatabase);
        ab(sQLiteDatabase);
        ac(sQLiteDatabase);
        ad(sQLiteDatabase);
        ae(sQLiteDatabase);
        af(sQLiteDatabase);
        ag(sQLiteDatabase);
        ah(sQLiteDatabase);
        am(sQLiteDatabase);
        v(sQLiteDatabase);
        t(sQLiteDatabase);
        u(sQLiteDatabase);
        P(sQLiteDatabase);
        s(sQLiteDatabase);
        r(sQLiteDatabase);
        j(sQLiteDatabase);
        k(sQLiteDatabase);
        l(sQLiteDatabase);
        m(sQLiteDatabase);
        i(sQLiteDatabase);
        n(sQLiteDatabase);
        o(sQLiteDatabase);
        p(sQLiteDatabase);
        q(sQLiteDatabase);
        C(sQLiteDatabase);
        b(sQLiteDatabase);
        c(sQLiteDatabase);
        d(sQLiteDatabase);
        e(sQLiteDatabase);
        f(sQLiteDatabase);
    }

    private void x(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cacheTable");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS goods");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS duanzi");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sound");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS video");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS collectTable");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS weibo");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS love");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS newCacheTable");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS subscribe_Label");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS recommend_Label");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS recommend_user");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS comment");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS draft");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ad");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS follow_list");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cacheOffline");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS operation");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS rule");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS user_info");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS voted");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS plate");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS table_selector_symbol_label");
        w(sQLiteDatabase);
    }

    private void y(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists love(");
        stringBuilder.append("id  integer primary key not null, ");
        stringBuilder.append("data_id varchar(50), ");
        stringBuilder.append("ding integer, ");
        stringBuilder.append("cai integer, ");
        stringBuilder.append("flag varchar(20), ");
        stringBuilder.append("cai_flag varchar(20), ");
        stringBuilder.append("data1 varchar(100), ");
        stringBuilder.append("data2 varchar(100), ");
        stringBuilder.append("data3 varchar(100), ");
        stringBuilder.append("data4 varchar(100)");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void z(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists cacheTable(");
        stringBuilder.append("id  integer primary key not null, ");
        stringBuilder.append("wid varchar(20), ");
        stringBuilder.append("name varchar(50), ");
        stringBuilder.append("image varchar(100), ");
        stringBuilder.append("neturl varchar(100), ");
        stringBuilder.append("addtime varchar(20), ");
        stringBuilder.append("passtime varchar(20), ");
        stringBuilder.append("comment varchar(50), ");
        stringBuilder.append("content varchar(200), ");
        stringBuilder.append("repost varchar(50), ");
        stringBuilder.append("love integer, ");
        stringBuilder.append("weibo varchar(50), ");
        stringBuilder.append("type varchar(50), ");
        stringBuilder.append("width integer, ");
        stringBuilder.append("height integer, ");
        stringBuilder.append("mid varchar(50), ");
        stringBuilder.append("is_gif varchar(50), ");
        stringBuilder.append("gif_first_frame varchar(100), ");
        stringBuilder.append("data1 varchar(100), ");
        stringBuilder.append("data2 varchar(100), ");
        stringBuilder.append("data3 varchar(100), ");
        stringBuilder.append("data4 varchar(100), ");
        stringBuilder.append("cai integer, ");
        stringBuilder.append("userid varchar(100), ");
        stringBuilder.append("weixinurl varchar(100), ");
        stringBuilder.append("profile varchar(100) ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void A(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists collectTable(");
        stringBuilder.append("id  integer primary key not null, ");
        stringBuilder.append("wid varchar(20), ");
        stringBuilder.append("name varchar(50), ");
        stringBuilder.append("image varchar(100), ");
        stringBuilder.append("neturl varchar(100), ");
        stringBuilder.append("addtime varchar(20), ");
        stringBuilder.append("passtime varchar(20), ");
        stringBuilder.append("comment varchar(50), ");
        stringBuilder.append("content varchar(200), ");
        stringBuilder.append("repost varchar(50), ");
        stringBuilder.append("love integer, ");
        stringBuilder.append("weibo varchar(50), ");
        stringBuilder.append("type varchar(50), ");
        stringBuilder.append("width integer, ");
        stringBuilder.append("height integer, ");
        stringBuilder.append("mid varchar(50), ");
        stringBuilder.append("is_gif varchar(50), ");
        stringBuilder.append("gif_first_frame varchar(100), ");
        stringBuilder.append("data1 varchar(100), ");
        stringBuilder.append("data2 varchar(100), ");
        stringBuilder.append("data3 varchar(100), ");
        stringBuilder.append("data4 varchar(100), ");
        stringBuilder.append("cai integer, ");
        stringBuilder.append("userid varchar(100), ");
        stringBuilder.append("weixinurl varchar(100), ");
        stringBuilder.append("profile varchar(100), ");
        stringBuilder.append("voiceuri varchar(100), ");
        stringBuilder.append("voicetime varchar(50), ");
        stringBuilder.append("playcount varchar(100), ");
        stringBuilder.append("playfcount varchar(100), ");
        stringBuilder.append("noVoiceCmt varchar(100), ");
        stringBuilder.append("voicelength varchar(50) ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void B(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists user_info(");
        stringBuilder.append("user_id varchar(20) primary key not null, ");
        stringBuilder.append("user_name varchar(50), ");
        stringBuilder.append("profile_image varchar(100) ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void C(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("drop table if exists user_info");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void D(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists collectTable(");
        stringBuilder.append("id  integer primary key not null, ");
        stringBuilder.append("wid varchar(20), ");
        stringBuilder.append("name varchar(50), ");
        stringBuilder.append("image varchar(100), ");
        stringBuilder.append("neturl varchar(100), ");
        stringBuilder.append("addtime varchar(20), ");
        stringBuilder.append("passtime varchar(20), ");
        stringBuilder.append("comment varchar(50), ");
        stringBuilder.append("content varchar(200), ");
        stringBuilder.append("repost varchar(50), ");
        stringBuilder.append("love integer, ");
        stringBuilder.append("weibo varchar(50), ");
        stringBuilder.append("type varchar(50), ");
        stringBuilder.append("width integer, ");
        stringBuilder.append("height integer, ");
        stringBuilder.append("mid varchar(50), ");
        stringBuilder.append("is_gif varchar(50), ");
        stringBuilder.append("gif_first_frame varchar(100), ");
        stringBuilder.append("data1 varchar(100), ");
        stringBuilder.append("data2 varchar(100), ");
        stringBuilder.append("data3 varchar(100), ");
        stringBuilder.append("data4 varchar(100), ");
        stringBuilder.append("cai integer, ");
        stringBuilder.append("userid varchar(100), ");
        stringBuilder.append("weixinurl varchar(100), ");
        stringBuilder.append("profile varchar(100), ");
        stringBuilder.append("voiceuri varchar(100), ");
        stringBuilder.append("voicetime varchar(50), ");
        stringBuilder.append("playcount varchar(100), ");
        stringBuilder.append("playfcount varchar(100), ");
        stringBuilder.append("noVoiceCmt varchar(100), ");
        stringBuilder.append("voicelength varchar(50), ");
        stringBuilder.append("videouri varchar(100), ");
        stringBuilder.append("videotime varchar(100), ");
        stringBuilder.append("cnd_img varchar(100), ");
        stringBuilder.append("theme_id integer, ");
        stringBuilder.append("theme_type integer, ");
        stringBuilder.append("theme_name TEXT, ");
        stringBuilder.append("THEME_ID_SET varchar(5), ");
        stringBuilder.append("THEME_TYPE_SET varchar(5), ");
        stringBuilder.append("THEME_NAME_SET VARCHAR(50), ");
        stringBuilder.append("rich_desc TEXT, ");
        stringBuilder.append("rich_img_url TEXT, ");
        stringBuilder.append("rich_source_rl TEXT, ");
        stringBuilder.append("rich_title TEXT, ");
        stringBuilder.append("cacheType TEXT, ");
        stringBuilder.append("original_pid TEXT ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void E(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists weibo(");
        stringBuilder.append("id  integer primary key not null, ");
        stringBuilder.append("uid varchar(50), ");
        stringBuilder.append("name varchar(50), ");
        stringBuilder.append("sex varchar(10), ");
        stringBuilder.append("portrait varchar(100), ");
        stringBuilder.append("result text, ");
        stringBuilder.append("data1 varchar(100), ");
        stringBuilder.append("data2 varchar(100), ");
        stringBuilder.append("data3 varchar(100), ");
        stringBuilder.append("data4 varchar(100), ");
        stringBuilder.append("data5 varchar(100), ");
        stringBuilder.append("sina varchar(100), ");
        stringBuilder.append("tenct varchar(100), ");
        stringBuilder.append("kongjian varchar(100), ");
        stringBuilder.append("kaixin varchar(100), ");
        stringBuilder.append("renren varchar(100), ");
        stringBuilder.append("introduction text, ");
        stringBuilder.append("background_image text, ");
        stringBuilder.append("follow_count varchar(50), ");
        stringBuilder.append("fans_count varchar(50)");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void F(SQLiteDatabase sQLiteDatabase) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table if not exists goods(");
        stringBuffer.append("id integer primary key not null,");
        stringBuffer.append("goodsId varchar(50), ");
        stringBuffer.append("title text, ");
        stringBuffer.append("market_price varchar(50), ");
        stringBuffer.append("price varchar(50), ");
        stringBuffer.append("smallImageUrl varchar(50), ");
        stringBuffer.append("largeImageUrl varchar(50), ");
        stringBuffer.append("smallWidth varchar(50), ");
        stringBuffer.append("smallHeight varchar(50), ");
        stringBuffer.append("largeWidth varchar(50), ");
        stringBuffer.append("largeHeight varchar(50), ");
        stringBuffer.append("valume varchar(50) ");
        stringBuffer.append(")");
        sQLiteDatabase.execSQL(stringBuffer.toString());
    }

    private void G(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists duanzi(");
        stringBuilder.append("id  integer primary key not null, ");
        stringBuilder.append("wid varchar(20), ");
        stringBuilder.append("name varchar(50), ");
        stringBuilder.append("addtime varchar(20), ");
        stringBuilder.append("passtime varchar(20), ");
        stringBuilder.append("comment varchar(50), ");
        stringBuilder.append("content varchar(200), ");
        stringBuilder.append("neturl varchar(100), ");
        stringBuilder.append("repost varchar(50), ");
        stringBuilder.append("love integer, ");
        stringBuilder.append("weibo varchar(50), ");
        stringBuilder.append("type varchar(50), ");
        stringBuilder.append("mid varchar(50), ");
        stringBuilder.append("data1 varchar(100), ");
        stringBuilder.append("data2 varchar(100), ");
        stringBuilder.append("data3 varchar(100), ");
        stringBuilder.append("data4 varchar(100), ");
        stringBuilder.append("cai integer, ");
        stringBuilder.append("userid varchar(100), ");
        stringBuilder.append("weixinurl varchar(100), ");
        stringBuilder.append("profile varchar(100) ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void H(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists sound(");
        stringBuilder.append("id  integer primary key not null, ");
        stringBuilder.append("wid varchar(20), ");
        stringBuilder.append("name varchar(50), ");
        stringBuilder.append("image varchar(100), ");
        stringBuilder.append("neturl varchar(100), ");
        stringBuilder.append("addtime varchar(20), ");
        stringBuilder.append("passtime varchar(20), ");
        stringBuilder.append("comment varchar(50), ");
        stringBuilder.append("content varchar(200), ");
        stringBuilder.append("repost varchar(50), ");
        stringBuilder.append("love integer, ");
        stringBuilder.append("weibo varchar(50), ");
        stringBuilder.append("type varchar(50), ");
        stringBuilder.append("width integer, ");
        stringBuilder.append("height integer, ");
        stringBuilder.append("mid varchar(50), ");
        stringBuilder.append("is_gif varchar(50), ");
        stringBuilder.append("gif_first_frame varchar(100), ");
        stringBuilder.append("cai integer, ");
        stringBuilder.append("weixinurl varchar(100), ");
        stringBuilder.append("profile varchar(100), ");
        stringBuilder.append("voiceuri varchar(100), ");
        stringBuilder.append("voicetime varchar(50), ");
        stringBuilder.append("voicelength varchar(100), ");
        stringBuilder.append("playcount varchar(100), ");
        stringBuilder.append("playfcount varchar(100), ");
        stringBuilder.append("noVoiceCmt varchar(100), ");
        stringBuilder.append("userid varchar(100), ");
        stringBuilder.append("data1 varchar(100), ");
        stringBuilder.append("data2 varchar(100), ");
        stringBuilder.append("data3 varchar(100), ");
        stringBuilder.append("data4 varchar(100), ");
        stringBuilder.append("length varchar(100), ");
        stringBuilder.append("cnd_img VARCHAR(100) ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void I(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists video(");
        stringBuilder.append("id  integer primary key not null, ");
        stringBuilder.append("wid varchar(20), ");
        stringBuilder.append("name varchar(50), ");
        stringBuilder.append("image varchar(100), ");
        stringBuilder.append("neturl varchar(100), ");
        stringBuilder.append("addtime varchar(20), ");
        stringBuilder.append("passtime varchar(20), ");
        stringBuilder.append("comment varchar(50), ");
        stringBuilder.append("content varchar(200), ");
        stringBuilder.append("repost varchar(50), ");
        stringBuilder.append("love integer, ");
        stringBuilder.append("weibo varchar(50), ");
        stringBuilder.append("type varchar(50), ");
        stringBuilder.append("width integer, ");
        stringBuilder.append("height integer, ");
        stringBuilder.append("mid varchar(50), ");
        stringBuilder.append("is_gif varchar(50), ");
        stringBuilder.append("gif_first_frame varchar(100), ");
        stringBuilder.append("cai integer, ");
        stringBuilder.append("weixinurl varchar(100), ");
        stringBuilder.append("profile varchar(100), ");
        stringBuilder.append("playcount varchar(100), ");
        stringBuilder.append("playfcount varchar(100), ");
        stringBuilder.append("noVoiceCmt varchar(100), ");
        stringBuilder.append("userid varchar(100), ");
        stringBuilder.append("data1 varchar(100), ");
        stringBuilder.append("data2 varchar(100), ");
        stringBuilder.append("data3 varchar(100), ");
        stringBuilder.append("data4 varchar(100), ");
        stringBuilder.append("videouri varchar(100), ");
        stringBuilder.append("videotime varchar(100), ");
        stringBuilder.append("length varchar(100) ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void J(SQLiteDatabase sQLiteDatabase) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table if not exists comment(");
        stringBuffer.append("id integer primary key not null, ");
        stringBuffer.append("commentId varchar(50), ");
        stringBuffer.append("count varchar(50), ");
        stringBuffer.append("content varchar(100), ");
        stringBuffer.append("voiceuri varchar(100), ");
        stringBuffer.append("voicetime varchar(100), ");
        stringBuffer.append("data1 varchar(100), ");
        stringBuffer.append("data2 varchar(100), ");
        stringBuffer.append("data3 varchar(100) ");
        stringBuffer.append(")");
        sQLiteDatabase.execSQL(stringBuffer.toString());
    }

    private void K(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists ad(");
        stringBuilder.append("id integer primary key not null,");
        stringBuilder.append("adId varchar(50), ");
        stringBuilder.append("state integer, ");
        stringBuilder.append("url varchar(50), ");
        stringBuilder.append("data1 varchar(100), ");
        stringBuilder.append("data2 varchar(100), ");
        stringBuilder.append("data3 varchar(100), ");
        stringBuilder.append("data4 varchar(100) ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void L(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists draft(");
        stringBuilder.append("id integer primary key not null,");
        stringBuilder.append("uid varchar(50), ");
        stringBuilder.append("state integer, ");
        stringBuilder.append("bimage TEXT, ");
        stringBuilder.append("voice TEXT, ");
        stringBuilder.append("content TEXT, ");
        stringBuilder.append("bvoiceid integer, ");
        stringBuilder.append("createTime TEXT, ");
        stringBuilder.append("voicetime integer ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void M(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists rule(");
        stringBuilder.append("name TEXT primary key not null,");
        stringBuilder.append("money integer, ");
        stringBuilder.append("up_limit integer ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void N(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists newCacheTable(");
        stringBuilder.append("id integer NOT NULL PRIMARY KEY, ");
        stringBuilder.append("wid varchar(20),");
        stringBuilder.append("screen_name varchar(50),");
        stringBuilder.append("mid varchar(50),");
        stringBuilder.append("created_at VARCHAR(20),");
        stringBuilder.append("passtime varchar(20),");
        stringBuilder.append("text varchar(200),");
        stringBuilder.append("type varchar(50),");
        stringBuilder.append("user_id varchar(100),");
        stringBuilder.append("width integer,");
        stringBuilder.append("height integer,");
        stringBuilder.append("profile_image varchar(100),");
        stringBuilder.append("image1 varchar(100),");
        stringBuilder.append("weixinurl varchar(100),");
        stringBuilder.append("is_gif varchar(50),");
        stringBuilder.append("love integer,");
        stringBuilder.append("hate integer,");
        stringBuilder.append("comment VARCHAR(50),");
        stringBuilder.append("bookmark INTEGER,");
        stringBuilder.append("forward VARCHAR(50),");
        stringBuilder.append("gifFistFrame varchar(100),");
        stringBuilder.append("voiceuri VARCHAR(100),");
        stringBuilder.append("voicetime VARCHAR(100),");
        stringBuilder.append("playcount VARCHAR(50),");
        stringBuilder.append("playfcount VARCHAR(50),");
        stringBuilder.append("readid VARCHAR(100),");
        stringBuilder.append("hasdata VARCHAR(20)        ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void O(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists recommend_user(");
        stringBuilder.append("id varchar(20),");
        stringBuilder.append("userid varchar(20),");
        stringBuilder.append("username varchar(50),");
        stringBuilder.append("profile_image varchar(100),");
        stringBuilder.append("introduction varchar(100),");
        stringBuilder.append("pid varchar(20),");
        stringBuilder.append("body varchar(50),");
        stringBuilder.append("image varchar(100),");
        stringBuilder.append("type varchar(20)");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void P(SQLiteDatabase sQLiteDatabase) {
        Q(sQLiteDatabase);
        sQLiteDatabase.execSQL("ALTER TABLE follow_list ADD profile_image varchar(100)");
        sQLiteDatabase.execSQL("ALTER TABLE follow_list ADD recent_contact_time INTEGER");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD original_pid varchar(20)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD tiezi_count varchar(50)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD cacheType TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD original_pid TEXT");
    }

    private void Q(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists table_selector_symbol_label(");
        stringBuilder.append("id varchar(20),");
        stringBuilder.append("userid varchar(20),");
        stringBuilder.append("username varchar(50),");
        stringBuilder.append("profile_image varchar(100),");
        stringBuilder.append("introduction varchar(100),");
        stringBuilder.append("pid varchar(20),");
        stringBuilder.append("body varchar(50),");
        stringBuilder.append("image varchar(100),");
        stringBuilder.append("type varchar(20)");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(android.database.sqlite.SQLiteDatabase r8) {
        /*
        r7 = this;
        r0 = 0;
        r2 = 0;
        r3 = a;
        monitor-enter(r3);
        r1 = 1;
        r4 = "SELECT count(*) FROM sqlite_master WHERE type='table' AND name='newCacheTable'";
        r5 = 0;
        r0 = r8.rawQuery(r4, r5);	 Catch:{ Exception -> 0x0025, all -> 0x002f }
        r4 = r0.moveToFirst();	 Catch:{ Exception -> 0x0025, all -> 0x0039 }
        if (r4 == 0) goto L_0x001b;
    L_0x0013:
        r4 = 0;
        r4 = r0.getInt(r4);	 Catch:{ Exception -> 0x0025, all -> 0x0039 }
        if (r4 > 0) goto L_0x001b;
    L_0x001a:
        r1 = r2;
    L_0x001b:
        r0.close();	 Catch:{ Exception -> 0x0025, all -> 0x0039 }
        if (r0 == 0) goto L_0x0023;
    L_0x0020:
        r0.close();	 Catch:{ all -> 0x002c }
    L_0x0023:
        monitor-exit(r3);	 Catch:{ all -> 0x002c }
        return r1;
    L_0x0025:
        r2 = move-exception;
        if (r0 == 0) goto L_0x0023;
    L_0x0028:
        r0.close();	 Catch:{ all -> 0x002c }
        goto L_0x0023;
    L_0x002c:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x002c }
        throw r0;
    L_0x002f:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x0033:
        if (r1 == 0) goto L_0x0038;
    L_0x0035:
        r1.close();	 Catch:{ all -> 0x002c }
    L_0x0038:
        throw r0;	 Catch:{ all -> 0x002c }
    L_0x0039:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.c.c.a(android.database.sqlite.SQLiteDatabase):boolean");
    }

    private void b(Context context) {
        Editor edit = context.getSharedPreferences("weiboprefer", 0).edit();
        edit.clear();
        edit.commit();
    }

    private void R(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD profile varchar(100)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD profile varchar(100)");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD profile varchar(100)");
    }

    private void S(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD portrait varchar(100)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD sex varchar(10)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD name varchar(50)");
    }

    private void T(SQLiteDatabase sQLiteDatabase) {
        K(sQLiteDatabase);
    }

    private void U(SQLiteDatabase sQLiteDatabase) {
        H(sQLiteDatabase);
        sQLiteDatabase.execSQL("ALTER TABLE comment ADD voiceuri varchar(50)");
        sQLiteDatabase.execSQL("ALTER TABLE comment ADD voicetime varchar(50)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD voiceuri varchar(100)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD voicetime varchar(50)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD voicelength varchar(50)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD playcount varchar(100)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD playfcount varchar(50)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD noVoiceCmt varchar(50)");
        L(sQLiteDatabase);
    }

    private void V(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE love ADD cai_flag varchar(20)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD introduction text");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD background_image text");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD follow_count varchar(50)");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD fans_count varchar(50)");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD userid varchar(100)");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD userid varchar(100)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD userid varchar(100)");
        if (this.d == 14) {
            sQLiteDatabase.execSQL("ALTER TABLE sound ADD userid varchar(100)");
        }
        sQLiteDatabase.execSQL("DELETE FROM cacheTable");
        sQLiteDatabase.execSQL("DELETE FROM duanzi");
        sQLiteDatabase.execSQL("DELETE FROM sound");
    }

    private void W(SQLiteDatabase sQLiteDatabase) {
        N(sQLiteDatabase);
    }

    private void X(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD hasdata varchar(20)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD passtime varchar(20)");
    }

    private void Y(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD passtime varchar(20)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD passtime varchar(20)");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD passtime varchar(20)");
        if (this.d > 13) {
            sQLiteDatabase.execSQL("ALTER TABLE sound ADD passtime varchar(20)");
        }
    }

    private void Z(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD cacheType int");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD statusText varchar(100)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD voiceBgId int");
    }

    private void aa(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("drop TABLE sound");
        H(sQLiteDatabase);
    }

    private void ab(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD reserve TEXT");
    }

    private void ac(SQLiteDatabase sQLiteDatabase) {
        I(sQLiteDatabase);
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD length varchar(100)");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD length varchar(100)");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD video TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD videotime integer");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD videouri VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD videotime VARCHAR(100)");
    }

    private void ad(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD videouri VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD videotime VARCHAR(100)");
    }

    private void ae(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD cnd_img VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD cnd_img VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD cnd_img VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD cnd_img VARCHAR(100)");
    }

    private void af(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD bookmark integer");
        sQLiteDatabase.execSQL("ALTER TABLE weibo ADD phone integer");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD theme_id integer");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD theme_type integer");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD theme_name TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD theme_id integer");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD theme_type integer");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD theme_name TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD theme_id integer");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD theme_type integer");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD theme_name TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD theme_id integer");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD theme_type integer");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD theme_name TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD theme_id integer");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD theme_type integer");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD theme_name TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD theme_id integer");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD theme_type integer");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD theme_name TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD theme_id integer");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD theme_type integer");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD theme_name TEXT");
    }

    private void ag(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD hot_cmt_username VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD hot_cmt_content VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD hot_cmt_reply_username VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD hot_cmt_reply_content VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD hot_cmt_username VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD hot_cmt_content VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD hot_cmt_reply_username VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD hot_cmt_reply_content VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD hot_cmt_username VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD hot_cmt_content VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD hot_cmt_voice_comment_url VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD hot_cmt_voice_comment_time VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD hot_cmt_reply_username VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD hot_cmt_reply_content VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD hot_cmt_reply_voice_comment_url VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD hot_cmt_reply_voice_comment_time VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD hot_cmt_username VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD hot_cmt_content VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD hot_cmt_voice_comment_url VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD hot_cmt_voice_comment_time VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD hot_cmt_reply_username VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD hot_cmt_reply_content VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD hot_cmt_reply_voice_comment_url VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD hot_cmt_reply_voice_comment_time VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD tid varchar(100)");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD landuri varchar(100)");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD isWatermark INTEGER(10)");
    }

    private void ah(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD hot_cmt_username VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD hot_cmt_content VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD hot_cmt_voice_comment_url VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD hot_cmt_voice_comment_time VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD hot_cmt_reply_username VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD hot_cmt_reply_content VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD hot_cmt_reply_voice_comment_url VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD hot_cmt_reply_voice_comment_time VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD topic_tag_type VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD topic_tag_type VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD topic_tag_type VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD topic_tag_type VARCHAR(100)");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD topic_tag_type VARCHAR(100)");
    }

    private void ai(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists follow_list(");
        stringBuilder.append("user_id  varchar(10), ");
        stringBuilder.append("user_name varchar(50), ");
        stringBuilder.append("user_sex varchar(5), ");
        stringBuilder.append("fans_count varchar(10), ");
        stringBuilder.append("follow_count varchar(10), ");
        stringBuilder.append("relationship varchar(5) ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void aj(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists subscribe_Label(");
        stringBuilder.append("id  integer primary key not null, ");
        stringBuilder.append("theme_id varchar(5), ");
        stringBuilder.append("theme_name varchar(50), ");
        stringBuilder.append("image_list varchar(100), ");
        stringBuilder.append("sub_number varchar(20), ");
        stringBuilder.append("is_default varchar(5), ");
        stringBuilder.append("is_sub varchar(20) ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void ak(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists recommend_Label(");
        stringBuilder.append("theme_id  varchar(5), ");
        stringBuilder.append("theme_name varchar(50), ");
        stringBuilder.append("image_list varchar(100), ");
        stringBuilder.append("sub_number varchar(20), ");
        stringBuilder.append("is_default varchar(5), ");
        stringBuilder.append("is_sub varchar(20) ");
        stringBuilder.append(")");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    private void al(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD THEME_ID_SET VARCHAR(5)");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD THEME_TYPE_SET VARCHAR(5)");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD THEME_NAME_SET VARCHAR(50)");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD THEME_ID_SET VARCHAR(5)");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD THEME_TYPE_SET VARCHAR(5)");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD THEME_NAME_SET VARCHAR(50)");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD THEME_ID_SET VARCHAR(5)");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD THEME_TYPE_SET VARCHAR(5)");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD THEME_NAME_SET VARCHAR(50)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD THEME_ID_SET VARCHAR(5)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD THEME_TYPE_SET VARCHAR(5)");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD THEME_NAME_SET VARCHAR(50)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD THEME_ID_SET VARCHAR(5)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD THEME_TYPE_SET VARCHAR(5)");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD THEME_NAME_SET VARCHAR(50)");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD THEME_ID_SET VARCHAR(5)");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD THEME_TYPE_SET VARCHAR(5)");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD THEME_NAME_SET VARCHAR(50)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD THEME_ID_SET VARCHAR(5)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD THEME_TYPE_SET VARCHAR(5)");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD THEME_NAME_SET VARCHAR(50)");
    }

    private void am(SQLiteDatabase sQLiteDatabase) {
        ai(sQLiteDatabase);
        aj(sQLiteDatabase);
        ak(sQLiteDatabase);
        al(sQLiteDatabase);
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD rich_desc TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD rich_img_url TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD rich_source_rl TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE newCacheTable ADD rich_title TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD rich_desc TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD rich_img_url TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD rich_source_rl TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE draft ADD rich_title TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD rich_desc TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD rich_img_url TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD rich_source_rl TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE collectTable ADD rich_title TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD rich_desc TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD rich_img_url TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD rich_source_rl TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE cacheTable ADD rich_title TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD rich_desc TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD rich_img_url TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD rich_source_rl TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE duanzi ADD rich_title TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD rich_desc TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD rich_img_url TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD rich_source_rl TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE sound ADD rich_title TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD rich_desc TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD rich_img_url TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD rich_source_rl TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE video ADD rich_title TEXT");
    }
}
