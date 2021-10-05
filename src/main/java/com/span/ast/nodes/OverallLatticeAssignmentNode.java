package com.span.ast.nodes;

public class OverallLatticeAssignmentNode extends BaseAstNode {
    private String identifier;
    public OverallLatticeAssignmentNode(int lineNumber, int column, String lhsIdentifier) {
        super(lineNumber, column);
        this.identifier = lhsIdentifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "OverallLatticeAssignmentNode{" +
                "lhsIdentifier='" + identifier + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
