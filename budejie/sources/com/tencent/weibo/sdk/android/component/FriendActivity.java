package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.open.wpa.WPA;
import com.tencent.weibo.sdk.android.api.PublishWeiBoAPI;
import com.tencent.weibo.sdk.android.api.adapter.FriendAdapter;
import com.tencent.weibo.sdk.android.api.util.BackGroudSeletor;
import com.tencent.weibo.sdk.android.api.util.FirendCompare;
import com.tencent.weibo.sdk.android.api.util.HypyUtil;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.component.LetterListView.OnTouchingLetterChangedListener;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.Firend;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FriendActivity extends Activity implements OnTouchingLetterChangedListener, HttpCallback {
    private FriendAdapter adapter;
    private Map<String, List<Firend>> child = new HashMap();
    private ProgressDialog dialog;
    private EditText editText;
    private List<String> group = new ArrayList();
    private List<String> groups = new ArrayList();
    private LetterListView letterListView;
    private ExpandableListView listView;
    private Map<String, List<Firend>> newchild = new HashMap();
    private List<String> newgourp = new ArrayList();
    private int[] nums;
    private TextView textView;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        LinearLayout linearLayout = (LinearLayout) initview();
        getdate();
        setContentView(linearLayout);
    }

    private View initview() {
        View linearLayout = new LinearLayout(this);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        relativeLayout.setGravity(0);
        relativeLayout.setBackgroundDrawable(BackGroudSeletor.getdrawble("up_bg2x", this));
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        layoutParams2.addRule(9, -1);
        layoutParams2.addRule(15, -1);
        layoutParams2.topMargin = 10;
        layoutParams2.leftMargin = 10;
        layoutParams2.bottomMargin = 10;
        View button = new Button(this);
        button.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[]{"return_btn2x", "return_btn_hover"}, this));
        button.setText("  返回");
        button.setLayoutParams(layoutParams2);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FriendActivity.this.finish();
            }
        });
        View textView = new TextView(this);
        textView.setText("好友列表");
        textView.setTextColor(-1);
        textView.setTextSize(24.0f);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13, -1);
        textView.setLayoutParams(layoutParams3);
        relativeLayout.addView(textView);
        relativeLayout.addView(button);
        linearLayout.addView(relativeLayout);
        relativeLayout = new LinearLayout(this);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        relativeLayout.setOrientation(0);
        relativeLayout.setGravity(17);
        this.editText = new EditText(this);
        this.editText.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1.0f));
        this.editText.setPadding(20, 0, 10, 0);
        this.editText.setBackgroundDrawable(BackGroudSeletor.getdrawble("searchbg_", this));
        this.editText.setCompoundDrawablesWithIntrinsicBounds(BackGroudSeletor.getdrawble("search_", this), null, null, null);
        this.editText.setHint("搜索");
        this.editText.setTextSize(18.0f);
        this.editText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                FriendActivity.this.search(editable.toString());
            }
        });
        relativeLayout.addView(this.editText);
        linearLayout.addView(relativeLayout);
        this.listView = new ExpandableListView(this);
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(-1, -1);
        this.listView.setLayoutParams(layoutParams);
        this.listView.setGroupIndicator(null);
        View linearLayout2 = new LinearLayout(this);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.textView = new TextView(this);
        linearLayout2.setPadding(30, 0, 0, 0);
        linearLayout2.setGravity(16);
        this.textView.setTextSize(18.0f);
        this.textView.setTextColor(-1);
        this.textView.setText("常用联系人");
        linearLayout2.addView(this.textView);
        linearLayout2.setBackgroundColor(Color.parseColor("#b0bac3"));
        this.letterListView = new LetterListView((Context) this, this.group);
        this.letterListView.setOnTouchingLetterChangedListener(this);
        LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(50, -1);
        textView = new RelativeLayout(this);
        LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams5.addRule(11);
        this.letterListView.setLayoutParams(layoutParams5);
        textView.setLayoutParams(layoutParams6);
        textView.addView(this.listView);
        textView.addView(linearLayout2);
        textView.addView(this.letterListView);
        linearLayout.addView(textView);
        return linearLayout;
    }

    public void search(String str) {
        int i = 0;
        this.newgourp.clear();
        this.newchild.clear();
        for (int i2 = 0; i2 < this.group.size(); i2++) {
            for (int i3 = 0; i3 < ((List) this.child.get(this.group.get(i2))).size(); i3++) {
                if (((Firend) ((List) this.child.get(this.group.get(i2))).get(i3)).getNick().contains(str)) {
                    if (this.newchild.get(this.group.get(i2)) == null) {
                        List arrayList = new ArrayList();
                        arrayList.add((Firend) ((List) this.child.get(this.group.get(i2))).get(i3));
                        this.newchild.put((String) this.group.get(i2), arrayList);
                        this.newgourp.add((String) this.group.get(i2));
                    } else {
                        ((List) this.newchild.get(this.group.get(i2))).add((Firend) ((List) this.child.get(this.group.get(i2))).get(i3));
                    }
                }
            }
        }
        Log.d("size", new StringBuilder(String.valueOf(this.newgourp.size())).append("---").append(this.newchild.size()).toString());
        this.nums = new int[this.newgourp.size()];
        while (i < this.nums.length) {
            this.nums[i] = totle(i);
            i++;
        }
        this.letterListView.setB(this.newgourp);
        this.adapter.setChild(this.newchild);
        this.adapter.setGroup(this.newgourp);
        this.adapter.notifyDataSetChanged();
        ex(this.newgourp, this.newchild);
    }

    private void getdate() {
        if (this.dialog == null) {
            this.dialog = new ProgressDialog(this);
            this.dialog.setMessage("请稍后...");
            this.dialog.show();
        }
        new PublishWeiBoAPI(new AccountModel(Util.getSharePersistent(getApplicationContext(), "ACCESS_TOKEN"))).mutual_list(this, this, null, 0, 0, 0, 10);
    }

    private void ex(final List<String> list, final Map<String, List<Firend>> map) {
        for (int i = 0; i < list.size(); i++) {
            this.listView.expandGroup(i);
        }
        this.listView.setOnGroupExpandListener(new OnGroupExpandListener() {
            public void onGroupExpand(int i) {
            }
        });
        this.listView.setOnGroupClickListener(new OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                return true;
            }
        });
        this.listView.setOnChildClickListener(new OnChildClickListener() {
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                Intent intent = new Intent();
                intent.setClass(FriendActivity.this, PublishActivity.class);
                intent.putExtra("firend", ((Firend) ((List) map.get(list.get(i))).get(i2)).getNick());
                FriendActivity.this.setResult(-1, intent);
                FriendActivity.this.finish();
                return true;
            }
        });
        this.listView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                Log.d("first", new StringBuilder(String.valueOf(i)).toString());
                int i4 = 0;
                while (i4 < list.size()) {
                    if (i4 == 0 && i >= 0 && i < FriendActivity.this.nums[i4]) {
                        FriendActivity.this.textView.setText(((String) list.get(i4)).toUpperCase());
                        return;
                    } else if (i >= FriendActivity.this.nums[i4] || i < FriendActivity.this.nums[i4 - 1]) {
                        i4++;
                    } else {
                        FriendActivity.this.textView.setText(((String) list.get(i4)).toUpperCase());
                        return;
                    }
                }
            }
        });
    }

    public void onTouchingLetterChanged(int i) {
        this.listView.setSelectedGroup(i);
    }

    public void onResult(Object obj) {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
        if (obj != null && ((ModelResult) obj).isSuccess()) {
            getJsonData((JSONObject) ((ModelResult) obj).getObj());
            this.nums = new int[this.group.size()];
            for (int i = 0; i < this.nums.length; i++) {
                this.nums[i] = totle(i);
            }
            this.letterListView.setB(this.group);
            this.adapter = new FriendAdapter(this, this.group, this.child);
            this.listView.setAdapter(this.adapter);
            ex(this.group, this.child);
            Log.d("发送成功", obj.toString());
        }
    }

    private void getJsonData(JSONObject jSONObject) {
        List arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONObject("data").getJSONArray("info");
            for (int i = 0; i < jSONArray.length(); i++) {
                Firend firend = new Firend();
                firend.setNick(((JSONObject) jSONArray.get(i)).getString("nick"));
                firend.setName(((JSONObject) jSONArray.get(i)).getString("name"));
                firend.setHeadurl(new StringBuilder(String.valueOf(((JSONObject) jSONArray.get(i)).getString("headurl").replaceAll("\\/", "/"))).append("/180").toString());
                arrayList.add(firend);
            }
            Collections.sort(arrayList, new FirendCompare());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (this.child.get(HypyUtil.cn2py(((Firend) arrayList.get(i2)).getNick()).substring(0, 1).toUpperCase()) != null) {
                ((List) this.child.get(HypyUtil.cn2py(((Firend) arrayList.get(i2)).getNick()).substring(0, 1).toUpperCase())).add((Firend) arrayList.get(i2));
            } else {
                Log.d(WPA.CHAT_TYPE_GROUP, HypyUtil.cn2py(((Firend) arrayList.get(i2)).getNick()).substring(0, 1));
                this.group.add(HypyUtil.cn2py(((Firend) arrayList.get(i2)).getNick()).substring(0, 1).toUpperCase());
                List arrayList2 = new ArrayList();
                arrayList2.add((Firend) arrayList.get(i2));
                this.child.put(HypyUtil.cn2py(((Firend) arrayList.get(i2)).getNick()).substring(0, 1).toUpperCase(), arrayList2);
            }
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }

    private int totle(int i) {
        if (i == 0) {
            return ((List) this.child.get(this.group.get(i))).size() + 1;
        }
        return (((List) this.child.get(this.group.get(i))).size() + 1) + totle(i - 1);
    }
}
