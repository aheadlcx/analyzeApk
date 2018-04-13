package com.budejie.www.activity.video;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class i {
    public static boolean a = true;
    public static boolean b = false;

    public static String a() {
        Map a = h.a("cat /proc/cpuinfo");
        if (a == null) {
            return "Sorry, Run Cmd Failure !!!";
        }
        InputStream inputStream = (InputStream) a.get("input");
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String str = "";
        while (true) {
            try {
                str = bufferedReader.readLine();
                if (str == null) {
                    break;
                } else if (str.indexOf("Features") != -1) {
                    stringBuilder.append(str);
                }
            } catch (Exception e) {
                String str2 = "Read InputStream Failure !!!";
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e3) {
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                    }
                }
                return str2;
            } catch (Throwable th) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e5) {
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e6) {
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e7) {
                    }
                }
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e8) {
            }
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException e9) {
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e10) {
            }
        }
        return stringBuilder.toString();
    }

    public static String b() throws Exception {
        Map a = h.a("cat /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
        if (a == null) {
            return "Sorry, Run Cmd Failure !!!";
        }
        InputStream inputStream = (InputStream) a.get("input");
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String str = "";
        while (true) {
            try {
                str = bufferedReader.readLine();
                if (str == null) {
                    break;
                }
                stringBuilder.append(str);
            } catch (Exception e) {
                String str2 = "Read InputStream Failure !!!";
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e3) {
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                    }
                }
                return str2;
            } catch (Throwable th) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e5) {
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e6) {
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e7) {
                    }
                }
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e8) {
            }
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException e9) {
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e10) {
            }
        }
        return stringBuilder.toString();
    }

    public static boolean c() {
        String a = a();
        if (a.indexOf("neon") == -1 && a.indexOf("vfp") == -1) {
            return false;
        }
        return true;
    }

    public static boolean d() {
        if (a().indexOf("neon") != -1) {
            return true;
        }
        return false;
    }

    public static int e() {
        int parseInt;
        try {
            parseInt = Integer.parseInt(b());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            parseInt = 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            parseInt = 0;
        }
        if (parseInt < 800000) {
            return 0;
        }
        if (parseInt < 800000 || parseInt > 1100000) {
            return 2;
        }
        return 1;
    }

    public static boolean f() {
        if (b) {
            return true;
        }
        if (e() == 0 || a || !c()) {
            return false;
        }
        return true;
    }
}
