import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Person person = new Person();
        person.talk();
        person.walkPerson();
        person.walkHuman();
        System.out.println("--------------------");
        Human human = new Person();
        human.talk();
        human.walkHuman();
        System.out.println("--------------------");
        person.child();
        person.parent();

        System.out.println("--------------------");
        Arrays.stream(Mevsim.values()).forEach(mevsim -> System.out.println(mevsim.ordinal() + " - " + mevsim.name() + " (" + mevsim.getMevsim() + ") [" + mevsim.havaSicakligi() + "]"));

    }


    private static class Human {

        public static int count = 5;

        private final String name;
        private final String lastname;

        public Human(String name, String lastname) {
            this.name = name;
            this.lastname = lastname;
        }

        protected void talk(){
            System.out.println("Human talked");
        }

        public void walkHuman(){
            System.out.println("Human walked");
        }

        protected static Number coding(){
            System.out.println("Human coding");
            return 1;
        }

        public void parent(){
            coding();
        }

    }


    private static class Person extends Human{

        public Person(){
            this("Ahmet");
        }

        public Person(String name) {
            this(name, "Kerem");
        }

        public Person(String name, String lastname) {
            super(name, lastname);
        }

        @Override
        protected void talk() {
            System.out.println("Person talked");
        }

        public void walkPerson(){
            System.out.println("Person walked");
        }

        public static Integer coding(){
            System.out.println("Person coding");
            return 0;
        }

        public void child(){
            coding();
        }

    }


    private enum Mevsim {

        ILKBAHAR("İlkbahar"){
            @Override
            public String havaSicakligi() {
                return "15 C";
            }
        },
        YAZ("Yaz") {
            @Override
            public String havaSicakligi() {
                return "30 C";
            }
        },
        SONBAHAR("Sonbahar") {
            @Override
            public String havaSicakligi() {
                return "10 C";
            }
        },
        KIS("Kış") {
            @Override
            public String havaSicakligi() {
                return "-10 C";
            }
        };

        private String mevsim;

        Mevsim(String mevsim){
            this.mevsim = mevsim;
        }

        public String getMevsim(){
            return this.mevsim;
        }

        public abstract String havaSicakligi();

    }
}



