package Task5;

import java.math.BigDecimal;

/**
 * Перевод без дедлока(считай банковский). Класс Account с полем long id и балансом; метод transfer(from, to, amount),
 * вызываемый встречно из многих потоков (from/to меняются местами). Реализуй без дедлока, списание и зачисление атомарны
 */
public class Account {

    private Long id;

    private BigDecimal balance;

    public Account(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
