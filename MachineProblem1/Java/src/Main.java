/*
	Approach for Machine Problem 1
	
	My thought process for this problem is that it is mainly the 3x + 1 problem with additional conditions
	in which the program must allow  n = 1 and allows the repeat of last 3 values twice.
		1.	First, initiate a script for getting user input for n and store the value.
		2.	Create a function that holds the computation for the sequence.
		3.	Inside the function, I used if-else statements to check if n is even/odd/0.
		4. 	The if-else statements is run through a while loop to continuously compute for the next number of the sequence.
		5.	I stored each value of the sequence into an integer arraylist for future purpose of the additional condition.
		6.	I initiated another if statement to store the last 6 values of the sequence when the size is more than or equal to 6
			as I think it is the minimum number of list size for the checking of additional condition.
		7.	I created another arraylist for the subList of the original arraylist with size 6. I sliced the newList into two arraylist
			with size 3 and assigned them to prev and last. Next will be the validation if the prev and last arraylist were equal which
			will then terminate the program.
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
			//If num is even, add the num to the arraylist,apply n/2 and re-store the result into num
			else if (num % 2 == 0) {
				list.add(num);
				num = num/2;
			}
			//If num is odd, add the num to the arraylist, appliy 3n+1 and re-store the result into num
			else {
				list.add(num);
				num = 3*num + 1;
			}
			
			//Start of validation for the last 3 values repeating twice
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
