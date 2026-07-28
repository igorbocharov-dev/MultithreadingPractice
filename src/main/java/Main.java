import Task4.PollingWorker;
import Task5.Account;
import Task5.AccountPayments;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Account account1 = new Account(1L, BigDecimal.valueOf(20_000L));
        Account account2 = new Account(2L, BigDecimal.valueOf(30_000L));
        Account account3 = new Account(3L, BigDecimal.valueOf(40_000L));
        Account account4 = new Account(4L, BigDecimal.valueOf(50_000L));
        Account account5 = new Account(5L, BigDecimal.valueOf(60_000L));

        AccountPayments accountPayments = new AccountPayments();

        ExecutorService executorService = Executors.newFixedThreadPool(25);

        for (int i = 0; i < 25; i++) {
            executorService.submit(() -> {
                accountPayments.transfer(account5, account3, BigDecimal.valueOf(60_000L));
                System.out.println("После перевода с account5: " + account5.getBalance().intValue());
                accountPayments.transfer(account3, account5, BigDecimal.valueOf(60_000L));
                System.out.println("После перевода с account3: " + account3.getBalance().intValue());
            });
        }

        executorService.shutdown();

        if(!executorService.awaitTermination(10, TimeUnit.SECONDS)){
            executorService.shutdownNow();
        }

        System.out.println(account3.getBalance().intValue());
    }
}
