$(document).ready(function () {
    var xValues = ["OJT Batch-4", "JWD Batch-28", "PFC Batch-52", "ASP.Net Batch-7", "OJT Batch-2", "PFC Batch-67", "OJT Batch-4", "JWD Batch-28", "PFC Batch-52", "ASP.Net Batch-7", "OJT Batch-2", "PFC Batch-67"];
    var yValues = [55, 49, 44, 24, 15, 9, 9, 15, 24, 44, 49, 55];
    var barColors = [
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255, 206, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)',
        'rgba(153, 102, 255, 0.2)',
        'rgba(255, 159, 64, 0.2)',
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255, 206, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)',
        'rgba(153, 102, 255, 0.2)',
        'rgba(255, 159, 64, 0.2)'
    ];
    var borderColors = [
        'rgba(255,99,132,1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
        'rgba(153, 102, 255, 1)',
        'rgba(255, 159, 64, 1)',
        'rgba(255,99,132,1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
        'rgba(153, 102, 255, 1)',
        'rgba(255, 159, 64, 1)'
    ];

// new Chart("myChart", {
//     type: "bar",
//     data: {
//         labels: xValues,
//         datasets: [{
//             backgroundColor: barColors,
//             borderColor: borderColors,
//             borderWidth: 1,
//             data: yValues
//         }]
//     },
//     options: {
//         title: {
//             display: true,
//         },
//         legend: { display: false },
//         scales: {
//             yAxes: [{
//                 ticks: {
//                     beginAtZero: true
//                 }
//             }],
//         }
//     }
// });

    new Chart("myChart", {
        type: "bar",
        data: {
            labels: xValues,
            datasets: [
                {
                    backgroundColor: barColors,
                    borderColor: borderColors,
                    borderWidth: 1,
                    data: yValues,
                },
            ],
        },
        options: {
            title: {
                display: true,
            },
            legend: {display: false},
            scales: {
                yAxes: [
                    {
                        ticks: {
                            beginAtZero: true,
                        },
                    },
                ],
            },
        },
    });

    new Chart("myChart2", {
        type: "pie",
        data: {
            datasets: [
                {
                    data: [10, 20, 30],
                    backgroundColor: barColors,
                    borderColor: borderColors,
                },
            ],

            // These labels appear in the legend and in the tooltips when hovering different arcs
            labels: ["Red", "Yellow", "Blue"],
        }
    })
});