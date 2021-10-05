package com.span.ast.nodes;

public class SetComprehensionNode extends BaseAstNode {
    private final String identifier;
    // children include an optional iterable expression and a
    // necessary conditionalExpression

    public SetComprehensionNode(int lineNumber, int columnNumber, String identifier) {
        super(lineNumber, columnNumber);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "SetComprehensionNode{" +
                "identifier='" + identifier + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
