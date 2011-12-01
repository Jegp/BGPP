package test.main;

import static org.junit.Assert.*;

import main.MainClass;

import org.junit.Test;


public class MainClassTest {

	@Test
	public void test() {
		MainClass mainClass = new MainClass();
		assert(mainClass.getOne() == 1);
	}

}
