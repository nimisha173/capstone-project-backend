package com.ust.security.service;

import com.ust.security.controller.dto.AuthenticationRequest;
import com.ust.security.controller.dto.AuthenticationResponse;
import com.ust.security.controller.dto.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
