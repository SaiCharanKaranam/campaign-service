package org.techcrackers.campaign.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a client.
 */
@Getter
@Setter
@Entity
@Table(name = "clients", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * The list of campaigns associated with the client.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "campaigns_by_clients",
    joinColumns = {
            @JoinColumn(name = "client_id", referencedColumnName = "id")
    },
            inverseJoinColumns = {
            @JoinColumn(name="campaign_id", referencedColumnName = "id")
            }
    )
    @JsonManagedReference
    private List<Campaign> campaigns = new ArrayList<>();

}
