package Testing;

import static org.junit.Assert.*;
import org.junit.*;

import chat.server.ListClients;
import chat.server.Client;

public class ComponentTest {
	
	private ListClients listClients;
	private Client client1;
	private Client client2;
	private Client client3;
	private Client client4;
	
	@Before
	public void SetUp(){
		listClients = new ListClients();
		client1 = new Client("lulu", 12, System.currentTimeMillis());
		client2 = new Client("baba", 0, System.currentTimeMillis());
		client3 = new Client("lulu", 14, System.currentTimeMillis());
		client4 = new Client("caca", 0, System.currentTimeMillis());
	}
	
	@Test
	public void testIsNewClientAddClient() {
		listClients.addClient(client1);
		assertFalse(listClients.isNewClient(client1));
		assertTrue(listClients.isNewClient(client2));
		assertFalse(listClients.isNewClient(client3));
		
	}
	
	public void testUpdateClient() {
		assertEquals(3,listClients.updateClient("tata", 3));
		assertEquals(2,listClients.getListClients().size());
		assertFalse(listClients.isNewClient(client4));
		assertEquals(3,listClients.updateClient("tata", 6));
		assertEquals(2,listClients.getListClients().size());
		assertEquals(0,listClients.updateClient("è_fstvièt#]@^`|[{#€bkskcdky_)ç)27", 0));
		assertEquals(3,listClients.getListClients().size());
	}

}
