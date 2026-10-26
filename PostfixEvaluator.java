import java.util.Scanner;

/** STUDENT FILE: Complete evaluate. Leave all other project files unchanged. */
public class PostfixEvaluator {
    /**
     * Evaluates a space-separated postfix expression using integer arithmetic.
     * Supported operators: +, -, *, / (integer division).
     *
     * @throws PostFixException for blank, malformed, or invalid expressions
     * @throws ArithmeticException for division by zero
     */
    public static int evaluate(String expression) {
        BoundedStackInterface<Integer> stack = new ArrayStack<>(100);
        Scanner tokens = new Scanner(expression);

        // TODO 1: Process tokens one at a time.
        // TODO 2: If a token is an integer, push it onto the stack.
        // TODO 3: If it is +, -, *, or /, pop two operands.
        //         IMPORTANT: the first popped number is the RIGHT operand.
        // TODO 4: Calculate and push the result.
        // TODO 5: Reject unknown tokens and expressions with too few operands.
        // TODO 6: After processing, require EXACTLY one result on the stack.
        //         Catch stack underflow/overflow or check before operations.
        // Close the Scanner when you are finished.

        tokens.useDelimiter(" ");

        while (tokens.hasNext()) {
            String token = tokens.next().trim();
            if (token.isEmpty()) {
                continue;
            }


            try {
                int number = Integer.parseInt(token);
                stack.push(number);
            } catch (StackOverflowError e) {
                throw new PostFixException("Overflow Error");
            } catch (NumberFormatException e) {
                int num1 = 0;
                int num2 = 0;
                try {
                    num1 = stack.top();
                    stack.pop();
                    num2 = stack.top();
                    stack.pop();
                } catch (StackUnderflowException ex) {
                    throw new PostFixException("Illicit Expression");
                }

                if (token.equals("+")) {
                    stack.push(num1 + num2);
                } else if (token.equals("-")) {
                    stack.push(num2 - num1);
                }  else if (token.equals("*")) {
                    stack.push(num1 * num2);
                } else if (token.equals("/")) {
                    stack.push(num2 / num1);
                } else {
                    throw new PostFixException("Illegal Expression");
                }
            }
        }

        tokens.close();

        if (stack.isEmpty()) {
            throw new PostFixException("Empty Expression");
        }

        int top = stack.top();
        stack.pop();
        if (stack.isEmpty()) {
            return top;
        } else {
            throw new PostFixException("Illicit Expression");
        }
    }
}
