import java.util.HashMap;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;


/*
Replace the array representing the coefficients by two arrays: one representing the non-
zero coefficients (of type double) and another one representing the corresponding
exponents (of type int). For example, the polynomial 6 − 2𝑥 + 5𝑥 ! would be represented
using the arrays [6, -2, 5] and [0, 1, 3]
AND 
Update the existing methods accordingly
*/
public class Polynomial {
	double[] coefficients;
	int[] exponents;

	/*
	 * It has a no-argument constructor that sets the polynomial to zero (i.e. the
	 * corresponding array would be [0])
	 */
	public Polynomial(){
		coefficients = new double[1];
		coefficients[0] = 0;
		exponents = new int[1];
		exponents[0] = 0;
	}

	/*
	 * It has a constructor that takes an array of double as an argument and sets the
	 * coefficients accordingly
	 */
	public Polynomial(double[] array, int[] exp){
		coefficients = new double[array.length];
		for (int i = 0; i < coefficients.length; i++){
			coefficients[i] = array[i];
		}
		
		exponents = new int[array.length];
		for (int j = 0; j < array.length; j++){
			exponents[j] = exp[j];
		}
	}
	
	/*
	 * It has a method named add that takes one argument of type Polynomial and
	 * returns the polynomial resulting from adding the calling object and the argument
	 */
	public Polynomial add(Polynomial A){
		HashMap<Integer, Double> map = new HashMap<>();

        //put the coefficients and expoenents into map
        for (int i = 0; i < this.coefficients.length; i++) {
            map.put(this.exponents[i], this.coefficients[i]);
        }
		for (int i = 0; i < A.coefficients.length; i++) {
			int e = A.exponents[i];
			double c = A.coefficients[i];

			// make sure expoenent is in the map
			if (map.containsKey(e)) {
				// then add the coeffs
				map.put(e, map.get(e) + c);
			} else {
				// otherwise add a new coeff
				map.put(e, c);
			}
		}
        int size = map.size();
        double[] new_c = new double[size];
        int[] new_e = new int[size];

        int counter = 0;
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            new_e[counter] = entry.getKey();
            new_c[counter] = entry.getValue();
            counter++;
        }
        return new Polynomial(new_c, new_e);
    }
	
	/*It has a method named evaluate that takes one argument of type double
	representing a value of x and evaluates the polynomial accordingly. For example,
	if the polynomial is 6 − 2𝑥 + 5𝑥 % and evaluate(-1) is invoked, the result should
	be 3
	*/
	public double evaluate(double x){
		double total = 0;
		for (int i = 0; i < coefficients.length; i++){
			total += coefficients[i] * Math.pow(x, exponents[i]);
		}
		return total;
	}
	
	/*It has a method named hasRoot that takes one argument of type double and
determines whether this value is a root of the polynomial or not. Note that a root
is a value of x for which the polynomial evaluates to zero. 
*/
	public boolean hasRoot(double n) {
		return evaluate(n) == 0;
	}
	
	/*
	Add a method named multiply that takes one argument of type Polynomial and returns
	the polynomial resulting from multiplying the calling object and the argument. The
	resulting polynomial should not contain redundant exponents.
	*/
	public Polynomial multiply(Polynomial z){
		HashMap<Integer, Double> map = new HashMap<>();
		
		for (int i = 0; i < this.coefficients.length; i++){
			for (int j = 0; j < z.coefficients.length; j++){
				double c = z.coefficients[j] * this.coefficients[i]; //new coefficient
				int e = z.exponents[j] + this.exponents[i]; //new expoenent
				
				//if the map already contains the exponent
				if (map.containsKey(e)){
					double a = map.get(e); //get its value i.e. the coefficient
					map.put(e, a + c);
				}
				else{
					map.put(e, c);
				}
			}
		}
		
		//convert map to polynomial (array of coefficient and its expoenent
		int size = map.size();
		int[] exponent_result = new int[size];
		double[] coefficient_result = new double[size];
		
		int counter = 0;
		//iterate the map
		for (Map.Entry<Integer, Double> e : map.entrySet()){
			exponent_result[counter] = e.getKey();
			coefficient_result[counter] = e.getValue();
			counter++;
		}
		
		return new Polynomial(coefficient_result, exponent_result);
	}
	
	/*
	Add a constructor that takes one argument of type File and initializes the polynomial
	based on the contents of the file. You can assume that the file contains one line with no
	whitespaces representing a valid polynomial. For example: the line 5-3x2+7x8
	corresponds to the polynomial 5 − 3𝑥2 + 7𝑥8 
	Hint: you might want to use the following methods: split of the String class, parseInt of
	the Integer class, and parseDouble of the Double class
	*/
	public Polynomial(File file) throws FileNotFoundException {
	    // Reading and opening file
	    Scanner new_f = new Scanner(file);
	    String p1 = new_f.nextLine();
	    new_f.close();

	    String[] str = p1.split("(?=[+-])"); // Keeps the + and - with coefficients
	    HashMap<Integer, Double> map = new HashMap<>();
 
	    for (String val : str) {
	        int e = 0;
	        double c;

	        // We can have a single digit like 1x^0/1 without an x
	        if (val.contains("x")) {
	            // Example: "5x^2" becomes ["5", "2"]
	            String[] digit = val.split("x");

	            // Handle coefficient
	            if (digit[0].equals("+") || digit[0].isEmpty()) {
	                c = 1.0; // Set to 1
	            } else if (digit[0].equals("-")) { // -1
	                c = -1.0; // Implicit -1 coefficient
	            } else {
	                c = Double.parseDouble(digit[0]);
	            }

	            if (digit.length > 1) {
	                e = Integer.parseInt(digit[1]);
	            } else {
	                e = 1;
	            }
	        } else {
	            e = 0;
	            c = Double.parseDouble(val);
	        }

	        map.put(e, c);
	    }

	    // Convert map to polynomial (array of coefficient and its exponent)
	    int size = map.size();
	    exponents = new int[size]; // Initialize the exponents array
	    coefficients = new double[size]; // Initialize the coefficients array

	    int counter = 0;
	    // Iterate the map
	    for (Map.Entry<Integer, Double> entry : map.entrySet()) {
	        exponents[counter] = entry.getKey();
	        coefficients[counter] = entry.getValue();
	        counter++;
	    }
	}
	
	public void saveToFile(String file) throws IOException{
		FileWriter new_file = new FileWriter(file);
		StringBuilder write_in = new StringBuilder();
		
		for (int i = 0; i < coefficients.length; i++) {
			double c = coefficients[i];
			int e = exponents[i];
			
			// + or - sign
			if (i > 0 && c > 0) {
				write_in.append("+");
			}
			write_in.append(c);
			
			// If exponent is not zero, append 'x^exponent'
			if (e == 1) {
				write_in.append("x");
			} else if (e > 1) {
				write_in.append("x").append(e);
			}
		}

		new_file.write(write_in.toString());
		new_file.close();
	}
	
	@Override
	public String toString() {
	    StringBuilder new_f = new StringBuilder();
	    
	    for (int i = 0; i < coefficients.length; i++) {
	        double c = coefficients[i];
	        int e = exponents[i];

	        if (i > 0 && c > 0) {
	            new_f.append("+");
	        }
	        
	        // Handle special cases for coefficients and exponents
	        if (e == 0) {
	            new_f.append(c);
	        } else if (e == 1) {
	            new_f.append(c).append("x");
	        } else {
	            new_f.append(c).append("x").append(e);
	        }
	    }
	    
	    return new_f.toString();
	}


}
