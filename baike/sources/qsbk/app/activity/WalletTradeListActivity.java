package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.model.TradeRecord;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;
import qsbk.app.widget.VerticalImageSpan;

public class WalletTradeListActivity extends BaseActionBarActivity implements PtrListener {
    BaseImageAdapter a;
    private PtrLayout b;
    private ListView c;
    private TipsHelper d;
    private EncryptHttpTask e;
    private boolean f = true;
    private String g;
    private ArrayList<Object> h = new ArrayList();

    private class a extends BaseImageAdapter {
        final /* synthetic */ WalletTradeListActivity a;

        public a(WalletTradeListActivity walletTradeListActivity, ArrayList<Object> arrayList, Activity activity) {
            this.a = walletTradeListActivity;
            super(arrayList, activity);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            int color;
            Exception e;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_trade_record, null);
                b bVar2 = new b(this.a, view);
                view.setTag(bVar2);
                bVar = bVar2;
            } else {
                bVar = (b) view.getTag();
            }
            Object item = getItem(i);
            if (item != null && (item instanceof TradeRecord)) {
                TradeRecord tradeRecord = (TradeRecord) item;
                if (tradeRecord != null) {
                    int argb;
                    bVar.a.setText(tradeRecord.content);
                    bVar.c.setText(tradeRecord.money);
                    bVar.b.setText(tradeRecord.getTimeString());
                    CharSequence spannableStringBuilder = new SpannableStringBuilder(tradeRecord.getTimeString() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    int length = spannableStringBuilder.length();
                    spannableStringBuilder.append(tradeRecord.source);
                    if (TextUtils.isEmpty(tradeRecord.toast)) {
                        argb = Color.argb(255, 143, 143, 149);
                    } else {
                        argb = Color.argb(255, 248, 78, 82);
                    }
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(argb), length, spannableStringBuilder.length(), 33);
                    if (!TextUtils.isEmpty(tradeRecord.toast)) {
                        argb = spannableStringBuilder.length();
                        spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                        VerticalImageSpan verticalImageSpan = new VerticalImageSpan(this.a.getDrawable(R.drawable.ic_exclamation_mark));
                        verticalImageSpan.setMargin(UIHelper.dip2px(this.a, 3.0f), UIHelper.dip2px(this.a, 3.0f));
                        spannableStringBuilder.setSpan(verticalImageSpan, argb, spannableStringBuilder.length(), 33);
                        spannableStringBuilder.setSpan(new agj(this, tradeRecord), argb, spannableStringBuilder.length(), 33);
                    }
                    bVar.b.setText(spannableStringBuilder);
                    bVar.b.setHighlightColor(0);
                    bVar.b.setMovementMethod(LinkMovementMethod.getInstance());
                    argb = Color.parseColor("#b8b8b8");
                    try {
                        Theme theme = viewGroup.getContext().getTheme();
                        int[] iArr = new int[1];
                        iArr[0] = tradeRecord.isPositive() ? R.attr.color_yellow : R.attr.color_black;
                        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(iArr);
                        color = obtainStyledAttributes.getColor(0, 0);
                        try {
                            obtainStyledAttributes.recycle();
                        } catch (Exception e2) {
                            e = e2;
                            e.printStackTrace();
                            bVar.c.setTextColor(color);
                            return view;
                        }
                    } catch (Exception e3) {
                        Exception exception = e3;
                        color = argb;
                        e = exception;
                        e.printStackTrace();
                        bVar.c.setTextColor(color);
                        return view;
                    }
                    bVar.c.setTextColor(color);
                }
            }
            return view;
        }
    }

    class b {
        TextView a;
        TextView b;
        TextView c;
        final /* synthetic */ WalletTradeListActivity d;

        public b(WalletTradeListActivity walletTradeListActivity, View view) {
            this.d = walletTradeListActivity;
            this.a = (TextView) view.findViewById(R.id.title);
            this.b = (TextView) view.findViewById(R.id.sub_title);
            this.c = (TextView) view.findViewById(R.id.money);
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, WalletTradeListActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    protected String f() {
        return getString(R.string.trade_list);
    }

    protected int a() {
        return R.layout.activity_wallet_trade_list;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.b = (PtrLayout) findViewById(R.id.ptr);
        this.b.setRefreshEnable(true);
        this.b.setLoadMoreEnable(true);
        this.b.setPtrListener(this);
        this.c = (ListView) findViewById(R.id.listview);
        this.b.autoRefresh();
        this.a = new a(this, this.h, this);
        this.c.setAdapter(this.a);
        this.d = new TipsHelper(findViewById(R.id.tips));
    }

    public void onRefresh() {
        this.f = true;
        this.b.loadMoreDone(true);
        g();
    }

    public void onLoadMore() {
        g();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.e != null) {
            this.e.cancel(true);
        }
    }

    private synchronized void g() {
        this.e = new EncryptHttpTask(null, Constants.WALLET_RECORDS, new agi(this));
        Map hashMap = new HashMap();
        hashMap.put(ParamKey.OFFSET, this.g);
        this.e.setMapParams(hashMap);
        this.e.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
