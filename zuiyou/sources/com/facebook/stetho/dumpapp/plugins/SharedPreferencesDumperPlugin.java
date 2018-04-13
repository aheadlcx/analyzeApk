package com.facebook.stetho.dumpapp.plugins;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.facebook.stetho.dumpapp.DumpUsageException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import java.io.File;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SharedPreferencesDumperPlugin implements DumperPlugin {
    private static final String NAME = "prefs";
    private static final String XML_SUFFIX = ".xml";
    private final Context mAppContext;

    private enum Type {
        BOOLEAN("boolean"),
        INT("int"),
        LONG("long"),
        FLOAT("float"),
        STRING("string"),
        SET("set");
        
        private final String name;

        private Type(String str) {
            this.name = str;
        }

        @Nullable
        public static Type of(String str) {
            for (Type type : values()) {
                if (type.name.equals(str)) {
                    return type;
                }
            }
            return null;
        }

        public static StringBuilder appendNamesList(StringBuilder stringBuilder, String str) {
            Object obj = 1;
            for (Type type : values()) {
                if (obj != null) {
                    obj = null;
                } else {
                    stringBuilder.append(str);
                }
                stringBuilder.append(type.name);
            }
            return stringBuilder;
        }
    }

    public SharedPreferencesDumperPlugin(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    public String getName() {
        return NAME;
    }

    public void dump(DumperContext dumperContext) throws DumpUsageException {
        PrintStream stdout = dumperContext.getStdout();
        List argsAsList = dumperContext.getArgsAsList();
        String str = argsAsList.isEmpty() ? "" : (String) argsAsList.remove(0);
        if (str.equals(SharePatchInfo.FINGER_PRINT)) {
            doPrint(stdout, argsAsList);
        } else if (str.equals("write")) {
            doWrite(argsAsList);
        } else {
            doUsage(stdout);
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    private void doWrite(List<String> list) throws DumpUsageException {
        String str = "Usage: prefs write <path> <key> <type> <value>, where type is one of: ";
        Iterator it = list.iterator();
        String nextArg = nextArg(it, "Expected <path>");
        String nextArg2 = nextArg(it, "Expected <key>");
        Type of = Type.of(nextArg(it, "Expected <type>"));
        if (of == null) {
            throw new DumpUsageException(", ");
        }
        Editor edit = getSharedPreferences(nextArg).edit();
        switch (SharedPreferencesDumperPlugin$1.$SwitchMap$com$facebook$stetho$dumpapp$plugins$SharedPreferencesDumperPlugin$Type[of.ordinal()]) {
            case 1:
                edit.putBoolean(nextArg2, Boolean.valueOf(nextArgValue(it)).booleanValue());
                break;
            case 2:
                edit.putInt(nextArg2, Integer.valueOf(nextArgValue(it)).intValue());
                break;
            case 3:
                edit.putLong(nextArg2, Long.valueOf(nextArgValue(it)).longValue());
                break;
            case 4:
                edit.putFloat(nextArg2, Float.valueOf(nextArgValue(it)).floatValue());
                break;
            case 5:
                edit.putString(nextArg2, nextArgValue(it));
                break;
            case 6:
                putStringSet(edit, nextArg2, it);
                break;
        }
        edit.commit();
    }

    @Nonnull
    private static String nextArg(Iterator<String> it, String str) throws DumpUsageException {
        if (it.hasNext()) {
            return (String) it.next();
        }
        throw new DumpUsageException(str);
    }

    @Nonnull
    private static String nextArgValue(Iterator<String> it) throws DumpUsageException {
        return nextArg(it, "Expected <value>");
    }

    @TargetApi(11)
    private static void putStringSet(Editor editor, String str, Iterator<String> it) {
        Set hashSet = new HashSet();
        while (it.hasNext()) {
            hashSet.add(it.next());
        }
        editor.putStringSet(str, hashSet);
    }

    private void doPrint(PrintStream printStream, List<String> list) {
        printRecursive(printStream, this.mAppContext.getApplicationInfo().dataDir + "/shared_prefs", "", list.isEmpty() ? "" : (String) list.get(0), list.size() > 1 ? (String) list.get(1) : "");
    }

    private void printRecursive(PrintStream printStream, String str, String str2, String str3, String str4) {
        File file = new File(str, str2);
        if (file.isFile()) {
            if (str2.endsWith(XML_SUFFIX)) {
                printFile(printStream, str2.substring(0, str2.length() - XML_SUFFIX.length()), str4);
            }
        } else if (file.isDirectory()) {
            String[] list = file.list();
            if (list != null) {
                int i = 0;
                while (i < list.length) {
                    String str5 = TextUtils.isEmpty(str2) ? list[i] : str2 + File.separator + list[i];
                    if (str5.startsWith(str3)) {
                        printRecursive(printStream, str, str5, str3, str4);
                    }
                    i++;
                }
            }
        }
    }

    private void printFile(PrintStream printStream, String str, String str2) {
        printStream.println(str + ":");
        for (Entry entry : getSharedPreferences(str).getAll().entrySet()) {
            if (((String) entry.getKey()).startsWith(str2)) {
                printStream.println("  " + ((String) entry.getKey()) + " = " + entry.getValue());
            }
        }
    }

    private void doUsage(PrintStream printStream) {
        String str = "dumpapp prefs";
        str = "Usage: dumpapp prefs ";
        printStream.println(str + "<command> [command-options]");
        printStream.println(str + "print [pathPrefix [keyPrefix]]");
        printStream.println(Type.appendNamesList(new StringBuilder("       dumpapp prefs ").append("write <path> <key> <"), "|").append("> <value>"));
        printStream.println();
        printStream.println("dumpapp prefs print: Print all matching values from the shared preferences");
        printStream.println();
        printStream.println("dumpapp prefs write: Writes a value to the shared preferences");
    }

    private SharedPreferences getSharedPreferences(String str) {
        return this.mAppContext.getSharedPreferences(str, 4);
    }
}
