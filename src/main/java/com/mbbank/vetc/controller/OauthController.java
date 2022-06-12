package com.mbbank.vetc.controller;

import com.mbbank.vetc.payload.request.OauthRequest;
import com.mbbank.vetc.payload.response.OauthResponse;
import com.mbbank.vetc.service.OauthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/oauth")
@Api(tags = "Oauth Controller")
@AllArgsConstructor
public class OauthController {

    private final OauthService oauthService;

    @ApiOperation(value = "Api login ")
    @PostMapping("/login")
    public OauthResponse login(@Valid @RequestBody OauthRequest request) {
        return oauthService.login(request);
    }

}
