
package com.mbbank.vetc.genarate.imp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rechargeBySyntax complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rechargeBySyntax">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="syntax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="partnerInfo" type="{http://imp.service.crm.ftu.com/}partnerInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rechargeBySyntax", propOrder = {
    "syntax",
    "amount",
    "partnerInfo"
})
public class RechargeBySyntax {

    protected String syntax;
    protected double amount;
    protected PartnerInfo partnerInfo;

    /**
     * Gets the value of the syntax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSyntax() {
        return syntax;
    }

    /**
     * Sets the value of the syntax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSyntax(String value) {
        this.syntax = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(double value) {
        this.amount = value;
    }

    /**
     * Gets the value of the partnerInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerInfo }
     *     
     */
    public PartnerInfo getPartnerInfo() {
        return partnerInfo;
    }

    /**
     * Sets the value of the partnerInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerInfo }
     *     
     */
    public void setPartnerInfo(PartnerInfo value) {
        this.partnerInfo = value;
    }

}
