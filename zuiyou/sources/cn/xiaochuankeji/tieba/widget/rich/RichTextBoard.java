package cn.xiaochuankeji.tieba.widget.rich;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.net.Uri;
import android.support.annotation.MainThread;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.tale.TaleAuthor;
import cn.xiaochuankeji.tieba.background.tale.TaleDetail;
import cn.xiaochuankeji.tieba.background.utils.i;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.bigImage.BigImageView;
import cn.xiaochuankeji.tieba.ui.widget.bigImage.h;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.widget.rich.RichTextEditor.a;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.izuiyou.a.a.b;
import java.util.ArrayList;
import java.util.List;

public class RichTextBoard extends LinearLayout {
    private TaleDetail a;
    private OnClickListener b;
    private List<a> c;
    private List<a> d;
    private int e;

    public RichTextBoard(Context context) {
        this(context, null);
    }

    public RichTextBoard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RichTextBoard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new OnClickListener(this) {
            final /* synthetic */ RichTextBoard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Object tag = view.getTag();
                b.b();
                if (tag instanceof a) {
                    int indexOf = this.a.d.indexOf(tag);
                    b.b();
                    if (indexOf >= 0) {
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < this.a.d.size(); i++) {
                            a aVar = (a) this.a.d.get(i);
                            arrayList.add(a("gif".equalsIgnoreCase(aVar.f), aVar.c));
                        }
                        MediaBrowseActivity.a(this.a.getContext(), indexOf, null, arrayList, false, EntranceType.PostDetail);
                    }
                }
            }

            private cn.htjyb.b.a a(boolean z, long j) {
                Type type;
                if (z) {
                    type = Type.kGif;
                } else {
                    type = Type.kCommentOriginImg;
                }
                return cn.xiaochuankeji.tieba.background.a.f().a(type, j);
            }
        };
        this.c = new ArrayList();
        this.d = new ArrayList();
        setOrientation(1);
        this.e = (int) TypedValue.applyDimension(1, 13.0f, getResources().getDisplayMetrics());
    }

    private void a(final TaleAuthor taleAuthor) {
        View inflate = View.inflate(getContext(), R.layout.board_author, null);
        ((AppCompatTextView) inflate.findViewById(R.id.name)).setText(String.valueOf(taleAuthor.name));
        WebImageView webImageView = (WebImageView) inflate.findViewById(R.id.avatar);
        webImageView.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(taleAuthor.id, taleAuthor.avatar));
        webImageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RichTextBoard b;

            public void onClick(View view) {
                MemberDetailActivity.a(this.b.getContext(), taleAuthor.id);
            }
        });
        addView(inflate);
    }

    private void a(a aVar) {
        View inflate = View.inflate(getContext(), R.layout.board_text, null);
        ((AppCompatTextView) inflate.findViewById(R.id.text)).setText(String.valueOf(aVar.b));
        addView(inflate, new LayoutParams(-1, -2));
    }

    private void b(a aVar) {
        View inflate = View.inflate(getContext(), R.layout.board_image, null);
        BigImageView bigImageView = (BigImageView) inflate.findViewById(R.id.image);
        final TextView textView = (TextView) inflate.findViewById(R.id.tv_loading);
        Resources resources = getResources();
        RectF a = cn.xiaochuankeji.tieba.background.f.b.a(resources, (float) aVar.d, (float) aVar.e);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        int a2 = (int) i.a(resources, a.width());
        int a3 = (int) i.a(resources, a.height());
        int i = a2 - (this.e * 2);
        a3 = (int) ((((float) a3) * ((float) i)) / ((float) a2));
        b.c("tale.w:" + aVar.d + "  tale.h:" + aVar.e + "  w:" + i + "  h:" + a3);
        layoutParams.width = i;
        layoutParams.height = a3;
        layoutParams.gravity = 1;
        textView.setLayoutParams(layoutParams);
        textView.setVisibility(8);
        View imageView = bigImageView.getImageView();
        if (imageView != null) {
            imageView.setTag(aVar);
            imageView.setOnClickListener(this.b);
        }
        inflate.setTag(aVar);
        inflate.setOnClickListener(this.b);
        bigImageView.setInitScaleType(1);
        bigImageView.setRecycleWhenDetached(false);
        Uri parse = Uri.parse(cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", aVar.c, "/sz/origin"));
        bigImageView.setProgressIndicator(new h(this) {
            final /* synthetic */ RichTextBoard b;

            public View a(BigImageView bigImageView) {
                return null;
            }

            public void a() {
            }

            public void a(int i) {
            }

            public void b() {
                textView.setVisibility(8);
            }
        });
        bigImageView.a(parse);
        addView(inflate);
    }

    private void c(a aVar) {
        addView(View.inflate(getContext(), R.layout.board_unsupport, null));
    }

    @MainThread
    public void setTale(TaleDetail taleDetail) {
        if (taleDetail != null) {
            this.a = taleDetail;
            removeAllViews();
            a(taleDetail.author);
            JSONArray parseArray = JSONArray.parseArray(taleDetail.content);
            for (int i = 0; i < parseArray.size(); i++) {
                JSONObject jSONObject = parseArray.getJSONObject(i);
                String string = jSONObject.getString("type");
                a aVar = new a();
                if ("txt".equals(string)) {
                    aVar.a = "txt";
                    aVar.b = jSONObject.getString("txt");
                    a(aVar);
                } else if ("img".equals(string)) {
                    aVar.a = "img";
                    aVar.c = jSONObject.getLongValue("id");
                    aVar.d = jSONObject.getIntValue("w");
                    aVar.e = jSONObject.getIntValue("h");
                    aVar.f = jSONObject.getString("fmt");
                    b(aVar);
                    this.d.add(aVar);
                } else {
                    aVar.a = "unsup";
                    c(aVar);
                }
                this.c.add(aVar);
            }
        }
    }

    public TaleDetail getDetail() {
        return this.a;
    }
}
