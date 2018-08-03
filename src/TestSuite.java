import static org.testng.Assert.assertEquals;

import org.testng.annotations.*;

public class TestSuite {

	private TheMaths maths;
	
	@BeforeClass
	public void beforeSuite() {
		maths = new TheMaths();
	}	
	
	@Test
	public void TestAdd() {
		assertEquals(maths.Add(2, 3), 5);
		assertEquals(maths.Add(2, -3), -1);
	}	
}
