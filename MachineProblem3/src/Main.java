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
			float totalBuy = 0;
			float totalSell = 0;
			List<List<String>> transaction = new ArrayList<>();
			List<List<String>> currency = new ArrayList<>();
			List<String> output = new ArrayList<>();
			
			try (BufferedReader br = new BufferedReader(new FileReader(transactionFile,StandardCharsets.UTF_8))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			        String[] values = line.split(",");
			        transaction.add(Arrays.asList(values));
			    }
			}
			
			try (BufferedReader br = new BufferedReader(new FileReader(currencyFile,StandardCharsets.UTF_8))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			        String[] values = line.split(",");
			        currency.add(Arrays.asList(values));
			    }
			}
			
			for (int i = 1; i <= transaction.size() - 1; i++) {
				List<String> rowval = transaction.get(i);
	            if (rowval.get(3).toString().equals(txnDate)) {
	            	for (int x = 1; x<= currency.size() - 1; x++) {
	            		List<String> curval = currency.get(x);
	            		if (rowval.get(1).toString().equals(curval.get(1))) {
	            			if  (rowval.get(2).toString().equals("FX_BUY")) {
	            				System.out.println(curval.get(3));
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
