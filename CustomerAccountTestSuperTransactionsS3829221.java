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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CustomerAccountTestSuperTransactionsS3829221 {

	CustomerAccount acc;

	@BeforeEach
	void setUp() throws Exception {
		acc = new CustomerAccount(1001, 1234, 3000, 5000, "Shaw", "Jeremy", 130414);
	}

	@Nested
	@DisplayName("Testing the credit method")
	class TestCredit {
		@Test
		@DisplayName("...checking whether credit transaction increases balance")
		void testNormalCredit() {
			acc.credit(1000);
			// Total balance should increase
			assertEquals(6000, acc.getTotalBalance());
			// Available balance should remain the same
			assertEquals(3000, acc.getAvailableBalance());
		}
		
		@Test
		@DisplayName("...checking exception thrown for negative credit")
		void testNegCredit() {
			// The credit method doesn't validate this operation, so we expect the test case to fail.
			Assertions.assertThrows(Exception.class, () -> {
				acc.credit(-1000);
			});
		}
	}
	
	@Nested
	@DisplayName("Testing the debit method")
	class TestDebit {
		@Test
		@DisplayName("...checking whether debit transaction decreases balance")
		void testNormalDebit() {
			acc.debit(2000);
			// Both balances should decrease
			assertEquals(1000, acc.getAvailableBalance());
			assertEquals(3000, acc.getTotalBalance());
		}
		
		@Test
		@DisplayName("...checking exception thrown for overwithdrawal")
		void testOverwithdrawal() {
			// The debit method doesn't validate this operation, so we expect the test case to fail.
			// Debit amount over available balance
			Assertions.assertThrows(Exception.class, () -> {
				acc.debit(4000);
			});
			// Debit amount over total balance
			Assertions.assertThrows(Exception.class, () -> {
				acc.debit(6000);
			});
		}
	}
	
	@Nested
	@DisplayName("Testing the getAvailableBalance method")
	class TestGetAvailableBalance {
		@Test
		@DisplayName("...checking the initial available balance")
		void checkInitAvalBal() {
			assertEquals(3000, acc.getAvailableBalance());
		}
		
		@Test
		@DisplayName("...checking the available balance after credit and debit")
		void checkUpdatedAvalBal() {
			acc.credit(200);
			assertEquals(3000, acc.getAvailableBalance());
			
			acc.debit(500);
			assertEquals(2500, acc.getAvailableBalance());
		}
	}
	
	@Nested
	@DisplayName("Testing the getTotalBalance method")
	class TestGetTotalBalance {
		@Test
		@DisplayName("...checking the initial total balance")
		void checkInitTotalBal() {
			assertEquals(5000, acc.getTotalBalance());
		}
		
		@Test
		@DisplayName("...checking the total balance after credit and debit")
		void checkUpdatedTotalBal() {
			acc.credit(100);
			assertEquals(5100, acc.getTotalBalance());
			
			acc.debit(900);
			assertEquals(4200, acc.getTotalBalance());
		}
	}
}
