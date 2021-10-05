package com.span.pygen.emitter;

import com.span.ast.nodes.BaseAstNode;
import com.span.pygen.codebuilder.TabbedUnit;

import java.util.List;
import java.util.Optional;

/**
 * Node handlers are classes that generate TabbedUnits from a BaseASTNode
 * NodeHandlers generate incomplete code. These blocks aren't fully functional until they are combined
 * with other blocks. Certain blocks are root level blocks, while others are leaves.
 */
public interface NodeHandler {
    public Optional<List<TabbedUnit>> handle(BaseAstNode node);
    public static Optional EMPTY = Optional.empty();
}
