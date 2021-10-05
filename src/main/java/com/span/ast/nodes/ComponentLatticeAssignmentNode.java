package com.span.ast.nodes;

public class ComponentLatticeAssignmentNode extends BaseAstNode {
    /**
     * The identifier that was assigned this component lattice
     */
    private String identifier;

    public ComponentLatticeAssignmentNode(int lineNumber, int columnNumber, String identifier) {
        super(lineNumber, columnNumber);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "ComponentLatticeAssignmentNode{" +
                "identifier='" + identifier + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
