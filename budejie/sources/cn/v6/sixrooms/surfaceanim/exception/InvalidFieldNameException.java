package cn.v6.sixrooms.surfaceanim.exception;

public class InvalidFieldNameException extends RuntimeException {
    public InvalidFieldNameException() {
        super("冲突的字段名字，建议重命名字段名。");
    }
}
