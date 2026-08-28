import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ContactManager {
    public static void main(String[] args) {
        HashMap<String, Contact> contacts = new HashMap<>();

        addContact(contacts, "Ada Lovelace", "+1 617 555 0101");
        addContact(contacts, "Alan Turing", "+1 617 555 0102");
        addContact(contacts, "Grace Hopper", "+1 617 555 0103");
        addContact(contacts, "Katherine Johnson", "+1 617 555 0104");
        addContact(contacts, "Linus Torvalds", "+1 617 555 0105");

        System.out.println("--KNOWN--");
        lookupContact(contacts, "Grace Hopper");

        System.out.println("--UNKNOWN--");
        lookupContact(contacts, "Margaret Hamilton");

        ArrayList<Contact> sortedContacts = new ArrayList<>(contacts.values());
        sortedContacts.sort(Comparator.comparing(Contact::getName));

        System.out.println("-ALL-");
        for (Contact contact : sortedContacts) {
            System.out.println(contact);
        }
    }

    private static void addContact(HashMap<String, Contact> contacts,
                                   String name, String phone) {
        contacts.put(name, new Contact(name, phone));
    }

    private static void lookupContact(HashMap<String, Contact> contacts,
                                      String name) {
        Contact contact = contacts.get(name);

        if (contact != null) {
            System.out.println("Found: " + contact);
        } else {
            System.out.println("Contact not found: " + name);
        }
    }
}
