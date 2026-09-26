/*
 * Created on: Mar 24, 2026
 *
 * ULID: <khpham1>
 * Class: IT 168 
 */
package edu.ilstu;

import java.text.DecimalFormat;

/**
 * Program menu-driven parking permit system class.
 *
 * @author Keaton Pham;
 *
 */
public class Permit
{
	DecimalFormat df = new DecimalFormat("$0.00");
	// iInstance variables//
	private String holderName;
	private String holderType;
	private String vehicleType;
	private String permitDuration;
	private double permitCost;

	// static variables//
	public static int totalPermitsIssued = 0;
	public static double totalRevenue = 0;

	// constant variables//
	public static final double STU_COMM_FALL = 120;
	public static final double STU_COMM_SPRING = 120;
	public static final double STU_COMM_SUMMER = 60;

	public static final double STU_RES_FALL = 200;
	public static final double STU_RES_SPRING = 200;
	public static final double STU_RES_SUMMER = 100;

	public static final double FAC_STAFF_FALL = 150;
	public static final double FAC_STAFF_SPRING = 150;
	public static final double FAC_STAFF_SUMMER = 75;

	public static final double ANNUAL_DISCOUNT = 0.15;
	public static final double MOTORCYCLE_DISCOUNT = 15;
	public static final double RESIDENT_FEE = 25;
	public static final double MINIMUM_COST = 55;

	// permit constructor //
	public Permit(String name, String type, String vehicle, String duration)
	{
		holderName = name;
		holderType = type;
		vehicleType = vehicle;
		permitDuration = duration;
		permitCost = 0.0;

		totalPermitsIssued++;

	}

	// calculating the cost//
	public void calculatePermitCost()
	{
		double baseCost = 0.0;

		if (holderType.equalsIgnoreCase("student_commuter"))
		{
			if (permitDuration.equalsIgnoreCase("fall"))
				baseCost = STU_COMM_FALL;
			else if (permitDuration.equalsIgnoreCase("spring"))
				baseCost = STU_COMM_SPRING;
			else if (permitDuration.equalsIgnoreCase("summer"))
				baseCost = STU_COMM_SUMMER;
			else
			{
				baseCost = (STU_COMM_FALL + STU_COMM_SPRING + STU_COMM_SUMMER);
				baseCost -= (baseCost * ANNUAL_DISCOUNT);
			}
		} else if (holderType.equalsIgnoreCase("student_resident"))
		{
			if (permitDuration.equalsIgnoreCase("fall"))
				baseCost = STU_RES_FALL;
			else if (permitDuration.equalsIgnoreCase("spring"))
				baseCost = STU_RES_SPRING;
			else if (permitDuration.equalsIgnoreCase("summer"))
				baseCost = STU_RES_SUMMER;
			else
			{
				baseCost = (STU_RES_FALL + STU_RES_SPRING + STU_RES_SUMMER);
				baseCost -= (baseCost * ANNUAL_DISCOUNT);
			}
		} else
		{
			if (permitDuration.equalsIgnoreCase("fall"))
				baseCost = FAC_STAFF_FALL;
			else if (permitDuration.equalsIgnoreCase("spring"))
				baseCost = FAC_STAFF_SPRING;
			else if (permitDuration.equalsIgnoreCase("summer"))
				baseCost = FAC_STAFF_SUMMER;
			else
			{
				baseCost = (FAC_STAFF_FALL + FAC_STAFF_SPRING + FAC_STAFF_SUMMER);
				baseCost -= (baseCost * ANNUAL_DISCOUNT);
			}
		}

		// resident fee//
		if (holderType.equalsIgnoreCase("student_resident")
				&& (permitDuration.equalsIgnoreCase("fall") || permitDuration.equalsIgnoreCase("spring")))
		{
			baseCost += RESIDENT_FEE;
		}

		// motorcycle discount//
		baseCost = applyVehicleAdjustment(baseCost);

		if (baseCost < MINIMUM_COST)
		{
			baseCost = MINIMUM_COST;
		}

		permitCost = baseCost;

	}

	// private method//
	private double applyVehicleAdjustment(double currentCost)
	{
		if (vehicleType.equalsIgnoreCase("motorcycle"))
		{
			return currentCost - MOTORCYCLE_DISCOUNT;
		}
		return currentCost;
	}

	public static void recordPermit(double cost)
	{
		totalRevenue += cost;
	}

	// getter//
	public static int getTotalPermitsIssued()
	{
		return totalPermitsIssued;
	}

	public static double getTotalRevenue()
	{
		return totalRevenue;
	}

	public double getPermitCost()
	{
		return permitCost;
	}

	// toString //
	public String toString()
	{
		return "Permit detail\n" + "----------------\n" + "Name: " + holderName + "\n" + "Holder Type: " + holderType
				+ "\n" + "Duration: " + permitDuration + "\n" + "Vehicle Type: " + vehicleType + "\n" + "Cost: "
				+ df.format(permitCost);

	}

}
