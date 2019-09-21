import java.util.Scanner;

public class ProblemOneDecrypt 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter a number to decrypt: ");
		int input = sc.nextInt();
		int[] userInt = new int[4];
		int[] userIntTwo = new int[4];
		
		// Turns the 4 digit int into an array of 4 ints
		for (int i = 3; i >= 0; i--)
		{
			userInt[i] = ((input + 10) - 7) % 10;
			userIntTwo[i] = ((input + 10) - 7) % 10;
			input /= 10;
		}
		
		// Swaps first & third, as well as second and fourth
		userInt[0] = userIntTwo[2]; // putting third in first
		userInt[1] = userIntTwo[3]; // putting fourth in second
		userInt[2] = userIntTwo[0]; // putting first in third
		userInt[3] = userIntTwo[1]; // putting fourth in second
		
		// Prints out the array
		System.out.print("Your decrypted number is ");
		for (int i = 0; i < 4; i++)
		{
			System.out.print(userInt[i]);
		}
	}
}
