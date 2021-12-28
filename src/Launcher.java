import java.util.Scanner;

public class Launcher {

    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Vous voulez jouer au Colon de Catan? [oui/non] ");
        String rep = sc.nextLine();

        boolean state = true;
        while (state) {
            if (rep.equals("oui") || rep.equals("o") || rep.equals("Oui") || rep.equals("yes")) {
                System.out.println("Préférez-vous jouer sur la terminal ou sur l'interface graphique ? [t/ig]");
                rep = sc.nextLine();
                if (rep.equals("ig") || rep.equals("interface graphique")) {
                    System.out.println("D'accord!");
                    WelcomeWindow ww = new WelcomeWindow();
                } else if (rep.equals("t") || rep.equals("terminal")) {
                    System.out.println("D'accord!");
                    System.out.println("Désolé :( cette option est encore en construction");
                    System.exit(0);
                }
            } else if (rep.equals("non") || rep.equals("n") || rep.equals("Non") || rep.equals("no")) {
                System.out.println("Au revoir! :)");
                System.exit(0);
            } else {
                System.out.println("Essayez de nouveau");
                state = false;
            }
        }


    }




}
