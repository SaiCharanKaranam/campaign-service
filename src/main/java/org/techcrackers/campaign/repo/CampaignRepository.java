package org.techcrackers.campaign.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.techcrackers.campaign.dao.Campaign;


/**
 * Repository interface for managing Campaign entities.
 */
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
