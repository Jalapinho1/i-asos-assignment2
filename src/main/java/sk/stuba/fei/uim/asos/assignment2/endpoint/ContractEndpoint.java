package sk.stuba.fei.uim.asos.assignment2.endpoint;

import sk.stuba.fei.uim.asos.assignment2.insurance.service.IInsuranceContractService;
import sk.stuba.fei.uim.asos.assignment2.ws.*;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

@WebService(name = "ContractServicePortType", targetNamespace = "contractsWSDLNamespace")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
        ObjectFactory.class
})
public class ContractEndpoint implements ContractServicePortType {

    private IInsuranceContractService contractService;

    public ContractEndpoint(IInsuranceContractService contractService){
        this.contractService = contractService;
    }

    @Override
    public Contracts list() {
        Contracts contracts = new Contracts();
        contracts.getContract().addAll(contractService.getAll());
        return contracts;
    }

    @Override
    public TravelInsuranceContract addTravelContract(
            @WebParam(name = "addTravelInsuranceInput", targetNamespace = "global", partName = "contract")
            TravelInsuranceContract contract) {
        contractService.create(contract);
        return contract;
    }

    @Override
    public TravelInsuranceContract updateTravelContract(
            @WebParam(name = "updateTravelInsuranceInput", targetNamespace = "global", partName = "contract")
            TravelInsuranceContract contract) {
        contractService.update(contract);
        return contract;
    }

    @Override
    public AccidentInsuranceContract addAccidentContract(
            @WebParam(name = "addAccidentInsuranceInput", targetNamespace = "global", partName = "contract")
            AccidentInsuranceContract contract) {
        contractService.create(contract);
        return contract;
    }

    @Override
    public AccidentInsuranceContract updateAccidentContract(
            @WebParam(name = "updateAccidentInsuranceInput", targetNamespace = "global", partName = "contract")
            AccidentInsuranceContract contract) {
        contractService.update(contract);
        return contract;
    }

    @Override
    public HouseholdInsurance addHouseholdContract(
            @WebParam(name = "addHouseholdInsuranceInput", targetNamespace = "global", partName = "contract")
            HouseholdInsurance contract) {
        contractService.create(contract);
        return contract;
    }

    @Override
    public HouseholdInsurance updateHouseholdContract(
            @WebParam(name = "updateHouseholdInsuranceInput", targetNamespace = "global", partName = "contract")
            HouseholdInsurance contract) {
        contractService.update(contract);
        return contract;
    }

    @Override
    public HouseAndFlatInsurance addHouseAndFlatContract(
            @WebParam(name = "addHouseAndFlatInsuranceInput", targetNamespace = "global", partName = "contract")
            HouseAndFlatInsurance contract) {
        contractService.create(contract);
        return contract;
    }

    @Override
    public HouseAndFlatInsurance updateHouseAndFlatContract(
            @WebParam(name = "updateHouseAndFlatInsuranceInput", targetNamespace = "global", partName = "contract")
            HouseAndFlatInsurance contract) {
        contractService.update(contract);
        return contract;
    }

    @Override
    public Contracts contractsOfUser(@WebParam(name = "getInsurerContractsInput", targetNamespace = "global", partName = "id")
                                                 long id) {
        Contracts userContracts = new Contracts();
        userContracts.getContract().addAll(contractService.getByUserId(id));
        return userContracts;
    }

}
