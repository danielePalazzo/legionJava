package org.generation.italy.legion.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Sex;
import org.generation.italy.legion.model.entities.Teacher;
import static org.generation.italy.legion.utilities.StringUtilities.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto extends PersonDto{
    private String pIVA;
    private boolean isEmployee;
    private String hireDate;
    private String fireDate;
    private Level level;

    public TeacherDto(long id, String firstname, String lastname, String dob, Sex sex, String email, String cellNumber,
                      String username, String password, String pIVA, boolean isEmployee, String hireDate, String fireDate, Level level) {
        super(id, firstname, lastname, dob, sex, email, cellNumber, username, password);
        this.pIVA = pIVA;
        this.isEmployee = isEmployee;
        this.hireDate = hireDate;
        this.fireDate = fireDate;
        this.level = level;
    }

    public static TeacherDto fromEntity(Teacher t){
        return new TeacherDto(t.getId(), t.getFirstname(), t.getLastname(), t.getDob().toString(), t.getSex(), t.getEmail(),
                t.getCellNumber(), t.getUsername(), t.getPassword(), t.getPIVA(), t.isEmployee(),
                t.getHireDate() != null? t.getHireDate().toString() : "",
                t.getFireDate() != null? t.getFireDate().toString() : "", t.getLevel());
    }

    public Teacher toEntity(){
        return new Teacher(this.id,this.getFirstname(),this.getLastname(),fromJSONString(this.getDob()),
                this.getSex(),this.getEmail(),this.getCellNumber(),null,
                this.getUsername(),this.getPassword(),null,this.getPIVA(),this.isEmployee(),
                fromJSONString(this.getHireDate()),fromJSONString(this.getFireDate()),this.getLevel());
    }

}
