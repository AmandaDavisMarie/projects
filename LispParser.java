import java.util.Stack;

public class LispParser {
	
	public static void main (String [] args) {
		System.out.println(hasProperParenthesis("(abc)abc"));
	}
	
	public static boolean hasProperParenthesis(String expression) {
		Stack<Character> parenthesisStack = new Stack<>();
		
		for(int i=0; i< expression.length(); i++) {
			char character = expression.charAt(i);
			//when a starting bracket is seen, push it
			if(character=='[' || character =='{' || character=='(') {
				parenthesisStack.push(character);
			
			//if an ending bracket is seen, the next bracket in the stack better be its match
			//if the stack is empty before the next bracket is seen, it is also wrong
			}else if(character==']') {
					if(parenthesisStack.isEmpty() || parenthesisStack.pop()!='[' || parenthesisStack.isEmpty()) {
						return false;
				}
			}else if(character=='}') {
				if(parenthesisStack.isEmpty() || parenthesisStack.pop()!='{' ) {
					return false;
				}
			}else if(character==')') {
				if(parenthesisStack.isEmpty() ||parenthesisStack.pop()!='(') {
					return false;
				}
			}
		}
		
		//the stack better not have anything left over at the end, or it is unbalanced
		return parenthesisStack.isEmpty();
	}

}
