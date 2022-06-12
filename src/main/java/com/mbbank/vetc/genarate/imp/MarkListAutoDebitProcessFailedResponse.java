
package com.mbbank.vetc.genarate.imp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for markListAutoDebitProcessFailedResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="markListAutoDebitProcessFailedResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://imp.service.crm.ftu.com/}autoDebitProcessResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "markListAutoDebitProcessFailedResponse", propOrder = {
    "_return"
})
public class MarkListAutoDebitProcessFailedResponse {

    @XmlElement(name = "return")
    protected AutoDebitProcessResponse _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link AutoDebitProcessResponse }
     *     
     */
    public AutoDebitProcessResponse getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link AutoDebitProcessResponse }
     *     
     */
    public void setReturn(AutoDebitProcessResponse value) {
        this._return = value;
    }

}
