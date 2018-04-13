package cn.xiaochuankeji.tieba.ui.chat.a;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.hollow.HollowService;
import cn.xiaochuankeji.tieba.api.user.UserService;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.network.c;
import cn.xiaochuankeji.tieba.push.b.d;
import cn.xiaochuankeji.tieba.push.data.XSession;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.f;
import com.alibaba.fastjson.JSONObject;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import rx.e;

public class a {
    private a a;
    private Activity b;
    private int c = 0;

    public interface a {
        void a();

        void b();

        void c();

        void d();
    }

    public a(Activity activity, a aVar) {
        this.b = activity;
        this.a = aVar;
    }

    public void a(XSession xSession, View view, boolean z) {
        a(xSession, view, z, true);
    }

    public void a(final XSession xSession, View view, boolean z, boolean z2) {
        if (this.b != null && !this.b.isFinishing()) {
            final boolean z3 = xSession.session_type == 2;
            PopupMenu popupMenu = new PopupMenu(this.b, view);
            Menu menu = popupMenu.getMenu();
            if (d.c(xSession.x_sid)) {
                menu.add(0, 1, 0, "移出黑名单");
            } else {
                menu.add(0, 0, 0, "加入黑名单");
            }
            if (z2) {
                menu.add(0, 2, 0, z3 ? "删除该树洞消息" : "删除该私信");
                menu.add(0, 5, 0, "清空聊天记录");
            }
            if (z) {
                menu.add(0, 3, 0, "举报");
            }
            popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener(this) {
                final /* synthetic */ a c;

                public boolean onMenuItemClick(MenuItem menuItem) {
                    int itemId = menuItem.getItemId();
                    if (itemId == 0) {
                        if (this.c.b == null || this.c.b.isFinishing()) {
                            return false;
                        }
                        f.a("确定加入黑名单?", z3 ? "加入黑名单后，你将不再收到对方树洞消息，对方也不能对你的内容进行评论、顶踩等操作。" : "加入黑名单后，你将不再收到对方私信，对方也不能对你的内容进行评论、顶踩等操作。", this.c.b, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void a(boolean z) {
                                if (z) {
                                    this.a.c.a(xSession);
                                }
                            }
                        }, true);
                    } else if (1 == itemId) {
                        this.c.b(xSession);
                    } else if (2 == itemId) {
                        if (this.c.a != null) {
                            this.c.a.c();
                        }
                    } else if (5 == itemId) {
                        if (this.c.a != null) {
                            this.c.a.d();
                        }
                    } else if (3 == itemId) {
                        this.c.a(xSession, xSession.x_sid);
                    }
                    return true;
                }
            });
            popupMenu.show();
        }
    }

    public void a(final XSession xSession, final cn.xiaochuankeji.tieba.push.data.a aVar, View view) {
        if (this.b != null && !this.b.isFinishing()) {
            int i = xSession.x_mask.id == aVar.a ? 1 : 0;
            PopupMenu popupMenu = new PopupMenu(this.b, view);
            Menu menu = popupMenu.getMenu();
            if (aVar.i == R.layout.view_item_chat_txt || aVar.i == R.layout.view_item_chat_self_txt) {
                menu.add(0, 4, 0, "复制");
            }
            menu.add(0, 2, 0, "删除");
            if (i == 0) {
                menu.add(0, 3, 0, "举报");
            }
            popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener(this) {
                final /* synthetic */ a c;

                public boolean onMenuItemClick(MenuItem menuItem) {
                    int itemId = menuItem.getItemId();
                    if (2 == itemId) {
                        if (this.c.a != null) {
                            this.c.a.c();
                        }
                    } else if (3 == itemId) {
                        this.c.a(xSession, aVar.a);
                    } else if (4 == itemId) {
                        ClipboardManager clipboardManager = (ClipboardManager) BaseApplication.getAppContext().getSystemService("clipboard");
                        if (clipboardManager != null) {
                            clipboardManager.setPrimaryClip(ClipData.newPlainText(aVar.e + "_" + aVar.k, aVar.f));
                            g.a("已复制到剪贴板");
                        }
                    }
                    return true;
                }
            });
            popupMenu.show();
        }
    }

    private void a(final XSession xSession, final long j) {
        LinkedHashMap u = cn.xiaochuankeji.tieba.background.utils.c.a.c().u();
        if (u == null || u.isEmpty()) {
            a(0, xSession, j);
            return;
        }
        SDCheckSheet sDCheckSheet = new SDCheckSheet(this.b, new cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet.a(this) {
            final /* synthetic */ a c;

            public void a(int i) {
                if (i == -123) {
                    CustomReportReasonActivity.a(this.c.b, j, this.c.c, "chat");
                } else {
                    this.c.a(i, xSession, j);
                }
            }
        });
        Iterator it = u.entrySet().iterator();
        while (it.hasNext()) {
            int i;
            Entry entry = (Entry) it.next();
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            int parseInt = Integer.parseInt(str);
            String trim = str2.trim();
            if (trim.equals("其他")) {
                this.c = parseInt;
                i = -123;
            } else {
                i = parseInt;
            }
            sDCheckSheet.a(trim, i, it.hasNext());
        }
        sDCheckSheet.b();
    }

    private void a(int i, XSession xSession, long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", Long.valueOf(j));
        jSONObject.put("type", xSession.isAnonymous() ? "flow_chat" : "chat");
        jSONObject.put("reason", Integer.valueOf(i));
        ((UserService) c.b(UserService.class)).reportUser(jSONObject).a(rx.a.b.a.a()).a(new e<Object>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.a(th == null ? "举报失败" : th.getMessage());
                if (this.a.a != null) {
                    this.a.a.b();
                }
            }

            public void onNext(Object obj) {
                g.a("已举报");
                if (this.a.a != null) {
                    this.a.a.b();
                }
            }
        });
    }

    private void a(final XSession xSession) {
        if (this.a != null) {
            this.a.a();
        }
        JSONObject jSONObject = new JSONObject();
        if (xSession.session_type == 1) {
            jSONObject.put("block_id", Long.valueOf(xSession.x_sid));
            ((UserService) c.b(UserService.class)).blockSession(jSONObject).d(new rx.b.g<EmptyJson, Object>(this) {
                final /* synthetic */ a b;

                public /* synthetic */ Object call(Object obj) {
                    return a((EmptyJson) obj);
                }

                public Object a(EmptyJson emptyJson) {
                    return Boolean.valueOf(d.a(xSession.x_sid));
                }
            }).a(rx.a.b.a.a()).a(new e<Object>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    th.printStackTrace();
                    g.a(th == null ? "添加失败" : th.getMessage());
                    if (this.a.a != null) {
                        this.a.a.b();
                    }
                }

                public void onNext(Object obj) {
                    g.a("已加入黑名单");
                    if (this.a.a != null) {
                        this.a.a.b();
                    }
                }
            });
            return;
        }
        jSONObject.put("session_type", Integer.valueOf(xSession.session_type));
        jSONObject.put("block_xid", Long.valueOf(xSession.x_sid));
        jSONObject.put("xroom_id", Long.valueOf(xSession.x_room_id));
        ((HollowService) c.b(HollowService.class)).blockXid(jSONObject).d(new rx.b.g<EmptyJson, Object>(this) {
            final /* synthetic */ a b;

            public /* synthetic */ Object call(Object obj) {
                return a((EmptyJson) obj);
            }

            public Object a(EmptyJson emptyJson) {
                return Boolean.valueOf(d.a(xSession.x_sid));
            }
        }).a(rx.a.b.a.a()).a(new e<Object>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
                g.a(th == null ? "添加失败" : th.getMessage());
                if (this.a.a != null) {
                    this.a.a.b();
                }
            }

            public void onNext(Object obj) {
                g.a("已加入黑名单");
                if (this.a.a != null) {
                    this.a.a.b();
                }
            }
        });
    }

    private void b(final XSession xSession) {
        if (this.a != null) {
            this.a.a();
        }
        JSONObject jSONObject = new JSONObject();
        if (xSession.session_type == 1) {
            jSONObject.put("block_id", Long.valueOf(xSession.x_sid));
            jSONObject.put("type", Integer.valueOf(1));
            ((UserService) c.b(UserService.class)).unblockSession(jSONObject).d(new rx.b.g<String, Object>(this) {
                final /* synthetic */ a b;

                public /* synthetic */ Object call(Object obj) {
                    return a((String) obj);
                }

                public Object a(String str) {
                    return Boolean.valueOf(d.b(xSession.x_sid));
                }
            }).a(rx.a.b.a.a()).a(new e<Object>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    th.printStackTrace();
                    g.a(th == null ? "移出失败" : th.getMessage());
                    if (this.a.a != null) {
                        this.a.a.b();
                    }
                }

                public void onNext(Object obj) {
                    g.a("已移出黑名单");
                    if (this.a.a != null) {
                        this.a.a.b();
                    }
                }
            });
            return;
        }
        jSONObject.put("session_type", Integer.valueOf(xSession.session_type));
        jSONObject.put("block_xid", Long.valueOf(xSession.x_sid));
        jSONObject.put("xroom_id", Long.valueOf(xSession.x_room_id));
        ((HollowService) c.b(HollowService.class)).unblockXid(jSONObject).d(new rx.b.g<EmptyJson, Object>(this) {
            final /* synthetic */ a b;

            public /* synthetic */ Object call(Object obj) {
                return a((EmptyJson) obj);
            }

            public Object a(EmptyJson emptyJson) {
                return Boolean.valueOf(d.b(xSession.x_sid));
            }
        }).a(rx.a.b.a.a()).a(new e<Object>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
                g.a(th == null ? "移除失败" : th.getMessage());
                if (this.a.a != null) {
                    this.a.a.b();
                }
            }

            public void onNext(Object obj) {
                g.a("已移除黑名单");
                if (this.a.a != null) {
                    this.a.a.b();
                }
            }
        });
    }
}
