package org.techcrackers.campaign.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data transfer object for campaign details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDTO {

    private Long id;
    @NotEmpty(message = "campaign name cannot be empty")
    @Size(min = 5, max = 30, message = "campaign name must be between 5 to 30 long")
    private String name;
    @NotEmpty(message = "subject cannot be empty")
    @Size(min = 5, max = 50, message = "subject must be between 5 to 50 long")
    private String subject;
    private String status = "ACTIVE";
    @NotEmpty(message = "email body cannot be empty")
    @Size(min = 150, max = 1000, message = "email must be between 150 to 1000 long")
    private String emailBody;
    private List<String> subscribers;
    @NotEmpty(message = "campaign must be associated with at least one client")
    private List<Long> clients;

}