package com.sina.weibo.sdk.register.mobile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import cn.xiaochuankeji.aop.permission.c;
import com.sina.weibo.sdk.component.view.TitleBar;
import com.sina.weibo.sdk.component.view.TitleBar.ListenerOnTitleBtnClicked;
import com.sina.weibo.sdk.register.mobile.LetterIndexBar.OnIndexChangeListener;
import com.sina.weibo.sdk.utils.ResourceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class SelectCountryActivity extends Activity implements OnIndexChangeListener {
    private static final String CHINA_CN = "中国";
    private static final String CHINA_EN = "China";
    private static final String CHINA_TW = "中國";
    public static final String EXTRA_COUNTRY_CODE = "code";
    public static final String EXTRA_COUNTRY_NAME = "name";
    private static final String INFO_CN = "常用";
    private static final String INFO_EN = "Common";
    private static final String INFO_TW = "常用";
    private static final String SELECT_COUNTRY_EN = "Region";
    private static final String SELECT_COUNTRY_ZH_CN = "选择国家";
    private static final String SELECT_COUNTRY_ZH_TW = "選擇國家";
    private static final /* synthetic */ a ajc$tjp_0 = null;
    private List<Country>[] arrSubCountry;
    String countryStr = "";
    private List<IndexCountry> indexCountries = new ArrayList();
    private CountryAdapter mAdapter;
    private List<Country> mCountries;
    private FrameLayout mFrameLayout;
    private LetterIndexBar mLetterIndexBar;
    private ListView mListView;
    private RelativeLayout mMainLayout;
    private CountryList result;

    public class AjcClosure1 extends org.aspectj.a.a.a {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            SelectCountryActivity.onCreate_aroundBody0((SelectCountryActivity) objArr2[0], (Bundle) objArr2[1], (org.aspectj.lang.a) objArr2[2]);
            return null;
        }
    }

    private class CountryAdapter extends BaseAdapter {
        private CountryAdapter() {
        }

        public int getCount() {
            if (SelectCountryActivity.this.indexCountries != null) {
                return SelectCountryActivity.this.indexCountries.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            if (SelectCountryActivity.this.indexCountries == null || SelectCountryActivity.this.indexCountries.isEmpty()) {
                return null;
            }
            if (i == SelectCountryActivity.this.indexCountries.size()) {
                return null;
            }
            IndexCountry indexCountry = (IndexCountry) SelectCountryActivity.this.indexCountries.get(i);
            if (indexCountry.indexInList == -1) {
                return null;
            }
            return SelectCountryActivity.this.arrSubCountry[indexCountry.indexInListArray].get(indexCountry.indexInList);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            IndexCountry indexCountry = (IndexCountry) SelectCountryActivity.this.indexCountries.get(i);
            Country country;
            if (view == null) {
                if (indexCountry.indexInList == -1) {
                    return createTitleView(indexCountry.indexInListArray);
                }
                country = (Country) SelectCountryActivity.this.arrSubCountry[indexCountry.indexInListArray].get(indexCountry.indexInList);
                return new SelectCountryItemView(SelectCountryActivity.this, country.getName(), country.getCode());
            } else if (indexCountry.indexInList != -1) {
                country = (Country) SelectCountryActivity.this.arrSubCountry[indexCountry.indexInListArray].get(indexCountry.indexInList);
                if (view instanceof SelectCountryTitleView) {
                    return new SelectCountryItemView(SelectCountryActivity.this, country.getName(), country.getCode());
                }
                ((SelectCountryItemView) view).updateContent(country.getName(), country.getCode());
                return view;
            } else if (!(view instanceof SelectCountryTitleView)) {
                return createTitleView(indexCountry.indexInListArray);
            } else {
                if (indexCountry.indexInListArray != 0) {
                    return createTitleView(indexCountry.indexInListArray);
                }
                ((SelectCountryTitleView) view).update(ResourceManager.getString(SelectCountryActivity.this, SelectCountryActivity.INFO_EN, "常用", "常用"));
                return view;
            }
        }

        private SelectCountryTitleView createTitleView(int i) {
            SelectCountryTitleView selectCountryTitleView = new SelectCountryTitleView(SelectCountryActivity.this.getApplicationContext());
            if (i == 0) {
                selectCountryTitleView.setTitle(ResourceManager.getString(SelectCountryActivity.this, SelectCountryActivity.INFO_EN, "常用", "常用"));
            } else {
                selectCountryTitleView.setTitle(String.valueOf((char) ((i + 65) - 1)));
            }
            return selectCountryTitleView;
        }
    }

    private class IndexCountry {
        int indexInList;
        int indexInListArray;

        IndexCountry(int i, int i2) {
            this.indexInListArray = i;
            this.indexInList = i2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof IndexCountry) || this.indexInList != -1) {
                return false;
            }
            IndexCountry indexCountry = (IndexCountry) obj;
            if (this.indexInListArray == indexCountry.indexInListArray && this.indexInList == indexCountry.indexInList) {
                return true;
            }
            return false;
        }
    }

    static {
        ajc$preClinit();
    }

    private static /* synthetic */ void ajc$preClinit() {
        b bVar = new b("SelectCountryActivity.java", SelectCountryActivity.class);
        ajc$tjp_0 = bVar.a("method-execution", bVar.a("4", "onCreate", "com.sina.weibo.sdk.register.mobile.SelectCountryActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 80);
    }

    static final /* synthetic */ void onCreate_aroundBody0(SelectCountryActivity selectCountryActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        selectCountryActivity.initView();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(ajc$tjp_0, this, this, bundle);
        c.c().a(new AjcClosure1(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    private void initView() {
        this.mMainLayout = new RelativeLayout(this);
        this.mMainLayout.setLayoutParams(new LayoutParams(-1, -1));
        View titleBar = new TitleBar(this);
        titleBar.setId(1);
        titleBar.setLeftBtnBg(ResourceManager.createStateListDrawable(this, "weibosdk_navigationbar_back.png", "weibosdk_navigationbar_back_highlighted.png"));
        titleBar.setTitleBarText(ResourceManager.getString(this, SELECT_COUNTRY_EN, SELECT_COUNTRY_ZH_CN, SELECT_COUNTRY_ZH_TW));
        titleBar.setTitleBarClickListener(new ListenerOnTitleBtnClicked() {
            public void onLeftBtnClicked() {
                SelectCountryActivity.this.setResult(0);
                SelectCountryActivity.this.finish();
            }
        });
        this.mMainLayout.addView(titleBar);
        this.mFrameLayout = new FrameLayout(this);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(3, titleBar.getId());
        this.mFrameLayout.setLayoutParams(layoutParams);
        this.mMainLayout.addView(this.mFrameLayout);
        this.mListView = new ListView(this);
        this.mListView.setLayoutParams(new AbsListView.LayoutParams(-1, -1));
        this.mListView.setFadingEdgeLength(0);
        this.mListView.setSelector(new ColorDrawable(0));
        this.mListView.setDividerHeight(ResourceManager.dp2px(this, 1));
        this.mListView.setCacheColorHint(0);
        this.mListView.setDrawSelectorOnTop(false);
        this.mListView.setScrollingCacheEnabled(false);
        this.mListView.setScrollbarFadingEnabled(false);
        this.mListView.setVerticalScrollBarEnabled(false);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Country country = (Country) SelectCountryActivity.this.mAdapter.getItem(i);
                if (country != null) {
                    Intent intent = new Intent();
                    intent.putExtra("code", country.getCode());
                    intent.putExtra("name", country.getName());
                    SelectCountryActivity.this.setResult(-1, intent);
                    SelectCountryActivity.this.finish();
                }
            }
        });
        this.mFrameLayout.addView(this.mListView);
        this.mAdapter = new CountryAdapter();
        this.mListView.setAdapter(this.mAdapter);
        this.mLetterIndexBar = new LetterIndexBar(this);
        this.mLetterIndexBar.setIndexChangeListener(this);
        ViewGroup.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -1);
        layoutParams2.gravity = 5;
        this.mLetterIndexBar.setLayoutParams(layoutParams2);
        this.mFrameLayout.addView(this.mLetterIndexBar);
        PinyinUtils.getInstance(this);
        Locale language = ResourceManager.getLanguage();
        if (Locale.SIMPLIFIED_CHINESE.equals(language)) {
            this.countryStr = ResourceManager.readCountryFromAsset(this, "countryCode.txt");
        } else if (Locale.TRADITIONAL_CHINESE.equals(language)) {
            this.countryStr = ResourceManager.readCountryFromAsset(this, "countryCodeTw.txt");
        } else {
            this.countryStr = ResourceManager.readCountryFromAsset(this, "countryCodeEn.txt");
        }
        this.result = new CountryList(this.countryStr);
        this.mCountries = this.result.countries;
        this.arrSubCountry = subCountries(this.mCountries);
        this.indexCountries = compose(this.arrSubCountry);
        this.mAdapter.notifyDataSetChanged();
        setContentView(this.mMainLayout);
    }

    protected void onPause() {
        super.onPause();
    }

    public void onIndexChange(int i) {
        if (this.arrSubCountry != null && i < this.arrSubCountry.length && this.arrSubCountry[i] != null) {
            this.mListView.setSelection(this.indexCountries.indexOf(new IndexCountry(i, -1)));
        }
    }

    private List<Country>[] subCountries(List<Country> list) {
        List[] listArr = new ArrayList[27];
        Country country = new Country();
        country.setCode(Country.CHINA_CODE);
        country.setName(ResourceManager.getString(this, CHINA_EN, CHINA_CN, CHINA_TW));
        listArr[0] = new ArrayList();
        listArr[0].add(country);
        for (int i = 0; i < list.size(); i++) {
            country = (Country) list.get(i);
            if (country.getCode().equals("00852") || country.getCode().equals("00853") || country.getCode().equals("00886")) {
                listArr[0].add(country);
            } else {
                int charAt = (country.getPinyin().charAt(0) - 97) + 1;
                if (listArr[charAt] == null) {
                    listArr[charAt] = new ArrayList();
                }
                listArr[charAt].add(country);
            }
        }
        return listArr;
    }

    private List<IndexCountry> compose(List<Country>[] listArr) {
        List<IndexCountry> arrayList = new ArrayList();
        if (listArr != null) {
            for (int i = 0; i < listArr.length; i++) {
                List list = listArr[i];
                if (list != null && list.size() > 0) {
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        if (i2 == 0) {
                            arrayList.add(new IndexCountry(i, -1));
                        }
                        arrayList.add(new IndexCountry(i, i2));
                    }
                }
            }
        }
        return arrayList;
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
