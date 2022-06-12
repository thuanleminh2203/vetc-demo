
package com.mbbank.vetc.genarate.imp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for rechargeGwInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rechargeGwInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="insertDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="rechargeGwId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rechargeGwInfo", propOrder = {
    "insertDatetime",
    "rechargeGwId"
})
public class RechargeGwInfo {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar insertDatetime;
    protected Long rechargeGwId;

    /**
     * Gets the value of the insertDatetime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInsertDatetime() {
        return insertDatetime;
    }

    /**
     * Sets the value of the insertDatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInsertDatetime(XMLGregorianCalendar value) {
        this.insertDatetime = value;
    }

    /**
     * Gets the value of the rechargeGwId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRechargeGwId() {
        return rechargeGwId;
    }

    /**
     * Sets the value of the rechargeGwId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRechargeGwId(Long value) {
        this.rechargeGwId = value;
    }

}
