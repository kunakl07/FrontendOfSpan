package com.span.pygen.emitter.nodehandlers;

import com.span.ast.nodes.BaseAstNode;
import com.span.ast.nodes.LatticePropertyAssignmentNode;
import com.span.pygen.codebuilder.ConditionalBlock;
import com.span.pygen.codebuilder.Function;
import com.span.pygen.codebuilder.TabbedUnit;
import com.span.pygen.emitter.NodeHandler;
import com.span.pygen.emitter.nodehandlers.contexts.ClassContextBuffer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LatticePropertyAssignmentHandler implements NodeHandler {

    private ClassContextBuffer classContextBuffer;
    private static final String SET_SUBSET = "#SetSubset";
    private static final String SET_SUPERSET = "#SetSupset";


    private static final String SET_INTERSECT_STR = "set.intersect(self.val, other.val)";
    private static final String SET_UNION_STR = "set.union(self.val, other.val)";

    private final static String SET_IS_SUBSET_STR = "set.issubset(self.val, other.val)";
    private final static String SET_IS_SUPERSET_STR = "set.issuperset(self.val, other.val)";
    public static final String CTOR_SUPER_CALL = "super().__init__(func, val, top, bot)";


    public LatticePropertyAssignmentHandler(){
        this.classContextBuffer = ClassContextBuffer.getInstance();
    }

    @Override
    public Optional<List<TabbedUnit>> handle(BaseAstNode node){
        LatticePropertyAssignmentNode latticePropertyAssignmentNode = (LatticePropertyAssignmentNode) node;
        if ( latticePropertyAssignmentNode.getRhsIdentifier() != null ){
            String rhsId = latticePropertyAssignmentNode.getRhsIdentifier();
            if( rhsId.equals(SET_SUBSET) ){

                this.handleSetMethod(latticePropertyAssignmentNode, 0);

            } else if( rhsId.equals(SET_SUPERSET) ){

                this.handleSetMethod(latticePropertyAssignmentNode, 1);

            } else{
                // todo implement this
                throw new NotImplementedException();
            }
        } else{
            throw new NotImplementedException();
        }
        return NodeHandler.EMPTY;
    }


    /**
     * Fetches a class and sets its meet, lt and str method
     * @param latticePropertyAssignmentNode
     * @param type 0 - SUBSET, 1 - SUPERSET
     */
    private void handleSetMethod(LatticePropertyAssignmentNode latticePropertyAssignmentNode, int type){
        String id = latticePropertyAssignmentNode.getIdentifier();
        this.classContextBuffer.getClass(id)
                .ifPresent((klass)->{
                    Function ctor = klass.getFunctions().get(0);
                    ctor.block((block -> {
                        block.conditionalBlock((conditionalBlock -> {
                            conditionalBlock.condition((condition -> {
                                condition.customExpression("self.top");
                            })).block((block1 -> {
                                block1.assignment((assignment -> {
                                    assignment.identifier("self.val").rhs("getAll"+klass.getValueType()+"()");
                                }));
                                block1.assignment((assignment -> {
                                    assignment.identifier("self.bot").rhs("False");
                                }));
                            })).type(ConditionalBlock.ConditionBlockType.IF);
                        })).conditionalBlock((conditionalBlock -> {
                            conditionalBlock.condition((condition -> {
                                condition.customExpression("self.bot");
                            })).block((block1 -> {
                                block1.assignment((assignment -> {
                                    assignment.identifier("self.val").rhs("{}");
                                }));
                                block1.assignment((assignment -> {
                                    assignment.identifier("self.top").rhs("False");
                                }));
                            })).type(ConditionalBlock.ConditionBlockType.IF);
                        }));
                        block.simpleStatement((simpleStatement -> {simpleStatement.setStatement(CTOR_SUPER_CALL);}));
                    }));
                    klass.function(LatticePropertyAssignmentHandler.getMeet(type));
                    klass.function(LatticePropertyAssignmentHandler.getPartialOrder(type));
                    klass.function(LatticePropertyAssignmentHandler.getStr(id));
                    klass.function(LatticePropertyAssignmentHandler.getEq());
                });
    }



    /**
     * Returns a Function builder instance given the type
     * 1 - SET_SUPERSET
     * 0 - SET_SUBSET
     * @param type
     * @return Function
     */
    private static Function getPartialOrder(int type){
        String setStr = type == 0 ? LatticePropertyAssignmentHandler.SET_IS_SUBSET_STR:
                LatticePropertyAssignmentHandler.SET_IS_SUPERSET_STR;

        return Function.function((function -> {
            function.name("__lt__").arguments(Arrays.asList("self", "other"))
                    .block((block -> {
                        block.returnStatement((aReturn -> {
                            aReturn.setReturnString(setStr);
                        }));
                    }));
            }));
    }

    /**
     * Returns a Function builder instance given the type
     * 1 - SET_SUPERSET
     * 0 - SET_SUBSET
     * @param type
     * @return Function
     */
    private static Function getMeet(int type){

        String setStr = type == 0 ? LatticePropertyAssignmentHandler.SET_INTERSECT_STR:
                LatticePropertyAssignmentHandler.SET_UNION_STR;

        return Function.function((function -> {
            function.name("meet").arguments(Arrays.asList("self", "other")).block((block -> {
                block.returnStatement((aReturn -> {
                    aReturn.setReturnString(setStr);
                }));
            }));
        }));

    }

    private static Function getStr(String latticeName){
        return Function.function((function -> {
            function.arguments(Arrays.asList("self")).name("__str__").block((block -> {
                block.simpleStatement((simpleStatement -> {
                    simpleStatement.setStatement("vnames = {util.simplifyName(name) for name in self.val}");
                })).returnStatement((aReturn -> {
                    aReturn.setReturnString(String.format("f\"%s({vnames})\"", latticeName));
                }));
            }));
        }));
    }

    private static Function getEq(){
        return Function.function((function -> {
            function.name("__eq__").arguments(Arrays.asList("self", "other")).block(block -> {
                block.simpleStatement((simpleStatement -> {simpleStatement.setStatement("return self.val == other.val");}));
            });
        }));
    }


}
