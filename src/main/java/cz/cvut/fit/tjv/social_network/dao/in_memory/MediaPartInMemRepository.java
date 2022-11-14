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

import cz.cvut.fit.tjv.social_network.dao.MediaPartRepository;
import cz.cvut.fit.tjv.social_network.domain.MediaPart;
import cz.cvut.fit.tjv.social_network.domain.MediaPartKey;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class MediaPartInMemRepository extends InMemoryRepository<MediaPart, MediaPartKey> implements MediaPartRepository {
}
