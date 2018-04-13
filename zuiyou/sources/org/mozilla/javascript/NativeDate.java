package org.mozilla.javascript;

import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

final class NativeDate extends IdScriptableObject {
    static final /* synthetic */ boolean $assertionsDisabled = (!NativeDate.class.desiredAssertionStatus());
    private static final int ConstructorId_UTC = -1;
    private static final int ConstructorId_now = -3;
    private static final int ConstructorId_parse = -2;
    private static final Object DATE_TAG = "Date";
    private static final double HalfTimeDomain = 8.64E15d;
    private static final double HoursPerDay = 24.0d;
    private static final int Id_constructor = 1;
    private static final int Id_getDate = 17;
    private static final int Id_getDay = 19;
    private static final int Id_getFullYear = 13;
    private static final int Id_getHours = 21;
    private static final int Id_getMilliseconds = 27;
    private static final int Id_getMinutes = 23;
    private static final int Id_getMonth = 15;
    private static final int Id_getSeconds = 25;
    private static final int Id_getTime = 11;
    private static final int Id_getTimezoneOffset = 29;
    private static final int Id_getUTCDate = 18;
    private static final int Id_getUTCDay = 20;
    private static final int Id_getUTCFullYear = 14;
    private static final int Id_getUTCHours = 22;
    private static final int Id_getUTCMilliseconds = 28;
    private static final int Id_getUTCMinutes = 24;
    private static final int Id_getUTCMonth = 16;
    private static final int Id_getUTCSeconds = 26;
    private static final int Id_getYear = 12;
    private static final int Id_setDate = 39;
    private static final int Id_setFullYear = 43;
    private static final int Id_setHours = 37;
    private static final int Id_setMilliseconds = 31;
    private static final int Id_setMinutes = 35;
    private static final int Id_setMonth = 41;
    private static final int Id_setSeconds = 33;
    private static final int Id_setTime = 30;
    private static final int Id_setUTCDate = 40;
    private static final int Id_setUTCFullYear = 44;
    private static final int Id_setUTCHours = 38;
    private static final int Id_setUTCMilliseconds = 32;
    private static final int Id_setUTCMinutes = 36;
    private static final int Id_setUTCMonth = 42;
    private static final int Id_setUTCSeconds = 34;
    private static final int Id_setYear = 45;
    private static final int Id_toDateString = 4;
    private static final int Id_toGMTString = 8;
    private static final int Id_toISOString = 46;
    private static final int Id_toJSON = 47;
    private static final int Id_toLocaleDateString = 7;
    private static final int Id_toLocaleString = 5;
    private static final int Id_toLocaleTimeString = 6;
    private static final int Id_toSource = 9;
    private static final int Id_toString = 2;
    private static final int Id_toTimeString = 3;
    private static final int Id_toUTCString = 8;
    private static final int Id_valueOf = 10;
    private static double LocalTZA = 0.0d;
    private static final int MAXARGS = 7;
    private static final int MAX_PROTOTYPE_ID = 47;
    private static final double MinutesPerDay = 1440.0d;
    private static final double MinutesPerHour = 60.0d;
    private static final double SecondsPerDay = 86400.0d;
    private static final double SecondsPerHour = 3600.0d;
    private static final double SecondsPerMinute = 60.0d;
    private static final String js_NaN_date_str = "Invalid Date";
    private static DateFormat localeDateFormatter = null;
    private static DateFormat localeDateTimeFormatter = null;
    private static DateFormat localeTimeFormatter = null;
    private static final double msPerDay = 8.64E7d;
    private static final double msPerHour = 3600000.0d;
    private static final double msPerMinute = 60000.0d;
    private static final double msPerSecond = 1000.0d;
    static final long serialVersionUID = -8307438915861678966L;
    private static TimeZone thisTimeZone;
    private static DateFormat timeZoneFormatter;
    private double date;

    static void init(Scriptable scriptable, boolean z) {
        NativeDate nativeDate = new NativeDate();
        nativeDate.date = ScriptRuntime.NaN;
        nativeDate.exportAsJSClass(47, scriptable, z);
    }

    private NativeDate() {
        if (thisTimeZone == null) {
            thisTimeZone = TimeZone.getDefault();
            LocalTZA = (double) thisTimeZone.getRawOffset();
        }
    }

    public String getClassName() {
        return "Date";
    }

    public Object getDefaultValue(Class<?> cls) {
        if (cls == null) {
            cls = ScriptRuntime.StringClass;
        }
        return super.getDefaultValue(cls);
    }

    double getJSTimeValue() {
        return this.date;
    }

    protected void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        addIdFunctionProperty(idFunctionObject, DATE_TAG, -3, "now", 0);
        addIdFunctionProperty(idFunctionObject, DATE_TAG, -2, "parse", 1);
        addIdFunctionProperty(idFunctionObject, DATE_TAG, -1, "UTC", 7);
        super.fillConstructorProperties(idFunctionObject);
    }

    protected void initPrototypeId(int i) {
        String str;
        int i2 = 0;
        switch (i) {
            case 1:
                i2 = 7;
                str = "constructor";
                break;
            case 2:
                str = "toString";
                break;
            case 3:
                str = "toTimeString";
                break;
            case 4:
                str = "toDateString";
                break;
            case 5:
                str = "toLocaleString";
                break;
            case 6:
                str = "toLocaleTimeString";
                break;
            case 7:
                str = "toLocaleDateString";
                break;
            case 8:
                str = "toUTCString";
                break;
            case 9:
                str = "toSource";
                break;
            case 10:
                str = "valueOf";
                break;
            case 11:
                str = "getTime";
                break;
            case 12:
                str = "getYear";
                break;
            case 13:
                str = "getFullYear";
                break;
            case 14:
                str = "getUTCFullYear";
                break;
            case 15:
                str = "getMonth";
                break;
            case 16:
                str = "getUTCMonth";
                break;
            case 17:
                str = "getDate";
                break;
            case 18:
                str = "getUTCDate";
                break;
            case 19:
                str = "getDay";
                break;
            case 20:
                str = "getUTCDay";
                break;
            case 21:
                str = "getHours";
                break;
            case 22:
                str = "getUTCHours";
                break;
            case 23:
                str = "getMinutes";
                break;
            case 24:
                str = "getUTCMinutes";
                break;
            case 25:
                str = "getSeconds";
                break;
            case 26:
                str = "getUTCSeconds";
                break;
            case 27:
                str = "getMilliseconds";
                break;
            case 28:
                str = "getUTCMilliseconds";
                break;
            case 29:
                str = "getTimezoneOffset";
                break;
            case 30:
                str = "setTime";
                i2 = 1;
                break;
            case 31:
                str = "setMilliseconds";
                i2 = 1;
                break;
            case 32:
                str = "setUTCMilliseconds";
                i2 = 1;
                break;
            case 33:
                str = "setSeconds";
                i2 = 2;
                break;
            case 34:
                str = "setUTCSeconds";
                i2 = 2;
                break;
            case 35:
                str = "setMinutes";
                i2 = 3;
                break;
            case 36:
                str = "setUTCMinutes";
                i2 = 3;
                break;
            case 37:
                str = "setHours";
                i2 = 4;
                break;
            case 38:
                str = "setUTCHours";
                i2 = 4;
                break;
            case 39:
                str = "setDate";
                i2 = 1;
                break;
            case 40:
                str = "setUTCDate";
                i2 = 1;
                break;
            case 41:
                str = "setMonth";
                i2 = 2;
                break;
            case 42:
                str = "setUTCMonth";
                i2 = 2;
                break;
            case 43:
                str = "setFullYear";
                i2 = 3;
                break;
            case 44:
                str = "setUTCFullYear";
                i2 = 3;
                break;
            case 45:
                str = "setYear";
                i2 = 1;
                break;
            case 46:
                str = "toISOString";
                break;
            case 47:
                str = "toJSON";
                i2 = 1;
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(DATE_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(DATE_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        double doubleValue;
        switch (methodId) {
            case -3:
                return ScriptRuntime.wrapNumber(now());
            case -2:
                return ScriptRuntime.wrapNumber(date_parseString(ScriptRuntime.toString(objArr, 0)));
            case -1:
                return ScriptRuntime.wrapNumber(jsStaticFunction_UTC(objArr));
            case 1:
                if (scriptable2 != null) {
                    return date_format(now(), 2);
                }
                return jsConstructor(objArr);
            case 47:
                String str = "toISOString";
                Scriptable toObject = ScriptRuntime.toObject(context, scriptable, scriptable2);
                Object toPrimitive = ScriptRuntime.toPrimitive(toObject, ScriptRuntime.NumberClass);
                if (toPrimitive instanceof Number) {
                    doubleValue = ((Number) toPrimitive).doubleValue();
                    if (doubleValue != doubleValue || Double.isInfinite(doubleValue)) {
                        return null;
                    }
                }
                toPrimitive = ScriptableObject.getProperty(toObject, "toISOString");
                if (toPrimitive == NOT_FOUND) {
                    throw ScriptRuntime.typeError2("msg.function.not.found.in", "toISOString", ScriptRuntime.toString(toObject));
                } else if (toPrimitive instanceof Callable) {
                    toPrimitive = ((Callable) toPrimitive).call(context, scriptable, toObject, ScriptRuntime.emptyArgs);
                    if (ScriptRuntime.isPrimitive(toPrimitive)) {
                        return toPrimitive;
                    }
                    throw ScriptRuntime.typeError1("msg.toisostring.must.return.primitive", ScriptRuntime.toString(toPrimitive));
                } else {
                    throw ScriptRuntime.typeError3("msg.isnt.function.in", "toISOString", ScriptRuntime.toString(toObject), ScriptRuntime.toString(toPrimitive));
                }
            default:
                if (scriptable2 instanceof NativeDate) {
                    NativeDate nativeDate = (NativeDate) scriptable2;
                    double d = nativeDate.date;
                    switch (methodId) {
                        case 2:
                        case 3:
                        case 4:
                            if (d == d) {
                                return date_format(d, methodId);
                            }
                            return js_NaN_date_str;
                        case 5:
                        case 6:
                        case 7:
                            if (d == d) {
                                return toLocale_helper(d, methodId);
                            }
                            return js_NaN_date_str;
                        case 8:
                            if (d == d) {
                                return js_toUTCString(d);
                            }
                            return js_NaN_date_str;
                        case 9:
                            return "(new Date(" + ScriptRuntime.toString(d) + "))";
                        case 10:
                        case 11:
                            return ScriptRuntime.wrapNumber(d);
                        case 12:
                        case 13:
                        case 14:
                            if (d == d) {
                                if (methodId != 14) {
                                    d = LocalTime(d);
                                }
                                d = (double) YearFromTime(d);
                                if (methodId == 12) {
                                    if (!context.hasFeature(1)) {
                                        d -= 1900.0d;
                                    } else if (1900.0d <= d && d < 2000.0d) {
                                        d -= 1900.0d;
                                    }
                                }
                            }
                            return ScriptRuntime.wrapNumber(d);
                        case 15:
                        case 16:
                            if (d == d) {
                                if (methodId == 15) {
                                    d = LocalTime(d);
                                }
                                d = (double) MonthFromTime(d);
                            }
                            return ScriptRuntime.wrapNumber(d);
                        case 17:
                        case 18:
                            if (d == d) {
                                if (methodId == 17) {
                                    d = LocalTime(d);
                                }
                                d = (double) DateFromTime(d);
                            }
                            return ScriptRuntime.wrapNumber(d);
                        case 19:
                        case 20:
                            if (d == d) {
                                if (methodId == 19) {
                                    d = LocalTime(d);
                                }
                                d = (double) WeekDay(d);
                            }
                            return ScriptRuntime.wrapNumber(d);
                        case 21:
                        case 22:
                            if (d == d) {
                                if (methodId == 21) {
                                    d = LocalTime(d);
                                }
                                d = (double) HourFromTime(d);
                            }
                            return ScriptRuntime.wrapNumber(d);
                        case 23:
                        case 24:
                            if (d == d) {
                                if (methodId == 23) {
                                    d = LocalTime(d);
                                }
                                d = (double) MinFromTime(d);
                            }
                            return ScriptRuntime.wrapNumber(d);
                        case 25:
                        case 26:
                            if (d == d) {
                                if (methodId == 25) {
                                    d = LocalTime(d);
                                }
                                d = (double) SecFromTime(d);
                            }
                            return ScriptRuntime.wrapNumber(d);
                        case 27:
                        case 28:
                            if (d == d) {
                                if (methodId == 27) {
                                    d = LocalTime(d);
                                }
                                d = (double) msFromTime(d);
                            }
                            return ScriptRuntime.wrapNumber(d);
                        case 29:
                            if (d == d) {
                                d = (d - LocalTime(d)) / msPerMinute;
                            }
                            return ScriptRuntime.wrapNumber(d);
                        case 30:
                            d = TimeClip(ScriptRuntime.toNumber(objArr, 0));
                            nativeDate.date = d;
                            return ScriptRuntime.wrapNumber(d);
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                            d = makeTime(d, objArr, methodId);
                            nativeDate.date = d;
                            return ScriptRuntime.wrapNumber(d);
                        case 39:
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 44:
                            d = makeDate(d, objArr, methodId);
                            nativeDate.date = d;
                            return ScriptRuntime.wrapNumber(d);
                        case 45:
                            doubleValue = ScriptRuntime.toNumber(objArr, 0);
                            if (doubleValue != doubleValue || Double.isInfinite(doubleValue)) {
                                d = ScriptRuntime.NaN;
                            } else {
                                double d2;
                                if (d != d) {
                                    d2 = 0.0d;
                                } else {
                                    d2 = LocalTime(d);
                                }
                                if (doubleValue < 0.0d || doubleValue > 99.0d) {
                                    d = doubleValue;
                                } else {
                                    d = doubleValue + 1900.0d;
                                }
                                d = TimeClip(internalUTC(MakeDate(MakeDay(d, (double) MonthFromTime(d2), (double) DateFromTime(d2)), TimeWithinDay(d2))));
                            }
                            nativeDate.date = d;
                            return ScriptRuntime.wrapNumber(d);
                        case 46:
                            if (d == d) {
                                return js_toISOString(d);
                            }
                            throw ScriptRuntime.constructError("RangeError", ScriptRuntime.getMessage0("msg.invalid.date"));
                        default:
                            throw new IllegalArgumentException(String.valueOf(methodId));
                    }
                }
                throw IdScriptableObject.incompatibleCallError(idFunctionObject);
        }
    }

    private static double Day(double d) {
        return Math.floor(d / msPerDay);
    }

    private static double TimeWithinDay(double d) {
        double d2 = d % msPerDay;
        if (d2 < 0.0d) {
            return d2 + msPerDay;
        }
        return d2;
    }

    private static boolean IsLeapYear(int i) {
        return i % 4 == 0 && (i % 100 != 0 || i % 400 == 0);
    }

    private static double DayFromYear(double d) {
        return (((365.0d * (d - 1970.0d)) + Math.floor((d - 1969.0d) / 4.0d)) - Math.floor((d - 1901.0d) / 100.0d)) + Math.floor((d - 1601.0d) / 400.0d);
    }

    private static double TimeFromYear(double d) {
        return DayFromYear(d) * msPerDay;
    }

    private static int YearFromTime(double d) {
        int i;
        int floor = ((int) Math.floor((d / msPerDay) / 366.0d)) + 1970;
        int floor2 = ((int) Math.floor((d / msPerDay) / 365.0d)) + 1970;
        if (floor2 < floor) {
            i = floor;
        } else {
            i = floor2;
            floor2 = floor;
        }
        while (i > floor2) {
            floor = (i + floor2) / 2;
            if (TimeFromYear((double) floor) > d) {
                i = floor - 1;
            } else {
                floor2 = floor + 1;
                if (TimeFromYear((double) floor2) > d) {
                    return floor;
                }
            }
        }
        return floor2;
    }

    private static double DayFromMonth(int i, int i2) {
        int i3 = i * 30;
        if (i >= 7) {
            i3 += (i / 2) - 1;
        } else if (i >= 2) {
            i3 += ((i - 1) / 2) - 1;
        } else {
            i3 += i;
        }
        if (i >= 2 && IsLeapYear(i2)) {
            i3++;
        }
        return (double) i3;
    }

    private static int DaysInMonth(int i, int i2) {
        return i2 == 2 ? IsLeapYear(i) ? 29 : 28 : i2 >= 8 ? 31 - (i2 & 1) : (i2 & 1) + 30;
    }

    private static int MonthFromTime(double d) {
        int YearFromTime = YearFromTime(d);
        int Day = ((int) (Day(d) - DayFromYear((double) YearFromTime))) - 59;
        if (Day >= 0) {
            int i;
            if (!IsLeapYear(YearFromTime)) {
                i = Day;
            } else if (Day == 0) {
                return 1;
            } else {
                i = Day - 1;
            }
            YearFromTime = i / 30;
            switch (YearFromTime) {
                case 0:
                    return 2;
                case 1:
                    Day = 31;
                    break;
                case 2:
                    Day = 61;
                    break;
                case 3:
                    Day = 92;
                    break;
                case 4:
                    Day = 122;
                    break;
                case 5:
                    Day = 153;
                    break;
                case 6:
                    Day = 184;
                    break;
                case 7:
                    Day = Opcodes.OR_INT_LIT16;
                    break;
                case 8:
                    Day = 245;
                    break;
                case 9:
                    Day = 275;
                    break;
                case 10:
                    return 11;
                default:
                    throw Kit.codeBug();
            }
            if (i >= Day) {
                return YearFromTime + 2;
            }
            return YearFromTime + 1;
        } else if (Day < -28) {
            return 0;
        } else {
            return 1;
        }
    }

    private static int DateFromTime(double d) {
        int i = 30;
        int i2 = 31;
        int YearFromTime = YearFromTime(d);
        int Day = ((int) (Day(d) - DayFromYear((double) YearFromTime))) - 59;
        if (Day >= 0) {
            if (IsLeapYear(YearFromTime)) {
                if (Day == 0) {
                    return 29;
                }
                Day--;
            }
            switch (Day / 30) {
                case 0:
                    return Day + 1;
                case 1:
                    i = 31;
                    break;
                case 2:
                    i2 = 61;
                    break;
                case 3:
                    i = 31;
                    i2 = 92;
                    break;
                case 4:
                    i2 = 122;
                    break;
                case 5:
                    i = 31;
                    i2 = 153;
                    break;
                case 6:
                    i = 31;
                    i2 = 184;
                    break;
                case 7:
                    i2 = Opcodes.OR_INT_LIT16;
                    break;
                case 8:
                    i = 31;
                    i2 = 245;
                    break;
                case 9:
                    i2 = 275;
                    break;
                case 10:
                    return (Day - 275) + 1;
                default:
                    throw Kit.codeBug();
            }
            Day -= i2;
            if (Day < 0) {
                Day += i;
            }
            return Day + 1;
        } else if (Day < -28) {
            return ((Day + 31) + 28) + 1;
        } else {
            return (Day + 28) + 1;
        }
    }

    private static int WeekDay(double d) {
        double Day = (Day(d) + 4.0d) % 7.0d;
        if (Day < 0.0d) {
            Day += 7.0d;
        }
        return (int) Day;
    }

    private static double now() {
        return (double) System.currentTimeMillis();
    }

    private static double DaylightSavingTA(double d) {
        if (d < 0.0d) {
            d = MakeDate(MakeDay((double) EquivalentYear(YearFromTime(d)), (double) MonthFromTime(d), (double) DateFromTime(d)), TimeWithinDay(d));
        }
        if (thisTimeZone.inDaylightTime(new Date((long) d))) {
            return msPerHour;
        }
        return 0.0d;
    }

    private static int EquivalentYear(int i) {
        int DayFromYear = (((int) DayFromYear((double) i)) + 4) % 7;
        if (DayFromYear < 0) {
            DayFromYear += 7;
        }
        if (!IsLeapYear(i)) {
            switch (DayFromYear) {
                case 0:
                    return 1978;
                case 1:
                    return 1973;
                case 2:
                    return 1985;
                case 3:
                    return 1986;
                case 4:
                    return 1981;
                case 5:
                    return 1971;
                case 6:
                    return 1977;
                default:
                    break;
            }
        }
        switch (DayFromYear) {
            case 0:
                return 1984;
            case 1:
                return 1996;
            case 2:
                return 1980;
            case 3:
                return 1992;
            case 4:
                return 1976;
            case 5:
                return 1988;
            case 6:
                return 1972;
        }
        throw Kit.codeBug();
    }

    private static double LocalTime(double d) {
        return (LocalTZA + d) + DaylightSavingTA(d);
    }

    private static double internalUTC(double d) {
        return (d - LocalTZA) - DaylightSavingTA(d - LocalTZA);
    }

    private static int HourFromTime(double d) {
        double floor = Math.floor(d / msPerHour) % HoursPerDay;
        if (floor < 0.0d) {
            floor += HoursPerDay;
        }
        return (int) floor;
    }

    private static int MinFromTime(double d) {
        double floor = Math.floor(d / msPerMinute) % 60.0d;
        if (floor < 0.0d) {
            floor += 60.0d;
        }
        return (int) floor;
    }

    private static int SecFromTime(double d) {
        double floor = Math.floor(d / msPerSecond) % 60.0d;
        if (floor < 0.0d) {
            floor += 60.0d;
        }
        return (int) floor;
    }

    private static int msFromTime(double d) {
        double d2 = d % msPerSecond;
        if (d2 < 0.0d) {
            d2 += msPerSecond;
        }
        return (int) d2;
    }

    private static double MakeTime(double d, double d2, double d3, double d4) {
        return (((((d * 60.0d) + d2) * 60.0d) + d3) * msPerSecond) + d4;
    }

    private static double MakeDay(double d, double d2, double d3) {
        double floor = d + Math.floor(d2 / 12.0d);
        double d4 = d2 % 12.0d;
        if (d4 < 0.0d) {
            d4 += 12.0d;
        }
        return ((DayFromMonth((int) d4, (int) floor) + Math.floor(TimeFromYear(floor) / msPerDay)) + d3) - 1.0d;
    }

    private static double MakeDate(double d, double d2) {
        return (msPerDay * d) + d2;
    }

    private static double TimeClip(double d) {
        if (d != d || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY || Math.abs(d) > HalfTimeDomain) {
            return ScriptRuntime.NaN;
        }
        if (d > 0.0d) {
            return Math.floor(d + 0.0d);
        }
        return Math.ceil(d + 0.0d);
    }

    private static double date_msecFromDate(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        return MakeDate(MakeDay(d, d2, d3), MakeTime(d4, d5, d6, d7));
    }

    private static double date_msecFromArgs(Object[] objArr) {
        double[] dArr = new double[7];
        for (int i = 0; i < 7; i++) {
            if (i < objArr.length) {
                double toNumber = ScriptRuntime.toNumber(objArr[i]);
                if (toNumber != toNumber || Double.isInfinite(toNumber)) {
                    return ScriptRuntime.NaN;
                }
                dArr[i] = ScriptRuntime.toInteger(objArr[i]);
            } else if (i == 2) {
                dArr[i] = 1.0d;
            } else {
                dArr[i] = 0.0d;
            }
        }
        if (dArr[0] >= 0.0d && dArr[0] <= 99.0d) {
            dArr[0] = dArr[0] + 1900.0d;
        }
        return date_msecFromDate(dArr[0], dArr[1], dArr[2], dArr[3], dArr[4], dArr[5], dArr[6]);
    }

    private static double jsStaticFunction_UTC(Object[] objArr) {
        return TimeClip(date_msecFromArgs(objArr));
    }

    private static double parseISOString(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        char charAt;
        char charAt2;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        double date_msecFromDate;
        int[] iArr = new int[]{1970, 1, 1, 0, 0, 0, 0, -1, -1};
        int i10 = 4;
        int i11 = 1;
        int i12 = 0;
        int length = str.length();
        if (length != 0) {
            char charAt3 = str.charAt(0);
            if (charAt3 == '+' || charAt3 == '-') {
                i12 = 1;
                i10 = 6;
                i = charAt3 == '-' ? -1 : 1;
                i2 = 0;
                while (i2 != -1) {
                    i3 = i2 == 0 ? i10 : i2 == 6 ? 3 : 2;
                    i4 = i12 + i3;
                    if (i4 > length) {
                        i2 = -1;
                    } else {
                        i3 = i12;
                        i12 = 0;
                        while (i3 < i4) {
                            charAt = str.charAt(i3);
                            if (charAt < '0' || charAt > '9') {
                                i2 = -1;
                                i12 = i3;
                            } else {
                                i12 = (i12 * 10) + (charAt - 48);
                                i3++;
                            }
                        }
                        iArr[i2] = i12;
                        if (i3 == length) {
                            switch (i2) {
                                case 3:
                                case 7:
                                    i2 = -1;
                                    break;
                            }
                            i12 = i3;
                        } else {
                            i12 = i3 + 1;
                            charAt2 = str.charAt(i3);
                            if (charAt2 != 'Z') {
                                iArr[7] = 0;
                                iArr[8] = 0;
                                switch (i2) {
                                    case 4:
                                    case 5:
                                    case 6:
                                        break;
                                    default:
                                        i2 = -1;
                                        break;
                                }
                            }
                            switch (i2) {
                                case 0:
                                case 1:
                                    i2 = charAt2 == '-' ? i2 + 1 : charAt2 == 'T' ? 3 : -1;
                                    i3 = i2;
                                    break;
                                case 2:
                                    i3 = charAt2 == 'T' ? 3 : -1;
                                    break;
                                case 3:
                                    i3 = charAt2 == ':' ? 4 : -1;
                                    break;
                                case 4:
                                    i2 = charAt2 != ':' ? 5 : (charAt2 != '+' || charAt2 == '-') ? 7 : -1;
                                    i3 = i2;
                                    break;
                                case 5:
                                    i2 = charAt2 != '.' ? 6 : (charAt2 != '+' || charAt2 == '-') ? 7 : -1;
                                    i3 = i2;
                                    break;
                                case 6:
                                    i2 = (charAt2 != '+' || charAt2 == '-') ? 7 : -1;
                                    i3 = i2;
                                    break;
                                case 7:
                                    if (charAt2 != ':') {
                                        i2 = i12 - 1;
                                    } else {
                                        i2 = i12;
                                    }
                                    i3 = 8;
                                    i12 = i2;
                                    break;
                                case 8:
                                    i3 = -1;
                                    break;
                                default:
                                    i3 = i2;
                                    break;
                            }
                            i2 = i3 == 7 ? charAt2 == '-' ? -1 : 1 : i11;
                            i11 = i2;
                            i2 = i3;
                        }
                    }
                    if (i2 != -1 && r3 == length) {
                        i2 = iArr[0];
                        i3 = iArr[1];
                        length = iArr[2];
                        i4 = iArr[3];
                        i5 = iArr[4];
                        i6 = iArr[5];
                        i7 = iArr[6];
                        i8 = iArr[7];
                        i9 = iArr[8];
                        if (i2 <= 275943 && i3 >= 1 && i3 <= 12 && length >= 1 && length <= DaysInMonth(i2, i3) && i4 <= 24 && ((i4 != 24 || (i5 <= 0 && i6 <= 0 && i7 <= 0)) && i5 <= 59 && i6 <= 59 && i8 <= 23 && i9 <= 59)) {
                            date_msecFromDate = date_msecFromDate((double) (i2 * i), (double) (i3 - 1), (double) length, (double) i4, (double) i5, (double) i6, (double) i7);
                            if (i8 != -1) {
                                date_msecFromDate -= (((double) ((i8 * 60) + i9)) * msPerMinute) * ((double) i11);
                            }
                            if (date_msecFromDate >= -8.64E15d) {
                                if (date_msecFromDate > HalfTimeDomain) {
                                    return date_msecFromDate;
                                }
                            }
                        }
                    }
                    return ScriptRuntime.NaN;
                }
                i2 = iArr[0];
                i3 = iArr[1];
                length = iArr[2];
                i4 = iArr[3];
                i5 = iArr[4];
                i6 = iArr[5];
                i7 = iArr[6];
                i8 = iArr[7];
                i9 = iArr[8];
                date_msecFromDate = date_msecFromDate((double) (i2 * i), (double) (i3 - 1), (double) length, (double) i4, (double) i5, (double) i6, (double) i7);
                if (i8 != -1) {
                    date_msecFromDate -= (((double) ((i8 * 60) + i9)) * msPerMinute) * ((double) i11);
                }
                if (date_msecFromDate >= -8.64E15d) {
                    if (date_msecFromDate > HalfTimeDomain) {
                        return date_msecFromDate;
                    }
                }
                return ScriptRuntime.NaN;
            } else if (charAt3 == 'T') {
                i12 = 1;
                i = 1;
                i2 = 3;
                while (i2 != -1) {
                    if (i2 == 0) {
                        if (i2 == 6) {
                        }
                    }
                    i4 = i12 + i3;
                    if (i4 > length) {
                        i3 = i12;
                        i12 = 0;
                        while (i3 < i4) {
                            charAt = str.charAt(i3);
                            if (charAt < '0') {
                            }
                            i2 = -1;
                            i12 = i3;
                        }
                        iArr[i2] = i12;
                        if (i3 == length) {
                            i12 = i3 + 1;
                            charAt2 = str.charAt(i3);
                            if (charAt2 != 'Z') {
                                iArr[7] = 0;
                                iArr[8] = 0;
                                switch (i2) {
                                    case 4:
                                    case 5:
                                    case 6:
                                        break;
                                    default:
                                        i2 = -1;
                                        break;
                                }
                            }
                            switch (i2) {
                                case 0:
                                case 1:
                                    if (charAt2 == '-') {
                                        if (charAt2 == 'T') {
                                        }
                                    }
                                    i3 = i2;
                                    break;
                                case 2:
                                    if (charAt2 == 'T') {
                                    }
                                    i3 = charAt2 == 'T' ? 3 : -1;
                                    break;
                                case 3:
                                    if (charAt2 == ':') {
                                    }
                                    i3 = charAt2 == ':' ? 4 : -1;
                                    break;
                                case 4:
                                    if (charAt2 != ':') {
                                        if (charAt2 != '+') {
                                            break;
                                        }
                                    }
                                    i3 = i2;
                                    break;
                                case 5:
                                    if (charAt2 != '.') {
                                        if (charAt2 != '+') {
                                            break;
                                        }
                                    }
                                    i3 = i2;
                                    break;
                                case 6:
                                    if (charAt2 != '+') {
                                        break;
                                    }
                                    i3 = i2;
                                    break;
                                case 7:
                                    if (charAt2 != ':') {
                                        i2 = i12;
                                    } else {
                                        i2 = i12 - 1;
                                    }
                                    i3 = 8;
                                    i12 = i2;
                                    break;
                                case 8:
                                    i3 = -1;
                                    break;
                                default:
                                    i3 = i2;
                                    break;
                            }
                            if (i3 == 7) {
                            }
                            i11 = i2;
                            i2 = i3;
                        } else {
                            switch (i2) {
                                case 3:
                                case 7:
                                    i2 = -1;
                                    break;
                            }
                            i12 = i3;
                        }
                    } else {
                        i2 = -1;
                    }
                    i2 = iArr[0];
                    i3 = iArr[1];
                    length = iArr[2];
                    i4 = iArr[3];
                    i5 = iArr[4];
                    i6 = iArr[5];
                    i7 = iArr[6];
                    i8 = iArr[7];
                    i9 = iArr[8];
                    date_msecFromDate = date_msecFromDate((double) (i2 * i), (double) (i3 - 1), (double) length, (double) i4, (double) i5, (double) i6, (double) i7);
                    if (i8 != -1) {
                        date_msecFromDate -= (((double) ((i8 * 60) + i9)) * msPerMinute) * ((double) i11);
                    }
                    if (date_msecFromDate >= -8.64E15d) {
                        if (date_msecFromDate > HalfTimeDomain) {
                            return date_msecFromDate;
                        }
                    }
                    return ScriptRuntime.NaN;
                }
                i2 = iArr[0];
                i3 = iArr[1];
                length = iArr[2];
                i4 = iArr[3];
                i5 = iArr[4];
                i6 = iArr[5];
                i7 = iArr[6];
                i8 = iArr[7];
                i9 = iArr[8];
                date_msecFromDate = date_msecFromDate((double) (i2 * i), (double) (i3 - 1), (double) length, (double) i4, (double) i5, (double) i6, (double) i7);
                if (i8 != -1) {
                    date_msecFromDate -= (((double) ((i8 * 60) + i9)) * msPerMinute) * ((double) i11);
                }
                if (date_msecFromDate >= -8.64E15d) {
                    if (date_msecFromDate > HalfTimeDomain) {
                        return date_msecFromDate;
                    }
                }
                return ScriptRuntime.NaN;
            }
        }
        i = 1;
        i2 = 0;
        while (i2 != -1) {
            if (i2 == 0) {
            }
            i4 = i12 + i3;
            if (i4 > length) {
                i2 = -1;
            } else {
                i3 = i12;
                i12 = 0;
                while (i3 < i4) {
                    charAt = str.charAt(i3);
                    if (charAt < '0') {
                    }
                    i2 = -1;
                    i12 = i3;
                }
                iArr[i2] = i12;
                if (i3 == length) {
                    switch (i2) {
                        case 3:
                        case 7:
                            i2 = -1;
                            break;
                    }
                    i12 = i3;
                } else {
                    i12 = i3 + 1;
                    charAt2 = str.charAt(i3);
                    if (charAt2 != 'Z') {
                        iArr[7] = 0;
                        iArr[8] = 0;
                        switch (i2) {
                            case 4:
                            case 5:
                            case 6:
                                break;
                            default:
                                i2 = -1;
                                break;
                        }
                    }
                    switch (i2) {
                        case 0:
                        case 1:
                            if (charAt2 == '-') {
                            }
                            i3 = i2;
                            break;
                        case 2:
                            if (charAt2 == 'T') {
                            }
                            i3 = charAt2 == 'T' ? 3 : -1;
                            break;
                        case 3:
                            if (charAt2 == ':') {
                            }
                            i3 = charAt2 == ':' ? 4 : -1;
                            break;
                        case 4:
                            if (charAt2 != ':') {
                                if (charAt2 != '+') {
                                    break;
                                }
                            }
                            i3 = i2;
                            break;
                        case 5:
                            if (charAt2 != '.') {
                                if (charAt2 != '+') {
                                    break;
                                }
                            }
                            i3 = i2;
                            break;
                        case 6:
                            if (charAt2 != '+') {
                                break;
                            }
                            i3 = i2;
                            break;
                        case 7:
                            if (charAt2 != ':') {
                                i2 = i12 - 1;
                            } else {
                                i2 = i12;
                            }
                            i3 = 8;
                            i12 = i2;
                            break;
                        case 8:
                            i3 = -1;
                            break;
                        default:
                            i3 = i2;
                            break;
                    }
                    if (i3 == 7) {
                        if (charAt2 == '-') {
                        }
                    }
                    i11 = i2;
                    i2 = i3;
                }
            }
            i2 = iArr[0];
            i3 = iArr[1];
            length = iArr[2];
            i4 = iArr[3];
            i5 = iArr[4];
            i6 = iArr[5];
            i7 = iArr[6];
            i8 = iArr[7];
            i9 = iArr[8];
            date_msecFromDate = date_msecFromDate((double) (i2 * i), (double) (i3 - 1), (double) length, (double) i4, (double) i5, (double) i6, (double) i7);
            if (i8 != -1) {
                date_msecFromDate -= (((double) ((i8 * 60) + i9)) * msPerMinute) * ((double) i11);
            }
            if (date_msecFromDate >= -8.64E15d) {
                if (date_msecFromDate > HalfTimeDomain) {
                    return date_msecFromDate;
                }
            }
            return ScriptRuntime.NaN;
        }
        i2 = iArr[0];
        i3 = iArr[1];
        length = iArr[2];
        i4 = iArr[3];
        i5 = iArr[4];
        i6 = iArr[5];
        i7 = iArr[6];
        i8 = iArr[7];
        i9 = iArr[8];
        date_msecFromDate = date_msecFromDate((double) (i2 * i), (double) (i3 - 1), (double) length, (double) i4, (double) i5, (double) i6, (double) i7);
        if (i8 != -1) {
            date_msecFromDate -= (((double) ((i8 * 60) + i9)) * msPerMinute) * ((double) i11);
        }
        if (date_msecFromDate >= -8.64E15d) {
            if (date_msecFromDate > HalfTimeDomain) {
                return date_msecFromDate;
            }
        }
        return ScriptRuntime.NaN;
    }

    private static double date_parseString(String str) {
        double parseISOString = parseISOString(str);
        if (parseISOString == parseISOString) {
            return parseISOString;
        }
        int i = -1;
        int i2 = -1;
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = 0;
        double d = -1.0d;
        Object obj = null;
        int length = str.length();
        char c = '\u0000';
        while (i7 < length) {
            int i8;
            char charAt = str.charAt(i7);
            i7++;
            char charAt2;
            if (charAt <= ' ' || charAt == ',' || charAt == '-') {
                if (i7 < length) {
                    charAt2 = str.charAt(i7);
                    if (charAt == '-' && '0' <= charAt2 && charAt2 <= '9') {
                        c = charAt;
                    }
                }
            } else if (charAt == '(') {
                r3 = i7;
                i7 = 1;
                while (r3 < length) {
                    charAt = str.charAt(r3);
                    r3++;
                    if (charAt == '(') {
                        i7++;
                    } else if (charAt == ')') {
                        i7--;
                        if (i7 <= 0) {
                            i7 = r3;
                        }
                    } else {
                        continue;
                    }
                }
                i7 = r3;
            } else if ('0' <= charAt && charAt <= '9') {
                double d2;
                Object obj2;
                int i9;
                i8 = i7;
                i7 = charAt - 48;
                charAt2 = charAt;
                while (i8 < length) {
                    charAt2 = str.charAt(i8);
                    if ('0' <= charAt2 && charAt2 <= '9') {
                        i7 = ((i7 * 10) + charAt2) - 48;
                        i8++;
                    }
                    if (c != '+' || c == '-') {
                        if (i7 >= 24) {
                            i7 *= 60;
                        } else {
                            i7 = ((i7 / 100) * 60) + (i7 % 100);
                        }
                        if (c == '+') {
                            i7 = -i7;
                        }
                        if (d == 0.0d && d != -1.0d) {
                            return ScriptRuntime.NaN;
                        }
                        d2 = (double) i7;
                        obj2 = 1;
                        r6 = i5;
                        r7 = i4;
                        i9 = i3;
                        r3 = i6;
                        i5 = i;
                        i6 = i2;
                    } else if (i7 >= 70 || (c == '/' && i2 >= 0 && i3 >= 0 && i < 0)) {
                        if (i >= 0) {
                            return ScriptRuntime.NaN;
                        }
                        if (charAt2 > ' ' && charAt2 != ',' && charAt2 != '/' && i8 < length) {
                            return ScriptRuntime.NaN;
                        }
                        if (i7 < 100) {
                            i7 += 1900;
                        }
                        d2 = d;
                        r3 = i6;
                        r6 = i5;
                        r7 = i4;
                        i6 = i2;
                        i5 = i7;
                        obj2 = obj;
                        i9 = i3;
                    } else if (charAt2 == ':') {
                        if (i4 < 0) {
                            d2 = d;
                            r3 = i6;
                            r6 = i5;
                            r7 = i7;
                            obj2 = obj;
                            i6 = i2;
                            i5 = i;
                            i9 = i3;
                        } else if (i5 >= 0) {
                            return ScriptRuntime.NaN;
                        } else {
                            d2 = d;
                            r3 = i6;
                            r6 = i7;
                            r7 = i4;
                            i5 = i;
                            i6 = i2;
                            obj2 = obj;
                            i9 = i3;
                        }
                    } else if (charAt2 == '/') {
                        if (i2 < 0) {
                            d2 = d;
                            r3 = i6;
                            r6 = i5;
                            r7 = i4;
                            i6 = i7 - 1;
                            i5 = i;
                            obj2 = obj;
                            i9 = i3;
                        } else if (i3 >= 0) {
                            return ScriptRuntime.NaN;
                        } else {
                            d2 = d;
                            r3 = i6;
                            r6 = i5;
                            r7 = i4;
                            i6 = i2;
                            i5 = i;
                            r22 = obj;
                            i9 = i7;
                            obj2 = r22;
                        }
                    } else if (i8 < length && charAt2 != ',' && charAt2 > ' ' && charAt2 != '-') {
                        return ScriptRuntime.NaN;
                    } else {
                        if (obj == null || i7 >= 60) {
                            if (i4 >= 0 && i5 < 0) {
                                d2 = d;
                                r3 = i6;
                                r6 = i7;
                                r7 = i4;
                                i5 = i;
                                i6 = i2;
                                obj2 = obj;
                                i9 = i3;
                            } else if (i5 >= 0 && i6 < 0) {
                                d2 = d;
                                r3 = i7;
                                r6 = i5;
                                r7 = i4;
                                i6 = i2;
                                obj2 = obj;
                                i5 = i;
                                i9 = i3;
                            } else if (i3 >= 0) {
                                return ScriptRuntime.NaN;
                            } else {
                                d2 = d;
                                r3 = i6;
                                r6 = i5;
                                r7 = i4;
                                i6 = i2;
                                i5 = i;
                                r22 = obj;
                                i9 = i7;
                                obj2 = r22;
                            }
                        } else if (d < 0.0d) {
                            d2 = d - ((double) i7);
                            r6 = i5;
                            r7 = i4;
                            obj2 = obj;
                            r3 = i6;
                            i5 = i;
                            i6 = i2;
                            i9 = i3;
                        } else {
                            d2 = ((double) i7) + d;
                            r6 = i5;
                            r7 = i4;
                            obj2 = obj;
                            r3 = i6;
                            i5 = i;
                            i6 = i2;
                            i9 = i3;
                        }
                    }
                    c = '\u0000';
                    d = d2;
                    i3 = i9;
                    i2 = i6;
                    i = i5;
                    i5 = r6;
                    i4 = r7;
                    obj = obj2;
                    i6 = r3;
                    i7 = i8;
                }
                if (c != '+') {
                }
                if (i7 >= 24) {
                    i7 = ((i7 / 100) * 60) + (i7 % 100);
                } else {
                    i7 *= 60;
                }
                if (c == '+') {
                    i7 = -i7;
                }
                if (d == 0.0d) {
                }
                d2 = (double) i7;
                obj2 = 1;
                r6 = i5;
                r7 = i4;
                i9 = i3;
                r3 = i6;
                i5 = i;
                i6 = i2;
                c = '\u0000';
                d = d2;
                i3 = i9;
                i2 = i6;
                i = i5;
                i5 = r6;
                i4 = r7;
                obj = obj2;
                i6 = r3;
                i7 = i8;
            } else if (charAt == '/' || charAt == ':' || charAt == '+' || charAt == '-') {
                c = charAt;
            } else {
                String str2;
                int i10;
                int indexOf;
                r6 = i7 - 1;
                int i11 = i7;
                while (i11 < length) {
                    char charAt3 = str.charAt(i11);
                    if (('A' <= charAt3 && charAt3 <= 'Z') || ('a' <= charAt3 && charAt3 <= 'z')) {
                        i11++;
                    }
                    r7 = i11 - r6;
                    if (r7 < 2) {
                        return ScriptRuntime.NaN;
                    }
                    str2 = "am;pm;monday;tuesday;wednesday;thursday;friday;saturday;sunday;january;february;march;april;may;june;july;august;september;october;november;december;gmt;ut;utc;est;edt;cst;cdt;mst;mdt;pst;pdt;";
                    i10 = 0;
                    i8 = 0;
                    while (true) {
                        indexOf = str2.indexOf(59, i10);
                        if (indexOf < 0) {
                            return ScriptRuntime.NaN;
                        }
                        if (str2.regionMatches(true, i10, str, r6, r7)) {
                            i10 = indexOf + 1;
                            i8++;
                        } else {
                            if (i8 < 2) {
                                i7 = i8 - 2;
                                if (i7 >= 7) {
                                    i7 -= 7;
                                    if (i7 < 12) {
                                        switch (i7 - 12) {
                                            case 0:
                                                d = 0.0d;
                                                break;
                                            case 1:
                                                d = 0.0d;
                                                break;
                                            case 2:
                                                d = 0.0d;
                                                break;
                                            case 3:
                                                d = 300.0d;
                                                break;
                                            case 4:
                                                d = 240.0d;
                                                break;
                                            case 5:
                                                d = 360.0d;
                                                break;
                                            case 6:
                                                d = 300.0d;
                                                break;
                                            case 7:
                                                d = 420.0d;
                                                break;
                                            case 8:
                                                d = 360.0d;
                                                break;
                                            case 9:
                                                d = 480.0d;
                                                break;
                                            case 10:
                                                d = 420.0d;
                                                break;
                                            default:
                                                Kit.codeBug();
                                                break;
                                        }
                                    } else if (i2 < 0) {
                                        return ScriptRuntime.NaN;
                                    } else {
                                        i2 = i7;
                                    }
                                }
                            } else if (i4 <= 12 || i4 < 0) {
                                return ScriptRuntime.NaN;
                            } else {
                                if (i8 == 0) {
                                    if (i4 == 12) {
                                        i4 = 0;
                                    }
                                } else if (i4 != 12) {
                                    i4 += 12;
                                }
                            }
                            i7 = i11;
                        }
                    }
                }
                r7 = i11 - r6;
                if (r7 < 2) {
                    return ScriptRuntime.NaN;
                }
                str2 = "am;pm;monday;tuesday;wednesday;thursday;friday;saturday;sunday;january;february;march;april;may;june;july;august;september;october;november;december;gmt;ut;utc;est;edt;cst;cdt;mst;mdt;pst;pdt;";
                i10 = 0;
                i8 = 0;
                while (true) {
                    indexOf = str2.indexOf(59, i10);
                    if (indexOf < 0) {
                        return ScriptRuntime.NaN;
                    }
                    if (str2.regionMatches(true, i10, str, r6, r7)) {
                        i10 = indexOf + 1;
                        i8++;
                    } else {
                        if (i8 < 2) {
                            i7 = i8 - 2;
                            if (i7 >= 7) {
                                i7 -= 7;
                                if (i7 < 12) {
                                    switch (i7 - 12) {
                                        case 0:
                                            d = 0.0d;
                                            break;
                                        case 1:
                                            d = 0.0d;
                                            break;
                                        case 2:
                                            d = 0.0d;
                                            break;
                                        case 3:
                                            d = 300.0d;
                                            break;
                                        case 4:
                                            d = 240.0d;
                                            break;
                                        case 5:
                                            d = 360.0d;
                                            break;
                                        case 6:
                                            d = 300.0d;
                                            break;
                                        case 7:
                                            d = 420.0d;
                                            break;
                                        case 8:
                                            d = 360.0d;
                                            break;
                                        case 9:
                                            d = 480.0d;
                                            break;
                                        case 10:
                                            d = 420.0d;
                                            break;
                                        default:
                                            Kit.codeBug();
                                            break;
                                    }
                                } else if (i2 < 0) {
                                    return ScriptRuntime.NaN;
                                } else {
                                    i2 = i7;
                                }
                            }
                        } else {
                            if (i4 <= 12) {
                            }
                            return ScriptRuntime.NaN;
                        }
                        i7 = i11;
                    }
                }
            }
        }
        if (i < 0 || i2 < 0 || i3 < 0) {
            return ScriptRuntime.NaN;
        }
        if (i6 < 0) {
            i8 = 0;
        } else {
            i8 = i6;
        }
        if (i5 < 0) {
            i5 = 0;
        }
        if (i4 < 0) {
            i4 = 0;
        }
        parseISOString = date_msecFromDate((double) i, (double) i2, (double) i3, (double) i4, (double) i5, (double) i8, 0.0d);
        if (d == -1.0d) {
            return internalUTC(parseISOString);
        }
        return parseISOString + (msPerMinute * d);
    }

    private static String date_format(double d, int i) {
        int YearFromTime;
        StringBuilder stringBuilder = new StringBuilder(60);
        double LocalTime = LocalTime(d);
        if (i != 3) {
            appendWeekDayName(stringBuilder, WeekDay(LocalTime));
            stringBuilder.append(' ');
            appendMonthName(stringBuilder, MonthFromTime(LocalTime));
            stringBuilder.append(' ');
            append0PaddedUint(stringBuilder, DateFromTime(LocalTime), 2);
            stringBuilder.append(' ');
            YearFromTime = YearFromTime(LocalTime);
            if (YearFromTime < 0) {
                stringBuilder.append('-');
                YearFromTime = -YearFromTime;
            }
            append0PaddedUint(stringBuilder, YearFromTime, 4);
            if (i != 4) {
                stringBuilder.append(' ');
            }
        }
        if (i != 4) {
            append0PaddedUint(stringBuilder, HourFromTime(LocalTime), 2);
            stringBuilder.append(':');
            append0PaddedUint(stringBuilder, MinFromTime(LocalTime), 2);
            stringBuilder.append(':');
            append0PaddedUint(stringBuilder, SecFromTime(LocalTime), 2);
            YearFromTime = (int) Math.floor((LocalTZA + DaylightSavingTA(d)) / msPerMinute);
            YearFromTime = (YearFromTime % 60) + ((YearFromTime / 60) * 100);
            if (YearFromTime > 0) {
                stringBuilder.append(" GMT+");
            } else {
                stringBuilder.append(" GMT-");
                YearFromTime = -YearFromTime;
            }
            append0PaddedUint(stringBuilder, YearFromTime, 4);
            if (timeZoneFormatter == null) {
                timeZoneFormatter = new SimpleDateFormat("zzz");
            }
            if (d < 0.0d) {
                d = MakeDate(MakeDay((double) EquivalentYear(YearFromTime(LocalTime)), (double) MonthFromTime(d), (double) DateFromTime(d)), TimeWithinDay(d));
            }
            stringBuilder.append(" (");
            Date date = new Date((long) d);
            synchronized (timeZoneFormatter) {
                stringBuilder.append(timeZoneFormatter.format(date));
            }
            stringBuilder.append(')');
        }
        return stringBuilder.toString();
    }

    private static Object jsConstructor(Object[] objArr) {
        NativeDate nativeDate = new NativeDate();
        if (objArr.length == 0) {
            nativeDate.date = now();
            return nativeDate;
        } else if (objArr.length == 1) {
            Object obj = objArr[0];
            if (obj instanceof Scriptable) {
                obj = ((Scriptable) obj).getDefaultValue(null);
            }
            if (obj instanceof CharSequence) {
                r0 = date_parseString(obj.toString());
            } else {
                r0 = ScriptRuntime.toNumber(obj);
            }
            nativeDate.date = TimeClip(r0);
            return nativeDate;
        } else {
            r0 = date_msecFromArgs(objArr);
            if (!(Double.isNaN(r0) || Double.isInfinite(r0))) {
                r0 = TimeClip(internalUTC(r0));
            }
            nativeDate.date = r0;
            return nativeDate;
        }
    }

    private static String toLocale_helper(double d, int i) {
        DateFormat dateFormat;
        String format;
        switch (i) {
            case 5:
                if (localeDateTimeFormatter == null) {
                    localeDateTimeFormatter = DateFormat.getDateTimeInstance(1, 1);
                }
                dateFormat = localeDateTimeFormatter;
                break;
            case 6:
                if (localeTimeFormatter == null) {
                    localeTimeFormatter = DateFormat.getTimeInstance(1);
                }
                dateFormat = localeTimeFormatter;
                break;
            case 7:
                if (localeDateFormatter == null) {
                    localeDateFormatter = DateFormat.getDateInstance(1);
                }
                dateFormat = localeDateFormatter;
                break;
            default:
                throw new AssertionError();
        }
        synchronized (dateFormat) {
            format = dateFormat.format(new Date((long) d));
        }
        return format;
    }

    private static String js_toUTCString(double d) {
        StringBuilder stringBuilder = new StringBuilder(60);
        appendWeekDayName(stringBuilder, WeekDay(d));
        stringBuilder.append(", ");
        append0PaddedUint(stringBuilder, DateFromTime(d), 2);
        stringBuilder.append(' ');
        appendMonthName(stringBuilder, MonthFromTime(d));
        stringBuilder.append(' ');
        int YearFromTime = YearFromTime(d);
        if (YearFromTime < 0) {
            stringBuilder.append('-');
            YearFromTime = -YearFromTime;
        }
        append0PaddedUint(stringBuilder, YearFromTime, 4);
        stringBuilder.append(' ');
        append0PaddedUint(stringBuilder, HourFromTime(d), 2);
        stringBuilder.append(':');
        append0PaddedUint(stringBuilder, MinFromTime(d), 2);
        stringBuilder.append(':');
        append0PaddedUint(stringBuilder, SecFromTime(d), 2);
        stringBuilder.append(" GMT");
        return stringBuilder.toString();
    }

    private static String js_toISOString(double d) {
        StringBuilder stringBuilder = new StringBuilder(27);
        int YearFromTime = YearFromTime(d);
        if (YearFromTime < 0) {
            stringBuilder.append('-');
            append0PaddedUint(stringBuilder, -YearFromTime, 6);
        } else if (YearFromTime > 9999) {
            append0PaddedUint(stringBuilder, YearFromTime, 6);
        } else {
            append0PaddedUint(stringBuilder, YearFromTime, 4);
        }
        stringBuilder.append('-');
        append0PaddedUint(stringBuilder, MonthFromTime(d) + 1, 2);
        stringBuilder.append('-');
        append0PaddedUint(stringBuilder, DateFromTime(d), 2);
        stringBuilder.append('T');
        append0PaddedUint(stringBuilder, HourFromTime(d), 2);
        stringBuilder.append(':');
        append0PaddedUint(stringBuilder, MinFromTime(d), 2);
        stringBuilder.append(':');
        append0PaddedUint(stringBuilder, SecFromTime(d), 2);
        stringBuilder.append('.');
        append0PaddedUint(stringBuilder, msFromTime(d), 3);
        stringBuilder.append('Z');
        return stringBuilder.toString();
    }

    private static void append0PaddedUint(StringBuilder stringBuilder, int i, int i2) {
        int i3 = 1000000000;
        if (i < 0) {
            Kit.codeBug();
        }
        int i4 = i2 - 1;
        if (i < 10) {
            i3 = 1;
        } else if (i < 1000000000) {
            i3 = 1;
            while (true) {
                int i5 = i3 * 10;
                if (i < i5) {
                    break;
                }
                i4--;
                i3 = i5;
            }
        } else {
            i4 -= 9;
        }
        while (i4 > 0) {
            stringBuilder.append('0');
            i4--;
        }
        while (i3 != 1) {
            stringBuilder.append((char) ((i / i3) + 48));
            i %= i3;
            i3 /= 10;
        }
        stringBuilder.append((char) (i + 48));
    }

    private static void appendMonthName(StringBuilder stringBuilder, int i) {
        String str = "JanFebMarAprMayJunJulAugSepOctNovDec";
        int i2 = i * 3;
        for (int i3 = 0; i3 != 3; i3++) {
            stringBuilder.append(str.charAt(i2 + i3));
        }
    }

    private static void appendWeekDayName(StringBuilder stringBuilder, int i) {
        String str = "SunMonTueWedThuFriSat";
        int i2 = i * 3;
        for (int i3 = 0; i3 != 3; i3++) {
            stringBuilder.append(str.charAt(i2 + i3));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static double makeTime(double r16, java.lang.Object[] r18, int r19) {
        /*
        r0 = r18;
        r2 = r0.length;
        if (r2 != 0) goto L_0x0008;
    L_0x0005:
        r2 = org.mozilla.javascript.ScriptRuntime.NaN;
    L_0x0007:
        return r2;
    L_0x0008:
        r2 = 1;
        switch(r19) {
            case 31: goto L_0x0012;
            case 32: goto L_0x0011;
            case 33: goto L_0x002d;
            case 34: goto L_0x002c;
            case 35: goto L_0x0032;
            case 36: goto L_0x0031;
            case 37: goto L_0x0037;
            case 38: goto L_0x0036;
            default: goto L_0x000c;
        };
    L_0x000c:
        r2 = org.mozilla.javascript.Kit.codeBug();
        throw r2;
    L_0x0011:
        r2 = 0;
    L_0x0012:
        r3 = 1;
        r10 = r2;
        r8 = r3;
    L_0x0015:
        r3 = 0;
        r0 = r18;
        r2 = r0.length;
        if (r2 >= r8) goto L_0x003b;
    L_0x001b:
        r0 = r18;
        r2 = r0.length;
        r9 = r2;
    L_0x001f:
        r2 = $assertionsDisabled;
        if (r2 != 0) goto L_0x003d;
    L_0x0023:
        r2 = 4;
        if (r9 <= r2) goto L_0x003d;
    L_0x0026:
        r2 = new java.lang.AssertionError;
        r2.<init>();
        throw r2;
    L_0x002c:
        r2 = 0;
    L_0x002d:
        r3 = 2;
        r10 = r2;
        r8 = r3;
        goto L_0x0015;
    L_0x0031:
        r2 = 0;
    L_0x0032:
        r3 = 3;
        r10 = r2;
        r8 = r3;
        goto L_0x0015;
    L_0x0036:
        r2 = 0;
    L_0x0037:
        r3 = 4;
        r10 = r2;
        r8 = r3;
        goto L_0x0015;
    L_0x003b:
        r9 = r8;
        goto L_0x001f;
    L_0x003d:
        r2 = 4;
        r13 = new double[r2];
        r2 = 0;
        r14 = r2;
        r2 = r3;
        r3 = r14;
    L_0x0044:
        if (r3 >= r9) goto L_0x0061;
    L_0x0046:
        r4 = r18[r3];
        r4 = org.mozilla.javascript.ScriptRuntime.toNumber(r4);
        r6 = (r4 > r4 ? 1 : (r4 == r4 ? 0 : -1));
        if (r6 != 0) goto L_0x0056;
    L_0x0050:
        r6 = java.lang.Double.isInfinite(r4);
        if (r6 == 0) goto L_0x005a;
    L_0x0056:
        r2 = 1;
    L_0x0057:
        r3 = r3 + 1;
        goto L_0x0044;
    L_0x005a:
        r4 = org.mozilla.javascript.ScriptRuntime.toInteger(r4);
        r13[r3] = r4;
        goto L_0x0057;
    L_0x0061:
        if (r2 != 0) goto L_0x0067;
    L_0x0063:
        r2 = (r16 > r16 ? 1 : (r16 == r16 ? 0 : -1));
        if (r2 == 0) goto L_0x006a;
    L_0x0067:
        r2 = org.mozilla.javascript.ScriptRuntime.NaN;
        goto L_0x0007;
    L_0x006a:
        r4 = 0;
        if (r10 == 0) goto L_0x0071;
    L_0x006d:
        r16 = LocalTime(r16);
    L_0x0071:
        r2 = 4;
        if (r8 < r2) goto L_0x00ac;
    L_0x0074:
        if (r4 >= r9) goto L_0x00ac;
    L_0x0076:
        r6 = 1;
        r2 = r13[r4];
    L_0x0079:
        r4 = 3;
        if (r8 < r4) goto L_0x00b3;
    L_0x007c:
        if (r6 >= r9) goto L_0x00b3;
    L_0x007e:
        r12 = r6 + 1;
        r4 = r13[r6];
    L_0x0082:
        r6 = 2;
        if (r8 < r6) goto L_0x00ba;
    L_0x0085:
        if (r12 >= r9) goto L_0x00ba;
    L_0x0087:
        r11 = r12 + 1;
        r6 = r13[r12];
    L_0x008b:
        r12 = 1;
        if (r8 < r12) goto L_0x00c1;
    L_0x008e:
        if (r11 >= r9) goto L_0x00c1;
    L_0x0090:
        r8 = r11 + 1;
        r8 = r13[r11];
    L_0x0094:
        r2 = MakeTime(r2, r4, r6, r8);
        r4 = Day(r16);
        r2 = MakeDate(r4, r2);
        if (r10 == 0) goto L_0x00a6;
    L_0x00a2:
        r2 = internalUTC(r2);
    L_0x00a6:
        r2 = TimeClip(r2);
        goto L_0x0007;
    L_0x00ac:
        r2 = HourFromTime(r16);
        r2 = (double) r2;
        r6 = r4;
        goto L_0x0079;
    L_0x00b3:
        r4 = MinFromTime(r16);
        r4 = (double) r4;
        r12 = r6;
        goto L_0x0082;
    L_0x00ba:
        r6 = SecFromTime(r16);
        r6 = (double) r6;
        r11 = r12;
        goto L_0x008b;
    L_0x00c1:
        r8 = msFromTime(r16);
        r8 = (double) r8;
        goto L_0x0094;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeDate.makeTime(double, java.lang.Object[], int):double");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static double makeDate(double r12, java.lang.Object[] r14, int r15) {
        /*
        r0 = r14.length;
        if (r0 != 0) goto L_0x0006;
    L_0x0003:
        r0 = org.mozilla.javascript.ScriptRuntime.NaN;
    L_0x0005:
        return r0;
    L_0x0006:
        r0 = 1;
        switch(r15) {
            case 39: goto L_0x0010;
            case 40: goto L_0x000f;
            case 41: goto L_0x002a;
            case 42: goto L_0x0029;
            case 43: goto L_0x002f;
            case 44: goto L_0x002e;
            default: goto L_0x000a;
        };
    L_0x000a:
        r0 = org.mozilla.javascript.Kit.codeBug();
        throw r0;
    L_0x000f:
        r0 = 0;
    L_0x0010:
        r1 = 1;
        r6 = r0;
        r4 = r1;
    L_0x0013:
        r1 = 0;
        r0 = r14.length;
        if (r0 >= r4) goto L_0x0033;
    L_0x0017:
        r0 = r14.length;
        r5 = r0;
    L_0x0019:
        r0 = $assertionsDisabled;
        if (r0 != 0) goto L_0x0035;
    L_0x001d:
        r0 = 1;
        if (r0 > r5) goto L_0x0023;
    L_0x0020:
        r0 = 3;
        if (r5 <= r0) goto L_0x0035;
    L_0x0023:
        r0 = new java.lang.AssertionError;
        r0.<init>();
        throw r0;
    L_0x0029:
        r0 = 0;
    L_0x002a:
        r1 = 2;
        r6 = r0;
        r4 = r1;
        goto L_0x0013;
    L_0x002e:
        r0 = 0;
    L_0x002f:
        r1 = 3;
        r6 = r0;
        r4 = r1;
        goto L_0x0013;
    L_0x0033:
        r5 = r4;
        goto L_0x0019;
    L_0x0035:
        r0 = 3;
        r9 = new double[r0];
        r0 = 0;
        r10 = r0;
        r0 = r1;
        r1 = r10;
    L_0x003c:
        if (r1 >= r5) goto L_0x0059;
    L_0x003e:
        r2 = r14[r1];
        r2 = org.mozilla.javascript.ScriptRuntime.toNumber(r2);
        r7 = (r2 > r2 ? 1 : (r2 == r2 ? 0 : -1));
        if (r7 != 0) goto L_0x004e;
    L_0x0048:
        r7 = java.lang.Double.isInfinite(r2);
        if (r7 == 0) goto L_0x0052;
    L_0x004e:
        r0 = 1;
    L_0x004f:
        r1 = r1 + 1;
        goto L_0x003c;
    L_0x0052:
        r2 = org.mozilla.javascript.ScriptRuntime.toInteger(r2);
        r9[r1] = r2;
        goto L_0x004f;
    L_0x0059:
        if (r0 == 0) goto L_0x005e;
    L_0x005b:
        r0 = org.mozilla.javascript.ScriptRuntime.NaN;
        goto L_0x0005;
    L_0x005e:
        r2 = 0;
        r0 = (r12 > r12 ? 1 : (r12 == r12 ? 0 : -1));
        if (r0 == 0) goto L_0x009d;
    L_0x0063:
        r0 = 3;
        if (r4 >= r0) goto L_0x0069;
    L_0x0066:
        r0 = org.mozilla.javascript.ScriptRuntime.NaN;
        goto L_0x0005;
    L_0x0069:
        r12 = 0;
    L_0x006b:
        r0 = 3;
        if (r4 < r0) goto L_0x00a4;
    L_0x006e:
        if (r2 >= r5) goto L_0x00a4;
    L_0x0070:
        r8 = 1;
        r0 = r9[r2];
    L_0x0073:
        r2 = 2;
        if (r4 < r2) goto L_0x00ab;
    L_0x0076:
        if (r8 >= r5) goto L_0x00ab;
    L_0x0078:
        r7 = r8 + 1;
        r2 = r9[r8];
    L_0x007c:
        r8 = 1;
        if (r4 < r8) goto L_0x00b2;
    L_0x007f:
        if (r7 >= r5) goto L_0x00b2;
    L_0x0081:
        r4 = r7 + 1;
        r4 = r9[r7];
    L_0x0085:
        r0 = MakeDay(r0, r2, r4);
        r2 = TimeWithinDay(r12);
        r0 = MakeDate(r0, r2);
        if (r6 == 0) goto L_0x0097;
    L_0x0093:
        r0 = internalUTC(r0);
    L_0x0097:
        r0 = TimeClip(r0);
        goto L_0x0005;
    L_0x009d:
        if (r6 == 0) goto L_0x006b;
    L_0x009f:
        r12 = LocalTime(r12);
        goto L_0x006b;
    L_0x00a4:
        r0 = YearFromTime(r12);
        r0 = (double) r0;
        r8 = r2;
        goto L_0x0073;
    L_0x00ab:
        r2 = MonthFromTime(r12);
        r2 = (double) r2;
        r7 = r8;
        goto L_0x007c;
    L_0x00b2:
        r4 = DateFromTime(r12);
        r4 = (double) r4;
        goto L_0x0085;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeDate.makeDate(double, java.lang.Object[], int):double");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int findPrototypeId(java.lang.String r9) {
        /*
        r8 = this;
        r6 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        r2 = 3;
        r5 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        r4 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        r0 = 0;
        r1 = 0;
        r3 = r9.length();
        switch(r3) {
            case 6: goto L_0x001d;
            case 7: goto L_0x0037;
            case 8: goto L_0x0099;
            case 9: goto L_0x00ef;
            case 10: goto L_0x00f9;
            case 11: goto L_0x015d;
            case 12: goto L_0x01f3;
            case 13: goto L_0x0211;
            case 14: goto L_0x025b;
            case 15: goto L_0x0282;
            case 16: goto L_0x0010;
            case 17: goto L_0x029e;
            case 18: goto L_0x02a8;
            default: goto L_0x0010;
        };
    L_0x0010:
        r2 = r1;
        r1 = r0;
    L_0x0012:
        if (r2 == 0) goto L_0x02e6;
    L_0x0014:
        if (r2 == r9) goto L_0x02e6;
    L_0x0016:
        r2 = r2.equals(r9);
        if (r2 != 0) goto L_0x02e6;
    L_0x001c:
        return r0;
    L_0x001d:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x002c;
    L_0x0023:
        r1 = "getDay";
        r2 = 19;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x002c:
        if (r2 != r6) goto L_0x0010;
    L_0x002e:
        r1 = "toJSON";
        r2 = 47;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0037:
        r2 = r9.charAt(r2);
        switch(r2) {
            case 68: goto L_0x0041;
            case 84: goto L_0x005b;
            case 89: goto L_0x0075;
            case 117: goto L_0x008f;
            default: goto L_0x003e;
        };
    L_0x003e:
        r2 = r1;
        r1 = r0;
        goto L_0x0012;
    L_0x0041:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x0050;
    L_0x0047:
        r1 = "getDate";
        r2 = 17;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0050:
        if (r2 != r5) goto L_0x0010;
    L_0x0052:
        r1 = "setDate";
        r2 = 39;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x005b:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x006a;
    L_0x0061:
        r1 = "getTime";
        r2 = 11;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x006a:
        if (r2 != r5) goto L_0x0010;
    L_0x006c:
        r1 = "setTime";
        r2 = 30;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0075:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x0084;
    L_0x007b:
        r1 = "getYear";
        r2 = 12;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0084:
        if (r2 != r5) goto L_0x0010;
    L_0x0086:
        r1 = "setYear";
        r2 = 45;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x008f:
        r1 = "valueOf";
        r2 = 10;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0099:
        r2 = r9.charAt(r2);
        switch(r2) {
            case 72: goto L_0x00a4;
            case 77: goto L_0x00c0;
            case 111: goto L_0x00dc;
            case 116: goto L_0x00e6;
            default: goto L_0x00a0;
        };
    L_0x00a0:
        r2 = r1;
        r1 = r0;
        goto L_0x0012;
    L_0x00a4:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x00b4;
    L_0x00aa:
        r1 = "getHours";
        r2 = 21;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x00b4:
        if (r2 != r5) goto L_0x0010;
    L_0x00b6:
        r1 = "setHours";
        r2 = 37;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x00c0:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x00d0;
    L_0x00c6:
        r1 = "getMonth";
        r2 = 15;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x00d0:
        if (r2 != r5) goto L_0x0010;
    L_0x00d2:
        r1 = "setMonth";
        r2 = 41;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x00dc:
        r1 = "toSource";
        r2 = 9;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x00e6:
        r1 = "toString";
        r2 = 2;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x00ef:
        r1 = "getUTCDay";
        r2 = 20;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x00f9:
        r2 = r9.charAt(r2);
        r3 = 77;
        if (r2 != r3) goto L_0x011d;
    L_0x0101:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x0111;
    L_0x0107:
        r1 = "getMinutes";
        r2 = 23;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0111:
        if (r2 != r5) goto L_0x0010;
    L_0x0113:
        r1 = "setMinutes";
        r2 = 35;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x011d:
        r3 = 83;
        if (r2 != r3) goto L_0x013d;
    L_0x0121:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x0131;
    L_0x0127:
        r1 = "getSeconds";
        r2 = 25;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0131:
        if (r2 != r5) goto L_0x0010;
    L_0x0133:
        r1 = "setSeconds";
        r2 = 33;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x013d:
        r3 = 85;
        if (r2 != r3) goto L_0x0010;
    L_0x0141:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x0151;
    L_0x0147:
        r1 = "getUTCDate";
        r2 = 18;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0151:
        if (r2 != r5) goto L_0x0010;
    L_0x0153:
        r1 = "setUTCDate";
        r2 = 40;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x015d:
        r2 = r9.charAt(r2);
        switch(r2) {
            case 70: goto L_0x0168;
            case 77: goto L_0x0184;
            case 83: goto L_0x018e;
            case 84: goto L_0x0198;
            case 85: goto L_0x01a2;
            case 115: goto L_0x01ea;
            default: goto L_0x0164;
        };
    L_0x0164:
        r2 = r1;
        r1 = r0;
        goto L_0x0012;
    L_0x0168:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x0178;
    L_0x016e:
        r1 = "getFullYear";
        r2 = 13;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0178:
        if (r2 != r5) goto L_0x0010;
    L_0x017a:
        r1 = "setFullYear";
        r2 = 43;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0184:
        r1 = "toGMTString";
        r2 = 8;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x018e:
        r1 = "toISOString";
        r2 = 46;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0198:
        r1 = "toUTCString";
        r2 = 8;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x01a2:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x01c8;
    L_0x01a8:
        r2 = 9;
        r2 = r9.charAt(r2);
        r3 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        if (r2 != r3) goto L_0x01bc;
    L_0x01b2:
        r1 = "getUTCHours";
        r2 = 22;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x01bc:
        if (r2 != r6) goto L_0x0010;
    L_0x01be:
        r1 = "getUTCMonth";
        r2 = 16;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x01c8:
        if (r2 != r5) goto L_0x0010;
    L_0x01ca:
        r2 = 9;
        r2 = r9.charAt(r2);
        r3 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        if (r2 != r3) goto L_0x01de;
    L_0x01d4:
        r1 = "setUTCHours";
        r2 = 38;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x01de:
        if (r2 != r6) goto L_0x0010;
    L_0x01e0:
        r1 = "setUTCMonth";
        r2 = 42;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x01ea:
        r1 = "constructor";
        r2 = 1;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x01f3:
        r3 = 2;
        r3 = r9.charAt(r3);
        r4 = 68;
        if (r3 != r4) goto L_0x0205;
    L_0x01fc:
        r1 = "toDateString";
        r2 = 4;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0205:
        r4 = 84;
        if (r3 != r4) goto L_0x0010;
    L_0x0209:
        r1 = "toTimeString";
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0211:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x0238;
    L_0x0217:
        r2 = 6;
        r2 = r9.charAt(r2);
        r3 = 77;
        if (r2 != r3) goto L_0x022a;
    L_0x0220:
        r1 = "getUTCMinutes";
        r2 = 24;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x022a:
        r3 = 83;
        if (r2 != r3) goto L_0x0010;
    L_0x022e:
        r1 = "getUTCSeconds";
        r2 = 26;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0238:
        if (r2 != r5) goto L_0x0010;
    L_0x023a:
        r2 = 6;
        r2 = r9.charAt(r2);
        r3 = 77;
        if (r2 != r3) goto L_0x024d;
    L_0x0243:
        r1 = "setUTCMinutes";
        r2 = 36;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x024d:
        r3 = 83;
        if (r2 != r3) goto L_0x0010;
    L_0x0251:
        r1 = "setUTCSeconds";
        r2 = 34;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x025b:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x026b;
    L_0x0261:
        r1 = "getUTCFullYear";
        r2 = 14;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x026b:
        if (r2 != r5) goto L_0x0277;
    L_0x026d:
        r1 = "setUTCFullYear";
        r2 = 44;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0277:
        if (r2 != r6) goto L_0x0010;
    L_0x0279:
        r1 = "toLocaleString";
        r2 = 5;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0282:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x0292;
    L_0x0288:
        r1 = "getMilliseconds";
        r2 = 27;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x0292:
        if (r2 != r5) goto L_0x0010;
    L_0x0294:
        r1 = "setMilliseconds";
        r2 = 31;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x029e:
        r1 = "getTimezoneOffset";
        r2 = 29;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x02a8:
        r2 = r9.charAt(r0);
        if (r2 != r4) goto L_0x02b8;
    L_0x02ae:
        r1 = "getUTCMilliseconds";
        r2 = 28;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x02b8:
        if (r2 != r5) goto L_0x02c4;
    L_0x02ba:
        r1 = "setUTCMilliseconds";
        r2 = 32;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x02c4:
        if (r2 != r6) goto L_0x0010;
    L_0x02c6:
        r2 = 8;
        r2 = r9.charAt(r2);
        r3 = 68;
        if (r2 != r3) goto L_0x02d9;
    L_0x02d0:
        r1 = "toLocaleDateString";
        r2 = 7;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x02d9:
        r3 = 84;
        if (r2 != r3) goto L_0x0010;
    L_0x02dd:
        r1 = "toLocaleTimeString";
        r2 = 6;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0012;
    L_0x02e6:
        r0 = r1;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeDate.findPrototypeId(java.lang.String):int");
    }
}
