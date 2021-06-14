package hu.flowacedmy.kappaspringexam.service;

import hu.flowacedmy.kappaspringexam.exception.ValidationException;
import hu.flowacedmy.kappaspringexam.model.Kind;
import hu.flowacedmy.kappaspringexam.model.SuperHero;
import hu.flowacedmy.kappaspringexam.model.Team;
import hu.flowacedmy.kappaspringexam.model.Universe;
import hu.flowacedmy.kappaspringexam.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public void createTeam(Team team) {
        validate(team);
        System.out.println(team);
        teamRepository.save(team);
    }

    public List<Team> getAllTeam() {
        return teamRepository.findAll();
    }

    public Optional<Team> getATeamByID(String id) {
        if(teamRepository.findById(id).equals(Optional.empty())){
            throw new ValidationException("Valós ID-t adj meg!");
        }
        return teamRepository.findById(id);

    }

    public void updateTeam(String id, Team team) {
        validate(team);
        Team oldTeam = teamRepository.findById(id).orElse(null);

        if(oldTeam == null){
            throw new ValidationException("Nincs ilyen ID-val rendelkező Team!");
        }
        teamRepository.save(team.toBuilder().id(oldTeam.getId())
                .build());
    }

    private void validate(Team team) {
        if(team.getKind() == null){
            throw new ValidationException("Kind nem leeht üres!");

        }
        if(team.getUniverse() == null){
            throw new ValidationException("Universe nem lehet üres!");

        }
        if (!StringUtils.hasText(team.getName())) {
            throw new ValidationException("Team name nem lehet üres!");
        }
        if (!team.getUniverse().equals(Universe.DC) && !team.getUniverse().equals(Universe.MARVEL)) {
            throw new ValidationException("Csak DC-t vagy Marvelt adhatsz meg Univerzumnak!");
        }

        if (!team.getKind().equals(Kind.HERO) && !team.getKind().equals(Kind.VILLAIN)) {
            throw new ValidationException("Csak HERO-t vagy VILLAIN-t adhatsz meg kind-nak!");
        }
    }
}
