package zaman.example.cricnews.service;

import org.springframework.http.HttpRequest;
import zaman.example.cricnews.dto.ServerSidePaginationInformation;

import javax.servlet.http.HttpServletRequest;

public class PageManagementService {

    public ServerSidePaginationInformation GetPageInfo(HttpServletRequest httpRequest){
        String start = httpRequest.getParameter ("start");
        String length = httpRequest.getParameter ("length");
        String searchVal = httpRequest.getParameter ("search[value]");
        String sortColumnVal = httpRequest.getParameter ("sortColumnVal");
        String sortColumnDirVal = httpRequest.getParameter ("order[0][dir]");
        int pageSizeVal = length != null ? Integer.parseInt(length): 0;
        int skipVal = start != null ? Integer.parseInt(start) : 0;
        int pageId = skipVal / pageSizeVal;

        return new ServerSidePaginationInformation(skipVal,pageSizeVal,pageId,sortColumnDirVal,sortColumnVal,searchVal);
    }
}
