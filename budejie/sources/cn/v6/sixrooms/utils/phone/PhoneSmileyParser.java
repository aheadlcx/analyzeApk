package cn.v6.sixrooms.utils.phone;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R.drawable;
import cn.v6.sixrooms.bean.SmileyVo;
import cn.v6.sixrooms.utils.DisPlayUtil;
import cn.v6.sixrooms.utils.SmilyEncUtils;
import cn.v6.sixrooms.widgets.phone.ImageSpanCenter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneSmileyParser {
    private static PhoneSmileyParser a = null;
    private Context b = null;
    private Pattern c = null;
    private HashMap<String, String> d = null;
    private String[] e = null;
    private String[] f = null;
    private List<List<SmileyVo>> g = null;
    private int h = 20;
    private int i = 8;
    private int j = 9;
    private List<List<SmileyVo>> k;
    private HashMap<String, String> l;
    private List<SmileyVo> m;
    private List<SmileyVo> n;
    private HashMap<String, String> o;
    private float p;

    public static PhoneSmileyParser getInstance(Context context) {
        if (a == null) {
            a = new PhoneSmileyParser(context);
        }
        return a;
    }

    private PhoneSmileyParser(Context context) {
        this.b = context;
        c();
        this.c = b();
        this.d = a();
        this.g = conventData();
    }

    public int getGroupCount() {
        return this.g.size();
    }

    public boolean parserText(String str) {
        if (TextUtils.isEmpty((String) this.d.get(str))) {
            return true;
        }
        return false;
    }

    public List<List<SmileyVo>> conventData() {
        List<List<SmileyVo>> arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        for (int i = 0; i < this.e.length; i++) {
            SmileyVo smileyVo = new SmileyVo();
            smileyVo.setFileName(this.e[i]);
            smileyVo.setFaceName(this.f[i]);
            arrayList2.add(smileyVo);
        }
        arrayList.add(arrayList2);
        arrayList.add(this.m);
        arrayList.add(this.n);
        return arrayList;
    }

    public List<List<SmileyVo>> getdivisData(int i) {
        int i2 = 0;
        this.k = new ArrayList();
        int size = ((List) this.g.get(i)).size();
        switch (i) {
            case 0:
                if (size % this.h != 0) {
                    size = (size / this.h) + 1;
                    break;
                }
                size /= this.h;
                break;
            case 1:
                if (size % this.i != 0) {
                    size = (size / this.i) + 1;
                    break;
                }
                size /= this.i;
                break;
            case 2:
                if (size % this.j != 0) {
                    size = (size / this.j) + 1;
                    break;
                }
                size /= this.j;
                break;
            default:
                size = 0;
                break;
        }
        while (i2 < size) {
            this.k.add(getData(i2, i));
            i2++;
        }
        return this.k;
    }

    public static int getResId(String str, Context context, Class<?> cls) {
        try {
            return cls.getDeclaredField(str).getInt(drawable.class);
        } catch (Exception e) {
            return -1;
        }
    }

    private HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap(this.e.length);
        for (int i = 0; i < this.e.length; i++) {
            hashMap.put(this.f[i], this.e[i]);
        }
        return hashMap;
    }

    private Pattern b() {
        StringBuilder stringBuilder = new StringBuilder(((this.f.length * 3) + (this.l.size() * 3)) + (this.o.size() * 3));
        stringBuilder.append('(');
        for (String quote : this.f) {
            stringBuilder.append(Pattern.quote(quote));
            stringBuilder.append('|');
        }
        for (Entry key : this.l.entrySet()) {
            stringBuilder.append(Pattern.quote((String) key.getKey()));
            stringBuilder.append('|');
        }
        for (Entry key2 : this.o.entrySet()) {
            stringBuilder.append(Pattern.quote((String) key2.getKey()));
            stringBuilder.append('|');
        }
        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), ")");
        return Pattern.compile(stringBuilder.toString());
    }

    public Spannable addSmileySpans(CharSequence charSequence, List<String> list, List<String> list2) {
        Spannable spannableStringBuilder = new SpannableStringBuilder(charSequence);
        Matcher matcher = this.c.matcher(charSequence);
        boolean z = true;
        while (matcher.find()) {
            boolean z2;
            String group = matcher.group();
            Object obj = (String) this.d.get(group);
            if (TextUtils.isEmpty(obj) && list2 != null && (list2.contains("7569") || list2.contains("7570"))) {
                obj = (String) this.l.get(group);
                z = false;
            }
            if (TextUtils.isEmpty(obj) && list != null && (list.contains("7104") || list.contains("7105") || list.contains("7559"))) {
                obj = (String) this.o.get(group);
                z = false;
            }
            if (TextUtils.isEmpty(obj)) {
                z2 = z;
            } else {
                spannableStringBuilder.setSpan(new ImageSpanCenter(this.b, SmilyEncUtils.getInstance().getSmilyByFileName(obj, getScale(z))), matcher.start(), matcher.end(), 33);
                z2 = true;
            }
            z = z2;
        }
        return spannableStringBuilder;
    }

    public float getScale(boolean z) {
        if (z) {
            return ((float) Math.round(((float) ((DisPlayUtil.getWidth(V6Coop.getInstance().getContext()) * 100) / 720)) * 0.6f)) / 100.0f;
        }
        return ((float) DisPlayUtil.getWidth(V6Coop.getInstance().getContext())) / 720.0f;
    }

    public float getScale() {
        return getScale(true);
    }

    public Spannable addSmileySpans(CharSequence charSequence) {
        Spannable spannableStringBuilder = new SpannableStringBuilder(charSequence);
        Matcher matcher = this.c.matcher(charSequence);
        while (matcher.find()) {
            String str = (String) this.d.get(matcher.group());
            if (!TextUtils.isEmpty(str)) {
                spannableStringBuilder.setSpan(new ImageSpanCenter(this.b, SmilyEncUtils.getInstance().getSmilyByFileName(str, getScale())), matcher.start(), matcher.end(), 33);
            }
        }
        return spannableStringBuilder;
    }

    public Spannable addSmileySpansByRetransmit(CharSequence charSequence, float f) {
        Spannable spannableStringBuilder = new SpannableStringBuilder(charSequence);
        Matcher matcher = this.c.matcher(charSequence);
        while (matcher.find()) {
            String str = (String) this.d.get(matcher.group());
            if (!TextUtils.isEmpty(str)) {
                spannableStringBuilder.setSpan(new ImageSpanCenter(this.b, SmilyEncUtils.getInstance().getSmilyByFileName(str, getScale() * f, true)), matcher.start(), matcher.end(), 33);
            }
        }
        return spannableStringBuilder;
    }

    public List<SmileyVo> getData(int i, int i2) {
        List list = (List) this.g.get(i2);
        int i3 = 0;
        if (i2 == 0) {
            i3 = this.h;
        } else if (i2 == 1) {
            i3 = this.i;
        } else if (i2 == 2) {
            i3 = this.j;
        }
        int i4 = i * i3;
        int i5 = i4 + i3;
        if (i5 > list.size()) {
            i5 = list.size();
        }
        List<SmileyVo> arrayList = new ArrayList();
        arrayList.addAll(list.subList(i4, i5));
        if (arrayList.size() <= i3) {
            for (int size = arrayList.size(); size < i3; size++) {
                arrayList.add(new SmileyVo());
            }
        }
        return arrayList;
    }

    private void c() {
        this.e = new String[60];
        this.e[0] = "face_0.png";
        this.e[1] = "face_1.png";
        this.e[2] = "face_2.png";
        this.e[3] = "face_3.png";
        this.e[4] = "face_4.png";
        this.e[5] = "face_5.png";
        this.e[6] = "face_6.png";
        this.e[7] = "face_7.png";
        this.e[8] = "face_8.png";
        this.e[9] = "face_9.png";
        this.e[10] = "face_10.png";
        this.e[11] = "face_11.png";
        this.e[12] = "face_12.png";
        this.e[13] = "face_13.png";
        this.e[14] = "face_14.png";
        this.e[15] = "face_15.png";
        this.e[16] = "face_16.png";
        this.e[17] = "face_17.png";
        this.e[18] = "face_18.png";
        this.e[19] = "face_19.png";
        this.e[20] = "face_20.png";
        this.e[21] = "face_21.png";
        this.e[22] = "face_22.png";
        this.e[23] = "face_23.png";
        this.e[24] = "face_24.png";
        this.e[25] = "face_25.png";
        this.e[26] = "face_26.png";
        this.e[27] = "face_27.png";
        this.e[28] = "face_28.png";
        this.e[29] = "face_29.png";
        this.e[30] = "face_30.png";
        this.e[31] = "face_31.png";
        this.e[32] = "face_32.png";
        this.e[33] = "face_33.png";
        this.e[34] = "face_34.png";
        this.e[35] = "face_35.png";
        this.e[36] = "face_36.png";
        this.e[37] = "face_37.png";
        this.e[38] = "face_38.png";
        this.e[39] = "face_39.png";
        this.e[40] = "face_40.png";
        this.e[41] = "face_41.png";
        this.e[42] = "face_42.png";
        this.e[43] = "face_43.png";
        this.e[44] = "face_44.png";
        this.e[45] = "face_45.png";
        this.e[46] = "face_46.png";
        this.e[47] = "face_47.png";
        this.e[48] = "face_48.png";
        this.e[49] = "face_49.png";
        this.e[50] = "face_50.png";
        this.e[51] = "face_51.png";
        this.e[52] = "face_52.png";
        this.e[53] = "face_53.png";
        this.e[54] = "face_54.png";
        this.e[55] = "face_55.png";
        this.e[56] = "face_56.png";
        this.e[57] = "face_57.png";
        this.e[58] = "face_58.png";
        this.e[59] = "face_59.png";
        this.f = new String[60];
        this.f[0] = "/狂笑";
        this.f[1] = "/大笑";
        this.f[2] = "/惊讶";
        this.f[3] = "/害羞";
        this.f[4] = "/窃笑";
        this.f[5] = "/发怒";
        this.f[6] = "/大哭";
        this.f[7] = "/色色";
        this.f[8] = "/坏笑";
        this.f[9] = "/火大";
        this.f[10] = "/汗";
        this.f[11] = "/奸笑";
        this.f[12] = "/欢迎";
        this.f[13] = "/再见";
        this.f[14] = "/白眼";
        this.f[15] = "/挖鼻";
        this.f[16] = "/顶";
        this.f[17] = "/胜利";
        this.f[18] = "/欧耶";
        this.f[19] = "/抱拳";
        this.f[20] = "/囧";
        this.f[21] = "/淡定";
        this.f[22] = "/美女";
        this.f[23] = "/靓仔";
        this.f[24] = "/神马";
        this.f[25] = "/开心";
        this.f[26] = "/给力";
        this.f[27] = "/飞吻";
        this.f[28] = "/眨眼";
        this.f[29] = "/V5";
        this.f[30] = "/来吧";
        this.f[31] = "/围观";
        this.f[32] = "/飘过";
        this.f[33] = "/地雷";
        this.f[34] = "/菜刀";
        this.f[35] = "/帅";
        this.f[36] = "/审视";
        this.f[37] = "/无语";
        this.f[38] = "/无奈";
        this.f[39] = "/亲亲";
        this.f[40] = "/勾引";
        this.f[41] = "/后后";
        this.f[42] = "/吐血";
        this.f[43] = "/媚眼";
        this.f[44] = "/愁人";
        this.f[45] = "/肿么了";
        this.f[46] = "/调戏";
        this.f[47] = "/抽";
        this.f[48] = "/哼哼";
        this.f[49] = "/bs";
        this.f[50] = "/鸡冻";
        this.f[51] = "/眼馋";
        this.f[52] = "/热汗";
        this.f[53] = "/输";
        this.f[54] = "/石化";
        this.f[55] = "/蔑视";
        this.f[56] = "/哭";
        this.f[57] = "/骂";
        this.f[58] = "/狂哭";
        this.f[59] = "/狂汗";
        this.m = new ArrayList();
        this.m.add(new SmileyVo("/被扁", "rooms_third_face_1_0.png"));
        this.m.add(new SmileyVo("/变脸", "rooms_third_face_1_1.png"));
        this.m.add(new SmileyVo("/吃饭", "rooms_third_face_1_2.png"));
        this.m.add(new SmileyVo("/吹裙子", "rooms_third_face_1_3.png"));
        this.m.add(new SmileyVo("/打劫", "rooms_third_face_1_4.png"));
        this.m.add(new SmileyVo("/憨笑", "rooms_third_face_1_5.png"));
        this.m.add(new SmileyVo("/泪流满面", "rooms_third_face_1_6.png"));
        this.m.add(new SmileyVo("/傻笑", "rooms_third_face_1_7.png"));
        this.m.add(new SmileyVo("/惊吓", "rooms_third_face_1_8.png"));
        this.m.add(new SmileyVo("/惊恐", "rooms_third_face_1_9.png"));
        this.m.add(new SmileyVo("/好囧", "rooms_third_face_1_10.png"));
        this.m.add(new SmileyVo("/蹲墙角", "rooms_third_face_1_11.png"));
        this.m.add(new SmileyVo("/可爱", "rooms_third_face_1_12.png"));
        this.m.add(new SmileyVo("/委屈落泪", "rooms_third_face_1_13.png"));
        this.m.add(new SmileyVo("/抠鼻", "rooms_third_face_1_14.png"));
        this.m.add(new SmileyVo("/亲一个", "rooms_third_face_1_15.png"));
        this.m.add(new SmileyVo("/色迷迷", "rooms_third_face_1_16.png"));
        this.m.add(new SmileyVo("/闪闪发光", "rooms_third_face_1_17.png"));
        this.m.add(new SmileyVo("/虐", "rooms_third_face_1_18.png"));
        this.m.add(new SmileyVo("/幸福", "rooms_third_face_1_19.png"));
        this.m.add(new SmileyVo("/装帅", "rooms_third_face_1_20.png"));
        this.m.add(new SmileyVo("/拍砖", "rooms_third_face_1_21.png"));
        this.m.add(new SmileyVo("/左吐", "rooms_third_face_1_22.png"));
        this.m.add(new SmileyVo("/右吐", "rooms_third_face_1_23.png"));
        this.m.add(new SmileyVo("/左闪", "rooms_third_face_1_24.png"));
        this.m.add(new SmileyVo("/右闪", "rooms_third_face_1_25.png"));
        this.m.add(new SmileyVo("/嫁给我吧", "rooms_third_face_1_26.png"));
        this.m.add(new SmileyVo("/心动的感觉", "rooms_third_face_1_27.png"));
        this.m.add(new SmileyVo("/兄弟们上", "rooms_third_face_1_28.png"));
        this.m.add(new SmileyVo("/白富美", "rooms_third_face_1_29.png"));
        this.m.add(new SmileyVo("/求交往", "rooms_third_face_1_30.png"));
        this.m.add(new SmileyVo("/在一起", "rooms_third_face_1_31.png"));
        this.m.add(new SmileyVo("/好基友", "rooms_third_face_1_32.png"));
        this.m.add(new SmileyVo("/屌爆了", "rooms_third_face_1_33.png"));
        this.m.add(new SmileyVo("/走你", "rooms_third_face_1_34.png"));
        this.m.add(new SmileyVo("/看好老婆", "rooms_third_face_1_35.png"));
        this.l = new HashMap();
        for (SmileyVo smileyVo : this.m) {
            this.l.put(smileyVo.getFaceName(), smileyVo.getFileName());
        }
        this.n = new ArrayList();
        this.n.add(new SmileyVo("/真好听", "rooms_third_face_2_0.png"));
        this.n.add(new SmileyVo("/嗨起来", "rooms_third_face_2_1.png"));
        this.n.add(new SmileyVo("/霸气", "rooms_third_face_2_2.png"));
        this.n.add(new SmileyVo("/红包刷起来", "rooms_third_face_2_3.png"));
        this.n.add(new SmileyVo("/太漂亮了", "rooms_third_face_2_4.png"));
        this.n.add(new SmileyVo("/马上投票", "rooms_third_face_2_5.png"));
        this.n.add(new SmileyVo("/玫瑰在哪里", "rooms_third_face_2_6.png"));
        this.n.add(new SmileyVo("/土豪来啦", "rooms_third_face_2_7.png"));
        this.n.add(new SmileyVo("/爱死你了", "rooms_third_face_2_8.png"));
        this.n.add(new SmileyVo("/啵一个", "rooms_third_face_2_9.png"));
        this.n.add(new SmileyVo("/新货求关注", "rooms_third_face_2_10.png"));
        this.n.add(new SmileyVo("/要抱抱", "rooms_third_face_2_11.png"));
        this.n.add(new SmileyVo("/冒个泡", "rooms_third_face_2_12.png"));
        this.n.add(new SmileyVo("/有黑幕", "rooms_third_face_2_13.png"));
        this.n.add(new SmileyVo("/爱你1314", "rooms_third_face_2_14.png"));
        this.n.add(new SmileyVo("/好甜呀", "rooms_third_face_2_15.png"));
        this.n.add(new SmileyVo("/坑爹", "rooms_third_face_2_16.png"));
        this.n.add(new SmileyVo("/女汉子", "rooms_third_face_2_17.png"));
        this.n.add(new SmileyVo("/鼓掌", "rooms_third_face_2_18.png"));
        this.n.add(new SmileyVo("/加油", "rooms_third_face_2_19.png"));
        this.n.add(new SmileyVo("/天然呆", "rooms_third_face_2_20.png"));
        this.n.add(new SmileyVo("/赞", "rooms_third_face_2_21.png"));
        this.o = new HashMap();
        for (SmileyVo smileyVo2 : this.n) {
            this.o.put(smileyVo2.getFaceName(), smileyVo2.getFileName());
        }
    }

    public Spannable addSmileySpans(String str, List<String> list, List<String> list2, float f) {
        this.p = f;
        return addSmileySpans(str, list, list2);
    }
}
