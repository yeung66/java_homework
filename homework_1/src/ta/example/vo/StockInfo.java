package ta.example.vo;

public class StockInfo {
	private int ID;
	private String Title;
	private String Author;
	private String Date;
	private String LastUpdate;
	private String Content;
	private String AnswerAuthor;
	private String Answer;
	
	public StockInfo(String []Info){
		ID=Integer.parseInt(Info[0]);
		Title=Info[1];
		Author=Info[2];
		Date=Info[3];
		LastUpdate=Info[4];
		Content=Info[5];
		AnswerAuthor=Info[6];
		Answer=Info[7];
	}

	public String[] getData(){
		String []data=new String[]{String.valueOf(ID),Title,Author,Date,LastUpdate,Content,AnswerAuthor,Answer};
		return data;
	}

	public int getAnswerLength(){
		return Answer.length();
	}
}
