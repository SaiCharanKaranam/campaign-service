package org.techcrackers.campaign.service.impl;


import org.springframework.stereotype.Service;
import org.techcrackers.campaign.commons.ErrorMessage;
import org.techcrackers.campaign.dao.Campaign;
import org.techcrackers.campaign.exceptions.CampaignException;
import org.techcrackers.campaign.service.EmailService;

import java.util.List;

/**
 * Service implementation for sending emails related to campaigns.
 */
@Service
public class EmailServiceImpl implements EmailService {

    /**
     * Sends an email to all subscribers of the given campaign.
     *
     * @param campaign the campaign containing the list of subscribers and email details.
     * @throws CampaignException if an error occurs while sending the email.
     */
    @Override
    public void sendEmail(Campaign campaign) {
        List<String> subscriberEmails = campaign.getSubscribers();

        for (String email : subscriberEmails) {
            try {
                            /*
                            * create an Email and set the parameters in the body and subject
                            * send an email
                            * */
            } catch (Exception e) {
               throw new CampaignException(ErrorMessage.UNEXPECTED_ERROR);
            }
        }
    }
}
