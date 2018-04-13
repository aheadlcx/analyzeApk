package cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.ui.base.l;

public abstract class c extends l {
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDetach() {
        super.onDetach();
        AppController.instance().watch(this);
    }
}
