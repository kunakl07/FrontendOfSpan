package com.span.pygen.codebuilder;

public class TabbedUnit {
    private static final int DEFAULT = 0;
    protected int numTabs;


    public TabbedUnit(){
        this.numTabs = DEFAULT;
    }

    public void setNumTabs(int numTabs){
        this.numTabs = numTabs;
    }

    public int getNumTabs(){
        return this.numTabs;
    }

    public String getTabsFormattedString(){
        return this.numTabs == 0 ? "" : String.format("%0"+(this.numTabs << 2)+"d", 0).replace("0", " ");
    }
}
