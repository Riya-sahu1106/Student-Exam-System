package Online.model;

public class Question {
    public int id;
    public String question;
    public String A;
    public String B;
    public String C;
    public String D;
    public String Right_Ans;
    public int subject_id;
	
    
	public Question() {
		super();
	}
	public Question(int id, String question, String a, String b, String c, String d, String right_Ans,
			int subject_id) {
		super();
		this.id = id;
		this.question = question;
		A = a;
		B = b;
		C = c;
		D = d;
		Right_Ans = right_Ans;
		this.subject_id = subject_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getA() {
		return A;
	}
	public void setA(String a) {
		A = a;
	}
	public String getB() {
		return B;
	}
	public void setB(String b) {
		B = b;
	}
	public String getC() {
		return C;
	}
	public void setC(String c) {
		C = c;
	}
	public String getD() {
		return D;
	}
	public void setD(String d) {
		D = d;
	}
	public String getRight_Ans() {
		return Right_Ans;
	}
	public void setRight_Ans(String right_Ans) {
		Right_Ans = right_Ans;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}   
	
}
