package Task5;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountPayments implements Payments{

    private final Lock lock = new ReentrantLock();

    @Override
    public void transfer(Account from, Account to, BigDecimal amount) {
        synchronized (lock) {
            BigDecimal newBalanceFromAccount1 = from.getBalance().subtract(amount);
            if(newBalanceFromAccount1.intValue() <= 0){
                throw new NotEnoughFundsToWriteOffException("Не достаточно средств, баланс - " + newBalanceFromAccount1);
            }
            from.setBalance(newBalanceFromAccount1);
            BigDecimal newBalanceToAccount2 = to.getBalance().add(amount);
            to.setBalance(newBalanceToAccount2);
        }
    }
}
