package hu.flowacedmy.kappaspringexam.controller;

import hu.flowacedmy.kappaspringexam.model.SuperHero;
import hu.flowacedmy.kappaspringexam.service.SuperHeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/super-hero")
@RequiredArgsConstructor
public class SuperHeroController {

    private final SuperHeroService superHeroService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSuperHero(@RequestBody SuperHero superHero){
        superHeroService.createSuperHero(superHero);
    }

    @GetMapping("")
    public List<SuperHero> getAllSuperHero(){
        return superHeroService.getAllSuperHero();
    }

    @GetMapping("/{id}")
    public Optional<SuperHero> getASuperHeroByID(@PathVariable String id){
        return superHeroService.getASuperHeroByID(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateSuperHero(@PathVariable String id,@RequestBody SuperHero superHero){
        superHeroService.updateSuperHero(id, superHero);
    }
}
