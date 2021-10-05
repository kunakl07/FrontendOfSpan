package com.span.ast.nodes;

public class LatticePropertyAssignmentNode extends BaseAstNode{
    /**
     * The name of the lattice
     */
    private String identifier;
    /**
     * Stores a lattice property. Is either of Top, Bot, Meet, BI, PO
     */
    private String latticeProperty;
    /**
     * Stores the name of an identifier to the right of this assignment expr.
     * If this is null then the value on the right is the result of a method.
     * Traverse the children to obtain a functionCall instance
     */
    private String rhsIdentifier;

    public LatticePropertyAssignmentNode(int lineNumber, int columnNumber, String identifier, String latticeProperty) {
        super(lineNumber, columnNumber);
        this.identifier = identifier;
        this.latticeProperty = latticeProperty;
    }

    public LatticePropertyAssignmentNode(int lineNumber, int columnNumber, String identifier, String latticeProperty
    ,String rhsIdentifier) {
        this(lineNumber, columnNumber, identifier, latticeProperty);
        this.rhsIdentifier = rhsIdentifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getRhsIdentifier() {
        return rhsIdentifier;
    }

    public String getLatticeProperty() {
        return latticeProperty;
    }

    @Override
    public String toString() {
        return "LatticePropertyAssignmentNode{" +
                "identifier='" + identifier + '\'' +
                ", latticeProperty='" + latticeProperty + '\'' +
                ", rhsIdentifier='" + rhsIdentifier + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
