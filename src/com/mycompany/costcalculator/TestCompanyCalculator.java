package com.mycompany.costcalculator;

import com.mycompany.costcalculator.dataaccesspatterninterface.InputDataFileTypeIf;
import com.mycompany.costcalculator.dataaccesspatternsimpl.CSVDataAccessPattern;
import com.mycompany.costcalculator.service.CompanyCalculatorService;

public class TestCompanyCalculator {
	
	public static void main(String[] args) 
	{
		InputDataFileTypeIf fileType= new CSVDataAccessPattern();
		CompanyCalculatorService service= new CompanyCalculatorService(fileType,"C:\\Users\\hug46010\\Desktop\\Compnay.csv","C:\\Users\\hug46010\\Desktop\\Company1.csv");
		service.calculateIntraDayTransaction();
		service= new CompanyCalculatorService(fileType,"C:\\Users\\hug46010\\Desktop\\Compnay.csv","C:\\Users\\hug46010\\Desktop\\Company2.csv");
		service.calculateAllDaysTransaction();
		System.out.println("Success");
	}

}
