import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

interface KListener {
	public void actionPerformed(KEvent e);
}

class KEvent {
	int x;
	int y;
	KWidget kb;

	public KEvent(int x, int y, KWidget b) {
		this.x = x;
		this.y = y;
		this.kb = b;
	}

	KWidget getSource() {
		return kb;
	}
}

class Frame extends JFrame implements MouseListener {
	List<KWidget> btnList;
	KButton rect, oval, line, text;
	KButton red, blue, black;
	KButton fillRed, fillBlue, fillBlack;
	KButton group, move, delete, copy;
	KButton save, open;
	KCheckBox gender;
	RubberPanel drawing = new RubberPanel(this);
	MenuBar menuBar;
	Menu Figure, ColorChoice, fillColor, Etc, File, Copy;
	int cntD = 0, cntF = 0;
	final static int FigureMenu = 5, ColorMenu = 4, FillColorMenu = 3,
			EtcMenu = 2, FileMenu = 1;

	class FileListener implements KListener {
		public void actionPerformed(KEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == save) {
				drawing.Save();
			} else if (e.getSource() == open) {
				drawing.Open();
			}
			File.hide(getGraphics());
		}
	}

	class FigureListener implements KListener {
		public void actionPerformed(KEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == rect) {
				drawing.Mode(drawing.Rec);
			} else if (e.getSource() == oval) {
				drawing.Mode(drawing.Oval);
			} else if (e.getSource() == line) {
				drawing.Mode(drawing.Line);
			} else if (e.getSource() == text) {
				drawing.Mode(drawing.Text);
			}
			Figure.hide(getGraphics());
		}
	}

	class EtcListener implements KListener {
		public void actionPerformed(KEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == group) {
				drawing.Mode(drawing.Group);
			} else if (e.getSource() == move) {
				drawing.Mode(drawing.Move);
			} else if (e.getSource() == delete) {
				drawing.Mode(drawing.Delete);
			} else if (e.getSource() == copy) {
				drawing.Mode(drawing.Copy);
			}
			Etc.hide(getGraphics());
		}
	}

	class ColorListener implements KListener {
		public void actionPerformed(KEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == red) {
				drawing.ColorChoice(drawing.red);
			} else if (e.getSource() == blue) {
				drawing.ColorChoice(drawing.blue);
			} else if (e.getSource() == black) {
				drawing.ColorChoice(drawing.black);
			}
			ColorChoice.hide(getGraphics());
		}
	}

	class FillColorListener implements KListener {
		public void actionPerformed(KEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == fillRed) {
				drawing.BackGroundColor(drawing.red);
			} else if (e.getSource() == fillBlue) {
				drawing.BackGroundColor(drawing.blue);
			} else if (e.getSource() == fillBlack) {
				drawing.BackGroundColor(drawing.black);
			}
			drawing.Mode(drawing.FillColor);
			fillColor.hide(getGraphics());
		}
	}

	class MyKListener implements KListener {
		@Override
		public void actionPerformed(KEvent e) {
			addDrawing();
			menuBar.hide(getGraphics());
			// TODO Auto-generated method stub
			if (e.getSource() == Figure) {
				Figure.show(getGraphics());
			} else if (e.getSource() == ColorChoice) {
				ColorChoice.show(getGraphics());
			} else if (e.getSource() == fillColor) {
				fillColor.show(getGraphics());
			} else if (e.getSource() == Etc) {
				Etc.show(getGraphics());
			} else if (e.getSource() == File) {
				File.show(getGraphics());
			}
		}
	}

	public void addDrawing() {
		if (cntD == 0) {
			add(drawing);
			cntD++;
		}
	}

	public void showField(TextField f) {
		if (cntF == 0) {
			add(f);
			cntF++;
		}
	}

	public Frame() {
		btnList = new List<KWidget>();

		MyKListener myListener = new MyKListener();
		FigureListener figureListener = new FigureListener();
		ColorListener colorListener = new ColorListener();
		FillColorListener fillcolorListener = new FillColorListener();
		EtcListener etcListener = new EtcListener();
		FileListener fileListener = new FileListener();

		menuBar = new MenuBar("MenuBar");
		this.add(menuBar);
		menuBar.addListener(myListener);

		Figure = new Menu("Figure");
		Figure.addListener(myListener);
		menuBar.add(Figure);

		rect = new KButton("rect");
		rect.addListener(figureListener);
		Figure.add(rect);

		oval = new KButton("oval");
		oval.addListener(figureListener);
		Figure.add(oval);

		line = new KButton("line");
		line.addListener(figureListener);
		Figure.add(line);

		text = new KButton("text");
		text.addListener(figureListener);
		Figure.add(text);

		ColorChoice = new Menu("Color");
		ColorChoice.addListener(myListener);
		menuBar.add(ColorChoice);

		red = new KButton("Red");
		red.addListener(colorListener);
		ColorChoice.add(red);

		blue = new KButton("Blue");
		blue.addListener(colorListener);
		ColorChoice.add(blue);

		black = new KButton("Black");
		black.addListener(colorListener);
		ColorChoice.add(black);

		fillColor = new Menu("Fill Color");
		fillColor.addListener(myListener);
		menuBar.add(fillColor);

		fillRed = new KButton("Red");
		fillRed.addListener(fillcolorListener);
		fillColor.add(fillRed);

		fillBlue = new KButton("Blue");
		fillBlue.addListener(fillcolorListener);
		fillColor.add(fillBlue);

		fillBlack = new KButton("Black");
		fillBlack.addListener(fillcolorListener);
		fillColor.add(fillBlack);

		Etc = new Menu("Etc");
		Etc.addListener(myListener);
		menuBar.add(Etc);

		group = new KButton("Group");
		group.addListener(etcListener);
		Etc.add(group);

		move = new KButton("Move");
		move.addListener(etcListener);
		Etc.add(move);

		delete = new KButton("Delete");
		delete.addListener(etcListener);
		Etc.add(delete);

		copy = new KButton("Copy");
		copy.addListener(etcListener);
		Etc.add(copy);

		File = new Menu("File");
		File.addListener(myListener);
		menuBar.add(File);

		save = new KButton("Save");
		save.addListener(fileListener);
		File.add(save);

		open = new KButton("Load");
		open.addListener(fileListener);
		File.add(open);

		addMouseListener(this);

		setSize(1000, 700);
		setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void add(KWidget kb) {
		btnList.add(kb);
	}

	@Override
	public void paint(Graphics g) {
		for (int i = 0; i < btnList.size(); i++) {
			btnList.get(i).draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		menuBar.findClick(e);
		if (menuBar.menuMode == FigureMenu) {
			Figure.findClick(e);
		} else if (menuBar.menuMode == ColorMenu) {
			ColorChoice.findClick(e);
		} else if (menuBar.menuMode == FillColorMenu) {
			fillColor.findClick(e);
		} else if (menuBar.menuMode == EtcMenu) {
			Etc.findClick(e);
		} else if (menuBar.menuMode == FileMenu) {
			File.findClick(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame myFrame = new Frame();
	}
}