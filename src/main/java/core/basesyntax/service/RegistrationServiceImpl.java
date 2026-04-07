package core.basesyntax.service;

import core.basesyntax.RegistrationException;
import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    public StorageDao getStorageDao() {
        return storageDao;
    }

    @Override
    public User register(User user) {
        for (User users : Storage.people) {
            if (users.equals(user)) {
                throw new RegistrationException("this Login already register");
            }
        }

        if (user.getLogin().toCharArray().length < 6) {
            throw new RegistrationException("this Login too short");
        }

        if (user.getPassword().toCharArray().length < 6) {
            throw new RegistrationException("this Password too short");
        }

        if (user.getAge() < 18) {
            throw new RegistrationException("you need be at least 18 years old to register");
        }

        storageDao.add(user);
        return user;
    }
}
