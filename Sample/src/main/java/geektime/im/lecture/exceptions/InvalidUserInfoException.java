package geektime.im.lecture.exceptions;


/**
 * 非法用户信息异常
 */
public class InvalidUserInfoException extends RuntimeException {
    public InvalidUserInfoException(String message) {
        super(message);
    }
}
