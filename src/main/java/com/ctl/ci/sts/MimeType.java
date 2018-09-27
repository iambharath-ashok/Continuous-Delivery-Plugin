/**
 * 
 */
package com.ctl.ci.sts;

import org.apache.http.entity.ContentType;

import hudson.util.ListBoxModel;


/**
 * @author AB40286
 *
 */
public enum MimeType {

    NOT_SET(null),
    TEXT_HTML(ContentType.TEXT_HTML),
    TEXT_PLAIN(ContentType.TEXT_PLAIN),
    APPLICATION_FORM(ContentType.APPLICATION_FORM_URLENCODED),
    APPLICATION_JSON(ContentType.create("application/json")),
    APPLICATION_JSON_UTF8(ContentType.APPLICATION_JSON),
    APPLICATION_XML(ContentType.APPLICATION_XML),
    APPLICATION_CSV(ContentType.create("text/csv"));
    
    
    private final ContentType contentType;

    /**
     * @param contentType
     */
    private MimeType(ContentType contentType) {
        this.contentType = contentType;
    }

    /**
     * @return String
     */
    public String getValue() {
        return contentType.getMimeType();
    }

    /**
     * @return ContentType
     */
    public ContentType getContentType() {
        return contentType;
    }
    
    public static ListBoxModel getContentTypeFillItems() {
        ListBoxModel items = new ListBoxModel();
        for (MimeType mimeType : values()) {
            items.add(mimeType.name());
        }
        return items;
    }

}
