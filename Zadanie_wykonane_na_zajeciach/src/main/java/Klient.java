import com.hazelcast.aggregation.Aggregators;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import data.Prowadzacy;
import data.Student;
import data.Zajecia;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Klient {

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
        System.out.println("3.prowadzący");
        return scan.nextInt();
    }

    public static int save(Scanner scan,Random r,HazelcastInstance client){
        System.out.println("Wybierz kubełek do zapisu");
        while (true) {
        int saveOperation = menu(scan);
        scan.nextLine();

            if (saveOperation == 1) {
                IMap<Long, Student> students = client.getMap("students");
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
                students.put((long) Math.abs(r.nextInt()), student);

            } else if (saveOperation == 2) {
                IMap<Long, Zajecia> zajeciaMap = client.getMap("zajecia");
                System.out.println("Podaj nazwe przedmiotu");
                String name = scan.nextLine();
                System.out.println("Podaj numer pokoju");
                int roomNumber = scan.nextInt();
                scan.nextLine();
                System.out.println("Zapisano zajecia o nazwie: " + name);
                Zajecia zajecia = new Zajecia(name,roomNumber);
                zajeciaMap.put((long) Math.abs(r.nextInt()), zajecia);

            } else if (saveOperation == 3) {
                IMap<Long, Prowadzacy> prowadzacyMap = client.getMap("prowadzacy");
                System.out.println("Podaj imie prowadzacego");
                String name = scan.nextLine();
                System.out.println("Podaj nazwisko prowadzacego");
                String surName = scan.nextLine();
                System.out.println("Podaj tytul naukowy prowadzacego");
                String pHD = scan.nextLine();
                Prowadzacy prowadzacy = new Prowadzacy(name, surName, pHD);
                System.out.println("Zapisano prowadzacego o imieniu: " + name + " nazwisku: " + surName + "tytule naukowy: " + pHD);
                prowadzacyMap.put((long) Math.abs(r.nextInt()), prowadzacy);

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

    public static int update(Scanner scan,Random r,HazelcastInstance client) {
        System.out.println("Wybierz kubełek do zaktualizowania");
        while (true) {
        int saveOperation = menu(scan);
        scan.nextLine();

            if (saveOperation == 1) {
                IMap<Long, Student> students = client.getMap("students");
                System.out.println("Podaj id klucza-wartości kóre chcesz zaktualizować");
                long id = scan.nextInt();
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
                IMap<Long, Zajecia> zajeciaMap = client.getMap("zajecia");
                System.out.println("Podaj id klucza-wartości kóre chcesz zaktualizować");
                long id = scan.nextInt();
                scan.nextLine();
                System.out.println("Podaj nazwe przedmiotu");
                String name = scan.nextLine();
                System.out.println("Podaj numer pokoju");
                int roomNumber = scan.nextInt();
                scan.nextLine();
                System.out.println("Zaktualizowana zajecia o id:" + id + " parametrami nazwa: " + name);
                Zajecia zajecia = new Zajecia(name,roomNumber);
                zajeciaMap.replace(id, zajecia);


            } else if (saveOperation == 3) {
               IMap<Long, Prowadzacy> prowadzacyMap = client.getMap("prowadzacy");
                System.out.println("Podaj id klucza-wartości kóre chcesz zaktualizować");
                long id = scan.nextInt();
                scan.nextLine();
                System.out.println("Podaj imie prowadzacego");
                String name = scan.nextLine();
                System.out.println("Podaj nazwisko prowadzacego");
                String surName = scan.nextLine();
                System.out.println("Podaj tytul naukowy prowadzacego");
                String pHD = scan.nextLine();
                Prowadzacy prowadzacy = new Prowadzacy(name, surName, pHD);
                System.out.println("Zaktualizowana prowadzacego o id:" + id + " parametrami imie: " + name + " nazwisku: " + surName + "tytule naukowy: " + pHD);
                prowadzacyMap.replace(id, prowadzacy);


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

    public static int remove(Scanner scan,Random r,HazelcastInstance client) {
        System.out.println("Wybierz kubełek z którego chcesz usunąć");
        while (true) {
            int saveOperation = menu(scan);
            scan.nextLine();
            if (saveOperation == 1) {
                IMap<Long, Student> students = client.getMap("students");
                System.out.println("Podaj id klucza-wartości kóre chcesz usunąć");
                long id = scan.nextInt();
                scan.nextLine();
                System.out.println("Usunieto studenta o id: " + id);
                students.remove(id);

            } else if (saveOperation == 2) {
                IMap<Long, Zajecia> zajeciaMap = client.getMap("zajecia");
                System.out.println("Podaj id klucza-wartości kóre chcesz usunąć");
                long id = scan.nextInt();
                scan.nextLine();
                System.out.println("Usunieto zajecia o id: " + id);
                zajeciaMap.remove(id);

            } else if (saveOperation == 3) {
                IMap<Long, Prowadzacy> prowadzacyMap = client.getMap("prowadzacy");
                System.out.println("Podaj id klucza-wartości kóre chcesz usunąć");
                long id = scan.nextInt();
                scan.nextLine();
                System.out.println("Usunieto prowadzacego o id: " + id);
                prowadzacyMap.remove(id);

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

    public static int show(Scanner scan,Random r,HazelcastInstance client) {
        System.out.println("Wybierz kubełek do wyświetlenia");
        while (true) {
            int saveOperation = menu(scan);
            if (saveOperation == 1) {
                IMap<Long, Student> students = client.getMap("students");
                System.out.println("Wszyscy studenci: ");
                for (Map.Entry<Long, Student> e : students.entrySet()) {
                    System.out.println(e.getKey() + " => " + e.getValue());
                }

            } else if (saveOperation == 2) {
                IMap<Long, Zajecia> zajeciaMap = client.getMap("zajecia");
                System.out.println("Wszystkie zajecia: ");
                for (Map.Entry<Long, Zajecia> e : zajeciaMap.entrySet()) {
                    System.out.println(e.getKey() + " => " + e.getValue());
                }

            } else if (saveOperation == 3) {
                IMap<Long, Prowadzacy> prowadzacyMap = client.getMap("prowadzacy");
                System.out.println("Wszyscy prowadzacy: ");
                for (Map.Entry<Long, Prowadzacy> e : prowadzacyMap.entrySet()) {
                    System.out.println(e.getKey() + " => " + e.getValue());
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

    public static int get(Scanner scan,Random r,HazelcastInstance client) {
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
                    IMap<Long, Student> students = client.getMap("students");
                    System.out.println("Podaj id klucza-wartości kóre chcesz wyswietlic");
                    long id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Dane wybranego studenta: " +students.get(id));
                } else if (saveOperation == 2) {
                    IMap<Long, Zajecia> zajeciaMap = client.getMap("zajecia");
                    System.out.println("Podaj id klucza-wartości kóre chcesz wyswietlic");
                    long id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Dane wybranych zajec: " +zajeciaMap.get(id));

                } else if (saveOperation == 3) {
                    IMap<Long, Prowadzacy> prowadzacyMap = client.getMap("prowadzacy");
                    System.out.println("Podaj id klucza-wartości kóre chcesz wyswietlic");
                    long id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Dane wybranego prowadzacego: " +prowadzacyMap.get(id));
                }
                else if (saveOperation == 0) {
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
                    IMap<Long, Student> students = client.getMap("students");
                    System.out.println("Podaj nazwisko studenta którego chcesz znaleźc");
                    String surname = scan.nextLine();
                    Predicate<?,?> namePredicate = Predicates.equal( "surname", surname);
                    Collection<Student> search = students.values(Predicates.and(namePredicate));
                    for (Student s : search) {
                        System.out.println(s);
                    }

                } else if (saveOperation == 2) {
                    IMap<Long, Zajecia> zajeciaMap = client.getMap("zajecia");
                    System.out.println("Podaj nazwe zajec które chcesz znalezc");
                    String name = scan.nextLine();
                    Predicate<?,?> namePredicate = Predicates.equal( "name", name);
                    Collection<Zajecia> search = zajeciaMap.values(Predicates.and(namePredicate));
                    for (Zajecia s : search) {
                        System.out.println(s);
                    }

                } else if (saveOperation == 3) {
                    IMap<Long, Prowadzacy> prowadzacyMap = client.getMap("prowadzacy");
                    System.out.println("Podaj nazwisko prowadzacego którego chcesz znalezc");
                    String surname = scan.nextLine();
                    Predicate<?,?> namePredicate = Predicates.equal( "name", surname);
                    Collection<Prowadzacy> search = prowadzacyMap.values(Predicates.and(namePredicate));
                    for (Prowadzacy s : search) {
                        System.out.println(s);
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

    public static int proccessing(Scanner scan,Random r,HazelcastInstance client) {
        System.out.println("Wybierz rodzaj pobrania");
        while (true) {
            System.out.println("0.powrot");
            System.out.println("1.po stronie klienta");
            System.out.println("2.po stronie serwera");
            int getOperation=scan.nextInt();
            scan.nextLine();
            if(getOperation==1) {
                IMap<Long, Student> students = client.getMap("students");
                System.out.println("Sredni wiek wszystkich studentow: "+students.aggregate(Aggregators.integerAvg("age")));
            }
            else if(getOperation==2){
                 new HExecutorService();
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

    public static void main(String[] args) throws UnknownHostException {
        Random r = new Random(System.currentTimeMillis());
        Scanner scan = new Scanner(System.in);
        ClientConfig clientConfig = HConfig.getClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );
        int operation=showMainOperation(scan);
        scan.nextLine();
        while(true) {
             if(operation==1) {
               operation= save(scan,r,client);
             }
            else if(operation==2){
                operation=update(scan,r,client);
                }
             else if(operation==3){
               operation=  remove(scan,r,client);

                }
             else if(operation==4){
                   operation=get(scan,r,client);
                }
             else if(operation==5){
                 operation=proccessing(scan,r,client);
                }
            else if(operation==6){
                 operation=show(scan,r,client);


            }else if(operation==0){
                 System.out.println("Zamkniecie aplikacji");
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
