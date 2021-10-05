package com.span.ast.nodes;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class ComponentLatticeDeclarationNode extends BaseAstNode {
    private String identifier;
    private List<String> enumsList;
    public ComponentLatticeDeclarationNode(int lineNumber, int column, String identifier, List<Token> enumsList) {
        super(lineNumber, column);
        this.identifier = identifier;
        this.enumsList = new ArrayList();
        enumsList.forEach((Token t)->{this.enumsList.add(t.getText());});
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<String> getEnumsList() {
        return enumsList;
    }

    @Override
    public String toString() {
        return "ComponentLatticeDeclarationNode{" +
                "identifier='" + identifier + '\'' +
                ", enumsList=" + enumsList +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
