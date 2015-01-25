package net._100steps.service.dao;

public class DAOException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 825887601636554279L;

	public DAOException()
	{
		
	}

	public DAOException(String message)
	{
		super(message);
	}

	public DAOException(Throwable cause)
	{
		super(cause);
	}

	public DAOException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
