/**
 * 
 */
package com.sos.mazerunner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.text.ParseException;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.sos.mazerunner.strategy.Strategy;
import com.sos.mazerunner.strategy.StrategyFactory;
import com.sos.mazerunner.util.CSVParser;
import com.sos.mazerunner.util.GridArray;
import com.sos.mazerunner.util.RowSet;

/**
 * @author louisweyrich
 *
 */
public class MazeRunnerFrame extends JFrame implements WindowListener, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2628122966714509961L;
	private Maze maze = null;
	private JMenuItem solve;
	private StrategyFactory strategyFactory;
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String [] args)
	{
		new MazeRunnerFrame();
	}

	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public MazeRunnerFrame() throws HeadlessException
	{
		super("Maze Runner");
		this.addWindowListener(this);
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setResizable(true);
		super.setLayout(new BorderLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setSize(screenSize.width -50, screenSize.height - 50);
	    strategyFactory = StrategyFactory.instance();
	    initializeMenu();
	    initializeComponents();
	    setVisible(true);
	    
	}
	
	private void initializeMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("Load Maze");
		
		// Load Mazes
		File file = new File("./mazes");
		if(file.exists() && file.isDirectory())
		{
			FilenameFilter filter = new FilenameFilter()
			{

				@Override
				public boolean accept(File dir, String name)
				{
					return name.endsWith(".maz");
				}
		
			};
			String [] fileNames = file.list(filter);
			
			for(int index = 0; index < fileNames.length; index++)
			{
				String fileName = fileNames[index];
				
				JMenuItem item = new JMenuItem(fileName);
				item.addActionListener(this);
				menu.add(item);
			}
		}
		menuBar.add(menu);
		
		
		// add Strategy
		JMenu strategyMenu = new JMenu("Strategies");
		for(String strategyName : strategyFactory.buildStrategyList())
		{
			JMenuItem mi = new JMenuItem(strategyName);
			mi.setActionCommand(strategyName);
			mi.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e)
				{
					Strategy strategy = strategyFactory.getStrategyByName(e.getActionCommand());
					maze.setStrategy(strategy);
				}
				
			});
			strategyMenu.add(mi);
		}
		menuBar.add(strategyMenu);
		
		
		// Add Run Menu
		JMenu runMenu = new JMenu("Run");
		solve = new JMenuItem("Solve Maze");
		final JFrame thisFrame = this;
		solve.addActionListener(new ActionListener() {

			
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				solve.setEnabled(false);
				SolveResults result = maze.solve();
				System.out.println(result.toString());
				ResultFrame frame = new ResultFrame(thisFrame, result);
			}
			
		});
		solve.setEnabled(false);
		runMenu.add(solve);
		menuBar.add(runMenu);
		
		JMenuItem closeMenu = new JMenuItem("Quit");
		closeMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(DISPOSE_ON_CLOSE);
			}
			
		});
		
		menuBar.add(closeMenu);
		
		super.add(menuBar, BorderLayout.NORTH);
	}
	
	private void initializeComponents()
	{
		maze = new Maze();
		super.add(maze, BorderLayout.CENTER);
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		System.exit(0);
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		loadFile(e.getActionCommand());
	}

	
	public void loadFile(String fileName)
	{
		maze.clearResults();
		
		
		File file = new File("./mazes/"+fileName);
		
		if(file.exists())
		{
			solve.setEnabled(true);
			
			int [] sizeArray = parseFileName(fileName);
			try
			{
				CSVParser parser = new CSVParser(file,',', false);
				if(parser.hasData())
				{
					Iterator <RowSet<String>> iterator = parser.iterator();
					int rowCount = 0;
					int columnCount = 0;
					GridArray <Cell> grid = new GridArray<Cell>(sizeArray[0], sizeArray[1]);
					
					while(iterator.hasNext())
					{
						RowSet<String> row = iterator.next();
						Iterator <String> dataIterator = row.dataIterator();
						while(dataIterator.hasNext())
						{
							String data = dataIterator.next();
							switch(data)
							{
								case "0" : {
									grid.addData(new Cell(rowCount, columnCount++, CellType.WALL));
									break;
								}
								case "1" : {
									grid.addData(new Cell(rowCount, columnCount++, CellType.PATH));
									break;
								}
								case "2" : {
									grid.addData(new Cell(rowCount, columnCount++, CellType.GOAL));
									break;
								}
							}
						}
						rowCount++;
						columnCount = 0;
						
					}
					maze.setGridArray(grid);
					this.repaint();
				}
				
			} 
			catch (FileNotFoundException | ParseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	protected int[] parseFileName(String fileName)
	{
		int [] result = new int[2];
		
		String [] values = fileName.substring(0, fileName.indexOf(".")).split("x");
		
		result [0] = Integer.parseInt(values[0]);
		result [1] = Integer.parseInt(values[1]);
		
		return result;
	}
}
