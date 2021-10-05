package com.span.ast.nodes;

public class DfvNode extends BaseAstNode {
    private final DfvNodeType currentType;

    public DfvNode(int line, int col, DfvNodeType nodeType) {
        super(line, col);
        this.currentType = nodeType;
    }

    /**
     * Returns a DfvNodeType instance given the a string
     * @param nodeType String : The type of node encountered
     * @return DfvNodeType: An enum that represents the given string
     */
    public static DfvNodeType fromStr(String nodeType) {
        switch (nodeType) {
            case "dfv":
                return DfvNodeType.DFV;
            case "dfvin":
                return DfvNodeType.DFV_IN;
            case "dfvout":
                return DfvNodeType.DFV_OUT;
            default:
                throw new IllegalStateException("Unexpected value: " + nodeType);
        }
    }

    public DfvNodeType getCurrentType() {
        return currentType;
    }

    @Override
    public String toString() {
        return "DfvNode{" +
                "currentType=" + currentType +
                '}';
    }

    enum DfvNodeType {
        DFV, DFV_IN, DFV_OUT
    }
}
