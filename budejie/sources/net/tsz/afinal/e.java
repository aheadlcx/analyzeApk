package net.tsz.afinal;

import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class e {

    public interface a {
        void a(int i);

        void a(String str);
    }

    public static boolean a() {
        try {
            if (new File("/system/app/Superuser.apk").exists() || new File("/system/bin/su").exists()) {
                return true;
            }
            if (a(null, new String[]{"which su"}, new a() {
                public void a(String str) {
                }

                public void a(int i) {
                }
            }, false, true).exitValue() == 0) {
                return true;
            }
            return false;
        } catch (IOException e) {
        } catch (Exception e2) {
        }
    }

    public static Process a(Process process, String[] strArr, a aVar, boolean z, boolean z2) throws Exception {
        if (process == null) {
            if (z) {
                process = Runtime.getRuntime().exec("su");
            } else {
                process = Runtime.getRuntime().exec(IXAdRequestInfo.SCREEN_HEIGHT);
            }
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(process.getOutputStream());
        for (String write : strArr) {
            outputStreamWriter.write(write);
            outputStreamWriter.write("\n");
        }
        outputStreamWriter.flush();
        outputStreamWriter.write("exit\n");
        outputStreamWriter.flush();
        if (z2) {
            char[] cArr = new char[20];
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            while (inputStreamReader.read(cArr) != -1) {
                if (aVar != null) {
                    aVar.a(new String(cArr));
                }
            }
            inputStreamReader = new InputStreamReader(process.getErrorStream());
            while (inputStreamReader.read(cArr) != -1) {
                if (aVar != null) {
                    aVar.a(new String(cArr));
                }
            }
            process.waitFor();
        }
        aVar.a(process.exitValue());
        return process;
    }
}
