package com.span.ast.nodes;

/**
 * To be used to track enums used to create a component lattice. Enums could contain
 * a constructor call of this lattice.
 */
public class ComponentLatticeCtorDefNode extends BaseAstNode {
    private String identifier;
    private String valueType; // the value that this lattice operates upon

    public ComponentLatticeCtorDefNode(int lineNumber, int columnNumber, String identifier, String valueType) {
        super(lineNumber, columnNumber);
        this.identifier = identifier;
        this.valueType = valueType;
    }

    public ComponentLatticeCtorDefNode(int lineNumber, int columnNumber, String identifier){
        this(lineNumber, columnNumber, identifier, null);
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getValueType(){
        return this.valueType;
    }

    @Override
    public String toString() {
        return "ComponentLatticeCtorDefNode{" +
                "identifier='" + identifier + '\'' +
                ", valueType='" + valueType + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
