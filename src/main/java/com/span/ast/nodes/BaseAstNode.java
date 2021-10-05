package com.span.ast.nodes;

import java.util.ArrayList;
import java.util.List;

public class BaseAstNode {
    private static final int NOT_INITIALIZED = Integer.MIN_VALUE;

    protected int lineNumber = NOT_INITIALIZED;
    protected int columnNumber = NOT_INITIALIZED;

    protected List<BaseAstNode> children;

    public BaseAstNode(int lineNumber, int columnNumber) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
        this.children = new ArrayList<>();
    }

    public BaseAstNode(){
        this(NOT_INITIALIZED, NOT_INITIALIZED);
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    /**
     * Appends a child BaseAstNode ref to this list
     * @param node The node to be added
     */
    public void addChild(BaseAstNode node){this.children.add(node);}

    /**
     * Returns a new list of the children of this node.
     * @return List<BaseAstNode>
     */
    public List<BaseAstNode> getChildren(){return new ArrayList<>(this.children);}

    /**
     * To be used carefully. Clears the list of all the children this node has
     */
    public void clearChildren(){
        this.children.clear();
    }

    public void removeChild(BaseAstNode node){
        this.children.remove(node);
    }

}
