package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Sequence {

	@BeforeMethod
	public void bmethod() {
		System.out.println("Before method");
	}

	@BeforeTest
	public void btest() {
		System.out.println("Before Test");
	}

	@Test
	public void f() {
		System.out.println("test");
	}

	@Test
	public void f1() {
		System.out.println("test1");
	}

	@BeforeClass
	public void bclass() {
		System.out.println("Before class");
	}

	@AfterClass
	public void aclass() {
		System.out.println("After class");
	}

	@AfterMethod
	public void amethod() {
		System.out.println("Ater method");
	}

	@AfterTest
	public void Atest() {
		System.out.println("After test");
	}

}
