package qsbk.app.live.utils;

import java.util.Map;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.websocket.WebSocketHandler;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.model.LiveMessageType;

public class LiveWebSocketHandler extends WebSocketHandler {
    protected Map<String, String> f;

    public static LiveWebSocketHandler create() {
        return new LiveWebSocketHandler();
    }

    public void attach(BaseActivity baseActivity, Map<String, String> map) {
        super.attach(baseActivity);
        this.f = map;
    }

    public void connect(String str) {
        super.connect(str);
    }

    protected String a() {
        return "live";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.lang.Object a(byte[] r9) throws java.io.IOException {
        /*
        r8 = this;
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveMessageType.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessageType) r1;
        r2 = 0;
        if (r1 == 0) goto L_0x0334;
    L_0x000d:
        r2 = r1.p;
        switch(r2) {
            case 1: goto L_0x0088;
            case 2: goto L_0x00a0;
            case 3: goto L_0x004c;
            case 4: goto L_0x0064;
            case 5: goto L_0x007c;
            case 6: goto L_0x00ad;
            case 7: goto L_0x007c;
            case 8: goto L_0x007c;
            case 9: goto L_0x0112;
            case 10: goto L_0x0070;
            case 11: goto L_0x011f;
            case 12: goto L_0x007c;
            case 13: goto L_0x0058;
            case 14: goto L_0x012c;
            case 15: goto L_0x0012;
            case 16: goto L_0x0139;
            case 17: goto L_0x007c;
            case 18: goto L_0x007c;
            case 19: goto L_0x0146;
            case 20: goto L_0x0012;
            case 21: goto L_0x0153;
            case 22: goto L_0x0160;
            case 23: goto L_0x007c;
            case 24: goto L_0x0094;
            case 25: goto L_0x007c;
            case 26: goto L_0x0012;
            case 27: goto L_0x007c;
            case 28: goto L_0x007c;
            case 29: goto L_0x016d;
            case 30: goto L_0x0012;
            case 31: goto L_0x0012;
            case 32: goto L_0x0012;
            case 33: goto L_0x0012;
            case 34: goto L_0x0012;
            case 35: goto L_0x0012;
            case 36: goto L_0x0012;
            case 37: goto L_0x0012;
            case 38: goto L_0x0012;
            case 39: goto L_0x0012;
            case 40: goto L_0x0012;
            case 41: goto L_0x01b4;
            case 42: goto L_0x01c1;
            case 43: goto L_0x01c1;
            case 44: goto L_0x01c1;
            case 45: goto L_0x01c1;
            case 46: goto L_0x0228;
            case 47: goto L_0x0228;
            case 48: goto L_0x0228;
            case 49: goto L_0x01c1;
            case 50: goto L_0x0012;
            case 51: goto L_0x0153;
            case 52: goto L_0x0235;
            case 53: goto L_0x0235;
            case 54: goto L_0x0012;
            case 55: goto L_0x0012;
            case 56: goto L_0x00ad;
            case 57: goto L_0x025c;
            case 58: goto L_0x0276;
            case 59: goto L_0x0269;
            case 60: goto L_0x0012;
            case 61: goto L_0x02aa;
            case 62: goto L_0x0012;
            case 63: goto L_0x0012;
            case 64: goto L_0x0012;
            case 65: goto L_0x0012;
            case 66: goto L_0x0012;
            case 67: goto L_0x0012;
            case 68: goto L_0x0012;
            case 69: goto L_0x0012;
            case 70: goto L_0x0012;
            case 71: goto L_0x02b7;
            case 72: goto L_0x02c4;
            case 73: goto L_0x02d1;
            case 74: goto L_0x02de;
            case 75: goto L_0x0012;
            case 76: goto L_0x0012;
            case 77: goto L_0x0012;
            case 78: goto L_0x0012;
            case 79: goto L_0x0012;
            case 80: goto L_0x0012;
            case 81: goto L_0x0012;
            case 82: goto L_0x0012;
            case 83: goto L_0x0012;
            case 84: goto L_0x0012;
            case 85: goto L_0x0012;
            case 86: goto L_0x0242;
            case 87: goto L_0x0242;
            case 88: goto L_0x0242;
            case 89: goto L_0x024f;
            case 90: goto L_0x0012;
            case 91: goto L_0x0012;
            case 92: goto L_0x0012;
            case 93: goto L_0x020e;
            case 94: goto L_0x0012;
            case 95: goto L_0x021b;
            case 96: goto L_0x01c1;
            case 97: goto L_0x0283;
            case 98: goto L_0x0290;
            case 99: goto L_0x0012;
            case 100: goto L_0x0012;
            case 101: goto L_0x0012;
            case 102: goto L_0x029d;
            case 103: goto L_0x0290;
            case 104: goto L_0x0088;
            case 105: goto L_0x02eb;
            case 106: goto L_0x0012;
            case 107: goto L_0x007c;
            case 108: goto L_0x01c1;
            case 109: goto L_0x007c;
            case 110: goto L_0x007c;
            default: goto L_0x0012;
        };
    L_0x0012:
        r2 = r8.b;	 Catch:{ Exception -> 0x02f8 }
        r3 = qsbk.app.live.model.LiveCommonMessage.class;
        r2 = r2.readValue(r9, r3);	 Catch:{ Exception -> 0x02f8 }
        r0 = r2;
        r0 = (qsbk.app.live.model.LiveMessage) r0;	 Catch:{ Exception -> 0x02f8 }
        r1 = r0;
        r2 = r1;
    L_0x001f:
        if (r2 == 0) goto L_0x004a;
    L_0x0021:
        r1 = r2.getUserAvatar();
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x004a;
    L_0x002b:
        r1 = r8.f;
        r3 = r2.getUserAvatarTemplate();
        r1 = r1.get(r3);
        r1 = (java.lang.String) r1;
        r3 = android.text.TextUtils.isEmpty(r1);
        if (r3 != 0) goto L_0x004a;
    L_0x003d:
        r3 = "$";
        r4 = r2.getUserAvatar();
        r1 = r1.replace(r3, r4);
        r2.setUserAvatar(r1);
    L_0x004a:
        r1 = r2;
    L_0x004b:
        return r1;
    L_0x004c:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveLoveMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0058:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveSystemMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0064:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveEnterMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0070:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveHeartMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x007c:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveCommonMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0088:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveCommentMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0094:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveBarrageMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x00a0:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveFollowMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x00ad:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveGiftMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1.getLiveMessageContent();
        r2 = (qsbk.app.live.model.LiveGiftMessageContent) r2;
        if (r2 == 0) goto L_0x0331;
    L_0x00bf:
        r3 = r2.g;
        if (r3 == 0) goto L_0x0331;
    L_0x00c3:
        r3 = r2.g;
        r3 = r3.t;
        r3 = android.text.TextUtils.isEmpty(r3);
        if (r3 != 0) goto L_0x0331;
    L_0x00cd:
        r3 = r8.f;
        r4 = r2.g;
        r4 = r4.t;
        r3 = r3.get(r4);
        r3 = (java.lang.String) r3;
        r4 = android.text.TextUtils.isEmpty(r3);
        if (r4 != 0) goto L_0x010f;
    L_0x00df:
        r4 = r2.g;
        r4 = r4.m;
        r4 = android.text.TextUtils.isEmpty(r4);
        if (r4 != 0) goto L_0x00f7;
    L_0x00e9:
        r4 = "$";
        r5 = r2.g;
        r5 = r5.m;
        r4 = r3.replace(r4, r5);
        r5 = r2.g;
        r5.m = r4;
    L_0x00f7:
        r4 = r2.g;
        r4 = r4.a;
        r4 = android.text.TextUtils.isEmpty(r4);
        if (r4 != 0) goto L_0x010f;
    L_0x0101:
        r4 = "$";
        r5 = r2.g;
        r5 = r5.a;
        r3 = r3.replace(r4, r5);
        r2 = r2.g;
        r2.a = r3;
    L_0x010f:
        r2 = r1;
        goto L_0x001f;
    L_0x0112:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveSendErrorMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x011f:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveReconnectMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x012c:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveBalanceMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0139:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveBeautyMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0146:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveDataMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0153:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveSilentMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0160:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveRichMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x016d:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveGlobalGiftMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1.getLiveMessageContent();
        r2 = (qsbk.app.live.model.LiveGlobalGiftMessageContent) r2;
        if (r2 == 0) goto L_0x0331;
    L_0x017f:
        r3 = r2.t;
        r3 = android.text.TextUtils.isEmpty(r3);
        if (r3 != 0) goto L_0x0331;
    L_0x0187:
        r3 = r2.p;
        r3 = android.text.TextUtils.isEmpty(r3);
        if (r3 != 0) goto L_0x0331;
    L_0x018f:
        r3 = r8.f;
        r4 = r2.t;
        r3 = r3.get(r4);
        r3 = (java.lang.String) r3;
        r4 = android.text.TextUtils.isEmpty(r3);
        if (r4 != 0) goto L_0x01b1;
    L_0x019f:
        r4 = r2.p;
        r4 = android.text.TextUtils.isEmpty(r4);
        if (r4 != 0) goto L_0x01b1;
    L_0x01a7:
        r4 = "$";
        r5 = r2.p;
        r3 = r3.replace(r4, r5);
        r2.p = r3;
    L_0x01b1:
        r2 = r1;
        goto L_0x001f;
    L_0x01b4:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveProTopMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x01c1:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveGameDataMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        r2 = (qsbk.app.live.model.LiveGameDataMessage) r2;
        r5 = r2.getBestBetResult();
        if (r5 == 0) goto L_0x0331;
    L_0x01d4:
        r2 = r5.size();
        if (r2 <= 0) goto L_0x0331;
    L_0x01da:
        r2 = 0;
        r4 = r2;
    L_0x01dc:
        r2 = r5.size();
        if (r4 >= r2) goto L_0x020b;
    L_0x01e2:
        r2 = r5.get(r4);
        r2 = (qsbk.app.live.model.BestBetResult) r2;
        r3 = r8.f;
        r6 = r2.getAvatarTemplate();
        r3 = r3.get(r6);
        r3 = (java.lang.String) r3;
        r6 = android.text.TextUtils.isEmpty(r3);
        if (r6 != 0) goto L_0x0207;
    L_0x01fa:
        r6 = "$";
        r7 = r2.getAvatar();
        r3 = r3.replace(r6, r7);
        r2.setAvatar(r3);
    L_0x0207:
        r2 = r4 + 1;
        r4 = r2;
        goto L_0x01dc;
    L_0x020b:
        r2 = r1;
        goto L_0x001f;
    L_0x020e:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveGameAnchorSelectMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x021b:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveGameStepMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0228:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveGameBetMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0235:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveMicMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0242:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveDollMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x024f:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveDollActionMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x025c:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveGiftWeekRankMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0269:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveGiftZhouxingMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0276:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveGiftWeekRankHintMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0283:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveGlobalRedEnvelopesMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x0290:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveRedEnvelopesMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x029d:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveRobRedEnvelopesResultMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x02aa:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveActivityMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x02b7:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveFreeGiftStartMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x02c4:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveFreeGiftAvailableMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x02d1:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveFreeGiftGetMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x02de:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveNewerDiscountMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x02eb:
        r1 = r8.b;
        r2 = qsbk.app.live.model.LiveRemindMessage.class;
        r1 = r1.readValue(r9, r2);
        r1 = (qsbk.app.live.model.LiveMessage) r1;
        r2 = r1;
        goto L_0x001f;
    L_0x02f8:
        r2 = move-exception;
        r3 = r2;
        r2 = r8.b;
        r4 = java.util.Map.class;
        r2 = r2.readValue(r9, r4);
        r2 = (java.util.Map) r2;
        r4 = "websocket";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = r8.a();
        r5 = r5.append(r6);
        r6 = " websocket parse msg failed at type:";
        r5 = r5.append(r6);
        r1 = r1.p;
        r1 = r5.append(r1);
        r5 = " and content is ";
        r1 = r1.append(r5);
        r1 = r1.append(r2);
        r1 = r1.toString();
        qsbk.app.core.utils.LogUtils.d(r4, r1);
        throw r3;
    L_0x0331:
        r2 = r1;
        goto L_0x001f;
    L_0x0334:
        r1 = r2;
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.live.utils.LiveWebSocketHandler.a(byte[]):java.lang.Object");
    }

    protected boolean b(Object obj) {
        if (!(obj instanceof LiveMessageType)) {
            return super.b(obj);
        }
        LiveMessageType liveMessageType = (LiveMessageType) obj;
        if (liveMessageType.p == 18 || liveMessageType.p == 1 || liveMessageType.p == 12) {
            return false;
        }
        return true;
    }

    protected Object b() {
        return LiveMessage.createHeartBeatMessage(AppUtils.getInstance().getUserInfoProvider().getUserOrigin());
    }
}
