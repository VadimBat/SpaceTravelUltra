package com.goit.planet;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Planet Entity.
 */
@Data
@Entity
@Table(name = "planet")
public class Planet {

    @Id
    @Pattern(regexp = "^[0-9A-Z]+$", message = "Only numbers and chars in upper case")
    private String id;

    @Column
    @Size(min = 1, max = 500)
    private String name;

}
