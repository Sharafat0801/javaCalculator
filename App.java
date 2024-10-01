import java.util.ArrayList;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    while (true) {
      try (Scanner scanner = new Scanner(System.in)) {
        System.out.println("Enter an expression: ");
        String expression = scanner.nextLine();
        String result = separator(expression);

        System.out.println(result);
      }
    }
  }

  static String separator(String expression) {
    char[] operators = { '+', '-', '*', '/' };
    ArrayList<String> allOperator = new ArrayList<String>();
    ArrayList<String> numbers = new ArrayList<String>();

    while (true) {
      int indexOfOperator = -1;

      for (char operator : operators) {
        int index = expression.indexOf(operator);
        if (index != -1 && (indexOfOperator == -1 || index < indexOfOperator)) {
          indexOfOperator = index;
        }
      }
      if (indexOfOperator == 0) {
        expression = "";
      }

      if (indexOfOperator != -1) {
        allOperator.add(
          expression.substring(indexOfOperator, indexOfOperator + 1)
        );
        numbers.add(expression.substring(0, indexOfOperator));
        expression = expression.substring(indexOfOperator + 1);
      } else {
        numbers.add(expression.substring(0));
        expression = "";
        break;
      }

      if (expression.isBlank()) {
        break;
      }
    }

    return calculator(allOperator, numbers);
  }

  public static String calculator(
    ArrayList<String> allOperator,
    ArrayList<String> numbers
  ) {
    int size = allOperator.size() - 1;
    int result = 0;
    int getNumber = 2;
    int getOperator = 1;
    int counter = allOperator.size() - 1;
    int firstNumber = Integer.parseInt(numbers.get(0));
    int secondNumber = Integer.parseInt(numbers.get(1));
    String temp = allOperator.get(0);
    char mainOperator = temp.charAt(0);

    result = sub_cal(mainOperator, firstNumber, secondNumber);

    if (size >= 1) {
      for (int i = 1; i <= counter; i++) {
        secondNumber = Integer.parseInt(numbers.get(getNumber));
        temp = allOperator.get(getOperator);
        mainOperator = temp.charAt(0);

        result = sub_cal(mainOperator, result, secondNumber);
        getOperator++;
        getNumber++;
      }
    }

    return "= " + result;
  }

  public static int sub_cal(
    char mainOperator,
    int firstNumber,
    int secondNumber
  ) {
    int temp = 0;
    switch (mainOperator) {
      case '+':
        temp = firstNumber + secondNumber;
        break;
      case '-':
        temp = firstNumber - secondNumber;
        break;
      case '*':
        temp = firstNumber * secondNumber;
        break;
      case '/':
        temp = firstNumber / secondNumber;
        break;
    }
    return temp;
  }
}
