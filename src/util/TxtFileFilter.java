package util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class TxtFileFilter extends FileFilter {

    public boolean accept( File f ) {
        boolean retorno = false;
        if ( f.isDirectory() ) {
            retorno = true;
        }

        String filename = f.getName();
        if ( filename.endsWith( ".txt" )) {
           retorno = true;
        } 
        
        return retorno;
    }

    public String getDescription() {
        return "*.txt;";
    }
}
