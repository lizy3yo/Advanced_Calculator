package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.text.NumberFormat;
import java.util.Locale;


public class AdvanceCalculatorController {

	@FXML
	private AnchorPane AnchorPane;

	@FXML
	private Label resultField;

	@FXML
	private Label variable;

	@FXML
	private Button Quadratic;

	private float number1 = 0;
	private float number2 = 0;

	private String operator = "";

	private boolean start = true;

	private Calculate calculate = new Calculate();

	// Getting the output
	@FXML
	public void processNumber(ActionEvent event) {
		if (start) {
			resultField.setText("");
			start = false;
		}
		String value = ((Button) event.getSource()).getText();
		resultField.setText(resultField.getText() + value);
	}
	
	// Making the number negative
	@FXML
	public void toggleSign(ActionEvent event) {
		String currentText = resultField.getText();

		if (!currentText.isEmpty() && !currentText.equals("-")) {
			if (currentText.startsWith("-")) {
				resultField.setText(currentText.substring(1));
			} else {
				resultField.setText("-" + currentText);
			}
		}
	}
	
	private String formatNumber(float number) {
	    NumberFormat formatter = NumberFormat.getInstance(Locale.US);
	    
	    // Check if the number is an integer
	    if (number == (int) number) {
	        // Format as an integer to remove .0 and then add commas
	        return formatter.format((int) number);
	    } else {
	        // Format as a float with up to 4 decimal places to maintain precision
	    	 formatter.setMaximumFractionDigits(6); // Set maximum decimal places
		     formatter.setMinimumFractionDigits(0);  // Don't force decimal places
	        return formatter.format(number);
	    }
	}

	public void processUnaryOperator(ActionEvent event) {
	    String value = ((Button) event.getSource()).getText();
	    float currentNumber = Float.parseFloat(resultField.getText());
	    float output = calculate.calculateUnaryNumber(currentNumber, value);

	    resultField.setText(formatNumber(output));
	    number1 = output;
	}


	public void processBinaryOperator(ActionEvent event) {
	    String value = ((Button) event.getSource()).getText();
	    if (!value.equals("=")) {
	        if (!operator.isEmpty()) {
	            number2 = Float.parseFloat(resultField.getText());
	            float output = calculate.calculateBinaryNumber(number1, number2, operator);
	            resultField.setText(formatNumber(output));
	            number1 = output;
	        } else {
	            number1 = Float.parseFloat(resultField.getText());
	        }
	        operator = value;
	        resultField.setText("");
	    } else {
	        number2 = Float.parseFloat(resultField.getText());
	        float output = calculate.calculateBinaryNumber(number1, number2, operator);
	        resultField.setText(formatNumber(output));
	        operator = "";
	    }
	}


	// Clear all input
	public void ClearFunction(ActionEvent event) {
		operator = "";
		start = true;
		resultField.setText("");
	}

	// Delete the last character from input
	@FXML
	void delete(ActionEvent event) {
		String text = resultField.getText();
		if (!text.isEmpty()) {
			text = text.substring(0, text.length() - 1);
			resultField.setText(text);
		}
	}

	
	
// Summation, Double Summation, Product Notation, Double Product Notation, Quadratic Equation
	    private float A = 0;
	    private float B = 0;
	    private float C = 0;
	    private float D = 0;
	    private float K = 0; // New variable for K
		private String currentExpression = ""; // Store the expression

	   
	

	    // Set expression methods
		  @FXML
		    private void handleSetExpressionKx() {
		        currentExpression = "Nx"; // Set the expression to Nx
		        updateVariableField(); // Update display
		    }

		    @FXML
		    private void handleSetExpressionXPlusK() {
		        currentExpression = "x+N"; // Set the expression to x+N
		        updateVariableField(); // Update display
		    }

		    @FXML
		    private void handleSetExpressionXToThePowerK() {
		        currentExpression = "xⁿ"; // Set the expression to xⁿ
		        updateVariableField(); // Update display
		    }
		    
		    @FXML
		    private void handleSetExpressionKToThePowerX() {
		        currentExpression = "nˣ"; // Set the expression to nˣ
		        updateVariableField(); // Update display
		    }
		

	    @FXML
	    private void handleSetExpressionXPlusY() {
	        currentExpression = "x+y"; // Set the expression to x+y
	        updateVariableField(); // Update display
	    }

	    @FXML
	    private void handleSetExpressionXTimesY() {
	        currentExpression = "x * y"; // Set the expression to x * y
	        updateVariableField(); // Update display
	    }

	    @FXML
	    private void handleSetExpressionXToThePowerY() {
	        currentExpression = "xʸ"; // Set the expression to xʸ
	        updateVariableField(); // Update display
	    }

	    @FXML
	    private void handleSetExpressionXY() {
	        currentExpression = "xy"; // Set the expression to xy
	        updateVariableField(); // Update display
	    }

	    // Calculate the summation based on the current expression
	    @FXML
	    private void handleSummation() {
	        float result = 0;
	        for (int i = (int) A; i <= (int) B; i++) {
	            result += evaluateExpression(i, 0);
	        }
	        resultField.setText(formatNumber(result)); // Display formatted result
	    }

	    // Calculate double summation using A for outer and D for upper limit, C for inner and B for upper limit
	    @FXML
	    private void handleDoubleSummation() {
	        float result = 0;
	        for (int i = (int) A; i <= (int) B; i++) {
	            for (int j = (int) C; j <= (int) D; j++) {
	                result += evaluateExpression(i, j);
	            }
	        }
	        resultField.setText(formatNumber(result));
	    }
	    // Calculate product notation based on the current expression
	    @FXML
	    private void handleProductNotation() {
	        float result = 1;
	        for (int i = (int) A; i <= (int) B; i++) {
	            result *= evaluateExpression(i, 0);
	        }
	        resultField.setText(formatNumber(result));
	    }
	    
	    // Calculate double product notation using A for outer and D for upper limit, C for inner and B for upper limit
	    @FXML
	    private void handleDoubleProductNotation() {
	        float result = 1;
	        for (int i = (int) A; i <= (int) B; i++) {
	            for (int j = (int) C; j <= (int) D; j++) {
	                result *= evaluateExpression(i, j);
	            }
	        }
	        resultField.setText(formatNumber(result));
	    }

	    // Evaluate the current expression
	    private float evaluateExpression(int x, int y) {
	        // Automatically use A, B, C, D in expression calculations
	        switch (currentExpression) {
	        case "Nx":
                return K * x; // Use N and x
            case "x+N":
                return x + K; // Use x and N
            case "xⁿ":
                return (float) Math.pow(x, K); // Use x and N
            case "nˣ":
                return (float) Math.pow(K, x); // Use N and x
            case "x+y":
                return x + y; // Use x and y
            case "x/y":
                return x / y; // Use x and y
            case "xʸ":
                return (float) Math.pow(x, y);  // Use x and y
            case "xy": // For multiplication without operator
                return x * y; // Use x and y
            default:
                return 0; // Default case
	        }
	    }


	    // Handle setting variables A, B, C, D
	    @FXML
	    private void handleSetA(ActionEvent event) {
	        setNumberFromInput(num -> A = num);
	    }

	    @FXML
	    private void handleSetB(ActionEvent event) {
	        setNumberFromInput(num -> B = num);
	    }

	    @FXML
	    private void handleSetC(ActionEvent event) {
	        setNumberFromInput(num -> C = num);
	    }

	    @FXML
	    private void handleSetD(ActionEvent event) {
	        setNumberFromInput(num -> D = num);
	    }
	    @FXML
	    private void handleSetK(ActionEvent event) {
	        setNumberFromInput(num -> K = num);
	    }
	   

	    // Generic method to set number from input
	    private void setNumberFromInput(java.util.function.Consumer<Float> numberSetter) {
	        try {
	            float number = Float.parseFloat(resultField.getText());
	            numberSetter.accept(number);
	            resultField.setText(""); // Clear field after setting number
	            updateVariableField(); // Update the variable field immediately
	        } catch (NumberFormatException ex) {
	            resultField.setText("Invalid input");
	        }
	    }
	    

	    // Update variable field to show current values and expression
	    private void updateVariableField() {
	        variable.setText("A: " + A + " B: " + B + " C: " + C + " D: " + D + " N: " + K + " Expression: " + currentExpression);
	    }
	


	    @FXML
		private void handlePowerOperation() {
			int result = (int) Math.pow(A, Math.pow(B, C)); // A^(B^C)
			resultField.setText("" + result);
		}
	    
	    
	    
	    private float factorial(int num) {
			if (num <= 1) {
				return 1;
			}
			return num * factorial(num - 1);
		}
	
	    @FXML
	    private void handleFactorialSum() {
	        float result = factorial((int) A) + factorial((int) B);
	        resultField.setText(formatNumber(result));
	    }
	    

	    @FXML
	    private void handleFactorialDivision() {
	        if (A < 0 || B < 0 || C < 0) {
	            resultField.setText("Error: Negative Input");
	            return;
	        }
	        
	        // Calculate 8! / (4! * 3!)
	        float result = factorial((int) A) / (factorial((int) B) * factorial((int) C));
	        resultField.setText(formatNumber(result));
	    }
	
	@FXML
    private void calculateFactorial(ActionEvent event) {
        try {
            int number = Integer.parseInt(resultField.getText());
            if (number < 0) {
            	resultField.setText("Error");
                return;
            }

            long factorial = 1;
            for (int i = 1; i <= number; i++) {
                factorial *= i;
            }

            resultField.setText(formatNumber(factorial));
        } catch (NumberFormatException e) {
        	resultField.setText("Error");
        }
	}

	
	
	
	
	@FXML
	private void handleQuadraticEquation() {
		// Solves the quadratic equation using a, b, and c
		if (A == 0) {
			resultField.setText("(a cannot be 0).");
			return;
		}

		float discriminant = B * B - 4 * A * C;
		if (discriminant > 0) {
			// Two real and distinct roots
			float root1 = (-B + (float) Math.sqrt(discriminant)) / (2 * A);
			float root2 = (-B - (float) Math.sqrt(discriminant)) / (2 * A);
			resultField.setText("" + root1 + "," + root2);
		} else if (discriminant == 0) {
			// One real root
			float root = -B / (2 * A);
			resultField.setText("" + root);
		} else {
			// Complex roots
			float realPart = -B / (2 * A);
			float imaginaryPart = (float) Math.sqrt(-discriminant) / (2 * A);
			resultField.setText("" + realPart + " ± " + imaginaryPart + "i");
		}
	}
}
