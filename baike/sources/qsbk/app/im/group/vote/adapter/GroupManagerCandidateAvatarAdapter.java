package qsbk.app.im.group.vote.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.im.group.vote.bean.UserAvatarInfo;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.UIHelper;

public class GroupManagerCandidateAvatarAdapter extends BaseAdapter {
    private static final String a = GroupManagerCandidateAvatarAdapter.class.getSimpleName();
    private Context b;
    private ArrayList<UserAvatarInfo> c = new ArrayList();
    private int d = 0;

    class a {
        final /* synthetic */ GroupManagerCandidateAvatarAdapter a;
        public ImageView mCandidateAvatarBg;
        public ImageView mCandidateAvatarIV;

        a(GroupManagerCandidateAvatarAdapter groupManagerCandidateAvatarAdapter) {
            this.a = groupManagerCandidateAvatarAdapter;
        }
    }

    public GroupManagerCandidateAvatarAdapter(Context context) {
        this.b = context;
    }

    public GroupManagerCandidateAvatarAdapter(Context context, ArrayList<UserAvatarInfo> arrayList) {
        this.b = context;
        this.c = arrayList;
        a();
    }

    public void setUserAvatarInfos(ArrayList<UserAvatarInfo> arrayList) {
        this.c = arrayList;
    }

    public void appendUserAvatarInfos(UserAvatarInfo userAvatarInfo) {
        this.c.add(userAvatarInfo);
    }

    private void a() {
    }

    public void setItemSelection(int i) {
        DebugUtil.debug(a, "setSelection mSelectedItemPosition=" + i);
        this.d = i;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    @SuppressLint({"NewApi"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.layout_group_manager_candidate_avatar_item, null, false);
            aVar = new a(this);
            aVar.mCandidateAvatarBg = (ImageView) view.findViewById(R.id.iv_avatar_bg);
            aVar.mCandidateAvatarIV = (ImageView) view.findViewById(R.id.avatar);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (i < this.c.size() - 1) {
            UserAvatarInfo userAvatarInfo = (UserAvatarInfo) this.c.get(i);
            Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(userAvatarInfo.mUserIcon, String.valueOf(userAvatarInfo.mUserId));
            if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                aVar.mCandidateAvatarIV.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                FrescoImageloader.displayAvatar(aVar.mCandidateAvatarIV, absoluteUrlOfMediumUserIcon);
            }
        } else if (i == this.c.size() - 1) {
            aVar.mCandidateAvatarIV.setImageResource(R.drawable.voting_box);
        }
        if (this.d == i) {
            aVar.mCandidateAvatarBg.setVisibility(0);
        } else {
            aVar.mCandidateAvatarBg.setVisibility(4);
        }
        return view;
    }
}
