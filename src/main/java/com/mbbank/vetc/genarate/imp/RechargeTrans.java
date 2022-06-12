
package com.mbbank.vetc.genarate.imp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for rechargeTrans complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rechargeTrans">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rechargeTransDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="rechargeTransId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="responseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="responseMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rechargeTrans", propOrder = {
    "rechargeTransDate",
    "rechargeTransId",
    "responseCode",
    "responseMessage"
})
public class RechargeTrans {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rechargeTransDate;
    protected long rechargeTransId;
    protected String responseCode;
    protected String responseMessage;

    /**
     * Gets the value of the rechargeTransDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRechargeTransDate() {
        return rechargeTransDate;
    }

    /**
     * Sets the value of the rechargeTransDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRechargeTransDate(XMLGregorianCalendar value) {
        this.rechargeTransDate = value;
    }

    /**
     * Gets the value of the rechargeTransId property.
     * 
     */
    public long getRechargeTransId() {
        return rechargeTransId;
    }

    /**
     * Sets the value of the rechargeTransId property.
     * 
     */
    public void setRechargeTransId(long value) {
        this.rechargeTransId = value;
    }

    /**
     * Gets the value of the responseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the value of the responseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseCode(String value) {
        this.responseCode = value;
    }

    /**
     * Gets the value of the responseMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Sets the value of the responseMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseMessage(String value) {
        this.responseMessage = value;
    }

}
