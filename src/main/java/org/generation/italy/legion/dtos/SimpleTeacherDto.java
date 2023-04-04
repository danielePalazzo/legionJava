package org.generation.italy.legion.dtos;

import org.generation.italy.legion.model.entities.Competence;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Sex;
import org.generation.italy.legion.model.entities.Teacher;

import java.util.Optional;
import java.util.stream.StreamSupport;

public class SimpleTeacherDto {
    private long id;
    private String firstname;
    private String lastname;
    private Sex sex;
    private String pIVA;
    private Level level;
    private String skill;
    private long skillId;
    private Level skillLevel;

    public SimpleTeacherDto(long id, String firstname, String lastname, Sex sex, String pIVA, Level level, String skill, long skillId, Level skillLevel) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.sex = sex;
        this.pIVA = pIVA;
        this.level = level;
        this.skill = skill;
        this.skillId = skillId;
        this.skillLevel = skillLevel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getpIVA() {
        return pIVA;
    }

    public void setpIVA(String pIVA) {
        this.pIVA = pIVA;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    public Level getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(Level skillLevel) {
        this.skillLevel = skillLevel;
    }

    public static SimpleTeacherDto fromEntity(Teacher t, long skillId){
        Optional<Competence> oc = t.getCompetenceForSkill(skillId);
        Competence c = oc.get();
        String skillName = c.getSkill().getName();
        Level level = c.getLevel();
        return new SimpleTeacherDto(t.getId(), t.getFirstname(), t.getLastname(), t.getSex(), t.getpIVA(), t.getLevel(),
                skillName, skillId, level);
    }

    public static Iterable<SimpleTeacherDto> fromEntityIterator(Iterable<Teacher> t, long skillId){
        return StreamSupport.stream(t.spliterator(), false)
                .map(s -> SimpleTeacherDto.fromEntity(s, skillId)).toList();
    }
}
