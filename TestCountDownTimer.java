package project1;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ConcurrentModificationException;
import java.util.concurrent.CountedCompleter;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.plaf.IconUIResource;

public class TestCountDownTimer {
	// Test Constructor
	@Test
	public void testDefaultConstructor() {
		CountDownTimer s = new CountDownTimer();
		assertEquals(0,s.getHours());
		assertEquals(0,s.getMinutes());
		assertEquals(0,s.getSeconds());
	}

	@Test
	public void testConstructor3Parameters() {
		CountDownTimer s = new CountDownTimer(0, 0, 0);
		assertTrue(s.getHours() == 0);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);

		s = new CountDownTimer(2, 3, 4);
		assertTrue(s.getHours() == 2);
		assertTrue(s.getMinutes() == 3);
		assertTrue(s.getSeconds() == 4);
	}

	// Testing for an exception; can only test for 1 at a time;
	// no lines of code after "new CountDownTimer(-2, 3, 4);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegHour() {
		new CountDownTimer(-2, 3, 4);
	}

	// Testing for an exception; can only test for 1 at a time;
	// no lines of code after "new CountDownTimer(2, -3, 4);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegMinute() {
		new CountDownTimer(2, -3, 4);
	}

	// Testing for an exception; can only test for 1 at a time;
	// no lines of code after "new CountDownTimer(2, 3, -4);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3ParametersNegSecond() {
		new CountDownTimer(2, 3, -4);
	}

	// Testing for exceptions; testing all 3 at the same time
	@Test
	public void testConstructor3ParametersNegInput() {
		try {
			new CountDownTimer(-2, 3, 4);
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}

		try {
			new CountDownTimer(2, -3, 4);
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}

		try {
			new CountDownTimer(2, 3, -4);
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}

	// Testing for an exception; no lines of code after
	// "new CountDownTimer(12,67,14);" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3LargeMinute() {
		new CountDownTimer(12, 60, 14);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructor3LargeSecond() {
		new CountDownTimer(12, 59, 60);
	}

	// Testing for an exception; no lines of code after
	// "new CountDownTimer("a");" will be run
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorString1ParameterAlpha() {
		new CountDownTimer("a");
	}
	//_____________________________________

	// Testing for Hours = 0; No exception
	@Test
	public void testConstructorTwoParameters() {
		CountDownTimer s = new CountDownTimer(3, 4);
		assertEquals(0, s.getHours());
		assertEquals(3, s.getMinutes());
		assertEquals(4, s.getSeconds());
	}
	// Testing for Exception
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorTwoParametersMinNeg() {
		new CountDownTimer(-3,4);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorTwoParametersSecNeg() {
		new CountDownTimer(3,-5);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorTwoParametersTwoNeg() {
		new CountDownTimer(-3,-4);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorTwoParametersLargerMin() { new CountDownTimer(60,4); }
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorTwoParametersLargerSec() {new CountDownTimer(7,61);}
	//___________________________________

	// Testing for hours = 0, minutes = 0
	@Test
	public void testConstructorOneParameters() {
		CountDownTimer s = new CountDownTimer(5);
		assertEquals(0, s.getHours());
		assertEquals(0, s.getMinutes());
		assertEquals(5, s.getSeconds());
	}
	// Testing for Exception
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorOneParametersNeg() {new CountDownTimer(-4);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorOneParametersLarge() {new CountDownTimer(67);
	}

	//_______________________________
	// Testing for CountDownTimer(CountDownTimer other)
	@Test
	public void testObjectOther1() {
		CountDownTimer s1 = new CountDownTimer(3,56,17);
		CountDownTimer s2 = new CountDownTimer(s1);
		assertEquals(s1.getHours(), s2.getHours());
		assertEquals(s1.getMinutes(), s2.getMinutes());
		assertEquals(s1.getSeconds(), s2.getSeconds());
	}

	@Test
	public void testObjectOther2() {
		CountDownTimer s1 = new CountDownTimer(56,17);
		CountDownTimer s2 = new CountDownTimer(s1);
		assertTrue(s2.getHours()==0);
		assertEquals(s1.getMinutes(), s2.getMinutes());
		assertEquals(s1.getSeconds(), s2.getSeconds());
	}
	// Test for exception
	@Test (expected = IllegalArgumentException.class)
	public void testObjectOtherNull() {
		CountDownTimer s1 = null;
		CountDownTimer other = new CountDownTimer(s1);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testObjectOtherNegative() {
		CountDownTimer s1 = new CountDownTimer(-1,2);
		CountDownTimer other = new CountDownTimer(s1);
	}

	// Testing startTime
	@Test
	public void testStartTime() {
		CountDownTimer s = new CountDownTimer("30");
		assertTrue(s.getSeconds()==30);
	}
	@Test
	public void testStartTime1() {
		CountDownTimer s = new CountDownTimer("14:30");
		assertTrue(s.getMinutes()==14);
		assertTrue(s.getSeconds()==30);
	}
	@Test
	public void testStartTime2() {
		CountDownTimer s = new CountDownTimer("14:30:59");
		assertTrue(s.getHours()==14);
		assertTrue(s.getMinutes()==30);
		assertTrue(s.getSeconds()==59);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testStartTime3() {new CountDownTimer("62");}

	@Test (expected = IllegalArgumentException.class)
	public void testStartTimeNull() {
		String start = null;
		new CountDownTimer(start);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testStartTime5() {new CountDownTimer("1:-61");}

	@Test (expected = IllegalArgumentException.class)
	public void testStartTime4() {new CountDownTimer("-1:61:78");}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStringLarge() {
		new CountDownTimer("1:23:45:678");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testWord() {new CountDownTimer("ab:sc");}

	@Test (expected = IllegalArgumentException.class)
	public void testStarts() {new CountDownTimer("**:**:8");}


	// Testing for method SUBTRACTS sub(seconds)
	@Test
	public void testSub1() {
		CountDownTimer s = new CountDownTimer(0,2,2);
		s.sub(1);
		assertEquals(0, s.getHours());
		assertEquals(2, s.getMinutes());
		assertEquals(1,s.getSeconds());
	}
	// Test to see if minutes change
	@Test
	public void testSub10() {
		CountDownTimer s = new CountDownTimer(0,2,10);
		s.sub(11);
		assertEquals(0, s.getHours());
		assertEquals(1, s.getMinutes());
		assertEquals(59,s.getSeconds());
	}
	//Test with large seconds
	@Test
	public void testSub120() {
		CountDownTimer s = new CountDownTimer(2,5,0);
		s.sub(120);
		assertEquals(2, s.getHours());
		assertEquals(3, s.getMinutes());
		assertEquals(0,s.getSeconds());
	}
	@Test
	public void testSubIntTo0MinutesAndSeconds() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);

		// sub to 0 min, 0 sec
		s.sub(3599);
		assertEquals(s.getHours(), 1);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 0);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorSubNegSec() {
		CountDownTimer s = new CountDownTimer();
		s.sub(-1);
	}

	//Testing for method INCREMENT inc()
	// Change minute
	@Test
	public void testInc() {
		CountDownTimer s = new CountDownTimer(1, 2, 59);
		s.inc();
		assertEquals(1, s.getHours());
		assertEquals(3, s.getMinutes());
		assertEquals(0, s.getSeconds());
	}
	// Normal
	@Test
	public void testInc2() {
		CountDownTimer s = new CountDownTimer(1, 2, 58);
		s.inc();
		assertEquals(1, s.getHours());
		assertEquals(2, s.getMinutes());
		assertEquals(59, s.getSeconds());
	}
	@Test
	public void testInc3() {
		CountDownTimer s = new CountDownTimer(1, 2, 0);
		s.inc();
		assertEquals(1, s.getHours());
		assertEquals(2, s.getMinutes());
		assertEquals(1, s.getSeconds());
	}
	// Change hour
	@Test
	public void testInc4() {
		CountDownTimer s = new CountDownTimer(1,59,59);
		s.inc();
		assertEquals(2,s.getHours());
		assertEquals(0,s.getMinutes());
		assertEquals(0,s.getSeconds());
	}

	// Testing for method ADD add(seconds)
	@Test
	public void testAdd1() {
		CountDownTimer s = new CountDownTimer();
		s.add(1);
		assertEquals(0,s.getHours());
		assertEquals(0,s.getMinutes());
		assertEquals(1,s.getSeconds());
	}
	@Test
	public void testAdd10() {
		CountDownTimer s = new CountDownTimer();

		s.add(10);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 10);
	}
	@Test
	public void testAdd59() {
		CountDownTimer s = new CountDownTimer();

		s.add(59);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 0);
		assertEquals(s.getSeconds(), 59);
	}
	// Change minute
	@Test
	public void testAdd60() {
		CountDownTimer s = new CountDownTimer();

		// inc to 1 min
		s.add(60);
		assertEquals(s.getHours(), 0);
		assertEquals(s.getMinutes(), 1);
		assertEquals(s.getSeconds(), 0);
	}
	// Test with Exception Negative Seconds
	@Test (expected = IllegalArgumentException.class)
	public void testAddNegSec() {
		CountDownTimer s = new CountDownTimer();
		s.add(-1);
	}

	// Testing for method DECREMENT dec()
	@Test
	public void testDec1Second() {
		CountDownTimer s = new CountDownTimer(1, 59, 59);
		s.dec();
		assertEquals(1, s.getHours());
		assertEquals(59, s.getMinutes());
		assertEquals(58, s.getSeconds());
	}
	@Test
	public void testDec2() {
		CountDownTimer s = new CountDownTimer(1, 59, 1);
		s.dec();
		assertEquals(1, s.getHours());
		assertEquals(59, s.getMinutes());
		assertEquals(0, s.getSeconds());
	}
	// Change minute
	@Test
	public void testDec3() {
		CountDownTimer s = new CountDownTimer(1, 56, 0);
		s.dec();
		assertEquals(1, s.getHours());
		assertEquals(55, s.getMinutes());
		assertEquals(59, s.getSeconds());
	}
	// Change hour
	@Test
	public void testDec4() {
		CountDownTimer s = new CountDownTimer(2,0,0);
		s.dec();
		assertEquals(1, s.getHours());
		assertEquals(59, s.getMinutes());
		assertEquals(59, s.getSeconds());
	}
	@Test (expected = IllegalArgumentException.class)
	public void testDecNeg() {
		CountDownTimer s = new CountDownTimer();
		s.setHours(-1);
		s.dec();
	}

	// Testing for method toString
	@Test
	public void testToString() {
		CountDownTimer s = new CountDownTimer(1,10,18);
		assertEquals("1:10:18", s.toString());
	}
	@Test
	public void testToString1() {
		CountDownTimer s = new CountDownTimer(10,1,18);
		assertEquals("10:01:18", s.toString());
	}
	@Test
	public void testToString2() {
		CountDownTimer s = new CountDownTimer(1,10,8);
		assertEquals("1:10:08", s.toString());
	}
	@Test
	public void testToString3() {
		CountDownTimer s = new CountDownTimer(4,4,8);
		assertEquals("4:04:08", s.toString());
	}
	// Testing for EQUALS method
	@Test
	public void testEquals(){
		CountDownTimer s1 = new CountDownTimer(4, 59, 30);
		CountDownTimer s2 = new CountDownTimer(6, 1, 20);
		CountDownTimer s3 = new CountDownTimer(4, 59, 30);
		CountDownTimer s4 = new CountDownTimer(5, 59, 20);
		CountDownTimer s5 = new CountDownTimer(6, 1, 20);
		CountDownTimer s6 = new CountDownTimer(4, 58, 30);
		assertFalse(s1.equals(s2));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertTrue(s1.equals(s3));

		assertFalse(CountDownTimer.equals(s1,s2));
		assertFalse(CountDownTimer.equals(s1,s4));
		assertFalse(CountDownTimer.equals(s5,s1));
		assertTrue(CountDownTimer.equals(s3,s1));
		assertTrue(CountDownTimer.equals(s2,s5));
	}
	// Test for Exception
	@Test (expected = IllegalArgumentException.class)
	public void testEqualsNull() {
		CountDownTimer s = new CountDownTimer(1,2,34);
		s.equals(null);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testEqualsNull1() {
		CountDownTimer.equals(null,null);
	}


	// Testing for methods compareTo(CountDownTimer other)
	@Test
	public void testCompareTo() {
		CountDownTimer s1 = new CountDownTimer(4,2,1);
		CountDownTimer s2 = new CountDownTimer(4,2,1);
		CountDownTimer s3 = new CountDownTimer(3,1,1);
		CountDownTimer s4 = new CountDownTimer(5,6,7);
		assertEquals(0,s1.compareTo(s2));
		assertEquals(-1,s3.compareTo(s1));
		assertEquals(1,s4.compareTo(s3));
		assertEquals(-1,s3.compareTo(s4));

		assertEquals(0,CountDownTimer.compareTo(s1,s2));
		assertEquals(-1,CountDownTimer.compareTo(s3,s1));
		assertEquals(1,CountDownTimer.compareTo(s4,s3));
		assertEquals(-1,CountDownTimer.compareTo(s3,s4));

	}
	// Testing for EXCEPTION
	@Test (expected = IllegalArgumentException.class)
	public void testCompareToString() {
		CountDownTimer s = new CountDownTimer(1,2,3);
		CountDownTimer s1 = new CountDownTimer("abc");
		s1.compareTo(s);
		CountDownTimer.compareTo(s,s1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testCompareToNull() {
		CountDownTimer s = new CountDownTimer(1,2,3);
		s.compareTo(null);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testCompareToNull2() {
		CountDownTimer s = new CountDownTimer(1,2,3);
		CountDownTimer.compareTo(s,null);
	}


	// Testing method add(Object other)
	@Test
	public void testAddOther() {
		CountDownTimer s = new CountDownTimer(0,0,1);
		s.add(new CountDownTimer(0,0,1));
		assertEquals(0, s.getHours());
		assertEquals(0, s.getMinutes());
		assertEquals(2,s.getSeconds());
	}

	@Test
	public void testAddOther1() {
		CountDownTimer s = new CountDownTimer(6,7,59);
		s.add(new CountDownTimer(3,0,1));
		assertEquals(9, s.getHours());
		assertEquals(8, s.getMinutes());
		assertEquals(0,s.getSeconds());
	}

	@Test
	public void testAddOther2() {
		CountDownTimer s = new CountDownTimer(21,15);
		s.add(new CountDownTimer(0,38,45));
		assertEquals(1, s.getHours());
		assertEquals(0, s.getMinutes());
		assertEquals(0,s.getSeconds());
	}
	// Test for exception
	@Test (expected = IllegalArgumentException.class)
	public void testAddOtherNeg() {
		CountDownTimer s = new CountDownTimer(21,15);
		s.add(new CountDownTimer(-1));
	}
	@Test (expected = IllegalArgumentException.class)
	public void testAddOtherNull() {
		CountDownTimer s = new CountDownTimer(21,15);
		s.add(null);
	}

	//Testing method sub(Object other)
	@Test
	public void testSubOther() {
		CountDownTimer s = new CountDownTimer(2,3,4);
		s.sub(new CountDownTimer(3,4));
		assertEquals(2, s.getHours());
		assertEquals(0, s.getMinutes());
		assertEquals(0,s.getSeconds());
	}

	@Test
	public void testSubOther1() {
		CountDownTimer s = new CountDownTimer(2,3,4);
		s.sub(new CountDownTimer(2,3,4));
		assertEquals(0, s.getHours());
		assertEquals(0, s.getMinutes());
		assertEquals(0,s.getSeconds());
	}

	@Test
	public void testSubOther2() {
		CountDownTimer s = new CountDownTimer(3,59,4);
		s.sub(new CountDownTimer(2,59,5));
		assertEquals(0, s.getHours());
		assertEquals(59, s.getMinutes());
		assertEquals(59,s.getSeconds());
	}
// Test for exception
	@Test (expected = IllegalArgumentException.class)
	public void testSubOtherNull() {
		CountDownTimer s = new CountDownTimer(21,15);
		s.sub(null);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSubOtherNeg() {
		CountDownTimer s = new CountDownTimer(1,21,15);
		s.sub(new CountDownTimer(1,22,12));
	}

	// Test File
	@Test
	public void testFiles1() {
		CountDownTimer s = new CountDownTimer(1,23,42);
		s.save("testfile.txt");
		s.load("testfile.txt");
		assertEquals(1, s.getHours());
		assertEquals(23,s.getMinutes());
		assertEquals(42,s.getSeconds());
	}

	@Test
	public void testFiles2() {
		CountDownTimer s = new CountDownTimer(23,42);
		s.save("testfile.txt");
		s.load("testfile.txt");
		assertEquals(0, s.getHours());
		assertEquals(23,s.getMinutes());
		assertEquals(42,s.getSeconds());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testFileError() {
		CountDownTimer s = new CountDownTimer(0,0,-1);
		s.save("testfile.txt");
		s.load("testfile.txt");
	}
//	@Test (expected = RuntimeException.class)
//	public void testFileError1() {
//		CountDownTimer s = new CountDownTimer(1,1,3);
//		s.save("testfile.txt");
//		s.load("testfile4.txt");
//	}


	@Test
	public void testSuspended(){
		CountDownTimer s = new CountDownTimer(1,2,3);
		assertEquals(false, s.isSuspended());
	}
	@Test
	public void testSuspended2(){
		CountDownTimer s = new CountDownTimer(1,2,3);
		CountDownTimer.setSuspended(true);
		assertEquals(true, s.isSuspended());
	}
	@Test
	public void testSetMinutes() {
		CountDownTimer s = new CountDownTimer();
		s.setMinutes(2);
		assertTrue(s.getMinutes()==2);
	}

	@Test
	public void testSetSeconds() {
		CountDownTimer s = new CountDownTimer();
		s.setSeconds(2);
		assertTrue(s.getSeconds()==2);
	}

	@Test
	public void testTotalTime() {
		CountDownTimer s = new CountDownTimer(1,2,1);
		assertEquals(3721, s.totalTime());
	}

}
