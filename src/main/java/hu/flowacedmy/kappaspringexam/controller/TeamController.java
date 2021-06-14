package hu.flowacedmy.kappaspringexam.controller;

import hu.flowacedmy.kappaspringexam.model.SuperHero;
import hu.flowacedmy.kappaspringexam.model.Team;
import hu.flowacedmy.kappaspringexam.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeam(@RequestBody Team team) {
        teamService.createTeam(team);
    }

    @GetMapping("")
    public List<Team> getAllTeam() {
        return teamService.getAllTeam();
    }

    @GetMapping("/{id}")
    public Optional<Team> getATeamByID(@PathVariable String id) {
        return teamService.getATeamByID(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateTeam(@PathVariable String id, @RequestBody Team team) {
        teamService.updateTeam(id, team);
    }
}
