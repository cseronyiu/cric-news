(function ($) {


    //
    // $('.view-user').click(function (e) {
    //     if ($('#partialView').is(":visible")) {
    //         $('#partialView').html("");
    //     }
    //     $("#user-data-table_filter input").val(null);
    //     if ($.fn.DataTable.isDataTable("#news-data-table")) $("#news-data-table").DataTable().destroy();
    //     // $("#news-data-table-div").hide();
    //     // $("#export").hide();
    //     if ($("#viewUser").valid()) {
    //         loadViewUserTable($("#news-data-table_filter input").val());
    //     }
    //     else e.preventDefault();
    // })

    loadTable=function (){
        $("#news-data-table_filter input").val(null);
        if ($.fn.DataTable.isDataTable("#news-data-table")) $("#news-data-table").DataTable().destroy();
        $("#news-data-table-div").hide();
        loadViewUserTable();
    }


    loadViewUserTable = function (searchval) {
        if (typeof searchval == "undefined" || searchval == "null") searchval = null;

        if ($.fn.DataTable.isDataTable("#news-data-table")) $("#news-data-table").DataTable().destroy();
        // var userType = document.getElementById("userTypeId").value;
        //var isVisible;
        //if (userType == "5" || userType == "21") isVisible = true;
        //else isVisible = false;
        // var token = $('input[name="__RequestVerificationToken"]').val();
        var headers = {};
        // headers['__RequestVerificationToken'] = token;
        var requestdata = { "searchKey": searchval }

        $("#news-data-table").DataTable({
            "lengthMenu": [5, 10, 15, 20,25,30],
            "destroy": true,
            //"processing": true,
            "serverSide": true,
            "orderMulti": true,
            //"scrollX": 350,
            "scrollY": 430,
            "async": false,
            "ordering": true,
            "ajax": {
                url: "/getNews",
                type: "POST",
                datatype: "json",
                headers: headers,
                // data: requestdata,
                data: function (data) {
                    requestdata.sortColumnVal = data.columns[data.order[0]['column']]['name'];
                    delete data.columns;
                    return $.extend(data, requestdata);
                },
                dataSrc: function (result) {
                    if (result.status == "200" && result.newses != null && result.newses.length > 0) {
                        if (!($("#export").is(":visible"))) $("#export").show();
                    }
                    else if (result.status == "400") {
                        var errorstring = "<div class='alert alert-danger alert-dismissable fade in' role='alert'><button type='button' class='close' data-dissmiss='alert' aria-label='Close'><span aria-hidden='true'>×</span></button>" + result.message + "</div>";
                        $('#partialView').html(errorstring);
                    }
                    $("#news-data-table-div").show();
                    return result.newses;
                },
                error: function (request, error) {
                    var errorstring = "<div class='alert alert-danger alert-dismissable fade in' role='alert'><button type='button' class='close' data-dissmiss='alert' aria-label='Close'><span aria-hidden='true'>×</span></button>Something went wrong. Please try again.</div>";
                    $("#partialView").html(errorstring);
                    $("#news-data-table-div").hide();

                }
            },
            "columns": [
                { "data": "title", "name": "title", "autoWidth": true },
                { "data": "description", "name": "mobileNumber", "autoWidth": true },
                { "data": "link", "name": "link",
                    fnCreatedCell:function (nTd,sData,oData,iRow,iCol){
                        if(oData.link){
                            $(nTd).html("<a href="+oData.link+">Detaile</a> ")
                        }
                    }

                },

            ],
            "columnDefs": [
                { "targets": [0], "orderable": false, "searchable": false },
                { "targets": [1], "orderable": true, "searchable": false },
                { "targets": [2], "orderable": true, "searchable": false },
            ]
            ,
            "fnDrawCallback": function () {
                $("#news-data-table_filter input").val(searchval);
                $("#news-data-table_filter input").unbind().keypress(function (e) {
                    if (e.which == 13) {
                        e.preventDefault();
                        var searchval = $(this).val();
                        loadViewUserTable(searchval);
                        $("#news-data-table_filter input").focus();
                    }
                })
            }
        });
    }

})(jQuery);