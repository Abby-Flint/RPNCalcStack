public interface Stack<T> {

	// purpose: pushes an element "elem" onto the stack
	// parameter: elem (of type <T>)
	// return value: none
	// preconditions: none
	// postconditions: if stack has room then stack size has increased by one and the top element is "elem"
	//                 otherwise a StackException will be thrown
	void push(T elem) throws StackException;

	// purpose: pops the top element from the stack
	// parameter: none
	// return value: top element of stack (of type <T>)
	// preconditions: none
	// postconditions: if stack was not empty, then stack size has decreased by one and the top element was removed and returned
	//                 otherwise a StackException will be thrown
	T pop() throws StackException;


	// purpose: pops all elements from the stack (makes it empty)
	// parameter: none
	// return value: none
	// preconditions: none
	// postconditions: stack is empty
	void popAll();

	// purpose: returns the top element from the stack without removing it
	// parameter: none
	// return value: top element of stack (of type <T>)
	// preconditions: none
	// postconditions: if stack was not empty, the top element was removed and returned and the stack size remains unchanged
	//                 otherwise a StackException will be thrown
	T peek() throws StackException;


	// purpose: to find out whether stack is empty
	// parameter: none
	// return value: true if stack is empty, false otherwise
	// preconditions: none
	// postconditions: stack remains unchanged
	boolean isEmpty();
}
