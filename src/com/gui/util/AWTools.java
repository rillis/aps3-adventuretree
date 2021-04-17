package com.gui.util;

import java.awt.*;

public class AWTools {
    public static Dimension center(Dimension parent, Dimension child){
        return new Dimension(parent.width/2 - child.width/2, parent.height/2 - child.height/2);
    }
}
