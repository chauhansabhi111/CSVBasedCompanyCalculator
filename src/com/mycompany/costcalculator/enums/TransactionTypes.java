package com.mycompany.costcalculator.enums;

public enum TransactionTypes {
	WITHDRAW,
	DEPOSIT,
	SELL,
	BUY;
	
	public String toString(){
        switch(this){
        case WITHDRAW :
            return "WITHDRAW";
        case DEPOSIT :
            return "DEPOSIT";
        case SELL :
            return "SELL";
        case BUY :
            return "BUY";
        }
        return null;
    }

}
