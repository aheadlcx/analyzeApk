package org.mozilla.javascript;

import java.util.Set;
import org.mozilla.javascript.ast.ErrorCollector;

public class CompilerEnvirons {
    Set<String> activationNames;
    private boolean allowMemberExprAsFunctionName = false;
    private boolean allowSharpComments = false;
    private ErrorReporter errorReporter = DefaultErrorReporter.instance;
    private boolean generateDebugInfo = true;
    private boolean generateObserverCount = false;
    private boolean generatingSource = true;
    private boolean ideMode;
    private int languageVersion = 0;
    private int optimizationLevel = 0;
    private boolean recordingComments;
    private boolean recordingLocalJsDocComments;
    private boolean recoverFromErrors;
    private boolean reservedKeywordAsIdentifier = true;
    private boolean strictMode = false;
    private boolean warnTrailingComma;
    private boolean warningAsError = false;
    private boolean xmlAvailable = true;

    public void initFromContext(Context context) {
        setErrorReporter(context.getErrorReporter());
        this.languageVersion = context.getLanguageVersion();
        boolean z = !context.isGeneratingDebugChanged() || context.isGeneratingDebug();
        this.generateDebugInfo = z;
        this.reservedKeywordAsIdentifier = context.hasFeature(3);
        this.allowMemberExprAsFunctionName = context.hasFeature(2);
        this.strictMode = context.hasFeature(11);
        this.warningAsError = context.hasFeature(12);
        this.xmlAvailable = context.hasFeature(6);
        this.optimizationLevel = context.getOptimizationLevel();
        this.generatingSource = context.isGeneratingSource();
        this.activationNames = context.activationNames;
        this.generateObserverCount = context.generateObserverCount;
    }

    public final ErrorReporter getErrorReporter() {
        return this.errorReporter;
    }

    public void setErrorReporter(ErrorReporter errorReporter) {
        if (errorReporter == null) {
            throw new IllegalArgumentException();
        }
        this.errorReporter = errorReporter;
    }

    public final int getLanguageVersion() {
        return this.languageVersion;
    }

    public void setLanguageVersion(int i) {
        Context.checkLanguageVersion(i);
        this.languageVersion = i;
    }

    public final boolean isGenerateDebugInfo() {
        return this.generateDebugInfo;
    }

    public void setGenerateDebugInfo(boolean z) {
        this.generateDebugInfo = z;
    }

    public final boolean isReservedKeywordAsIdentifier() {
        return this.reservedKeywordAsIdentifier;
    }

    public void setReservedKeywordAsIdentifier(boolean z) {
        this.reservedKeywordAsIdentifier = z;
    }

    public final boolean isAllowMemberExprAsFunctionName() {
        return this.allowMemberExprAsFunctionName;
    }

    public void setAllowMemberExprAsFunctionName(boolean z) {
        this.allowMemberExprAsFunctionName = z;
    }

    public final boolean isXmlAvailable() {
        return this.xmlAvailable;
    }

    public void setXmlAvailable(boolean z) {
        this.xmlAvailable = z;
    }

    public final int getOptimizationLevel() {
        return this.optimizationLevel;
    }

    public void setOptimizationLevel(int i) {
        Context.checkOptimizationLevel(i);
        this.optimizationLevel = i;
    }

    public final boolean isGeneratingSource() {
        return this.generatingSource;
    }

    public boolean getWarnTrailingComma() {
        return this.warnTrailingComma;
    }

    public void setWarnTrailingComma(boolean z) {
        this.warnTrailingComma = z;
    }

    public final boolean isStrictMode() {
        return this.strictMode;
    }

    public void setStrictMode(boolean z) {
        this.strictMode = z;
    }

    public final boolean reportWarningAsError() {
        return this.warningAsError;
    }

    public void setGeneratingSource(boolean z) {
        this.generatingSource = z;
    }

    public boolean isGenerateObserverCount() {
        return this.generateObserverCount;
    }

    public void setGenerateObserverCount(boolean z) {
        this.generateObserverCount = z;
    }

    public boolean isRecordingComments() {
        return this.recordingComments;
    }

    public void setRecordingComments(boolean z) {
        this.recordingComments = z;
    }

    public boolean isRecordingLocalJsDocComments() {
        return this.recordingLocalJsDocComments;
    }

    public void setRecordingLocalJsDocComments(boolean z) {
        this.recordingLocalJsDocComments = z;
    }

    public void setRecoverFromErrors(boolean z) {
        this.recoverFromErrors = z;
    }

    public boolean recoverFromErrors() {
        return this.recoverFromErrors;
    }

    public void setIdeMode(boolean z) {
        this.ideMode = z;
    }

    public boolean isIdeMode() {
        return this.ideMode;
    }

    public Set<String> getActivationNames() {
        return this.activationNames;
    }

    public void setActivationNames(Set<String> set) {
        this.activationNames = set;
    }

    public void setAllowSharpComments(boolean z) {
        this.allowSharpComments = z;
    }

    public boolean getAllowSharpComments() {
        return this.allowSharpComments;
    }

    public static CompilerEnvirons ideEnvirons() {
        CompilerEnvirons compilerEnvirons = new CompilerEnvirons();
        compilerEnvirons.setRecoverFromErrors(true);
        compilerEnvirons.setRecordingComments(true);
        compilerEnvirons.setStrictMode(true);
        compilerEnvirons.setWarnTrailingComma(true);
        compilerEnvirons.setLanguageVersion(170);
        compilerEnvirons.setReservedKeywordAsIdentifier(true);
        compilerEnvirons.setIdeMode(true);
        compilerEnvirons.setErrorReporter(new ErrorCollector());
        return compilerEnvirons;
    }
}
