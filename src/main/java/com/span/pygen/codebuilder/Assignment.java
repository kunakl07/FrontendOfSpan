package com.span.pygen.codebuilder;

import java.util.function.Consumer;

public class Assignment extends TabbedUnit {
    private String identifier;
    private String rhs;
    private Assignment(){
    }

    public Assignment rhs(String rhs){
        this.rhs = rhs;
        return this;
    }

    public Assignment identifier(String lhs){
        this.identifier = lhs;
        return this;
    }

    public static Assignment assignment(Consumer<Assignment> assignmentConsumer){
        Assignment a = new Assignment();
        assignmentConsumer.accept(a);
        return a;
    }

    public static Assignment assignment(String lhs, String rhs){
        Assignment a = new Assignment();
        a.identifier(lhs).rhs(rhs);
        return a;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getTabsFormattedString());
        stringBuilder.append(this.identifier);
        stringBuilder.append('=');
        stringBuilder.append(this.rhs);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }


}
