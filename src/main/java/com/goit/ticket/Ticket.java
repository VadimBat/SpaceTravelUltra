package com.goit.ticket;

import com.goit.client.Client;
import com.goit.planet.Planet;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Space travel ticket entity.
 * Every ticket includes client, from and to travel planet.
 */
@Data
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet fromPlanetId;

    @ManyToOne
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet toPlanetId;

}
