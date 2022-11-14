/*
 * Copyright (c) 2022. Czech Technical University in Prague, Faculty of Information Technology.
 *
 * Project Social Network. Created for Java Technology course.
 *
 *  Authors:
 *  Ondřej Guth (ondrej.guth@fit.cvut.cz)
 *  Jan Blizničenko (jan.bliznicenko@fit.cvut.cz)
 *  Filip Glazar (glazafil@fit.cvut.cz)
 *
 *  This code is intended for educational purposes only.
 *
 */

package cz.cvut.fit.tjv.social_network.dao.in_memory;

import cz.cvut.fit.tjv.social_network.dao.CrudRepository;
import cz.cvut.fit.tjv.social_network.domain.DomainEntity;
import cz.cvut.fit.tjv.social_network.domain.GenerateValueWhenSave;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

//TODO correct implementation: now objects are not linked

/**
 * Data layer implemented as in-memory-only storage, supports CRUD.
 * @param <T> entity type
 * @param <ID> primary key type
 */
public abstract class InMemoryRepository<T extends DomainEntity<ID>, ID> implements CrudRepository<T, ID> {
    private final transient Random rnd = new Random();
    private final Map<ID, T> database = new HashMap<>();
    @Override
    public void deleteById(ID id) {
        database.remove(id);
    }

    @Override
    public boolean existsById(ID id) {
        return database.containsKey(id);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public T save(T entity) {
        for (var m : entity.getClass().getMethods()) {
            if (m.getAnnotation(GenerateValueWhenSave.class) != null && m.getName().startsWith("set")) {
                try {
                    m.invoke(entity, rnd.nextLong()); //UUID suits better the purpose; however, it would complicate integration with SQL DB
                } catch (IllegalAccessException|IllegalArgumentException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        ID id = entity.getId();
        database.put(id, entity);
        return entity;
    }

    @Override
    public Collection<T> findAll() {
        return database.values();
    }
}
