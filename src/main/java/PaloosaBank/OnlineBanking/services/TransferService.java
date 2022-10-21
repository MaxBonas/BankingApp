package PaloosaBank.OnlineBanking.services;

import PaloosaBank.OnlineBanking.entities.Transfer;
import PaloosaBank.OnlineBanking.entities.users.User;
import PaloosaBank.OnlineBanking.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransferService implements TransferServiceInterface{

    @Autowired
    TransferRepository transferRepository;

    @Override
    public List<Transfer> findBySenderAccountId(Long id) {
        return transferRepository.findBySenderAccountId(id);
    }

    @Override
    public List<Transfer> findByPrimaryOwnerId(Long id) {
        return transferRepository.findByPrimaryOwnerId(id);
    }

}
