package com.kovtunov.personservice.rest;

import com.kovtunov.personservice.dto.AllInformationDTO;
import com.kovtunov.personservice.dto.ErrorDto;
import com.kovtunov.personservice.dto.UserDTO;
import com.kovtunov.personservice.entity.User;
import com.kovtunov.personservice.exception.UserNotFoundException;
import com.kovtunov.personservice.exception.UserWithDuplicateEmailException;
import com.kovtunov.personservice.mapper.CustomMapper;
import com.kovtunov.personservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private final CustomMapper customMapper;
    private final UserService userService;

    @PostMapping("/full")
    public ResponseEntity<?> allInformationUser(@RequestBody AllInformationDTO information) {
        log.info("In all information user {}", information);
        try {
            return ResponseEntity.ok(userService.createFullUser(information));
        } catch (UserWithDuplicateEmailException e) {
            return ResponseEntity.badRequest()
                    .body(ErrorDto.builder()
                            .status(400)
                            .message(e.getMessage())
                            .build());
        }

    }

    @PostMapping("/create-user")
    public ResponseEntity<?> create(@RequestBody User user) {
        log.info("In create-user {}", user);
        try {
            User createUser = userService.createOnlyUser(user);
            UserDTO result = customMapper.toDtoUser(createUser);
            return ResponseEntity.ok(result);
        } catch (UserWithDuplicateEmailException e) {
            return ResponseEntity.badRequest()
                    .body(ErrorDto.builder()
                            .status(400)
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getUserById(@PathVariable UUID uuid) {
        log.info("In getUser {}", uuid);
        try {
            User user = userService.getUserByUUID(uuid);
            UserDTO result = customMapper.toDtoUser(user);
            return ResponseEntity.ok(result);
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(404)
                    .body(ErrorDto.builder()
                            .message(e.getMessage())
                            .build());
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("In getAllUsers");
        return ResponseEntity.ok()
                .body(userService.getAllUsers()
                        .stream()
                        .map(customMapper::toDtoUser)
                        .toList());
    }
}
