package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.tencent.weibo.sdk.android.api.PublishWeiBoAPI;
import com.tencent.weibo.sdk.android.api.adapter.ConversationAdapter;
import com.tencent.weibo.sdk.android.api.util.BackGroudSeletor;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConversationActivity extends Activity implements HttpCallback {
    private ConversationAdapter adapter;
    private ProgressDialog dialog;
    private EditText editText;
    private List<String> list;
    private ListView listView;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView((LinearLayout) initview());
        this.listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Toast.makeText(ConversationActivity.this, new StringBuilder(String.valueOf(i)).toString(), 100).show();
            }
        });
        if (this.dialog == null) {
            this.dialog = new ProgressDialog(this);
            this.dialog.setMessage("请稍后...");
            this.dialog.setCancelable(false);
        }
        this.dialog.show();
        new PublishWeiBoAPI(new AccountModel(Util.getSharePersistent(getApplicationContext(), "ACCESS_TOKEN"))).recent_used(this, this, null, 15, 1, 0);
    }

    private View initview() {
        View linearLayout = new LinearLayout(this);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        View linearLayout2 = new LinearLayout(this);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        linearLayout2.setLayoutParams(layoutParams2);
        linearLayout2.setOrientation(0);
        linearLayout2.setBackgroundDrawable(BackGroudSeletor.getdrawble("up_bg2x", this));
        linearLayout2.setPadding(20, 0, 20, 0);
        linearLayout2.setGravity(16);
        View linearLayout3 = new LinearLayout(this);
        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1.0f));
        linearLayout3.setPadding(0, 0, 12, 0);
        this.editText = new EditText(this);
        this.editText.setSingleLine(true);
        this.editText.setLines(1);
        this.editText.setTextSize(18.0f);
        this.editText.setHint("搜索话题");
        this.editText.setPadding(20, 0, 10, 0);
        this.editText.setBackgroundDrawable(BackGroudSeletor.getdrawble("huati_input2x", this));
        this.editText.setCompoundDrawablesWithIntrinsicBounds(BackGroudSeletor.getdrawble("huati_icon_hover2x", this), null, null, null);
        this.editText.setLayoutParams(layoutParams);
        View button = new Button(this);
        button.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[]{"sent_btn_22x", "sent_btn_hover"}, this));
        button.setTextColor(-1);
        button.setText("取消");
        linearLayout3.addView(this.editText);
        linearLayout2.addView(linearLayout3);
        linearLayout2.addView(button);
        linearLayout.addView(linearLayout2);
        linearLayout2 = new LinearLayout(this);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1.0f));
        this.listView = new ListView(this);
        this.listView.setDivider(BackGroudSeletor.getdrawble("table_lie_", this));
        this.listView.setLayoutParams(layoutParams);
        this.editText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                List arrayList = new ArrayList();
                for (int i = 0; i < ConversationActivity.this.list.size(); i++) {
                    if (((String) ConversationActivity.this.list.get(i)).contains(editable.toString())) {
                        arrayList.add((String) ConversationActivity.this.list.get(i));
                    }
                }
                ConversationActivity.this.adapter.setCvlist(arrayList);
                ConversationActivity.this.adapter.notifyDataSetChanged();
                ConversationActivity.this.click(arrayList);
            }
        });
        LinearLayout linearLayout4 = new LinearLayout(this);
        linearLayout3 = new Button(this);
        linearLayout4.setGravity(17);
        linearLayout3.setLayoutParams(layoutParams2);
        linearLayout3.setText("abc");
        linearLayout3.setTextColor(-16777216);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ConversationActivity.this.finish();
            }
        });
        linearLayout4.addView(linearLayout3);
        linearLayout2.addView(this.listView);
        linearLayout.addView(linearLayout2);
        return linearLayout;
    }

    private List<String> initData(JSONObject jSONObject) {
        List<String> arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONObject("data").getJSONArray("info");
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add("#" + jSONArray.getJSONObject(i).getString("text") + "#");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private void click(final List<String> list) {
        this.listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Intent intent = new Intent();
                intent.setClass(ConversationActivity.this, PublishActivity.class);
                intent.putExtra("conversation", (String) list.get(i));
                ConversationActivity.this.setResult(-1, intent);
                ConversationActivity.this.finish();
            }
        });
    }

    protected void onStop() {
        super.onStop();
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }

    public void onResult(Object obj) {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
        if (obj != null && ((ModelResult) obj).isSuccess()) {
            Log.d("发送成功", ((ModelResult) obj).getObj().toString());
            this.list = initData((JSONObject) ((ModelResult) obj).getObj());
            this.adapter = new ConversationAdapter(this, this.list);
            this.listView.setAdapter(this.adapter);
            click(this.list);
        }
    }
}
