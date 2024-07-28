package org.techcrackers.campaign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.techcrackers.campaign.commons.Constants;
import org.techcrackers.campaign.dao.Campaign;
import org.techcrackers.campaign.dto.CampaignResponse;
import org.techcrackers.campaign.service.ClientService;

import java.util.List;

/**
 * REST controller for managing client-related operations.
 */
@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;


    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Retrieves the list of campaigns associated with a given client ID.
     *
     * @param clientId the ID of the client whose campaigns are to be retrieved.
     * @return a ResponseEntity containing a CampaignResponse with the list of campaigns.
     */
    @GetMapping(path = "/campaign/{clientId}")
    private ResponseEntity<CampaignResponse<List<Campaign>>> getCampaigns(@PathVariable("clientId") Long clientId) {
        return new ResponseEntity<>(new CampaignResponse<>(Constants.SUCCESS, null,
                clientService.get(clientId)), HttpStatus.OK);
    }
}
