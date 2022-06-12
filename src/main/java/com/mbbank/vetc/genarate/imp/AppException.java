
package com.mbbank.vetc.genarate.imp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AppException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AppException">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errormessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="str1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="suscess" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppException", propOrder = {
    "errorcode",
    "errormessage",
    "message",
    "str1",
    "suscess"
})
public class AppException {

    protected String errorcode;
    protected String errormessage;
    protected String message;
    protected String str1;
    protected int suscess;

    /**
     * Gets the value of the errorcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorcode() {
        return errorcode;
    }

    /**
     * Sets the value of the errorcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorcode(String value) {
        this.errorcode = value;
    }

    /**
     * Gets the value of the errormessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrormessage() {
        return errormessage;
    }

    /**
     * Sets the value of the errormessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrormessage(String value) {
        this.errormessage = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the str1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStr1() {
        return str1;
    }

    /**
     * Sets the value of the str1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStr1(String value) {
        this.str1 = value;
    }

    /**
     * Gets the value of the suscess property.
     * 
     */
    public int getSuscess() {
        return suscess;
    }

    /**
     * Sets the value of the suscess property.
     * 
     */
    public void setSuscess(int value) {
        this.suscess = value;
    }

}
