package com.span.pygen.codebuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Class extends TabbedUnit {
    private String className;
    private List<String> extendsList;
    private List<Function> functions;
    private transient String valueType;

    private Class(){
        this.functions = new ArrayList<>();
        this.extendsList = new ArrayList<>();
    };

    public static Class newClass(Consumer<Class> classConsumer){
        Class c =new Class();
        classConsumer.accept(c);
        return c;
    }

    public Class name(String name){
        this.className = name;
        return this;
    }

    public Class setExtendsList(List<String> extendsList){
        this.extendsList.addAll(extendsList);
        return this;
    }

    public Class setValueType(String valueType){
        this.valueType = valueType;
        return this;
    }

    public String getValueType(){
        return this.valueType;
    }

    public Class function(Consumer<Function> functionConsumer){
        this.functions.add(Function.function(functionConsumer));
        return this;
    }
    public Class function(Function function){
        this.functions.add(function);
        return this;
    }

    public Class extendClasses(String []classList){
        this.extendsList.addAll(Arrays.asList(classList));
        return this;
    }

    public List<Function> getFunctions(){
        return this.functions;
    }

    public String getClassName() {
        return className;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.getTabsFormattedString());
        sb.append("class ");
        sb.append(this.className);
        sb.append("(");
        sb.append(String.join(",", this.extendsList));
        sb.append("):\n");
        this.functions.forEach((function)->{
            function.setNumTabs(this.numTabs+1);
            sb.append(function.toString()+"\n\n");
        });
        return sb.toString();
    }

}
