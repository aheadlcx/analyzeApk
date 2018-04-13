package cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.b;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.BitmapStickerDrawable;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.GifStickerDrawable;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.VectorStickerDrawable;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.WebPStickerDrawable;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.StickerTrace;
import cn.xiaochuankeji.tieba.ui.widget.indicator.MagicIndicator;
import cn.xiaochuankeji.tieba.ui.widget.indicator.h;
import cn.xiaochuankeji.tieba.ui.widget.indicator.i;
import cn.xiaochuankeji.tieba.ui.widget.indicator.j;
import cn.xiaochuankeji.tieba.ui.widget.indicator.l;
import java.lang.reflect.Field;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.c;

public class VideoStickerFragment extends Fragment {
    private static final String[] b = new String[]{"自定义", "贴纸", "GIF"};
    SimpleOnPageChangeListener a = new SimpleOnPageChangeListener(this) {
        final /* synthetic */ VideoStickerFragment a;

        {
            this.a = r1;
        }

        public void onPageSelected(int i) {
            super.onPageSelected(i);
            if (this.a.magic_indicator != null) {
                this.a.magic_indicator.a(i);
            }
            b.a = VideoStickerFragment.b[i];
        }

        public void onPageScrolled(int i, float f, int i2) {
            super.onPageScrolled(i, f, i2);
            if (this.a.magic_indicator != null) {
                this.a.magic_indicator.a(i, f, i2);
            }
        }

        public void onPageScrollStateChanged(int i) {
            super.onPageScrollStateChanged(i);
            if (this.a.magic_indicator != null) {
                this.a.magic_indicator.b(i);
            }
        }
    };
    private FragmentManager c;
    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.a d;
    @BindView
    MagicIndicator magic_indicator;
    @BindView
    View panel;
    @BindView
    ViewPager viewPager;

    private static class a extends FragmentStatePagerAdapter {
        a(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return a(i);
        }

        public int getCount() {
            return VideoStickerFragment.b.length;
        }

        private Fragment a(int i) {
            String str = VideoStickerFragment.b[Math.abs(i) % VideoStickerFragment.b.length];
            Object obj = -1;
            switch (str.hashCode()) {
                case 70564:
                    if (str.equals("GIF")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 1153028:
                    if (str.equals("贴纸")) {
                        obj = null;
                        break;
                    }
                    break;
                case 32707929:
                    if (str.equals("自定义")) {
                        obj = 2;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return d.i();
                case 1:
                    return a.i();
                case 2:
                    return CustomStickerFragment.i();
                default:
                    return CustomStickerFragment.i();
            }
        }

        public CharSequence getPageTitle(int i) {
            return VideoStickerFragment.b[i];
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        c.a().a(this);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.view_video_sticker_browser, viewGroup, false);
        ButterKnife.a(this, inflate);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        int i = 0;
        super.onViewCreated(view, bundle);
        this.viewPager.setAdapter(new a(getChildFragmentManager()));
        this.viewPager.setOffscreenPageLimit(3);
        i bVar = new cn.xiaochuankeji.tieba.ui.widget.indicator.b(getContext());
        bVar.setIsNeedMargin(false);
        bVar.setAdjustMode(true);
        bVar.setAdapter(new cn.xiaochuankeji.tieba.ui.widget.indicator.c(this) {
            @ColorInt
            int a = 1140850688;
            @ColorInt
            int b = -872415232;
            final /* synthetic */ VideoStickerFragment c;

            {
                this.c = r2;
            }

            public int a() {
                return VideoStickerFragment.b.length;
            }

            public j a(Context context, final int i) {
                j anonymousClass1 = new cn.xiaochuankeji.tieba.ui.widget.indicator.a(this, context) {
                    final /* synthetic */ AnonymousClass1 a;

                    public void a(int i, int i2, float f, boolean z) {
                        super.a(i, i2, f, z);
                        setBackgroundColor(cn.xiaochuankeji.tieba.background.utils.i.a(f, this.a.b, this.a.a));
                    }

                    public void b(int i, int i2, float f, boolean z) {
                        super.b(i, i2, f, z);
                        setBackgroundColor(cn.xiaochuankeji.tieba.background.utils.i.a(f, this.a.a, this.a.b));
                    }
                };
                anonymousClass1.setText(VideoStickerFragment.b[i]);
                anonymousClass1.setTextSize(15.0f);
                anonymousClass1.setNormalColor(-1996488705);
                anonymousClass1.setSelectedColor(-1);
                anonymousClass1.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void onClick(View view) {
                        this.b.c.viewPager.setCurrentItem(i);
                    }
                });
                return anonymousClass1;
            }

            public h a(Context context) {
                h lVar = new l(context);
                lVar.setMode(2);
                lVar.setColors(Integer.valueOf(-2283420));
                float a = cn.xiaochuankeji.tieba.background.utils.i.a(this.c.getResources(), 2.5f);
                lVar.setLineWidth(6.0f * a);
                lVar.setLineHeight(a);
                lVar.setRoundRadius(a);
                return lVar;
            }

            public float a(int i) {
                return 1.0f;
            }
        });
        this.magic_indicator.setNavigator(bVar);
        this.viewPager.addOnPageChangeListener(this.a);
        while (i < b.length) {
            if (b[i].equals(b.a)) {
                this.viewPager.setCurrentItem(i);
            }
            i++;
        }
    }

    public void onDestroyView() {
        if (this.viewPager != null) {
            this.viewPager.removeOnPageChangeListener(this.a);
        }
        super.onDestroyView();
        com.facebook.drawee.a.a.c.c().a();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDetach() {
        super.onDetach();
        try {
            Field declaredField = Fragment.class.getDeclaredField("mChildFragmentManager");
            declaredField.setAccessible(true);
            declaredField.set(this, null);
            declaredField = Fragment.class.getDeclaredField("mFragmentManager");
            declaredField.setAccessible(true);
            declaredField.set(this, null);
            declaredField = Fragment.class.getDeclaredField("mHost");
            declaredField.setAccessible(true);
            declaredField.set(this, null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
        c.a().c(this);
        AppController.instance().watch(this);
    }

    @OnClick
    public void close() {
        if (this.c != null && this.c.popBackStackImmediate("V_S_F_sticker", 1)) {
            if (this.d != null) {
                this.d.b();
            }
            if (this.viewPager != null) {
                this.viewPager.removeAllViews();
                this.viewPager = null;
            }
            if (this.magic_indicator != null) {
                this.magic_indicator.removeAllViews();
                this.magic_indicator = null;
            }
            this.d = null;
            this.panel = null;
            this.c = null;
        }
    }

    private static VideoStickerFragment a(FragmentManager fragmentManager, cn.xiaochuankeji.tieba.ui.videomaker.sticker.a aVar) {
        VideoStickerFragment videoStickerFragment = new VideoStickerFragment();
        videoStickerFragment.setArguments(new Bundle());
        videoStickerFragment.d = aVar;
        videoStickerFragment.c = fragmentManager;
        return videoStickerFragment;
    }

    public static void a(@NonNull FragmentManager fragmentManager, @IdRes int i, @NonNull cn.xiaochuankeji.tieba.ui.videomaker.sticker.a aVar) {
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_bottom, R.anim.slide_in_bottom, R.anim.slide_out_bottom).replace(i, a(fragmentManager, aVar), "V_S_F_sticker").addToBackStack("V_S_F_sticker").commit();
        aVar.a();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void sticker(cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.b bVar) {
        if (bVar != null) {
            float f = bVar.d <= 0.0f ? 1.0f : ((double) bVar.d) > 1.0d ? bVar.d / 100.0f : bVar.d;
            if (11 == bVar.a) {
                a(new BitmapStickerDrawable(bVar.b, f), bVar);
            } else if (13 == bVar.a) {
                if (this.d != null) {
                    this.d.a(new VectorStickerDrawable(bVar.c, f));
                }
                close();
            } else if (17 == bVar.a) {
                a(new GifStickerDrawable(bVar.b, f), bVar);
            } else if (19 == bVar.a) {
                a(new WebPStickerDrawable(bVar.b, f), bVar);
            } else if (3 == bVar.a) {
                if (this.panel != null && this.panel.isShown()) {
                    this.panel.setVisibility(4);
                }
            } else if (5 == bVar.a && this.panel != null && !this.panel.isShown()) {
                this.panel.setVisibility(0);
            }
        }
    }

    private void a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar, cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.b bVar) {
        if (this.d != null) {
            aVar.a(new StickerTrace(bVar.e, bVar.f));
            this.d.a(aVar);
        }
        close();
    }
}
