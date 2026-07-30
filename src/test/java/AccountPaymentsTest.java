import Task5.Account;
import Task5.AccountPayments;
import Task5.Payments;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountPaymentsTest {

    private Payments accountPayments;

    @Test
    void transfer_ShouldCorrectlyTransferFromAccountOneToAccountTwoWithoutDeadlock() throws InterruptedException {
        accountPayments = new AccountPayments();

        final int balanceFromAccount1 = 20_000;
        final int balanceFromAccount2 = 80_000;

        final int transferAmount = 20_000;

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        Account account1 = new Account(1L, BigDecimal.valueOf(balanceFromAccount1));
        Account account2 = new Account(2L, BigDecimal.valueOf(balanceFromAccount2));

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> accountPayments.transfer(account1, account2, BigDecimal.valueOf(transferAmount)));
            executorService.submit(() -> accountPayments.transfer(account2, account1, BigDecimal.valueOf(transferAmount)));
        }

        executorService.shutdown();

        if(!executorService.awaitTermination(5, TimeUnit.SECONDS)){
            executorService.shutdownNow();
        }

        assertEquals(balanceFromAccount1, account1.getBalance().intValue());
        assertEquals(balanceFromAccount2, account2.getBalance().intValue());
    }
}
