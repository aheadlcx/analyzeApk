package cn.xiaochuankeji.tieba.background.utils.monitor.netmonitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class d {
    a a;
    private final String b = "LDNetTraceRoute";

    public interface a {
        void b(String str);

        void b(boolean z);
    }

    private class b {
        final /* synthetic */ d a;
        private String b;

        public String a() {
            return this.b;
        }

        public b(d dVar, String str) {
            this.a = dVar;
            this.b = str;
            Matcher matcher = Pattern.compile("(?<=\\().*?(?=\\))").matcher(str);
            if (matcher.find()) {
                this.b = matcher.group();
            }
        }
    }

    private class c {
        final /* synthetic */ d a;
        private final String b;
        private int c;

        public c(d dVar, String str, int i) {
            this.a = dVar;
            this.b = str;
            this.c = i;
        }

        public String a() {
            return this.b;
        }

        public int b() {
            return this.c;
        }

        public void a(int i) {
            this.c = i;
        }
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(String str) {
        a(new c(this, str, 1));
    }

    private String a(b bVar) {
        Process exec;
        BufferedReader bufferedReader;
        String str;
        IOException e;
        Process process;
        InterruptedException e2;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        String str2 = "";
        try {
            exec = Runtime.getRuntime().exec("ping -c 1 " + bVar.a());
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                str = str2;
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
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
                bufferedReader.close();
                exec.waitFor();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e5) {
                    }
                }
                exec.destroy();
            } catch (IOException e6) {
                bufferedReader = null;
                e = e6;
                str = str2;
                process = exec;
                try {
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e7) {
                        }
                    }
                    process.destroy();
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    exec = process;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception e8) {
                            throw th;
                        }
                    }
                    exec.destroy();
                    throw th;
                }
            } catch (InterruptedException e9) {
                bufferedReader = null;
                e2 = e9;
                str = str2;
                try {
                    e2.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e10) {
                        }
                    }
                    exec.destroy();
                    return str;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    exec.destroy();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                exec.destroy();
                throw th;
            }
        } catch (IOException e62) {
            bufferedReader = null;
            IOException iOException = e62;
            str = str2;
            process = null;
            e = iOException;
            e.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            process.destroy();
            return str;
        } catch (InterruptedException e92) {
            bufferedReader = null;
            exec = null;
            e2 = e92;
            str = str2;
            e2.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            exec.destroy();
            return str;
        } catch (Throwable th5) {
            th = th5;
            exec = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            exec.destroy();
            throw th;
        }
        return str;
    }

    private void a(c cVar) {
        Process exec;
        BufferedReader bufferedReader;
        IOException e;
        BufferedReader bufferedReader2;
        Process process;
        InterruptedException e2;
        Throwable th;
        BufferedReader bufferedReader3 = null;
        boolean z = false;
        Pattern compile = Pattern.compile("(?<=From )(?:[0-9]{1,3}\\.){3}[0-9]{1,3}");
        Pattern compile2 = Pattern.compile("(?<=from ).*(?=: icmp_seq=1 ttl=)");
        Pattern compile3 = Pattern.compile("(?<=time=).*?ms");
        boolean z2 = false;
        Process process2 = null;
        boolean z3 = true;
        while (!z2) {
            try {
                if (cVar.b() >= 30) {
                    break;
                }
                String str = "";
                exec = Runtime.getRuntime().exec("ping -c 1 -t " + cVar.b() + " " + cVar.a());
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                    Object obj = str;
                    while (true) {
                        try {
                            str = bufferedReader.readLine();
                            if (str == null) {
                                break;
                            }
                            obj = obj + str;
                        } catch (IOException e3) {
                            e = e3;
                            bufferedReader2 = bufferedReader;
                            process = exec;
                        } catch (InterruptedException e4) {
                            e2 = e4;
                        }
                    }
                    bufferedReader.close();
                    exec.waitFor();
                    Matcher matcher = compile.matcher(obj);
                    StringBuilder stringBuilder = new StringBuilder(256);
                    String group;
                    if (matcher.find()) {
                        group = matcher.group();
                        CharSequence a = a(new b(this, group));
                        if (a.length() == 0) {
                            stringBuilder.append("unknown host or network error\n");
                            z2 = true;
                            z3 = false;
                        } else {
                            matcher = compile3.matcher(a);
                            if (matcher.find()) {
                                str = matcher.group();
                                stringBuilder.append(cVar.b());
                                stringBuilder.append("   ");
                                stringBuilder.append(group);
                                stringBuilder.append("   ");
                                stringBuilder.append(str);
                                stringBuilder.append("\t");
                            } else {
                                stringBuilder.append(cVar.b());
                                stringBuilder.append("   ");
                                stringBuilder.append(group);
                                stringBuilder.append("   timeout\n");
                            }
                            this.a.b(stringBuilder.toString());
                            cVar.a(cVar.b() + 1);
                        }
                    } else {
                        matcher = compile2.matcher(obj);
                        if (matcher.find()) {
                            String group2 = matcher.group();
                            Matcher matcher2 = compile3.matcher(obj);
                            if (matcher2.find()) {
                                group = matcher2.group();
                                stringBuilder.append(cVar.b());
                                stringBuilder.append("   ");
                                stringBuilder.append(group2);
                                stringBuilder.append("   ");
                                stringBuilder.append(group);
                                stringBuilder.append("\t");
                                this.a.b(stringBuilder.toString());
                            }
                            z2 = true;
                        } else if (obj.length() == 0) {
                            stringBuilder.append("unknown host or network error\t");
                            z2 = true;
                            z3 = false;
                        } else {
                            stringBuilder.append(cVar.b());
                            stringBuilder.append("   timeout\n");
                            cVar.a(cVar.b() + 1);
                            this.a.b(stringBuilder.toString());
                        }
                    }
                    bufferedReader3 = bufferedReader;
                    process2 = exec;
                } catch (IOException e5) {
                    e = e5;
                    bufferedReader2 = bufferedReader3;
                    process = exec;
                } catch (InterruptedException e6) {
                    e2 = e6;
                    bufferedReader = bufferedReader3;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader3;
                }
            } catch (IOException e7) {
                e = e7;
                bufferedReader2 = bufferedReader3;
                process = process2;
            } catch (InterruptedException e8) {
                e2 = e8;
                exec = process2;
                bufferedReader = bufferedReader3;
            } catch (Throwable th3) {
                th = th3;
                exec = process2;
                bufferedReader = bufferedReader3;
            }
        }
        if (bufferedReader3 != null) {
            try {
                bufferedReader3.close();
            } catch (Exception e9) {
                z = z3;
            }
        }
        process2.destroy();
        z = z3;
        this.a.b(z);
        try {
            e2.printStackTrace();
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e10) {
                }
            }
            exec.destroy();
            this.a.b(z);
        } catch (Throwable th4) {
            th = th4;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e11) {
                    throw th;
                }
            }
            exec.destroy();
            throw th;
        }
        try {
            e.printStackTrace();
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (Exception e12) {
                }
            }
            process.destroy();
            this.a.b(z);
        } catch (Throwable th5) {
            th = th5;
            bufferedReader = bufferedReader2;
            exec = process;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            exec.destroy();
            throw th;
        }
    }
}
