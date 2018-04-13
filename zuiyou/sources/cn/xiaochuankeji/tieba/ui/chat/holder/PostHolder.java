package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.push.data.a;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.post.PostAllegeActivity;
import cn.xiaochuankeji.tieba.ui.post.SubjectActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.cloud.SpeechConstant;
import com.tencent.wcdb.database.SQLiteDatabase;
import org.json.JSONException;

public class PostHolder extends a {
    @BindView
    WebImageView avatar;
    @BindView
    View click_area;
    @BindView
    TextView content;
    @BindView
    WebImageView thumb;
    @BindView
    TextView title;

    public PostHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(a aVar, int i) {
        a(aVar, i, this.avatar);
        a(this.avatar, new b(this, this.a.session_type, aVar.a, aVar.c, aVar.e));
        Object a = a(aVar.f);
        if (a instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) a;
            CharSequence string = jSONObject.getString("msg");
            if (TextUtils.isEmpty(string)) {
                this.content.setVisibility(8);
            } else {
                this.content.setVisibility(0);
                this.content.setText(string);
            }
            jSONObject = jSONObject.getJSONObject("data");
            if (aVar.g == 9) {
                a(aVar, jSONObject.getJSONObject(SpeechConstant.SUBJECT));
                return;
            }
            final long longValue = jSONObject.getLongValue("pid");
            final long longValue2 = jSONObject.getLongValue("tid");
            jSONObject = jSONObject.getJSONObject("post");
            if (jSONObject != null) {
                string = jSONObject.getString("content");
                if (TextUtils.isEmpty(string)) {
                    this.title.setVisibility(8);
                } else {
                    this.title.setVisibility(0);
                    this.title.setText(string);
                }
                JSONArray jSONArray = jSONObject.getJSONArray("imgs");
                if (jSONArray == null || jSONArray.isEmpty()) {
                    ((com.facebook.drawee.generic.a) this.thumb.getHierarchy()).a((int) R.drawable.chat_image_holder);
                } else {
                    try {
                        this.thumb.setWebImage(b.a(new ServerImage(new org.json.JSONObject(jSONArray.getJSONObject(0).toJSONString())).postImageId, true));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                final a aVar2 = aVar;
                a(this.click_area, new rx.b.b<Void>(this) {
                    final /* synthetic */ PostHolder d;

                    public /* synthetic */ void call(Object obj) {
                        a((Void) obj);
                    }

                    public void a(Void voidR) {
                        if (this.d.click_area != null && this.d.click_area.getContext() != null) {
                            if (aVar2.g == 6) {
                                PostDetailActivity.a(this.d.click_area.getContext(), new Post(longValue), false, 0, null, EntranceType.Chat);
                            } else if (aVar2.g == 8) {
                                PostAllegeActivity.a(this.d.click_area.getContext(), longValue, longValue2);
                            }
                        }
                    }
                });
            }
        }
    }

    private void a(a aVar, JSONObject jSONObject) {
        if (jSONObject != null) {
            long longValue = jSONObject.getLongValue("id");
            long longValue2 = jSONObject.getLongValue("cover_id");
            CharSequence string = jSONObject.getString("title");
            final cn.xiaochuan.jsbridge.b a = cn.xiaochuan.jsbridge.b.a(string, jSONObject.getString("url"));
            a.a = longValue;
            a.d = longValue2;
            this.thumb.setWebImage(b.a(longValue2));
            this.title.setText(string);
            a(this.click_area, new rx.b.b<Void>(this) {
                final /* synthetic */ PostHolder b;

                public /* synthetic */ void call(Object obj) {
                    a((Void) obj);
                }

                public void a(Void voidR) {
                    if (this.b.itemView != null && this.b.itemView.getContext() != null) {
                        Intent intent = new Intent(this.b.itemView.getContext(), SubjectActivity.class);
                        if (!(this.b.itemView.getContext() instanceof Activity)) {
                            intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                        }
                        intent.putExtra("web_data", a);
                        this.b.itemView.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}
