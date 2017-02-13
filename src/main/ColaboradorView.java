package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import util.JTableRenderer;

public class ColaboradorView extends JInternalFrame {

    private static final long serialVersionUID = -6599491686439229668L;

    private JTable table;
    
    private DefaultTableModel model = new DefaultTableModel() {
        private static final long serialVersionUID = 160347672712789415L;
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JTextField txtNome;
    
    private JComboBox<Time> comboTimes = new JComboBox<Time>();

    private JDesktopPane desktopPane;
    
    /**
     * Launch the application.
     */
    public static void main( String[] args ) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch ( ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1 ) {
            e1.printStackTrace();
        }
        EventQueue.invokeLater( new Runnable() {
            public void run() {
                try {
                    ColaboradorView frame = new ColaboradorView();
                    frame.setVisible( true );
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        } );
    }

    public ColaboradorView( JDesktopPane desktopPaneParam ) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch ( ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1 ) {
            e1.printStackTrace();
        }
        this.desktopPane = desktopPaneParam;
        initComponent();
    }
    
    public ColaboradorView() {
        initComponent();
    }

    private void initComponent() {
    	setIconifiable( true );
        setMaximizable( true );
        setClosable( true );
        setTitle( "Colaborador" );
        setBounds( 100, 100, 630, 391 );

        JPanel panelPrincipal = new JPanel();

        JScrollPane scrollPane = new JScrollPane();

        JPanel panelBotoes = new JPanel();
        GroupLayout groupLayout = new GroupLayout( getContentPane() );
        groupLayout.setHorizontalGroup( groupLayout.createParallelGroup( Alignment.LEADING )
                .addGroup( groupLayout.createSequentialGroup().addGap( 2 )
                        .addComponent( panelPrincipal, GroupLayout.PREFERRED_SIZE, 611, GroupLayout.PREFERRED_SIZE ).addContainerGap() )
                .addGroup( groupLayout.createSequentialGroup().addComponent( panelBotoes, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE ).addGap( 2 ) )
                .addGroup( groupLayout.createSequentialGroup().addComponent( scrollPane, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE ).addContainerGap() ) );
        groupLayout.setVerticalGroup( groupLayout.createParallelGroup( Alignment.LEADING )
                .addGroup( groupLayout.createSequentialGroup().addComponent( panelPrincipal, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE )
                        .addPreferredGap( ComponentPlacement.RELATED )
                        .addComponent( panelBotoes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE )
                        .addPreferredGap( ComponentPlacement.RELATED ).addComponent( scrollPane, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE )
                        .addContainerGap( 16, Short.MAX_VALUE ) ) );

        JButton btnConsultar1 = new JButton( "consultar" );
        btnConsultar1.addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent e ) {
//            	Countries country = (Countries) comboCountries.getSelectedItem();
//            	Time time = (Time) comboTimes.getSelectedItem();
//                getCompetidores( model, txtNome.getText(), country, time );
            }
        } );
        panelBotoes.add( btnConsultar1 );

        JButton btnLimpar = new JButton( "limpar" );
        btnLimpar.addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent e ) {
                txtNome.setText( "" );
//                comboCountries.setSelectedIndex(0);
                comboTimes.setSelectedIndex(0);
                txtNome.requestFocus();
            }
        } );
        panelBotoes.add( btnLimpar );

        final ColaboradorView thisview = this;
        JButton btnNovo = new JButton( "novo" );
        btnNovo.addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent e ) {
//                CompetidorEditView frame = new CompetidorEditView( competidorManager, thisview, model );
//                desktopPane.add( frame );
//                frame.setVisible( true );
            }
        } );
        
        panelBotoes.add( btnNovo );
        
        table = new JTable( model );
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int coluna = table.getSelectedColumn();
                if ( coluna == 4 ) {
                    alterar();
                } else if ( coluna == 5 ) {
                    excluir();
                }
            }

            private void alterar() {
//                Long id = (Long) ((DefaultTableModel)table.getModel()).getValueAt( table.getSelectedRow(), 0 );
//                CompetidorEditView frame = new CompetidorEditView( competidorManager, thisview, model, id );
//                desktopPane.add( frame );
//                frame.setVisible( true );
            }
            
            private void excluir() {
//                String descricao = (String) ((DefaultTableModel)table.getModel()).getValueAt( table.getSelectedRow(), 1 );
//                if ( JOptionPane.showConfirmDialog( null, "Confirma exclusão de " + descricao, "Exclusão", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION ) {
//                    Long id = (Long) ((DefaultTableModel)table.getModel()).getValueAt( table.getSelectedRow(), 0 );
//                    Competidor competidor = competidorManager.findById( id );
//                    competidorManager.excluir( competidor );
//                    ((DefaultTableModel)table.getModel()).removeRow( table.getSelectedRow() );
//                }
            }
        });
        model.addColumn( "ID" );
        model.addColumn( "nome" );
        model.addColumn( "classe" );
        model.addColumn( "pais" );
        model.addColumn( "alterar" );
        model.addColumn( "excluir" );
        table.getColumnModel().getColumn( 0 ).setPreferredWidth( 20 );
        table.getColumnModel().getColumn( 1 ).setPreferredWidth( 200 );
        table.getColumnModel().getColumn( 2 ).setPreferredWidth( 100 );
        table.getColumnModel().getColumn( 3 ).setPreferredWidth( 150 );
        table.getColumnModel().getColumn( 4 ).setPreferredWidth( 30 );
        table.getColumnModel().getColumn( 5 ).setPreferredWidth( 30 );
        
        // botoes
        JTableRenderer jTableRenderer = new JTableRenderer();
        jTableRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        table.getColumnModel().getColumn( 4 ).setCellRenderer( jTableRenderer );
        table.getColumnModel().getColumn( 5 ).setCellRenderer( jTableRenderer );
        table.setRowHeight( 30 );
        
        getColaboradores( model, null );
        
        TableColumnModel colunaModelo = table.getColumnModel();      
        colunaModelo.getColumn(0).setMinWidth(0);     
        colunaModelo.getColumn(0).setPreferredWidth(0);  
        colunaModelo.getColumn(0).setMaxWidth(0);     

        scrollPane.setViewportView( table );
        
        JLabel lblNome = new JLabel( "Nome" );

        txtNome = new JTextField();
        txtNome.setColumns( 10 );
        
        GroupLayout gl_panelPrincipal = new GroupLayout( panelPrincipal );
        gl_panelPrincipal.setHorizontalGroup(
            gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelPrincipal.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNome)
                        .addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panelPrincipal.createParallelGroup(Alignment.LEADING))
                    .addGap(18)
                    .addGroup(gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
                        .addComponent(comboTimes, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
                    .addGap(12))
        );
        gl_panelPrincipal.setVerticalGroup(
            gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelPrincipal.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNome))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
                        .addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        panelPrincipal.setLayout( gl_panelPrincipal );
        getContentPane().setLayout( groupLayout );
    }
    
    public void getColaboradores( DefaultTableModel model, String nome ) {
        model.setNumRows( 0 );
        Icon alterar = new ImageIcon( getClass().getResource( "/main/edit.png" ) );
        Icon excluir = new ImageIcon( getClass().getResource( "/main/delete.png" ) );
//        for ( Competidor c: competidorManager.listar( nome, country, time ) ) {
//            model.addRow( new Object[] {
//                    c.getId(), c.getNome(), c.getClasse().getNome(), c.getCountry().getName(), alterar, excluir
//            } );
//        }
    }
    
    public DefaultTableModel getModel() {
        return this.model;
    }

    public void setModel( DefaultTableModel modelParam ) {
        this.model = modelParam;
    }

    public JTextField getTxtNome() {
        return this.txtNome;
    }

    public void setTxtNome( JTextField txtNomeParam ) {
        this.txtNome = txtNomeParam;
    }

	public JComboBox<Time> getComboTimes() {
		return comboTimes;
	}

	public void setComboTimes(JComboBox<Time> comboTimes) {
		this.comboTimes = comboTimes;
	}

}
