import com.span.pygen.codebuilder.ConditionalBlock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConditionalBlockTest {
    @Test
    public void createConditionalBlock(){
        ConditionalBlock c = ConditionalBlock.conditionalBlock((conditionalBlock -> {
            conditionalBlock.type(ConditionalBlock.ConditionBlockType.IF);
            conditionalBlock.condition((condition -> {
                condition.lhs("hello").rhs("world").operation("==");
            }));
            conditionalBlock.or((condition)->{
                condition.lhs("hello").operation("==").rhs("hello");
            });
            conditionalBlock.block(block -> {
                block.returnStatement(ret -> {
                    ret.setReturnString("self.world");
                });
            });
        }));
        assertEquals(c.toString(), "if hello == world or hello == hello:\n" +
                "    return self.world\n");
    }
}
