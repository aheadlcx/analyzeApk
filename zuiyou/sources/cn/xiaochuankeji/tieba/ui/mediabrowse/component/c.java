package cn.xiaochuankeji.tieba.ui.mediabrowse.component;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import cn.htjyb.b.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.tencent.open.SocialConstants;

public abstract class c extends Fragment {
    private boolean a = true;

    public abstract void a();

    public abstract void b();

    public abstract int c();

    public abstract a d();

    public abstract void e();

    protected static Bundle b(int i, boolean z, long j, a aVar, a aVar2) {
        Bundle bundle = new Bundle();
        bundle.putInt(RequestParameters.POSITION, i);
        bundle.putBoolean("show", z);
        bundle.putLong("pid", j);
        Bundle pictureBundle = PictureImpl.getPictureBundle(aVar);
        if (aVar2 != null) {
            bundle.putBundle("thumbpic", PictureImpl.getPictureBundle(aVar2));
        }
        bundle.putBundle(SocialConstants.PARAM_AVATAR_URI, pictureBundle);
        return bundle;
    }

    protected void a(boolean z) {
        this.a = z;
    }

    protected void a(a aVar) {
        if (this.a && MediaBrowseActivity.class.isInstance(getActivity())) {
            ((MediaBrowseActivity) getActivity()).a(aVar);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.rootView);
        findViewById.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.getActivity().finish();
            }
        });
        findViewById.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                this.a.a(this.a.d());
                return true;
            }
        });
    }

    public void onDestroyView() {
        super.onDestroyView();
        AppController.instance().watch(this);
    }
}
