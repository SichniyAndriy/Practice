package home.practice.dao;

import home.practice.model.Car;
import java.util.List;
import java.util.Optional;

public interface CarsDao {
    Long save(Car car);

    List<Car> getAllCars();

    Optional<Car> getCarById(Long id);
}
