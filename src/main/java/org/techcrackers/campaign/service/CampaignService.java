package org.techcrackers.campaign.service;

import org.techcrackers.campaign.dao.Campaign;
import org.techcrackers.campaign.dto.CampaignDTO;



/**
 * Service interface for managing campaigns.
 */
public interface CampaignService {

    /**
     * Saves a new campaign or updates an existing campaign.
     *
     * @param campaignDto the data transfer object containing campaign details.
     * @return the saved or updated campaign.
     */
    Campaign save(CampaignDTO campaignDto);

    /**
     * Sends the campaign information to the subscribers.
     *
     * @param id the identifier of the campaign to send.
     */
    void send(Long id);
}
