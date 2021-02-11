import java.math.BigInteger;

public class RPNCalculator {
	private Stack<String> stack;
	private int size;
	private BigInteger MAX_INT = new BigInteger("2147483647");
	private BigInteger MIN_INT = new BigInteger("-2147483648");

	public RPNCalculator (int initSize) {

		stack = new StackArray<String>(initSize);
		size = initSize;
	}

	public int calculate(String[] expression) throws CalculatorException {
		String op1 = "";
		String op2 = "";
		BigInteger o1 = new BigInteger("0");
		BigInteger o2 = new BigInteger("0");
		BigInteger result = new BigInteger("0");
		int n = 0;

		for (int i = 0; i < expression.length; i++){
			if (isOperator(expression[i])) {
				try{
					op2 = stack.pop();
					op1 = stack.pop();
					size = size- 2;
				} catch(StackException a) {
					System.out.println("StackException" + a);
				}
				if (!isOperand(op1) || !isOperand(op2)) {
					throw new CalculatorException("Invalid expression");
				} else {
					o2 = new BigInteger(op2);
					o1 = new BigInteger(op1);
				}

				result = compute(o1, o2, expression[i]);

				try {
					stack.push(result.toString());
					size =size+1;
				} catch (StackException b) {
					System.out.println("StackException" + b);
					growAndCopy();
					try {
						stack.push(result.toString());
						size++;
					} catch (StackException c) {
						System.out.println("StackException" + c);
					}
				}
				} else if (isOperand(expression[i])){
					try {
						stack.push(expression[i]);
						size =size+1;
					} catch (StackException d) {
						System.out.println("StackException " + d);
						growAndCopy();
						try {
							stack.push(expression[i]);
							size= size+1;
						} catch(StackException e) {
							System.out.println("StackException" + e);
						}
					}
				} else {
					throw new CalculatorException("Invalid token");
				}
		}
		try {
			result = new BigInteger(stack.pop());
			size =size-1;
		} catch (StackException f) {
			System.out.println("StackException" + f);
		}
		if (result.compareTo(MAX_INT) > 0) {
			throw new CalculatorException("integer overflow");
		} else if (result.compareTo(MIN_INT) < 0) {
			throw new CalculatorException("integer underflow");
		}
		return result.intValue();
	}

	private BigInteger compute(BigInteger o1, BigInteger o2, String operator) throws CalculatorException{
		switch(operator) {
			case "+":
				return o1.add(o2);
			case "-":
				return o1.subtract(o2);
			case "/":
				if(o2.intValue() == 0) {
					throw new CalculatorException("division by zero");
				}
				return o1.divide(o2);
			case "*":
				return o1.multiply(o2);
		}
		return new BigInteger("0");
	}

	private void growAndCopy() {
		Stack<String> newStack = new StackArray<String>(size);
		String str;
		while(!stack.isEmpty()) {
			try {
				str = stack.pop();
				newStack.push(str);
			} catch (StackException g) {
				System.out.println("StackException caught in growAndCopy()" + g);
			}
		}
		stack = new StackArray<String>(size*2);
		while(!newStack.isEmpty()) {
			try {
				str = newStack.pop();
				stack.push(str);
			} catch (StackException h) {
				System.out.println("StackException caught in growAndCopy()" + h);
			}
		}
		stack = newStack;
	}

	private boolean isOperand(String token) {
		try {
			int tok = Integer.parseInt(token)%1;
			return tok==0;
		} catch (NumberFormatException e) {
			System.out.println("Invalid token");
			return false;
		}
	}

	private boolean isOperator(String token) {
		return(token.equals("+") || token.equals("-") ||
		   token.equals("/") || token.equals("*"));
	}


}
