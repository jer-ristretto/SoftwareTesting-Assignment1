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
import static java.time.Duration.ofMillis;

class DepositSlotTimedTestSimpleRepeatS3829221 {

	static DepositSlotTimed depositSlotTimed;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		depositSlotTimed = new DepositSlotTimed("Main");
	}

	@Test
	@RepeatedTest(10)
	@DisplayName("Testing the running duration of deposit slot")
	void RepTestDepositSlotDuration() {
		// The ideal margin that causes 5 repeated tests to fail is 11 ms
		assertTimeout(ofMillis(1211), () -> {
			depositSlotTimed.checkTimer();
		});
	}
}
