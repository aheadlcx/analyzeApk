package org.mozilla.javascript;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.mozilla.javascript.Token.CommentType;
import org.mozilla.javascript.ast.ArrayComprehension;
import org.mozilla.javascript.ast.ArrayComprehensionLoop;
import org.mozilla.javascript.ast.ArrayLiteral;
import org.mozilla.javascript.ast.Assignment;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.Block;
import org.mozilla.javascript.ast.BreakStatement;
import org.mozilla.javascript.ast.CatchClause;
import org.mozilla.javascript.ast.Comment;
import org.mozilla.javascript.ast.ConditionalExpression;
import org.mozilla.javascript.ast.ContinueStatement;
import org.mozilla.javascript.ast.DestructuringForm;
import org.mozilla.javascript.ast.DoLoop;
import org.mozilla.javascript.ast.ElementGet;
import org.mozilla.javascript.ast.EmptyExpression;
import org.mozilla.javascript.ast.EmptyStatement;
import org.mozilla.javascript.ast.ErrorNode;
import org.mozilla.javascript.ast.ExpressionStatement;
import org.mozilla.javascript.ast.ForInLoop;
import org.mozilla.javascript.ast.ForLoop;
import org.mozilla.javascript.ast.FunctionCall;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.GeneratorExpression;
import org.mozilla.javascript.ast.GeneratorExpressionLoop;
import org.mozilla.javascript.ast.IdeErrorReporter;
import org.mozilla.javascript.ast.IfStatement;
import org.mozilla.javascript.ast.InfixExpression;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.KeywordLiteral;
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

public class Parser {
    public static final int ARGC_LIMIT = 65536;
    static final int CLEAR_TI_MASK = 65535;
    private static final int GET_ENTRY = 2;
    private static final int PROP_ENTRY = 1;
    private static final int SET_ENTRY = 4;
    static final int TI_AFTER_EOL = 65536;
    static final int TI_CHECK_LABEL = 131072;
    boolean calledByCompileFunction;
    CompilerEnvirons compilerEnv;
    private int currentFlaggedToken;
    private Comment currentJsDocComment;
    private LabeledStatement currentLabel;
    Scope currentScope;
    ScriptNode currentScriptOrFn;
    private int currentToken;
    private int endFlags;
    private IdeErrorReporter errorCollector;
    private ErrorReporter errorReporter;
    private boolean inDestructuringAssignment;
    private boolean inForInit;
    protected boolean inUseStrictDirective;
    private Map<String, LabeledStatement> labelSet;
    private List<Jump> loopAndSwitchSet;
    private List<Loop> loopSet;
    protected int nestingOfFunction;
    private boolean parseFinished;
    private int prevNameTokenLineno;
    private int prevNameTokenStart;
    private String prevNameTokenString;
    private List<Comment> scannedComments;
    private char[] sourceChars;
    private String sourceURI;
    private int syntaxErrorCount;
    private TokenStream ts;

    private static class ConditionData {
        AstNode condition;
        int lp;
        int rp;

        private ConditionData() {
            this.lp = -1;
            this.rp = -1;
        }
    }

    private static class ParserException extends RuntimeException {
        static final long serialVersionUID = 5882582646773765630L;

        private ParserException() {
        }
    }

    protected class PerFunctionVariables {
        private Scope savedCurrentScope;
        private ScriptNode savedCurrentScriptOrFn;
        private int savedEndFlags;
        private boolean savedInForInit;
        private Map<String, LabeledStatement> savedLabelSet;
        private List<Jump> savedLoopAndSwitchSet;
        private List<Loop> savedLoopSet;

        PerFunctionVariables(FunctionNode functionNode) {
            this.savedCurrentScriptOrFn = Parser.this.currentScriptOrFn;
            Parser.this.currentScriptOrFn = functionNode;
            this.savedCurrentScope = Parser.this.currentScope;
            Parser.this.currentScope = functionNode;
            this.savedLabelSet = Parser.this.labelSet;
            Parser.this.labelSet = null;
            this.savedLoopSet = Parser.this.loopSet;
            Parser.this.loopSet = null;
            this.savedLoopAndSwitchSet = Parser.this.loopAndSwitchSet;
            Parser.this.loopAndSwitchSet = null;
            this.savedEndFlags = Parser.this.endFlags;
            Parser.this.endFlags = 0;
            this.savedInForInit = Parser.this.inForInit;
            Parser.this.inForInit = false;
        }

        void restore() {
            Parser.this.currentScriptOrFn = this.savedCurrentScriptOrFn;
            Parser.this.currentScope = this.savedCurrentScope;
            Parser.this.labelSet = this.savedLabelSet;
            Parser.this.loopSet = this.savedLoopSet;
            Parser.this.loopAndSwitchSet = this.savedLoopAndSwitchSet;
            Parser.this.endFlags = this.savedEndFlags;
            Parser.this.inForInit = this.savedInForInit;
        }
    }

    public Parser() {
        this(new CompilerEnvirons());
    }

    public Parser(CompilerEnvirons compilerEnvirons) {
        this(compilerEnvirons, compilerEnvirons.getErrorReporter());
    }

    public Parser(CompilerEnvirons compilerEnvirons, ErrorReporter errorReporter) {
        this.currentFlaggedToken = 0;
        this.prevNameTokenString = "";
        this.compilerEnv = compilerEnvirons;
        this.errorReporter = errorReporter;
        if (errorReporter instanceof IdeErrorReporter) {
            this.errorCollector = (IdeErrorReporter) errorReporter;
        }
    }

    void addStrictWarning(String str, String str2) {
        int i;
        int i2 = -1;
        if (this.ts != null) {
            i = this.ts.tokenBeg;
            i2 = this.ts.tokenEnd - this.ts.tokenBeg;
        } else {
            i = -1;
        }
        addStrictWarning(str, str2, i, i2);
    }

    void addStrictWarning(String str, String str2, int i, int i2) {
        if (this.compilerEnv.isStrictMode()) {
            addWarning(str, str2, i, i2);
        }
    }

    void addWarning(String str, String str2) {
        int i;
        int i2 = -1;
        if (this.ts != null) {
            i = this.ts.tokenBeg;
            i2 = this.ts.tokenEnd - this.ts.tokenBeg;
        } else {
            i = -1;
        }
        addWarning(str, str2, i, i2);
    }

    void addWarning(String str, int i, int i2) {
        addWarning(str, null, i, i2);
    }

    void addWarning(String str, String str2, int i, int i2) {
        String lookupMessage = lookupMessage(str, str2);
        if (this.compilerEnv.reportWarningAsError()) {
            addError(str, str2, i, i2);
        } else if (this.errorCollector != null) {
            this.errorCollector.warning(lookupMessage, this.sourceURI, i, i2);
        } else {
            this.errorReporter.warning(lookupMessage, this.sourceURI, this.ts.getLineno(), this.ts.getLine(), this.ts.getOffset());
        }
    }

    void addError(String str) {
        addError(str, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
    }

    void addError(String str, int i, int i2) {
        addError(str, null, i, i2);
    }

    void addError(String str, String str2) {
        addError(str, str2, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
    }

    void addError(String str, String str2, int i, int i2) {
        int i3 = 1;
        this.syntaxErrorCount++;
        String lookupMessage = lookupMessage(str, str2);
        if (this.errorCollector != null) {
            this.errorCollector.error(lookupMessage, this.sourceURI, i, i2);
            return;
        }
        int lineno;
        String str3 = "";
        if (this.ts != null) {
            lineno = this.ts.getLineno();
            str3 = this.ts.getLine();
            i3 = this.ts.getOffset();
        } else {
            lineno = 1;
        }
        this.errorReporter.error(lookupMessage, this.sourceURI, lineno, str3, i3);
    }

    private void addStrictWarning(String str, String str2, int i, int i2, int i3, String str3, int i4) {
        if (this.compilerEnv.isStrictMode()) {
            addWarning(str, str2, i, i2, i3, str3, i4);
        }
    }

    private void addWarning(String str, String str2, int i, int i2, int i3, String str3, int i4) {
        String lookupMessage = lookupMessage(str, str2);
        if (this.compilerEnv.reportWarningAsError()) {
            addError(str, str2, i, i2, i3, str3, i4);
        } else if (this.errorCollector != null) {
            this.errorCollector.warning(lookupMessage, this.sourceURI, i, i2);
        } else {
            this.errorReporter.warning(lookupMessage, this.sourceURI, i3, str3, i4);
        }
    }

    private void addError(String str, String str2, int i, int i2, int i3, String str3, int i4) {
        this.syntaxErrorCount++;
        String lookupMessage = lookupMessage(str, str2);
        if (this.errorCollector != null) {
            this.errorCollector.error(lookupMessage, this.sourceURI, i, i2);
        } else {
            this.errorReporter.error(lookupMessage, this.sourceURI, i3, str3, i4);
        }
    }

    String lookupMessage(String str) {
        return lookupMessage(str, null);
    }

    String lookupMessage(String str, String str2) {
        return str2 == null ? ScriptRuntime.getMessage0(str) : ScriptRuntime.getMessage1(str, str2);
    }

    void reportError(String str) {
        reportError(str, null);
    }

    void reportError(String str, String str2) {
        if (this.ts == null) {
            reportError(str, str2, 1, 1);
        } else {
            reportError(str, str2, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
        }
    }

    void reportError(String str, int i, int i2) {
        reportError(str, null, i, i2);
    }

    void reportError(String str, String str2, int i, int i2) {
        addError(str, i, i2);
        if (!this.compilerEnv.recoverFromErrors()) {
            throw new ParserException();
        }
    }

    private int getNodeEnd(AstNode astNode) {
        return astNode.getPosition() + astNode.getLength();
    }

    private void recordComment(int i, String str) {
        if (this.scannedComments == null) {
            this.scannedComments = new ArrayList();
        }
        Comment comment = new Comment(this.ts.tokenBeg, this.ts.getTokenLength(), this.ts.commentType, str);
        if (this.ts.commentType == CommentType.JSDOC && this.compilerEnv.isRecordingLocalJsDocComments()) {
            this.currentJsDocComment = comment;
        }
        comment.setLineno(i);
        this.scannedComments.add(comment);
    }

    private Comment getAndResetJsDoc() {
        Comment comment = this.currentJsDocComment;
        this.currentJsDocComment = null;
        return comment;
    }

    private int getNumberOfEols(String str) {
        int i = 0;
        for (int length = str.length() - 1; length >= 0; length--) {
            if (str.charAt(length) == '\n') {
                i++;
            }
        }
        return i;
    }

    private int peekToken() throws IOException {
        if (this.currentFlaggedToken != 0) {
            return this.currentToken;
        }
        int i;
        int lineno = this.ts.getLineno();
        int token = this.ts.getToken();
        Object obj = null;
        while (true) {
            if (token != 1 && token != 161) {
                break;
            }
            if (token == 1) {
                lineno++;
                obj = 1;
            } else if (this.compilerEnv.isRecordingComments()) {
                String andResetCurrentComment = this.ts.getAndResetCurrentComment();
                recordComment(lineno, andResetCurrentComment);
                lineno += getNumberOfEols(andResetCurrentComment);
            }
            token = this.ts.getToken();
        }
        this.currentToken = token;
        if (obj != null) {
            i = 65536;
        } else {
            i = 0;
        }
        this.currentFlaggedToken = i | token;
        return this.currentToken;
    }

    private int peekFlaggedToken() throws IOException {
        peekToken();
        return this.currentFlaggedToken;
    }

    private void consumeToken() {
        this.currentFlaggedToken = 0;
    }

    private int nextToken() throws IOException {
        int peekToken = peekToken();
        consumeToken();
        return peekToken;
    }

    private int nextFlaggedToken() throws IOException {
        peekToken();
        int i = this.currentFlaggedToken;
        consumeToken();
        return i;
    }

    private boolean matchToken(int i) throws IOException {
        if (peekToken() != i) {
            return false;
        }
        consumeToken();
        return true;
    }

    private int peekTokenOrEOL() throws IOException {
        int peekToken = peekToken();
        if ((this.currentFlaggedToken & 65536) != 0) {
            return 1;
        }
        return peekToken;
    }

    private boolean mustMatchToken(int i, String str) throws IOException {
        return mustMatchToken(i, str, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
    }

    private boolean mustMatchToken(int i, String str, int i2, int i3) throws IOException {
        if (matchToken(i)) {
            return true;
        }
        reportError(str, i2, i3);
        return false;
    }

    private void mustHaveXML() {
        if (!this.compilerEnv.isXmlAvailable()) {
            reportError("msg.XML.not.available");
        }
    }

    public boolean eof() {
        return this.ts.eof();
    }

    boolean insideFunction() {
        return this.nestingOfFunction != 0;
    }

    void pushScope(Scope scope) {
        Scope parentScope = scope.getParentScope();
        if (parentScope == null) {
            this.currentScope.addChildScope(scope);
        } else if (parentScope != this.currentScope) {
            codeBug();
        }
        this.currentScope = scope;
    }

    void popScope() {
        this.currentScope = this.currentScope.getParentScope();
    }

    private void enterLoop(Loop loop) {
        if (this.loopSet == null) {
            this.loopSet = new ArrayList();
        }
        this.loopSet.add(loop);
        if (this.loopAndSwitchSet == null) {
            this.loopAndSwitchSet = new ArrayList();
        }
        this.loopAndSwitchSet.add(loop);
        pushScope(loop);
        if (this.currentLabel != null) {
            this.currentLabel.setStatement(loop);
            this.currentLabel.getFirstLabel().setLoop(loop);
            loop.setRelative(-this.currentLabel.getPosition());
        }
    }

    private void exitLoop() {
        Loop loop = (Loop) this.loopSet.remove(this.loopSet.size() - 1);
        this.loopAndSwitchSet.remove(this.loopAndSwitchSet.size() - 1);
        if (loop.getParent() != null) {
            loop.setRelative(loop.getParent().getPosition());
        }
        popScope();
    }

    private void enterSwitch(SwitchStatement switchStatement) {
        if (this.loopAndSwitchSet == null) {
            this.loopAndSwitchSet = new ArrayList();
        }
        this.loopAndSwitchSet.add(switchStatement);
    }

    private void exitSwitch() {
        this.loopAndSwitchSet.remove(this.loopAndSwitchSet.size() - 1);
    }

    public AstRoot parse(String str, String str2, int i) {
        if (this.parseFinished) {
            throw new IllegalStateException("parser reused");
        }
        this.sourceURI = str2;
        if (this.compilerEnv.isIdeMode()) {
            this.sourceChars = str.toCharArray();
        }
        this.ts = new TokenStream(this, null, str, i);
        try {
            AstRoot parse = parse();
            this.parseFinished = true;
            return parse;
        } catch (IOException e) {
            throw new IllegalStateException();
        } catch (Throwable th) {
            this.parseFinished = true;
        }
    }

    public AstRoot parse(Reader reader, String str, int i) throws IOException {
        if (this.parseFinished) {
            throw new IllegalStateException("parser reused");
        } else if (this.compilerEnv.isIdeMode()) {
            return parse(readFully(reader), str, i);
        } else {
            try {
                this.sourceURI = str;
                this.ts = new TokenStream(this, reader, null, i);
                AstRoot parse = parse();
                return parse;
            } finally {
                this.parseFinished = true;
            }
        }
    }

    private AstRoot parse() throws IOException {
        String lookupMessage;
        AstNode astRoot = new AstRoot(0);
        this.currentScriptOrFn = astRoot;
        this.currentScope = astRoot;
        int i = this.ts.lineno;
        boolean z = this.inUseStrictDirective;
        this.inUseStrictDirective = false;
        boolean z2 = true;
        int i2 = 0;
        loop2:
        while (true) {
            int peekToken = peekToken();
            if (peekToken <= 0) {
                break loop2;
            } else if (peekToken == 109) {
                consumeToken();
                try {
                    if (this.calledByCompileFunction) {
                        peekToken = 2;
                    } else {
                        peekToken = 1;
                    }
                    r1 = function(peekToken);
                    try {
                        i2 = getNodeEnd(r1);
                        astRoot.addChildToBack(r1);
                        r1.setParent(astRoot);
                    } catch (StackOverflowError e) {
                        r1 = e;
                        r1 = lookupMessage("msg.too.deep.parser.recursion");
                        z2 = this.compilerEnv.isIdeMode();
                        if (z2) {
                            break;
                        }
                        throw Context.reportRuntimeError(r1, this.sourceURI, this.ts.lineno, null, 0);
                    } finally {
                        this.inUseStrictDirective = z;
                    }
                } catch (ParserException e2) {
                }
            } else {
                r1 = statement();
                if (z2) {
                    String directive = getDirective(r1);
                    if (directive == null) {
                        z2 = false;
                    } else if (directive.equals("use strict")) {
                    }
                }
                i2 = getNodeEnd(r1);
                astRoot.addChildToBack(r1);
                r1.setParent(astRoot);
            }
        }
        int i3 = i2;
        if (this.syntaxErrorCount != 0) {
            lookupMessage = lookupMessage("msg.got.syntax.errors", String.valueOf(this.syntaxErrorCount));
            if (!this.compilerEnv.isIdeMode()) {
                throw this.errorReporter.runtimeError(lookupMessage, this.sourceURI, i, null, 0);
            }
        }
        if (this.scannedComments != null) {
            i3 = Math.max(i3, getNodeEnd((AstNode) this.scannedComments.get(this.scannedComments.size() - 1)));
            for (Comment addComment : this.scannedComments) {
                astRoot.addComment(addComment);
            }
        }
        astRoot.setLength(i3 - 0);
        astRoot.setSourceName(this.sourceURI);
        astRoot.setBaseLineno(i);
        astRoot.setEndLineno(this.ts.lineno);
        return astRoot;
        this.inUseStrictDirective = z;
        i3 = i2;
        if (this.syntaxErrorCount != 0) {
            lookupMessage = lookupMessage("msg.got.syntax.errors", String.valueOf(this.syntaxErrorCount));
            if (this.compilerEnv.isIdeMode()) {
                throw this.errorReporter.runtimeError(lookupMessage, this.sourceURI, i, null, 0);
            }
        }
        if (this.scannedComments != null) {
            i3 = Math.max(i3, getNodeEnd((AstNode) this.scannedComments.get(this.scannedComments.size() - 1)));
            while (r1.hasNext()) {
                astRoot.addComment(addComment);
            }
        }
        astRoot.setLength(i3 - 0);
        astRoot.setSourceName(this.sourceURI);
        astRoot.setBaseLineno(i);
        astRoot.setEndLineno(this.ts.lineno);
        return astRoot;
    }

    private org.mozilla.javascript.ast.AstNode parseFunctionBody() throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:org.mozilla.javascript.Parser.parseFunctionBody():org.mozilla.javascript.ast.AstNode. bs: [B:7:0x0035, B:18:0x007d]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r9 = this;
        r1 = 0;
        r2 = 1;
        r0 = 85;
        r0 = r9.matchToken(r0);
        if (r0 != 0) goto L_0x00c3;
    L_0x000a:
        r0 = r9.compilerEnv;
        r0 = r0.getLanguageVersion();
        r3 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        if (r0 >= r3) goto L_0x007b;
    L_0x0014:
        r0 = "msg.no.brace.body";
        r9.reportError(r0);
        r0 = r1;
    L_0x001b:
        r3 = r9.nestingOfFunction;
        r3 = r3 + 1;
        r9.nestingOfFunction = r3;
        r3 = r9.ts;
        r4 = r3.tokenBeg;
        r5 = new org.mozilla.javascript.ast.Block;
        r5.<init>(r4);
        r6 = r9.inUseStrictDirective;
        r3 = r9.ts;
        r3 = r3.lineno;
        r5.setLineno(r3);
        if (r0 == 0) goto L_0x00c1;
    L_0x0035:
        r1 = new org.mozilla.javascript.ast.ReturnStatement;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r2 = r9.ts;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r2 = r2.lineno;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r1.<init>(r2);	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r2 = r9.assignExpr();	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r1.setReturnValue(r2);	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r2 = 25;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r3 = java.lang.Boolean.TRUE;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r1.putProp(r2, r3);	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r2 = 25;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r3 = java.lang.Boolean.TRUE;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r5.putProp(r2, r3);	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r5.addStatement(r1);	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
    L_0x0056:
        r1 = r9.nestingOfFunction;
        r1 = r1 + -1;
        r9.nestingOfFunction = r1;
        r9.inUseStrictDirective = r6;
    L_0x005e:
        r1 = r9.ts;
        r1 = r1.tokenEnd;
        r9.getAndResetJsDoc();
        if (r0 != 0) goto L_0x00bf;
    L_0x0067:
        r0 = 86;
        r2 = "msg.no.brace.after.body";
        r0 = r9.mustMatchToken(r0, r2);
        if (r0 == 0) goto L_0x00bf;
    L_0x0072:
        r0 = r9.ts;
        r0 = r0.tokenEnd;
    L_0x0076:
        r0 = r0 - r4;
        r5.setLength(r0);
        return r5;
    L_0x007b:
        r0 = r2;
        goto L_0x001b;
    L_0x007d:
        r2 = r9.peekToken();	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        switch(r2) {
            case -1: goto L_0x0056;
            case 0: goto L_0x0056;
            case 86: goto L_0x0056;
            case 109: goto L_0x009f;
            default: goto L_0x0084;
        };	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
    L_0x0084:
        r2 = r9.statement();	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        if (r3 == 0) goto L_0x0091;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
    L_0x008a:
        r7 = r9.getDirective(r2);	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        if (r7 != 0) goto L_0x00a8;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
    L_0x0090:
        r3 = r1;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
    L_0x0091:
        r5.addStatement(r2);	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        goto L_0x007d;
    L_0x0095:
        r1 = move-exception;
        r1 = r9.nestingOfFunction;
        r1 = r1 + -1;
        r9.nestingOfFunction = r1;
        r9.inUseStrictDirective = r6;
        goto L_0x005e;
    L_0x009f:
        r9.consumeToken();	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r2 = 1;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r2 = r9.function(r2);	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        goto L_0x0091;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
    L_0x00a8:
        r8 = "use strict";	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r7 = r7.equals(r8);	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        if (r7 == 0) goto L_0x0091;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
    L_0x00b1:
        r7 = 1;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        r9.inUseStrictDirective = r7;	 Catch:{ ParserException -> 0x0095, all -> 0x00b5 }
        goto L_0x0091;
    L_0x00b5:
        r0 = move-exception;
        r1 = r9.nestingOfFunction;
        r1 = r1 + -1;
        r9.nestingOfFunction = r1;
        r9.inUseStrictDirective = r6;
        throw r0;
    L_0x00bf:
        r0 = r1;
        goto L_0x0076;
    L_0x00c1:
        r3 = r2;
        goto L_0x007d;
    L_0x00c3:
        r0 = r1;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.parseFunctionBody():org.mozilla.javascript.ast.AstNode");
    }

    private String getDirective(AstNode astNode) {
        if (astNode instanceof ExpressionStatement) {
            AstNode expression = ((ExpressionStatement) astNode).getExpression();
            if (expression instanceof StringLiteral) {
                return ((StringLiteral) expression).getValue();
            }
        }
        return null;
    }

    private void parseFunctionParams(FunctionNode functionNode) throws IOException {
        if (matchToken(88)) {
            functionNode.setRp(this.ts.tokenBeg - functionNode.getPosition());
            return;
        }
        Map map = null;
        Set hashSet = new HashSet();
        do {
            int peekToken = peekToken();
            if (peekToken == 83 || peekToken == 85) {
                AstNode destructuringPrimaryExpr = destructuringPrimaryExpr();
                markDestructuring(destructuringPrimaryExpr);
                functionNode.addParam(destructuringPrimaryExpr);
                if (map == null) {
                    map = new HashMap();
                }
                String nextTempName = this.currentScriptOrFn.getNextTempName();
                defineSymbol(87, nextTempName, false);
                map.put(nextTempName, destructuringPrimaryExpr);
            } else if (mustMatchToken(39, "msg.no.parm")) {
                functionNode.addParam(createNameNode());
                String string = this.ts.getString();
                defineSymbol(87, string);
                if (this.inUseStrictDirective) {
                    if ("eval".equals(string) || "arguments".equals(string)) {
                        reportError("msg.bad.id.strict", string);
                    }
                    if (hashSet.contains(string)) {
                        addError("msg.dup.param.strict", string);
                    }
                    hashSet.add(string);
                }
            } else {
                functionNode.addParam(makeErrorNode());
            }
        } while (matchToken(89));
        if (map != null) {
            Node node = new Node(89);
            for (Entry entry : map.entrySet()) {
                node.addChildToBack(createDestructuringAssignment(122, (Node) entry.getValue(), createName((String) entry.getKey())));
            }
            functionNode.putProp(23, node);
        }
        if (mustMatchToken(88, "msg.no.paren.after.parms")) {
            functionNode.setRp(this.ts.tokenBeg - functionNode.getPosition());
        }
    }

    private FunctionNode function(int i) throws IOException {
        AstNode createNameNode;
        String identifier;
        int i2;
        int i3;
        Name name = null;
        int i4 = this.ts.lineno;
        int i5 = this.ts.tokenBeg;
        if (matchToken(39)) {
            createNameNode = createNameNode(true, 39);
            if (this.inUseStrictDirective) {
                identifier = createNameNode.getIdentifier();
                if ("eval".equals(identifier) || "arguments".equals(identifier)) {
                    reportError("msg.bad.id.strict", identifier);
                }
            }
            AstNode astNode;
            if (matchToken(87)) {
                astNode = createNameNode;
                createNameNode = null;
            } else {
                if (this.compilerEnv.isAllowMemberExprAsFunctionName()) {
                    createNameNode = memberExprTail(false, createNameNode);
                } else {
                    astNode = createNameNode;
                    createNameNode = null;
                }
                mustMatchToken(87, "msg.no.paren.parms");
            }
        } else if (matchToken(87)) {
            createNameNode = null;
        } else {
            if (this.compilerEnv.isAllowMemberExprAsFunctionName()) {
                createNameNode = memberExpr(false);
            } else {
                createNameNode = null;
            }
            mustMatchToken(87, "msg.no.paren.parms");
        }
        if (this.currentToken == 87) {
            i2 = this.ts.tokenBeg;
        } else {
            i2 = -1;
        }
        if (createNameNode != null) {
            i3 = 2;
        } else {
            i3 = i;
        }
        if (!(i3 == 2 || name == null || name.length() <= 0)) {
            defineSymbol(109, name.getIdentifier());
        }
        FunctionNode functionNode = new FunctionNode(i5, name);
        functionNode.setFunctionType(i);
        if (i2 != -1) {
            functionNode.setLp(i2 - i5);
        }
        functionNode.setJsDocNode(getAndResetJsDoc());
        PerFunctionVariables perFunctionVariables = new PerFunctionVariables(functionNode);
        try {
            parseFunctionParams(functionNode);
            functionNode.setBody(parseFunctionBody());
            functionNode.setEncodedSourceBounds(i5, this.ts.tokenEnd);
            functionNode.setLength(this.ts.tokenEnd - i5);
            if (this.compilerEnv.isStrictMode() && !functionNode.getBody().hasConsistentReturnUsage()) {
                identifier = (name == null || name.length() <= 0) ? "msg.anon.no.return.value" : "msg.no.return.value";
                addStrictWarning(identifier, name == null ? "" : name.getIdentifier());
            }
            perFunctionVariables.restore();
            if (createNameNode != null) {
                Kit.codeBug();
                functionNode.setMemberExprNode(createNameNode);
            }
            functionNode.setSourceName(this.sourceURI);
            functionNode.setBaseLineno(i4);
            functionNode.setEndLineno(this.ts.lineno);
            if (this.compilerEnv.isIdeMode()) {
                functionNode.setParentScope(this.currentScope);
            }
            return functionNode;
        } catch (Throwable th) {
            perFunctionVariables.restore();
        }
    }

    private AstNode statements(AstNode astNode) throws IOException {
        if (!(this.currentToken == 85 || this.compilerEnv.isIdeMode())) {
            codeBug();
        }
        int i = this.ts.tokenBeg;
        if (astNode == null) {
            astNode = new Block(i);
        }
        astNode.setLineno(this.ts.lineno);
        while (true) {
            int peekToken = peekToken();
            if (peekToken <= 0 || peekToken == 86) {
                astNode.setLength(this.ts.tokenBeg - i);
            } else {
                astNode.addChild(statement());
            }
        }
        astNode.setLength(this.ts.tokenBeg - i);
        return astNode;
    }

    private AstNode statements() throws IOException {
        return statements(null);
    }

    private ConditionData condition() throws IOException {
        ConditionData conditionData = new ConditionData();
        if (mustMatchToken(87, "msg.no.paren.cond")) {
            conditionData.lp = this.ts.tokenBeg;
        }
        conditionData.condition = expr();
        if (mustMatchToken(88, "msg.no.paren.after.cond")) {
            conditionData.rp = this.ts.tokenBeg;
        }
        if (conditionData.condition instanceof Assignment) {
            addStrictWarning("msg.equal.as.assign", "", conditionData.condition.getPosition(), conditionData.condition.getLength());
        }
        return conditionData;
    }

    private AstNode statement() throws IOException {
        int i = this.ts.tokenBeg;
        try {
            AstNode statementHelper = statementHelper();
            if (statementHelper != null) {
                if (!this.compilerEnv.isStrictMode() || statementHelper.hasSideEffects()) {
                    return statementHelper;
                }
                String str;
                int position = statementHelper.getPosition();
                int max = Math.max(position, lineBeginningFor(position));
                if (statementHelper instanceof EmptyStatement) {
                    str = "msg.extra.trailing.semi";
                } else {
                    str = "msg.no.side.effects";
                }
                addStrictWarning(str, "", max, nodeEnd(statementHelper) - max);
                return statementHelper;
            }
        } catch (ParserException e) {
        }
        while (true) {
            int peekTokenOrEOL = peekTokenOrEOL();
            consumeToken();
            switch (peekTokenOrEOL) {
                case -1:
                case 0:
                case 1:
                case 82:
                    return new EmptyStatement(i, this.ts.tokenBeg - i);
                default:
            }
        }
    }

    private AstNode statementHelper() throws IOException {
        AstNode returnOrYield;
        boolean z = true;
        if (!(this.currentLabel == null || this.currentLabel.getStatement() == null)) {
            this.currentLabel = null;
        }
        int peekToken = peekToken();
        int i = this.ts.tokenBeg;
        int i2;
        switch (peekToken) {
            case -1:
                consumeToken();
                return makeErrorNode();
            case 4:
            case 72:
                returnOrYield = returnOrYield(peekToken, false);
                break;
            case 39:
                returnOrYield = nameOrLabel();
                if (returnOrYield instanceof ExpressionStatement) {
                    break;
                }
                return returnOrYield;
            case 50:
                returnOrYield = throwStatement();
                break;
            case 81:
                return tryStatement();
            case 82:
                consumeToken();
                i2 = this.ts.tokenBeg;
                returnOrYield = new EmptyStatement(i2, this.ts.tokenEnd - i2);
                returnOrYield.setLineno(this.ts.lineno);
                return returnOrYield;
            case 85:
                return block();
            case 109:
                consumeToken();
                return function(3);
            case 112:
                return ifStatement();
            case 114:
                return switchStatement();
            case 116:
                returnOrYield = defaultXmlNamespace();
                break;
            case 117:
                return whileLoop();
            case 118:
                return doLoop();
            case 119:
                return forLoop();
            case 120:
                returnOrYield = breakStatement();
                break;
            case 121:
                returnOrYield = continueStatement();
                break;
            case 122:
            case 154:
                consumeToken();
                i2 = this.ts.lineno;
                returnOrYield = variables(this.currentToken, this.ts.tokenBeg, true);
                returnOrYield.setLineno(i2);
                break;
            case 123:
                if (this.inUseStrictDirective) {
                    reportError("msg.no.with.strict");
                }
                return withStatement();
            case 153:
                returnOrYield = letStatement();
                if ((returnOrYield instanceof VariableDeclaration) && peekToken() == 82) {
                    break;
                }
                return returnOrYield;
            case 160:
                consumeToken();
                returnOrYield = new KeywordLiteral(this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg, peekToken);
                returnOrYield.setLineno(this.ts.lineno);
                break;
            default:
                i = this.ts.lineno;
                AstNode expr = expr();
                if (insideFunction()) {
                    z = false;
                }
                AstNode expressionStatement = new ExpressionStatement(expr, z);
                expressionStatement.setLineno(i);
                returnOrYield = expressionStatement;
                break;
        }
        autoInsertSemicolon(returnOrYield);
        return returnOrYield;
    }

    private void autoInsertSemicolon(AstNode astNode) throws IOException {
        int peekFlaggedToken = peekFlaggedToken();
        int position = astNode.getPosition();
        switch (65535 & peekFlaggedToken) {
            case -1:
            case 0:
            case 86:
                warnMissingSemi(position, nodeEnd(astNode));
                return;
            case 82:
                consumeToken();
                astNode.setLength(this.ts.tokenEnd - position);
                return;
            default:
                if ((peekFlaggedToken & 65536) == 0) {
                    reportError("msg.no.semi.stmt");
                    return;
                } else {
                    warnMissingSemi(position, nodeEnd(astNode));
                    return;
                }
        }
    }

    private IfStatement ifStatement() throws IOException {
        int i;
        if (this.currentToken != 112) {
            codeBug();
        }
        consumeToken();
        int i2 = this.ts.tokenBeg;
        int i3 = this.ts.lineno;
        ConditionData condition = condition();
        AstNode statement = statement();
        AstNode astNode = null;
        if (matchToken(113)) {
            int i4 = this.ts.tokenBeg - i2;
            astNode = statement();
            i = i4;
        } else {
            i = -1;
        }
        IfStatement ifStatement = new IfStatement(i2, getNodeEnd(astNode != null ? astNode : statement) - i2);
        ifStatement.setCondition(condition.condition);
        ifStatement.setParens(condition.lp - i2, condition.rp - i2);
        ifStatement.setThenPart(statement);
        ifStatement.setElsePart(astNode);
        ifStatement.setElsePosition(i);
        ifStatement.setLineno(i3);
        return ifStatement;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.ast.SwitchStatement switchStatement() throws java.io.IOException {
        /*
        r8 = this;
        r0 = r8.currentToken;
        r1 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        if (r0 == r1) goto L_0x0009;
    L_0x0006:
        r8.codeBug();
    L_0x0009:
        r8.consumeToken();
        r0 = r8.ts;
        r2 = r0.tokenBeg;
        r3 = new org.mozilla.javascript.ast.SwitchStatement;
        r3.<init>(r2);
        r0 = 87;
        r1 = "msg.no.paren.switch";
        r0 = r8.mustMatchToken(r0, r1);
        if (r0 == 0) goto L_0x0028;
    L_0x0020:
        r0 = r8.ts;
        r0 = r0.tokenBeg;
        r0 = r0 - r2;
        r3.setLp(r0);
    L_0x0028:
        r0 = r8.ts;
        r0 = r0.lineno;
        r3.setLineno(r0);
        r0 = r8.expr();
        r3.setExpression(r0);
        r8.enterSwitch(r3);
        r0 = 88;
        r1 = "msg.no.paren.after.switch";
        r0 = r8.mustMatchToken(r0, r1);	 Catch:{ all -> 0x0077 }
        if (r0 == 0) goto L_0x004c;
    L_0x0044:
        r0 = r8.ts;	 Catch:{ all -> 0x0077 }
        r0 = r0.tokenBeg;	 Catch:{ all -> 0x0077 }
        r0 = r0 - r2;
        r3.setRp(r0);	 Catch:{ all -> 0x0077 }
    L_0x004c:
        r0 = 85;
        r1 = "msg.no.brace.switch";
        r8.mustMatchToken(r0, r1);	 Catch:{ all -> 0x0077 }
        r1 = 0;
    L_0x0055:
        r0 = r8.nextToken();	 Catch:{ all -> 0x0077 }
        r4 = r8.ts;	 Catch:{ all -> 0x0077 }
        r4 = r4.tokenBeg;	 Catch:{ all -> 0x0077 }
        r5 = r8.ts;	 Catch:{ all -> 0x0077 }
        r5 = r5.lineno;	 Catch:{ all -> 0x0077 }
        switch(r0) {
            case 86: goto L_0x006e;
            case 115: goto L_0x007c;
            case 116: goto L_0x00b5;
            default: goto L_0x0064;
        };	 Catch:{ all -> 0x0077 }
    L_0x0064:
        r0 = "msg.bad.switch";
        r8.reportError(r0);	 Catch:{ all -> 0x0077 }
    L_0x006a:
        r8.exitSwitch();
        return r3;
    L_0x006e:
        r0 = r8.ts;	 Catch:{ all -> 0x0077 }
        r0 = r0.tokenEnd;	 Catch:{ all -> 0x0077 }
        r0 = r0 - r2;
        r3.setLength(r0);	 Catch:{ all -> 0x0077 }
        goto L_0x006a;
    L_0x0077:
        r0 = move-exception;
        r8.exitSwitch();
        throw r0;
    L_0x007c:
        r0 = r8.expr();	 Catch:{ all -> 0x0077 }
        r6 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        r7 = "msg.no.colon.case";
        r8.mustMatchToken(r6, r7);	 Catch:{ all -> 0x0077 }
    L_0x0088:
        r6 = new org.mozilla.javascript.ast.SwitchCase;	 Catch:{ all -> 0x0077 }
        r6.<init>(r4);	 Catch:{ all -> 0x0077 }
        r6.setExpression(r0);	 Catch:{ all -> 0x0077 }
        r0 = r8.ts;	 Catch:{ all -> 0x0077 }
        r0 = r0.tokenEnd;	 Catch:{ all -> 0x0077 }
        r0 = r0 - r2;
        r6.setLength(r0);	 Catch:{ all -> 0x0077 }
        r6.setLineno(r5);	 Catch:{ all -> 0x0077 }
    L_0x009b:
        r0 = r8.peekToken();	 Catch:{ all -> 0x0077 }
        r4 = 86;
        if (r0 == r4) goto L_0x00c8;
    L_0x00a3:
        r4 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r0 == r4) goto L_0x00c8;
    L_0x00a7:
        r4 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        if (r0 == r4) goto L_0x00c8;
    L_0x00ab:
        if (r0 == 0) goto L_0x00c8;
    L_0x00ad:
        r0 = r8.statement();	 Catch:{ all -> 0x0077 }
        r6.addStatement(r0);	 Catch:{ all -> 0x0077 }
        goto L_0x009b;
    L_0x00b5:
        if (r1 == 0) goto L_0x00bd;
    L_0x00b7:
        r0 = "msg.double.switch.default";
        r8.reportError(r0);	 Catch:{ all -> 0x0077 }
    L_0x00bd:
        r1 = 1;
        r0 = 0;
        r6 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        r7 = "msg.no.colon.case";
        r8.mustMatchToken(r6, r7);	 Catch:{ all -> 0x0077 }
        goto L_0x0088;
    L_0x00c8:
        r3.addCase(r6);	 Catch:{ all -> 0x0077 }
        goto L_0x0055;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.switchStatement():org.mozilla.javascript.ast.SwitchStatement");
    }

    private WhileLoop whileLoop() throws IOException {
        if (this.currentToken != 117) {
            codeBug();
        }
        consumeToken();
        int i = this.ts.tokenBeg;
        Loop whileLoop = new WhileLoop(i);
        whileLoop.setLineno(this.ts.lineno);
        enterLoop(whileLoop);
        try {
            ConditionData condition = condition();
            whileLoop.setCondition(condition.condition);
            whileLoop.setParens(condition.lp - i, condition.rp - i);
            AstNode statement = statement();
            whileLoop.setLength(getNodeEnd(statement) - i);
            whileLoop.setBody(statement);
            return whileLoop;
        } finally {
            exitLoop();
        }
    }

    private DoLoop doLoop() throws IOException {
        if (this.currentToken != 118) {
            codeBug();
        }
        consumeToken();
        int i = this.ts.tokenBeg;
        Loop doLoop = new DoLoop(i);
        doLoop.setLineno(this.ts.lineno);
        enterLoop(doLoop);
        try {
            AstNode statement = statement();
            mustMatchToken(117, "msg.no.while.do");
            doLoop.setWhilePosition(this.ts.tokenBeg - i);
            ConditionData condition = condition();
            doLoop.setCondition(condition.condition);
            doLoop.setParens(condition.lp - i, condition.rp - i);
            int nodeEnd = getNodeEnd(statement);
            doLoop.setBody(statement);
            if (matchToken(82)) {
                nodeEnd = this.ts.tokenEnd;
            }
            doLoop.setLength(nodeEnd - i);
            return doLoop;
        } finally {
            exitLoop();
        }
    }

    private Loop forLoop() throws IOException {
        if (this.currentToken != 119) {
            codeBug();
        }
        consumeToken();
        int i = this.ts.tokenBeg;
        int i2 = this.ts.lineno;
        AstNode astNode = null;
        Scope scope = new Scope();
        pushScope(scope);
        try {
            int i3;
            boolean z;
            int i4;
            AstNode forLoopInit;
            AstNode emptyExpression;
            int i5;
            Object obj;
            AstNode astNode2;
            int i6;
            Scope forInLoop;
            Scope scope2;
            if (matchToken(39)) {
                if ("each".equals(this.ts.getString())) {
                    i3 = this.ts.tokenBeg - i;
                    z = true;
                    if (mustMatchToken(87, "msg.no.paren.for")) {
                        i4 = -1;
                    } else {
                        i4 = this.ts.tokenBeg - i;
                    }
                    forLoopInit = forLoopInit(peekToken());
                    if (matchToken(52)) {
                        mustMatchToken(82, "msg.no.semi.for");
                        if (peekToken() != 82) {
                            emptyExpression = new EmptyExpression(this.ts.tokenBeg, 1);
                            emptyExpression.setLineno(this.ts.lineno);
                        } else {
                            emptyExpression = expr();
                        }
                        mustMatchToken(82, "msg.no.semi.for.cond");
                        i5 = this.ts.tokenEnd;
                        if (peekToken() != 88) {
                            astNode = new EmptyExpression(i5, 1);
                            astNode.setLineno(this.ts.lineno);
                            i5 = -1;
                            obj = null;
                            astNode2 = emptyExpression;
                        } else {
                            astNode = expr();
                            i5 = -1;
                            obj = null;
                            astNode2 = emptyExpression;
                        }
                    } else {
                        i5 = this.ts.tokenBeg - i;
                        obj = 1;
                        astNode2 = expr();
                    }
                    if (mustMatchToken(88, "msg.no.paren.for.ctrl")) {
                        i6 = -1;
                    } else {
                        i6 = this.ts.tokenBeg - i;
                    }
                    if (obj == null) {
                        forInLoop = new ForInLoop(i);
                        if ((forLoopInit instanceof VariableDeclaration) && ((VariableDeclaration) forLoopInit).getVariables().size() > 1) {
                            reportError("msg.mult.index");
                        }
                        forInLoop.setIterator(forLoopInit);
                        forInLoop.setIteratedObject(astNode2);
                        forInLoop.setInPosition(i5);
                        forInLoop.setIsForEach(z);
                        forInLoop.setEachPosition(i3);
                        scope2 = forInLoop;
                    } else {
                        scope2 = new ForLoop(i);
                        scope2.setInitializer(forLoopInit);
                        scope2.setCondition(astNode2);
                        scope2.setIncrement(astNode);
                    }
                    this.currentScope.replaceWith(scope2);
                    popScope();
                    enterLoop(scope2);
                    forLoopInit = statement();
                    scope2.setLength(getNodeEnd(forLoopInit) - i);
                    scope2.setBody(forLoopInit);
                    exitLoop();
                    if (this.currentScope == scope) {
                        popScope();
                    }
                    scope2.setParens(i4, i6);
                    scope2.setLineno(i2);
                    return scope2;
                }
                reportError("msg.no.paren.for");
            }
            i3 = -1;
            z = false;
            if (mustMatchToken(87, "msg.no.paren.for")) {
                i4 = -1;
            } else {
                i4 = this.ts.tokenBeg - i;
            }
            forLoopInit = forLoopInit(peekToken());
            if (matchToken(52)) {
                mustMatchToken(82, "msg.no.semi.for");
                if (peekToken() != 82) {
                    emptyExpression = expr();
                } else {
                    emptyExpression = new EmptyExpression(this.ts.tokenBeg, 1);
                    emptyExpression.setLineno(this.ts.lineno);
                }
                mustMatchToken(82, "msg.no.semi.for.cond");
                i5 = this.ts.tokenEnd;
                if (peekToken() != 88) {
                    astNode = expr();
                    i5 = -1;
                    obj = null;
                    astNode2 = emptyExpression;
                } else {
                    astNode = new EmptyExpression(i5, 1);
                    astNode.setLineno(this.ts.lineno);
                    i5 = -1;
                    obj = null;
                    astNode2 = emptyExpression;
                }
            } else {
                i5 = this.ts.tokenBeg - i;
                obj = 1;
                astNode2 = expr();
            }
            if (mustMatchToken(88, "msg.no.paren.for.ctrl")) {
                i6 = -1;
            } else {
                i6 = this.ts.tokenBeg - i;
            }
            if (obj == null) {
                scope2 = new ForLoop(i);
                scope2.setInitializer(forLoopInit);
                scope2.setCondition(astNode2);
                scope2.setIncrement(astNode);
            } else {
                forInLoop = new ForInLoop(i);
                reportError("msg.mult.index");
                forInLoop.setIterator(forLoopInit);
                forInLoop.setIteratedObject(astNode2);
                forInLoop.setInPosition(i5);
                forInLoop.setIsForEach(z);
                forInLoop.setEachPosition(i3);
                scope2 = forInLoop;
            }
            this.currentScope.replaceWith(scope2);
            popScope();
            enterLoop(scope2);
            forLoopInit = statement();
            scope2.setLength(getNodeEnd(forLoopInit) - i);
            scope2.setBody(forLoopInit);
            exitLoop();
            if (this.currentScope == scope) {
                popScope();
            }
            scope2.setParens(i4, i6);
            scope2.setLineno(i2);
            return scope2;
        } catch (Throwable th) {
            if (this.currentScope == scope) {
                popScope();
            }
        }
    }

    private AstNode forLoopInit(int i) throws IOException {
        try {
            AstNode emptyExpression;
            this.inForInit = true;
            if (i == 82) {
                emptyExpression = new EmptyExpression(this.ts.tokenBeg, 1);
                emptyExpression.setLineno(this.ts.lineno);
            } else if (i == 122 || i == 153) {
                consumeToken();
                emptyExpression = variables(i, this.ts.tokenBeg, false);
            } else {
                emptyExpression = expr();
                markDestructuring(emptyExpression);
            }
            this.inForInit = false;
            return emptyExpression;
        } catch (Throwable th) {
            this.inForInit = false;
        }
    }

    private TryStatement tryStatement() throws IOException {
        if (this.currentToken != 81) {
            codeBug();
        }
        consumeToken();
        Comment andResetJsDoc = getAndResetJsDoc();
        int i = this.ts.tokenBeg;
        int i2 = this.ts.lineno;
        if (peekToken() != 85) {
            reportError("msg.no.brace.try");
        }
        AstNode statement = statement();
        int nodeEnd = getNodeEnd(statement);
        List list = null;
        Object obj = null;
        int peekToken = peekToken();
        if (peekToken == 124) {
            while (matchToken(124)) {
                int i3;
                AstNode expr;
                Object obj2;
                List arrayList;
                int i4 = this.ts.lineno;
                if (obj != null) {
                    reportError("msg.catch.unreachable");
                }
                int i5 = this.ts.tokenBeg;
                int i6 = -1;
                if (mustMatchToken(87, "msg.no.paren.catch")) {
                    peekToken = this.ts.tokenBeg;
                } else {
                    peekToken = -1;
                }
                mustMatchToken(39, "msg.bad.catchcond");
                Name createNameNode = createNameNode();
                String identifier = createNameNode.getIdentifier();
                if (this.inUseStrictDirective && ("eval".equals(identifier) || "arguments".equals(identifier))) {
                    reportError("msg.bad.id.strict", identifier);
                }
                if (matchToken(112)) {
                    i3 = this.ts.tokenBeg;
                    expr = expr();
                    obj2 = obj;
                } else {
                    i3 = -1;
                    expr = null;
                    nodeEnd = 1;
                }
                if (mustMatchToken(88, "msg.bad.catchcond")) {
                    i6 = this.ts.tokenBeg;
                }
                mustMatchToken(85, "msg.no.brace.catchblock");
                Block block = (Block) statements();
                int nodeEnd2 = getNodeEnd(block);
                CatchClause catchClause = new CatchClause(i5);
                catchClause.setVarName(createNameNode);
                catchClause.setCatchCondition(expr);
                catchClause.setBody(block);
                if (i3 != -1) {
                    catchClause.setIfPosition(i3 - i5);
                }
                catchClause.setParens(peekToken, i6);
                catchClause.setLineno(i4);
                if (mustMatchToken(86, "msg.no.brace.after.body")) {
                    peekToken = this.ts.tokenEnd;
                } else {
                    peekToken = nodeEnd2;
                }
                catchClause.setLength(peekToken - i5);
                if (list == null) {
                    arrayList = new ArrayList();
                } else {
                    arrayList = list;
                }
                arrayList.add(catchClause);
                list = arrayList;
                obj = obj2;
                nodeEnd = peekToken;
            }
        } else if (peekToken != 125) {
            mustMatchToken(125, "msg.try.no.catchfinally");
        }
        AstNode astNode = null;
        if (matchToken(125)) {
            peekToken = this.ts.tokenBeg;
            astNode = statement();
            nodeEnd = getNodeEnd(astNode);
        } else {
            peekToken = -1;
        }
        TryStatement tryStatement = new TryStatement(i, nodeEnd - i);
        tryStatement.setTryBlock(statement);
        tryStatement.setCatchClauses(list);
        tryStatement.setFinallyBlock(astNode);
        if (peekToken != -1) {
            tryStatement.setFinallyPosition(peekToken - i);
        }
        tryStatement.setLineno(i2);
        if (andResetJsDoc != null) {
            tryStatement.setJsDocNode(andResetJsDoc);
        }
        return tryStatement;
    }

    private ThrowStatement throwStatement() throws IOException {
        if (this.currentToken != 50) {
            codeBug();
        }
        consumeToken();
        int i = this.ts.tokenBeg;
        int i2 = this.ts.lineno;
        if (peekTokenOrEOL() == 1) {
            reportError("msg.bad.throw.eol");
        }
        AstNode expr = expr();
        ThrowStatement throwStatement = new ThrowStatement(i, getNodeEnd(expr), expr);
        throwStatement.setLineno(i2);
        return throwStatement;
    }

    private LabeledStatement matchJumpLabelName() throws IOException {
        LabeledStatement labeledStatement = null;
        if (peekTokenOrEOL() == 39) {
            consumeToken();
            if (this.labelSet != null) {
                labeledStatement = (LabeledStatement) this.labelSet.get(this.ts.getString());
            }
            if (labeledStatement == null) {
                reportError("msg.undef.label");
            }
        }
        return labeledStatement;
    }

    private BreakStatement breakStatement() throws IOException {
        int nodeEnd;
        Name name;
        if (this.currentToken != 120) {
            codeBug();
        }
        consumeToken();
        int i = this.ts.lineno;
        int i2 = this.ts.tokenBeg;
        int i3 = this.ts.tokenEnd;
        if (peekTokenOrEOL() == 39) {
            AstNode createNameNode = createNameNode();
            nodeEnd = getNodeEnd(createNameNode);
            name = createNameNode;
        } else {
            name = null;
            nodeEnd = i3;
        }
        LabeledStatement matchJumpLabelName = matchJumpLabelName();
        Jump firstLabel = matchJumpLabelName == null ? null : matchJumpLabelName.getFirstLabel();
        if (firstLabel == null && name == null) {
            if (this.loopAndSwitchSet != null && this.loopAndSwitchSet.size() != 0) {
                firstLabel = (Jump) this.loopAndSwitchSet.get(this.loopAndSwitchSet.size() - 1);
            } else if (name == null) {
                reportError("msg.bad.break", i2, nodeEnd - i2);
            }
        }
        BreakStatement breakStatement = new BreakStatement(i2, nodeEnd - i2);
        breakStatement.setBreakLabel(name);
        if (firstLabel != null) {
            breakStatement.setBreakTarget(firstLabel);
        }
        breakStatement.setLineno(i);
        return breakStatement;
    }

    private ContinueStatement continueStatement() throws IOException {
        int nodeEnd;
        Name name;
        Loop loop = null;
        if (this.currentToken != 121) {
            codeBug();
        }
        consumeToken();
        int i = this.ts.lineno;
        int i2 = this.ts.tokenBeg;
        int i3 = this.ts.tokenEnd;
        if (peekTokenOrEOL() == 39) {
            AstNode createNameNode = createNameNode();
            nodeEnd = getNodeEnd(createNameNode);
            name = createNameNode;
        } else {
            name = null;
            nodeEnd = i3;
        }
        LabeledStatement matchJumpLabelName = matchJumpLabelName();
        if (matchJumpLabelName != null || name != null) {
            if (matchJumpLabelName == null || !(matchJumpLabelName.getStatement() instanceof Loop)) {
                reportError("msg.continue.nonloop", i2, nodeEnd - i2);
            }
            if (matchJumpLabelName != null) {
                loop = (Loop) matchJumpLabelName.getStatement();
            }
        } else if (this.loopSet == null || this.loopSet.size() == 0) {
            reportError("msg.continue.outside");
        } else {
            loop = (Loop) this.loopSet.get(this.loopSet.size() - 1);
        }
        ContinueStatement continueStatement = new ContinueStatement(i2, nodeEnd - i2);
        if (loop != null) {
            continueStatement.setTarget(loop);
        }
        continueStatement.setLabel(name);
        continueStatement.setLineno(i);
        return continueStatement;
    }

    private WithStatement withStatement() throws IOException {
        int i;
        int i2 = -1;
        if (this.currentToken != 123) {
            codeBug();
        }
        consumeToken();
        Comment andResetJsDoc = getAndResetJsDoc();
        int i3 = this.ts.lineno;
        int i4 = this.ts.tokenBeg;
        if (mustMatchToken(87, "msg.no.paren.with")) {
            i = this.ts.tokenBeg;
        } else {
            i = -1;
        }
        AstNode expr = expr();
        if (mustMatchToken(88, "msg.no.paren.after.with")) {
            i2 = this.ts.tokenBeg;
        }
        AstNode statement = statement();
        WithStatement withStatement = new WithStatement(i4, getNodeEnd(statement) - i4);
        withStatement.setJsDocNode(andResetJsDoc);
        withStatement.setExpression(expr);
        withStatement.setStatement(statement);
        withStatement.setParens(i, i2);
        withStatement.setLineno(i3);
        return withStatement;
    }

    private AstNode letStatement() throws IOException {
        AstNode let;
        if (this.currentToken != 153) {
            codeBug();
        }
        consumeToken();
        int i = this.ts.lineno;
        int i2 = this.ts.tokenBeg;
        if (peekToken() == 87) {
            let = let(true, i2);
        } else {
            let = variables(153, i2, true);
        }
        let.setLineno(i);
        return let;
    }

    private static final boolean nowAllSet(int i, int i2, int i3) {
        return (i & i3) != i3 && (i2 & i3) == i3;
    }

    private AstNode returnOrYield(int i, boolean z) throws IOException {
        AstNode astNode;
        int i2 = 4;
        if (!insideFunction()) {
            reportError(i == 4 ? "msg.bad.return" : "msg.bad.yield");
        }
        consumeToken();
        int i3 = this.ts.lineno;
        int i4 = this.ts.tokenBeg;
        int i5 = this.ts.tokenEnd;
        AstNode astNode2 = null;
        switch (peekTokenOrEOL()) {
            case -1:
            case 0:
            case 1:
            case 72:
            case 82:
            case 84:
            case 86:
            case 88:
                break;
            default:
                astNode2 = expr();
                i5 = getNodeEnd(astNode2);
                break;
        }
        int i6 = this.endFlags;
        AstNode returnStatement;
        if (i == 4) {
            int i7 = this.endFlags;
            if (astNode2 == null) {
                i2 = 2;
            }
            this.endFlags = i2 | i7;
            returnStatement = new ReturnStatement(i4, i5 - i4, astNode2);
            if (nowAllSet(i6, this.endFlags, 6)) {
                addStrictWarning("msg.return.inconsistent", "", i4, i5 - i4);
                astNode = returnStatement;
            } else {
                astNode = returnStatement;
            }
        } else {
            if (!insideFunction()) {
                reportError("msg.bad.yield");
            }
            this.endFlags |= 8;
            returnStatement = new Yield(i4, i5 - i4, astNode2);
            setRequiresActivation();
            setIsGenerator();
            astNode = !z ? new ExpressionStatement(returnStatement) : returnStatement;
        }
        if (insideFunction() && nowAllSet(i6, this.endFlags, 12)) {
            Name functionName = ((FunctionNode) this.currentScriptOrFn).getFunctionName();
            if (functionName == null || functionName.length() == 0) {
                addError("msg.anon.generator.returns", "");
            } else {
                addError("msg.generator.returns", functionName.getIdentifier());
            }
        }
        astNode.setLineno(i3);
        return astNode;
    }

    private AstNode block() throws IOException {
        if (this.currentToken != 85) {
            codeBug();
        }
        consumeToken();
        int i = this.ts.tokenBeg;
        AstNode scope = new Scope(i);
        scope.setLineno(this.ts.lineno);
        pushScope(scope);
        try {
            statements(scope);
            mustMatchToken(86, "msg.no.brace.block");
            scope.setLength(this.ts.tokenEnd - i);
            return scope;
        } finally {
            popScope();
        }
    }

    private AstNode defaultXmlNamespace() throws IOException {
        if (this.currentToken != 116) {
            codeBug();
        }
        consumeToken();
        mustHaveXML();
        setRequiresActivation();
        int i = this.ts.lineno;
        int i2 = this.ts.tokenBeg;
        if (!(matchToken(39) && "xml".equals(this.ts.getString()))) {
            reportError("msg.bad.namespace");
        }
        if (!(matchToken(39) && "namespace".equals(this.ts.getString()))) {
            reportError("msg.bad.namespace");
        }
        if (!matchToken(90)) {
            reportError("msg.bad.namespace");
        }
        AstNode expr = expr();
        AstNode unaryExpression = new UnaryExpression(i2, getNodeEnd(expr) - i2);
        unaryExpression.setOperator(74);
        unaryExpression.setOperand(expr);
        unaryExpression.setLineno(i);
        return new ExpressionStatement(unaryExpression, true);
    }

    private void recordLabel(Label label, LabeledStatement labeledStatement) throws IOException {
        if (peekToken() != 103) {
            codeBug();
        }
        consumeToken();
        String name = label.getName();
        if (this.labelSet == null) {
            this.labelSet = new HashMap();
        } else {
            LabeledStatement labeledStatement2 = (LabeledStatement) this.labelSet.get(name);
            if (labeledStatement2 != null) {
                if (this.compilerEnv.isIdeMode()) {
                    Label labelByName = labeledStatement2.getLabelByName(name);
                    reportError("msg.dup.label", labelByName.getAbsolutePosition(), labelByName.getLength());
                }
                reportError("msg.dup.label", label.getPosition(), label.getLength());
            }
        }
        labeledStatement.addLabel(label);
        this.labelSet.put(name, labeledStatement);
    }

    private AstNode nameOrLabel() throws IOException {
        boolean z = true;
        if (this.currentToken != 39) {
            throw codeBug();
        }
        int i = this.ts.tokenBeg;
        this.currentFlaggedToken |= 131072;
        AstNode expr = expr();
        if (expr.getType() != 130) {
            if (insideFunction()) {
                z = false;
            }
            AstNode expressionStatement = new ExpressionStatement(expr, z);
            expressionStatement.lineno = expr.lineno;
            return expressionStatement;
        }
        AstNode statementHelper;
        expressionStatement = new LabeledStatement(i);
        recordLabel((Label) expr, expressionStatement);
        expressionStatement.setLineno(this.ts.lineno);
        while (peekToken() == 39) {
            this.currentFlaggedToken |= 131072;
            expr = expr();
            if (expr.getType() != 130) {
                if (insideFunction()) {
                    z = false;
                }
                AstNode expressionStatement2 = new ExpressionStatement(expr, z);
                autoInsertSemicolon(expressionStatement2);
                expr = expressionStatement2;
                this.currentLabel = expressionStatement;
                if (expr != null) {
                    statementHelper = statementHelper();
                } else {
                    statementHelper = expr;
                }
                this.currentLabel = null;
                for (Label name : expressionStatement.getLabels()) {
                    this.labelSet.remove(name.getName());
                }
                expressionStatement.setLength(statementHelper.getParent() != null ? getNodeEnd(statementHelper) - i : getNodeEnd(statementHelper));
                expressionStatement.setStatement(statementHelper);
                return expressionStatement;
            }
            recordLabel((Label) expr, expressionStatement);
        }
        expr = null;
        try {
            this.currentLabel = expressionStatement;
            if (expr != null) {
                statementHelper = expr;
            } else {
                statementHelper = statementHelper();
            }
            this.currentLabel = null;
            while (r2.hasNext()) {
                this.labelSet.remove(name.getName());
            }
            if (statementHelper.getParent() != null) {
            }
            expressionStatement.setLength(statementHelper.getParent() != null ? getNodeEnd(statementHelper) - i : getNodeEnd(statementHelper));
            expressionStatement.setStatement(statementHelper);
            return expressionStatement;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.currentLabel = null;
            for (Label name2 : expressionStatement.getLabels()) {
                this.labelSet.remove(name2.getName());
            }
        }
    }

    private VariableDeclaration variables(int i, int i2, boolean z) throws IOException {
        int nodeEnd;
        VariableDeclaration variableDeclaration = new VariableDeclaration(i2);
        variableDeclaration.setType(i);
        variableDeclaration.setLineno(this.ts.lineno);
        Comment andResetJsDoc = getAndResetJsDoc();
        if (andResetJsDoc != null) {
            variableDeclaration.setJsDocNode(andResetJsDoc);
        }
        do {
            AstNode destructuringPrimaryExpr;
            int i3;
            AstNode astNode;
            AstNode assignExpr;
            int peekToken = peekToken();
            int i4 = this.ts.tokenBeg;
            int i5 = this.ts.tokenEnd;
            if (peekToken == 83 || peekToken == 85) {
                destructuringPrimaryExpr = destructuringPrimaryExpr();
                i5 = getNodeEnd(destructuringPrimaryExpr);
                if (!(destructuringPrimaryExpr instanceof DestructuringForm)) {
                    reportError("msg.bad.assign.left", i4, i5 - i4);
                }
                markDestructuring(destructuringPrimaryExpr);
                i3 = i5;
                astNode = destructuringPrimaryExpr;
                destructuringPrimaryExpr = null;
            } else {
                mustMatchToken(39, "msg.bad.var");
                destructuringPrimaryExpr = createNameNode();
                destructuringPrimaryExpr.setLineno(this.ts.getLineno());
                if (this.inUseStrictDirective) {
                    String string = this.ts.getString();
                    if ("eval".equals(string) || "arguments".equals(this.ts.getString())) {
                        reportError("msg.bad.id.strict", string);
                    }
                }
                defineSymbol(i, this.ts.getString(), this.inForInit);
                i3 = i5;
                astNode = null;
            }
            int i6 = this.ts.lineno;
            Comment andResetJsDoc2 = getAndResetJsDoc();
            if (matchToken(90)) {
                assignExpr = assignExpr();
                nodeEnd = getNodeEnd(assignExpr);
            } else {
                nodeEnd = i3;
                assignExpr = null;
            }
            VariableInitializer variableInitializer = new VariableInitializer(i4, nodeEnd - i4);
            if (astNode != null) {
                if (assignExpr == null && !this.inForInit) {
                    reportError("msg.destruct.assign.no.init");
                }
                variableInitializer.setTarget(astNode);
            } else {
                variableInitializer.setTarget(destructuringPrimaryExpr);
            }
            variableInitializer.setInitializer(assignExpr);
            variableInitializer.setType(i);
            variableInitializer.setJsDocNode(andResetJsDoc2);
            variableInitializer.setLineno(i6);
            variableDeclaration.addVariable(variableInitializer);
        } while (matchToken(89));
        variableDeclaration.setLength(nodeEnd - i2);
        variableDeclaration.setIsStatement(z);
        return variableDeclaration;
    }

    private AstNode let(boolean z, int i) throws IOException {
        AstNode letNode = new LetNode(i);
        letNode.setLineno(this.ts.lineno);
        if (mustMatchToken(87, "msg.no.paren.after.let")) {
            letNode.setLp(this.ts.tokenBeg - i);
        }
        pushScope(letNode);
        try {
            letNode.setVariables(variables(153, this.ts.tokenBeg, z));
            if (mustMatchToken(88, "msg.no.paren.let")) {
                letNode.setRp(this.ts.tokenBeg - i);
            }
            if (z && peekToken() == 85) {
                consumeToken();
                int i2 = this.ts.tokenBeg;
                AstNode statements = statements();
                mustMatchToken(86, "msg.no.curly.let");
                statements.setLength(this.ts.tokenEnd - i2);
                letNode.setLength(this.ts.tokenEnd - i);
                letNode.setBody(statements);
                letNode.setType(153);
            } else {
                AstNode expr = expr();
                letNode.setLength(getNodeEnd(expr) - i);
                letNode.setBody(expr);
                if (z) {
                    expr = new ExpressionStatement(letNode, !insideFunction());
                    expr.setLineno(letNode.getLineno());
                    popScope();
                    return expr;
                }
            }
            popScope();
            return letNode;
        } catch (Throwable th) {
            popScope();
        }
    }

    void defineSymbol(int i, String str) {
        defineSymbol(i, str, false);
    }

    void defineSymbol(int i, String str, boolean z) {
        if (str == null) {
            if (!this.compilerEnv.isIdeMode()) {
                codeBug();
            } else {
                return;
            }
        }
        Scope definingScope = this.currentScope.getDefiningScope(str);
        Symbol symbol = definingScope != null ? definingScope.getSymbol(str) : null;
        int declType = symbol != null ? symbol.getDeclType() : -1;
        if (symbol == null || !(declType == 154 || i == 154 || (definingScope == this.currentScope && declType == 153))) {
            switch (i) {
                case 87:
                    if (symbol != null) {
                        addWarning("msg.dup.parms", str);
                    }
                    this.currentScriptOrFn.putSymbol(new Symbol(i, str));
                    return;
                case 109:
                case 122:
                case 154:
                    if (symbol == null) {
                        this.currentScriptOrFn.putSymbol(new Symbol(i, str));
                        return;
                    } else if (declType == 122) {
                        addStrictWarning("msg.var.redecl", str);
                        return;
                    } else if (declType == 87) {
                        addStrictWarning("msg.var.hides.arg", str);
                        return;
                    } else {
                        return;
                    }
                case 153:
                    if (z || !(this.currentScope.getType() == 112 || (this.currentScope instanceof Loop))) {
                        this.currentScope.putSymbol(new Symbol(i, str));
                        return;
                    } else {
                        addError("msg.let.decl.not.in.block");
                        return;
                    }
                default:
                    throw codeBug();
            }
        }
        String str2 = declType == 154 ? "msg.const.redecl" : declType == 153 ? "msg.let.redecl" : declType == 122 ? "msg.var.redecl" : declType == 109 ? "msg.fn.redecl" : "msg.parm.redecl";
        addError(str2, str);
    }

    private AstNode expr() throws IOException {
        AstNode assignExpr = assignExpr();
        int position = assignExpr.getPosition();
        while (matchToken(89)) {
            int i = this.ts.tokenBeg;
            if (this.compilerEnv.isStrictMode() && !assignExpr.hasSideEffects()) {
                addStrictWarning("msg.no.side.effects", "", position, nodeEnd(assignExpr) - position);
            }
            if (peekToken() == 72) {
                reportError("msg.yield.parenthesized");
            }
            assignExpr = new InfixExpression(89, assignExpr, assignExpr(), i);
        }
        return assignExpr;
    }

    private AstNode assignExpr() throws IOException {
        int peekToken = peekToken();
        if (peekToken == 72) {
            return returnOrYield(peekToken, true);
        }
        AstNode condExpr = condExpr();
        int peekToken2 = peekToken();
        if (90 > peekToken2 || peekToken2 > 101) {
            if (peekToken2 == 82 && this.currentJsDocComment != null) {
                condExpr.setJsDocNode(getAndResetJsDoc());
            }
            return condExpr;
        }
        consumeToken();
        Comment andResetJsDoc = getAndResetJsDoc();
        markDestructuring(condExpr);
        AstNode assignment = new Assignment(peekToken2, condExpr, assignExpr(), this.ts.tokenBeg);
        if (andResetJsDoc == null) {
            return assignment;
        }
        assignment.setJsDocNode(andResetJsDoc);
        return assignment;
    }

    private AstNode condExpr() throws IOException {
        AstNode orExpr = orExpr();
        if (!matchToken(102)) {
            return orExpr;
        }
        int i = this.ts.lineno;
        int i2 = this.ts.tokenBeg;
        int i3 = -1;
        boolean z = this.inForInit;
        AstNode astNode = null;
        this.inForInit = astNode;
        try {
            astNode = assignExpr();
            if (mustMatchToken(103, "msg.no.colon.cond")) {
                i3 = this.ts.tokenBeg;
            }
            AstNode assignExpr = assignExpr();
            int position = orExpr.getPosition();
            AstNode conditionalExpression = new ConditionalExpression(position, getNodeEnd(assignExpr) - position);
            conditionalExpression.setLineno(i);
            conditionalExpression.setTestExpression(orExpr);
            conditionalExpression.setTrueExpression(astNode);
            conditionalExpression.setFalseExpression(assignExpr);
            conditionalExpression.setQuestionMarkPosition(i2 - position);
            conditionalExpression.setColonPosition(i3 - position);
            return conditionalExpression;
        } finally {
            this.inForInit = z;
        }
    }

    private AstNode orExpr() throws IOException {
        AstNode andExpr = andExpr();
        if (!matchToken(104)) {
            return andExpr;
        }
        return new InfixExpression(104, andExpr, orExpr(), this.ts.tokenBeg);
    }

    private AstNode andExpr() throws IOException {
        AstNode bitOrExpr = bitOrExpr();
        if (!matchToken(105)) {
            return bitOrExpr;
        }
        return new InfixExpression(105, bitOrExpr, andExpr(), this.ts.tokenBeg);
    }

    private AstNode bitOrExpr() throws IOException {
        AstNode bitXorExpr = bitXorExpr();
        while (matchToken(9)) {
            bitXorExpr = new InfixExpression(9, bitXorExpr, bitXorExpr(), this.ts.tokenBeg);
        }
        return bitXorExpr;
    }

    private AstNode bitXorExpr() throws IOException {
        AstNode bitAndExpr = bitAndExpr();
        while (matchToken(10)) {
            bitAndExpr = new InfixExpression(10, bitAndExpr, bitAndExpr(), this.ts.tokenBeg);
        }
        return bitAndExpr;
    }

    private AstNode bitAndExpr() throws IOException {
        AstNode eqExpr = eqExpr();
        while (matchToken(11)) {
            eqExpr = new InfixExpression(11, eqExpr, eqExpr(), this.ts.tokenBeg);
        }
        return eqExpr;
    }

    private AstNode eqExpr() throws IOException {
        AstNode relExpr = relExpr();
        while (true) {
            int peekToken = peekToken();
            int i = this.ts.tokenBeg;
            switch (peekToken) {
                case 12:
                case 13:
                case 46:
                case 47:
                    int i2;
                    consumeToken();
                    if (this.compilerEnv.getLanguageVersion() == 120) {
                        if (peekToken == 12) {
                            i2 = 46;
                        } else if (peekToken == 13) {
                            i2 = 47;
                        }
                        relExpr = new InfixExpression(i2, relExpr, relExpr(), i);
                    }
                    i2 = peekToken;
                    relExpr = new InfixExpression(i2, relExpr, relExpr(), i);
                default:
                    return relExpr;
            }
        }
    }

    private AstNode relExpr() throws IOException {
        AstNode shiftExpr = shiftExpr();
        while (true) {
            int peekToken = peekToken();
            int i = this.ts.tokenBeg;
            switch (peekToken) {
                case 14:
                case 15:
                case 16:
                case 17:
                case 53:
                    break;
                case 52:
                    if (this.inForInit) {
                        break;
                    }
                    continue;
                default:
                    break;
            }
            return shiftExpr;
            consumeToken();
            shiftExpr = new InfixExpression(peekToken, shiftExpr, shiftExpr(), i);
        }
    }

    private AstNode shiftExpr() throws IOException {
        AstNode addExpr = addExpr();
        while (true) {
            int peekToken = peekToken();
            int i = this.ts.tokenBeg;
            switch (peekToken) {
                case 18:
                case 19:
                case 20:
                    consumeToken();
                    addExpr = new InfixExpression(peekToken, addExpr, addExpr(), i);
                default:
                    return addExpr;
            }
        }
    }

    private AstNode addExpr() throws IOException {
        AstNode mulExpr = mulExpr();
        while (true) {
            int peekToken = peekToken();
            int i = this.ts.tokenBeg;
            if (peekToken != 21 && peekToken != 22) {
                return mulExpr;
            }
            consumeToken();
            mulExpr = new InfixExpression(peekToken, mulExpr, mulExpr(), i);
        }
    }

    private AstNode mulExpr() throws IOException {
        AstNode unaryExpr = unaryExpr();
        while (true) {
            int peekToken = peekToken();
            int i = this.ts.tokenBeg;
            switch (peekToken) {
                case 23:
                case 24:
                case 25:
                    consumeToken();
                    unaryExpr = new InfixExpression(peekToken, unaryExpr, unaryExpr(), i);
                default:
                    return unaryExpr;
            }
        }
    }

    private AstNode unaryExpr() throws IOException {
        AstNode unaryExpression;
        int peekToken = peekToken();
        int i = this.ts.lineno;
        switch (peekToken) {
            case -1:
                consumeToken();
                return makeErrorNode();
            case 14:
                if (this.compilerEnv.isXmlAvailable()) {
                    consumeToken();
                    return memberExprTail(true, xmlInitializer());
                }
                break;
            case 21:
                consumeToken();
                unaryExpression = new UnaryExpression(28, this.ts.tokenBeg, unaryExpr());
                unaryExpression.setLineno(i);
                return unaryExpression;
            case 22:
                consumeToken();
                unaryExpression = new UnaryExpression(29, this.ts.tokenBeg, unaryExpr());
                unaryExpression.setLineno(i);
                return unaryExpression;
            case 26:
            case 27:
            case 32:
            case 126:
                consumeToken();
                unaryExpression = new UnaryExpression(peekToken, this.ts.tokenBeg, unaryExpr());
                unaryExpression.setLineno(i);
                return unaryExpression;
            case 31:
                consumeToken();
                unaryExpression = new UnaryExpression(peekToken, this.ts.tokenBeg, unaryExpr());
                unaryExpression.setLineno(i);
                return unaryExpression;
            case 106:
            case 107:
                consumeToken();
                unaryExpression = new UnaryExpression(peekToken, this.ts.tokenBeg, memberExpr(true));
                unaryExpression.setLineno(i);
                checkBadIncDec(unaryExpression);
                return unaryExpression;
        }
        unaryExpression = memberExpr(true);
        int peekTokenOrEOL = peekTokenOrEOL();
        if (peekTokenOrEOL != 106 && peekTokenOrEOL != 107) {
            return unaryExpression;
        }
        consumeToken();
        AstNode unaryExpression2 = new UnaryExpression(peekTokenOrEOL, this.ts.tokenBeg, unaryExpression, true);
        unaryExpression2.setLineno(i);
        checkBadIncDec(unaryExpression2);
        return unaryExpression2;
    }

    private AstNode xmlInitializer() throws IOException {
        if (this.currentToken != 14) {
            codeBug();
        }
        int i = this.ts.tokenBeg;
        int firstXMLToken = this.ts.getFirstXMLToken();
        if (firstXMLToken == 145 || firstXMLToken == 148) {
            AstNode xmlLiteral = new XmlLiteral(i);
            xmlLiteral.setLineno(this.ts.lineno);
            while (true) {
                switch (firstXMLToken) {
                    case 145:
                        xmlLiteral.addFragment(new XmlString(this.ts.tokenBeg, this.ts.getString()));
                        mustMatchToken(85, "msg.syntax");
                        i = this.ts.tokenBeg;
                        AstNode emptyExpression = peekToken() == 86 ? new EmptyExpression(i, this.ts.tokenEnd - i) : expr();
                        mustMatchToken(86, "msg.syntax");
                        XmlFragment xmlExpression = new XmlExpression(i, emptyExpression);
                        xmlExpression.setIsXmlAttribute(this.ts.isXMLAttribute());
                        xmlExpression.setLength(this.ts.tokenEnd - i);
                        xmlLiteral.addFragment(xmlExpression);
                        firstXMLToken = this.ts.getNextXMLToken();
                    case 148:
                        xmlLiteral.addFragment(new XmlString(this.ts.tokenBeg, this.ts.getString()));
                        return xmlLiteral;
                    default:
                        reportError("msg.syntax");
                        return makeErrorNode();
                }
            }
        }
        reportError("msg.syntax");
        return makeErrorNode();
    }

    private List<AstNode> argumentList() throws IOException {
        if (matchToken(88)) {
            return null;
        }
        List<AstNode> arrayList = new ArrayList();
        boolean z = this.inForInit;
        this.inForInit = false;
        while (true) {
            try {
                if (peekToken() == 72) {
                    reportError("msg.yield.parenthesized");
                }
                AstNode assignExpr = assignExpr();
                if (peekToken() == 119) {
                    try {
                        arrayList.add(generatorExpression(assignExpr, 0, true));
                    } catch (IOException e) {
                    }
                } else {
                    arrayList.add(assignExpr);
                }
                if (!matchToken(89)) {
                    break;
                }
            } finally {
                this.inForInit = z;
            }
        }
        mustMatchToken(88, "msg.no.paren.arg");
        return arrayList;
    }

    private AstNode memberExpr(boolean z) throws IOException {
        AstNode primaryExpr;
        int peekToken = peekToken();
        int i = this.ts.lineno;
        if (peekToken != 30) {
            primaryExpr = primaryExpr();
        } else {
            consumeToken();
            int i2 = this.ts.tokenBeg;
            AstNode newExpression = new NewExpression(i2);
            AstNode memberExpr = memberExpr(false);
            peekToken = getNodeEnd(memberExpr);
            newExpression.setTarget(memberExpr);
            if (matchToken(87)) {
                int i3 = this.ts.tokenBeg;
                List argumentList = argumentList();
                if (argumentList != null && argumentList.size() > 65536) {
                    reportError("msg.too.many.constructor.args");
                }
                int i4 = this.ts.tokenBeg;
                peekToken = this.ts.tokenEnd;
                if (argumentList != null) {
                    newExpression.setArguments(argumentList);
                }
                newExpression.setParens(i3 - i2, i4 - i2);
            }
            if (matchToken(85)) {
                memberExpr = objectLiteral();
                peekToken = getNodeEnd(memberExpr);
                newExpression.setInitializer(memberExpr);
            }
            newExpression.setLength(peekToken - i2);
            primaryExpr = newExpression;
        }
        primaryExpr.setLineno(i);
        return memberExprTail(z, primaryExpr);
    }

    private AstNode memberExprTail(boolean z, AstNode astNode) throws IOException {
        if (astNode == null) {
            codeBug();
        }
        int position = astNode.getPosition();
        while (true) {
            AstNode elementGet;
            int peekToken = peekToken();
            int i;
            int i2;
            AstNode expr;
            int i3;
            int i4;
            switch (peekToken) {
                case 83:
                    consumeToken();
                    i = this.ts.tokenBeg;
                    i2 = this.ts.lineno;
                    expr = expr();
                    peekToken = getNodeEnd(expr);
                    if (mustMatchToken(84, "msg.no.bracket.index")) {
                        peekToken = this.ts.tokenBeg;
                        i3 = this.ts.tokenEnd;
                        i4 = peekToken;
                    } else {
                        i4 = -1;
                        i3 = peekToken;
                    }
                    elementGet = new ElementGet(position, i3 - position);
                    elementGet.setTarget(astNode);
                    elementGet.setElement(expr);
                    elementGet.setParens(i, i4);
                    elementGet.setLineno(i2);
                    continue;
                case 87:
                    if (!z) {
                        break;
                    }
                    i4 = this.ts.lineno;
                    consumeToken();
                    checkCallRequiresActivation(astNode);
                    elementGet = new FunctionCall(position);
                    elementGet.setTarget(astNode);
                    elementGet.setLineno(i4);
                    elementGet.setLp(this.ts.tokenBeg - position);
                    List argumentList = argumentList();
                    if (argumentList != null && argumentList.size() > 65536) {
                        reportError("msg.too.many.function.args");
                    }
                    elementGet.setArguments(argumentList);
                    elementGet.setRp(this.ts.tokenBeg - position);
                    elementGet.setLength(this.ts.tokenEnd - position);
                    continue;
                case 108:
                case 143:
                    i4 = this.ts.lineno;
                    elementGet = propertyAccess(peekToken, astNode);
                    elementGet.setLineno(i4);
                    continue;
                case 146:
                    consumeToken();
                    i = this.ts.tokenBeg;
                    i2 = this.ts.lineno;
                    mustHaveXML();
                    setRequiresActivation();
                    expr = expr();
                    peekToken = getNodeEnd(expr);
                    if (mustMatchToken(88, "msg.no.paren")) {
                        i3 = this.ts.tokenBeg;
                        i4 = this.ts.tokenEnd;
                    } else {
                        i4 = peekToken;
                        i3 = -1;
                    }
                    elementGet = new XmlDotQuery(position, i4 - position);
                    elementGet.setLeft(astNode);
                    elementGet.setRight(expr);
                    elementGet.setOperatorPosition(i);
                    elementGet.setRp(i3 - position);
                    elementGet.setLineno(i2);
                    continue;
                default:
                    break;
            }
            return astNode;
            astNode = elementGet;
        }
    }

    private AstNode propertyAccess(int i, AstNode astNode) throws IOException {
        if (astNode == null) {
            codeBug();
        }
        int i2 = 0;
        int i3 = this.ts.lineno;
        int i4 = this.ts.tokenBeg;
        consumeToken();
        if (i == 143) {
            mustHaveXML();
            i2 = 4;
        }
        AstNode propertyName;
        if (this.compilerEnv.isXmlAvailable()) {
            AstNode xmlMemberGet;
            i3 = nextToken();
            switch (i3) {
                case 23:
                    saveNameTokenData(this.ts.tokenBeg, "*", this.ts.lineno);
                    propertyName = propertyName(-1, "*", i2);
                    break;
                case 39:
                    propertyName = propertyName(-1, this.ts.getString(), i2);
                    break;
                case 50:
                    saveNameTokenData(this.ts.tokenBeg, "throw", this.ts.lineno);
                    propertyName = propertyName(-1, "throw", i2);
                    break;
                case 147:
                    propertyName = attributeAccess();
                    break;
                default:
                    if (this.compilerEnv.isReservedKeywordAsIdentifier()) {
                        String keywordToName = Token.keywordToName(i3);
                        if (keywordToName != null) {
                            saveNameTokenData(this.ts.tokenBeg, keywordToName, this.ts.lineno);
                            propertyName = propertyName(-1, keywordToName, i2);
                            break;
                        }
                    }
                    reportError("msg.no.name.after.dot");
                    return makeErrorNode();
            }
            boolean z = propertyName instanceof XmlRef;
            if (z) {
                xmlMemberGet = new XmlMemberGet();
            } else {
                xmlMemberGet = new PropertyGet();
            }
            if (z && i == 108) {
                xmlMemberGet.setType(108);
            }
            int position = astNode.getPosition();
            xmlMemberGet.setPosition(position);
            xmlMemberGet.setLength(getNodeEnd(propertyName) - position);
            xmlMemberGet.setOperatorPosition(i4 - position);
            xmlMemberGet.setLineno(astNode.getLineno());
            xmlMemberGet.setLeft(astNode);
            xmlMemberGet.setRight(propertyName);
            return xmlMemberGet;
        }
        if (!(nextToken() == 39 || (this.compilerEnv.isReservedKeywordAsIdentifier() && TokenStream.isKeyword(this.ts.getString())))) {
            reportError("msg.no.name.after.dot");
        }
        propertyName = new PropertyGet(astNode, createNameNode(true, 33), i4);
        propertyName.setLineno(i3);
        return propertyName;
    }

    private AstNode attributeAccess() throws IOException {
        int nextToken = nextToken();
        int i = this.ts.tokenBeg;
        switch (nextToken) {
            case 23:
                saveNameTokenData(this.ts.tokenBeg, "*", this.ts.lineno);
                return propertyName(i, "*", 0);
            case 39:
                return propertyName(i, this.ts.getString(), 0);
            case 83:
                return xmlElemRef(i, null, -1);
            default:
                reportError("msg.no.name.after.xmlAttr");
                return makeErrorNode();
        }
    }

    private AstNode propertyName(int i, String str, int i2) throws IOException {
        int i3;
        int i4;
        if (i != -1) {
            i3 = i;
        } else {
            i3 = this.ts.tokenBeg;
        }
        int i5 = this.ts.lineno;
        AstNode createNameNode = createNameNode(true, this.currentToken);
        if (matchToken(144)) {
            int i6 = this.ts.tokenBeg;
            switch (nextToken()) {
                case 23:
                    saveNameTokenData(this.ts.tokenBeg, "*", this.ts.lineno);
                    i4 = i6;
                    AstNode astNode = createNameNode;
                    createNameNode = createNameNode(false, -1);
                    break;
                case 39:
                    i4 = i6;
                    Name name = createNameNode;
                    createNameNode = createNameNode();
                    break;
                case 83:
                    return xmlElemRef(i, createNameNode, i6);
                default:
                    reportError("msg.no.name.after.coloncolon");
                    return makeErrorNode();
            }
        }
        name = null;
        i4 = -1;
        if (name == null && i2 == 0 && i == -1) {
            return createNameNode;
        }
        AstNode xmlPropRef = new XmlPropRef(i3, getNodeEnd(createNameNode) - i3);
        xmlPropRef.setAtPos(i);
        xmlPropRef.setNamespace(name);
        xmlPropRef.setColonPos(i4);
        xmlPropRef.setPropName(createNameNode);
        xmlPropRef.setLineno(i5);
        return xmlPropRef;
    }

    private XmlElemRef xmlElemRef(int i, Name name, int i2) throws IOException {
        int i3 = -1;
        int i4 = this.ts.tokenBeg;
        int i5 = i != -1 ? i : i4;
        AstNode expr = expr();
        int nodeEnd = getNodeEnd(expr);
        if (mustMatchToken(84, "msg.no.bracket.index")) {
            i3 = this.ts.tokenBeg;
            nodeEnd = this.ts.tokenEnd;
        }
        XmlElemRef xmlElemRef = new XmlElemRef(i5, nodeEnd - i5);
        xmlElemRef.setNamespace(name);
        xmlElemRef.setColonPos(i2);
        xmlElemRef.setAtPos(i);
        xmlElemRef.setExpression(expr);
        xmlElemRef.setBrackets(i4, i3);
        return xmlElemRef;
    }

    private AstNode destructuringPrimaryExpr() throws IOException, ParserException {
        try {
            this.inDestructuringAssignment = true;
            AstNode primaryExpr = primaryExpr();
            return primaryExpr;
        } finally {
            this.inDestructuringAssignment = false;
        }
    }

    private AstNode primaryExpr() throws IOException {
        int nextFlaggedToken = nextFlaggedToken();
        int i = 65535 & nextFlaggedToken;
        switch (i) {
            case -1:
                break;
            case 0:
                reportError("msg.unexpected.eof");
                break;
            case 24:
            case 100:
                this.ts.readRegExp(i);
                i = this.ts.tokenBeg;
                AstNode regExpLiteral = new RegExpLiteral(i, this.ts.tokenEnd - i);
                regExpLiteral.setValue(this.ts.getString());
                regExpLiteral.setFlags(this.ts.readAndClearRegExpFlags());
                return regExpLiteral;
            case 39:
                return name(nextFlaggedToken, i);
            case 40:
                String string = this.ts.getString();
                if (this.inUseStrictDirective && this.ts.isNumberOctal()) {
                    reportError("msg.no.octal.strict");
                }
                if (this.ts.isNumberOctal()) {
                    string = "0" + string;
                }
                if (this.ts.isNumberHex()) {
                    string = "0x" + string;
                }
                return new NumberLiteral(this.ts.tokenBeg, string, this.ts.getNumber());
            case 41:
                return createStringLiteral();
            case 42:
            case 43:
            case 44:
            case 45:
                int i2 = this.ts.tokenBeg;
                return new KeywordLiteral(i2, this.ts.tokenEnd - i2, i);
            case 83:
                return arrayLiteral();
            case 85:
                return objectLiteral();
            case 87:
                return parenExpr();
            case 109:
                return function(2);
            case 127:
                reportError("msg.reserved.id");
                break;
            case 147:
                mustHaveXML();
                return attributeAccess();
            case 153:
                return let(false, this.ts.tokenBeg);
            default:
                reportError("msg.syntax");
                break;
        }
        return makeErrorNode();
    }

    private AstNode parenExpr() throws IOException {
        boolean z = this.inForInit;
        this.inForInit = false;
        try {
            AstNode generatorExpression;
            Comment andResetJsDoc = getAndResetJsDoc();
            int i = this.ts.lineno;
            int i2 = this.ts.tokenBeg;
            AstNode expr = expr();
            if (peekToken() == 119) {
                generatorExpression = generatorExpression(expr, i2);
            } else {
                generatorExpression = new ParenthesizedExpression(expr);
                if (andResetJsDoc == null) {
                    andResetJsDoc = getAndResetJsDoc();
                }
                if (andResetJsDoc != null) {
                    generatorExpression.setJsDocNode(andResetJsDoc);
                }
                mustMatchToken(88, "msg.no.paren");
                generatorExpression.setLength(this.ts.tokenEnd - generatorExpression.getPosition());
                generatorExpression.setLineno(i);
                this.inForInit = z;
            }
            return generatorExpression;
        } finally {
            this.inForInit = z;
        }
    }

    private AstNode name(int i, int i2) throws IOException {
        String string = this.ts.getString();
        int i3 = this.ts.tokenBeg;
        int i4 = this.ts.lineno;
        if ((131072 & i) == 0 || peekToken() != 103) {
            saveNameTokenData(i3, string, i4);
            if (this.compilerEnv.isXmlAvailable()) {
                return propertyName(-1, string, 0);
            }
            return createNameNode(true, 39);
        }
        AstNode label = new Label(i3, this.ts.tokenEnd - i3);
        label.setName(string);
        label.setLineno(this.ts.lineno);
        return label;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.ast.AstNode arrayLiteral() throws java.io.IOException {
        /*
        r12 = this;
        r3 = -1;
        r1 = 0;
        r5 = 1;
        r0 = r12.currentToken;
        r2 = 83;
        if (r0 == r2) goto L_0x000c;
    L_0x0009:
        r12.codeBug();
    L_0x000c:
        r0 = r12.ts;
        r8 = r0.tokenBeg;
        r0 = r12.ts;
        r6 = r0.tokenEnd;
        r9 = new java.util.ArrayList;
        r9.<init>();
        r7 = new org.mozilla.javascript.ast.ArrayLiteral;
        r7.<init>(r8);
        r0 = r1;
        r2 = r3;
        r4 = r5;
    L_0x0021:
        r10 = r12.peekToken();
        r11 = 89;
        if (r10 != r11) goto L_0x0043;
    L_0x0029:
        r12.consumeToken();
        r2 = r12.ts;
        r2 = r2.tokenEnd;
        if (r4 != 0) goto L_0x0034;
    L_0x0032:
        r4 = r5;
        goto L_0x0021;
    L_0x0034:
        r10 = new org.mozilla.javascript.ast.EmptyExpression;
        r11 = r12.ts;
        r11 = r11.tokenBeg;
        r10.<init>(r11, r5);
        r9.add(r10);
        r0 = r0 + 1;
        goto L_0x0021;
    L_0x0043:
        r11 = 84;
        if (r10 != r11) goto L_0x0078;
    L_0x0047:
        r12.consumeToken();
        r6 = r12.ts;
        r6 = r6.tokenEnd;
        r10 = r9.size();
        if (r4 == 0) goto L_0x0076;
    L_0x0054:
        r1 = r10 + r5;
        r7.setDestructuringLength(r1);
        r7.setSkipCount(r0);
        if (r2 == r3) goto L_0x00b3;
    L_0x005e:
        r12.warnTrailingComma(r8, r9, r2);
        r1 = r6;
    L_0x0062:
        r2 = r9.iterator();
    L_0x0066:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x00ac;
    L_0x006c:
        r0 = r2.next();
        r0 = (org.mozilla.javascript.ast.AstNode) r0;
        r7.addElement(r0);
        goto L_0x0066;
    L_0x0076:
        r5 = r1;
        goto L_0x0054;
    L_0x0078:
        r2 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        if (r10 != r2) goto L_0x008f;
    L_0x007c:
        if (r4 != 0) goto L_0x008f;
    L_0x007e:
        r2 = r9.size();
        if (r2 != r5) goto L_0x008f;
    L_0x0084:
        r0 = r9.get(r1);
        r0 = (org.mozilla.javascript.ast.AstNode) r0;
        r0 = r12.arrayComprehension(r0, r8);
    L_0x008e:
        return r0;
    L_0x008f:
        if (r10 != 0) goto L_0x0099;
    L_0x0091:
        r0 = "msg.no.bracket.arg";
        r12.reportError(r0);
        r1 = r6;
        goto L_0x0062;
    L_0x0099:
        if (r4 != 0) goto L_0x00a1;
    L_0x009b:
        r2 = "msg.no.bracket.arg";
        r12.reportError(r2);
    L_0x00a1:
        r2 = r12.assignExpr();
        r9.add(r2);
        r2 = r3;
        r4 = r1;
        goto L_0x0021;
    L_0x00ac:
        r0 = r1 - r8;
        r7.setLength(r0);
        r0 = r7;
        goto L_0x008e;
    L_0x00b3:
        r1 = r6;
        goto L_0x0062;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Parser.arrayLiteral():org.mozilla.javascript.ast.AstNode");
    }

    private AstNode arrayComprehension(AstNode astNode, int i) throws IOException {
        List arrayList = new ArrayList();
        while (peekToken() == 119) {
            arrayList.add(arrayComprehensionLoop());
        }
        int i2 = -1;
        ConditionData conditionData = null;
        if (peekToken() == 112) {
            consumeToken();
            i2 = this.ts.tokenBeg - i;
            conditionData = condition();
        }
        mustMatchToken(84, "msg.no.bracket.arg");
        AstNode arrayComprehension = new ArrayComprehension(i, this.ts.tokenEnd - i);
        arrayComprehension.setResult(astNode);
        arrayComprehension.setLoops(arrayList);
        if (conditionData != null) {
            arrayComprehension.setIfPosition(i2);
            arrayComprehension.setFilter(conditionData.condition);
            arrayComprehension.setFilterLp(conditionData.lp - i);
            arrayComprehension.setFilterRp(conditionData.rp - i);
        }
        return arrayComprehension;
    }

    private ArrayComprehensionLoop arrayComprehensionLoop() throws IOException {
        boolean z = true;
        if (nextToken() != 119) {
            codeBug();
        }
        int i = this.ts.tokenBeg;
        Scope arrayComprehensionLoop = new ArrayComprehensionLoop(i);
        pushScope(arrayComprehensionLoop);
        try {
            int i2;
            int i3;
            AstNode createNameNode;
            AstNode destructuringPrimaryExpr;
            int i4;
            AstNode expr;
            int i5;
            if (matchToken(39)) {
                if (this.ts.getString().equals("each")) {
                    i2 = this.ts.tokenBeg - i;
                    if (mustMatchToken(87, "msg.no.paren.for")) {
                        i3 = -1;
                    } else {
                        i3 = this.ts.tokenBeg - i;
                    }
                    switch (peekToken()) {
                        case 39:
                            consumeToken();
                            createNameNode = createNameNode();
                            break;
                        case 83:
                        case 85:
                            destructuringPrimaryExpr = destructuringPrimaryExpr();
                            markDestructuring(destructuringPrimaryExpr);
                            createNameNode = destructuringPrimaryExpr;
                            break;
                        default:
                            reportError("msg.bad.var");
                            createNameNode = null;
                            break;
                    }
                    if (createNameNode.getType() == 39) {
                        defineSymbol(153, this.ts.getString(), true);
                    }
                    if (mustMatchToken(52, "msg.in.after.for.name")) {
                        i4 = -1;
                    } else {
                        i4 = this.ts.tokenBeg - i;
                    }
                    expr = expr();
                    if (mustMatchToken(88, "msg.no.paren.for.ctrl")) {
                        i5 = -1;
                    } else {
                        i5 = this.ts.tokenBeg - i;
                    }
                    arrayComprehensionLoop.setLength(this.ts.tokenEnd - i);
                    arrayComprehensionLoop.setIterator(createNameNode);
                    arrayComprehensionLoop.setIteratedObject(expr);
                    arrayComprehensionLoop.setInPosition(i4);
                    arrayComprehensionLoop.setEachPosition(i2);
                    if (i2 == -1) {
                        z = false;
                    }
                    arrayComprehensionLoop.setIsForEach(z);
                    arrayComprehensionLoop.setParens(i3, i5);
                    return arrayComprehensionLoop;
                }
                reportError("msg.no.paren.for");
            }
            i2 = -1;
            if (mustMatchToken(87, "msg.no.paren.for")) {
                i3 = -1;
            } else {
                i3 = this.ts.tokenBeg - i;
            }
            switch (peekToken()) {
                case 39:
                    consumeToken();
                    createNameNode = createNameNode();
                    break;
                case 83:
                case 85:
                    destructuringPrimaryExpr = destructuringPrimaryExpr();
                    markDestructuring(destructuringPrimaryExpr);
                    createNameNode = destructuringPrimaryExpr;
                    break;
                default:
                    reportError("msg.bad.var");
                    createNameNode = null;
                    break;
            }
            if (createNameNode.getType() == 39) {
                defineSymbol(153, this.ts.getString(), true);
            }
            if (mustMatchToken(52, "msg.in.after.for.name")) {
                i4 = -1;
            } else {
                i4 = this.ts.tokenBeg - i;
            }
            expr = expr();
            if (mustMatchToken(88, "msg.no.paren.for.ctrl")) {
                i5 = -1;
            } else {
                i5 = this.ts.tokenBeg - i;
            }
            arrayComprehensionLoop.setLength(this.ts.tokenEnd - i);
            arrayComprehensionLoop.setIterator(createNameNode);
            arrayComprehensionLoop.setIteratedObject(expr);
            arrayComprehensionLoop.setInPosition(i4);
            arrayComprehensionLoop.setEachPosition(i2);
            if (i2 == -1) {
                z = false;
            }
            arrayComprehensionLoop.setIsForEach(z);
            arrayComprehensionLoop.setParens(i3, i5);
            return arrayComprehensionLoop;
        } finally {
            popScope();
        }
    }

    private AstNode generatorExpression(AstNode astNode, int i) throws IOException {
        return generatorExpression(astNode, i, false);
    }

    private AstNode generatorExpression(AstNode astNode, int i, boolean z) throws IOException {
        List arrayList = new ArrayList();
        while (peekToken() == 119) {
            arrayList.add(generatorExpressionLoop());
        }
        int i2 = -1;
        ConditionData conditionData = null;
        if (peekToken() == 112) {
            consumeToken();
            i2 = this.ts.tokenBeg - i;
            conditionData = condition();
        }
        if (!z) {
            mustMatchToken(88, "msg.no.paren.let");
        }
        AstNode generatorExpression = new GeneratorExpression(i, this.ts.tokenEnd - i);
        generatorExpression.setResult(astNode);
        generatorExpression.setLoops(arrayList);
        if (conditionData != null) {
            generatorExpression.setIfPosition(i2);
            generatorExpression.setFilter(conditionData.condition);
            generatorExpression.setFilterLp(conditionData.lp - i);
            generatorExpression.setFilterRp(conditionData.rp - i);
        }
        return generatorExpression;
    }

    private GeneratorExpressionLoop generatorExpressionLoop() throws IOException {
        int i = -1;
        if (nextToken() != 119) {
            codeBug();
        }
        int i2 = this.ts.tokenBeg;
        Scope generatorExpressionLoop = new GeneratorExpressionLoop(i2);
        pushScope(generatorExpressionLoop);
        try {
            int i3;
            AstNode createNameNode;
            int i4;
            if (mustMatchToken(87, "msg.no.paren.for")) {
                i3 = this.ts.tokenBeg - i2;
            } else {
                i3 = -1;
            }
            switch (peekToken()) {
                case 39:
                    consumeToken();
                    createNameNode = createNameNode();
                    break;
                case 83:
                case 85:
                    AstNode destructuringPrimaryExpr = destructuringPrimaryExpr();
                    markDestructuring(destructuringPrimaryExpr);
                    createNameNode = destructuringPrimaryExpr;
                    break;
                default:
                    reportError("msg.bad.var");
                    createNameNode = null;
                    break;
            }
            if (createNameNode.getType() == 39) {
                defineSymbol(153, this.ts.getString(), true);
            }
            if (mustMatchToken(52, "msg.in.after.for.name")) {
                i4 = this.ts.tokenBeg - i2;
            } else {
                i4 = -1;
            }
            AstNode expr = expr();
            if (mustMatchToken(88, "msg.no.paren.for.ctrl")) {
                i = this.ts.tokenBeg - i2;
            }
            generatorExpressionLoop.setLength(this.ts.tokenEnd - i2);
            generatorExpressionLoop.setIterator(createNameNode);
            generatorExpressionLoop.setIteratedObject(expr);
            generatorExpressionLoop.setInPosition(i4);
            generatorExpressionLoop.setParens(i3, i);
            return generatorExpressionLoop;
        } finally {
            popScope();
        }
    }

    private ObjectLiteral objectLiteral() throws IOException {
        int i = this.ts.tokenBeg;
        int i2 = this.ts.lineno;
        int i3 = -1;
        List arrayList = new ArrayList();
        Set set = null;
        Set set2 = null;
        if (this.inUseStrictDirective) {
            set = new HashSet();
            set2 = new HashSet();
        }
        Comment andResetJsDoc = getAndResetJsDoc();
        while (true) {
            Object obj;
            String string;
            int peekToken = peekToken();
            Comment andResetJsDoc2 = getAndResetJsDoc();
            String string2;
            switch (peekToken) {
                case 39:
                    AstNode createNameNode = createNameNode();
                    string2 = this.ts.getString();
                    int i4 = this.ts.tokenBeg;
                    consumeToken();
                    int peekToken2 = peekToken();
                    Object obj2 = ("get".equals(string2) || "set".equals(string2)) ? 1 : null;
                    if (obj2 != null && peekToken2 != 89 && peekToken2 != 103 && peekToken2 != 86) {
                        boolean equals = "get".equals(string2);
                        obj = equals ? 2 : 4;
                        AstNode objliteralProperty = objliteralProperty();
                        if (objliteralProperty != null) {
                            string = this.ts.getString();
                            ObjectProperty objectProperty = getterSetterProperty(i4, objliteralProperty, equals);
                            objliteralProperty.setJsDocNode(andResetJsDoc2);
                            arrayList.add(objectProperty);
                            break;
                        }
                        string = null;
                        break;
                    }
                    createNameNode.setJsDocNode(andResetJsDoc2);
                    arrayList.add(plainProperty(createNameNode, peekToken));
                    string = string2;
                    i3 = 1;
                    break;
                    break;
                case 86:
                    if (i3 != -1) {
                        warnTrailingComma(i, arrayList, i3);
                        break;
                    }
                    break;
                default:
                    AstNode objliteralProperty2 = objliteralProperty();
                    if (objliteralProperty2 != null) {
                        string2 = this.ts.getString();
                        objliteralProperty2.setJsDocNode(andResetJsDoc2);
                        arrayList.add(plainProperty(objliteralProperty2, peekToken));
                        string = string2;
                        i3 = 1;
                        break;
                    }
                    string = null;
                    obj = 1;
                    break;
            }
            if (this.inUseStrictDirective && string != null) {
                switch (obj) {
                    case 1:
                        if (set.contains(string) || set2.contains(string)) {
                            addError("msg.dup.obj.lit.prop.strict", string);
                        }
                        set.add(string);
                        set2.add(string);
                        break;
                    case 2:
                        if (set.contains(string)) {
                            addError("msg.dup.obj.lit.prop.strict", string);
                        }
                        set.add(string);
                        break;
                    case 4:
                        if (set2.contains(string)) {
                            addError("msg.dup.obj.lit.prop.strict", string);
                        }
                        set2.add(string);
                        break;
                }
            }
            getAndResetJsDoc();
            if (matchToken(89)) {
                i3 = this.ts.tokenEnd;
            } else {
                mustMatchToken(86, "msg.no.brace.prop");
                ObjectLiteral objectLiteral = new ObjectLiteral(i, this.ts.tokenEnd - i);
                if (andResetJsDoc != null) {
                    objectLiteral.setJsDocNode(andResetJsDoc);
                }
                objectLiteral.setElements(arrayList);
                objectLiteral.setLineno(i2);
                return objectLiteral;
            }
        }
    }

    private AstNode objliteralProperty() throws IOException {
        AstNode createNameNode;
        switch (peekToken()) {
            case 39:
                createNameNode = createNameNode();
                break;
            case 40:
                createNameNode = new NumberLiteral(this.ts.tokenBeg, this.ts.getString(), this.ts.getNumber());
                break;
            case 41:
                createNameNode = createStringLiteral();
                break;
            default:
                if (this.compilerEnv.isReservedKeywordAsIdentifier() && TokenStream.isKeyword(this.ts.getString())) {
                    createNameNode = createNameNode();
                    break;
                }
                reportError("msg.bad.prop");
                return null;
        }
        consumeToken();
        return createNameNode;
    }

    private ObjectProperty plainProperty(AstNode astNode, int i) throws IOException {
        int peekToken = peekToken();
        if ((peekToken == 89 || peekToken == 86) && i == 39 && this.compilerEnv.getLanguageVersion() >= 180) {
            if (!this.inDestructuringAssignment) {
                reportError("msg.bad.object.init");
            }
            AstNode name = new Name(astNode.getPosition(), astNode.getString());
            ObjectProperty objectProperty = new ObjectProperty();
            objectProperty.putProp(26, Boolean.TRUE);
            objectProperty.setLeftAndRight(astNode, name);
            return objectProperty;
        }
        mustMatchToken(103, "msg.no.colon.prop");
        objectProperty = new ObjectProperty();
        objectProperty.setOperatorPosition(this.ts.tokenBeg);
        objectProperty.setLeftAndRight(astNode, assignExpr());
        return objectProperty;
    }

    private ObjectProperty getterSetterProperty(int i, AstNode astNode, boolean z) throws IOException {
        AstNode function = function(2);
        Name functionName = function.getFunctionName();
        if (!(functionName == null || functionName.length() == 0)) {
            reportError("msg.bad.prop");
        }
        ObjectProperty objectProperty = new ObjectProperty(i);
        if (z) {
            objectProperty.setIsGetter();
            function.setFunctionIsGetter();
        } else {
            objectProperty.setIsSetter();
            function.setFunctionIsSetter();
        }
        int nodeEnd = getNodeEnd(function);
        objectProperty.setLeft(astNode);
        objectProperty.setRight(function);
        objectProperty.setLength(nodeEnd - i);
        return objectProperty;
    }

    private Name createNameNode() {
        return createNameNode(false, 39);
    }

    private Name createNameNode(boolean z, int i) {
        int i2 = this.ts.tokenBeg;
        String string = this.ts.getString();
        int i3 = this.ts.lineno;
        if (!"".equals(this.prevNameTokenString)) {
            i2 = this.prevNameTokenStart;
            string = this.prevNameTokenString;
            i3 = this.prevNameTokenLineno;
            this.prevNameTokenStart = 0;
            this.prevNameTokenString = "";
            this.prevNameTokenLineno = 0;
        }
        int i4 = i3;
        String str = string;
        int i5 = i4;
        if (str == null) {
            if (this.compilerEnv.isIdeMode()) {
                str = "";
            } else {
                codeBug();
            }
        }
        Name name = new Name(i2, str);
        name.setLineno(i5);
        if (z) {
            checkActivationName(str, i);
        }
        return name;
    }

    private StringLiteral createStringLiteral() {
        int i = this.ts.tokenBeg;
        StringLiteral stringLiteral = new StringLiteral(i, this.ts.tokenEnd - i);
        stringLiteral.setLineno(this.ts.lineno);
        stringLiteral.setValue(this.ts.getString());
        stringLiteral.setQuoteCharacter(this.ts.getQuoteChar());
        return stringLiteral;
    }

    protected void checkActivationName(String str, int i) {
        Object obj = 1;
        if (insideFunction()) {
            if (!"arguments".equals(str) && ((this.compilerEnv.getActivationNames() == null || !this.compilerEnv.getActivationNames().contains(str)) && !("length".equals(str) && i == 33 && this.compilerEnv.getLanguageVersion() == 120))) {
                obj = null;
            }
            if (obj != null) {
                setRequiresActivation();
            }
        }
    }

    protected void setRequiresActivation() {
        if (insideFunction()) {
            ((FunctionNode) this.currentScriptOrFn).setRequiresActivation();
        }
    }

    private void checkCallRequiresActivation(AstNode astNode) {
        if ((astNode.getType() == 39 && "eval".equals(((Name) astNode).getIdentifier())) || (astNode.getType() == 33 && "eval".equals(((PropertyGet) astNode).getProperty().getIdentifier()))) {
            setRequiresActivation();
        }
    }

    protected void setIsGenerator() {
        if (insideFunction()) {
            ((FunctionNode) this.currentScriptOrFn).setIsGenerator();
        }
    }

    private void checkBadIncDec(UnaryExpression unaryExpression) {
        int type = removeParens(unaryExpression.getOperand()).getType();
        if (type != 39 && type != 33 && type != 36 && type != 67 && type != 38) {
            reportError(unaryExpression.getType() == 106 ? "msg.bad.incr" : "msg.bad.decr");
        }
    }

    private ErrorNode makeErrorNode() {
        ErrorNode errorNode = new ErrorNode(this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
        errorNode.setLineno(this.ts.lineno);
        return errorNode;
    }

    private int nodeEnd(AstNode astNode) {
        return astNode.getPosition() + astNode.getLength();
    }

    private void saveNameTokenData(int i, String str, int i2) {
        this.prevNameTokenStart = i;
        this.prevNameTokenString = str;
        this.prevNameTokenLineno = i2;
    }

    private int lineBeginningFor(int i) {
        if (this.sourceChars == null) {
            return -1;
        }
        if (i <= 0) {
            return 0;
        }
        char[] cArr = this.sourceChars;
        if (i >= cArr.length) {
            i = cArr.length - 1;
        }
        do {
            i--;
            if (i < 0) {
                return 0;
            }
        } while (!ScriptRuntime.isJSLineTerminator(cArr[i]));
        return i + 1;
    }

    private void warnMissingSemi(int i, int i2) {
        if (this.compilerEnv.isStrictMode()) {
            int[] iArr = new int[2];
            String line = this.ts.getLine(i2, iArr);
            int max = this.compilerEnv.isIdeMode() ? Math.max(i, i2 - iArr[1]) : i;
            if (line != null) {
                addStrictWarning("msg.missing.semi", "", max, i2 - max, iArr[0], line, iArr[1]);
            } else {
                addStrictWarning("msg.missing.semi", "", max, i2 - max);
            }
        }
    }

    private void warnTrailingComma(int i, List<?> list, int i2) {
        if (this.compilerEnv.getWarnTrailingComma()) {
            if (!list.isEmpty()) {
                i = ((AstNode) list.get(0)).getPosition();
            }
            int max = Math.max(i, lineBeginningFor(i2));
            addWarning("msg.extra.trailing.comma", max, i2 - max);
        }
    }

    private String readFully(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            char[] cArr = new char[1024];
            StringBuilder stringBuilder = new StringBuilder(1024);
            while (true) {
                int read = bufferedReader.read(cArr, 0, 1024);
                if (read == -1) {
                    break;
                }
                stringBuilder.append(cArr, 0, read);
            }
            String stringBuilder2 = stringBuilder.toString();
            return stringBuilder2;
        } finally {
            bufferedReader.close();
        }
    }

    Node createDestructuringAssignment(int i, Node node, Node node2) {
        String nextTempName = this.currentScriptOrFn.getNextTempName();
        Node destructuringAssignmentHelper = destructuringAssignmentHelper(i, node, node2, nextTempName);
        destructuringAssignmentHelper.getLastChild().addChildToBack(createName(nextTempName));
        return destructuringAssignmentHelper;
    }

    Node destructuringAssignmentHelper(int i, Node node, Node node2, String str) {
        boolean z = true;
        Node createScopeNode = createScopeNode(158, node.getLineno());
        createScopeNode.addChildToFront(new Node(153, createName(39, str, node2)));
        try {
            pushScope(createScopeNode);
            defineSymbol(153, str, true);
            Node node3 = new Node(89);
            createScopeNode.addChildToBack(node3);
            List arrayList = new ArrayList();
            switch (node.getType()) {
                case 33:
                case 36:
                    switch (i) {
                        case 122:
                        case 153:
                        case 154:
                            reportError("msg.bad.assign.left");
                            break;
                    }
                    node3.addChildToBack(simpleAssignment(node, createName(str)));
                    break;
                case 65:
                    z = destructuringArray((ArrayLiteral) node, i, str, node3, arrayList);
                    break;
                case 66:
                    z = destructuringObject((ObjectLiteral) node, i, str, node3, arrayList);
                    break;
                default:
                    reportError("msg.bad.assign.left");
                    break;
            }
            if (z) {
                node3.addChildToBack(createNumber(0.0d));
            }
            createScopeNode.putProp(22, arrayList);
            return createScopeNode;
        } finally {
            popScope();
        }
    }

    boolean destructuringArray(ArrayLiteral arrayLiteral, int i, String str, Node node, List<String> list) {
        int i2 = i == 154 ? 155 : 8;
        boolean z = true;
        int i3 = 0;
        for (AstNode astNode : arrayLiteral.getElements()) {
            if (astNode.getType() == 128) {
                i3++;
            } else {
                Node node2 = new Node(36, createName(str), createNumber((double) i3));
                if (astNode.getType() == 39) {
                    String string = astNode.getString();
                    node.addChildToBack(new Node(i2, createName(49, string, null), node2));
                    if (i != -1) {
                        defineSymbol(i, string, true);
                        list.add(string);
                    }
                } else {
                    node.addChildToBack(destructuringAssignmentHelper(i, astNode, node2, this.currentScriptOrFn.getNextTempName()));
                }
                z = false;
                i3++;
            }
        }
        return z;
    }

    boolean destructuringObject(ObjectLiteral objectLiteral, int i, String str, Node node, List<String> list) {
        int i2;
        if (i == 154) {
            i2 = 155;
        } else {
            i2 = 8;
        }
        boolean z = true;
        for (ObjectProperty objectProperty : objectLiteral.getElements()) {
            int i3;
            Node node2;
            if (this.ts != null) {
                i3 = this.ts.lineno;
            } else {
                i3 = 0;
            }
            AstNode left = objectProperty.getLeft();
            if (left instanceof Name) {
                node2 = new Node(33, createName(str), Node.newString(((Name) left).getIdentifier()));
            } else if (left instanceof StringLiteral) {
                node2 = new Node(33, createName(str), Node.newString(((StringLiteral) left).getValue()));
            } else if (left instanceof NumberLiteral) {
                node2 = new Node(36, createName(str), createNumber((double) ((int) ((NumberLiteral) left).getNumber())));
            } else {
                throw codeBug();
            }
            node2.setLineno(i3);
            Node right = objectProperty.getRight();
            if (right.getType() == 39) {
                String identifier = ((Name) right).getIdentifier();
                node.addChildToBack(new Node(i2, createName(49, identifier, null), node2));
                if (i != -1) {
                    defineSymbol(i, identifier, true);
                    list.add(identifier);
                }
            } else {
                node.addChildToBack(destructuringAssignmentHelper(i, right, node2, this.currentScriptOrFn.getNextTempName()));
            }
            z = false;
        }
        return z;
    }

    protected Node createName(String str) {
        checkActivationName(str, 39);
        return Node.newString(39, str);
    }

    protected Node createName(int i, String str, Node node) {
        Node createName = createName(str);
        createName.setType(i);
        if (node != null) {
            createName.addChildToBack(node);
        }
        return createName;
    }

    protected Node createNumber(double d) {
        return Node.newNumber(d);
    }

    protected Scope createScopeNode(int i, int i2) {
        Scope scope = new Scope();
        scope.setType(i);
        scope.setLineno(i2);
        return scope;
    }

    protected Node simpleAssignment(Node node, Node node2) {
        int type = node.getType();
        Node target;
        switch (type) {
            case 33:
            case 36:
                Node property;
                Node node3;
                int i;
                if (node instanceof PropertyGet) {
                    target = ((PropertyGet) node).getTarget();
                    property = ((PropertyGet) node).getProperty();
                    node3 = target;
                } else if (node instanceof ElementGet) {
                    target = ((ElementGet) node).getTarget();
                    property = ((ElementGet) node).getElement();
                    node3 = target;
                } else {
                    target = node.getFirstChild();
                    property = node.getLastChild();
                    node3 = target;
                }
                if (type == 33) {
                    i = 35;
                    property.setType(41);
                } else {
                    i = 37;
                }
                return new Node(i, node3, property, node2);
            case 39:
                if (this.inUseStrictDirective && "eval".equals(((Name) node).getIdentifier())) {
                    reportError("msg.bad.id.strict", ((Name) node).getIdentifier());
                }
                node.setType(49);
                return new Node(8, node, node2);
            case 67:
                target = node.getFirstChild();
                checkMutableReference(target);
                return new Node(68, target, node2);
            default:
                throw codeBug();
        }
    }

    protected void checkMutableReference(Node node) {
        if ((node.getIntProp(16, 0) & 4) != 0) {
            reportError("msg.bad.assign.left");
        }
    }

    protected AstNode removeParens(AstNode astNode) {
        AstNode astNode2 = astNode;
        while (astNode2 instanceof ParenthesizedExpression) {
            astNode2 = ((ParenthesizedExpression) astNode2).getExpression();
        }
        return astNode2;
    }

    void markDestructuring(AstNode astNode) {
        if (astNode instanceof DestructuringForm) {
            ((DestructuringForm) astNode).setIsDestructuring(true);
        } else if (astNode instanceof ParenthesizedExpression) {
            markDestructuring(((ParenthesizedExpression) astNode).getExpression());
        }
    }

    private RuntimeException codeBug() throws RuntimeException {
        throw Kit.codeBug("ts.cursor=" + this.ts.cursor + ", ts.tokenBeg=" + this.ts.tokenBeg + ", currentToken=" + this.currentToken);
    }
}
