package com.mbbank.vetc.config;

import com.mbbank.vetc.exception.BadRequestException;
import com.mbbank.vetc.genarate.imp.AppException;
import com.mbbank.vetc.genarate.imp.Exception_Exception;
import com.sun.xml.ws.wsdl.writer.document.Fault;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapEnvelope;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.support.MarshallingUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.DetailEntry;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;

@Slf4j
//@Component
public class SoapClientInterceptor implements ClientInterceptor {
//    @Autowired
//    Jaxb2Marshaller marshaller;


    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {

        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        return true;
    }

    @Override
    @SneakyThrows
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
        WebServiceMessage message = messageContext.getResponse();
        SaajSoapMessage saajSoapMessage = (SaajSoapMessage) message;
        SOAPMessage soapMessage = saajSoapMessage.getSaajMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        SOAPBody soapBody = soapEnvelope.getBody();
        SOAPFault soapFault = soapBody.getFault();

        String mess = soapFault.getFaultString();
        String errCode = mess != null ? mess.substring(0,mess.indexOf(":")) : null;

        log.error(String.format("Error occurred while invoking web service - %s ", mess));
        throw new BadRequestException(mess,errCode);
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception e) throws WebServiceClientException {

    }

}
