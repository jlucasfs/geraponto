package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Classe ConstantesData responsavel por manter valores constantes do tipo data.
 */
public final class ConstantesData {

    /**
     * Constroi a instancia de 'ConstantesData'.
     */
    private ConstantesData() {
        super();
    }

    /** Formato de data padrao dd */
    public static final String FORMATO_DIA = "dd";

    /** Formato de data padrao dd/MM/yyyy */
    public static final String FORMATO_DATA = "dd/MM/yyyy";

    /** Formato de data padrao dd/MM/yy */
    public static final String FORMATO_DATA2 = "dd/MM/yy";

    /** Formato de data padrao yyyy/MM/dd HH:mm:ss */
    public static final String FORMATO_DATA_HORA2 = "yyyy/MM/dd HH:mm:ss";

    /** Formato de data padrao yyyyMMdd-HHmmss */
    public static final String FORMATO_DATA_HORA3 = "yyyyMMdd_HHmmss";

    /** Formato de data padrao yyyyMMddHHmmss */
    public static final String FORMATO_DATA_HORA4 = "yyyyMMddHHmmss";
    
    /** Formato de data padrao yyyy-MM-dd HH:mm:ss */
    public static final String FORMATO_DATA_HORA5 = "yyyy-MM-dd HH:mm:ss";

    /** Formato de data padrao yyyyMMdd */
    public static final String FORMATO_ANO_MES_DIA = "yyyyMMdd";

    /** Formato de data MM/yyyy. */
    public static final String FORMATO_MES_ANO = "MM/yyyy";

    /** Formato de data MMM/yyyy. */
    public static final String FORMATO_MES_ANO2 = "MMM/yyyy";

    /** Formato de data yy. */
    public static final String FORMATO_ANO2 = "yy";

    /** Formato de data yyyy. */
    public static final String FORMATO_ANO4 = "yyyy";

    /** Formato de hora padrao HH:mm:ss */
    public static final String FORMATO_HORA = "HH:mm:ss";

    /** Formato de hora padrao HH:mm */
    public static final String FORMATO_HHMM = "HH:mm";

    /** Formato de data e hora padrao dd/MM/yyyy HH:mm:ss */
    public static final String FORMATO_DATA_HORA = FORMATO_DATA + ' ' + FORMATO_HORA;

    /** Formato de data e hora padrao dd/MM/yyyy HH:mm */
    public static final String FORMATO_DATA_HHMM = FORMATO_DATA + ' ' + FORMATO_HHMM;

    /** Objeto SimpleDatFormat a ser utilizado no formato de data padrao dd/MM/yyyy */
    public static final DateFormat FMT_DATA = new SimpleDateFormat( FORMATO_DATA );

    /** Objeto SimpleDatFormat a ser utilizado no formato de data padrao dd/MM/yy */
    public static final DateFormat FMT_DATA2 = new SimpleDateFormat( FORMATO_DATA2 );

    /** Objeto SimpleDatFormat a ser utilizado no formato de hora padrao HH:mm:ss */
    public static final DateFormat FMT_HORA = new SimpleDateFormat( FORMATO_HORA );

    /** Objeto SimpleDatFormat a ser utilizado no formato de hora padrao HH:mm */
    public static final DateFormat FMT_HHMM = new SimpleDateFormat( FORMATO_HHMM );

    /** Objeto SimpleDatFormat a ser utilizado no formato de hora padrao dd/MM/yyyy HH:mm:ss */
    public static final DateFormat FMT_DATA_HORA = new SimpleDateFormat( FORMATO_DATA_HORA );

    /** Objeto SimpleDatFormat a ser utilizado no formato: dd */
    public static final DateFormat FMT_DIA = new SimpleDateFormat( FORMATO_DIA );
    
    /** Objeto SimpleDatFormat a ser utilizado no formato de hora padrao dd/MM/yyyy HH:mm */
    public static final DateFormat FMT_DATA_HHMM = new SimpleDateFormat( FORMATO_DATA_HHMM );

    /** Objeto SimpleDatFormat a ser utilizado no formato MM/yyyy. */
    public static final DateFormat FMT_MMYYYY = new SimpleDateFormat( FORMATO_MES_ANO );
    
    /** Objeto SimpleDatFormat a ser utilizado no formato MMM/yyyy. */
    public static final DateFormat FMT_MMMYYYY = new SimpleDateFormat( FORMATO_MES_ANO2 );

    /** Objeto SimpleDatFormat a ser utilizado no formato yy. */
    public static final DateFormat FMT_YY = new SimpleDateFormat( FORMATO_ANO2 );

    /** Objeto SimpleDatFormat a ser utilizado no formato yyyy. */
    public static final DateFormat FMT_YYYY = new SimpleDateFormat( FORMATO_ANO4 );

    /** Objeto SimpleDatFormat a ser utilizado no formato de hora padrao yyyy/MM/dd HH:mm:ss */
    public static final DateFormat FMT_DATA_HORA2 = new SimpleDateFormat( FORMATO_DATA_HORA2 );

    /** Objeto SimpleDatFormat a ser utilizado no formato de data/hora para criacao de arquivo de saida - yyyyMMdd_HHmmss */
    public static final DateFormat FMT_DATA_HORA3 = new SimpleDateFormat( FORMATO_DATA_HORA3 );

    /** Objeto SimpleDatFormat a ser utilizado no formato de data/hora para criacao de arquivo de leitura - yyyyMMddHHmmss */
    public static final DateFormat FMT_DATA_HORA4 = new SimpleDateFormat( FORMATO_DATA_HORA4 );
    
    /** Objeto SimpleDatFormat a ser utilizado no formato de data/hora para criacao de arquivo de leitura - yyyy-MM-dd HH:mm:ss */
    public static final DateFormat FMT_DATA_HORA5 = new SimpleDateFormat( FORMATO_DATA_HORA5 );

    /** Objeto SimpleDatFormat a ser utilizado no formato de data/hora para criacao de arquivo de leitura - yyyyMMdd */
    public static final DateFormat FMT_DATA_ANO_MES_DIA = new SimpleDateFormat( FORMATO_ANO_MES_DIA );

    /** Objeto SimpleDatFormat a ser utilizado no formato de nome abreviado do mes - MMM */
    public static final DateFormat FMT_NOME_MES = new SimpleDateFormat( "MMM" );
    
    /** Formato de data e hora padrao yyyyMM */
    public static final String FORMATO_ANO_MES = "yyyyMM";
    
    /** Objeto SimpleDatFormat a ser utilizado no formato ano/mes - yyyyMM */
    public static final DateFormat FMT_ANO_MES = new SimpleDateFormat( FORMATO_ANO_MES );

    public static final DateFormat FMT_DIA_SEMANA = new SimpleDateFormat( "ddd" );
}
