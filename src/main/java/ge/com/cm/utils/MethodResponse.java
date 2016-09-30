package ge.com.cm.utils;

import java.io.Serializable;

public class MethodResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final MethodResponse SUCCESS = new MethodResponse();
	
	private Boolean success; 
	private String msg;
	private String errorMsg;
	private Object data;
	private Long totalCount;
	private Integer errorCode;
	
	public MethodResponse(){
		setSuccess(true);
		setErrorCode(0);
	}
	
	public MethodResponse(Object data){
		setSuccess(true);
		setErrorCode(0);
		setData(data);
		//logger();
	}
	
	public MethodResponse(Object data, Long totalCount){
		setSuccess(true);
		setErrorCode(0);
		setData(data);
		setTotalCount(totalCount);
		//logger();
	}
	
	public MethodResponse(Object data, Integer totalCount){
		setSuccess(true);
		setErrorCode(0);
		setData(data);
		setTotalCount((long)totalCount);
		//logger();
	}



	public MethodResponse(String msg){
		setSuccess(false);
		setErrorCode(-20001);
		setMsg(msg);
		//logger();
	}
	
	public MethodResponse(Exception e){
		setSuccess(false);
		setErrorMsg(parseExceptionMsg(e)); //TODO need to be parsed!!!
		setErrorCode(-100); //TODO need to be parsed!!!
		//logger();
	}
	
	public MethodResponse(Exception e, String msg){
		setSuccess(false);
		setMsg(msg);
		setErrorMsg(parseExceptionMsg(e)); //TODO need to be parsed!!!
		setErrorCode(-100); //TODO need to be parsed!!!
		//logger();
	}
	
	public MethodResponse(String msg, String errorMsg) throws Exception{
		setSuccess(false);
		setErrorCode(-20001);
		setMsg(msg);
		setErrorMsg(errorMsg);
		//logger();
	}
	
	private void setSuccess(Boolean success) {
		this.success = success;
	}
	public Boolean getSuccess() {
		return success;
	}
	private void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	

	public void setData(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}
	
	private void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	private void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public static String parseExceptionMsg(Exception e) {
		
		//e.printStackTrace();

		String errMsg = e.getMessage();	 

		return errMsg;

	}

}