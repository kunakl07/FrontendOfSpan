package com.span.ast.nodes;

public class TransferFunctionBodyNode extends BaseAstNode {
    private final String dataFlowVar;
    private final String irExpression;
    // children will hold several assignment stmts and atleast one expression
    // to be precise a function call
    // This function call is the return value to be used for this irEpxr body

    public TransferFunctionBodyNode(int lineNumber, int columnNumber, String dataFlowVar, String irExpression) {
        super(lineNumber, columnNumber);
        this.dataFlowVar = dataFlowVar;
        this.irExpression = irExpression;
    }

    public String getDataFlowVar() {
        return dataFlowVar;
    }

    public String getIrExpression() {
        return irExpression;
    }

    @Override
    public String toString() {
        return "TransferFunctionBodyNode{" +
                "dataFlowVar='" + dataFlowVar + '\'' +
                ", irExpression='" + irExpression + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
