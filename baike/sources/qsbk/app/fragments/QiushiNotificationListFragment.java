package qsbk.app.fragments;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Intent;
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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.dialog.PromoteFlowerDialog;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.QiushiNotificationCountManager;
import qsbk.app.im.datastore.BaseChatMsgStore;
import qsbk.app.im.datastore.ChatMsgStore;
import qsbk.app.im.datastore.Util;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.QiushiNotificationItemView;

public class QiushiNotificationListFragment extends Fragment implements PtrListener {
    public static final String KEY_NEW_COUNT = "_new_count";
    public static final String KEY_SHOW_TYPE = "_show_type_";
    public static final String NEED_SHOW_FLOWER = "_need_show_flower";
    public static final String NEED_SHOW_PREFER = "_need_show_prefer";
    public static final int REQUEST_FOR_PROMOTE = 888;
    public static final String SHOW_FLOWER_DESC = "_show_flower_desc";
    public static final int SHOW_FORWARD = 4;
    public static final String SHOW_PREFER_DESC = "_show_prefer_desc";
    public static final int _SHOW_AT_INFO = 3;
    public static final int _SHOW_COMMENT_LIKE = 2;
    public static final int _SHOW_SMILE = 1;
    public static final int _SHOW_TOTAL = 0;
    private int a = 0;
    private PtrLayout b;
    private ListView c;
    private ChatMsgStore d;
    private boolean e = false;
    private b f;
    private boolean g = false;
    private boolean h = false;
    private int i;
    private int j;
    private int k;
    private int l = 0;
    private int m = 30;
    private int n = 0;
    private boolean o = false;
    private Handler p;
    private ProgressDialog q;
    public int showSmileOrLike;

    private static class a {
        List<ChatMsg> a;
        Boolean b;
        int c;

        private a() {
        }
    }

    private class b extends BaseAdapter {
        List<ChatMsg> a;
        final /* synthetic */ QiushiNotificationListFragment b;

        b(QiushiNotificationListFragment qiushiNotificationListFragment, List<ChatMsg> list) {
            this.b = qiushiNotificationListFragment;
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
            if (this.b.showSmileOrLike != 0) {
                return Math.min(this.b.a, size);
            }
            if (this.b.i < 1 && this.b.j < 1 && this.b.k < 1) {
                return Math.min(this.b.a, size);
            }
            if (this.b.i >= 1) {
                i = 1;
            }
            if (this.b.j >= 1) {
                i++;
            }
            if (this.b.k >= 1) {
                i++;
            }
            return Math.min(this.b.a, (((this.b.a - this.b.i) - this.b.j) - this.b.k) + i);
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
            View qiushiNotificationItemView;
            if (view == null) {
                qiushiNotificationItemView = new QiushiNotificationItemView(this.b.getActivity());
            } else {
                qiushiNotificationItemView = view;
            }
            ((QiushiNotificationItemView) qiushiNotificationItemView).setData((ChatMsg) this.a.get(i), this.a, i);
            return qiushiNotificationItemView;
        }
    }

    public static QiushiNotificationListFragment newInstance(int i) {
        QiushiNotificationListFragment qiushiNotificationListFragment = new QiushiNotificationListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("_new_count", i);
        bundle.putInt(KEY_SHOW_TYPE, 0);
        qiushiNotificationListFragment.setArguments(bundle);
        return qiushiNotificationListFragment;
    }

    public static QiushiNotificationListFragment newInstance(int i, int i2) {
        QiushiNotificationListFragment qiushiNotificationListFragment = new QiushiNotificationListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("_new_count", i);
        bundle.putInt(KEY_SHOW_TYPE, i2);
        qiushiNotificationListFragment.setArguments(bundle);
        return qiushiNotificationListFragment;
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
            this.showSmileOrLike = arguments.getInt(KEY_SHOW_TYPE, 0);
        }
        this.d = ChatMsgStore.getChatMsgStore(QsbkApp.currentUser.userId);
        this.g = SharePreferenceUtils.getSharePreferencesBoolValue(NEED_SHOW_FLOWER);
        this.h = SharePreferenceUtils.getSharePreferencesBoolValue(NEED_SHOW_PREFER);
        if (this.g) {
            PromoteFlowerDialog.launch(getActivity(), REQUEST_FOR_PROMOTE, 0);
            this.g = false;
            SharePreferenceUtils.setSharePreferencesValue(NEED_SHOW_FLOWER, false);
        } else if (this.h) {
            PromoteFlowerDialog.launch(getActivity(), 1);
            this.h = false;
            SharePreferenceUtils.setSharePreferencesValue(NEED_SHOW_PREFER, false);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.notification_list, viewGroup, false);
        this.b = (PtrLayout) inflate.findViewById(R.id.ptr);
        this.c = (ListView) inflate.findViewById(R.id.listview);
        this.b.setPtrListener(this);
        this.b.setRefreshEnable(false);
        this.p = new Handler();
        this.q = ProgressDialog.show(getActivity(), null, getString(R.string.loading), true, false);
        Util.imStorageQueue.postRunnable(new io(this));
        return inflate;
    }

    private boolean a() {
        if (this.showSmileOrLike == 0 && !this.e && this.a > 0) {
            return true;
        }
        return false;
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
            if (this.showSmileOrLike == 0) {
                new Builder(getActivity()).setMessage("清空所有消息？").setNegativeButton("取消", new ir(this)).setPositiveButton("确定", new iq(this)).show();
            } else if (this.showSmileOrLike == 1) {
                new Builder(getActivity()).setMessage("清空所有笑脸消息？").setNegativeButton("取消", new it(this)).setPositiveButton("确定", new is(this)).show();
            } else if (this.showSmileOrLike == 2) {
                new Builder(getActivity()).setMessage("清空所有评论点赞消息？").setNegativeButton("取消", new iv(this)).setPositiveButton("确定", new iu(this)).show();
            } else if (this.showSmileOrLike == 3) {
                new Builder(getActivity()).setMessage("清空所有@消息？").setNegativeButton("取消", new ix(this)).setPositiveButton("确定", new iw(this)).show();
            }
        }
    }

    private a c() {
        ChatMsg chatMsg;
        if (this.showSmileOrLike == 0) {
            this.l = 0;
            this.m = 1;
            this.n = 0;
            HashMap byAllCommentLikeUnreadMsgType = this.d.getByAllCommentLikeUnreadMsgType(20, this.l, this.m, this.n);
            List list = (List) byAllCommentLikeUnreadMsgType.get("data");
            HashMap byAllSmileUnreadMsgType = this.d.getByAllSmileUnreadMsgType(20, this.l, this.m, this.n);
            List list2 = (List) byAllSmileUnreadMsgType.get("data");
            HashMap byAllAtInfoUnreadMsgType = this.d.getByAllAtInfoUnreadMsgType(20, this.l, this.m, this.n);
            List list3 = (List) byAllAtInfoUnreadMsgType.get("data");
            List<ChatMsg> byAllOtherExcepttSmileOrLikeMsgType = this.d.getByAllOtherExcepttSmileOrLikeMsgType(20);
            if (list2.size() > 0) {
                this.i = ((Integer) byAllSmileUnreadMsgType.get("total")).intValue();
            } else {
                list2 = (List) this.d.getByAllSmileReadedMsgType(20, this.l, this.m, this.n).get("data");
            }
            if (list2.size() > 0) {
                ChatMsg chatMsg2 = (ChatMsg) list2.get(list2.size() - 1);
                chatMsg2.showType = 2;
                chatMsg2.totalLikeNum = this.i;
                byAllOtherExcepttSmileOrLikeMsgType.add(chatMsg2);
            }
            if (list.size() > 0) {
                this.j = ((Integer) byAllCommentLikeUnreadMsgType.get("total")).intValue();
            } else {
                list = (List) this.d.getByAllCommentLikeReadedMsgType(20, this.l, this.m, this.n).get("data");
            }
            if (list.size() > 0) {
                chatMsg = (ChatMsg) list.get(list.size() - 1);
                chatMsg.showType = 3;
                chatMsg.totalLikeNum = this.j;
                byAllOtherExcepttSmileOrLikeMsgType.add(chatMsg);
            }
            if (list3.size() > 0) {
                this.k = ((Integer) byAllAtInfoUnreadMsgType.get("total")).intValue();
            } else {
                list3 = (List) this.d.getByAllAtInfoReadedMsgType(20, this.l, this.m, this.n).get("data");
            }
            if (list3.size() > 0) {
                chatMsg = (ChatMsg) list3.get(list3.size() - 1);
                chatMsg.showType = 5;
                chatMsg.totalLikeNum = this.k;
                byAllOtherExcepttSmileOrLikeMsgType.add(chatMsg);
            }
            Collection byAllHourHotMsgType = this.d.getByAllHourHotMsgType(20);
            if (byAllHourHotMsgType.size() > 0) {
                byAllOtherExcepttSmileOrLikeMsgType.addAll(byAllHourHotMsgType);
            }
            list2 = new ArrayList();
            if (byAllOtherExcepttSmileOrLikeMsgType.size() > 0) {
                BaseChatMsgStore.sortMsgsByTime(byAllOtherExcepttSmileOrLikeMsgType);
                Collections.reverse(byAllOtherExcepttSmileOrLikeMsgType);
                for (ChatMsg chatMsg3 : byAllOtherExcepttSmileOrLikeMsgType) {
                    if (QiushiNotificationCountManager.QIUSHI_PUSH_UID.equals(chatMsg3.from)) {
                        list2.add(chatMsg3);
                    }
                }
                if (list2.size() > 0) {
                    a aVar = new a();
                    aVar.a = list2;
                    aVar.c = QiushiNotificationCountManager.getInstance(QsbkApp.currentUser.userId).getUnreadCount();
                    return aVar;
                }
            }
        } else if (this.showSmileOrLike == 2) {
            if (this.a > 0) {
                this.l = 0;
                this.m = this.a;
                this.n = 0;
            } else {
                this.l = 0;
                this.n = 0;
                this.m = 30;
            }
            r2 = this.d.getByAllCommentLikeReadedMsgType(20, this.l, this.m, this.n);
            r0 = (List) r2.get("data");
            r3 = new ArrayList();
            if (r0.size() > 0) {
                Collections.reverse(r0);
                for (ChatMsg chatMsg32 : r0) {
                    if (QiushiNotificationCountManager.QIUSHI_PUSH_UID.equals(chatMsg32.from)) {
                        r3.add(chatMsg32);
                    }
                }
                if (r3.size() > 0) {
                    r1 = new a();
                    r1.a = r3;
                    r1.b = (Boolean) r2.get("hasMore");
                    return r1;
                }
            }
        } else if (this.showSmileOrLike == 1) {
            if (this.a > 0) {
                this.l = 0;
                this.m = this.a;
                this.n = 0;
            } else {
                this.l = 0;
                this.n = 0;
                this.m = 30;
            }
            r2 = this.d.getByAllSmileReadedMsgType(20, this.l, this.m, this.n);
            r0 = (List) r2.get("data");
            r3 = new ArrayList();
            if (r0.size() > 0) {
                Collections.reverse(r0);
                for (ChatMsg chatMsg322 : r0) {
                    if (QiushiNotificationCountManager.QIUSHI_PUSH_UID.equals(chatMsg322.from)) {
                        r3.add(chatMsg322);
                    }
                }
                if (r3.size() > 0) {
                    r1 = new a();
                    r1.a = r3;
                    r1.b = (Boolean) r2.get("hasMore");
                    return r1;
                }
            }
        } else if (this.showSmileOrLike == 3) {
            if (this.a > 0) {
                this.l = 0;
                this.m = this.a;
                this.n = 0;
            } else {
                this.l = 0;
                this.n = 0;
                this.m = 30;
            }
            r2 = this.d.getByAllAtInfoReadedMsgType(20, this.l, this.m, this.n);
            r0 = (List) r2.get("data");
            r3 = new ArrayList();
            if (r0.size() > 0) {
                Collections.reverse(r0);
                for (ChatMsg chatMsg3222 : r0) {
                    if (QiushiNotificationCountManager.QIUSHI_PUSH_UID.equals(chatMsg3222.from)) {
                        r3.add(chatMsg3222);
                    }
                }
                if (r3.size() > 0) {
                    r1 = new a();
                    r1.b = (Boolean) r2.get("hasMore");
                    r1.a = r3;
                    return r1;
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
        List list;
        if (this.showSmileOrLike == 1) {
            this.n = this.f.get_data().size();
            this.m = 30;
            this.l++;
            list = (List) this.d.getByAllSmileReadedMsgType(20, this.l, this.m, this.n).get("data");
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
        } else if (this.showSmileOrLike == 2) {
            this.n = this.f.get_data().size();
            this.m = 30;
            this.l++;
            list = (List) this.d.getByAllCommentLikeReadedMsgType(20, this.l, this.m, this.n).get("data");
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
        } else if (this.showSmileOrLike == 3) {
            this.n = this.f.get_data().size();
            this.m = 30;
            this.l++;
            list = (List) this.d.getByAllAtInfoReadedMsgType(20, this.l, this.m, this.n).get("data");
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
        } else if (this.showSmileOrLike == 0) {
            this.e = true;
            this.b.loadMoreDone(true);
            this.b.setLoadMoreEnable(false);
            this.f.notifyDataSetChanged();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == REQUEST_FOR_PROMOTE && i2 == -1) {
            this.h = SharePreferenceUtils.getSharePreferencesBoolValue(NEED_SHOW_PREFER);
            if (this.h) {
                PromoteFlowerDialog.launch(getActivity(), 1);
                this.h = false;
                SharePreferenceUtils.setSharePreferencesValue(NEED_SHOW_PREFER, false);
            }
        }
    }
}
