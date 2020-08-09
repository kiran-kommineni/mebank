package com.bank.me.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ramakiran
 *
 */
public class TransactionLoad {
	
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private List<TransactionModel> transactionList = new ArrayList<TransactionModel>();
	
	
	/**
	 * A constructor which will parse the datafile and 
	 * creates List<TransactionModel> object
	 * @param dataFileName
	 * @throws IOException
	 */
	TransactionLoad(String dataFileName) throws IOException {
		
		// Read data
		Stream<String> stream = Files.lines(Paths.get(dataFileName));

		transactionList = stream
				        .skip(1) // skip headers
				        .map(line -> line.split(","))
				        .map(data -> {
				             TransactionModel transactionModel = new TransactionModel();
				             transactionModel.setTransactionId(data[0].trim());
				             transactionModel.setFromAccountId(data[1].trim());
				             transactionModel.setToAccountId(data[2].trim());
				             transactionModel.setCreatedAt(LocalDateTime.parse(data[3].trim(), dateTimeFormatter));
				             transactionModel.setAmount(new BigDecimal(data[4].trim()));
				             transactionModel.setTransactionType(data[5].trim());
				             if(data.length > 6)
				            	 transactionModel.setRelatedTransaction(data[6].trim());
				            return transactionModel;
				        })
				        .collect(Collectors.toList());
		stream.close();
		
		System.out.println("Data file loaded successfully, " + transactionList.size() + " records parsed");
	}
	
	/**
	 * A function which returns matching list of transactions based on user inputs
	 * @param fromAccount
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public List<TransactionModel> getMatchingTransactions(String fromAccount, LocalDateTime fromDate, LocalDateTime toDate){
		  
		return transactionList.stream().filter( transaction -> 
					transaction.getFromAccountId().compareTo(fromAccount) == 0 &&
				  (transaction.getCreatedAt().isAfter(fromDate) &&
				   transaction.getCreatedAt().isBefore(toDate)) ||
					transaction.getCreatedAt().isEqual(fromDate) ||
					transaction.getCreatedAt().isEqual(toDate)
				  	).collect(Collectors.toList());
	
	}
	
}
