public class Lab4 {

    static class X {

        public static final String something = "test1";

        public X(){
        }

    	/*
    	public void method(String test){
            System.out.println(test);
        }
        */


        public String method() {
            return something;
        }

    }

    static class Y extends X implements testInterface{

        public static final String something = "test2";

        public Y(){

        }

    	/*
    	public String main(){
    		return something;
    	}
    	*/

    	/*
    	public String main() {
    		return X.something;
    	}
    	*/


        public String method() {
            return something;
        }

    }

    interface testInterface {
        //public void method(String test);
        //public int method(String test);
        //public void method(char i);
        //public void method(String test2);
        //public String main();
        //public String method();
    }

    public static void main(String[] args) {

        //Part I Assignments and casting

        /*	PART A: Compile Time
         * Array Y can be assigned to Array X because Class x is a superclass of Class Y.
         * Array X has to be cast to a object in array type Class Y because an object of type Class X is not
         * implicitly an object of type Class Y
         */
        //X xa[] = new X[5];
        //Y ya[] = new Y[5];
        //xa = ya;
        //ya = (Y[]) xa;


        /*
         * PART B Runtime
         * ya array cannot be assigned to X array at runtime, even when using casting.
         * ya cannot then be assigned back because it fails initial assignment during runtime.
         */
        //X xa[] = new X[5];
        //Y ya[] = new Y[5];
        //ya = (Y[]) xa;
        //ya = ya1;


        /*
         * PART C
         * X array can be assigned to Y array and then assigned back as long as contents of X array are type X.
         * X array can be assigned back whether or not the contents are of type Y because Y is an instance of X.
         */
        //X xa[] = new X[5];
        //Y ya[] = new Y[5];
        //X xa1[] = new X[5];
        //xa[0] = new Y();
        //xa[1] = new Y();
        //xa[2] = new Y();
        //xa = ya;
        //xa = xa;
        //xa = (Y[]) xa;


        // Part II Conflicting Declarations

        //Y y = new Y();

        /* Part A:
        *  Java compiles result if prototype is a match exactly.
        */

        /* Part B:
        *  Java doesn't compile result if return types are different.
        */

        /* Part C:
        *  Java doesn't compile return if type are the same but parameter types are different.
        */

        /* PART D:
        * Java compiles signatures are the same but have different parameter names.
        */


        // Part III More Conflicting Declarations

        /*
         * Part A:
         * Java will compile if X and Y both have a public static final variable with the same name, because
         * static variables are not inherited or shared by different classes. They belong to the class which
         * declared them.
         * Returns test2
         * */
        //Y y = new Y();
        //y.main();

        /* Part B:
         * Java will compile if main method name is the same as superclass and Java interface. It will return
         * the constant within its own class.
         * Returns test1
         */
        //X x = new X();
        //Y y = new Y();

        /* Part C:
         * Java will compile main method. Returns public static variable from Y Class.
         * Returns test2
         */
        //Y y = new Y();
        //System.out.println(y.main());


        // Part IV: Method Overriding

        /* Part A:
         * Casting the object y to Class X be will return Class Y's public static variable. It does not cast
         * y to class X.
         */
        //Y y = new Y();
        //((X)y).method();

        /* Part B:
         * If Class X object is casted to Class Y before method is called, it will attempt to call the method
         * from the Class Y, which results in an exception, but such a method doesn't exits.
         * Returns Exception in thread "main" java.lang.ClassCastException:
         */
        //X x = new X();
        //((Y)x).method();

        /* Part C;
         * If Class Y object is casted to Class X before method is called, it will call the method from the
         * superclass (Class X) and return the static variable from Class X.
         * Returns test1*/
        //Y y = new Y();
        //((X)y).method();

    }
}