/**
 * 
 */
package com.ctl.ci.exceptions;

/**
 * @author AB40286
 *
 */
public class DAOConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public DAOConfigurationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DAOConfigurationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DAOConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

}