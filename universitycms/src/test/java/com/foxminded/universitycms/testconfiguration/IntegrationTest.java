package com.foxminded.universitycms.testconfiguration;

import com.foxminded.universitycms.repository.CourseRepository;
import com.foxminded.universitycms.repository.GroupRepository;
import com.foxminded.universitycms.repository.ScheduleRepository;
import com.foxminded.universitycms.service.impl.ScheduleServiceImpl;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        ScheduleRepository.class,
        ScheduleServiceImpl.class,
        CourseRepository.class,
        GroupRepository.class
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Sql(
        scripts = "/sql/schedule-test-data.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public @interface IntegrationTest {
}
