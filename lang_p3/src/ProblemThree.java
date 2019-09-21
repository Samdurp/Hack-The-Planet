import java.util.Scanner;

public class ProblemThree {
	
	public static int[][] responses = new int[5][10];
	public static int numVotes;
	
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		
		String[] topics = {"$15/hour minimum wage", "Universal health care", "Music", "UCF Football", "This class"};
		int response = 0;
		
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				responses[i][j] = 0;
			}
		}
		
		System.out.println("How many people will be rating?");
		numVotes = sc.nextInt();
		System.out.println("For each topic, rate from 1-10, 1 = least important, 10 = most important.\n");
		
		for (int i = 0; i < numVotes; i++)
		{
			System.out.println("Voter " + (i + 1) + "\n------------------------");
			for (int j = 0; j < 5; j++)
			{
				System.out.println((j + 1) + ": " + topics[j]);
				response = sc.nextInt();
				responses[j][response - 1]++;
			}
			System.out.println();
		}
		
		System.out.println(""
				+ "----------------------------------------------------   -----------  \n"
				+ "| Topic # | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |   | Average |  \n"
				+ "----------------------------------------------------   -----------  \n"
				+ "|    1    | " + responses[0][0] + " | " + responses[0][1] + " | " + responses[0][2] + " | " + responses[0][3] + " | " + responses[0][4] + " | " +
				responses[0][5] + " | " + responses[0][6] + " | " + responses[0][7] + " | " + responses[0][8] + " | " + responses[0][9] + "  |   |   " + avg(0) + "   |\n"
				
				+ "|    2    | " + responses[1][0] + " | " + responses[1][1] + " | " + responses[1][2] + " | " + responses[1][3] + " | " + responses[1][4] + " | " +
				responses[1][5] + " | " + responses[1][6] + " | " + responses[1][7] + " | " + responses[1][8] + " | " + responses[1][9] + "  |   |   " + avg(1) + "   |\n"
				
				+ "|    3    | " + responses[2][0] + " | " + responses[2][1] + " | " + responses[2][2] + " | " + responses[2][3] + " | " + responses[2][4] + " | " +
				responses[2][5] + " | " + responses[2][6] + " | " + responses[2][7] + " | " + responses[2][8] + " | " + responses[2][9] + "  |   |   " + avg(2) + "   |\n"
				
				+ "|    4    | " + responses[3][0] + " | " + responses[3][1] + " | " + responses[3][2] + " | " + responses[3][3] + " | " + responses[3][4] + " | " +
				responses[3][5] + " | " + responses[3][6] + " | " + responses[3][7] + " | " + responses[3][8] + " | " + responses[3][9] + "  |   |   " + avg(3) + "   |\n"
				
				+ "|    5    | " + responses[4][0] + " | " + responses[4][1] + " | " + responses[4][2] + " | " + responses[4][3] + " | " + responses[4][4] + " | " +
				responses[4][5] + " | " + responses[4][6] + " | " + responses[4][7] + " | " + responses[4][8] + " | " + responses[4][9] + "  |   |   " + avg(4) + "   |\n"
				
				+ "----------------------------------------------------   -----------  \n");
		
		// I know there are sorting algorithms that I could have implemented but I was lazy sorry
		if (total(0) > total(1) && total(0) > total(2) && total(0) > total(3) && total(0) > total(4))
		{
			System.out.println("Topic 1 (" + topics[0] + ") has the highest point total, with " + total(0) + " point(s)!");
		}
		else if (total(1) > total(0) && total(1) > total(2) && total(1) > total(3) && total(1) > total(4))
		{
			System.out.println("Topic 2 (" + topics[1] + ") has the highest point total, with " + total(1) + " point(s)!");
		}
		else if (total(2) > total(0) && total(2) > total(1) && total(2) > total(3) && total(2) > total(4))
		{
			System.out.println("Topic 3 (" + topics[2] + ") has the highest point total, with " + total(2) + " point(s)!");
		}
		else if (total(3) > total(0) && total(3) > total(1) && total(3) > total(2) && total(3) > total(4))
		{
			System.out.println("Topic 4 (" + topics[3] + ") has the highest point total, with " + total(3) + " point(s)!");
		}
		else if (total(4) > total(0) && total(4) > total(1) && total(4) > total(2) && total(4) > total(3))
		{
			System.out.println("Topic 5 (" + topics[4] + ") has the highest point total, with " + total(4) + " point(s)!");
		}
		
		System.out.println();
		
		// I know there are sorting algorithms that I could have implemented but I was lazy sorry
		if (total(0) < total(1) && total(0) < total(2) && total(0) < total(3) && total(0) < total(4))
		{
			System.out.println("Topic 1 (" + topics[0] + ") has the lowest point total, with " + total(0) + " point(s).");
		}
		else if (total(1) < total(0) && total(1) < total(2) && total(1) < total(3) && total(1) < total(4))
		{
			System.out.println("Topic 2 (" + topics[1] + ") has the lowest point total, with " + total(1) + " point(s).");
		}
		else if (total(2) < total(0) && total(2) < total(1) && total(2) < total(3) && total(2) < total(4))
		{
			System.out.println("Topic 3 (" + topics[2] + ") has the lowest point total, with " + total(2) + " point(s).");
		}
		else if (total(3) < total(0) && total(3) < total(1) && total(3) < total(2) && total(3) < total(4))
		{
			System.out.println("Topic 4 (" + topics[3] + ") has the lowest point total, with " + total(3) + " point(s).");
		}
		else if (total(4) < total(0) && total(4) < total(1) && total(4) < total(2) && total(4) < total(3))
		{
			System.out.println("Topic 5 (" + topics[4] + ") has the lowest point total, with " + total(4) + " point(s).");
		}
	}
	
	public static double avg(int num)
	{
		int counter = 0;
		for (int i = 0; i < 10; i++)
		{
			//System.out.println("Adding " + responses[num][i] + " to " + counter);
			counter += responses[num][i] * (i + 1);
		}
		//System.out.println();
		return (double)counter / (double)numVotes;
	}
	
	public static int total(int num)
	{
		int counter = 0;
		for (int i = 0; i < 10; i++)
		{
			//System.out.println("Adding " + responses[num][i] + " to " + counter);
			counter += responses[num][i] * (i + 1);
		}
		//System.out.println();
		return counter;
	}
}
