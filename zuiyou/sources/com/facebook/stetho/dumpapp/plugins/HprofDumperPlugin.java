package com.facebook.stetho.dumpapp.plugins;

import android.content.Context;
import android.os.Debug;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.dumpapp.DumpException;
import com.facebook.stetho.dumpapp.DumpUsageException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;

public class HprofDumperPlugin implements DumperPlugin {
    private static final String NAME = "hprof";
    private final Context mContext;

    public HprofDumperPlugin(Context context) {
        this.mContext = context;
    }

    public String getName() {
        return NAME;
    }

    public void dump(DumperContext dumperContext) throws DumpException {
        OutputStream stdout = dumperContext.getStdout();
        Iterator it = dumperContext.getArgsAsList().iterator();
        String str = it.hasNext() ? (String) it.next() : null;
        if (str == null) {
            usage(stdout);
        } else if ("-".equals(str)) {
            handlePipeOutput(stdout);
        } else {
            File file = new File(str);
            if (!file.isAbsolute()) {
                file = this.mContext.getFileStreamPath(str);
            }
            writeHprof(file);
            stdout.println("Wrote to " + file);
        }
    }

    private void handlePipeOutput(OutputStream outputStream) throws DumpException {
        File fileStreamPath = this.mContext.getFileStreamPath("hprof-dump.hprof");
        try {
            writeHprof(fileStreamPath);
            InputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(fileStreamPath);
                Util.copy(fileInputStream, outputStream, new byte[2048]);
                fileInputStream.close();
            } catch (IOException e) {
                throw new DumpException("Failure copying " + fileStreamPath + " to dumper output");
            } catch (Throwable th) {
                fileInputStream.close();
            }
        } finally {
            if (fileStreamPath.exists()) {
                fileStreamPath.delete();
            }
        }
    }

    private void writeHprof(File file) throws DumpException {
        try {
            truncateAndDeleteFile(file);
            Debug.dumpHprofData(file.getAbsolutePath());
        } catch (IOException e) {
            throw new DumpException("Failure writing to " + file + ": " + e.getMessage());
        }
    }

    private static void truncateAndDeleteFile(File file) throws IOException {
        new FileOutputStream(file).close();
        if (!file.delete()) {
            throw new IOException("Failed to delete " + file);
        }
    }

    private void usage(PrintStream printStream) throws DumpUsageException {
        printStream.println("Usage: dumpapp hprof [ path ]");
        printStream.println("Dump HPROF memory usage data from the running application.");
        printStream.println();
        printStream.println("Where path can be any of:");
        printStream.println("  -           Output directly to stdout");
        printStream.println("  <path>      Full path to a writable file on the device");
        printStream.println("  <filename>  Relative filename that will be stored in the app internal storage");
        throw new DumpUsageException("Missing path");
    }
}
