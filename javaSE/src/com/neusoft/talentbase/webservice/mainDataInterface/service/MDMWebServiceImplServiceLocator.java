/**
 * MDMWebServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.neusoft.talentbase.webservice.mainDataInterface.service;

public class MDMWebServiceImplServiceLocator extends org.apache.axis.client.Service implements MDMWebServiceImplService {

    public MDMWebServiceImplServiceLocator() {
    }


    public MDMWebServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MDMWebServiceImplServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MDMWebServiceImplPort
    private String MDMWebServiceImplPort_address = "http://ehr.chinawanxiang.com:10086/service/MDMWebService";

    public String getMDMWebServiceImplPortAddress() {
        return MDMWebServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MDMWebServiceImplPortWSDDServiceName = "MDMWebServiceImplPort";

    public String getMDMWebServiceImplPortWSDDServiceName() {
        return MDMWebServiceImplPortWSDDServiceName;
    }

    public void setMDMWebServiceImplPortWSDDServiceName(String name) {
        MDMWebServiceImplPortWSDDServiceName = name;
    }

    public MDMWebService getMDMWebServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MDMWebServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMDMWebServiceImplPort(endpoint);
    }

    public MDMWebService getMDMWebServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            MDMWebServiceImplServiceSoapBindingStub _stub = new MDMWebServiceImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getMDMWebServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMDMWebServiceImplPortEndpointAddress(String address) {
        MDMWebServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (MDMWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                MDMWebServiceImplServiceSoapBindingStub _stub = new MDMWebServiceImplServiceSoapBindingStub(new java.net.URL(MDMWebServiceImplPort_address), this);
                _stub.setPortName(getMDMWebServiceImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("MDMWebServiceImplPort".equals(inputPortName)) {
            return getMDMWebServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.mainDataInterface.webservice.talentbase.neusoft.com/", "MDMWebServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.mainDataInterface.webservice.talentbase.neusoft.com/", "MDMWebServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("MDMWebServiceImplPort".equals(portName)) {
            setMDMWebServiceImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
