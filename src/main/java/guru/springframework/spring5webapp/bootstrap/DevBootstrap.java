package guru.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;

	
	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		initData();
	}

	private void initData() {
		//Eric
		Author ericEvans = new Author("Eric", "Evans");
		Publisher harper = new Publisher("Harper Collins", "15 St Margarets Lane, New York, NY 10033");
		Book domainDrivenDesign = new Book("Domain Driven Design", "1234", harper);
		
		ericEvans.getBooks().add(domainDrivenDesign);
		domainDrivenDesign.getAuthors().add(ericEvans);
		domainDrivenDesign.setPublisher(harper);
		
		authorRepository.save(ericEvans);
		publisherRepository.save(harper);
		bookRepository.save(domainDrivenDesign);
		
		//Rod
		Author rodJohnson = new Author("Rod", "Johnson");
		Publisher worx = new Publisher("Worx", "93 Bayport Ave, South Richmond Hill, NY 11419");
		Book j22eeDev = new Book("J2EE Development without EJB", "2345", worx);
		//rodJohnson.getBooks().add(j22eeDev);
		j22eeDev.getAuthors().add(rodJohnson);
		j22eeDev.setPublisher(worx);
		
		authorRepository.save(rodJohnson);
		publisherRepository.save(worx);
		bookRepository.save(j22eeDev);
	}

}
