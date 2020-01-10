package net.test;

import java.util.function.Consumer;

public interface Service extends Consumer<String> {
    String getType();

    /**
     * To flush out lambda support in Idea - i.e. setting required in pom.xml.
     */
    @Override
    public default void accept(String s) {
    }

}
