package project.home.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.home.webapp.data.UserWorkout;
import project.home.webapp.repository.UserWorkoutRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@Controller
@RestController
public class RestWebController {

	@Autowired
	private UserWorkoutRepository eventRepository;

	@GetMapping(value = "/events")
	public List<UserWorkout> getEventsInRange(@RequestParam(value = "start", required = false) String start,
											  @RequestParam(value = "end", required = false) String end) {
		if ((start == null || start.isEmpty()) || (end == null || end.isEmpty())) {
			return eventRepository.findAll();
		}

		Instant startDate;
		Instant endDate;
		SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			startDate = inputDateFormat.parse(start).toInstant();
		} catch (ParseException e) {
			throw new BadDateFormatException("bad start date: " + start);
		}

		try {
			endDate = inputDateFormat.parse(end).toInstant();
		} catch (ParseException e) {
			throw new BadDateFormatException("bad end date: " + end);
		}

		return eventRepository.findByStartAfterAndEndBefore(startDate, endDate);
	}

	@PostMapping(value = "/events")
	public UserWorkout addEvent(@RequestBody UserWorkout event) {
		return eventRepository.save(event);
	}

	@PutMapping(value = "/events")
	public UserWorkout updateEvent(@RequestBody UserWorkout event) {
		return eventRepository.save(event);
	}

	@DeleteMapping(value = "/events/{id}")
	public void removeEvent(@PathVariable("id") String id) {
		eventRepository.deleteById(Integer.valueOf(id));
	}
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadDateFormatException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BadDateFormatException(String dateString) {
		super(dateString);
	}
}
    

