package com.span.ast.nodes;

public class ExpressionAssignmentNode extends BaseAstNode {
    private final String identifier;
    // expression would be the child of this node

    public ExpressionAssignmentNode(int lineNumber, int columnNumber, String identifier) {
        super(lineNumber, columnNumber);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "ExpressionAssignmentNode{" +
                "identifier='" + identifier + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
