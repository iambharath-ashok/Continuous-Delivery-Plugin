/**
 * 
 */
package com.ctl.ci.sts;

import hudson.util.ListBoxModel;

/**
 * @author AB40286
 *
 */
public enum HttpMethod {
	GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH;
	
	public static ListBoxModel getFillItems() {
		ListBoxModel items = new ListBoxModel();
		for (HttpMethod httpMethod : values()) {
			items.add(httpMethod.name());
		}
		return items;
	}
}
