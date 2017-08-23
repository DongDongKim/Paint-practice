import java.awt.Color;
import java.awt.Graphics;

public class KWidget {
	int x, y, width, height;
	String title;
	List<KListener> myListener;

	public KWidget(String name) {
		this.title = name;
		x = y = 0;
		width = 100;
		height = 30;
		myListener = new List<KListener>();
	}

	public void addListener(KListener l) {
		myListener.add(l);
	}

	public void setBounds(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	public boolean isAt(int x, int y) {
		return this.x <= x && x <= this.x + this.width && this.y <= y
				&& y <= this.y + this.height;
	}

	public void provideEvent(KEvent e) {
		for (int i = 0; i < myListener.size(); i++) {
			myListener.get(i).actionPerformed(e);
		}
	}

	public void draw(Graphics g) {
	}
}

class KButton extends KWidget {
	public KButton(String title) {
		super(title);
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
		g.drawString(title, x + 5, y + 25);
	}
}

class KCheckBox extends KWidget {
	private String ck1, ck2;
	private boolean c1 = true, c2 = false;

	public KCheckBox(String ck1, String ck2) {
		super("check");
		this.ck1 = ck1;
		this.ck2 = ck2;
	}

	@Override
	public boolean isAt(int x, int y) {
		if (this.x <= x && x <= (this.x + this.width / 2) && this.y <= y
				&& y <= (this.y + this.height)) {
			c1 = true;
			c2 = false;
		} else if ((this.x + this.width / 2) <= x && x <= this.x + this.width
				&& this.y <= y && y <= this.y + this.height) {
			c1 = false;
			c2 = true;
		}
		return this.x <= x && x <= this.x + this.width && this.y <= y
				&& y <= this.y + this.height;
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(x - 40, y, 35, height);
		g.drawString(title, x - 40, y + 10);

		g.drawRect(x, y, width / 2, height);
		g.drawString(ck1, x + 1, y + 10);

		g.drawRect((x + width / 2), y, width / 2, height);
		g.drawString(ck2, (x + width / 2 + 1), y + 10);
	}

	public void drawCheck(Graphics g) {

		if (isWhat()) {
			g.drawString("V", x + 40, y + 10);
			g.setColor(Color.white);
			g.drawString("V", (x + width / 2) + 40, y + 10);

		} else {
			g.drawString("V", (x + width / 2) + 40, y + 10);
			g.setColor(Color.white);
			g.drawString("V", x + 40, y + 10);
		}

	}

	public boolean isWhat() {
		return c1;
	}
}
