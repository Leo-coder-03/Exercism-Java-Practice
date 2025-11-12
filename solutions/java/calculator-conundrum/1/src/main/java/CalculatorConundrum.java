class CalculatorConundrum {
    public String calculate(int operand1, int operand2, String operation) {
        String result = "";
        int val = 0;
        if(operation == null)
        {
            throw new IllegalArgumentException("Operation cannot be null");
        }
        else if(operation == "")
        {
            throw new IllegalArgumentException("Operation cannot be empty");
        }
        else if(operation == "+")
        {
            val = operand1 + operand2;
            result = operand1 + " + " + operand2 +" = "+val;
        }
        else if(operation == "*")
        {
            val = operand1 * operand2;
            result = operand1 + " * " + operand2 +" = "+val;
        }
        else if(operation == "/")
            {
            try
            {val = operand1 / operand2;}
                catch(ArithmeticException e){
                  throw new IllegalOperationException("Division by zero is not allowed",e);
                }
            result = operand1 + " / " + operand2 +" = "+val;
        }
        else
        {
            throw new IllegalOperationException("Operation '"+ operation+"' does not exist");
        }
           return result; 
    }
}
