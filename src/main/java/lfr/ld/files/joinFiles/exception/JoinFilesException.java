package lfr.ld.files.joinFiles.exception;

/**
 * The Class JoinFilesException.
 */
public class JoinFilesException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Build a generic JoinFilesException without any message. 
     */
    public JoinFilesException() {
        super();
    }

	/** 
	 * Build a generic JoinFilesException with a message. 
     * @param msg Exception message 
     * */
    public JoinFilesException(final String msg) {
        super(msg);
    }
    
    /** 
     * Build a generic JoinFilesException with a message and cause.
     * @param msg Exception message.
     * @param cause Error cause. 
     * */
    public JoinFilesException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

}
