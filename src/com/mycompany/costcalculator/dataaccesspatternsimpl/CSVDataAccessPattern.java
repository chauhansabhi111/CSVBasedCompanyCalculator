package com.mycompany.costcalculator.dataaccesspatternsimpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.costcalculator.dataaccesspatterninterface.InputDataFileTypeIf;
import com.mycompany.costcalculator.pojo.CompanyTransactionReport;
import com.mycompany.costcalculator.pojo.CompanyTransactions;

public class CSVDataAccessPattern implements InputDataFileTypeIf
{
	
	@Override
	public void processFile() 
	{
		
	}
	
	public List<CompanyTransactions> readCSVData(String filePath)
	{
		List<CompanyTransactions> csvData=new ArrayList<CompanyTransactions>();
		if(filePath!=null && !filePath.isEmpty())
		{
			BufferedReader br=null;
			try 
			{
				br = new BufferedReader(new FileReader(new File(filePath)));
				String line =null;
				while((line=br.readLine() )!= null)
				{
					if(line!=null && line.length()>1)
					{
					String[] rowData=line.split(",");
						CompanyTransactions ct = new CompanyTransactions();
						ct.setCompnayId(rowData[0]);
						ct.setCompnayName(rowData[1]);
						ct.setTransactionType(rowData[2]);
						ct.setTransactionDate(rowData[3]);
						ct.setTransactionStatus(rowData[4]);
						csvData.add(ct);
					}
					else
					{
						break;
					}
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return csvData;
	}
	
	public void writeDataIntoCsv(String filePath,List<CompanyTransactionReport> writeData)
	{
		if(filePath!=null && !filePath.isEmpty())
		{
			FileWriter writer =null;
			try 
			{
				writer = new FileWriter(new File(filePath));
				for (CompanyTransactionReport data : writeData) 
				{
					writer.write(data.toString()+"\n");
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
