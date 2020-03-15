package test;

import java.time.LocalDate;

import Service.BookService;
import Service.LoanService;
import Service.MemberService;
import Service.IMPL.BookServiceImpl;
import Service.IMPL.LoanServiceImpl;
import Service.IMPL.MemberServiceImpl;
import exception.ServiceException;
import model.Book;
import model.Loan;
import model.Member;
import model.Member.SubscriptionType;

public class ServiceTest {
	public static void main(String[] args) throws ServiceException {
		/**
		 * Emprunt
		 */
		Member m = new Member(50, "LeonardiH", "AlessandroH", "ParisFrance", "meemail@email.com", "0145",
				SubscriptionType.PREMIUM);
		Book l = new Book(11, "Leonardi", "Alessandro", "elegidoisbn");
		//INITIALISATION OBJET MEMBRE
		Loan emprunt = new Loan(7, m, l, LocalDate.of(2019, 12, 1), LocalDate.now());
				
		//INITIALISATION SERVICE
		LoanService empruntService = LoanServiceImpl.getInstance();
		
		//GETLIST
		empruntService.getList();
		System.out.println("\n\n\n\n");
		empruntService.getListCurrent();
		empruntService.getListCurrentByLivre(4);
		empruntService.getListCurrentByMembre(5);
		
		//Emprunt creation
		empruntService.create(4, 5, LocalDate.now());
		
		//Get by ID
		empruntService.getById(8);
		
		//Create
		empruntService.create(5, 6, LocalDate.now());
		
		//ReturnBook
	    empruntService.returnBook(1);
		
		//VERIFICATION METHODE COUNT
	    empruntService.count();
		
		//Disponibilit livre
		if(empruntService.isLivreDispo(14)) {
			System.out.println("OUI");
		}
		else {System.out.println("noOUI");}
		
		
		//Member membre = new Member();
		// Disponibilit emprunt
		//empruntService.isEmpruntPossible(membre);
		
		/**
		 * Livre
		 */
		
	    BookService livreservice = BookServiceImpl.getInstance();
		
		//GET
	    livreservice.getList();
	    livreservice.getListDispo();
	    livreservice.getById(13);
	    Book livre = new Book(12,"Araujo","Victor","elegidoisbn2");	
        //VERIFICATION DE METHODE CREATE
        Book livre_2= new Book("Ale","Titre","isbnelegido2");
	    livreservice.create(livre_2);
		
		//VERIFICATION DE METHODE 
	
	    livreservice.update(livre);
		
		//DELETE
	    livreservice.delete(16);
		
		//Count
		livreservice.count();
		
		/**
		 * Membre
		 */
		
	    MemberService membreService = MemberServiceImpl.getInstance();
		
//		GET
	    membreService.getList();
		//TODO Logica del service
	    membreService.getListMembreEmpruntPossible();
	    membreService.getById(8);
	    Member membre_2 = new Member("Leonardi", "Alessandro", "Ita", "correo@dominio.com", "5694641", SubscriptionType.VIP);
	    membreService.create(membre_2);

	
	    Member membre_3 = new Member(13,"LeonardiBelen","AlessandroA","ParisFrance","meemail@email.com","0145", SubscriptionType.PREMIUM);
	    membreService.update(membre_3); //
	
	    System.out.println("*********************");
	    membreService.getById(13);
		
	    membreService.delete(13);
		
	    membreService.count();
	}

}
