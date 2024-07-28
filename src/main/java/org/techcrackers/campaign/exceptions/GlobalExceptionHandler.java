package org.techcrackers.campaign.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.techcrackers.campaign.commons.Constants;
import org.techcrackers.campaign.dto.CampaignResponse;

/**
 * Global exception handler for handling exceptions across the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles CampaignException thrown by any controller method.
     *
     * @param ex the CampaignException to be handled.
     * @return a ResponseEntity containing a CampaignResponse with error details.
     */
    @ExceptionHandler(CampaignException.class)
    public ResponseEntity<CampaignResponse<?>> handleApiException(CampaignException ex) {
        return new ResponseEntity<>(new CampaignResponse<>(Constants.FAILED, ex.getErrorMessage().getErrorMessage(), null),
                ex.getErrorMessage().getStatus());
    }

}
