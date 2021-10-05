package com.span.pygen.codebuilder;

import java.util.function.Consumer;

public class Return extends TabbedUnit {
    public String returnString;

    public static Return returnStatement(Consumer<Return> returnConsumer){
        Return r = new Return();
        returnConsumer.accept(r);
        return r;
    }

    public void setReturnString(String returnString){
        this.returnString = returnString;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getTabsFormattedString());
        stringBuilder.append("return ");
        stringBuilder.append(this.returnString);
        return stringBuilder.toString();
    }
}
