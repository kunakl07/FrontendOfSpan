package com.span.helper;

import com.span.Main;
import com.span.ast.nodes.BaseAstNode;

import java.util.ArrayList;
import java.util.Arrays;
// NOT THE MOST PERFORMANT CODE BUT GETS THE JOB DONE
public class TreePrinter {
    private BaseAstNode root;
    private ArrayList<Boolean> hasMoreChildren;
    private BaseAstNode lastChild;
    public TreePrinter(BaseAstNode root){
        this.root = root;
        this.hasMoreChildren = new ArrayList<>();
        this.lastChild = this.root.getChildren().get(this.root.getChildren().size() - 1);
        this.hasMoreChildren.add(0, root.getChildren().size() > 1);
    }

    public void walk(){
        System.out.println("\t\t***BEGIN OF TREE WALK***\n\n");
        this.preorder(this.root, 0);
        System.out.println("\n\n\t\t***END OF TREE WALK***\n\n");
    }

    private void preorder(BaseAstNode b, int depth){
        if(b == null)
            return;
        /*if(b.equals(this.lastChild))
            this.hasMoreChildren.add(1, false);
        else*/
            this.hasMoreChildren.add(depth + 1, b.getChildren().size() > 1);
        this.depthPrinter(depth);
        System.out.println(b.toString());
        b.getChildren().forEach(
                (BaseAstNode child)->{
                    preorder(child, depth + 1);
                }
        );

    }

    private void preorderTest(BaseAstNode b, int depth){
        if(b == null)
            return;
        /*if(b.equals(this.lastChild))
            this.hasMoreChildren.add(1, false);
        else*/
        System.out.println(depth+" "+b.toString()+" "+(b.getChildren().size() > 1));
        this.hasMoreChildren.add(depth, b.getChildren().size() > 1);
        this.depthPrinter(depth);
        System.out.println(b.toString());
        b.getChildren().forEach(
                (BaseAstNode child)->{
                    preorderTest(child, depth + 1);
                }
        );
    }


    private void depthPrinter(int depth){
        int i=0;
        while(++i <= depth)
            System.out.print("\t"+(this.hasMoreChildren.get(i) ? "|":""));
        System.out.print((!this.hasMoreChildren.get(depth) ? "|": "")+"---");
    }
}
