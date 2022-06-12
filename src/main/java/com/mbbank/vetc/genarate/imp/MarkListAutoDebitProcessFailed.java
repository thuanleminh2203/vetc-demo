
package com.mbbank.vetc.genarate.imp;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for markListAutoDebitProcessFailed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="markListAutoDebitProcessFailed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partnerInfo" type="{http://imp.service.crm.ftu.com/}partnerInfo" minOccurs="0"/>
 *         &lt;element name="listAccountNo" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="queryToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "markListAutoDebitProcessFailed", propOrder = {
    "partnerInfo",
    "listAccountNo",
    "queryToken"
})
public class MarkListAutoDebitProcessFailed {

    protected PartnerInfo partnerInfo;
    protected List<String> listAccountNo;
    protected String queryToken;

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
     * Gets the value of the listAccountNo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listAccountNo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListAccountNo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getListAccountNo() {
        if (listAccountNo == null) {
            listAccountNo = new ArrayList<String>();
        }
        return this.listAccountNo;
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

}
