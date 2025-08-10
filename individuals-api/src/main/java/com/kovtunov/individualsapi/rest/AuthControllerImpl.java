package com.kovtunov.individualsapi.rest;

import com.kovtunov.individualsapi.service.UserService;
import com.kovtunov.individualsapi.validation.interfaces.ValidateRegistrationRequest;
import com.kovtunov.individualsapi.validation.interfaces.ValidationUserLoginRequest;
import com.kovtynov.individuals.api.dto.ErrorResponse;
import com.kovtynov.individuals.api.dto.TokenRefreshRequest;
import com.kovtynov.individuals.api.dto.TokenResponse;
import com.kovtynov.individuals.api.dto.UserInfoResponse;
import com.kovtynov.individuals.api.dto.UserLoginRequest;
import com.kovtynov.individuals.api.dto.UserRegistrationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@Tag(name = "auth", description = "the auth API")
public class AuthControllerImpl {

    private final UserService userService;

    /**
     * POST /auth/login : Аутентификация пользователя
     *
     * @param userLoginRequest (required)
     * @return Успешная аутентификация (status code 200)
     * or Неверный логин или пароль (status code 401)
     */
    @Operation(
            operationId = "authLoginPost",
            summary = "Аутентификация пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешная аутентификация", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Неверный логин или пароль", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    })
            }
    )
    @RequestMapping
            (
                    method = RequestMethod.POST,
                    value = "/auth/login",
                    produces = {"application/json"},
                    consumes = {"application/json"}
            )
    @ValidationUserLoginRequest
    public Mono<TokenResponse> authLoginPost(@Parameter(name = "UserLoginRequest", description = "", required = true)
                                             @RequestBody UserLoginRequest userLoginRequest) {
        return null;
    }

    /**
     * GET /auth/me : Получение данных текущего пользователя
     *
     * @return Данные пользователя (status code 200)
     * or Недействительный токен (status code 401)
     * or Пользователь не найден (status code 404)
     */
    @Operation(
            operationId = "authMeGet",
            summary = "Получение данных текущего пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные пользователя", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserInfoResponse.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Недействительный токен", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    })
            },
            security = {
                    @SecurityRequirement(name = "bearerAuth")
            }
    )
    @RequestMapping
            (
                    method = RequestMethod.GET,
                    value = "/auth/me",
                    produces = {"application/json"}
            )
    @PreAuthorize("hasRole('USER')")
    public Mono<UserInfoResponse> authMeGet() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .flatMap(userService::getCurrentUser);
    }

    /**
     * POST /auth/refresh-token : Обновление токена доступа
     *
     * @param tokenRefreshRequest (required)
     * @return Токен успешно обновлён (status code 200)
     * or Недействительный или просроченный refresh token (status code 401)
     */
    @Operation(
            operationId = "authRefreshTokenPost",
            summary = "Обновление токена доступа",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Токен успешно обновлён", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Недействительный или просроченный refresh token", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/auth/refresh-token",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public Mono<TokenResponse> authRefreshTokenPost(@Parameter(name = "TokenRefreshRequest", description = "", required = true)
                                                    @Valid
                                                    @RequestBody TokenRefreshRequest tokenRefreshRequest) {
        return userService.authRefreshTokenPost(tokenRefreshRequest);
    }

    /**
     * POST /auth/registration : Регистрация пользователя
     *
     * @param userRegistrationRequest (required)
     * @return Успешная регистрация (status code 201)
     * or Ошибка валидации (status code 400)
     * or Пользователь уже существует (status code 409)
     */
    @Operation(
            operationId = "authRegistrationPost",
            summary = "Регистрация пользователя",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Успешная регистрация", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    }),
                    @ApiResponse(responseCode = "409", description = "Пользователь уже существует", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/auth/registration",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ValidateRegistrationRequest
    public Mono<TokenResponse> authRegistrationPost(UserRegistrationRequest userRegistrationRequest) {
        return userService.register(userRegistrationRequest);
    }

}
