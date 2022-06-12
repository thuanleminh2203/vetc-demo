
package com.mbbank.vetc.genarate.imp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for autoDebitAccountResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="autoDebitAccountResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="autoDebitAccountHistoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="autoDebitAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="executionDatetime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minimumBalance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
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
@XmlType(name = "autoDebitAccountResponse", propOrder = {
    "accountNo",
    "autoDebitAccountHistoryId",
    "autoDebitAmount",
    "executionDatetime",
    "minimumBalance",
    "responseCode",
    "responseMessage"
})
public class AutoDebitAccountResponse {

    protected String accountNo;
    protected Long autoDebitAccountHistoryId;
    protected Double autoDebitAmount;
    protected String executionDatetime;
    protected Double minimumBalance;
    protected String responseCode;
    protected String responseMessage;

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
     * Gets the value of the autoDebitAccountHistoryId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAutoDebitAccountHistoryId() {
        return autoDebitAccountHistoryId;
    }

    /**
     * Sets the value of the autoDebitAccountHistoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAutoDebitAccountHistoryId(Long value) {
        this.autoDebitAccountHistoryId = value;
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
     * Gets the value of the executionDatetime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExecutionDatetime() {
        return executionDatetime;
    }

    /**
     * Sets the value of the executionDatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExecutionDatetime(String value) {
        this.executionDatetime = value;
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
