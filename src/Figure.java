import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.TextField;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

@SuppressWarnings("serial")
public class Figure implements Serializable {
	final int black = 0, red = 1, blue = 2;
	Point start, end;
	int myColor, background;

	public void setColor(Graphics g, int color) {
		if (color == red) {
			g.setColor(Color.RED);
		} else if (color == blue) {
			g.setColor(Color.BLUE);
		} else if (color == black) {
			g.setColor(Color.BLACK);
		}
	}

	public Figure(Point x, Point y, int color) {
		start = x;
		end = y;
		myColor = color;
		background = 10;
	}

	void setBackground(int color) {
		background = color;
	}

	public void draw(Graphics g) {
	}

	public Point Start() {
		return start;
	}

	public Point End() {
		return end;
	}

	public void fillColor(Graphics g, int color) {

	}

	public boolean isAt(int x, int y) {
		if (start.x < end.x && start.y < end.y) {
			return (start.x < x && start.y < y && x < end.x && y < end.y);
		} else if (start.x < end.x && start.y > end.y) {
			return (start.x < x && end.y < y && x < end.x && y < start.y);
		} else if (start.x > end.x && start.y > end.y) {
			return (end.x < x && end.y < y && x < start.x && y < start.y);
		} else if (start.x > end.x && start.y < end.y) {
			return (end.x < x && start.y < y && x < start.x && y < end.y);
		} else {
			return false;
		}
	}

	public void change(int x, int y) {
		start.x = start.x + x;
		start.y = start.y + y;
		end.x = end.x + x;
		end.y = end.y + y;
	}

}

class Rectangle extends Figure {
	public Rectangle(Point x, Point y, int color) {
		super(x, y, color);
	}

	public Rectangle(Point x, Point y, int color, int backColor) {
		super(x, y, color);
		background = backColor;
	}

	public void draw(Graphics g) {
		setColor(g, myColor);
		if (start.x < end.x && start.y < end.y) {
			g.drawRect(start.x, start.y, end.x - start.x, end.y - start.y);
		} else if (start.x < end.x && start.y > end.y) {
			g.drawRect(start.x, end.y, end.x - start.x, start.y - end.y);
		} else if (start.x > end.x && start.y > end.y) {
			g.drawRect(end.x, end.y, start.x - end.x, start.y - end.y);
		} else if (start.x > end.x && start.y < end.y) {
			g.drawRect(end.x, start.y, start.x - end.x, end.y - start.y);
		}
		if (background < 3) {
			fillColor(g, background);
		}
	}

	public void fillColor(Graphics g, int color) {
		setBackground(color);
		setColor(g, color);
		if (start.x < end.x && start.y < end.y) {
			g.fillRect(start.x, start.y, end.x - start.x, end.y - start.y);
		} else if (start.x < end.x && start.y > end.y) {
			g.fillRect(start.x, end.y, end.x - start.x, start.y - end.y);
		} else if (start.x > end.x && start.y > end.y) {
			g.fillRect(end.x, end.y, start.x - end.x, start.y - end.y);
		} else if (start.x > end.x && start.y < end.y) {
			g.fillRect(end.x, start.y, start.x - end.x, end.y - start.y);
		}
	}
}

class Oval extends Figure {
	public Oval(Point x, Point y, int color) {
		super(x, y, color);
	}

	public Oval(Point x, Point y, int color, int backColor) {
		super(x, y, color);
		background = backColor;
	}

	public void draw(Graphics g) {
		setColor(g, myColor);
		if (start.x < end.x && start.y < end.y) {
			g.drawOval(start.x, start.y, end.x - start.x, end.y - start.y);
		} else if (start.x < end.x && start.y > end.y) {
			g.drawOval(start.x, end.y, end.x - start.x, start.y - end.y);
		} else if (start.x > end.x && start.y > end.y) {
			g.drawOval(end.x, end.y, start.x - end.x, start.y - end.y);
		} else if (start.x > end.x && start.y < end.y) {
			g.drawOval(end.x, start.y, start.x - end.x, end.y - start.y);
		}
		if (background < 3) {
			fillColor(g, background);
		}
	}

	public void fillColor(Graphics g, int color) {
		setBackground(color);
		setColor(g, color);
		if (start.x < end.x && start.y < end.y) {
			g.fillOval(start.x, start.y, end.x - start.x, end.y - start.y);
		} else if (start.x < end.x && start.y > end.y) {
			g.fillOval(start.x, end.y, end.x - start.x, start.y - end.y);
		} else if (start.x > end.x && start.y > end.y) {
			g.fillOval(end.x, end.y, start.x - end.x, start.y - end.y);
		} else if (start.x > end.x && start.y < end.y) {
			g.fillOval(end.x, start.y, start.x - end.x, end.y - start.y);
		}
	}
}

class Line extends Figure {
	public Line(Point x, Point y, int color) {
		super(x, y, color);
	}

	public void draw(Graphics g) {
		setColor(g, myColor);
		g.drawLine(start.x, start.y, end.x, end.y);
	}
}

class Strings extends Figure {
	private String title;
	TextField field;
	boolean show = true;

	public Strings(Point x, Point y, int color) {
		super(x, y, color);
	}

	public void setTextField(TextField t) {
		field = t;
	}

	public void getString() {
		title = field.getText();
	}

	public void draw(Graphics g) {
		setColor(g, myColor);
		if (start.x < end.x && start.y < end.y) {
			g.drawString(title, start.x, start.y + 8);
		} else if (start.x < end.x && start.y > end.y) {
			g.drawString(title, start.x, end.y + 8);
		} else if (start.x > end.x && start.y > end.y) {
			g.drawString(title, end.x, end.y + 8);
		} else if (start.x > end.x && start.y < end.y) {
			g.drawString(title, end.x, start.y + 8);
		}
	}
}

class GroupFigure extends Figure {
	ArrayList<Figure> list = new ArrayList<Figure>();
	GroupFigure copyFigure;

	public GroupFigure(Point x, Point y, int color) {
		super(x, y, color);
		// TODO Auto-generated constructor stub
	}

	public GroupFigure() {
		super(new Point(0, 0), new Point(0, 0), 0);
		// super(null, null, 0);
	}

	public void add(Figure t) {
		list.add(t);
		Point tStart = t.Start();
		Point tEnd = t.End();
		if (start.x == 0 && start.y == 0 && end.x == 0 && end.y == 0) {
			start = new Point(tStart.x, tStart.y);
			end = new Point(tEnd.x, tEnd.y);
		} else {
			if (start.x > tStart.x) {
				start.x = tStart.x;
			}
			if (start.y > tStart.y) {
				start.y = tStart.y;
			}
			if (end.x < tEnd.x) {
				end.x = tEnd.x;
			}
			if (end.y < tEnd.y) {
				end.y = tEnd.y;
			}
		}

	}

	public void change(int x, int y) {
		start.x = start.x + x;
		start.y = start.y + y;
		end.x = end.x + x;
		end.y = end.y + y;
		for (int i = 0; i < list.size(); i++) {
			Figure f = list.get(i);
			f.change(x, y);
		}
	}

	@Override
	public void draw(Graphics g) {
		setColor(g, myColor);
		if (start.x < end.x && start.y < end.y) {
			g.drawRect(start.x, start.y, end.x - start.x, end.y - start.y);
		} else if (start.x < end.x && start.y > end.y) {
			g.drawRect(start.x, end.y, end.x - start.x, start.y - end.y);
		} else if (start.x > end.x && start.y > end.y) {
			g.drawRect(end.x, end.y, start.x - end.x, start.y - end.y);
		} else if (start.x > end.x && start.y < end.y) {
			g.drawRect(end.x, start.y, start.x - end.x, end.y - start.y);
		}
		for (int i = 0; i < list.size(); i++) {
			Figure f = list.get(i);
			f.draw(g);
		}
	}

	public void copy(GroupFigure figure) {
		for (int i = 0; i < figure.list.size(); i++) {
			Point copyStart = new Point();
			Point copyEnd = new Point();
			Figure f = figure.list.get(i);
			copyStart.x = (int) (f.Start().getX() + 5);
			copyStart.y = (int) (f.Start().getY() + 5);
			copyEnd.x = (int) (f.End().getX() + 5);
			copyEnd.y = (int) (f.End().getY() + 5);
			if (f.getClass() == Rectangle.class) {
				add(new Rectangle(copyStart, copyEnd, f.myColor, f.background));
			} else if (f.getClass() == Oval.class) {
				add(new Oval(copyStart, copyEnd, f.myColor, f.background));
			} else if (f.getClass() == Line.class) {
				add(new Line(copyStart, copyEnd, f.myColor));
			} else if (f.getClass() == Strings.class) {
				add(new Strings(copyStart, copyEnd, f.myColor));
			} else if (f.getClass() == GroupFigure.class) {
				copyFigure = new GroupFigure(copyStart, copyEnd, f.myColor);
				copyFigure.copy(((GroupFigure) f));
				list.add(copyFigure);
			}
		}
	}
}
