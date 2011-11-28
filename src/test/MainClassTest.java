package test;

import static org.junit.Assert.*;

import org.junit.Test;
import main.MainClass;

public class MainClassTest {

	@Test
	public void test() {
		MainClass mainClass = new MainClass();
		assert(mainClass.getOne() == 1);
	}

}
