package com.budejie.www.activity.posts;

import android.app.Activity;
import android.os.Handler;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.w;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.exception.ErrorCode;
import com.sprite.ads.nati.NativeADListener;
import com.sprite.ads.nati.NativeAd;
import com.sprite.ads.nati.NativeAdRef;
import java.util.ArrayList;
import java.util.List;

public class FeedADTools {
    State a = State.not_load;
    Handler b = new Handler();
    private boolean c = true;
    private Activity d;
    private List<NativeAdRef> e = new ArrayList();
    private boolean f;
    private final NativeAd g;

    public enum State {
        not_load,
        loading,
        failed,
        successed
    }

    public interface a {
        void a();
    }

    public FeedADTools(Activity activity) {
        this.d = activity;
        this.g = new NativeAd(this.d);
    }

    public boolean a() {
        return this.c;
    }

    public FeedADTools a(final String str, final a aVar) {
        this.a = State.loading;
        this.g.loadAd(str, new NativeADListener(this) {
            final /* synthetic */ FeedADTools c;

            public void preLoad(List<AdItem> list) {
            }

            public void onADSkip(AdItem adItem) {
                w.a(this.c.d, false).a(adItem.getUrl());
            }

            public void loadSuccess(List<NativeAdRef> list) {
                this.c.c = false;
                if (list != null) {
                    this.c.a = State.successed;
                    this.c.e.clear();
                    this.c.e.addAll(list);
                    if (aVar != null) {
                        aVar.a();
                    }
                }
            }

            public void loadFailure(ErrorCode errorCode) {
                this.c.c = false;
                this.c.a = State.failed;
                this.c.a((NativeADListener) this, str);
            }
        });
        return this;
    }

    private void a(final NativeADListener nativeADListener, final String str) {
        this.b.postDelayed(new Runnable(this) {
            final /* synthetic */ FeedADTools c;

            public void run() {
                if (this.c.e.isEmpty() && this.c.a != State.loading && this.c.a != State.failed) {
                    this.c.a = State.loading;
                    this.c.g.loadAd(str, nativeADListener);
                }
            }
        }, 1000);
    }

    public int a(List<ListItemObject> list, int i) {
        this.f = true;
        int i2 = i;
        while (i < this.e.size()) {
            NativeAdRef nativeAdRef = (NativeAdRef) this.e.get(i);
            int size = list.size();
            int position = nativeAdRef.getPosition();
            if (position >= size) {
                break;
            }
            int i3;
            ListItemObject listItemObject = (ListItemObject) list.get(position);
            if (listItemObject == null || !listItemObject.isIs_ad()) {
                list.add(nativeAdRef.getPosition(), a(nativeAdRef));
                i3 = i2 + 1;
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return i2;
    }

    public int a(List<ListItemObject> list, List<ListItemObject> list2, int i) {
        this.f = true;
        int size = list.size() - list2.size();
        int i2 = i;
        while (i < this.e.size()) {
            NativeAdRef nativeAdRef = (NativeAdRef) this.e.get(i);
            int position = nativeAdRef.getPosition() - size;
            if (nativeAdRef.getPosition() >= list2.size() + size || position < 0 || position >= list2.size()) {
                break;
            }
            int i3;
            ListItemObject a = a(nativeAdRef);
            ListItemObject listItemObject = (ListItemObject) list2.get(position);
            if (listItemObject == null || !listItemObject.isIs_ad() || listItemObject.getAdItem() == null || listItemObject.getAdItem().getPosition() != nativeAdRef.getPosition()) {
                list2.add(position, a);
                list.add(nativeAdRef.getPosition(), a);
                i3 = i2 + 1;
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return i2;
    }

    private ListItemObject a(NativeAdRef nativeAdRef) {
        ListItemObject listItemObject = new ListItemObject();
        listItemObject.setIs_ad(true);
        listItemObject.setAdItem(nativeAdRef);
        return listItemObject;
    }
}
