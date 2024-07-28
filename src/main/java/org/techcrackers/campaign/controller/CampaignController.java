package org.techcrackers.campaign.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.techcrackers.campaign.commons.Constants;
import org.techcrackers.campaign.dao.Campaign;
import org.techcrackers.campaign.dto.CampaignDTO;
import org.techcrackers.campaign.dto.CampaignResponse;
import org.techcrackers.campaign.service.CampaignService;


/**
 * REST controller for managing campaign-related operations.
 */
@RestController
@RequestMapping("/api/campaign")
public class CampaignController {

    private final CampaignService campaignService;


    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    /**
     * Saves a new campaign.
     *
     * @param campaign the campaign data transfer object containing the details of the campaign to be saved.
     * @return a ResponseEntity containing a CampaignResponse with the saved campaign.
     */
    @PostMapping
    private ResponseEntity<CampaignResponse<Campaign>> saveCampaign(@RequestBody CampaignDTO campaign) {
        return new ResponseEntity<>(new CampaignResponse<>(Constants.SUCCESS, null,
                campaignService.save(campaign)), HttpStatus.OK);
    }

    /**
     * Sends a campaign to its subscribers.
     *
     * @param campaignId the ID of the campaign to be sent.
     * @return a ResponseEntity containing a CampaignResponse with the status of the operation.
     */
    @PostMapping(path = "{campaignId}/send")
    private ResponseEntity<CampaignResponse<Campaign>> sendCampaign(@PathVariable("campaignId") Long campaignId) {
        campaignService.send(campaignId);
        return new ResponseEntity<>(new CampaignResponse<>(Constants.SUCCESS, null,
                null), HttpStatus.OK);
    }
}
