package com.kovtunov.personservice.rest;

import com.kovtunov.personservice.dto.AllInformationDTO;
import com.kovtunov.personservice.dto.UserDTO;
import com.kovtunov.personservice.entity.Address;
import com.kovtunov.personservice.entity.Countries;
import com.kovtunov.personservice.entity.Individuals;
import com.kovtunov.personservice.entity.User;
import com.kovtunov.personservice.mapper.UserMapper;
import com.kovtunov.personservice.repository.AddressRepository;
import com.kovtunov.personservice.repository.IndividualsRepository;
import com.kovtunov.personservice.service.CountriesService;
import com.kovtunov.personservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping("/full")
    public void allInformationUser(@RequestBody AllInformationDTO information) {
       userService.createFullUser(information);
    }

//    @PostMapping("/create-user")
//    @Transactional(rollbackFor = Exception.class)
//    public String createUser(@RequestBody User user) {
//        String userLoad = userService.createUser(user);
//        return "create new user " + userLoad;
//    }


    @GetMapping("/{uuid}")
    public UserDTO getUser(@PathVariable UUID uuid) {
        return userMapper.toDtoUser(userService.getUserByUUID(uuid));
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User>users = userService.getAllUsers();
        List<UserDTO>dto = users.stream()
                .map(userMapper::toDtoUser)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(dto);
    }
}
