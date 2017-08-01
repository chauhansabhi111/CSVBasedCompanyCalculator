package com.mycompany.costcalculator.pojo;

public class CompanyTransactionReport {
	
	private String companyId;
	private String companyName;
	private String cost;
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	@Override
	public String toString()
	{
		return this.getCompanyId()+","+this.getCompanyName()+","+this.getCost();
	}
}
