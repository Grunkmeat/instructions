package instructions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InstructionsTest {

	@Test
	void test() {
		assertEquals(9, power(3, 2));
		assertEquals(1, power(5, 0));
		assertEquals(64, power(4, 3));
	}
	
	/**
	 * @pre | 0 <= y
	 */
	static int power(int x, int y) {
		
		//int result = 1;
		//while (y != 0) {
		//	y--;
		//	result *= x;
		//}
		
		Instruction[] powerProgram = {
				/*0*/ new LoadConstantInstruction(2, 1),
				/*1*/ new JumpIfZeroInstruction(1, 5),
				/*2*/ new DecrementInstruction(1),
				/*3*/ new MultiplyInstruction(2, 0),
				/*4*/ new JumpInstruction(1),
				/*5*/ new HaltInstruction()
				
		};
		
		int[] registers = {x, y, 0};
		Machine.execute(registers, powerProgram);
		return registers[2];
	}

}
