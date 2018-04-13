package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import org.json.JSONObject;

public class aN {
    aN() {
    }

    public JSONObject a() {
        Closeable fileInputStream;
        Throwable e;
        Closeable closeable = null;
        v.a().b().a(Crashlytics.TAG, "Reading cached settings...");
        try {
            JSONObject jSONObject;
            File file = new File(v.a().h(), "com.crashlytics.settings.json");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    jSONObject = new JSONObject(C0003ab.a((InputStream) fileInputStream));
                    closeable = fileInputStream;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        v.a().b().a(Crashlytics.TAG, "Failed to fetch cached settings", e);
                        C0003ab.a(fileInputStream, "Error while closing settings cache file.");
                        return null;
                    } catch (Throwable th) {
                        e = th;
                        closeable = fileInputStream;
                        C0003ab.a(closeable, "Error while closing settings cache file.");
                        throw e;
                    }
                }
            }
            v.a().b().a(Crashlytics.TAG, "No cached settings found.");
            jSONObject = null;
            C0003ab.a(closeable, "Error while closing settings cache file.");
            return jSONObject;
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            v.a().b().a(Crashlytics.TAG, "Failed to fetch cached settings", e);
            C0003ab.a(fileInputStream, "Error while closing settings cache file.");
            return null;
        } catch (Throwable th2) {
            e = th2;
            C0003ab.a(closeable, "Error while closing settings cache file.");
            throw e;
        }
    }

    public void a(long j, JSONObject jSONObject) {
        Throwable e;
        v.a().b().a(Crashlytics.TAG, "Writing settings to cache file...");
        if (jSONObject != null) {
            Closeable closeable = null;
            Closeable fileWriter;
            try {
                jSONObject.put("expires_at", j);
                fileWriter = new FileWriter(new File(v.a().h(), "com.crashlytics.settings.json"));
                try {
                    fileWriter.write(jSONObject.toString());
                    fileWriter.flush();
                    C0003ab.a(fileWriter, "Failed to close settings writer.");
                } catch (Exception e2) {
                    e = e2;
                    try {
                        v.a().b().a(Crashlytics.TAG, "Failed to cache settings", e);
                        C0003ab.a(fileWriter, "Failed to close settings writer.");
                    } catch (Throwable th) {
                        e = th;
                        closeable = fileWriter;
                        C0003ab.a(closeable, "Failed to close settings writer.");
                        throw e;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                fileWriter = null;
                v.a().b().a(Crashlytics.TAG, "Failed to cache settings", e);
                C0003ab.a(fileWriter, "Failed to close settings writer.");
            } catch (Throwable th2) {
                e = th2;
                C0003ab.a(closeable, "Failed to close settings writer.");
                throw e;
            }
        }
    }
}
