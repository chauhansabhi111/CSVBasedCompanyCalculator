package com.mycompany.costcalculator.pojo;

import com.mycompany.costcalculator.enums.TransactionTypes;

public class CompanyTransactions {
	
	private String compnayId;
	private String compnayName;
	private String transactionType;
	private String transactionStatus;
	private String transactionDate;
	public String getCompnayId() {
		return compnayId;
	}
	public void setCompnayId(String compnayId) {
		this.compnayId = compnayId;
	}
	public String getCompnayName() {
		return compnayName;
	}
	public void setCompnayName(String compnayName) {
		this.compnayName = compnayName;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	@Override
	public int hashCode()
	{
		return this.getCompnayId().hashCode()+ this.getCompnayName().hashCode()+this.getTransactionDate().hashCode()+this.getTransactionStatus().hashCode()+this.getTransactionType().hashCode();
	}
	
	@Override
	public boolean equals(Object o)
	{
		boolean isTransactionEqual=false;
		if(this==o)
		{
			return isTransactionEqual=true;
		}
		else
		{
			CompanyTransactions ct=(CompanyTransactions) o;
			if(this.getCompnayId().equals(ct.getCompnayId())&&(this.getCompnayName().equals(ct.getCompnayName())))
			{
				if((this.getTransactionType().equals(TransactionTypes.WITHDRAW.toString())&&(ct.getTransactionType().equals(TransactionTypes.DEPOSIT.toString())))||
						(this.getTransactionType().equals(TransactionTypes.SELL.toString())&&(ct.getTransactionType().equals(TransactionTypes.BUY.toString()))))
				{
					isTransactionEqual=true;
				}
			}
		}
		return isTransactionEqual;
	}
}
