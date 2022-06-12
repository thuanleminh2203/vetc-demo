
package com.mbbank.vetc.genarate.imp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for modifyAutoDebitAccountSignature complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modifyAutoDebitAccountSignature">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerInfo" type="{http://imp.service.crm.ftu.com/}partnerInfo" minOccurs="0"/>
 *         &lt;element name="accountNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minimumBalance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="autoDebitAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="transDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyAutoDebitAccountSignature", propOrder = {
    "partnerInfo",
    "accountNo",
    "minimumBalance",
    "autoDebitAmount",
    "transDate",
    "signature"
})
public class ModifyAutoDebitAccountSignature {

    protected PartnerInfo partnerInfo;
    protected String accountNo;
    protected Double minimumBalance;
    protected Double autoDebitAmount;
    protected String transDate;
    protected String signature;

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

    /**
     * Gets the value of the accountNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * Sets the value of the accountNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNo(String value) {
        this.accountNo = value;
    }

    /**
     * Gets the value of the minimumBalance property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinimumBalance() {
        return minimumBalance;
    }

    /**
     * Sets the value of the minimumBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinimumBalance(Double value) {
        this.minimumBalance = value;
    }

    /**
     * Gets the value of the autoDebitAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAutoDebitAmount() {
        return autoDebitAmount;
    }

    /**
     * Sets the value of the autoDebitAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAutoDebitAmount(Double value) {
        this.autoDebitAmount = value;
    }

    /**
     * Gets the value of the transDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransDate() {
        return transDate;
    }

    /**
     * Sets the value of the transDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransDate(String value) {
        this.transDate = value;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignature(String value) {
        this.signature = value;
    }

}
