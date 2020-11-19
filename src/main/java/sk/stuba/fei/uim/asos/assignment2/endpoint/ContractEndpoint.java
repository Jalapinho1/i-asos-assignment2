package sk.stuba.fei.uim.asos.assignment2.endpoint;

import sk.stuba.fei.uim.asos.assignment2.insurance.service.IInsuranceContractService;
import sk.stuba.fei.uim.asos.assignment2.ws.*;

import javax.jws.WebParam;
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
    public TravelInsuranceContract addContract(@WebParam(name = "addTravelInsuranceContractInput", targetNamespace = "global", partName = "contract")
            TravelInsuranceContract contract) {
        contractService.create(contract);
        return contract;
    }

    @Override
    public TravelInsuranceContract updateContract(@WebParam(name = "updateTravelInsuranceContractInput", targetNamespace = "global", partName = "contract")
                                                              TravelInsuranceContract contract) {
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
