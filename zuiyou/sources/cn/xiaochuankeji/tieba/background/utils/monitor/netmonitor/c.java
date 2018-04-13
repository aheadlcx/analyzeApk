package cn.xiaochuankeji.tieba.background.utils.monitor.netmonitor;

import com.iflytek.cloud.SpeechConstant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class c {
    a a;
    private final int b;

    public interface a {
        void a(boolean z, String str);
    }

    private class b {
        final /* synthetic */ c a;
        private String b;

        public String a() {
            return this.b;
        }

        public b(c cVar, String str) {
            this.a = cVar;
            this.b = str;
            Matcher matcher = Pattern.compile("(?<=\\().*?(?=\\))").matcher(str);
            if (matcher.find()) {
                this.b = matcher.group();
            }
        }
    }

    public c(a aVar, int i) {
        this.a = aVar;
        this.b = i;
    }

    private String a(b bVar, boolean z) {
        Process exec;
        IOException e;
        Process process;
        InterruptedException e2;
        Throwable th;
        BufferedReader bufferedReader = null;
        String str = "ping -c ";
        if (z) {
            str = "ping -s 8185 -c  ";
        }
        String str2 = "";
        BufferedReader bufferedReader2;
        try {
            exec = Runtime.getRuntime().exec(str + this.b + " " + bVar.a());
            try {
                bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                str = str2;
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str = str + readLine;
                    } catch (IOException e3) {
                        e = e3;
                        process = exec;
                    } catch (InterruptedException e4) {
                        e2 = e4;
                    }
                }
                bufferedReader2.close();
                exec.waitFor();
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Exception e5) {
                    }
                }
                exec.destroy();
            } catch (IOException e6) {
                bufferedReader2 = null;
                e = e6;
                str = str2;
                process = exec;
                try {
                    e.printStackTrace();
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception e7) {
                        }
                    }
                    process.destroy();
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                    exec = process;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e8) {
                            throw th;
                        }
                    }
                    exec.destroy();
                    throw th;
                }
            } catch (InterruptedException e9) {
                bufferedReader2 = null;
                e2 = e9;
                str = str2;
                try {
                    e2.printStackTrace();
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception e10) {
                        }
                    }
                    exec.destroy();
                    return str;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    exec.destroy();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                exec.destroy();
                throw th;
            }
        } catch (IOException e62) {
            bufferedReader2 = null;
            IOException iOException = e62;
            str = str2;
            process = null;
            e = iOException;
            e.printStackTrace();
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            process.destroy();
            return str;
        } catch (InterruptedException e92) {
            bufferedReader2 = null;
            exec = null;
            e2 = e92;
            str = str2;
            e2.printStackTrace();
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            exec.destroy();
            return str;
        } catch (Throwable th5) {
            th = th5;
            exec = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            exec.destroy();
            throw th;
        }
        return str;
    }

    public void a(String str, boolean z) {
        boolean z2 = true;
        b bVar = new b(this, str);
        StringBuilder stringBuilder = new StringBuilder(256);
        String a = a(bVar, z);
        if (Pattern.compile("(?<=from ).*(?=: icmp_seq=1 ttl=)").matcher(a).find()) {
            stringBuilder.append("\t" + a);
        } else if (a.length() == 0) {
            z2 = false;
        } else {
            stringBuilder.append(SpeechConstant.NET_TIMEOUT);
        }
        this.a.a(z2, f.a(str, stringBuilder.toString()));
    }
}
