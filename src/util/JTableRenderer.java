package util;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

public class JTableRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 8126674512740670194L;

    protected void setValue( Object value ) {
        if ( value != null && value instanceof ImageIcon ) {
            ImageIcon d = (ImageIcon) value;
            setIcon( d );
        } else {
            super.setValue( value );
        }
    }
}
