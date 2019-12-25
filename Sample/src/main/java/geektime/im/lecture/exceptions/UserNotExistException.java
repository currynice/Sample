package geektime.im.lecture.exceptions;


/**
 * 用户不存在异常
 */
public class UserNotExistException extends RuntimeException {

    public UserNotExistException(String message) {
        super(message);
    }
}
