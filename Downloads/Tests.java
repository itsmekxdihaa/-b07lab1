import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Tests {
	@Test
		void PentagonEquilateral() {
			Point a = new Point(1,1);
			Point b = new Point(1,1);
			Point c = new Point(1,1);
			Point d = new Point(1,1);
			Point e = new Point(1,1);
			
			Pentagon p = new Pentagon(a,b,c,d,e);
			assertTrue(p.isEquilateral());
		}
		
		void PentagonNotEquilateral() {
			Point a = new Point(1,2);
			Point b = new Point(1,5);
			Point c = new Point(1,9);
			Point d = new Point(1,10);
			Point e = new Point(1,12);
			
			Pentagon p = new Pentagon(a,b,c,d,e);
			assertFalse(p.isEquilateral());
		}
		
		@Test
		void PentagonPerimeterZero() {
			Point a = new Point(1,1);
			Point b = new Point(1,1);
			Point c = new Point(1,1);
			Point d = new Point(1,1);
			Point e = new Point(1,1);
			
			Pentagon p = new Pentagon(a,b,c,d,e);
			assertEquals(0, p.perimeter());
		}
		
		@Test
		void PentagonPeriNotZero() {
			Point a = new Point(1,1);
			Point b = new Point(1,2);
			Point c = new Point(1,3);
			Point d = new Point(1,4);
			Point e = new Point(1,5);
			
			Pentagon p = new Pentagon(a,b,c,d,e);
			assertEquals(8, p.perimeter());
		}
	
}
