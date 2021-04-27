import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FlikTest {
    @Test
    public void TestFlik1() {
        Assert.assertTrue(Flik.isSameNumber(128, 128));
    }
    @Test
    public void TestFlik2() {
        Assert.assertTrue(Flik.isSameNumber(127, 127));
    }

}
