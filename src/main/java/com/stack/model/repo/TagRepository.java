package com.stack.model.repo;

import com.stack.model.entities.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Integer> {
    Tag findByTag(String tag);
}
