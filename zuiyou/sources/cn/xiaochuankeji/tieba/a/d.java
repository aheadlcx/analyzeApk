package cn.xiaochuankeji.tieba.a;

import android.content.ContentValues;
import android.support.annotation.WorkerThread;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import com.alibaba.fastjson.JSON;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.wcdb.Cursor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public final class d {
    public static void a(final PostDataBean postDataBean) {
        a.p().d().execute(new Runnable() {
            public void run() {
                d.b(JSON.toJSONString(postDataBean), postDataBean.postId);
            }
        });
    }

    @WorkerThread
    private static void b(String str, long j) {
        cn.xiaochuankeji.tieba.background.modules.a.a g = a.g();
        if (!g.d()) {
            long c = g.c();
            ContentValues contentValues = new ContentValues();
            contentValues.put("mid", Long.valueOf(c));
            contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("post_json_str", str);
            Cursor rawQuery = j.a().rawQuery("select post_id,post_json_str from post_config where post_id =" + j + " and mid=" + c + VoiceWakeuperAidl.PARAMS_SEPARATE, null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        j.a().update("post_config", contentValues, "post_id=? and mid=?", new String[]{String.valueOf(j), String.valueOf(c)});
                        if (rawQuery != null && !rawQuery.isClosed()) {
                            rawQuery.close();
                            return;
                        }
                    }
                } catch (Throwable th) {
                    if (!(rawQuery == null || rawQuery.isClosed())) {
                        rawQuery.close();
                    }
                }
            }
            contentValues.put("post_id", Long.valueOf(j));
            j.a().insert("post_config", null, contentValues);
            if (rawQuery != null) {
            }
        }
    }

    @WorkerThread
    public static void a(Post post) {
        cn.xiaochuankeji.tieba.background.modules.a.a g = a.g();
        if (!g.d()) {
            long c = g.c();
            ContentValues contentValues = new ContentValues();
            contentValues.put("mid", Long.valueOf(c));
            contentValues.put("update_time", Long.valueOf(System.currentTimeMillis()));
            try {
                contentValues.put("post_json_str", post.serializeTo().toString());
                Cursor rawQuery = j.a().rawQuery("select post_id,post_json_str from post_config where post_id =" + post._ID + " and mid=" + c + VoiceWakeuperAidl.PARAMS_SEPARATE, null);
                if (rawQuery != null) {
                    try {
                        if (rawQuery.moveToFirst()) {
                            j.a().update("post_config", contentValues, "post_id=? and mid=?", new String[]{String.valueOf(post._ID), String.valueOf(c)});
                            if (rawQuery != null && !rawQuery.isClosed()) {
                                rawQuery.close();
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        if (!(rawQuery == null || rawQuery.isClosed())) {
                            rawQuery.close();
                        }
                    }
                }
                contentValues.put("post_id", Long.valueOf(post._ID));
                j.a().insert("post_config", null, contentValues);
                if (rawQuery != null) {
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @WorkerThread
    public static long a() {
        long j = 0;
        cn.xiaochuankeji.tieba.background.modules.a.a g = a.g();
        if (!g.d()) {
            Cursor rawQuery = j.a().rawQuery("select count(post_id) from post_config where mid=" + g.c() + VoiceWakeuperAidl.PARAMS_SEPARATE, null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        j = rawQuery.getLong(0);
                        if (j >= 500) {
                            j.a().execSQL("delete from post_config where rowid not in (select rowid from post_config order by update_time desc limit 450);");
                        }
                        if (!(rawQuery == null || rawQuery.isClosed())) {
                            rawQuery.close();
                        }
                    }
                } catch (Throwable th) {
                    if (!(rawQuery == null || rawQuery.isClosed())) {
                        rawQuery.close();
                    }
                }
            }
            if (!(rawQuery == null || rawQuery.isClosed())) {
                rawQuery.close();
            }
        }
        return j;
    }

    public static void a(rx.b.a aVar) {
        cn.xiaochuankeji.tieba.background.modules.a.a g = a.g();
        if (!g.d()) {
            long c = g.c();
            j.a().delete("post_config", "mid=?", new String[]{String.valueOf(c)});
            if (aVar != null) {
                aVar.call();
            }
        } else if (aVar != null) {
            aVar.call();
        }
    }

    @WorkerThread
    public static ArrayList<Post> b() {
        ArrayList<Post> arrayList = new ArrayList();
        cn.xiaochuankeji.tieba.background.modules.a.a g = a.g();
        if (!g.d()) {
            Cursor rawQuery = j.a().rawQuery("select post_id,post_json_str,mid from post_config where mid=" + g.c() + " order by update_time desc;", null);
            if (rawQuery != null) {
                while (rawQuery.moveToNext()) {
                    try {
                        arrayList.add(new Post(new JSONObject(rawQuery.getString(1))));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        if (!(rawQuery == null || rawQuery.isClosed())) {
                            rawQuery.close();
                        }
                    }
                }
            }
            if (!(rawQuery == null || rawQuery.isClosed())) {
                rawQuery.close();
            }
        }
        return arrayList;
    }

    @WorkerThread
    public static List<String> c() {
        Object linkedList = new LinkedList();
        cn.xiaochuankeji.tieba.background.modules.a.a g = a.g();
        if (!g.d()) {
            Cursor rawQuery = j.a().rawQuery("select post_id,post_json_str,mid from post_config where mid=" + g.c() + " order by update_time desc;", null);
            if (rawQuery != null) {
                while (rawQuery.moveToNext()) {
                    try {
                        linkedList.add(rawQuery.getString(1));
                    } catch (Throwable th) {
                        if (!(rawQuery == null || rawQuery.isClosed())) {
                            rawQuery.close();
                        }
                    }
                }
            }
            if (!(rawQuery == null || rawQuery.isClosed())) {
                rawQuery.close();
            }
        }
        return linkedList;
    }
}
