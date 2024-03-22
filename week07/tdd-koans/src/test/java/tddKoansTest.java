import app.Greeting;
import app.exceptions.NullException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class tddKoansTest {




    @Test
    void testGreet(){

        String result1= Greeting.greet("Jim");
        System.out.println(result1);

        String result2= Greeting.greet(null);
        System.out.println(result2);

        String result3= Greeting.greet("JIM");
        System.out.println(result3);

        String result4= Greeting.greet(new String[]{"Jack", "Jill"});
        System.out.println(result4);

        String result5= Greeting.greet(new String[]{"Jack", "Jill", "Jim"});
        System.out.println(result5);

        String result6= Greeting.greet(new String[]{"Jack", "JILL", "Jim"});
        System.out.println(result6);

        String result7= Greeting.greet(new String[]{"Jack", "Jeff, Jim"});
        System.out.println(result7);

        String result8= Greeting.greet(new String[]{"Jack", "Jeff", "\"Jim, Jill\""});
        System.out.println(result8);
    }


}
