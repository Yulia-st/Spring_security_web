package com.itstep;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.text.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/notes")
public class NoteController {
	private NoteRepository noteRepository;
//	private List<Note> notes = new ArrayList<>();
//private int idId;
	@Autowired
	public NoteController(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}
	private LocalDateConverter localDateConverter;
	
	
	public Date getDateFromString(String dateString) throws java.text.ParseException{
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date parsed = null;
	    try {
	        parsed = format.parse(dateString);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    Date date = new Date(parsed.getTime());
	    return date;
	}
	
	
	@GetMapping("/create")
	public String create() {
		return "create_note";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute(name = "note") Note note) {
		note.setStartDate(LocalDate.now());
		noteRepository.save(note);
		return "redirect:/notes";
		
	}

	@GetMapping
	public String all(Model model) {
		model.addAttribute("notes", noteRepository.findAll());
		return "notes";
	}

	@GetMapping("/{id}")
	public String info(@PathVariable(name = "id") int id, Model model) {
		Note note = noteRepository.findById(id).get();
//		Note note = notes.stream()
//		.filter(n -> n.getId()==id)
//		.findFirst().get();
		model.addAttribute("note", note);
	    return "info";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") int id) {
		noteRepository.deleteById(id);

//		Note note = notes.stream()
//		.filter(n -> n.getId()==id)
//		.findFirst().get();
//		notes.remove(note);

		return "redirect:/notes";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable(name = "id") int id, Model model) {
		model.addAttribute("note", noteRepository.findById(id).get());
//		Note note = notes.stream()
//		.filter(n -> n.getId()==id)
//		.findFirst().get();
//		model.addAttribute("note", note);
//		idId=id;
		return "edit";
	}
	
//	@PostMapping("/change")
//	public String change(@ModelAttribute(name = "note") Note note) {
//		note.setId(idId);
//		notes.set(idId-1, note);
		
//		return "redirect:/notes";
//	}
	
	
	
	@PostMapping("/change")
	public String change(@ModelAttribute(name = "note") Note note) {
		
		Note note1 =noteRepository.findById(note.getId()).orElseThrow();
				
		note1.setTitle(note.getTitle());
		note1.setMessage(note.getMessage());
		note1.setStartDate(LocalDate.now());
		noteRepository.save(note1);
		return "redirect:/notes";
	}

	@GetMapping("/search")
	public String search(@RequestParam(name="date1")LocalDate date1, Model model){
			//@RequestParam(name="date2")LocalDate date2, Model model ) {
	
		//List<Note> notes=noteRepository.search("%" + word + "%");
		//List<Note> notes=noteRepository.findByStartDateBetween(date1, date2);
		List<Note> notes=noteRepository.findByStartDate(date1);
		model.addAttribute("notes", notes);
		
		return "notes";
	}

}
/*
 public class NoteController {
	private NoteRepository noteRepository;

	@Autowired
	public NoteController(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	@GetMapping("/create")
	public String create() {
		return "create_note";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute(name = "note") Note note) {
		noteRepository.save(note);
		return "redirect:/notes";
	}

	@GetMapping
	public String all(Model model) {
		model.addAttribute("notes", noteRepository.findAll());
		return "notes";
	}

	@GetMapping("/{id}")
	public String info(@PathVariable(name = "id") int id, Model model) {
		// ищем объект по ид
		Note note = noteRepository.findById(id).get();
		model.addAttribute("note", note);
		return "info";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") int id) {
		noteRepository.deleteById(id);
		return "redirect:/notes";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable(name = "id") int id, Model model) {
		model.addAttribute("note", noteRepository.findById(id).get());
		return "edit";
	}

	
	@PostMapping("/change")
	public String change(@ModelAttribute(name = "note") Note note) {
		
		Note note1 = new Note();
		note1=noteRepository.findById(note.getId()).orElseThrow();
				
		note1.setTitle(note.getTitle());
		note1.setMessage(note.getMessage());
		noteRepository.save(note1);
		return "redirect:/notes";
	}

	@GetMapping("/search")
	public String search(@RequestParam(name="word")String word, Model model ) {
	
		List<Note> notes=noteRepository.search("%" + word + "%");
		model.addAttribute("notes", notes);
		
		return "notes";
	}

}


 */
//List<Note> notes=noteRepository.findByTitle(word);
		//List<Note> notes=noteRepository.findByTitleContaining(word);
//List<Note> notes=noteRepository.findByTitleContaining(word);
		//List<Note> notes=noteRepository.findByTitleContainingOrMessageContaining(word, word);

		
//select * from note where title like'%word%'
		//найти заметки поо плю тайтл. содержащие слово
		//findByTitle Containing(String word)