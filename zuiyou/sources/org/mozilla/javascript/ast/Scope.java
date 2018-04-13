package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.mozilla.javascript.Node;

public class Scope extends Jump {
    private List<Scope> childScopes;
    protected Scope parentScope;
    protected Map<String, Symbol> symbolTable;
    protected ScriptNode top;

    public Scope() {
        this.type = 129;
    }

    public Scope(int i) {
        this.type = 129;
        this.position = i;
    }

    public Scope(int i, int i2) {
        this(i);
        this.length = i2;
    }

    public Scope getParentScope() {
        return this.parentScope;
    }

    public void setParentScope(Scope scope) {
        this.parentScope = scope;
        this.top = scope == null ? (ScriptNode) this : scope.top;
    }

    public void clearParentScope() {
        this.parentScope = null;
    }

    public List<Scope> getChildScopes() {
        return this.childScopes;
    }

    public void addChildScope(Scope scope) {
        if (this.childScopes == null) {
            this.childScopes = new ArrayList();
        }
        this.childScopes.add(scope);
        scope.setParentScope(this);
    }

    public void replaceWith(Scope scope) {
        if (this.childScopes != null) {
            for (Scope addChildScope : this.childScopes) {
                scope.addChildScope(addChildScope);
            }
            this.childScopes.clear();
            this.childScopes = null;
        }
        if (this.symbolTable != null && !this.symbolTable.isEmpty()) {
            joinScopes(this, scope);
        }
    }

    public ScriptNode getTop() {
        return this.top;
    }

    public void setTop(ScriptNode scriptNode) {
        this.top = scriptNode;
    }

    public static Scope splitScope(Scope scope) {
        AstNode scope2 = new Scope(scope.getType());
        scope2.symbolTable = scope.symbolTable;
        scope.symbolTable = null;
        scope2.parent = scope.parent;
        scope2.setParentScope(scope.getParentScope());
        scope2.setParentScope(scope2);
        scope.parent = scope2;
        scope2.top = scope.top;
        return scope2;
    }

    public static void joinScopes(Scope scope, Scope scope2) {
        Map ensureSymbolTable = scope.ensureSymbolTable();
        Map ensureSymbolTable2 = scope2.ensureSymbolTable();
        if (!Collections.disjoint(ensureSymbolTable.keySet(), ensureSymbolTable2.keySet())) {
            AstNode.codeBug();
        }
        for (Entry entry : ensureSymbolTable.entrySet()) {
            Symbol symbol = (Symbol) entry.getValue();
            symbol.setContainingTable(scope2);
            ensureSymbolTable2.put(entry.getKey(), symbol);
        }
    }

    public Scope getDefiningScope(String str) {
        for (Scope scope = this; scope != null; scope = scope.parentScope) {
            Map symbolTable = scope.getSymbolTable();
            if (symbolTable != null && symbolTable.containsKey(str)) {
                return scope;
            }
        }
        return null;
    }

    public Symbol getSymbol(String str) {
        return this.symbolTable == null ? null : (Symbol) this.symbolTable.get(str);
    }

    public void putSymbol(Symbol symbol) {
        if (symbol.getName() == null) {
            throw new IllegalArgumentException("null symbol name");
        }
        ensureSymbolTable();
        this.symbolTable.put(symbol.getName(), symbol);
        symbol.setContainingTable(this);
        this.top.addSymbol(symbol);
    }

    public Map<String, Symbol> getSymbolTable() {
        return this.symbolTable;
    }

    public void setSymbolTable(Map<String, Symbol> map) {
        this.symbolTable = map;
    }

    private Map<String, Symbol> ensureSymbolTable() {
        if (this.symbolTable == null) {
            this.symbolTable = new LinkedHashMap(5);
        }
        return this.symbolTable;
    }

    public List<AstNode> getStatements() {
        List<AstNode> arrayList = new ArrayList();
        for (Node firstChild = getFirstChild(); firstChild != null; firstChild = firstChild.getNext()) {
            arrayList.add((AstNode) firstChild);
        }
        return arrayList;
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeIndent(i));
        stringBuilder.append("{\n");
        Iterator it = iterator();
        while (it.hasNext()) {
            stringBuilder.append(((AstNode) ((Node) it.next())).toSource(i + 1));
        }
        stringBuilder.append(makeIndent(i));
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            Iterator it = iterator();
            while (it.hasNext()) {
                ((AstNode) ((Node) it.next())).visit(nodeVisitor);
            }
        }
    }
}
