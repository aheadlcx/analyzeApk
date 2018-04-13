package qsbk.app.share.message;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.fragments.StatisticFragment;
import qsbk.app.im.ContactListItem;
import qsbk.app.im.datastore.ContactListItemStore;
import qsbk.app.share.message.adapter.RecentChatQiuyouAdapter;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.comm.ArrayUtils;
import qsbk.app.widget.AutoLoadMoreListView;

public class RecentContactsFragment extends StatisticFragment {
    private static final String a = RecentContactsFragment.class.getSimpleName();
    private AutoLoadMoreListView b;
    private Bundle c;
    private ContactListItemStore d;
    private RecentChatQiuyouAdapter e;
    private List<ContactListItem> f = new ArrayList();
    private Handler g = new Handler();
    private String h;

    public static RecentContactsFragment newInstance(Bundle bundle) {
        RecentContactsFragment recentContactsFragment = new RecentContactsFragment();
        recentContactsFragment.setArguments(bundle);
        return recentContactsFragment;
    }

    public static void sortContactListItems(List<ContactListItem> list) {
        if (list != null && !list.isEmpty()) {
            ArrayUtils.sort(list, new e());
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (TextUtils.isEmpty(this.h)) {
            this.h = QsbkApp.currentUser == null ? null : QsbkApp.currentUser.userId;
        }
        this.c = getArguments();
        if (this.c == null) {
            getActivity().finish();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.recent_contacts_fragment, null);
        a(inflate);
        b();
        c();
        d();
        return inflate;
    }

    private void a(View view) {
        this.b = (AutoLoadMoreListView) view.findViewById(R.id.recent_chat_list);
    }

    private void b() {
        this.e = new RecentChatQiuyouAdapter(getActivity());
        this.b.setAdapter(this.e);
        if (TextUtils.isEmpty(this.h)) {
            this.h = QsbkApp.currentUser == null ? null : QsbkApp.currentUser.userId;
        }
        this.d = ContactListItemStore.getContactStore(this.h);
    }

    private void c() {
        this.b.setOnItemClickListener(new f(this));
    }

    private void d() {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new g(this));
    }

    private void a(List<ContactListItem> list) {
        this.e.setDatas(list);
        this.e.notifyDataSetChanged();
    }

    private List<ContactListItem> a(String str) {
        List<ContactListItem> num = this.d.getNum(30);
        if (num == null || num.isEmpty()) {
            DebugUtil.debug(a, "getContactListItem, empty");
        } else {
            sortContactListItems(num);
        }
        return num;
    }

    private void a(ContactListItem contactListItem) {
        CharSequence charSequence = "确定发给\n" + contactListItem.name;
        DebugUtil.debug(a, "showShareDialog " + contactListItem.name);
        Builder builder = new Builder(getActivity());
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.share_dialog, null);
        ((TextView) inflate.findViewById(R.id.tv_content)).setText(charSequence);
        builder.setView(inflate);
        builder.setPositiveButton(R.string.confirm, new i(this, contactListItem));
        builder.setNegativeButton(R.string.cancel, new j(this));
        builder.create().show();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDestroyView() {
        super.onDestroyView();
    }
}
