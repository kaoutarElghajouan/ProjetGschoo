package fpl.service;

import java.util.List;


import fpl.entities.User;
import fpl.service.impl.ResultObject;

public interface UserService {

	ResultObject Save(User o);
	ResultObject delete (User o);
	User getByID(int id);
	List<User> getAll();
	List<User> getByExample(User u);
}
