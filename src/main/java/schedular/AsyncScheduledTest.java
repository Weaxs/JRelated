package schedular;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @author Weaxs
 *
 * Async Fixed Delay ThreadSat Nov 14 18:14:14 HKT 2020
 * Async Corn ThreadSat Nov 14 18:14:15 HKT 2020
 * Async Fixed Delay ThreadSat Nov 14 18:14:15 HKT 2020
 * Async Corn ThreadSat Nov 14 18:14:16 HKT 2020
 * Async Fixed Delay ThreadSat Nov 14 18:14:16 HKT 2020
 * Async Corn ThreadSat Nov 14 18:14:17 HKT 2020
 * Async Fixed Delay ThreadSat Nov 14 18:14:17 HKT 2020
 *
 *
 * Fixed Delay ThreadSat Nov 14 18:15:31 HKT 2020
 * Async Fixed Delay ThreadSat Nov 14 18:15:31 HKT 2020
 * Corn ThreadSat Nov 14 18:15:36 HKT 2020
 * Async Corn ThreadSat Nov 14 18:15:36 HKT 2020
 * Async Corn ThreadSat Nov 14 18:15:41 HKT 2020
 * Fixed Delay ThreadSat Nov 14 18:15:41 HKT 2020
 * Async Fixed Delay ThreadSat Nov 14 18:15:41 HKT 2020
 * Corn ThreadSat Nov 14 18:15:46 HKT 2020
 * Fixed Delay ThreadSat Nov 14 18:15:51 HKT 2020
 * Async Corn ThreadSat Nov 14 18:15:51 HKT 2020
 * Async Fixed Delay ThreadSat Nov 14 18:15:51 HKT 2020
 * Corn ThreadSat Nov 14 18:15:56 HKT 2020
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class AsyncScheduledTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncScheduledTest.class);

    public static void main(String[] args) {
        SpringApplication.run(AsyncScheduledTest.class, args);
    }

    /**
     * Async Fixed Delay ThreadSat Nov 14 18:05:58 HKT 2020
     * Async Fixed Delay ThreadSat Nov 14 18:05:59 HKT 2020
     * Async Fixed Delay ThreadSat Nov 14 18:06:00 HKT 2020
     * Async Fixed Delay ThreadSat Nov 14 18:06:01 HKT 2020
     */
    @Async
    @Scheduled(fixedDelay = 1000)
    public void asyncFixedDelayTest() {
        try {
            LOGGER.info( "Async Fixed Delay Thread" + new Date());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Async Corn ThreadSat Nov 14 18:12:32 HKT 2020
     * Async Corn ThreadSat Nov 14 18:12:33 HKT 2020
     * Async Corn ThreadSat Nov 14 18:12:34 HKT 2020
     */
    @Async
    @Scheduled(cron = "0/1 * * * * ?")
    public void asyncCornTest() {
        try {
            LOGGER.info("Async Corn Thread" + new Date().toString());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
