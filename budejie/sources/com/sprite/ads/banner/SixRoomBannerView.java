package com.sprite.ads.banner;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.sprite.ads.R;
import com.sprite.ads.internal.a.d;
import com.sprite.ads.internal.bean.data.LiveItem;
import com.sprite.ads.internal.bean.data.SixRoomItem;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.reporter.NoReporter;
import com.sprite.ads.third.sixroom.SixRoomAdData;
import com.sprite.ads.third.sixroom.SixRoomAdLoader;
import com.sprite.ads.third.sixroom.SixRoomListAdapter;

public class SixRoomBannerView extends RelativeLayout {
    SixRoomItem mAdItem;
    SixRoomAdLoader mAdLoader;
    SixRoomListAdapter mAdapter;
    Context mContext;
    BannerADListener mListener;
    SixRoomAdData mNativeAdData;
    ViewGroup mParentLayout;

    public SixRoomBannerView(SixRoomAdLoader sixRoomAdLoader, SixRoomItem sixRoomItem, SixRoomAdData sixRoomAdData, Context context, ViewGroup viewGroup, BannerADListener bannerADListener) {
        super(context);
        this.mContext = context;
        this.mAdLoader = sixRoomAdLoader;
        this.mAdItem = sixRoomItem;
        this.mNativeAdData = sixRoomAdData;
        this.mParentLayout = viewGroup;
        this.mListener = bannerADListener;
        this.mParentLayout.removeAllViews();
        this.mParentLayout.addView(this, new LayoutParams(-2, -2));
        initView();
    }

    private void initView() {
        View.inflate(this.mContext, R.layout.ad_six_rooms_list_item, this);
        GridView gridView = (GridView) findViewById(R.id.gv_room_list);
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                LiveItem liveItem = (LiveItem) SixRoomBannerView.this.mNativeAdData.getLiveItems().get(i);
                d.a("点击广告", "详情页|点击直播间");
                SixRoomBannerView.this.mAdItem.setUrl("mod://BDJ_To_SRLiveRoom@uid=" + liveItem.uid + "#rid=" + liveItem.rid);
                SixRoomBannerView.this.mListener.onADSkip(SixRoomBannerView.this.mAdItem);
            }
        });
        TextView textView = (TextView) findViewById(R.id.ad_six_room_view_all_text);
        ((TextView) findViewById(R.id.ad_six_room_change_text)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NativeAdData nativeAdData = SixRoomBannerView.this.mAdLoader.getNativeAdData();
                if (nativeAdData != null) {
                    d.a("点击广告", "详情页|点击换一批");
                    SixRoomBannerView.this.mNativeAdData = (SixRoomAdData) nativeAdData;
                    SixRoomBannerView.this.mAdapter.setLiveItems(SixRoomBannerView.this.mNativeAdData.getLiveItems());
                    SixRoomBannerView.this.mAdapter.notifyDataSetChanged();
                    return;
                }
                Toast.makeText(SixRoomBannerView.this.mContext, "暂时没有更多主播啦，请稍后重试", 0);
            }
        });
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                d.a("点击广告", "详情页|点击查看全部");
                SixRoomBannerView.this.mAdItem.setUrl("mod://BDJ_To_SixRooms");
                SixRoomBannerView.this.mListener.onADSkip(SixRoomBannerView.this.mAdItem);
            }
        });
        if (this.mNativeAdData == null || this.mNativeAdData.getLiveItems() == null) {
            this.mListener.onNoAD(0);
            return;
        }
        this.mAdapter = new SixRoomListAdapter(this.mContext, this.mNativeAdData.getLiveItems());
        gridView.setAdapter(this.mAdapter);
        this.mListener.onADReceive(new NoReporter(), true);
    }
}
