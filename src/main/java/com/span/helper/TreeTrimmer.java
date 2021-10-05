package com.span.helper;

import com.span.ast.nodes.ArgsListNode;
import com.span.ast.nodes.BaseAstNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeTrimmer {

    private static final List<Class> forbiddenTrimList = new ArrayList<>(Arrays.asList(ArgsListNode.class));

    /**
     * Moves child nodes to the given parent node
     * Think of it this way. The current placement of nodes in the parse tree leads to a pattern that looks something
     * like this
     * A
     * |-- B
     *      |---C
     *          |---D
     * What we want is
     *  A
     *  |--B
     *  |--C
     *  |--D
     * @param parent The node that requires children to be pushed up
     * */
    public static void trimNodes(BaseAstNode parent){
        parent.getChildren().stream().filter(
                (BaseAstNode node)->{
                    return ! TreeTrimmer.isClassInForbiddenTrimList(node.getClass()) ;
                }).forEach(
                (BaseAstNode child)->{
                    /**
                     * filtering children of child for cases like functionCall node being a
                     * child of argumentNode... This could lead to the argsList node of the functionCallNode
                     * being pushed up.
                     */
                    child.getChildren().stream().filter(
                            (BaseAstNode node)->{
                                return ! TreeTrimmer.isClassInForbiddenTrimList(node.getClass()) ;
                            }).forEach((node)->{
                        child.removeChild(node);
                        parent.addChild(node);
                    });
                });
    }

    private static boolean isClassInForbiddenTrimList(Class classToBeTested){
        return TreeTrimmer.forbiddenTrimList.contains(classToBeTested);
    }
}
