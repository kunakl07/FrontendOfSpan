package com.span.pygen.codebuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Function extends TabbedUnit {
    private String functionName;
    private List<String> arguments;
    private Block block;

    public static Function function(Consumer<Function> functionConsumer){
        Function f = new Function();
        functionConsumer.accept(f);
        return f;
    }

    private Function() {
        this.arguments = new ArrayList<>();
    }

    public Function name(String name){
        this.functionName = name;
        return this;
    }

    public Function arguments(List<String> args){
        this.arguments.addAll(args);
        return this;
    }

    public Function block(Consumer<Block> blockConsumer){
        this.block = Block.block(blockConsumer);
        return this;
    }

    public Block getBlock(){
        return this.block;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getTabsFormattedString());
        sb.append("def ");
        sb.append(this.functionName);
        sb.append("(");
        sb.append(String.join(",", this.arguments));
        sb.append("):\n");

        if(this.block != null) {
            this.block.setNumTabs(this.numTabs + 1);
            sb.append(this.block.toString());
        }

        return sb.toString();
    }
}
