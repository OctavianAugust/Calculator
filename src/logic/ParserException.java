package logic;

public class ParserException extends Exception{
    
    private static final long serialVersionUID = 1L;
    private String errStr;  //  Описание ошибки
 
    public ParserException(String errStr) {
        this.errStr = errStr;
    }
    
    public String toString(){
        return this.errStr;
    }
}