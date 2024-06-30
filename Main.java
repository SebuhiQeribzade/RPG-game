
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static int gold = 0;
    public static int healthpotion = 1;
    public static int manapotion = 0;

    public static void main(String[] args) {

            List<Character> heroes = new ArrayList<>();
            heroes.add(new Character("Warrior", 200, 90, 0, 120, 40));
            heroes.add(new Character("Mage", 50, 5, 150, 50, 100));
            heroes.add(new Character("Assassin", 100, 125, 25, 75, 75));
            heroes.add(new Character("Paladin", 150, 140, 20, 100, 150));
            heroes.add(new Character("Healer", 80, 10, 40, 50, 50));
            heroes.add(new Character("Exorcist", 50, 50, 125, 50, 75));

            List<Character> enemies = new ArrayList<>();
            enemies.add(new Character("Zombie", 50, 55, 55, 10, 5));
            enemies.add(new Character("Ghost", 100, 60, 100, 9999, 10));
            enemies.add(new Character("Necromancer", 500, 100, 250, 100, 150));
            enemies.add(new Character("Werewolf", 500, 120, 0, 75, 50));
            enemies.add(new Character("Vampire", 250, 80, 60, 50, 75));
            enemies.add(new Character("Dracula", 100, 150, 150, 150, 150));
            enemies.add(new Character("Ork", 300, 100, 0, 75, 50));
            enemies.add(new Character("Wendigo", 500, 150, 70, 90, 90));
            enemies.add(new Character("Troll", 1000, 200, 0, 150, 150));

            Scanner scanner = new Scanner(System.in);
            List<Character> selectedTeam = new ArrayList<>();
            int teamlimit = 3;
            for (int i = 0; i < heroes.size(); i++) {
                System.out.println(i + 1 + ". " + heroes.get(i));
            }
            while (selectedTeam.size() < teamlimit) {
                System.out.println("\nSelect your heroes:");
                int choice = scanner.nextInt();
                if (choice > 0 && choice < heroes.size() + 1) {
                    Character selectedhero = heroes.get(choice - 1);
                    if (!selectedTeam.contains(selectedhero)) {
                        selectedTeam.add(selectedhero);
                        System.out.println(selectedhero.name + " has been added to your team");
                    } else {
                        System.out.println("Hero already selected. Choose different heroe");
                    }
                } else {
                    System.out.println("Invalid choice. Please try again");
                }
            }
            System.out.println("\nA team consists of :");
            for (Character hero : selectedTeam) {
                System.out.println("a " + hero.name + ",");
            }
            System.out.println("has begun their adventure!");
            System.out.println("\"Now you you have to chose your first path,\nwould you go from (main road), (forest) or (forbidden city)?\"");
            scanner.nextLine();  // Consume newline left-over
            String pathChoice = scanner.nextLine().toLowerCase();

            List<Character> enemyTeam = new ArrayList<>();
            if (pathChoice.equals("main road")) {
                for (int i = 0; i < 4; i++) {
                    enemyTeam.add(new Character("Zombie", 65, 50, 5, 10, 5));
                }
                System.out.println("\nYour team was passing by a old graveyard and they encountered 4 zombies!");
            } else if (pathChoice.equals("forest")) {
                for (int i = 0; i < 2; i++) {
                    enemyTeam.add(new Character("Ork", 200, 100, 0, 75, 50));
                }
                System.out.println("\nYour team was going through a dark forest and their path blocked by 2 orks!");
            } else if (pathChoice.equals("forbidden city")) {
                for (int i = 0; i < 3; i++) {
                    enemyTeam.add(new Character("Vampire", 150, 80, 60, 50, 75));
                }
                System.out.println("\nYour team has found an abandoned city, and when they entered it they have found 3 vampires waiting for them!");
            } else {
                System.out.println("Invalid path choice. The adventure ends here.");
                return;
            }

            fight(scanner, selectedTeam, enemyTeam);
        }

        public static void fight (Scanner scanner, List < Character > heroes, List < Character > enemies){
            boolean heroesTurn = true;

            if (heroesTurn) {

                while (!heroes.isEmpty() && !enemies.isEmpty()) {
                    System.out.println("\nYour turn! Do you want to 1)attack or 2)use potions:");
                    String actionChoice = scanner.nextLine().toLowerCase();
                    if (actionChoice.equals("1")) {
                        for (int i = 0; i < heroes.size(); i++) {
                            System.out.println((i + 1) + ". " + heroes.get(i));
                        }
                        System.out.println("Which hero do you want to use?");
                        int heroChoice = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (heroChoice >= 0 && heroChoice < heroes.size()) {
                            Character hero = heroes.get(heroChoice);
                            System.out.println("Choose an enemy to attack:");
                            for (int i = 0; i < enemies.size(); i++) {
                                System.out.println((i + 1) + ". " + enemies.get(i));
                            }
                            int enemyChoice = scanner.nextInt() - 1;
                            scanner.nextLine();
                            if (enemyChoice >= 0 && enemyChoice < enemies.size()) {
                                Character enemy = enemies.get(enemyChoice);
                                int physicalDamage = (hero.physicalDamage - enemy.physicalDefence);
                                if (physicalDamage <= 0) {
                                    physicalDamage = 0;
                                }
                                int elementalDamage = (hero.elementalDamage - enemy.resilience);
                                if (elementalDamage <= 0) {
                                    elementalDamage = 0;
                                }
                                int totalDamage = physicalDamage + elementalDamage;
                                enemy.health -= totalDamage;
                                System.out.println(hero.name + " attacks " + enemy.name + " for " + totalDamage + " damage!");
                                if (enemy.health <= 0) {
                                    System.out.println(enemy.name + " has been killed!");
                                    enemies.remove(enemyChoice);
                                }
                            }
                        } else {
                            System.out.println("Invalid choice. Skipping turn.");
                        }

                    } else if (actionChoice.equals("2")) {
                        if (healthpotion > 0) {
                            System.out.println("Choose a hero to use your health potion:");
                            for (int i = 0; i < heroes.size(); i++) {
                                System.out.println((i + 1) + ". " + heroes.get(i));
                            }
                            int potionChoice = scanner.nextInt() - 1;
                            scanner.nextLine();
                            if (potionChoice >= 0 && potionChoice < heroes.size()) {
                                Character hero = heroes.get(potionChoice);
                                hero.health += 80;
                                healthpotion--;
                                System.out.println(hero.name + " uses a health potion and recovers 80 health.");
                            } else {
                                System.out.println("Invalid choice. Skipping turn.");

                            }
                        } else {
                            System.out.println("No health potions available. Skipping turn.");
                        }
                    }
                }

                while (!enemies.isEmpty()) {
                    System.out.println("\nEnemy's turn!");
                    int enemyChoice = (int) (Math.random() * enemies.size());
                    Character enemy = enemies.get(enemyChoice);
                    int heroChoice = (int) (Math.random() * heroes.size());
                    Character hero = heroes.get(heroChoice);
                    int pyhsicalDamage = (enemy.physicalDamage - hero.physicalDefence);
                    if (pyhsicalDamage <= 0) {
                        pyhsicalDamage = 0;
                    }
                    int elementalDamage = (enemy.elementalDamage - hero.resilience);
                    if (elementalDamage <= 0) {
                        elementalDamage = 0;
                    }
                    int totalDamage = pyhsicalDamage + elementalDamage;
                    hero.health -= totalDamage;
                    System.out.println(enemy.name + " attacks " + hero.name + " for " + totalDamage + " damage!");
                    if (hero.health <= 0) {
                        System.out.println(hero.name + " has been defeated!");
                        heroes.remove(heroChoice);
                    }
                }


                if (heroes.isEmpty()) {
                    System.out.println("\nYour team has been defeated. Game over!");
                } else {
                    System.out.println("\nYou have defeated all the enemies! Congratulations!");
                    gold += 100;
                    System.out.println("Do you want to go back to (town) or (continue) your journey? ");
                    String pathChoice2 = scanner.nextLine();
                    if (pathChoice2.equals("town")) {
                        System.out.println("Press 1 for buying an health potion(50 gold), press 2 for buying an mana potion(50 gold)\ntype exit for exiting ");
                        market(scanner);
                    } else if (pathChoice2.equals("continue")) {
                        System.out.println("Alma");
                        ;
                    } else {
                        System.out.println("Invalid choice. Skipping turn.");
                    }
                }
            }
        }

        public static void market (Scanner scanner){

            String marketchoice = scanner.nextLine();
            switch (marketchoice) {
                case "1":
                    gold -= 50;
                    healthpotion += 1;
                    break;
                case "2":
                    gold -= 50;
                    manapotion += 1;
                    break;
                case "exit":
                    return;

                default:
                    System.out.println("Invalid choice. Please choose correctly.");
            }
        }
}
