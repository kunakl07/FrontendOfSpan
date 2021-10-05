package com.span.pygen.codebuilder;

public class Condition extends TabbedUnit {
    private String lhs;
    private String rhs;
    private String operation;

    public Condition lhs(String lhs){
        this.lhs = lhs;
        return this;
    }

    public Condition rhs(String rhs){
        this.rhs = rhs;
        return this;
    }

    public Condition operation(String operation){
        this.operation = operation;
        return this;
    }

    public Condition justTrue(String prepend){
        this.lhs = (prepend == null? "": prepend ) +"True";
        this.rhs = this.operation = "";
        return this;
    }

    public Condition justTrue(){
        return this.justTrue("");
    }

    public Condition justFalse(String prepend){
        this.lhs = (prepend == null? "": prepend ) + "False";
        this.rhs = this.operation = "";
        return this;
    }

    public Condition customExpression(String expr){
        this.lhs = expr;
        this.rhs=this.operation = "";
        return this;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.lhs);
        sb.append(" "+this.operation+" ");
        sb.append(this.rhs);
        return sb.toString();
    }
}
