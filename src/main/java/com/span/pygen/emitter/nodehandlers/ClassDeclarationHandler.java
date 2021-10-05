package com.span.pygen.emitter.nodehandlers;

import com.span.ast.nodes.BaseAstNode;
import com.span.ast.nodes.ClassDeclarationStatementNode;
import com.span.pygen.codebuilder.TabbedUnit;
import com.span.pygen.emitter.NodeHandler;
import com.span.pygen.emitter.nodehandlers.contexts.LatticeDeclContextBuffer;

import java.util.List;
import java.util.Optional;

/**
 * Handles a class declaration node
 */
public class ClassDeclarationHandler implements NodeHandler {

    private LatticeDeclContextBuffer latticeContextBuffer;

    public ClassDeclarationHandler(){
        this.latticeContextBuffer = LatticeDeclContextBuffer.getInstance();
    }

    @Override
    public Optional<List<TabbedUnit>> handle(BaseAstNode node) {
        ClassDeclarationStatementNode classDeclarationStatementNode = (ClassDeclarationStatementNode)node;
        classDeclarationStatementNode.getIdentifiers().forEach(latticeContextBuffer::add);
        return NodeHandler.EMPTY;
    }
}
