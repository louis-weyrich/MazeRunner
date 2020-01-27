package com.sos.mazerunner;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ResultFrame extends JDialog
{
	public ResultFrame(JFrame frame, SolveResults results )
	{
		super(frame, "Solve Results", ModalityType.APPLICATION_MODAL);
		super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		super.setLayout(new GridLayout(5,2));
		initializeComponents(results);
		super.setSize(250,150);
		super.setLocation(frame.getLocation().x + 10, frame.getLocation().y + 60);
		super.setVisible(true);
	}

	private void initializeComponents(SolveResults results)
	{
		this.add(new JLabel("Rows"));
		this.add(new JLabel(Integer.toString(results.getRows())));
		this.add(new JLabel("Columns"));
		this.add(new JLabel(Integer.toString(results.getColumns())));
		this.add(new JLabel("Strategy Name"));
		this.add(new JLabel(results.getStrategyName()));
		this.add(new JLabel("Visited Cell Count"));
		this.add(new JLabel(Integer.toString(results.getVisitedCount())));
		this.add(new JLabel("Path Count"));
		this.add(new JLabel(Integer.toString(results.getPathCount())));
	}
}
