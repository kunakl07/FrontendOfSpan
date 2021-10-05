package com.span.pygen.codebuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * A class that maps to If, else and elif
 */
public class ConditionalBlock extends TabbedUnit {
    private String type;
    private List<Condition> and;
    private List<Condition> or;
    private Condition baseCondition;
    private Block block;
    private static HashMap<ConditionBlockType, String> enumMap;

    public static ConditionalBlock conditionalBlock(Consumer<ConditionalBlock> conditionalBlockConsumer){
        ConditionalBlock c = new ConditionalBlock();
        conditionalBlockConsumer.accept(c);
        return c;
    }

    static{
        enumMap = new HashMap<>();
        enumMap.put(ConditionBlockType.IF, "if ");
        enumMap.put(ConditionBlockType.ELSEIF, "elif ");
        enumMap.put(ConditionBlockType.ELSE, "else ");
    }

    public ConditionalBlock(){
        this.and = new ArrayList<>();
        this.or = new ArrayList<>();
    }

    public void type(ConditionBlockType blockType){
        this.type = ConditionalBlock.enumMap.get(blockType);
    }

    public ConditionalBlock condition(Consumer<Condition> conditionConsumer){
        Condition c = new Condition();
        this.baseCondition = c;
        conditionConsumer.accept(c);
        return this;
    }

    public ConditionalBlock and(Consumer<Condition> conditionConsumer) {
        Condition c = new Condition();
        this.and.add(c);
        conditionConsumer.accept(c);
        return this;
    }

    public ConditionalBlock or(Consumer<Condition> conditionConsumer) {
        Condition c = new Condition();
        this.or.add(c);
        conditionConsumer.accept(c);
        return this;
    }

    public ConditionalBlock block(Consumer<Block> blockConsumer){
        this.block = Block.block(blockConsumer);
        return this;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTabsFormattedString());
        sb.append(this.type);
        sb.append(this.baseCondition);
        this.and.forEach((andCondition)->{
            sb.append(" and "+andCondition.toString());
        });
        this.or.forEach((orCondition)->{
            sb.append(" or "+orCondition.toString());
        });
        sb.append(":\n");
        if(this.block != null) {
            this.block.setNumTabs(this.numTabs + 1);
            sb.append(this.block.toString());
        }
        sb.append("\n");
        return sb.toString();
    }

    public enum ConditionBlockType{
        IF, ELSEIF, ELSE;
    }

}
