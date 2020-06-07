var main = () => {
    console.log("main...");
};

    


            document.addEventListener('DOMContentLoaded', function () {
                var calendarEl = document.getElementById('calendar');

                var calendar = new FullCalendar.Calendar(calendarEl, {
                    plugins: ['dayGrid', 'timeGrid', 'timeGridDay','list', 'interaction'],
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
                        list: 'Liste',
                    },                      
                    weekNumbers: true,
                    editable: true,
                    eventLimit: true,
                    navLinks: true,

                    
                    
                    events: 'https://fullcalendar.io/demo-events.json',
                    selectable: true,
                    selectHelper: true,      
                    
                    eventClick: function(info) {
                        info.jsEvent.preventDefault();
                        
                        if(info.event.url){
                            window.open(info.event.url);
                                                }else {
  Swal.fire(info.event.title, 'Start:  '+info.event.start, 'question');
                                                }
                        
                    },
                    
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
                            title: 'Virtuelle Sitzung',
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
                        },  
                    ],      
                                    
                    eventRender: function (info) {
                        if (info.event.extendedProps.status === 'done') {

                            // Hintergrundfarbe auf rot
                            info.el.style.backgroundColor = 'red';

                            // dot Marker rot
                            var dotEl = info.el.getElementsByClassName('fc-event-dot')[0];
                            if (dotEl) {
                                dotEl.style.backgroundColor = 'white';
                            }
                        }
                    },
                    eventClick: function (info) {
                        alert('Event: ' + info.event.title);
                        alert('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
                        alert('View: ' + info.view.type);

                        // change the border color just for fun
                        info.el.style.borderColor = 'red';
                    },

                    deleventClick: function (info) {
                        info.jsEvent.preventDefault(); // don't let the browser navigate

                        if (info.event.url) {
                            window.open(info.event.url);
                        }
                    }                    
                });


                calendar.render();
                
                  $(".fc-left").append('<select class="select_month"><option value="">Select Month</option><option value="1">Jan</option><option value="2">Feb</option><option value="3">Mrch</option><option value="4">Aprl</option><option value="5">May</option><option value="6">June</option><option value="7">July</option><option value="8">Aug</option><option value="9">Sep</option><option value="10">Oct</option><option value="11">Nov</option><option value="12">Dec</option></select>');
  
                  $(".select_month").on("change", function(event) {
                  $('#calendar').fullCalendar('changeView', 'month', this.value);
                  $('#calendar').fullCalendar('gotoDate', "2020-"+this.value+"-1");
                });
          
 
    });
       