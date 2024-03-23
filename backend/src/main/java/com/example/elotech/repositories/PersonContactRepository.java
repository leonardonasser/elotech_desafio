package com.example.elotech.repositories;

import com.example.elotech.models.PersonContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonContactRepository extends JpaRepository<PersonContactModel, Long> {
}
