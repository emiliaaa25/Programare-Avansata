public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("You have to introduce 3 parameters like: java Main <a> <b> <k>");
            System.exit(-1);
        }

        int a, b, k;
        a = Integer.parseInt(args[0]);
        b = Integer.parseInt(args[1]);
        k = Integer.parseInt(args[2]);
        if (a >= b) {
            System.out.println("This is not an interval. a must be smaller than b");
            System.exit(-1);
        }
long startTime=System.nanoTime();
        String kReductible = "";
        for (int i = a; i <= b; i++) {
            if (isKReductible(i, k)) {
                String temp = Integer.toString(i);
                kReductible += temp + " ";

            }
        }
        long finishTime=System.nanoTime();
        long time=finishTime-startTime;
        System.out.println("Running time is:"+time+" nanoseconds");
        System.out.println("K-reductibles numbers from "+a+ " to "+b +" are "+ kReductible);

    }

    private static boolean isKReductible(int x, int k) {
        while(x>9){
            int sum=0;
            int n=x;
            while(n!=0){
                int d = n%10;
                sum=sum+(d*d);
                n=n/10;
            }
            x=sum;
        }
        if(x==k)
            return true;
        else
            return false;
    }
}