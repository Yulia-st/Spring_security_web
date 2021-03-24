//package com.itstep;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequestMapping("/notes")
//public class HomeTaskController {
//
//	private List<Note> notes = new ArrayList<>();
//	private int idId;
//
//	@GetMapping("/create")
//	public String create() {
//		return "create_note";
//	}
//
//	@PostMapping("/add")
//	public String add(@ModelAttribute(name = "note") Note note) {
//
//		int id = notes.stream().map(n -> n.getId()).max((i1, i2) -> i1 - i2).orElse(0);
//		note.setId(++id);
//		notes.add(note);
//		return "redirect:/notes";
//	}
//
//	@GetMapping
//	public String all(Model model) {
//
//		model.addAttribute("notes", notes);
//
//		return "notes";
//	}
//
//	@GetMapping("/{id}")
//	public String info(@PathVariable(name = "id") int id, Model model) {
//		// ищем объект по ид
//
//		Note note = notes.stream().filter(n -> n.getId() == id).findFirst().get();
//		model.addAttribute("note", note);
//		return "info";
//	}
//
//	@GetMapping("/delete/{id}")
//	public String delete(@PathVariable(name = "id") int id) {
//
////			notes.remove(id-1);
//		Note note = notes.stream().filter(n -> n.getId() == id).findFirst().get();
//		notes.remove(note);
////			model.addAttribute("note", note);
//
//		return "redirect:/notes";
//	}
//
//	@GetMapping("/edit/{id}")
//	public String edit(@PathVariable(name = "id") int id, Model model) {
//		 
//		Note note = notes.stream().filter(n -> n.getId() == id).findFirst().get();
//		model.addAttribute("note", note);
//		idId = id;
//		return "edit";
//	}
//
//	@PostMapping("/change")
//	public String change(@ModelAttribute(name = "note") Note note) {
//		note.setId(idId);
//		notes.set(idId - 1, note);
//
//		return "redirect:/notes";
//	}
//
////		@PostMapping("/change")
////		public String change(@ModelAttribute(name = "note") Note note) {
////			
////			Note note1 = new Note();
////			//note1=noteRepository.findById(note.getId()).orElseThrow();
////					
////			note1.setTitle(note.getTitle());
////			note1.setMessage(note.getMessage());
////			//noteRepository.save(note1);
////			return "redirect:/notes";
////		}
//
//	@GetMapping("/search")
//	public String search(@RequestParam(name = "word") String word, Model model) {
//
//		//List<Note> notes=noteRepository.findByTitle(word);
//		//List<Note> notes=noteRepository.findByTitleContaining(word);
//		//List<Note> notes=noteRepository.findByTitleContaining(word);
//		//List<Note> notes=noteRepository.findByTitleContainingOrMessageContaining(word, word);
//
//		
//		//select * from note where title like'%word%'
//		//найти заметки по полю тайтл. содержащие слово
//		//findByTitle Containing(String word)
//		
//		// List<Note> notes=noteRepository.search("%" + word + "%");
//		model.addAttribute("notes", notes);
//
//		return "notes";
//		
//	}
//
//}
////	/*
////	 public class NoteController {
////		private NoteRepository noteRepository;
////
////		@Autowired
////		public NoteController(NoteRepository noteRepository) {
////			this.noteRepository = noteRepository;
////		}
////
////		@GetMapping("/create")
////		public String create() {
////			return "create_note";
////		}
////
////		@PostMapping("/add")
////		public String add(@ModelAttribute(name = "note") Note note) {
////			noteRepository.save(note);
////			return "redirect:/notes";
////		}
////
////		@GetMapping
////		public String all(Model model) {
////			model.addAttribute("notes", noteRepository.findAll());
////			return "notes";
////		}
////
////		@GetMapping("/{id}")
////		public String info(@PathVariable(name = "id") int id, Model model) {
////			// ищем объект по ид
////			Note note = noteRepository.findById(id).get();
////			model.addAttribute("note", note);
////			return "info";
////		}
////
////		@GetMapping("/delete/{id}")
////		public String delete(@PathVariable(name = "id") int id) {
////			noteRepository.deleteById(id);
////			return "redirect:/notes";
////		}
////
////		@GetMapping("/edit/{id}")
////		public String edit(@PathVariable(name = "id") int id, Model model) {
////			model.addAttribute("note", noteRepository.findById(id).get());
////			return "edit";
////		}
////
////		
////		@PostMapping("/change")
////		public String change(@ModelAttribute(name = "note") Note note) {
////			
////			Note note1 = new Note();
////			note1=noteRepository.findById(note.getId()).orElseThrow();
////					
////			note1.setTitle(note.getTitle());
////			note1.setMessage(note.getMessage());
////			noteRepository.save(note1);
////			return "redirect:/notes";
////		}
////
////		@GetMapping("/search")
////		public String search(@RequestParam(name="word")String word, Model model ) {
////		
////			List<Note> notes=noteRepository.search("%" + word + "%");
////			model.addAttribute("notes", notes);
////			
////			return "notes";
////		}
////
////	}
//
