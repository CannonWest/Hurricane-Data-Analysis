import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.io.FileNotFoundException;

public class HurricaneAnalysis {

	public static void main(String[] args) throws FileNotFoundException	{
		// TODO Auto-generated method stub
		File inputFile = new File("project5Data.txt");
		File outputFile = new File("outputFile.txt");
		parseData(inputFile, outputFile);
	}
	
	public static void parseData(File inputFile, File outputFile) throws FileNotFoundException	{
		printHeading();
		
		Scanner fileScanner = new Scanner(inputFile);
		fileScanner.next();
		
		int latestYear = fileScanner.nextInt();
		int earliestYear = fileScanner.nextInt();
		int range = latestYear - earliestYear + 1;
		int count = 0;
		
		int[] numYear = new int[range];
		int[] numStorms = new int[range];
		int[] numHurricane = new int[range];
		int[] numDamages = new int[range];

		while (fileScanner.hasNext())	{
			numYear[count] = fileScanner.nextInt();
			numStorms[count] = fileScanner.nextInt();
			numHurricane[count] = fileScanner.nextInt();
			numDamages[count] = fileScanner.nextInt();
			
			count++;
		}
		
		PrintWriter out = new PrintWriter(outputFile);
		out.printf("For the years: %d to %d:%n", latestYear, earliestYear);
		out.printf("The average number of storms was %.2f.%n", calculateAverage(numStorms));
		out.printf("The average number of hurricanes was %.2f.%n", calculateAverage(numHurricane));
		out.printf("The average damage was %s.%n", averageFormatter(calculateAverage(numDamages)));
		out.printf("The year(s) with the highest number of storms is:  %s.%n", findHighest(numStorms, numYear));
		out.printf("The year(s) with the highest number of hurricanes is:  %s.%n", findHighest(numHurricane, numYear));
		out.close();
		fileScanner.close();
	}
	
	public static double calculateAverage(int[] data)	{
		double total = 0;
		for (int i = 0; i < data.length; i++)	{
			total += data[i];
		}
		
		return (total)/(double)(data.length);
	}
	
	public static String findHighest(int[] storm, int[] year)	{		
		int maxStorm = storm[0];
		for (int i = 0; i < storm.length; i++)	{
			if (storm[i] > maxStorm)	{
				maxStorm = storm[i];
			}
		}
		String years = "";
		for (int i = 0; i < storm.length; i++)	{
			if (storm[i] == maxStorm)	{
				String yearsStr = Integer.toString(year[i]);
				years = years.concat(" " + yearsStr);
			}
		}
		return years.trim();
	}

	public static String averageFormatter(double avg)	{
		avg *= 1000000;
		DecimalFormat formatter = new DecimalFormat("$#,###.00");
		String avgFormatted = formatter.format(avg);
		return avgFormatted;
	}
	
	public static void printHeading()	{
		System.out.println("\nCannon West \nProject 5 - Hurricane Data");
		System.out.println("10/30/18\nCMSC 255-001\n");
		System.out.println("This project takes inputs from a file \nfor information regarding hurricanes/storms by year and severity,\ncalculates certain statistical features \nand outputs all the information to a new file.");
	}

}