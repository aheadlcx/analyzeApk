package qsbk.app.utils;

import android.content.Context;
import com.alipay.sdk.util.h;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.model.CustomButton;

public class CircleVoteBuffer {

    private static class a {
        String a;
        String b;
        int c;

        private a() {
        }
    }

    private static File a(Context context) {
        return new File(context.getCacheDir(), "circle_vote_" + QsbkApp.currentUser.userId);
    }

    private static void b(Context context, List<a> list) {
        try {
            FileWriter fileWriter = new FileWriter(a(context));
            for (int i = 0; i < list.size(); i++) {
                a aVar = (a) list.get(i);
                fileWriter.write(aVar.a);
                fileWriter.write(Constants.ACCEPT_TIME_SEPARATOR_SP);
                fileWriter.write(aVar.b);
                fileWriter.write(Constants.ACCEPT_TIME_SEPARATOR_SP);
                fileWriter.write(String.valueOf(aVar.c));
                fileWriter.write(h.b);
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void a(Context context, a aVar) {
        File a = a(context);
        try {
            FileWriter fileWriter = new FileWriter(a, a.exists());
            fileWriter.write(aVar.a);
            fileWriter.write(Constants.ACCEPT_TIME_SEPARATOR_SP);
            fileWriter.write(aVar.b);
            fileWriter.write(Constants.ACCEPT_TIME_SEPARATOR_SP);
            fileWriter.write(String.valueOf(aVar.c));
            fileWriter.write(h.b);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized List<a> b(Context context) {
        List<a> arrayList;
        synchronized (CircleVoteBuffer.class) {
            arrayList = new ArrayList();
            File a = a(context);
            if (a.exists()) {
                try {
                    String readLine;
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(a));
                    StringBuilder stringBuilder = new StringBuilder();
                    while (true) {
                        readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                    }
                    int i = 0;
                    readLine = stringBuilder.toString();
                    while (true) {
                        int indexOf = readLine.indexOf(44, i);
                        if (indexOf == -1) {
                            break;
                        }
                        a aVar = new a();
                        aVar.a = readLine.substring(i, indexOf).trim();
                        i = indexOf + 1;
                        indexOf = readLine.indexOf(44, i);
                        if (indexOf == -1) {
                            break;
                        }
                        aVar.b = readLine.substring(i, indexOf).trim();
                        indexOf++;
                        i = readLine.indexOf(59, indexOf);
                        if (i == -1) {
                            break;
                        }
                        try {
                            aVar.c = Integer.parseInt(readLine.substring(indexOf, i).trim());
                            arrayList.add(aVar);
                            i = indexOf;
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            i = indexOf;
                        }
                    }
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public static void put(Context context, String str, int i) {
        a aVar = new a();
        aVar.a = str;
        aVar.b = i == 0 ? "a" : CustomButton.POSITION_BOTTOM;
        a(context, aVar);
    }

    public static void retryVoteIfNeed(Context context) {
        if (HttpUtils.isNetworkConnected(context)) {
            List b = b(context);
            if (b != null && b.size() > 0) {
                AsyncTask.THREAD_POOL_EXECUTOR.execute(new h(b, context));
            }
        }
    }
}
