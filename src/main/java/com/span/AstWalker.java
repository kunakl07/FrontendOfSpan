package com.span;

import com.span.ast.nodes.BaseAstNode;

public class AstWalker {
    private BaseAstNode root;
    private Emitter emitter;
    public AstWalker(BaseAstNode root, Emitter emitter){
        this.root = root;
        this.emitter = emitter;
    }

    /**
     * callback triggered for every node found in each line
     */
    public static interface Emitter{
        public void emit(BaseAstNode node);
    }

    public void walk(){
        this.root.getChildren().forEach((child)->{
            this.emitter.emit(child);
        });
    }

}
