package com.span;

import com.span.ast.AstGenVisitor;
import com.span.ast.nodes.BaseAstNode;
import com.span.helper.TreePrinter;
import com.span.pygen.emitter.PyEmitter;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Main {
    private final static String USING_DEF_FILE = "Since args was empty using av_expr_short_spec.sfe from resources.";
    public static void main(String args[]) throws IOException {
        InputStream inputStream;
        if(args.length == 0) {
            System.out.println(USING_DEF_FILE);
            inputStream = Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("av_expr_short_spec.sfe"));
        }else {
            inputStream = new FileInputStream(args[0]);
        }

        SpanFeLexer lexer = new SpanFeLexer(CharStreams.fromStream(inputStream));
        SpanFeParser parser = new SpanFeParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.parse();
        AstGenVisitor visitor = new AstGenVisitor();
        BaseAstNode b = visitor.visit(tree);

        TreePrinter t= new TreePrinter(b);
        t.walk();

        System.out.println("\t***Emitting code***");

        PyEmitter pyEmitter = new PyEmitter();
        AstWalker pyAstWalker = new AstWalker(b, pyEmitter);
        pyAstWalker.walk();
        pyEmitter.getCode().forEach(System.out::println);

        System.out.println("\t***End of emit***");
    }



}
