package Testing;

import static org.junit.Assert.*;
import org.junit.*;

import chat.server.ListClients;
import chat.server.ClientData;

public class ComponentTest {
	
	private ListClients listClients;
	private ClientData client1;
	
	@Before
	public void SetUp(){
		listClients = new ListClients();
		client1 = new ClientData("lulu", 12, System.currentTimeMillis());
	}
	
	@Test
	public void testIsNewClientAddClient() {
		listClients.addClient(client1);
	}
	
	@Test
	public void testUpdateClient() {
		listClients.addClient(client1);
		assertEquals(3,listClients.updateClient("tata", 3));
		assertEquals(2,listClients.getListClients().size());
		assertEquals(3,listClients.updateClient("tata", 6));
		assertEquals(2,listClients.getListClients().size());
		assertEquals(0,listClients.updateClient("è_fstvièt#]@^`|[{#€bkskcdky_)ç)27", 0));
		assertEquals(3,listClients.getListClients().size());
	}

}
