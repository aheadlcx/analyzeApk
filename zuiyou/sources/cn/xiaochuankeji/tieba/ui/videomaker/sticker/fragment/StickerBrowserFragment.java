package cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuan.image.a.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.i;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.c;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.adapter.StickerBrowserAdapter;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.b;
import java.util.Collection;
import java.util.List;
import rx.b.g;
import rx.d;
import rx.e;

public class StickerBrowserFragment extends a {
    private StickerBrowserAdapter a = new StickerBrowserAdapter();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AppCompatTextView title;

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog appCompatDialog = new AppCompatDialog(getActivity(), R.style.SheetTheme);
        appCompatDialog.setCanceledOnTouchOutside(true);
        appCompatDialog.setCancelable(true);
        appCompatDialog.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ StickerBrowserFragment a;

            {
                this.a = r1;
            }

            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return false;
                }
                this.a.dismiss();
                return true;
            }
        });
        return appCompatDialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_sticker_list, viewGroup, false);
        ButterKnife.a(this, inflate);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getDialog() != null) {
            if (VERSION.SDK_INT > 19) {
                getDialog().getWindow().setFlags(67108864, 67108864);
            }
            getDialog().getWindow().setSoftInputMode(2);
            getDialog().getWindow().setLayout(-1, -1);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.title.setText(getArguments().getInt("S_L_F_MODE", 3) == 2 ? "自定义贴纸" : "自定义贴纸");
        this.a.a(this);
        this.recyclerView.setItemAnimator(new cn.xiaochuankeji.tieba.ui.widget.a.a());
        i.a(this.recyclerView);
        LayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        gridLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.recyclerView.setAdapter(this.a);
        a();
    }

    public void a() {
        d.a(Boolean.valueOf(true)).d(new g<Boolean, List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a>>(this) {
            final /* synthetic */ StickerBrowserFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((Boolean) obj);
            }

            public List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> a(Boolean bool) {
                return c.a().a("自定义");
            }
        }).a(new g<List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a>, Boolean>(this) {
            final /* synthetic */ StickerBrowserFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((List) obj);
            }

            public Boolean a(List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> list) {
                return Boolean.valueOf(list != null);
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a>>(this) {
            final /* synthetic */ StickerBrowserFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((List) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a> list) {
                this.a.a.a((Collection) list);
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void dismiss() {
        super.dismiss();
        org.greenrobot.eventbus.c.a().d(new b(5));
    }

    @OnClick
    public void close() {
        dismiss();
    }

    public static StickerBrowserFragment a(FragmentManager fragmentManager, Bundle bundle) {
        StickerBrowserFragment findFragmentByTag = fragmentManager.findFragmentByTag("S_L_F_sticker");
        if (findFragmentByTag == null) {
            findFragmentByTag = new StickerBrowserFragment();
            findFragmentByTag.setArguments(bundle);
            findFragmentByTag.show(fragmentManager, "S_L_F_sticker");
        } else {
            fragmentManager.beginTransaction().show(findFragmentByTag).commitAllowingStateLoss();
        }
        org.greenrobot.eventbus.c.a().d(new b(3));
        return findFragmentByTag;
    }
}
