package sk.stuba.fei.uim.asos.assignment2;

import lombok.extern.java.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sk.stuba.fei.uim.asos.assignment2.endpoint.ContractEndpoint;
import sk.stuba.fei.uim.asos.assignment2.endpoint.UserEndpoint;
import sk.stuba.fei.uim.asos.assignment2.insurance.service.IInsuranceContractService;
import sk.stuba.fei.uim.asos.assignment2.insurance.service.IInsuranceContractServiceImpl;
import sk.stuba.fei.uim.asos.assignment2.user.service.IUserService;
import sk.stuba.fei.uim.asos.assignment2.ws.*;

import javax.xml.ws.Endpoint;
import java.awt.print.Book;
import java.time.LocalDate;

@Log
public class Assignment2Application {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new ClassPathXmlApplicationContext("config.xml");

        IInsuranceContractService service = applicationContext.getBean(IInsuranceContractService.class);
        IUserService userService = applicationContext.getBean(IUserService.class);

        TravelInsuranceContract contract = new TravelInsuranceContract();
        contract.setInsideEuropeanUnion(true);
        contract.setTravelPurpose(TravelPurpose.SPORT);
        service.create(contract);

        Address address = new Address();
        address.setCity("Bratislava");
        address.setStreet("SNP");
        address.setStreetNumber("15");
        address.setZip("90502");
        User user = new User();
        user.setCorrespondenceAddress(address);
        user.setSurname("Peter");
        user.setLastname("Mrkvicka");
        user.setEmail("mrkvica@gmail.com");
        user.setIdentificationNumber("970824/7950");
        userService.add(user);

        Endpoint.publish("http://localhost:8080/contract", applicationContext.getBean(ContractEndpoint.class));
        Endpoint.publish("http://localhost:8080/user", applicationContext.getBean(UserEndpoint.class));

        log.info("Application has started...");
    }

}
