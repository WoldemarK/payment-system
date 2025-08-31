package com.kovtunov.personservice.service;


import com.kovtunov.personservice.entity.User;
import com.kovtynov.person_service.api.dto.AllInformationDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    AllInformationDTO createFullUser(AllInformationDTO allInformationDTO);

    User createOnlyUser(User user);

    List<User> getAllUsers();

    User getUserByUUID(UUID uuid);

    User findByEmail(String email);
}
