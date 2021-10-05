package com.span.pygen.codebuilder;

import java.util.function.Consumer;

public class SimpleStatement extends TabbedUnit {
    private String statement;

    /**
     * Generates a \n separated statement
     * @param simpleStatementConsumer
     * @return
     */
    public static SimpleStatement simpleStatement(Consumer<SimpleStatement> simpleStatementConsumer){
        SimpleStatement s = new SimpleStatement();
        simpleStatementConsumer.accept(s);
        return s;
    }

    private SimpleStatement(){}

    public void setStatement(String statement){
        this.statement = statement;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getTabsFormattedString());
        stringBuilder.append(this.statement);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

}
