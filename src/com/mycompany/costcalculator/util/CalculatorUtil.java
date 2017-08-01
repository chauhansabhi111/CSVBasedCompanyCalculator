package com.mycompany.costcalculator.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.mycompany.costcalculator.enums.TransactionStatus;
import com.mycompany.costcalculator.enums.TransactionTypes;
import com.mycompany.costcalculator.pojo.CompanyTransactionReport;
import com.mycompany.costcalculator.pojo.CompanyTransactions;

public class CalculatorUtil {
	
	public static Set<Entry<CompanyTransactions, Integer>> fetchData(List<CompanyTransactions> companyData)
	{
		Map<CompanyTransactions,Integer> map = new HashMap<CompanyTransactions, Integer>();
		for (CompanyTransactions companyTransactions : companyData) 
		{
			if(map.size()==0)
			{
				map.put(companyTransactions, 1);
			}
			else
			{
				if(map.containsKey(companyTransactions))
				{
					int value=map.get(companyTransactions);
					value=value++;
					map.put(companyTransactions, value);
				}
				else
				{
					map.put(companyTransactions, 1);
				}
			}
			/*if(map.containsKey(companyTransactions))
			{
				int value=map.get(companyTransactions);
				value=value++;
				map.put(companyTransactions, value);
			}
			else
			{
				map.put(companyTransactions, 1);
			}*/
		}
		Set<Entry<CompanyTransactions, Integer>> entries=map.entrySet();
		return entries;
	}
	
	public static List<CompanyTransactionReport> fetchIntraDayData(Set<Entry<CompanyTransactions, Integer>> entries,String date)
	{
		List<CompanyTransactionReport> intraDayReport= new ArrayList<CompanyTransactionReport>();
		for (Entry<CompanyTransactions, Integer> entry : entries)
		{
			CompanyTransactions ct= entry.getKey();
			int value=entry.getValue();
			if(ct.getTransactionStatus().equals(TransactionStatus.YES.toString()) && (ct.getTransactionDate().equals(date)))
			{
				CompanyTransactionReport ctr= new CompanyTransactionReport();
				ctr.setCompanyId(ct.getCompnayId());
				ctr.setCompanyName(ct.getCompnayName());
				ctr.setCost((10*value)+"");
				intraDayReport.add(ctr);
			}
		}
		return intraDayReport;
	}
	
	public static List<CompanyTransactionReport> fetchAllDaysData(Set<Entry<CompanyTransactions, Integer>> entries)
	{
		List<CompanyTransactionReport> intraDayReport= new ArrayList<CompanyTransactionReport>();
		for (Entry<CompanyTransactions, Integer> entry : entries)
		{
			CompanyTransactions ct= entry.getKey();
			if(ct.getTransactionStatus().equals(TransactionStatus.YES.toString()))
			{
				CompanyTransactionReport ctr= new CompanyTransactionReport();
				ctr.setCompanyId(ct.getCompnayId());
				ctr.setCompanyName(ct.getCompnayName());
				String cost=null;
				if(ct.getTransactionType().equals(TransactionTypes.WITHDRAW.toString()))
				{
					cost="50";
				}
				else if(ct.getTransactionType().equals(TransactionTypes.DEPOSIT.toString()))
				{
					cost="40";
				}
				else if(ct.getTransactionType().equals(TransactionTypes.SELL.toString()))
				{
					cost="60";
				}
				else if(ct.getTransactionType().equals(TransactionTypes.BUY.toString()))
				{
					cost="100";
				}
				ctr.setCost(cost);
				intraDayReport.add(ctr);
			}
		}
		return intraDayReport;
	}
	
	public static List<CompanyTransactionReport> groupReportByCost(List<CompanyTransactionReport> reportData)
	{
		List<CompanyTransactionReport> groupByList =null;
		Map<String,List<CompanyTransactionReport>> map= new HashMap<String, List<CompanyTransactionReport>>();
		for (CompanyTransactionReport companyTransactionReport : reportData) 
		{
			groupByList = new ArrayList<CompanyTransactionReport>();
			String key=companyTransactionReport.getCost();
			if(!map.containsKey(key))
			{
				groupByList.add(companyTransactionReport);
				map.put(key, groupByList);
			}
			else
			{
				groupByList=map.get(key);
				groupByList.add(companyTransactionReport);
				map.put(key, groupByList);
			}
		}
		Set<Entry<String,List<CompanyTransactionReport>>> entries= map.entrySet();
		groupByList=new ArrayList<CompanyTransactionReport>();
		for (Entry<String, List<CompanyTransactionReport>> entry : entries) 
		{
			groupByList.addAll(entry.getValue());
		}
		return groupByList;
	}
}
