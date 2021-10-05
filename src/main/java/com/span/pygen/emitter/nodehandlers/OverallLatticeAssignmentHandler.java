package com.span.pygen.emitter.nodehandlers;

import com.span.ast.nodes.BaseAstNode;
import com.span.ast.nodes.FunctionCallNode;
import com.span.ast.nodes.GeneralTypePlaceholderNode;
import com.span.ast.nodes.OverallLatticeAssignmentNode;
import com.span.pygen.codebuilder.Class;
import com.span.pygen.codebuilder.TabbedUnit;
import com.span.pygen.emitter.nodehandlers.contexts.ClassContextBuffer;
import com.span.pygen.emitter.NodeHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OverallLatticeAssignmentHandler implements NodeHandler {

    private static final String BASE_VAL_STR = "Optional[%s[types.%s]] = None";
    private static final String[] DEF_EXTENDS_LIST = {"DataLT"};
    // todo add func, val, top and bot to this
    private static final String DEF_CTOR_NAME = "__init__";
    private ClassContextBuffer ctxBuf;
    public OverallLatticeAssignmentHandler(){
        this.ctxBuf = ClassContextBuffer.getInstance();
    }


    @Override
    public Optional<List<TabbedUnit>> handle(BaseAstNode node) {
        OverallLatticeAssignmentNode assignmentNode = (OverallLatticeAssignmentNode) node;
        Class c = Class.newClass((klass)->{

            klass.name(assignmentNode.getIdentifier()).extendClasses(DEF_EXTENDS_LIST);
            klass.function((ctor)->{
               ctor.name(DEF_CTOR_NAME).arguments(Arrays.asList(
                 "func: obj.Func",
                 "val: "+OverallLatticeAssignmentHandler.fmtVal(node),
                 "top: bool = False",
                 "bot: bool = False"
               ));
            });
            klass.setValueType(OverallLatticeAssignmentHandler.findValueType(node));
        });
        this.ctxBuf.addClass(c);
        return Optional.of(Arrays.asList(c));
    }

    /**
     * Formats strings in BASE_VAL_STR
     * @param root
     * @return
     */
    private static String fmtVal(BaseAstNode root){
        String containerType = OverallLatticeAssignmentHandler.findContainerType(root);
        String valType = OverallLatticeAssignmentHandler.findValueType(root);
        return String.format(OverallLatticeAssignmentHandler.BASE_VAL_STR, containerType, valType);
    }

    /**
     * Returns the name of the first function that it finds
     * @param node
     * @return
     */
    private static String findContainerType(BaseAstNode node){
        if(node instanceof FunctionCallNode)
            return ((FunctionCallNode)node).getIdentifier();
        String result = "";
        for (BaseAstNode child : node.getChildren()) {
            result = OverallLatticeAssignmentHandler.findContainerType(child);
            if (!result.isEmpty())
                return result;
        }

        return result;
    }

    /**
     * Finds the first occurrence of a GeneralTypePlaceHolder
     * @param node
     * @return
     */
    private static String findValueType(BaseAstNode node){
        if(node instanceof GeneralTypePlaceholderNode)
            return ((GeneralTypePlaceholderNode)node).getPlaceholder();
        String result = "";
        for (BaseAstNode child : node.getChildren()) {
            result = OverallLatticeAssignmentHandler.findValueType(child);
            if (!result.isEmpty())
                return result;
        }

        return result;
    }

}
