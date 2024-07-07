package hotel.domain.service;

import hotel.data.entity.guest.Car;
import hotel.domain.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car getCarById(Integer integer) {
        return carRepository.findById(integer).orElseThrow(RuntimeException::new);
    }
}
