package applications;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Rectangle;
import geometry.Point;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

public class Sort extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Rectangle> dlm=new DefaultListModel<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sort frame = new Sort();
					frame.setTitle("IT14-2017 Tamara Acimovic");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Sort() {
		
		setTitle("IT14-2017 Tamara Acimovic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 550);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Dimension d = new Dimension(80,30);
		
		Image img = new ImageIcon("rsc/Sort.png").getImage();
		setIconImage(img);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JList list = new JList();
		list.setFont(new Font("Arial", Font.PLAIN, 18));
		list.setModel(dlm);
		scrollPane.setViewportView(list);
		contentPane.add(scrollPane);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setBackground(new Color(255, 222, 173));
		contentPane.add(pnlButtons, BorderLayout.NORTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(105, 105, 105));
		btnAdd.setBackground(new Color(255, 235, 205));
		btnAdd.setFont(new Font("Arial", Font.BOLD, 17));
		pnlButtons.add(btnAdd);
		btnAdd.setBorder(new RoundedBorder(3));
		btnAdd.setPreferredSize(d);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Rectangle rect=new Rectangle();
				DlgRectangle dlg= new DlgRectangle();
				dlg.setVisible(true);
				if(dlg.isOk())
				{
					rect.setUpperLeftPoint(new Point(Integer.parseInt((dlg.getTxtX().getText())),Integer.parseInt(dlg.getTxtY().getText())));
					rect.setWidth(Integer.parseInt(dlg.getTxtWidth().getText()));
					rect.setHeight(Integer.parseInt(dlg.getTxtHeight().getText()));
					dlm.addElement(rect);
				}
			}
		});
		
		JButton btnSort = new JButton("Sort");
		btnSort.setForeground(new Color(105, 105, 105));
		btnSort.setBackground(new Color(255, 235, 205));
		btnSort.setFont(new Font("Arial", Font.BOLD, 17));
		pnlButtons.add(btnSort);
		btnSort.setBorder(new RoundedBorder(3));
		btnSort.setPreferredSize(d);
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Rectangle> temp=new ArrayList<Rectangle>();
				if(!dlm.isEmpty()) {
					for(int i=0;i<dlm.size();i++){
					temp.add(i,dlm.getElementAt(i));
							}
					for(int i=0;i<temp.size();i++) {
						for(int j=0;j<temp.size()-1;j++) {
							if(temp.get(j).compareTo(temp.get(j+1))>0) {
								Rectangle temprect=temp.get(j);
								temp.set(j, temp.get(j+1));
								temp.set(j+1, temprect);
							}
						}
					}
				dlm.removeAllElements();
				for(Rectangle i:temp) {//prolazi kroz celu listu
					dlm.addElement(i);
					}
				}
				else 
					JOptionPane.showMessageDialog(new JFrame(), "List is empty.", "Error!", JOptionPane.WARNING_MESSAGE);
			}
		});
		
	}
}
