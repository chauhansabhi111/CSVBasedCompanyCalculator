package com.mycompany.costcalculator.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import com.mycompany.costcalculator.comparators.CompanyNameComparatorForTransaction;
import com.mycompany.costcalculator.dataaccesspatterninterface.InputDataFileTypeIf;
import com.mycompany.costcalculator.dataaccesspatternsimpl.CSVDataAccessPattern;
import com.mycompany.costcalculator.pojo.CompanyTransactionReport;
import com.mycompany.costcalculator.pojo.CompanyTransactions;
import com.mycompany.costcalculator.util.CalculatorUtil;

public class CompanyCalculatorService 
{
	private InputDataFileTypeIf fileType=null;
	private String filePath=null;
	private String outPutFilePath=null;
	public CompanyCalculatorService(InputDataFileTypeIf fileType,String filePath,String outPutFilePath)
	{
		this.fileType=fileType;
		this.filePath=filePath;
		this.outPutFilePath=outPutFilePath;
	}
	
	public void calculateIntraDayTransaction()
	{
		if(fileType instanceof CSVDataAccessPattern)
		{
			CSVDataAccessPattern csvDataAccessPattern=(CSVDataAccessPattern) fileType;
			List<CompanyTransactions> fileData=csvDataAccessPattern.readCSVData(filePath);
			Set<Entry<CompanyTransactions, Integer>> entriesData= CalculatorUtil.fetchData(fileData);
			List<CompanyTransactionReport> intraDayReport=CalculatorUtil.fetchIntraDayData(entriesData, "31/07/17");
			Collections.sort(intraDayReport, new CompanyNameComparatorForTransaction());
			intraDayReport=CalculatorUtil.groupReportByCost(intraDayReport);
			csvDataAccessPattern.writeDataIntoCsv(outPutFilePath, intraDayReport);
		}
	}
	
	public void calculateAllDaysTransaction()
	{
		if(fileType instanceof CSVDataAccessPattern)
		{
			CSVDataAccessPattern csvDataAccessPattern=(CSVDataAccessPattern) fileType;
			List<CompanyTransactions> fileData=csvDataAccessPattern.readCSVData(filePath);
			Set<Entry<CompanyTransactions, Integer>> entriesData= CalculatorUtil.fetchData(fileData);
			List<CompanyTransactionReport> allDaysReport=CalculatorUtil.fetchAllDaysData(entriesData);
			Collections.sort(allDaysReport, new CompanyNameComparatorForTransaction());
			allDaysReport=CalculatorUtil.groupReportByCost(allDaysReport);
			csvDataAccessPattern.writeDataIntoCsv(outPutFilePath, allDaysReport);
		}
	}

}
