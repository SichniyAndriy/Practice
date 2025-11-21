package home.practice;

import home.practice.dao.CarsDao;
import home.practice.dao.CarsDaoImpl;
import home.practice.model.Car;

public class Main {

    public static void main(String[] args) {
        System.out.println( "Hello and welcome!" );
        CarsDao carsDao = new CarsDaoImpl();
        System.out.println( carsDao.getAllCars() );
        System.out.println( carsDao.getCarById( 5L ) );
        System.out.println("Car got id - " + carsDao.save( new Car("Porsche", "911", 2019 ) ) );
    }
}
