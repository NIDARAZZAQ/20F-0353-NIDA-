package PresentationLayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import LogicLayer.Bussiness_Logic;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Interface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	//static ArrayList arr01=new ArrayList();
	static ArrayList arr02=new ArrayList();
	static ArrayList arr03=new ArrayList();
	private JTable table;
	private DefaultTableModel strc;
	private JTextField textField_1;
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
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
	public Interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 494);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(68, 238, 233, 46);
		textField.setForeground(Color.RED);
		textField.setBackground(Color.WHITE);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Address Area");
		lblNewLabel.setBounds(67, 197, 125, 46);
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 15));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Upload Data");
		btnNewButton.setBounds(113, 308, 149, 39);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String path=textField.getText();
				
				File Folder= new File(path);
				File[] files = Folder.listFiles();

						
						Bussiness_Logic f=new Bussiness_Logic();
					
		      			Bussiness_Logic.ReadXML(files);
						
					
				
				textField.setText("");
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Sylfaen", Font.BOLD, 14));
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("SPELL CHECKER");
		lblNewLabel_1.setBounds(296, 66, 244, 51);
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 29));
		contentPane.add(lblNewLabel_1);
		
		JScrollPane js=new JScrollPane();
		js.setBounds(0, 0, 0, 0);
		js.setVisible(true);
		getContentPane().add(js);
		
		String[] colNames = { "Word","Frequency"};
	    strc = new DefaultTableModel(colNames, 0);
		
		JButton btnNewButton_1 = new JButton("View Table");
		btnNewButton_1.setBounds(473, 407, 128, 21);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel maknull = (DefaultTableModel)table.getModel();
				maknull.getDataVector().removeAllElements();
				maknull.fireTableDataChanged();

				
				
				try {
					Bussiness_Logic.word_Read();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = 0; i <arr03.size();i++)  {
					int j=0;
					Object[] Tabl = new Object[2];
					 Tabl[j]= arr03.get(i);
		            Tabl[j+1]= arr02.get(i);
		            
                        
		        strc.addRow(Tabl);
				 }
				 table.setModel(strc);
			}
		});
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(352, 133, 399, 264);
		contentPane.add(scrollPane);
		
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Words", "Frequency"
			}
		));
		
		textField_1 = new JTextField();
		textField_1.setBounds(141, 357, 107, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(141, 388, 107, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.setBounds(152, 417, 85, 21);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(textField_1.getText().isEmpty()||textField_2.getText().isEmpty()) {
					System.out.println("Select Some field");
	
				}
				else {
					try {
						update(textField_2.getText(),textField_1.getText());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
					
				
				
			}
		});
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("Old Word");
		lblNewLabel_2.setBounds(68, 360, 63, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New word");
		lblNewLabel_3.setBounds(68, 391, 63, 13);
		contentPane.add(lblNewLabel_3);
		
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(final MouseEvent e) {
		        if (e.getClickCount() == 1) {
		            final JTable jTable= (JTable)e.getSource();
		            final int row = jTable.getSelectedRow();
		            final int column = jTable.getSelectedColumn();
		            final String valueInCell = (String)jTable.getValueAt(row, column);
		            textField_1.setText(valueInCell);
		        }}
		    });	}
	
	
	
	public void update(String newword,String preword) throws SQLException {
		
		Bussiness_Logic.update(newword,preword);
	}

	public void get_Word(ArrayList arr2, ArrayList arr3) {
		// TODO Auto-generated method stub
		arr02=arr2;
		arr03=arr3;  
	}
}