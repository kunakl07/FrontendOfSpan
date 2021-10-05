package com.span.ast.nodes;

public class ConditionalExpressionNode extends BaseAstNode {
    private final String lhs;
    private final String rhs;
    private final String operation;
    private final boolean not;
    // if rhs contains a function call this node's childrenList wont be empty
    public ConditionalExpressionNode(int lineNumber, int columnNumber, String lhs, String rhs,
                                     String operation, boolean not) {
        super(lineNumber, columnNumber);
        this.lhs = lhs;
        this.rhs = rhs;
        this.operation = operation;
        this.not = not;
    }

    public String getLhs() {
        return lhs;
    }

    public String getRhs() {
        return rhs;
    }

    public String getOperation() {
        return operation;
    }

    public boolean hasNot() {
        return not;
    }

    @Override
    public String toString() {
        return "ConditionalExpressionNode{" +
                "lhs='" + lhs + '\'' +
                ", rhs='" + rhs + '\'' +
                ", operation=" + operation +
                ", not=" + not +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }

}
