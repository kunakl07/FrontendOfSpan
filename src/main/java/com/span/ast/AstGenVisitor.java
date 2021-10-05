package com.span.ast;

import com.span.SpanFeBaseVisitor;
import com.span.SpanFeParser;
import com.span.ast.nodes.*;
import com.span.helper.TreeTrimmer;

public class AstGenVisitor extends SpanFeBaseVisitor<BaseAstNode> {

    @Override
    public BaseAstNode visitParse(SpanFeParser.ParseContext ctx) {
        BaseAstNode root = new BaseAstNode();
        if (ctx.statement() != null) {
            ctx.statement().forEach((statementContext -> root.addChild(this.visit(statementContext))));
        }
        return root;
    }

    @Override
    public BaseAstNode visitStatement(SpanFeParser.StatementContext ctx) {

        if (ctx.varDecl() != null) {
            return this.visit(ctx.varDecl());
        } else if (ctx.assignment() != null) {
            return this.visit(ctx.assignment());
        } else if (ctx.transferFunctionDef() != null)
            return this.visit(ctx.transferFunctionDef());
        return new BaseAstNode();
    }

    @Override
    public BaseAstNode visitVarDecl(SpanFeParser.VarDeclContext ctx) {
        return new ClassDeclarationStatementNode(ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(), ctx.vdIdList, ctx.vdType.getText());
    }

    @Override
    public BaseAstNode visitLatticePropertyAssignment(SpanFeParser.LatticePropertyAssignmentContext ctx) {
        /**
         * If assignmentRhs is null it means this is a LatticeCtorAssignment
         * something like this
         * LiveL.top =   LiveL(allVar)
         * LiveL.bot =   LiveL(EmptySet)
         */
        LatticePropertyAssignmentNode node = null;
        if (ctx.assignmentRhs == null) {
            node = new LatticePropertyAssignmentNode(ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine(), ctx.assignmentLhs.getText(),
                    ctx.latticeProperty.getText());
            node.addChild(this.visit(ctx.functionCall()));
        } else {
            node = new LatticePropertyAssignmentNode(ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine(), ctx.assignmentLhs.getText(),
                    ctx.latticeProperty.getText(), ctx.assignmentRhs.getText());
        }
        return node;
    }

    @Override
    public BaseAstNode visitComponentLatticeAssignment(SpanFeParser.ComponentLatticeAssignmentContext ctx) {
        ComponentLatticeAssignmentNode node = new ComponentLatticeAssignmentNode(ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(), ctx.Identifier().getText());
        BaseAstNode childDef = this.visit(ctx.componentLatticeCtorDef());
        node.addChild(childDef);
        // removes skews from the tree
        TreeTrimmer.trimNodes(childDef);
        TreeTrimmer.trimNodes(node);
        return node;
    }

    @Override
    public BaseAstNode visitOverallLatticeAssignment(SpanFeParser.OverallLatticeAssignmentContext ctx) {
        OverallLatticeAssignmentNode node = new OverallLatticeAssignmentNode(ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(), ctx.Identifier().getText());
        OverallLatticeCtorNode ctorNode = OverallLatticeCtorNode.fromFunctionCall((FunctionCallNode) this.visit(ctx.functionCall()));
        node.addChild(ctorNode);
        return node;
    }

    @Override
    public BaseAstNode visitExpressionAssignment(SpanFeParser.ExpressionAssignmentContext ctx) {
        ExpressionAssignmentNode node = new ExpressionAssignmentNode(ctx.getStart().getLine(), ctx.getStart().getLine(),
                ctx.assignmentLhs.getText());
        node.addChild(this.visit(ctx.expression()));
        return node;
    }

    @Override
    public BaseAstNode visitComponentLatticeCtorDef(SpanFeParser.ComponentLatticeCtorDefContext ctx) {
        ComponentLatticeCtorDefNode node = null;
        if (ctx.componentLatticeCtorDefValueType != null) {
            node = new ComponentLatticeCtorDefNode(ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine(), ctx.Identifier().getText(),
                    ctx.componentLatticeCtorDefValueType.getText());
        } else {
            node = new ComponentLatticeCtorDefNode(ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine(), ctx.Identifier().getText());
        }

        // the remaining args
        if (ctx.componentLatticeCtorDef() != null) {
            BaseAstNode child = this.visit(ctx.componentLatticeCtorDef());
            TreeTrimmer.trimNodes(child);
            node.addChild(child);
        }
        return node;
    }

    @Override
    public BaseAstNode visitFunctionCall(SpanFeParser.FunctionCallContext ctx) {
        /**
         * Strings along the lines of
         * LiveL(SetOf(Var))
         * are expected here
         * Anything between () is argsList
         */
        FunctionCallNode node = new FunctionCallNode(ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(), ctx.functionCallIdentifier.getText());
        if (ctx.argsList() != null) {
            ArgsListNode argsListNode = new ArgsListNode();
            BaseAstNode childContainingAllArgs = this.visit(ctx.argsList());
            argsListNode.addChild(childContainingAllArgs);
            TreeTrimmer.trimNodes(argsListNode);
            node.addChild(argsListNode);

        }
        return node;
    }


    @Override
    public BaseAstNode visitArgsList(SpanFeParser.ArgsListContext ctx) {
        BaseAstNode parent = null;
        // at any given moment only 1 is going to be non null
        if (ctx.Identifier() != null) {
            if (ctx.latticeProperty != null) {
                parent = new LatticeIdentifierArgNode(ctx.getStart().getLine(),
                        ctx.getStart().getCharPositionInLine(), ctx.Identifier().getText(),
                        ctx.latticeProperty.getText());
            } else {
                parent = new IdentifierArgumentNode(ctx.getStart().getLine(),
                        ctx.getStart().getCharPositionInLine(), ctx.Identifier().getText());
            }
        } else if (ctx.functionCall() != null) {

            parent = this.visit(ctx.functionCall());

        } else if (ctx.valueTypesPlaceholder() != null) {

            parent = this.visit(ctx.valueTypesPlaceholder());

        } else if (ctx.entityTypesPlaceholder() != null) {

            parent = this.visit(ctx.entityTypesPlaceholder());

        } else if (ctx.dfvVar != null) {
            parent = new DfvNode(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
                    DfvNode.fromStr(ctx.dfvVar.getText()));
        }

        if (ctx.argsList() != null) {
            final BaseAstNode parentRef = parent;
            ctx.argsList().forEach((argsListContext -> parentRef.addChild(this.visit(argsListContext))));
            if (parent instanceof IdentifierArgumentNode) {
                TreeTrimmer.trimNodes(parent);
            }
        }
        return parent;
    }

    @Override
    public BaseAstNode visitValueTypesPlaceholder(SpanFeParser.ValueTypesPlaceholderContext ctx) {
        return new GeneralTypePlaceholderNode(ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(), ctx.valueTypeValue.getText());
    }

    @Override
    public BaseAstNode visitEntityTypesPlaceholder(SpanFeParser.EntityTypesPlaceholderContext ctx) {
        return new GeneralTypePlaceholderNode(ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(), ctx.entityTypeValue.getText());
    }

    @Override
    public BaseAstNode visitContainerTypesPlaceholder(SpanFeParser.ContainerTypesPlaceholderContext ctx) {
        return new GeneralTypePlaceholderNode(ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(), ctx.containerTypeValue.getText());
    }


    @Override
    public BaseAstNode visitTransferFunctionDef(SpanFeParser.TransferFunctionDefContext ctx) {
        TransferFunctionDefNode defNode = new TransferFunctionDefNode(ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                ctx.Identifier().getText()
        );
        ctx.transferFunctionBody().forEach((transferFunctionBodyContext -> {
            defNode.addChild(this.visit(transferFunctionBodyContext));
        }));
        return defNode;
    }

    @Override
    public BaseAstNode visitTransferFunctionBody(SpanFeParser.TransferFunctionBodyContext ctx) {
        final TransferFunctionBodyNode n = new TransferFunctionBodyNode(ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                ctx.dataFlowVar.getText(),
                "temp");
        if (ctx.assignment() != null) {
            ctx.assignment().forEach((assignmentContext -> {
                n.addChild(AstGenVisitor.this.visit(assignmentContext));
            }));
        }
        ctx.expression().forEach((expressionContext -> {
            n.addChild(AstGenVisitor.this.visit(expressionContext));
        }));
        return n;
    }


    @Override
    public BaseAstNode visitExpression(SpanFeParser.ExpressionContext ctx) {
        // todo wrap in expression node ?
        if (ctx.setComprehension() != null)
            return this.visit(ctx.setComprehension());
        else if (ctx.conditionalExpression() != null)
            return this.visit(ctx.conditionalExpression());
        else if (ctx.iterableExpression() != null)
            return this.visit(ctx.iterableExpression());
        else if (ctx.functionCall() != null)
            return this.visit(ctx.functionCall());
        return super.visitExpression(ctx);
    }

    @Override
    public BaseAstNode visitIterableExpression(SpanFeParser.IterableExpressionContext ctx) {
        // expression of the type a <- allExpr or a <- allNumVar etc
        String allVal = "";
        if (ctx.allTypeValue != null)
            allVal = ctx.allTypeValue.getText();

        IterableExpressionNode i = new IterableExpressionNode(ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                ctx.Identifier().getText(),
                allVal
        );
        if (ctx.functionCall() != null)
            i.addChild(this.visit(ctx.functionCall()));
        return i;
    }

    @Override
    public BaseAstNode visitConditionalExpression(SpanFeParser.ConditionalExpressionContext ctx) {
        // expression of the type a > b, a in b, not a in b etc
        String rhs = "";
        if (ctx.functionCall() == null)
            rhs = ctx.rhs.getText();

        boolean not = false;
        if (ctx.Not() != null)
            not = true;

        ConditionalExpressionNode c = new ConditionalExpressionNode(ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                ctx.lhs.getText(),
                rhs,
                ctx.relationalOperator.getText(),
                not
        );

        if (ctx.functionCall() != null)
            c.addChild(this.visit(ctx.functionCall()));

        return c;
    }

    @Override
    public BaseAstNode visitSetComprehension(SpanFeParser.SetComprehensionContext ctx) {
        SetComprehensionNode s = new SetComprehensionNode(ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                ctx.Identifier().getText()
        );
        if (ctx.iterableExpression() != null) {
            s.addChild(this.visit(ctx.iterableExpression()));
        }
        s.addChild(this.visit(ctx.conditionalExpression()));
        return s;
    }
}
