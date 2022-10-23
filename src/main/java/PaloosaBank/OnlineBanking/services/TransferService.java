package PaloosaBank.OnlineBanking.services;

import PaloosaBank.OnlineBanking.entities.Transfer;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.enums.Status;
import PaloosaBank.OnlineBanking.repositories.TransferRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
public class TransferService implements TransferServiceInterface {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Transfer> findBySenderAccountId(Long id) {
        return transferRepository.findBySenderAccountId(id);
    }

    @Override
    public List<Transfer> findByPrimaryOwnerId(Long id) {
        return transferRepository.findByPrimaryOwnerId(id);
    }

    public List<Transfer> findByAmount(BigDecimal amount) {
        return transferRepository.findByAmount(amount);
    }

    public List<Transfer> findByTransferDate(LocalDate transferDate) {
        return transferRepository.findByTransferDate(transferDate);
    }

    public List<Transfer> findByTransferTime(LocalTime transferTime) {
        return transferRepository.findByTransferTime(transferTime);
    }

    public void addTransfer(Account senderAccount, String receiverName, AccountHolder primaryOwner, BigDecimal amount) {
        Transfer transfer = new Transfer(senderAccount, receiverName, primaryOwner, amount);

        checkFraudLessThanSecond(transfer);

        transferRepository.save(transfer);
    }

    public void checkFraudLessThanSecond(Transfer transfer) {
        Account senderAccount = transfer.getSenderAccount();
        for (Transfer transfer1 : transferRepository.findAll()) {
            if (transfer.getTransferTime().getSecond() == transfer1.getTransferTime().getSecond()) {
                if (transfer.getTransferDate().isEqual(transfer1.getTransferDate())) {
                    if (Objects.equals(transfer.getSenderAccount().getId(), transfer1.getSenderAccount().getId())) {

                        senderAccount.setStatus(Status.FROZEN);
                        accountRepository.save(senderAccount);
                        throw new ResponseStatusException(HttpStatus.TOO_EARLY,
                                "Your Account was Frozen because fraud suspicions. Contact with your PaloosaBank Office.");
                    }
                }
            }
        }
    }
}


//    public void  checkFraudTooMuch24h(Transfer transfer) {
//        for (Transfer transfer2 : transferRepository.findAll()) {
//            if (transfer2.getTransferDate() == transfer2.getTransferDate()) {
//
//
//            }
//
//        }
//
//    }
//}
//    public Money keepDailySpended(Transfer transfer) {
//        Money dailySpended = new Money(transfer.getAmount());
////        for (Transfer transfer1 : transferRepository.findAll()) {
//        for (Transfer transfer1 : findByTransferDate(transfer.getTransferDate())) {
//
//        }
//    }