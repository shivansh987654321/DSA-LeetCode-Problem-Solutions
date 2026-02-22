class Solution {
    public int largestRectangleArea(int[] a) {
        List<Integer> right = nsr(a);
		List<Integer> left = nsl(a);

		int maxArea = 0;
		for (int i = 0; i < a.length; i++) {
			maxArea = Math.max(maxArea, (right.get(i) - left.get(i) - 1) * a[i]);
		}
		return maxArea;
    }
    
    private List<Integer> nsl(int a[]) {
		List<Integer> res = new ArrayList<>();
		Stack<Pair> s = new Stack<>();
		for (int i = 0; i < a.length; i++) {
			if (s.size() == 0) {
				res.add(-1);
			} else if (s.size() > 0 && s.peek().getNge() < a[i]) {
				res.add(s.peek().getIndex());
			} else if (s.size() > 0 && s.peek().getNge() >= a[i]) {
				while (s.size() > 0 && s.peek().getNge() >= a[i]) {
					s.pop();
				}
				if (s.size() == 0) {
					res.add(-1);
				} else {
					res.add(s.peek().getIndex());
				}
			}
			s.add(new Pair(a[i], i));
		}
		return res;
	}

	// nsr: it has right so start with right and it has smaller so user <
	private List<Integer> nsr(int a[]) {
		List<Integer> res = new ArrayList<>();
		Stack<Pair> s = new Stack<>();
		for (int i = a.length - 1; i >= 0; i--) {
			if (s.size() == 0) {
				res.add(a.length );
			} else if (s.size() > 0 && s.peek().getNge() < a[i]) {
				res.add(s.peek().getIndex());
			} else if (s.size() > 0 && s.peek().getNge() >= a[i]) {
				while (s.size() > 0 && s.peek().getNge() >= a[i]) {
					s.pop();
				}
				if (s.size() == 0) {
					res.add(a.length );
				} else {
					res.add(s.peek().getIndex());
				}

			}
			s.add(new Pair(a[i], i));
		}
		Collections.reverse(res);
		return res;
	}
}
class Pair {
	int nge;
	int index;

	Pair(int nge, int index) {
		this.nge = nge;
		this.index = index;
	}

	public int getNge() {
		return nge;
	}

	public void setNge(int nge) {
		this.nge = nge;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}