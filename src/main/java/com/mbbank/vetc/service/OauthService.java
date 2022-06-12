package com.mbbank.vetc.service;

import com.mbbank.vetc.payload.request.OauthRequest;
import com.mbbank.vetc.payload.response.OauthResponse;

public interface OauthService {
    OauthResponse login(OauthRequest request);
}
