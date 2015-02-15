/**
 * 
 */
package net._100steps.bbter.service.manager.defaultimpl;

/**
 * @author Administrator
 *
 */
public class GeneralException extends RuntimeException {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GeneralException() {
		// TODO Auto-generated constructor stub
		 super();
	}
	public GeneralException(String s){
		super(s);
	}
	public GeneralException(String s, Throwable e)	{
		super(s, e);
	}
}
