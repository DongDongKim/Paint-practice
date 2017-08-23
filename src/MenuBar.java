import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class MenuBar extends KWidget {
	List<Menu> myList;
	int menuMode;

	public MenuBar(String name) {
		super(name);
		myList = new List<Menu>();
		setBounds(30, 40, 800, 50);
		menuMode = 0;
	}

	public void add(Menu m) {
		myList.add(m);
		m.setBounds(100 * myList.size(), 60, 70, 30);
	}

	public void changeMode(int n) {
		menuMode = n;
	}

	public void findClick(MouseEvent e) {
		for (int i = 0; i < myList.size(); i++) {
			KWidget k = myList.get(i);
			if (k.isAt(e.getX(), e.getY())) {
				changeMode(i + 1);
				k.provideEvent(new KEvent(e.getX(), e.getY(), k));
				break;
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
		g.drawString(title, x + 5, y + 25);
		MyIterable t = myList.getIterator();
		while (t.hasNext()) {
			KWidget f = (KWidget) t.getValue();
			f.draw(g);
			t.next();
		}
	}

	public void hide(Graphics g) {
		MyIterable t = myList.getIterator();
		while (t.hasNext()) {
			Menu f = (Menu) t.getValue();
			f.hide(g);
			t.next();
		}
	}
}

class Menu extends KWidget {
	List<KButton> myList;

	public Menu(String name) {
		super(name);
		myList = new List<KButton>();
	}

	public void add(KButton t) {
		myList.add(t);
		t.setBounds(this.x, this.y + (30 * myList.size()) + 1, width, height);
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
		g.drawString(title, x + 5, y + 25);
	}

	public void show(Graphics g) {
		MyIterable t = myList.getIterator();
		while (t.hasNext()) {
			KWidget f = (KWidget) t.getValue();
			f.draw(g);
			t.next();
		}
	}

	public void hide(Graphics g) {
		g.setColor(Color.WHITE);
		MyIterable t = myList.getIterator();
		while (t.hasNext()) {
			KWidget f = (KWidget) t.getValue();
			f.draw(g);
			t.next();
		}
	}

	public void findClick(MouseEvent e) {
		for (int i = 0; i < myList.size(); i++) {
			KWidget k = myList.get(i);
			if (k.isAt(e.getX(), e.getY())) {
				k.provideEvent(new KEvent(e.getX(), e.getY(), k));
				break;
			}
		}
	}
}
