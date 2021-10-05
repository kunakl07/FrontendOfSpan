import com.span.pygen.codebuilder.Class;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PyGenClassTest {
    @Test
    public void createClass(){
        Class c = Class.newClass((klass)->{
           klass.name("Hello").extendClasses(new String[]{"Object"}).function((function -> {
               function.name("__init__").arguments(Arrays.asList("self")).block((block -> {
                   block.assignment((assignment -> {
                       assignment.identifier("self.hello").rhs("\"world\"");
                   }));
               }));
           }));
        });

        assertEquals(c.toString(), "class Hello(Object):\n" +
                "    def __init__(self):\n" +
                "        self.hello=\"world\"\n\n\n");
    }
}
