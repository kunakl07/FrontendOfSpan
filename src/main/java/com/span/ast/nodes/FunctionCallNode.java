package com.span.ast.nodes;

public class FunctionCallNode extends BaseAstNode {
    /**
     * Stores the name of the method that was invoked
     * ArgsList is in its children
     */
    private String identifier;

    public FunctionCallNode(int lineNumber, int columnNumber, String identifier) {
        super(lineNumber, columnNumber);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "FunctionCallNode{" +
                "identifier='" + identifier + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
