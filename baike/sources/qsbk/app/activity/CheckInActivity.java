package qsbk.app.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.facebook.common.util.UriUtil;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONArray;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.pay.ItemSignCardBuyActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.core.AsyncTask;
import qsbk.app.fragments.QiuyouCircleFragment;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.http.HttpTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.TimeUtils;
import qsbk.app.model.Fortune;
import qsbk.app.model.PunchInfo;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UIHelper$Theme;
import qsbk.app.widget.BaseCell;
import qsbk.app.widget.BlackProgressDialog;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PunchCardCell;
import qsbk.app.widget.ShowcaseView;
import qsbk.app.widget.qiuyoucircle.UnknownCell;

public class CheckInActivity extends BaseActionBarActivity {
    static HashMap<String, Integer> a = new HashMap();
    ListView A;
    View B;
    Button C;
    TextView D;
    TextView E;
    BaseAdapter F;
    h G;
    d H;
    ShowcaseView I;
    BlackProgressDialog J;
    BroadcastReceiver K;
    private int O;
    private int P;
    private SplashAdItem Q;
    private SplashAdItem R;
    private int S = 0;
    private PunchInfo T = new PunchInfo();
    SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat c = new SimpleDateFormat("yyyy-MM");
    Calendar d = Calendar.getInstance();
    ArrayList<Object> e = new ArrayList();
    HashSet<String> f = new HashSet();
    HashSet<String> g = new HashSet();
    HashSet<String> h = new HashSet();
    String i;
    String j;
    String[] k;
    String[] l;
    List<Fortune> m = new ArrayList();
    List<Fortune> n = new ArrayList();
    boolean o;
    boolean p;
    String q;
    String r;
    HttpTask s;
    HttpTask t;
    HttpTask u;
    HttpTask v;
    SimpleHttpTask w;
    HttpTask x;
    HttpTask y;
    PtrLayout z;

    public class TodayDecorator implements DayViewDecorator {
        final /* synthetic */ CheckInActivity a;
        private int b;

        public TodayDecorator(CheckInActivity checkInActivity, int i) {
            this.a = checkInActivity;
            this.b = i;
        }

        public boolean shouldDecorate(CalendarDay calendarDay) {
            if (calendarDay.getCalendar().get(1) == Calendar.getInstance().get(1) && calendarDay.getCalendar().get(6) == Calendar.getInstance().get(6)) {
                return true;
            }
            return false;
        }

        public void decorate(DayViewFacade dayViewFacade) {
            dayViewFacade.addSpan(new DotSpan(5.0f, this.b, UIHelper.dip2px(this.a, 7.0f)));
        }
    }

    class a extends BaseCell {
        ImageView a;
        final /* synthetic */ CheckInActivity b;

        a(CheckInActivity checkInActivity) {
            this.b = checkInActivity;
        }

        public void onCreate() {
            setCellView((int) R.layout.cell_item_sign_ad);
            this.a = (ImageView) findViewById(R.id.img);
        }

        public void onUpdate() {
            SplashAdItem splashAdItem = (SplashAdItem) getItem();
            if (splashAdItem == null || splashAdItem.id == 0) {
                this.a.setVisibility(8);
                return;
            }
            this.a.setVisibility(0);
            displayImage(this.a, splashAdItem.picUrl);
            this.a.setOnClickListener(new eo(this, splashAdItem));
        }
    }

    class b implements DayViewDecorator {
        final /* synthetic */ CheckInActivity a;

        b(CheckInActivity checkInActivity) {
            this.a = checkInActivity;
        }

        public boolean shouldDecorate(CalendarDay calendarDay) {
            return !TimeUtils.isSameDay(calendarDay.getCalendar(), Calendar.getInstance()) && calendarDay.getCalendar().after(Calendar.getInstance());
        }

        public void decorate(DayViewFacade dayViewFacade) {
            dayViewFacade.setDaysDisabled(true);
        }
    }

    class c implements DayViewDecorator {
        final /* synthetic */ CheckInActivity a;

        c(CheckInActivity checkInActivity) {
            this.a = checkInActivity;
        }

        public boolean shouldDecorate(CalendarDay calendarDay) {
            return this.a.g.contains(this.a.b.format(calendarDay.getDate()));
        }

        public void decorate(DayViewFacade dayViewFacade) {
            dayViewFacade.setBackgroundDrawable(this.a.getResources().getDrawable(R.drawable.ic_footprint));
            dayViewFacade.addSpan(new ForegroundColorSpan(-1));
        }
    }

    class d extends BaseCell {
        View a;
        View b;
        TextView c;
        ImageView d;
        TextView e;
        TextView f;
        TextView g;
        LinearLayout h;
        ImageView i;
        final /* synthetic */ CheckInActivity j;

        d(CheckInActivity checkInActivity) {
            this.j = checkInActivity;
        }

        public void onCreate() {
            setCellView((int) R.layout.item_sign_fortune);
            this.a = findViewById(R.id.fortune);
            this.b = findViewById(R.id.content);
            this.b.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.shape_white_dark : R.drawable.shape_white);
            this.h = (LinearLayout) findViewById(R.id.fortune_container);
            this.c = (TextView) findViewById(R.id.astrology);
            this.d = (ImageView) findViewById(R.id.astrology_img);
            this.e = (TextView) findViewById(R.id.title);
            this.f = (TextView) findViewById(R.id.vote_left);
            this.g = (TextView) findViewById(R.id.vote_right);
            this.i = (ImageView) findViewById(R.id.vote_vs);
        }

        public void onUpdate() {
            if (TextUtils.isEmpty(this.j.j) || TextUtils.isEmpty(this.j.i)) {
                this.a.setVisibility(8);
                return;
            }
            int size;
            this.a.setVisibility(0);
            this.f.setOnClickListener(new LoginPermissionClickDelegate(new ep(this), null));
            this.g.setOnClickListener(new LoginPermissionClickDelegate(new eq(this), null));
            this.i.setImageResource(UIHelper.getCircleVoteVsOn());
            if (this.j.o) {
                this.f.setTextColor(UIHelper.getCircleVoteOnTextColor());
                this.g.setTextColor(UIHelper.getCircleVoteTextColor());
                this.f.setBackgroundResource(UIHelper.getCircleVoteLeftOn());
                this.g.setBackgroundResource(R.color.transparent);
            } else if (this.j.p) {
                this.f.setTextColor(UIHelper.getCircleVoteTextColor());
                this.g.setTextColor(UIHelper.getCircleVoteOnTextColor());
                this.f.setBackgroundResource(R.color.transparent);
                this.g.setBackgroundResource(UIHelper.getCircleVoteRightOn());
            } else {
                this.f.setBackgroundResource(R.color.transparent);
                this.g.setBackgroundResource(R.color.transparent);
                this.f.setTextColor(UIHelper.getCircleVoteTextColor());
                this.g.setTextColor(UIHelper.getCircleVoteTextColor());
                this.i.setImageResource(UIHelper.getCircleVoteVs());
            }
            if (!TextUtils.isEmpty(this.j.i)) {
                this.c.setText(this.j.i);
                displayImage(this.d, new Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(((Integer) CheckInActivity.a.get(this.j.i)).intValue())).build().toString());
            }
            this.e.setText(this.j.j);
            this.f.setText(this.j.q + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.j.O);
            this.g.setText(this.j.r + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.j.P);
            this.h.removeAllViews();
            if (this.j.m.size() >= this.j.n.size()) {
                size = this.j.m.size();
            } else {
                size = this.j.n.size();
            }
            LayoutInflater from = LayoutInflater.from(getContext());
            for (int i = 0; i < size; i++) {
                Fortune fortune;
                View inflate = from.inflate(R.layout.item_fortune_luck, null);
                if (this.j.m.size() > i) {
                    int parseInt;
                    int i2;
                    int i3;
                    fortune = (Fortune) this.j.m.get(i);
                    ImageView imageView = (ImageView) inflate.findViewById(R.id.image_0);
                    ImageView imageView2 = (ImageView) inflate.findViewById(R.id.image_1);
                    ImageView imageView3 = (ImageView) inflate.findViewById(R.id.image_2);
                    ImageView imageView4 = (ImageView) inflate.findViewById(R.id.image_3);
                    ImageView imageView5 = (ImageView) inflate.findViewById(R.id.image_4);
                    ((TextView) inflate.findViewById(R.id.left)).setText(fortune.name);
                    try {
                        parseInt = Integer.parseInt(fortune.value);
                    } catch (Exception e) {
                        e.printStackTrace();
                        parseInt = 0;
                    }
                    int i4 = UIHelper.isNightTheme() ? R.drawable.ic_fortune_shit_dark : R.drawable.ic_fortune_shit;
                    int i5 = UIHelper.isNightTheme() ? R.drawable.ic_fortune_shit_empty_dark : R.drawable.ic_fortune_shit_empty;
                    if (parseInt > 0) {
                        i2 = i4;
                    } else {
                        i2 = i5;
                    }
                    imageView.setImageResource(i2);
                    if (parseInt > 1) {
                        i3 = i4;
                    } else {
                        i3 = i5;
                    }
                    imageView2.setImageResource(i3);
                    if (parseInt > 2) {
                        i3 = i4;
                    } else {
                        i3 = i5;
                    }
                    imageView3.setImageResource(i3);
                    if (parseInt > 3) {
                        i3 = i4;
                    } else {
                        i3 = i5;
                    }
                    imageView4.setImageResource(i3);
                    if (parseInt <= 4) {
                        i4 = i5;
                    }
                    imageView5.setImageResource(i4);
                }
                if (this.j.n.size() > i) {
                    fortune = (Fortune) this.j.n.get(i);
                    ((TextView) inflate.findViewById(R.id.right)).setText(fortune.name + ": " + fortune.value);
                }
                this.h.addView(inflate);
            }
        }
    }

    class e {
        final /* synthetic */ CheckInActivity a;
        public String content;
        public String desc;
        public String link;

        e(CheckInActivity checkInActivity) {
            this.a = checkInActivity;
        }
    }

    class f implements DayViewDecorator {
        final /* synthetic */ CheckInActivity a;

        f(CheckInActivity checkInActivity) {
            this.a = checkInActivity;
        }

        public boolean shouldDecorate(CalendarDay calendarDay) {
            return (this.a.g.contains(this.a.b.format(calendarDay.getDate())) || calendarDay.getCalendar().after(Calendar.getInstance())) ? false : true;
        }

        public void decorate(DayViewFacade dayViewFacade) {
            dayViewFacade.setSelectionDrawable(this.a.getResources().getDrawable(R.drawable.bg_sign_selector));
        }
    }

    class g extends BaseImageAdapter {
        public static final int TYPE_AD = 2;
        public static final int TYPE_CALENDAR = 0;
        public static final int TYPE_COUNT = 5;
        public static final int TYPE_FORTUNE = 1;
        public static final int TYPE_PUNCH_CARD = 4;
        public static final int TYPE_UNKOWN = 3;
        final /* synthetic */ CheckInActivity a;

        public g(CheckInActivity checkInActivity, ArrayList arrayList, Activity activity) {
            this.a = checkInActivity;
            super(arrayList, activity);
        }

        public int getViewTypeCount() {
            return 5;
        }

        public int getItemViewType(int i) {
            Object item = getItem(i);
            if (item instanceof Collection) {
                return 0;
            }
            if (item instanceof String) {
                return 1;
            }
            if (item instanceof SplashAdItem) {
                return 2;
            }
            if (item instanceof PunchInfo) {
                return 4;
            }
            return 3;
        }

        public Object getItem(int i) {
            return super.getItem(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            BaseCell baseCell;
            Object item = getItem(i);
            if (view == null) {
                switch (getItemViewType(i)) {
                    case 0:
                        baseCell = this.a.G;
                        break;
                    case 1:
                        baseCell = this.a.H;
                        break;
                    case 2:
                        baseCell = new a(this.a);
                        break;
                    case 4:
                        baseCell = new PunchCardCell();
                        break;
                    default:
                        baseCell = new UnknownCell();
                        break;
                }
                baseCell.performCreate(i, viewGroup, item);
                view = baseCell.getCellView();
                view.setTag(baseCell);
            } else {
                baseCell = (BaseCell) view.getTag();
            }
            baseCell.performUpdate(i, viewGroup, item);
            return view;
        }
    }

    class h extends BaseCell {
        MaterialCalendarView a;
        View b;
        TextView c;
        TextView d;
        ImageView e;
        ImageView f;
        final /* synthetic */ CheckInActivity g;

        h(CheckInActivity checkInActivity) {
            this.g = checkInActivity;
        }

        public void onCreate() {
            setCellView((int) R.layout.item_sign_calendar);
            this.b = findViewById(R.id.content);
            this.c = (TextView) findViewById(R.id.good);
            this.d = (TextView) findViewById(R.id.bad);
            this.e = (ImageView) findViewById(R.id.img_good);
            this.f = (ImageView) findViewById(R.id.img_bad);
            Calendar instance = Calendar.getInstance();
            instance.set(5, instance.getActualMaximum(5));
            this.a = (MaterialCalendarView) findViewById(R.id.calendar);
            this.a.setBtnLeftClickListener(new er(this));
            this.a.setBtnRightClickListener(new et(this));
            this.a.setShowOtherDates(7);
            this.a.setDateTextAppearance(UIHelper.isNightTheme() ? R.style.TextAppearance.MaterialCalendarWidget.Date.Night : R.style.TextAppearance.MaterialCalendarWidget.Date);
            this.a.setArrowDownClickListener(new eu(this));
            this.a.state().edit().setMinimumDate(this.g.d.getTime()).setMaximumDate(instance.getTime()).commit();
            this.a.setOnMonthChangedListener(new ev(this));
            this.a.setTitleFormatter(new ew(this));
            this.a.setOnInterceptDateClickListener(new ex(this));
            this.a.setOnDateChangedListener(new ez(this));
            this.a.setArrowColor(-1);
            this.a.setSelectionMode(2);
            this.a.addDecorator(new b(this.g));
            this.a.addDecorator(new f(this.g));
            this.a.addDecorator(new c(this.g));
            this.a.addDecorator(new TodayDecorator(this.g, -65536));
            if (!SharePreferenceUtils.getSharePreferencesBoolValue("_first_check_in")) {
                this.a.getViewTreeObserver().addOnPreDrawListener(new fa(this));
            }
        }

        public void onUpdate() {
            if (this.a != null) {
                CharSequence stringBuilder;
                this.b.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.shape_white_dark : R.drawable.shape_white);
                this.a.setLeftCount(this.g.S);
                this.a.invalidateDecorators();
                if (this.g.k != null && this.g.k.length > 0) {
                    stringBuilder = new StringBuilder();
                    for (String append : this.g.k) {
                        stringBuilder.append(append);
                        stringBuilder.append("  ");
                    }
                    stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
                    this.c.setText(stringBuilder);
                }
                if (this.g.l != null && this.g.l.length > 0) {
                    stringBuilder = new StringBuilder();
                    for (String append2 : this.g.l) {
                        stringBuilder.append(append2);
                        stringBuilder.append("  ");
                    }
                    stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
                    this.d.setText(stringBuilder);
                }
                this.e.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_good_dark : R.drawable.ic_good);
                this.f.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_bad_dark : R.drawable.ic_bad);
            }
        }

        public void clearSelection() {
            this.a.clearSelection();
        }

        public Calendar getDate() {
            if (this.a != null) {
                return this.a.getCurrentDate().getCalendar();
            }
            return Calendar.getInstance();
        }

        public void setDate(Calendar calendar) {
            if (this.a != null) {
                this.a.setCurrentDate(calendar);
            }
        }
    }

    public CheckInActivity() {
        a.put("白羊座", Integer.valueOf(R.drawable.ic_zodiac_aries));
        a.put("金牛座", Integer.valueOf(R.drawable.ic_zodiac_taurus));
        a.put("双子座", Integer.valueOf(R.drawable.ic_zodiac_gemini));
        a.put("巨蟹座", Integer.valueOf(R.drawable.ic_zodiac_cancer));
        a.put("狮子座", Integer.valueOf(R.drawable.ic_zodiac_leo));
        a.put("处女座", Integer.valueOf(R.drawable.ic_zodiac_virgo));
        a.put("天秤座", Integer.valueOf(R.drawable.ic_zodiac_aquarius));
        a.put("天蝎座", Integer.valueOf(R.drawable.ic_zodiac_scorpio));
        a.put("射手座", Integer.valueOf(R.drawable.ic_zodiac_sagittarius));
        a.put("摩羯座", Integer.valueOf(R.drawable.ic_zodiac_capricornus));
        a.put("水瓶座", Integer.valueOf(R.drawable.ic_zodiac_aquarius));
        a.put("双鱼座", Integer.valueOf(R.drawable.ic_zodiac_pisces));
        this.d.set(2015, 8, 11);
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, CheckInActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    protected CharSequence getCustomTitle() {
        return "签到";
    }

    protected int a() {
        return R.layout.activity_check_in;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.J = new BlackProgressDialog(this);
        this.G = new h(this);
        this.H = new d(this);
        this.B = findViewById(R.id.done_container);
        this.B.setBackgroundColor(UIHelper.isNightTheme() ? getResources().getColor(R.color.navy_blue) : getResources().getColor(R.color.white));
        this.D = (TextView) findViewById(R.id.days);
        this.E = (TextView) findViewById(R.id.left_days);
        this.C = (Button) findViewById(R.id.done);
        this.C.setOnClickListener(new dv(this));
        this.z = (PtrLayout) findViewById(R.id.ptr);
        this.z.setRefreshEnable(false);
        this.z.setLoadMoreEnable(false);
        this.A = (ListView) findViewById(R.id.listview);
        this.F = new g(this, this.e, this);
        this.A.setAdapter(this.F);
        m();
        k();
        j();
        a(Calendar.getInstance());
        c();
        e();
        this.K = new ed(this);
        registerReceiver(this.K, new IntentFilter(ItemSignCardBuyActivity.ACTION_BUY_SUCCESS));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_check_in, menu);
        menu.findItem(R.id.top).setIcon(UIHelper.isNightTheme() ? R.drawable.ic_menu_top_dark : R.drawable.ic_menu_top);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.top:
                CheckInTopActivity.launch(this, QsbkApp.currentUser);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void f() {
        this.J.show();
        this.y = new HttpTask(Constants.CIRCLE_SIGN_IN_SUPPLEMENT_QUERY, new ee(this));
        this.y.setMapParams(g());
        this.y.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private Map g() {
        Map hashMap = new HashMap();
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            jSONArray.put((String) it.next());
        }
        hashMap.put("days", jSONArray);
        return hashMap;
    }

    private void i() {
        this.u = new HttpTask(Constants.CIRCLE_SIGN_IN_SUPPLEMENT, new ei(this));
        this.u.setMapParams(g());
        this.u.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!(this.v == null || this.v.isCancelled())) {
            this.v.cancel(true);
        }
        if (!(this.s == null || this.s.isCancelled())) {
            this.s.cancel(true);
        }
        if (!(this.u == null || this.u.isCancelled())) {
            this.u.cancel(true);
        }
        if (!(this.t == null || this.t.isCancelled())) {
            this.t.cancel(true);
        }
        if (!(this.w == null || this.w.isCancelled())) {
            this.w.cancel(true);
        }
        if (!(this.x == null || this.x.isCancelled())) {
            this.x.cancel(true);
        }
        if (!(this.y == null || this.y.isCancelled())) {
            this.y.cancel(true);
        }
        if (this.K != null) {
            unregisterReceiver(this.K);
        }
    }

    private void a(Calendar calendar) {
        Calendar calendar2 = (Calendar) calendar.clone();
        Calendar calendar3 = (Calendar) calendar.clone();
        calendar3.add(2, 1);
        ((Calendar) calendar3.clone()).add(2, 1);
        Calendar calendar4 = (Calendar) calendar.clone();
        calendar4.add(2, -1);
        ((Calendar) calendar4.clone()).add(2, -1);
        b(calendar2);
        b(calendar4);
        b(calendar3);
    }

    private void b(Calendar calendar) {
        Calendar c = c(calendar);
        if (!this.f.contains(this.c.format(c.getTime())) && !c.after(Calendar.getInstance())) {
            new HttpTask(Constants.CIRCLE_SIGN_IN_MONTH_RECORD + "?date=" + this.c.format(c.getTime()), new ej(this, c)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private void j() {
        this.v = new HttpTask(Constants.CIRCLE_USER_FORTUNE, new ek(this));
        this.v.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void a(String str) {
        this.s = new HttpTask(Constants.CIRCLE_USER_FORTUNE_VOTE, new el(this, str));
        Map hashMap = new HashMap();
        hashMap.put("option", str);
        this.s.setMapParams(hashMap);
        this.s.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void k() {
        this.t = new EncryptHttpTask(Constants.WALLET_BALANCE, new em(this));
        this.t.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void l() {
        String str = Constants.CIRCLE_CARD_BUY_WEB;
        Object[] objArr = new Object[1];
        objArr[0] = UIHelper.isNightTheme() ? UIHelper$Theme.THEME_NIGHT : "default";
        SimpleWebActivity.launch(this, String.format(str, objArr));
    }

    private void m() {
        long sharePreferencesLongValue = SharePreferenceUtils.getSharePreferencesLongValue(QiuyouCircleFragment.SIGN_TIME + QsbkApp.currentUser.userId);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(sharePreferencesLongValue);
        if (!TimeUtils.isSameDay(instance, Calendar.getInstance())) {
            this.w = new SimpleHttpTask(Constants.CIRCLE_SIGN_IN, new en(this));
            this.w.setMapParams(new HashMap());
            this.w.execute();
        }
    }

    private void a(int i, int i2, e eVar) {
        if (!isFinishing()) {
            new dw(this, this, eVar, i2, i).show();
        }
    }

    private void a(int i, int i2) {
        if (!isFinishing()) {
            new dy(this, this, i2, i).show();
        }
    }

    private void a(Date date) {
        new DatePickerPopWin.Builder(this, new ea(this)).textConfirm("确认").textCancel("取消").btnTextSize(16).viewTextSize(25).colorCancel(getResources().getColor(R.color.gray)).colorConfirm(getResources().getColor(R.color.yellow)).minDate(this.d).maxDate(Calendar.getInstance()).dateChose(this.b.format(date)).showDay(false).build().showPopWin(this);
    }

    void c() {
        this.x = new HttpTask(Constants.CIRCLE_SIGN_AD, new eb(this));
        this.x.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void n() {
        this.e.clear();
        if (!TextUtils.isEmpty(this.T.content)) {
            this.e.add(0, this.T);
        }
        if (this.Q != null) {
            this.e.add(this.Q);
        }
        this.e.add(this.f);
        if (this.R != null) {
            this.e.add(this.R);
        }
        if (!TextUtils.isEmpty(this.i)) {
            this.e.add(this.i);
        }
        this.F.notifyDataSetChanged();
    }

    void e() {
        PunchCardCell.getPunchCard(new ec(this));
    }

    public void onBackPressed() {
        if (this.I == null || !this.I.isShown()) {
            super.onBackPressed();
        } else {
            this.I.dismiss();
        }
    }

    private Calendar c(Calendar calendar) {
        Calendar calendar2 = (Calendar) calendar.clone();
        calendar2.set(1, calendar2.get(1));
        calendar2.set(2, calendar2.get(2));
        calendar2.set(5, 1);
        calendar2.set(10, 0);
        calendar2.set(12, 0);
        calendar2.set(14, 0);
        return calendar2;
    }

    private void q() {
        if (this.h.size() > 0) {
            this.B.setVisibility(0);
            CharSequence spannableStringBuilder = new SpannableStringBuilder("补签：");
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append(this.h.size() + "");
            spannableStringBuilder.setSpan(new RelativeSizeSpan(1.4f), length, spannableStringBuilder.length(), 33);
            spannableStringBuilder.append("天");
            spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yellow)), length, spannableStringBuilder.length(), 33);
            this.D.setText(spannableStringBuilder);
            spannableStringBuilder = new SpannableStringBuilder("可补签");
            length = spannableStringBuilder.length();
            spannableStringBuilder.append((this.S - this.h.size()) + "");
            int length2 = spannableStringBuilder.length();
            spannableStringBuilder.append("天");
            spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yellow)), length, length2, 33);
            this.E.setText(spannableStringBuilder);
            return;
        }
        this.B.setVisibility(8);
    }
}
