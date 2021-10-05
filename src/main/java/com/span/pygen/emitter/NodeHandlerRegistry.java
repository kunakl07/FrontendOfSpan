package com.span.pygen.emitter;

import com.span.ast.nodes.ClassDeclarationStatementNode;
import com.span.ast.nodes.LatticePropertyAssignmentNode;
import com.span.ast.nodes.OverallLatticeAssignmentNode;
import com.span.pygen.emitter.nodehandlers.ClassDeclarationHandler;
import com.span.pygen.emitter.nodehandlers.LatticePropertyAssignmentHandler;
import com.span.pygen.emitter.nodehandlers.OverallLatticeAssignmentHandler;

import java.util.HashMap;
import java.util.Optional;

/**
 * Register all your NodeHandlers here
 */
public class NodeHandlerRegistry {
    private static HashMap<Class, NodeHandler> implementedHandlers;

    static {
        NodeHandlerRegistry.implementedHandlers = new HashMap<>();

        NodeHandlerRegistry.implementedHandlers.put(ClassDeclarationStatementNode.class, new ClassDeclarationHandler());
        NodeHandlerRegistry.implementedHandlers.put(OverallLatticeAssignmentNode.class, new OverallLatticeAssignmentHandler());
        NodeHandlerRegistry.implementedHandlers.put(LatticePropertyAssignmentNode.class, new LatticePropertyAssignmentHandler());
    }

    /**
     * Fetches a NodeHandler instance for the given class
     *
     * @param nodeClass
     * @return
     */
    public static Optional<NodeHandler> getNodeHandler(Class nodeClass) {
        return Optional.ofNullable(NodeHandlerRegistry.implementedHandlers.get(nodeClass));
    }

}
