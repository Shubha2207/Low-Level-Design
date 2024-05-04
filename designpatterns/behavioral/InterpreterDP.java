package designpatterns.behavioral;

import java.util.Map;
import java.util.Objects;

/**
 * Used to interpret given expression
 */
public class InterpreterDP {
    public static void main(String[] args) {
        /**
         * let's take an example expression [(a+b)*(c-d)]
         * we can interpret it as
         * first_expression => (a+b)
         * second_expression => (c-d)
         * first evaluate a+b and then c-d and then multiple operation
         *
         * So to modularize this, we can use this design pattern
         *
         * there are some imp components of this DP eg.
         * Context : which provides values of variables eg. a, b etc
         * AbstractExpression : abstraction of expression
         * ConcreteExpression : any expression of addition, multiplication
         * TerminalConcreteExpression : which provide the value of variables because "a" is also an expression
         * NonTerminalConcreteExpression : which provides the result of operations like addition, multiplication performed on terminalExpressions
         *
         *
         * so there
         * a => terminalExpression
         * (a+b) => nonTerminalExpression
         */

        // exp => [(12/4)*(5+7)]
        Context context = new Context(Map.of("a", 12, "b", 4, "c",5, "d", 7));
        BinaryNonTerminalExpression addExp = new BinaryNonTerminalExpression(new TerminalExpression("c"), new TerminalExpression("d"), '+');
        BinaryNonTerminalExpression divExp = new BinaryNonTerminalExpression(new TerminalExpression("a"), new TerminalExpression("b"), '/');
        BinaryNonTerminalExpression mulExp = new BinaryNonTerminalExpression(divExp, addExp,'*');

        System.out.println(mulExp.interpret(context));
    }
}

/**
 * This object maintains the data required for expression calculations
 */
class Context {
    private final Map<String, Integer> context;

    public Context(Map<String, Integer> context){
        this.context = context;
    }

    public void set(String key, Integer value){
        context.put(key, value);
    }

    public Integer get(String key){
        return context.get(key);
    }
}

interface AbstractExpression {
    Integer interpret(Context context);
}

/**
 * object that provides value for a variable
 */
class TerminalExpression implements AbstractExpression {

    private final String key;

    public TerminalExpression(String key){
        this.key = key;
    }

    @Override
    public Integer interpret(Context context) {
        return context.get(key);
    }
}

/**
 * object that returns result of expression
 */
class BinaryNonTerminalExpression implements AbstractExpression{


    private final AbstractExpression leftTerminalExpression;
    private final AbstractExpression rightTerminalExpression;
    private final char operator;

    public BinaryNonTerminalExpression(AbstractExpression leftTerminalExp, AbstractExpression rightTerminalExp, char operator){
        this.leftTerminalExpression = leftTerminalExp;
        this.rightTerminalExpression = rightTerminalExp;
        this.operator = operator;
    }

    @Override
    public Integer interpret(Context context) {
        switch (operator){
            case '+':
                return leftTerminalExpression.interpret(context) + rightTerminalExpression.interpret(context);
            case '-':
                return leftTerminalExpression.interpret(context) - rightTerminalExpression.interpret(context);
            case '*':
                return leftTerminalExpression.interpret(context) * rightTerminalExpression.interpret(context);
            case '/':
                return leftTerminalExpression.interpret(context) / rightTerminalExpression.interpret(context);
            default:
                return null;
        }
    }
}