/**
 * 
 */
package com.ctl.ci.exceptions;

/**
 * @author AB40286
 *
 */
public class ApplicationConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public ApplicationConfigurationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ApplicationConfigurationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApplicationConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}
}
