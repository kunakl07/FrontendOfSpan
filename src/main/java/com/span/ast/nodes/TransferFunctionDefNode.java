package com.span.ast.nodes;

public class TransferFunctionDefNode extends BaseAstNode {
    private final String latticeName;
    // child holds body

    public TransferFunctionDefNode(int lineNumber, int columnNumber, String latticeName) {
        super(lineNumber, columnNumber);
        this.latticeName = latticeName;
    }

    public String getLatticeName() {
        return latticeName;
    }

    @Override
    public String toString() {
        return "TransferFunctionDefNode{" +
                "latticeName='" + latticeName + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
