import java.math.BigInteger;

public class RPNCalculator {
	private Stack<String> stack;
	private int size;

	public RPNCalculator (int initSize) {
		size = initSize;
		stack = new StackArray<String>(size);
	}

	public int calculate(String[] expression) throws CalculatorException {
			 int answer=0;
			 String o1;
			 String o2;
			for(int i =0;i<expression.length;i++){
				if(isOperator(expression[i])==false){
					try{
						Integer.parseInt(expression[i]);
					}catch(NumberFormatException nfe){
							throw new CalculatorException("Invalid Token");
						}
					try{
						stack.push(expression[i]);
					}catch(StackException se){
						copyAndGrow();
						try{
							stack.push(expression[i]);
						}catch(StackException see){
							copyAndGrow();
						}
					}
				}else if(isOperator(expression[i]) == true){
					try{
					 o1= stack.pop();
					 o2 = stack.pop();
					}catch(StackException se){
						throw new CalculatorException("Invalid Expression");
					}
					answer =  Integer.parseInt(compute(expression[i],o1,o2));
					try{
						stack.push(Integer.toString(answer));
					}catch(StackException se){
						copyAndGrow();
					}
				}
			}
			return answer;
	}

	//HELPER METHODS
	private Stack<String> newStack;

	private void copyAndGrow(){
		newStack = new StackArray<String>(size*2);
		for(int i = 0; i< size; i++){
			try{
				newStack.push(stack.pop());
			}catch(StackException se){
				System.out.println("error");
			}
		}
		stack = newStack;
		size *=2;
	}

	private boolean isOperator(String s){
		if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")){
			return true;
		}
		return false;
	}


	private String compute(String operator, String o1, String o2) throws CalculatorException{
		int operand1 = Integer.parseInt(o1);
		int operand2 = Integer.parseInt(o2);
		int result = 0;
		String returnValue;
		long val = 0;
		if(operator.equals("+")){
			val = ((long) operand1) + ((long)operand2);
			if (val > Integer.MAX_VALUE-1) {
				throw new CalculatorException("Integer Overflow");
	 		} else if (val < Integer.MIN_VALUE+1) {
				throw new CalculatorException("Integer Underflow");
	 		}
			result = operand1 + operand2;
		}else if(operator.equals("-")){
			val = ((long) operand1) - ((long)operand2);
			if (val > Integer.MAX_VALUE-1) {
				throw new CalculatorException("Integer Overflow");
	 		} else if (val < Integer.MIN_VALUE+1) {
				throw new CalculatorException("Integer Underflow");
	 		}
			result = operand1 - operand2;
		}else if(operator.equals("*")){
			val = ((long) operand1) * ((long) operand2);
			if (val > Integer.MAX_VALUE-1) {
				throw new CalculatorException("Integer Overflow");
	 		} else if (val < Integer.MIN_VALUE+1) {
				throw new CalculatorException("Integer Underflow");
	 		}
			result = operand1*operand2;
		}else if(operator.equals("/")){
			if(operand2 == 0 || operand1 == 0){
				throw new CalculatorException("Division By Zero");
			}else{
				val = ((long) operand1)/((long) operand2);
				if (val > Integer.MAX_VALUE-1) {
					throw new CalculatorException("Integer Overflow");
		 		} else if (val < Integer.MIN_VALUE+1) {
					throw new CalculatorException("Integer Underflow");
		 		}
				result = operand1/operand2;
			}
		}
		returnValue = Integer.toString(result);
		return returnValue;
	}

}
