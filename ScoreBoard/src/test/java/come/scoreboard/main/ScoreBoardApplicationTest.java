package come.scoreboard.main;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ScoreBoardApplicationTest extends TestCase {

    /*Creating a test case.*/
    public ScoreBoardApplicationTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ScoreBoardApplicationTest.class);
    }

     public void testApplication() {
        assertTrue(true);
    }

}
