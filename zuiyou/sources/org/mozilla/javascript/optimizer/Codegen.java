package org.mozilla.javascript.optimizer;

import android.support.v4.app.NotificationCompat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mozilla.classfile.ClassFileWriter;
import org.mozilla.classfile.ClassFileWriter.ClassFileFormatException;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Evaluator;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.SecurityController;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.ScriptNode;

public class Codegen implements Evaluator {
    static final String DEFAULT_MAIN_METHOD_CLASS = "org.mozilla.javascript.optimizer.OptRuntime";
    static final String FUNCTION_CONSTRUCTOR_SIGNATURE = "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;I)V";
    static final String FUNCTION_INIT_SIGNATURE = "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)V";
    static final String ID_FIELD_NAME = "_id";
    static final String REGEXP_INIT_METHOD_NAME = "_reInit";
    static final String REGEXP_INIT_METHOD_SIGNATURE = "(Lorg/mozilla/javascript/Context;)V";
    private static final String SUPER_CLASS_NAME = "org.mozilla.javascript.NativeFunction";
    private static final Object globalLock = new Object();
    private static int globalSerialClassCounter;
    private CompilerEnvirons compilerEnv;
    private ObjArray directCallTargets;
    private double[] itsConstantList;
    private int itsConstantListSize;
    String mainClassName;
    String mainClassSignature;
    private String mainMethodClass = DEFAULT_MAIN_METHOD_CLASS;
    private ObjToIntMap scriptOrFnIndexes;
    ScriptNode[] scriptOrFnNodes;

    public void captureStackInfo(RhinoException rhinoException) {
        throw new UnsupportedOperationException();
    }

    public String getSourcePositionFromStack(Context context, int[] iArr) {
        throw new UnsupportedOperationException();
    }

    public String getPatchedStack(RhinoException rhinoException, String str) {
        throw new UnsupportedOperationException();
    }

    public List<String> getScriptStack(RhinoException rhinoException) {
        throw new UnsupportedOperationException();
    }

    public void setEvalScriptFlag(Script script) {
        throw new UnsupportedOperationException();
    }

    public Object compile(CompilerEnvirons compilerEnvirons, ScriptNode scriptNode, String str, boolean z) {
        int i;
        synchronized (globalLock) {
            i = globalSerialClassCounter + 1;
            globalSerialClassCounter = i;
        }
        String str2 = "c";
        if (scriptNode.getSourceName().length() > 0) {
            str2 = scriptNode.getSourceName().replaceAll("\\W", "_");
            if (!Character.isJavaIdentifierStart(str2.charAt(0))) {
                str2 = "_" + str2;
            }
        }
        byte[] compileToClassFile = compileToClassFile(compilerEnvirons, "org.mozilla.javascript.gen." + str2 + "_" + i, scriptNode, str, z);
        return new Object[]{r2, compileToClassFile};
    }

    public Script createScriptObject(Object obj, Object obj2) {
        try {
            return (Script) defineClass(obj, obj2).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Unable to instantiate compiled class:" + e.toString());
        }
    }

    public Function createFunctionObject(Context context, Scriptable scriptable, Object obj, Object obj2) {
        try {
            return (NativeFunction) defineClass(obj, obj2).getConstructors()[0].newInstance(new Object[]{scriptable, context, Integer.valueOf(0)});
        } catch (Exception e) {
            throw new RuntimeException("Unable to instantiate compiled class:" + e.toString());
        }
    }

    private Class<?> defineClass(Object obj, Object obj2) {
        Object e;
        Object[] objArr = (Object[]) obj;
        String str = (String) objArr[0];
        byte[] bArr = (byte[]) objArr[1];
        GeneratedClassLoader createLoader = SecurityController.createLoader(getClass().getClassLoader(), obj2);
        try {
            Class<?> defineClass = createLoader.defineClass(str, bArr);
            createLoader.linkClass(defineClass);
            return defineClass;
        } catch (SecurityException e2) {
            e = e2;
            throw new RuntimeException("Malformed optimizer package " + e);
        } catch (IllegalArgumentException e3) {
            e = e3;
            throw new RuntimeException("Malformed optimizer package " + e);
        }
    }

    public byte[] compileToClassFile(CompilerEnvirons compilerEnvirons, String str, ScriptNode scriptNode, String str2, boolean z) {
        this.compilerEnv = compilerEnvirons;
        transform(scriptNode);
        if (z) {
            scriptNode = scriptNode.getFunctionNode(0);
        }
        initScriptNodesData(scriptNode);
        this.mainClassName = str;
        this.mainClassSignature = ClassFileWriter.classNameToSignature(str);
        try {
            return generateCode(str2);
        } catch (ClassFileFormatException e) {
            throw reportClassFileFormatException(scriptNode, e.getMessage());
        }
    }

    private RuntimeException reportClassFileFormatException(ScriptNode scriptNode, String str) {
        return Context.reportRuntimeError(scriptNode instanceof FunctionNode ? ScriptRuntime.getMessage2("msg.while.compiling.fn", ((FunctionNode) scriptNode).getFunctionName(), str) : ScriptRuntime.getMessage1("msg.while.compiling.script", str), scriptNode.getSourceName(), scriptNode.getLineno(), null, 0);
    }

    private void transform(ScriptNode scriptNode) {
        initOptFunctions_r(scriptNode);
        int optimizationLevel = this.compilerEnv.getOptimizationLevel();
        Map map = null;
        if (optimizationLevel > 0 && scriptNode.getType() == 136) {
            int functionCount = scriptNode.getFunctionCount();
            for (int i = 0; i != functionCount; i++) {
                OptFunctionNode optFunctionNode = OptFunctionNode.get(scriptNode, i);
                if (optFunctionNode.fnode.getFunctionType() == 1) {
                    String name = optFunctionNode.fnode.getName();
                    if (name.length() != 0) {
                        if (map == null) {
                            map = new HashMap();
                        }
                        map.put(name, optFunctionNode);
                    }
                }
            }
        }
        if (map != null) {
            this.directCallTargets = new ObjArray();
        }
        new OptTransformer(map, this.directCallTargets).transform(scriptNode);
        if (optimizationLevel > 0) {
            new Optimizer().optimize(scriptNode);
        }
    }

    private static void initOptFunctions_r(ScriptNode scriptNode) {
        int functionCount = scriptNode.getFunctionCount();
        for (int i = 0; i != functionCount; i++) {
            ScriptNode functionNode = scriptNode.getFunctionNode(i);
            OptFunctionNode optFunctionNode = new OptFunctionNode(functionNode);
            initOptFunctions_r(functionNode);
        }
    }

    private void initScriptNodesData(ScriptNode scriptNode) {
        ObjArray objArray = new ObjArray();
        collectScriptNodes_r(scriptNode, objArray);
        int size = objArray.size();
        this.scriptOrFnNodes = new ScriptNode[size];
        objArray.toArray(this.scriptOrFnNodes);
        this.scriptOrFnIndexes = new ObjToIntMap(size);
        for (int i = 0; i != size; i++) {
            this.scriptOrFnIndexes.put(this.scriptOrFnNodes[i], i);
        }
    }

    private static void collectScriptNodes_r(ScriptNode scriptNode, ObjArray objArray) {
        objArray.add(scriptNode);
        int functionCount = scriptNode.getFunctionCount();
        for (int i = 0; i != functionCount; i++) {
            collectScriptNodes_r(scriptNode.getFunctionNode(i), objArray);
        }
    }

    private byte[] generateCode(String str) {
        int i = 1;
        int i2 = 0;
        int i3 = this.scriptOrFnNodes[0].getType() == 136 ? 1 : 0;
        if (this.scriptOrFnNodes.length <= 1 && i3 != 0) {
            i = 0;
        }
        String str2 = null;
        if (this.compilerEnv.isGenerateDebugInfo()) {
            str2 = this.scriptOrFnNodes[0].getSourceName();
        }
        ClassFileWriter classFileWriter = new ClassFileWriter(this.mainClassName, SUPER_CLASS_NAME, str2);
        classFileWriter.addField(ID_FIELD_NAME, "I", (short) 2);
        if (i != 0) {
            generateFunctionConstructor(classFileWriter);
        }
        if (i3 != 0) {
            classFileWriter.addInterface("org/mozilla/javascript/Script");
            generateScriptCtor(classFileWriter);
            generateMain(classFileWriter);
            generateExecute(classFileWriter);
        }
        generateCallMethod(classFileWriter);
        generateResumeGenerator(classFileWriter);
        generateNativeFunctionOverrides(classFileWriter, str);
        i3 = this.scriptOrFnNodes.length;
        while (i2 != i3) {
            ScriptNode scriptNode = this.scriptOrFnNodes[i2];
            BodyCodegen bodyCodegen = new BodyCodegen();
            bodyCodegen.cfw = classFileWriter;
            bodyCodegen.codegen = this;
            bodyCodegen.compilerEnv = this.compilerEnv;
            bodyCodegen.scriptOrFn = scriptNode;
            bodyCodegen.scriptOrFnIndex = i2;
            try {
                bodyCodegen.generateBodyCode();
                if (scriptNode.getType() == 109) {
                    OptFunctionNode optFunctionNode = OptFunctionNode.get(scriptNode);
                    generateFunctionInit(classFileWriter, optFunctionNode);
                    if (optFunctionNode.isTargetOfDirectCall()) {
                        emitDirectConstructor(classFileWriter, optFunctionNode);
                    }
                }
                i2++;
            } catch (ClassFileFormatException e) {
                throw reportClassFileFormatException(scriptNode, e.getMessage());
            }
        }
        emitRegExpInit(classFileWriter);
        emitConstantDudeInitializers(classFileWriter);
        return classFileWriter.toByteArray();
    }

    private void emitDirectConstructor(ClassFileWriter classFileWriter, OptFunctionNode optFunctionNode) {
        int i = 0;
        classFileWriter.startMethod(getDirectCtorName(optFunctionNode.fnode), getBodyMethodSignature(optFunctionNode.fnode), (short) 10);
        int paramCount = optFunctionNode.fnode.getParamCount();
        int i2 = ((paramCount * 3) + 4) + 1;
        classFileWriter.addALoad(0);
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.addInvoke(182, "org/mozilla/javascript/BaseFunction", "createObject", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.addAStore(i2);
        classFileWriter.addALoad(0);
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.addALoad(i2);
        while (i < paramCount) {
            classFileWriter.addALoad((i * 3) + 4);
            classFileWriter.addDLoad((i * 3) + 5);
            i++;
        }
        classFileWriter.addALoad((paramCount * 3) + 4);
        classFileWriter.addInvoke(184, this.mainClassName, getBodyMethodName(optFunctionNode.fnode), getBodyMethodSignature(optFunctionNode.fnode));
        i = classFileWriter.acquireLabel();
        classFileWriter.add(89);
        classFileWriter.add(193, "org/mozilla/javascript/Scriptable");
        classFileWriter.add(153, i);
        classFileWriter.add(192, "org/mozilla/javascript/Scriptable");
        classFileWriter.add(176);
        classFileWriter.markLabel(i);
        classFileWriter.addALoad(i2);
        classFileWriter.add(176);
        classFileWriter.stopMethod((short) (i2 + 1));
    }

    static boolean isGenerator(ScriptNode scriptNode) {
        return scriptNode.getType() == 109 && ((FunctionNode) scriptNode).isGenerator();
    }

    private void generateResumeGenerator(ClassFileWriter classFileWriter) {
        int addTableSwitch;
        int i = 0;
        int i2 = 0;
        for (ScriptNode isGenerator : this.scriptOrFnNodes) {
            if (isGenerator(isGenerator)) {
                i2 = 1;
            }
        }
        if (i2 != 0) {
            classFileWriter.startMethod("resumeGenerator", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", (short) 17);
            classFileWriter.addALoad(0);
            classFileWriter.addALoad(1);
            classFileWriter.addALoad(2);
            classFileWriter.addALoad(4);
            classFileWriter.addALoad(5);
            classFileWriter.addILoad(3);
            classFileWriter.addLoadThis();
            classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
            addTableSwitch = classFileWriter.addTableSwitch(0, this.scriptOrFnNodes.length - 1);
            classFileWriter.markTableSwitchDefault(addTableSwitch);
            i2 = classFileWriter.acquireLabel();
            while (i < this.scriptOrFnNodes.length) {
                ScriptNode scriptNode = this.scriptOrFnNodes[i];
                classFileWriter.markTableSwitchCase(addTableSwitch, i, 6);
                if (isGenerator(scriptNode)) {
                    classFileWriter.addInvoke(184, this.mainClassName, getBodyMethodName(scriptNode) + "_gen", "(" + this.mainClassSignature + "Lorg/mozilla/javascript/Context;" + "Lorg/mozilla/javascript/Scriptable;" + "Ljava/lang/Object;" + "Ljava/lang/Object;I)Ljava/lang/Object;");
                    classFileWriter.add(176);
                } else {
                    classFileWriter.add(167, i2);
                }
                i++;
            }
            classFileWriter.markLabel(i2);
            pushUndefined(classFileWriter);
            classFileWriter.add(176);
            classFileWriter.stopMethod((short) 6);
        }
    }

    private void generateCallMethod(ClassFileWriter classFileWriter) {
        int addTableSwitch;
        classFileWriter.startMethod(NotificationCompat.CATEGORY_CALL, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;", (short) 17);
        int acquireLabel = classFileWriter.acquireLabel();
        classFileWriter.addALoad(1);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "hasTopCall", "(Lorg/mozilla/javascript/Context;)Z");
        classFileWriter.add(154, acquireLabel);
        classFileWriter.addALoad(0);
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.addALoad(3);
        classFileWriter.addALoad(4);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "doTopCall", "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;");
        classFileWriter.add(176);
        classFileWriter.markLabel(acquireLabel);
        classFileWriter.addALoad(0);
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.addALoad(3);
        classFileWriter.addALoad(4);
        int length = this.scriptOrFnNodes.length;
        int i = 2 <= length ? 1 : 0;
        if (i != 0) {
            classFileWriter.addLoadThis();
            classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
            addTableSwitch = classFileWriter.addTableSwitch(1, length - 1);
        } else {
            addTableSwitch = 0;
        }
        acquireLabel = 0;
        for (int i2 = 0; i2 != length; i2++) {
            ScriptNode scriptNode = this.scriptOrFnNodes[i2];
            if (i != 0) {
                if (i2 == 0) {
                    classFileWriter.markTableSwitchDefault(addTableSwitch);
                    acquireLabel = classFileWriter.getStackTop();
                } else {
                    classFileWriter.markTableSwitchCase(addTableSwitch, i2 - 1, acquireLabel);
                }
            }
            if (scriptNode.getType() == 109) {
                OptFunctionNode optFunctionNode = OptFunctionNode.get(scriptNode);
                if (optFunctionNode.isTargetOfDirectCall()) {
                    int paramCount = optFunctionNode.fnode.getParamCount();
                    if (paramCount != 0) {
                        for (int i3 = 0; i3 != paramCount; i3++) {
                            classFileWriter.add(190);
                            classFileWriter.addPush(i3);
                            int acquireLabel2 = classFileWriter.acquireLabel();
                            int acquireLabel3 = classFileWriter.acquireLabel();
                            classFileWriter.add(164, acquireLabel2);
                            classFileWriter.addALoad(4);
                            classFileWriter.addPush(i3);
                            classFileWriter.add(50);
                            classFileWriter.add(167, acquireLabel3);
                            classFileWriter.markLabel(acquireLabel2);
                            pushUndefined(classFileWriter);
                            classFileWriter.markLabel(acquireLabel3);
                            classFileWriter.adjustStackTop(-1);
                            classFileWriter.addPush(0.0d);
                            classFileWriter.addALoad(4);
                        }
                    }
                }
            }
            classFileWriter.addInvoke(184, this.mainClassName, getBodyMethodName(scriptNode), getBodyMethodSignature(scriptNode));
            classFileWriter.add(176);
        }
        classFileWriter.stopMethod((short) 5);
    }

    private void generateMain(ClassFileWriter classFileWriter) {
        classFileWriter.startMethod("main", "([Ljava/lang/String;)V", (short) 9);
        classFileWriter.add(187, classFileWriter.getClassName());
        classFileWriter.add(89);
        classFileWriter.addInvoke(183, classFileWriter.getClassName(), "<init>", "()V");
        classFileWriter.add(42);
        classFileWriter.addInvoke(184, this.mainMethodClass, "main", "(Lorg/mozilla/javascript/Script;[Ljava/lang/String;)V");
        classFileWriter.add(177);
        classFileWriter.stopMethod((short) 1);
    }

    private void generateExecute(ClassFileWriter classFileWriter) {
        classFileWriter.startMethod("exec", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;", (short) 17);
        classFileWriter.addLoadThis();
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.add(89);
        classFileWriter.add(1);
        classFileWriter.addInvoke(182, classFileWriter.getClassName(), NotificationCompat.CATEGORY_CALL, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;");
        classFileWriter.add(176);
        classFileWriter.stopMethod((short) 3);
    }

    private void generateScriptCtor(ClassFileWriter classFileWriter) {
        classFileWriter.startMethod("<init>", "()V", (short) 1);
        classFileWriter.addLoadThis();
        classFileWriter.addInvoke(183, SUPER_CLASS_NAME, "<init>", "()V");
        classFileWriter.addLoadThis();
        classFileWriter.addPush(0);
        classFileWriter.add(181, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
        classFileWriter.add(177);
        classFileWriter.stopMethod((short) 1);
    }

    private void generateFunctionConstructor(ClassFileWriter classFileWriter) {
        int i;
        int i2 = 0;
        classFileWriter.startMethod("<init>", FUNCTION_CONSTRUCTOR_SIGNATURE, (short) 1);
        classFileWriter.addALoad(0);
        classFileWriter.addInvoke(183, SUPER_CLASS_NAME, "<init>", "()V");
        classFileWriter.addLoadThis();
        classFileWriter.addILoad(3);
        classFileWriter.add(181, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
        classFileWriter.addLoadThis();
        classFileWriter.addALoad(2);
        classFileWriter.addALoad(1);
        if (this.scriptOrFnNodes[0].getType() == 136) {
            i = 1;
        } else {
            i = 0;
        }
        int length = this.scriptOrFnNodes.length;
        if (i == length) {
            throw badTree();
        }
        int addTableSwitch;
        short s = 2 <= length - i ? (short) 1 : (short) 0;
        if (s != (short) 0) {
            classFileWriter.addILoad(3);
            addTableSwitch = classFileWriter.addTableSwitch(i + 1, length - 1);
        } else {
            addTableSwitch = 0;
        }
        for (int i3 = i; i3 != length; i3++) {
            if (s != (short) 0) {
                if (i3 == i) {
                    classFileWriter.markTableSwitchDefault(addTableSwitch);
                    i2 = classFileWriter.getStackTop();
                } else {
                    classFileWriter.markTableSwitchCase(addTableSwitch, (i3 - 1) - i, i2);
                }
            }
            classFileWriter.addInvoke(183, this.mainClassName, getFunctionInitMethodName(OptFunctionNode.get(this.scriptOrFnNodes[i3])), FUNCTION_INIT_SIGNATURE);
            classFileWriter.add(177);
        }
        classFileWriter.stopMethod((short) 4);
    }

    private void generateFunctionInit(ClassFileWriter classFileWriter, OptFunctionNode optFunctionNode) {
        classFileWriter.startMethod(getFunctionInitMethodName(optFunctionNode), FUNCTION_INIT_SIGNATURE, (short) 18);
        classFileWriter.addLoadThis();
        classFileWriter.addALoad(1);
        classFileWriter.addALoad(2);
        classFileWriter.addInvoke(182, "org/mozilla/javascript/NativeFunction", "initScriptFunction", FUNCTION_INIT_SIGNATURE);
        if (optFunctionNode.fnode.getRegexpCount() != 0) {
            classFileWriter.addALoad(1);
            classFileWriter.addInvoke(184, this.mainClassName, REGEXP_INIT_METHOD_NAME, REGEXP_INIT_METHOD_SIGNATURE);
        }
        classFileWriter.add(177);
        classFileWriter.stopMethod((short) 3);
    }

    private void generateNativeFunctionOverrides(ClassFileWriter classFileWriter, String str) {
        classFileWriter.startMethod("getLanguageVersion", "()I", (short) 1);
        classFileWriter.addPush(this.compilerEnv.getLanguageVersion());
        classFileWriter.add(172);
        classFileWriter.stopMethod((short) 1);
        for (int i = 0; i != 6; i++) {
            if (i != 4 || str != null) {
                short s;
                int addTableSwitch;
                switch (i) {
                    case 0:
                        classFileWriter.startMethod("getFunctionName", "()Ljava/lang/String;", (short) 1);
                        s = (short) 1;
                        break;
                    case 1:
                        classFileWriter.startMethod("getParamCount", "()I", (short) 1);
                        s = (short) 1;
                        break;
                    case 2:
                        classFileWriter.startMethod("getParamAndVarCount", "()I", (short) 1);
                        s = (short) 1;
                        break;
                    case 3:
                        classFileWriter.startMethod("getParamOrVarName", "(I)Ljava/lang/String;", (short) 1);
                        s = (short) 2;
                        break;
                    case 4:
                        classFileWriter.startMethod("getEncodedSource", "()Ljava/lang/String;", (short) 1);
                        classFileWriter.addPush(str);
                        s = (short) 1;
                        break;
                    case 5:
                        classFileWriter.startMethod("getParamOrVarConst", "(I)Z", (short) 1);
                        s = (short) 3;
                        break;
                    default:
                        throw Kit.codeBug();
                }
                int length = this.scriptOrFnNodes.length;
                short s2 = (short) 0;
                if (length > 1) {
                    classFileWriter.addLoadThis();
                    classFileWriter.add(180, classFileWriter.getClassName(), ID_FIELD_NAME, "I");
                    addTableSwitch = classFileWriter.addTableSwitch(1, length - 1);
                } else {
                    addTableSwitch = 0;
                }
                int i2 = 0;
                while (i2 != length) {
                    short stackTop;
                    ScriptNode scriptNode = this.scriptOrFnNodes[i2];
                    if (i2 == 0) {
                        if (length > 1) {
                            classFileWriter.markTableSwitchDefault(addTableSwitch);
                            stackTop = classFileWriter.getStackTop();
                        }
                        stackTop = s2;
                    } else {
                        classFileWriter.markTableSwitchCase(addTableSwitch, i2 - 1, s2);
                        stackTop = s2;
                    }
                    int addTableSwitch2;
                    int i3;
                    switch (i) {
                        case 0:
                            if (scriptNode.getType() == 136) {
                                classFileWriter.addPush("");
                            } else {
                                classFileWriter.addPush(((FunctionNode) scriptNode).getName());
                            }
                            classFileWriter.add(176);
                            break;
                        case 1:
                            classFileWriter.addPush(scriptNode.getParamCount());
                            classFileWriter.add(172);
                            break;
                        case 2:
                            classFileWriter.addPush(scriptNode.getParamAndVarCount());
                            classFileWriter.add(172);
                            break;
                        case 3:
                            int paramAndVarCount = scriptNode.getParamAndVarCount();
                            if (paramAndVarCount != 0) {
                                if (paramAndVarCount != 1) {
                                    classFileWriter.addILoad(1);
                                    addTableSwitch2 = classFileWriter.addTableSwitch(1, paramAndVarCount - 1);
                                    for (i3 = 0; i3 != paramAndVarCount; i3++) {
                                        if (classFileWriter.getStackTop() != (short) 0) {
                                            Kit.codeBug();
                                        }
                                        String paramOrVarName = scriptNode.getParamOrVarName(i3);
                                        if (i3 == 0) {
                                            classFileWriter.markTableSwitchDefault(addTableSwitch2);
                                        } else {
                                            classFileWriter.markTableSwitchCase(addTableSwitch2, i3 - 1, 0);
                                        }
                                        classFileWriter.addPush(paramOrVarName);
                                        classFileWriter.add(176);
                                    }
                                    break;
                                }
                                classFileWriter.addPush(scriptNode.getParamOrVarName(0));
                                classFileWriter.add(176);
                                break;
                            }
                            classFileWriter.add(1);
                            classFileWriter.add(176);
                            break;
                        case 4:
                            classFileWriter.addPush(scriptNode.getEncodedSourceStart());
                            classFileWriter.addPush(scriptNode.getEncodedSourceEnd());
                            classFileWriter.addInvoke(182, "java/lang/String", "substring", "(II)Ljava/lang/String;");
                            classFileWriter.add(176);
                            break;
                        case 5:
                            i3 = scriptNode.getParamAndVarCount();
                            boolean[] paramAndVarConst = scriptNode.getParamAndVarConst();
                            if (i3 != 0) {
                                if (i3 != 1) {
                                    classFileWriter.addILoad(1);
                                    addTableSwitch2 = classFileWriter.addTableSwitch(1, i3 - 1);
                                    for (int i4 = 0; i4 != i3; i4++) {
                                        if (classFileWriter.getStackTop() != (short) 0) {
                                            Kit.codeBug();
                                        }
                                        if (i4 == 0) {
                                            classFileWriter.markTableSwitchDefault(addTableSwitch2);
                                        } else {
                                            classFileWriter.markTableSwitchCase(addTableSwitch2, i4 - 1, 0);
                                        }
                                        classFileWriter.addPush(paramAndVarConst[i4]);
                                        classFileWriter.add(172);
                                    }
                                    break;
                                }
                                classFileWriter.addPush(paramAndVarConst[0]);
                                classFileWriter.add(172);
                                break;
                            }
                            classFileWriter.add(3);
                            classFileWriter.add(172);
                            break;
                        default:
                            throw Kit.codeBug();
                    }
                    i2++;
                    s2 = stackTop;
                }
                classFileWriter.stopMethod(s);
            }
        }
    }

    private void emitRegExpInit(ClassFileWriter classFileWriter) {
        int i;
        int i2 = 0;
        for (i = 0; i != this.scriptOrFnNodes.length; i++) {
            i2 += this.scriptOrFnNodes[i].getRegexpCount();
        }
        if (i2 != 0) {
            classFileWriter.startMethod(REGEXP_INIT_METHOD_NAME, REGEXP_INIT_METHOD_SIGNATURE, (short) 10);
            classFileWriter.addField("_reInitDone", "Z", (short) 74);
            classFileWriter.add(178, this.mainClassName, "_reInitDone", "Z");
            i = classFileWriter.acquireLabel();
            classFileWriter.add(153, i);
            classFileWriter.add(177);
            classFileWriter.markLabel(i);
            classFileWriter.addALoad(0);
            classFileWriter.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "checkRegExpProxy", "(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/RegExpProxy;");
            classFileWriter.addAStore(1);
            for (i = 0; i != this.scriptOrFnNodes.length; i++) {
                ScriptNode scriptNode = this.scriptOrFnNodes[i];
                int regexpCount = scriptNode.getRegexpCount();
                for (i2 = 0; i2 != regexpCount; i2++) {
                    String compiledRegexpName = getCompiledRegexpName(scriptNode, i2);
                    String str = "Ljava/lang/Object;";
                    String regexpString = scriptNode.getRegexpString(i2);
                    String regexpFlags = scriptNode.getRegexpFlags(i2);
                    classFileWriter.addField(compiledRegexpName, str, (short) 10);
                    classFileWriter.addALoad(1);
                    classFileWriter.addALoad(0);
                    classFileWriter.addPush(regexpString);
                    if (regexpFlags == null) {
                        classFileWriter.add(1);
                    } else {
                        classFileWriter.addPush(regexpFlags);
                    }
                    classFileWriter.addInvoke(185, "org/mozilla/javascript/RegExpProxy", "compileRegExp", "(Lorg/mozilla/javascript/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;");
                    classFileWriter.add(179, this.mainClassName, compiledRegexpName, str);
                }
            }
            classFileWriter.addPush(1);
            classFileWriter.add(179, this.mainClassName, "_reInitDone", "Z");
            classFileWriter.add(177);
            classFileWriter.stopMethod((short) 2);
        }
    }

    private void emitConstantDudeInitializers(ClassFileWriter classFileWriter) {
        int i = this.itsConstantListSize;
        if (i != 0) {
            classFileWriter.startMethod("<clinit>", "()V", (short) 24);
            double[] dArr = this.itsConstantList;
            for (int i2 = 0; i2 != i; i2++) {
                double d = dArr[i2];
                String str = "_k" + i2;
                String staticConstantWrapperType = getStaticConstantWrapperType(d);
                classFileWriter.addField(str, staticConstantWrapperType, (short) 10);
                int i3 = (int) d;
                if (((double) i3) == d) {
                    classFileWriter.addPush(i3);
                    classFileWriter.addInvoke(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                } else {
                    classFileWriter.addPush(d);
                    addDoubleWrap(classFileWriter);
                }
                classFileWriter.add(179, this.mainClassName, str, staticConstantWrapperType);
            }
            classFileWriter.add(177);
            classFileWriter.stopMethod((short) 0);
        }
    }

    void pushNumberAsObject(ClassFileWriter classFileWriter, double d) {
        int i = 0;
        if (d == 0.0d) {
            if (1.0d / d > 0.0d) {
                classFileWriter.add(178, "org/mozilla/javascript/optimizer/OptRuntime", "zeroObj", "Ljava/lang/Double;");
                return;
            }
            classFileWriter.addPush(d);
            addDoubleWrap(classFileWriter);
        } else if (d == 1.0d) {
            classFileWriter.add(178, "org/mozilla/javascript/optimizer/OptRuntime", "oneObj", "Ljava/lang/Double;");
        } else if (d == -1.0d) {
            classFileWriter.add(178, "org/mozilla/javascript/optimizer/OptRuntime", "minusOneObj", "Ljava/lang/Double;");
        } else if (d != d) {
            classFileWriter.add(178, "org/mozilla/javascript/ScriptRuntime", "NaNobj", "Ljava/lang/Double;");
        } else if (this.itsConstantListSize >= 2000) {
            classFileWriter.addPush(d);
            addDoubleWrap(classFileWriter);
        } else {
            int i2 = this.itsConstantListSize;
            if (i2 == 0) {
                this.itsConstantList = new double[64];
            } else {
                double[] dArr = this.itsConstantList;
                int i3 = 0;
                while (i3 != i2 && dArr[i3] != d) {
                    i3++;
                }
                if (i2 == dArr.length) {
                    Object obj = new double[(i2 * 2)];
                    System.arraycopy(this.itsConstantList, 0, obj, 0, i2);
                    this.itsConstantList = obj;
                }
                i = i3;
            }
            if (i == i2) {
                this.itsConstantList[i2] = d;
                this.itsConstantListSize = i2 + 1;
            }
            classFileWriter.add(178, this.mainClassName, "_k" + i, getStaticConstantWrapperType(d));
        }
    }

    private static void addDoubleWrap(ClassFileWriter classFileWriter) {
        classFileWriter.addInvoke(184, "org/mozilla/javascript/optimizer/OptRuntime", "wrapDouble", "(D)Ljava/lang/Double;");
    }

    private static String getStaticConstantWrapperType(double d) {
        if (((double) ((int) d)) == d) {
            return "Ljava/lang/Integer;";
        }
        return "Ljava/lang/Double;";
    }

    static void pushUndefined(ClassFileWriter classFileWriter) {
        classFileWriter.add(178, "org/mozilla/javascript/Undefined", "instance", "Ljava/lang/Object;");
    }

    int getIndex(ScriptNode scriptNode) {
        return this.scriptOrFnIndexes.getExisting(scriptNode);
    }

    String getDirectCtorName(ScriptNode scriptNode) {
        return "_n" + getIndex(scriptNode);
    }

    String getBodyMethodName(ScriptNode scriptNode) {
        return "_c_" + cleanName(scriptNode) + "_" + getIndex(scriptNode);
    }

    String cleanName(ScriptNode scriptNode) {
        String str = "";
        if (!(scriptNode instanceof FunctionNode)) {
            return "script";
        }
        Name functionName = ((FunctionNode) scriptNode).getFunctionName();
        if (functionName == null) {
            return "anonymous";
        }
        return functionName.getIdentifier();
    }

    String getBodyMethodSignature(ScriptNode scriptNode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        stringBuilder.append(this.mainClassSignature);
        stringBuilder.append("Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;");
        if (scriptNode.getType() == 109) {
            OptFunctionNode optFunctionNode = OptFunctionNode.get(scriptNode);
            if (optFunctionNode.isTargetOfDirectCall()) {
                int paramCount = optFunctionNode.fnode.getParamCount();
                for (int i = 0; i != paramCount; i++) {
                    stringBuilder.append("Ljava/lang/Object;D");
                }
            }
        }
        stringBuilder.append("[Ljava/lang/Object;)Ljava/lang/Object;");
        return stringBuilder.toString();
    }

    String getFunctionInitMethodName(OptFunctionNode optFunctionNode) {
        return "_i" + getIndex(optFunctionNode.fnode);
    }

    String getCompiledRegexpName(ScriptNode scriptNode, int i) {
        return "_re" + getIndex(scriptNode) + "_" + i;
    }

    static RuntimeException badTree() {
        throw new RuntimeException("Bad tree in codegen");
    }

    public void setMainMethodClass(String str) {
        this.mainMethodClass = str;
    }
}
