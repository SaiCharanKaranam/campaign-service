package org.techcrackers.campaign.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.techcrackers.campaign.dao.Client;


/**
 * Repository interface for managing client entities.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
