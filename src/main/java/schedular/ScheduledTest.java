package schedular;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @author Weaxs
 *
 * corn job
 *
 * Fixed Delay ThreadSat Nov 14 17:48:02 HKT 2020
 * Corn ThreadSat Nov 14 17:48:07 HKT 2020
 * Fixed Delay ThreadSat Nov 14 17:48:12 HKT 2020
 * Corn ThreadSat Nov 14 17:48:17 HKT 2020
 * Fixed Delay ThreadSat Nov 14 17:48:22 HKT 2020
 * Corn ThreadSat Nov 14 17:48:27 HKT 2020
 * Fixed Delay ThreadSat Nov 14 17:48:32 HKT 2020
 * Corn ThreadSat Nov 14 17:48:37 HKT 2020
 */
@EnableScheduling
@SpringBootApplication
public class ScheduledTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTest.class);

    public static void main(String[] args) {
        SpringApplication.run(ScheduledTest.class, args);
    }

    /**
     * Fixed Delay ThreadSat Nov 14 17:54:06 HKT 2020
     * Fixed Delay ThreadSat Nov 14 17:54:12 HKT 2020
     * Fixed Delay ThreadSat Nov 14 17:54:18 HKT 2020
     * Fixed Delay ThreadSat Nov 14 17:54:24 HKT 2020
     */
    @Scheduled(fixedDelay = 1000)
    public void fixedDelayTest() {
        try {
            LOGGER.info( "Fixed Delay Thread" + new Date());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Corn ThreadSat Nov 14 17:50:53 HKT 2020
     * Corn ThreadSat Nov 14 17:50:59 HKT 2020
     * Corn ThreadSat Nov 14 17:51:05 HKT 2020
     */
    @Scheduled(cron = "0/1 * * * * ?")
    public void cornTest() {
        try {
            LOGGER.info("Corn Thread" + new Date().toString());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
