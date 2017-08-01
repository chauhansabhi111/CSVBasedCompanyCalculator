package com.mycompany.costcalculator.comparators;

import java.util.Comparator;

import com.mycompany.costcalculator.pojo.CompanyTransactionReport;

public class CompanyNameComparatorForTransaction implements Comparator<CompanyTransactionReport> 
{

	@Override
	public int compare(CompanyTransactionReport o1, CompanyTransactionReport o2) 
	{
		return (o1.getCompanyName().compareTo(o2.getCompanyName()));
	}

}
