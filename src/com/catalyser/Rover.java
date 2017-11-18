package com.catalyser;

import java.awt.Point;

import com.catalyser.common.Direction;


public class Rover {

    private Point point;

    private Direction direction;
    
    public Rover() {
    	
    }

    public Rover(Point point, Direction direction) {
        this.point = point;
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
