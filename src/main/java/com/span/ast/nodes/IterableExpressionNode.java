package com.span.ast.nodes;

public class IterableExpressionNode extends BaseAstNode {
    private final String sink;
    private final String source;
    // if the iteration operation includes a function call source would be empty string,
    // its child would be a function call node

    public IterableExpressionNode(int lineNumber, int columnNumber, String sink, String source) {
        super(lineNumber, columnNumber);
        this.sink = sink;
        this.source = source;
    }

    public String getSink() {
        return sink;
    }

    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "IterableExpressionNode{" +
                "sink='" + sink + '\'' +
                ", source='" + source + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
