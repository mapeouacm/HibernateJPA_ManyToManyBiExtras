package mx.edu.uacm.hibernatejpamanytomanybiextras.entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


@SpringBootTest
public class PersonTest {
	
	private static final Logger log = LogManager.getLogger(PersonTest.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Test
	@Transactional
	@Rollback(false)
	public void debeGuardarPerson() {
		
		log.debug(">Entrando al metodo debeGuardarPerson<");
		
		//Creamos algunas personas
		Person person1 = new Person("Pepe Pérez");
		Person person2 = new Person("Juan Martínez");

		
		//Creamos algunas direcciones
		Address address1 = new Address( "Calle", "11", "41010" );
		Address address2 = new Address( "Av. de la Constitución", "1", "41000" );
		
		em.persist( person1 );
		em.persist( person2 );

		em.persist( address1 );
		em.persist( address2 );

		em.flush();

		//Añadimos a la primera persona dos direcciones
		person1.addAddress( address1, "Casa");
		person1.addAddress( address2, "Trabajo" );

		//Añadimos a la segunda persona una dirección
		person2.addAddress( address1, "Trabajo" );

		//Almacenamos
		em.flush();
		//Desasociamos la primera dirección de la primera persona
		person1.removeAddress( address1 );
	}

}
