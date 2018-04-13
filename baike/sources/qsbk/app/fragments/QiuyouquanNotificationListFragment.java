package qsbk.app.fragments;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.QiuyouquanNotificationCountManager;
import qsbk.app.im.datastore.BaseChatMsgStore;
import qsbk.app.im.datastore.ChatMsgStore;
import qsbk.app.im.datastore.Util;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.QiuyouCircleNotificationItemView;

public class QiuyouquanNotificationListFragment extends Fragment implements PtrListener {
    public static final String KEY_NEW_COUNT = "_new_count";
    public static final String KEY_SHOW_AT_INFO = "_show_at_info_";
    public static final String KEY_SHOW_COMMENT_LIKED = "_show_comment_at_info";
    public static final String KEY_SHOW_FORWARDS = "_show_forwards";
    public static final String KEY_SHOW_LIKED = "_show_liked_";
    private int a = 0;
    private PtrLayout b;
    private ListView c;
    private ChatMsgStore d;
    private boolean e = false;
    private b f;
    private boolean g;
    private int h = 0;
    private boolean i;
    private int j = 0;
    private boolean k;
    private int l = 0;
    private boolean m;
    private int n = 0;
    private boolean o = true;
    private int p = 0;
    private int q = 30;
    private int r = 0;
    private boolean s = false;
    private Handler t;
    private ProgressDialog u;

    private static class a {
        List<ChatMsg> a;
        Boolean b;
        int c;

        private a() {
        }
    }

    private class b extends BaseAdapter {
        List<ChatMsg> a;
        final /* synthetic */ QiuyouquanNotificationListFragment b;

        b(QiuyouquanNotificationListFragment qiuyouquanNotificationListFragment, List<ChatMsg> list) {
            this.b = qiuyouquanNotificationListFragment;
            this.a = list;
        }

        public List<ChatMsg> get_data() {
            return this.a;
        }

        public void set_data(List<ChatMsg> list) {
            this.a = list;
        }

        public void addAll(List<ChatMsg> list) {
            if (this.a == null) {
                this.a = list;
            } else {
                this.a.addAll(list);
            }
            notifyDataSetChanged();
        }

        public int getCount() {
            int i = 0;
            int size = this.a != null ? this.a.size() : 0;
            if (!this.b.a()) {
                return size;
            }
            if (this.b.h < 1 && this.b.j < 1 && this.b.l < 1 && this.b.n < 1) {
                return Math.min(this.b.a, size);
            }
            if (this.b.j >= 1) {
                i = 1;
            }
            if (this.b.h >= 1) {
                i++;
            }
            if (this.b.l >= 1) {
                i++;
            }
            if (this.b.n >= 1) {
                i++;
            }
            return Math.min(this.b.a, ((((this.b.a - this.b.j) - this.b.h) - this.b.l) - this.b.n) + i);
        }

        void a() {
            if (this.a != null) {
                this.a.clear();
            }
        }

        public Object getItem(int i) {
            return this.a.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (this.a == null || this.a.size() <= 0 || this.a.size() <= i) {
                return null;
            }
            View qiuyouCircleNotificationItemView;
            if (view == null) {
                qiuyouCircleNotificationItemView = new QiuyouCircleNotificationItemView(this.b.getActivity());
            } else {
                qiuyouCircleNotificationItemView = view;
            }
            ((QiuyouCircleNotificationItemView) qiuyouCircleNotificationItemView).setData((ChatMsg) this.a.get(i), this.a, i);
            return qiuyouCircleNotificationItemView;
        }
    }

    public static QiuyouquanNotificationListFragment newInstance(int i) {
        QiuyouquanNotificationListFragment qiuyouquanNotificationListFragment = new QiuyouquanNotificationListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("_new_count", i);
        qiuyouquanNotificationListFragment.setArguments(bundle);
        return qiuyouquanNotificationListFragment;
    }

    public static QiuyouquanNotificationListFragment newInstance(int i, boolean z) {
        QiuyouquanNotificationListFragment qiuyouquanNotificationListFragment = new QiuyouquanNotificationListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("_new_count", i);
        bundle.putBoolean(KEY_SHOW_LIKED, z);
        qiuyouquanNotificationListFragment.setArguments(bundle);
        return qiuyouquanNotificationListFragment;
    }

    public static QiuyouquanNotificationListFragment newInstance(int i, boolean z, boolean z2) {
        QiuyouquanNotificationListFragment qiuyouquanNotificationListFragment = new QiuyouquanNotificationListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("_new_count", i);
        bundle.putBoolean(KEY_SHOW_AT_INFO, z2);
        qiuyouquanNotificationListFragment.setArguments(bundle);
        return qiuyouquanNotificationListFragment;
    }

    public static QiuyouquanNotificationListFragment newInstance(int i, boolean z, boolean z2, boolean z3) {
        QiuyouquanNotificationListFragment qiuyouquanNotificationListFragment = new QiuyouquanNotificationListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("_new_count", i);
        bundle.putBoolean(KEY_SHOW_COMMENT_LIKED, z3);
        qiuyouquanNotificationListFragment.setArguments(bundle);
        return qiuyouquanNotificationListFragment;
    }

    public static QiuyouquanNotificationListFragment newInstance(int i, boolean z, boolean z2, boolean z3, boolean z4) {
        QiuyouquanNotificationListFragment qiuyouquanNotificationListFragment = new QiuyouquanNotificationListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("_new_count", i);
        bundle.putBoolean(KEY_SHOW_FORWARDS, z4);
        qiuyouquanNotificationListFragment.setArguments(bundle);
        return qiuyouquanNotificationListFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        if (QsbkApp.currentUser == null) {
            getActivity().finish();
            return;
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.a = arguments.getInt("_new_count", 0);
            this.g = arguments.getBoolean(KEY_SHOW_LIKED, false);
            this.i = arguments.getBoolean(KEY_SHOW_AT_INFO, false);
            this.k = arguments.getBoolean(KEY_SHOW_COMMENT_LIKED, false);
            this.m = arguments.getBoolean(KEY_SHOW_FORWARDS, false);
        }
        this.d = ChatMsgStore.getChatMsgStore(QsbkApp.currentUser.userId);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.notification_list, viewGroup, false);
        this.b = (PtrLayout) inflate.findViewById(R.id.ptr);
        this.c = (ListView) inflate.findViewById(R.id.listview);
        this.b.setPtrListener(this);
        this.b.setRefreshEnable(false);
        this.t = new Handler();
        this.u = ProgressDialog.show(getActivity(), null, getString(R.string.loading), true, false);
        Util.imStorageQueue.postRunnable(new kc(this));
        return inflate;
    }

    private boolean a() {
        if (this.i || this.k || this.g || this.m || this.e || this.a <= 0) {
            return false;
        }
        return true;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.notification, menu);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_clear).setIcon(UIHelper.isNightTheme() ? R.drawable.ic_delete_night : R.drawable.ic_delete);
        super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_clear) {
            return super.onOptionsItemSelected(menuItem);
        }
        b();
        return true;
    }

    private void b() {
        if (this.f != null && this.f.getCount() != 0) {
            if (this.g) {
                new Builder(getActivity()).setMessage("确认清空所有点赞消息？").setNegativeButton("取消", new kh(this)).setPositiveButton("确定", new kg(this)).show();
            } else if (this.i) {
                new Builder(getActivity()).setMessage("确认清空所有糗友圈@消息？").setNegativeButton("取消", new kj(this)).setPositiveButton("确定", new ki(this)).show();
            } else if (this.k) {
                new Builder(getActivity()).setMessage("确认清空所有糗友圈转发消息？").setNegativeButton("取消", new kl(this)).setPositiveButton("确定", new kk(this)).show();
            } else if (this.m) {
                new Builder(getActivity()).setMessage("确认清空所有糗友圈评论点赞消息？").setNegativeButton("取消", new kn(this)).setPositiveButton("确定", new km(this)).show();
            } else if (!this.g && !this.i && !this.k) {
                new Builder(getActivity()).setMessage("清空所有消息？").setNegativeButton("取消", new kf(this)).setPositiveButton("确定", new ke(this)).show();
            }
        }
    }

    private a c() {
        HashMap byAllLikeReadedMsgType;
        List<ChatMsg> list;
        List arrayList;
        ChatMsg chatMsg;
        a aVar;
        if (this.g) {
            if (this.a > 0) {
                this.p = 0;
                this.q = this.a;
                this.r = 0;
            } else {
                this.p = 0;
                this.r = 0;
                this.q = 30;
            }
            byAllLikeReadedMsgType = this.d.getByAllLikeReadedMsgType(20, this.p, this.q, this.r);
            list = (List) byAllLikeReadedMsgType.get("data");
            arrayList = new ArrayList();
            if (list.size() > 0) {
                Collections.reverse(list);
                for (ChatMsg chatMsg2 : list) {
                    if (QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID.equals(chatMsg2.from)) {
                        arrayList.add(chatMsg2);
                    }
                }
                if (arrayList.size() > 0) {
                    aVar = new a();
                    aVar.a = arrayList;
                    aVar.b = (Boolean) byAllLikeReadedMsgType.get("hasMore");
                    return aVar;
                }
            }
        } else if (this.i) {
            if (this.a > 0) {
                this.p = 0;
                this.q = this.a;
                this.r = 0;
            } else {
                this.p = 0;
                this.r = 0;
                this.q = 30;
            }
            byAllLikeReadedMsgType = this.d.getByAllAtReadedMsgType(20, this.p, this.q, this.r);
            list = (List) byAllLikeReadedMsgType.get("data");
            arrayList = new ArrayList();
            if (list.size() > 0) {
                Collections.reverse(list);
                for (ChatMsg chatMsg22 : list) {
                    if (QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID.equals(chatMsg22.from)) {
                        arrayList.add(chatMsg22);
                    }
                }
                if (arrayList.size() > 0) {
                    aVar = new a();
                    aVar.a = arrayList;
                    aVar.b = (Boolean) byAllLikeReadedMsgType.get("hasMore");
                    return aVar;
                }
            }
        } else if (this.k) {
            if (this.a > 0) {
                this.p = 0;
                this.q = this.a;
                this.r = 0;
            } else {
                this.p = 0;
                this.r = 0;
                this.q = 30;
            }
            byAllLikeReadedMsgType = this.d.getByAllCircleComemntLikeReadedMsgType(20, this.p, this.q, this.r);
            list = (List) byAllLikeReadedMsgType.get("data");
            arrayList = new ArrayList();
            if (list.size() > 0) {
                Collections.reverse(list);
                for (ChatMsg chatMsg222 : list) {
                    if (QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID.equals(chatMsg222.from)) {
                        arrayList.add(chatMsg222);
                    }
                }
                if (arrayList.size() > 0) {
                    aVar = new a();
                    aVar.a = arrayList;
                    aVar.b = (Boolean) byAllLikeReadedMsgType.get("hasMore");
                    return aVar;
                }
            }
        } else if (this.m) {
            if (this.a > 0) {
                this.p = 0;
                this.q = this.a;
                this.r = 0;
            } else {
                this.p = 0;
                this.r = 0;
                this.q = 30;
            }
            byAllLikeReadedMsgType = this.d.getByAllForwardReadedMsgType(20, this.p, this.q, this.r);
            list = (List) byAllLikeReadedMsgType.get("data");
            arrayList = new ArrayList();
            if (list.size() > 0) {
                Collections.reverse(list);
                for (ChatMsg chatMsg2222 : list) {
                    if (QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID.equals(chatMsg2222.from)) {
                        arrayList.add(chatMsg2222);
                    }
                }
                if (arrayList.size() > 0) {
                    aVar = new a();
                    aVar.a = arrayList;
                    aVar.b = (Boolean) byAllLikeReadedMsgType.get("hasMore");
                    return aVar;
                }
            }
        } else if (!(this.i || this.g || this.k || this.m)) {
            this.p = 0;
            this.q = 1;
            this.r = 0;
            List<ChatMsg> byAllOtherMsgType = this.d.getByAllOtherMsgType(20);
            HashMap byAllLikeUnreadMsgType = this.d.getByAllLikeUnreadMsgType(20, this.p, this.q, this.r);
            List list2 = (List) byAllLikeUnreadMsgType.get("data");
            HashMap byAllAtUnreadMsgType = this.d.getByAllAtUnreadMsgType(20, this.p, this.q, this.r);
            List list3 = (List) byAllAtUnreadMsgType.get("data");
            HashMap byAllCircleCommentLikeUnreadMsgType = this.d.getByAllCircleCommentLikeUnreadMsgType(20, this.p, this.q, this.r);
            List list4 = (List) byAllCircleCommentLikeUnreadMsgType.get("data");
            HashMap byAllForwardUnreadMsgType = this.d.getByAllForwardUnreadMsgType(20, this.p, this.q, this.r);
            arrayList = (List) byAllForwardUnreadMsgType.get("data");
            if (list2.size() > 0) {
                this.h = ((Integer) byAllLikeUnreadMsgType.get("total")).intValue();
            } else {
                list2 = (List) this.d.getByAllLikeReadedMsgType(20, this.p, this.q, this.r).get("data");
            }
            if (list2.size() > 0) {
                chatMsg2222 = (ChatMsg) list2.get(list2.size() - 1);
                chatMsg2222.showType = 1;
                chatMsg2222.totalLikeNum = this.h;
                byAllOtherMsgType.add(chatMsg2222);
            }
            if (list3.size() > 0) {
                this.j = ((Integer) byAllAtUnreadMsgType.get("total")).intValue();
            } else {
                list3 = (List) this.d.getByAllAtReadedMsgType(20, this.p, this.q, this.r).get("data");
            }
            if (list3.size() > 0) {
                chatMsg2222 = (ChatMsg) list3.get(list3.size() - 1);
                chatMsg2222.showType = 4;
                chatMsg2222.totalLikeNum = this.j;
                byAllOtherMsgType.add(chatMsg2222);
            }
            if (list4.size() > 0) {
                this.l = ((Integer) byAllCircleCommentLikeUnreadMsgType.get("total")).intValue();
            } else {
                list4 = (List) this.d.getByAllCircleComemntLikeReadedMsgType(20, this.p, this.q, this.r).get("data");
            }
            if (list4.size() > 0) {
                chatMsg2222 = (ChatMsg) list4.get(list4.size() - 1);
                chatMsg2222.showType = 6;
                chatMsg2222.totalLikeNum = this.l;
                byAllOtherMsgType.add(chatMsg2222);
            }
            if (arrayList.size() > 0) {
                this.n = ((Integer) byAllForwardUnreadMsgType.get("total")).intValue();
            } else {
                arrayList = (List) this.d.getByAllForwardReadedMsgType(20, this.p, this.q, this.r).get("data");
            }
            if (arrayList.size() > 0) {
                chatMsg2222 = (ChatMsg) arrayList.get(arrayList.size() - 1);
                chatMsg2222.showType = 7;
                chatMsg2222.totalLikeNum = this.n;
                byAllOtherMsgType.add(chatMsg2222);
            }
            list3 = new ArrayList();
            if (byAllOtherMsgType.size() > 0) {
                BaseChatMsgStore.sortMsgsByTime(byAllOtherMsgType);
                Collections.reverse(byAllOtherMsgType);
                for (ChatMsg chatMsg22222 : byAllOtherMsgType) {
                    if (QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID.equals(chatMsg22222.from)) {
                        list3.add(chatMsg22222);
                    }
                }
                if (list3.size() > 0) {
                    a aVar2 = new a();
                    aVar2.a = list3;
                    aVar2.c = QiuyouquanNotificationCountManager.getInstance(QsbkApp.currentUser.userId).getUnreadCount();
                    return aVar2;
                }
            }
        }
        return null;
    }

    private void d() {
        this.c.setVisibility(8);
    }

    public void onRefresh() {
    }

    public void onLoadMore() {
        if (this.g || this.i || this.k || this.m) {
            this.r = this.f.get_data().size();
            this.q = 30;
            this.p++;
            HashMap hashMap = null;
            if (this.g) {
                hashMap = this.d.getByAllLikeReadedMsgType(20, this.p, this.q, this.r);
            } else if (this.i) {
                hashMap = this.d.getByAllAtReadedMsgType(20, this.p, this.q, this.r);
            } else if (this.k) {
                hashMap = this.d.getByAllCircleComemntLikeReadedMsgType(20, this.p, this.q, this.r);
            } else if (this.m) {
                hashMap = this.d.getByAllForwardReadedMsgType(20, this.p, this.q, this.r);
            }
            if (hashMap != null) {
                List list = (List) hashMap.get("data");
                Collections.reverse(list);
                if (list == null || list.size() <= 0) {
                    this.b.loadMoreDone(true);
                    this.b.setLoadMoreEnable(false);
                    this.f.notifyDataSetChanged();
                    return;
                }
                this.b.loadMoreDone(true);
                this.b.setLoadMoreEnable(true);
                this.b.setLoadMoreState(2, "查看更早消息");
                this.f.addAll(list);
                return;
            }
            return;
        }
        this.e = true;
        this.b.loadMoreDone(true);
        this.b.setLoadMoreEnable(false);
        this.f.notifyDataSetChanged();
    }
}
