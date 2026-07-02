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
	private String reverse(String s) {
		int n = s.length();
		char[] arr = s.toCharArray();
		int left = 0;
		int right = n - 1;
		
		// reverse the string
		while (left < right) {
			char temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++; right--;
		}
		
		// reverse '(' to ')' and ')' to '('
		for (int i = 0; i < n; i++) {
			char ch = arr[i];
			if (ch == '(') {
				arr[i] = ')';
			} else if (ch == ')') {
				arr[i] = '(';
			}
		}
		return new String(arr);
	}
	public String infixToPrefix(String s) {
	    // TC : O(n)
	    // SC : O(n)
		Stack<Character> stack = new Stack<>();
		StringBuilder res = new StringBuilder();
		
		s = reverse(s);
		
		for (char ch : s.toCharArray()) {
			if (Character.isLetterOrDigit(ch)) {
				// an operand: directly push
				res.append(ch);
			} else {
				// an operator
				if (ch == '(') {
					stack.push(ch);
				} else if (ch == ')') {
					while (stack.peek() != '(') {
						res.append(stack.pop());
					}
					stack.pop();
				} else if (ch == '^') {
					// in stack we can't put ^^
					while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
						res.append(stack.pop());
					}
					stack.push(ch);
				} else {
					// in stack we can put +-
					while (!stack.isEmpty() && precedence(ch) < precedence(stack.peek())) {
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
		return res.reverse().toString();
	}
}

