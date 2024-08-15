package hotel.domain.service;

import hotel.data.entity.Car;
import hotel.data.entity.Guest;
import hotel.domain.repository.CarRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static jakarta.transaction.Transactional.TxType.MANDATORY;

@Service
@Transactional(MANDATORY)
public class CarService {
    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car getCarById(Integer integer) {
        return carRepository.findById(integer).orElse(null);
    }

    public Car getCarByGuest(Guest guest) {
        return carRepository.findByGuest(guest).orElse(null);
    }
}
