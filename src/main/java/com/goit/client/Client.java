package com.goit.client;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Client Entity.
 */
@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

}


