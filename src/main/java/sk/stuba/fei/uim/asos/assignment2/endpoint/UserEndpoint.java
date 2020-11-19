package sk.stuba.fei.uim.asos.assignment2.endpoint;

import sk.stuba.fei.uim.asos.assignment2.insurance.service.IInsuranceContractService;
import sk.stuba.fei.uim.asos.assignment2.user.service.UserService;
import sk.stuba.fei.uim.asos.assignment2.ws.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "UserServicePortType", targetNamespace = "usersWSDLNamespace")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
        ObjectFactory.class
})
public class UserEndpoint implements UserServicePortType {

    private UserService userService;

    public UserEndpoint(UserService userService){
        this.userService = userService;
    }

    @Override
    public Users list() {
        Users users = new Users();
        users.getUser().addAll(userService.getAll());
        return users;
    }

    @Override
    public User addUser(@WebParam(name = "addUserInput", targetNamespace = "global", partName = "user")
                                    User user) {
        userService.add(user);
        return user;
    }

    @Override
    public User updateUser(@WebParam(name = "updateUserInput", targetNamespace = "global", partName = "user")
                                       User user) {
        userService.update(user);
        return user;
    }

    @Override
    public User getUserById(@WebParam(name = "getUserByIdInput", targetNamespace = "global", partName = "id")
                                        long id) {
        return userService.getOne(id);
    }

}
