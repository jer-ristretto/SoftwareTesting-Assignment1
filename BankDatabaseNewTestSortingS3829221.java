/**
*  
* Name: Chenyu Xiao
* Student ID: s3829221
* Email: s3829221@student.rmit.edu.au
* 
* [OPTIONAL: add any notes here about the code]
*/

package au.edu.rmit.ct;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BankDatabaseNewTestSortingS3829221 {

	static BankDatabaseNew bank;
	static CustomerAccount acc1, acc2, acc3, acc4, acc5, acc6, acc7, acc8, acc9, acc10;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ArrayList<CustomerAccount> accounts = new ArrayList<>();

		acc1 = new CustomerAccount(1001, 1234, 3000, 5000, "Shaw", "Jeremy", 130414);
		acc2 = new CustomerAccount(1002, 2345, 2000, 2000, "Rum", "Asaka", 403119);
		acc3 = new CustomerAccount(1003, 3456, 999, 2134, "Laurent", "Victorie", 865744);
		acc4 = new CustomerAccount(1004, 4567, 42, 50, "Shirogane", "Renne", 921081);
		acc5 = new CustomerAccount(1005, 5678, 2347, 5000, "Kamisato", "Ayaka", 602313);
		acc6 = new CustomerAccount(1006, 6789, 3632, 5000, "Kaedehara", "Kazuha", 592704);
		acc7 = new CustomerAccount(1007, 7890, 8356, 8500, "Raiden", "Shogun", 423988);
		acc8 = new CustomerAccount(1008, 1230, 99, 99, "Shaw", "Andy", 529909);
		acc9 = new CustomerAccount(1009, 9999, 6213, 6200, "Shaw", "Bernard", 287563);
		acc10 = new CustomerAccount(1010, 4869, 7500, 7600, "Cheung", "Leslie", 323994);

		accounts.add(acc1);
		accounts.add(acc2);
		accounts.add(acc3);
		accounts.add(acc4);
		accounts.add(acc5);
		accounts.add(acc6);
		accounts.add(acc7);
		accounts.add(acc8);
		accounts.add(acc9);
		accounts.add(acc10);

		bank = new BankDatabaseNew(accounts);
	}

	@Nested
	@DisplayName("Testing on sorting accounts by name")
	class TestSortByName {
		@Test
		@DisplayName("...checking if accounts are sorted by family name")
		void checkSortedByFamilyName() {
			bank.sortByName();
			List<CustomerAccount> accList = bank.export();
			
			assertTrue(accList.indexOf(acc2) < accList.indexOf(acc1));
			assertTrue(accList.indexOf(acc10) < accList.indexOf(acc9));
			assertTrue(accList.indexOf(acc3) < accList.indexOf(acc7));
		}

		@Test
		@DisplayName("...checking if accounts are sorted by given name for identical family name")
		void checkSameFamilyName() {
			bank.sortByName();
			List<CustomerAccount> accList = bank.export();
			
			assertTrue(accList.indexOf(acc8) < accList.indexOf(acc1));
			assertTrue(accList.indexOf(acc9) < accList.indexOf(acc1));
			assertTrue(accList.indexOf(acc8) < accList.indexOf(acc9));
		}
	}

	@Nested
	@DisplayName("Testing on sorting accounts by total balance")
	class TestSortByTotalBal {
		@Test
		@DisplayName("...checking if accounts are sorted by total balance")
		void checkSortedByTotalBal() {
			bank.sortByTotalBalance();
			List<CustomerAccount> accList = bank.export();
			
			assertTrue(accList.indexOf(acc4) < accList.indexOf(acc2));
			assertTrue(accList.indexOf(acc4) < accList.indexOf(acc8));
			assertTrue(accList.indexOf(acc9) < accList.indexOf(acc10));
		}
		
		@Test
		@DisplayName("...checking if accounts are sorted by family name for identical total balance")
		void checkSameTotalBal() {
			bank.sortByTotalBalance();
			List<CustomerAccount> accList = bank.export();
			
			assertTrue(accList.indexOf(acc5) < accList.indexOf(acc1));
			assertTrue(accList.indexOf(acc6) < accList.indexOf(acc5));
			assertTrue(accList.indexOf(acc6) < accList.indexOf(acc1));
		}
	}
}
