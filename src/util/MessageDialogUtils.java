package util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.lang.StringUtils;

public final class MessageDialogUtils {

    public static void showMessageInformation( final String msg, final String title ) {
        JOptionPane.showMessageDialog( new JFrame( StringUtils.EMPTY ), msg, title, JOptionPane.INFORMATION_MESSAGE );
    }
}
