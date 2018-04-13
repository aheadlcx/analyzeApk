package bolts;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AggregateException extends Exception {
    private List<Throwable> a;

    public AggregateException(String str, Throwable[] thArr) {
        this(str, Arrays.asList(thArr));
    }

    public AggregateException(String str, List<? extends Throwable> list) {
        Throwable th = (list == null || list.size() <= 0) ? null : (Throwable) list.get(0);
        super(str, th);
        this.a = Collections.unmodifiableList(list);
    }

    public AggregateException(List<? extends Throwable> list) {
        this("There were multiple errors.", (List) list);
    }

    public List<Throwable> getInnerThrowables() {
        return this.a;
    }

    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        int i = -1;
        for (Throwable th : this.a) {
            printStream.append("\n");
            printStream.append("  Inner throwable #");
            i++;
            printStream.append(Integer.toString(i));
            printStream.append(": ");
            th.printStackTrace(printStream);
            printStream.append("\n");
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        int i = -1;
        for (Throwable th : this.a) {
            printWriter.append("\n");
            printWriter.append("  Inner throwable #");
            i++;
            printWriter.append(Integer.toString(i));
            printWriter.append(": ");
            th.printStackTrace(printWriter);
            printWriter.append("\n");
        }
    }

    @Deprecated
    public List<Exception> getErrors() {
        List<Exception> arrayList = new ArrayList();
        if (this.a == null) {
            return arrayList;
        }
        for (Throwable th : this.a) {
            if (th instanceof Exception) {
                arrayList.add((Exception) th);
            } else {
                arrayList.add(new Exception(th));
            }
        }
        return arrayList;
    }

    @Deprecated
    public Throwable[] getCauses() {
        return (Throwable[]) this.a.toArray(new Throwable[this.a.size()]);
    }
}
