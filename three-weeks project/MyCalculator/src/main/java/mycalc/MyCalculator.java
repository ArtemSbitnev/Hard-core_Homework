package mycalc;

import java.util.*;

public class MyCalculator {

	private MyCalculator() {
	}

	private static boolean isDelim(char c) {
		return c == ' ';
	}

	private static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
	}

	private static boolean isNumber(char c) {
		return ((c >= '0' && c <= '9') || c == '.');
	}
	
	private static boolean isFunc(String s, int i) {
		if (s.charAt(i) == 's' && s.charAt(i+1) == 'q' && s.charAt(i+2) == 'r' && s.charAt(i+3) == 't') f = Func.SQRT;
		else if (s.charAt(i) == 'm' && s.charAt(i+1) == 'i' && s.charAt(i+2) == 'n') f = Func.MIN;
		else if (s.charAt(i) == 'm' && s.charAt(i+1) == 'a' && s.charAt(i+2) == 'x') f = Func.MAX;
		else if (s.charAt(i) == 's' && s.charAt(i+1) == 'u' && s.charAt(i+2) == 'm') f = Func.SUM;
		else return false;
		return true;
	}

	private static int priority(char op) {
		switch (op) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
		case '%':
			return 2;
		case '^':
			return 3;
		default:
			return -1;
		}
	}

	private static boolean checkMistakes(String s) {
		/*if (!isNumber(s.charAt(0)) && s.charAt(0) != '-' && s.charAt(0) != '(') {
			error = "Error in first symbol";
			return false;
		}*/
		int openBracket = 0;
		int closeBracket = 0;
		int point = 0;
		boolean symbol = true;
		boolean delim = false;
		boolean func = false;
		boolean endBracket = false;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '-') {
				if (symbol && !(openBracket > closeBracket) && i > 0) {
					error = "two operarors";
					cursor = i;
					return false;
				}
				point = 0;
				endBracket = false;
				symbol = true;
				continue;
			}
			else if (s.charAt(i) == '.') {
				point++;
				if (symbol) {
					error = "no symbol before point";
					cursor = i;
					return false;
				}
				if (point > 1) {
					error = "can not been two points in one digit";
					cursor = i + 1;
					return false;
				}
			}
			if (s.charAt(i) != ')' && !isOperator(s.charAt(i)) && !isDelim(s.charAt(i))) {
				if (endBracket) {
					cursor = i;
					
					error = "Error after bracket 2";
					return false;
				}
			}
			endBracket = false;
			if (s.charAt(i) == '(') {
				point = 0;
				if (!symbol) {
					cursor = i;
					error = "Error before bracket 1";
					return false;
				}
				openBracket++;
			}
			else if (s.charAt(i) == ')') {
				point = 0;
				if (symbol) {
					cursor = i;
					error = "Error before bracket 2";
					return false;
				}
				func = false;
				closeBracket++;
				endBracket = true;
			}
			if (func) {
				if (s.charAt(i) == ',') {
                    point = 0;
                    if(s.charAt(i-1) == ',') {
                        cursor = i;
                        error = "no number";
                        return false;
                    }
				}
				if (isNumber(s.charAt(i)) || isDelim(s.charAt(i)) || s.charAt(i) == ',' || s.charAt(i) == '(') {
					symbol = false;
					continue;
				}
				else {
					error = "error in function";
					cursor = i;
					return false;
				}
			}
			if (isOperator(s.charAt(i))) {
				if (symbol) {
					error = "two operators";
					cursor = i;
					return false;
				}
				point = 0;
				symbol = true;
			}
			else if (isNumber(s.charAt(i))) {
				if (!symbol && delim) {
					error = "no operators between two numbers";
					cursor = i;
					return false;
				}
				symbol = false;
			} 
			else if (s.charAt(i) == 's') {
				if (s.charAt(i+1) == 'q' && s.charAt(i+2) == 'r' && s.charAt(i+3) == 't') {
					if(!symbol) {
						error = "error";
						cursor = i;
						return false;
					}
					i+=3;
					//func = true;
					symbol = true;
				}
				else if (s.charAt(i+1) == 'u' && s.charAt(i+2) == 'm') {
					if(!symbol) {
						error = "error";
						cursor = i;
						return false;
					}
					i+=2;
					func = true;
					symbol = true;
				}
				else {
					error = "prohibited symbol";
					cursor = i;
					return false;
				}
				
			}
			else if (s.charAt(i) == 'm') {
				if(s.charAt(i+1) == 'i' && s.charAt(i+2) == 'n') {
					if(!symbol) {
						error = "error";
						cursor = i;
						return false;
					}
					i+=2;
					func = true;
					symbol = true;
				}
				else if(s.charAt(i+1) == 'a' && s.charAt(i+2) == 'x') {
					if(!symbol) {
						error = "error";
						cursor = i;
						return false;
					}
					i+=2;
					func = true;
					symbol = true;
				}
				else {
					error = "prohibited symbol";
					cursor = i;
					return false;
				}
			}
			else if (!isDelim(s.charAt(i)) && !(s.charAt(i) == ')') && !(s.charAt(i) == '(')) {
				error = "prohibited symbol";
				cursor = i;
				return false;
			}
			if (isDelim(s.charAt(i))) delim = true;
			else delim = false;				
			if (closeBracket > openBracket) {
				cursor = 0;
				error = "no opening bracket";
				return false;
			}
		}
		if (openBracket != closeBracket) {
			cursor = s.length();
			error = "no closing bracket";
			return false;
		}
		return true;
	}
	
	private static void processFunc(LinkedList<Double> st, Func c, int counter) {
		switch (c) {
		case SQRT:
			double r = st.removeLast();
			st.add(Math.sqrt(r));
			break;
		case MAX:
			double[] mas1 = new double[counter];
			for (int i = 0; i < mas1.length; i++) 
				mas1[i] = st.removeLast();
			double max = Double.MIN_VALUE;
			for (double i : mas1)
				if (max < i) max = i;
			st.add(max);
			break;
		case MIN:
			double[] mas2 = new double[counter];
			for (int i = 0; i < mas2.length; i++) 
				mas2[i] = st.removeLast();
			double min = Double.MAX_VALUE;
			for (double i : mas2) 
				if (min > i) min = i; 
			st.add(min);
			break;
		case SUM:
			double[] mas3 = new double[counter];
			for (int i = 0; i < mas3.length; i++) 
				mas3[i] = st.removeLast();
			double res = 0;
			for (double i : mas3)
				res += i;
			st.add(res);
			break;
		default:
			error = "error func";
		}
	}
	
	private static void processOperator(LinkedList<Double> st, LinkedList<Character> o, int brackets) {
		double r = st.removeLast();
		char op = o.removeLast();
		if (op == '-' && (st.size() <= (o.size() - brackets))) {
			st.add(0 - r);
		} else {
			double l = st.removeLast();

			switch (op) {
			case '+':
				st.add(l + r);
				break;
			case '-':
				st.add(l - r);
				break;
			case '*':
				st.add(l * r);
				break;
			case '/':
				st.add(l / r);
				break;
			case '%':
				st.add(l % r);
				break;
			case '^':
				st.add(Math.pow(l, r));
				break;
			}
		}
	}

	public static Double eval(String s) {
		error = "";
		cursor = 0;
		if (checkMistakes(s)) {
			LinkedList<Double> st = new LinkedList<Double>();
			LinkedList<Character> op = new LinkedList<Character>();
			int openBracket = 0;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (isDelim(c))
					continue;
				if (c == '(') {
					openBracket++;
					op.add('(');
				}
				else if (c == ')') {
					while (op.getLast() != '(')
						processOperator(st, op, openBracket);
					openBracket--;
					op.removeLast();
				} else if (isOperator(c)) {
					if (i == 0 && c == '-') st.add(0.0);
					while (!op.isEmpty()
							&& priority(op.getLast()) >= priority(c))
						processOperator(st, op, openBracket);
					op.add(c);
				} else if (isFunc(s, i)) {
					i+=4;
					while(s.charAt(i) == '(' || s.charAt(i) == ' ') i++;
					int counter = 1;
					while(s.charAt(i) != ')') {
						String operand = "";
						while (i < s.length() && isNumber(s.charAt(i)))
							operand += s.charAt(i++);
						st.add(Double.parseDouble(operand));
						if (s.charAt(i) == ',') {
							i++;
							counter++;
						}
						else if (isDelim(s.charAt(i))) {
							i++;
						}
					}
					processFunc(st, f, counter);
				} else {
					String operand = "";
					while (i < s.length() && isNumber(s.charAt(i)))
						operand += s.charAt(i++);
					--i;
					st.add(Double.parseDouble(operand));
				}
			}
			while (!op.isEmpty())
				processOperator(st, op, openBracket);
			return st.get(0);
		}
		return 0.0;
	}

	public static String getError() {
		return error;
	}
	
	public static int getCursor() {
		return cursor;
	}

	private static Func f = null;
	private static String error = "";
	private static int cursor;
}

enum Func {MIN, MAX, SQRT, SUM}
