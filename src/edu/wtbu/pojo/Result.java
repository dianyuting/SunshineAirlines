package edu.wtbu.pojo;

public class Result {
	String flag;
	Object data;
	Page page;
	public Result() {
		super();
	}
	public Result(String flag) {
		super();
		this.flag = flag;
	}
	public Result(String flag, Object data) {
		super();
		this.flag = flag;
		this.data = data;
	}
	public Result(String flag, Object data, Page page) {
		super();
		this.flag = flag;
		this.data = data;
		this.page = page;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}
