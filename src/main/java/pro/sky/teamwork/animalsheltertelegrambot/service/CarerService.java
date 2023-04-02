package pro.sky.teamwork.animalsheltertelegrambot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.teamwork.animalsheltertelegrambot.model.Carer;
import pro.sky.teamwork.animalsheltertelegrambot.repository.CarerRepository;

@Service
public class CarerService {
    private final CarerRepository carerRepository;

    public CarerService(CarerRepository carerRepository) {
        this.carerRepository = carerRepository;
    }

    @Transactional
    public void addCarer(String fullName, String phoneNumber) {
        Carer carer = new Carer();
        carer.setFullName(fullName);
        carer.setPhoneNumber(phoneNumber);
        carer.setAge(19); //костыль - нужно продумать заполнение колонки с возрастом
        carerRepository.save(carer);
    }
}
