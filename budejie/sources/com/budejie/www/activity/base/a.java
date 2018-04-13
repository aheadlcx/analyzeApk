package com.budejie.www.activity.base;

import android.content.Context;
import android.content.SharedPreferences;

public class a {
    public e A = new e("nearby_img_last_refreshtime", System.currentTimeMillis());
    public e B = new e("nearby_text_last_refreshtime", System.currentTimeMillis());
    public e C = new e("nearby_voice_last_refreshtime", System.currentTimeMillis());
    public e D = new e("nearby_video_last_refreshtime", System.currentTimeMillis());
    public e E = new e("nearby_all_last_refreshtime", System.currentTimeMillis());
    public e F = new e("random_img_last_refreshtime", System.currentTimeMillis());
    public e G = new e("random_text_last_refreshtime", System.currentTimeMillis());
    public e H = new e("random_voice_last_refreshtime", System.currentTimeMillis());
    public e I = new e("random_video_last_refreshtime", System.currentTimeMillis());
    public e J = new e("random_all_last_refreshtime", System.currentTimeMillis());
    public e K = new e("ranking_img_last_refreshtime", System.currentTimeMillis());
    public e L = new e("ranking_text_last_refreshtime", System.currentTimeMillis());
    public e M = new e("ranking_voice_last_refreshtime", System.currentTimeMillis());
    public e N = new e("ranking_video_last_refreshtime", System.currentTimeMillis());
    public e O = new e("ranking_all_last_refreshtime", System.currentTimeMillis());
    public g P = new g(this, "is_first_launch", null);
    public e Q = new e("open_app_time", 0);
    public e R = new e("voice_maxid", 0);
    public e S = new e("video_maxid", 0);
    public e T = new e("image_maxid", 0);
    public e U = new e("text_maxid", 0);
    public e V = new e("new_item_login_last_show_time", -1);
    public e W = new e("new_item_share_last_show_time", -1);
    public e X = new e("new_item_invite_last_show_time", -1);
    public e Y = new e("new_item_user_info_gender_last_show_time", -1);
    public e Z = new e("new_item_user_info_age_group_last_show_time", -1);
    public a a = new a("saveposition", true);
    public e aa = new e("new_item_user_info_education_last_show_time", -1);
    public e ab = new e("new_item_operation_last_show_time", -1);
    public e ac = new e("get_my_square_icon_data_time", -1);
    public e ad = new e("upload_contacts_period_time", 0);
    public a ae = new a("forward_and_collect", false);
    public g af = new g(this, "last_read_tag", null);
    public g ag = new g(this, "last_read_topic_type_essence", null);
    public g ah = new g(this, "last_read_topic_type_new", null);
    public g ai = new g(this, "sub_label_uid_state", "");
    public g aj = new g(this, "re_label_uid_state", "");
    public a$d ak = new a$d(this, "ltwo_uploadcontact", 0, null);
    public a al = new a("is_show_dialog", false);
    public c am = new c("add_theme_click_num", 0);
    private final Context an;
    private SharedPreferences ao;
    public a b = new a("is_top_title_bar_auto_hide", false);
    public a c = new a("is_top_title_bar_auto_hide_umeng", true);
    public a d = new a("is_top_navigation_auto_hide", false);
    public a e = new a("is_top_navigation_auto_hide_umeng", false);
    public a f = new a("is_bottom_navigation_auto_hide", false);
    public a g = new a("is_bottom_navigation_auto_hide_umeng", false);
    public a h = new a("is_click_look", false);
    public a i = new a("is_click_after", false);
    public a j = new a("is_first_enter", true);
    public a$f k = new a$f(this);
    public g l = new g(this, "recommendLbale_name", "0");
    public g m = new g(this, "recommendLbale_id", "0");
    public c n = new c(this);
    public e o = new e("save_system_time", 0);
    public e p = new e("re_current_itme", 0);
    public e q = new e("essence_img_last_refreshtime", System.currentTimeMillis());
    public e r = new e("essence_text_last_refreshtime", System.currentTimeMillis());
    public e s = new e("essence_voice_last_refreshtime", System.currentTimeMillis());
    public e t = new e("essence_video_last_refreshtime", System.currentTimeMillis());
    public e u = new e("essence_all_last_refreshtime", System.currentTimeMillis());
    public e v = new e("new_img_last_refreshtime", System.currentTimeMillis());
    public e w = new e("new_text_last_refreshtime", System.currentTimeMillis());
    public e x = new e("new_voice_last_refreshtime", System.currentTimeMillis());
    public e y = new e("new_video_last_refreshtime", System.currentTimeMillis());
    public e z = new e("new_all_last_refreshtime", System.currentTimeMillis());

    public abstract class b<T> {
        private final String a;
        final /* synthetic */ a b;
        private T c;

        public b(a aVar, String str, T t) {
            this.b = aVar;
            this.a = str;
            this.c = t;
        }

        protected T b() {
            return this.c;
        }

        public String c() {
            return this.a;
        }
    }

    public class a extends b<Boolean> {
        final /* synthetic */ a a;

        private a(a aVar, String str, boolean z) {
            this.a = aVar;
            super(aVar, str, Boolean.valueOf(z));
        }

        public Boolean a() {
            return Boolean.valueOf(this.a.ao.getBoolean(c(), ((Boolean) b()).booleanValue()));
        }

        public boolean a(Boolean bool) {
            return this.a.ao.edit().putBoolean(c(), bool.booleanValue()).commit();
        }
    }

    public class c extends b<Integer> {
        final /* synthetic */ a a;

        public c(a aVar) {
            this.a = aVar;
            super(aVar, null, null);
        }

        private c(a aVar, String str, int i) {
            this.a = aVar;
            super(aVar, str, Integer.valueOf(i));
        }

        public Integer a() {
            return Integer.valueOf(this.a.ao.getInt(c(), ((Integer) b()).intValue()));
        }

        public boolean a(Integer num) {
            return this.a.ao.edit().putInt(c(), num.intValue()).commit();
        }

        public boolean a(String str, int i) {
            return this.a.ao.edit().putInt(str, i).commit();
        }

        public int a(String str) {
            return this.a.ao.getInt(str, 0);
        }
    }

    public class e extends b<Long> {
        final /* synthetic */ a a;

        private e(a aVar, String str, long j) {
            this.a = aVar;
            super(aVar, str, Long.valueOf(j));
        }

        public Long a() {
            return Long.valueOf(this.a.ao.getLong(c(), ((Long) b()).longValue()));
        }

        public boolean a(Long l) {
            return this.a.ao.edit().putLong(c(), l.longValue()).commit();
        }
    }

    public class g extends b<String> {
        final /* synthetic */ a a;

        public g(a aVar, String str, String str2) {
            this.a = aVar;
            super(aVar, str, str2);
        }

        public String a() {
            return this.a.ao.getString(c(), (String) b());
        }

        public boolean a(String str) {
            return this.a.ao.edit().putString(c(), str).commit();
        }
    }

    public a(Context context) {
        this.an = context;
        this.ao = context.getSharedPreferences("com.budejie.settings", 0);
    }
}
