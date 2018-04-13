package org.mozilla.javascript;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mozilla.javascript.ast.ArrayComprehension;
import org.mozilla.javascript.ast.ArrayComprehensionLoop;
import org.mozilla.javascript.ast.ArrayLiteral;
import org.mozilla.javascript.ast.Assignment;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.Block;
import org.mozilla.javascript.ast.BreakStatement;
import org.mozilla.javascript.ast.CatchClause;
import org.mozilla.javascript.ast.ConditionalExpression;
import org.mozilla.javascript.ast.ContinueStatement;
import org.mozilla.javascript.ast.DestructuringForm;
import org.mozilla.javascript.ast.DoLoop;
import org.mozilla.javascript.ast.ElementGet;
import org.mozilla.javascript.ast.EmptyExpression;
import org.mozilla.javascript.ast.ExpressionStatement;
import org.mozilla.javascript.ast.ForInLoop;
import org.mozilla.javascript.ast.ForLoop;
import org.mozilla.javascript.ast.FunctionCall;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.GeneratorExpression;
import org.mozilla.javascript.ast.GeneratorExpressionLoop;
import org.mozilla.javascript.ast.IfStatement;
import org.mozilla.javascript.ast.InfixExpression;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.Label;
import org.mozilla.javascript.ast.LabeledStatement;
import org.mozilla.javascript.ast.LetNode;
import org.mozilla.javascript.ast.Loop;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NewExpression;
import org.mozilla.javascript.ast.NumberLiteral;
import org.mozilla.javascript.ast.ObjectLiteral;
import org.mozilla.javascript.ast.ObjectProperty;
import org.mozilla.javascript.ast.ParenthesizedExpression;
import org.mozilla.javascript.ast.PropertyGet;
import org.mozilla.javascript.ast.RegExpLiteral;
import org.mozilla.javascript.ast.ReturnStatement;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.ast.StringLiteral;
import org.mozilla.javascript.ast.SwitchCase;
import org.mozilla.javascript.ast.SwitchStatement;
import org.mozilla.javascript.ast.Symbol;
import org.mozilla.javascript.ast.ThrowStatement;
import org.mozilla.javascript.ast.TryStatement;
import org.mozilla.javascript.ast.UnaryExpression;
import org.mozilla.javascript.ast.VariableDeclaration;
import org.mozilla.javascript.ast.VariableInitializer;
import org.mozilla.javascript.ast.WhileLoop;
import org.mozilla.javascript.ast.WithStatement;
import org.mozilla.javascript.ast.XmlDotQuery;
import org.mozilla.javascript.ast.XmlElemRef;
import org.mozilla.javascript.ast.XmlExpression;
import org.mozilla.javascript.ast.XmlFragment;
import org.mozilla.javascript.ast.XmlLiteral;
import org.mozilla.javascript.ast.XmlMemberGet;
import org.mozilla.javascript.ast.XmlPropRef;
import org.mozilla.javascript.ast.XmlRef;
import org.mozilla.javascript.ast.XmlString;
import org.mozilla.javascript.ast.Yield;

public final class IRFactory extends Parser {
    private static final int ALWAYS_FALSE_BOOLEAN = -1;
    private static final int ALWAYS_TRUE_BOOLEAN = 1;
    private static final int LOOP_DO_WHILE = 0;
    private static final int LOOP_FOR = 2;
    private static final int LOOP_WHILE = 1;
    private Decompiler decompiler;

    public IRFactory() {
        this.decompiler = new Decompiler();
    }

    public IRFactory(CompilerEnvirons compilerEnvirons) {
        this(compilerEnvirons, compilerEnvirons.getErrorReporter());
    }

    public IRFactory(CompilerEnvirons compilerEnvirons, ErrorReporter errorReporter) {
        super(compilerEnvirons, errorReporter);
        this.decompiler = new Decompiler();
    }

    public ScriptNode transformTree(AstRoot astRoot) {
        this.currentScriptOrFn = astRoot;
        this.inUseStrictDirective = astRoot.isInStrictMode();
        ScriptNode scriptNode = (ScriptNode) transform(astRoot);
        scriptNode.setEncodedSourceBounds(this.decompiler.getCurrentOffset(), this.decompiler.getCurrentOffset());
        if (this.compilerEnv.isGeneratingSource()) {
            scriptNode.setEncodedSource(this.decompiler.getEncodedSource());
        }
        this.decompiler = null;
        return scriptNode;
    }

    public Node transform(AstNode astNode) {
        switch (astNode.getType()) {
            case 4:
                return transformReturn((ReturnStatement) astNode);
            case 30:
                return transformNewExpr((NewExpression) astNode);
            case 33:
                return transformPropertyGet((PropertyGet) astNode);
            case 36:
                return transformElementGet((ElementGet) astNode);
            case 38:
                return transformFunctionCall((FunctionCall) astNode);
            case 39:
                return transformName((Name) astNode);
            case 40:
                return transformNumber((NumberLiteral) astNode);
            case 41:
                return transformString((StringLiteral) astNode);
            case 42:
            case 43:
            case 44:
            case 45:
            case 160:
                return transformLiteral(astNode);
            case 48:
                return transformRegExp((RegExpLiteral) astNode);
            case 50:
                return transformThrow((ThrowStatement) astNode);
            case 65:
                return transformArrayLiteral((ArrayLiteral) astNode);
            case 66:
                return transformObjectLiteral((ObjectLiteral) astNode);
            case 72:
                return transformYield((Yield) astNode);
            case 81:
                return transformTry((TryStatement) astNode);
            case 102:
                return transformCondExpr((ConditionalExpression) astNode);
            case 109:
                return transformFunction((FunctionNode) astNode);
            case 112:
                return transformIf((IfStatement) astNode);
            case 114:
                return transformSwitch((SwitchStatement) astNode);
            case 117:
                return transformWhileLoop((WhileLoop) astNode);
            case 118:
                return transformDoLoop((DoLoop) astNode);
            case 119:
                if (astNode instanceof ForInLoop) {
                    return transformForInLoop((ForInLoop) astNode);
                }
                return transformForLoop((ForLoop) astNode);
            case 120:
                return transformBreak((BreakStatement) astNode);
            case 121:
                return transformContinue((ContinueStatement) astNode);
            case 123:
                return transformWith((WithStatement) astNode);
            case 128:
                return astNode;
            case 129:
                return transformBlock(astNode);
            case 136:
                return transformScript((ScriptNode) astNode);
            case 157:
                return transformArrayComp((ArrayComprehension) astNode);
            case 162:
                return transformGenExpr((GeneratorExpression) astNode);
            default:
                if (astNode instanceof ExpressionStatement) {
                    return transformExprStmt((ExpressionStatement) astNode);
                }
                if (astNode instanceof Assignment) {
                    return transformAssignment((Assignment) astNode);
                }
                if (astNode instanceof UnaryExpression) {
                    return transformUnary((UnaryExpression) astNode);
                }
                if (astNode instanceof XmlMemberGet) {
                    return transformXmlMemberGet((XmlMemberGet) astNode);
                }
                if (astNode instanceof InfixExpression) {
                    return transformInfix((InfixExpression) astNode);
                }
                if (astNode instanceof VariableDeclaration) {
                    return transformVariables((VariableDeclaration) astNode);
                }
                if (astNode instanceof ParenthesizedExpression) {
                    return transformParenExpr((ParenthesizedExpression) astNode);
                }
                if (astNode instanceof LabeledStatement) {
                    return transformLabeledStatement((LabeledStatement) astNode);
                }
                if (astNode instanceof LetNode) {
                    return transformLetNode((LetNode) astNode);
                }
                if (astNode instanceof XmlRef) {
                    return transformXmlRef((XmlRef) astNode);
                }
                if (astNode instanceof XmlLiteral) {
                    return transformXmlLiteral((XmlLiteral) astNode);
                }
                throw new IllegalArgumentException("Can't transform: " + astNode);
        }
    }

    private Node transformArrayComp(ArrayComprehension arrayComprehension) {
        int lineno = arrayComprehension.getLineno();
        Node createScopeNode = createScopeNode(157, lineno);
        String nextTempName = this.currentScriptOrFn.getNextTempName();
        pushScope(createScopeNode);
        try {
            defineSymbol(153, nextTempName, false);
            Node node = new Node(129, lineno);
            node.addChildToBack(new Node(133, createAssignment(90, createName(nextTempName), createCallOrNew(30, createName("Array"))), lineno));
            node.addChildToBack(arrayCompTransformHelper(arrayComprehension, nextTempName));
            createScopeNode.addChildToBack(node);
            createScopeNode.addChildToBack(createName(nextTempName));
            return createScopeNode;
        } finally {
            popScope();
        }
    }

    private Node arrayCompTransformHelper(ArrayComprehension arrayComprehension, String str) {
        Throwable th;
        this.decompiler.addToken(83);
        int lineno = arrayComprehension.getLineno();
        Node transform = transform(arrayComprehension.getResult());
        List loops = arrayComprehension.getLoops();
        int size = loops.size();
        Node[] nodeArr = new Node[size];
        Node[] nodeArr2 = new Node[size];
        int i = 0;
        while (i < size) {
            String string;
            ArrayComprehensionLoop arrayComprehensionLoop = (ArrayComprehensionLoop) loops.get(i);
            this.decompiler.addName(" ");
            this.decompiler.addToken(119);
            if (arrayComprehensionLoop.isForEach()) {
                this.decompiler.addName("each ");
            }
            this.decompiler.addToken(87);
            Node iterator = arrayComprehensionLoop.getIterator();
            if (iterator.getType() == 39) {
                string = iterator.getString();
                this.decompiler.addName(string);
                iterator = transform;
            } else {
                decompile(iterator);
                string = this.currentScriptOrFn.getNextTempName();
                defineSymbol(87, string, false);
                iterator = createBinary(89, createAssignment(90, iterator, createName(string)), transform);
            }
            Node createName = createName(string);
            defineSymbol(153, string, false);
            nodeArr[i] = createName;
            this.decompiler.addToken(52);
            nodeArr2[i] = transform(arrayComprehensionLoop.getIteratedObject());
            this.decompiler.addToken(88);
            i++;
            transform = iterator;
        }
        Node createCallOrNew = createCallOrNew(38, createPropertyGet(createName(str), null, "push", 0));
        Node node = new Node(133, createCallOrNew, lineno);
        if (arrayComprehension.getFilter() != null) {
            this.decompiler.addName(" ");
            this.decompiler.addToken(112);
            this.decompiler.addToken(87);
            node = createIf(transform(arrayComprehension.getFilter()), node, null, lineno);
            this.decompiler.addToken(88);
        }
        int i2 = size - 1;
        Node node2 = node;
        int i3 = 0;
        while (i2 >= 0) {
            try {
                arrayComprehensionLoop = (ArrayComprehensionLoop) loops.get(i2);
                iterator = createLoopNode(null, arrayComprehensionLoop.getLineno());
                pushScope(iterator);
                int i4 = i3 + 1;
                try {
                    node2 = createForIn(153, iterator, nodeArr[i2], nodeArr2[i2], node2, arrayComprehensionLoop.isForEach());
                    i2--;
                    i3 = i4;
                } catch (Throwable th2) {
                    th = th2;
                    i3 = i4;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        for (int i5 = 0; i5 < i3; i5++) {
            popScope();
        }
        this.decompiler.addToken(84);
        createCallOrNew.addChildToBack(transform);
        return node2;
        for (int i6 = 0; i6 < i3; i6++) {
            popScope();
        }
        throw th;
    }

    private Node transformArrayLiteral(ArrayLiteral arrayLiteral) {
        int i = 0;
        if (arrayLiteral.isDestructuring()) {
            return arrayLiteral;
        }
        this.decompiler.addToken(83);
        List elements = arrayLiteral.getElements();
        Node node = new Node(65);
        List list = null;
        for (int i2 = 0; i2 < elements.size(); i2++) {
            AstNode astNode = (AstNode) elements.get(i2);
            if (astNode.getType() != 128) {
                node.addChildToBack(transform(astNode));
            } else {
                List arrayList;
                if (list == null) {
                    arrayList = new ArrayList();
                } else {
                    arrayList = list;
                }
                arrayList.add(Integer.valueOf(i2));
                list = arrayList;
            }
            if (i2 < elements.size() - 1) {
                this.decompiler.addToken(89);
            }
        }
        this.decompiler.addToken(84);
        node.putIntProp(21, arrayLiteral.getDestructuringLength());
        if (list != null) {
            Object obj = new int[list.size()];
            while (i < list.size()) {
                obj[i] = ((Integer) list.get(i)).intValue();
                i++;
            }
            node.putProp(11, obj);
        }
        return node;
    }

    private Node transformAssignment(Assignment assignment) {
        Node removeParens = removeParens(assignment.getLeft());
        if (isDestructuring(removeParens)) {
            decompile(removeParens);
        } else {
            removeParens = transform(removeParens);
        }
        this.decompiler.addToken(assignment.getType());
        return createAssignment(assignment.getType(), removeParens, transform(assignment.getRight()));
    }

    private Node transformBlock(AstNode astNode) {
        if (astNode instanceof Scope) {
            pushScope((Scope) astNode);
        }
        try {
            List<Node> arrayList = new ArrayList();
            Iterator it = astNode.iterator();
            while (it.hasNext()) {
                arrayList.add(transform((AstNode) ((Node) it.next())));
            }
            astNode.removeChildren();
            for (Node addChildToBack : arrayList) {
                astNode.addChildToBack(addChildToBack);
            }
            return astNode;
        } finally {
            if (astNode instanceof Scope) {
                popScope();
            }
        }
    }

    private Node transformBreak(BreakStatement breakStatement) {
        this.decompiler.addToken(120);
        if (breakStatement.getBreakLabel() != null) {
            this.decompiler.addName(breakStatement.getBreakLabel().getIdentifier());
        }
        this.decompiler.addEOL(82);
        return breakStatement;
    }

    private Node transformCondExpr(ConditionalExpression conditionalExpression) {
        Node transform = transform(conditionalExpression.getTestExpression());
        this.decompiler.addToken(102);
        Node transform2 = transform(conditionalExpression.getTrueExpression());
        this.decompiler.addToken(103);
        return createCondExpr(transform, transform2, transform(conditionalExpression.getFalseExpression()));
    }

    private Node transformContinue(ContinueStatement continueStatement) {
        this.decompiler.addToken(121);
        if (continueStatement.getLabel() != null) {
            this.decompiler.addName(continueStatement.getLabel().getIdentifier());
        }
        this.decompiler.addEOL(82);
        return continueStatement;
    }

    private Node transformDoLoop(DoLoop doLoop) {
        doLoop.setType(132);
        pushScope(doLoop);
        try {
            this.decompiler.addToken(118);
            this.decompiler.addEOL(85);
            Node transform = transform(doLoop.getBody());
            this.decompiler.addToken(86);
            this.decompiler.addToken(117);
            this.decompiler.addToken(87);
            Node transform2 = transform(doLoop.getCondition());
            this.decompiler.addToken(88);
            this.decompiler.addEOL(82);
            Node createLoop = createLoop(doLoop, 0, transform, transform2, null, null);
            return createLoop;
        } finally {
            popScope();
        }
    }

    private Node transformElementGet(ElementGet elementGet) {
        Node transform = transform(elementGet.getTarget());
        this.decompiler.addToken(83);
        Node transform2 = transform(elementGet.getElement());
        this.decompiler.addToken(84);
        return new Node(36, transform, transform2);
    }

    private Node transformExprStmt(ExpressionStatement expressionStatement) {
        Node transform = transform(expressionStatement.getExpression());
        this.decompiler.addEOL(82);
        return new Node(expressionStatement.getType(), transform, expressionStatement.getLineno());
    }

    private Node transformForInLoop(ForInLoop forInLoop) {
        this.decompiler.addToken(119);
        if (forInLoop.isForEach()) {
            this.decompiler.addName("each ");
        }
        this.decompiler.addToken(87);
        forInLoop.setType(132);
        pushScope(forInLoop);
        int i = -1;
        try {
            AstNode iterator = forInLoop.getIterator();
            if (iterator instanceof VariableDeclaration) {
                i = ((VariableDeclaration) iterator).getType();
            }
            Node transform = transform(iterator);
            this.decompiler.addToken(52);
            Node transform2 = transform(forInLoop.getIteratedObject());
            this.decompiler.addToken(88);
            this.decompiler.addEOL(85);
            Node transform3 = transform(forInLoop.getBody());
            this.decompiler.addEOL(86);
            Node createForIn = createForIn(i, forInLoop, transform, transform2, transform3, forInLoop.isForEach());
            return createForIn;
        } finally {
            popScope();
        }
    }

    private Node transformForLoop(ForLoop forLoop) {
        this.decompiler.addToken(119);
        this.decompiler.addToken(87);
        forLoop.setType(132);
        Scope scope = this.currentScope;
        this.currentScope = forLoop;
        try {
            Node transform = transform(forLoop.getInitializer());
            this.decompiler.addToken(82);
            Node transform2 = transform(forLoop.getCondition());
            this.decompiler.addToken(82);
            Node transform3 = transform(forLoop.getIncrement());
            this.decompiler.addToken(88);
            this.decompiler.addEOL(85);
            Node transform4 = transform(forLoop.getBody());
            this.decompiler.addEOL(86);
            Node createFor = createFor(forLoop, transform, transform2, transform3, transform4);
            return createFor;
        } finally {
            this.currentScope = scope;
        }
    }

    private Node transformFunction(FunctionNode functionNode) {
        int functionType = functionNode.getFunctionType();
        int markFunctionStart = this.decompiler.markFunctionStart(functionType);
        Node decompileFunctionHeader = decompileFunctionHeader(functionNode);
        int addFunction = this.currentScriptOrFn.addFunction(functionNode);
        PerFunctionVariables perFunctionVariables = new PerFunctionVariables(functionNode);
        try {
            Node node = (Node) functionNode.getProp(23);
            functionNode.removeProp(23);
            int lineno = functionNode.getBody().getLineno();
            this.nestingOfFunction++;
            Node transform = transform(functionNode.getBody());
            if (!functionNode.isExpressionClosure()) {
                this.decompiler.addToken(86);
            }
            functionNode.setEncodedSourceBounds(markFunctionStart, this.decompiler.markFunctionEnd(markFunctionStart));
            if (!(functionType == 2 || functionNode.isExpressionClosure())) {
                this.decompiler.addToken(1);
            }
            if (node != null) {
                transform.addChildToFront(new Node(133, node, lineno));
            }
            functionType = functionNode.getFunctionType();
            node = initFunction(functionNode, addFunction, transform, functionType);
            if (decompileFunctionHeader != null) {
                node = createAssignment(90, decompileFunctionHeader, node);
                if (functionType != 2) {
                    node = createExprStatementNoReturn(node, functionNode.getLineno());
                }
            }
            this.nestingOfFunction--;
            perFunctionVariables.restore();
            return node;
        } catch (Throwable th) {
            this.nestingOfFunction--;
            perFunctionVariables.restore();
        }
    }

    private Node transformFunctionCall(FunctionCall functionCall) {
        Node createCallOrNew = createCallOrNew(38, transform(functionCall.getTarget()));
        createCallOrNew.setLineno(functionCall.getLineno());
        this.decompiler.addToken(87);
        List arguments = functionCall.getArguments();
        for (int i = 0; i < arguments.size(); i++) {
            createCallOrNew.addChildToBack(transform((AstNode) arguments.get(i)));
            if (i < arguments.size() - 1) {
                this.decompiler.addToken(89);
            }
        }
        this.decompiler.addToken(88);
        return createCallOrNew;
    }

    private Node transformGenExpr(GeneratorExpression generatorExpression) {
        FunctionNode functionNode = new FunctionNode();
        functionNode.setSourceName(this.currentScriptOrFn.getNextTempName());
        functionNode.setIsGenerator();
        functionNode.setFunctionType(2);
        functionNode.setRequiresActivation();
        int functionType = functionNode.getFunctionType();
        int markFunctionStart = this.decompiler.markFunctionStart(functionType);
        Node decompileFunctionHeader = decompileFunctionHeader(functionNode);
        int addFunction = this.currentScriptOrFn.addFunction(functionNode);
        PerFunctionVariables perFunctionVariables = new PerFunctionVariables(functionNode);
        try {
            Node node = (Node) functionNode.getProp(23);
            functionNode.removeProp(23);
            int i = generatorExpression.lineno;
            this.nestingOfFunction++;
            Node genExprTransformHelper = genExprTransformHelper(generatorExpression);
            if (!functionNode.isExpressionClosure()) {
                this.decompiler.addToken(86);
            }
            functionNode.setEncodedSourceBounds(markFunctionStart, this.decompiler.markFunctionEnd(markFunctionStart));
            if (!(functionType == 2 || functionNode.isExpressionClosure())) {
                this.decompiler.addToken(1);
            }
            if (node != null) {
                genExprTransformHelper.addChildToFront(new Node(133, node, i));
            }
            functionType = functionNode.getFunctionType();
            node = initFunction(functionNode, addFunction, genExprTransformHelper, functionType);
            if (decompileFunctionHeader != null) {
                node = createAssignment(90, decompileFunctionHeader, node);
                if (functionType != 2) {
                    node = createExprStatementNoReturn(node, functionNode.getLineno());
                }
            }
            this.nestingOfFunction--;
            perFunctionVariables.restore();
            node = createCallOrNew(38, node);
            node.setLineno(generatorExpression.getLineno());
            this.decompiler.addToken(87);
            this.decompiler.addToken(88);
            return node;
        } catch (Throwable th) {
            this.nestingOfFunction--;
            perFunctionVariables.restore();
        }
    }

    private Node genExprTransformHelper(GeneratorExpression generatorExpression) {
        Throwable th;
        this.decompiler.addToken(87);
        int lineno = generatorExpression.getLineno();
        Node transform = transform(generatorExpression.getResult());
        List loops = generatorExpression.getLoops();
        int size = loops.size();
        Node[] nodeArr = new Node[size];
        Node[] nodeArr2 = new Node[size];
        for (int i = 0; i < size; i++) {
            String string;
            GeneratorExpressionLoop generatorExpressionLoop = (GeneratorExpressionLoop) loops.get(i);
            this.decompiler.addName(" ");
            this.decompiler.addToken(119);
            this.decompiler.addToken(87);
            Node iterator = generatorExpressionLoop.getIterator();
            if (iterator.getType() == 39) {
                string = iterator.getString();
                this.decompiler.addName(string);
            } else {
                decompile(iterator);
                string = this.currentScriptOrFn.getNextTempName();
                defineSymbol(87, string, false);
                transform = createBinary(89, createAssignment(90, iterator, createName(string)), transform);
            }
            iterator = createName(string);
            defineSymbol(153, string, false);
            nodeArr[i] = iterator;
            this.decompiler.addToken(52);
            nodeArr2[i] = transform(generatorExpressionLoop.getIteratedObject());
            this.decompiler.addToken(88);
        }
        Node node = new Node(133, new Node(72, transform, generatorExpression.getLineno()), lineno);
        if (generatorExpression.getFilter() != null) {
            this.decompiler.addName(" ");
            this.decompiler.addToken(112);
            this.decompiler.addToken(87);
            node = createIf(transform(generatorExpression.getFilter()), node, null, lineno);
            this.decompiler.addToken(88);
        }
        int i2 = size - 1;
        Node node2 = node;
        int i3 = 0;
        while (i2 >= 0) {
            try {
                generatorExpressionLoop = (GeneratorExpressionLoop) loops.get(i2);
                transform = createLoopNode(null, generatorExpressionLoop.getLineno());
                pushScope(transform);
                int i4 = i3 + 1;
                try {
                    node2 = createForIn(153, transform, nodeArr[i2], nodeArr2[i2], node2, generatorExpressionLoop.isForEach());
                    i2--;
                    i3 = i4;
                } catch (Throwable th2) {
                    th = th2;
                    i3 = i4;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        for (int i5 = 0; i5 < i3; i5++) {
            popScope();
        }
        this.decompiler.addToken(88);
        return node2;
        for (int i6 = 0; i6 < i3; i6++) {
            popScope();
        }
        throw th;
    }

    private Node transformIf(IfStatement ifStatement) {
        this.decompiler.addToken(112);
        this.decompiler.addToken(87);
        Node transform = transform(ifStatement.getCondition());
        this.decompiler.addToken(88);
        this.decompiler.addEOL(85);
        Node transform2 = transform(ifStatement.getThenPart());
        Node node = null;
        if (ifStatement.getElsePart() != null) {
            this.decompiler.addToken(86);
            this.decompiler.addToken(113);
            this.decompiler.addEOL(85);
            node = transform(ifStatement.getElsePart());
        }
        this.decompiler.addEOL(86);
        return createIf(transform, transform2, node, ifStatement.getLineno());
    }

    private Node transformInfix(InfixExpression infixExpression) {
        Node transform = transform(infixExpression.getLeft());
        this.decompiler.addToken(infixExpression.getType());
        Node transform2 = transform(infixExpression.getRight());
        if (infixExpression instanceof XmlDotQuery) {
            this.decompiler.addToken(88);
        }
        return createBinary(infixExpression.getType(), transform, transform2);
    }

    private Node transformLabeledStatement(LabeledStatement labeledStatement) {
        Node firstLabel = labeledStatement.getFirstLabel();
        List labels = labeledStatement.getLabels();
        this.decompiler.addName(firstLabel.getName());
        if (labels.size() > 1) {
            for (Label label : labels.subList(1, labels.size())) {
                this.decompiler.addEOL(103);
                this.decompiler.addName(label.getName());
            }
        }
        if (labeledStatement.getStatement().getType() == 129) {
            this.decompiler.addToken(66);
            this.decompiler.addEOL(85);
        } else {
            this.decompiler.addEOL(103);
        }
        Node transform = transform(labeledStatement.getStatement());
        if (labeledStatement.getStatement().getType() == 129) {
            this.decompiler.addEOL(86);
        }
        Node newTarget = Node.newTarget();
        Node node = new Node(129, firstLabel, transform, newTarget);
        firstLabel.target = newTarget;
        return node;
    }

    private Node transformLetNode(LetNode letNode) {
        pushScope(letNode);
        try {
            this.decompiler.addToken(153);
            this.decompiler.addToken(87);
            Node transformVariableInitializers = transformVariableInitializers(letNode.getVariables());
            this.decompiler.addToken(88);
            letNode.addChildToBack(transformVariableInitializers);
            Object obj = letNode.getType() == 158 ? 1 : null;
            if (letNode.getBody() != null) {
                if (obj != null) {
                    this.decompiler.addName(" ");
                } else {
                    this.decompiler.addEOL(85);
                }
                letNode.addChildToBack(transform(letNode.getBody()));
                if (obj == null) {
                    this.decompiler.addEOL(86);
                }
            }
            popScope();
            return letNode;
        } catch (Throwable th) {
            popScope();
        }
    }

    private Node transformLiteral(AstNode astNode) {
        this.decompiler.addToken(astNode.getType());
        return astNode;
    }

    private Node transformName(Name name) {
        this.decompiler.addName(name.getIdentifier());
        return name;
    }

    private Node transformNewExpr(NewExpression newExpression) {
        this.decompiler.addToken(30);
        Node createCallOrNew = createCallOrNew(30, transform(newExpression.getTarget()));
        createCallOrNew.setLineno(newExpression.getLineno());
        List arguments = newExpression.getArguments();
        this.decompiler.addToken(87);
        for (int i = 0; i < arguments.size(); i++) {
            createCallOrNew.addChildToBack(transform((AstNode) arguments.get(i)));
            if (i < arguments.size() - 1) {
                this.decompiler.addToken(89);
            }
        }
        this.decompiler.addToken(88);
        if (newExpression.getInitializer() != null) {
            createCallOrNew.addChildToBack(transformObjectLiteral(newExpression.getInitializer()));
        }
        return createCallOrNew;
    }

    private Node transformNumber(NumberLiteral numberLiteral) {
        this.decompiler.addNumber(numberLiteral.getNumber());
        return numberLiteral;
    }

    private Node transformObjectLiteral(ObjectLiteral objectLiteral) {
        if (!objectLiteral.isDestructuring()) {
            Object obj;
            this.decompiler.addToken(85);
            List<ObjectProperty> elements = objectLiteral.getElements();
            objectLiteral = new Node(66);
            if (elements.isEmpty()) {
                obj = ScriptRuntime.emptyArgs;
            } else {
                int size = elements.size();
                Object obj2 = new Object[size];
                int i = 0;
                for (ObjectProperty objectProperty : elements) {
                    if (objectProperty.isGetter()) {
                        this.decompiler.addToken(151);
                    } else if (objectProperty.isSetter()) {
                        this.decompiler.addToken(152);
                    }
                    int i2 = i + 1;
                    obj2[i] = getPropKey(objectProperty.getLeft());
                    if (!(objectProperty.isGetter() || objectProperty.isSetter())) {
                        this.decompiler.addToken(66);
                    }
                    Node transform = transform(objectProperty.getRight());
                    Node createUnary = objectProperty.isGetter() ? createUnary(151, transform) : objectProperty.isSetter() ? createUnary(152, transform) : transform;
                    objectLiteral.addChildToBack(createUnary);
                    if (i2 < size) {
                        this.decompiler.addToken(89);
                    }
                    i = i2;
                }
                obj = obj2;
            }
            this.decompiler.addToken(86);
            objectLiteral.putProp(12, obj);
        }
        return objectLiteral;
    }

    private Object getPropKey(Node node) {
        String identifier;
        if (node instanceof Name) {
            identifier = ((Name) node).getIdentifier();
            this.decompiler.addName(identifier);
            return ScriptRuntime.getIndexObject(identifier);
        } else if (node instanceof StringLiteral) {
            identifier = ((StringLiteral) node).getValue();
            this.decompiler.addString(identifier);
            return ScriptRuntime.getIndexObject(identifier);
        } else if (node instanceof NumberLiteral) {
            double number = ((NumberLiteral) node).getNumber();
            this.decompiler.addNumber(number);
            return ScriptRuntime.getIndexObject(number);
        } else {
            throw Kit.codeBug();
        }
    }

    private Node transformParenExpr(ParenthesizedExpression parenthesizedExpression) {
        AstNode expression = parenthesizedExpression.getExpression();
        this.decompiler.addToken(87);
        AstNode astNode = expression;
        int i = 1;
        while (astNode instanceof ParenthesizedExpression) {
            this.decompiler.addToken(87);
            i++;
            astNode = ((ParenthesizedExpression) astNode).getExpression();
        }
        Node transform = transform(astNode);
        for (int i2 = 0; i2 < i; i2++) {
            this.decompiler.addToken(88);
        }
        transform.putProp(19, Boolean.TRUE);
        return transform;
    }

    private Node transformPropertyGet(PropertyGet propertyGet) {
        Node transform = transform(propertyGet.getTarget());
        String identifier = propertyGet.getProperty().getIdentifier();
        this.decompiler.addToken(108);
        this.decompiler.addName(identifier);
        return createPropertyGet(transform, null, identifier, 0);
    }

    private Node transformRegExp(RegExpLiteral regExpLiteral) {
        this.decompiler.addRegexp(regExpLiteral.getValue(), regExpLiteral.getFlags());
        this.currentScriptOrFn.addRegExp(regExpLiteral);
        return regExpLiteral;
    }

    private Node transformReturn(ReturnStatement returnStatement) {
        boolean equals = Boolean.TRUE.equals(returnStatement.getProp(25));
        if (equals) {
            this.decompiler.addName(" ");
        } else {
            this.decompiler.addToken(4);
        }
        AstNode returnValue = returnStatement.getReturnValue();
        Node transform = returnValue == null ? null : transform(returnValue);
        if (!equals) {
            this.decompiler.addEOL(82);
        }
        if (returnValue == null) {
            return new Node(4, returnStatement.getLineno());
        }
        return new Node(4, transform, returnStatement.getLineno());
    }

    private Node transformScript(ScriptNode scriptNode) {
        this.decompiler.addToken(136);
        if (this.currentScope != null) {
            Kit.codeBug();
        }
        this.currentScope = scriptNode;
        Node node = new Node(129);
        Iterator it = scriptNode.iterator();
        while (it.hasNext()) {
            node.addChildToBack(transform((AstNode) ((Node) it.next())));
        }
        scriptNode.removeChildren();
        Node firstChild = node.getFirstChild();
        if (firstChild != null) {
            scriptNode.addChildrenToBack(firstChild);
        }
        return scriptNode;
    }

    private Node transformString(StringLiteral stringLiteral) {
        this.decompiler.addString(stringLiteral.getValue());
        return Node.newString(stringLiteral.getValue());
    }

    private Node transformSwitch(SwitchStatement switchStatement) {
        this.decompiler.addToken(114);
        this.decompiler.addToken(87);
        Node transform = transform(switchStatement.getExpression());
        this.decompiler.addToken(88);
        switchStatement.addChildToBack(transform);
        Node node = new Node(129, (Node) switchStatement, switchStatement.getLineno());
        this.decompiler.addEOL(85);
        for (SwitchCase switchCase : switchStatement.getCases()) {
            AstNode expression = switchCase.getExpression();
            Node node2 = null;
            if (expression != null) {
                this.decompiler.addToken(115);
                node2 = transform(expression);
            } else {
                this.decompiler.addToken(116);
            }
            this.decompiler.addEOL(103);
            List<AstNode> statements = switchCase.getStatements();
            Node block = new Block();
            if (statements != null) {
                for (AstNode transform2 : statements) {
                    block.addChildToBack(transform(transform2));
                }
            }
            addSwitchCase(node, node2, block);
        }
        this.decompiler.addEOL(86);
        closeSwitch(node);
        return node;
    }

    private Node transformThrow(ThrowStatement throwStatement) {
        this.decompiler.addToken(50);
        Node transform = transform(throwStatement.getExpression());
        this.decompiler.addEOL(82);
        return new Node(50, transform, throwStatement.getLineno());
    }

    private Node transformTry(TryStatement tryStatement) {
        this.decompiler.addToken(81);
        this.decompiler.addEOL(85);
        Node transform = transform(tryStatement.getTryBlock());
        this.decompiler.addEOL(86);
        Node block = new Block();
        for (CatchClause catchClause : tryStatement.getCatchClauses()) {
            Node transform2;
            this.decompiler.addToken(124);
            this.decompiler.addToken(87);
            String identifier = catchClause.getVarName().getIdentifier();
            this.decompiler.addName(identifier);
            AstNode catchCondition = catchClause.getCatchCondition();
            if (catchCondition != null) {
                this.decompiler.addName(" ");
                this.decompiler.addToken(112);
                transform2 = transform(catchCondition);
            } else {
                transform2 = new EmptyExpression();
            }
            this.decompiler.addToken(88);
            this.decompiler.addEOL(85);
            Node transform3 = transform(catchClause.getBody());
            this.decompiler.addEOL(86);
            block.addChildToBack(createCatch(identifier, transform2, transform3, catchClause.getLineno()));
        }
        Node node = null;
        if (tryStatement.getFinallyBlock() != null) {
            this.decompiler.addToken(125);
            this.decompiler.addEOL(85);
            node = transform(tryStatement.getFinallyBlock());
            this.decompiler.addEOL(86);
        }
        return createTryCatchFinally(transform, block, node, tryStatement.getLineno());
    }

    private Node transformUnary(UnaryExpression unaryExpression) {
        int type = unaryExpression.getType();
        if (type == 74) {
            return transformDefaultXmlNamepace(unaryExpression);
        }
        if (unaryExpression.isPrefix()) {
            this.decompiler.addToken(type);
        }
        Node transform = transform(unaryExpression.getOperand());
        if (unaryExpression.isPostfix()) {
            this.decompiler.addToken(type);
        }
        if (type == 106 || type == 107) {
            return createIncDec(type, unaryExpression.isPostfix(), transform);
        }
        return createUnary(type, transform);
    }

    private Node transformVariables(VariableDeclaration variableDeclaration) {
        this.decompiler.addToken(variableDeclaration.getType());
        transformVariableInitializers(variableDeclaration);
        AstNode parent = variableDeclaration.getParent();
        if (!((parent instanceof Loop) || (parent instanceof LetNode))) {
            this.decompiler.addEOL(82);
        }
        return variableDeclaration;
    }

    private Node transformVariableInitializers(VariableDeclaration variableDeclaration) {
        List<VariableInitializer> variables = variableDeclaration.getVariables();
        int size = variables.size();
        int i = 0;
        for (VariableInitializer variableInitializer : variables) {
            Node node;
            Node target = variableInitializer.getTarget();
            AstNode initializer = variableInitializer.getInitializer();
            if (variableInitializer.isDestructuring()) {
                decompile(target);
                node = target;
            } else {
                node = transform(target);
            }
            target = null;
            if (initializer != null) {
                this.decompiler.addToken(90);
                target = transform(initializer);
            }
            if (!variableInitializer.isDestructuring()) {
                if (target != null) {
                    node.addChildToBack(target);
                }
                variableDeclaration.addChildToBack(node);
            } else if (target == null) {
                variableDeclaration.addChildToBack(node);
            } else {
                variableDeclaration.addChildToBack(createDestructuringAssignment(variableDeclaration.getType(), node, target));
            }
            int i2 = i + 1;
            if (i < size - 1) {
                this.decompiler.addToken(89);
            }
            i = i2;
        }
        return variableDeclaration;
    }

    private Node transformWhileLoop(WhileLoop whileLoop) {
        this.decompiler.addToken(117);
        whileLoop.setType(132);
        pushScope(whileLoop);
        try {
            this.decompiler.addToken(87);
            Node transform = transform(whileLoop.getCondition());
            this.decompiler.addToken(88);
            this.decompiler.addEOL(85);
            Node transform2 = transform(whileLoop.getBody());
            this.decompiler.addEOL(86);
            Node createLoop = createLoop(whileLoop, 1, transform2, transform, null, null);
            return createLoop;
        } finally {
            popScope();
        }
    }

    private Node transformWith(WithStatement withStatement) {
        this.decompiler.addToken(123);
        this.decompiler.addToken(87);
        Node transform = transform(withStatement.getExpression());
        this.decompiler.addToken(88);
        this.decompiler.addEOL(85);
        Node transform2 = transform(withStatement.getStatement());
        this.decompiler.addEOL(86);
        return createWith(transform, transform2, withStatement.getLineno());
    }

    private Node transformYield(Yield yield) {
        this.decompiler.addToken(72);
        Node transform = yield.getValue() == null ? null : transform(yield.getValue());
        if (transform != null) {
            return new Node(72, transform, yield.getLineno());
        }
        return new Node(72, yield.getLineno());
    }

    private Node transformXmlLiteral(XmlLiteral xmlLiteral) {
        Node node = new Node(30, xmlLiteral.getLineno());
        List<XmlFragment> fragments = xmlLiteral.getFragments();
        node.addChildToBack(createName(((XmlString) fragments.get(0)).getXml().trim().startsWith("<>") ? "XMLList" : MNSConstants.DEFAULT_NOTIFY_CONTENT_TYPE));
        Node node2 = null;
        for (XmlFragment xmlFragment : fragments) {
            Node createString;
            if (xmlFragment instanceof XmlString) {
                String xml = ((XmlString) xmlFragment).getXml();
                this.decompiler.addName(xml);
                if (node2 == null) {
                    createString = createString(xml);
                } else {
                    createString = createBinary(21, node2, createString(xml));
                }
            } else {
                XmlExpression xmlExpression = (XmlExpression) xmlFragment;
                boolean isXmlAttribute = xmlExpression.isXmlAttribute();
                this.decompiler.addToken(85);
                if (xmlExpression.getExpression() instanceof EmptyExpression) {
                    createString = createString("");
                } else {
                    createString = transform(xmlExpression.getExpression());
                }
                this.decompiler.addToken(86);
                if (isXmlAttribute) {
                    createString = createBinary(21, createBinary(21, createString("\""), createUnary(75, createString)), createString("\""));
                } else {
                    createString = createUnary(76, createString);
                }
                createString = createBinary(21, node2, createString);
            }
            node2 = createString;
        }
        node.addChildToBack(node2);
        return node;
    }

    private Node transformXmlMemberGet(XmlMemberGet xmlMemberGet) {
        XmlRef memberRef = xmlMemberGet.getMemberRef();
        Node transform = transform(xmlMemberGet.getLeft());
        int i = memberRef.isAttributeAccess() ? 2 : 0;
        if (xmlMemberGet.getType() == 143) {
            i |= 4;
            this.decompiler.addToken(143);
        } else {
            this.decompiler.addToken(108);
        }
        return transformXmlRef(transform, memberRef, i);
    }

    private Node transformXmlRef(XmlRef xmlRef) {
        return transformXmlRef(null, xmlRef, xmlRef.isAttributeAccess() ? 2 : 0);
    }

    private Node transformXmlRef(Node node, XmlRef xmlRef, int i) {
        if ((i & 2) != 0) {
            this.decompiler.addToken(147);
        }
        Name namespace = xmlRef.getNamespace();
        String identifier = namespace != null ? namespace.getIdentifier() : null;
        if (identifier != null) {
            this.decompiler.addName(identifier);
            this.decompiler.addToken(144);
        }
        if (xmlRef instanceof XmlPropRef) {
            String identifier2 = ((XmlPropRef) xmlRef).getPropName().getIdentifier();
            this.decompiler.addName(identifier2);
            return createPropertyGet(node, identifier, identifier2, i);
        }
        this.decompiler.addToken(83);
        Node transform = transform(((XmlElemRef) xmlRef).getExpression());
        this.decompiler.addToken(84);
        return createElementGet(node, identifier, transform, i);
    }

    private Node transformDefaultXmlNamepace(UnaryExpression unaryExpression) {
        this.decompiler.addToken(116);
        this.decompiler.addName(" xml");
        this.decompiler.addName(" namespace");
        this.decompiler.addToken(90);
        return createUnary(74, transform(unaryExpression.getOperand()));
    }

    private void addSwitchCase(Node node, Node node2, Node node3) {
        if (node.getType() != 129) {
            throw Kit.codeBug();
        }
        Jump jump = (Jump) node.getFirstChild();
        if (jump.getType() != 114) {
            throw Kit.codeBug();
        }
        Node newTarget = Node.newTarget();
        if (node2 != null) {
            Node jump2 = new Jump(115, node2);
            jump2.target = newTarget;
            jump.addChildToBack(jump2);
        } else {
            jump.setDefault(newTarget);
        }
        node.addChildToBack(newTarget);
        node.addChildToBack(node3);
    }

    private void closeSwitch(Node node) {
        if (node.getType() != 129) {
            throw Kit.codeBug();
        }
        Jump jump = (Jump) node.getFirstChild();
        if (jump.getType() != 114) {
            throw Kit.codeBug();
        }
        Node newTarget = Node.newTarget();
        jump.target = newTarget;
        Node node2 = jump.getDefault();
        if (node2 == null) {
            node2 = newTarget;
        }
        node.addChildAfter(makeJump(5, node2), jump);
        node.addChildToBack(newTarget);
    }

    private Node createExprStatementNoReturn(Node node, int i) {
        return new Node(133, node, i);
    }

    private Node createString(String str) {
        return Node.newString(str);
    }

    private Node createCatch(String str, Node node, Node node2, int i) {
        Node node3;
        if (node == null) {
            node3 = new Node(128);
        } else {
            node3 = node;
        }
        return new Node(124, createName(str), node3, node2, i);
    }

    private Node initFunction(FunctionNode functionNode, int i, Node node, int i2) {
        functionNode.setFunctionType(i2);
        functionNode.addChildToBack(node);
        if (functionNode.getFunctionCount() != 0) {
            functionNode.setRequiresActivation();
        }
        if (i2 == 2) {
            Name functionName = functionNode.getFunctionName();
            if (!(functionName == null || functionName.length() == 0 || functionNode.getSymbol(functionName.getIdentifier()) != null)) {
                functionNode.putSymbol(new Symbol(109, functionName.getIdentifier()));
                node.addChildrenToFront(new Node(133, new Node(8, Node.newString(49, functionName.getIdentifier()), new Node(63))));
            }
        }
        Node lastChild = node.getLastChild();
        if (lastChild == null || lastChild.getType() != 4) {
            node.addChildToBack(new Node(4));
        }
        lastChild = Node.newString(109, functionNode.getName());
        lastChild.putIntProp(1, i);
        return lastChild;
    }

    private Scope createLoopNode(Node node, int i) {
        Jump createScopeNode = createScopeNode(132, i);
        if (node != null) {
            ((Jump) node).setLoop(createScopeNode);
        }
        return createScopeNode;
    }

    private Node createFor(Scope scope, Node node, Node node2, Node node3, Node node4) {
        if (node.getType() != 153) {
            return createLoop(scope, 2, node4, node2, node, node3);
        }
        Node splitScope = Scope.splitScope(scope);
        splitScope.setType(153);
        splitScope.addChildrenToBack(node);
        splitScope.addChildToBack(createLoop(scope, 2, node4, node2, new Node(128), node3));
        return splitScope;
    }

    private Node createLoop(Jump jump, int i, Node node, Node node2, Node node3, Node node4) {
        Node newTarget = Node.newTarget();
        Node newTarget2 = Node.newTarget();
        if (i == 2 && node2.getType() == 128) {
            node2 = new Node(45);
        }
        Node jump2 = new Jump(6, node2);
        jump2.target = newTarget;
        Node newTarget3 = Node.newTarget();
        jump.addChildToBack(newTarget);
        jump.addChildrenToBack(node);
        if (i == 1 || i == 2) {
            jump.addChildrenToBack(new Node(128, jump.getLineno()));
        }
        jump.addChildToBack(newTarget2);
        jump.addChildToBack(jump2);
        jump.addChildToBack(newTarget3);
        jump.target = newTarget3;
        if (i == 1 || i == 2) {
            jump.addChildToFront(makeJump(5, newTarget2));
            if (i == 2) {
                int type = node3.getType();
                if (type != 128) {
                    if (!(type == 122 || type == 153)) {
                        node3 = new Node(133, node3);
                    }
                    jump.addChildToFront(node3);
                }
                newTarget2 = Node.newTarget();
                jump.addChildAfter(newTarget2, node);
                if (node4.getType() != 128) {
                    jump.addChildAfter(new Node(133, node4), newTarget2);
                }
            }
        }
        jump.setContinue(newTarget2);
        return jump;
    }

    private Node createForIn(int i, Node node, Node node2, Node node3, Node node4, boolean z) {
        Node lastChild;
        int type;
        int i2;
        Node node5;
        int i3 = 0;
        int type2 = node2.getType();
        if (type2 == 122 || type2 == 153) {
            lastChild = node2.getLastChild();
            type = lastChild.getType();
            if (type == 65 || type == 66) {
                if (lastChild instanceof ArrayLiteral) {
                    type2 = ((ArrayLiteral) lastChild).getDestructuringLength();
                    i3 = type;
                } else {
                    type2 = 0;
                    i3 = type;
                }
            } else if (type == 39) {
                lastChild = Node.newString(39, lastChild.getString());
                type = -1;
                i3 = type2;
                type2 = 0;
            } else {
                reportError("msg.bad.for.in.lhs");
                return null;
            }
            i2 = i3;
            i3 = type2;
            type2 = type;
        } else if (type2 == 65 || type2 == 66) {
            i3 = 0;
            if (node2 instanceof ArrayLiteral) {
                i3 = ((ArrayLiteral) node2).getDestructuringLength();
                i2 = type2;
                lastChild = node2;
            } else {
                i2 = type2;
                lastChild = node2;
            }
        } else {
            lastChild = makeReference(node2);
            if (lastChild == null) {
                reportError("msg.bad.for.in.lhs");
                return null;
            }
            i2 = type2;
            type2 = -1;
        }
        Node node6 = new Node(141);
        type = z ? 59 : type2 != -1 ? 60 : 58;
        Node node7 = new Node(type, node3);
        node7.putProp(3, node6);
        Node node8 = new Node(61);
        node8.putProp(3, node6);
        Node node9 = new Node(62);
        node9.putProp(3, node6);
        Node node10 = new Node(129);
        if (type2 != -1) {
            lastChild = createDestructuringAssignment(i, lastChild, node9);
            if (z || (type2 != 66 && r1 == 2)) {
                node5 = lastChild;
            } else {
                reportError("msg.bad.for.in.destruct");
                node5 = lastChild;
            }
        } else {
            node5 = simpleAssignment(lastChild, node9);
        }
        node10.addChildToBack(new Node(133, node5));
        node10.addChildToBack(node4);
        node5 = createLoop((Jump) node, 1, node10, node8, null, null);
        node5.addChildToFront(node7);
        if (i2 == 122 || i2 == 153) {
            node5.addChildToFront(node2);
        }
        node6.addChildToBack(node5);
        return node6;
    }

    private Node createTryCatchFinally(Node node, Node node2, Node node3, int i) {
        Object obj = (node3 == null || (node3.getType() == 129 && !node3.hasChildren())) ? null : 1;
        if (node.getType() == 129 && !node.hasChildren() && obj == null) {
            return node;
        }
        boolean hasChildren = node2.hasChildren();
        if (obj == null && !hasChildren) {
            return node;
        }
        Node newTarget;
        Node node4 = new Node(141);
        Node jump = new Jump(81, node, i);
        jump.putProp(3, node4);
        if (hasChildren) {
            Node newTarget2 = Node.newTarget();
            jump.addChildToBack(makeJump(5, newTarget2));
            newTarget = Node.newTarget();
            jump.target = newTarget;
            jump.addChildToBack(newTarget);
            Node node5 = new Node(141);
            Object obj2 = null;
            Node firstChild = node2.getFirstChild();
            int i2 = 0;
            while (firstChild != null) {
                int lineno = firstChild.getLineno();
                Node firstChild2 = firstChild.getFirstChild();
                Node next = firstChild2.getNext();
                newTarget = next.getNext();
                firstChild.removeChild(firstChild2);
                firstChild.removeChild(next);
                firstChild.removeChild(newTarget);
                newTarget.addChildToBack(new Node(3));
                newTarget.addChildToBack(makeJump(5, newTarget2));
                if (next.getType() == 128) {
                    obj2 = 1;
                } else {
                    newTarget = createIf(next, newTarget, null, lineno);
                }
                next = new Node(57, firstChild2, createUseLocal(node4));
                next.putProp(3, node5);
                next.putIntProp(14, i2);
                node5.addChildToBack(next);
                node5.addChildToBack(createWith(createUseLocal(node5), newTarget, lineno));
                firstChild = firstChild.getNext();
                i2++;
            }
            jump.addChildToBack(node5);
            if (obj2 == null) {
                newTarget = new Node(51);
                newTarget.putProp(3, node4);
                jump.addChildToBack(newTarget);
            }
            jump.addChildToBack(newTarget2);
        }
        if (obj != null) {
            Node newTarget3 = Node.newTarget();
            jump.setFinally(newTarget3);
            jump.addChildToBack(makeJump(135, newTarget3));
            newTarget = Node.newTarget();
            jump.addChildToBack(makeJump(5, newTarget));
            jump.addChildToBack(newTarget3);
            newTarget3 = new Node(125, node3);
            newTarget3.putProp(3, node4);
            jump.addChildToBack(newTarget3);
            jump.addChildToBack(newTarget);
        }
        node4.addChildToBack(jump);
        return node4;
    }

    private Node createWith(Node node, Node node2, int i) {
        setRequiresActivation();
        Node node3 = new Node(129, i);
        node3.addChildToBack(new Node(2, node));
        node3.addChildrenToBack(new Node(123, node2, i));
        node3.addChildToBack(new Node(3));
        return node3;
    }

    private Node createIf(Node node, Node node2, Node node3, int i) {
        int isAlwaysDefinedBoolean = isAlwaysDefinedBoolean(node);
        if (isAlwaysDefinedBoolean == 1) {
            return node2;
        }
        if (isAlwaysDefinedBoolean != -1) {
            Node node4 = new Node(129, i);
            Node newTarget = Node.newTarget();
            Node jump = new Jump(7, node);
            jump.target = newTarget;
            node4.addChildToBack(jump);
            node4.addChildrenToBack(node2);
            if (node3 != null) {
                jump = Node.newTarget();
                node4.addChildToBack(makeJump(5, jump));
                node4.addChildToBack(newTarget);
                node4.addChildrenToBack(node3);
                node4.addChildToBack(jump);
            } else {
                node4.addChildToBack(newTarget);
            }
            return node4;
        } else if (node3 != null) {
            return node3;
        } else {
            return new Node(129, i);
        }
    }

    private Node createCondExpr(Node node, Node node2, Node node3) {
        int isAlwaysDefinedBoolean = isAlwaysDefinedBoolean(node);
        if (isAlwaysDefinedBoolean == 1) {
            return node2;
        }
        if (isAlwaysDefinedBoolean == -1) {
            return node3;
        }
        return new Node(102, node, node2, node3);
    }

    private Node createUnary(int i, Node node) {
        int type = node.getType();
        switch (i) {
            case 26:
                int isAlwaysDefinedBoolean = isAlwaysDefinedBoolean(node);
                if (isAlwaysDefinedBoolean != 0) {
                    if (isAlwaysDefinedBoolean == 1) {
                        isAlwaysDefinedBoolean = 44;
                    } else {
                        isAlwaysDefinedBoolean = 45;
                    }
                    if (type != 45 && type != 44) {
                        return new Node(isAlwaysDefinedBoolean);
                    }
                    node.setType(isAlwaysDefinedBoolean);
                    return node;
                }
                break;
            case 27:
                if (type == 40) {
                    node.setDouble((double) (ScriptRuntime.toInt32(node.getDouble()) ^ -1));
                    return node;
                }
                break;
            case 29:
                if (type == 40) {
                    node.setDouble(-node.getDouble());
                    return node;
                }
                break;
            case 31:
                Node node2;
                if (type == 39) {
                    node.setType(49);
                    node2 = new Node(i, node, Node.newString(node.getString()));
                } else if (type == 33 || type == 36) {
                    r1 = node.getFirstChild();
                    Node lastChild = node.getLastChild();
                    node.removeChild(r1);
                    node.removeChild(lastChild);
                    node2 = new Node(i, r1, lastChild);
                } else if (type == 67) {
                    r1 = node.getFirstChild();
                    node.removeChild(r1);
                    node2 = new Node(69, r1);
                } else {
                    node2 = new Node(i, new Node(45), node);
                }
                return node2;
            case 32:
                if (type == 39) {
                    node.setType(137);
                    return node;
                }
                break;
        }
        return new Node(i, node);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.Node createCallOrNew(int r5, org.mozilla.javascript.Node r6) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        r2 = r6.getType();
        r3 = 39;
        if (r2 != r3) goto L_0x0032;
    L_0x000a:
        r2 = r6.getString();
        r3 = "eval";
        r3 = r2.equals(r3);
        if (r3 == 0) goto L_0x0027;
    L_0x0017:
        r1 = new org.mozilla.javascript.Node;
        r1.<init>(r5, r6);
        if (r0 == 0) goto L_0x0026;
    L_0x001e:
        r4.setRequiresActivation();
        r2 = 10;
        r1.putIntProp(r2, r0);
    L_0x0026:
        return r1;
    L_0x0027:
        r0 = "With";
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x004b;
    L_0x0030:
        r0 = 2;
        goto L_0x0017;
    L_0x0032:
        r2 = r6.getType();
        r3 = 33;
        if (r2 != r3) goto L_0x004b;
    L_0x003a:
        r2 = r6.getLastChild();
        r2 = r2.getString();
        r3 = "eval";
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0017;
    L_0x004b:
        r0 = r1;
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.IRFactory.createCallOrNew(int, org.mozilla.javascript.Node):org.mozilla.javascript.Node");
    }

    private Node createIncDec(int i, boolean z, Node node) {
        Node makeReference = makeReference(node);
        switch (makeReference.getType()) {
            case 33:
            case 36:
            case 39:
            case 67:
                Node node2 = new Node(i, makeReference);
                int i2 = 0;
                if (i == 107) {
                    i2 = 1;
                }
                if (z) {
                    i2 |= 2;
                }
                node2.putIntProp(13, i2);
                return node2;
            default:
                throw Kit.codeBug();
        }
    }

    private Node createPropertyGet(Node node, String str, String str2, int i) {
        if (str != null || i != 0) {
            return createMemberRefGet(node, str, Node.newString(str2), i | 1);
        }
        if (node == null) {
            return createName(str2);
        }
        checkActivationName(str2, 33);
        if (!ScriptRuntime.isSpecialProperty(str2)) {
            return new Node(33, node, Node.newString(str2));
        }
        Node node2 = new Node(71, node);
        node2.putProp(17, str2);
        return new Node(67, node2);
    }

    private Node createElementGet(Node node, String str, Node node2, int i) {
        if (str != null || i != 0) {
            return createMemberRefGet(node, str, node2, i);
        }
        if (node != null) {
            return new Node(36, node, node2);
        }
        throw Kit.codeBug();
    }

    private Node createMemberRefGet(Node node, String str, Node node2, int i) {
        Node node3;
        Node node4;
        if (str == null) {
            node3 = null;
        } else if (str.equals("*")) {
            node3 = new Node(42);
        } else {
            node3 = createName(str);
        }
        if (node == null) {
            if (str == null) {
                node4 = new Node(79, node2);
            } else {
                node4 = new Node(80, node3, node2);
            }
        } else if (str == null) {
            node4 = new Node(77, node, node2);
        } else {
            node4 = new Node(78, node, node3, node2);
        }
        if (i != 0) {
            node4.putIntProp(16, i);
        }
        return new Node(67, node4);
    }

    private Node createBinary(int i, Node node, Node node2) {
        double d;
        int isAlwaysDefinedBoolean;
        switch (i) {
            case 21:
                if (node.type == 41) {
                    String string;
                    if (node2.type == 41) {
                        string = node2.getString();
                    } else if (node2.type == 40) {
                        string = ScriptRuntime.numberToString(node2.getDouble(), 10);
                    }
                    node.setString(node.getString().concat(string));
                    return node;
                } else if (node.type == 40) {
                    if (node2.type == 40) {
                        node.setDouble(node.getDouble() + node2.getDouble());
                        return node;
                    } else if (node2.type == 41) {
                        node2.setString(ScriptRuntime.numberToString(node.getDouble(), 10).concat(node2.getString()));
                        return node2;
                    }
                }
                break;
            case 22:
                if (node.type == 40) {
                    d = node.getDouble();
                    if (node2.type == 40) {
                        node.setDouble(d - node2.getDouble());
                        return node;
                    } else if (d == 0.0d) {
                        return new Node(29, node2);
                    }
                } else if (node2.type == 40 && node2.getDouble() == 0.0d) {
                    return new Node(28, node);
                }
                break;
            case 23:
                if (node.type == 40) {
                    d = node.getDouble();
                    if (node2.type == 40) {
                        node.setDouble(d * node2.getDouble());
                        return node;
                    } else if (d == 1.0d) {
                        return new Node(28, node2);
                    }
                } else if (node2.type == 40 && node2.getDouble() == 1.0d) {
                    return new Node(28, node);
                }
                break;
            case 24:
                if (node2.type == 40) {
                    d = node2.getDouble();
                    if (node.type == 40) {
                        node.setDouble(node.getDouble() / d);
                        return node;
                    } else if (d == 1.0d) {
                        return new Node(28, node);
                    }
                }
                break;
            case 104:
                isAlwaysDefinedBoolean = isAlwaysDefinedBoolean(node);
                if (isAlwaysDefinedBoolean == 1) {
                    return node;
                }
                if (isAlwaysDefinedBoolean == -1) {
                    return node2;
                }
                break;
            case 105:
                isAlwaysDefinedBoolean = isAlwaysDefinedBoolean(node);
                if (isAlwaysDefinedBoolean == -1) {
                    return node;
                }
                if (isAlwaysDefinedBoolean == 1) {
                    return node2;
                }
                break;
        }
        return new Node(i, node, node2);
    }

    private Node createAssignment(int i, Node node, Node node2) {
        Node makeReference = makeReference(node);
        if (makeReference != null) {
            int i2;
            switch (i) {
                case 90:
                    return simpleAssignment(makeReference, node2);
                case 91:
                    i2 = 9;
                    break;
                case 92:
                    i2 = 10;
                    break;
                case 93:
                    i2 = 11;
                    break;
                case 94:
                    i2 = 18;
                    break;
                case 95:
                    i2 = 19;
                    break;
                case 96:
                    i2 = 20;
                    break;
                case 97:
                    i2 = 21;
                    break;
                case 98:
                    i2 = 22;
                    break;
                case 99:
                    i2 = 23;
                    break;
                case 100:
                    i2 = 24;
                    break;
                case 101:
                    i2 = 25;
                    break;
                default:
                    throw Kit.codeBug();
            }
            int type = makeReference.getType();
            switch (type) {
                case 33:
                case 36:
                    return new Node(type == 33 ? 139 : 140, makeReference.getFirstChild(), makeReference.getLastChild(), new Node(i2, new Node(138), node2));
                case 39:
                    return new Node(8, Node.newString(49, makeReference.getString()), new Node(i2, makeReference, node2));
                case 67:
                    makeReference = makeReference.getFirstChild();
                    checkMutableReference(makeReference);
                    return new Node(142, makeReference, new Node(i2, new Node(138), node2));
                default:
                    throw Kit.codeBug();
            }
        } else if (node.getType() != 65 && node.getType() != 66) {
            reportError("msg.bad.assign.left");
            return node2;
        } else if (i == 90) {
            return createDestructuringAssignment(-1, node, node2);
        } else {
            reportError("msg.bad.destruct.op");
            return node2;
        }
    }

    private Node createUseLocal(Node node) {
        if (141 != node.getType()) {
            throw Kit.codeBug();
        }
        Node node2 = new Node(54);
        node2.putProp(3, node);
        return node2;
    }

    private Jump makeJump(int i, Node node) {
        Jump jump = new Jump(i);
        jump.target = node;
        return jump;
    }

    private Node makeReference(Node node) {
        switch (node.getType()) {
            case 33:
            case 36:
            case 39:
            case 67:
                return node;
            case 38:
                node.setType(70);
                return new Node(67, node);
            default:
                return null;
        }
    }

    private static int isAlwaysDefinedBoolean(Node node) {
        switch (node.getType()) {
            case 40:
                double d = node.getDouble();
                if (d != d || d == 0.0d) {
                    return -1;
                }
                return 1;
            case 42:
            case 44:
                return -1;
            case 45:
                return 1;
            default:
                return 0;
        }
    }

    boolean isDestructuring(Node node) {
        return (node instanceof DestructuringForm) && ((DestructuringForm) node).isDestructuring();
    }

    Node decompileFunctionHeader(FunctionNode functionNode) {
        Node node;
        if (functionNode.getFunctionName() != null) {
            this.decompiler.addName(functionNode.getName());
            node = null;
        } else if (functionNode.getMemberExprNode() != null) {
            node = transform(functionNode.getMemberExprNode());
        } else {
            node = null;
        }
        this.decompiler.addToken(87);
        List params = functionNode.getParams();
        for (int i = 0; i < params.size(); i++) {
            decompile((AstNode) params.get(i));
            if (i < params.size() - 1) {
                this.decompiler.addToken(89);
            }
        }
        this.decompiler.addToken(88);
        if (!functionNode.isExpressionClosure()) {
            this.decompiler.addEOL(85);
        }
        return node;
    }

    void decompile(AstNode astNode) {
        switch (astNode.getType()) {
            case 33:
                decompilePropertyGet((PropertyGet) astNode);
                return;
            case 36:
                decompileElementGet((ElementGet) astNode);
                return;
            case 39:
                this.decompiler.addName(((Name) astNode).getIdentifier());
                return;
            case 40:
                this.decompiler.addNumber(((NumberLiteral) astNode).getNumber());
                return;
            case 41:
                this.decompiler.addString(((StringLiteral) astNode).getValue());
                return;
            case 43:
                this.decompiler.addToken(astNode.getType());
                return;
            case 65:
                decompileArrayLiteral((ArrayLiteral) astNode);
                return;
            case 66:
                decompileObjectLiteral((ObjectLiteral) astNode);
                return;
            case 128:
                return;
            default:
                Kit.codeBug("unexpected token: " + Token.typeToName(astNode.getType()));
                return;
        }
    }

    void decompileArrayLiteral(ArrayLiteral arrayLiteral) {
        this.decompiler.addToken(83);
        List elements = arrayLiteral.getElements();
        int size = elements.size();
        for (int i = 0; i < size; i++) {
            decompile((AstNode) elements.get(i));
            if (i < size - 1) {
                this.decompiler.addToken(89);
            }
        }
        this.decompiler.addToken(84);
    }

    void decompileObjectLiteral(ObjectLiteral objectLiteral) {
        this.decompiler.addToken(85);
        List elements = objectLiteral.getElements();
        int size = elements.size();
        for (int i = 0; i < size; i++) {
            ObjectProperty objectProperty = (ObjectProperty) elements.get(i);
            boolean equals = Boolean.TRUE.equals(objectProperty.getProp(26));
            decompile(objectProperty.getLeft());
            if (!equals) {
                this.decompiler.addToken(103);
                decompile(objectProperty.getRight());
            }
            if (i < size - 1) {
                this.decompiler.addToken(89);
            }
        }
        this.decompiler.addToken(86);
    }

    void decompilePropertyGet(PropertyGet propertyGet) {
        decompile(propertyGet.getTarget());
        this.decompiler.addToken(108);
        decompile(propertyGet.getProperty());
    }

    void decompileElementGet(ElementGet elementGet) {
        decompile(elementGet.getTarget());
        this.decompiler.addToken(83);
        decompile(elementGet.getElement());
        this.decompiler.addToken(84);
    }
}
