
package com.mbbank.vetc.genarate.imp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryAccountInfoByEtagNumber complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryAccountInfoByEtagNumber">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="etagNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryAccountInfoByEtagNumber", propOrder = {
    "etagNumber"
})
public class QueryAccountInfoByEtagNumber {

    protected String etagNumber;

    /**
     * Gets the value of the etagNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEtagNumber() {
        return etagNumber;
    }

    /**
     * Sets the value of the etagNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEtagNumber(String value) {
        this.etagNumber = value;
    }

}
