package com.budejie.www.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.label.enumeration.PlatePostEnum;
import com.budejie.www.activity.noticesetting.NotificationSettingsActivity;
import com.budejie.www.activity.textcomment.MakeTextCommentsActivity;
import com.budejie.www.activity.video.CommentItemVideoActivity;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.view.CustomListView;
import com.budejie.www.bean.BaseCommentItem;
import com.budejie.www.bean.CmtMyTieziItem;
import com.budejie.www.bean.CommentItemForNews;
import com.budejie.www.bean.DingOrCaiCommentNewsItem;
import com.budejie.www.bean.HotComment;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.MentionedItem;
import com.budejie.www.bean.MyNewsData;
import com.budejie.www.bean.MyNewsItem;
import com.budejie.www.bean.NewCommentItem;
import com.budejie.www.bean.ReplyNewsItem;
import com.budejie.www.bean.SystemNewsItem;
import com.budejie.www.c.d;
import com.budejie.www.c.h;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.j;
import com.budejie.www.util.z;
import com.elves.update.DownloadServer;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.Collection;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class MyNewsActivity extends SensorBaseActivity {
    private OnItemClickListener A = new OnItemClickListener(this) {
        final /* synthetic */ MyNewsActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            MyNewsItem myNewsItem = (MyNewsItem) adapterView.getItemAtPosition(i);
            if ("post".equals(myNewsItem.getType()) || "voice_post".equals(myNewsItem.getType()) || "video_post".equals(myNewsItem.getType())) {
                this.a.a(myNewsItem.getListItemObject());
            } else if ("cding".equals(myNewsItem.getType()) || "voice_cding".equals(myNewsItem.getType()) || "video_cding".equals(myNewsItem.getType()) || "ccai".equals(myNewsItem.getType())) {
                this.a.a(myNewsItem.getListItemObject());
            } else if ("hot_comment".equals(myNewsItem.getType())) {
                this.a.a(myNewsItem.getListItemObject());
            } else if ("replay".equals(myNewsItem.getType()) || "voice_replay".equals(myNewsItem.getType()) || "video_replay".equals(myNewsItem.getType())) {
                r0 = myNewsItem.getListItemObject();
                an.a(r0, this.a.r, this.a.s);
                com.budejie.www.util.a.a(this.a.b, r0, "", false);
            } else if ("ugc".equals(myNewsItem.getType()) || "voice_ugc".equals(myNewsItem.getType()) || "video_ugc".equals(myNewsItem.getType())) {
                this.a.a(myNewsItem.getListItemObject());
            } else if (com.umeng.analytics.pro.d.c.a.equals(myNewsItem.getType())) {
                SystemNewsItem systemNewsItem = myNewsItem.getSystemNewsItem();
                if (!this.a.a(systemNewsItem, myNewsItem.getType())) {
                    String linkType = systemNewsItem.getLinkType();
                    String link = systemNewsItem.getLink();
                    if (!TextUtils.isEmpty(link)) {
                        if ("0".equals(linkType)) {
                            this.a.b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(link)));
                        } else if ("1".equals(linkType)) {
                            Intent intent = new Intent(this.a.b, DetailContentActivity.class);
                            intent.putExtra("operator", com.umeng.analytics.pro.d.c.a);
                            intent.putExtra("url", link);
                            this.a.b.startActivity(intent);
                        } else if ("2".equals(linkType)) {
                            this.a.b(systemNewsItem);
                        } else {
                            this.a.b(systemNewsItem);
                        }
                    }
                }
            } else if ("mentioned".equals(myNewsItem.getType())) {
                this.a.a(myNewsItem.getListItemObject());
            } else if ("url_topic".equals(myNewsItem.getType())) {
                r0 = new ListItemObject();
                r0.setWid("16529619");
                com.budejie.www.util.a.a(this.a.b, r0, "", false);
            } else if ("forum_jing_topic".equals(myNewsItem.getType()) || "forum_top_topic".equals(myNewsItem.getType())) {
                this.a.a(myNewsItem.getSystemNewsItem(), myNewsItem.getType());
            } else {
                this.a.b(myNewsItem.getSystemNewsItem());
            }
        }
    };
    private com.budejie.www.activity.view.CustomListView.b B = new com.budejie.www.activity.view.CustomListView.b(this) {
        final /* synthetic */ MyNewsActivity a;

        {
            this.a = r1;
        }

        public void a() {
            if (!an.a(this.a.b)) {
                this.a.f = an.a(this.a.b, this.a.b.getString(R.string.nonet), -1);
                this.a.f.show();
            } else if (!this.a.k) {
                this.a.f = an.a(this.a.b, this.a.b.getString(R.string.no_more_data), -1);
                this.a.f.show();
                this.a.c.setmEnablePullLoad(false);
            } else if (this.a.g != null) {
                if (this.a.e != null) {
                    ((TextView) this.a.e.findViewById(R.id.message_list_more_tv)).setText(R.string.more_info);
                    this.a.e.setVisibility(0);
                }
                if (!this.a.j) {
                    this.a.j = true;
                    if (this.a.o == null || !this.a.o.isEmpty()) {
                        this.a.c();
                    }
                }
            }
        }
    };
    private OnClickListener C = new OnClickListener(this) {
        final /* synthetic */ MyNewsActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            MyNewsItem myNewsItem = (MyNewsItem) view.getTag();
            Intent intent;
            if (myNewsItem.getCmtMyTieziItem() != null) {
                CmtMyTieziItem cmtMyTieziItem = myNewsItem.getCmtMyTieziItem();
                intent = new Intent(this.a.b, MakeTextCommentsActivity.class);
                intent.putExtra("posts_id", myNewsItem.getListItemObject().getWid());
                intent.putExtra("reply_comment", this.a.a(cmtMyTieziItem.r_cinfo));
                this.a.b.startActivity(intent);
            } else if (myNewsItem.getReplyNewsItem() != null) {
                ReplyNewsItem replyNewsItem = myNewsItem.getReplyNewsItem();
                intent = new Intent(this.a.b, MakeTextCommentsActivity.class);
                intent.putExtra("posts_id", myNewsItem.getListItemObject().getWid());
                intent.putExtra("reply_comment", this.a.a(replyNewsItem.r_cinfo));
                this.a.b.startActivity(intent);
            }
        }
    };
    private OnClickListener D = new OnClickListener(this) {
        final /* synthetic */ MyNewsActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            aa.b("postLayoutClickListener", "System.currentTimeMillis()-contentClickTime=" + (System.currentTimeMillis() - this.a.x));
            if (System.currentTimeMillis() - this.a.x > 500) {
                this.a.a((ListItemObject) view.getTag());
            }
        }
    };
    private OnClickListener E = new OnClickListener(this) {
        final /* synthetic */ MyNewsActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            DingOrCaiCommentNewsItem dingOrCaiCommentNewsItem = (DingOrCaiCommentNewsItem) view.getTag();
            Intent intent = new Intent(this.a.b, DingMeActivity.class);
            intent.putExtra("comment_id", dingOrCaiCommentNewsItem.r_cinfo.id);
            if (dingOrCaiCommentNewsItem.isCai) {
                intent.putExtra("page_type", 2);
            } else {
                intent.putExtra("page_type", 1);
            }
            this.a.b.startActivity(intent);
        }
    };
    private Handler F = new Handler(this) {
        final /* synthetic */ MyNewsActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 983) {
                this.a.d.setVisibility(8);
                if (this.a.w) {
                    this.a.c.b();
                    this.a.w = false;
                }
                String str = (String) message.obj;
                this.a.n = z.a(this.a.b, str);
                if (this.a.n != null) {
                    ListInfo listInfo = this.a.n.info;
                    if (listInfo != null) {
                        this.a.l = listInfo.np;
                        if (this.a.l > 0) {
                            this.a.k = true;
                            this.a.c.setmEnablePullLoad(true);
                        } else {
                            this.a.k = false;
                            this.a.c.setmEnablePullLoad(false);
                        }
                        this.a.o = this.a.n.getList();
                        if (this.a.o == null) {
                            this.a.o = new ArrayList();
                        }
                        if (this.a.o.isEmpty()) {
                            this.a.f = an.a(this.a.b, this.a.b.getString(R.string.no_my_news), -1);
                            this.a.f.show();
                        } else if (this.a.g == null) {
                            this.a.g = new b(this.a);
                            this.a.c.setAdapter(this.a.g);
                        } else {
                            this.a.g.notifyDataSetChanged();
                        }
                    }
                }
            } else if (i == 984) {
                this.a.f = an.a(this.a.b, this.a.b.getString(R.string.time_out), -1);
                this.a.f.show();
                this.a.d.setVisibility(8);
            } else if (i == 986) {
                MyNewsData a = z.a(this.a.b, (String) message.obj);
                Collection list = a.getList();
                this.a.l = a.info.np;
                if (this.a.l > 0) {
                    this.a.k = true;
                    this.a.c.setmEnablePullLoad(true);
                } else {
                    this.a.k = false;
                    this.a.c.setmEnablePullLoad(false);
                }
                if (!(list == null || list.isEmpty())) {
                    this.a.o.addAll(list);
                    this.a.g.notifyDataSetChanged();
                }
                this.a.e.setVisibility(8);
            } else if (i == 987) {
                this.a.e.setVisibility(8);
                this.a.f = an.a(this.a.b, this.a.b.getString(R.string.time_out), -1);
                this.a.f.show();
            }
        }
    };
    String a = "MyNewsActivity";
    MyNewsActivity b;
    CustomListView c;
    View d;
    View e;
    Toast f;
    b g;
    Dialog h;
    String i;
    boolean j = false;
    boolean k = false;
    long l = 0;
    boolean m = false;
    MyNewsData n = new MyNewsData();
    ArrayList<MyNewsItem> o = new ArrayList();
    private int p;
    private RelativeLayout q;
    private h r;
    private d s;
    private boolean t = false;
    private int u;
    private boolean v = false;
    private boolean w = false;
    private long x;
    private net.tsz.afinal.a.a<String> y = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ MyNewsActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.F.sendEmptyMessage(984);
        }

        public void a(String str) {
            super.onSuccess(str);
            new AsyncTask<String, String, ArrayList<ListItemObject>>(this) {
                final /* synthetic */ AnonymousClass5 a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((ArrayList) obj);
                }

                protected void a(ArrayList<ListItemObject> arrayList) {
                }

                protected ArrayList<ListItemObject> a(String... strArr) {
                    this.a.a.F.sendMessage(this.a.a.F.obtainMessage(983, strArr[0]));
                    return null;
                }
            }.execute(new String[]{str});
        }
    };
    private net.tsz.afinal.a.a<String> z = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ MyNewsActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.j = false;
            this.a.F.sendEmptyMessage(987);
        }

        public void a(String str) {
            super.onSuccess(str);
            new AsyncTask<String, String, ArrayList<ListItemObject>>(this) {
                final /* synthetic */ AnonymousClass6 a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((ArrayList) obj);
                }

                protected void a(ArrayList<ListItemObject> arrayList) {
                }

                protected ArrayList<ListItemObject> a(String... strArr) {
                    Object obj = strArr[0];
                    this.a.a.j = false;
                    this.a.a.F.sendMessage(this.a.a.F.obtainMessage(986, obj));
                    return null;
                }
            }.execute(new String[]{str});
        }
    };

    public class a extends ImageSpan {
        final /* synthetic */ MyNewsActivity a;

        public a(MyNewsActivity myNewsActivity, Context context, int i) {
            this.a = myNewsActivity;
            super(context, i);
        }

        public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
            Rect bounds = getDrawable().getBounds();
            if (fontMetricsInt != null) {
                FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
                int i3 = fontMetricsInt2.bottom - fontMetricsInt2.top;
                int i4 = bounds.bottom - bounds.top;
                int i5 = (i4 / 2) - (i3 / 4);
                i3 = (i3 / 4) + (i4 / 2);
                fontMetricsInt.ascent = -i3;
                fontMetricsInt.top = -i3;
                fontMetricsInt.bottom = i5;
                fontMetricsInt.descent = i5;
            }
            return bounds.right;
        }

        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            Drawable drawable = getDrawable();
            canvas.save();
            canvas.translate(f, (float) ((((i5 - i3) - drawable.getBounds().bottom) / 2) + i3));
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    private class b extends BaseAdapter {
        final /* synthetic */ MyNewsActivity a;
        private ClickableSpan b = new MyNewsActivity$b$1(this);
        private ClickableSpan c = new MyNewsActivity$b$2(this);
        private ClickableSpan d = new MyNewsActivity$b$3(this);
        private ClickableSpan e = new MyNewsActivity$b$4(this);

        public b(MyNewsActivity myNewsActivity) {
            this.a = myNewsActivity;
        }

        public int getCount() {
            return this.a.o.size();
        }

        public Object getItem(int i) {
            return this.a.o.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            c cVar;
            MyNewsItem myNewsItem = (MyNewsItem) this.a.o.get(i);
            if (view == null) {
                view = this.a.b.getLayoutInflater().inflate(R.layout.my_news_item_layout, null);
                c cVar2 = new c();
                cVar2.a = (ImageView) view.findViewById(R.id.TypeIconImageView);
                cVar2.b = (TextView) view.findViewById(R.id.TitleTextView);
                cVar2.c = (TextView) view.findViewById(R.id.ContentTextView);
                cVar2.d = (TextView) view.findViewById(R.id.TimeTextView);
                cVar2.e = (TextView) view.findViewById(R.id.reply_comment_btn);
                cVar2.f = (AsyncImageView) view.findViewById(R.id.MyTipImageView);
                cVar2.g = (ImageView) view.findViewById(R.id.VideoPlayImageView);
                cVar2.h = (TextView) view.findViewById(R.id.TextTextView);
                cVar2.i = (LinearLayout) view.findViewById(R.id.comment_like_layout);
                cVar2.j = (ImageView) view.findViewById(R.id.comment_like_iv);
                cVar2.k = (TextView) view.findViewById(R.id.comment_like_count);
                view.setTag(cVar2);
                cVar = cVar2;
            } else {
                cVar = (c) view.getTag();
            }
            cVar.b.setVisibility(8);
            cVar.d.setText(myNewsItem.ctime);
            cVar.e.setVisibility(8);
            cVar.h.setVisibility(8);
            cVar.f.setVisibility(8);
            cVar.f.setImageResource(17170445);
            cVar.g.setVisibility(8);
            cVar.i.setVisibility(8);
            String trim = myNewsItem.getType().trim();
            BaseCommentItem cmtMyTieziItem;
            ListItemObject listItemObject;
            if ("post".equals(trim) || "voice_post".equals(trim) || "video_post".equals(trim)) {
                cVar.a.setImageResource(R.drawable.mymsg_commend);
                cmtMyTieziItem = myNewsItem.getCmtMyTieziItem();
                a(cVar.c, cmtMyTieziItem);
                cVar.c.setTag(myNewsItem.getListItemObject());
                cVar.c.setOnClickListener(this.a.D);
                listItemObject = myNewsItem.getListItemObject();
                if (listItemObject != null) {
                    a(cVar, listItemObject);
                }
                if (!(cmtMyTieziItem.r_cinfo.commentIsDelete() || listItemObject == null || listItemObject.isDelete())) {
                    cVar.e.setVisibility(0);
                    cVar.e.setTag(myNewsItem);
                    cVar.e.setOnClickListener(this.a.C);
                }
            } else if ("replay".equals(trim) || "voice_replay".equals(trim) || "video_replay".equals(trim)) {
                cVar.a.setImageResource(R.drawable.mymsg_reply);
                cmtMyTieziItem = myNewsItem.getReplyNewsItem();
                a(cVar.c, cmtMyTieziItem);
                cVar.c.setTag(myNewsItem.getListItemObject());
                cVar.c.setOnClickListener(this.a.D);
                listItemObject = myNewsItem.getListItemObject();
                if (listItemObject != null) {
                    a(cVar, listItemObject);
                }
                if (!(cmtMyTieziItem.r_cinfo.commentIsDelete() || listItemObject == null || listItemObject.isDelete())) {
                    cVar.e.setVisibility(0);
                    cVar.e.setTag(myNewsItem);
                    cVar.e.setOnClickListener(this.a.C);
                }
            } else if ("cding".equals(trim) || "ccai".equals(trim) || "voice_cding".equals(trim) || "video_cding".equals(trim)) {
                cmtMyTieziItem = myNewsItem.getDingOrCaiCommentNewsItem();
                a(cVar.c, cmtMyTieziItem);
                cVar.c.setTag(myNewsItem.getListItemObject());
                cVar.c.setOnClickListener(this.a.D);
                cVar.i.setVisibility(0);
                cVar.i.setTag(cmtMyTieziItem);
                cVar.i.setOnClickListener(this.a.E);
                if (cmtMyTieziItem.isCai) {
                    cVar.a.setImageResource(R.drawable.mymsg_cai);
                    cVar.j.setImageResource(R.drawable.operation_cai_day_select_image);
                    if (cmtMyTieziItem.r_cinfo.hate_count < 1) {
                        cVar.k.setText("1");
                    } else {
                        cVar.k.setText("" + cmtMyTieziItem.r_cinfo.hate_count);
                    }
                } else {
                    cVar.a.setImageResource(R.drawable.mymsg_ding);
                    cVar.j.setImageResource(R.drawable.operation_ding_day_select_image);
                    if (cmtMyTieziItem.r_cinfo.like_count < 1) {
                        cVar.k.setText("1");
                    } else {
                        cVar.k.setText("" + cmtMyTieziItem.r_cinfo.like_count);
                    }
                }
            } else if ("hot_comment".equals(trim)) {
                cVar.a.setImageResource(R.drawable.mymsg_hotcomment);
                cVar.c.setTag(myNewsItem.getListItemObject());
                cVar.c.setOnClickListener(this.a.D);
                a(cVar.c, myNewsItem.getHotCommentItem());
                r0 = myNewsItem.getListItemObject();
                if (r0 != null) {
                    a(cVar, r0);
                }
            } else if ("ugc".equals(trim) || "voice_ugc".equals(trim) || "video_ugc".equals(trim)) {
                cVar.a.setImageResource(R.drawable.mymsg_notify);
                r2 = myNewsItem.getSystemNewsItem();
                if (r2 != null) {
                    cVar.b.setVisibility(0);
                    cVar.b.setText(r2.getTitle());
                    cVar.c.setText(r2.getBody());
                    cVar.c.setClickable(false);
                    cVar.c.setOnClickListener(null);
                }
                r0 = myNewsItem.getListItemObject();
                if (r0 != null) {
                    a(cVar, r0);
                }
            } else if ("url_topic".equals(trim)) {
                cVar.a.setImageResource(R.drawable.mymsg_substandard);
                r2 = myNewsItem.getSystemNewsItem();
                if (r2 != null) {
                    cVar.b.setVisibility(0);
                    cVar.b.setText(r2.getTitle());
                    cVar.c.setText(r2.getBody());
                }
                r0 = myNewsItem.getListItemObject();
                if (r0 != null) {
                    a(cVar, r0);
                }
            } else if (com.umeng.analytics.pro.d.c.a.equals(trim) || "forum_jing_topic".equals(trim) || "forum_top_topic".equals(trim)) {
                cVar.a.setImageResource(R.drawable.mymsg_notify);
                r2 = myNewsItem.getSystemNewsItem();
                if (r2 != null) {
                    cVar.b.setVisibility(0);
                    cVar.b.setText(r2.getTitle());
                    cVar.c.setText(r2.getBody());
                }
                r0 = myNewsItem.getListItemObject();
                if (r0 != null) {
                    a(cVar, r0);
                }
            } else if ("mentioned".equals(trim)) {
                cVar.a.setImageResource(R.drawable.mymsg_mentioned);
                MentionedItem mentionedItem = myNewsItem.getMentionedItem();
                if (mentionedItem != null) {
                    cVar.c.setText(mentionedItem.getTitle());
                }
                r0 = myNewsItem.getListItemObject();
                if (r0 != null) {
                    a(cVar, r0);
                }
            } else {
                cVar.a.setImageResource(R.drawable.mymsg_notify);
                SystemNewsItem systemNewsItem = myNewsItem.getSystemNewsItem();
                if (systemNewsItem != null) {
                    cVar.b.setVisibility(0);
                    cVar.b.setText(systemNewsItem.getTitle());
                    cVar.c.setText(systemNewsItem.getReserve());
                }
            }
            return view;
        }

        public void a(TextView textView, BaseCommentItem baseCommentItem) {
            Object obj;
            CharSequence charSequence;
            Object obj2;
            String str = "";
            String str2 = "";
            String str3 = "";
            String str4 = "";
            String str5 = "";
            String str6 = "";
            String str7 = "";
            String str8 = "";
            String str9 = "";
            String str10 = "";
            String str11 = "";
            String str12 = "";
            String str13 = "";
            String str14;
            if (baseCommentItem instanceof CmtMyTieziItem) {
                CmtMyTieziItem cmtMyTieziItem = (CmtMyTieziItem) baseCommentItem;
                str = a(cmtMyTieziItem.r_cinfo.user.username);
                str2 = "评论了：";
                if (!"audio".equals(cmtMyTieziItem.r_cinfo.type)) {
                    str3 = cmtMyTieziItem.r_cinfo.content;
                }
                str5 = b(cmtMyTieziItem.r_cinfo.type);
                str10 = cmtMyTieziItem.r_cinfo.user.id;
                str11 = cmtMyTieziItem.r_cinfo.type;
                obj = cmtMyTieziItem.r_cinfo;
                Object obj3 = str12;
                Object obj4 = str10;
                str10 = str8;
                str8 = str13;
                str13 = str11;
                charSequence = str9;
                str9 = str6;
                str6 = str4;
                str4 = str2;
                str2 = str3;
                obj2 = null;
                str14 = str5;
                str5 = str7;
                str7 = str14;
            } else if (baseCommentItem instanceof ReplyNewsItem) {
                ReplyNewsItem replyNewsItem = (ReplyNewsItem) baseCommentItem;
                str = a(replyNewsItem.r_cinfo.user.username);
                str2 = "回复了：";
                if (!"audio".equals(replyNewsItem.r_cinfo.type)) {
                    str3 = replyNewsItem.r_cinfo.content;
                }
                str6 = b(replyNewsItem.r_cinfo.type);
                str7 = "//";
                str9 = a("@" + replyNewsItem.s_cinfo.user.username + ":");
                if (!"audio".equals(replyNewsItem.s_cinfo.type)) {
                    str8 = replyNewsItem.s_cinfo.content;
                }
                str5 = b(replyNewsItem.s_cinfo.type);
                str10 = replyNewsItem.r_cinfo.user.id;
                str11 = replyNewsItem.r_cinfo.type;
                CommentItemForNews commentItemForNews = replyNewsItem.r_cinfo;
                str13 = replyNewsItem.s_cinfo.user.id;
                String str15 = replyNewsItem.s_cinfo.type;
                r5 = str13;
                str13 = str11;
                r9 = str5;
                str5 = str9;
                str9 = str7;
                str7 = str6;
                str6 = str4;
                str4 = str2;
                str2 = str3;
                CommentItemForNews commentItemForNews2 = replyNewsItem.s_cinfo;
                str14 = str10;
                str10 = str8;
                str8 = str15;
                r6 = commentItemForNews;
                str12 = str14;
            } else if (baseCommentItem instanceof HotComment) {
                HotComment hotComment = (HotComment) baseCommentItem;
                str4 = "您的评论：";
                if (!"audio".equals(hotComment.r_cinfo.type)) {
                    str3 = hotComment.r_cinfo.content;
                }
                str5 = b(hotComment.r_cinfo.type);
                str11 = hotComment.r_cinfo.type;
                r6 = hotComment.r_cinfo;
                r5 = str12;
                str12 = str10;
                str10 = str8;
                str8 = str13;
                str13 = str11;
                r9 = str9;
                str9 = str6;
                str6 = "被赞为热评";
                str2 = str3;
                obj2 = null;
                str14 = str7;
                str7 = str5;
                str5 = str14;
            } else if (baseCommentItem instanceof DingOrCaiCommentNewsItem) {
                DingOrCaiCommentNewsItem dingOrCaiCommentNewsItem = (DingOrCaiCommentNewsItem) baseCommentItem;
                str = a(dingOrCaiCommentNewsItem.user.username);
                if (dingOrCaiCommentNewsItem.isCai) {
                    r5 = "踩了你的评论：";
                } else {
                    r5 = "赞了你的评论：";
                }
                if (!"audio".equals(dingOrCaiCommentNewsItem.r_cinfo.type)) {
                    str3 = dingOrCaiCommentNewsItem.r_cinfo.content;
                }
                str2 = b(dingOrCaiCommentNewsItem.r_cinfo.type);
                str5 = dingOrCaiCommentNewsItem.user.id;
                str10 = dingOrCaiCommentNewsItem.r_cinfo.type;
                r6 = dingOrCaiCommentNewsItem.r_cinfo;
                r9 = str9;
                str9 = str6;
                str6 = str4;
                str4 = r5;
                r5 = str12;
                str12 = str5;
                str5 = str7;
                str7 = str2;
                str2 = str3;
                obj2 = null;
                str14 = str8;
                str8 = str13;
                str13 = str10;
                str10 = str14;
            } else {
                obj = null;
                r5 = str12;
                str12 = str10;
                str10 = str8;
                str8 = str13;
                str13 = str11;
                r9 = str9;
                str9 = str6;
                str6 = str4;
                str4 = str2;
                str2 = str3;
                obj2 = null;
                str14 = str5;
                str5 = str7;
                str7 = str14;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str + str4 + str2 + str7 + str6 + str9 + str5 + str10 + charSequence);
            if (!TextUtils.isEmpty(str)) {
                spannableStringBuilder.setSpan(this.b, 0, str.length(), 33);
                textView.setTag(R.id.USER_ID, obj4);
            }
            if (!TextUtils.isEmpty(str7)) {
                SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
                spannableStringBuilder2.setSpan(new a(this.a, this.a.b, c(str13)), (str + str4 + str2).length(), (str + str4 + str2).length() + 1, 33);
                spannableStringBuilder.setSpan(this.d, (str + str4 + str2).length(), (str + str4 + str2).length() + 3, 33);
                textView.setTag(R.id.COMMENT, obj);
            }
            if (!TextUtils.isEmpty(str6)) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(this.a.getResources().getColor(R.color.main_red)), (str + str4 + str2 + str7).length(), (str + str4 + str2 + str7 + str6).length(), 33);
            }
            if (!TextUtils.isEmpty(str5)) {
                spannableStringBuilder.setSpan(this.c, (str + str4 + str2 + str7 + str6 + str9).length(), (str + str4 + str2 + str7 + str6 + str9 + str5).length() - 1, 33);
                textView.setTag(R.id.ORIGINAL_USER_ID, obj3);
            }
            if (!TextUtils.isEmpty(charSequence)) {
                spannableStringBuilder.setSpan(new a(this.a, this.a.b, c(str8)), (str + str4 + str2 + str7 + str6 + str9 + str5 + str10).length(), (str + str4 + str2 + str7 + str6 + str9 + str5 + str10).length() + 1, 33);
                spannableStringBuilder.setSpan(this.e, (str + str4 + str2 + str7 + str6 + str9 + str5 + str10).length(), (str + str4 + str2 + str7 + str6 + str9 + str5 + str10).length() + 3, 33);
                textView.setTag(R.id.ORIGINAL_COMMENT, obj2);
            }
            textView.setText(spannableStringBuilder);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }

        private String a(String str) {
            return str;
        }

        private String b(String str) {
            String str2 = "";
            if (CheckCodeDO.CHECKCODE_IMAGE_URL_KEY.equals(str) || "gif".equals(str)) {
                return "#图片";
            }
            if ("audio".equals(str)) {
                return "#语音";
            }
            if ("video".equals(str)) {
                return "#视频";
            }
            return str2;
        }

        private int c(String str) {
            if (CheckCodeDO.CHECKCODE_IMAGE_URL_KEY.equals(str) || "gif".equals(str)) {
                return R.drawable.my_news_comment_image;
            }
            if ("audio".equals(str)) {
                return R.drawable.my_news_comment_audio;
            }
            if ("video".equals(str)) {
                return R.drawable.my_news_comment_video;
            }
            return 0;
        }

        private void a(CommentItemForNews commentItemForNews) {
            if (commentItemForNews != null) {
                String str = commentItemForNews.type;
                if (CheckCodeDO.CHECKCODE_IMAGE_URL_KEY.equals(str)) {
                    Intent intent = new Intent(this.a.b, CommentShowBigPicture.class);
                    aa.b(this.a.a, "commentInfo.image.images.get(0)=" + ((String) commentItemForNews.image.images.get(0)));
                    intent.putExtra("imgPath", (String) commentItemForNews.image.images.get(0));
                    intent.putExtra("isgif", "0");
                    intent.putExtra(IndexEntry.COLUMN_NAME_WIDTH, commentItemForNews.image.width);
                    intent.putExtra(IndexEntry.COLUMN_NAME_HEIGHT, commentItemForNews.image.height);
                    intent.putExtra("download_uri", commentItemForNews.image.download);
                    this.a.b.startActivity(intent);
                } else if ("video".equals(str)) {
                    CommentItemVideoActivity.a(this.a.b, commentItemForNews.id, (String) commentItemForNews.video.video.get(0));
                } else if ("gif".equals(str)) {
                    r0 = new Intent(this.a.b, CommentShowBigPicture.class);
                    r0.putExtra("isgif", "1");
                    r0.putExtra("GifShowUrls", commentItemForNews.gif.images);
                    r0.putExtra(IndexEntry.COLUMN_NAME_WIDTH, commentItemForNews.gif.width);
                    r0.putExtra(IndexEntry.COLUMN_NAME_HEIGHT, commentItemForNews.gif.height);
                    r0.putExtra("download_uri", commentItemForNews.gif.download);
                    this.a.b.startActivity(r0);
                } else if ("audio".equals(str)) {
                    r0 = new Intent(this.a.b, PlayVoiceActivity.class);
                    r0.putExtra("voice_comment", commentItemForNews);
                    this.a.b.startActivity(r0);
                }
            }
        }

        private void a(c cVar, ListItemObject listItemObject) {
            if ("29".equals(listItemObject.getType())) {
                cVar.h.setVisibility(0);
                cVar.h.setText(listItemObject.getContent());
                cVar.h.setTag(listItemObject);
                cVar.h.setOnClickListener(this.a.D);
            } else if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(listItemObject.getType())) {
                cVar.f.setVisibility(0);
                String str = "";
                if (TextUtils.isEmpty(listItemObject.getIs_gif()) || !"1".equals(listItemObject.getIs_gif())) {
                    str = listItemObject.getsmallImage();
                } else {
                    str = listItemObject.getImgUrl();
                }
                cVar.f.setPostImage(str);
                if (listItemObject.getHeight() > listItemObject.getWidth() * 2) {
                    cVar.f.setScaleType(ScaleType.CENTER_CROP);
                } else {
                    cVar.f.setScaleType(ScaleType.FIT_XY);
                }
                cVar.f.setTag(listItemObject);
                cVar.f.setOnClickListener(this.a.D);
            } else if ("31".equals(listItemObject.getType())) {
                cVar.f.setVisibility(0);
                cVar.f.setPostImage(listItemObject.getsmallImage());
                cVar.f.setScaleType(ScaleType.FIT_XY);
                cVar.g.setVisibility(0);
                cVar.g.setBackgroundResource(R.drawable.my_news_play_audio);
                cVar.g.setTag(listItemObject);
                cVar.g.setOnClickListener(this.a.D);
                cVar.f.setTag(listItemObject);
                cVar.f.setOnClickListener(this.a.D);
            } else if ("41".equals(listItemObject.getType())) {
                cVar.f.setVisibility(0);
                cVar.f.setPostImage(listItemObject.getsmallImage());
                cVar.f.setScaleType(ScaleType.FIT_XY);
                cVar.g.setVisibility(0);
                cVar.g.setBackgroundResource(R.drawable.play_video_normal);
                cVar.g.setTag(listItemObject);
                cVar.g.setOnClickListener(this.a.D);
                cVar.f.setTag(listItemObject);
                cVar.f.setOnClickListener(this.a.D);
            }
        }
    }

    static class c {
        ImageView a;
        TextView b;
        TextView c;
        TextView d;
        TextView e;
        AsyncImageView f;
        ImageView g;
        TextView h;
        LinearLayout i;
        ImageView j;
        TextView k;

        c() {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mynews_layout);
        this.b = this;
        WindowManager windowManager = getWindowManager();
        this.p = windowManager.getDefaultDisplay().getWidth();
        this.u = windowManager.getDefaultDisplay().getHeight();
        this.r = new h(this);
        this.s = new d(this);
        a();
        b();
    }

    protected void onPause() {
        super.onPause();
        if (this.f != null) {
            this.f.cancel();
        }
        k.a((Context) this).o();
    }

    protected void onDestroy() {
        super.onDestroy();
        k.a((Context) this).p();
    }

    protected void onResume() {
        super.onResume();
        if (this.g != null) {
            this.g.notifyDataSetChanged();
        }
    }

    public void onrefreshTheme() {
        this.q.setBackgroundResource(j.a);
        this.c.setBackgroundResource(j.s);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void a() {
        ((LinearLayout) findViewById(R.id.TitleGapLayout)).setVisibility(8);
        this.c = (CustomListView) findViewById(R.id.mynews_listview);
        this.d = findViewById(R.id.loadingDialog);
        this.q = (RelativeLayout) findViewById(R.id.mynews_title_layout);
        this.q.setVisibility(8);
        this.c.setOnItemClickListener(this.A);
        this.c.setonLoadListener(this.B);
        this.c.setOnRefreshListener(new com.budejie.www.activity.view.CustomListView.c(this) {
            final /* synthetic */ MyNewsActivity a;

            {
                this.a = r1;
            }

            public void c() {
                this.a.w = true;
                this.a.b();
            }
        });
        this.c.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ MyNewsActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                k.a(this.a.b).a(absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition(), this.a.c.getHeaderViewsCount());
            }
        });
        this.e = this.c.getFootView();
        if (this.e != null) {
            this.e.setVisibility(8);
        }
    }

    private void b() {
        this.c.setmEnablePullLoad(false);
        this.l = 0;
        if (an.a((Context) this)) {
            this.i = ai.b(this.b);
            this.d.setVisibility(0);
            BudejieApplication.a.a(RequstMethod.GET, com.budejie.www.http.j.a(this.l), new com.budejie.www.http.j(this), this.y);
            return;
        }
        this.f = an.a((Activity) this, getString(R.string.nonet), -1);
        this.f.show();
    }

    private void c() {
        BudejieApplication.a.a(RequstMethod.GET, com.budejie.www.http.j.a(this.l), new com.budejie.www.http.j(this), this.z);
    }

    private boolean a(SystemNewsItem systemNewsItem, String str) {
        int i = 0;
        if (systemNewsItem == null) {
            return false;
        }
        String tagId = systemNewsItem.getTagId();
        if (TextUtils.isEmpty(tagId)) {
            tagId = systemNewsItem.getPostId();
            if (TextUtils.isEmpty(tagId)) {
                return false;
            }
            com.budejie.www.util.a.a((Activity) this, null, tagId, false);
            return true;
        }
        if ("forum_jing_topic".equals(str)) {
            i = PlatePostEnum.ESSENCE_TAB_POSITION.getCode();
        } else if (com.umeng.analytics.pro.d.c.a.equals(str)) {
            i = PlatePostEnum.MEMBER_TAB_POSITION.getCode();
        }
        com.budejie.www.util.a.a((Context) this, tagId, i);
        return true;
    }

    private void a(ListItemObject listItemObject) {
        if (listItemObject != null) {
            an.a(listItemObject, this.r, this.s);
            com.budejie.www.util.a.a(this.b, listItemObject, "", false);
        }
    }

    private void b(final SystemNewsItem systemNewsItem) {
        RelativeLayout relativeLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.system_dialog, null);
        ((Button) relativeLayout.findViewById(R.id.downloadBtn)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyNewsActivity b;

            public void onClick(View view) {
                this.b.a(systemNewsItem);
            }
        });
        this.h = new Dialog(this, R.style.dialogTheme);
        this.h.setContentView(relativeLayout);
        LayoutParams attributes = this.h.getWindow().getAttributes();
        attributes.width = (this.p / 2) + 100;
        attributes.height = an.a((Context) this, 160);
        this.h.getWindow().setAttributes(attributes);
        this.h.show();
    }

    public void noticeLayout$Click(View view) {
        startActivity(new Intent(this, NotificationSettingsActivity.class));
    }

    public void systemCancelBtn$Click(View view) {
        if (this.h != null) {
            this.h.dismiss();
        }
    }

    public void a(SystemNewsItem systemNewsItem) {
        if (this.h != null) {
            this.h.dismiss();
        }
        if (com.elves.update.d.a()) {
            String str = Environment.getExternalStorageDirectory().getPath() + "/elves/apk";
            String str2 = getString(R.string.app_name) + ".apk";
            Intent intent = new Intent(this, DownloadServer.class);
            intent.putExtra("apkPath", str);
            if (systemNewsItem == null || TextUtils.isEmpty(systemNewsItem.getDownload())) {
                intent.putExtra("url", "http://www.budejie.com/budejie/download.php");
            } else {
                intent.putExtra("url", systemNewsItem.getDownload());
            }
            intent.putExtra("apkName", str2);
            startService(intent);
            return;
        }
        this.f = an.a((Activity) this, getString(R.string.sd_message), -1);
        this.f.show();
    }

    private NewCommentItem a(CommentItemForNews commentItemForNews) {
        if (commentItemForNews == null) {
            return null;
        }
        NewCommentItem newCommentItem = new NewCommentItem();
        newCommentItem.id = commentItemForNews.id;
        newCommentItem.data_id = commentItemForNews.data_id;
        newCommentItem.status = commentItemForNews.status;
        newCommentItem.content = commentItemForNews.content;
        newCommentItem.ctime = commentItemForNews.ctime;
        newCommentItem.user = commentItemForNews.user;
        return newCommentItem;
    }
}
