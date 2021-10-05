package com.span.pygen.codebuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Block extends TabbedUnit{
    private List<TabbedUnit> statements;
    private Block(){
        this.statements = new ArrayList<>();
    }

    public static Block block(Consumer<Block> blockConsumer){
        Block b = new Block();
        blockConsumer.accept(b);
        return b;
    }

    public Block assignment(Consumer<Assignment> assignmentConsumer){
        this.statements.add(Assignment.assignment(assignmentConsumer));
        return this;
    }

    public Block conditionalBlock(Consumer<ConditionalBlock> conditionalBlockConsumer){
        this.statements.add(ConditionalBlock.conditionalBlock(conditionalBlockConsumer));
        return this;
    }

    public Block simpleStatement(Consumer<SimpleStatement> simpleStatementConsumer){
        this.statements.add(SimpleStatement.simpleStatement(simpleStatementConsumer));
        return this;
    }

    public Block returnStatement(Consumer<Return> returnConsumer){
        this.statements.add(Return.returnStatement(returnConsumer));
        return this;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        statements.forEach((statement)->{
            statement.setNumTabs(this.numTabs);
            sb.append(statement.toString());
        });
        return sb.toString();
    }


}
