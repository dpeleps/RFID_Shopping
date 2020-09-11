/*
Daniel Pelepelin
112096007
Rec 30 Section 2
*/

public class InvalidLocationException extends RuntimeException{
    public InvalidLocationException() {
        super("Location is not valid.");
    }
}
