package Task5;

import java.math.BigDecimal;

/**
 * Перевод без дедлока(считай банковский). Класс Account с полем long id и балансом; метод transfer(from, to, amount),
 * вызываемый встречно из многих потоков (from/to меняются местами). Реализуй без дедлока, списание и зачисление атомарны
 */
public class Account implements Comparable<Account>{

    private final Long id;

    private volatile BigDecimal balance;

    public Account(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public int compareTo(Account o) {
        return Long.compare(this.id, o.getId());
    }
}
