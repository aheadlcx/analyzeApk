package qsbk.app.fragments;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ManageQiuShiNewActivity;
import qsbk.app.activity.publish.CirclePublishActivity;
import qsbk.app.activity.publish.PublishActivity;
import qsbk.app.activity.publish.QiushiPublishTask;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.adapter.ManageMyContentsAdapter;
import qsbk.app.adapter.ManageMyContentsAdapter.IViewClickListener;
import qsbk.app.cache.FileCache;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.model.PostedArticle;
import qsbk.app.share.ShareUtils;
import qsbk.app.slide.SlideActivity;
import qsbk.app.utils.CollectionUtils;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.QiushiArticleBus;
import qsbk.app.utils.QiushiArticleBus.OnArticleActionListener;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class ManangeMyContentsFragment extends PureArticleListFragment implements IViewClickListener, OnArticleActionListener {
    private final a Q;
    private boolean R;
    private boolean S;
    private boolean T;
    private boolean U;
    private c V;
    private b W;
    private LocalBroadcastManager X;
    private Article Y;

    private static class a extends Handler {
        private final WeakReference<ManangeMyContentsFragment> a;

        public a(ManangeMyContentsFragment manangeMyContentsFragment) {
            this.a = new WeakReference(manangeMyContentsFragment);
        }

        public void handleMessage(Message message) {
            ManangeMyContentsFragment manangeMyContentsFragment = (ManangeMyContentsFragment) this.a.get();
            if (manangeMyContentsFragment != null) {
                String str = "";
                switch (message.what) {
                    case 0:
                        manangeMyContentsFragment.j.remove(manangeMyContentsFragment.Y);
                        manangeMyContentsFragment.i.notifyDataSetChanged();
                        FileCache.cacheTextToDiskImmediately(manangeMyContentsFragment.B, manangeMyContentsFragment.v, CollectionUtils.artilesToJsonString(manangeMyContentsFragment.j));
                        if (!(manangeMyContentsFragment.S || manangeMyContentsFragment.R || manangeMyContentsFragment.T)) {
                            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "删除成功！", Integer.valueOf(0)).show();
                        }
                        if (manangeMyContentsFragment.T) {
                            manangeMyContentsFragment.T = false;
                        }
                        if (manangeMyContentsFragment.R) {
                            manangeMyContentsFragment.startPublish();
                            manangeMyContentsFragment.R = false;
                            return;
                        }
                        return;
                    case 999:
                        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "删除失败，请稍后重试！", Integer.valueOf(0)).show();
                        return;
                    case 9999:
                        manangeMyContentsFragment.removeFromLocal(manangeMyContentsFragment.Y);
                        manangeMyContentsFragment.l.refresh();
                        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "投稿成功，人品+1，请耐心等待审核", Integer.valueOf(0)).show();
                        return;
                    case 10000:
                        LogUtil.e(manangeMyContentsFragment.Y.selectedPath);
                        manangeMyContentsFragment.j.remove(manangeMyContentsFragment.Y);
                        manangeMyContentsFragment.i.notifyDataSetChanged();
                        manangeMyContentsFragment.removeFromLocal(manangeMyContentsFragment.Y);
                        return;
                    case 10001:
                        int indexOf = manangeMyContentsFragment.j.indexOf(manangeMyContentsFragment.Y);
                        if (indexOf >= 0) {
                            ((Article) manangeMyContentsFragment.j.get(indexOf)).stateExtra = 1;
                            manangeMyContentsFragment.i.notifyDataSetChanged();
                            return;
                        }
                        return;
                    default:
                        if (message.obj == null || !(message.obj instanceof String)) {
                            str = "删除失败，请稍后重试！";
                        } else {
                            str = (String) message.obj;
                        }
                        ToastAndDialog.makeNegativeToast(manangeMyContentsFragment.getActivity(), str, Integer.valueOf(1)).show();
                        return;
                }
            }
        }
    }

    private class b extends BroadcastReceiver {
        final /* synthetic */ ManangeMyContentsFragment a;

        private b(ManangeMyContentsFragment manangeMyContentsFragment) {
            this.a = manangeMyContentsFragment;
        }

        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("err_msg");
            if (TextUtils.isEmpty(stringExtra)) {
                stringExtra = "网络错误，投稿失败，请重试";
            }
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, stringExtra, Integer.valueOf(0)).show();
            Article article = (Article) intent.getSerializableExtra("sendData");
            if (this.a.j.size() > 0) {
                int indexOf = this.a.j.indexOf(article);
                if (indexOf >= 0) {
                    ((Article) this.a.j.get(indexOf)).stateExtra = 1;
                    this.a.i.notifyDataSetChanged();
                }
            }
        }
    }

    private class c extends BroadcastReceiver {
        final /* synthetic */ ManangeMyContentsFragment a;

        private c(ManangeMyContentsFragment manangeMyContentsFragment) {
            this.a = manangeMyContentsFragment;
        }

        public void onReceive(Context context, Intent intent) {
            this.a.removeFromLocal((Article) intent.getSerializableExtra("sendData"));
            this.a.l.refresh();
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "投稿成功，人品+1，请耐心等待审核", Integer.valueOf(0)).show();
        }
    }

    public ManangeMyContentsFragment() {
        this.Q = new a(this);
        this.R = false;
        this.S = false;
        this.T = false;
        this.U = false;
        this.u = Constants.MYCONTENTS;
        this.v = "myContent";
        this.w = false;
        b = 0;
    }

    protected boolean u() {
        return false;
    }

    protected boolean r() {
        return false;
    }

    protected boolean o() {
        return false;
    }

    protected boolean s() {
        return false;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.V = new c();
        this.W = new b();
        this.X = LocalBroadcastManager.getInstance(activity);
        this.X.registerReceiver(this.V, new IntentFilter("_KEY_PUBLISH_ARTICLE_SUCC_"));
        this.X.registerReceiver(this.W, new IntentFilter("_KEY_PUBLISH_ARTICLE_FAILED_"));
        QiushiArticleBus.registerOnArticleActionListener(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_ptr_listview, null);
        a(inflate);
        if (UIHelper.isNightTheme()) {
            this.m.setDivider(new ColorDrawable(-16777216));
            this.m.setDividerHeight((int) (getResources().getDisplayMetrics().density / 2.0f));
        } else {
            this.m.setDivider(null);
            this.m.setDividerHeight(0);
        }
        this.F = new fg(this, this.m);
        this.F.setEnable(false);
        if (!isSelected()) {
            this.F.stopAll();
        } else if (QsbkApp.getInstance().isAutoPlayConfiged()) {
            this.F.autoPlay();
        }
        return inflate;
    }

    public void onDetach() {
        super.onDetach();
        this.X.unregisterReceiver(this.V);
        this.X.unregisterReceiver(this.W);
        QiushiArticleBus.unregisterOnArticleActionListener(this);
    }

    public void onArticleDeleted(OnArticleActionListener onArticleActionListener, Article article) {
        this.T = true;
        delete(article);
        if (this.Q != null) {
            this.Q.obtainMessage(0).sendToTarget();
        }
    }

    public void removeFromLocal(Article article) {
        if (article != null) {
            if (!TextUtils.isEmpty(article.editUuid)) {
                File file = new File(DeviceUtils.getSDPath() + File.separator + "qsbk/send/" + article.editUuid + ".jpg");
                if (file.exists() && !file.isDirectory()) {
                    file.delete();
                }
            }
            Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(PublishActivity.KEY_PUBLISH_ARTICLE);
            if (!TextUtils.isEmpty(sharePreferencesValue)) {
                JSONArray jSONArray = new JSONArray();
                try {
                    JSONArray jSONArray2 = new JSONArray(sharePreferencesValue);
                    for (int i = 0; i < jSONArray2.length(); i++) {
                        Article parseJsonFromLocal = Article.parseJsonFromLocal(jSONArray2.getJSONObject(i));
                        if (TextUtils.isEmpty(parseJsonFromLocal.uuid) || TextUtils.isEmpty(article.uuid) || !parseJsonFromLocal.uuid.equals(article.uuid)) {
                            jSONArray.put(jSONArray2.get(i));
                        } else {
                            LogUtil.e("continue remove");
                        }
                    }
                    SharePreferenceUtils.setSharePreferencesValue(PublishActivity.KEY_PUBLISH_ARTICLE, jSONArray.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected BaseImageAdapter b() {
        return new ManageMyContentsAdapter(this.B, this.m, this.j, this);
    }

    protected boolean y() {
        return true;
    }

    public void onStop() {
        super.onStop();
        if (this.i != null) {
            this.i.onStop();
        }
    }

    protected void k() {
        this.m.setOnItemClickListener(new fp(this));
    }

    protected void l() {
        this.m.setOnItemLongClickListener(new fq(this));
    }

    private void c() {
        boolean z = false;
        if (this.O != null && this.O.getTag().equals("active")) {
            z = true;
        }
        if (getActivity() instanceof ManageQiuShiNewActivity) {
            ShareUtils.openShareDialog(getParentFragment() == null ? this : getParentFragment(), this.B, 1, z, this.M, true);
        } else {
            ShareUtils.openShareDialog(getParentFragment() == null ? this : getParentFragment(), this.B, 1, z, this.M, this.O);
        }
    }

    public void delete(Article article) {
        if (article != null) {
            this.Y = article;
        }
    }

    private void F() {
        Message obtainMessage = this.Q.obtainMessage();
        obtainMessage.what = 10000;
        obtainMessage.sendToTarget();
    }

    private void G() {
        new fr(this, "qbk-MyCtnAct").start();
    }

    public void startPublish() {
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.showLoginGuideDialog(getActivity(), R.string.login_guide_dialog_content_publish);
            return;
        }
        Intent intent = new Intent(getActivity(), PublishActivity.class);
        intent.putExtra("flag", "article");
        intent.putExtra("article", this.Y);
        getActivity().startActivityForResult(intent, 100);
        getActivity().finish();
    }

    public void startToQiuyouCircle() {
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.showLoginGuideDialog(getActivity(), R.string.login_guide_dialog_content_publish);
            return;
        }
        Intent intent = new Intent(getActivity(), CirclePublishActivity.class);
        intent.putExtra("article", this.Y);
        intent.putExtra("fromManageQiushi", true);
        startActivity(intent);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ShareUtils shareUtils = new ShareUtils();
        if (i2 >= 1) {
            if (this.M != null && i == 1) {
                if (i2 == 100) {
                    this.R = true;
                    new Builder(getActivity()).setTitle("提示").setMessage("原帖将被删除，重新进入审核，是否继续？").setPositiveButton("确定", new ft(this)).setNegativeButton("取消", new fs(this)).show();
                } else if (i2 == 101) {
                    startToQiuyouCircle();
                } else if (i2 == 102) {
                    new Builder(getActivity()).setTitle("提示").setMessage("确认删除这条糗事？").setPositiveButton("确定", new fv(this)).setNegativeButton("取消", new fu(this)).show();
                } else if (i2 == 103) {
                    this.R = true;
                    new Builder(getActivity()).setTitle("提示").setMessage("重新投稿将导致原糗事删除，好笑数减少，且通过几率未知，是否继续？").setPositiveButton("确定", new fh(this)).setNegativeButton("取消", new fw(this)).show();
                } else if (i2 == 11) {
                    new Builder(getActivity()).setTitle("提示").setMessage("确认删除这条糗事？").setPositiveButton("确定", new fj(this)).setNegativeButton("取消", new fi(this)).show();
                } else if (i2 == 10000) {
                    new Builder(getActivity()).setTitle("提示").setMessage("确认删除这条未发表的糗事？").setPositiveButton("确定", new fl(this)).setNegativeButton("取消", new fk(this)).show();
                } else if (i2 == 9999) {
                    new Builder(getActivity()).setTitle("提示").setMessage("确认重新投稿这条糗事？").setPositiveButton("确定", new fn(this)).setNegativeButton("取消", new fm(this)).show();
                }
            }
            if (i2 == SlideActivity.SLIDE_REQUEST) {
                this.j = QsbkApp.currentDataSource;
                b().notifyDataSetChanged();
                int intExtra = intent.getIntExtra("position", 0) + this.m.getHeaderViewsCount();
                this.o = intent.getIntExtra(ParamKey.PAGE, this.o);
                this.m.post(new fo(this, intExtra));
            }
        }
    }

    public void reportNewPublishArticle() {
        Article article = this.M;
        if (PublishActivity.isPublishingArticle.containsKey(article.uuid)) {
            ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "该糗事正在投稿中，请稍候...", Integer.valueOf(0)).show();
            return;
        }
        article.state = ManageMyContentsAdapter.NEW_PUBLISH;
        article.stateExtra = 0;
        new QiushiPublishTask(article).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        PublishActivity.isPublishingArticle.put(article.uuid, Boolean.valueOf(true));
        changeFromLocal(article);
    }

    public void changeFromLocal(Article article) {
        if (article != null) {
            Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(PublishActivity.KEY_PUBLISH_ARTICLE);
            if (!TextUtils.isEmpty(sharePreferencesValue)) {
                JSONArray jSONArray = new JSONArray();
                try {
                    JSONArray jSONArray2 = new JSONArray(sharePreferencesValue);
                    for (int i = 0; i < jSONArray2.length(); i++) {
                        Article parseJsonFromLocal = Article.parseJsonFromLocal(jSONArray2.getJSONObject(i));
                        if (TextUtils.isEmpty(parseJsonFromLocal.uuid) || TextUtils.isEmpty(article.uuid) || !parseJsonFromLocal.uuid.equals(article.uuid)) {
                            jSONArray.put(jSONArray2.get(i));
                        } else {
                            parseJsonFromLocal.stateExtra = 1;
                            jSONArray.put(Article.toJSONObjectToSaveLocal(parseJsonFromLocal));
                        }
                    }
                    SharePreferenceUtils.setSharePreferencesValue(PublishActivity.KEY_PUBLISH_ARTICLE, jSONArray.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public HashMap<String, Object> getPublishPostParams() {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("content", this.M.content);
        hashMap.put("anonymous", Boolean.valueOf(this.M.anonymous));
        hashMap.put("image_width", this.M.image_width + "");
        hashMap.put("image_height", this.M.image_height + "");
        hashMap.put("image_type", this.M.image_format);
        if (this.M.publish_location != null) {
            hashMap.put(ParamKey.LONGITUDE, Double.valueOf(this.M.publish_location.longitude));
            hashMap.put(ParamKey.LATITUDE, Double.valueOf(this.M.publish_location.latitude));
            hashMap.put("city", this.M.publish_location.city);
            hashMap.put("district", this.M.publish_location.district);
        }
        hashMap.put("display", Integer.valueOf(this.M.display));
        hashMap.put("allow_comment", Boolean.valueOf(this.M.allow_comment));
        return hashMap;
    }

    public void report(Article article, int i) {
        if (article != null) {
            if (QsbkApp.currentUser == null) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能举报哦！", Integer.valueOf(1)).show();
                LoginPermissionClickDelegate.startLoginActivity(getActivity());
                return;
            }
            ReportArticle.setReportArticle(article, i);
            this.j.remove(article);
            this.i.notifyDataSetChanged();
            FileCache.cacheTextToDiskImmediately(this.B, this.v, CollectionUtils.artilesToJsonString(this.j));
            ReportArticle.reportHandler(true);
        }
    }

    public void onPause() {
        super.onPause();
    }

    protected boolean g() {
        return false;
    }

    protected boolean a(String str) {
        try {
            int i;
            JSONObject jSONObject = new JSONObject(str);
            JSONArray jSONArray = jSONObject.getJSONArray("items");
            this.p = jSONObject.getInt("total");
            int length = jSONArray.length();
            if (length == 0 || this.p <= length) {
                this.q = true;
            }
            if (this.q) {
                this.l.setLoadMoreEnable(false);
                this.footView.setVisibility(8);
            } else {
                this.l.setLoadMoreEnable(true);
                this.footView.setVisibility(0);
            }
            if (this.e.equals("top_refresh") || (this.o == 1 && !x())) {
                this.j.clear();
            }
            if (this.o == 1) {
                Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(PublishActivity.KEY_PUBLISH_ARTICLE);
                if (!TextUtils.isEmpty(sharePreferencesValue)) {
                    JSONArray jSONArray2 = new JSONArray(sharePreferencesValue);
                    for (i = 0; i < jSONArray2.length(); i++) {
                        this.j.add(Article.parseJsonFromLocal(jSONArray2.getJSONObject(i)));
                    }
                }
            }
            for (i = 0; i < length; i++) {
                try {
                    if (jSONArray.optJSONObject(i) != null) {
                        Article postedArticle = new PostedArticle(jSONArray.optJSONObject(i));
                        if (!(this.j.contains(postedArticle) || ReportArticle.mReportArtcicle.keySet().contains(postedArticle.id))) {
                            this.j.add(postedArticle);
                        }
                    }
                } catch (QiushibaikeException e) {
                }
            }
            if (this.o == 1 && this.w) {
                sort(this.j);
            }
            this.o++;
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
