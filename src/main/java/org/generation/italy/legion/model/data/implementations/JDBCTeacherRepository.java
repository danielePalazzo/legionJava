package org.generation.italy.legion.model.data.implementations;

import org.generation.italy.legion.model.data.abstractions.TeacherRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.generation.italy.legion.model.data.JDBCTeacherConstants.FIND_TEACHER_BY_LEVEL;

@Repository
@Profile("jdbc")
public class JDBCTeacherRepository {
    private Connection con;
    public JDBCTeacherRepository(Connection con) {this.con = con;}

    public Iterable<Teacher> findByLevel(Level teacherLevel) throws DataException {
        try(PreparedStatement ps = con.prepareStatement(FIND_TEACHER_BY_LEVEL)) {
            ps.setObject(1, teacherLevel, Types.OTHER);
            try (ResultSet rs = ps.executeQuery()) {
                List<Teacher> teacherList = new ArrayList<>();
                while (rs.next()) {
                    teacherList.add(teacherRawMapper(rs));
                }
                return teacherList;
            }
        } catch (SQLException e) {
            throw new DataException("Errore nella ricerca di docente a partire dal livello",e);
        }
    }

    public Iterable<Teacher> findWithSkillAndLevel(long idSkill, Level competenceLevel) {
        return null;
    }

    private Teacher teacherRawMapper(ResultSet rs) {
        return null; // da scrivere ma c'è il barbatrucco
    }

    public List<Teacher> findAll() throws DataException {
        return null;
    }

    public Optional<Teacher> findById(long id) throws DataException {
        return Optional.empty();
    }

    public Teacher create(Teacher entity) throws DataException {
        return null;
    }

    public void update(Teacher entity) throws EntityNotFoundException, DataException {

    }

    public void deleteById(long id) throws EntityNotFoundException, DataException {

    }
}
