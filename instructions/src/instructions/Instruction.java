package instructions;

abstract class Instruction {}

class LoadConstantInstruction extends Instruction {
	int registerIndex;
	int constantValue;
	
	LoadConstantInstruction(int registerIndex, int constantValue) {
		this.registerIndex = registerIndex;
		this.constantValue = constantValue;
	}
	
	void execute(Machine machine) {
		machine.registers[registerIndex] = constantValue;
		machine.pc++;
	}
}

class DecrementInstruction extends Instruction {
	int registerIndex;
	
	DecrementInstruction(int registerIndex) {
		this.registerIndex = registerIndex;
	}
	
	void execute(Machine machine) {
		machine.registers[registerIndex]--;
		machine.pc++;
	}
}

class JumpIfZeroInstruction extends Instruction {
	int registerIndex;
	int instructionIndex;
	
	JumpIfZeroInstruction(int registerIndex, int instructionIndex) {
		this.registerIndex = registerIndex;
		this.instructionIndex = instructionIndex;
	}
	
	void execute(Machine machine) {
		if (machine.registers[registerIndex] == 0)
			machine.pc = instructionIndex;
		else
			machine.pc++;
	}
}

class JumpInstruction extends Instruction {
	int instructionIndex;
	
	JumpInstruction(int instructionIndex) {
		this.instructionIndex = instructionIndex;
	}
	
	void execute(Machine machine) {
		machine.pc = instructionIndex;
	}
}

class MultiplyInstruction extends Instruction {
	int registerIndex1;
	int registerIndex2;
	
	MultiplyInstruction(int registerIndex1, int registerIndex2) {
		this.registerIndex1 = registerIndex1;
		this.registerIndex2 = registerIndex2;
	}
	
	void execute(Machine machine) {
		machine.registers[registerIndex1] *= machine.registers[registerIndex2];
		machine.pc++;
	}
}

class HaltInstruction extends Instruction {
	void exectue(Machine machine) {
		machine.stop = true;
	}
}

class Machine {
	boolean stop;
	int pc;
	int[] registers;
	Instruction[] instructions;
	
	Machine(int[] registers, Instruction[] instructions) {
		this.registers = registers;
		this.instructions = instructions;
	}
	
	void run() {
		while(!stop) {
			Instruction instruction = instructions[pc];
			if (instruction instanceof LoadConstantInstruction) {
				LoadConstantInstruction lci = (LoadConstantInstruction)instruction;
				lci.execute(this);
			} else if (instruction instanceof DecrementInstruction) {
				DecrementInstruction di = (DecrementInstruction)instruction;
				di.execute(this);
			} else if (instruction instanceof MultiplyInstruction) {
				MultiplyInstruction mi = (MultiplyInstruction)instruction;
				mi.execute(this);
			} else if (instruction instanceof JumpIfZeroInstruction) {
				JumpIfZeroInstruction jizi = (JumpIfZeroInstruction)instruction;
				jizi.execute(this);
			} else if (instruction instanceof JumpInstruction) {
				JumpInstruction ji = (JumpInstruction)instruction;
				pc = ji.instructionIndex;
			} else if (instruction instanceof HaltInstruction) {
				HaltInstruction hi = (HaltInstruction)instruction;
				hi.exectue(this);
			} else
				throw new AssertionError();
		}
	}
	
	static void execute(int[] registers, Instruction[] instructions) {
		new Machine(registers, instructions).run();
	}
}