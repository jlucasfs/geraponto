package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import model.Collaborator;
import util.Constantes;

/**
 * @author thiago.lima
 *
 */
public class Main extends JFrame {

	private static final long serialVersionUID = 7814972484416064266L;

	static HashSet<Collaborator> collaborators;

	private JPanel contentPane;

	private JDesktopPane desktopPane = new JDesktopPane();

	// Menus
	private JMenu mnCadastro = new JMenu("Cadastro");
	private JMenuItem mntmColaborador = new JMenuItem("Colaborador");
	private JMenuItem mntmImportacao = new JMenuItem("Importar marcacoes");

	// Actions
	private final Action itemColaboradorAction = new ItemColaboradorAction();
	private final Action itemImportacaoAction = new ItemImportacaoAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// HibernateUtil.initialize();
			UIManager.setLookAndFeel(Constantes.LOOK_AND_FEEL_NIMBUS);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowOpened(WindowEvent e) {
				setExtendedState(MAXIMIZED_BOTH);
			}
		});
		setTitle(Constantes.APP_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 526);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuBar.add(mnCadastro);

		mntmColaborador.setAction(itemColaboradorAction);
		mnCadastro.add(mntmColaborador);

		mnCadastro.addSeparator();

		mntmImportacao.setAction(itemImportacaoAction);
		mnCadastro.add(mntmImportacao);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		
		mntmImportacao.doClick();
	}

	private class ItemColaboradorAction extends AbstractAction {

		private static final long serialVersionUID = 8889834435576927855L;

		public ItemColaboradorAction() {
			putValue(NAME, "Colaborador");
			putValue(SHORT_DESCRIPTION, "Cadastro de Colaborador");
		}

		public void actionPerformed(ActionEvent e) {
//			ColaboradorView item = new ColaboradorView(desktopPane);
//			desktopPane.add(item);
//			item.setVisible(true);
		}
	}

	private class ItemImportacaoAction extends AbstractAction {

		private static final long serialVersionUID = -6721667389598666270L;

		public ItemImportacaoAction() {
			putValue(NAME, "Importar Marcações");
			putValue(SHORT_DESCRIPTION, "Importar marcações de ponto");
		}

		public void actionPerformed(ActionEvent e) {
			ImportacaoView item = new ImportacaoView(desktopPane);
			desktopPane.add(item);
			item.setVisible(true);
		}
	}

}
