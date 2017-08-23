import java.io.Serializable;

interface MyIterable<T> {
	public boolean hasNext();

	public void next();

	public T getValue();
}

@SuppressWarnings("serial")
class List<T> {
	private node<T> head = new node<T>(null);
	private node<T> tail = new node<T>(null);
	private int cnt = 0;

	void add(T n) {
		node<T> t = new node<T>(n);
		if (head.right == null) {
			head.right = t;
			tail.right = t;
		} else {
			t.right = head.right;
			head.right.left = t;
			head.right = t;
		}
		cnt++;
	}

	void insert_front(T n) {
		node<T> t = new node<T>(n);
		if (head.right == null) {
			head.right = t;
			tail.right = t;
		} else {
			t.right = head.right;
			head.right.left = t;
			head.right = t;
		}
		cnt++;
	}

	void insert_back(T n) {
		node<T> t = new node<T>(n);
		if (tail.right == null) {
			head.right = t;
			tail.right = t;
		} else {
			tail.right.right = t;
			t.left = tail.right;
			tail.right = t;
		}
		cnt++;
	}

	void delete_front() {
		if (head.right == tail.right) {
			head.right = tail.right = null;
		} else {
			head.right = head.right.right;
			head.right.left = null;
		}
		cnt--;
	}

	void delete_back() {
		if (head.right == tail.right) {
			head.right = tail.right = null;
		} else {
			tail.right = tail.right.left;
			tail.right.right = null;
		}
		cnt--;
	}

	boolean find(T n) {
		node<T> t = head.right;
		boolean find = false;
		while (t != null && find != true) {
			if (t.value.equals(n)) {
				find = true;
				break;
			}
			t = t.right;
		}
		return find;
	}

	int size() {
		return cnt;
	}

	T get(int num) {
		node<T> t = head.right;
		for (int i = 0; i < num; i++) {
			t = t.right;
		}
		return t.value;
	}

	void delete(T n) {
		node<T> t = head.right;
		boolean find = false;
		while (t != null && find != true) {
			if (t.value.equals(n)) {
				find = true;
				break;
			}
			t = t.right;
		}
		if (find) {
			if (head.right == t) {
				delete_front();
			} else if (tail.right == t) {
				delete_back();
			} else {
				t.right.left = t.left;
				t.left.right = t.right;
				t = null;
			}
			cnt--;
		}
	}

	void print() {
		node<T> t = head.right;
		while (t != null) {
			System.out.println(t.value);
			t = t.right;
		}
	}

	Iterator getIterator() {
		return new Iterator();
	}

	reverseIterator getReverseIterator() {
		return new reverseIterator();
	}

	class Iterator implements MyIterable {
		node<T> t = head.right;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (t == null) {
				return false;
			} else {
				return true;
			}
		}

		public T getValue() {
			return t.value;
		}

		@Override
		public void next() {
			// TODO Auto-generated method stub
			t = t.right;
		}
	}

	class reverseIterator implements MyIterable {
		node<T> t = tail.right;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (t == null) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public void next() {
			// TODO Auto-generated method stub
			t = t.left;

		}

		@Override
		public T getValue() {
			// TODO Auto-generated method stub
			return t.value;
		}
	}

}

class node<T> {
	T value;
	node<T> right;
	node<T> left;

	node(T n) {
		value = n;
		right = null;
		left = null;
	}
}