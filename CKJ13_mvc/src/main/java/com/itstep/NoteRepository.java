package com.itstep;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


//описывает функциональность NoteDao
//унаследовал базовые CRUD-методы от CrudRepository
@Repository
public interface NoteRepository extends CrudRepository<Note, Integer> {

	
	@Query("SELECT n FROM Note n WHERE n.title LIKE :word OR n.message LIKE :word")
	List<Note> search(@Param("word")String word);
	
	@Query("SELECT n FROM Note n WHERE n.startDate LIKE :date")
	List<Note> findByStartDate(@Param("date") LocalDate date);
	
	List<Note> findBystartDate(LocalDate startDate);
	
	List<Note> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
}
//List<Note> findByTitle(String word);

	//List<Note> findByTitleContainingOrMessageContaining(String word1, String word2);
	
	//List<Note>findByTitleContaining(String word);
	
	//select * from where title=word or message like '%word%'