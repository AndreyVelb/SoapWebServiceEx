package com.velb.soapwebserviceex.service;

import jakarta.xml.bind.JAXBElement;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;

@Service
public class JaxbService {

    public  <T> JAXBElement<T> createJaxbElement(T object, Class<T> clazz) {
        return new JAXBElement<>(new QName(clazz.getSimpleName()), clazz, object);
    }

}
