package com.alibaba.fastjson.serializer;

import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

public class JSONLibDataFormatSerializer implements ObjectSerializer {
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        if (obj == null) {
            jSONSerializer.out.writeNull();
            return;
        }
        Date date = (Date) obj;
        Object jSONObject = new JSONObject();
        jSONObject.put(HistoryOpenHelper.COLUMN_DATE, Integer.valueOf(date.getDate()));
        jSONObject.put("day", Integer.valueOf(date.getDay()));
        jSONObject.put("hours", Integer.valueOf(date.getHours()));
        jSONObject.put("minutes", Integer.valueOf(date.getMinutes()));
        jSONObject.put("month", Integer.valueOf(date.getMonth()));
        jSONObject.put("seconds", Integer.valueOf(date.getSeconds()));
        jSONObject.put(AppLinkConstants.TIME, Long.valueOf(date.getTime()));
        jSONObject.put("timezoneOffset", Integer.valueOf(date.getTimezoneOffset()));
        jSONObject.put("year", Integer.valueOf(date.getYear()));
        jSONSerializer.write(jSONObject);
    }
}
