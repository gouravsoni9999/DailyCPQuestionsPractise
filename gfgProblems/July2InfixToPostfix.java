import java.util.*;
class Solution {
	private static int precedence(char ch) {
		if (ch == '^') {
			return 3;
		}
		if (ch == '*' || ch == '/') {
			return 2;
		}
		if (ch == '+' || ch == '-') {
			return 1;
		}
		// else
		return - 1;
	}
	private static boolean isRightAssociative(char ch) {
		return ch == '^';
	}
	public static String infixToPostfix(String s) {
		// code here
		StringBuilder res = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		
		for (char ch: s.toCharArray()) {
			if (Character.isLetterOrDigit(ch)) {
				// operand
				res.append(ch);
			} else {
				// if is operator
				if (ch == '(') {
					stack.push(ch);
				}
				else if (ch == ')') {
					while (stack.peek() != '(') {
						res.append(stack.pop());
					}
					stack.pop(); // for popping out '('
				}
				else {
					// any other operator
					while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch) && !isRightAssociative(ch)) {
						res.append(stack.pop());
					}
					stack.push(ch);
				}
			}
		}
		
		// push remaining elements from stack to result
		while (!stack.isEmpty()) {
			res.append(stack.pop());
		}
		return res.toString();
	}
}
