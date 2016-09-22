package ge.com.cm.ctrl;

import ge.com.cm.entities.Car;
import ge.com.cm.utils.MethodResponse;
import ge.com.ws.CarService;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/CarController", produces = "application/json;charset=UTF-8")
public class CarController {

    @Autowired
    private CarService carService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/getCars")
    public MethodResponse getCars() {
        LogManager.getLogger(CarController.class).error("selectcar called!!!");
        List<Car> cars = carService.getCars();
        return new MethodResponse(cars , cars.size());
    }
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/editCar")
    public MethodResponse editCar(@RequestBody Car car) {
        carService.editCar(car);
        return MethodResponse.SUCCESS;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/removeCar")
    public MethodResponse removeCar(Integer id) {
        carService.removeCar(id);
        return MethodResponse.SUCCESS;
    }
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/getGarageCars")
    public MethodResponse getGarageCars(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        LogManager.getLogger(CarController.class).error("selectGaragecar called!!!");
        List<Car> cars = carService.getGarageCars(date);
        return new MethodResponse(cars , cars.size());
    }

}
