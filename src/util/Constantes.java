package util;

import java.util.Locale;

public final class Constantes {

    private Constantes() {
        super();
    }

    /** Nome do arquivo config.properties. */
    public static final String BUNDLE_CONFIG = "config";

    /** Nome do arquivo messages.properties. */
    public static final String BUNDLE_MESSAGES = "messages";
    
    /** Arquivo de configuracao do Hibernate */
    public static final String ARQ_CONFIG_HIBERNATE_APP = "hibernate.cfg.xml";
    
    /** Titulo */
    public static final String APP_TITLE = "IMPORTAÇÃO ARQUIVO MARCAÇÕES DE PONTO";

    /** Ambiente de desenvolvimento local. */
    public static final String APP_AMBIENTE_LOCAL = "local";

    /** Titulo para inibir a verificacao de avisos (warnings). */
    public static final String UNCHECKED = "unchecked";

    /** Locale Ingles-Estados Unidos */
    public static final Locale LOCALE_EN_US = new Locale( "en", "US" );

    /** Locale Espanhol */
    public static final Locale LOCALE_ES = new Locale( "es" );

    /** Locale Portugues-Brasil */
    public static final Locale LOCALE_PT_BR = new Locale( "pt", "BR" );

    /** */
    public static final String EXTENSAO_XML = ".xml";

    /** */
    public static final String EXTENSAO_EXCEL = ".xls";

    /** */
    public static final String EXTENSAO_TXT = ".txt";

    /** Valor inicial padrao de combos */
    public static final String SELECIONE = "-- Selecione --";

    /** Look and feel Nimbus */
    public static final String LOOK_AND_FEEL_NIMBUS = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
}
