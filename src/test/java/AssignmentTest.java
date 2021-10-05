import com.span.pygen.codebuilder.Assignment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AssignmentTest {
    @Test
    public void testAssignmentToString(){
        Assignment assignment = Assignment.assignment((assignment1 -> {
           assignment1.identifier("hello").rhs("\"world\"");
        }));

        //with tabs
        Assignment assignment2 = Assignment.assignment((assignment1 -> {
            assignment1.identifier("random").rhs("2");
        }));
        assignment2.setNumTabs(1);
        assertArrayEquals(
                new String[]{
                        "hello=\"world\"\n",
                        "    random=2\n"
                }
                ,new String[]{
                assignment.toString(),
                assignment2.toString(),
                }
        );
    }
}
