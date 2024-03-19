public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("You have to introduce a parameter");
            System.exit(-1);
        }

        int n;
        n = Integer.parseInt(args[0]);
        int[][] matrix = createMatrixForWheelGraph(n);
        System.out.println("Adjacency matrix for "+n+"-wheel graph is:");
        printMatrix(matrix);
        System.out.println("There are the following cycles:");
        findCycles(n);
    }

    public static int[][] createMatrixForWheelGraph(int n){
        int [][] matrix= new int[n][n];
        for (int i=0;i < n-1;i++) {
            matrix[i][i+1]=1;
            matrix[i+1][i]=1;
            matrix[i][n-1]=1;
            matrix[n-1][i]=1;
        }
        matrix[n-2][0]=1;
        matrix[0][n-2]=1;
        return matrix;
    }

    public static void printMatrix(int[][] matrix){
        for(int i=0;i <= matrix.length-1;i++){
            for(int j=0;j <= matrix.length-1;j++)
                System.out.print(matrix[i][j]+" ");
        System.out.print('\n');
        }
    }
    public static void findInnerCycles(int n, int k, int l){
        System.out.print(k);
        System.out.print(' ');
        System.out.print(n);
        System.out.print(' ');
        while(l > 0) {
            if(l+k >= n){
                System.out.print(l+k-n+1);
            }
            else
                System.out.print(l+k);
            System.out.print(' ');
            l--;
        }
        System.out.print(k);
        System.out.println();

    }

    public static void findCycles(int n){
        int total=1;
        for(int i=1;i <= n-1;i++) {
            System.out.print(i);
            System.out.print(' ');
        }
        System.out.print('1');
        System.out.print('\n');

        for(int k=1;k <= n-1;k++)
            for(int l=1;l <= n-2;l++) {
                findInnerCycles(n, k, l);
                total = total + 1;
            }
        System.out.println("In total are "+ total+" cycles");

    }


}