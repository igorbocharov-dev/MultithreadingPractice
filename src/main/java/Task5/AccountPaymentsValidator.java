package Task5;

import java.math.BigDecimal;

public final class AccountPaymentsValidator {

    public void validateAccount(Account account){
        if(account == null) {
            throw new IllegalArgumentException("Значение 'account' не должно быть null");
        }
    }

    public void validateDeductionFromAccount(Account from, BigDecimal amount){
        BigDecimal newBalanceFromAccount1 = from.getBalance().subtract(amount);
        if(newBalanceFromAccount1.intValue() < 0){
            throw new NotEnoughFundsToWriteOffException
                    ("Не достаточно средств для списания со счета, баланс аккаунта: " + from.getBalance());
        }
    }

    public void validateAmount(BigDecimal amount){
        if(amount == null || amount.intValue() < 0) {
            throw new IllegalArgumentException("Значение 'amount' не должно быть null или меньше 0");
        }
    }
}
