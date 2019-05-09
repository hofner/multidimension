package com.inc.jmosprograms.multidimension.repository;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inc.jmosprograms.multidimension.entity.Melate;
import com.inc.jmosprograms.multidimension.entity.MelateContinua;
import com.inc.jmosprograms.multidimension.vo.MelateVoContainers;

@Component
public class PipelineDAO {
	@Autowired
	MelateRepository melateRepository;
	@Autowired
	MelateContinuaRepository melateContinuaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void truncateTables() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaDelete<Melate> query = builder.createCriteriaDelete(Melate.class);
		query.from(Melate.class);
		entityManager.createQuery(query).executeUpdate();
		builder = entityManager.getCriteriaBuilder();
		CriteriaDelete<MelateContinua> query2 = builder.createCriteriaDelete(MelateContinua.class);
		query2.from(MelateContinua.class);
		entityManager.createQuery(query2).executeUpdate();
	}

	@Transactional
	public <T> int deleteEntity(Class<T> entityType) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaDelete<T> query = builder.createCriteriaDelete(entityType);
		query.from(entityType);
		return entityManager.createQuery(query).executeUpdate();
	}

	public void saveAll(MelateVoContainers melatesContainers) {
		melateRepository.saveAll(melatesContainers.getResult());
		ArrayList<MelateContinua> orderedContinuas = orderTreemapContinuas(melatesContainers.getResultContinua());
		melateContinuaRepository.saveAll(orderedContinuas);
	}

	ArrayList<MelateContinua> orderTreemapContinuas(TreeMap<Integer, ArrayList<MelateContinua>> treemapContinuas) {
		ArrayList<MelateContinua> result = new ArrayList<>();
		for (Integer idContinua : treemapContinuas.keySet()) {
			ArrayList<MelateContinua> laSeriede6 = treemapContinuas.get(idContinua);
			result.addAll(laSeriede6);
		}
		return result;
	}

}
