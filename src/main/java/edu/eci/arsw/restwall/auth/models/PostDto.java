package edu.eci.arsw.restwall.auth.models;

public class PostDto {
	private String content;
	private Integer id;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PostDto [content=" + content + ", id=" + id + "]";
	}
	
	
	
}

