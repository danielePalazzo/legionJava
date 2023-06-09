package org.generation.italy.legion.model.data.implementations;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.generation.italy.legion.model.data.JDBCConstants.*;
import static org.generation.italy.legion.model.data.implementations.JDBCTestUtils.findCourseById;
import static org.generation.italy.legion.model.data.implementations.JDBCTestUtils.update;
import static org.generation.italy.legion.model.data.implementations.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class JDBCCourseRepositoryTest {

    private Course c1;
    private Course c2;
    private Course c3;
    private List<Course> courses;
    private Connection con;
    private JDBCCourseRepository repo;

    @BeforeEach
    void setUp() throws SQLException {
        c1 = new Course(0, TITLE1, DESCRIPTION, PROGRAM, DURATION, IS_ACTIVE, CREATED_AT);
        c2 = new Course(0, TITLE2, DESCRIPTION2, PROGRAM2, DURATION2, IS_ACTIVE, CREATED_AT.plusDays(1));
        c3 = new Course(0, TITLE3, DESCRIPTION3, PROGRAM3, DURATION3, IS_ACTIVE, CREATED_AT.plusMonths(2));
        courses = List.of(c1,c2,c3);
        con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        con.setAutoCommit(false);
        //courses.forEach(c -> insertCourse(c,con));
        courses.forEach((c ->{
            int key = update(INSERT_COURSE_RETURNING_ID, con, true,c.getTitle(),
                    c.getDescription(), c.getProgram(), c.getDuration(), c.isActive(),
                    Date.valueOf(c.getCreatedAt()));
            c.setId(key);
        }));
        repo = new JDBCCourseRepository(con);
    }

    @AfterEach
    void tearDown() {
        try {
            con.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByTitleContains() {
    }

    @Test
    void create() {
    }

    @Test
    void update2() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void countActiveCourses() {
    }

    @Test
    void deactivateOldest() {
        try {
            repo.deactivateOldest(2);
            assertFalse(findCourseById(c1.getId(), con).get().isActive());
            assertFalse(findCourseById(c2.getId(), con).get().isActive());
            assertTrue(findCourseById(c3.getId(), con).get().isActive());

        } catch (DataException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void adjustActiveCourses() {
    }
}