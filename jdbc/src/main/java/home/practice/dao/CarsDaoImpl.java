package home.practice.dao;

import home.practice.model.Car;
import home.practice.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CarsDaoImpl implements CarsDao {
    @Override
    public Long save(Car car) {
        String req = "INSERT INTO cars ( brand, model, year) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement( req, Statement.RETURN_GENERATED_KEYS ) )
        {
            preparedStatement.setString( 1, car.getBrand() );
            preparedStatement.setObject( 2, car.getModel() );
            preparedStatement.setObject( 3, car.getYear() );
            if (preparedStatement.executeUpdate() == 0) {
                throw new RuntimeException("Expected for inserting 1 row but was 0");
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            Long id = 0L;
            if ( generatedKeys.next() ) {
                id = generatedKeys.getObject( 1, Long.class );
            }
            return id;
        } catch ( SQLException e ) {
            throw new RuntimeException("Cannot insert car in DB");
        }
    }

    @Override
    public List<Car> getAllCars() {
        String req = "SELECT * FROM cars";
        try ( Connection connection = ConnectionUtil.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(req))
        {
            if( preparedStatement.execute( req ) ) {
                List<Car> carList = new ArrayList<>();
                ResultSet resultSet = preparedStatement.getResultSet();
                while( resultSet.next() ) {
                    carList.add( getCarFromResult( resultSet ) );
                }
                return carList;
            }
        } catch ( SQLException e ) {
            throw new RuntimeException("Cannot get car's list", e);
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        String paramReq = "SELECT * FROM cars WHERE id = ?";
        try ( Connection connection = ConnectionUtil.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement( paramReq ) )
        {
            preparedStatement.setLong( 1, id);
            if (preparedStatement.execute()) {
                ResultSet resultSet =  preparedStatement.getResultSet();
                resultSet.next();
                return Optional.of( getCarFromResult( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new RuntimeException( "Cannot get car by ID", e );
        }
        return Optional.empty();
    }

    private Car getCarFromResult(ResultSet resultSet) throws SQLException {
        String brand = resultSet.getObject("brand", String.class);
        String model = resultSet.getObject("model", String.class);
        Integer year = resultSet.getObject("year", Integer.class);
        return new Car(brand, model, year);
    }
}
