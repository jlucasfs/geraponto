package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.apache.commons.lang.StringUtils;

import util.TxtFileFilter;

public class ImportacaoView extends JInternalFrame {

	private static final long serialVersionUID = 3898923933876586401L;

	private final ImportacaoView thisview = this;

	private JLabel txtCompeticao = new JLabel();

	private JScrollPane scrollPaneGrupos;

	private JTable tableGrupos;

	private JLabel lblArquivo;

	private JTextField txtCaminhoArquivo;

	private JButton btnSelecionar;

	private JFileChooser flcArquivo;

	private JDesktopPane desktopPane;
	
	private JTextField textField;
	
	private String extensao;

	public ImportacaoView(JDesktopPane desktopPaneParam) {
		this.desktopPane = desktopPaneParam;

		initComponents();
	}

	private void initComponents() {
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Importar Marcacoes");
		setBounds(100, 100, 578, 240);

		JButton btnNewButton = new JButton("Gerar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(StringUtils.EMPTY);
			}
		});

		textField = new JTextField();
		textField.setColumns(10);
		textField.setEditable(false);
		textField.setText("Arquivo nao selecionado.");

		JLabel lblArquivo_1 = new JLabel("Arquivo:");

		flcArquivo = new JFileChooser();
		flcArquivo.setFileFilter(new TxtFileFilter());
//		flcArquivo.setCurrentDirectory(new File("C:/"));

		JButton btnSelecione = new JButton("Selecionar");
		btnSelecione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = flcArquivo.showOpenDialog( thisview );
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = flcArquivo.getSelectedFile();
					textField.setText(file.getAbsolutePath());
					extensao = file.getName().substring(file.getName().lastIndexOf('.'));
				}
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addGap(54)
								.addComponent(lblArquivo_1).addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSelecione)
								.addContainerGap(63, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap(151, Short.MAX_VALUE)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 77,
										GroupLayout.PREFERRED_SIZE)
								.addGap(111)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addGap(141)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(65)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblArquivo_1).addComponent(btnSelecione))
				.addGap(49).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
				.addContainerGap(50, Short.MAX_VALUE)));

		getContentPane().setLayout(groupLayout);
	}

}
