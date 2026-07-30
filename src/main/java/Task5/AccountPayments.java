package Task5;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;

public class AccountPayments implements Payments{

    private final AccountPaymentsValidator accountPaymentsValidator = new AccountPaymentsValidator();

    @Override
    public void transfer(Account from, Account to, BigDecimal amount) {
        Lock lock1 = (Lock) from;
        Lock lock2 = (Lock) to;
        if(from.compareTo(to) < 0){
            lock2 = (Lock) from;
            lock1 = (Lock) to;
        }
        takeLocks(lock1, lock2);
        try {
            accountPaymentsValidator.validateAccount(from);
            accountPaymentsValidator.validateAccount(to);
            accountPaymentsValidator.validateAmount(amount);
            accountPaymentsValidator.validateDeductionFromAccount(from, amount);
            BigDecimal newBalanceFromAccount1 = from.getBalance().subtract(amount);
            from.setBalance(newBalanceFromAccount1);
            BigDecimal newBalanceToAccount2 = to.getBalance().add(amount);
            to.setBalance(newBalanceToAccount2);
        } finally {
            lock1.unlock();
            lock2.unlock();
        }
    }

    private void takeLocks(Lock lock1, Lock lock2){
        boolean firstLockTaken = false;
        boolean secondLockTaken = false;
        while (true){
            try{
                firstLockTaken = lock1.tryLock();
                secondLockTaken = lock2.tryLock();
            } finally {
                if(firstLockTaken && secondLockTaken){
                    return;
                } else if (firstLockTaken){
                    lock1.unlock();
                } else if (secondLockTaken) {
                    lock2.unlock();
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
