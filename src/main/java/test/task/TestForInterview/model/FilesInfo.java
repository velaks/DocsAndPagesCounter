package test.task.TestForInterview.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class FilesInfo {

    @JsonProperty("Documents")
    private long amountOfDocuments;
    @JsonProperty("Pages")
    private long amountOfPages;

    public FilesInfo() {
    }

    public long getAmountOfDocuments() {
        return amountOfDocuments;
    }

    public void setAmountOfDocuments(long amountOfDocuments) {
        this.amountOfDocuments = amountOfDocuments;
    }

    public long getAmountOfPages() {
        return amountOfPages;
    }

    public void setAmountOfPages(long amountOfPages) {
        this.amountOfPages = amountOfPages;
    }
}
