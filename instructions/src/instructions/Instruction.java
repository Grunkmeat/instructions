package instructions;

abstract class Instruction {}

class LoadConstantInstruction extends Instruction {
	int registerIndex;
	int constantValue;
	
	LoadConstantInstruction(int registerIndex, int constantValue) {
		this.registerIndex = registerIndex;
		this.constantValue = constantValue;
	}
}

class DecrementInstruction extends Instruction {
	int registerIndex;
	
	DecrementInstruction(int registerIndex) {
		this.registerIndex = registerIndex;
	}
}

class JumpIfZeroInstruction extends Instruction {
	int registerIndex;
	int instructionIndex;
	
	JumpIfZeroInstruction(int registerIndex, int instructionIndex) {
		this.registerIndex = registerIndex;
		this.instructionIndex = instructionIndex;
	}
}

class JumpInstruction extends Instruction {
	int instructionIndex;
	
	JumpInstruction(int instructionIndex) {
		this.instructionIndex = instructionIndex;
	}
}

class MultiplyInstruction extends Instruction {
	int registerIndex1;
	int registerIndex2;
	
	MultiplyInstruction(int registerIndex1, int registerIndex2) {
		this.registerIndex1 = registerIndex1;
		this.registerIndex2 = registerIndex2;
	}
	
}

class HaltInstruction extends Instruction {}

class Machine {
	static void execute(int[] registers, Instruction[] instructions) {
		int pc = 0; // program counter
		for (;;) {
			Instruction instruction = instructions[pc];
			if (instruction instanceof LoadConstantInstruction) {
				LoadConstantInstruction lci = (LoadConstantInstruction)instruction;
				registers[lci.registerIndex] = lci.constantValue;
				pc++;
			} else if (instruction instanceof DecrementInstruction) {
				DecrementInstruction di = (DecrementInstruction)instruction;
				registers[di.registerIndex]--;
				pc++;
			} else if (instruction instanceof MultiplyInstruction) {
				MultiplyInstruction mi = (MultiplyInstruction)instruction;
				registers[mi.registerIndex1] *= registers[mi.registerIndex2];
				pc++;
			} else if (instruction instanceof JumpIfZeroInstruction) {
				JumpIfZeroInstruction jizi = (JumpIfZeroInstruction)instruction;
				if (registers[jizi.registerIndex] == 0)
					pc = jizi.instructionIndex;
				else
					pc++;
			} else if (instruction instanceof JumpInstruction) {
				JumpInstruction ji = (JumpInstruction)instruction;
				pc = ji.instructionIndex;
			} else if (instruction instanceof HaltInstruction) {
				break;
			} else
				throw new AssertionError();
		}
	}
}