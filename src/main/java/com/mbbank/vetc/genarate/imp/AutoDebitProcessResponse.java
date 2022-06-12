
package com.mbbank.vetc.genarate.imp;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for autoDebitProcessResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="autoDebitProcessResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="executionDatetime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listAutoDebitAccountData" type="{http://imp.service.crm.ftu.com/}autoDebitAccountData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="queryToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "autoDebitProcessResponse", propOrder = {
    "executionDatetime",
    "listAutoDebitAccountData",
    "queryToken",
    "responseCode",
    "responseMessage"
})
public class AutoDebitProcessResponse {

    protected String executionDatetime;
    @XmlElement(nillable = true)
    protected List<AutoDebitAccountData> listAutoDebitAccountData;
    protected String queryToken;
    protected String responseCode;
    protected String responseMessage;

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
     * Gets the value of the listAutoDebitAccountData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listAutoDebitAccountData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListAutoDebitAccountData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AutoDebitAccountData }
     * 
     * 
     */
    public List<AutoDebitAccountData> getListAutoDebitAccountData() {
        if (listAutoDebitAccountData == null) {
            listAutoDebitAccountData = new ArrayList<AutoDebitAccountData>();
        }
        return this.listAutoDebitAccountData;
    }

    /**
     * Gets the value of the queryToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryToken() {
        return queryToken;
    }

    /**
     * Sets the value of the queryToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryToken(String value) {
        this.queryToken = value;
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
