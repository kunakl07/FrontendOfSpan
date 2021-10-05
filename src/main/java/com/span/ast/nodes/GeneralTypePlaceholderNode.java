package com.span.ast.nodes;

public class GeneralTypePlaceholderNode extends BaseAstNode{
    private String placeholder;

    public GeneralTypePlaceholderNode(int lineNumber, int columnNumber, String placeholder) {
        super(lineNumber, columnNumber);
        this.placeholder = placeholder;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    public String toString() {
        return "GeneralTypePlaceholderNode{" +
                "placeholder='" + placeholder + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
