var main = () => {
    console.log("main...");
};


document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: ['dayGrid', 'timeGrid', 'timeGridDay', 'list', 'interaction','bootstrap'],
        defaultView: 'dayGridMonth',
        locale: 'de',
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth',
        },
        buttonText: {

            today: 'Heute',
            month: 'Monat',
            week: 'Woche',
            day: 'Tag',
            list: 'Liste'
        },
        weekNumbers: true,
        editable: true,
        eventLimit: true,
        navLinks: true,
        timeZone: 'Europe/Berlin',     
       
        selectable: true,
        selectHelper: true,

        events: [
            {
                title: 'Meeting',
                start: '2020-06-01T14:30:00',
                textColor: 'white',
                extendedProps: {
                    status: 'done',
                }
            },
            {
                title: 'Birthday Party',
                start: '2020-06-04T18:00:00',
                backgroundColor: 'green',
                borderColor: 'green'
            },
            {
                title: 'xyz',
                start: '2020-06-07T09:00:00',
                backgroundColor: 'yellow',
                borderColor: 'blue'
            },
           {
                title: 'Prüfung',
                start: '2020-06-22T09:00:00',
                backgroundColor: 'red',
                borderColor: 'blue',
                textColor: 'white',
            },
            {
                title: 'Feiertag',
                start: '2020-06-11T09:00:00',
                backgroundColor: 'blue',
                borderColor: 'white',
                textColor: 'white',
            }
        ],


        /*select: function (info, start, end) {
            var title = prompt("Event Name:");
            var eventData;

            if (title) {
                eventData = {
                    title: title,
                    date: info.dateStr,
                    start: info.startStr,
                    end: info.endStr,
                };

                calendar.addEvent(eventData);
            }
            calendar.unselect();
        },*/
        
        /*add: function(date){
             calendar.addEvent({
              title: 'dynamic event',
              start: date,
              end: date
            });
        },*/
        
       select: function (info, start, end)
       $.ajax({
       type: "POST",
       contentType: "application/json",
       url: "/deine/api/url",
       data: JSON.stringify(eventData),
       dataType: 'json',
       success: function (data) {
             calendar.addEvent(eventData);
        },
        error: function (e) {
             console.log(e);
         }
 });


        
//        destroy: function (info) {
//            var title = prompt("Event Name:");
//            var eventData;
//
//            if (title) {
//                eventData = {
//                    title: title,
//                    start: info.startStr,
//                    end: info.endStr
//                };
//
//                calendar.addEvent(eventData);
//            }
//            calendar.unselect();
//        },
//
//           deleventClick: function (info) {
//            info.jsEvent.preventDefault(); // don't let the browser navigate
//
//            if (info.event.url) {
//                window.open(info.event.url);
//            }
//        }
//    });



  eventClick: function(info) {
    //alert('Event: ' + info.startStr + info.event.title);
    alert('date: ' + info.dateStr);
    console.log(info.event.extendedProps);
  }

});

         calendar.render();

        $(".fc-left").append('<select class="select_month"><option value="">Select Month</option><option value="1">Jan</option><option value="2">Feb</option><option value="3">Mrch</option><option value="4">Aprl</option><option value="5">May</option><option value="6">June</option><option value="7">July</option><option value="8">Aug</option><option value="9">Sep</option><option value="10">Oct</option><option value="11">Nov</option><option value="12">Dec</option></select>');

        $(".select_month").on("change", function (event) {
        $('#calendar').fullCalendar('changeView', 'month', this.value);
        $('#calendar').fullCalendar('gotoDate', "2020-" + this.value + "-1");
        $('#calendar').fullCalendar('')
    });
});
       