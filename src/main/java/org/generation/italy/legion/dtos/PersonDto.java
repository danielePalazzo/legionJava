package org.generation.italy.legion.dtos;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import org.generation.italy.legion.model.entities.Address;
import org.generation.italy.legion.model.entities.Competence;
import org.generation.italy.legion.model.entities.Sex;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.Set;

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

    public PersonDto(long id, String firstname, String lastname, String dob, Sex sex, String email, String cellNumber,
                     String username, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.sex = sex;
        this.email = email;
        this.cellNumber = cellNumber;
        this.username = username;
        this.password = password;
    }
}
