package org.generation.italy.legion.dtos;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.legion.model.entities.Address;
import org.generation.italy.legion.model.entities.Competence;
import org.generation.italy.legion.model.entities.Sex;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class PersonDto {
    protected long id;
    protected String firstname;
    protected String lastname;
    protected String dob;
    protected Sex sex;
    protected String email;
    protected String cellNumber;
    protected String username;
    protected String password;

}
