/*
	Approach for Machine Problem 3
	
	My thought process for this problem is to store the Transaction and Currency data into an arrayList and looping through
	each element to check if there is an element that corresponds to a given transaction date. I will then match the currency code
	with the code of Transaction data to compute the total sell and buy in PHP.
		1.	First, initiate a script for getting user input for transactionFile,currencyFile,outputFile and the txnDate.
		2.	The values will then be stored to be passed into the compute() function.
		3.	In the compute() function, I will initialize the necessary variables for sell and buy value together with the arraylist
			for transaction and currency data.
		4.	I will initiate a file read to both Transaction and Currency file and using a while loop, I stored each line of data into
			their respective arrayList.
		5.	After storing Transaction and Currency data, I will loop through the transaction list first to check if an element has a date
			equals the input txnDate. If an element exists, I will loop through the currency list to match the transaction element
			with the correct currency rate. 
		6.	After above process, check whether the txnCode is 'FX_BUY' or 'FX_SELL'. Separate each to compute the total buy and total sell
			which will be stored in the totalBuy and totalSell variables. Since we're still in a loop, if there exists more than one transaction element
			which satisfies all condition, it will then be added to the totalBuy and totalSell variables.
		7.	I will initiate a file write into Output file and write the data from the totalBuy and totalSell variable.
 */

import java.io.*;
import java.lang.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.math.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Scanner myObj = new Scanner(System.in);
			System.out.print("currencyFile = ");
			String currency_file = myObj.next();
			System.out.print("transaction_file = ");
			String transaction_file = myObj.next();
			System.out.print("output_file = ");
			String output_file = myObj.next();
			System.out.print("txnDate = ");
			String txnDate = myObj.next();
			myObj.close();
			compute(currency_file, transaction_file, output_file, txnDate);
			
		} catch (Exception e) {
			System.out.println("Invalid input");
		}

	}
	
	public static void compute(String currencyFile, String transactionFile, String outputFile, String txnDate) {
		try {
			//Initialization of variables. Arraylist is used to store the values from the file.
			float totalBuy = 0;
			float totalSell = 0;
			List<List<String>> transaction = new ArrayList<>();
			List<List<String>> currency = new ArrayList<>();
			List<String> output = new ArrayList<>();
			
			//Script for reading and storing data from file into arraylist - Transaction.csv
			try (BufferedReader br = new BufferedReader(new FileReader(transactionFile,StandardCharsets.UTF_8))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			        String[] values = line.split(",");
			        transaction.add(Arrays.asList(values));
			    }
			}
			
			//Script for reading and storing data from file into arraylist - Currency.csv
			try (BufferedReader br = new BufferedReader(new FileReader(currencyFile,StandardCharsets.UTF_8))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			        String[] values = line.split(",");
			        currency.add(Arrays.asList(values));
			    }
			}
			
			//For loop for each data to compute the totalBuy and totalSell in PHP
			for (int i = 1; i <= transaction.size() - 1; i++) {
				List<String> rowval = transaction.get(i);
	            if (rowval.get(3).toString().equals(txnDate)) {
	            	for (int x = 1; x<= currency.size() - 1; x++) {
	            		List<String> curval = currency.get(x);
	            		if (rowval.get(1).toString().equals(curval.get(1))) {
	            			if  (rowval.get(2).toString().equals("FX_BUY")) {
	            				totalBuy += BigDecimal.valueOf(Float.parseFloat(rowval.get(4)) * Float.parseFloat(curval.get(4))).setScale(Integer.parseInt(curval.get(3)),RoundingMode.HALF_UP).floatValue();
	            			}
	            			else {
	            				System.out.println(curval.get(3));
	            				totalSell += BigDecimal.valueOf(Float.parseFloat(rowval.get(4)) * Float.parseFloat(curval.get(4))).setScale(Integer.parseInt(curval.get(3)),RoundingMode.HALF_UP).floatValue();
	            			}
	            		}
	            	}
	            }	            
	        }
			
			//No transaction on input txnDate
			if (totalSell == 0 && totalBuy == 0) {
				System.out.println("No transaction on transaction date.");
			}
			System.out.println("FX_SELL=" + totalSell);
			System.out.println("FX_BUY=" + totalBuy);
			output.add("FX_SELL=" + totalSell);
			output.add("FX_BUY=" + totalBuy);
			
			//Script for writing the total Buy and Sell in PHP into the outputFile
			try (BufferedWriter wr = new BufferedWriter(new FileWriter(outputFile,StandardCharsets.UTF_8))) {
			    for (String outputLine : output) {
			    	wr.append(outputLine);
			    	wr.newLine();
			    }
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
