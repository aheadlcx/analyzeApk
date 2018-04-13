package qsbk.app.utils;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.Remark;

public class RemarkManager {
    public static final long REFRESH_TIME = 1800000;
    public static ArrayList<Remark> REMARKS = new ArrayList();
    protected static HashMap<String, RemarkManager> a = new HashMap();

    private RemarkManager() {
    }

    public static synchronized RemarkManager getRemarkManager() {
        RemarkManager remarkManager;
        synchronized (RemarkManager.class) {
            if (QsbkApp.currentUser == null) {
                remarkManager = null;
            } else {
                remarkManager = (RemarkManager) a.get(QsbkApp.currentUser.userId);
                if (remarkManager == null) {
                    remarkManager = new RemarkManager();
                    a.put(QsbkApp.currentUser.userId, remarkManager);
                }
            }
        }
        return remarkManager;
    }

    private static File a() {
        String sDPath = DeviceUtils.getSDPath();
        if (sDPath == null || sDPath.length() == 0) {
            sDPath = QsbkApp.getInstance().getFilesDir().getAbsolutePath();
        }
        Object obj = QsbkApp.currentUser == null ? "" : QsbkApp.currentUser.userId;
        if (TextUtils.isEmpty(obj)) {
            return null;
        }
        File file = new File(sDPath + File.separator + "qsbk/data");
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, Integer.toHexString("Remark".hashCode()) + "_" + obj);
    }

    private static void b(JSONObject jSONObject) {
        File a = a();
        if (a != null) {
            if (jSONObject == null) {
                a.delete();
                return;
            }
            try {
                FileWriter fileWriter = new FileWriter(a);
                fileWriter.write(jSONObject.toString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static JSONObject b() {
        File a = a();
        if (a == null || !a.exists()) {
            return null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(a));
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuilder.append(readLine);
                } else {
                    bufferedReader.close();
                    return new JSONObject(stringBuilder.toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (JSONException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static void deleteFile() {
        File a = a();
        if (a != null && a.exists()) {
            a.delete();
        }
    }

    public static ArrayList<Remark> parseRemarks(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("data");
        ArrayList<Remark> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Remark parseJson = Remark.parseJson(jSONArray.getJSONObject(i));
            if (parseJson != null) {
                arrayList.add(parseJson);
            }
        }
        return arrayList;
    }

    public static JSONObject remarksToJson(ArrayList<Remark> arrayList) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                JSONObject toJson = Remark.toJson((Remark) it.next());
                if (toJson != null) {
                    jSONArray.put(toJson);
                }
            }
            jSONObject.put("data", jSONArray);
            jSONObject.put(IndexEntry.COLUMN_NAME_DATE, new Date().getTime());
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getRemark(String str) {
        if (str == null) {
            return "";
        }
        Iterator it = REMARKS.iterator();
        while (it.hasNext()) {
            Remark remark = (Remark) it.next();
            if (TextUtils.equals(remark.uid, str)) {
                return remark.remark;
            }
        }
        return "";
    }

    public static String replaceFirst(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str3 == null) {
            return str;
        }
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return str;
        }
        return str3 + str.substring(indexOf + str2.length());
    }

    public void init() {
        if (REMARKS.size() > 0) {
            REMARKS.clear();
        }
        JSONObject b = b();
        if (b == null || (b != null && b.optJSONArray("data").length() == 0)) {
            loadRemarksFromServer();
            return;
        }
        try {
            if (new Date().getTime() - b.optLong(IndexEntry.COLUMN_NAME_DATE, 0) > 1800000) {
                loadRemarksFromServer();
            } else {
                REMARKS = parseRemarks(b);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void loadRemarksFromServer() {
        new SimpleHttpTask(Constants.USER_REMARK, new as(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void loadFromLocal() {
        JSONObject b = b();
        if (b != null && b.optJSONArray("data").length() > 0) {
            try {
                REMARKS = parseRemarks(b);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadRemarkToServer(Remark remark, RemarkManager$UploadCallBack remarkManager$UploadCallBack) {
        CharSequence toJsonForServer = Remark.toJsonForServer(remark);
        if (!TextUtils.isEmpty(toJsonForServer) && HttpUtils.netIsAvailable()) {
            Map hashMap = new HashMap();
            hashMap.put("nicks", toJsonForServer);
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.USER_REMARK, new at(this, remark, remarkManager$UploadCallBack));
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void insertOrUpdate(Remark remark, boolean z) {
        remark.state = z ? 1 : 2;
        remark.t = new Date().getTime();
        if (REMARKS.contains(remark)) {
            REMARKS.set(REMARKS.indexOf(remark), remark);
        } else {
            REMARKS.add(remark);
        }
        b(remarksToJson(REMARKS));
    }

    public void delete(Remark remark) {
        REMARKS.remove(remark);
    }

    public void uploadedRemark(Remark remark, RemarkManager$UploadCallBack remarkManager$UploadCallBack) {
        uploadRemarkToServer(remark, remarkManager$UploadCallBack);
    }

    public ArrayList<String> getAllContainsTextUids(String str) {
        ArrayList<String> arrayList = new ArrayList();
        Iterator it = REMARKS.iterator();
        while (it.hasNext()) {
            Remark remark = (Remark) it.next();
            if (remark.remark.contains(str)) {
                arrayList.add(remark.uid);
            }
        }
        return arrayList;
    }
}
