package com.mbbank.vetc.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import javax.xml.namespace.QName;

@Component
public class SoapClientSecurityHeaderWriter implements WebServiceMessageCallback {

    @Value("${vetc.username}")
    private String USERNAME;

    @Value("${vetc.password}")
    private String PASSWORD;

    @SneakyThrows
    @Override
    public void doWithMessage(WebServiceMessage message) {
        var soapMessage = ((SaajSoapMessage) message).getSaajMessage();
        var header = soapMessage.getSOAPHeader();
        var security = header.addHeaderElement(
                new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security", "wsse"));
        var usernameToken = security.addChildElement("UsernameToken", "wsse");
        var usernameElement = usernameToken.addChildElement("Username", "wsse");
        var passwordElement = usernameToken.addChildElement("Password", "wsse");

        usernameElement.setTextContent(USERNAME);
        passwordElement.setTextContent(PASSWORD);
    }



}
