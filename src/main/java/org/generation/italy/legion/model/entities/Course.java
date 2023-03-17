package org.generation.italy.legion.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course")    //annotazione di runtime,rimane nel bytecode
public class Course implements Serializable {
    @Id
    @GeneratedValue(generator = "course_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "course_generator", sequenceName = "course_sequence", allocationSize = 1)
    @Column(name = "id_course")   //nome colonna lato DB
    private long id;
    @NotBlank(message = "il corso deve specificare un titolo")
    private String title;
    private String description;
    private String program;
    //@NotBlank(message = "la durata è obbligatoria") da rivedere
    private double duration;
    //private static final long serialVersionUID = 1;
    @Column(name = "is_active")
    private boolean active;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<CourseEdition> editions;

    public Course() {
        this.createdAt = LocalDate.now();
    }
    public Course(long id, String title, String description, String program, double duration, LocalDate createdAt) {
        this(id, title, description, program, duration, true, createdAt);
    }

    public Course(long id, String title, String description, String program, double duration, boolean active, LocalDate createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.program = program;
        this.duration = duration;
        this.active = active;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getProgram() {
        return program;
    }

    public double getDuration() {
        return duration;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public List<CourseEdition> getEditions() {
        return editions;
    }

    public boolean isActive() {
        return active;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean deactivate(){
        boolean wasActive = active;
        active = false;
        return active != wasActive;
    }
    public void setEditions(List<CourseEdition> editions) {
        this.editions = editions;
    }

    //override del metodo madre Object toString() e lo facciamo meglio
    @Override
    public String toString() {
//        return "Course{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", program='" + program + '\'' +
//                ", duration=" + duration +
//                '}';
//        ritorna la stessa cosa, anzi è fatta meglio
        return String.format("Course{id=%d, title=%s, description=%s, program=%s, duration=%f, isActive = %b, createdAt = %s}",
                id,title,description,program,duration, active, createdAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Course course = (Course) o;     //DownCast
        return getId() == course.getId() && Double.compare(course.getDuration(), getDuration()) == 0 && getTitle().equals(course.getTitle()) && Objects.equals(getDescription(), course.getDescription()) && Objects.equals(getProgram(), course.getProgram());
    }
    //!! equals e hashCode devono stare sempre insieme (sono bff)
    @Override
    public int hashCode() {
        return Objects.hash(getId());       //modifica l'hashCode in base all'equals
    }
}
