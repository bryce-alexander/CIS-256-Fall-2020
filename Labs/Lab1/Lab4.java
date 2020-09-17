public class Lab4 {

    static class X {
        public X(){
        }
        public void method(String test){
            System.out.println(test);
        }
        public static final String test = "Constant string";
        public static void override() {
            System.out.println("Method from superclass");
        }
    }

    static class Y extends X implements testInterface{
        public Y(){

        }
        public static void override() {
            System.out.println("Method from subclass");
        }
        public static void main(){
            //System.out.println(test);
            //System.out.println(X.test);
        }
    }

    interface testInterface {
        //public void method(String test);
        //public int method(String test);
        //public void method(int i);
        //public void method(String test2);
        public static final String test = "Constant string";
        //public static final String test = "Other constant string";
    }

    public static void main(String[] args) {

        //Part I: Assignments and casting

        // PART A: Compile Time
        /*
        Y array can be assigned to X array because Y is a subclass of X
        X array must be cast to a Y object array because an X is not implicitly a Y
        */
        //X xa[] = new X[3];
        //Y ya[] = new Y[3];
        //xa = ya;
        //ya = (Y[]) xa;


        // PART B: Runtime
        /*
        ya array cannot be assigned to X array at runtime, even when using casting.
        ya cannot then be assigned back because it fails initial assignment during runtime.
        */
        //X xa[] = new X[3];
        //Y ya[] = new Y[3];
        //ya = (Y[]) xa;
        //ya = xa;

        // PART C
        /*
        X array can be assigned to Y array and then assigned back as long as contents of X array are type X
        X array can be assigned back whether or not the contents are of type Y because Y is an instance of X
        */
        //X xa[] = new X[3];
        //Y ya[] = new Y[3];
        //X xa1[] = new X[3];
/*        xa[0] = new Y();
        xa[1] = new Y();
        xa[2] = new Y();*/
        //xa = ya;
        //xa = xa;
        //xa = (Y[]) xa;


        // Part II: Conflicting Declarations

        /*
        --Uncomment lines in testInterface to compare output--
        PART A: Java will compile the result if prototype is a match
        PART B: Java will not compile the result if different return types
        PART C: Java will not compile if return type is the same and parameter type is different
        PART D: Java will compile if signature is the same but with different parameter name
        */
        //Y y = new Y();


        // Part III: More Conflicting Declarations

        /*
        --Uncomment lines in testInterface and Y to compare output--
        PART A: Java compiles the result with the same name whether or not values match
        PART B: Java will not compile the result because the variable is ambiguous regardless of value match
        PART C: Main method can access the static class method in X if directly referenced (X.test)

        */
         //Y y = new Y();
         //y.main();


        // PART IV: Method Overriding
        /*
        PART A: Java will call the method from the subclass UNLESS the methods are static
        PART B: It would call subclass method except it fails casting at runtime
        PART C: If the method is static, you can call the superclass method from the subclass by casting
                it to the superclass.  This is not possible if the methods are not static unless the subclass
                method calls the superclass method.
        */

        //Y y = new Y();
        //y.override();
        //((X)y).override();

        //X x = new X();
        //((Y)x).override();








    }
}
