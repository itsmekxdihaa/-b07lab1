
public class Polynomial {
	double[] coefficients;

	/*
	 * It has a no-argument constructor that sets the polynomial to zero (i.e. the
	 * corresponding array would be [0])
	 */
	public Polynomial(){
		coefficients = new double[1];
		coefficients[0] = 0;
	}

	/*
	 * It has a constructor that takes an array of double as an argument and sets the
	 * coefficients accordingly
	 */
	public Polynomial(double[] array){
		coefficients = new double[array.length];
		for (int i = 0; i < coefficients.length; i++){
			coefficients[i] = array[i];
		}
	}
	
	/*
	 * It has a method named add that takes one argument of type Polynomial and
	 * returns the polynomial resulting from adding the calling object and the argument
	 */
	public Polynomial add(Polynomial A){
		int max = Math.max(A.coefficients.length, coefficients.length);
		
		//set result to len
		double[] result = new double[max];
		
		for (int i = 0; i < max; i++){
			if (i < A.coefficients.length && i < coefficients.length) {
				result[i] = A.coefficients[i] + coefficients[i];
			}
			else if(i >= A.coefficients.length) {
				result[i] = coefficients[i];
			}
			else {
				result[i] = A.coefficients[i];
			}
			
			}
		
		return new Polynomial(result);
	}
	
	/*It has a method named evaluate that takes one argument of type double
	representing a value of x and evaluates the polynomial accordingly. For example,
	if the polynomial is 6 − 2𝑥 + 5𝑥 % and evaluate(-1) is invoked, the result should
	be 3
	*/
	public double evaluate(double x){
		double total = 0;
		for (int i = 0; i < coefficients.length; i++){
			total += coefficients[i] * Math.pow(x, i);
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

}
