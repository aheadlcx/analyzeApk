package com.budejie.www.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Adapter;
import com.budejie.www.bean.FriendsListResults;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.bean.SuggestedFollowsResults;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class as {
    private static as a;
    private a b = new a(this, "list", "0");
    private a c = new a(this, "list", "0");
    private as$b d;
    private List<WeakReference<Handler>> e = new ArrayList();

    public class a {
        final /* synthetic */ as a;
        private String b;
        private String c;

        public a(as asVar) {
            this.a = asVar;
        }

        public a(as asVar, String str, String str2) {
            this.a = asVar;
            this.b = str;
            this.c = str2;
        }

        public void a(String str) {
            this.b = str;
        }

        public String a() {
            return this.b;
        }

        public void b(String str) {
            this.c = str;
        }

        public String b() {
            return this.c;
        }
    }

    public List<WeakReference<Handler>> a() {
        return this.e;
    }

    public void a(SuggestedFollowsListItem suggestedFollowsListItem) {
        if (!this.e.isEmpty()) {
            for (int i = 0; i < this.e.size(); i++) {
                Handler handler = (Handler) ((WeakReference) this.e.get(i)).get();
                if (handler != null) {
                    Message obtainMessage = handler.obtainMessage();
                    obtainMessage.what = 100;
                    obtainMessage.obj = suggestedFollowsListItem;
                    handler.sendMessage(obtainMessage);
                }
            }
        }
    }

    public void b(SuggestedFollowsListItem suggestedFollowsListItem) {
        if (!this.e.isEmpty()) {
            for (int i = 0; i < this.e.size(); i++) {
                Handler handler = (Handler) ((WeakReference) this.e.get(i)).get();
                if (handler != null) {
                    Message obtainMessage = handler.obtainMessage();
                    obtainMessage.what = 101;
                    obtainMessage.obj = suggestedFollowsListItem;
                    handler.sendMessage(obtainMessage);
                }
            }
        }
    }

    private as() {
    }

    public static as b() {
        if (a == null) {
            a = new as();
        }
        return a;
    }

    public a c() {
        return new a(this, "list", "0");
    }

    public List<SuggestedFollowsListItem> a(SuggestedFollowsResults suggestedFollowsResults) {
        List<SuggestedFollowsListItem> arrayList = new ArrayList();
        if (suggestedFollowsResults != null) {
            List<SuggestedFollowsListItem> list = suggestedFollowsResults.list;
            if (!(list == null || list.isEmpty())) {
                for (SuggestedFollowsListItem suggestedFollowsListItem : list) {
                    suggestedFollowsListItem.mark = 1;
                    arrayList.add(suggestedFollowsListItem);
                }
            }
            Collection collection = suggestedFollowsResults.top_list;
            if (!(collection == null || collection.isEmpty())) {
                arrayList.addAll(collection);
            }
            collection = suggestedFollowsResults.hot_list;
            if (!(collection == null || collection.isEmpty())) {
                arrayList.addAll(collection);
            }
            if (suggestedFollowsResults.info != null) {
                this.b = new a(this);
                this.b.b(suggestedFollowsResults.info.last_coord);
                this.b.a(suggestedFollowsResults.info.last_flag);
            }
        }
        return arrayList;
    }

    public List<SuggestedFollowsListItem> a(Context context, FriendsListResults friendsListResults, List<SuggestedFollowsListItem> list, List<SuggestedFollowsListItem> list2, List<SuggestedFollowsListItem> list3, boolean z) {
        List<SuggestedFollowsListItem> arrayList = new ArrayList();
        String b = ai.b(context);
        if (friendsListResults != null) {
            SuggestedFollowsListItem suggestedFollowsListItem;
            if (!z) {
                list.clear();
                list2.clear();
                list3.clear();
            }
            List list4 = friendsListResults.list;
            this.c = new a(this);
            if (!(list4 == null || list4.isEmpty())) {
                if (list.isEmpty()) {
                    suggestedFollowsListItem = new SuggestedFollowsListItem();
                    suggestedFollowsListItem.title_type = 1;
                    suggestedFollowsListItem.title = "已加入百思不得姐好友";
                    list.add(suggestedFollowsListItem);
                    arrayList.add(suggestedFollowsListItem);
                }
                for (int size = list4.size() - 1; size >= 0; size--) {
                    suggestedFollowsListItem = (SuggestedFollowsListItem) list4.get(size);
                    suggestedFollowsListItem.title_type = 2;
                    if (b.equals(suggestedFollowsListItem.uid)) {
                        list4.remove(suggestedFollowsListItem);
                    } else {
                        list.add(suggestedFollowsListItem);
                        arrayList.add(suggestedFollowsListItem);
                    }
                }
                this.c.a("list");
            }
            List<SuggestedFollowsListItem> list5 = friendsListResults.social_list;
            if (!(list5 == null || list5.isEmpty())) {
                if (list2.isEmpty()) {
                    suggestedFollowsListItem = new SuggestedFollowsListItem();
                    suggestedFollowsListItem.title_type = 1;
                    suggestedFollowsListItem.title = "未加入百思不得姐好友";
                    list2.add(suggestedFollowsListItem);
                    arrayList.add(suggestedFollowsListItem);
                }
                for (SuggestedFollowsListItem suggestedFollowsListItem2 : list5) {
                    suggestedFollowsListItem2.title_type = 3;
                    if (b.equals(suggestedFollowsListItem2.uid)) {
                        list5.remove(suggestedFollowsListItem2);
                    } else {
                        list2.add(suggestedFollowsListItem2);
                        arrayList.add(suggestedFollowsListItem2);
                    }
                }
                this.c.a("social_list");
            }
            if (arrayList.size() > 0) {
                this.c.b(((SuggestedFollowsListItem) arrayList.get(arrayList.size() - 1)).id);
            }
        }
        return arrayList;
    }

    public a d() {
        return this.b;
    }

    public a e() {
        return this.c;
    }

    public void a(String str, Integer num) {
        this.d = new as$b(this, str, num);
    }

    public void a(Adapter adapter) {
        if (this.d != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                Object item = adapter.getItem(i);
                if (item instanceof SuggestedFollowsListItem) {
                    SuggestedFollowsListItem suggestedFollowsListItem = (SuggestedFollowsListItem) item;
                    if (this.d.a().equals(suggestedFollowsListItem.uid)) {
                        suggestedFollowsListItem.is_follow = this.d.b().intValue();
                        break;
                    }
                }
            }
            this.d = null;
        }
    }
}
