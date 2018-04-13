package com.umeng.commonsdk.internal.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class e {

    public enum a {
        check_su_binary(new String[]{"/system/xbin/which", "su"});
        
        String[] b;

        private a(String[] strArr) {
            this.b = strArr;
        }
    }

    public ArrayList a(a aVar) {
        ArrayList arrayList = new ArrayList();
        try {
            Process exec = Runtime.getRuntime().exec(aVar.b);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(exec.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        return arrayList;
                    }
                    arrayList.add(readLine);
                } catch (Exception e) {
                    return arrayList;
                }
            }
        } catch (Exception e2) {
            return null;
        }
    }
}
