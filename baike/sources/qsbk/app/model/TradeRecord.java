package qsbk.app.model;

import java.util.Calendar;
import org.json.JSONObject;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.im.TimeUtils;
import qsbk.app.utils.DateUtil;

public class TradeRecord {
    public String content;
    public String id;
    public String money;
    public int pos;
    public String source;
    public long time;
    public String toast;

    public static TradeRecord parse(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        TradeRecord tradeRecord = new TradeRecord();
        tradeRecord.money = jSONObject.optString(PayPWDUniversalActivity.MONEY);
        tradeRecord.content = jSONObject.optString("cnt");
        tradeRecord.time = jSONObject.optLong(QsbkDatabase.CREATE_AT);
        tradeRecord.source = jSONObject.optString("source");
        tradeRecord.pos = jSONObject.optInt("pos");
        tradeRecord.id = jSONObject.optString("record_id");
        tradeRecord.toast = jSONObject.optString("toast");
        return tradeRecord;
    }

    public boolean isPositive() {
        return this.pos == 1;
    }

    public String getTimeString() {
        long j = this.time * 1000;
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        Calendar instance2 = Calendar.getInstance();
        if (TimeUtils.isSameDay(instance2, instance)) {
            return DateUtil.getHHMM(instance);
        }
        if (TimeUtils.isSameYear(instance2, instance)) {
            return DateUtil.getMMddHHmm(instance);
        }
        return DateUtil.getYYYYMMddHHmm(instance);
    }
}
