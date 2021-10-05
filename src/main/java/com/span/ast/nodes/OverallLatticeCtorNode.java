package com.span.ast.nodes;

public class OverallLatticeCtorNode extends BaseAstNode{
    private String identifier;

    public static OverallLatticeCtorNode fromFunctionCall(FunctionCallNode node){
        OverallLatticeCtorNode overall = new OverallLatticeCtorNode(node.getLineNumber(), node.getColumnNumber(),
                node.getIdentifier());
        node.getChildren().forEach((child)->{
            overall.addChild(child);
        });
        return overall;
    }


    public OverallLatticeCtorNode (int line, int col, String identifier){
        super(line, col);
        this.identifier = identifier;
    }


    @Override
    public String toString() {
        return "OverallLatticeCtorNode{" +
                "identifier='" + identifier + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
