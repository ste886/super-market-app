import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
CREARE UNA APPLICAZIONE CHE POSSA GESTIRE UNA LISTA DI SUPERMARKET

SUPERMARKET DIVISI IN REPARTI -> OGNI REPARTO HA UNA LISTA DI PRODOTTI

UTENTE
    1) RICERCARE UN PRODOTTO E VEDERE IN QUALI SUPERMERCATI E' PRESENTE
        1.1) RICERCARE UN PRODOTTO IN UN CERTO SUPERMERCATO
        1.2) STAMPARE TUTTI I PRODOTTI PRESENTI IN UN CERTO SUPERMERCATO
        1.3) RICERCARE SE UN REPARTO E' PRESENTE IN UN CERTO SUPERMERCATO
        1.4) USARE TOSTRING() PER STAMPARE LE INFO DEI PRODOTTI

    2) ACQUISTARE QUEL PRODOTTO DATA UNA CERTA QUANTITA' (1 UNITA')
    3) GESTIRE IL CARRELLO ->
        3.1) L'UTENTE PUO' STAMPARE E VEDERE IL SUO CARRELLO
        3.2) PUO' RIMUOVERE UNO O PIU' PRODOTTI

        3.3) AGGIUNGERE PRODOTTI AL CARRELLO
        3.4) CHECKOUT DEL CARRELLO -> STAMPA LISTA PRODOTTI + MOSTRO TOTALE PRODOTTI

    4) CONSEGNA OPZIONALE: GESTIRE LE OPERAZIONI UTENTE DA STANDARD INPUT

    N.B.
    dovete versionare l'esercizio sul vostro repository github


 */
public class App {
    private ShoppingCart shoppingCart;
    private List<SuperMarket> superMarkets;

    public App(ShoppingCart shoppingCart, List<SuperMarket> superMarkets) {
        this.shoppingCart = shoppingCart;
        this.superMarkets = superMarkets;
    }


    public static void main(String[] args) {

        ShoppingCart shoppingCart = new ShoppingCart();
        List<SuperMarket> superMarkets = new ArrayList<>();

        superMarkets.add(createSuperMarket("Conad", "via dei Conad"));
        superMarkets.add(createSuperMarket("Despar", "via da Qui"));
        superMarkets.add(createSuperMarket("Famila", "tricesimo"));

        App app = new App(shoppingCart, superMarkets);

        // Cerco un prodotto in tutti i supermercati, e stampo i supermercati.
        List<SuperMarket> superMarketsWithRiso = app.findProduct("riso");
        for (SuperMarket superMarket : superMarketsWithRiso) {
            System.out.println(superMarket);
        }

        // Cerco un prodotto in un supermercato specifico.
        if (app.findProductInSuperMarket("Conad", "riso")) {
            System.out.println("Il Conad vende riso.");
        } else {
            System.out.println("Il Conad non vende riso.");
        }
        if (app.findProductInSuperMarket("Conad", "pasta")) {
            System.out.println("Il Conad vende pasta.");
        } else {
            System.out.println("Il Conad non vende pasta.");
        }

        // Stampa tutti i prodotti di un supermercato.
        app.getSuperMarketByName("Conad").printProducts();

        // Certa un reparto in uno specifico supermercato
        if (app.findDepartmentInSuperMarket("Conad", "Igiene")) {
            System.out.println("Il Conad ha un reporto Igiene.");
        } else {
            System.out.println("Il Conad non ha un reparto Igiene");
        }

        if (app.findDepartmentInSuperMarket("Conad", "Motori")) {
            System.out.println("Il Conad ha un reporto Motori.");
        } else {
            System.out.println("Il Conad non ha un reparto Motori");
        }

        // Compra prodotto
        if (app.findProductInSuperMarket("Despar", "riso")) {
            app.addToShoppingCart("Despar", "riso", 5);
        }

        // Stampa contenuto del carrello
        app.printShoppingCart();
    }

    private void addToShoppingCart(String superMarketName, String productName, int quantity) {
        SuperMarket superMarket = getSuperMarketByName(superMarketName);
        Product product = superMarket.getProductByName(productName);
        shoppingCart.add(product, quantity);
    }


    private static Department createHygieneDepartment() {

        Product cottonfioc = new Product("cottonfioc", 0.99, 1);
        Product dentifricio = new Product("dentifricio", 2.15, 2);
        Product cartaigienica = new Product("cartaigienica", 2.50, 3);
        Product deodorante = new Product("deodorante", 2.00, 4);
        Product cremaviso = new Product("cremaviso", 10.00, 5);
        Product fazzoletti = new Product("fazzoletti", 2.00, 6);

        Map<String, Product> hygieneDepartment = new HashMap<>();

        hygieneDepartment.put(cottonfioc.getName(), cottonfioc);
        hygieneDepartment.put(dentifricio.getName(), dentifricio);
        hygieneDepartment.put(cartaigienica.getName(), cartaigienica);
        hygieneDepartment.put(deodorante.getName(), deodorante);
        hygieneDepartment.put(cremaviso.getName(), cremaviso);
        hygieneDepartment.put(fazzoletti.getName(), fazzoletti);

        Department result = new Department("Igiene", hygieneDepartment);
        return result;

    }

    private static Department createAnimalDepartment() {

        Product bocconciniGatto = new Product("bocconciniGatto", 1.00, 7);
        Product bocconciniCane = new Product("bocconciniCane", 1.00, 8);
        Product croccantiniGatto = new Product("croccantiniGatto", 5.00, 9);
        Product croccantiniCane = new Product("CroccantiniCane", 5.00, 10);
        Product pateGatto = new Product("pateGatto", 1.00, 11);
        Product lettiera = new Product("lettiera", 5.00, 12);

        Map<String, Product> animalDepartment = new HashMap<>();
        animalDepartment.put(bocconciniGatto.getName(), bocconciniGatto);
        animalDepartment.put(bocconciniCane.getName(), bocconciniCane);
        animalDepartment.put(croccantiniGatto.getName(), croccantiniGatto);
        animalDepartment.put(croccantiniCane.getName(), croccantiniCane);
        animalDepartment.put(pateGatto.getName(), pateGatto);
        animalDepartment.put(lettiera.getName(), lettiera);

        Department result = new Department("Animal", animalDepartment);
        return result;

    }

    private static Department createMeatDepartment() {

        Product spiedini = new Product("spiedini", 5.00, 13);
        Product rostbeef = new Product("rostbeef", 6.00, 14);
        Product lonza = new Product("lonza", 4.50, 15);
        Product filetto = new Product("filetto", 8.50, 16);
        Product coscePollo = new Product("coscePollo", 3.50, 17);
        Product pettoPollo = new Product("pettoPollo", 5.00, 18);

        Map<String, Product> meatDepartment = new HashMap<>();
        meatDepartment.put(spiedini.getName(), spiedini);
        meatDepartment.put(rostbeef.getName(), rostbeef);
        meatDepartment.put(lonza.getName(), lonza);
        meatDepartment.put(filetto.getName(), filetto);
        meatDepartment.put(coscePollo.getName(), coscePollo);
        meatDepartment.put(pettoPollo.getName(), pettoPollo);

        Department result = new Department("Carne", meatDepartment);
        return result;

    }

    private static Department createFishDepartment() {

        Product orata = new Product("orata", 9.00, 19);
        Product branzino = new Product("branzino", 9.00, 20);
        Product cozze = new Product("cozze", 4.50, 21);
        Product trota = new Product("trota", 6.50, 22);
        Product polpo = new Product("polpo", 7.50, 23);
        Product sardine = new Product("sardine", 3.50, 24);

        Map<String, Product> fishDepartment = new HashMap<>();

        fishDepartment.put(orata.getName(), orata);
        fishDepartment.put(branzino.getName(), branzino);
        fishDepartment.put(cozze.getName(), cozze);
        fishDepartment.put(trota.getName(), trota);
        fishDepartment.put(polpo.getName(), polpo);
        fishDepartment.put(sardine.getName(), sardine);

        Department result = new Department("Pesce", fishDepartment);
        return result;

    }

    private static Department createPastaDepartment() {

        Product farfalle = new Product("farfalle", 1.50, 25);
        Product spaghetti = new Product("spaghetti", 1.75, 26);
        Product penne = new Product("penne", 1.50, 27);
        Product fusilli = new Product("fusilli", 1.50, 28);
        Product rigatoni = new Product("rigatoni", 1.75, 29);
        Product riso = new Product("riso", 2.50, 30);

        Map<String, Product> pastaDepartment = new HashMap<>();

        pastaDepartment.put(farfalle.getName(), farfalle);
        pastaDepartment.put(spaghetti.getName(), spaghetti);
        pastaDepartment.put(penne.getName(), penne);
        pastaDepartment.put(fusilli.getName(), fusilli);
        pastaDepartment.put(rigatoni.getName(), rigatoni);
        pastaDepartment.put(riso.getName(), riso);

        Department result = new Department("Pasta", pastaDepartment);
        return result;
    }

    private static SuperMarket createSuperMarket(String name, String address) {

        Department pastaDepartment = createPastaDepartment();
        Department fisfDepartment = createFishDepartment();
        Department meatDepartment = createMeatDepartment();
        Department animalDepartment = createAnimalDepartment();
        Department hygieneDepartment = createHygieneDepartment();


        List<Department> departments = new ArrayList<>();

        departments.add(pastaDepartment);
        departments.add(fisfDepartment);
        departments.add(meatDepartment);
        departments.add(animalDepartment);
        departments.add(hygieneDepartment);

        SuperMarket res = new SuperMarket(name, address, departments);
        return res;

    }

    public List<SuperMarket> findProduct(String name) {
        List<SuperMarket> result = new ArrayList<>();
        for (SuperMarket superMarket: superMarkets) {
            if (superMarket.findProduct(name)) {
                result.add(superMarket);
            }
        }
        return result;
    }

    public boolean findProductInSuperMarket(String superMarketName, String productName) {
        SuperMarket superMarket = getSuperMarketByName(superMarketName);
        return superMarket.findProduct(productName);
    }

    private boolean findDepartmentInSuperMarket(String superMarketName, String departmentName) {
        SuperMarket superMarket = getSuperMarketByName(superMarketName);
        return superMarket.findDepartment(departmentName);
    }

    public SuperMarket getSuperMarketByName(String name) {
        for (SuperMarket superMarket : superMarkets) {
            if (superMarket.getName().equals(name)) {
                return superMarket;
            }
        }
        return null;
    }

    void printShoppingCart() {
        System.out.println(shoppingCart);
    }
}

