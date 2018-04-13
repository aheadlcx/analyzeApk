package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

final class MemberBox implements Serializable {
    private static final Class<?>[] primitives = new Class[]{Boolean.TYPE, Byte.TYPE, Character.TYPE, Double.TYPE, Float.TYPE, Integer.TYPE, Long.TYPE, Short.TYPE, Void.TYPE};
    static final long serialVersionUID = 6358550398665688245L;
    transient Class<?>[] argTypes;
    transient Object delegateTo;
    private transient Member memberObject;
    transient boolean vararg;

    MemberBox(Method method) {
        init(method);
    }

    MemberBox(Constructor<?> constructor) {
        init((Constructor) constructor);
    }

    private void init(Method method) {
        this.memberObject = method;
        this.argTypes = method.getParameterTypes();
        this.vararg = VMBridge.instance.isVarArgs(method);
    }

    private void init(Constructor<?> constructor) {
        this.memberObject = constructor;
        this.argTypes = constructor.getParameterTypes();
        this.vararg = VMBridge.instance.isVarArgs(constructor);
    }

    Method method() {
        return (Method) this.memberObject;
    }

    Constructor<?> ctor() {
        return (Constructor) this.memberObject;
    }

    Member member() {
        return this.memberObject;
    }

    boolean isMethod() {
        return this.memberObject instanceof Method;
    }

    boolean isCtor() {
        return this.memberObject instanceof Constructor;
    }

    boolean isStatic() {
        return Modifier.isStatic(this.memberObject.getModifiers());
    }

    String getName() {
        return this.memberObject.getName();
    }

    Class<?> getDeclaringClass() {
        return this.memberObject.getDeclaringClass();
    }

    String toJavaDeclaration() {
        StringBuilder stringBuilder = new StringBuilder();
        if (isMethod()) {
            Method method = method();
            stringBuilder.append(method.getReturnType());
            stringBuilder.append(' ');
            stringBuilder.append(method.getName());
        } else {
            String name = ctor().getDeclaringClass().getName();
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf >= 0) {
                name = name.substring(lastIndexOf + 1);
            }
            stringBuilder.append(name);
        }
        stringBuilder.append(JavaMembers.liveConnectSignature(this.argTypes));
        return stringBuilder.toString();
    }

    public String toString() {
        return this.memberObject.toString();
    }

    Object invoke(Object obj, Object[] objArr) {
        Method method = method();
        try {
            return method.invoke(obj, objArr);
        } catch (Throwable e) {
            Method searchAccessibleMethod = searchAccessibleMethod(method, this.argTypes);
            if (searchAccessibleMethod != null) {
                this.memberObject = searchAccessibleMethod;
            } else if (VMBridge.instance.tryToMakeAccessible(method)) {
                searchAccessibleMethod = method;
            } else {
                throw Context.throwAsScriptRuntimeEx(e);
            }
            return searchAccessibleMethod.invoke(obj, objArr);
        } catch (InvocationTargetException e2) {
            InvocationTargetException e3 = e2;
            do {
                e3 = e3.getTargetException();
            } while (e3 instanceof InvocationTargetException);
            if (e3 instanceof ContinuationPending) {
                throw ((ContinuationPending) e3);
            }
            throw Context.throwAsScriptRuntimeEx(e3);
        } catch (Throwable e4) {
            throw Context.throwAsScriptRuntimeEx(e4);
        }
    }

    Object newInstance(Object[] objArr) {
        Object newInstance;
        Constructor ctor = ctor();
        try {
            newInstance = ctor.newInstance(objArr);
        } catch (Throwable e) {
            if (VMBridge.instance.tryToMakeAccessible(ctor)) {
                newInstance = ctor.newInstance(objArr);
            } else {
                throw Context.throwAsScriptRuntimeEx(e);
            }
        } catch (Throwable e2) {
            throw Context.throwAsScriptRuntimeEx(e2);
        }
        return newInstance;
    }

    private static Method searchAccessibleMethod(Method method, Class<?>[] clsArr) {
        int modifiers = method.getModifiers();
        if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers)) {
            Class declaringClass = method.getDeclaringClass();
            if (!Modifier.isPublic(declaringClass.getModifiers())) {
                String name = method.getName();
                Class[] interfaces = declaringClass.getInterfaces();
                int i = 0;
                int length = interfaces.length;
                while (i != length) {
                    Class cls = interfaces[i];
                    if (Modifier.isPublic(cls.getModifiers())) {
                        try {
                            return cls.getMethod(name, clsArr);
                        } catch (NoSuchMethodException e) {
                        } catch (SecurityException e2) {
                        }
                    } else {
                        i++;
                    }
                }
                while (true) {
                    declaringClass = declaringClass.getSuperclass();
                    if (declaringClass == null) {
                        break;
                    } else if (Modifier.isPublic(declaringClass.getModifiers())) {
                        try {
                            Method method2 = declaringClass.getMethod(name, clsArr);
                            int modifiers2 = method2.getModifiers();
                            if (Modifier.isPublic(modifiers2) && !Modifier.isStatic(modifiers2)) {
                                return method2;
                            }
                        } catch (NoSuchMethodException e3) {
                        } catch (SecurityException e4) {
                        }
                    }
                }
            }
        }
        return null;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        Member readMember = readMember(objectInputStream);
        if (readMember instanceof Method) {
            init((Method) readMember);
        } else {
            init((Constructor) readMember);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        writeMember(objectOutputStream, this.memberObject);
    }

    private static void writeMember(ObjectOutputStream objectOutputStream, Member member) throws IOException {
        if (member == null) {
            objectOutputStream.writeBoolean(false);
            return;
        }
        objectOutputStream.writeBoolean(true);
        if ((member instanceof Method) || (member instanceof Constructor)) {
            objectOutputStream.writeBoolean(member instanceof Method);
            objectOutputStream.writeObject(member.getName());
            objectOutputStream.writeObject(member.getDeclaringClass());
            if (member instanceof Method) {
                writeParameters(objectOutputStream, ((Method) member).getParameterTypes());
                return;
            } else {
                writeParameters(objectOutputStream, ((Constructor) member).getParameterTypes());
                return;
            }
        }
        throw new IllegalArgumentException("not Method or Constructor");
    }

    private static Member readMember(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        if (!objectInputStream.readBoolean()) {
            return null;
        }
        boolean readBoolean = objectInputStream.readBoolean();
        String str = (String) objectInputStream.readObject();
        Class cls = (Class) objectInputStream.readObject();
        Class[] readParameters = readParameters(objectInputStream);
        if (!readBoolean) {
            return cls.getConstructor(readParameters);
        }
        try {
            return cls.getMethod(str, readParameters);
        } catch (NoSuchMethodException e) {
            throw new IOException("Cannot find member: " + e);
        }
    }

    private static void writeParameters(ObjectOutputStream objectOutputStream, Class<?>[] clsArr) throws IOException {
        objectOutputStream.writeShort(clsArr.length);
        for (Class cls : clsArr) {
            boolean isPrimitive = cls.isPrimitive();
            objectOutputStream.writeBoolean(isPrimitive);
            if (isPrimitive) {
                int i = 0;
                while (i < primitives.length) {
                    if (cls.equals(primitives[i])) {
                        objectOutputStream.writeByte(i);
                    } else {
                        i++;
                    }
                }
                throw new IllegalArgumentException("Primitive " + cls + " not found");
            }
            objectOutputStream.writeObject(cls);
        }
    }

    private static Class<?>[] readParameters(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Class<?>[] clsArr = new Class[objectInputStream.readShort()];
        for (int i = 0; i < clsArr.length; i++) {
            if (objectInputStream.readBoolean()) {
                clsArr[i] = primitives[objectInputStream.readByte()];
            } else {
                clsArr[i] = (Class) objectInputStream.readObject();
            }
        }
        return clsArr;
    }
}
