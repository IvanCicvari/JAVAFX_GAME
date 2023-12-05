package algebra.hr.gamejava.customExceptions;


public class InvalidPlayerTurnException extends Exception
{
    String playerID;
    public InvalidPlayerTurnException(String message,String pid){
        super(message);
        playerID=pid;
    }
    public String getPid(){
        return playerID;
    }
}