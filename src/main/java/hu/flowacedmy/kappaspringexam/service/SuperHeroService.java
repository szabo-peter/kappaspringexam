package hu.flowacedmy.kappaspringexam.service;

import hu.flowacedmy.kappaspringexam.exception.ValidationException;
import hu.flowacedmy.kappaspringexam.model.Kind;
import hu.flowacedmy.kappaspringexam.model.SuperHero;
import hu.flowacedmy.kappaspringexam.model.Team;
import hu.flowacedmy.kappaspringexam.model.Universe;
import hu.flowacedmy.kappaspringexam.repository.SuperHeroRepository;
import hu.flowacedmy.kappaspringexam.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuperHeroService {

    private final SuperHeroRepository superHeroRepository;
    private final TeamRepository teamRepository;

    public void createSuperHero(SuperHero superHero) {
        validate(superHero);
        Team team = teamRepository.findById(superHero.getTeam().getId()).orElse(null);
        validate(superHero,team);
        superHero.setTeam(team);
        superHeroRepository.save(superHero);
    }

    public List<SuperHero> getAllSuperHero() {
        return superHeroRepository.findAll();
    }

    public Optional<SuperHero> getASuperHeroByID(String id) {
        if(superHeroRepository.findById(id).equals(Optional.empty())){
            throw new ValidationException("Valós ID-t adj meg!");
        }
        return superHeroRepository.findById(id);
    }

    public void updateSuperHero(String id, SuperHero superHero) {
        validate(superHero);
        SuperHero oldSuperHero = superHeroRepository.findById(id).orElse(null);
        if(oldSuperHero == null){
            throw new ValidationException("Nincs ilyen ID-val rendelkező Team!");
        }
        Team team = teamRepository.findById(superHero.getTeam().getId()).orElse(null);
        validate(superHero,team);

        superHeroRepository.save(superHero.toBuilder().id(oldSuperHero.getId())
                .build());
    }

    private void validate(SuperHero superHero) {

        if(superHero.getUniverse() == null){
            throw new ValidationException("Universe nem lehet üres!");
        }
        if (!StringUtils.hasText(superHero.getName())) {
            throw new ValidationException("Team name nem lehet üres!");
        }
        if (!superHero.getUniverse().equals(Universe.DC) && !superHero.getUniverse().equals(Universe.MARVEL)) {
            throw new ValidationException("Csak DC-t vagy Marvelt adhatsz meg Univerzumnak!");
        }
    }

    private void validate(SuperHero superHero, Team team){
        if(superHero.getTeam().getId() == null){
            throw new ValidationException("Team ID megadása kötelező!");
        }

        if(team == null){
            throw new ValidationException("Nem létezik ilyen ID-jú Team!");
        }
        if(!team.getUniverse().equals(superHero.getUniverse())){
            throw new ValidationException("A csapat és a sziperhős Univerzuma meg kell egyezzen!");
        }
        if(superHero.isHero() && !team.getKind().equals(Kind.HERO)){
            throw new ValidationException("Hero csak Hero csapatba kerülhet!");
        }
        if(!superHero.isHero() && !team.getKind().equals(Kind.VILLAIN)){
            throw new ValidationException("Ha nem hero akkor csak Villain csapatba kerülhet!");
        }

    }

}
