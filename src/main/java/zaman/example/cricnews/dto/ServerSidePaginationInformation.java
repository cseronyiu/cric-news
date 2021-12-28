package zaman.example.cricnews.dto;

public class ServerSidePaginationInformation {
    private int skip ;
    private int pageSize ;
    private int pageId ;
    private String sortDirection ;
    private String sortColumn ;
    private String search ;

    public ServerSidePaginationInformation() {
    }

    public ServerSidePaginationInformation(int skip, int pageSize, int pageId, String sortDirection, String sortColumn, String search) {
        this.skip = skip;
        this.pageSize = pageSize;
        this.pageId = pageId;
        this.sortDirection = sortDirection;
        this.sortColumn = sortColumn;
        this.search = search;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
