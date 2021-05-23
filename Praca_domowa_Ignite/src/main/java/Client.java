import Data.Lesson;
import Data.Student;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.apache.ignite.lang.IgniteRunnable;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;

import javax.cache.Cache;
import java.util.*;

public class Client {
    public static int showMainOperation(Scanner scan){
        System.out.println("Wybierz rodzaj operaccji:");
        System.out.println("0.zamknij");
        System.out.println("1.zapisywanie");
        System.out.println("2.aktualizacja");
        System.out.println("3.kasowanie");
        System.out.println("4.pobieranie");
        System.out.println("5.przetwarzanie");
        System.out.println("6.wyswietlenie");
        return scan.nextInt();
    }

    public static int menu(Scanner scan){

        System.out.println("0.Powrot");
        System.out.println("1.studenci");
        System.out.println("2.zajecia");
        return scan.nextInt();
    }

    public static int save(Scanner scan, Random r, Ignite ignite){
        System.out.println("Wybierz kubełek do zapisu");
        while (true) {
            int saveOperation = menu(scan);
            scan.nextLine();

            if (saveOperation == 1) {
                IgniteCache<Integer, Student> students = ignite.getOrCreateCache("Students");
                System.out.println("Podaj imie studenta");
                String name = scan.nextLine();
                System.out.println("Podaj nazwisko studenta");
                String surName = scan.nextLine();
                System.out.println("Podaj numer indexu studenta");
                int indexNumber = scan.nextInt();
                scan.nextLine();
                System.out.println("Podaj wiek studenta");
                int birthyear = scan.nextInt();
                scan.nextLine();
                Student student = new Student(name, surName, indexNumber, birthyear);
                System.out.println("Zapisano studenta o  imieniu: " + name + " nazwisku: " + surName + " numerze Indeksu: " + indexNumber + " dacie urodzenia: " + birthyear);
                students.put(Math.abs(r.nextInt()), student);

            } else if (saveOperation == 2) {
                IgniteCache<Integer, Lesson> lessons = ignite.getOrCreateCache("Lessons");
                System.out.println("Podaj nazwe przedmiotu");
                String name = scan.nextLine();
                System.out.println("Podaj numer pokoju");
                int roomNumber = scan.nextInt();
                scan.nextLine();
                System.out.println("Podaj dane prowadzacego");
                String teacher = scan.nextLine();
                System.out.println("Zapisano zajecia o nazwie: " + name);
                Lesson lesson = new Lesson(name,roomNumber,teacher);
                lessons.put( Math.abs(r.nextInt()), lesson);

            }  else if (saveOperation == 0) {
                int operation = showMainOperation(scan);
                return operation;
            } else {
                System.out.println("Wprowadzon złą wartośc");
                saveOperation = menu(scan);
                scan.nextLine();
            }
        }

    }

    public static int update(Scanner scan,Random r,Ignite ignite) {
        System.out.println("Wybierz kubełek do zaktualizowania");
        while (true) {
            int saveOperation = menu(scan);
            scan.nextLine();

            if (saveOperation == 1) {
                IgniteCache<Integer, Student> students = ignite.getOrCreateCache("Students");
                System.out.println("Podaj id klucza-wartości kóre chcesz zaktualizować");
                int id = scan.nextInt();
                scan.nextLine();
                System.out.println("Podaj imie studenta");
                String name = scan.nextLine();
                System.out.println("Podaj nazwisko studenta");
                String surName = scan.nextLine();
                System.out.println("Podaj numer indexu studenta");
                int indexNumber = scan.nextInt();
                scan.nextLine();
                System.out.println("Podaj wiek studenta");
                int birthyear = scan.nextInt();
                scan.nextLine();
                Student student = new Student(name, surName, indexNumber, birthyear);
                System.out.println("Zaktualizowana studenta o id:" + id + " parametrami imieniu: " + name + " nazwisku: " + surName + " numerze Indeksu: " + indexNumber + " dacie urodzenia: " + birthyear);
                students.replace(id, student);

            } else if (saveOperation == 2) {
                IgniteCache<Integer, Lesson> lessons = ignite.getOrCreateCache("Lessons");
                System.out.println("Podaj id klucza-wartości kóre chcesz zaktualizować");
                int id = scan.nextInt();
                scan.nextLine();
                System.out.println("Podaj nazwe przedmiotu");
                String name = scan.nextLine();
                System.out.println("Podaj numer pokoju");
                int roomNumber = scan.nextInt();
                scan.nextLine();
                System.out.println("Podaj dane prowadzacego");
                String teacher = scan.nextLine();
                System.out.println("Zaktualizowana zajecia o id:" + id + " parametrami nazwa: " + name);
                Lesson zajecia = new Lesson(name,roomNumber,teacher);
                lessons.replace(id, zajecia);


            }  else if (saveOperation == 0) {
                int operation = showMainOperation(scan);
                return operation;
            } else {
                System.out.println("Wprowadzon złą wartośc");
                saveOperation = menu(scan);
                scan.nextLine();
            }
        }

    }

    public static int remove(Scanner scan,Random r,Ignite ignite) {
        System.out.println("Wybierz kubełek z którego chcesz usunąć");
        while (true) {
            int saveOperation = menu(scan);
            scan.nextLine();
            if (saveOperation == 1) {
                IgniteCache<Integer, Student> students = ignite.getOrCreateCache("Students");
                System.out.println("Podaj id klucza-wartości kóre chcesz usunąć");
                int id = scan.nextInt();
                scan.nextLine();
                System.out.println("Usunieto studenta o id: " + id);
                students.remove(id);

            } else if (saveOperation == 2) {
                IgniteCache<Integer, Lesson> lessons = ignite.getOrCreateCache("Lessons");
                System.out.println("Podaj id klucza-wartości kóre chcesz usunąć");
                int id = scan.nextInt();
                scan.nextLine();
                System.out.println("Usunieto zajecia o id: " + id);
                lessons.remove(id);

            }  else if (saveOperation == 0) {
                int operation = showMainOperation(scan);
                return operation;
            } else {
                System.out.println("Wprowadzon złą wartośc");
                saveOperation = menu(scan);
                scan.nextLine();
            }
        }

    }

   public static int show(Scanner scan,Random r,Ignite ignite) {
        System.out.println("Wybierz kubełek do wyświetlenia");
        while (true) {
            int saveOperation = menu(scan);
            if (saveOperation == 1) {
                IgniteCache<Integer, Student> students = ignite.getOrCreateCache("Students");
                System.out.println("Wszyscy studenci: ");
                try (QueryCursor<Cache.Entry<Integer, Student>> qryCursor = students.query(new ScanQuery<>(null))) {
                    qryCursor.forEach(
                            entry -> System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()));
                }

            } else if (saveOperation == 2) {
                IgniteCache<Integer, Lesson> lessons = ignite.getOrCreateCache("Lessons");
                System.out.println("Wszystkie zajecia: ");
                try (QueryCursor<Cache.Entry<Integer, Lesson>> qryCursor = lessons.query(new ScanQuery<>(null))) {
                    qryCursor.forEach(
                            entry -> System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()));
                }

            } else if (saveOperation == 0) {
                int operation = showMainOperation(scan);
                return operation;
            } else {
                System.out.println("Wprowadzon złą wartośc");
                saveOperation = menu(scan);
                scan.nextLine();
            }
        }
    }

   public static int get(Scanner scan,Random r,Ignite ignite) {
        System.out.println("Wybierz rodzaj pobrania");
        while (true) {
            System.out.println("0.powrot");
            System.out.println("1.po indeksie");
            System.out.println("2.zlozone");
            int getOperation=scan.nextInt();
            scan.nextLine();
            if(getOperation==1) {
                int saveOperation = menu(scan);
                if (saveOperation == 1) {
                    IgniteCache<Integer, Student> students = ignite.getOrCreateCache("Students");
                    System.out.println("Podaj id klucza-wartości kóre chcesz wyswietlic");
                    int id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Dane wybranego studenta: " +students.get(id));
                } else if (saveOperation == 2) {
                    IgniteCache<Integer, Lesson> lessons = ignite.getOrCreateCache("Lessons");
                    System.out.println("Podaj id klucza-wartości kóre chcesz wyswietlic");
                    int id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Dane wybranych zajec: " +lessons.get(id));

                } else if (saveOperation == 0) {
                    int operation = showMainOperation(scan);
                    return operation;
                } else {
                    System.out.println("Wprowadzon złą wartośc");
                    saveOperation = menu(scan);
                    scan.nextLine();
                }
            }else if(getOperation==2){
                int saveOperation = menu(scan);
                scan.nextLine();
                if (saveOperation == 1) {
                    IgniteCache<Integer, Student> students = ignite.getOrCreateCache("Students");
                    System.out.println("Podaj nazwisko studenta którego chcesz znaleźc");
                    String surname = scan.nextLine();
                    IgniteBiPredicate<Integer, Student> filter = (key, p) -> p.getSurname().equals(surname);
                    try (QueryCursor<Cache.Entry<Integer, Student>> qryCursor = students.query(new ScanQuery<>(filter))) {
                        qryCursor.forEach(
                                entry -> System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()));
                    }

                } else if (saveOperation == 2) {
                    IgniteCache<Integer, Lesson> lessons = ignite.getOrCreateCache("Lessons");
                    System.out.println("Podaj nazwe zajec które chcesz znalezc");
                    String name = scan.nextLine();
                    IgniteBiPredicate<Integer, Lesson> filter = (key, p) -> p.getName().equals(name);
                    try (QueryCursor<Cache.Entry<Integer, Lesson>> qryCursor = lessons.query(new ScanQuery<>(filter))) {
                        qryCursor.forEach(
                                entry -> System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()));
                    }

                } else if (saveOperation == 0) {
                    int operation = showMainOperation(scan);
                    return operation;
                } else {
                    System.out.println("Wprowadzon złą wartośc");
                    saveOperation = menu(scan);
                    scan.nextLine();
                }
            }else if (getOperation == 0) {
                int operation = showMainOperation(scan);
                return operation;
            }else{
                System.out.println("Wprowadzon złą wartośc");
            }
        }
    }

    public static int proccessing(Scanner scan,Random r,Ignite ignite) {
        System.out.println("Wybierz rodzaj pobrania");
        while (true) {
            System.out.println("0.powrot");
            System.out.println("1.po stronie klienta");
            System.out.println("2.po stronie serwera");
            int getOperation=scan.nextInt();
            scan.nextLine();
            if(getOperation==1) {
                double avg=0;
                IgniteCache<Integer, Student> students = ignite.getOrCreateCache("Students");

                for (Cache.Entry<Integer, Student> student : students) {
                    avg += student.getValue().getBirthyear();
                }
                System.out.println("Sednia wieku studentów wynosi: "+avg/students.size()+" lat");
            }
            else if(getOperation==2){
                ignite.compute(ignite.cluster().forServers()).broadcast(new RemoteTask());
                System.out.println("Wyświetlono sredni wiek po stronie serwera");
            }
            else if(getOperation==0){
                int operation = showMainOperation(scan);
                return operation;
            }
            else{
                System.out.println("Wprowadzon złą wartośc");
            }
        }
    }


    public static void main(String[] args) throws IgniteException {
        Random r = new Random(System.currentTimeMillis());
        Scanner scan = new Scanner(System.in);
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(true);
        cfg.setPeerClassLoadingEnabled(true);
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500"));
        cfg.setDiscoverySpi(new TcpDiscoverySpi().setIpFinder(ipFinder));
        Ignite ignite = Ignition.start(cfg);

        int operation=showMainOperation(scan);
        scan.nextLine();
        while(true) {
            if(operation==1) {
                operation= save(scan,r,ignite);
            }
            else if(operation==2){
               operation=update(scan,r,ignite);
            }
            else if(operation==3){
              operation=  remove(scan,r,ignite);
            }
            else if(operation==4){
               operation=get(scan,r,ignite);
            }
            else if(operation==5){
               operation=proccessing(scan,r,ignite);
            }
            else if(operation==6){
                operation=show(scan,r,ignite);


            }else if(operation==0){
                System.out.println("Zamkniecie aplikacji");
                ignite.close();
                return;
            }
            else {
                System.out.println("Wprowadzon złą wartośc");
                operation=showMainOperation(scan);
                scan.nextLine();
            }

        }

    }

}
