package cn.htjyb.c.a;

import android.os.AsyncTask;
import com.izuiyou.a.a.b;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class a extends AsyncTask<Void, Void, Void> {
    private final ArrayList<String> a;
    private final long b;
    private final long c;
    private a d;

    public interface a {
        void a();
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Void) obj);
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void a() {
        this.d = null;
    }

    public a(ArrayList<String> arrayList, long j) {
        this.a = arrayList;
        this.b = System.currentTimeMillis() - j;
        this.c = j;
    }

    protected Void a(Void... voidArr) {
        try {
            if (!(this.a == null || this.a.isEmpty())) {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    a(new File((String) it.next()));
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    private void a(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    a(file2);
                } else if (this.c <= 0) {
                    file2.delete();
                } else if (file2.lastModified() < this.b) {
                    b.c("delete expire file: " + file2.getPath());
                    file2.delete();
                }
            }
        }
    }

    protected void a(Void voidR) {
        super.onPostExecute(voidR);
        if (this.d != null) {
            this.d.a();
        }
    }
}
