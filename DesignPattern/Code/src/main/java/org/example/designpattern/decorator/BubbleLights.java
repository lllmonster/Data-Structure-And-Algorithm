package org.example.designpattern.decorator;

public class BubbleLights extends TreeDecorator{

    public BubbleLights(ChristmasTree tree) {
        super(tree);
    }

    @Override
    public String decorate() {
        return super.decorate() + decorateWithBubbleLights();
    }

    private String decorateWithBubbleLights() {
        return " With Bubble lights";
    }
}
