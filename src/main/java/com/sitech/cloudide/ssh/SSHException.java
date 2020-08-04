package com.sitech.cloudide.ssh;

/**
 * 
 * <p>Title: SSHException</p>
 * <p>Description: ssh异常</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 思特奇 </p>
 * @author heweia
 * @version 1.0
 * @createtime 2019-4-9 上午9:12:38
 *
 */
public class SSHException extends Exception {

    private static final long serialVersionUID = -6213665149000064880L;

    public SSHException() {
        super();
    }

    public SSHException(String message) {
        super(message);
    }

    public SSHException(String message, Throwable cause) {
        super(message, cause);
    }

    public SSHException(Throwable cause) {
        super(cause);
    }

}
