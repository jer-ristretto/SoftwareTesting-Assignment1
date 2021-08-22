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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class DepositSlotTimedTestAdvancedTimingS3829221 {
	
	static DepositSlotTimed depositSlotTimed;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		depositSlotTimed  = new DepositSlotTimed("DepositSlot thread", 5000);
	}
	
	@Test
	@Order(1)
	@RepeatedTest(10)
	@DisplayName("Checking the status of deposit slot before activation")
	void checkBeforeActivation() {
		assertFalse(depositSlotTimed.isOpenForDeposit());
	}

	@Test
	@Order(2)
	@DisplayName("Activating deposit slot")
	void ActivateDepositSlot() {
		// Let the deposit slot run in a separate thread
		depositSlotTimed.activateSlotWithTimedShut();
	}
	
	@Test
	@Order(3)
	@RepeatedTest(55)
	@DisplayName("Repeatedly checking the status of deposit slot after activation")
	void checkAfterActivation() throws InterruptedException {
		// Expect the test to pass before the deposit slot is deactivated and fail afterwards
		assertTrue(depositSlotTimed.isOpenForDeposit());
		// Let the testing thread sleep before repetition
		Thread.sleep(100);
	}

}
