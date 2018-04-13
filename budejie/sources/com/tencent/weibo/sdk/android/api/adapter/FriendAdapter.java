package com.tencent.weibo.sdk.android.api.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.tencent.weibo.sdk.android.api.util.BackGroudSeletor;
import com.tencent.weibo.sdk.android.model.Firend;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class FriendAdapter extends BaseExpandableListAdapter {
    private Map<String, List<Firend>> child;
    private Context ctx;
    private List<String> group;

    public FriendAdapter(Context context, List<String> list, Map<String, List<Firend>> map) {
        this.ctx = context;
        this.group = list;
        this.child = map;
    }

    public Object getChild(int i, int i2) {
        return ((List) this.child.get(getGroup(i))).get(i2);
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public List<String> getGroup() {
        return this.group;
    }

    public void setGroup(List<String> list) {
        this.group = list;
    }

    public Map<String, List<Firend>> getChild() {
        return this.child;
    }

    public void setChild(Map<String, List<Firend>> map) {
        this.child = map;
    }

    public View getChildView(final int i, final int i2, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = new LinearLayout(this.ctx);
            LayoutParams layoutParams = new LayoutParams(-1, -2);
            view.setOrientation(0);
            view.setGravity(16);
            view.setPadding(15, 0, 10, 0);
            view.setBackgroundDrawable(BackGroudSeletor.getdrawble("childbg_", this.ctx));
            View imageView = new ImageView(this.ctx);
            imageView.setId(4502);
            imageView.setLayoutParams(new LayoutParams(45, 45));
            imageView.setImageDrawable(BackGroudSeletor.getdrawble("logo", this.ctx));
            View linearLayout = new LinearLayout(this.ctx);
            linearLayout.setPadding(10, 0, 0, 0);
            linearLayout.setGravity(16);
            linearLayout.setOrientation(1);
            View textView = new TextView(this.ctx);
            View textView2 = new TextView(this.ctx);
            textView.setTextSize(18.0f);
            textView.setId(4500);
            textView.setTextColor(Color.parseColor("#32769b"));
            textView2.setTextSize(12.0f);
            textView2.setId(4501);
            textView2.setTextColor(Color.parseColor("#a6c6d5"));
            textView.setText(((Firend) getChild(i, i2)).getNick());
            textView2.setText(((Firend) getChild(i, i2)).getName());
            linearLayout.addView(textView);
            linearLayout.addView(textView2);
            view.setBackgroundDrawable(BackGroudSeletor.getdrawble("childbg_", this.ctx));
            view.addView(imageView);
            view.addView(linearLayout);
            view.setTag(view);
        } else {
            LinearLayout linearLayout2 = (LinearLayout) view.getTag();
            TextView textView3 = (TextView) linearLayout2.findViewById(4500);
            TextView textView4 = (TextView) linearLayout2.findViewById(4501);
            ((ImageView) ((LinearLayout) linearLayout2.getTag()).findViewById(4502)).setImageDrawable(BackGroudSeletor.getdrawble("logo", this.ctx));
            textView3.setText(((Firend) getChild(i, i2)).getNick());
            textView4.setText(((Firend) getChild(i, i2)).getName());
        }
        if (((Firend) getChild(i, i2)).getHeadurl() != null) {
            new AsyncTask<String, Integer, Bitmap>() {
                protected Bitmap doInBackground(String... strArr) {
                    try {
                        Log.d("image urel", new StringBuilder(String.valueOf(((Firend) FriendAdapter.this.getChild(i, i2)).getHeadurl())).toString());
                        URL url = new URL(((Firend) FriendAdapter.this.getChild(i, i2)).getHeadurl());
                        String headerField = url.openConnection().getHeaderField(0);
                        if (headerField.indexOf("200") < 0) {
                            Log.d("图片文件不存在或路径错误，错误代码：", headerField);
                        }
                        return BitmapFactory.decodeStream(url.openStream());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        return null;
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return null;
                    }
                }

                protected void onPreExecute() {
                    super.onPreExecute();
                }

                protected void onPostExecute(Bitmap bitmap) {
                    ImageView imageView = (ImageView) ((LinearLayout) view.getTag()).findViewById(4502);
                    if (bitmap == null) {
                        imageView.setImageDrawable(BackGroudSeletor.getdrawble("logo", FriendAdapter.this.ctx));
                    } else {
                        imageView.setImageBitmap(bitmap);
                    }
                    super.onPostExecute(bitmap);
                }
            }.execute(new String[]{((Firend) getChild(i, i2)).getHeadurl()});
        } else {
            ((ImageView) ((LinearLayout) view.getTag()).findViewById(4502)).setImageDrawable(BackGroudSeletor.getdrawble("logo", this.ctx));
        }
        return view;
    }

    public int getChildrenCount(int i) {
        return ((List) this.child.get(this.group.get(i))).size();
    }

    public Object getGroup(int i) {
        return this.group.get(i);
    }

    public int getGroupCount() {
        return this.group.size();
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = new LinearLayout(this.ctx);
            View textView = new TextView(this.ctx);
            view.setPadding(30, 0, 0, 0);
            view.setGravity(16);
            textView.setTextSize(18.0f);
            textView.setTextColor(-1);
            textView.setWidth(40);
            textView.setText(getGroup(i).toString().toUpperCase());
            view.addView(textView);
            view.setBackgroundDrawable(BackGroudSeletor.getdrawble("groupbg_", this.ctx));
            textView.setTag(Integer.valueOf(i));
            view.setTag(textView);
            return view;
        }
        ((TextView) view.getTag()).setText(getGroup(i).toString().toUpperCase());
        return view;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }
}
