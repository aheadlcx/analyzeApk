package qsbk.app.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.adapter.ArticleAdapter.AcrossChangeDate;
import qsbk.app.adapter.ArticleAdapter.ViewHolder;
import qsbk.app.model.Article;
import qsbk.app.model.RssArticle;
import qsbk.app.model.RssArticle.Type;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UIHelper$Theme;
import qsbk.app.widget.DiggerBar;

public class SubscribeAdapter extends BaseVideoAdapter {
    private static HashMap<String, SubscribeIcon> e = null;

    public static class SubscribeIcon {
        public String day_icon_url;
        public String night_icon_url;
        public String text;
        public String type;

        public String getIconUrl() {
            return UIHelper.isNightTheme() ? this.night_icon_url : this.day_icon_url;
        }

        public String getText() {
            return TextUtils.isEmpty(this.text) ? "" : this.text;
        }

        public String toString() {
            return "SubscribeIcon{type='" + this.type + '\'' + ", text='" + this.text + '\'' + ", day_icon_url='" + this.day_icon_url + '\'' + ", night_icon_url='" + this.night_icon_url + '\'' + '}';
        }
    }

    class a extends ViewHolder {
        DiggerBar b;
        TextView c;
        final /* synthetic */ SubscribeAdapter d;

        public a(SubscribeAdapter subscribeAdapter, View view) {
            this.d = subscribeAdapter;
            super(subscribeAdapter, view);
            this.b = (DiggerBar) view.findViewById(R.id.diggerbar);
            this.c = (TextView) view.findViewById(R.id.type);
        }
    }

    public SubscribeAdapter(Activity activity, ListView listView, ArrayList<Object> arrayList, String str, String str2) {
        this(activity, listView, arrayList, str, str2, null);
    }

    public SubscribeAdapter(Activity activity, ListView listView, ArrayList<Object> arrayList, String str, String str2, AcrossChangeDate acrossChangeDate) {
        super(activity, listView, arrayList, str, str2, acrossChangeDate);
    }

    public static SubscribeIcon getSubscribIcons(String str) {
        if (e == null) {
            e = new HashMap();
            try {
                JSONObject optJSONObject = QsbkApp.indexConfig.optJSONObject("subscribe_icon");
                LogUtil.d("subscribe_icon");
                String optString = optJSONObject.optString("prefix");
                JSONArray optJSONArray = optJSONObject.optJSONArray("conf");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                        SubscribeIcon subscribeIcon = new SubscribeIcon();
                        subscribeIcon.type = optJSONObject2.optString("type");
                        subscribeIcon.text = optJSONObject2.optString("text");
                        subscribeIcon.day_icon_url = optString + optJSONObject2.optString("day");
                        subscribeIcon.night_icon_url = optString + optJSONObject2.optString(UIHelper$Theme.THEME_NIGHT);
                        LogUtil.d("put subicon:" + subscribeIcon.toString());
                        e.put(subscribeIcon.type, subscribeIcon);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (SubscribeIcon) e.get(str);
    }

    public static void initType(RssArticle rssArticle, TextView textView) {
        String str = rssArticle.type;
        if (Type.SUB.equals(str) && rssArticle.containsAction("publish")) {
            str = "publish";
        }
        textView.setVisibility(0);
        int paddingLeft = textView.getPaddingLeft();
        int paddingRight = textView.getPaddingRight();
        int paddingTop = textView.getPaddingTop();
        int paddingBottom = textView.getPaddingBottom();
        String str2 = "";
        SubscribeIcon subscribIcons = getSubscribIcons(str);
        if (subscribIcons != null) {
            str2 = subscribIcons.getText();
            if (subscribIcons.type.equals(Type.NEARBY)) {
                textView.setText("  " + String.format("%s·%s", new Object[]{rssArticle.city, rssArticle.district}));
            } else {
                textView.setText("  " + str2);
            }
            String iconUrl = subscribIcons.getIconUrl();
            textView.setTag(iconUrl);
            if (TextUtils.isEmpty(iconUrl)) {
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            } else {
                Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(iconUrl), textView.getContext().getApplicationContext()).subscribe(new dp(textView, iconUrl, paddingLeft, paddingTop, paddingRight, paddingBottom), UiThreadImmediateExecutorService.getInstance());
            }
        } else {
            textView.setVisibility(8);
        }
        if (rssArticle.qiushiTopic != null && rssArticle.qiushiTopic.hasEvent()) {
            textView.setVisibility(8);
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return super.getView(i, view, viewGroup);
    }

    protected ViewHolder a(View view) {
        return new a(this, view);
    }

    protected void a(Article article, ViewHolder viewHolder, int i, View view) {
        super.a(article, viewHolder, i, view);
        if ((article instanceof RssArticle) && (viewHolder instanceof a)) {
            a((RssArticle) article, (a) viewHolder);
            b((RssArticle) article, (a) viewHolder);
        }
    }

    protected int a() {
        return R.layout.layout_article_item;
    }

    private void a(RssArticle rssArticle, a aVar) {
        initType(rssArticle, aVar.c);
    }

    private void b(RssArticle rssArticle, a aVar) {
        aVar.b.belongTo(rssArticle.id);
        aVar.b.setDiggers(rssArticle, true);
    }
}
