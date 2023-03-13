/*
	Approach for Machine Problem 1
	
	My thought process for this problem is that it is mainly the 3x + 1 problem with additional conditions
	in which the program must allow  n = 1 and allows the repeat of last 3 values twice.
		1.	First, initiate a script for getting user input for n and store the value.
		2.	Create a function that holds the computation for the sequence.
		3.	Inside the function, 
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static  void result (int num) {
		List<Integer> list = new ArrayList<Integer>();
		
		while (true) {
			
			if (num == 0) {
				break;
			}
			else if (num % 2 == 0) {
				list.add(num);
				num = num/2;
			}
			else {
				list.add(num);
				num = 3*num + 1;
			}

			if (list.size() >= 6) {
				List<Integer> newList = list.subList(list.size() - 6, list.size());
				List<Integer> last = newList.subList(newList.size() - 3, newList.size());
				List<Integer> prev = newList.subList(0, 3);
				if (prev.equals(last)) {
					break;
				}
			}
		}
		System.out.println(list);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Scanner myObj = new Scanner(System.in);
			System.out.print("n = ");
			int number = myObj.nextInt();
			myObj.close();
			
			result(number);
			
		} catch (Exception e) {
			System.out.println("Invalid input");
		}
		
		
	}

}
