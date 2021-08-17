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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BankDatabaseNewTestConstructorsS3829221 {

	static BankDatabaseNew bankDatabaseNew;
	static CustomerAccount acc1, acc2;
	ArrayList<CustomerAccount> seedCustomers;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		acc1 = new CustomerAccount(1001, 1234, 3000, 5000, "Shaw", "Jeremy", 130414);
		acc2 = new CustomerAccount(1002, 5678, 2000, 2000, "Rum", "Asaka", 795483);
	}

	@BeforeEach
	void setUp() throws Exception {
		seedCustomers = new ArrayList<>();
	}

	@Test
	@DisplayName("Testing the default constructor")
	void testDefaultConstructor() {
		bankDatabaseNew = new BankDatabaseNew();

		assertTrue(bankDatabaseNew.isEmpty());
		assertEquals(0, bankDatabaseNew.size());
		assertEquals(1000.0, bankDatabaseNew.getAvailableBalance(12345));
		assertEquals(1200.0, bankDatabaseNew.getTotalBalance(12345));
		assertEquals(200.0, bankDatabaseNew.getAvailableBalance(98765));
		assertEquals(200.0, bankDatabaseNew.getTotalBalance(98765));
	}

	@Nested
	@DisplayName("Testing the constructor with params")
	class TestConstructor {
		@Test
		@DisplayName("...checking field value of object")
		void checkParam() throws Exception {
			seedCustomers.add(acc1);
			seedCustomers.add(acc2);
			bankDatabaseNew = new BankDatabaseNew(seedCustomers);

			assertFalse(bankDatabaseNew.isEmpty());
			assertEquals(2, bankDatabaseNew.size());
			assertEquals(seedCustomers, bankDatabaseNew.export());
		}

		@Test
		@DisplayName("...catching exception thrown by BankDatabaseNew object")
		void throwExceptionInBankDatabaseNew() {
			Assertions.assertThrows(Exception.class, () -> {
				new BankDatabaseNew(seedCustomers);
			});

			try {
				new BankDatabaseNew(seedCustomers);
			} catch (Exception e) {
				assertEquals("Trying to seed database with empty source", e.getMessage());
			}
		}

		@Test
		@DisplayName("...catching exception thrown by CustomerAccount object")
		void throwExceptionInCustomerAccount() {
			Assertions.assertThrows(Exception.class, () -> {
				new CustomerAccount(1003, 123, 1000, 1200, "Laurent", "Victorie", 640139);
			});

			try {
				new CustomerAccount(1003, 123, 1000, 1200, "Laurent", "Victorie", 640139);
			} catch (Exception e) {
				assertEquals("Pin number too short, must be > 999 ", e.getMessage());
			}

			Assertions.assertThrows(Exception.class, () -> {
				new CustomerAccount(1003, 12345, 1000, 1200, "Laurent", "Victorie", 640139);
			});

			try {
				new CustomerAccount(1003, 12345, 1000, 1200, "Laurent", "Victorie", 640139);
			} catch (Exception e) {
				assertEquals("Pin number too big, must be <=9999", e.getMessage());
			}
		}
	}

	@Nested
	@DisplayName("Adding customer account into bank database")
	class TestAddCustomerAccount {
		@Test
		@DisplayName("...checking if the customer account is added")
		void checkCustomerAdded() throws Exception {
			seedCustomers.add(acc1);
			bankDatabaseNew = new BankDatabaseNew(seedCustomers);
			int bankSize = bankDatabaseNew.size();
			bankDatabaseNew.add(acc2);

			assertEquals(bankSize + 1, bankDatabaseNew.size());

			List<CustomerAccount> expected = new ArrayList<CustomerAccount>();
			expected.add(acc1);
			expected.add(acc2);

			assertEquals(expected, bankDatabaseNew.export());
		}

		@Test
		@DisplayName("...catching exception thrown by adding customer account")
		void throwExceptionInAdd() throws Exception {
			seedCustomers.add(acc1);
			bankDatabaseNew = new BankDatabaseNew(seedCustomers);

			Assertions.assertThrows(Exception.class, () -> {
				bankDatabaseNew.add(acc1);
			});

			try {
				bankDatabaseNew.add(acc1);
			} catch (Exception e) {
				assertEquals("Customer number already exists", e.getMessage());
			}
		}
	}
}
