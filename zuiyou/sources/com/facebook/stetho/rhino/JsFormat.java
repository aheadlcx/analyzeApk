package com.facebook.stetho.rhino;

import android.support.annotation.NonNull;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class JsFormat {
    private static final Pattern FORMAT_SPECIFIER_PATTERN = Pattern.compile("^%([0-9]+ [$])?([0-9]+)?([.] [0-9]+)?([difs])", 4);

    private static class ArrayCharSequence implements CharSequence {
        @NonNull
        private final char[] array;
        private final int end;
        private final int start;

        private ArrayCharSequence(@NonNull char[] cArr, int i, int i2) {
            this.array = cArr;
            this.start = i;
            this.end = i2;
        }

        public int length() {
            return this.end - this.start;
        }

        public char charAt(int i) {
            return this.array[this.start + i];
        }

        public CharSequence subSequence(int i, int i2) {
            return new ArrayCharSequence(this.array, this.start + i, this.start + i2);
        }

        @NonNull
        private CharSequence substring(int i) {
            return new ArrayCharSequence(this.array, this.start + i, this.start + this.end);
        }

        @NonNull
        public String toString() {
            return new String(this.array, this.start, this.end - this.start);
        }
    }

    JsFormat() {
    }

    @NonNull
    static String parse(@NonNull Object... objArr) {
        boolean[] zArr = new boolean[objArr.length];
        String str = (String) objArr[0];
        zArr[0] = true;
        char[] toCharArray = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        ArrayCharSequence arrayCharSequence = new ArrayCharSequence(toCharArray, 0, toCharArray.length);
        int i = 1;
        int i2 = 0;
        while (i2 < toCharArray.length) {
            int i3;
            char c = toCharArray[i2];
            if (c != '%') {
                stringBuilder.append(c);
                i3 = i2;
                i2 = i;
                i = i3;
            } else {
                Matcher matcher = FORMAT_SPECIFIER_PATTERN.matcher(arrayCharSequence.substring(i2));
                if (matcher.find()) {
                    int i4;
                    Object obj;
                    Object obj2;
                    int groupCount = matcher.groupCount();
                    int i5 = -1;
                    int i6 = -1;
                    int i7 = -1;
                    int i8 = 1;
                    c = '\u0000';
                    while (i8 <= groupCount) {
                        String group = matcher.group(i8);
                        if (group != null) {
                            if (group.equals("")) {
                                i4 = i7;
                                i7 = i6;
                                i6 = i5;
                            } else {
                                if (group.endsWith("$")) {
                                    i3 = i7;
                                    i7 = i6;
                                    i6 = Integer.parseInt(group.substring(0, group.length() - 1));
                                    i4 = i3;
                                } else {
                                    char charAt = group.charAt(0);
                                    if (charAt == '.') {
                                        i4 = Integer.parseInt(group.substring(1));
                                        i7 = i6;
                                        i6 = i5;
                                    } else if (charAt < '0' || charAt > '9') {
                                        c = charAt;
                                        i4 = i7;
                                        i7 = i6;
                                        i6 = i5;
                                    } else {
                                        i6 = i5;
                                        i4 = i7;
                                        i7 = Integer.parseInt(group);
                                    }
                                }
                            }
                        } else {
                            i4 = i7;
                            i7 = i6;
                            i6 = i5;
                        }
                        i8++;
                        i5 = i6;
                        i6 = i7;
                        i7 = i4;
                    }
                    String group2 = matcher.group();
                    if (i5 > zArr.length || (i6 > -1 && i5 == -1)) {
                        i4 = i;
                        obj = null;
                        obj2 = null;
                    } else if (i5 <= zArr.length && i5 > 0) {
                        r5 = objArr[i5];
                        zArr[i5] = true;
                        obj = r5;
                        i4 = i5 + 1;
                        i6 = 1;
                    } else if (i < zArr.length) {
                        r5 = objArr[i];
                        zArr[i] = true;
                        i6 = i + 1;
                        obj = r5;
                        i4 = i6;
                        i6 = 1;
                    } else {
                        i4 = i;
                        obj = null;
                        obj2 = null;
                    }
                    if (obj2 == null) {
                        stringBuilder.append(group2);
                        i = i2 + (group2.length() - 1);
                        i2 = i4;
                    } else {
                        switch (c) {
                            case 'd':
                            case 'i':
                                if (obj instanceof String) {
                                    try {
                                        obj = Long.valueOf(Long.parseLong((String) obj));
                                    } catch (NumberFormatException e) {
                                        obj = "NaN";
                                    }
                                } else if (obj instanceof Number) {
                                    obj = Integer.valueOf(((Number) obj).intValue());
                                } else {
                                    obj = Integer.valueOf(0);
                                }
                                stringBuilder.append(obj);
                                break;
                            case 'f':
                                if (obj instanceof String) {
                                    try {
                                        obj = Double.valueOf(Double.parseDouble((String) obj));
                                    } catch (NumberFormatException e2) {
                                        obj = "NaN";
                                    }
                                } else if (obj instanceof Number) {
                                    obj = Double.valueOf(((Number) obj).doubleValue());
                                } else {
                                    obj = Integer.valueOf(0);
                                }
                                if (i7 > -1 && (obj instanceof Number)) {
                                    obj = String.format(Locale.US, "%." + i7 + "f", new Object[]{obj});
                                }
                                stringBuilder.append(obj);
                                break;
                            default:
                                stringBuilder.append(obj);
                                break;
                        }
                        i = i2 + (group2.length() - 1);
                        i2 = i4;
                    }
                } else {
                    if (i2 + 1 < toCharArray.length && toCharArray[i2 + 1] == '%') {
                        i2++;
                    }
                    stringBuilder.append('%');
                    i3 = i2;
                    i2 = i;
                    i = i3;
                }
            }
            i3 = i + 1;
            i = i2;
            i2 = i3;
        }
        for (i = 0; i < zArr.length; i++) {
            if (!zArr[i]) {
                stringBuilder.append(" ");
                stringBuilder.append(objArr[i]);
            }
        }
        return stringBuilder.toString();
    }
}
