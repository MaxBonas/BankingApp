package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositories.accounts.CreditCardRepository;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.CreditCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreditCardService implements CreditCardServiceInterface {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Override
    public CreditCard getCreditCardById(Long id) {
        return creditCardRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A Credit Card with the given id doesn't exist"));
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }
}
