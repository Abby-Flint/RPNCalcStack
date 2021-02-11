public class StackArray<T> implements Stack<T> {

	private T[] data;
	private int top;


	StackArray(int size) {
		data = (T[]) new Object[size];
		// Java does not allow using "new" on a generic type
		// don't worry about the constructor - you don't have to change it
		top = -1;
	}

	// purpose: pushes an element "elem" onto the stack
	// parameter: elem (of type <T>)
	// return value: none
	// preconditions: none
	// postconditions: if stack has room then stack size has increased by one and the top element is "elem"
	//                 otherwise a StackException will be thrown
	public void push(T elem) throws StackException {
		if(top == data.length-1)
			throw new StackException("thrown within push");
		data[++top] = elem;
	}

	// purpose: pops the top element from the stack
	// parameter: none
	// return value: top element of stack (of type <T>)
	// preconditions: none
	// postconditions: if stack was not empty, then stack size has decreased by one and the top element was removed and returned
	//                 otherwise a StackException will be thrown
	public T pop() throws StackException {
		if(top == -1){
			throw new StackException("thrown within pop");
		}
		T result = data[top--];
		return result;
	}


	// purpose: pops all elements from the stack (makes it empty)
	// parameter: none
	// return value: none
	// preconditions: none
	// postconditions: stack is empty
	public void popAll() {
		top = -1;
	}

	// purpose: returns the top element from the stack without removing it
	// parameter: none
	// return value: top element of stack (of type <T>)
	// preconditions: none
	// postconditions: if stack was not empty, the top element was removed and returned and the stack size remains unchanged
	//                 otherwise a StackException will be thrown
	public T peek() throws StackException {
		if(top == -1){
			throw new StackException("throw within peek");
		}
		return data[top];
	}


	// purpose: to find out whether stack is empty
	// parameter: none
	// return value: true if stack is empty, false otherwise
	// preconditions: none
	// postconditions: stack remains unchanged
	public boolean isEmpty() {
		return (top == -1);
	}



	public String toString() {
		String result = "{";
		String separator = "";
	  for (int i=top; i>=0; i--) {
			result += separator + data[i];
		  separator = ", ";
	  }
		result += "}";
		return result;
	}
}
