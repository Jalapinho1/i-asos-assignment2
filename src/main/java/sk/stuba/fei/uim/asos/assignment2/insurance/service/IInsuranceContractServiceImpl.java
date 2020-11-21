package sk.stuba.fei.uim.asos.assignment2.insurance.service;

import sk.stuba.fei.uim.asos.assignment2.ws.Contract;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class IInsuranceContractServiceImpl implements IInsuranceContractService<Contract> {

    private final AtomicLong idCounter;

    private final Map<Long, Contract> contracts;

    public IInsuranceContractServiceImpl() {
        this.contracts = new HashMap<>();
        idCounter = new AtomicLong(0);
    }

    @Override
    public Contract create(Contract contract) {
        //TODO zadanie 3 - vytiahnem z Repository osobu a tej zmluvu priradim
        contract.setId(this.idCounter.incrementAndGet());
        contracts.put(contract.getId(), contract);
        return contract;
    }

    @Override
    public Contract update(Contract contract) {
        if (!contracts.containsKey(contract.getId())) {
            throw new IllegalArgumentException("Contract has not been found!");
        }
        return contracts.put(contract.getId(), contract);
    }

    @Override
    public List<Contract> getAll() {
        return new ArrayList<>(contracts.values());
    }

    @Override
    public List<Contract> getByUserId(Long userId) {
        return contracts
                .values()
                .stream()
                .filter(insuranceContract -> insuranceContract.getInsurerId() == userId)
                .collect(Collectors.toList());
    }
}
