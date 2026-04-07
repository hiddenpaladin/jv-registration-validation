package core.basesyntax;

import core.basesyntax.model.User;
import core.basesyntax.service.RegistrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Feel free to remove this class and create your own.
 */
public class RegistrationServiceImplTest {

    @Test
    void registerDone() {
        RegistrationServiceImpl service = new RegistrationServiceImpl();
        User FirstUser = new User();
        FirstUser.setAge(18);
        FirstUser.setLogin("Oksana");
        FirstUser.setPassword("Oksana2005");
        service.register(FirstUser);
        User actualUser = service.getStorageDao().get(FirstUser.getLogin());
        assertSame(actualUser, FirstUser);
    }

    @Test
    void loginAlreadyRegister() {
        RegistrationServiceImpl service = new RegistrationServiceImpl();
        User FirstUser = new User();
        FirstUser.setAge(18);
        FirstUser.setLogin("Oksana");
        FirstUser.setPassword("Oksana2005");
        service.register(FirstUser);
        assertThrows(RegistrationException.class, () -> {
            service.register(FirstUser);
        });
    }

    @Test
    void passwordTooShort() {
        RegistrationServiceImpl service = new RegistrationServiceImpl();
        User FirstUser = new User();
        FirstUser.setAge(18);
        FirstUser.setLogin("Oksana");
        FirstUser.setPassword("Ok25");
        assertThrows(RegistrationException.class, () -> {
            service.register(FirstUser);
        });
    }

    @Test
    void loginTooShort() {
        RegistrationServiceImpl service = new RegistrationServiceImpl();
        User FirstUser = new User();
        FirstUser.setAge(18);
        FirstUser.setLogin("Oksan");
        FirstUser.setPassword("Oksana2005");
        assertThrows(RegistrationException.class, () -> {
            service.register(FirstUser);
        });
    }
}
