package com.neusoft.talentbase.webservice.mainDataInterface.service;

public class MDMWebServiceProxy implements MDMWebService {
  private String _endpoint = null;
  private MDMWebService mDMWebService = null;
  
  public MDMWebServiceProxy() {
    _initMDMWebServiceProxy();
  }
  
  public MDMWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initMDMWebServiceProxy();
  }
  
  private void _initMDMWebServiceProxy() {
    try {
      mDMWebService = (new MDMWebServiceImplServiceLocator()).getMDMWebServiceImplPort();
      if (mDMWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mDMWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mDMWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mDMWebService != null)
      ((javax.xml.rpc.Stub)mDMWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public MDMWebService getMDMWebService() {
    if (mDMWebService == null)
      _initMDMWebServiceProxy();
    return mDMWebService;
  }
  
  public String getKey(String arg0, String arg1) throws java.rmi.RemoteException{
    if (mDMWebService == null)
      _initMDMWebServiceProxy();
    return mDMWebService.getKey(arg0, arg1);
  }
  
  public String getHrInfo(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) throws java.rmi.RemoteException{
    if (mDMWebService == null)
      _initMDMWebServiceProxy();
    return mDMWebService.getHrInfo(arg0, arg1, arg2, arg3, arg4, arg5);
  }
  
  public String getSingleInfo(String arg0, String arg1, String arg2, String arg3) throws java.rmi.RemoteException{
    if (mDMWebService == null)
      _initMDMWebServiceProxy();
    return mDMWebService.getSingleInfo(arg0, arg1, arg2, arg3);
  }
  
  
}