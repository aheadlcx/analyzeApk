package qsbk.app.utils.comm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import qsbk.app.core.model.CustomButton;

public class ArrayUtils {

    public static abstract class EqualeOP<T> {
        public boolean test(T t, int i) {
            return test(t);
        }

        public boolean test(T t) {
            return false;
        }
    }

    public interface Processor<T, E> {
        E process(T t);
    }

    public static <T, E> void each(List<T> list, Processor<T, E> processor) {
        if (!isEmpty(list) && processor != null) {
            for (int i = 0; i < list.size(); i++) {
                processor.process(list.get(i));
            }
        }
    }

    public static <T, E> List<E> map(List<T> list, Processor<T, E> processor) {
        if (list == null) {
            return null;
        }
        List<E> arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(processor.process(list.get(i)));
        }
        return arrayList;
    }

    public static <T> int find(T[] tArr, T t) {
        if (t == null) {
            return -1;
        }
        for (int i = 0; i < tArr.length; i++) {
            if (tArr[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    public static <T> int find(List<T> list, T t) {
        if (t == null) {
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> List<T> toList(Set<T> set) {
        if (set == null) {
            return null;
        }
        List<T> linkedList = new LinkedList();
        for (T add : set) {
            linkedList.add(add);
        }
        return linkedList;
    }

    public static int find(int[] iArr, int i) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] == i) {
                return i2;
            }
        }
        return -1;
    }

    public static int find(int[] iArr, long j) {
        for (int i = 0; i < iArr.length; i++) {
            if (((long) iArr[i]) == j) {
                return i;
            }
        }
        return -1;
    }

    public static <T> String join(List<T> list, String str) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int size = list.size();
        int i = size - 1;
        for (int i2 = 0; i2 < size; i2++) {
            stringBuilder.append(list.get(i2).toString());
            if (i2 != i) {
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }

    public static <T> String join(List<T> list, String str, String str2) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int size = list.size();
        int i = size - 1;
        for (int i2 = 0; i2 < size; i2++) {
            Field field;
            Object obj = list.get(i2);
            if (str2 != null) {
                try {
                    field = obj.getClass().getField(str2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (field == null) {
                    try {
                        field.setAccessible(true);
                        stringBuilder.append(field.get(obj));
                        field.setAccessible(false);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else {
                    stringBuilder.append(obj.toString());
                }
                if (i2 != i) {
                    stringBuilder.append(str);
                }
            }
            field = null;
            if (field == null) {
                stringBuilder.append(obj.toString());
            } else {
                field.setAccessible(true);
                stringBuilder.append(field.get(obj));
                field.setAccessible(false);
            }
            if (i2 != i) {
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }

    public static <T> T findFirst(List<T> list, EqualeOP<T> equaleOP) {
        if (equaleOP == null) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            if (equaleOP.test(list.get(i), i)) {
                return list.get(i);
            }
        }
        return null;
    }

    public static <T> T findLast(List<T> list, EqualeOP<T> equaleOP) {
        if (equaleOP == null) {
            return null;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            if (equaleOP.test(list.get(size), size)) {
                return list.get(size);
            }
        }
        return null;
    }

    public static <T> List<T> filter(List<T> list, EqualeOP<T> equaleOP) {
        if (list == null || list.size() == 0) {
            return list;
        }
        List<T> arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (equaleOP.test(list.get(i), i)) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    public static <T> List<T> remove(List<T> list, EqualeOP<T> equaleOP) {
        if (!(list == null || list.size() == 0)) {
            for (int size = list.size() - 1; size >= 0; size--) {
                if (equaleOP.test(list.get(size), size)) {
                    list.remove(size);
                }
            }
        }
        return list;
    }

    public static <T> List<T> keep(List<T> list, EqualeOP<T> equaleOP) {
        if (!(list == null || list.size() == 0)) {
            for (int size = list.size() - 1; size >= 0; size--) {
                if (!equaleOP.test(list.get(size), size)) {
                    list.remove(size);
                }
            }
        }
        return list;
    }

    public static <T> List<T> reverse(List<T> list) {
        if (!(list == null || list.size() == 0)) {
            int size = list.size() - 1;
            for (int i = 0; i < list.size() / 2; i++) {
                Object obj = list.get(i);
                list.set(i, list.get(size - i));
                list.set(size - i, obj);
            }
        }
        return list;
    }

    public static <T> void sort(List<T> list, Comparator<T> comparator) {
        if (list != null && list.size() != 0) {
            Object[] toArray = list.toArray();
            Arrays.sort(toArray, comparator);
            for (int i = 0; i < list.size(); i++) {
                list.set(i, toArray[i]);
            }
        }
    }

    public static <T> ArrayList<T> from(T[] tArr) {
        ArrayList<T> arrayList = new ArrayList();
        for (Object add : tArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static int size(List list) {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static <T> int indexOf(T t, T[] tArr) {
        int i = 0;
        while (i < tArr.length) {
            if (tArr[i] != null && tArr[i].equals(t)) {
                return i;
            }
            if (t == null && tArr[i] == null) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static long[] copyOfRange(long[] jArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = jArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        length = Math.min(i2 - i, length - i);
        Object obj = new long[length];
        System.arraycopy(jArr, i, obj, 0, length);
        return obj;
    }

    public static String[] copyOfRange(String[] strArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = strArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        length = Math.min(i2 - i, length - i);
        Object obj = new String[length];
        System.arraycopy(strArr, i, obj, 0, length);
        return obj;
    }

    public static void main(String[] strArr) {
        List from = from(new String[]{"a", CustomButton.POSITION_BOTTOM, "c", "d"});
        reverse(from);
        System.out.println(join(from, "|"));
    }
}
