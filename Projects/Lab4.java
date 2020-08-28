public class Lab4 {

    static class X {
        public X(){
        }
        public void method(String test){
            System.out.println(test);
        }
    }

    static class Y extends X implements testInterface{
        public Y(){

        }
    }

    interface testInterface {
        //public void method(String test);
        //public int method(String test);
        //public void method(int i);
        public void method(String test2);
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
        //ya = ya1;

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

        */








    }
}
