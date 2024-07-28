package org.techcrackers.campaign.service;

import org.techcrackers.campaign.dao.Campaign;

/**
 * Service interface for sending emails related to campaigns.
 */
public interface EmailService {

    /**
     * Sends an email to all subscribers of the given campaign.
     *
     * @param campaign the campaign containing the list of subscribers and email details.
     */
    void sendEmail(Campaign campaign);
}
