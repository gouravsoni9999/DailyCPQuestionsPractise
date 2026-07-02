class Solution {
	static String preToInfix(String pre_exp) {
		// TC : O(n)
		// SC : O(n)
		Stack<String> stack = new Stack<>();
		int n = pre_exp.length();
		
		for (int i = n - 1; i >= 0; i--) {
			char ch = pre_exp.charAt(i);
			
			if (Character.isLetterOrDigit(ch)) {
				// operand
				stack.push("" + ch);
			}
			else {
				// operator
				String s1 = stack.pop();
				String s2 = stack.pop();
				stack.push("(" + s1 + ch + s2 + ")");
			}
		}
		
		return stack.pop();
		
	}
}