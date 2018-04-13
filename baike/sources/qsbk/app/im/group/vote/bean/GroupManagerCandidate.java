package qsbk.app.im.group.vote.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.model.QbBean;

public class GroupManagerCandidate extends QbBean implements Serializable {
    public int age;
    public String avatar;
    public String gender;
    public int groupAge;
    public boolean isOwner;
    public String manifesto;
    public GroupMonthActive[] monthActives;
    public String nickName;
    public ArrayList<GroupManagerInfo> ownerHistory = new ArrayList();
    public int qbAge;
    public int uid;
    public int votesNum;

    public GroupManagerCandidate(JSONObject jSONObject) {
        parseJson(jSONObject);
    }

    public void parseJson(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.uid = jSONObject.optInt("uid");
                this.isOwner = jSONObject.optBoolean("is_owner");
                this.gender = jSONObject.optString("gender");
                this.age = jSONObject.optInt("age");
                this.manifesto = jSONObject.optString("reason");
                this.votesNum = jSONObject.optInt("voters_num");
                this.nickName = jSONObject.optString(QsbkDatabase.LOGIN);
                this.qbAge = jSONObject.optInt("qb_age");
                this.groupAge = jSONObject.optInt("t_age");
                this.monthActives = parseMonthActive(jSONObject.optJSONArray("active_list"));
                JSONArray optJSONArray = jSONObject.optJSONArray("admin_list");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        this.ownerHistory.add(new GroupManagerInfo(optJSONArray.optJSONObject(i)));
                    }
                }
                this.avatar = jSONObject.optString("icon");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void a(int[] iArr, int[] iArr2, int i, int i2) {
        if (i < i2) {
            int i3 = iArr[i];
            int i4 = iArr2[i];
            int i5 = i2;
            int i6 = i;
            while (i6 < i5) {
                while (i6 < i5 && iArr[i5] > i3) {
                    i5--;
                }
                iArr[i6] = iArr[i5];
                iArr2[i6] = iArr2[i5];
                while (i6 < i5 && iArr[i6] < i3) {
                    i6++;
                }
                iArr[i5] = iArr[i6];
                iArr2[i5] = iArr2[i6];
            }
            iArr[i6] = i3;
            iArr2[i6] = i4;
            a(iArr, iArr2, i, i6 - 1);
            a(iArr, iArr2, i6 + 1, i2);
        }
    }

    public GroupMonthActive[] parseMonthActive(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        try {
            int i;
            int length = jSONArray.length();
            int[] iArr = new int[length];
            Object obj = new int[length];
            for (i = 0; i < length; i++) {
                JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                iArr[i] = jSONArray2.getInt(0);
                obj[i] = jSONArray2.getInt(1);
            }
            a(iArr, obj, 0, length - 1);
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(((long) iArr[length - 1]) * 1000);
            i = instance.get(2) + 1;
            int i2 = instance.get(1);
            instance.set(14, 0);
            instance.set(13, 0);
            instance.set(12, 0);
            instance.set(11, 0);
            instance.set(5, 1);
            int timeInMillis = (int) (instance.getTimeInMillis() / 1000);
            instance.setTimeInMillis(((long) iArr[0]) * 1000);
            int i3 = instance.get(2) + 1;
            int i4 = instance.get(1);
            instance.set(14, 0);
            instance.set(13, 0);
            instance.set(12, 0);
            instance.set(11, 0);
            instance.set(5, 1);
            int timeInMillis2 = (int) (instance.getTimeInMillis() / 1000);
            i = (i - i3) + 1;
            if (i2 != i4) {
                i4 = i + 12;
            } else {
                i4 = i;
            }
            int[] iArr2 = new int[i4];
            iArr2[0] = timeInMillis2;
            iArr2[i4 - 1] = timeInMillis;
            i = 0;
            for (i2 = 1; i2 < i4 - 1; i2++) {
                timeInMillis = (i3 + i2) - 1;
                instance.set(2, timeInMillis);
                if (i > 0) {
                    instance.set(1, instance.get(1) + i);
                }
                if (timeInMillis == 12) {
                    i++;
                }
                iArr2[i2] = (int) (instance.getTimeInMillis() / 1000);
            }
            GroupMonthActive[] groupMonthActiveArr = new GroupMonthActive[i4];
            int i5 = 0;
            timeInMillis = 0;
            while (timeInMillis < i4 - 1) {
                GroupMonthActive groupMonthActive = new GroupMonthActive();
                groupMonthActive.month = i3 + timeInMillis;
                if (groupMonthActive.month > 12) {
                    groupMonthActive.month %= 12;
                }
                i2 = i5;
                while (i2 < length) {
                    if (iArr[i2] >= iArr2[timeInMillis + 1]) {
                        groupMonthActive.actives = new int[(i2 - i5)];
                        System.arraycopy(obj, i5, groupMonthActive.actives, 0, i2 - i5);
                        break;
                    }
                    i2++;
                }
                i2 = i5;
                groupMonthActiveArr[timeInMillis] = groupMonthActive;
                timeInMillis++;
                i5 = i2;
            }
            GroupMonthActive groupMonthActive2 = new GroupMonthActive();
            groupMonthActive2.month = (i3 + i4) - 1;
            if (groupMonthActive2.month > 12) {
                groupMonthActive2.month %= 12;
            }
            groupMonthActive2.actives = new int[(length - i5)];
            System.arraycopy(obj, i5, groupMonthActive2.actives, 0, length - i5);
            groupMonthActiveArr[i4 - 1] = groupMonthActive2;
            return groupMonthActiveArr;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
