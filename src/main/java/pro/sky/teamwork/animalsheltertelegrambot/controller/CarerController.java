package pro.sky.teamwork.animalsheltertelegrambot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.teamwork.animalsheltertelegrambot.model.Carer;
import pro.sky.teamwork.animalsheltertelegrambot.service.CarerService;

@RestController
@RequestMapping("/carer")
public class CarerController {
    private final CarerService carerService;

    public CarerController(CarerService carerService) {
        this.carerService = carerService;
    }

    @PostMapping
    public ResponseEntity<Carer> addCarer(@RequestParam String fullName,
                                          @RequestParam int age,
                                          @RequestParam String phoneNumber) {
        return ResponseEntity.ok(this.carerService.addCarer(fullName, age, phoneNumber));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carer> findCarer(@PathVariable long id) {
        return ResponseEntity.ok(this.carerService.findCarer(id));
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
