package com.span.pygen.emitter;

import com.span.AstWalker;
import com.span.ast.nodes.BaseAstNode;
import com.span.pygen.codebuilder.TabbedUnit;

import java.util.ArrayList;
import java.util.List;

public class PyEmitter implements AstWalker.Emitter {

    private List<TabbedUnit> code;

    public PyEmitter(){
        this.code = new ArrayList<>();
    }

    @Override
    public void emit(BaseAstNode node) {
        NodeHandlerRegistry.getNodeHandler(node.getClass()).ifPresent((nodeHandler -> {
             nodeHandler.handle(node).ifPresent((units -> {
                 this.code.addAll(units);
             }));
        }));
    }

    public List<TabbedUnit> getCode(){
        return this.code;
    }

}
