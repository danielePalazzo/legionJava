package org.generation.italy.legion.model.data.implementations;

import org.generation.italy.legion.model.data.abstractions.CourseRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.generation.italy.legion.model.data.JDBCConstants.*;


@Repository
@Profile("jdbc")
public class JDBCCourseRepository implements CourseRepository {
    /*public static int askToClient;
    static{
        System.out.println("inizializzazione statica");
        Driver d=new Driver();
        try {
            DriverManager.registerDriver(d);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    private Connection con;
    @Autowired
    public JDBCCourseRepository(Connection connection) {
        this.con = connection;
    }


    @Override
    public <S extends Course> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Course> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Course> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Course> findAll(){
        try (
             Statement st = con.createStatement();//factory method pattern
             ResultSet rs = st.executeQuery(COURSE_QUERY);
        ) {
            //Class.forName("org.postgresql.Driver");   //hack
            List<Course> courseList = new ArrayList<>();
            while (rs.next()) {
                courseList.add(databaseToCourse(rs));
            }
            return courseList;
            /*for(Course course: courseList){
                System.out.println(course.getTitle());
            }*/
            //courseList.forEach(c-> System.out.println(c.getTitle()));
            //courseList.forEach(System.out::println);
            /*courseList.stream().map(Course::getTitle)
                               .forEach(System.out::println);*/
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("errore nella lettura dei corsi da database", e);
        }

    }

    @Override
    public List<Course> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Course entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Course> entities) {

    }

    @Override
    public void deleteAll() {

    }

    public Optional<Course> findById(long id) throws DataException {
        try (
             PreparedStatement st = con.prepareStatement(FIND_COURSE_BY_ID);//factory method pattern
        ) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(databaseToCourse(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException("errore nella lettura dei corsi da database", e);
        }
    }

    @Override
    public List<Course> findByTitleContains(String part) throws DataException {
        try (
             PreparedStatement st = con.prepareStatement(FIND_BY_TITLE_CONTAINS);//factory method pattern
        ) {
            st.setString(1, "%" + part + "%");
            try (ResultSet rs = st.executeQuery()) {
                List<Course> courseList = new ArrayList<>();
                while (rs.next()) {
                    courseList.add(databaseToCourse(rs));
                }
                return courseList;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException("errore nella lettura dei corsi da database", e);
        }
    }

    public Course create(Course course) throws DataException {
        try (
             PreparedStatement st = con.prepareStatement(INSERT_COURSE);//factory method pattern
             Statement st2 = con.createStatement();//factory method pattern
             ResultSet rs = st2.executeQuery(NEXT_VAL_COURSE);
        ) {
            rs.next();
            long nextVal = rs.getLong("nextval");
            course.setId(nextVal);
            st.setLong(1, course.getId());
            st.setString(2, course.getTitle());
            st.setString(3, course.getDescription());
            st.setString(4, course.getProgram());
            st.setDouble(5, course.getDuration());
            st.setBoolean(6, course.isActive());
            st.setDate(7, Date.valueOf(course.getCreatedAt()));
            st.executeUpdate();
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException("errore nell'insermiento del corso", e);
        }

    }
    public Course create2(Course course) throws DataException {
        try (
             PreparedStatement st = con.prepareStatement(INSERT_COURSE_RETURNING_ID,Statement.RETURN_GENERATED_KEYS);//factory method pattern
        ) {
            st.setString(1, course.getTitle());
            st.setString(2, course.getDescription());
            st.setString(3, course.getProgram());
            st.setDouble(4, course.getDuration());
            st.setBoolean(5, course.isActive());
            st.setDate(6, Date.valueOf(course.getCreatedAt()));
            st.executeUpdate();
            try (ResultSet keys = st.getGeneratedKeys()){
                keys.next();
                long key = keys.getLong(1);
                course.setId(key);
                return course;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException("errore nell'insermiento del corso", e);
        }

    }

    public void update(Course course) throws EntityNotFoundException, DataException {
        try (
             PreparedStatement st = con.prepareStatement(UPDATE_COURSE)){
            st.setString(1, course.getTitle());
            st.setString(2, course.getDescription());
            st.setString(3, course.getProgram());
            st.setDouble(4, course.getDuration());
            st.setBoolean(5, course.isActive());
            st.setDate(6, Date.valueOf(course.getCreatedAt()));
            st.setLong(7, course.getId());
            int numLines = st.executeUpdate();
            if (numLines != 1){
                throw new EntityNotFoundException("Non e' stato trovato il corso con quell'id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException("errore nella lettura dei corsi da database", e);
        }

    }

    public void deleteById(long id) throws EntityNotFoundException, DataException {
        try (
             PreparedStatement st = con.prepareStatement(DELETE_COURSE_BY_ID);//factory method pattern
        ) {
            st.setLong(1, id);
            int numLines = st.executeUpdate();
            if (numLines != 1) {
                throw new EntityNotFoundException("Non e' stato trovato il corso con quell'id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException("errore nella lettura dei corsi da database", e);
        }
    }

    @Override
    public int countActiveCourses() throws DataException{
        try (
             Statement st = con.createStatement();//factory method pattern
             ResultSet rs = st.executeQuery(ACTIVE_COURSES)){
                 rs.next();
                 return rs.getInt(1);

        }catch (SQLException e) {
            e.printStackTrace();
            throw new DataException("errore nella lettura dei corsi da database", e);
        }

    }

    @Override
    public void deactivateOldest(int n) throws DataException {
        try(
            PreparedStatement st = con.prepareStatement(DEACTIVATE_OLDEST_N_COURSES)) {
            st.setInt(1, n);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException("Errore nella lettura dei corsi da database", e);
        }
    }

    @Override
    public boolean adjustActiveCourses(int NumActive) throws DataException {
        return false;
    }

    @Override
    public Iterable<Course> findByTitleAndIsActiveAndMinEdition(String part, boolean status, int minEditions) throws DataException {
        return null;
    }

    @Override
    public Iterable<Course> findByTitleContainingAndActiveTrue(String part) throws DataException {
        return null;
    }

    @Override
    public Iterable<Course> findByTitleAndMinEdition(String part, int minEditions) throws DataException {
        return null;
    }


    private Course databaseToCourse(ResultSet rs) throws SQLException {
        try {
            return new Course(rs.getLong("id_course"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("program"),
                    rs.getDouble("duration"),
                    rs.getBoolean("is_active"),
                    rs.getDate("created_at").toLocalDate());
        } catch (SQLException e) {
            throw new SQLException("errore nella lettura dei corsi da database", e);
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Course> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Course> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Course> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Course getOne(Long aLong) {
        return null;
    }

    @Override
    public Course getById(Long aLong) {
        return null;
    }

    @Override
    public Course getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Course> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Course> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Course> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Course> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Course> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Course> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Course, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Course> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Course> findAll(Pageable pageable) {
        return null;
    }
}
