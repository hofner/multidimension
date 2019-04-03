package com.inc.jmosprograms.multidimension.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inc.jmosprograms.multidimension.entity.MelateContinua;

/**
 * @author Juan Miguel Olguin Salguero
 *
 */
@Repository
public interface MelateContinuaRepository extends JpaRepository<MelateContinua, Integer> {

}