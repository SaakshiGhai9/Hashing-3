// Time Complexity :O(n) as we iterate through the bracket once
// Space complexity: O(1) constant space 
public class CalculateTax {
    public double calculateTotalTax(int[][] brackets, int income) {
        double totalTax = 0;
        int previousUpper = 0;

        for (int[] bracket : brackets) {
            int upper = bracket[0];
            int percent = bracket[1];

            // Calculate taxable amount for the current bracket
            int taxable = Math.min(income, upper) - previousUpper;

            if (taxable > 0) {
                // Calculate tax for the current taxable amount
                totalTax += taxable * (percent / 100.0);
            }

            // Update the previous upper limit
            previousUpper = upper;

            // Stop if the income is fully taxed
            if (income <= upper) break;
        }

        return totalTax;
    }

    public static void main(String[] args) {
        CalculateTax calculator = new CalculateTax();

        int[][] brackets = {{3, 50}, {7, 10}, {12, 25}};
        int income = 10;

        System.out.println("Total Tax: " + calculator.calculateTotalTax(brackets, income));
    }
}
