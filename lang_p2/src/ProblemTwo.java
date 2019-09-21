import java.util.Scanner;
import java.lang.Math;

public class ProblemTwo {
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		double height, BMI = 0, weight;
		
		// I know this wasn't required but I wanted to do it :)
		System.out.printf("\n"
				+ "    FORMULA 1:                FORMULA 2:     \n\n"
				+ "703 * weightPounds          weightKilograms  \n"
				+ "------------------    or    ---------------  \n"
				+ "  heightInches^2             heightMeters^2  \n\n\n");
		
		System.out.println("Which formula do you want to use? (1 or 2)");
		int choice = sc.nextInt();
		
		if (choice == 1) // Customary
		{
			System.out.println("Enter your height (inches): ");
			height = sc.nextDouble();
			System.out.println("Enter your weight (pounds): ");
			weight = sc.nextDouble();
			BMI = (703 * weight) / (Math.pow(height, 2.0));
			System.out.println("\nYour BMI is " + BMI);
		}
		else if (choice == 2) // Metric
		{
			System.out.println("Enter your height (meters): ");
			height = sc.nextDouble();
			System.out.println("Enter your weight (kilograms): ");
			weight = sc.nextDouble();
			BMI = weight / Math.pow(height, 2.0);
			System.out.println("\nYour BMI is " + BMI);
		}
		else
		{
			System.out.println("Invalid input");
			System.exit(0);
		}
		
		// I know this wasn't required but I wanted to do it :)
		if (BMI < 18.5)
			System.out.println("You are underweight.");
		else if (BMI >= 18.5 && BMI <= 24.9)
			System.out.println("You are normal weight.");
		else if (BMI >= 25 && BMI <= 29.9)
			System.out.println("You are overweight.");
		else if (BMI >= 30)
			System.out.println("You are obese.");
		
		System.out.println("" + "\n"
				+ "---------------------------- \n"
				+ "BMI Categories:              \n"
				+ "---------------------------- \n"
				+ "Underweight:    18.4 or less \n"
				+ "Normal Weight:   18.5 - 24.9 \n"
				+ "Overweight:        25 - 29.9 \n"
				+ "Obese:            30 or more \n");
	}
	
}
