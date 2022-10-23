package PaloosaBank.OnlineBanking.services;

import PaloosaBank.OnlineBanking.entities.Transfer;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;

import java.math.BigDecimal;
import java.util.List;

public interface TransferServiceInterface {

    List<Transfer> findBySenderAccountId (Long id);
    List<Transfer> findByPrimaryOwnerId (Long id);
    void addTransfer(Account senderAccount, String receiverName, AccountHolder primaryOwner, BigDecimal amount);
    void checkFraudLessThanSecond(Transfer transfer);
}
