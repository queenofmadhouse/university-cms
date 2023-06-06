package com.foxminded.universitycms.testconfiguration;

import com.foxminded.universitycms.repository.CourseRepository;
import com.foxminded.universitycms.repository.GroupRepository;
import com.foxminded.universitycms.repository.ScheduleRepository;
import com.foxminded.universitycms.service.impl.ScheduleServiceImpl;
import com.foxminded.universitycms.service.impl.StudentServiceImpl;
import com.foxminded.universitycms.service.impl.TeacherServiceImpl;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DataJpaTest
@ComponentScan(basePackages = {"com.foxminded.universitycms.repository", "com.foxminded.universitycms.service.impl"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Sql(
        scripts = "/sql/schedule-test-data.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public @interface IntegrationTest {
}
