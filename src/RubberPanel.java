import java.awt.Color;
import java.util.Scanner;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.peer.KeyboardFocusManagerPeer;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RubberPanel extends JPanel implements MouseListener,
		MouseMotionListener {
	final static int Rec = 1, Oval = 2, Line = 3, Text = 4, FillColor = 5,
			Group = 6, Move = 7, Delete = 8, Copy = 9;
	static JFrame frame;
	private List<Figure> list = new List<Figure>();
	Figure moveFigure;
	GroupFigure group, copyFigure;
	final static int black = 0, red = 1, blue = 2;
	private int whatColor, background;
	private int what;
	Frame myFrame;
	TextField field = new TextField();
	Point start = null, end = null;

	public RubberPanel(Frame f) {
		setBackground(Color.WHITE);
		setBounds(0, 180, 1000, 620);
		addMouseListener(this);
		addMouseMotionListener(this);

		myFrame = f;
		what = 0;
		whatColor = 0;
		field.setBounds(700, 100, 100, 100);
	}

	public void Save() {
		try {
			FileDialog f = new FileDialog(frame, "save", FileDialog.SAVE);
			f.setDirectory("C:Desktop");
			f.setVisible(true);
			String name = f.getDirectory() + f.getFile();
			FileOutputStream fout;
			fout = new FileOutputStream(name);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			ArrayList<Figure> arrayList = new ArrayList<Figure>();
			for (int i = 0; i < list.size(); i++) {
				arrayList.add(list.get(i));
			}
			out.writeObject(arrayList);
			out.close();
			fout.close();
		} catch (Exception e) {
			System.out.println("Error");
			// TODO Auto-generated catch block
		}
	}

	@SuppressWarnings("unchecked")
	public void Open() {
		try {
			FileDialog f = new FileDialog(frame);
			f.setDirectory("C:Desktop");
			f.setVisible(true);
			String name = f.getDirectory() + f.getFile();
			FileInputStream fin;
			fin = new FileInputStream(name);
			ObjectInputStream in = new ObjectInputStream(fin);
			ArrayList<Figure> saveList = (ArrayList<Figure>) in.readObject();
			in.close();
			for (int i = 0; i < saveList.size(); i++) {
				list.insert_front(saveList.get(i));
			}
			paintComponent(getGraphics());
		} catch (Exception e) {
			// TODO Auto-generated catch bloc
			System.out.println("Error");
		}
	}

	public void Mode(int n) {
		what = n;
		if (n == Group) {
			group = new GroupFigure();
			list.insert_front(group);
		}
	}

	public void ColorChoice(int n) {
		whatColor = n;
	}

	public void BackGroundColor(int n) {
		background = n;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.setPaintMode();
		MyIterable t = list.getIterator();
		while (t.hasNext()) {
			Figure f = (Figure) t.getValue();
			f.draw(getGraphics());
			t.next();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Graphics g = getGraphics();
		g.setXORMode(Color.white);
		if (start != null && (what == Rec || what == Text)) {
			if (start.x < end.x && start.y < end.y) {
				g.drawRect(start.x, start.y, end.x - start.x, end.y - start.y);
			} else if (start.x < end.x && start.y > end.y) {
				g.drawRect(start.x, end.y, end.x - start.x, start.y - end.y);
			} else if (start.x > end.x && start.y > end.y) {
				g.drawRect(end.x, end.y, start.x - end.x, start.y - end.y);
			} else if (start.x > end.x && start.y < end.y) {
				g.drawRect(end.x, start.y, start.x - end.x, end.y - start.y);
			}
		} else if (start != null && what == Oval) {
			if (start.x < end.x && start.y < end.y) {
				g.drawOval(start.x, start.y, end.x - start.x, end.y - start.y);
			} else if (start.x < end.x && start.y > end.y) {
				g.drawOval(start.x, end.y, end.x - start.x, start.y - end.y);
			} else if (start.x > end.x && start.y > end.y) {
				g.drawOval(end.x, end.y, start.x - end.x, start.y - end.y);
			} else if (start.x > end.x && start.y < end.y) {
				g.drawOval(end.x, start.y, start.x - end.x, end.y - start.y);
			}
		} else if (start != null && what == Line) {
			g.drawLine(start.x, start.y, end.x, end.y);
		} else if (start != null && what == Move && moveFigure != null) {
			moveFigure.draw(g);
		}
		int pre_x = end.x, pre_y = end.y;
		end.x = e.getX();
		end.y = e.getY();
		if (start != null && (what == Rec || what == Text)) {
			if (start.x < end.x && start.y < end.y) {
				g.drawRect(start.x, start.y, end.x - start.x, end.y - start.y);
			} else if (start.x < end.x && start.y > end.y) {
				g.drawRect(start.x, end.y, end.x - start.x, start.y - end.y);
			} else if (start.x > end.x && start.y > end.y) {
				g.drawRect(end.x, end.y, start.x - end.x, start.y - end.y);
			} else if (start.x > end.x && start.y < end.y) {
				g.drawRect(end.x, start.y, start.x - end.x, end.y - start.y);
			}
		} else if (start != null && what == Oval) {
			if (start.x < end.x && start.y < end.y) {
				g.drawOval(start.x, start.y, end.x - start.x, end.y - start.y);
			} else if (start.x < end.x && start.y > end.y) {
				g.drawOval(start.x, end.y, end.x - start.x, start.y - end.y);
			} else if (start.x > end.x && start.y > end.y) {
				g.drawOval(end.x, end.y, start.x - end.x, start.y - end.y);
			} else if (start.x > end.x && start.y < end.y) {
				g.drawOval(end.x, start.y, start.x - end.x, end.y - start.y);
			}
		} else if (start != null && what == Line) {
			g.drawLine(start.x, start.y, end.x, end.y);
		} else if (start != null && what == Move && moveFigure != null) {
			int x = end.x - pre_x, y = end.y - pre_y;
			moveFigure.change(x, y);
			moveFigure.draw(g);
		}
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		start = new Point(e.getX(), e.getY());
		if (start != null && what == FillColor) {
			MyIterable t = list.getIterator();
			while (t.hasNext()) {
				Figure f = (Figure) t.getValue();
				if (f.isAt(e.getX(), e.getY())) {
					if (f.getClass() == Oval.class
							|| f.getClass() == Rectangle.class) {
						f.fillColor(getGraphics(), background);
						break;
					}
				}
				t.next();
			}
		} else if (start != null && what == Group) {
			MyIterable t = list.getIterator();
			while (t.hasNext()) {
				Figure f = (Figure) t.getValue();
				if (f.isAt(e.getX(), e.getY())) {
					if (!f.equals(group)) {
						group.add(f);
						list.delete(f);
						break;
					}
				}
				t.next();
			}
		} else if (start != null && what == Delete) {
			MyIterable t = list.getIterator();
			while (t.hasNext()) {
				Figure f = (Figure) t.getValue();
				if (f.isAt(e.getX(), e.getY())) {
					list.delete(f);
					break;
				}
				t.next();
			}
		} else if (start != null && what == Copy) {
			MyIterable t = list.getIterator();
			while (t.hasNext()) {
				Figure f = (Figure) t.getValue();
				if (f.isAt(e.getX(), e.getY())) {
					Point copyStart = new Point();
					Point copyEnd = new Point();
					copyStart.x = (int) (f.Start().getX() + 5);
					copyStart.y = (int) (f.Start().getY() + 5);
					copyEnd.x = (int) (f.End().getX() + 5);
					copyEnd.y = (int) (f.End().getY() + 5);
					if (f.getClass() == Rectangle.class) {
						list.insert_front(new Rectangle(copyStart, copyEnd,
								f.myColor, f.background));
					} else if (f.getClass() == Oval.class) {
						list.insert_front(new Oval(copyStart, copyEnd,
								f.myColor, f.background));
					} else if (f.getClass() == Line.class) {
						list.insert_front(new Line(copyStart, copyEnd,
								f.myColor));
					} else if (f.getClass() == GroupFigure.class) {
						copyFigure = new GroupFigure();
						copyFigure.copy(((GroupFigure) f));
						list.insert_front(copyFigure);
					}
					break;
				}
				t.next();
			}
		}
		paintComponent(getGraphics());
		start = null;
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		start = new Point(e.getX(), e.getY());
		end = new Point(e.getX(), e.getY());
		if (start != null && what == Move) {
			MyIterable t = list.getIterator();
			while (t.hasNext()) {
				Figure f = (Figure) t.getValue();
				if (f.isAt(e.getX(), e.getY())) {
					moveFigure = f;
					break;
				}
				t.next();
			}
		}
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Graphics g = getGraphics();
		if (what == Rec) {
			list.insert_front(new Rectangle(start, end, whatColor));
		} else if (what == Oval) {
			list.insert_front(new Oval(start, end, whatColor));
		} else if (what == Line) {
			list.insert_front(new Line(start, end, whatColor));
		} else if (what == Move) {
			moveFigure = null;
		} else if (what == Text) {
			Strings s = new Strings(start, end, whatColor);
			s.setTextField(field);
			s.getString();
			myFrame.showField(field);
			list.insert_front(s);
		}
		paintComponent(g);
		start = end = null;
		// TODO Auto-generated method stub
	}

}
