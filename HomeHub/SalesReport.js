google.charts.load('current', {packages: ['corechart', 'bar']});
// google.charts.setOnLoadCallback(drawStacked);

//Bind click event to make an ajax call to post request of SalesReport. This will return
// a json object with top 3 review for each city;
$("#btnGetChartData").click(function () {
     $("#btnGetChartData").hide();
    $.ajax({
        url: "SalesReport",
        type: "POST",
        data: "{}",
        success: function (msg) {
            createDataTable(msg)            
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });    
});


//This method will parse json data and build datatable required by google api to plot the bar chart.
function createDataTable(jsonData) {
    var parsedData = $.parseJSON(jsonData);
    console.log("parsed json data at client",parsedData)
    var data = new Array();
    var serviceNameArr = new Array();
    // var zipCodeArr = new Array();


    //Create an array of product name and an array of zipcodes
    for(var i=0; i<parsedData.length; i++) {
        var serviceName = parsedData[i]["serviceName"];
        // var zipCode = parsedData[i]["storeZip"];
        if(!serviceNameArr.includes(serviceName)) {
            serviceNameArr.push(serviceName);
        }
        // if(!zipCodeArr.includes(zipCode)) {
        //     zipCodeArr.push(zipCode);
        // }
     }

     //Create header array for google api
     var headingArray = new Array(2);
     headingArray[0] = "Service Name";
     headingArray[1] = "Total Sales";

    //  for(var i=1; i<=serviceNameArr.length; i++) {
    //     headingArray[i] = serviceNameArr[j]; 
    //     j++;
    //  }

     console.log(serviceNameArr);

     data[0] = headingArray;
     var m =1;
    
     //Loop through jsondata and create an array of arrays to plot the chart;
    for(var i=0; i<serviceNameArr.length; i++) {
        var dataArr = new Array(headingArray.length);
        dataArr[0] = serviceNameArr[i];
        for(k=0; k<parsedData.length; k++) {
            if(parsedData[k]["serviceName"] === serviceNameArr[i]) {                    
                dataArr[1] = parseFloat(parsedData[k]["totalRevenue"]);                   
            }                 
        }
        
        
        //Set empty cell elements to zero;
        // for(var n=1; n<headingArray.length; n++) {
        //     if(!(dataArr[n] > 0)) {
        //         dataArr[n] = 0;
        //     }
        // }
        data[m] = (dataArr);
        m++;
    }

    console.log("data is printed here: ",data);
    
    // for(var j=0; j<serviceNameArr.length; j++) {
    //     for(k=0; k<parsedData.length; k++) {
    //         if(parsedData[k]["serviceName"] == serviceNameArr[j]) {                    
    //             dataArr[j+1] = parseInt(parsedData[k]["totalSales"]);                   
    //         }                 
    //     }
    // }
     drawChart(data, serviceNameArr);
}

//Plot the chart using 2d array and product names as subtitles;
function drawChart(data, serviceNameArr) {
    var serviceNames = "";
    for(var i=0; i<serviceNameArr.length; i++) {
        serviceNames += serviceNameArr[i] + ",";
    }


    //Invoke google's built in method to get data table object required by google.
     var chartData = google.visualization.arrayToDataTable(data);

     var options = {
        'width':700,
        'height':750,
          chart: {
            title: 'Trending Services Chart',
            subtitle: serviceNames,
          },
          bars: 'horizontal' // Required for Material Bar Charts.
        };

    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
    chart.draw(chartData, options);
}




