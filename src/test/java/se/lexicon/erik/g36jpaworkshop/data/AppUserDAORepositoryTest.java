package se.lexicon.erik.g36jpaworkshop.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.erik.g36jpaworkshop.model.AppUser;
import se.lexicon.erik.g36jpaworkshop.model.Details;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class AppUserDAORepositoryTest {

    @Autowired private TestEntityManager em;
    @Autowired private AppUserDAORepository testObject;

    public List<AppUser> appUsers(){
        return Collections.singletonList(
                new AppUser(0, "nisse", "1234", LocalDate.parse("2010-09-11"), new Details(0, "nisse@gmail.com", "Nils Svensson", LocalDate.parse("1999-12-31")))
        );
    }

    List<AppUser> persistedAppUsers;

    @BeforeEach
    void setUp() {
        persistedAppUsers = appUsers().stream()
                .map(em::persist)
                .collect(Collectors.toList());
    }

    @Test
    void create() {
        AppUser appUser = new AppUser(0, "terminator", "hastalavistababy", LocalDate.parse("1999-01-01"), new Details(0, "t-100@skynet.org", "Arnold Schwarzenegger", LocalDate.parse("1947-04-14")));

        AppUser result = testObject.create(appUser);

        assertNotNull(result);
        assertNotEquals(0, result.getAppUserId());
    }


}