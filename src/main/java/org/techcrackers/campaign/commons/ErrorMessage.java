package org.techcrackers.campaign.commons;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;


/**
 * Enum representing error messages used in the application.
 */
@AllArgsConstructor
public enum ErrorMessage {

    CAMPAIGN_NOT_FOUND("1111", "Campaign Not Found", HttpStatus.NOT_FOUND),
    Client_NOT_FOUND("1112", "Client Not Found", HttpStatus.NOT_FOUND),
    UNEXPECTED_ERROR("1113", "Server encounted error, please try again", HttpStatus.INTERNAL_SERVER_ERROR);

    private String errorCode;
    private String errorMessage;
    private HttpStatus status;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
