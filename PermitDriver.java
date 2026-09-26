/*
 * Created on: Mar 24, 2026
 *
 * ULID: <khpham1>
 * Class: IT 168 
 */
package edu.ilstu;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Program that controls execution of PermitClass with menu prompts and allows
 * user to create their permits and see their totals.
 *
 * @author Keaton Pham;
 *
 */
public class PermitDriver
{
	static DecimalFormat df = new DecimalFormat("$0.00");
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args)
	{
		int choice = 0;

		while (choice != 3)
		{
			displayMenu();
			choice = readMenuChoice();

			if (choice == 1)
			{
				requestPermit();
			} else if (choice == 2)
			{
				printSummary();
			}
		}
		System.out.println(Permit.getTotalPermitsIssued() + " permits have been issued.");
		System.out.println("Thanks for visiting the ISU Parking Permit Portal.");
	}

	private static void displayMenu()
	{
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1 -- Create a permit");
		System.out.println("2 -- Display Totals");
		System.out.println("3 -- Quit");
		System.out.print("Enter choice: \n");

	}

	private static int readMenuChoice()
	{
		boolean trueOrFalse = false;
		int choice = 0;
		while (!trueOrFalse)
		{
			String answer = scan.nextLine();
			if (answer.equals("1"))
			{
				choice = 1;
				trueOrFalse = true;
			} else if (answer.equals("2"))
			{
				choice = 2;
				trueOrFalse = true;
			} else if (answer.equals("3"))
			{
				choice = 3;
				trueOrFalse = true;
			} else
			{
				System.out.println("Invalid choice. Please try again.");
				displayMenu();

			}
		}
		return choice;
	}

	private static String readValidHolderType()
	{
		String holderType = "";
		boolean trueOrFalse = false;
		while (!trueOrFalse)
		{
			System.out.println("Enter your holder type: (1=student_commuter, 2=student_resident, 3=faculty_staff)");
			String answer = scan.nextLine().toLowerCase();
			if (answer.equals("1"))
			{
				holderType = "student_commuter";
				trueOrFalse = true;
			} else if (answer.equals("2"))
			{
				holderType = "student_resident";
				trueOrFalse = true;
			} else if (answer.equals("3"))
			{
				holderType = "faculty_staff";
				trueOrFalse = true;
			} else
			{
				System.out.println("Invalid holder type. Please try again.");
			}
		}
		return holderType;
	}

	private static String readValidDuration()
	{
		String validDuration = "";
		boolean trueOrFalse = false;

		while (!trueOrFalse)
		{
			System.out.println("Enter your permit duration: (Fall, Spring, Summer, or Annual) ");
			String answer = scan.nextLine().toLowerCase();
			if (answer.equals("fall") || answer.equals("spring") || answer.equals("summer") || answer.equals("annual"))
			{
				validDuration = answer;
				trueOrFalse = true;
			} else
			{
				System.out.println("Invalid duration. Please try again");
			}
		}
		return validDuration;
	}

	private static String readValidVehicleType()
	{
		String vehicleType = "";
		boolean trueOrFalse = false;

		while (!trueOrFalse)
		{
			System.out.println("Please enter your vehicle type: (Car or Motorcycle) ");
			String answer = scan.nextLine().toLowerCase();
			if (answer.equals("car") || answer.equals("motorcycle"))
			{
				vehicleType = answer;
				trueOrFalse = true;
			} else
			{
				System.out.println("Invalid vehicle type. Please try again");
			}
		}

		return vehicleType;
	}

	private static void requestPermit()
	{
		System.out.print("Please enter permit holder name: ");
		String name = scan.nextLine();
		String holderType = readValidHolderType();
		String permitDuration = readValidDuration();
		String vehicleType = readValidVehicleType();
		Permit p = new Permit(name, holderType, vehicleType, permitDuration);
		p.calculatePermitCost();
		System.out.println(p);
		Permit.recordPermit(p.getPermitCost());

	}

	private static void printSummary()
	{
		System.out.println("=====Program Totals=====");
		System.out.println("Total Permits Issued: " + Permit.getTotalPermitsIssued());
		System.out.println("Total Revenue: " + (df.format(Permit.getTotalRevenue())));
		System.out.println("========================");
	}

}
