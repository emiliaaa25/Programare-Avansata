public class Main {
    public static void main(String[] args){
        System.out.println("Hello World!");
        String[] languages= {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1000000);
        int result=n*3;
        result=result+0b10101;
        result=result+0xFF;
        result*=6;
        while(result>=10)
        {
            result=result/10+result%10;
        }
        System.out.println("Willy-nilly, this semester I will learn "+languages[result]);

    }
}
