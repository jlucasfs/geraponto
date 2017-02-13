package util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;

public class JFrameUtils {

    public static void centreWindow( JInternalFrame frame ) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ( ( dimension.getWidth() - frame.getWidth() ) / 2 );
        int y = (int) ( ( dimension.getHeight() - frame.getHeight() ) / 2 );
        frame.setLocation( x, y );
    }
}
