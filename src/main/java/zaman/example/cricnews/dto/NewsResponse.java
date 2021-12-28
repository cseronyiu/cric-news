package zaman.example.cricnews.dto;
import java.util.List;

public class NewsResponse extends ServerSidePaginationInformation {
    private List<News> newses;
    private long recordsTotal;
    private long recordsFiltered;
    private long total;

    public List<News> getNewses() {
        return newses;
    }

    public void setNewses(List<News> newses) {
        this.newses = newses;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

}
