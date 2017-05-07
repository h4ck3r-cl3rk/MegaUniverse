package parser.entities;

public class Page {
	private String baseURL;
	private String name;

	public Page(String baseURL, String name) {
		super();
		this.baseURL = baseURL;
		this.name = name;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public String getName() {
		return name;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Page [baseURL=" + baseURL + ", name=" + name + "]";
	}
	
	
}
