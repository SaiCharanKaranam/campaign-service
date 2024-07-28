package org.techcrackers.campaign.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techcrackers.campaign.commons.ErrorMessage;
import org.techcrackers.campaign.dao.Campaign;
import org.techcrackers.campaign.dao.Client;
import org.techcrackers.campaign.dto.CampaignDTO;
import org.techcrackers.campaign.exceptions.CampaignException;
import org.techcrackers.campaign.repo.CampaignRepository;
import org.techcrackers.campaign.repo.ClientRepository;
import org.techcrackers.campaign.service.CampaignService;
import org.techcrackers.campaign.service.EmailService;



import java.util.stream.Collectors;

/**
 * Service implementation for managing campaigns.
 */
@Service
public class CampaignServiceImpl implements CampaignService {

     private CampaignRepository campaignRepository;
     private ClientRepository clientRepository;
     private EmailService emailService;
     private ModelMapper mapper;

    /**
     * Constructs a new CampaignServiceImpl with the given dependencies.
     *
     * @param campaignRepository the repository for managing campaigns.
     * @param mapper the ModelMapper for converting between DTOs and entities.
     * @param clientRepository the repository for managing clients.
     * @param emailService the service for sending emails.
     */
    @Autowired
    public CampaignServiceImpl(CampaignRepository campaignRepository, ModelMapper mapper, ClientRepository clientRepository,
                               EmailService emailService) {
        this.campaignRepository = campaignRepository;
        this.mapper = mapper;
        this.clientRepository = clientRepository;
        this.emailService = emailService;
    }

    /**
     * Saves a new campaign or updates an existing campaign.
     *
     * @param request the data transfer object containing campaign details.
     * @return the saved or updated campaign.
     * @throws CampaignException if a client is not found.
     */
    @Override
    public Campaign save(CampaignDTO request) {
        Campaign newCampaign = mapper.map(request, Campaign.class);
        newCampaign.getClients().addAll(request.getClients().stream().map(clientId -> {
            Client client = clientRepository.findById(clientId).orElseThrow(() -> new CampaignException(ErrorMessage.Client_NOT_FOUND));
            if (!client.getCampaigns().contains(newCampaign)) {
                client.getCampaigns().add(newCampaign);
            }
            return client;
        }).collect(Collectors.toList()));

        return campaignRepository.save(newCampaign);
    }

    /**
     * Sends the campaign information to the subscribers.
     *
     * @param id the identifier of the campaign to send.
     * @throws CampaignException if the campaign is not found.
     */
    @Override
    public void send(Long id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new CampaignException(ErrorMessage.CAMPAIGN_NOT_FOUND));
        emailService.sendEmail(campaign);
    }
}
