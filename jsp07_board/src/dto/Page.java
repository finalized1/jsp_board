package dto;

public class Page {
	private int curpage; //현재페이지
	private int totpage; //전체페이지
	private int perpage=10; //한페이지 당 게시물 수
	private int startnum; //시작번호
	private int endnum; //끝번호
	
	private int perblock=10; //페이지 블럭의 수
	private int startpage; //블럭의 시작 페이지
	private int endpage; //블럭의 끝 페이지
	
	private String findkey; //검색 키
	private String findvalue; //검색 값
	
	public int getCurpage() {
		return curpage;
	}
	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}
	public int getTotpage() {
		return totpage;
	}
	public void setTotpage(int totpage) {
		this.totpage = totpage;
	}
	public int getPerpage() {
		return perpage;
	}
	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}
	public int getStartnum() {
		return startnum;
	}
	public void setStartnum(int startnum) {
		this.startnum = startnum;
	}
	public int getEndnum() {
		return endnum;
	}
	public void setEndnum(int endnum) {
		this.endnum = endnum;
	}
	public int getPerblock() {
		return perblock;
	}
	public void setPerblock(int perblock) {
		this.perblock = perblock;
	}
	public int getStartpage() {
		return startpage;
	}
	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}
	public int getEndpage() {
		return endpage;
	}
	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}
	public String getFindkey() {
		return findkey;
	}
	public void setFindkey(String findkey) {
		this.findkey = findkey;
	}
	public String getFindvalue() {
		return findvalue;
	}
	public void setFindvalue(String findvalue) {
		this.findvalue = findvalue;
	}
	@Override
	public String toString() {
		return "Page [curpage=" + curpage + ", totpage=" + totpage + ", perpage=" + perpage + ", startnum=" + startnum
				+ ", endnum=" + endnum + ", perblock=" + perblock + ", startpage=" + startpage + ", endpage=" + endpage
				+ ", findkey=" + findkey + ", findvalue=" + findvalue + "]";
	}
	
	

	
}
