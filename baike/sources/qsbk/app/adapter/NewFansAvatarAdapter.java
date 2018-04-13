package qsbk.app.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.NewFan;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.UIHelper;

public class NewFansAvatarAdapter extends BaseAdapter {
    private static final String a = NewFansAvatarAdapter.class.getSimpleName();
    private ArrayList<NewFan> b = new ArrayList();
    private int c = 0;

    class a {
        final /* synthetic */ NewFansAvatarAdapter a;
        public ImageView mAvatarIV;
        public ImageView mBg;
        public ImageView mSourceIV;

        a(NewFansAvatarAdapter newFansAvatarAdapter) {
            this.a = newFansAvatarAdapter;
        }
    }

    public NewFansAvatarAdapter(ArrayList<NewFan> arrayList) {
        this.b = arrayList;
        a();
    }

    private void a() {
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(QsbkApp.getInstance().getApplicationContext()).inflate(R.layout.layout_newfans_avatar_item, null, false);
            aVar = new a(this);
            aVar.mBg = (ImageView) view.findViewById(R.id.avatar_bg);
            aVar.mAvatarIV = (ImageView) view.findViewById(R.id.avatar);
            aVar.mSourceIV = (ImageView) view.findViewById(R.id.source);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        NewFan newFan = (NewFan) this.b.get(i);
        Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(newFan.userIcon, newFan.userId);
        if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
            aVar.mAvatarIV.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            FrescoImageloader.displayAvatar(aVar.mAvatarIV, absoluteUrlOfMediumUserIcon);
        }
        if (this.c == i) {
            aVar.mBg.setVisibility(0);
        } else {
            aVar.mBg.setVisibility(4);
        }
        if (newFan.mSource != null) {
            switch (newFan.mSource.type) {
                case 1:
                    aVar.mSourceIV.setImageResource(R.drawable.source_nearby);
                    break;
                case 2:
                    aVar.mSourceIV.setImageResource(R.drawable.source_qiushi);
                    break;
                case 3:
                    aVar.mSourceIV.setImageResource(R.drawable.source_comment);
                    break;
                case 4:
                    aVar.mSourceIV.setImageResource(R.drawable.source_cafe);
                    break;
                case 5:
                    aVar.mSourceIV.setImageResource(R.drawable.source_search);
                    break;
                default:
                    aVar.mSourceIV.setImageBitmap(null);
                    break;
            }
        }
        return view;
    }

    public void setSelection(int i) {
        DebugUtil.debug(a, "setSelection mSelectedPosition:" + i);
        this.c = i;
        notifyDataSetChanged();
    }
}
