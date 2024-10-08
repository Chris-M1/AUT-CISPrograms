package Question_2;

import java.awt.Point;

/*
*  @author William Wang
*
* this class is used to represent the body of the snake
* it contains the body character, the x and y coordinates of the body
* and the previous and next body
* 
*/

public class SnakeBody {

    // the previous and next body of the snake
    private SnakeBody prev;
    private char body;
    private SnakeBody next;
    private Point location;

    public SnakeBody(char body) {
        this.body = body;
    }

    public SnakeBody(char body, Point location) {
        this.body = body;
        this.location = location;
    }

    public SnakeBody getPrev() {
        return prev;
    }

    public void setPrev(SnakeBody prev) {
        this.prev = prev;
    }

    public char getBody() {
        return body;
    }

    public void setBody(char body) {
        this.body = body;
    }

    public SnakeBody getNext() {
        return next;
    }

    public void setNext(SnakeBody next) {
        this.next = next;
    }

    public void setLocation(int x, int y) {
        this.location.x = x;
        this.location.y = y;
    }

    public Point getLocation() {
        return this.location;
    }

    public String toString() {
        return String.valueOf(this.body);
    }

}
