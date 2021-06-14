package hu.flowacedmy.kappaspringexam.repository;

import hu.flowacedmy.kappaspringexam.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SuperHeroRepository extends JpaRepository<SuperHero,String> {
}
