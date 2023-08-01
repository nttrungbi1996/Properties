
import java.time.LocalDate;
import java.util.List;

public abstract class NoticeMail {

	private List<String> receiveAddressArray;
	
	private LocalDate expirationDate;
	

	private String titleTemplate;
	private String messageTemplate;
	
	public List<String> getReceiveAddressArray() {
		return this.receiveAddressArray;
	}
	public LocalDate getExpirationDate() {
		return this.expirationDate;
	}
	
	public void setReceiveAddressArray(List<String> array) {
		this.receiveAddressArray = array;
	}

	public void setExpirationDate(LocalDate date) {
		this.expirationDate = date;
	}
	
	public void setMessageTemplate(String message) {
		this.messageTemplate = message;
	}
	
	public String getMessageTemplate() {
		return this.messageTemplate;
	}
	
	public void setTitleTemplate(String title) {
		this.titleTemplate = title;
	}
	
	public String getTitleTemplate() {
		return this.titleTemplate;
	}
	
	public abstract String getMessage();
	public abstract String getTitle();

}
