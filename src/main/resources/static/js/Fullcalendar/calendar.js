var allEvents = [];
var calendar;
var currentWeekDate = new Date();

document.addEventListener('DOMContentLoaded', async function () {
    // hole alle events aus der Datenbank
    allEvents = await getAllEvents();

    const calendarEl = document.getElementById('calendar');
    calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: ['dayGrid', 'timeGrid', 'timeGridDay', 'list', 'interaction'],
        defaultView: 'dayGridMonth',
        locales: ['deLocale'],
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
        firstDay: 1,
        weekNumbers: true,
        droppable: true,
        editable: true,
        eventLimit: true,
        navLinks: true,
        timeZone: 'Europe/Berlin',
        selectable: true,
        selectHelper: true,
        events: allEvents, // events in den Kalender geben
    });

    calendar.render();

    // die box mit den Workouts als external draggable definieren
    let workoutsContainer = document.getElementById('workouts');
    new FullCalendarInteraction.Draggable(workoutsContainer, {
        itemSelector: '.workout-element',
        eventData: function (eventEl) {
            return {
                startTime: '12:00',
                title: eventEl.innerText,
                duration: '00:30'
            };
        }
    })

    // die Monatsauswahl muss leider jedes mal neu erzeugt werden, wenn sie etwas ändert
    calendar.on('datesRender', function () {
        addMonthSelector();
    });

    // Workout wurde per Drag and Drop in den Kalender gezogen
    calendar.on('eventReceive', async function (info) {
        const event = {
            title: info.event.title,
            start: info.event.start,
            workoutType: titleToWorkoutType(info.event.title),
            end: info.event.end || info.event.start,
            color: '#3788d8'
        };
        await createEvent(event);
        await reloadEvents(calendar);
        await drawStatisticForDate();
    });

    // zeige Popup an, wenn auf ein Datum geklickt wird
    calendar.on('dateClick', function (event) {
        const now = new Date();
        const selectedDate = new Date(event.date);
        selectedDate.setHours(now.getHours(), now.getMinutes());
        $('.modal').modal('show');
        $('.modal').find('#delete-event').hide();
        $('.modal').find('#title').val('');
        $('.modal').find('#starts-at').val(selectedDate.toISOString().substr(0, 23));
        $('.modal').find('#ends-at').val(selectedDate.toISOString().substr(0, 23));
        $('.modal').find('#note').val('');
    });

    // zeige Popup an, wenn auf ein bereits existierendes Event geklickt wird und setzte die Daten
    calendar.on('eventClick', function (info) {
        const end = info.event.end || info.event.start;
        $('.modal').modal('show');
        $('.modal').find('#delete-event').show();
        $('.modal').find('#id').val(info.event.id);
        $('.modal').find('#title').val(info.event.title);
        $('.modal').find('#workouttype').val(allEvents.filter(event => event.id.toString() === info.event.id)[0].workoutType);
        $('.modal').find('#starts-at').val(info.event.start.toISOString().substr(0, 23));
        $('.modal').find('#ends-at').val(end.toISOString().substr(0, 23));
        $('.modal').find('#note').val(allEvents.filter(event => event.id.toString() === info.event.id)[0].note);
        $('.modal').find('#color').val(allEvents.filter(event => event.id.toString() === info.event.id)[0].color);
    });

    // aktualisiere die Eventdaten, sobald es verschoben wird
    calendar.on('eventDrop', async function (info) {
        const event = {
            id: info.event.id,
            title: info.event.title,
            workoutType: allEvents.filter(event => event.id.toString() === info.event.id)[0].workoutType,
            start: info.event.start,
            end: info.event.end || info.event.start,
            note: allEvents.filter(event => event.id.toString() === info.event.id)[0].note,
            color: info.event.backgroundColor
        };
        await updateEvent(event);
        await reloadEvents(calendar);
        await drawStatisticForDate();
    });

    // Erstelle oder aktualisiere ein Event
    $('#save-event').on('click', async function () {
        const id = $('#id').val();
        const title = $('#title').val();
        const workoutType = $('#workouttype').val();
        const startsAt = $('#starts-at').val();
        const endsAt = $('#ends-at').val();
        const note = $('#note').val();
        const color = $('#color').val();

        if (title && startsAt && endsAt) {
            const eventData = {
                id: id,
                title: title,
                workoutType: workoutType,
                start: new Date(startsAt + 'Z').toISOString(),
                end: new Date(endsAt + 'Z').toISOString(),
                note: note,
                color: color
            };

            if (id) {
                await updateEvent(eventData);
            } else {
                await createEvent(eventData);
            }
            await reloadEvents(calendar);
            await drawStatisticForDate();
        }

        $('.modal').modal('hide');
    });

    // Lösche ein Event, wenn auf löschen geklickt wird
    $('#delete-event').on('click', async function () {
        const id = $('#id').val();
        await deleteEvent(id);
        await reloadEvents(calendar);
        await drawStatisticForDate();
    });

    addMonthSelector();

    $('.previous-week').on('click', async function () {
        currentWeekDate.setDate(currentWeekDate.getDate() - 7);

        const text = `${formatDateToDDMMYYYY(getStartOfWeek(currentWeekDate))} - ${formatDateToDDMMYYYY(getEndOfWeek(currentWeekDate))}`
        $('.displayed-week').text(text)

        await drawStatisticForDate();
    });

    $('.next-week').on('click', async function () {
        console.log('Next week');
        currentWeekDate.setDate(currentWeekDate.getDate() + 7);

        const text = `${formatDateToDDMMYYYY(getStartOfWeek(currentWeekDate))} - ${formatDateToDDMMYYYY(getEndOfWeek(currentWeekDate))}`
        $('.displayed-week').text(text)

        await drawStatisticForDate();
    });

    const text = `${formatDateToDDMMYYYY(getStartOfWeek(currentWeekDate))} - ${formatDateToDDMMYYYY(getEndOfWeek(currentWeekDate))}`
    $('.displayed-week').text(text)

    await drawStatisticForDate();
});

// Holt alle Events per HTTP call
async function getAllEvents() {
    const response = await fetch('/events');
    const jsonResponse = await response.json();
    //console.log(jsonResponse); // nur für debugging Zwecke damit man etwas in der Console sieht
    return jsonResponse;
}

// Holt alle Events für die aktuelle Woche
async function getAllEventsForThisWeek(date) {
    const start = getStartOfWeek(date).toISOString().substr(0, 10);
    const end = getEndOfWeek(date).toISOString().substr(0, 10);
    const response = await fetch(`/events?start=${start}&end=${end}`);
    const jsonResponse = await response.json();
    //console.log(jsonResponse); // nur für debugging Zwecke damit man etwas in der Console sieht
    return jsonResponse;
}

// Erstellt ein neues Event
async function createEvent(event) {
    const response = await fetch('/events', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(event)
    });
    const jsonResponse = await response.json();
    //console.log(jsonResponse);// nur für debugging Zwecke damit man etwas in der Console sieht
}

// aktualisiert ein exisiterendes Event
async function updateEvent(event) {
    const response = await fetch('/events', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(event)
    });
    const jsonResponse = await response.json();
    console.log(jsonResponse);
}

// löscht ein Event
async function deleteEvent(id) {
    await fetch(`/events/${id}`, {
        method: 'DELETE'
    });
}

// lädt alle Events neu und zeigt sie in dem Kalender an
async function reloadEvents(calender) {
    const events = await getAllEvents();

    calender.getEvents().forEach(event => event.remove());
    events.forEach(event => calender.addEvent(event));
    calender.refetchEvents();
    allEvents = events;
}

// simples mapping vom Workouttypen fürs Backend
function titleToWorkoutType(title) {
    switch (title) {
        case 'Brustraining':
            return 'CHESTWORKOUT';
        case 'Bizeps':
            return 'BICEPS';
        case 'Trizeps':
            return 'TRICEPS';
        case 'Bauch':
            return 'BELLY';
        case 'Beine':
            return 'LEGS';
        case 'Rücken':
            return 'BACK';
        case 'Cardio':
            return 'CARDIO';
        default:
            return 'UNKWON';
    }
}

// Helfer Methode um den Anfang einer Woche zu bekommen
function getStartOfWeek(sourceDate) {
    const date = new Date(sourceDate);
    date.setDate((date.getDate() + 1) - date.getDay())
    date.setHours(0, 0, 0, 0);
    return date;
}

// Helfer Methode um das Ende einer Woche zu bekommen
function getEndOfWeek(sourceDate) {
    const date = new Date(sourceDate);
    date.setDate(date.getDate() - (date.getDay() - 1) + 6);
    date.setHours(23, 59, 59);
    return date;
}

// Ermittelt die Dauer eines Workouts in Minuten
function extractTimePerWorkout(values) {
    return event => {
        const start = new Date(event.start);
        const end = new Date(event.end);
        const differenceMinutes = Math.ceil(Math.abs(end - start) / 1000 / 60);
        values[start.getDay()] = values[start.getDay()] + differenceMinutes;
    };
}

// zeichnet die Statistik
async function drawStatisticForDate() {
    const eventsThisWeek = await getAllEventsForThisWeek(currentWeekDate);

    const traces = [];
    ['CHESTWORKOUT', 'BICEPS', 'TRICEPS', 'BELLY', 'LEGS', 'BACK', 'CARDIO'].forEach(workoutType => {
        const values = new Array(7);
        values.fill(0);

        const workoutEvents = eventsThisWeek.filter(event => event.workoutType === workoutType);
        workoutEvents.forEach(extractTimePerWorkout(values));

        const sum = values.reduce((total, value) => total + value);

        const chestWorkoutTrace = {
            x: ['Sonntag', 'Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag'],
            y: values,
            type: 'bar',
            name: `${workoutType} (${(sum / 60).toFixed(2)} h)`
        };

        traces.push(chestWorkoutTrace)
    });

    const layout = {
        title: 'Workouts diese Woche in Minuten',
        showlegend: true
    };

    Plotly.newPlot('weeklyStats', traces, layout, {displayModeBar: false});
}

// fügt die Monatsauswahl hinzu wenn sie noch nicht da ist
function addMonthSelector() {
    if (!$('#select_month').length) {
        $(".fc-left").append('<select class="select_month" id="select_month"><option value="">Select Month</option><option value="0">Januar</option><option value="1">Februar</option><option value="2">März</option><option value="3">April</option><option value="4">Mai</option><option value="5">Juni</option><option value="6">Juli</option><option value="7">August</option><option value="8">September</option><option value="9">Oktober</option><option value="10">November</option><option value="11">Dezember</option></select>');
        $("#select_month").val(new Date().getMonth());
        $("#select_month").on("change", function () {
            const month = this.value.toString().length === 1 ? '0' + (parseInt(this.value) + 1) : (this.value + 1);
            const date = "2020-" + month + "-01T12:00:00Z"
            calendar.gotoDate(date);
            $("#select_month").val(this.value);
        });
    }
}

function formatDateToDDMMYYYY(date) {
    const ye = new Intl.DateTimeFormat('de', { year: 'numeric' }).format(date);
    const mo = new Intl.DateTimeFormat('de', { month: '2-digit' }).format(date);
    const da = new Intl.DateTimeFormat('de', { day: '2-digit' }).format(date);
    return `${da}-${mo}-${ye}`;
}
