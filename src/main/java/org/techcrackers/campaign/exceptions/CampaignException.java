package org.techcrackers.campaign.exceptions;

import lombok.Data;
import org.techcrackers.campaign.commons.ErrorMessage;

@Data
public class CampaignException extends RuntimeException {
    private ErrorMessage errorMessage;

    public CampaignException(ErrorMessage message) {
        this.errorMessage = message;
    }
}
