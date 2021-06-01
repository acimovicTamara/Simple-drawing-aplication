package applications;


import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.lang.invoke.CallSite;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;

public class Stack extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Rectangle> dlm=new DefaultListModel<>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stack frame = new Stack();
					frame.setTitle("IT14-2017 Tamara Acimovic");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Stack() {
		
		setTitle("IT14-2017 Tamara Acimovic");
		setForeground(SystemColor.activeCaption); //podesavanje prozora
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //dugme za zatvaranje aplikacije
		setBounds(100, 100, 550, 550);
		
		Image img = new ImageIcon("rsc/Stack.png").getImage();
		setIconImage(img);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Dimension d = new Dimension(80,30);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JList list = new JList();
		list.setFont(new Font("Arial", Font.PLAIN, 18));
		list.setModel(dlm);
		
		contentPane.setLayout(new BorderLayout(0, 0));
		scrollPane.setViewportView(list);
		contentPane.add(scrollPane);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setBackground(new Color(255, 222, 173));
		contentPane.add(pnlButtons, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(105, 105, 105));
		btnAdd.setBackground(new Color(255, 235, 205));
		pnlButtons.add(btnAdd);
		btnAdd.setFont(new Font("Arial", Font.BOLD, 17));
		btnAdd.setBorder(new RoundedBorder(3));
		btnAdd.setPreferredSize(d);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Rectangle rect = new Rectangle();
				DlgRectangle dlg = new DlgRectangle();
				dlg.setVisible(true);
				if(dlg.isOk())
				{
					rect.setUpperLeftPoint(new Point(Integer.parseInt((dlg.getTxtX().getText())),Integer.parseInt(dlg.getTxtY().getText())));
					rect.setWidth(Integer.parseInt(dlg.getTxtWidth().getText()));
					rect.setHeight(Integer.parseInt(dlg.getTxtHeight().getText()));
					dlm.add(0,rect);
				}
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(105, 105, 105));
		btnDelete.setBackground(new Color(255, 235, 205));
		pnlButtons.add(btnDelete);
		btnDelete.setFont(new Font("Arial", Font.BOLD, 17));
		btnDelete.setBorder(new RoundedBorder(3));
		btnDelete.setPreferredSize(d);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!dlm.isEmpty()) {
					
					Rectangle rect=(Rectangle) dlm.getElementAt(0);
					DlgRectangle dr=new DlgRectangle();
					
					dr.setTxtX(Integer.toString(rect.getUpperLeftPoint().getX()));
					dr.setTxtY(Integer.toString(rect.getUpperLeftPoint().getY()));
					dr.setTxtHeight(Integer.toString(rect.getHeight()));
					dr.setTxtWidth(Integer.toString(rect.getWidth()));
					
					dr.setTxtXEdit(false);
					dr.setTxtYEdit(false);
					dr.setTxtHeightEdit(false);
					dr.setTxtWidthEdit(false);
					
					dr.setVisible(true);
					
					if(dr.isOk());
					dlm.removeElementAt(0);
				}
				else if(dlm.isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "List is empty.", "Error!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
	}
}
