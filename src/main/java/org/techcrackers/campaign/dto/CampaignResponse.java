package org.techcrackers.campaign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A generic response class for campaign-related API responses.
 *
 * @param <T> the type of the data contained in the response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignResponse<T> {
    private String status;
    private String errorMessage;
    private T data;
}
