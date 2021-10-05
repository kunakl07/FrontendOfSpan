package com.span.ast.nodes;

import com.span.ast.nodes.BaseAstNode;

public class TransferFunctionNode extends BaseAstNode {
    private final String latticeName;
    private final String latticeProperty;

    public TransferFunctionNode(int lineNumber, int columnNumber, String latticeName, String latticeProperty) {
        super(lineNumber, columnNumber);
        this.latticeName = latticeName;
        this.latticeProperty = latticeProperty;
    }

    public String getLatticeName() {
        return latticeName;
    }

    public String getLatticeProperty() {
        return latticeProperty;
    }

    @Override
    public String toString() {
        return "TransferFunctionNode{" +
                "latticeName='" + latticeName + '\'' +
                ", latticeProperty='" + latticeProperty + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
