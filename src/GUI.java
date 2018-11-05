//Frank Baiata
import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class GUI extends JFrame
{
	private JPanel mainPane = new JPanel(new BorderLayout());
	private Model m;
	GUI g = this;
	JLabel C0 = new JLabel("Hollow output: ");
	JLabel C1 = new JLabel("Whole output: ");
	public GUI(Model model)
	{
		m=model;
		frameINIT();
		varINIT();
	}
	private void varINIT()
	{
		JPanel inputPane = new JPanel(new GridLayout(0,2));
		JPanel outPane = new JPanel(new GridLayout(0,1));
		JPanel argPane = new JPanel(new FlowLayout());
		JPanel exPane = new JPanel(new FlowLayout());
		mainPane.add(inputPane, BorderLayout.LINE_START);
		mainPane.add(outPane, BorderLayout.LINE_END);
		mainPane.add(argPane, BorderLayout.PAGE_END);
		mainPane.add(exPane, BorderLayout.PAGE_START);
		
		
		JLabel lenOfShaft = new JLabel("Length of Shaft ('in')");
		JTextField lenOfShaftF = new JTextField("18");
		inputPane.add(lenOfShaft);
		inputPane.add(lenOfShaftF);
		
		
		JLabel shrModulus = new JLabel("Shear Modulus ('psi')");
		JTextField shrModulusF = new JTextField("3770000");
		inputPane.add(shrModulus);
		inputPane.add(shrModulusF);
		
		JLabel shrStr = new JLabel("Shear Strength ('psi')");
		JTextField shrStrF = new JTextField("30000");
		inputPane.add(shrStr);
		inputPane.add(shrStrF);
		
		JLabel mxTorque = new JLabel("Max Torque ('in-lb')");
		JTextField mxTorqueF = new JTextField("25000");
		inputPane.add(mxTorque);
		inputPane.add(mxTorqueF);
		
		JLabel mxAOT = new JLabel("Max Angle of Twist ('degree')");
		JTextField mxAOTF = new JTextField("10");
		inputPane.add(mxAOT);
		inputPane.add(mxAOTF);
		
		JLabel mxShaftSz = new JLabel("Max Shaft Size ('in')");
		JTextField mxShaftSzF = new JTextField("2");
		inputPane.add(mxShaftSz);
		inputPane.add(mxShaftSzF);
		
		JLabel minShaftThicc = new JLabel("Minimum Shaft Thickness ('in')");
		JTextField minShaftThiccF = new JTextField(".125");
		inputPane.add(minShaftThicc);
		inputPane.add(minShaftThiccF);
		outPane.add(new JLabel("Hollow"));
		outPane.add(C0);
		outPane.add(new JLabel("Whole"));
		outPane.add(C1);
		
		
		JButton Calculate = new JButton("Calculate!");

		Calculate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				m.ingest(Double.parseDouble(lenOfShaftF.getText()),Double.parseDouble(shrModulusF.getText()),Double.parseDouble(shrStrF.getText()),Double.parseDouble(mxTorqueF.getText()),Double.parseDouble(mxAOTF.getText()),Double.parseDouble(mxShaftSzF.getText()),Double.parseDouble(minShaftThiccF.getText()));	
				m.calculate();
				g.updateO();
				g.pack();
				
				
			}
			
		});
		
		argPane.add(Calculate);
		updateO();

		g.pack();
		
		
		
		
	}
	private void updateO()
	{
		C0.setText("Hollow output: C0["+m.small.c0+"] Ci["+m.small.c1+"]");
		C1.setText("Whole output: C0["+m.large.c0+"] Ci["+m.large.c1+"]");
		

	}
	private void frameINIT()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Trevors Shaft");
		this.getContentPane().add(mainPane);
		this.setSize(1000, 800);
	}

}
