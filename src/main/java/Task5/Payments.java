package Task5;

import java.math.BigDecimal;

public interface Payments {
    void transfer(Account from, Account to, BigDecimal amount);
}
