package org.techcrackers.campaign.service;

import org.techcrackers.campaign.dao.Campaign;

import java.util.List;

public interface ClientService {
    /**
     * Retrieves a list of campaigns based on the given identifier.
     *
     * @param id the identifier of the campaign(s) to retrieve.
     * @return a list of campaigns matching the given identifier.
     */
    List<Campaign> get(Long id);
}
