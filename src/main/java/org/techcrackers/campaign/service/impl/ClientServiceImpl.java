package org.techcrackers.campaign.service.impl;

import org.springframework.stereotype.Service;
import org.techcrackers.campaign.commons.ErrorMessage;
import org.techcrackers.campaign.dao.Campaign;
import org.techcrackers.campaign.dao.Client;
import org.techcrackers.campaign.exceptions.CampaignException;
import org.techcrackers.campaign.repo.ClientRepository;
import org.techcrackers.campaign.service.ClientService;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Retrieves a list of campaigns associated with the given client identifier.
     *
     * @param id the identifier of the client.
     * @return a list of campaigns associated with the given client identifier.
     * @throws CampaignException if the client is not found.
     */
    @Override
    public List<Campaign> get(Long id) {
        Client client =  clientRepository.findById(id)
                .orElseThrow(()->new CampaignException(ErrorMessage.Client_NOT_FOUND));
        return client.getCampaigns();
    }
}
