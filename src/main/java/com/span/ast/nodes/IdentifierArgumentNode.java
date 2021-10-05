package com.span.ast.nodes;

public class IdentifierArgumentNode extends BaseAstNode{
    private String identifier;

    public IdentifierArgumentNode(int lineNumber, int columnNumber, String identifier) {
        super(lineNumber, columnNumber);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "IdentifierArgumentNode{" +
                "identifier='" + identifier + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
