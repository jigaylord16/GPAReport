//Jacob Gaylord
//jigaylord16@ole.augie.edu
//GPAReport.java
import java.io.*;
import java.util.*;
public class GPAReport
{
	//Desc:	The program reads the student information stored in "data.txt", computes the GPA of the
	//	student, and produces a report
	//Input:	The input file "data.txt" contains the following items, one on each line:
	//	Student name (a string such as John Johnson)
	//	Number of course (an int such as 2)
	//	Course Name (a string such as Computer Science I)
	//	Credit hours (an int such as 4)
	//	Grade (any one of A, B, C, D, F)
	//	Course Name (e.g. Introduction to Art)
	//	Credit hours
	//	Grade
	//Output:A report in the following format is printed on the screen:
	//	Name		Courses			Credit Hours	Grade
	//	-----------------------------------------------------------------------------------
	//	John Johnson	Computer Science I	4		A
	//			Introduction to Art	3		B
	//	GPA: 3.57
	public static void main(String[] args) throws FileNotFoundException
	{
		PrintWriter output = new PrintWriter(new File("report.txt"));
		output.printf("%-20s%-25s%-15s%-5s\n",
														 "Name", "Courses", "Credit Hours", "Grade");
		output.print("---------------------------------");
		output.println("---------------------------------");
		Scanner input = new Scanner(new File("data.txt"));
		double GP = 0.0, totalCreditHr = 0.0;
		while (input.hasNextLine())
		{
		boolean first = true;
		String stuName = input.nextLine();
		int n = input.nextInt();
		input.nextLine();
		for(int i = 0 ; i<n ; ++i)
		{
			String courseName = input.nextLine();
			int creditHr = input.nextInt();
			input.nextLine();
			char grade = input.nextLine().charAt(0);
			char sign = input.nextLine().charAt(1);
			totalCreditHr += creditHr;
			GP += creditHr * findPoint(grade, sign);
			if(first)
			{
				output.printf("%-20s%-25s%-15d%-5c\n",
																 stuName, courseName, creditHr, grade);
				first = false;
			}
			else
				output.printf("%-20s%-25s%-15d%-5c\n",
																 "", courseName, creditHr, grade);
		}
		output.printf("GPA: %5.2f\n" , GP/ totalCreditHr);
		}
		input.close();
		output.close();
	}
	//Pre:	ch represents a valid grade, which must be A, B, C, D, or F
	//Return:	the grade point of ch
	public static double findPoint (char ch, char sign)
	{
		double gradePoint = 0.0;
		switch(ch)
		{
			case 'A': gradePoint = 4.0;
			case 'B': gradePoint = 3.0;
			case 'C': gradePoint = 2.0;
			case 'D': gradePoint = 1.0;
			case 'F': gradePoint = 0.0;
			default: gradePoint = -1.0;
		}
		if(sign == '+' && ch != 'A') gradePoint += 0.3;
		else if(sign == '-') gradePoint -= 0.3;
		return gradePoint;
	}
}
