package org.generation.italy.legion.dtos;

import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Sex;
import org.generation.italy.legion.model.entities.Teacher;

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
                t.getCellNumber(), t.getUsername(), t.getPassword(), t.getpIVA(), t.isEmployee(), t.getHireDate().toString(),
                t.getFireDate().toString(), t.getLevel());
    }
}
