package PaloosaBank.OnlineBanking.services;

import PaloosaBank.OnlineBanking.entities.Transfer;

import java.util.List;

public interface TransferServiceInterface {

    List<Transfer> findBySenderAccountId (Long id);
    List<Transfer> findByPrimaryOwnerId (Long id);
}
