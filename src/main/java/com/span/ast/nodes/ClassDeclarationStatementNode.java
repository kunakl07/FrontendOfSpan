package com.span.ast.nodes;



import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class ClassDeclarationStatementNode extends BaseAstNode {
    private List<String> identifiers;
    private String type;
    public ClassDeclarationStatementNode(int lineNumber, int column) {
        this(lineNumber, column, null, null);
    }

    public ClassDeclarationStatementNode(int line, int column, List<Token> identifiers, String type){
        super(line, column);
        this.type = type;
        this.identifiers = new ArrayList<>();
        identifiers.forEach(
                (Token t)->{
                    this.identifiers.add(t.getText());
                }
        );
    }

    public String getType(){return this.type;}
    public List<String> getIdentifiers() {
        return this.identifiers;
    }

    @Override
    public String toString() {
        return "ClassDeclarationNode{" +
                "identifiers='" + this.identifiers.toString() + '\'' +
                ", type='" + type + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
