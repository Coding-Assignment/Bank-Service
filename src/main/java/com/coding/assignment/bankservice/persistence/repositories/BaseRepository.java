package com.coding.assignment.bankservice.persistence.repositories;

import com.coding.assignment.bankservice.persistence.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, UUID> {

}
