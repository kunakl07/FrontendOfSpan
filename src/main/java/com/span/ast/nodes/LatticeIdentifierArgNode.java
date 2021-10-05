package com.span.ast.nodes;

public class LatticeIdentifierArgNode extends IdentifierArgumentNode{
    private String latticeProperty;

    public LatticeIdentifierArgNode(int lineNumber, int columnNumber, String latticeIdentifier, String latticeProperty) {
        super(lineNumber, columnNumber, latticeIdentifier);
        this.latticeProperty = latticeProperty;
    }

    public String getLatticeProperty() {
        return latticeProperty;
    }

    @Override
    public String toString() {
        return "LatticeIdentifierArgNode{" +
                "identifier='" + super.getIdentifier() + '\'' +
                "latticeProperty='" + latticeProperty + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
