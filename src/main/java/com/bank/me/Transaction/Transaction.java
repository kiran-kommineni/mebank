package com.bank.me.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Transaction {
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	public static void main(String[] args) throws IOException {
		
		// TODO Auto-generated method stub
		//String path = "/home/ramakiran/Documents/workspace-spring-tool-suite-4-4.6.1.RELEASE/MeChallenge/src/com/me/bank/data.csv";
		if(args.length == 0) {
			System.out.println("Error: Please Input CSV file");
			return;
		}
		
		// Load the Data file
		TransactionLoad transactionObject = new TransactionLoad(args[0]);
		
		//Ask user for filter inputs
		do{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter AccountName:");
			String fromAccount = br.readLine().trim();
			System.out.println("Enter FromDate 'dd/MM/yyyy HH:mm:ss':");
		    LocalDateTime fromDate = LocalDateTime.parse(br.readLine().trim(), dateTimeFormatter);
			System.out.println("Enter ToDate 'dd/MM/yyyy HH:mm:ss':");
		    LocalDateTime toDate = LocalDateTime.parse(br.readLine().trim(), dateTimeFormatter);
		    System.out.println("========================================================");
		    
		    //Get the matching transactions
		    List<TransactionModel> matchedTransactions = transactionObject.getMatchingTransactions(fromAccount, fromDate, toDate);
		    
		    System.out.println("Transaction count is " + matchedTransactions.size());
			System.out.println("Relative balance for the period is" + getRelativeBalance(matchedTransactions));
		}while(true);
		
	}
	
	private static BigDecimal getRelativeBalance(List<TransactionModel> matchedTransactions) {
		
	    return matchedTransactions.stream().map( transaction -> {
			BigDecimal value = transaction.getTransactionType().compareTo("REVERSAL") == 0 ? 
					transaction.getAmount().multiply(new BigDecimal("-1")) :
						transaction.getAmount();
					return value;}
	    		).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
