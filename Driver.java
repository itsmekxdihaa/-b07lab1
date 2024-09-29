public class Driver {
    public static void main(String[] args) {
        double[] c = {5, -3, 7};
            int[] e = {0, 2, 8};
            Polynomial p = new Polynomial(c, e);
            System.out.println("Polynomial 1:" + p);

            // Create another polynomial directly
            double[] c1 = {6, -2, 5};
            int[] e1 = {0, 1, 3};
            Polynomial p1 = new Polynomial(c1, e1);
            System.out.println("Polynomial 2: " + p1);

            // Add the polynomials
            Polynomial sum = p.add(p1);
            System.out.println("Sum: " + sum);

            // Evaluate
            double result = p.evaluate(2);
            System.out.println("Evaluation of p1 at x=2: " + result);
            
            //Multiply
            Polynomial p2 = p.multiply(p1);
            System.out.println("Multiply: " + p2);
            
            // Check if there's a root
            boolean hasRoot = p.hasRoot(0);
            System.out.println("Does p1 have a root at x=0? " + hasRoot);
    }
}
